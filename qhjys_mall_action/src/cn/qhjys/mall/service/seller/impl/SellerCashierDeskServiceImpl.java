package cn.qhjys.mall.service.seller.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.qhjys.mall.common.AlipayService;
import cn.qhjys.mall.common.SzPfPost;
import cn.qhjys.mall.entity.AuthenticationChnnel;
import cn.qhjys.mall.entity.FqMicroOrder;
import cn.qhjys.mall.entity.FqMicroOrderExample;
import cn.qhjys.mall.entity.FqSellerStatement;
import cn.qhjys.mall.entity.FqSellerStatementExample;
import cn.qhjys.mall.entity.FqStoreCredit;
import cn.qhjys.mall.entity.FqStoreCreditExample;
import cn.qhjys.mall.entity.FqThirdPay;
import cn.qhjys.mall.entity.FqThirdPayExample;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.mapper.FqMicroOrderMapper;
import cn.qhjys.mall.mapper.FqSellerStatementMapper;
import cn.qhjys.mall.mapper.FqStoreCreditMapper;
import cn.qhjys.mall.mapper.FqThirdPayMapper;
import cn.qhjys.mall.mapper.SellerInfoMapper;
import cn.qhjys.mall.quartz.PushPayInfoTask;
import cn.qhjys.mall.service.AuthenticationChnnelService;
import cn.qhjys.mall.service.FqStoreRateService;
import cn.qhjys.mall.service.StoreService;
import cn.qhjys.mall.service.seller.SellerCashierDeskService;
import cn.qhjys.mall.szpf.SignUtils;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.DateUtil;
import cn.qhjys.mall.util.ms.BaseRequest;
import cn.qhjys.mall.util.ms.MsConstant;
import cn.qhjys.mall.weixin.qqpay.CQpayAPIURL;
import cn.qhjys.mall.weixin.qqpay.CQpayMchSpBase;
import cn.qhjys.mall.weixin.util.HttpClientUtil;
import cn.qhjys.mall.weixin.util.MD5Util;
import cn.qhjys.mall.weixin.util.OrderUtil;
import cn.qhjys.mall.weixin.util.RequestHandler;
import cn.qhjys.mall.weixin.util.SystemConstant;
import cn.qhjys.mall.weixin.util.XMLUtil;

import com.alibaba.fastjson.JSON;
import com.alipay.demo.trade.model.builder.AlipayTradePayRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPayResult;
import com.github.pagehelper.PageHelper;

@Service("SellerCashierDeskService")
public class SellerCashierDeskServiceImpl implements SellerCashierDeskService {

	protected final Logger logger = LoggerFactory.getLogger(SellerCashierDeskServiceImpl.class);

	@Autowired
	private FqThirdPayMapper fqThirdPayMapper;
	@Autowired
	private FqMicroOrderMapper fqmicroOrderMapper;
	@Autowired
	private FqStoreCreditMapper fqStoreCreditMapper;
	@Autowired
	private FqSellerStatementMapper fqSellerStatementMapper;
	@Autowired
	private SellerInfoMapper sellerInfoMapper;
	@Autowired
	private StoreService storeService;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	@Autowired
	private AuthenticationChnnelService authenticationChnnelService;

	@Autowired
	private FqStoreRateService fqStoreRateService;
	@Override
	public List<FqThirdPay> queryOrderList(Long sellerId) {
		FqThirdPayExample example = new FqThirdPayExample();
		if (null != sellerId) {
			example.createCriteria().andSellerIdEqualTo(sellerId);
		}
		example.setOrderByClause("pay_time DESC");
		PageHelper.startPage(1, 3);
		return fqThirdPayMapper.selectByExample(example);

	}

	/**
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  光大银行（微信支付） start
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 */
	
