package cn.qhjys.mall.service.impl;

import java.io.IOException;
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
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.message.BasicNameValuePair;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.demo.trade.model.builder.AlipayTradePayRequestBuilder;
import com.alipay.demo.trade.model.builder.AlipayTradeQueryRequestBuilder;
import com.alipay.demo.trade.model.result.AlipayF2FPayResult;
import com.alipay.demo.trade.model.result.AlipayF2FQueryResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.qhjys.mall.common.AccessToken;
import cn.qhjys.mall.common.AlipayService;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.common.SzPfPost;
import cn.qhjys.mall.entity.FqChannelRole;
import cn.qhjys.mall.entity.FqChannelRoleExample;
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
import cn.qhjys.mall.entity.WeixinUserinfoExample;
import cn.qhjys.mall.entity.FqThirdPayExample.Criteria;
import cn.qhjys.mall.mapper.FqChannelRoleMapper;
import cn.qhjys.mall.mapper.FqMicroOrderMapper;
import cn.qhjys.mall.mapper.FqSellerStatementMapper;
import cn.qhjys.mall.mapper.FqStoreCreditMapper;
import cn.qhjys.mall.mapper.FqThirdPayMapper;
import cn.qhjys.mall.mapper.SellerInfoMapper;
import cn.qhjys.mall.mapper.custom.DaliyCreditMapper;
import cn.qhjys.mall.quartz.PushPayInfoTask;
import cn.qhjys.mall.service.AuthenticationChnnelService;
import cn.qhjys.mall.service.FqStoreRateService;
import cn.qhjys.mall.service.FqThirdPayService;
import cn.qhjys.mall.service.StoreService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.DateUtil;
import cn.qhjys.mall.util.ms.BaseRequest;
import cn.qhjys.mall.util.ms.MsConstant;
import cn.qhjys.mall.vo.OrderCountVo;
import cn.qhjys.mall.weixin.qqpay.CQpayAPIURL;
import cn.qhjys.mall.weixin.qqpay.CQpayMchSpBase;
import cn.qhjys.mall.weixin.util.HttpClientUtil;
import cn.qhjys.mall.weixin.util.MD5Util;
import cn.qhjys.mall.weixin.util.OrderUtil;
import cn.qhjys.mall.weixin.util.RequestHandler;
import cn.qhjys.mall.weixin.util.Sha1;
import cn.qhjys.mall.weixin.util.SystemConstant;
import cn.qhjys.mall.weixin.util.XMLUtil;

@Service("fqThirdPayService")
public class FqThirdPayServiceImpl extends Base implements FqThirdPayService {
	
	
	@Autowired
	private FqThirdPayMapper fqThirdPayMapper;
	@Autowired
	private SellerInfoMapper sellerInfoMapper;
	@Autowired
	private FqSellerStatementMapper fqSellerStatementMapper;
	@Autowired
	private FqStoreCreditMapper fqStoreCreditMapper;
	@Autowired
	private FqMicroOrderMapper fqmicroOrderMapper;
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	@Autowired
	private FqStoreRateService fqStoreRateService;
	@Autowired
	private DaliyCreditMapper daliyCreditMapper;
	@Autowired
	private FqChannelRoleMapper fqChannelRoleMapper;
	@Autowired
	AuthenticationChnnelService authenticationChnnelService;
	@Override
	public Page<FqThirdPay> queryFqThirdPays(Long sellerId,Integer year, Integer month,
			Integer day,Integer pageNum,Integer pageSize) {
		if (pageNum == null || pageNum <0) {
			pageNum = 1;
		}
		if (pageSize == null || pageSize <0) {
			pageSize = 10;
		}
		Date begintime = null;
		Date endtime = null;
		Calendar c = Calendar.getInstance();
		if (year != null && month != null) {
			c.set(Calendar.YEAR, year);
			c.set(Calendar.MONTH, month - 1);
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			if (day >0) {
				c.set(Calendar.DAY_OF_MONTH, day);
				begintime = c.getTime();
				c.add(Calendar.DAY_OF_MONTH, 1);
				endtime = c.getTime();
			}else{
				c.set(Calendar.DAY_OF_MONTH, 1);
				begintime = c.getTime();
				c.add(Calendar.MONTH, 1);
				endtime = c.getTime();
			}
		}
		FqThirdPayExample example = new FqThirdPayExample();
		Criteria criteria = example.createCriteria();
		criteria.andSellerIdEqualTo(sellerId);
		if (begintime != null) {
			criteria.andPayTimeGreaterThanOrEqualTo(begintime);
		}
		if (endtime != null) {
			criteria.andPayTimeLessThan(endtime);
		}
		
		example.setOrderByClause("pay_time desc");
		PageHelper.startPage(pageNum, pageSize);
		return (Page<FqThirdPay>) fqThirdPayMapper.selectByExample(example);
	}
	