	@Override
	public FqThirdPay insertPayOrderByGdPay(Long sellerId, BigDecimal money, String authCode,
				       HttpServletRequest request, HttpServletResponse response) {
		try {
			String orderNo="";
			Integer payType = 0;
			Integer authCodeBegin = Integer.valueOf(authCode.substring(0, 2));
		    if(authCodeBegin>9 && authCodeBegin < 16) {
		    	 orderNo = OrderUtil.getOrderNo(SystemConstant.GD_WX_MICOR_PAY_TYPE);		//微信
		    	 payType = 1;
		    }else if(authCodeBegin == 28) {
		    	 orderNo = OrderUtil.getOrderNo(SystemConstant.GD_ALI_MICOR_PAY_TYPE);		//支付宝
		    	 payType = 2;
		    }
			
			FqMicroOrder microOrder = new FqMicroOrder();
			microOrder.setCreateTime(new Date());
			microOrder.setCxNum(6);
			microOrder.setMoney(money);
			microOrder.setOrderNo(orderNo);
			microOrder.setSellerId(sellerId);
			microOrder.setStatus(1);
			microOrder.setPayType(payType);
			fqmicroOrderMapper.insertSelective(microOrder);

			String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
			String MCH_ID = ConstantsConfigurer.getProperty(SystemConstant.GD_MCH_ID);
			String PAY_KEY = ConstantsConfigurer.getProperty(SystemConstant.GD_PAY_KEY);
			StoreInfo store = storeService.queryStoreInfoBySeller(sellerId);
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
			packageParams.put("service", "unified.trade.micropay");
			packageParams.put("mch_id", MCH_ID);
			packageParams.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
			packageParams.put("body", store.getName()+"-移动支付"); // 商品描述
			packageParams.put("out_trade_no", orderNo);
			int fee = money.multiply(new BigDecimal(100)).intValue();
			packageParams.put("total_fee", String.valueOf(fee));
			packageParams.put("mch_create_ip", request.getRemoteAddr());
			packageParams.put("auth_code", authCode);
			String sign = createSign(packageParams, PAY_KEY);

			packageParams.put("sign", sign);
			String requestXML = XMLUtil.getRequestXml(packageParams);
			String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
			logger.info(">>>>>>>>>>>>>>>>>>GD 扫码枪  MICRO 支付回调 参数xml=" + result);
			Map<String, String> signMap = new HashMap<String, String>();
			signMap = XMLUtil.doXMLParse(result);
			if (signMap == null) {
				logger.info(">>>>>>>>>>>>>>>>>>GD MICRO支付回调 参数 is null");
				microOrder.setStatus(1);
				microOrder.setCxNum(6);
				microOrder.setState("USERPAYING");
				fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
				return null;
			}

			logger.info(">>>>>>>>>>>>>>>>>>GD 扫码枪  MICRO 支付回调 参数Map=" + signMap.toString());

			if (!"0".equals(signMap.get("status"))) {
				logger.info(">>>>>>>>>>>>>>>>>>GD MICRO支付回调，通信异常：" + signMap.toString());
				microOrder.setStatus(1);
				microOrder.setCxNum(6);
				microOrder.setState("USERPAYING");
				fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
				return null;
			}
			String need_query = signMap.get("need_query") == null ? "N" : signMap.get("need_query").trim();
			if (!"0".equals(signMap.get("result_code"))) {
				if ("USERPAYING".equals(signMap.get("err_code")) || "10003".equals(signMap.get("err_code")) || need_query.equals("Y")) {
					microOrder.setStatus(1);
					microOrder.setCxNum(6);
					microOrder.setState("USERPAYING");
					fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
					logger.info("当前订单" + orderNo + "交易用户正在支付中，等待5秒后发起查询");
					// 等待5秒发起查询
					Thread.sleep(5000);
					int cxNum = 6;
					String trade_state = signMap.get("err_code");
					while (cxNum > 0) {
						Map<String,String> cxSignMap = doQueryPayState(orderNo,"PF");
						logger.info(">>>>>>>>>>>>>>>>>>GD MICRO循环查询订单回调 参数"+cxNum+">>>>>>>>" + cxSignMap.toString());
						if (isSign(cxSignMap,"GD")) {
							if ("0".equals(cxSignMap.get("status")) && "0".equals(cxSignMap.get("result_code"))) {
								 trade_state = cxSignMap.get("trade_state");
								if (("SUCCESS").equals(trade_state)) {
										// 交易成功
									logger.info("当前订单" + orderNo + "交易成功");
									FqThirdPay fqThirdPay =doHandlePaySuccess(sellerId,store,"GD",cxSignMap);
									
									return fqThirdPay;	
								} else if (trade_state.equals("USERPAYING") || trade_state.equals("10003")) {
									// 还未成功 等待10秒再次发起查询
									if(cxNum==1){
										microOrder.setCxNum(3);
										fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
									}else{
										microOrder.setCxNum(cxNum-1);
										fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
										logger.info("当前订单" + orderNo +"----------"+cxNum+ "交易用户正在支付中，再次等待10秒后发起查询");
									}
									Thread.sleep(10000);
									cxNum--;
								} else {
									if(cxNum!=1){
										microOrder.setCxNum(cxNum-1);
										fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
									}
									logger.info("当前订单" + orderNo +"----------"+cxNum+ "交易信息不明确，再次等待10秒后发起查询");
									Thread.sleep(10000);
									cxNum--;
								}
							}else{
								microOrder.setStatus(1);
								microOrder.setCxNum(6);
								microOrder.setState("ERROR");
								fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
								logger.info(">>>>>>>>>>>>>>>>>>GD MICRO支付查询回调失败:" + cxSignMap.toString());
							}
						}
					}
				}else{
					// err_code = 10003和文档返回不一致
					//{sign=C092E7966D4D60FA05E1D2897E369586, result_code=1, mch_id=103510002720, err_msg=付款码无效或等待用户输入密码, err_code=10003, status=0, sign_type=MD5, charset=UTF-8, appid=2016091901922958, need_query=Y, nonce_str=6be0a17a1e514f9abae3f75ed1f499c2, version=2.0}
					microOrder.setStatus(1);
					microOrder.setCxNum(6);
					microOrder.setState("ERROR");
					fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
					logger.info(">>>>>>>>>>>>>>>>>>GD MICRO支付回调失败:" + signMap.toString());
					return null;
				}
			} else {
				//交易成功 验证签名 处理逻辑
				if (isSign(signMap,"GD")) {
					if ("0".equals(signMap.get("status")) && "0".equals(signMap.get("result_code")) && "0".equals(signMap.get("pay_result"))) {
						// 交易成功
						FqThirdPay fqThirdPay = doHandlePaySuccess(sellerId,store,"GD",signMap);
						return fqThirdPay;
					}
				} else {
					logger.info(">>>>>>>>>>>>>>>>>>GD MICRO支付回调失败:签名验证失败");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	
//	@Override
//	public FqThirdPay insertPayOrderByGdPay(Long sellerId, BigDecimal money, String authCode,
//			HttpServletRequest request, HttpServletResponse response) {
//		try {
//			String orderNo = OrderUtil.getOrderNo(SystemConstant.GD_MICOR_PAY_TYPE);
//			FqMicroOrder microOrder = new FqMicroOrder();
//			microOrder.setCreateTime(new Date());
//			microOrder.setCxNum(0);
//			microOrder.setMoney(money);
//			microOrder.setOrderNo(orderNo);
//			microOrder.setSellerId(sellerId);
//			microOrder.setStatus(0);
//			microOrder.setPayType(1);
//			fqmicroOrderMapper.insertSelective(microOrder);
//
//			String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
//			String MCH_ID = ConstantsConfigurer.getProperty(SystemConstant.GD_MCH_ID);
//			String PAY_KEY = ConstantsConfigurer.getProperty(SystemConstant.GD_PAY_KEY);
//			StoreInfo store = storeService.queryStoreInfoBySeller(sellerId);
//			SortedMap<String, String> packageParams = new TreeMap<String, String>();
//			packageParams.put("service", "unified.trade.micropay");
//			packageParams.put("mch_id", MCH_ID);
//			packageParams.put("nonce_str", UUID.randomUUID().toString().replace("-", "")); // 商家号
//			packageParams.put("body", store.getName()+"-移动支付"); // 商品描述
//			packageParams.put("out_trade_no", orderNo);
//			int fee = money.multiply(new BigDecimal(100)).intValue();
//			packageParams.put("total_fee", String.valueOf(fee));
//			packageParams.put("mch_create_ip", request.getRemoteAddr());
//			packageParams.put("auth_code", authCode);
//			String sign = createSign(packageParams, PAY_KEY);
//			;
//			packageParams.put("sign", sign);
//			String requestXML = XMLUtil.getRequestXml(packageParams);
//			String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
//			Map<String, String> signMap = new HashMap<String, String>();
//			signMap = XMLUtil.doXMLParse(result);
//			if (signMap == null) {
//				logger.info(">>>>>>>>>>>>>>>>>>GD MICRO支付回调 参数 is null");
//				microOrder.setStatus(1);
//				microOrder.setCxNum(6);
//				microOrder.setState("USERPAYING");
//				fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
//				return null;
//			}
//
//			logger.info(">>>>>>>>>>>>>>>>>>GD MICRO支付回调 参数" + signMap.toString());
//
//			if (!"0".equals(signMap.get("status"))) {
//				logger.info(">>>>>>>>>>>>>>>>>>GD MICRO支付回调，通信异常：" + signMap.toString());
//				microOrder.setStatus(1);
//				microOrder.setCxNum(6);
//				microOrder.setState("USERPAYING");
//				fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
//				return null;
//			}
//			String need_query = signMap.get("need_query") == null ? "N" : signMap.get("need_query").trim();
//			if (!"0".equals(signMap.get("result_code"))) {
//				if ("USERPAYING".equals(signMap.get("err_code")) || "10003".equals(signMap.get("err_code")) || need_query.equals("Y")) {
//					microOrder.setStatus(1);
//					microOrder.setCxNum(6);
//					microOrder.setState("USERPAYING");
//					fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
//					logger.info("当前订单" + orderNo + "交易用户正在支付中，等待5秒后发起查询");
//					// 等待5秒发起查询
//					Thread.sleep(5000);
//					int cxNum = 6;
//					String trade_state = signMap.get("err_code");
//					while (cxNum > 0) {
//						Map<String,String> cxSignMap = doQueryPayState(orderNo,"GD");
//						logger.info(">>>>>>>>>>>>>>>>>>GD MICRO循环查询订单回调 参数"+cxNum+">>>>>>>>" + cxSignMap.toString());
//						if (isSign(cxSignMap,"GD")) {
//							if ("0".equals(cxSignMap.get("status")) && "0".equals(cxSignMap.get("result_code"))) {
//								
//									 trade_state = cxSignMap.get("trade_state");
//									 if (("SUCCESS").equals(trade_state)) {
//											// 交易成功	
//										 logger.info("当前订单" + orderNo + "交易成功");
//										FqThirdPay fqThirdPay = doHandlePaySuccess(sellerId,store,"GD",cxSignMap);
//										return fqThirdPay;	
//									} else if (trade_state.equals("USERPAYING") || trade_state.equals("10003")) {
//										// 还未成功 等待10秒再次发起查询
//										microOrder.setCxNum(cxNum-1);
//										fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
//										logger.info("当前订单" + orderNo + "交易用户正在支付中，再次等待10秒后发起查询");
//										Thread.sleep(10000);
//										cxNum--;
//									} else {
//										if(cxNum!=1){
//											microOrder.setCxNum(cxNum-1);
//											fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
//										}
//										logger.info("当前订单" + orderNo +"----------"+cxNum+ "交易信息不明确，再次等待10秒后发起查询");
//										Thread.sleep(10000);
//										cxNum--;
//									}
//								 }else{
//										microOrder.setStatus(1);
//										microOrder.setCxNum(6);
//										microOrder.setState("ERROR");
//										fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
//										logger.info(">>>>>>>>>>>>>>>>>>GD MICRO支付查询回调失败:" + cxSignMap.toString());
//										return null;
//									}
//						}
//					}
//				}else{
//					// err_code = 10003和文档返回不一致
//					//{sign=C092E7966D4D60FA05E1D2897E369586, result_code=1, mch_id=103510002720, err_msg=付款码无效或等待用户输入密码, err_code=10003, status=0, sign_type=MD5, charset=UTF-8, appid=2016091901922958, need_query=Y, nonce_str=6be0a17a1e514f9abae3f75ed1f499c2, version=2.0}
//					microOrder.setStatus(1);
//					microOrder.setCxNum(6);
//					microOrder.setState("ERROR");
//					fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
//					logger.info(">>>>>>>>>>>>>>>>>>PF MICRO支付回调失败:" + signMap.toString());
//					return null;
//				}
//			} else {
//				//交易成功 验证签名 处理逻辑
//				if (isSign(signMap,"GD")) {
//					if ("0".equals(signMap.get("status")) && "0".equals(signMap.get("result_code")) && "0".equals(signMap.get("pay_result"))) {
//						// 交易成功
//						FqThirdPay fqThirdPay = doHandlePaySuccess(sellerId,store,"GD",signMap);
//						return fqThirdPay;
//					}
//				} else {
//					logger.info(">>>>>>>>>>>>>>>>>>GD MICRO支付回调失败:签名验证失败");
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//		return null;
//	}
	
	
	
	/**
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * 光大银行微信支付 end 
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 */
	
	/**
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * 浦发银行（微信，支付宝） start
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 */
	@Override
	public FqThirdPay insertPayOrderByPFPay(Long sellerId, BigDecimal money, String authCode,
				       HttpServletRequest request, HttpServletResponse response) {
		try {
			String orderNo="";
			Integer payType = 0;
			Integer authCodeBegin = Integer.valueOf(authCode.substring(0, 2));
		    if(authCodeBegin>9 && authCodeBegin < 16) {
		    	 orderNo = OrderUtil.getOrderNo(SystemConstant.PF_WX_MICOR_PAY_TYPE);		//微信
		    	 payType = 1;
		    }else if(authCodeBegin == 28) {
		    	 orderNo = OrderUtil.getOrderNo(SystemConstant.PF_ALI_MICOR_PAY_TYPE);		//支付宝
		    	 payType = 2;
		    }
			
			FqMicroOrder microOrder = new FqMicroOrder();
			microOrder.setCreateTime(new Date());
			microOrder.setCxNum(6);
			microOrder.setMoney(money);
			microOrder.setOrderNo(orderNo);
			microOrder.setSellerId(sellerId);
			microOrder.setStatus(1);
			microOrder.setPayType(payType);
			fqmicroOrderMapper.insertSelective(microOrder);

			String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
			String MCH_ID = ConstantsConfigurer.getProperty(SystemConstant.PF_MCH_ID);
			String PAY_KEY = ConstantsConfigurer.getProperty(SystemConstant.PF_PAY_KEY);
			StoreInfo store = storeService.queryStoreInfoBySeller(sellerId);
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
			packageParams.put("service", "unified.trade.micropay");
			packageParams.put("mch_id", MCH_ID);
			packageParams.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
			packageParams.put("body", store.getName()+"-移动支付"); // 商品描述
			packageParams.put("out_trade_no", orderNo);
			int fee = money.multiply(new BigDecimal(100)).intValue();
			packageParams.put("total_fee", String.valueOf(fee));
			packageParams.put("mch_create_ip", request.getRemoteAddr());
			packageParams.put("auth_code", authCode);
			String sign = createSign(packageParams, PAY_KEY);

			packageParams.put("sign", sign);
			String requestXML = XMLUtil.getRequestXml(packageParams);
			String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
			logger.info(">>>>>>>>>>>>>>>>>>PF 扫码枪  MICRO 支付回调 参数xml=" + result);
			Map<String, String> signMap = new HashMap<String, String>();
			signMap = XMLUtil.doXMLParse(result);
			if (signMap == null) {
				logger.info(">>>>>>>>>>>>>>>>>>PF MICRO支付回调 参数 is null");
				microOrder.setStatus(1);
				microOrder.setCxNum(6);
				microOrder.setState("USERPAYING");
				fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
				return null;
			}

			logger.info(">>>>>>>>>>>>>>>>>>PF 扫码枪  MICRO 支付回调 参数Map=" + signMap.toString());

			if (!"0".equals(signMap.get("status"))) {
				logger.info(">>>>>>>>>>>>>>>>>>PF MICRO支付回调，通信异常：" + signMap.toString());
				microOrder.setStatus(1);
				microOrder.setCxNum(6);
				microOrder.setState("USERPAYING");
				fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
				return null;
			}
			String need_query = signMap.get("need_query") == null ? "N" : signMap.get("need_query").trim();
			if (!"0".equals(signMap.get("result_code"))) {
				if ("USERPAYING".equals(signMap.get("err_code")) || "10003".equals(signMap.get("err_code")) || need_query.equals("Y")) {
					microOrder.setStatus(1);
					microOrder.setCxNum(6);
					microOrder.setState("USERPAYING");
					fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
					logger.info("当前订单" + orderNo + "交易用户正在支付中，等待5秒后发起查询");
					// 等待5秒发起查询
					Thread.sleep(5000);
					int cxNum = 6;
					String trade_state = signMap.get("err_code");
					while (cxNum > 0) {
						Map<String,String> cxSignMap = doQueryPayState(orderNo,"PF");
						logger.info(">>>>>>>>>>>>>>>>>>PF MICRO循环查询订单回调 参数"+cxNum+">>>>>>>>" + cxSignMap.toString());
						if (isSign(cxSignMap,"PF")) {
							if ("0".equals(cxSignMap.get("status")) && "0".equals(cxSignMap.get("result_code"))) {
								 trade_state = cxSignMap.get("trade_state");
								if (("SUCCESS").equals(trade_state)) {
										// 交易成功
									logger.info("当前订单" + orderNo + "交易成功");
									FqThirdPay fqThirdPay =doHandlePaySuccess(sellerId,store,"PF",cxSignMap);
									
									return fqThirdPay;	
								} else if (trade_state.equals("USERPAYING") || trade_state.equals("10003")) {
									// 还未成功 等待10秒再次发起查询
									microOrder.setCxNum(cxNum-1);
									fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
									logger.info("当前订单" + orderNo +"----------"+cxNum+ "交易用户正在支付中，再次等待10秒后发起查询");
									Thread.sleep(10000);
									cxNum--;
								} else {
									if(cxNum!=1){
										microOrder.setCxNum(cxNum-1);
										fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
									}
									logger.info("当前订单" + orderNo +"----------"+cxNum+ "交易信息不明确，再次等待10秒后发起查询");
									Thread.sleep(10000);
									cxNum--;
								}
							}else{
								microOrder.setStatus(1);
								microOrder.setCxNum(6);
								microOrder.setState("ERROR");
								fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
								logger.info(">>>>>>>>>>>>>>>>>>PF MICRO支付查询回调失败:" + cxSignMap.toString());
							}
						}
					}
				}else{
					// err_code = 10003和文档返回不一致
					//{sign=C092E7966D4D60FA05E1D2897E369586, result_code=1, mch_id=103510002720, err_msg=付款码无效或等待用户输入密码, err_code=10003, status=0, sign_type=MD5, charset=UTF-8, appid=2016091901922958, need_query=Y, nonce_str=6be0a17a1e514f9abae3f75ed1f499c2, version=2.0}
					microOrder.setStatus(1);
					microOrder.setCxNum(6);
					microOrder.setState("ERROR");
					fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
					logger.info(">>>>>>>>>>>>>>>>>>PF MICRO支付回调失败:" + signMap.toString());
					return null;
				}
			} else {
				//交易成功 验证签名 处理逻辑
				if (isSign(signMap,"PF")) {
					if ("0".equals(signMap.get("status")) && "0".equals(signMap.get("result_code")) && "0".equals(signMap.get("pay_result"))) {
						// 交易成功
						FqThirdPay fqThirdPay = doHandlePaySuccess(sellerId,store,"PF",signMap);
						return fqThirdPay;
					}
				} else {
					logger.info(">>>>>>>>>>>>>>>>>>GD MICRO支付回调失败:签名验证失败");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * 浦发银行（微信，支付宝） end
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 */
	
	/**
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * 兴业银行的（微信，支付宝） start
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 */
	public FqThirdPay insertPayOrderByXYPay(Long sellerId, BigDecimal money, String authCode,
				       HttpServletRequest request, HttpServletResponse response) {
		try {
			String  orderNo = "";
			Integer payType = 0;
			Integer authCodeBegin = Integer.valueOf(authCode.substring(0, 2));
		    if(authCodeBegin>9 && authCodeBegin < 16) {
		    	orderNo = OrderUtil.getOrderNo(SystemConstant.XY_WX_MICOR_PAY_TYPE);
		    	 payType = 1;
		    }else if(authCodeBegin == 28) {
		    	orderNo = OrderUtil.getOrderNo(SystemConstant.XY_ALI_MICOR_PAY_TYPE);
		    	 payType = 2;
		    }
			
			FqMicroOrder microOrder = new FqMicroOrder();
			microOrder.setCreateTime(new Date());
			microOrder.setCxNum(6);
			microOrder.setMoney(money);
			microOrder.setOrderNo(orderNo);
			microOrder.setSellerId(sellerId);
			microOrder.setStatus(1);
			microOrder.setPayType(payType);
			fqmicroOrderMapper.insertSelective(microOrder);

			String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
			StoreInfo store = storeService.queryStoreInfoBySeller(sellerId);
			String MCH_ID = authenticationChnnelService.findByXyAuthcationInfo(store.getId()).getXyMerchantNum();
			String PAY_KEY = authenticationChnnelService.findByXyAuthcationInfo(store.getId()).getXyBankKey();
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
			packageParams.put("service", "unified.trade.micropay");
			packageParams.put("mch_id", MCH_ID);
			packageParams.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
			packageParams.put("body", store.getName()+"-移动支付"); // 商品描述
			packageParams.put("out_trade_no", orderNo);
			int fee = money.multiply(new BigDecimal(100)).intValue();
			packageParams.put("total_fee", String.valueOf(fee));
			packageParams.put("mch_create_ip", request.getRemoteAddr());
			packageParams.put("auth_code", authCode);
			String sign = createSign(packageParams, PAY_KEY);

			packageParams.put("sign", sign);
			String requestXML = XMLUtil.getRequestXml(packageParams);
			String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
			logger.info(">>>>>>>>>>>>>>>>>>XY 扫码枪  MICRO 支付回调 参数xml=" + result);
			Map<String, String> signMap = new HashMap<String, String>();
			signMap = XMLUtil.doXMLParse(result);
			if (signMap == null) {
				logger.info(">>>>>>>>>>>>>>>>>>XY MICRO支付回调 参数 is null");
				microOrder.setStatus(1);
				microOrder.setCxNum(6);
				microOrder.setState("USERPAYING");
				fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
				return null;
			}

			logger.info(">>>>>>>>>>>>>>>>>>XY 扫码枪  MICRO 支付回调 参数Map=" + signMap.toString());

			if (!"0".equals(signMap.get("status"))) {
				logger.info(">>>>>>>>>>>>>>>>>>XY MICRO支付回调，通信异常：" + signMap.toString());
				microOrder.setStatus(1);
				microOrder.setCxNum(6);
				microOrder.setState("USERPAYING");
				fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
				return null;
			}
			String need_query = signMap.get("need_query") == null ? "N" : signMap.get("need_query").trim();
			if (!"0".equals(signMap.get("result_code"))) {
				if ("USERPAYING".equals(signMap.get("err_code")) || "10003".equals(signMap.get("err_code")) || need_query.equals("Y")) {
					microOrder.setStatus(1);
					microOrder.setCxNum(6);
					microOrder.setState("USERPAYING");
					fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
					logger.info("当前订单" + orderNo + "交易用户正在支付中，等待5秒后发起查询");
					// 等待5秒发起查询
					Thread.sleep(5000);
					int cxNum = 6;
					String trade_state = signMap.get("err_code");
					while (cxNum > 0) {
						Map<String,String> cxSignMap = doQueryPayStateXY(orderNo,store.getId());
						logger.info(">>>>>>>>>>>>>>>>>>XY MICRO循环查询订单回调 参数"+cxNum+">>>>>>>>" + cxSignMap.toString());
							if ("0".equals(cxSignMap.get("status")) && "0".equals(cxSignMap.get("result_code"))) {
								 trade_state = cxSignMap.get("trade_state");
								if (("SUCCESS").equals(trade_state)) {
										// 交易成功
									logger.info("当前订单" + orderNo + "交易成功");
									FqThirdPay fqThirdPay =doHandlePaySuccessXY(sellerId,store,cxSignMap);
									
									return fqThirdPay;	
								} else if (trade_state.equals("USERPAYING") || trade_state.equals("10003")) {
									// 还未成功 等待10秒再次发起查询
									microOrder.setCxNum(cxNum-1);
									fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
									logger.info("当前订单" + orderNo +"----------"+cxNum+ "交易用户正在支付中，再次等待10秒后发起查询");
									Thread.sleep(10000);
									cxNum--;
								} else {
									if(cxNum!=1){
										microOrder.setCxNum(cxNum-1);
										fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
									}
									logger.info("当前订单" + orderNo +"----------"+cxNum+ "交易信息不明确，再次等待10秒后发起查询");
									Thread.sleep(10000);
									cxNum--;
								}
							}else{
								microOrder.setStatus(1);
								microOrder.setCxNum(6);
								microOrder.setState("ERROR");
								fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
								logger.info(">>>>>>>>>>>>>>>>>>XY MICRO支付查询回调失败:" + cxSignMap.toString());
							}
						}
					}
			} else {
				//交易成功 验证签名 处理逻辑
					if ("0".equals(signMap.get("status")) && "0".equals(signMap.get("result_code")) && "0".equals(signMap.get("pay_result"))) {
						// 交易成功
						FqThirdPay fqThirdPay = doHandlePaySuccessXY(sellerId,store,signMap);
						return fqThirdPay;
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * 兴业银行的（微信，支付宝） end
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 */
	
	/**
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * 兴业银行的（微信，支付宝） start 二清    WX 98  AL 97
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 */
	public FqThirdPay insertPayOrderByXYEQPay(Long sellerId, BigDecimal money, String authCode,
				       HttpServletRequest request, HttpServletResponse response) {
		try {
			String  orderNo = "";
			Integer payType = 0;
			Integer authCodeBegin = Integer.valueOf(authCode.substring(0, 2));
		    if(authCodeBegin>9 && authCodeBegin < 16) {
		    	orderNo = OrderUtil.getOrderNo(SystemConstant.XY_WX_MICOR_EQPAY_TYPE);
		    	payType = 11;
		    }else if(authCodeBegin == 28) {
		    	orderNo = OrderUtil.getOrderNo(SystemConstant.XY_ALI_MICOR_EQPAY_TYPE);
		    	 payType = 12;
		    }
			
			FqMicroOrder microOrder = new FqMicroOrder();
			microOrder.setCreateTime(new Date());
			microOrder.setCxNum(6);
			microOrder.setMoney(money);
			microOrder.setOrderNo(orderNo);
			microOrder.setSellerId(sellerId);
			microOrder.setStatus(1);
			microOrder.setPayType(payType);
			fqmicroOrderMapper.insertSelective(microOrder);

			String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
			StoreInfo store = storeService.queryStoreInfoBySeller(sellerId);
//			String MCH_ID = authenticationChnnelService.findByXyAuthcationInfo(store.getId()).getXyMerchantNum();
//			String PAY_KEY = authenticationChnnelService.findByXyAuthcationInfo(store.getId()).getXyBankKey();
			String MCH_ID = ConstantsConfigurer.getProperty("xy_eqpay_mch_id");
			String PAY_KEY = ConstantsConfigurer.getProperty("xy_eqpay_key");
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
			packageParams.put("service", "unified.trade.micropay");
			packageParams.put("mch_id", MCH_ID);
			packageParams.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
			packageParams.put("body", store.getName()+"-移动支付"); // 商品描述
			packageParams.put("out_trade_no", orderNo);
			int fee = money.multiply(new BigDecimal(100)).intValue();
			packageParams.put("total_fee", String.valueOf(fee));
			packageParams.put("mch_create_ip", request.getRemoteAddr());
			packageParams.put("auth_code", authCode);
			String sign = createSign(packageParams, PAY_KEY);

			packageParams.put("sign", sign);
			String requestXML = XMLUtil.getRequestXml(packageParams);
			String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
			logger.info(">>>>>>>>>>>>>>>>>>XY 扫码枪  MICRO 支付回调 参数xml=" + result);
			Map<String, String> signMap = new HashMap<String, String>();
			signMap = XMLUtil.doXMLParse(result);
			if (signMap == null) {
				logger.info(">>>>>>>>>>>>>>>>>>XY MICRO支付回调 参数 is null");
				microOrder.setStatus(1);
				microOrder.setCxNum(6);
				microOrder.setState("USERPAYING");
				fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
				return null;
			}

			logger.info(">>>>>>>>>>>>>>>>>>XY 扫码枪  MICRO 支付回调 参数Map=" + signMap.toString());

			if (!"0".equals(signMap.get("status"))) {
				logger.info(">>>>>>>>>>>>>>>>>>XY MICRO支付回调，通信异常：" + signMap.toString());
				microOrder.setStatus(1);
				microOrder.setCxNum(6);
				microOrder.setState("USERPAYING");
				fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
				return null;
			}
			String need_query = signMap.get("need_query") == null ? "N" : signMap.get("need_query").trim();
			if (!"0".equals(signMap.get("result_code"))) {
				if ("USERPAYING".equals(signMap.get("err_code")) || "10003".equals(signMap.get("err_code")) || need_query.equals("Y")) {
					microOrder.setStatus(1);
					microOrder.setCxNum(6);
					microOrder.setState("USERPAYING");
					fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
					logger.info("当前订单" + orderNo + "交易用户正在支付中，等待5秒后发起查询");
					// 等待5秒发起查询
					Thread.sleep(5000);
					int cxNum = 6;
					String trade_state = signMap.get("err_code");
					while (cxNum > 0) {
						Map<String,String> cxSignMap = doQueryPayStateEQXY(orderNo,store.getId());
						logger.info(">>>>>>>>>>>>>>>>>>XY MICRO循环查询订单回调 参数"+cxNum+">>>>>>>>" + cxSignMap.toString());
							if ("0".equals(cxSignMap.get("status")) && "0".equals(cxSignMap.get("result_code"))) {
								 trade_state = cxSignMap.get("trade_state");
								if (("SUCCESS").equals(trade_state)) {
										// 交易成功
									logger.info("当前订单" + orderNo + "交易成功");
									FqThirdPay fqThirdPay =doHandlePaySuccessEQXY(sellerId,store,cxSignMap);
									
									return fqThirdPay;	
								} else if (trade_state.equals("USERPAYING") || trade_state.equals("10003")) {
									// 还未成功 等待10秒再次发起查询
									microOrder.setCxNum(cxNum-1);
									fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
									logger.info("当前订单" + orderNo +"----------"+cxNum+ "交易用户正在支付中，再次等待10秒后发起查询");
									Thread.sleep(10000);
									cxNum--;
								} else {
									if(cxNum!=1){
										microOrder.setCxNum(cxNum-1);
										fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
									}
									logger.info("当前订单" + orderNo +"----------"+cxNum+ "交易信息不明确，再次等待10秒后发起查询");
									Thread.sleep(10000);
									cxNum--;
								}
							}else{
								microOrder.setStatus(1);
								microOrder.setCxNum(6);
								microOrder.setState("ERROR");
								fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
								logger.info(">>>>>>>>>>>>>>>>>>XY MICRO支付查询回调失败:" + cxSignMap.toString());
							}
						}
					}
			} else {
				//交易成功 验证签名 处理逻辑
					if ("0".equals(signMap.get("status")) && "0".equals(signMap.get("result_code")) && "0".equals(signMap.get("pay_result"))) {
						// 交易成功
						FqThirdPay fqThirdPay = doHandlePaySuccessEQXY(sellerId,store,signMap);
						return fqThirdPay;
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * 兴业银行的（微信，支付宝） end   二清
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 */
	
	
	
	/**
	 * 光大银行 浦发银行 公用方法
	 * 
	 * @param packageParams
	 * @param key
	 * @return
	 */
	private String createSign(SortedMap<String, String> packageParams, String key) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + key);
		String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();

		return sign;

	}

	/**
	 * 光大银行 浦发银行 公用方法
	 * @param paramsMap
	 * @param bankType  GD光大验签   PF浦发验签
	 * @return
	 * @throws Exception
	 */
	private boolean isSign(Map<String, String> paramsMap,String bankType) throws Exception {

		boolean flag = false;
		String pkey="";
		if(bankType.equals("GD")){
			pkey = ConstantsConfigurer.getProperty(SystemConstant.GD_PAY_KEY);
		}else if(bankType.equals("PF")){
			pkey = ConstantsConfigurer.getProperty(SystemConstant.PF_PAY_KEY);
		}
		
		// String pkey = "";
		RequestHandler reqHandler = new RequestHandler(null, null);
		// 初始化
		reqHandler.init(null, null, null, null, pkey);

		// 转换成sortedMap
		SortedMap<String, String> signMap = new TreeMap<String, String>();
		for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
			signMap.put(entry.getKey(), entry.getValue());
		}

		String signValid = reqHandler.createSign(signMap);
		String sign = paramsMap.get("sign");
		if (signValid.equals(sign)) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 光大银行 浦发银行 公用方法
	 * 查询订单状态
	 * @param orderNo
	 * @param bankType PF浦发银行   GD光大银行
	 * @return
	 * @throws Exception
	 */
	private Map<String, String> doQueryPayState(String orderNo,String bankType) throws Exception{

		String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
		String MCH_ID = "";
		String PAY_KEY = "";
		if(bankType.equals("GD")){
			 MCH_ID = ConstantsConfigurer.getProperty(SystemConstant.GD_MCH_ID);
			 PAY_KEY = ConstantsConfigurer.getProperty(SystemConstant.GD_PAY_KEY);
		}else if(bankType.equals("PF")){
			 MCH_ID = ConstantsConfigurer.getProperty(SystemConstant.PF_MCH_ID);
			 PAY_KEY = ConstantsConfigurer.getProperty(SystemConstant.PF_PAY_KEY);
		}
		
		SortedMap<String, String> cxQueryParams = new TreeMap<String, String>();
		cxQueryParams.put("service", "unified.trade.query");
		cxQueryParams.put("mch_id", MCH_ID);
		cxQueryParams.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
		cxQueryParams.put("out_trade_no", orderNo);
		String sign = createSign(cxQueryParams, PAY_KEY);

		cxQueryParams.put("sign", sign);
		String requestXML = XMLUtil.getRequestXml(cxQueryParams);
		String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
		Map<String, String> cxSignMap = XMLUtil.doXMLParse(result);
		if (!"0".equals(cxSignMap.get("status"))) {
			logger.info(">>>>>>>>>>>>>>>>>>GD MICRO SELECT，通信异常：" + cxSignMap.toString());
			return null;
		}
		if (!"0".equals(cxSignMap.get("result_code"))) {
			logger.info(">>>>>>>>>>>>>>>>>>MICRO SELECT失败:" + cxSignMap.toString());
			return null;
		}
         return cxSignMap;		
	}
	
	private Map<String, String> doQueryPayStateXY(String orderNo,Long storeId) throws Exception{

		String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
		
		String MCH_ID = authenticationChnnelService.findByXyAuthcationInfo(storeId).getXyMerchantNum();
		String PAY_KEY = authenticationChnnelService.findByXyAuthcationInfo(storeId).getXyBankKey();
		
		SortedMap<String, String> cxQueryParams = new TreeMap<String, String>();
		cxQueryParams.put("service", "unified.trade.query");
		cxQueryParams.put("mch_id", MCH_ID);
		cxQueryParams.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
		cxQueryParams.put("out_trade_no", orderNo);
		String sign = createSign(cxQueryParams, PAY_KEY);

		cxQueryParams.put("sign", sign);
		String requestXML = XMLUtil.getRequestXml(cxQueryParams);
		String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
		Map<String, String> cxSignMap = XMLUtil.doXMLParse(result);
		if (!"0".equals(cxSignMap.get("status"))) {
			logger.info(">>>>>>>>>>>>>>>>>>XY MICRO SELECT，通信异常：" + cxSignMap.toString());
			return null;
		}
		if (!"0".equals(cxSignMap.get("result_code"))) {
			logger.info(">>>>>>>>>>>>>>>>>>MICRO SELECT失败:" + cxSignMap.toString());
			return null;
		}
         return cxSignMap;		
	}
	
	private Map<String, String> doQueryPayStateEQXY(String orderNo,Long storeId) throws Exception{

		String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
		
		String MCH_ID = ConstantsConfigurer.getProperty("xy_eqpay_mch_id");
		String PAY_KEY = ConstantsConfigurer.getProperty("xy_eqpay_key");
		
		SortedMap<String, String> cxQueryParams = new TreeMap<String, String>();
		cxQueryParams.put("service", "unified.trade.query");
		cxQueryParams.put("mch_id", MCH_ID);
		cxQueryParams.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
		cxQueryParams.put("out_trade_no", orderNo);
		String sign = createSign(cxQueryParams, PAY_KEY);

		cxQueryParams.put("sign", sign);
		String requestXML = XMLUtil.getRequestXml(cxQueryParams);
		String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
		Map<String, String> cxSignMap = XMLUtil.doXMLParse(result);
		if (!"0".equals(cxSignMap.get("status"))) {
			logger.info(">>>>>>>>>>>>>>>>>>XY MICRO SELECT，通信异常：" + cxSignMap.toString());
			return null;
		}
		if (!"0".equals(cxSignMap.get("result_code"))) {
			logger.info(">>>>>>>>>>>>>>>>>>MICRO SELECT失败:" + cxSignMap.toString());
			return null;
		}
         return cxSignMap;		
	}
	
	private Map<String, String> doQueryPayStateWX(String orderNo) throws Exception{
		
		String JSAPI_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
		String appId = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_APPID);
		String partner = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_PAY_PARTNER);
		String pkey = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_PAY_PKEY);
		
		
		SortedMap<String, String> cxQueryParams = new TreeMap<String, String>();
		cxQueryParams.put("appid", appId);
		cxQueryParams.put("mch_id", partner);
		cxQueryParams.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
		cxQueryParams.put("out_trade_no", orderNo);
		String sign = createSign(cxQueryParams, pkey);
		cxQueryParams.put("sign", sign);
		
		String requestXML = XMLUtil.getRequestXml(cxQueryParams);
		String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
		Map<String, String> cxSignMap = XMLUtil.doXMLParse(result);
		if ("FAIL".equals(cxSignMap.get("return_code"))) {
			logger.info(">>>>>>>>>>>>>>>>>>WX MICRO SELECT，通信异常：" + cxSignMap.toString());
			return null;
		}
		
		return cxSignMap;		
	}
	
	/**
	 *  光大银行 浦发银行 公用方法
	 *  处理支付交易成功后的业务逻辑
	 *  
	 *  bank  PF浦发银行   GD光大银行
	 * @param signMap 支付结果map
	 * @throws Exception 
	 */
	private FqThirdPay doHandlePaySuccess(Long  sellerId,StoreInfo store,String bank, Map<String, String> signMap) throws Exception{
			
			String weixinOrderNo =signMap.get("transaction_id") ;			
			String openid = signMap.get("sub_openid");
			String payTime = signMap.get("time_end");	
			/**
			 *  pay.weixin.micropay
			 *	pay.alipay.micropay
			 *	pay.jdpay.micropay
			 *	pay.qq.micropay
			 */
			String tradeType =  signMap.get("trade_type");
			
			if (StringUtils.isEmpty(openid)) {
				openid = signMap.get("openid");
			}
			String orderNo = signMap.get("out_trade_no");
			String totalFee = signMap.get("total_fee");
			BigDecimal money = BigDecimal.valueOf(Long.valueOf(totalFee)).divide(new BigDecimal(100)); //支付结果中的金额单位为分;
			
			BigDecimal calTotamt =  money;
			BigDecimal rate = new BigDecimal(0); 
			BigDecimal rateMoney = new BigDecimal(0); 
			//查询本店费率
			Integer type = 1;
			if(tradeType.contains("weixin")){
				 type = 1;
			}
			if(tradeType.contains("alipay")){
				type = 2;
			}
			HashMap<String, BigDecimal>  rateMap = fqStoreRateService.calculateOrderRate(money, store, type);
			
			if(rateMap!=null&&rateMap.size()>0){
				rate = rateMap.get("smallRate");	 //费率
				rateMoney = rateMap.get("rateMoney");	 //手续费
				calTotamt = rateMap.get("calTotamt");	  //扣除手续费结算的金额 
			}
			
			FqThirdPay thirdPay = new FqThirdPay();
			if (StringUtils.isEmpty(openid)) {
				openid = signMap.get("openid");
			}
			String bankTypeCode = signMap.get("bank_type");
			Integer bankType = null;
			if (!StringUtils.isEmpty(bankTypeCode)) {
				if (bankTypeCode.contains("DEBIT")) {
					bankType = 3;
				}
				if (bankTypeCode.contains("CREDIT")) {
					bankType = 2;
				}
				if (bankTypeCode.equals("CFT")) {
					bankType = 1;
				}
			}
			thirdPay.setBankType(bankType);
			thirdPay.setBankTypeCode(bankTypeCode);
			thirdPay.setMoney(money);
			thirdPay.setOrderId(0L);
			thirdPay.setOrderNo(orderNo);
			thirdPay.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss", payTime));
			thirdPay.setSort(3);
			thirdPay.setTotamt(calTotamt);
			thirdPay.setOrderRate(rate);
			thirdPay.setRateFee(rateMoney);
			
			//5,浦发微信 6,浦发支付宝   3,光大微信 暂时没有光大支付宝 留空4
			if(bank.equals("GD")){
				if(tradeType.contains("weixin")){
					thirdPay.setType(3);
				}
				if(tradeType.contains("alipay")){
					thirdPay.setType(6);
				}
			}else if(bank.equals("PF")){
				if(tradeType.contains("weixin")){
					thirdPay.setType(5);
				}
				if(tradeType.contains("alipay")){
					thirdPay.setType(6);
				}
			}
			thirdPay.setSellerId(sellerId);
			thirdPay.setThirdOrderNo(weixinOrderNo);
			thirdPay.setOpenId(openid);
			fqThirdPayMapper.insertSelective(thirdPay);
			SellerInfo seller = sellerInfoMapper.selectByPrimaryKey(sellerId);
			if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
				Date date0 = BaseUtil.getTimeDate(thirdPay.getPayTime());
				FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
				fqSellerStatementExample.createCriteria().andSellerIdEqualTo(sellerId).andStatementDateEqualTo(date0).andStateEqualTo(0);
				List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
				FqSellerStatement fsstate = null;
				if (fsslist.size()>0) {
					fsstate = fsslist.get(0);
					fsstate.setTotalMoney(fsstate.getTotalMoney().add(calTotamt));
					fsstate.setTotalNum(fsstate.getTotalNum()+1);
					fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
				}else{
					fsstate = new FqSellerStatement();
					fsstate.setCreateTime(new Date());
					fsstate.setStatementDate(date0);
					fsstate.setSellerId(sellerId);
					fsstate.setState(0);
					fsstate.setTotalMoney(calTotamt);
					fsstate.setTotalNum(1);
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date0);
					calendar.add(Calendar.DATE, store.getStatementPeriod());
					fsstate.setPeriodDate(calendar.getTime());
					fqSellerStatementMapper.insertSelective(fsstate);
				}
			}else{
				FqStoreCreditExample example3 = new FqStoreCreditExample();
				Date now = new Date();
				example3.createCriteria().andSellerIdEqualTo(sellerId).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
				example3.setOrderByClause("period desc");
				List<FqStoreCredit> credits = fqStoreCreditMapper.selectByExample(example3);
				if (credits.size()>0) {
					FqStoreCredit credit = credits.get(0);
					BigDecimal noRevert = credit.getNoRevert().subtract(calTotamt);
					if (noRevert.compareTo(BigDecimal.ZERO) == -1) {
						noRevert = BigDecimal.ZERO;
						credit.setStatus(3);
					}else{
						credit.setNoRevert(noRevert);
					}
					fqStoreCreditMapper.updateByPrimaryKeySelective(credit);
				}
			}
			
			FqMicroOrderExample fqMicroOrderExample = new FqMicroOrderExample();
			fqMicroOrderExample.createCriteria().andOrderNoEqualTo(orderNo).andSellerIdEqualTo(sellerId);
			FqMicroOrder fqMicroOrder = new FqMicroOrder();
			fqMicroOrder.setStatus(2);
			fqmicroOrderMapper.updateByExampleSelective(fqMicroOrder, fqMicroOrderExample);
			
			
			//推送信息
			HashSet<Integer> pushSet = new HashSet<Integer>();
			pushSet.add(2);
			pushSet.add(3);
			if(tradeType.contains("weixin")){
				pushSet.add(4);
			}
			PushPayInfoTask task = new PushPayInfoTask();
			task.setSellerId(thirdPay.getSellerId());
			task.setPushTypeArr(pushSet);
			task.setMoney(thirdPay.getMoney());
			task.setTradeType(tradeType);
			task.setTransactionId(orderNo);
			task.setOpenId(thirdPay.getOpenId());
			task.setPayTime(thirdPay.getPayTime());
			taskExecutor.execute(task);
			
			
			return thirdPay;
	}
	/**
	 *  bank  兴业银行的 处理成功回调
	 * @param signMap 支付结果map
	 * @throws Exception 
	 */
	private FqThirdPay doHandlePaySuccessXY(Long  sellerId,StoreInfo store, Map<String, String> signMap) throws Exception{
			
			String weixinOrderNo =signMap.get("transaction_id") ;			
			String openid = signMap.get("sub_openid");
			String payTime = signMap.get("time_end");	
			/**
			 *  pay.weixin.micropay
			 *	pay.alipay.micropay
			 *	pay.jdpay.micropay
			 *	pay.qq.micropay
			 */
			String tradeType =  signMap.get("trade_type");
			
			if (StringUtils.isEmpty(openid)) {
				openid = signMap.get("openid");
			}
			String orderNo = signMap.get("out_trade_no");
			String totalFee = signMap.get("total_fee");
			BigDecimal money = BigDecimal.valueOf(Long.valueOf(totalFee)).divide(new BigDecimal(100)); //支付结果中的金额单位为分;
			
			BigDecimal calTotamt =  money;
			BigDecimal rate = new BigDecimal(0); 
			BigDecimal rateMoney = new BigDecimal(0); 
			//查询本店费率
			Integer type = 1;
			if(tradeType.contains("weixin")){
				 type = 1;
			}
			if(tradeType.contains("alipay")){
				type = 2;
			}
			HashMap<String, BigDecimal>  rateMap = fqStoreRateService.calculateOrderRate(money, store, type);
			
			if(rateMap!=null&&rateMap.size()>0){
				rate = rateMap.get("smallRate");	 //费率
				rateMoney = rateMap.get("rateMoney");	 //手续费
				calTotamt = rateMap.get("calTotamt");	  //扣除手续费结算的金额 
			}
			
			FqThirdPay thirdPay = new FqThirdPay();
			if (StringUtils.isEmpty(openid)) {
				openid = signMap.get("openid");
			}
			String bankTypeCode = signMap.get("bank_type");
			Integer bankType = null;
			if (!StringUtils.isEmpty(bankTypeCode)) {
				if (bankTypeCode.contains("DEBIT")) {
					bankType = 3;
				}
				if (bankTypeCode.contains("CREDIT")) {
					bankType = 2;
				}
				if (bankTypeCode.equals("CFT")) {
					bankType = 1;
				}
			}
			thirdPay.setBankType(bankType);
			thirdPay.setBankTypeCode(bankTypeCode);
			thirdPay.setMoney(money);
			thirdPay.setOrderId(0L);
			thirdPay.setOrderNo(orderNo);
			thirdPay.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss", payTime));
			thirdPay.setSort(98);
			thirdPay.setTotamt(calTotamt);
			thirdPay.setOrderRate(rate);
			thirdPay.setRateFee(rateMoney);
			
			if(tradeType.contains("weixin")){
				thirdPay.setType(5);
			}
			if(tradeType.contains("alipay")){
				thirdPay.setType(6);
			}
			thirdPay.setSellerId(sellerId);
			thirdPay.setThirdOrderNo(weixinOrderNo);
			thirdPay.setOpenId(openid);
			fqThirdPayMapper.insertSelective(thirdPay);
			SellerInfo seller = sellerInfoMapper.selectByPrimaryKey(sellerId);
			if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
				Date date0 = BaseUtil.getTimeDate(thirdPay.getPayTime());
				FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
				fqSellerStatementExample.createCriteria().andSellerIdEqualTo(sellerId).andStatementDateEqualTo(date0).andStateEqualTo(98);
				List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
				FqSellerStatement fsstate = null;
				if (fsslist.size()>0) {
					fsstate = fsslist.get(0);
					fsstate.setTotalMoney(fsstate.getTotalMoney().add(calTotamt));
					fsstate.setTotalNum(fsstate.getTotalNum()+1);
					fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
				}else{
					fsstate = new FqSellerStatement();
					fsstate.setCreateTime(new Date());
					fsstate.setStatementDate(date0);
					fsstate.setSellerId(sellerId);
					fsstate.setState(98);
					fsstate.setTotalMoney(calTotamt);
					fsstate.setTotalNum(1);
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date0);
					calendar.add(Calendar.DATE, store.getStatementPeriod());
					fsstate.setPeriodDate(calendar.getTime());
					fqSellerStatementMapper.insertSelective(fsstate);
				}
			}else{
				FqStoreCreditExample example3 = new FqStoreCreditExample();
				Date now = new Date();
				example3.createCriteria().andSellerIdEqualTo(sellerId).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
				example3.setOrderByClause("period desc");
				List<FqStoreCredit> credits = fqStoreCreditMapper.selectByExample(example3);
				if (credits.size()>0) {
					FqStoreCredit credit = credits.get(0);
					BigDecimal noRevert = credit.getNoRevert().subtract(calTotamt);
					if (noRevert.compareTo(BigDecimal.ZERO) == -1) {
						noRevert = BigDecimal.ZERO;
						credit.setStatus(3);
					}else{
						credit.setNoRevert(noRevert);
					}
					fqStoreCreditMapper.updateByPrimaryKeySelective(credit);
				}
			}
			
			FqMicroOrderExample fqMicroOrderExample = new FqMicroOrderExample();
			fqMicroOrderExample.createCriteria().andOrderNoEqualTo(orderNo).andSellerIdEqualTo(sellerId);
			FqMicroOrder fqMicroOrder = new FqMicroOrder();
			fqMicroOrder.setStatus(2);
			fqmicroOrderMapper.updateByExampleSelective(fqMicroOrder, fqMicroOrderExample);
			
			
			//推送信息
			HashSet<Integer> pushSet = new HashSet<Integer>();
			pushSet.add(2);
			pushSet.add(3);
			if(tradeType.contains("weixin")){
				pushSet.add(4);
			}
			PushPayInfoTask task = new PushPayInfoTask();
			task.setSellerId(thirdPay.getSellerId());
			task.setPushTypeArr(pushSet);
			task.setMoney(thirdPay.getMoney());
			task.setTradeType(tradeType);
			task.setTransactionId(orderNo);
			task.setOpenId(thirdPay.getOpenId());
			task.setPayTime(thirdPay.getPayTime());
			taskExecutor.execute(task);
			
			
			return thirdPay;
	}
	
	/**
	 *  bank  兴业银行 二清  的 处理成功回调
	 * @param signMap 支付结果map
	 * @throws Exception 
	 */
	private FqThirdPay doHandlePaySuccessEQXY(Long  sellerId,StoreInfo store, Map<String, String> signMap) throws Exception{
			
			String weixinOrderNo =signMap.get("transaction_id") ;			
			String openid = signMap.get("sub_openid");
			String payTime = signMap.get("time_end");	
			/**
			 *  pay.weixin.micropay
			 *	pay.alipay.micropay
			 *	pay.jdpay.micropay
			 *	pay.qq.micropay
			 */
			String tradeType =  signMap.get("trade_type");
			
			if (StringUtils.isEmpty(openid)) {
				openid = signMap.get("openid");
			}
			String orderNo = signMap.get("out_trade_no");
			String totalFee = signMap.get("total_fee");
			BigDecimal money = BigDecimal.valueOf(Long.valueOf(totalFee)).divide(new BigDecimal(100)); //支付结果中的金额单位为分;
			
			BigDecimal calTotamt =  money;
			BigDecimal rate = new BigDecimal(0); 
			BigDecimal rateMoney = new BigDecimal(0); 
			//查询本店费率
			Integer type = 1;
			if(tradeType.contains("weixin")){
				 type = 1;
			}
			if(tradeType.contains("alipay")){
				type = 2;
			}
			HashMap<String, BigDecimal>  rateMap = fqStoreRateService.calculateOrderRate(money, store, type);
			
			if(rateMap!=null&&rateMap.size()>0){
				rate = rateMap.get("smallRate");	 //费率
				rateMoney = rateMap.get("rateMoney");	 //手续费
				calTotamt = rateMap.get("calTotamt");	  //扣除手续费结算的金额 
			}
			
			FqThirdPay thirdPay = new FqThirdPay();
			if (StringUtils.isEmpty(openid)) {
				openid = signMap.get("openid");
			}
			String bankTypeCode = signMap.get("bank_type");
			Integer bankType = null;
			if (!StringUtils.isEmpty(bankTypeCode)) {
				if (bankTypeCode.contains("DEBIT")) {
					bankType = 3;
				}
				if (bankTypeCode.contains("CREDIT")) {
					bankType = 2;
				}
				if (bankTypeCode.equals("CFT")) {
					bankType = 1;
				}
			}
			thirdPay.setBankType(bankType);
			thirdPay.setBankTypeCode(bankTypeCode);
			thirdPay.setMoney(money);
			thirdPay.setOrderId(0L);
			thirdPay.setOrderNo(orderNo);
			thirdPay.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss", payTime));
			thirdPay.setSort(1);
			thirdPay.setTotamt(calTotamt);
			thirdPay.setOrderRate(rate);
			thirdPay.setRateFee(rateMoney);
			
			if(tradeType.contains("weixin")){
				thirdPay.setType(5);
			}
			if(tradeType.contains("alipay")){
				thirdPay.setType(6);
			}
			thirdPay.setSellerId(sellerId);
			thirdPay.setThirdOrderNo(weixinOrderNo);
			thirdPay.setOpenId(openid);
			fqThirdPayMapper.insertSelective(thirdPay);
			SellerInfo seller = sellerInfoMapper.selectByPrimaryKey(sellerId);
			if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
				Date date0 = BaseUtil.getTimeDate(thirdPay.getPayTime());
				FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
				fqSellerStatementExample.createCriteria().andSellerIdEqualTo(sellerId).andStatementDateEqualTo(date0).andStateEqualTo(0);
				List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
				FqSellerStatement fsstate = null;
				if (fsslist.size()>0) {
					fsstate = fsslist.get(0);
					fsstate.setTotalMoney(fsstate.getTotalMoney().add(calTotamt));
					fsstate.setTotalNum(fsstate.getTotalNum()+1);
					fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
				}else{
					fsstate = new FqSellerStatement();
					fsstate.setCreateTime(new Date());
					fsstate.setStatementDate(date0);
					fsstate.setSellerId(sellerId);
					fsstate.setState(0);
					fsstate.setTotalMoney(calTotamt);
					fsstate.setTotalNum(1);
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date0);
					calendar.add(Calendar.DATE, store.getStatementPeriod());
					fsstate.setPeriodDate(calendar.getTime());
					fqSellerStatementMapper.insertSelective(fsstate);
				}
			}else{
				FqStoreCreditExample example3 = new FqStoreCreditExample();
				Date now = new Date();
				example3.createCriteria().andSellerIdEqualTo(sellerId).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
				example3.setOrderByClause("period desc");
				List<FqStoreCredit> credits = fqStoreCreditMapper.selectByExample(example3);
				if (credits.size()>0) {
					FqStoreCredit credit = credits.get(0);
					BigDecimal noRevert = credit.getNoRevert().subtract(calTotamt);
					if (noRevert.compareTo(BigDecimal.ZERO) == -1) {
						noRevert = BigDecimal.ZERO;
						credit.setStatus(3);
					}else{
						credit.setNoRevert(noRevert);
					}
					fqStoreCreditMapper.updateByPrimaryKeySelective(credit);
				}
			}
			
			FqMicroOrderExample fqMicroOrderExample = new FqMicroOrderExample();
			fqMicroOrderExample.createCriteria().andOrderNoEqualTo(orderNo).andSellerIdEqualTo(sellerId);
			FqMicroOrder fqMicroOrder = new FqMicroOrder();
			fqMicroOrder.setStatus(2);
			fqmicroOrderMapper.updateByExampleSelective(fqMicroOrder, fqMicroOrderExample);
			
			
			//推送信息
			HashSet<Integer> pushSet = new HashSet<Integer>();
			pushSet.add(2);
			pushSet.add(3);
			if(tradeType.contains("weixin")){
				pushSet.add(4);
			}
			PushPayInfoTask task = new PushPayInfoTask();
			task.setSellerId(thirdPay.getSellerId());
			task.setPushTypeArr(pushSet);
			task.setMoney(thirdPay.getMoney());
			task.setTradeType(tradeType);
			task.setTransactionId(orderNo);
			task.setOpenId(thirdPay.getOpenId());
			task.setPayTime(thirdPay.getPayTime());
			taskExecutor.execute(task);
			
			
			return thirdPay;
	}
	
	
	
	
	/**
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  原生支付宝支付 start
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 */
	@Override
	public FqThirdPay insertPayOrderByAliPay(Long sellerId1, BigDecimal money,
			String authCode,FqMicroOrder microOrder, HttpServletRequest request,
			HttpServletResponse response){
		try {
		
		StoreInfo store = storeService.queryStoreInfoBySeller(sellerId1);
		FqThirdPay thirdPay = new FqThirdPay();
		thirdPay.setMoney(money);
		thirdPay.setOrderId(0L);
		thirdPay.setOrderNo(microOrder.getOrderNo());
		thirdPay.setPayTime(new Date());
		thirdPay.setSort(3);
		thirdPay.setType(2);
		thirdPay.setSellerId(sellerId1);
		thirdPay.setTotamt(money);
		
	
		
		// (必填) 订单标题，粗略描述用户的支付目的。如“xxx品牌xxx门店消费”
		String subject = store.getName()+"-移动支付";
		
		// (必填) 订单总金额，单位为元，不能超过1亿元
		// 如果同时传入了【打折金额】,【不可打折金额】,【订单总金额】三者,则必须满足如下条件:【订单总金额】=【打折金额】+【不可打折金额】
		String totalAmount = money.toString();
		
		
		// 卖家支付宝账号ID，用于支持一个签约账号下支持打款到不同的收款账号，(打款到sellerId对应的支付宝账号)
		// 如果该字段为空，则默认为与支付宝签约的商户的PID，也就是appid对应的PID
		String sellerId = ConstantsConfigurer.getProperty("ali_mcrit_pid");
		
		// 订单描述，可以对交易或商品进行一个详细地描述，比如填写"购买商品3件共20.00元"
		String body = "用户消费共"+money.toString()+"元";
		
		// 支付超时，线下扫码交易定义为5分钟
		String timeoutExpress = "10m";
		
		
		// 创建条码支付请求builder，设置请求参数
		AlipayTradePayRequestBuilder builder = new AlipayTradePayRequestBuilder()
		 //            .setAppAuthToken(appAuthToken)
		 .setOutTradeNo(microOrder.getOrderNo()).setSubject(subject).setAuthCode(authCode)
		 .setTotalAmount(totalAmount).setBody(body).setSellerId(sellerId)
		 .setTimeoutExpress(timeoutExpress).setStoreId("2017011800077000000000058409");
		//		 Configs.init("config/zfbinfo.properties");
		//		AlipayTradeService   tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();
		AlipayF2FPayResult result = AlipayService.tradeService.tradePay(builder);
		 logger.info("支付宝 desk result---"+JSON.toJSONString(result));
		switch (result.getTradeStatus()) {
		 case SUCCESS:
			 logger.info("支付宝支付成功: )");
			 	
				BigDecimal calTotamt =  money;
				BigDecimal rate = new BigDecimal(0); 
				BigDecimal rateMoney = new BigDecimal(0); 
				//查询本店费率
				Integer type = 2;
				
				HashMap<String, BigDecimal>  rateMap = fqStoreRateService.calculateOrderRate(money, store, type);
				
				if(rateMap!=null&&rateMap.size()>0){
					rate = rateMap.get("smallRate");	 //费率
					rateMoney = rateMap.get("rateMoney");	 //手续费
					calTotamt = rateMap.get("calTotamt");	  //扣除手续费结算的金额 
				}
			 
				thirdPay.setTotamt(calTotamt);
				thirdPay.setOrderRate(rate);
				thirdPay.setRateFee(rateMoney);
				thirdPay.setThirdOrderNo( result.getResponse().getTradeNo());
				thirdPay.setPayTime(result.getResponse().getGmtPayment());
				fqThirdPayMapper.insertSelective(thirdPay);
				
				microOrder.setStatus(2);
				microOrder.setState("SUCCESS");
				microOrder.setCxNum(0);
				fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
				
				SellerInfo seller = sellerInfoMapper.selectByPrimaryKey(sellerId1);
				if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
					Date date0 = BaseUtil.getTimeDate(thirdPay.getPayTime());
					FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
					fqSellerStatementExample.createCriteria().andSellerIdEqualTo(sellerId1).andStatementDateEqualTo(date0).andStateEqualTo(0);
					List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
					FqSellerStatement fsstate = null;
					if (fsslist.size()>0) {
						fsstate = fsslist.get(0);
						fsstate.setTotalMoney(fsstate.getTotalMoney().add(calTotamt));
						fsstate.setTotalNum(fsstate.getTotalNum()+1);
						fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
					}else{
						fsstate = new FqSellerStatement();
						fsstate.setCreateTime(new Date());
						fsstate.setStatementDate(date0);
						fsstate.setSellerId(sellerId1);
						fsstate.setState(0);
						fsstate.setTotalMoney(calTotamt);
						fsstate.setTotalNum(1);
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(date0);
						calendar.add(Calendar.DATE, store.getStatementPeriod());
						fsstate.setPeriodDate(calendar.getTime());
						fqSellerStatementMapper.insertSelective(fsstate);
					}
				}else{
					FqStoreCreditExample example3 = new FqStoreCreditExample();
					Date now = new Date();
					example3.createCriteria().andSellerIdEqualTo(sellerId1).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
					example3.setOrderByClause("period desc");
					List<FqStoreCredit> credits = fqStoreCreditMapper.selectByExample(example3);
					if (credits.size()>0) {
						FqStoreCredit credit = credits.get(0);
						BigDecimal noRevert = credit.getNoRevert().subtract(calTotamt);
						if (noRevert.compareTo(BigDecimal.ZERO) == -1) {
							noRevert = BigDecimal.ZERO;
							credit.setStatus(3);
						}else{
							credit.setNoRevert(noRevert);
						}
						fqStoreCreditMapper.updateByPrimaryKeySelective(credit);
					}
				}
				
				//推送信息
				HashSet<Integer> pushSet = new HashSet<Integer>();
				pushSet.add(2);
				pushSet.add(3);
				PushPayInfoTask task = new PushPayInfoTask();
				task.setSellerId(sellerId1);
				task.setPushTypeArr(pushSet);
				task.setMoney(thirdPay.getMoney());
				task.setTradeType("alipay");
				task.setTransactionId(microOrder.getOrderNo());
				taskExecutor.execute(task);
				return thirdPay;
		     
		 case FAILED:
			 logger.error("支付宝支付失败!!!");
			 microOrder.setStatus(1);
				microOrder.setState("ERROR");
				microOrder.setCxNum(3);
				fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
		     break;
		
		 case UNKNOWN:
			 logger.error("系统异常，订单状态未知!!!");
			 	microOrder.setStatus(1);
				microOrder.setState("USERPAYING");
				microOrder.setCxNum(6);
				fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
		        thirdPay.setType(-1);
		        return thirdPay;
		
		 default:
			 logger.error("不支持的交易状态，交易返回异常!!!");
			 microOrder.setStatus(1);
				microOrder.setState("ERROR");
				microOrder.setCxNum(3);
				fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
		     break;
		}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
     }
	/**
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 * 原生支付宝支付 end
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 */
	
	
	
	/**
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  原生QQ支付 start
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 */
	@Override
	public FqThirdPay insertPayOrderByQqPay(Long sellerId, BigDecimal money,String authCode, HttpServletRequest request,HttpServletResponse response) {
		try {
			String orderNo = OrderUtil.getOrderNo(SystemConstant.QQ_MICOR_PAY_TYPE);
			FqMicroOrder microOrder = new FqMicroOrder();
			microOrder.setCreateTime(new Date());
			microOrder.setCxNum(6);
			microOrder.setMoney(money);
			microOrder.setOrderNo(orderNo);
			microOrder.setSellerId(sellerId);
			microOrder.setStatus(0);
			microOrder.setPayType(3);
			fqmicroOrderMapper.insertSelective(microOrder);
			
			String JSAPI_URL = "https://qpay.qq.com/cgi-bin/pay/qpay_micro_pay.cgi";
			String MCH_ID = ConstantsConfigurer.getProperty(SystemConstant.QQ_MCH_ID);
			String PAY_KEY = ConstantsConfigurer.getProperty(SystemConstant.QQ_PAY_API_KEY);
			String QQ_MICRO_PAY_NOTIFYURL = ConstantsConfigurer.getProperty(SystemConstant.QQ_MICRO_PAY_NOTIFYURL);
			StoreInfo store = storeService.queryStoreInfoBySeller(sellerId);
			TreeMap<String, String> packageParams = new TreeMap<String, String>();
	        packageParams.put("mch_id", MCH_ID); 		
	        packageParams.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));  //商家号
	        packageParams.put("body",store.getName()+"-移动支付"); //商品描述   
	        packageParams.put("out_trade_no", orderNo); 	
	        packageParams.put("fee_type", "CNY"); 	
	        int fee = money.multiply(new BigDecimal(100)).intValue();
	        packageParams.put("total_fee", String.valueOf(fee)); 
	        packageParams.put("spbill_create_ip",request.getRemoteAddr());
	        packageParams.put("auth_code",authCode);
	        packageParams.put("device_info","987456123056057");
	        packageParams.put("trade_type","MICROPAY");
	        packageParams.put("notify_url",QQ_MICRO_PAY_NOTIFYURL);
	        packageParams.put("attach",""+sellerId);	//此为附加数据，在异步回调时，得知商家id
	        
	    	String sign = createSignQq(packageParams, PAY_KEY);
			packageParams.put("sign", sign);
			String requestXML = XMLUtil.getRequestXml(packageParams);
		    String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
		    Map<String, String> signMap = new HashMap<String, String>();
			
				signMap = XMLUtil.doXMLParse(result);
				logger.info(">>>>>>>>>>>>>>>>>>QQ MICRO支付回调 参数" + signMap.toString());
				
				if (!"SUCCESS".equals(signMap.get("return_code"))) {
					logger.info(">>>>>>>>>>>>>>>>>>QQ MICRO支付回调，通信异常："+signMap.toString());
					microOrder.setStatus(1);
					microOrder.setCxNum(6);
					microOrder.setState("USERPAYING");
					fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
					 return null;
				}
				if (!"SUCCESS".equals(signMap.get("result_code"))) {
					if ("USERPAYING".equals(signMap.get("err_code"))|| "USERPAYING".equals(signMap.get("trade_state"))) {
							logger.info("当前订单" + orderNo + "交易用户正在支付中，等待5秒后发起查询");
							microOrder.setCxNum(6);
							microOrder.setState("USERPAYING");
							fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
							// 等待5秒发起查询
							Thread.sleep(5000);
							int cxNum = 6;
							String trade_state = signMap.get("err_code");
							while (cxNum > 0 && trade_state.equals("USERPAYING")) {
								Map<String,String> cxSignMap = doQueryQQPayState(orderNo);
								
								if (isQqSign(cxSignMap)) {
									if ("SUCCESS".equals(cxSignMap.get("return_code")) && "SUCCESS".equals(cxSignMap.get("result_code"))) {
										//SUCCESS 支付成功  
										//REFUND 转入退款
										//REVOKED订单已撤销
										//CLOSED 订单已关闭 
										//USERPAYING 用户支付中   
										 trade_state = cxSignMap.get("trade_state");
										if (("SUCCESS").equals(trade_state)) {
												// 交易成功
											FqThirdPay fqThirdPay = doHandleQQPaySuccess(sellerId,store,cxSignMap);
											microOrder.setStatus(2);
											microOrder.setCxNum(0);
											microOrder.setState("SUCCESS");
											fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
											return fqThirdPay;	
										} else if (trade_state.equals("USERPAYING")) {
											// 还未成功 等待10秒再次发起查询
											logger.info("当前订单" + orderNo +"---------"+cxNum+"交易用户正在支付中，再次等待10秒后发起查询"+cxSignMap.toString());
											
											microOrder.setCxNum(cxNum-1);
											fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
											Thread.sleep(10000);
											cxNum--;
										} else {
											microOrder.setStatus(0);
											microOrder.setCxNum(0);
											microOrder.setState("ERROR");
											fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
											logger.info(">>>>>>>>>>>>>>>>>>MICRO SELECT失败:" + cxSignMap.toString());
											break;
										}
									}
								}
							}
					}else{
						logger.info(">>>>>>>>>>>>>>>>>>QQ MICRO支付回调失败:"+signMap.toString());
						return null;
					}
				}else{
					if(isQqSign(signMap)){
						if ("SUCCESS".equals(signMap.get("return_code")) && "SUCCESS".equals(signMap.get("result_code")) && "SUCCESS".equals(signMap.get("trade_state"))) {
							FqThirdPay fqThirdPay = doHandleQQPaySuccess(sellerId,store,signMap);
							return fqThirdPay;	
						}
					}else{
						logger.info(">>>>>>>>>>>>>>>>>>GD MICRO支付回调失败:签名验证失败");
					}
					
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	private Map<String, String> doQueryQQPayState(String orderNo) throws Exception {
		String MCH_ID = ConstantsConfigurer.getProperty(SystemConstant.QQ_MCH_ID);
		String API_KEY = ConstantsConfigurer.getProperty(SystemConstant.QQ_PAY_API_KEY);
		
		CQpayMchSpBase CQpayObj = new CQpayMchSpBase();
		TreeMap<String, String> requestMap = new TreeMap<>();
		requestMap.put("mch_id", MCH_ID);//商户id
		requestMap.put("api_key", API_KEY);//商户api请求秘钥
		requestMap.put("nonce_str", UUID.randomUUID().toString().toUpperCase().replace("-", "")); //随机请求字符串
		requestMap.put("out_trade_no", orderNo);//订单号
		CQpayObj.setRequestMapAndUrl(requestMap, CQpayAPIURL.getOrderQueryUrl());
		
		TreeMap<String, String>  resultMap = CQpayObj.call();
		//通信结果
		if (!"SUCCESS".equals(resultMap.get("return_code"))) {
			logger.info(">>>>>>>>>>>>>>>>>>GD MICRO SELECT，通信异常："+resultMap.toString());
			 return null;
		}
		//业务结果
		if (!"SUCCESS".equals(resultMap.get("result_code"))) {
			 logger.info(">>>>>>>>>>>>>>>>>>MICRO SELECT失败:"+resultMap.toString());
			 return null;
		}
		return resultMap;
	}

	private FqThirdPay doHandleQQPaySuccess(Long sellerId, StoreInfo store,Map<String, String> signMap) throws Exception {
		FqThirdPay thirdPay = new FqThirdPay();
		String weixinOrderNo = signMap.get("transaction_id");
		String openid = signMap.get("openid");
		String orderNo = signMap.get("out_trade_no");
		String totalFee = signMap.get("total_fee");
		BigDecimal money = BigDecimal.valueOf(Long.valueOf(totalFee)).divide(new BigDecimal(100)); //支付结果中的金额单位为分;
		
		String bankTypeCode = signMap.get("bank_type");
		Integer bankType = null;
		if (!StringUtils.isEmpty(bankTypeCode)) {
			if (bankTypeCode.contains("DEBIT")) {
				bankType = 3;
			}
			if (bankTypeCode.contains("CREDIT")) {
				bankType = 2;
			}
			if (bankTypeCode.equals("BALANCE")) {
				bankType = 1;
			}
		}
		BigDecimal calTotamt =  money;
		BigDecimal rate = new BigDecimal(0); 
		BigDecimal rateMoney = new BigDecimal(0); 
		//查询本店费率
		Integer type = 3;
		HashMap<String, BigDecimal>  rateMap = fqStoreRateService.calculateOrderRate(money, store, type);
		
		if(rateMap!=null&&rateMap.size()>0){
			rate = rateMap.get("smallRate");	 //费率
			rateMoney = rateMap.get("rateMoney");	 //手续费
			calTotamt = rateMap.get("calTotamt");	  //扣除手续费结算的金额 
		}
		
		thirdPay.setBankType(bankType);
		thirdPay.setBankTypeCode(bankTypeCode);
		thirdPay.setMoney(money);
		thirdPay.setOrderId(0L);
		thirdPay.setOrderNo(orderNo);
		thirdPay.setPayTime(new Date());
		thirdPay.setSort(3);
		thirdPay.setType(7);
		thirdPay.setSellerId(sellerId);
		thirdPay.setTotamt(calTotamt);
		thirdPay.setOrderRate(rate);
		thirdPay.setRateFee(rateMoney);
		thirdPay.setThirdOrderNo(weixinOrderNo);
		thirdPay.setOpenId(openid);
		fqThirdPayMapper.insertSelective(thirdPay);
		SellerInfo seller = sellerInfoMapper.selectByPrimaryKey(sellerId);
		if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
			Date date0 = BaseUtil.getTimeDate(thirdPay.getPayTime());
			FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
			fqSellerStatementExample.createCriteria().andSellerIdEqualTo(sellerId).andStatementDateEqualTo(date0).andStateEqualTo(0);
			List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
			FqSellerStatement fsstate = null;
			if (fsslist.size() > 0) {
				fsstate = fsslist.get(0);
				fsstate.setTotalMoney(fsstate.getTotalMoney().add(calTotamt));
				fsstate.setTotalNum(fsstate.getTotalNum() + 1);
				fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
			} else {
				fsstate = new FqSellerStatement();
				fsstate.setCreateTime(new Date());
				fsstate.setStatementDate(date0);
				fsstate.setSellerId(sellerId);
				fsstate.setState(0);
				fsstate.setTotalMoney(calTotamt);
				fsstate.setTotalNum(1);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date0);
				calendar.add(Calendar.DATE, store.getStatementPeriod());
				fsstate.setPeriodDate(calendar.getTime());
				fqSellerStatementMapper.insertSelective(fsstate);
			}
		} else {
			FqStoreCreditExample example3 = new FqStoreCreditExample();
			Date now = new Date();
			example3.createCriteria().andSellerIdEqualTo(sellerId).andStatusEqualTo(1)
					.andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
			example3.setOrderByClause("period desc");
			List<FqStoreCredit> credits = fqStoreCreditMapper.selectByExample(example3);
			if (credits.size() > 0) {
				FqStoreCredit credit = credits.get(0);
				BigDecimal noRevert = credit.getNoRevert().subtract(calTotamt);
				if (noRevert.compareTo(BigDecimal.ZERO) == -1) {
					noRevert = BigDecimal.ZERO;
					credit.setStatus(3);
				} else {
					credit.setNoRevert(noRevert);
				}
				fqStoreCreditMapper.updateByPrimaryKeySelective(credit);
			}
		}
		
		FqMicroOrderExample fqMicroOrderExample = new FqMicroOrderExample();
		fqMicroOrderExample.createCriteria().andOrderNoEqualTo(orderNo).andSellerIdEqualTo(sellerId);
		FqMicroOrder fqMicroOrder = new FqMicroOrder();
		fqMicroOrder.setStatus(2);
		fqmicroOrderMapper.updateByExampleSelective(fqMicroOrder, fqMicroOrderExample);
		
		//推送信息
		HashSet<Integer> pushSet = new HashSet<Integer>();
		pushSet.add(2);
		pushSet.add(3);
		PushPayInfoTask task = new PushPayInfoTask();
		task.setSellerId(thirdPay.getSellerId());
		task.setPushTypeArr(pushSet);
		task.setMoney(thirdPay.getMoney());
		task.setTradeType("qq");
		task.setTransactionId(orderNo);
		taskExecutor.execute(task);
		
		return thirdPay;

	}

	private boolean isQqSign(Map<String,String> paramsMap) throws Exception{
		
		boolean flag = false;
		String pkey = ConstantsConfigurer.getProperty(SystemConstant.QQ_PAY_API_KEY);
		//String pkey = "";
		RequestHandler reqHandler = new RequestHandler(null, null);
		//初始化 
        reqHandler.init(null, null, null,null, pkey);
        
        //转换成sortedMap 
        SortedMap<String, String> signMap = new TreeMap<String, String>();
        for(Map.Entry<String, String> entry : paramsMap.entrySet()) {
        	signMap.put(entry.getKey(), entry.getValue());
        }
        
		String signValid = reqHandler.createSign(signMap);
		String sign = paramsMap.get("sign");
		if(signValid.equals(sign)){
			flag = true;
		}
		return flag;
	}
	
	private String createSignQq(SortedMap<String, String> packageParams,String key) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)
					&& !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + key);
		String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8")
				.toUpperCase();

		return sign;

	}
	/**
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
	 *  QQ支付 end
	 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
	 * @throws Exception 
	 */

	@Override
	public FqThirdPay insertPayOrderByWXPay(Long id, BigDecimal money,
			String authCode, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		FqThirdPay thirdPay = new FqThirdPay();
    	String orderNo = OrderUtil.getOrderNo(SystemConstant.WX_MICOR_PAY_TYPE);
		FqMicroOrder microOrder = new FqMicroOrder();
		microOrder.setCreateTime(new Date());
		microOrder.setCxNum(0);
		microOrder.setMoney(money);
		microOrder.setOrderNo(orderNo);
		microOrder.setSellerId(id);
		microOrder.setStatus(0);
		microOrder.setPayType(5);
		fqmicroOrderMapper.insertSelective(microOrder);
		
		
		String JSAPI_URL = "https://api.mch.weixin.qq.com/pay/micropay";
		String appId = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_APPID);
		String partner = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_PAY_PARTNER);
		String pkey = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_PAY_PKEY);
		StoreInfo store = storeService.queryStoreInfoBySeller(id);
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
        packageParams.put("appid", appId); //支付appId
        packageParams.put("mch_id", partner); 		
        packageParams.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));  //商家号
        packageParams.put("body",store.getName()+"-移动支付"); //商品描述   
        packageParams.put("out_trade_no", orderNo); 	
        int fee = money.multiply(new BigDecimal(100)).intValue();
        packageParams.put("total_fee", String.valueOf(fee)); 
        packageParams.put("spbill_create_ip",request.getRemoteAddr());
        packageParams.put("auth_code",authCode);
    	String sign = createSign(packageParams, pkey);
		packageParams.put("sign", sign);
		String requestXML = XMLUtil.getRequestXml(packageParams);
	    String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
	    Map<String, String> signMap = new HashMap<String, String>();
		signMap = XMLUtil.doXMLParse(result);
		if(signMap == null){
			  logger.info(">>>>>>>>>>>>>>>>>>微信支付回调 参数 is null");
			  	microOrder.setStatus(1);
				microOrder.setCxNum(6);
				microOrder.setState("ERROR");
				fqmicroOrderMapper.updateByPrimaryKey(microOrder);
              return null;
		}
		
		logger.info(">>>>>>>>>>>>>>>>>>微信支付回调 参数" + signMap.toString());
		
		if ("FAIL".equals(signMap.get("return_code"))) {
			logger.info(">>>>>>>>>>>>>>>>>>微信支付回调，通信异常："+signMap.toString());
			microOrder.setStatus(1);
			microOrder.setCxNum(6);
			microOrder.setState("ERROR");
			fqmicroOrderMapper.updateByPrimaryKey(microOrder);
			 return null;
		}
		
		if(isWxSign(signMap)){
			microOrder.setStatus(1);
			microOrder.setCxNum(6);
			fqmicroOrderMapper.updateByPrimaryKey(microOrder);
			if ("SUCCESS".equals(signMap.get("return_code"))&& "SUCCESS".equals(signMap.get("result_code"))) {
					FqThirdPay fqThirdPay = doHandlePaySuccessWx(id, store, signMap);
					return fqThirdPay;	
				}else{
					if ("USERPAYING".equals(signMap.get("err_code"))) { 
						thirdPay.setType(-1);
						microOrder.setStatus(1);
						microOrder.setCxNum(6);
						microOrder.setState("USERPAYING");
						fqmicroOrderMapper.updateByPrimaryKey(microOrder);
						logger.info("当前订单" + orderNo + "交易用户正在支付中，等待5秒后发起查询");
						// 等待5秒发起查询
						Thread.sleep(5000);
						int cxNum = 6;
						String trade_state = signMap.get("err_code");
						while (cxNum > 0) {
							Map<String,String> cxSignMap = doQueryPayStateWX(orderNo);
							logger.info(">>>>>>>>>>>>>>>>>>WX MICRO循环查询订单回调 参数"+cxNum+">>>>>>>>" + cxSignMap.toString());
							if (isWxSign(cxSignMap)) {
								if ("SUCCESS".equals(cxSignMap.get("return_code")) && "SUCCESS".equals(cxSignMap.get("result_code"))) {
									if (("SUCCESS").equals(cxSignMap.get("trade_state"))) {
											// 交易成功
										logger.info("当前订单" + orderNo + "交易成功");
										FqThirdPay fqThirdPay =doHandlePaySuccessWx(id,store,cxSignMap);
										
										return fqThirdPay;	
									} else {
										 trade_state = cxSignMap.get("trade_state");
										if (trade_state.equals("USERPAYING")) {
											// 还未成功 等待10秒再次发起查询
											if(cxNum!=1){
												microOrder.setCxNum(cxNum-1);
												fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
											}
											logger.info("当前订单" + orderNo +"----------"+cxNum+ "交易用户正在支付中，再次等待10秒后发起查询");
											Thread.sleep(10000);
											cxNum--;
										} else {
											if(cxNum!=1){
												microOrder.setCxNum(cxNum-1);
												fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
											}
											logger.info("当前订单" + orderNo +"----------"+cxNum+ "交易信息不明确，再次等待10秒后发起查询");
											Thread.sleep(10000);
											cxNum--;
										}
									}
								}else{
									microOrder.setStatus(1);
									microOrder.setCxNum(cxNum-1);
									microOrder.setState("ERROR");
									fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
									logger.info(">>>>>>>>>>>>>>>>>>PF MICRO支付查询回调失败:" + cxSignMap.toString());
									cxNum--;
								}
							}
						}
						return thirdPay;
					}else{
						thirdPay.setType(-1);
						microOrder.setStatus(1);
						microOrder.setCxNum(5);
						microOrder.setState(signMap.get("err_code"));
						fqmicroOrderMapper.updateByPrimaryKey(microOrder);
						return null;
					}
				}
		}
		return null;
	}
	
public static boolean isWxSign(Map<String,String> paramsMap) throws Exception{
		
		boolean flag = false;
		String pkey = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_PAY_PKEY);
		//String pkey = "";
		RequestHandler reqHandler = new RequestHandler(null, null);
		//初始化 
        reqHandler.init(null, null, null,null, pkey);
        
        //转换成sortedMap 
        SortedMap<String, String> signMap = new TreeMap<String, String>();
        for(Map.Entry<String, String> entry : paramsMap.entrySet()) {
        	signMap.put(entry.getKey(), entry.getValue());
        }
        
		String signValid = reqHandler.createSign(signMap);
		String sign = paramsMap.get("sign");
		if(signValid.equals(sign)){
			flag = true;
		}
		return flag;
	}
	

	/**
	 * 民生银行（微信，支付宝）反扫支付
	 */
	@Override
	public FqThirdPay insertPayOrderByMSPay(SellerInfo seller, BigDecimal money, String authCode,
				       HttpServletRequest request, HttpServletResponse response) {
		try {
			String orderNo="";
			Integer payType = 0;
			Map<String,Object> resultMap = new HashMap<String,Object>();
			Integer authCodeBegin = Integer.valueOf(authCode.substring(0, 2));
			StoreInfo store = storeService.queryStoreInfoBySeller(seller.getId());
	        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
	        nvps.add(new BasicNameValuePair("requestNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())));
	        nvps.add(new BasicNameValuePair("version", "V2.0"));
	        nvps.add(new BasicNameValuePair("transId", "10"));
	        nvps.add(new BasicNameValuePair("merNo", ConstantsConfigurer.getProperty(SystemConstant.MS_MCH_ID)));
	        nvps.add(new BasicNameValuePair("orderDate", new SimpleDateFormat("yyyyMMdd").format(new Date())));
	        nvps.add(new BasicNameValuePair("returnUrl", ConstantsConfigurer.getProperty(SystemConstant.MS_PAY_RETURNURL)));  //页面通知地址
	        nvps.add(new BasicNameValuePair("notifyUrl", ConstantsConfigurer.getProperty(SystemConstant.MS_PAY_ALL_NOTIFYURL)));  //异步通知地址
	        nvps.add(new BasicNameValuePair("transAmt", money.multiply(new BigDecimal(100)).intValue()+""));
	        nvps.add(new BasicNameValuePair("commodityName", store.getName()+"-买单"));
	        nvps.add(new BasicNameValuePair("authCode", authCode)); 									  //100000001060
	        nvps.add(new BasicNameValuePair("subChnlMerNo", "100000001060")); 			//au.getApplyId()//"100000001059"
			
		    if(authCodeBegin>9 && authCodeBegin < 16) {
		    	 orderNo = OrderUtil.getOrderNo(SystemConstant.MS_WX_MICOR_PAY_TYPE);		//微信
		    	 payType = 1;
		    	 nvps.add(new BasicNameValuePair("productId", "0106"));		//0106-微信刷卡（反扫）
		    	 nvps.add(new BasicNameValuePair("subMerNo", String.valueOf(store.getId())));
			     nvps.add(new BasicNameValuePair("subMerName", store.getName()));
			   //AuthenticationChnnel au = authenticationChnnelService.findByStoreIdAndBankIdAndPayChannelAndState(store.getId(),1,1,2);
			    // nvps.add(new BasicNameValuePair("subChnlMerNo", au.getApplyId())); 			//au.getApplyId()//"100000001059"
		    }else if(authCodeBegin == 28) {
		    	 orderNo = OrderUtil.getOrderNo(SystemConstant.MS_ALI_MICOR_PAY_TYPE);		//支付宝
		    	 payType = 2;
		    	 nvps.add(new BasicNameValuePair("productId", "0110"));		//0110-支付宝刷卡支付
		    	 nvps.add(new BasicNameValuePair("storeId", String.valueOf(store.getId())));
			     nvps.add(new BasicNameValuePair("terminalId", store.getName()));
			   //AuthenticationChnnel au = authenticationChnnelService.findByStoreIdAndBankIdAndPayChannelAndState(store.getId(),1,2,2);
			  // nvps.add(new BasicNameValuePair("subChnlMerNo", au.getApplyId())); 	
		    }else if(authCodeBegin == 91){
		    	orderNo = OrderUtil.getOrderNo(SystemConstant.MS_QQ_MICOR_PAY_TYPE);		//QQPay
		    	 payType = 3;
		    	 nvps.add(new BasicNameValuePair("productId", "0120"));		//0120-QQ刷卡（反扫）
		    	 nvps.add(new BasicNameValuePair("subMerNo", String.valueOf(store.getId())));
			     nvps.add(new BasicNameValuePair("subMerName", store.getName()));
		    }
		    nvps.add(new BasicNameValuePair("orderNo", orderNo));
			
			FqMicroOrder microOrder = new FqMicroOrder();
			microOrder.setCreateTime(new Date());
			microOrder.setCxNum(6);
			microOrder.setMoney(money);
			microOrder.setOrderNo(orderNo);
			microOrder.setSellerId(seller.getId());
			microOrder.setStatus(1);
			microOrder.setPayType(payType);
			microOrder.setState("USERPAYING");
			fqmicroOrderMapper.insertSelective(microOrder);
			
			Map<String, String> result = BaseRequest.getSignToSend(nvps);
			
			if (result == null) {
				logger.info(">>>>>>>>>>>>>>>>>>MS MICRO支付响应 参数 is null");
				microOrder.setStatus(1);
				microOrder.setCxNum(6);
				microOrder.setState("USERPAYING");
				fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
				return null;
			}
			
			logger.info(">>>>>>>>>>>>>>>>>>MS 扫码枪  MICRO 支付响应 参数Map=" + result.toString());
			int cxNum = 6;
			if (SystemConstant.SUCCESS.equals(result.get("respCode"))) {
				//交易成功 验证签名 处理逻辑
						// 交易成功
				FqThirdPay fqThirdPay = doHandleMsPaySuccess(seller.getId(),store,result);
				return fqThirdPay;
				
			}else if (MsConstant.DOING_SUCCEE.equals(result.get("respCode"))) {
				//正在交易  
				if (("USERPAYING").equals(microOrder.getState())){
					// 还未成功 等待10秒再次发起查询
					microOrder.setCxNum(cxNum-1);
					fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
					logger.info("当前订单" + orderNo +"----------"+cxNum+ "交易用户正在支付中，再次等待10秒后发起查询");
					Thread.sleep(10000);
					cxNum--;
				} else {
					if(cxNum!=1){
						microOrder.setCxNum(cxNum-1);
						fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
					}
					logger.info("当前订单" + orderNo +"----------"+cxNum+ "交易信息不明确，再次等待10秒后发起查询");
					Thread.sleep(10000);
					cxNum--;
				}
					
					FqThirdPay fqThirdPay = doHandleMsPaySuccess(seller.getId(),store,result);
					return fqThirdPay;
					
			}else {
				logger.info(">>>>>>>>>>>>>>>>>>MS MICRO支付响应失败:签名验证失败");
				microOrder.setStatus(1);
				microOrder.setCxNum(6);
				microOrder.setState("USERPAYING");
				fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
				return null;
			}
		}catch(Exception e){
			logger.info(">>>>>>>>>>>>>>>>>>MS MICRO支付响应失败:"+e.getMessage());
			return null;
		}
		
	}
	
private FqThirdPay doHandleMsPaySuccess(Long  sellerId,StoreInfo store, Map<String, String> signMap) throws Exception{
		
		String weixinOrderNo =signMap.get("payId");		// 第三方订单号 	
		String openid = signMap.get("chnlUserId");	//民生子商户ID
		String payTime = signMap.get("payTime");			
		/**
		 *  0106-微信刷卡（反扫）
		 *	0110-支付宝刷卡支付
		 *	0120-QQ刷卡（反扫）
		 */
		String tradeType =  signMap.get("productId");
		
		if (StringUtils.isEmpty(openid)) {
			openid = signMap.get("openid");
		}
		String orderNo = signMap.get("orderNo");
		String totalFee = signMap.get("transAmt");
		BigDecimal money = BigDecimal.valueOf(Long.valueOf(totalFee)).divide(new BigDecimal(100)); //支付结果中的金额单位为分;
		
		BigDecimal calTotamt =  money;
		BigDecimal rate = new BigDecimal(0); 
		BigDecimal rateMoney = new BigDecimal(0); 
		//查询本店费率
		Integer type = 1;
		if(tradeType.contains("0106")){
			 type = 1;
		}
		if(tradeType.contains("0110")){
			type = 2;
		}
		if(tradeType.contains("0120")){
			type = 3;
		}
		HashMap<String, BigDecimal>  rateMap = fqStoreRateService.calculateOrderRate(money, store, type);
		
		if(rateMap!=null&&rateMap.size()>0){
			rate = rateMap.get("smallRate");	 //费率
			rateMoney = rateMap.get("rateMoney");	 //手续费
			calTotamt = rateMap.get("calTotamt");	  //扣除手续费结算的金额 
		}
		
		FqThirdPay thirdPay = new FqThirdPay();
		if (StringUtils.isEmpty(openid)) {
			openid = signMap.get("openid");
		}
		String bankTypeCode = signMap.get("bankType");
		Integer bankType = null;
		if (!StringUtils.isEmpty(bankTypeCode)) {
			if (bankTypeCode.contains("DEBIT")) {
				bankType = 3;
			}
			if (bankTypeCode.contains("CREDIT")) {
				bankType = 2;
			}
			if (bankTypeCode.equals("CFT")) {
				bankType = 1;
			}
		}
		thirdPay.setBankType(bankType);
		thirdPay.setBankTypeCode(bankTypeCode);
		thirdPay.setMoney(money);
		thirdPay.setOrderId(0L);
		thirdPay.setOrderNo(orderNo);
		thirdPay.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss", payTime));
		thirdPay.setSort(3);
		thirdPay.setTotamt(calTotamt);
		thirdPay.setOrderRate(rate);
		thirdPay.setRateFee(rateMoney);
		
		//15,民生微信  0106 	16,民生支付宝   		17,民生QQ 
		if(tradeType.contains("0106")){
			thirdPay.setType(15);
			tradeType = "weixin";
		}
		if(tradeType.contains("0110")){
			thirdPay.setType(16);
			tradeType = "alipay";
		}
		if(tradeType.contains("0120")){
			thirdPay.setType(17);
			tradeType = "qq";
		}
		thirdPay.setSellerId(sellerId);
		thirdPay.setThirdOrderNo(weixinOrderNo);
		thirdPay.setOpenId(openid);
		fqThirdPayMapper.insertSelective(thirdPay);
		SellerInfo seller = sellerInfoMapper.selectByPrimaryKey(sellerId);
		if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
			String dateend = DateUtil.convertDateToString(thirdPay.getPayTime());
			Date enddate0 = DateUtil.convertStringToDate(dateend+" 23:00:00");
			Date startdate0 = DateUtil.convertStringToDate(DateUtil.getYesDateStr("YYYY-MM-DD")+" 23:00:00");
			FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
			fqSellerStatementExample.createCriteria().andSellerIdEqualTo(store.getSellerId()).
			andStatementDateGreaterThan(enddate0).andStatementDateLessThanOrEqualTo(startdate0);
			List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
			FqSellerStatement fsstate = null;
			if (fsslist.size()>0) {
				fsstate = fsslist.get(0);
				fsstate.setTotalMoney(fsstate.getTotalMoney().add(calTotamt));
				fsstate.setTotalNum(fsstate.getTotalNum()+1);
				fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
			}else{
				fsstate = new FqSellerStatement();
				fsstate.setCreateTime(new Date());
				fsstate.setStatementDate(thirdPay.getPayTime());
				fsstate.setSellerId(sellerId);
				fsstate.setState(2);		//   2..为民生通道  	
				fsstate.setTotalMoney(calTotamt);
				fsstate.setTotalNum(1);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(thirdPay.getPayTime());
				calendar.add(Calendar.DATE, store.getStatementPeriod());
				fsstate.setPeriodDate(calendar.getTime());
				fqSellerStatementMapper.insertSelective(fsstate);
			}
		}else{
			FqStoreCreditExample example3 = new FqStoreCreditExample();
			Date now = new Date();
			example3.createCriteria().andSellerIdEqualTo(sellerId).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
			example3.setOrderByClause("period desc");
			List<FqStoreCredit> credits = fqStoreCreditMapper.selectByExample(example3);
			if (credits.size()>0) {
				FqStoreCredit credit = credits.get(0);
				BigDecimal noRevert = credit.getNoRevert().subtract(calTotamt);
				if (noRevert.compareTo(BigDecimal.ZERO) == -1) {
					noRevert = BigDecimal.ZERO;
					credit.setStatus(3);
				}else{
					credit.setNoRevert(noRevert);
				}
				fqStoreCreditMapper.updateByPrimaryKeySelective(credit);
			}
		}
		
		FqMicroOrderExample fqMicroOrderExample = new FqMicroOrderExample();
		fqMicroOrderExample.createCriteria().andOrderNoEqualTo(orderNo).andSellerIdEqualTo(sellerId);
		FqMicroOrder fqMicroOrder = new FqMicroOrder();
		fqMicroOrder.setStatus(2);
		fqMicroOrder.setState("SUCCESS");
		fqmicroOrderMapper.updateByExampleSelective(fqMicroOrder, fqMicroOrderExample);
		
		
		//推送信息
		HashSet<Integer> pushSet = new HashSet<Integer>();
		pushSet.add(2);
		pushSet.add(3);
		if(tradeType.contains("weixin")){
			pushSet.add(4);
		}
		PushPayInfoTask task = new PushPayInfoTask();
		task.setSellerId(thirdPay.getSellerId());
		task.setPushTypeArr(pushSet);
		task.setMoney(thirdPay.getMoney());
		task.setTradeType(tradeType);
		task.setTransactionId(orderNo);
		task.setOpenId(thirdPay.getOpenId());
		task.setPayTime(thirdPay.getPayTime());
		taskExecutor.execute(task);
		
		
		return thirdPay;
	}
	

private FqThirdPay doHandlePaySuccessWx(Long  sellerId,StoreInfo store, Map<String, String> signMap) throws Exception{
	
	String weixinOrderNo =signMap.get("transaction_id") ;			
	String openid = signMap.get("openid");
	String payTime = signMap.get("time_end");	
	/**
	 *  pay.weixin.micropay
	 *	pay.alipay.micropay
	 *	pay.jdpay.micropay
	 *	pay.qq.micropay
	 */
	
	
	String orderNo = signMap.get("out_trade_no");
	String totalFee = signMap.get("total_fee");
	BigDecimal money = BigDecimal.valueOf(Long.valueOf(totalFee)).divide(new BigDecimal(100)); //支付结果中的金额单位为分;
	
	BigDecimal calTotamt =  money;
	BigDecimal rate = new BigDecimal(0); 
	BigDecimal rateMoney = new BigDecimal(0); 
	//查询本店费率
	Integer type = 1;
	
	HashMap<String, BigDecimal>  rateMap = fqStoreRateService.calculateOrderRate(money, store, type);
	
	if(rateMap!=null&&rateMap.size()>0){
		rate = rateMap.get("smallRate");	 //费率
		rateMoney = rateMap.get("rateMoney");	 //手续费
		calTotamt = rateMap.get("calTotamt");	  //扣除手续费结算的金额 
	}
	
	FqThirdPay thirdPay = new FqThirdPay();
	String bankTypeCode = signMap.get("bank_type");
	Integer bankType = null;
	if (!StringUtils.isEmpty(bankTypeCode)) {
		if (bankTypeCode.contains("DEBIT")) {
			bankType = 3;
		}
		if (bankTypeCode.contains("CREDIT")) {
			bankType = 2;
		}
		if (bankTypeCode.equals("CFT")) {
			bankType = 1;
		}
	}
	thirdPay.setBankType(bankType);
	thirdPay.setBankTypeCode(bankTypeCode);
	thirdPay.setMoney(money);
	thirdPay.setOrderId(0L);
	thirdPay.setOrderNo(orderNo);
	thirdPay.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss", payTime));
	thirdPay.setSort(3);
	thirdPay.setTotamt(calTotamt);
	thirdPay.setOrderRate(rate);
	thirdPay.setRateFee(rateMoney);
	
	//5,浦发微信 6,浦发支付宝   3,光大微信 暂时没有光大支付宝 留空4
	
	thirdPay.setType(1);
	
	thirdPay.setSellerId(sellerId);
	thirdPay.setThirdOrderNo(weixinOrderNo);
	thirdPay.setOpenId(openid);
	fqThirdPayMapper.insertSelective(thirdPay);
	SellerInfo seller = sellerInfoMapper.selectByPrimaryKey(sellerId);
	if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
		Date date0 = BaseUtil.getTimeDate(thirdPay.getPayTime());
		FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
		fqSellerStatementExample.createCriteria().andSellerIdEqualTo(sellerId).andStatementDateEqualTo(date0).andStateEqualTo(0);
		List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
		FqSellerStatement fsstate = null;
		if (fsslist.size()>0) {
			fsstate = fsslist.get(0);
			fsstate.setTotalMoney(fsstate.getTotalMoney().add(calTotamt));
			fsstate.setTotalNum(fsstate.getTotalNum()+1);
			fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
		}else{
			fsstate = new FqSellerStatement();
			fsstate.setCreateTime(new Date());
			fsstate.setStatementDate(date0);
			fsstate.setSellerId(sellerId);
			fsstate.setState(0);
			fsstate.setTotalMoney(calTotamt);
			fsstate.setTotalNum(1);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date0);
			calendar.add(Calendar.DATE, store.getStatementPeriod());
			fsstate.setPeriodDate(calendar.getTime());
			fqSellerStatementMapper.insertSelective(fsstate);
		}
	}else{
		FqStoreCreditExample example3 = new FqStoreCreditExample();
		Date now = new Date();
		example3.createCriteria().andSellerIdEqualTo(sellerId).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
		example3.setOrderByClause("period desc");
		List<FqStoreCredit> credits = fqStoreCreditMapper.selectByExample(example3);
		if (credits.size()>0) {
			FqStoreCredit credit = credits.get(0);
			BigDecimal noRevert = credit.getNoRevert().subtract(calTotamt);
			if (noRevert.compareTo(BigDecimal.ZERO) == -1) {
				noRevert = BigDecimal.ZERO;
				credit.setStatus(3);
			}else{
				credit.setNoRevert(noRevert);
			}
			fqStoreCreditMapper.updateByPrimaryKeySelective(credit);
		}
	}
	
	FqMicroOrderExample fqMicroOrderExample = new FqMicroOrderExample();
	fqMicroOrderExample.createCriteria().andOrderNoEqualTo(orderNo).andSellerIdEqualTo(sellerId);
	FqMicroOrder fqMicroOrder = new FqMicroOrder();
	fqMicroOrder.setStatus(2);
	fqmicroOrderMapper.updateByExampleSelective(fqMicroOrder, fqMicroOrderExample);
	
	
	//推送信息
	HashSet<Integer> pushSet = new HashSet<Integer>();
	pushSet.add(2);
	pushSet.add(3);
	pushSet.add(4);
	PushPayInfoTask task = new PushPayInfoTask();
	task.setSellerId(thirdPay.getSellerId());
	task.setPushTypeArr(pushSet);
	task.setMoney(thirdPay.getMoney());
	task.setTradeType("weixin");
	task.setTransactionId(orderNo);
	task.setOpenId(thirdPay.getOpenId());
	task.setPayTime(thirdPay.getPayTime());
	taskExecutor.execute(task);
	
	
	return thirdPay;
}