	/**
	 * 兴业支付宝微信
	 */
	public FqThirdPay insertPayOrderByXYPayAPP(Long sellerId, BigDecimal money,String authCode, HttpServletRequest request,HttpServletResponse response) {
		try {
			String orderNo = "";
			Integer payType = 0;
			Integer authCodeBegin = Integer.valueOf(authCode.substring(0, 2));
			if (authCodeBegin > 9 && authCodeBegin < 16) {
				orderNo = OrderUtil.getOrderNo(SystemConstant.XY_WX_MICOR_PAY_TYPE); // 微信
				payType = 1;
			} else if (authCodeBegin == 28) {
				orderNo = OrderUtil.getOrderNo(SystemConstant.XY_ALI_MICOR_PAY_TYPE); // 支付宝
				payType = 2;
			}

			FqThirdPay thirdPay = new FqThirdPay();
			FqMicroOrder microOrder = new FqMicroOrder();
			microOrder.setCreateTime(new Date());
			microOrder.setCxNum(0);
			microOrder.setMoney(money);
			microOrder.setOrderNo(orderNo);
			microOrder.setSellerId(sellerId);
			microOrder.setStatus(0);
			microOrder.setPayType(payType);
			fqmicroOrderMapper.insertSelective(microOrder);

			String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
			StoreInfo store = storeService.queryStoreInfoBySeller(sellerId);
			String MCH_ID = authenticationChnnelService.findByXyAuthcationInfo(store.getId()).getXyMerchantNum();
			String PAY_KEY = authenticationChnnelService.findByXyAuthcationInfo(store.getId()).getXyBankKey();
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
			packageParams.put("service", "unified.trade.micropay");
			packageParams.put("mch_id", MCH_ID);
			packageParams.put("nonce_str", UUID.randomUUID().toString().replace("-", "")); // 商家号
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
			Map<String, String> signMap = new HashMap<String, String>();
			signMap = XMLUtil.doXMLParse(result);
			if (signMap == null) {
				logger.info(">>>>>>>>>>>>>>>>>>XY MICRO支付回调 参数 is null");
				microOrder.setStatus(1);
				microOrder.setCxNum(6);
				microOrder.setState("ERROR");
				fqmicroOrderMapper.updateByPrimaryKey(microOrder);
				return null;
			}

			logger.info(">>>>>>>>>>>>>>>>>>XY APP MICRO支付回调 参数" + signMap.toString());

			if (!"0".equals(signMap.get("status"))) {
				logger.info(">>>>>>>>>>>>>>>>>>XY MICRO支付回调，通信异常：" + signMap.toString());
				microOrder.setStatus(1);
				microOrder.setCxNum(6);
				microOrder.setState("ERROR");
				fqmicroOrderMapper.updateByPrimaryKey(microOrder);
				return null;
			}
			if (!"0".equals(signMap.get("result_code"))) {
				logger.info(">>>>>>>>>>>>>>>>>>XY APP MICRO支付回调失败:" + signMap.toString());
				if ("USERPAYING".equals(signMap.get("err_code")) || "10003".equals(signMap.get("err_code"))) { 
					thirdPay.setType(-1);
					microOrder.setStatus(1);
					microOrder.setCxNum(6);
					microOrder.setState("USERPAYING");
					fqmicroOrderMapper.updateByPrimaryKey(microOrder);
					return thirdPay;
				}else{
					thirdPay.setType(-1);
					microOrder.setStatus(1);
					microOrder.setCxNum(2);
					microOrder.setState("ERROR");
					fqmicroOrderMapper.updateByPrimaryKey(microOrder);
					return thirdPay;
				}
			}
				if ("0".equals(signMap.get("status")) && "0".equals(signMap.get("result_code"))
						&& "0".equals(signMap.get("pay_result"))) {
					String weixinOrderNo = signMap.get("transaction_id");
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
					
					thirdPay.setBankType(bankType);
					thirdPay.setBankTypeCode(bankTypeCode);
					thirdPay.setMoney(money);
					thirdPay.setOrderId(0L);
					thirdPay.setOrderNo(orderNo);
					thirdPay.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss", payTime));
					thirdPay.setSort(98);
					//5,浦发微信 6,浦发支付宝 
					if(tradeType.contains("weixin")){
						thirdPay.setType(5);
					}
					if(tradeType.contains("alipay")){
						thirdPay.setType(6);
					}
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
						fqSellerStatementExample.createCriteria().andSellerIdEqualTo(sellerId)
								.andStatementDateEqualTo(date0).andStateEqualTo(98);
						List<FqSellerStatement> fsslist = fqSellerStatementMapper
								.selectByExample(fqSellerStatementExample);
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
							fsstate.setState(98);
							fsstate.setTotalMoney(calTotamt);
							fsstate.setTotalNum(1);
							Calendar c = Calendar.getInstance();
							c.setTime(date0);
							c.add(Calendar.DATE, store.getStatementPeriod());
							fsstate.setPeriodDate(c.getTime());
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
					microOrder.setStatus(2);
					fqmicroOrderMapper.updateByPrimaryKey(microOrder);
					
					WeixinUserinfoExample example2 = new WeixinUserinfoExample();
					example2.createCriteria().andStoreIdEqualTo(store.getId());

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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	/**
	 * 兴业支付宝微信
	 */
	public FqThirdPay insertPayOrderByXYEQPayAPP(Long sellerId, BigDecimal money,String authCode, HttpServletRequest request,HttpServletResponse response) {
		try {
			String orderNo = "";
			Integer payType = 0;
			Integer authCodeBegin = Integer.valueOf(authCode.substring(0, 2));
			if (authCodeBegin > 9 && authCodeBegin < 16) {
				orderNo = OrderUtil.getOrderNo(SystemConstant.XY_WX_MICOR_PAY_TYPE); // 微信
				payType = 11;
			} else if (authCodeBegin == 28) {
				orderNo = OrderUtil.getOrderNo(SystemConstant.XY_ALI_MICOR_PAY_TYPE); // 支付宝
				payType = 12;
			}

			FqThirdPay thirdPay = new FqThirdPay();
			FqMicroOrder microOrder = new FqMicroOrder();
			microOrder.setCreateTime(new Date());
			microOrder.setCxNum(0);
			microOrder.setMoney(money);
			microOrder.setOrderNo(orderNo);
			microOrder.setSellerId(sellerId);
			microOrder.setStatus(0);
			microOrder.setPayType(payType);
			fqmicroOrderMapper.insertSelective(microOrder);

			String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
			StoreInfo store = storeService.queryStoreInfoBySeller(sellerId);
			String MCH_ID = ConstantsConfigurer.getProperty("xy_eqpay_mch_id");
			String PAY_KEY = ConstantsConfigurer.getProperty("xy_eqpay_key");
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
			packageParams.put("service", "unified.trade.micropay");
			packageParams.put("mch_id", MCH_ID);
			packageParams.put("nonce_str", UUID.randomUUID().toString().replace("-", "")); // 商家号
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
			Map<String, String> signMap = new HashMap<String, String>();
			signMap = XMLUtil.doXMLParse(result);
			if (signMap == null) {
				logger.info(">>>>>>>>>>>>>>>>>>XY MICRO支付回调 参数 is null");
				microOrder.setStatus(1);
				microOrder.setCxNum(6);
				microOrder.setState("ERROR");
				fqmicroOrderMapper.updateByPrimaryKey(microOrder);
				return null;
			}

			logger.info(">>>>>>>>>>>>>>>>>>XY APP MICRO支付回调 参数" + signMap.toString());

			if (!"0".equals(signMap.get("status"))) {
				logger.info(">>>>>>>>>>>>>>>>>>XY MICRO支付回调，通信异常：" + signMap.toString());
				microOrder.setStatus(1);
				microOrder.setCxNum(6);
				microOrder.setState("ERROR");
				fqmicroOrderMapper.updateByPrimaryKey(microOrder);
				return null;
			}
			if (!"0".equals(signMap.get("result_code"))) {
				logger.info(">>>>>>>>>>>>>>>>>>XY APP MICRO支付回调失败:" + signMap.toString());
				if ("USERPAYING".equals(signMap.get("err_code")) || "10003".equals(signMap.get("err_code"))) { 
					thirdPay.setType(-1);
					microOrder.setStatus(1);
					microOrder.setCxNum(6);
					microOrder.setState("USERPAYING");
					fqmicroOrderMapper.updateByPrimaryKey(microOrder);
					return thirdPay;
				}else{
					thirdPay.setType(-1);
					microOrder.setStatus(1);
					microOrder.setCxNum(2);
					microOrder.setState("ERROR");
					fqmicroOrderMapper.updateByPrimaryKey(microOrder);
					return thirdPay;
				}
			}
				if ("0".equals(signMap.get("status")) && "0".equals(signMap.get("result_code"))
						&& "0".equals(signMap.get("pay_result"))) {
					String weixinOrderNo = signMap.get("transaction_id");
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
					
					thirdPay.setBankType(bankType);
					thirdPay.setBankTypeCode(bankTypeCode);
					thirdPay.setMoney(money);
					thirdPay.setOrderId(0L);
					thirdPay.setOrderNo(orderNo);
					thirdPay.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss", payTime));
					thirdPay.setSort(1);
					//5,浦发微信 6,浦发支付宝 
					if(tradeType.contains("weixin")){
						thirdPay.setType(5);
					}
					if(tradeType.contains("alipay")){
						thirdPay.setType(6);
					}
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
						fqSellerStatementExample.createCriteria().andSellerIdEqualTo(sellerId)
								.andStatementDateEqualTo(date0).andStateEqualTo(0);
						List<FqSellerStatement> fsslist = fqSellerStatementMapper
								.selectByExample(fqSellerStatementExample);
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
							Calendar c = Calendar.getInstance();
							c.setTime(date0);
							c.add(Calendar.DATE, store.getStatementPeriod());
							fsstate.setPeriodDate(c.getTime());
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
					microOrder.setStatus(2);
					fqmicroOrderMapper.updateByPrimaryKey(microOrder);
					
					WeixinUserinfoExample example2 = new WeixinUserinfoExample();
					example2.createCriteria().andStoreIdEqualTo(store.getId());

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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	/**
	 * 广大支付宝微信
	 */
	public FqThirdPay insertPayOrderByGdPay(Long sellerId, BigDecimal money,String authCode, HttpServletRequest request,HttpServletResponse response) {
		try {
			String orderNo = "";
			Integer payType = 0;
			Integer authCodeBegin = Integer.valueOf(authCode.substring(0, 2));
			if (authCodeBegin > 9 && authCodeBegin < 16) {
				orderNo = OrderUtil.getOrderNo(SystemConstant.GD_WX_MICOR_PAY_TYPE); // 微信
				payType = 1;
			} else if (authCodeBegin == 28) {
				orderNo = OrderUtil.getOrderNo(SystemConstant.GD_ALI_MICOR_PAY_TYPE); // 支付宝
				payType = 2;
			}

			FqThirdPay thirdPay = new FqThirdPay();
			FqMicroOrder microOrder = new FqMicroOrder();
			microOrder.setCreateTime(new Date());
			microOrder.setCxNum(0);
			microOrder.setMoney(money);
			microOrder.setOrderNo(orderNo);
			microOrder.setSellerId(sellerId);
			microOrder.setStatus(0);
			microOrder.setPayType(payType);
			fqmicroOrderMapper.insertSelective(microOrder);

			String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
			String MCH_ID = ConstantsConfigurer.getProperty(SystemConstant.GD_MCH_ID);
			String PAY_KEY = ConstantsConfigurer.getProperty(SystemConstant.GD_PAY_KEY);
			StoreInfo store = storeService.queryStoreInfoBySeller(sellerId);
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
			packageParams.put("service", "unified.trade.micropay");
			packageParams.put("mch_id", MCH_ID);
			packageParams.put("nonce_str", UUID.randomUUID().toString().replace("-", "")); // 商家号
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
			Map<String, String> signMap = new HashMap<String, String>();
			signMap = XMLUtil.doXMLParse(result);
			if (signMap == null) {
				logger.info(">>>>>>>>>>>>>>>>>>GD MICRO支付回调 参数 is null");
				microOrder.setStatus(1);
				microOrder.setCxNum(6);
				microOrder.setState("ERROR");
				fqmicroOrderMapper.updateByPrimaryKey(microOrder);
				return null;
			}

			logger.info(">>>>>>>>>>>>>>>>>>GD APP MICRO支付回调 参数" + signMap.toString());

			if (!"0".equals(signMap.get("status"))) {
				logger.info(">>>>>>>>>>>>>>>>>>GD MICRO支付回调，通信异常：" + signMap.toString());
				microOrder.setStatus(1);
				microOrder.setCxNum(6);
				microOrder.setState("ERROR");
				fqmicroOrderMapper.updateByPrimaryKey(microOrder);
				return null;
			}
			if (!"0".equals(signMap.get("result_code"))) {
				logger.info(">>>>>>>>>>>>>>>>>>GD APP MICRO支付回调失败:" + signMap.toString());
				if ("USERPAYING".equals(signMap.get("err_code")) || "10003".equals(signMap.get("err_code"))) { 
					thirdPay.setType(-1);
					microOrder.setStatus(1);
					microOrder.setCxNum(6);
					microOrder.setState("USERPAYING");
					fqmicroOrderMapper.updateByPrimaryKey(microOrder);
					return thirdPay;
				}else{
					thirdPay.setType(-1);
					microOrder.setStatus(1);
					microOrder.setCxNum(2);
					microOrder.setState("ERROR");
					fqmicroOrderMapper.updateByPrimaryKey(microOrder);
					return thirdPay;
				}
			}
			if (isSign(signMap,"GD")) {
				if ("0".equals(signMap.get("status")) && "0".equals(signMap.get("result_code"))
						&& "0".equals(signMap.get("pay_result"))) {
					String weixinOrderNo = signMap.get("transaction_id");
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
					
					thirdPay.setBankType(bankType);
					thirdPay.setBankTypeCode(bankTypeCode);
					thirdPay.setMoney(money);
					thirdPay.setOrderId(0L);
					thirdPay.setOrderNo(orderNo);
					thirdPay.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss", payTime));
					thirdPay.setSort(3);
					//5,浦发微信 6,浦发支付宝 
					if(tradeType.contains("weixin")){
						thirdPay.setType(3);
					}
					if(tradeType.contains("alipay")){
						thirdPay.setType(6);
					}
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
						fqSellerStatementExample.createCriteria().andSellerIdEqualTo(sellerId)
								.andStatementDateEqualTo(date0).andStateEqualTo(0);
						List<FqSellerStatement> fsslist = fqSellerStatementMapper
								.selectByExample(fqSellerStatementExample);
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
							Calendar c = Calendar.getInstance();
							c.setTime(date0);
							c.add(Calendar.DATE, store.getStatementPeriod());
							fsstate.setPeriodDate(c.getTime());
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
					microOrder.setStatus(2);
					fqmicroOrderMapper.updateByPrimaryKey(microOrder);
					
					WeixinUserinfoExample example2 = new WeixinUserinfoExample();
					example2.createCriteria().andStoreIdEqualTo(store.getId());

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
			} else {
				microOrder.setStatus(1);
				microOrder.setCxNum(6);
				microOrder.setState("ERROR");
				fqmicroOrderMapper.updateByPrimaryKey(microOrder);
				logger.info(">>>>>>>>>>>>>>>>>>PF MICRO支付回调失败:签名验证失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
//	@Override
//	public FqThirdPay insertPayOrderByGdPay(Long sellerId, BigDecimal money,
//			String authCode, HttpServletRequest request,
//			HttpServletResponse response) {
//		
//		try {
//		FqThirdPay thirdPay = new FqThirdPay();
//		String orderNo = OrderUtil.getOrderNo(SystemConstant.GD_MICOR_PAY_TYPE);
//		FqMicroOrder microOrder = new FqMicroOrder();
//		microOrder.setCreateTime(new Date());
//		microOrder.setCxNum(0);
//		microOrder.setMoney(money);
//		microOrder.setOrderNo(orderNo);
//		microOrder.setSellerId(sellerId);
//		microOrder.setStatus(0);
//		microOrder.setPayType(1);
//		fqmicroOrderMapper.insertSelective(microOrder);
//	
//		String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
//		String MCH_ID = ConstantsConfigurer.getProperty(SystemConstant.GD_MCH_ID);
//		String PAY_KEY = ConstantsConfigurer.getProperty(SystemConstant.GD_PAY_KEY);
//		StoreInfo store = storeService.queryStoreInfoBySeller(sellerId);
//		SortedMap<String, String> packageParams = new TreeMap<String, String>();
//		packageParams.put("service", "unified.trade.micropay"); 
//        packageParams.put("mch_id", MCH_ID); 		
//        packageParams.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));  //商家号
//        packageParams.put("body",store.getName()+"-移动支付"); //商品描述   
//        packageParams.put("out_trade_no", orderNo); 	
//        int fee = money.multiply(new BigDecimal(100)).intValue();
//        packageParams.put("total_fee", String.valueOf(fee)); 
//        packageParams.put("mch_create_ip",request.getRemoteAddr());
//        packageParams.put("auth_code",authCode);
//        String sign = createSignGd(packageParams, PAY_KEY);
//		packageParams.put("sign", sign);
//		String requestXML = XMLUtil.getRequestXml(packageParams);
//	    String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
//	    Map<String, String> signMap = new HashMap<String, String>();
//		signMap = XMLUtil.doXMLParse(result);
//		if(signMap == null){
//			  logger.info(">>>>>>>>>>>>>>>>>>GD MICRO支付回调 参数 is null");
//			  microOrder.setStatus(1);
//				microOrder.setCxNum(6);
//				microOrder.setState("ERROR");
//				fqmicroOrderMapper.updateByPrimaryKey(microOrder);
//              return null;
//		}
//		
//		logger.info(">>>>>>>>>>>>>>>>>>GD MICRO支付回调 参数" + signMap.toString());
//		
//		if (!"0".equals(signMap.get("status"))) {
//			logger.info(">>>>>>>>>>>>>>>>>>GD MICRO支付回调，通信异常："+signMap.toString());
//			microOrder.setStatus(1);
//			microOrder.setCxNum(6);
//			microOrder.setState("ERROR");
//			fqmicroOrderMapper.updateByPrimaryKey(microOrder);
//			 return null;
//		}
//		
//		if (!"0".equals(signMap.get("result_code"))) {
//			logger.info(">>>>>>>>>>>>>>>>>>PF APP MICRO支付回调失败:" + signMap.toString());
//			if ("USERPAYING".equals(signMap.get("err_code")) || "10003".equals(signMap.get("err_code"))) { 
//				thirdPay.setType(-1);
//				microOrder.setStatus(1);
//				microOrder.setCxNum(6);
//				microOrder.setState("USERPAYING");
//				fqmicroOrderMapper.updateByPrimaryKey(microOrder);
//				return thirdPay;
//			}else{
//				thirdPay.setType(-1);
//				microOrder.setStatus(1);
//				microOrder.setCxNum(2);
//				microOrder.setState("ERROR");
//				fqmicroOrderMapper.updateByPrimaryKey(microOrder);
//				return thirdPay;
//			}
//		}
//		if(isSign(signMap,"GD")){
//			if ("0".equals(signMap.get("status")) && "0".equals(signMap.get("result_code")) && "0".equals(signMap.get("pay_result"))) {
//				String weixinOrderNo =signMap.get("transaction_id") ;			
//				String openid = signMap.get("sub_openid");
//				if (StringUtils.isEmpty(openid)) {
//					openid = signMap.get("openid");
//				}
//				String payTime = signMap.get("time_end");
//				String bankTypeCode = signMap.get("bank_type");
//				Integer bankType = null;
//				if (!StringUtils.isEmpty(bankTypeCode)) {
//					if (bankTypeCode.contains("DEBIT")) {
//						bankType = 3;
//					}
//					if (bankTypeCode.contains("CREDIT")) {
//						bankType = 2;
//					}
//					if (bankTypeCode.equals("CFT")) {
//						bankType = 1;
//					}
//				}
//				
//				BigDecimal calTotamt =  money;
//				BigDecimal rate = new BigDecimal(0); 
//				BigDecimal rateMoney = new BigDecimal(0); 
//				//查询本店费率
//				Integer type = 1;
//				HashMap<String, BigDecimal>  rateMap = fqStoreRateService.calculateOrderRate(money, store, type);
//				if(rateMap!=null&&rateMap.size()>0){
//					rate = rateMap.get("smallRate");	 //费率
//					rateMoney = rateMap.get("rateMoney");	 //手续费
//					calTotamt = rateMap.get("calTotamt");	  //扣除手续费结算的金额 
//				}
//				
//				thirdPay.setBankType(bankType);
//				thirdPay.setBankTypeCode(bankTypeCode);
//				thirdPay.setMoney(money);
//				thirdPay.setOrderId(0L);
//				thirdPay.setOrderNo(orderNo);
//				thirdPay.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss", payTime));
//				thirdPay.setSort(3);
//				thirdPay.setType(3);
//				thirdPay.setSellerId(sellerId);
//				thirdPay.setTotamt(calTotamt);
//				thirdPay.setOrderRate(rate);
//				thirdPay.setRateFee(rateMoney);
//				thirdPay.setThirdOrderNo(weixinOrderNo);
//				thirdPay.setOpenId(openid);
//				fqThirdPayMapper.insertSelective(thirdPay);
//				SellerInfo seller = sellerInfoMapper.selectByPrimaryKey(sellerId);
//				if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
//					Date date0 = BaseUtil.getTimeDate(thirdPay.getPayTime());
//					FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
//					fqSellerStatementExample.createCriteria().andSellerIdEqualTo(sellerId).andStatementDateEqualTo(date0);
//					List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
//					FqSellerStatement fsstate = null;
//					if (fsslist.size()>0) {
//						fsstate = fsslist.get(0);
//						fsstate.setTotalMoney(fsstate.getTotalMoney().add(calTotamt));
//						fsstate.setTotalNum(fsstate.getTotalNum()+1);
//						fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
//					}else{
//						fsstate = new FqSellerStatement();
//						fsstate.setCreateTime(new Date());
//						fsstate.setStatementDate(date0);
//						fsstate.setSellerId(sellerId);
//						fsstate.setState(0);
//						fsstate.setTotalMoney(calTotamt);
//						fsstate.setTotalNum(1);
//						Calendar c = Calendar.getInstance();
//						c.setTime(date0);
//						c.add(Calendar.DATE, store.getStatementPeriod());
//						fsstate.setPeriodDate(c.getTime());
//						fqSellerStatementMapper.insertSelective(fsstate);
//					}
//				}else{
//					FqStoreCreditExample example3 = new FqStoreCreditExample();
//					Date now = new Date();
//					example3.createCriteria().andSellerIdEqualTo(sellerId).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
//					example3.setOrderByClause("period desc");
//					List<FqStoreCredit> credits = fqStoreCreditMapper.selectByExample(example3);
//					if (credits.size()>0) {
//						FqStoreCredit credit = credits.get(0);
//						BigDecimal noRevert = credit.getNoRevert().subtract(calTotamt);
//						if (noRevert.compareTo(BigDecimal.ZERO) == -1) {
//							noRevert = BigDecimal.ZERO;
//							credit.setStatus(3);
//						}else{
//							credit.setNoRevert(noRevert);
//						}
//						fqStoreCreditMapper.updateByPrimaryKeySelective(credit);
//					}
//				}
//				microOrder.setStatus(2);
//				fqmicroOrderMapper.updateByPrimaryKey(microOrder);
//				
//				WeixinUserinfoExample example2 = new WeixinUserinfoExample();
//				example2.createCriteria().andStoreIdEqualTo(store.getId());
//				
//				//推送信息
//				HashSet<Integer> pushSet = new HashSet<Integer>();
//				pushSet.add(1);
//				PushPayInfoTask task = new PushPayInfoTask();
//				task.setSellerId(store.getSellerId());
//				task.setPushTypeArr(pushSet);
//				task.setMoney(thirdPay.getMoney());
//				task.setTradeType("weixin");
//				task.setTransactionId(orderNo);
//				task.setOpenId(thirdPay.getOpenId());
//				task.setPayTime(thirdPay.getPayTime());
//				taskExecutor.execute(task);
//				
//				
//				return thirdPay;
//			}
//		}else{
//			logger.info(">>>>>>>>>>>>>>>>>>GD MICRO支付回调失败:签名验证失败");
//		}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//		return null;
//	}
//	
	/**
	 * 浦发银行（微信，支付宝支付）
	 * 
	 */
	public FqThirdPay insertPayOrderByPFPay(Long sellerId, BigDecimal money,String authCode, HttpServletRequest request,HttpServletResponse response) {
		try {
			String orderNo = "";
			Integer payType = 0;
			Integer authCodeBegin = Integer.valueOf(authCode.substring(0, 2));
			if (authCodeBegin > 9 && authCodeBegin < 16) {
				orderNo = OrderUtil.getOrderNo(SystemConstant.PF_WX_MICOR_PAY_TYPE); // 微信
				payType = 1;
			} else if (authCodeBegin == 28) {
				orderNo = OrderUtil.getOrderNo(SystemConstant.PF_ALI_MICOR_PAY_TYPE); // 支付宝
				payType = 2;
			}

			FqThirdPay thirdPay = new FqThirdPay();
			FqMicroOrder microOrder = new FqMicroOrder();
			microOrder.setCreateTime(new Date());
			microOrder.setCxNum(0);
			microOrder.setMoney(money);
			microOrder.setOrderNo(orderNo);
			microOrder.setSellerId(sellerId);
			microOrder.setStatus(0);
			microOrder.setPayType(payType);
			fqmicroOrderMapper.insertSelective(microOrder);

			String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
			String MCH_ID = ConstantsConfigurer.getProperty(SystemConstant.PF_MCH_ID);
			String PAY_KEY = ConstantsConfigurer.getProperty(SystemConstant.PF_PAY_KEY);
			StoreInfo store = storeService.queryStoreInfoBySeller(sellerId);
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
			packageParams.put("service", "unified.trade.micropay");
			packageParams.put("mch_id", MCH_ID);
			packageParams.put("nonce_str", UUID.randomUUID().toString().replace("-", "")); // 商家号
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
			Map<String, String> signMap = new HashMap<String, String>();
			signMap = XMLUtil.doXMLParse(result);
			if (signMap == null) {
				logger.info(">>>>>>>>>>>>>>>>>>PF MICRO支付回调 参数 is null");
				microOrder.setStatus(1);
				microOrder.setCxNum(6);
				microOrder.setState("ERROR");
				fqmicroOrderMapper.updateByPrimaryKey(microOrder);
				return null;
			}

			logger.info(">>>>>>>>>>>>>>>>>>PF APP MICRO支付回调 参数" + signMap.toString());

			if (!"0".equals(signMap.get("status"))) {
				logger.info(">>>>>>>>>>>>>>>>>>PF MICRO支付回调，通信异常：" + signMap.toString());
				microOrder.setStatus(1);
				microOrder.setCxNum(6);
				microOrder.setState("ERROR");
				fqmicroOrderMapper.updateByPrimaryKey(microOrder);
				return null;
			}
			if (!"0".equals(signMap.get("result_code"))) {
				logger.info(">>>>>>>>>>>>>>>>>>PF APP MICRO支付回调失败:" + signMap.toString());
				if ("USERPAYING".equals(signMap.get("err_code")) || "10003".equals(signMap.get("err_code"))) { 
					thirdPay.setType(-1);
					microOrder.setStatus(1);
					microOrder.setCxNum(6);
					microOrder.setState("USERPAYING");
					fqmicroOrderMapper.updateByPrimaryKey(microOrder);
					return thirdPay;
				}else{
					thirdPay.setType(-1);
					microOrder.setStatus(1);
					microOrder.setCxNum(2);
					microOrder.setState("ERROR");
					fqmicroOrderMapper.updateByPrimaryKey(microOrder);
					return thirdPay;
				}
			}
			if (isSign(signMap,"PF")) {
				if ("0".equals(signMap.get("status")) && "0".equals(signMap.get("result_code"))
						&& "0".equals(signMap.get("pay_result"))) {
					String weixinOrderNo = signMap.get("transaction_id");
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
					
					thirdPay.setBankType(bankType);
					thirdPay.setBankTypeCode(bankTypeCode);
					thirdPay.setMoney(money);
					thirdPay.setOrderId(0L);
					thirdPay.setOrderNo(orderNo);
					thirdPay.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss", payTime));
					thirdPay.setSort(3);
					//5,浦发微信 6,浦发支付宝 
					if(tradeType.contains("weixin")){
						thirdPay.setType(5);
					}
					if(tradeType.contains("alipay")){
						thirdPay.setType(6);
					}
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
						fqSellerStatementExample.createCriteria().andSellerIdEqualTo(sellerId)
								.andStatementDateEqualTo(date0).andStateEqualTo(0);
						List<FqSellerStatement> fsslist = fqSellerStatementMapper
								.selectByExample(fqSellerStatementExample);
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
							Calendar c = Calendar.getInstance();
							c.setTime(date0);
							c.add(Calendar.DATE, store.getStatementPeriod());
							fsstate.setPeriodDate(c.getTime());
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
					microOrder.setStatus(2);
					fqmicroOrderMapper.updateByPrimaryKey(microOrder);
					
					WeixinUserinfoExample example2 = new WeixinUserinfoExample();
					example2.createCriteria().andStoreIdEqualTo(store.getId());

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
			} else {
				microOrder.setStatus(1);
				microOrder.setCxNum(6);
				microOrder.setState("ERROR");
				fqmicroOrderMapper.updateByPrimaryKey(microOrder);
				logger.info(">>>>>>>>>>>>>>>>>>PF MICRO支付回调失败:签名验证失败");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	/**
	 *  bankType GD光大银行 PF浦发银行
	 * @param paramsMap
	 * @return
	 */
	public static boolean isSign(Map<String,String> paramsMap,String bankType) throws Exception{
		
		boolean flag = false;
		String pkey="";
		if(bankType.equals("GD")){
			pkey = ConstantsConfigurer.getProperty(SystemConstant.GD_PAY_KEY);
		}else if(bankType.equals("PF")){
			pkey = ConstantsConfigurer.getProperty(SystemConstant.PF_PAY_KEY);
		}
		else if(bankType.equals("XY")){
			pkey = ConstantsConfigurer.getProperty("xy_eqpay_key");
		}
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
	public String createSignGd(SortedMap<String, String> packageParams,String key) {
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

	@Override
	public FqThirdPay insertPayOrderByWxPay(Long sellerId, BigDecimal money,
			String authCode,HttpServletRequest request,HttpServletResponse response) {
	    try {
	    	
	    	FqThirdPay thirdPay = new FqThirdPay();
	    	String orderNo = OrderUtil.getOrderNo(SystemConstant.WX_MICOR_PAY_TYPE);
			FqMicroOrder microOrder = new FqMicroOrder();
			microOrder.setCreateTime(new Date());
			microOrder.setCxNum(0);
			microOrder.setMoney(money);
			microOrder.setOrderNo(orderNo);
			microOrder.setSellerId(sellerId);
			microOrder.setStatus(0);
			microOrder.setPayType(5);
			fqmicroOrderMapper.insertSelective(microOrder);
			
			
			String JSAPI_URL = "https://api.mch.weixin.qq.com/pay/micropay";
			String appId = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_APPID);
			String partner = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_PAY_PARTNER);
			String pkey = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_PAY_PKEY);
			StoreInfo store = storeService.queryStoreInfoBySeller(sellerId);
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
				if ("SUCCESS".equals(signMap.get("return_code"))&&"SUCCESS".equals(signMap.get("result_code"))) {
						String weixinOrderNo = signMap.get("transaction_id");
						String openid = signMap.get("openid");
						String payTime = signMap.get("time_end");	
						/**
						 *  pay.weixin.micropay
						 *	pay.alipay.micropay
						 *	pay.jdpay.micropay
						 *	pay.qq.micropay
						 */
						
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
						
						thirdPay.setBankType(bankType);
						thirdPay.setBankTypeCode(bankTypeCode);
						thirdPay.setMoney(money);
						thirdPay.setOrderId(0L);
						thirdPay.setOrderNo(orderNo);
						thirdPay.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss", payTime));
						thirdPay.setSort(3);
						//5,浦发微信 6,浦发支付宝 
						thirdPay.setType(1);
						
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
							fqSellerStatementExample.createCriteria().andSellerIdEqualTo(sellerId)
									.andStatementDateEqualTo(date0).andStateEqualTo(0);
							List<FqSellerStatement> fsslist = fqSellerStatementMapper
									.selectByExample(fqSellerStatementExample);
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
								Calendar c = Calendar.getInstance();
								c.setTime(date0);
								c.add(Calendar.DATE, store.getStatementPeriod());
								fsstate.setPeriodDate(c.getTime());
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
						microOrder.setStatus(2);
						fqmicroOrderMapper.updateByPrimaryKey(microOrder);
						
						WeixinUserinfoExample example2 = new WeixinUserinfoExample();
						example2.createCriteria().andStoreIdEqualTo(store.getId());

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
					}else{
						if ("USERPAYING".equals(signMap.get("err_code"))) { 
							thirdPay.setType(-1);
							microOrder.setStatus(1);
							microOrder.setCxNum(6);
							microOrder.setState("USERPAYING");
							fqmicroOrderMapper.updateByPrimaryKey(microOrder);
							return thirdPay;
						}else{
							thirdPay.setType(-1);
							microOrder.setStatus(1);
							microOrder.setCxNum(2);
							microOrder.setState(signMap.get("err_code"));
							fqmicroOrderMapper.updateByPrimaryKey(microOrder);
							return thirdPay;
						}
						
					}
					
			}else{
				logger.info(">>>>>>>>>>>>>>>>>>微信支付回调失败:签名验证失败");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
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
	
	public String createSign(SortedMap<String, String> packageParams,String key) {
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

	@Override
	public FqThirdPay insertPayOrderByAliPay(Long sellerId1, BigDecimal money,
			String authCode,FqMicroOrder microOrder, HttpServletRequest request,
			HttpServletResponse response) throws ParseException {
		FqThirdPay thirdPay = new FqThirdPay();
		thirdPay.setMoney(money);
		thirdPay.setOrderId(0L);
		thirdPay.setOrderNo(microOrder.getOrderNo());
		thirdPay.setPayTime(new Date());
		thirdPay.setSort(3);
		thirdPay.setType(2);
		thirdPay.setSellerId(sellerId1);
		thirdPay.setTotamt(money);
		
		
		StoreInfo store = storeService.queryStoreInfoBySeller(sellerId1);
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
		 logger.info("支付宝 desk app---"+JSON.toJSONString(result));
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
						Calendar c = Calendar.getInstance();
						c.setTime(date0);
						c.add(Calendar.DATE, store.getStatementPeriod());
						fsstate.setPeriodDate(c.getTime());
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
		     break;
		
		 default:
			 logger.error("不支持的交易状态，交易返回异常!!!");
			 	microOrder.setStatus(1);
				microOrder.setState("ERROR");
				microOrder.setCxNum(3);
				fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
		     break;
		}
		return null;
}

	@Override
	public int updateMicroOrder() throws Exception {
		FqMicroOrderExample example = new FqMicroOrderExample();
		example.createCriteria().andStatusEqualTo(1).andCxNumGreaterThan(0);
		List<FqMicroOrder> list = fqmicroOrderMapper.selectByExample(example);
		int c = 0;
		if (list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				FqMicroOrder micro = list.get(i);
				if (micro.getPayType() == 1 || micro.getPayType() == 2) {
					String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
					String MCH_ID="";
					String PAY_KEY="";
					String orderNo = micro.getOrderNo();
					String bank="";
					if(orderNo.startsWith("PX")||orderNo.startsWith("PI")){ //浦发微信 浦发支付宝
						 MCH_ID = ConstantsConfigurer.getProperty(SystemConstant.PF_MCH_ID);
						 PAY_KEY = ConstantsConfigurer.getProperty(SystemConstant.PF_PAY_KEY);
						 bank="PF";
					}else if(orderNo.startsWith("GI")||orderNo.startsWith("GW")){ //光大微信
						 MCH_ID = ConstantsConfigurer.getProperty(SystemConstant.GD_MCH_ID);
						 PAY_KEY = ConstantsConfigurer.getProperty(SystemConstant.GD_PAY_KEY);
						 bank="GD";
					}
					
					SortedMap<String, String> packageParams = new TreeMap<String, String>();
					packageParams.put("service", "unified.trade.query"); 
			        packageParams.put("mch_id", MCH_ID); 		
			        packageParams.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
			        packageParams.put("out_trade_no",orderNo); 	
			        String sign = createSignGd(packageParams, PAY_KEY);
					packageParams.put("sign", sign);
					String requestXML = XMLUtil.getRequestXml(packageParams);
				    String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
				    Map<String, String> signMap = new HashMap<String, String>();
					signMap = XMLUtil.doXMLParse(result);
					if (!"0".equals(signMap.get("status"))) {
						logger.info(">>>>>>>>>>>>>>>>>> MICRO SELECT，通信异常："+signMap.toString());
						micro.setCxNum(micro.getCxNum()-1);
						fqmicroOrderMapper.updateByPrimaryKeySelective(micro);
						continue;
					}
					if (!"0".equals(signMap.get("result_code"))) {
						logger.info(">>>>>>>>>>>>>>>>>>MICRO SELECT失败:"+signMap.toString());
						micro.setCxNum(micro.getCxNum()-1);
						fqmicroOrderMapper.updateByPrimaryKeySelective(micro);
						continue;
					}
					
					logger.info("定时器补单:"+orderNo+">>>>>>>>>>>>>>>>>>MICRO 查询参数:"+signMap.toString());
					
					if(isSign(signMap,bank)){
						if ("0".equals(signMap.get("status")) && "0".equals(signMap.get("result_code"))) {
							String trade_state = signMap.get("trade_state");
							if (trade_state.equals("SUCCESS")) {
								String weixinOrderNo =signMap.get("transaction_id") ;
								String payTime = signMap.get("time_end");	
								/**
								 *  pay.weixin.micropay
								 *	pay.alipay.micropay
								 *	pay.jdpay.micropay
								 *	pay.qq.micropay
								 */
								String tradeType =  signMap.get("trade_type");
								
								String openid = signMap.get("openid");
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
								thirdPay.setMoney(micro.getMoney());
								thirdPay.setOrderId(0L);
								thirdPay.setOrderNo(micro.getOrderNo());
								thirdPay.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss", payTime));
								thirdPay.setSort(3);
								
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
								BigDecimal calTotamt =  micro.getMoney();
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
								StoreInfo store = storeService.queryStoreInfoBySeller(micro.getSellerId());
								HashMap<String, BigDecimal>  rateMap = fqStoreRateService.calculateOrderRate(micro.getMoney(), store, type);
								if(rateMap!=null&&rateMap.size()>0){
									rate = rateMap.get("smallRate");	 //费率
									rateMoney = rateMap.get("rateMoney");	 //手续费
									calTotamt = rateMap.get("calTotamt");	  //扣除手续费结算的金额 
								}
								
								thirdPay.setSellerId(micro.getSellerId());
								thirdPay.setOrderRate(rate);
								thirdPay.setTotamt(calTotamt);
								thirdPay.setRateFee(rateMoney);
								thirdPay.setThirdOrderNo(weixinOrderNo);
								thirdPay.setOpenId(openid);
								c += fqThirdPayMapper.insertSelective(thirdPay);
								micro.setState("SUCCESS");
								micro.setStatus(2);
								micro.setCxNum(0);
								
								
								SellerInfo seller = sellerInfoMapper.selectByPrimaryKey(micro.getSellerId());
								if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
									Date date0 = BaseUtil.getTimeDate(thirdPay.getPayTime());
									FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
									fqSellerStatementExample.createCriteria().andSellerIdEqualTo(micro.getSellerId()).andStatementDateEqualTo(date0).andStateEqualTo(0);
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
										fsstate.setSellerId(micro.getSellerId());
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
									example3.createCriteria().andSellerIdEqualTo(micro.getSellerId()).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
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
								if(tradeType.contains("weixin")){
									pushSet.add(4);
								}
								
								PushPayInfoTask task = new PushPayInfoTask();
								task.setSellerId(thirdPay.getSellerId());
								task.setPushTypeArr(pushSet);
								task.setMoney(thirdPay.getMoney());
								task.setTradeType(tradeType);
								task.setTransactionId(micro.getOrderNo());
								task.setOpenId(thirdPay.getOpenId());
								task.setPayTime(thirdPay.getPayTime());
								taskExecutor.execute(task);
								
								
								fqmicroOrderMapper.updateByPrimaryKeySelective(micro);
							}else if (trade_state.equals("USERPAYING")) {
								micro.setCxNum(micro.getCxNum()-1);
								fqmicroOrderMapper.updateByPrimaryKeySelective(micro);
							}else{
								 micro.setCxNum(micro.getCxNum()-1);
								 fqmicroOrderMapper.updateByPrimaryKeySelective(micro);
							}
							
						}	
					}
				}else if(micro.getPayType() == 3){
					
						String MCH_ID = ConstantsConfigurer.getProperty(SystemConstant.QQ_MCH_ID);
						String API_KEY = ConstantsConfigurer.getProperty(SystemConstant.QQ_PAY_API_KEY);
						
						CQpayMchSpBase CQpayObj = new CQpayMchSpBase();
						TreeMap<String, String> requestMap = new TreeMap<>();
						requestMap.put("mch_id", MCH_ID);//商户id
						requestMap.put("api_key", API_KEY);//商户api请求秘钥
						requestMap.put("nonce_str", UUID.randomUUID().toString().toUpperCase().replace("-", "")); //随机请求字符串
						requestMap.put("out_trade_no", micro.getOrderNo());//订单号
						CQpayObj.setRequestMapAndUrl(requestMap, CQpayAPIURL.getOrderQueryUrl());
						
						TreeMap<String, String>  resultMap = CQpayObj.call();
						//通信结果
						if (!"SUCCESS".equals(resultMap.get("return_code"))) {
							logger.info(">>>>>>>>>>>>>>>>>>GD MICRO SELECT，通信异常："+resultMap.toString());
							continue;
						}
						//业务结果
						if (!"SUCCESS".equals(resultMap.get("result_code"))) {
							 logger.info(">>>>>>>>>>>>>>>>>>MICRO SELECT失败:"+resultMap.toString());
							continue;
						}
						
						logger.info("定时器补单:"+micro.getOrderNo()+">>>>>>>>>>>>>>>>>>MICRO 查询参数:"+resultMap.toString());
						
						//验证签名
						if(CQpayObj.checkSign(resultMap)){
							if ("SUCCESS".equals(resultMap.get("return_code")) && "SUCCESS".equals(resultMap.get("result_code"))) {
								//SUCCESS 支付成功  
								//REFUND 转入退款
								//REVOKED订单已撤销
								//CLOSED 订单已关闭 
								//USERPAYING 用户支付中   
								String trade_state = resultMap.get("trade_state");
								if (trade_state.equals("SUCCESS")) {
									String qqTranscatNo = resultMap.get("transaction_id") ;			
									String openid = resultMap.get("openid"); 		//用户在商户appid下的唯一标识
									FqThirdPay thirdPay = new FqThirdPay();
									String bankTypeCode = resultMap.get("bank_type");
									if (!StringUtils.isEmpty(bankTypeCode)) {
										if (!StringUtils.isEmpty(bankTypeCode)) {
											if(bankTypeCode.equals("BALANCE")){
												//QQ零钱
												thirdPay.setBankType(1);
											}else if(bankTypeCode.contains("CREDIT")){
												//信用卡
												thirdPay.setBankType(2);
											}else if(bankTypeCode.contains("DEBIT")){
												//储蓄卡
												thirdPay.setBankType(3);
											}
										}
									}
									BigDecimal calTotamt = micro.getMoney();
									BigDecimal rate = new BigDecimal(0); 
									BigDecimal rateMoney = new BigDecimal(0); 
									//查询本店费率
									Integer type = 3;
									StoreInfo store = storeService.queryStoreInfoBySeller(micro.getSellerId());
									HashMap<String, BigDecimal>  rateMap = fqStoreRateService.calculateOrderRate(micro.getMoney(),store, type);
									
									if(rateMap!=null&&rateMap.size()>0){
										rate = rateMap.get("smallRate");	 //费率
										rateMoney = rateMap.get("rateMoney");	 //手续费
										calTotamt = rateMap.get("calTotamt");	  //扣除手续费结算的金额 
									}
									
									
									thirdPay.setBankTypeCode(bankTypeCode);
									thirdPay.setMoney(micro.getMoney());
									thirdPay.setOrderId(0L);
									thirdPay.setOrderNo(micro.getOrderNo());
									thirdPay.setPayTime(new Date());
									thirdPay.setSort(3);
									thirdPay.setType(7);
									thirdPay.setSellerId(micro.getSellerId());
									thirdPay.setTotamt(calTotamt);
									thirdPay.setOrderRate(rate);
									thirdPay.setRateFee(rateMoney);
									thirdPay.setThirdOrderNo(qqTranscatNo);
									thirdPay.setOpenId(openid);
									c += fqThirdPayMapper.insertSelective(thirdPay);
									micro.setState("SUCCESS");
									micro.setStatus(2);
									micro.setCxNum(0);
									
									
									SellerInfo seller = sellerInfoMapper.selectByPrimaryKey(micro.getSellerId());
									if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
										Date date0 = BaseUtil.getTimeDate(thirdPay.getPayTime());
										FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
										fqSellerStatementExample.createCriteria().andSellerIdEqualTo(micro.getSellerId()).andStatementDateEqualTo(date0).andStateEqualTo(0);
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
											fsstate.setSellerId(micro.getSellerId());
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
										example3.createCriteria().andSellerIdEqualTo(micro.getSellerId()).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
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
									task.setSellerId(thirdPay.getSellerId());
									task.setPushTypeArr(pushSet);
									task.setMoney(thirdPay.getMoney());
									task.setTradeType("qq");
									task.setTransactionId(micro.getOrderNo());
									taskExecutor.execute(task);
									
									fqmicroOrderMapper.updateByPrimaryKeySelective(micro);
								}else if (trade_state.equals("USERPAYING")) {
									micro.setCxNum(micro.getCxNum()-1);
									fqmicroOrderMapper.updateByPrimaryKeySelective(micro);
								}else{
									micro.setCxNum(micro.getCxNum()-1);
									fqmicroOrderMapper.updateByPrimaryKeySelective(micro);
								}
								
							}	
						}
					
				}else if(micro.getPayType() == 5){
					String JSAPI_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
					String appId = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_APPID);
					String partner = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_PAY_PARTNER);
					String pkey = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_PAY_PKEY);
					
					String orderNo = micro.getOrderNo();
					SortedMap<String, String> cxQueryParams = new TreeMap<String, String>();
					cxQueryParams.put("appid", appId);
					cxQueryParams.put("mch_id", partner);
					cxQueryParams.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
					cxQueryParams.put("out_trade_no", orderNo);
					String sign = createSign(cxQueryParams, pkey);
					cxQueryParams.put("sign", sign);
					
					String requestXML = XMLUtil.getRequestXml(cxQueryParams);
					String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
					Map<String, String> signMap = XMLUtil.doXMLParse(result);
					if ("FAIL".equals(signMap.get("return_code"))) {
						logger.info(">>>>>>>>>>>>>>>>>> MICRO SELECT，通信异常："+signMap.toString());
						micro.setCxNum(micro.getCxNum()-1);
						fqmicroOrderMapper.updateByPrimaryKeySelective(micro);
						continue;
					}
					logger.info("定时器补单:"+orderNo+">>>>>>>>>>>>>>>>>>MICRO 查询参数:"+signMap.toString());
					if(isWxSign(signMap)){
						if ("SUCCESS".equals(signMap.get("return_code"))&& "SUCCESS".equals(signMap.get("result_code"))) {
							if (("SUCCESS").equals(signMap.get("trade_state"))) {
									// 交易成功
								logger.info("当前订单" + orderNo + "交易成功");
								
								String weixinOrderNo =signMap.get("transaction_id") ;
								String payTime = signMap.get("time_end");	
								/**
								 *  pay.weixin.micropay
								 *	pay.alipay.micropay
								 *	pay.jdpay.micropay
								 *	pay.qq.micropay
								 */
//								String tradeType =  signMap.get("trade_type");
								
								String openid = signMap.get("openid");
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
								thirdPay.setMoney(micro.getMoney());
								thirdPay.setOrderId(0L);
								thirdPay.setOrderNo(micro.getOrderNo());
								thirdPay.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss", payTime));
								thirdPay.setSort(3);
								thirdPay.setType(1);
								BigDecimal calTotamt =  micro.getMoney();
								BigDecimal rate = new BigDecimal(0); 
								BigDecimal rateMoney = new BigDecimal(0); 
								//查询本店费率
								Integer type = 1;
//								if(tradeType.contains("weixin")){
//									 type = 1;
//								}
//								if(tradeType.contains("alipay")){
//									 type = 2;
//								}
								StoreInfo store = storeService.queryStoreInfoBySeller(micro.getSellerId());
								HashMap<String, BigDecimal>  rateMap = fqStoreRateService.calculateOrderRate(micro.getMoney(), store, type);
								if(rateMap!=null&&rateMap.size()>0){
									rate = rateMap.get("smallRate");	 //费率
									rateMoney = rateMap.get("rateMoney");	 //手续费
									calTotamt = rateMap.get("calTotamt");	  //扣除手续费结算的金额 
								}
								
								thirdPay.setSellerId(micro.getSellerId());
								thirdPay.setOrderRate(rate);
								thirdPay.setTotamt(calTotamt);
								thirdPay.setRateFee(rateMoney);
								thirdPay.setThirdOrderNo(weixinOrderNo);
								thirdPay.setOpenId(openid);
								c += fqThirdPayMapper.insertSelective(thirdPay);
								micro.setState("SUCCESS");
								micro.setStatus(2);
								micro.setCxNum(0);
								
								
								SellerInfo seller = sellerInfoMapper.selectByPrimaryKey(micro.getSellerId());
								if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
									Date date0 = BaseUtil.getTimeDate(thirdPay.getPayTime());
									FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
									fqSellerStatementExample.createCriteria().andSellerIdEqualTo(micro.getSellerId()).andStatementDateEqualTo(date0).andStateEqualTo(0);
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
										fsstate.setSellerId(micro.getSellerId());
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
									example3.createCriteria().andSellerIdEqualTo(micro.getSellerId()).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
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
								pushSet.add(4);
								
								PushPayInfoTask task = new PushPayInfoTask();
								task.setSellerId(thirdPay.getSellerId());
								task.setPushTypeArr(pushSet);
								task.setMoney(thirdPay.getMoney());
								task.setTradeType("weixin");
								task.setTransactionId(micro.getOrderNo());
								task.setOpenId(thirdPay.getOpenId());
								task.setPayTime(thirdPay.getPayTime());
								taskExecutor.execute(task);
								
								fqmicroOrderMapper.updateByPrimaryKeySelective(micro);
								
							} else {
								micro.setCxNum(micro.getCxNum()-1);
								fqmicroOrderMapper.updateByPrimaryKeySelective(micro);
							}
						}else{
							micro.setCxNum(micro.getCxNum()-1);
							fqmicroOrderMapper.updateByPrimaryKeySelective(micro);
						}
					}
				}else if(micro.getPayType() == 6){
					String orderNo = micro.getOrderNo();
					AlipayTradeQueryRequestBuilder builder = new AlipayTradeQueryRequestBuilder()
	        		.setOutTradeNo(orderNo);
				AlipayF2FQueryResult result = AlipayService.tradeService.queryTradeResult(builder);
				switch (result.getTradeStatus()) {
					case SUCCESS:
						logger.info("查询返回该订单支付成功: )");
	
						AlipayTradeQueryResponse resp = result.getResponse();
	
						BigDecimal calTotamt =  micro.getMoney();
						BigDecimal rate = new BigDecimal(0); 
						BigDecimal rateMoney = new BigDecimal(0); 
						//查询本店费率
						Integer type = 2;
						StoreInfo store = storeService.queryStoreInfoBySeller(micro.getSellerId());
						HashMap<String, BigDecimal>  rateMap = fqStoreRateService.calculateOrderRate(calTotamt, store, type);
						
						if(rateMap!=null&&rateMap.size()>0){
							rate = rateMap.get("smallRate");	 //费率
							rateMoney = rateMap.get("rateMoney");	 //手续费
							calTotamt = rateMap.get("calTotamt");	  //扣除手续费结算的金额 
						}
						FqThirdPay thirdPay = new FqThirdPay();
						
						thirdPay.setMoney(micro.getMoney());
						thirdPay.setOrderId(0L);
						thirdPay.setOrderNo(micro.getOrderNo());
						thirdPay.setPayTime(resp.getSendPayDate());
						thirdPay.setSort(3);
						thirdPay.setType(2);
						
						thirdPay.setSellerId(micro.getSellerId());
						thirdPay.setOrderRate(rate);
						thirdPay.setTotamt(calTotamt);
						thirdPay.setRateFee(rateMoney);
						thirdPay.setThirdOrderNo(result.getResponse().getTradeNo());
						
						c+=fqThirdPayMapper.insertSelective(thirdPay);
						
						micro.setStatus(2);
						micro.setState("SUCCESS");
						micro.setCxNum(0);
						fqmicroOrderMapper.updateByPrimaryKeySelective(micro);
						
						SellerInfo seller = sellerInfoMapper.selectByPrimaryKey(micro.getSellerId());
						if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
							Date date0 = BaseUtil.getTimeDate(thirdPay.getPayTime());
							FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
							fqSellerStatementExample.createCriteria().andSellerIdEqualTo(micro.getSellerId()).andStatementDateEqualTo(date0).andStateEqualTo(0);
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
								fsstate.setSellerId(micro.getSellerId());
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
							example3.createCriteria().andSellerIdEqualTo(micro.getSellerId()).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
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
						task.setSellerId(micro.getSellerId());
						task.setPushTypeArr(pushSet);
						task.setMoney(thirdPay.getMoney());
						task.setTradeType("alipay");
						task.setTransactionId(orderNo);
						taskExecutor.execute(task);
						break;
	
					case FAILED:
						logger.error("查询返回该订单支付失败!!!");
						micro.setCxNum(micro.getCxNum()-1);
						fqmicroOrderMapper.updateByPrimaryKeySelective(micro);
						break;
	
					case UNKNOWN:
						logger.error("系统异常，订单支付状态未知!!!");
						micro.setCxNum(micro.getCxNum()-1);
						fqmicroOrderMapper.updateByPrimaryKeySelective(micro);
						break;
	
					default:
						logger.error("不支持的交易状态，交易返回异常!!!");
						micro.setCxNum(micro.getCxNum()-1);
						fqmicroOrderMapper.updateByPrimaryKeySelective(micro);
						break;
				}
				}else if(micro.getPayType() == 7){
					 String merNo = ConstantsConfigurer.getProperty(SystemConstant.SZ_PF_MCH_ID);
					
					 SortedMap<String, String> chaxunParams = new TreeMap<String, String>();
					 chaxunParams.put("transId", "04");
					 chaxunParams.put("merNo",merNo);
					 chaxunParams.put("orderDate",DateUtil.getDateTime("yyyyMMdd",micro.getCreateTime()));
					 chaxunParams.put("orderNo", micro.getOrderNo());
					 Map<String, String> chaxunParamsMap =  SzPfPost.postPay(chaxunParams);
					 logger.info("select 1 -----"+chaxunParamsMap.toString());
					 if ("0000".equals(chaxunParamsMap.get("respCode")) && "0000".equals(chaxunParamsMap.get("origRespCode"))) {
						 StoreInfo store = storeService.queryStoreInfoBySeller(micro.getSellerId());
						 doHandlePaySuccessSzPfWx(micro.getSellerId(), store, micro, chaxunParamsMap);
						 micro.setCxNum(0);
						 micro.setStatus(2);
						 micro.setState("SUCCESS");
						 c += fqmicroOrderMapper.updateByPrimaryKeySelective(micro);
					}else{
						micro.setCxNum(micro.getCxNum()-1);
						fqmicroOrderMapper.updateByPrimaryKeySelective(micro);
					}
					
				}
						//民生银行扫码支付补单
						// *  0106-微信刷卡（反扫）
						// *	0110-支付宝刷卡支付
						// *	0120-QQ刷卡（反扫）
				else if (micro.getPayType() == 106 || micro.getPayType() == 110 
						|| micro.getPayType() == 120){
					Map<String, String> resutl = queryPayStatus(micro);
					logger.info(">>>>>>>>>>>>>>>>>> MICRO SELECT MS，resutl："+resutl.toString());
					if(SystemConstant.SUCCESS.equals(resutl.get("respCode"))
						&& SystemConstant.SUCCESS.equals(resutl.get("origRespCode"))){
							
							doHandleMsPaySuccess(micro,resutl);
					}else{
						
						micro.setCxNum(micro.getCxNum()-1);
						fqmicroOrderMapper.updateByPrimaryKeySelective(micro);
					}
					
				}else if (micro.getPayType() == 11 || micro.getPayType() == 12) {
					String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
					String MCH_ID = ConstantsConfigurer.getProperty("xy_eqpay_mch_id");
					String PAY_KEY = ConstantsConfigurer.getProperty("xy_eqpay_key");
					String orderNo = micro.getOrderNo();
					String bank="XY";
					
					SortedMap<String, String> packageParams = new TreeMap<String, String>();
					packageParams.put("service", "unified.trade.query"); 
			        packageParams.put("mch_id", MCH_ID); 		
			        packageParams.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
			        packageParams.put("out_trade_no",orderNo); 	
			        String sign = createSignGd(packageParams, PAY_KEY);
					packageParams.put("sign", sign);
					String requestXML = XMLUtil.getRequestXml(packageParams);
				    String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
				    Map<String, String> signMap = new HashMap<String, String>();
					signMap = XMLUtil.doXMLParse(result);
					if (!"0".equals(signMap.get("status"))) {
						logger.info(">>>>>>>>>>>>>>>>>> MICRO SELECT XYEQ，通信异常："+signMap.toString());
						micro.setCxNum(micro.getCxNum()-1);
						fqmicroOrderMapper.updateByPrimaryKeySelective(micro);
						continue;
					}
					if (!"0".equals(signMap.get("result_code"))) {
						logger.info(">>>>>>>>>>>>>>>>>>MICRO SELECT XYEQ 失败:"+signMap.toString());
						micro.setCxNum(micro.getCxNum()-1);
						fqmicroOrderMapper.updateByPrimaryKeySelective(micro);
						continue;
					}
					
					logger.info("定时器补单:"+orderNo+">>>>>>>>>>>>>>>>>>MICRO XYEQ 查询参数:"+signMap.toString());
					
					if(isSign(signMap,bank)){
						if ("0".equals(signMap.get("status")) && "0".equals(signMap.get("result_code"))) {
							String trade_state = signMap.get("trade_state");
							if (trade_state.equals("SUCCESS")) {
								String weixinOrderNo =signMap.get("transaction_id") ;
								String payTime = signMap.get("time_end");	
								/**
								 *  pay.weixin.micropay
								 *	pay.alipay.micropay
								 *	pay.jdpay.micropay
								 *	pay.qq.micropay
								 */
								String tradeType =  signMap.get("trade_type");
								
								String openid = signMap.get("openid");
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
								thirdPay.setMoney(micro.getMoney());
								thirdPay.setOrderId(0L);
								thirdPay.setOrderNo(micro.getOrderNo());
								thirdPay.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss", payTime));
								thirdPay.setSort(3);
								
								
								if(tradeType.contains("weixin")){
										thirdPay.setType(5);
							    }
									if(tradeType.contains("alipay")){
										thirdPay.setType(6);
									}
								BigDecimal calTotamt =  micro.getMoney();
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
								StoreInfo store = storeService.queryStoreInfoBySeller(micro.getSellerId());
								HashMap<String, BigDecimal>  rateMap = fqStoreRateService.calculateOrderRate(micro.getMoney(), store, type);
								if(rateMap!=null&&rateMap.size()>0){
									rate = rateMap.get("smallRate");	 //费率
									rateMoney = rateMap.get("rateMoney");	 //手续费
									calTotamt = rateMap.get("calTotamt");	  //扣除手续费结算的金额 
								}
								
								thirdPay.setSellerId(micro.getSellerId());
								thirdPay.setOrderRate(rate);
								thirdPay.setTotamt(calTotamt);
								thirdPay.setRateFee(rateMoney);
								thirdPay.setThirdOrderNo(weixinOrderNo);
								thirdPay.setOpenId(openid);
								c += fqThirdPayMapper.insertSelective(thirdPay);
								micro.setState("SUCCESS");
								micro.setStatus(2);
								micro.setCxNum(0);
								
								
								SellerInfo seller = sellerInfoMapper.selectByPrimaryKey(micro.getSellerId());
								if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
									Date date0 = BaseUtil.getTimeDate(thirdPay.getPayTime());
									FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
									fqSellerStatementExample.createCriteria().andSellerIdEqualTo(micro.getSellerId()).andStatementDateEqualTo(date0).andStateEqualTo(0);
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
										fsstate.setSellerId(micro.getSellerId());
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
									example3.createCriteria().andSellerIdEqualTo(micro.getSellerId()).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
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
								if(tradeType.contains("weixin")){
									pushSet.add(4);
								}
								
								PushPayInfoTask task = new PushPayInfoTask();
								task.setSellerId(thirdPay.getSellerId());
								task.setPushTypeArr(pushSet);
								task.setMoney(thirdPay.getMoney());
								task.setTradeType(tradeType);
								task.setTransactionId(micro.getOrderNo());
								task.setOpenId(thirdPay.getOpenId());
								task.setPayTime(thirdPay.getPayTime());
								taskExecutor.execute(task);
								
								
								fqmicroOrderMapper.updateByPrimaryKeySelective(micro);
							}else if (trade_state.equals("USERPAYING")) {
								micro.setCxNum(micro.getCxNum()-1);
								fqmicroOrderMapper.updateByPrimaryKeySelective(micro);
							}else{
								 micro.setCxNum(micro.getCxNum()-1);
								 fqmicroOrderMapper.updateByPrimaryKeySelective(micro);
							}
							
						}	
					}
				}
		}
	}
		return c;
		}
	
	private FqThirdPay doHandleMsPaySuccess(FqMicroOrder micro, Map<String, String> signMap){
		try{
		
		logger.info(">>>>>>>>>>>>>>>>>>进入民生银行统一支付 MS MICRO 扫码支付 定时补单 start"+micro.getId());
		Long  sellerId = micro.getSellerId();
		 StoreInfo store = storeService.queryStoreInfoBySeller(micro.getSellerId());
		String weixinOrderNo =signMap.get("payId");		// 第三方订单号 	
		String openid = signMap.get("chnlUserId");	//民生子商户ID
		String payTime = signMap.get("payTime");
		/**
		 *  0106-微信刷卡（反扫）
		 *	0110-支付宝刷卡支付
		 *	0120-QQ刷卡（反扫）
		 */
		String tradeType =  "0"+micro.getPayType();
		
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
		thirdPay.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss",payTime));
		thirdPay.setSort(99);
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
			
			Date statementDay = thirdPay.getPayTime();
			if(is11hBetween12h(thirdPay.getPayTime()))
			{
				statementDay = addOneDay(statementDay);
			}
			Date date0 = BaseUtil.getTimeDate(statementDay);
		
			logger.info("now  date0:"+date0);

			FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
			fqSellerStatementExample.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatementDateEqualTo(date0).andStateBetween(2, 4);
			List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
			FqSellerStatement fsstate = null;
			if (fsslist.size()>0) {
				fsstate = fsslist.get(0);
				fsstate.setTotalMoney(fsstate.getTotalMoney().add(calTotamt));
				fsstate.setTotalNum(fsstate.getTotalNum()+1);
				fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
			}else{
				
				fsstate = new FqSellerStatement();
				fsstate.setCreateTime(date0);
				fsstate.setStatementDate(date0);
				fsstate.setSellerId(sellerId);
				fsstate.setState(2);		//   2..为民生通道  	
				fsstate.setTotalMoney(calTotamt);
				fsstate.setTotalNum(1);
				fsstate.setPeriodDate(addOneDay(date0));
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
		fqMicroOrder.setCxNum(0);
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
		task.setPayTime(new Date());
		taskExecutor.execute(task);
		
		
		return thirdPay;
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * 民生支付状态查询
	 */
	private  Map<String, String> queryPayStatus(FqMicroOrder micro){
		logger.info(">>>>>>>>>>>>>>>>>>进入民生银行统一支付状态查询接口：START，orderNo："+micro.getOrderNo());
		List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
        nvps.add(new BasicNameValuePair("requestNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())));
        nvps.add(new BasicNameValuePair("version", "V2.0"));
        nvps.add(new BasicNameValuePair("transId", "04"));
        nvps.add(new BasicNameValuePair("merNo", ConstantsConfigurer.getProperty(SystemConstant.MS_MCH_ID)));
        nvps.add(new BasicNameValuePair("orderDate", new SimpleDateFormat("yyyyMMdd").format(micro.getCreateTime())));
        nvps.add(new BasicNameValuePair("orderNo", micro.getOrderNo()));
		
        Map<String, String> notifyParamsMap = BaseRequest.getSignToSend(nvps);
        
        if(notifyParamsMap == null ){
        	logger.info(">>>>>>>>>>>>>>>>>>进入民生银行统一支付状态查询接口：执行异常 map is null，orderNo："+micro.getOrderNo());
			return null;
        }
		if(MsConstant.DOING_SUCCEE.equals(notifyParamsMap.get("respCode")) 
        		|| SystemConstant.SUCCESS.equals(notifyParamsMap.get("respCode")))
		{
			logger.info(">>>>>>>>>>>>>>>>>>进入民生银行统一支付状态查询接口："+notifyParamsMap.get("origRespDesc")+"，orderNo："+micro.getOrderNo());
			if(SystemConstant.SUCCESS.equals(notifyParamsMap.get("origRespCode")))
			{
				logger.info(">>>>>>>>>>>>>>>>>>进入民生银行统一支付状态查询接口：START，RETURN：SUCCESS");
				return notifyParamsMap;
			}else if(MsConstant.DOING_SUCCEE.equals(notifyParamsMap.get("origRespCode")))
			{
				logger.info(">>>>>>>>>>>>>>>>>>进入民生银行统一支付状态查询接口：START，RETURN：USERPAYING");
				return notifyParamsMap;
			}
		}
		logger.info(">>>>>>>>>>>>>>>>>>进入民生银行统一支付状态查询接口：START，RETURN：FAIL");
		return notifyParamsMap;
	}

	@Override
	public int reverseMicroOrder(String orderNo) throws JDOMException, IOException {
		String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
		String MCH_ID = ConstantsConfigurer.getProperty(SystemConstant.GD_MCH_ID);
		String PAY_KEY = ConstantsConfigurer.getProperty(SystemConstant.GD_PAY_KEY);
		
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("service", "unified.micropay.reverse"); 
        packageParams.put("mch_id", MCH_ID); 		
        packageParams.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));  //商家号
        packageParams.put("out_trade_no",orderNo); //商品描述   	
        String sign = createSignGd(packageParams, PAY_KEY);
		packageParams.put("sign", sign);
		String requestXML = XMLUtil.getRequestXml(packageParams);
	    String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
	    System.out.println(result);
	    Map<String, String> signMap = new HashMap<String, String>();
		signMap = XMLUtil.doXMLParse(result);
		return 0;
	}

	@Override
	public FqThirdPay insertPayOrderByQqPay(Long sellerId, BigDecimal money,
			String authCode, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			FqThirdPay thirdPay = new FqThirdPay();
			String orderNo = OrderUtil.getOrderNo(SystemConstant.QQ_MICOR_PAY_TYPE);
			FqMicroOrder microOrder = new FqMicroOrder();
			microOrder.setCreateTime(new Date());
			microOrder.setCxNum(0);
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
					microOrder.setState("ERROR");
					fqmicroOrderMapper.updateByPrimaryKey(microOrder);
					 return null;
				}
				if (!"SUCCESS".equals(signMap.get("result_code"))) {
					if ("USERPAYING".equals(signMap.get("err_code"))) {
						thirdPay.setType(-1);
						microOrder.setStatus(1);
						microOrder.setCxNum(3);
						microOrder.setState("USERPAYING");
						fqmicroOrderMapper.updateByPrimaryKey(microOrder);
						return thirdPay;
					}
					logger.info(">>>>>>>>>>>>>>>>>>QQ MICRO支付回调失败:"+signMap.toString());
					
					 return null;
				}
				
					if(isQqSign(signMap)){
						if ("SUCCESS".equals(signMap.get("return_code")) && "SUCCESS".equals(signMap.get("result_code")) && "SUCCESS".equals(signMap.get("trade_state"))) {
							String weixinOrderNo =signMap.get("transaction_id") ;			
							String openid = signMap.get("openid");
							
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
							microOrder.setStatus(2);
							fqmicroOrderMapper.updateByPrimaryKey(microOrder);
							
							WeixinUserinfoExample example2 = new WeixinUserinfoExample();
							example2.createCriteria().andStoreIdEqualTo(store.getId());
							
							//推送信息
							HashSet<Integer> pushSet = new HashSet<Integer>();
							pushSet.add(2);
							pushSet.add(3);
							PushPayInfoTask task = new PushPayInfoTask();
							task.setSellerId(store.getSellerId());
							task.setPushTypeArr(pushSet);
							task.setMoney(thirdPay.getMoney());
							task.setTradeType("qq");
							task.setTransactionId(orderNo);
							taskExecutor.execute(task);
							
							
							return thirdPay;
						}
					}else{
						logger.info(">>>>>>>>>>>>>>>>>>GD MICRO支付回调失败:签名验证失败");
						microOrder.setStatus(1);
						microOrder.setCxNum(6);
						microOrder.setState("ERROR");
						fqmicroOrderMapper.updateByPrimaryKey(microOrder);
					}
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
public static boolean isQqSign(Map<String,String> paramsMap) throws Exception{
		
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
	
	public String createSignQq(SortedMap<String, String> packageParams,String key) {
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
	 * ==================================================================================================================
	 *  旺pos start
	 * ==================================================================================================================
	 */
	@Override
	/**
	 * 新增旺pos订单
	 */
	public String insertPosOrder(Long sellerId, BigDecimal money) {
		String orderNo = OrderUtil.getOrderNo(SystemConstant.W_POS_MICOR_PAY_TYPE);
		FqMicroOrder microOrder = new FqMicroOrder();
		microOrder.setCreateTime(new Date());
		microOrder.setCxNum(0);
		microOrder.setMoney(money);
		microOrder.setOrderNo(orderNo);
		microOrder.setSellerId(sellerId);
		microOrder.setStatus(0);
		microOrder.setPayType(4); //旺pos
		int num = fqmicroOrderMapper.insertSelective(microOrder);
		if(num>0){
			return orderNo;
		}else{
			return null;
		}
	}
	
	
	
	
	/**
	 * 验证旺pos 签名
	 * @param paramsMap
	 * @return
	 * @throws Exception
	 */
	public  boolean isWangPosSign(Map<String,String> paramsMap) throws Exception{
		boolean flag = false;
		String pkey = ConstantsConfigurer.getProperty(SystemConstant.WANG_POS_API_KEY);
		//String pkey = "";
		RequestHandler reqHandler = new RequestHandler(null, null);
		//初始化 
        reqHandler.init(null, null, null,null, pkey);
        
        //转换成sortedMap 
        SortedMap<String, String> signMap = new TreeMap<String, String>();
        for(Map.Entry<String, String> entry : paramsMap.entrySet()) {
        	if(!"sign_type".equals(entry.getKey())){  			//返回的结果map中含有签名方式 sign_type=MD5 去除，不然导致签名失败
        		signMap.put(entry.getKey(), entry.getValue());
        	}
        }
        
		String signValid = reqHandler.createSign(signMap);
		String sign = paramsMap.get("sign").toUpperCase(); //坑，我们的签名为字母大写，必须对返回的签名转换为大写
		if(signValid.equals(sign)){
			flag = true;
		}
		return flag;
	}
	
	
	

	/**
	 * 旺pos回调
	 * {pay_fee=1, sign_type=MD5, out_trade_no=OS20170405161227001606, bp_id=58dcc3eceb670024651d66f2, thirdSerialNo=571265161257, 
	 * batchNo=170405, pay_type=1006, total_fee=1, buyer=6217995840021688643, time_end=20170405161258, thirdMDiscount=0, 
	 * mcode=193898, time_create=20170405161245, operator_name=匿名用户, terminalNo=30369741, input_charset=UTF-8, pay_info=支付成功,
	 *  cardType=借记卡, trade_status=PAY, vouchNo=096957, thirdDiscount=0, sign=371951852ec72168302d42d94fb1f6fa,
	 *   cashier_trade_no=10001938982017040500000013, device_en=7c3cf418, nonce=ztEICVLxosnyeSmH, attach=备注信息, check_fee=0}
	 * 
	 */
	@Override
	public Integer updateCheckNotifyWangPos(Map<String, String> notifyParamsMap) throws Exception {
			logger.info(">>>>>>>>>>>>>>>>>>旺 POS支付回调 参数" + notifyParamsMap.toString());
			if(isWangPosSign(notifyParamsMap)){
				/**
				 *  WAIT_PAY 	待支付：交易等待支付
					PAYING 	支付中：交易正在进行，等待输入密码等操作
					PAY 	已支付：支付完成
					REFUND 	已退款：交易已退款
					CLOSED 	已关闭：订单超时或者支付失败
				 */
				if ("PAY".equals(notifyParamsMap.get("trade_status"))&&notifyParamsMap.get("pay_type").trim().equals("1006")) { //只处理银行卡的
					//pay_type
					//1001 	现金
					//1003 	微信支付
					//1004 	支付宝
					//1005 	百度钱包
					//1006 	银行卡
					//1007 	易付宝
					//1008 	点评闪惠
					//1009 	京东钱包
					String orderNo = notifyParamsMap.get("out_trade_no") ;	 ///商户订单号		
					String weixinOrderNo = notifyParamsMap.get("cashier_trade_no") ;	 ///收银订单号		
					String payType = notifyParamsMap.get("pay_type");
					String payFee = notifyParamsMap.get("pay_fee"); //订单支付金额 分
					String totalFee = notifyParamsMap.get("total_fee"); //订单金额 分
					String checkFee = notifyParamsMap.get("check_fee"); //优惠支付金额
					String buyer = notifyParamsMap.get("buyer"); //付款用户信息，可空
					String cardType = notifyParamsMap.get("cardType"); //银行卡类型
					String timeEnd = notifyParamsMap.get("time_end"); //支付时间
					Date payTime = DateUtil.convertStringToDate("yyyyMMddHHmmss", timeEnd);
					
					FqMicroOrderExample example = new FqMicroOrderExample();
					example.createCriteria().andOrderNoEqualTo(orderNo);
					List<FqMicroOrder> recordlist  = fqmicroOrderMapper.selectByExample(example);

					if(recordlist == null || recordlist.size() == 0){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+orderNo+"不存在");
						return 0;
					}
					FqMicroOrder record = recordlist.get(0);
					
					BigDecimal money = BigDecimal.valueOf(Long.valueOf(totalFee)).divide(new BigDecimal(100)); //支付结果中的金额单位为分;
					if(money.doubleValue()!=record.getMoney().doubleValue()){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+orderNo+"的金额与实际支付金额不一致");
						return 0;
					}
					if(SystemConstant.PAY_ISPAY.equals(record.getStatus())){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+orderNo+"己支付");
						return 1;
					}else{
							FqThirdPay thirdPay = new FqThirdPay();
							Integer bankType = null;
							if (!StringUtils.isEmpty(cardType)) {
								if (cardType.contains("借记")) {
									bankType = 3;
								}
								if (cardType.contains("信用")) {
									bankType = 2;
								}
							}
							thirdPay.setBankType(bankType);
							thirdPay.setMoney(money);
							thirdPay.setOrderId(0L);
							thirdPay.setOrderNo(orderNo);
							thirdPay.setPayTime(payTime);
							thirdPay.setSort(3);
							thirdPay.setType(4);//旺pos
							thirdPay.setSellerId(record.getSellerId());
							thirdPay.setTotamt(money);
							thirdPay.setThirdOrderNo(weixinOrderNo);
							thirdPay.setOpenId(buyer);
							fqThirdPayMapper.insertSelective(thirdPay);
							SellerInfo seller = sellerInfoMapper.selectByPrimaryKey(record.getSellerId());
							if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
								Date date0 = BaseUtil.getTimeDate(thirdPay.getPayTime());
								FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
								fqSellerStatementExample.createCriteria().andSellerIdEqualTo(record.getSellerId()).andStatementDateEqualTo(date0).andStateEqualTo(0);
								List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
								FqSellerStatement fsstate = null;
								if (fsslist.size()>0) {
									fsstate = fsslist.get(0);
									fsstate.setTotalMoney(fsstate.getTotalMoney().add(thirdPay.getMoney()));
									fsstate.setTotalNum(fsstate.getTotalNum()+1);
									fsstate.setWpMoney((fsstate.getWpMoney()== null ?BigDecimal.ZERO:fsstate.getWpMoney()).add(thirdPay.getMoney()));
									fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
								}else{
									fsstate = new FqSellerStatement();
									fsstate.setCreateTime(new Date());
									fsstate.setStatementDate(date0);
									fsstate.setSellerId(record.getSellerId());
									fsstate.setState(0);
									fsstate.setTotalMoney(thirdPay.getMoney());
									fsstate.setTotalNum(1);
									fsstate.setWpMoney(thirdPay.getMoney());
									StoreInfo store = storeService.queryStoreInfoBySeller(record.getSellerId());
									Calendar calendar = Calendar.getInstance();
									calendar.setTime(date0);
									calendar.add(Calendar.DATE, store.getStatementPeriod());
									fsstate.setPeriodDate(calendar.getTime());
									fqSellerStatementMapper.insertSelective(fsstate);
								}
							}else{
								FqStoreCreditExample example3 = new FqStoreCreditExample();
								Date now = new Date();
								example3.createCriteria().andSellerIdEqualTo(record.getSellerId()).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
								example3.setOrderByClause("period desc");
								List<FqStoreCredit> credits = fqStoreCreditMapper.selectByExample(example3);
								if (credits.size()>0) {
									FqStoreCredit credit = credits.get(0);
									BigDecimal noRevert = credit.getNoRevert().subtract(thirdPay.getMoney());
									if (noRevert.compareTo(BigDecimal.ZERO) == -1) {
										noRevert = BigDecimal.ZERO;
										credit.setStatus(3);
									}else{
										credit.setNoRevert(noRevert);
									}
									fqStoreCreditMapper.updateByPrimaryKeySelective(credit);
								}
							}
							record.setStatus(2);
							fqmicroOrderMapper.updateByPrimaryKey(record);
							
							//推送信息
							HashSet<Integer> pushSet = new HashSet<Integer>();
							pushSet.add(2);
							pushSet.add(3);
							PushPayInfoTask task = new PushPayInfoTask();
							task.setSellerId(record.getSellerId());
							task.setPushTypeArr(pushSet);
							task.setMoney(thirdPay.getMoney());
							task.setTradeType("pos");
							task.setTransactionId(orderNo);
							taskExecutor.execute(task);
							return 1;
					}
				}
			}else{
				logger.info(">>>>>>>>>>>>>>>>>>旺pos 支付回调失败:签名验证失败");
				
			}
			return 0;
	}
	
	/***
	 * 我们app 自己发送的 pos 机支付结果， pos的异步回调 目前只通知一次
	 * @throws Exception 
	 */
	public Integer updateCheckNotifyWangPosByApp(String data, Long sellerId) throws Exception {
		logger.info(">>>>>>>>>>>>>>>>>>app 旺 POS支付回调 参数" +data);
		//String str = "{\"errCode\":\"0\",\"errMsg\":\"支付成功\",\"out_trade_no\":\"OS2017040609514100123000\",\"trade_status\":\"PAY\",\"input_charset\":\"UTF-8\",\"cashier_trade_no\":\"10001938982017040600000001\",\"pay_type\":\"1006\",\"pay_info\":\"支付成功\",\"buy_user_info\":\"{\"buyer_user\":\"621799******8643\",\"third_serial_no\":\"092504095209\"}\"}";
		Map<String, Object> dataMap = JSONObject.parseObject(data);
		String buy_user_info = String.valueOf(dataMap.get("buy_user_info"));
		Map<String, Object> buyUserInfoMap = JSONObject.parseObject(buy_user_info);
		
		   /**
			 *  WAIT_PAY 	待支付：交易等待支付
				PAYING 	支付中：交易正在进行，等待输入密码等操作
				PAY 	已支付：支付完成
				REFUND 	已退款：交易已退款
				CLOSED 	已关闭：订单超时或者支付失败
			 */
			if ("PAY".equals(dataMap.get("trade_status"))&& "0".equals(dataMap.get("errCode")) && dataMap.get("pay_type").toString().trim().equals("1006")) { //银行卡
				//pay_type
				//1001 	现金
				//1003 	微信支付
				//1004 	支付宝
				//1005 	百度钱包
				//1006 	银行卡
				//1007 	易付宝
				//1008 	点评闪惠
				//1009 	京东钱包
				String orderNo = dataMap.get("out_trade_no").toString() ;	 ///商户订单号		
				String weixinOrderNo = dataMap.get("cashier_trade_no").toString() ;	 ///收银订单号		
				String payType = dataMap.get("pay_type").toString();
				String buyer = buyUserInfoMap.get("buyer_user").toString(); //付款用户信息，银行卡号
				Date payTime =  new Date();//支付时间
				String cardType = dataMap.get("cardType").toString();
				
				FqMicroOrderExample example = new FqMicroOrderExample();
				example.createCriteria().andOrderNoEqualTo(orderNo);
				List<FqMicroOrder> recordlist  = fqmicroOrderMapper.selectByExample(example);

				if(recordlist == null || recordlist.size() == 0){
					logger.error(">>>>>>>>>>>>>>>>>>订单"+orderNo+"不存在");
					return 0;
				}
				FqMicroOrder record = recordlist.get(0);
			
				if(SystemConstant.PAY_ISPAY.equals(record.getStatus())){
					logger.error(">>>>>>>>>>>>>>>>>>订单"+orderNo+"己支付");
					return 1;
				}else{
						
						Integer bankType = null;
						if (!StringUtils.isEmpty(cardType)) {
							if (cardType.contains("借记")) {
								bankType = 3;
							}
							if (cardType.contains("信用")) {
								bankType = 2;
							}
						}
					
						FqThirdPay thirdPay = new FqThirdPay();
						thirdPay.setMoney(record.getMoney());
						thirdPay.setOrderId(0L);
						thirdPay.setOrderNo(orderNo);
						thirdPay.setPayTime(payTime);
						thirdPay.setSort(3);
						thirdPay.setType(4);//旺pos
						thirdPay.setSellerId(record.getSellerId());
						thirdPay.setTotamt(record.getMoney());
						thirdPay.setThirdOrderNo(weixinOrderNo);
						thirdPay.setOpenId(buyer);
						thirdPay.setBankType(bankType);
						fqThirdPayMapper.insertSelective(thirdPay);
						SellerInfo seller = sellerInfoMapper.selectByPrimaryKey(record.getSellerId());
						if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
							Date date0 = BaseUtil.getTimeDate(thirdPay.getPayTime());
							FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
							fqSellerStatementExample.createCriteria().andSellerIdEqualTo(record.getSellerId()).andStatementDateEqualTo(date0).andStateEqualTo(0);
							List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
							FqSellerStatement fsstate = null;
							if (fsslist.size()>0) {
								fsstate = fsslist.get(0);
								fsstate.setTotalMoney(fsstate.getTotalMoney().add(thirdPay.getMoney()));
								fsstate.setTotalNum(fsstate.getTotalNum()+1);
								fsstate.setWpMoney((fsstate.getWpMoney()== null ?BigDecimal.ZERO:fsstate.getWpMoney()).add(thirdPay.getMoney()));
								fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
							}else{
								fsstate = new FqSellerStatement();
								fsstate.setCreateTime(new Date());
								fsstate.setStatementDate(date0);
								fsstate.setSellerId(record.getSellerId());
								fsstate.setState(0);
								fsstate.setTotalMoney(thirdPay.getMoney());
								fsstate.setTotalNum(1);
								fsstate.setWpMoney(thirdPay.getMoney());
								StoreInfo store = storeService.queryStoreInfoBySeller(record.getSellerId());
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(date0);
								calendar.add(Calendar.DATE, store.getStatementPeriod());
								fsstate.setPeriodDate(calendar.getTime());
								fqSellerStatementMapper.insertSelective(fsstate);
							}
						}else{
							FqStoreCreditExample example3 = new FqStoreCreditExample();
							Date now = new Date();
							example3.createCriteria().andSellerIdEqualTo(record.getSellerId()).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
							example3.setOrderByClause("period desc");
							List<FqStoreCredit> credits = fqStoreCreditMapper.selectByExample(example3);
							if (credits.size()>0) {
								FqStoreCredit credit = credits.get(0);
								BigDecimal noRevert = credit.getNoRevert().subtract(thirdPay.getMoney());
								if (noRevert.compareTo(BigDecimal.ZERO) == -1) {
									noRevert = BigDecimal.ZERO;
									credit.setStatus(3);
								}else{
									credit.setNoRevert(noRevert);
								}
								fqStoreCreditMapper.updateByPrimaryKeySelective(credit);
							}
						}
						record.setStatus(2);
						fqmicroOrderMapper.updateByPrimaryKey(record);
						
						//推送信息
						HashSet<Integer> pushSet = new HashSet<Integer>();
						pushSet.add(2);
						pushSet.add(3);
						PushPayInfoTask task = new PushPayInfoTask();
						task.setSellerId(record.getSellerId());
						task.setPushTypeArr(pushSet);
						task.setMoney(thirdPay.getMoney());
						task.setTradeType("pos");
						task.setTransactionId(orderNo);
						taskExecutor.execute(task);
						return 1;
				}
		}else{
			logger.info(">>>>>>>>>>>>>>>>>>app 旺pos 支付失败 "+data);
		}
		return 0;
	}
	/**
	 * ==================================================================================================================
	 *  旺pos end
	 * ==================================================================================================================
	 */

	/**
	 * 旺pos机定时处理任务
	 * @throws Exception 
	 */
	public int updateWPosOrder() throws Exception {
		//半小时内pos未支付成功的订单
		List<FqMicroOrder> list = fqmicroOrderMapper.selectWPosOrderByUnPay();
		if (list.size()>0) {
			for (int i = 0; i < list.size(); i++) {
				FqMicroOrder micro = list.get(i);
				String URL = "http://open.wangpos.com/wopengateway/api/entry";
				String WANG_POS_TOKEN= ConstantsConfigurer.getProperty(SystemConstant.WANG_POS_TOKEN);
				String orderNo  = micro.getOrderNo();
				String access_token = AccessToken.getWPosAccessToken();
				
				SortedMap<String, String> packageParams = new TreeMap<String, String>();
				packageParams.put("service", "cashier.api.query"); 
				packageParams.put("timestamp", System.currentTimeMillis()+"");
				packageParams.put("access_token", access_token);
				packageParams.put("nonce",(10000000 + new Random().nextInt(90000000))+"");
				packageParams.put("out_trade_no",orderNo); 	
				
				StringBuffer sb = new StringBuffer();
				Set es = packageParams.entrySet();
				Iterator it = es.iterator();
				while (it.hasNext()) {
					Map.Entry entry = (Map.Entry) it.next();
					String k = (String) entry.getKey();
					String v = (String) entry.getValue();
					sb.append(k + "=" + v + "&");
				}
				sb.append("token=" + WANG_POS_TOKEN);
				Sha1 sha1 = new Sha1();
				String sign	 = sha1.getDigestOfString(sb.toString().getBytes()).toUpperCase();
				packageParams.put("signature",sign);
				String result = cn.qhjys.mall.util.HttpClientUtil.sendHttpMsg2(URL, packageParams);
			
			    JSONObject resultObj = JSONObject.parseObject(result);
			    logger.debug("订单:"+orderNo+",查询结果为"+resultObj.toString());
				String status = resultObj.get("status").toString(); //查询成功
				//结果未做签名 无需验证
			    if(status.equals("0")){
			    	 resultObj =  resultObj.getJSONObject("data");
			    	 /**
					 *  WAIT_PAY 	待支付：交易等待支付
						PAYING 	支付中：交易正在进行，等待输入密码等操作
						PAY 	已支付：支付完成
						REFUND 	已退款：交易已退款
						CLOSED 	已关闭：订单超时或者支付失败
					 */
					if ("PAY".equals(resultObj.get("trade_status"))&&resultObj.get("pay_fee").toString().equals("1006")) { //只有银行卡
						//pay_type
						//1001 	现金
						//1003 	微信支付
						//1004 	支付宝
						//1005 	百度钱包
						//1006 	银行卡
						//1007 	易付宝
						//1008 	点评闪惠
						//1009 	京东钱包
						String weixinOrderNo = resultObj.get("cashier_trade_no").toString() ;	 ///收银订单号		
						String payType = resultObj.get("pay_type").toString();
						String payFee = resultObj.get("pay_fee").toString(); //订单支付金额 分
						String totalFee = resultObj.get("total_fee").toString(); //订单金额 分
						String checkFee = resultObj.get("check_fee").toString(); //优惠支付金额
						String buyer = resultObj.get("buyer").toString(); //付款用户信息，可空
						String cardType = resultObj.get("cardType").toString(); //银行卡类型
						String timeEnd = resultObj.get("time_end").toString(); //支付时间
						Date payTime = DateUtil.convertStringToDate("yyyyMMddHHmmss", timeEnd);
						
						FqMicroOrderExample example = new FqMicroOrderExample();
						example.createCriteria().andOrderNoEqualTo(orderNo);
						List<FqMicroOrder> recordlist  = fqmicroOrderMapper.selectByExample(example);

						if(recordlist == null || recordlist.size() == 0){
							logger.error(">>>>>>>>>>>>>>>>>>订单"+orderNo+"不存在");
							continue; //继续下次循环
						}
						FqMicroOrder record = recordlist.get(0);
						
						BigDecimal money = BigDecimal.valueOf(Long.valueOf(totalFee)).divide(new BigDecimal(100)); //支付结果中的金额单位为分;
						if(money.doubleValue()!=record.getMoney().doubleValue()){
							logger.error(">>>>>>>>>>>>>>>>>>订单"+orderNo+"的金额与实际支付金额不一致");
							continue; //继续下次循环
						}
						if(SystemConstant.PAY_ISPAY.equals(record.getStatus())){
							logger.error(">>>>>>>>>>>>>>>>>>订单"+orderNo+"己支付");
							continue; //继续下次循环
						}else{
								FqThirdPay thirdPay = new FqThirdPay();
								Integer bankType = null;
								if (!StringUtils.isEmpty(cardType)) {
									if (cardType.contains("借记")) {
										bankType = 3;
									}
									if (cardType.contains("信用")) {
										bankType = 2;
									}
								}
								thirdPay.setBankType(bankType);
								thirdPay.setMoney(money);
								thirdPay.setOrderId(0L);
								thirdPay.setOrderNo(orderNo);
								thirdPay.setPayTime(payTime);
								thirdPay.setSort(3);
								thirdPay.setType(4);//旺pos
								thirdPay.setSellerId(record.getSellerId());
								thirdPay.setTotamt(money);
								thirdPay.setThirdOrderNo(weixinOrderNo);
								thirdPay.setOpenId(buyer);
								fqThirdPayMapper.insertSelective(thirdPay);
								SellerInfo seller = sellerInfoMapper.selectByPrimaryKey(record.getSellerId());
								if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
									Date date0 = BaseUtil.getTimeDate(thirdPay.getPayTime());
									FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
									fqSellerStatementExample.createCriteria().andSellerIdEqualTo(record.getSellerId()).andStatementDateEqualTo(date0).andStateEqualTo(0);
									List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
									FqSellerStatement fsstate = null;
									if (fsslist.size()>0) {
										fsstate = fsslist.get(0);
										fsstate.setTotalMoney(fsstate.getTotalMoney().add(thirdPay.getMoney()));
										fsstate.setTotalNum(fsstate.getTotalNum()+1);
										fsstate.setWpMoney((fsstate.getWpMoney()== null ?BigDecimal.ZERO:fsstate.getWpMoney()).add(thirdPay.getMoney()));
										fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
									}else{
										fsstate = new FqSellerStatement();
										fsstate.setCreateTime(new Date());
										fsstate.setStatementDate(date0);
										fsstate.setSellerId(record.getSellerId());
										fsstate.setState(0);
										fsstate.setTotalMoney(thirdPay.getMoney());
										fsstate.setTotalNum(1);
										fsstate.setWpMoney(thirdPay.getMoney());
										StoreInfo store = storeService.queryStoreInfoBySeller(record.getSellerId());
										Calendar calendar = Calendar.getInstance();
										calendar.setTime(date0);
										calendar.add(Calendar.DATE, store.getStatementPeriod());
										fsstate.setPeriodDate(calendar.getTime());
										fqSellerStatementMapper.insertSelective(fsstate);
									}
								}else{
									FqStoreCreditExample example3 = new FqStoreCreditExample();
									Date now = new Date();
									example3.createCriteria().andSellerIdEqualTo(record.getSellerId()).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
									example3.setOrderByClause("period desc");
									List<FqStoreCredit> credits = fqStoreCreditMapper.selectByExample(example3);
									if (credits.size()>0) {
										FqStoreCredit credit = credits.get(0);
										BigDecimal noRevert = credit.getNoRevert().subtract(thirdPay.getMoney());
										if (noRevert.compareTo(BigDecimal.ZERO) == -1) {
											noRevert = BigDecimal.ZERO;
											credit.setStatus(3);
										}else{
											credit.setNoRevert(noRevert);
										}
										fqStoreCreditMapper.updateByPrimaryKeySelective(credit);
									}
								}
								record.setStatus(2);
								fqmicroOrderMapper.updateByPrimaryKey(record);
								
								//推送信息
								HashSet<Integer> pushSet = new HashSet<Integer>();
								pushSet.add(2);
								pushSet.add(3);
								PushPayInfoTask task = new PushPayInfoTask();
								task.setSellerId(record.getSellerId());
								task.setPushTypeArr(pushSet);
								task.setMoney(thirdPay.getMoney());
								task.setTradeType("pos");
								task.setTransactionId(orderNo);
								taskExecutor.execute(task);
						}
						
					}
			    	
			    }
			}
		}
		return list.size();
	 }

	@Override
	public int getFinancialQuota(Long sellerId) {
		
//		FqSellerStatementExample example = new FqSellerStatementExample();
//		example.createCriteria().andSellerIdEqualTo(sellerId);
//		example.setOrderByClause("total_money DESC");
//		PageHelper.startPage(1, 1);
//		List<FqSellerStatement> list = fqSellerStatementMapper.selectByExample(example);
//		if (list != null &&list.size()>0) {
//			FqSellerStatement fqSellerStatement = list.get(0);
//			return fqSellerStatement.getTotalMoney().multiply(new BigDecimal(365)).intValue();
//		}else{
			if (daliyCreditMapper.countOrderNum(sellerId)>0) {
				return daliyCreditMapper.queryMaxDaliyCount(sellerId).multiply(new BigDecimal(365)).intValue();
			}
//		}
		return 0;
	}
	@Override
	public int updateCashOrder() {
		Date date = new Date();
		FqChannelRoleExample example2 = new FqChannelRoleExample();
		example2.createCriteria().andTypeEqualTo(2).andEffectiveTimeLessThanOrEqualTo(date);
		example2.setOrderByClause(" effective_time desc ");
		PageHelper.startPage(0, 1);
		List<FqChannelRole> roles = fqChannelRoleMapper.selectByExample(example2);
		if (roles.size()<1) {
			return 0;
		}
		FqChannelRole role = roles.get(0);
		FqThirdPayExample example = new FqThirdPayExample();
		example.createCriteria().andPayTimeGreaterThanOrEqualTo(new Date(System.currentTimeMillis()-60*60*1000L))
		.andBankTypeEqualTo(role.getPayType()).andMoneyGreaterThanOrEqualTo(role.getTotalMoney());
		List<FqThirdPay> orders = fqThirdPayMapper.selectByExample(example);
		int a = 0;
		for (int i = 0; i < orders.size(); i++) {
			FqThirdPay order = orders.get(0);
			example.clear();
			example.createCriteria().andSellerIdEqualTo(order.getSellerId()).andPayTimeGreaterThanOrEqualTo(new Date(System.currentTimeMillis()-role.getComputeCycle()*24*60*1000L))
			.andBankTypeEqualTo(role.getPayType()).andMoneyGreaterThanOrEqualTo(role.getTotalMoney());
			int count = fqThirdPayMapper.countByExample(example);
			FqThirdPay order1 = new FqThirdPay();
			order1.setId(order.getId());
			order1.setPayNum(count);
			if (count>=role.getTotalNum()) {
				order1.setIsCash(1);
			}
			a+=fqThirdPayMapper.updateByPrimaryKeySelective(order1);
		}
		return a;
	}
	@Override
	public FqMicroOrder insertFqMicroOrder(Long id, BigDecimal money) {
		String orderNo = OrderUtil.getOrderNo(SystemConstant.SZ_PF_WX_APP_PAY_TYPE);
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
	public FqThirdPay insertPayOrderBySZPFWXPay(Long id, BigDecimal money,
			String authCode, FqMicroOrder order, HttpServletRequest request,
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
		 packageParams.put("orderDate", DateUtil.getDateTime("yyyyMMdd",new Date()));
		 packageParams.put("orderNo", order.getOrderNo());
//		 packageParams.put("sub_openid", record.getOpenId());
		 packageParams.put("clientIp", request.getRemoteAddr());
		 packageParams.put("returnUrl", returnUrl);
		 packageParams.put("notifyUrl", notifyUrl);
		 packageParams.put("transAmt", String.valueOf(fee));
		 packageParams.put("autoCode", authCode);
		 StoreInfo store = storeService.queryStoreInfoBySeller(id);
		 packageParams.put("commodityName", store.getName()+"-移动支付");
		 System.out.println("------"+packageParams.toString());
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
				 int cxNum = 1;
				 while (cxNum>0) {
					  chaxunParamsMap =  SzPfPost.postPay(chaxunParams);
					  logger.info("select "+cxNum+" -----"+chaxunParamsMap.toString());
					  if ("0000".equals(chaxunParamsMap.get("respCode")) && "0000".equals(chaxunParamsMap.get("origRespCode"))) {
						 thirdPay = doHandlePaySuccessSzPfWx(order.getSellerId(), store, order, chaxunParamsMap);
						 return thirdPay;
					 }else{
						 order.setCxNum(cxNum+5);
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
//		String bankTypeCode = signMap.get("bank_type");
//		Integer bankType = null;
//		if (!StringUtils.isEmpty(bankTypeCode)) {
//			if (bankTypeCode.contains("DEBIT")) {
//				bankType = 3;
//			}
//			if (bankTypeCode.contains("CREDIT")) {
//				bankType = 2;
//			}
//			if (bankTypeCode.equals("CFT")) {
//				bankType = 1;
//			}
//		}
//		thirdPay.setBankType(bankType);
//		thirdPay.setBankTypeCode(bankTypeCode);
		thirdPay.setMoney(money);
		thirdPay.setOrderId(0L);
		thirdPay.setOrderNo(orderNo);
		thirdPay.setPayTime(order.getCreateTime());
		thirdPay.setSort(3);
		thirdPay.setTotamt(calTotamt);
		thirdPay.setOrderRate(rate);
		thirdPay.setRateFee(rateMoney);
		
		//5,浦发微信 6,浦发支付宝   3,光大微信 暂时没有光大支付宝 留空4
		
		thirdPay.setType(5);
		
		thirdPay.setSellerId(sellerId);
		thirdPay.setThirdOrderNo(weixinOrderNo);
//		thirdPay.setOpenId(openid);
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
		
		
		order.setStatus(2);
		fqmicroOrderMapper.updateByPrimaryKeySelective(order);
		
		
		//推送信息
		HashSet<Integer> pushSet = new HashSet<Integer>();
		pushSet.add(2);
		pushSet.add(3);
//		pushSet.add(4);
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

	// 是不是11点到12点之间的
		public static boolean is11hBetween12h(Date date){
			
			boolean flag = false;
			Calendar ncalendar = Calendar.getInstance();
			ncalendar.setTime(date);
			//小时
			int hour = ncalendar.get(Calendar.HOUR_OF_DAY);
			//System.out.println(hour);
			//分
			int minute = ncalendar.get(Calendar.MINUTE);
			//System.out.println(minute);
			//秒
			int second = ncalendar.get(Calendar.SECOND);
			//System.out.println(second);
			
			if(hour == 23 && minute < 59 && second < 59)
			{
				//System.out.println(minute);
				flag = true;
			}
			return flag;
		}
		public static Date addOneDay(Date date){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, 1);
			//System.out.print(calendar.getTime());
			return calendar.getTime();
			
		}
		public static void main(String[] args) {
			Date statementDay1 = DateUtil.convertStringToDate("yyyy-MM-dd hh:mm:ss","2017-09-05 23:16:31");
			if(is11hBetween12h(statementDay1))
			{
				statementDay1 = addOneDay(statementDay1);
			}
			String dateend = DateUtil.convertDateToString("yyyy-MM-dd",statementDay1);
			Date enddate0 = DateUtil.convertStringToDate("yyyy-MM-dd hh:mm:ss",dateend+" 23:00:00");
			Date startdate0 = DateUtil.convertStringToDate("yyyy-MM-dd hh:mm:ss",DateUtil.getYesDateStr(statementDay1,"yyyy-MM-dd")+" 23:00:00");
			System.out.println("now:"+dateend+" 23:00:00"+"=================="+DateUtil.getYesDateStr(statementDay1,"yyyy-MM-dd")+" 23:00:00");
		}
}