@Override
public FqThirdPay insertPayOrderBySZPFWXPay(Long id, BigDecimal money,
		String authCode,FqMicroOrder order, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	
	FqThirdPay thirdPay = new FqThirdPay();
	
	 String notifyUrl = ConstantsConfigurer.getProperty(SystemConstant.SZ_PF_PAY_WX_MC_NOTIFYURL);
	 String returnUrl = ConstantsConfigurer.getProperty(SystemConstant.SZ_PF_PAY_WX_RETURNURL);
	 String merNo = ConstantsConfigurer.getProperty(SystemConstant.SZ_PF_MCH_ID);
	 String subMerNo = ConstantsConfigurer.getProperty(SystemConstant.SZ_PF_WX_MCH_ID);
	 
	 int fee = money.multiply(new BigDecimal(100)).intValue();
	 SortedMap<String, String> packageParams = new TreeMap<String, String>();
	 packageParams.put("productId","0113");
	 packageParams.put("transId", "17");
	 packageParams.put("merNo",merNo);
	 packageParams.put("subMchId",subMerNo);
	 packageParams.put("orderDate", DateUtil.getDateTime("yyyyMMdd",order.getCreateTime()));
	 packageParams.put("orderNo", order.getOrderNo());
	 
	 packageParams.put("clientIp", request.getRemoteAddr());
	 packageParams.put("returnUrl", returnUrl);
	 packageParams.put("notifyUrl", notifyUrl);
	 packageParams.put("transAmt", String.valueOf(fee));
	 packageParams.put("autoCode", authCode);
	 StoreInfo store = storeService.queryStoreInfoBySeller(id);
	 packageParams.put("commodityName", store.getName()+"-移动支付");
	 
	 Map<String, String> notifyParamsMap =  SzPfPost.postPay(packageParams);
	 System.out.println("---notifyParamsMap---"+notifyParamsMap.toString());
	 if (notifyParamsMap.get("signcode") == "0") {
		if ("0000".equals(notifyParamsMap.get("respCode"))||"P000".equals(notifyParamsMap.get("respCode"))) {
			 order.setStatus(1);
			 order.setCxNum(6);
			 order.setState("USERPAYING");
			 fqmicroOrderMapper.updateByPrimaryKeySelective(order);
			 SortedMap<String, String> chaxunParams = new TreeMap<String, String>();
			 chaxunParams.put("transId", "04");
			 chaxunParams.put("merNo",merNo);
			 chaxunParams.put("orderDate", packageParams.get("orderDate"));
			 chaxunParams.put("orderNo", order.getOrderNo());
			 Map<String, String> chaxunParamsMap =  SzPfPost.postPay(chaxunParams);
			 logger.info("select 1 -----"+chaxunParamsMap.toString());
			 if ("0000".equals(chaxunParamsMap.get("respCode")) && "0000".equals(chaxunParamsMap.get("origRespCode"))) {
				 thirdPay = doHandlePaySuccessSzPfWx(order.getSellerId(), store, order, chaxunParamsMap);
				 return thirdPay;
			}
			 Thread.sleep(5000);
			 int cxNum = 6;
			 while (cxNum>0) {
				  chaxunParamsMap =  SzPfPost.postPay(chaxunParams);
				  logger.info("select "+cxNum+" -----"+chaxunParamsMap.toString());
				  if ("0000".equals(chaxunParamsMap.get("respCode")) && "0000".equals(chaxunParamsMap.get("origRespCode"))) {
					 thirdPay = doHandlePaySuccessSzPfWx(order.getSellerId(), store, order, chaxunParamsMap);
					 return thirdPay;
				 }else{
					 order.setCxNum(cxNum+1);
					 fqmicroOrderMapper.updateByPrimaryKeySelective(order);
				 }
				Thread.sleep(10000);
				cxNum--;
			}
			thirdPay.setType(-1);
			return thirdPay;
		}
	}
	return null;
}

private FqThirdPay doHandlePaySuccessSzPfWx(Long  sellerId,StoreInfo store,FqMicroOrder order , Map<String, String> signMap) throws Exception{
	
	String weixinOrderNo =signMap.get("orderId") ;			
	/**
	 *  pay.weixin.micropay  10013547032
	 *	pay.alipay.micropay  20170721145800
	 *	pay.jdpay.micropay
	 *	pay.qq.micropay
	 */
	
	
	String orderNo = signMap.get("orderNo");
	String totalFee = signMap.get("transAmt");
	BigDecimal money = BigDecimal.valueOf(Long.valueOf(totalFee)).divide(new BigDecimal(100)); //支付结果中的金额单位为分;
	
	BigDecimal calTotamt =  money;
	BigDecimal rate = new BigDecimal(0); 
	BigDecimal rateMoney = new BigDecimal(0); 
	//查询本店费率
	Integer type = 1;
	
	HashMap<String, BigDecimal>  rateMap = fqStoreRateService.calculateOrderRate(money, store, type);
	
	if(rateMap!=null&&rateMap.size()>0){
		rate = rateMap.get("smallRate");	 //费率
		rateMoney = rateMap.get("rateMoney");	 //手续费
		calTotamt = rateMap.get("calTotamt");	  //扣除手续费结算的金额 
	}
	
	FqThirdPay thirdPay = new FqThirdPay();
//	String bankTypeCode = signMap.get("bank_type");
//	Integer bankType = null;
//	if (!StringUtils.isEmpty(bankTypeCode)) {
//		if (bankTypeCode.contains("DEBIT")) {
//			bankType = 3;
//		}
//		if (bankTypeCode.contains("CREDIT")) {
//			bankType = 2;
//		}
//		if (bankTypeCode.equals("CFT")) {
//			bankType = 1;
//		}
//	}
//	thirdPay.setBankType(bankType);
//	thirdPay.setBankTypeCode(bankTypeCode);
	thirdPay.setMoney(money);
	thirdPay.setOrderId(0L);
	thirdPay.setOrderNo(orderNo);
	thirdPay.setPayTime(new  Date());
	thirdPay.setSort(3);
	thirdPay.setTotamt(calTotamt);
	thirdPay.setOrderRate(rate);
	thirdPay.setRateFee(rateMoney);
	
	//5,浦发微信 6,浦发支付宝   3,光大微信 暂时没有光大支付宝 留空4
	
	thirdPay.setType(5);
	
	thirdPay.setSellerId(sellerId);
	thirdPay.setThirdOrderNo(weixinOrderNo);
//	thirdPay.setOpenId(openid);
	fqThirdPayMapper.insertSelective(thirdPay);
	SellerInfo seller = sellerInfoMapper.selectByPrimaryKey(sellerId);
	if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
		Date date0 = BaseUtil.getTimeDate(thirdPay.getPayTime());
		FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
		fqSellerStatementExample.createCriteria().andSellerIdEqualTo(sellerId).andStatementDateEqualTo(date0);
		List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
		FqSellerStatement fsstate = null;
		if (fsslist.size()>0) {
			fsstate = fsslist.get(0);
			fsstate.setTotalMoney(fsstate.getTotalMoney().add(calTotamt));
			fsstate.setTotalNum(fsstate.getTotalNum()+1);
			fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
		}else{
			fsstate = new FqSellerStatement();
			fsstate.setCreateTime(new Date());
			fsstate.setStatementDate(date0);
			fsstate.setSellerId(sellerId);
			fsstate.setState(0);
			fsstate.setTotalMoney(calTotamt);
			fsstate.setTotalNum(1);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date0);
			calendar.add(Calendar.DATE, store.getStatementPeriod());
			fsstate.setPeriodDate(calendar.getTime());
			fqSellerStatementMapper.insertSelective(fsstate);
		}
	}else{
		FqStoreCreditExample example3 = new FqStoreCreditExample();
		Date now = new Date();
		example3.createCriteria().andSellerIdEqualTo(sellerId).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
		example3.setOrderByClause("period desc");
		List<FqStoreCredit> credits = fqStoreCreditMapper.selectByExample(example3);
		if (credits.size()>0) {
			FqStoreCredit credit = credits.get(0);
			BigDecimal noRevert = credit.getNoRevert().subtract(calTotamt);
			if (noRevert.compareTo(BigDecimal.ZERO) == -1) {
				noRevert = BigDecimal.ZERO;
				credit.setStatus(3);
			}else{
				credit.setNoRevert(noRevert);
			}
			fqStoreCreditMapper.updateByPrimaryKeySelective(credit);
		}
	}
	
	
	order.setStatus(2);
	order.setCxNum(0);
	order.setState("SUCCESS");
	fqmicroOrderMapper.updateByPrimaryKeySelective(order);
	
	
	//推送信息
	HashSet<Integer> pushSet = new HashSet<Integer>();
	pushSet.add(2);
	pushSet.add(3);
//	pushSet.add(4);
	PushPayInfoTask task = new PushPayInfoTask();
	task.setSellerId(thirdPay.getSellerId());
	task.setPushTypeArr(pushSet);
	task.setMoney(thirdPay.getMoney());
	task.setTradeType("weixin");
	task.setTransactionId(orderNo);
	task.setOpenId(thirdPay.getOpenId());
	task.setPayTime(thirdPay.getPayTime());
	taskExecutor.execute(task);
	
	
	return thirdPay;
}

@Override
public Integer updateCheckNotifyWXSZPF(Map<String, String> signMap,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	response.setContentType("text/html;charset=UTF-8");
	if(signMap == null){
		logger.info(">>>>>>>>>>>>>>>>>>深圳浦发微信支付回调 参数 is null");
		// response.getWriter().print("fail");
		return 0;
	}
	logger.info(">>>>>>>>>>>>>>>>>>深圳浦发微信支付回调 参数" + signMap.toString());
	

	if (!"0000".equals(signMap.get("respCode"))) {
		logger.info(">>>>>>>>>>>>>>>>>>深圳浦发微信支付回调失败:"+signMap.toString());
		//response.getWriter().print("fail");
		return 0;
	}
	
	
     StringBuffer buf = new StringBuffer();
     String signature = "";
     for (Map.Entry<String, String> m : signMap.entrySet()) {
     if ("signature".equals(m.getKey())) {
                signature = m.getValue();
                continue;
       }
      System.out.println("排序验签字段：" + m.getKey() + "=" + m.getValue());
        buf.append(m.getKey()).append("=").append(m.getValue()).append("&");
      }
      String signatureStr = buf.substring(0, buf.length() - 1);

      boolean f = SignUtils.notifyVerify(signatureStr, signature);
	//微信支付回调通知验签
	if(f){
		
		//回调成功 -处理业务状态
		if ("0000".equals(signMap.get("respCode"))) {
			String weixinOrderNo =signMap.get("transactionId") ;			
			String payTime = signMap.get("timeEnd");	
			/**
			 *  pay.weixin.micropay
			 *	pay.alipay.micropay
			 *	pay.jdpay.micropay
			 *	pay.qq.micropay
			 */
			
			
			String orderNo = signMap.get("orderNo");
			String totalFee = signMap.get("transAmt");
			BigDecimal money = BigDecimal.valueOf(Long.valueOf(totalFee)).divide(new BigDecimal(100)); //支付结果中的金额单位为分;
			
			FqMicroOrderExample fqMicroOrderExample = new FqMicroOrderExample();
			fqMicroOrderExample.createCriteria().andOrderNoEqualTo(orderNo);
			List<FqMicroOrder> orders = fqmicroOrderMapper.selectByExample(fqMicroOrderExample);
			if (orders == null || orders.size() == 0) {
				logger.info(">>>>>>>>>>>>>>>>>>订单不存在:"+signMap.toString());
				return 0;
			}
			FqMicroOrder fqMicroOrder = orders.get(0);
			if (fqMicroOrder.getStatus()==2) {
				logger.info(">>>>>>>>>>>>>>>>>>订单回调已成功:"+signMap.toString());
			}else{
				BigDecimal calTotamt =  money;
				BigDecimal rate = new BigDecimal(0); 
				BigDecimal rateMoney = new BigDecimal(0); 
				//查询本店费率
				Integer type = 1;
				StoreInfo store = storeService.queryStoreInfoBySeller(fqMicroOrder.getSellerId());
				HashMap<String, BigDecimal>  rateMap = fqStoreRateService.calculateOrderRate(money, store, type);
				
				if(rateMap!=null&&rateMap.size()>0){
					rate = rateMap.get("smallRate");	 //费率
					rateMoney = rateMap.get("rateMoney");	 //手续费
					calTotamt = rateMap.get("calTotamt");	  //扣除手续费结算的金额 
				}
				
				FqThirdPay thirdPay = new FqThirdPay();
				String bankTypeCode = signMap.get("bank_type");
				Integer bankType = null;
				if (!StringUtils.isEmpty(bankTypeCode)) {
					if (bankTypeCode.contains("DEBIT")) {
						bankType = 3;
					}
					if (bankTypeCode.contains("CREDIT")) {
						bankType = 2;
					}
					if (bankTypeCode.equals("CFT")) {
						bankType = 1;
					}
				}
				thirdPay.setBankType(bankType);
				thirdPay.setBankTypeCode(bankTypeCode);
				thirdPay.setMoney(money);
				thirdPay.setOrderId(0L);
				thirdPay.setOrderNo(orderNo);
				thirdPay.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss", payTime));
				thirdPay.setSort(3);
				thirdPay.setTotamt(calTotamt);
				thirdPay.setOrderRate(rate);
				thirdPay.setRateFee(rateMoney);
				
				//5,浦发微信 6,浦发支付宝   3,光大微信 暂时没有光大支付宝 留空4
				
				thirdPay.setType(5);
				
				thirdPay.setSellerId(fqMicroOrder.getSellerId());
				thirdPay.setThirdOrderNo(weixinOrderNo);
				fqThirdPayMapper.insertSelective(thirdPay);
				SellerInfo seller = sellerInfoMapper.selectByPrimaryKey(fqMicroOrder.getSellerId());
				if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
					Date date0 = BaseUtil.getTimeDate(thirdPay.getPayTime());
					FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
					fqSellerStatementExample.createCriteria().andSellerIdEqualTo(fqMicroOrder.getSellerId()).andStatementDateEqualTo(date0).andStateEqualTo(0);
					List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
					FqSellerStatement fsstate = null;
					if (fsslist.size()>0) {
						fsstate = fsslist.get(0);
						fsstate.setTotalMoney(fsstate.getTotalMoney().add(calTotamt));
						fsstate.setTotalNum(fsstate.getTotalNum()+1);
						fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
					}else{
						fsstate = new FqSellerStatement();
						fsstate.setCreateTime(new Date());
						fsstate.setStatementDate(date0);
						fsstate.setSellerId(fqMicroOrder.getSellerId());
						fsstate.setState(0);
						fsstate.setTotalMoney(calTotamt);
						fsstate.setTotalNum(1);
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(date0);
						calendar.add(Calendar.DATE, store.getStatementPeriod());
						fsstate.setPeriodDate(calendar.getTime());
						fqSellerStatementMapper.insertSelective(fsstate);
					}
				}else{
					FqStoreCreditExample example3 = new FqStoreCreditExample();
					Date now = new Date();
					example3.createCriteria().andSellerIdEqualTo(fqMicroOrder.getSellerId()).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
					example3.setOrderByClause("period desc");
					List<FqStoreCredit> credits = fqStoreCreditMapper.selectByExample(example3);
					if (credits.size()>0) {
						FqStoreCredit credit = credits.get(0);
						BigDecimal noRevert = credit.getNoRevert().subtract(calTotamt);
						if (noRevert.compareTo(BigDecimal.ZERO) == -1) {
							noRevert = BigDecimal.ZERO;
							credit.setStatus(3);
						}else{
							credit.setNoRevert(noRevert);
						}
						fqStoreCreditMapper.updateByPrimaryKeySelective(credit);
					}
				}
				
				
				fqMicroOrder.setStatus(2);
				fqMicroOrder.setCxNum(0);
				fqmicroOrderMapper.updateByPrimaryKeySelective(fqMicroOrder);
				
				
				//推送信息
				HashSet<Integer> pushSet = new HashSet<Integer>();
				pushSet.add(2);
				pushSet.add(3);
				PushPayInfoTask task = new PushPayInfoTask();
				task.setSellerId(thirdPay.getSellerId());
				task.setPushTypeArr(pushSet);
				task.setMoney(thirdPay.getMoney());
				task.setTradeType("weixin");
				task.setTransactionId(orderNo);
				task.setOpenId(thirdPay.getOpenId());
				task.setPayTime(thirdPay.getPayTime());
				taskExecutor.execute(task);
				return 1;
			}
			
			
		}
		}
	return null;
}

@Override
public FqMicroOrder insertFqMicroOrder(Long id, BigDecimal money) {
	String orderNo = OrderUtil.getOrderNo(SystemConstant.SZ_PF_WX_DESK_PAY_TYPE);
	FqMicroOrder microOrder = new FqMicroOrder();
	microOrder.setCreateTime(new Date());
	microOrder.setCxNum(0);
	microOrder.setMoney(money);
	microOrder.setOrderNo(orderNo);
	microOrder.setSellerId(id);
	microOrder.setStatus(0);
	microOrder.setPayType(7);
	fqmicroOrderMapper.insertSelective(microOrder);
	return microOrder;
}

@Override
public FqMicroOrder insertFqMicroOrderToAli(Long id, BigDecimal money) {
	String orderNo = OrderUtil.getOrderNo(SystemConstant.ALI_MICOR_PAY_TYPE);
	FqMicroOrder microOrder = new FqMicroOrder();
	microOrder.setCreateTime(new Date());
	microOrder.setCxNum(4);
	microOrder.setMoney(money);
	microOrder.setOrderNo(orderNo);
	microOrder.setSellerId(id);
	microOrder.setStatus(1);
	microOrder.setPayType(6);
	fqmicroOrderMapper.insertSelective(microOrder);
	return microOrder;
}


}
