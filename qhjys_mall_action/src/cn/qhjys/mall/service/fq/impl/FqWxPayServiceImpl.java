package cn.qhjys.mall.service.fq.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.qhjys.mall.alipay.util.AlipaySubmit;
import cn.qhjys.mall.common.AlipayConfig;
import cn.qhjys.mall.common.XingeService;
import cn.qhjys.mall.entity.FqMicroOrder;
import cn.qhjys.mall.entity.FqMicroOrderExample;
import cn.qhjys.mall.entity.FqOrder;
import cn.qhjys.mall.entity.FqOrderDetail;
import cn.qhjys.mall.entity.FqOrderDetailExample;
import cn.qhjys.mall.entity.FqOrderExample;
import cn.qhjys.mall.entity.FqPushInfo;
import cn.qhjys.mall.entity.FqSellerStatement;
import cn.qhjys.mall.entity.FqSellerStatementExample;
import cn.qhjys.mall.entity.FqStore;
import cn.qhjys.mall.entity.FqStoreCredit;
import cn.qhjys.mall.entity.FqStoreCreditExample;
import cn.qhjys.mall.entity.FqThirdPay;
import cn.qhjys.mall.entity.FqUserInfo;
import cn.qhjys.mall.entity.FqUserInfoExample;
import cn.qhjys.mall.entity.RebateCash;
import cn.qhjys.mall.entity.RebateOrder;
import cn.qhjys.mall.entity.RebateOrderExample;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.StoreRebate;
import cn.qhjys.mall.entity.WeixinUserinfo;
import cn.qhjys.mall.entity.WeixinUserinfoExample;
import cn.qhjys.mall.mapper.CashAccountMapper;
import cn.qhjys.mall.mapper.CashLogMapper;
import cn.qhjys.mall.mapper.FqMicroOrderMapper;
import cn.qhjys.mall.mapper.FqOrderDetailMapper;
import cn.qhjys.mall.mapper.FqOrderMapper;
import cn.qhjys.mall.mapper.FqRedpackDetailMapper;
import cn.qhjys.mall.mapper.FqRedpackMapper;
import cn.qhjys.mall.mapper.FqRedpackRecordMapper;
import cn.qhjys.mall.mapper.FqRedpackTimeMapper;
import cn.qhjys.mall.mapper.FqSellerStatementMapper;
import cn.qhjys.mall.mapper.FqStoreCreditMapper;
import cn.qhjys.mall.mapper.FqStoreMapper;
import cn.qhjys.mall.mapper.FqThirdPayMapper;
import cn.qhjys.mall.mapper.FqUserInfoMapper;
import cn.qhjys.mall.mapper.SellerInfoMapper;
import cn.qhjys.mall.mapper.WeixinUserinfoMapper;
import cn.qhjys.mall.quartz.PushPayInfoTask;
import cn.qhjys.mall.service.FqPushInfoService;
import cn.qhjys.mall.service.FqStoreRateService;
import cn.qhjys.mall.service.RedisService;
import cn.qhjys.mall.service.SellerService;
import cn.qhjys.mall.service.StoreService;
import cn.qhjys.mall.service.fq.FqWxPayService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.DateUtil;
import cn.qhjys.mall.util.WeixinUtil;
import cn.qhjys.mall.weixin.qqpay.CQpayMchSpBase;
import cn.qhjys.mall.weixin.util.HttpClientUtil;
import cn.qhjys.mall.weixin.util.MD5Util;
import cn.qhjys.mall.weixin.util.RequestHandler;
import cn.qhjys.mall.weixin.util.Sha1Util;
import cn.qhjys.mall.weixin.util.SystemConstant;
import cn.qhjys.mall.weixin.util.XMLUtil;

import com.alibaba.fastjson.JSONObject;
import com.tencent.xinge.ClickAction;
import com.tencent.xinge.Message;
import com.tencent.xinge.MessageIOS;
import com.tencent.xinge.XingeApp;

@Service("fqWxPayService")
public class FqWxPayServiceImpl implements FqWxPayService {
	private final Log logger = LogFactory.getLog(FqWxPayServiceImpl.class);
	
	@Autowired
	private FqOrderMapper fqOrderMapper;
	@Autowired
	private FqOrderDetailMapper fqOrderDetailMapper;
	@Autowired
	private FqUserInfoMapper fqUserInfoMapper;
	@Autowired
	private FqStoreMapper fqStoreMapper;
	@Autowired
	private CashAccountMapper cashAccountMapper;
	@Autowired
	private CashLogMapper cashLogMapper;
	
	@Autowired
	private FqThirdPayMapper fqThirdPayMapper;
	@Autowired
	private FqStoreCreditMapper fqStoreCreditMapper;
	@Autowired
	private FqRedpackMapper fqRedpackMapper;
	@Autowired
	private FqRedpackTimeMapper fqRedpackTimeMapper;
	@Autowired
	private FqRedpackDetailMapper fqRedpackDetailMapper;
	@Autowired
	private FqRedpackRecordMapper fqRedpackRecordMapper;
	@Autowired
	private RedisService redisService;
	@Autowired
	private FqSellerStatementMapper fqSellerStatementMapper;
	@Autowired
	private SellerService sellerService;
	@Autowired
	private FqPushInfoService fqPushInfoService;
	@Autowired
	private FqMicroOrderMapper fqmicroOrderMapper;
	@Autowired
	private SellerInfoMapper sellerInfoMapper;
	@Autowired
	private StoreService storeService;
	@Autowired
	private WeixinUserinfoMapper weixinUserinfoMapper;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired
	private FqStoreRateService fqStoreRateService;
	@Override
	public Map<String, Object> updateAndPayOrder(FqOrder order,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int a = fqOrderMapper.updateByPrimaryKeySelective(order);
		if (a <=0) {
			resultMap.put("errcode", "80000");
			return resultMap;
		}
		FqUserInfo user = fqUserInfoMapper.selectByPrimaryKey(order.getUserId());
		String JSAPI_URL = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_PAY_JSAPI_URL);
		String appId = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_APPID);
		String appSecret =ConstantsConfigurer.getProperty(SystemConstant.WECHAT_APPSECRET);
		String partner = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_PAY_PARTNER);
		String pkey = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_PAY_PKEY);
		String notifyUrl = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_PAY_NOTIFYURLYF);
		RequestHandler reqHandler = new RequestHandler(request, response);
		//初始化 
	    reqHandler.init(appId, appSecret, null,partner, pkey); 
	    String noncestr = Sha1Util.getNonceStr();
			String outtradeno = order.getOrderNo();
			if (order.getPayType() == 0) {
				outtradeno +="1";
			}else{
				outtradeno +="2";
			}
	        //设置package订单参数
	        SortedMap<String, String> packageParams = new TreeMap<String, String>();
	        packageParams.put("appid", appId); //支付appId
	        packageParams.put("body", order.getStoreName()+"-支付"); //商品描述   
	        packageParams.put("device_info","WEB"); //商品描述   
	        packageParams.put("mch_id", partner); 							//商家号
	        packageParams.put("nonce_str", noncestr);  
	        packageParams.put("notify_url", notifyUrl); 					//通知地址  
	        packageParams.put("openid", user.getOpenId());
	        packageParams.put("out_trade_no", outtradeno); 				//商户订单号  
	        packageParams.put("spbill_create_ip",request.getRemoteAddr()); 	//订单生成的机器IP，指用户浏览器端IP
	        int fee = order.getPayAmount().multiply(new BigDecimal(100)).intValue();
	        packageParams.put("total_fee", String.valueOf(fee)); 			//商品总金额,以分为单位
	        packageParams.put("trade_type", "JSAPI"); 						//js支付
			
	        
	        //第一次签名
	        String sign1  = reqHandler.createSign(packageParams);
	        packageParams.put("sign", sign1);
	        
			
	        String requestXML = XMLUtil.getRequestXml(packageParams);
	        String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
	        logger.info(">>>>>>>>>>>>>>>>>>>>微信支付第一次签名参数result："+result);
	        Map<String, String> signMap = new HashMap<String, String>();
	        try{
	        	signMap = XMLUtil.doXMLParse(result);
	        }catch (JDOMException e){
	           logger.error("-----------xml解析出错:",e);
	           resultMap.put("errcode", SystemConstant.ERROR);
			   return resultMap;
	        }catch (IOException e){
	            logger.info("-----------数据异常:",e);
	            resultMap.put("errcode", SystemConstant.ERROR);
				return resultMap;
	        }
	        
	        	logger.info(">>>>>>>>>>>>>>>>>>>>微信支付统一下单返回参数signMap："+signMap.toString());
	        
	        if("FAIL".equals(signMap.get("return_code")) ){
	        	resultMap.put("errcode", SystemConstant.ERROR);
	        	logger.info(">>>>>>>>>>>>>>>>>>>>微信支付统一下单失败signMap："+signMap.toString());
				return resultMap;
	        }
	        
	        //获取 到prepay_id
	        String packageValue = signMap.get("prepay_id");
	        String timestamp = Sha1Util.getTimeStamp();
	        
	        //第二次签名
	        SortedMap<String, String> signParams = new TreeMap<String, String>();
	        signParams.put("appId", appId);
	        signParams.put("nonceStr", noncestr);
	        signParams.put("package", "prepay_id=" + packageValue);
	        signParams.put("timeStamp", timestamp);
	        signParams.put("signType", "MD5");
	        String sign2 = reqHandler.createSign(signParams);
	        
	        //返回前台参数
	        resultMap.put("appId", appId);
	        resultMap.put("nonceStr", noncestr);
	        resultMap.put("package", "prepay_id=" + packageValue);
	        resultMap.put("timeStamp", timestamp);
	        resultMap.put("signType", "MD5");
	        resultMap.put("paySign", sign2);
	        resultMap.put("orderId", order.getId().toString());
	        resultMap.put("payType", order.getPayType());
	        resultMap.put("openid", user.getOpenId());
			
	        resultMap.put("errcode", SystemConstant.SUCCESS);
	        
	        logger.info(">>>>>>>>>>>>>>>>>>>>返回微信支付参数resultMap："+resultMap.toString());
	        
			return resultMap;
	}
	@Override
	public synchronized Integer updateYudingPay(Map<String, String> notifyParamsMap,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			response.setContentType("text/html;charset=UTF-8");
			if(notifyParamsMap == null){
				  logger.info(">>>>>>>>>>>>>>>>>>微信支付回调 参数 is null");
	             // response.getWriter().print("fail");
	              return 0;
			}
			logger.info(">>>>>>>>>>>>>>>>>>微信支付回调 参数" + notifyParamsMap.toString());
			
			if ("FAIL".equals(notifyParamsMap.get("return_code"))) {
				logger.info(">>>>>>>>>>>>>>>>>>微信支付回调，通信异常："+notifyParamsMap.toString());
				//response.getWriter().print("fail");
				return 0;
			}
			if ("FAIL".equals(notifyParamsMap.get("result_code"))) {
				logger.info(">>>>>>>>>>>>>>>>>>微信支付回调失败:"+notifyParamsMap.toString());
				//response.getWriter().print("fail");
				return 0;
			}
			//微信支付回调通知验签
			if(isWxSign(notifyParamsMap)){
				
				//回调成功 -处理业务状态
				if ("SUCCESS".equals(notifyParamsMap.get("return_code")) && "SUCCESS".equals(notifyParamsMap.get("result_code"))) {
					String outtradeno = notifyParamsMap.get("out_trade_no");
					String tradeNo =outtradeno.substring(0, outtradeno.length()-1);			
					String weixinOrderNo =notifyParamsMap.get("transaction_id") ;			
					String payTime = notifyParamsMap.get("time_end");				//支付时间
					
					FqOrderExample example = new FqOrderExample();
					example.createCriteria().andOrderNoEqualTo(tradeNo);
					List<FqOrder> recordlist  = fqOrderMapper.selectByExample(example);
					
					if(recordlist == null || recordlist.size() == 0){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"不存在");
						//response.getWriter().print("fail");
						return 0;
					}
					FqOrder record =recordlist.get(0);
					if(!SystemConstant.PAY_WAITPAY.equals(record.getStatus())){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"己支付");
						//response.getWriter().print("fail");
						return 1;
					}else{
						
						//更新订单  
						if (record.getPayType() == 1) {
							record.setStatus(2);
						}else{
							record.setStatus(1);
						}
						
						record.setWxOrderNo(weixinOrderNo);
						Date now = new Date();
						fqOrderMapper.updateByPrimaryKeySelective(record);
						
						
						FqStore store = fqStoreMapper.selectByPrimaryKey(record.getStoreId());
						FqUserInfo user =fqUserInfoMapper.selectByPrimaryKey(record.getUserId());
						SellerInfo seller = sellerService.getSellerById(store.getSellerId());

						FqThirdPay thirdPay = new  FqThirdPay();
						thirdPay.setMoney(record.getPayAmount());
						thirdPay.setOrderId(record.getId());
						thirdPay.setOrderNo(record.getOrderNo());
						thirdPay.setPayTime(now);
						thirdPay.setSort(2);
						thirdPay.setThirdOrderNo(weixinOrderNo);
						thirdPay.setType(1);
						thirdPay.setSellerId(store.getSellerId());
						thirdPay.setTotamt(record.getTotalAmount());
						thirdPay.setUserId(user.getId());
						thirdPay.setOpenId(user.getOpenId());
						fqThirdPayMapper.insertSelective(thirdPay);
						
						if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
							Date date0 = BaseUtil.getTimeDate(record.getCreateTime());
							FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
							fqSellerStatementExample.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatementDateEqualTo(date0);
							List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
							FqSellerStatement fsstate = null;
							if (fsslist.size()>0) {
								fsstate = fsslist.get(0);
								fsstate.setTotalMoney(fsstate.getTotalMoney().add(record.getPayAmount()));
								fsstate.setTotalNum(fsstate.getTotalNum()+1);
								fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
							}else{
								fsstate = new FqSellerStatement();
								fsstate.setCreateTime(new Date());
								fsstate.setStatementDate(date0);
								fsstate.setSellerId(store.getSellerId());
								fsstate.setState(0);
								fsstate.setTotalMoney(record.getPayAmount());
								fsstate.setTotalNum(1);
								StoreInfo storeinfo = storeService.queryStoreInfoBySeller(store.getSellerId());
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(date0);
								calendar.add(Calendar.DATE, storeinfo.getStatementPeriod());
								fsstate.setPeriodDate(calendar.getTime());
								fqSellerStatementMapper.insertSelective(fsstate);
							}
							
						}else{
							FqStoreCreditExample example3 = new FqStoreCreditExample();
							example3.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
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
						FqPushInfo fqPushInfo = new FqPushInfo();
						fqPushInfo.setContent("");
						fqPushInfo.setPushTime(new Date());
						fqPushInfo.setSellerId(thirdPay.getSellerId());
						fqPushInfo.setTitle("你已收到付款"+thirdPay.getMoney().toString()+"元");
						fqPushInfo.setType(1);
					    int a = fqPushInfoService.insertFqPushInfo(fqPushInfo);
					    logger.info("---------save--pushinfo-------"+a);
						Map<String,Object> custom = new HashMap<String,Object>(); 
						 custom.put("type",1);
						 MessageIOS messageIos = new MessageIOS();
						 messageIos.setExpireTime(60);
						 messageIos.setAlert("您已收到付款"+thirdPay.getMoney().toString()+"元");
						 messageIos.setBadge(1);
						 messageIos.setSound("12 Bar Blues Bass.caf");
						 messageIos.setCustom(custom);
						 JSONObject obj = new JSONObject();
						 JSONObject aps = new JSONObject();
						 aps.put("sound", "12 Bar Blues Bass.caf"); 
						 aps.put("alert", "您已收到付款"+thirdPay.getMoney().toString()+"元");
						 aps.put("badge", 1); 
						 obj.put("aps", aps); 
						 messageIos.setRaw(obj.toString());
						 XingeService.xingeIos.pushSingleAccount(0, thirdPay.getSellerId()+"", messageIos, XingeApp.IOSENV_PROD);
						 XingeService.xingeIos.pushSingleAccount(0, thirdPay.getSellerId()+"", messageIos, XingeApp.IOSENV_DEV);
						 Message message = new Message();
						 message.setExpireTime(60);
						 message.setTitle("支付成功");
						 message.setContent("您已收到付款"+thirdPay.getMoney().toString()+"元");
						 message.setType(Message.TYPE_NOTIFICATION);
						 message.setCustom(custom);
						 ClickAction action = new ClickAction();
						 action.setActionType(ClickAction.TYPE_ACTIVITY);
						 action.setActivity("com.freechange.seller.modules.MainActivity");
						 message.setAction(action);
						XingeService.xinge.pushSingleAccount(0, thirdPay.getSellerId()+"", message);
						
						FqOrderDetailExample fodexample = new FqOrderDetailExample();
						fodexample.createCriteria().andOrderIdEqualTo(record.getId());
						List<FqOrderDetail> fods = fqOrderDetailMapper.selectByExample(fodexample);
						String detailinfo = "商品详情:";
						for (int i = 0; i < fods.size(); i++) {
							FqOrderDetail fod = fods.get(i);
							if (i>0) {
								detailinfo+="\n\t\t   ";
							}
							detailinfo +=fod.getProductName()+" X "+fod.getQuantity();
						}
						
						JSONObject data1 = new JSONObject();
						data1.put("touser", "ob8WbwWQWmdZoykLjOvwCLw5CRmk");
						data1.put("template_id", "jXAaCLWCcZuShw2nuHpS_SI8igVx8nZY_taD_vLnj3c");
						JSONObject data = new JSONObject();
						JSONObject params;
						String firstdata = "";
						if (!StringUtils.isEmpty(record.getDeskNo())&&record.getDeskNo().length()>0) {
							firstdata = "桌号："+record.getDeskNo()+",";
						}
						params = new JSONObject();
						params.put("value", "您好，桌号["+firstdata+"] 已点餐成功！\n\n"+detailinfo);
						params.put("color", "#000000");
						data.put("first", params);
						params = new JSONObject();
						params.put("value", record.getOrderNo());
						params.put("color", "#000000");
						data.put("keyword1", params);
						params = new JSONObject();
						params.put("value", "￥"+record.getPayAmount());
						params.put("color", "#000000");
						data.put("keyword2", params);
						
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						params = new JSONObject();
						params.put("value", sdf.format(new Date()));
						params.put("color", "#000000");
						data.put("keyword3", params);
						
						params = new JSONObject();
						params.put("value", "\n感谢您的使用！");
						params.put("color", "#000000");
						data.put("remark", params);
						
						data1.put("data", data);
						data1.put("url", "");
						
						FqUserInfoExample example2 = new FqUserInfoExample();
						example2.createCriteria().andStoreIdEqualTo(store.getSellerId());
						List<FqUserInfo> list = fqUserInfoMapper.selectByExample(example2);
						for (int i = 0; i < list.size(); i++) {
							data1.remove("touser");
							data1.put("touser", list.get(i).getOpenId());
							WeixinUtil.sendTempMessage(data1.toJSONString());
						}
//						logger.info("-----store.getId().toString()-----"+store.getId().toString());
//						logger.info("-----store.getStoreName()-----"+store.getStoreName());
//						logger.info("-----now-----"+now);
//						
//						FqRedpackExample example4 = new FqRedpackExample();
//						example4.createCriteria().andStatusEqualTo(1).andStoreIdsLike("%"+store.getId().toString()+"%").andStoreNamesLike("%"+store.getStoreName()+"%")
//						.andBeginDateLessThanOrEqualTo(now).andEndDateGreaterThanOrEqualTo(now);
//						example4.setOrderByClause("create_time desc");
//						List<FqRedpack> list3 = fqRedpackMapper.selectByExample(example4);
//						logger.info("-----List<FqRedpack> list3-----"+list3.size());
//						if (list3.size()>0) {
//							logger.info("-----list3.get(0).getId()-----"+list3.get(0).getId());
//							FqRedpackRecordExample frrExample = new FqRedpackRecordExample();
//							frrExample.createCriteria().andOpenIdEqualTo(user.getOpenId()).andRedpackIdEqualTo(list3.get(0).getId()).andCreateTimeBetween(new Date(now.getTime()-now.getTime()%86400000 - 28800000), new Date(now.getTime()-now.getTime()%86400000 + 57600000));
//							int rpnum = fqRedpackRecordMapper.countByExample(frrExample);
//							logger.info("-----rpnum----"+rpnum);
//							if (rpnum<list3.get(0).getDaliyNum()) {
//								
//							Long minit = (now.getTime()+28800000)%86400000/1000/60;
//							logger.info("-----minit----"+minit);
//							FqRedpackTimeExample example5 = new FqRedpackTimeExample();
//							example5.createCriteria().andBeginTimeLessThanOrEqualTo(minit).andEndTimeGreaterThanOrEqualTo(minit).andRedpackIdEqualTo(list3.get(0).getId());
//							List<FqRedpackTime> list4 = fqRedpackTimeMapper.selectByExample(example5);
//							logger.info("-----list4.size()----"+list4.size());
//							if (list4.size()>0) {
//								FqRedpackDetailExample example6 = new FqRedpackDetailExample();
//								example6.createCriteria().andRedpackIdEqualTo(list3.get(0).getId());
//								example6.setOrderByClause("id");
//								List<FqRedpackDetail> list5 = fqRedpackDetailMapper.selectByExample(example6);
//								logger.info("-----list5.size()----"+list5.size());
//								if (list5.size()>0) {
//									BigDecimal rpm = BigDecimal.ZERO;
//									if (list5.get(0).getType() == 1) {
//										Integer suiji =  (Integer) redisService.getValueByKey("store"+store.getId());
//										if (suiji == null||suiji > 10) {
//											suiji = 1;
//										}
//										int num = 0;
//										for (int i = 0; i < list5.size(); i++) {
//											FqRedpackDetail frd = list5.get(i);
//											num += frd.getProbability()/10;
//											if (num>=suiji) {
//												int a = (int)(Math.random()*(frd.getMaxAmount().subtract(frd.getMinAmount()).multiply(new BigDecimal(100)).intValue()+1));
//												logger.info("-----a---"+a);
//												logger.info("-----chazhi---"+frd.getMaxAmount().subtract(frd.getMinAmount()).multiply(new BigDecimal(100)).intValue()+1);
//												rpm = new BigDecimal(a).divide(new BigDecimal(100)).add(frd.getMinAmount());
//												logger.info("-----new BigDecimal(a).divide(new BigDecimal(100))---"+new BigDecimal(a).divide(new BigDecimal(100)));
//												break;
//											}
//										}
//										if (suiji >= 10) {
//											suiji = 0;
//										}
//										logger.info("-----suiji---"+suiji);
//
//										redisService.setValueByKey("store"+store.getId(), suiji+1);	
//									}else{
//										for (int i = 0; i < list5.size(); i++) {
//											FqRedpackDetail frd = list5.get(i);
//											if (frd.getMaxMoney().compareTo(record.getPayAmount())== 1 && frd.getMinMoney().compareTo(record.getPayAmount())<1) {
//												int a = (int)(Math.random()*(frd.getMaxAmount().subtract(frd.getMinAmount()).multiply(new BigDecimal(100)).intValue()+1));
//												rpm = new BigDecimal(a).divide(new BigDecimal(100)).add(frd.getMinAmount());
//												logger.info("-----1rpm---"+rpm);
//												break;
//											}
//										}
//									}
//									logger.info("-----rpm---"+rpm);
//									if (rpm.compareTo(BigDecimal.ZERO)==1) {
//										FqRedpackRecord frr = new FqRedpackRecord();
//										frr.setConsumeMoney(record.getPayAmount());
//										frr.setCreateTime(now);
//										frr.setOrderId(record.getId()*10+1);
//										frr.setRedpackId(list3.get(0).getId());
//										frr.setRedpackMoney(rpm);
//										frr.setStatus(0);
//										frr.setStoreId(store.getId());
//										frr.setStoreName(store.getStoreName());
//										frr.setOpenId(user.getOpenId());
//										fqRedpackRecordMapper.insertSelective(frr);
//										
//										JSONObject data2 = new JSONObject();
//										data2.put("touser", user.getOpenId());
//										data2.put("msgtype", "news");
//										JSONArray articles = new JSONArray();
//										JSONObject article = new JSONObject();
//										article.put("title", "支付好礼");
//										article.put("url", "http://"+ConstantsConfigurer.getProperty("web_url")+"/user/fqRedpack/index.do?rpid="+frr.getId());
//										article.put("picurl", "http://"+ConstantsConfigurer.getProperty("web_url")+"/images/feiquan320.png");
//										articles.add(article);
//										JSONObject news = new JSONObject();
//										news.put("articles", articles);
//										data2.put("news", news);
//										JSONObject result = WeixinUtil.sendCustomMessage(data2.toJSONString());
//										logger.info("-----result---"+result);
//									}
//								}
//							}
//							}
//						}
						
						logger.error(">>>>>>>>>>>>>>>>>>订单："+record.getId()+"，回调成功");
						//更新订单成功，返回成功状态
						//response.getWriter().print("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
						return 1;
					}
				}
			}else{
				logger.error(">>>>>>>>>>>>>>>>>>回调验签失败notifyParamsMap："+notifyParamsMap.toString());
				return 0;
			}
			return 0;
		}catch(Exception e){
			logger.error("updateCheckNotify error", e);
			throw e;
		}
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
public static boolean isWftSign(Map<String,String> paramsMap) throws Exception{
	
	boolean flag = false;
	String pkey = ConstantsConfigurer.getProperty(SystemConstant.WFT_PAY_KEY);
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
public static boolean isGdSign(Map<String,String> paramsMap) throws Exception{
	
	boolean flag = false;
	String pkey = ConstantsConfigurer.getProperty(SystemConstant.GD_PAY_KEY);
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
public static boolean isPfSign(Map<String,String> paramsMap) throws Exception{
	
	boolean flag = false;
	String pkey = ConstantsConfigurer.getProperty(SystemConstant.PF_PAY_KEY);
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
@Override
public Integer updateWeiKuanPay(Map<String, String> notifyParamsMap,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	try{
	response.setContentType("text/html;charset=UTF-8");
	if(notifyParamsMap == null){
		  logger.info(">>>>>>>>>>>>>>>>>>微信支付回调 参数 is null");
         // response.getWriter().print("fail");
          return 0;
	}
	logger.info(">>>>>>>>>>>>>>>>>>微信支付回调 参数" + notifyParamsMap.toString());
	
	if ("FAIL".equals(notifyParamsMap.get("return_code"))) {
		logger.info(">>>>>>>>>>>>>>>>>>微信支付回调，通信异常："+notifyParamsMap.toString());
		//response.getWriter().print("fail");
		return 0;
	}
	if ("FAIL".equals(notifyParamsMap.get("result_code"))) {
		logger.info(">>>>>>>>>>>>>>>>>>微信支付回调失败:"+notifyParamsMap.toString());
		//response.getWriter().print("fail");
		return 0;
	}
	//微信支付回调通知验签
	if(isWxSign(notifyParamsMap)){
		
		//回调成功 -处理业务状态
		if ("SUCCESS".equals(notifyParamsMap.get("return_code")) && "SUCCESS".equals(notifyParamsMap.get("result_code"))) {
			
			String outtradeno = notifyParamsMap.get("out_trade_no");
			String tradeNo =outtradeno.substring(0, outtradeno.length()-1);		
			String weixinOrderNo =notifyParamsMap.get("transaction_id") ;			
			String payTime = notifyParamsMap.get("time_end");				//支付时间
			
			FqOrderExample example = new FqOrderExample();
			example.createCriteria().andOrderNoEqualTo(tradeNo);
			List<FqOrder> recordlist  = fqOrderMapper.selectByExample(example);
			
			if(recordlist == null || recordlist.size() == 0){
				logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"不存在");
				//response.getWriter().print("fail");
				return 0;
			}
			FqOrder record =recordlist.get(0);
			if(SystemConstant.PAY_ISPAY.equals(record.getStatus())){
				logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"己支付");
				//response.getWriter().print("fail");
				return 1;
			}else{
				//更新订单  
				BigDecimal ruzhang = record.getRebateAmount().subtract(record.getPayAmount());
				record.setStatus(2);
				record.setPayAmount(record.getRebateAmount());
				
				if (StringUtils.isEmpty(record.getWxOrderNo())) {
					record.setWxOrderNo(weixinOrderNo);
				}else{
					record.setWxOrderNo(record.getWxOrderNo()+","+weixinOrderNo);
				}
				Date now = new Date();
				fqOrderMapper.updateByPrimaryKeySelective(record);
				
				FqStore store = fqStoreMapper.selectByPrimaryKey(record.getStoreId());
				FqUserInfo user =fqUserInfoMapper.selectByPrimaryKey(record.getUserId());
				SellerInfo seller = sellerService.getSellerById(store.getSellerId());
				
				FqThirdPay thirdPay = new  FqThirdPay();
				thirdPay.setMoney(record.getPayAmount());
				thirdPay.setOrderId(record.getId());
				thirdPay.setOrderNo(record.getOrderNo());
				thirdPay.setPayTime(now);
				thirdPay.setSort(2);
				thirdPay.setThirdOrderNo(weixinOrderNo);
				thirdPay.setType(1);
				thirdPay.setSellerId(store.getSellerId());
				thirdPay.setTotamt(record.getTotalAmount());
				thirdPay.setUserId(user.getId());
				thirdPay.setOpenId(user.getOpenId());
				fqThirdPayMapper.insertSelective(thirdPay);
				
				if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
					Date date0 = BaseUtil.getTimeDate(record.getCreateTime());
					FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
					fqSellerStatementExample.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatementDateEqualTo(date0);
					List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
					FqSellerStatement fsstate = null;
					if (fsslist.size()>0) {
						fsstate = fsslist.get(0);
						fsstate.setTotalMoney(fsstate.getTotalMoney().add(record.getPayAmount()));
						fsstate.setTotalNum(fsstate.getTotalNum()+1);
						fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
					}else{
						fsstate = new FqSellerStatement();
						fsstate.setCreateTime(new Date());
						fsstate.setStatementDate(date0);
						fsstate.setSellerId(store.getSellerId());
						fsstate.setState(0);
						fsstate.setTotalMoney(record.getPayAmount());
						fsstate.setTotalNum(1);
						StoreInfo storeinfo = storeService.queryStoreInfoBySeller(store.getSellerId());
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(date0);
						calendar.add(Calendar.DATE, storeinfo.getStatementPeriod());
						fsstate.setPeriodDate(calendar.getTime());
						fqSellerStatementMapper.insertSelective(fsstate);
					}
				}else{
					FqStoreCreditExample example3 = new FqStoreCreditExample();
					example3.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
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
				
				
				
//				CashAccountExample example1 = new CashAccountExample();
//				example1.createCriteria().andAccountIdEqualTo(store.getSellerId()).andAccountTypeEqualTo(1);
//				List<CashAccount> list2 = cashAccountMapper.selectByExample(example1);
//				if (list2 != null && list2.size() >0){
//					CashAccount account = list2.get(0); // 商家资金帐号
//					BigDecimal sbalance = account.getBalance(); // 商家账户金额
//					account.setBalance(sbalance.add(ruzhang));
//					account.setCreateDate(new Date());
//					int row = cashAccountMapper.updateByPrimaryKey(account); // 3.商家添加金额
//					CashLog log = new CashLog(); // 添加商家销售入账记录
//					log.setRecordNo(record.getOrderNo());
//					log.setSendId(user.getId());
//					log.setReviewId(store.getSellerId());
//					log.setAmount(ruzhang);
//					log.setPayType(5);
//					log.setPayWay(4);
//					log.setBusinessCode(PAYCODE.B005);
//					log.setBusinessName(PAYCODE.B005N);
//					log.setReviewBefor(sbalance);
//					log.setReviewAfter(sbalance.add(ruzhang));
//					log.setCreateTime(now);
//					row += cashLogMapper.insertSelective(log);
//				}
				

				FqOrderDetailExample fodexample = new FqOrderDetailExample();
				fodexample.createCriteria().andOrderIdEqualTo(record.getId());
				List<FqOrderDetail> fods = fqOrderDetailMapper.selectByExample(fodexample);
				String detailinfo = "商品详情:";
				for (int i = 0; i < fods.size(); i++) {
					FqOrderDetail fod = fods.get(i);
					if (i>0) {
						detailinfo+="\n\t\t   ";
					}
					detailinfo +=fod.getProductName()+" X "+fod.getQuantity();
				}
				
				JSONObject data1 = new JSONObject();
				data1.put("touser", "ob8WbwWQWmdZoykLjOvwCLw5CRmk");
				data1.put("template_id", "jXAaCLWCcZuShw2nuHpS_SI8igVx8nZY_taD_vLnj3c");
				JSONObject data = new JSONObject();
				JSONObject params;
				String firstdata = "";
				if (!StringUtils.isEmpty(record.getDeskNo())&&record.getDeskNo().length()>0) {
					firstdata = "桌号："+record.getDeskNo()+",";
				}
				params = new JSONObject();
				params.put("value", "您好，桌号["+firstdata+"] 已点餐成功！\n\n"+detailinfo);
				params.put("color", "#000000");
				data.put("first", params);
				params = new JSONObject();
				params.put("value", record.getOrderNo());
				params.put("color", "#000000");
				data.put("keyword1", params);
				params = new JSONObject();
				params.put("value", "￥"+record.getPayAmount());
				params.put("color", "#000000");
				data.put("keyword2", params);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				params = new JSONObject();
				params.put("value", sdf.format(new Date()));
				params.put("color", "#000000");
				data.put("keyword3", params);
				
				params = new JSONObject();
				params.put("value", "\n感谢您的使用！");
				params.put("color", "#000000");
				data.put("remark", params);
				
				data1.put("data", data);
				data1.put("url", "");
				
				FqUserInfoExample example2 = new FqUserInfoExample();
				example2.createCriteria().andStoreIdEqualTo(store.getSellerId());
				List<FqUserInfo> list = fqUserInfoMapper.selectByExample(example2);
				for (int i = 0; i < list.size(); i++) {
					data1.remove("touser");
					data1.put("touser", list.get(i).getOpenId());
					WeixinUtil.sendTempMessage(data1.toJSONString());
				}
				
				
				logger.error(">>>>>>>>>>>>>>>>>>订单："+record.getId()+"，回调成功");
				//更新订单成功，返回成功状态
				//response.getWriter().print("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
				return 1;
			}
		}
		}else{
		logger.error(">>>>>>>>>>>>>>>>>>回调验签失败notifyParamsMap："+notifyParamsMap.toString());
		return 0;
		}
		return 0;
	}catch(Exception e){
		logger.error("updateCheckNotify error", e);
		throw e;
	}
}
@Override
public Map<String, Object> updateAndPayOrderWk(FqOrder order,
		HttpServletRequest request, HttpServletResponse response) {
	Map<String,Object> resultMap = new HashMap<String,Object>();
	
	FqUserInfo user = fqUserInfoMapper.selectByPrimaryKey(order.getUserId());
	String JSAPI_URL = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_PAY_JSAPI_URL);
	String appId = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_APPID);
	String appSecret =ConstantsConfigurer.getProperty(SystemConstant.WECHAT_APPSECRET);
	String partner = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_PAY_PARTNER);
	String pkey = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_PAY_PKEY);
	String notifyUrl = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_PAY_NOTIFYURLWK);
	RequestHandler reqHandler = new RequestHandler(request, response);
	//初始化 
    reqHandler.init(appId, appSecret, null,partner, pkey);
    String noncestr = Sha1Util.getNonceStr();
	    String outtradeno = order.getOrderNo()+"3";
        //设置package订单参数
        SortedMap<String, String> packageParams = new TreeMap<String, String>();
        packageParams.put("appid", appId); //支付appId
        packageParams.put("body", order.getStoreName()+"-尾款支付"); //商品描述   
        packageParams.put("device_info","WEB"); //商品描述   
        packageParams.put("mch_id", partner); 							//商家号
        packageParams.put("nonce_str", noncestr);  
        packageParams.put("notify_url", notifyUrl); 					//通知地址  
        packageParams.put("openid", user.getOpenId());
        packageParams.put("out_trade_no", outtradeno); 				//商户订单号  
        packageParams.put("spbill_create_ip",request.getRemoteAddr()); 	//订单生成的机器IP，指用户浏览器端IP
        int fee = order.getRebateAmount().subtract(order.getPayAmount()).multiply(new BigDecimal(100)).intValue();
        packageParams.put("total_fee", String.valueOf(fee)); 			//商品总金额,以分为单位
        packageParams.put("trade_type", "JSAPI"); 						//js支付
		
        
        //第一次签名
        String sign1  = reqHandler.createSign(packageParams);
        packageParams.put("sign", sign1);
        
		
        String requestXML = XMLUtil.getRequestXml(packageParams);
        String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
        logger.info(">>>>>>>>>>>>>>>>>>>>微信支付第一次签名参数result："+result);
        Map<String, String> signMap = new HashMap<String, String>();
        try{
        	signMap = XMLUtil.doXMLParse(result);
        }catch (JDOMException e){
           logger.error("-----------xml解析出错:",e);
           resultMap.put("errcode", SystemConstant.ERROR);
		   return resultMap;
        }catch (IOException e){
            logger.info("-----------数据异常:",e);
            resultMap.put("errcode", SystemConstant.ERROR);
			return resultMap;
        }
        
        	logger.info(">>>>>>>>>>>>>>>>>>>>微信支付统一下单返回参数signMap："+signMap.toString());
        
        if("FAIL".equals(signMap.get("return_code")) ){
        	resultMap.put("errcode", SystemConstant.ERROR);
        	logger.info(">>>>>>>>>>>>>>>>>>>>微信支付统一下单失败signMap："+signMap.toString());
			return resultMap;
        }
        
        //获取 到prepay_id
        String packageValue = signMap.get("prepay_id");
        String timestamp = Sha1Util.getTimeStamp();
        
        //第二次签名
        SortedMap<String, String> signParams = new TreeMap<String, String>();
        signParams.put("appId", appId);
        signParams.put("nonceStr", noncestr);
        signParams.put("package", "prepay_id=" + packageValue);
        signParams.put("timeStamp", timestamp);
        signParams.put("signType", "MD5");
        String sign2 = reqHandler.createSign(signParams);
        
        
        
        
        //返回前台参数
        resultMap.put("appId", appId);
        resultMap.put("nonceStr", noncestr);
        resultMap.put("package", "prepay_id=" + packageValue);
        resultMap.put("timeStamp", timestamp);
        resultMap.put("signType", "MD5");
        resultMap.put("paySign", sign2);
        resultMap.put("orderId", order.getId().toString());
        resultMap.put("openid", user.getOpenId());
		
        resultMap.put("errcode", SystemConstant.SUCCESS);
        
        logger.info(">>>>>>>>>>>>>>>>>>>>返回微信支付参数resultMap："+resultMap.toString());
        
		return resultMap;
}
@Override
public Map<String, Object> updateAndPayOrderByAlipay(FqOrder order) {
	// TODO Auto-generated method stub
	Map<String,Object> resultMap = new HashMap<String,Object>();
	int a = fqOrderMapper.updateByPrimaryKeySelective(order);
	if (a <=0) {
		resultMap.put("errcode", "80000");
		return resultMap;
	}
	Map<String, String> sParaTemp = new HashMap<String, String>();
	sParaTemp.put("service", AlipayConfig.service);
    sParaTemp.put("partner", AlipayConfig.partner);
    sParaTemp.put("seller_id", AlipayConfig.seller_id);
    sParaTemp.put("_input_charset", AlipayConfig.input_charset);
	sParaTemp.put("payment_type", AlipayConfig.payment_type);
	sParaTemp.put("notify_url", AlipayConfig.notify_url);
	sParaTemp.put("return_url", AlipayConfig.return_url);
	sParaTemp.put("out_trade_no", order.getOrderNo());
	sParaTemp.put("subject", order.getStoreName()+"-支付");
	sParaTemp.put("total_fee", order.getPayAmount().toString());
	sParaTemp.put("show_url", "");
	//sParaTemp.put("app_pay","Y");//启用此参数可唤起钱包APP支付。
	sParaTemp.put("body", "");
	String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
	 resultMap.put("errcode", SystemConstant.SUCCESS);
	 resultMap.put("sHtmlText", sHtmlText);
	return resultMap;
}
@Override
public Integer updateAlipay(String orderNo,String trandno) throws Exception {
	FqOrderExample example = new FqOrderExample();
	example.createCriteria().andOrderNoEqualTo(orderNo);
	List<FqOrder> recordlist  = fqOrderMapper.selectByExample(example);
	if (recordlist.size()<=0) {
		return 0;
	}
	FqOrder record = recordlist.get(0);
	if(SystemConstant.PAY_ISPAY.equals(record.getStatus())){
		logger.error(">>>>>>>>>>>>>alipay>>>>>订单"+orderNo+"己支付");
		//response.getWriter().print("fail");
		return 1;
	}else{
		//更新订单  
		BigDecimal ruzhang = record.getRebateAmount().subtract(record.getPayAmount());
		record.setStatus(2);
		record.setPayAmount(record.getRebateAmount());
		
		if (StringUtils.isEmpty(record.getWxOrderNo())) {
			record.setWxOrderNo(trandno);
		}else{
			record.setWxOrderNo(record.getWxOrderNo()+","+trandno);
		}
		Date now = new Date();
		fqOrderMapper.updateByPrimaryKeySelective(record);
		
		
		FqStore store = fqStoreMapper.selectByPrimaryKey(record.getStoreId());
		FqUserInfo user =fqUserInfoMapper.selectByPrimaryKey(record.getUserId());
		SellerInfo seller = sellerService.getSellerById(store.getSellerId());
		
		FqThirdPay thirdPay = new  FqThirdPay();
		thirdPay.setMoney(record.getPayAmount());
		thirdPay.setOrderId(record.getId());
		thirdPay.setOrderNo(record.getOrderNo());
		thirdPay.setPayTime(now);
		thirdPay.setSort(2);
		thirdPay.setThirdOrderNo(trandno);
		thirdPay.setType(2);
		thirdPay.setSellerId(store.getSellerId());
		thirdPay.setTotamt(record.getTotalAmount());
		thirdPay.setUserId(user.getId());
		thirdPay.setOpenId(user.getOpenId());
		fqThirdPayMapper.insertSelective(thirdPay);
		
		if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
			Date date0 = BaseUtil.getTimeDate(record.getCreateTime());
			FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
			fqSellerStatementExample.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatementDateEqualTo(date0).andStateEqualTo(0);
			List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
			FqSellerStatement fsstate = null;
			if (fsslist.size()>0) {
				fsstate = fsslist.get(0);
				fsstate.setTotalMoney(fsstate.getTotalMoney().add(record.getPayAmount()));
				fsstate.setTotalNum(fsstate.getTotalNum()+1);
				fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
			}else{
				fsstate = new FqSellerStatement();
				fsstate.setCreateTime(new Date());
				fsstate.setStatementDate(date0);
				fsstate.setSellerId(store.getSellerId());
				fsstate.setState(0);
				fsstate.setTotalMoney(record.getPayAmount());
				fsstate.setTotalNum(1);
				StoreInfo storeinfo = storeService.queryStoreInfoBySeller(store.getSellerId());
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date0);
				calendar.add(Calendar.DATE, storeinfo.getStatementPeriod());
				fsstate.setPeriodDate(calendar.getTime());
				fqSellerStatementMapper.insertSelective(fsstate);
			}
		}else{
			FqStoreCreditExample example3 = new FqStoreCreditExample();
			example3.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
			example3.setOrderByClause("period desc");
			List<FqStoreCredit> credits = fqStoreCreditMapper.selectByExample(example3);
			if (credits.size()>0) {
				FqStoreCredit credit = credits.get(0);
				BigDecimal noRevert = credit.getNoRevert().subtract(thirdPay.getMoney());
				if (noRevert.compareTo(BigDecimal.ZERO) == -1) {
					noRevert = BigDecimal.ZERO;
					credit.setStatus(3);
				}
				credit.setNoRevert(noRevert);
				fqStoreCreditMapper.updateByPrimaryKeySelective(credit);
			}
		}
		FqPushInfo fqPushInfo = new FqPushInfo();
		fqPushInfo.setContent("");
		fqPushInfo.setPushTime(new Date());
		fqPushInfo.setSellerId(thirdPay.getSellerId());
		fqPushInfo.setTitle("你已收到付款"+thirdPay.getMoney().toString()+"元");
		fqPushInfo.setType(1);
	    int a = fqPushInfoService.insertFqPushInfo(fqPushInfo);
	    logger.info("---------save--pushinfo-------"+a);
		Map<String,Object> custom = new HashMap<String,Object>(); 
		 custom.put("type",1);
		 MessageIOS messageIos = new MessageIOS();
		 messageIos.setExpireTime(60);
		 messageIos.setAlert("您已收到付款"+thirdPay.getMoney().toString()+"元");
		 messageIos.setBadge(1);
		 messageIos.setSound("12 Bar Blues Bass.caf");
		 messageIos.setCustom(custom);
		 JSONObject obj = new JSONObject();
		 JSONObject aps = new JSONObject();
		 aps.put("sound", "12 Bar Blues Bass.caf"); 
		 aps.put("alert", "您已收到付款"+thirdPay.getMoney().toString()+"元");
		 aps.put("badge", 1); 
		 obj.put("aps", aps); 
		 messageIos.setRaw(obj.toString());
		 XingeService.xingeIos.pushSingleAccount(0, thirdPay.getSellerId()+"", messageIos, XingeApp.IOSENV_PROD);
		 XingeService.xingeIos.pushSingleAccount(0, thirdPay.getSellerId()+"", messageIos, XingeApp.IOSENV_DEV);
		 Message message = new Message();
		 message.setExpireTime(60);
		 message.setTitle("支付成功");
		 message.setContent("您已收到付款"+thirdPay.getMoney().toString()+"元");
		 message.setType(Message.TYPE_NOTIFICATION);
		 message.setCustom(custom);
		 ClickAction action = new ClickAction();
		 action.setActionType(ClickAction.TYPE_ACTIVITY);
		 action.setActivity("com.freechange.seller.modules.MainActivity");
		 message.setAction(action);
		XingeService.xinge.pushSingleAccount(0, thirdPay.getSellerId()+"", message);
		
		
//		CashAccountExample example1 = new CashAccountExample();
//		example1.createCriteria().andAccountIdEqualTo(store.getSellerId()).andAccountTypeEqualTo(1);
//		List<CashAccount> list2 = cashAccountMapper.selectByExample(example1);
//		if (list2 != null && list2.size() >0){
//			CashAccount account = list2.get(0); // 商家资金帐号
//			BigDecimal sbalance = account.getBalance(); // 商家账户金额
//			account.setBalance(sbalance.add(ruzhang));
//			account.setCreateDate(new Date());
//			int row = cashAccountMapper.updateByPrimaryKey(account); // 3.商家添加金额
//			CashLog log = new CashLog(); // 添加商家销售入账记录
//			
//			log.setRecordNo(record.getOrderNo());
//			log.setSendId(user.getId());
//			log.setReviewId(store.getSellerId());
//			log.setAmount(ruzhang);
//			log.setPayType(5);
//			log.setPayWay(4);
//			log.setBusinessCode(PAYCODE.B005);
//			log.setBusinessName(PAYCODE.B005N);
//			log.setReviewBefor(sbalance);
//			log.setReviewAfter(sbalance.add(ruzhang));
//			log.setCreateTime(now);
//			row += cashLogMapper.insertSelective(log);
//		}
		
		

		FqOrderDetailExample fodexample = new FqOrderDetailExample();
		fodexample.createCriteria().andOrderIdEqualTo(record.getId());
		List<FqOrderDetail> fods = fqOrderDetailMapper.selectByExample(fodexample);
		String detailinfo = "商品详情:";
		for (int i = 0; i < fods.size(); i++) {
			FqOrderDetail fod = fods.get(i);
			if (i>0) {
				detailinfo+="\n\t\t   ";
			}
			detailinfo +=fod.getProductName()+" X "+fod.getQuantity();
		}
		
		JSONObject data1 = new JSONObject();
		data1.put("touser", "ob8WbwWQWmdZoykLjOvwCLw5CRmk");
		data1.put("template_id", "jXAaCLWCcZuShw2nuHpS_SI8igVx8nZY_taD_vLnj3c");
		JSONObject data = new JSONObject();
		JSONObject params;
		String firstdata = "";
		if (!StringUtils.isEmpty(record.getDeskNo())&&record.getDeskNo().length()>0) {
			firstdata = "桌号："+record.getDeskNo()+",";
		}
		params = new JSONObject();
		params.put("value", "您好，桌号["+firstdata+"] 已点餐成功！\n\n"+detailinfo);
		params.put("color", "#000000");
		data.put("first", params);
		params = new JSONObject();
		params.put("value", record.getOrderNo());
		params.put("color", "#000000");
		data.put("keyword1", params);
		params = new JSONObject();
		params.put("value", "￥"+record.getPayAmount());
		params.put("color", "#000000");
		data.put("keyword2", params);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		params = new JSONObject();
		params.put("value", sdf.format(new Date()));
		params.put("color", "#000000");
		data.put("keyword3", params);
		
		params = new JSONObject();
		params.put("value", "\n感谢您的使用！");
		params.put("color", "#000000");
		data.put("remark", params);
		
		data1.put("data", data);
		data1.put("url", "");
		
		FqUserInfoExample example2 = new FqUserInfoExample();
		example2.createCriteria().andStoreIdEqualTo(store.getSellerId());
		List<FqUserInfo> list = fqUserInfoMapper.selectByExample(example2);
		for (int i = 0; i < list.size(); i++) {
			data1.remove("touser");
			data1.put("touser", list.get(i).getOpenId());
			WeixinUtil.sendTempMessage(data1.toJSONString());
		}
		
		
		logger.error(">>>>>>>>>>alipay>>>>>>>>订单："+record.getId()+"，回调成功");
		//更新订单成功，返回成功状态
		//response.getWriter().print("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
		return 1;
	}
}
@Override
public Map<String, String> updateAndPayOrderByWft(FqOrder order,
		HttpServletRequest request, HttpServletResponse response) throws JDOMException, IOException {
	Map<String,String> resultMap = new HashMap<String,String>();
	int a = fqOrderMapper.updateByPrimaryKeySelective(order);
	if (a <=0) {
		resultMap.put("errcode", "80000");
		return resultMap;
	}
	FqUserInfo user = fqUserInfoMapper.selectByPrimaryKey(order.getUserId());
	String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
	String notifyUrl = ConstantsConfigurer.getProperty(SystemConstant.WFT_PAY_NOTIFYURLYF);
	
	String MCH_ID = ConstantsConfigurer.getProperty(SystemConstant.WFT_MCH_ID);
	String PAY_KEY = ConstantsConfigurer.getProperty(SystemConstant.WFT_PAY_KEY);
	
	 String noncestr = Sha1Util.getNonceStr();
	 int fee = order.getPayAmount().multiply(new BigDecimal(100)).intValue();
	SortedMap<String, String> packageParams = new TreeMap<String, String>();
	packageParams.put("service","pay.weixin.jspay" );
	packageParams.put("mch_id", MCH_ID);
	packageParams.put("is_raw", "1");
	packageParams.put("out_trade_no", order.getOrderNo());
	packageParams.put("body", order.getStoreName()+"-支付");
	packageParams.put("sub_openid", user.getOpenId());
	
	packageParams.put("total_fee", String.valueOf(fee));
	packageParams.put("mch_create_ip", request.getRemoteAddr());
	packageParams.put("notify_url", notifyUrl);
	packageParams.put("nonce_str", noncestr);
	String sign = createSign(packageParams, PAY_KEY);;
	packageParams.put("sign", sign);
	String requestXML = XMLUtil.getRequestXml(packageParams);
	
	String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
	Map<String, String> notifyParamsMap =XMLUtil.doXMLParse(result);
	
	if (notifyParamsMap.get("status").equals("0")&&notifyParamsMap.get("result_code").equals("0")) {
		String jsonString = notifyParamsMap.get("pay_info");
		resultMap.put("errcode", "0000");
		resultMap.put("jsonstr", jsonString);
		return resultMap;
	}else{
		resultMap.put("errcode", "80000");
		return resultMap;
	}
	
}

@Override
public Map<String, String> updateAndPayOrderByGd(FqOrder order,
		HttpServletRequest request, HttpServletResponse response) throws JDOMException, IOException {
	Map<String,String> resultMap = new HashMap<String,String>();
	int a = fqOrderMapper.updateByPrimaryKeySelective(order);
	if (a <=0) {
		resultMap.put("errcode", "80000");
		return resultMap;
	}
	FqUserInfo user = fqUserInfoMapper.selectByPrimaryKey(order.getUserId());
	String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
	String notifyUrl = ConstantsConfigurer.getProperty(SystemConstant.GD_PAY_NOTIFYURLYF);
	
	String MCH_ID = ConstantsConfigurer.getProperty(SystemConstant.GD_MCH_ID);
	String PAY_KEY = ConstantsConfigurer.getProperty(SystemConstant.GD_PAY_KEY);
	
	 String noncestr = Sha1Util.getNonceStr();
	 int fee = order.getPayAmount().multiply(new BigDecimal(100)).intValue();
	SortedMap<String, String> packageParams = new TreeMap<String, String>();
	packageParams.put("service","pay.weixin.jspay" );
	packageParams.put("mch_id", MCH_ID);
	packageParams.put("is_raw", "1");
	packageParams.put("out_trade_no", order.getOrderNo());
	packageParams.put("body", order.getStoreName()+"-支付");
	packageParams.put("sub_openid", user.getOpenId());
	
	packageParams.put("total_fee", String.valueOf(fee));
	packageParams.put("mch_create_ip", request.getRemoteAddr());
	packageParams.put("notify_url", notifyUrl);
	packageParams.put("nonce_str", noncestr);
	String sign = createSign(packageParams, PAY_KEY);;
	packageParams.put("sign", sign);
	String requestXML = XMLUtil.getRequestXml(packageParams);
	
	String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
	Map<String, String> notifyParamsMap =XMLUtil.doXMLParse(result);
	
	if (notifyParamsMap.get("status").equals("0")&&notifyParamsMap.get("result_code").equals("0")) {
		String jsonString = notifyParamsMap.get("pay_info");
		resultMap.put("errcode", "0000");
		resultMap.put("jsonstr", jsonString);
		return resultMap;
	}else{
		resultMap.put("errcode", "80000");
		return resultMap;
	}
	
}
@Override
public Map<String, String> updateAndPayOrderByPf(FqOrder order,
		HttpServletRequest request, HttpServletResponse response) throws JDOMException, IOException {
	Map<String,String> resultMap = new HashMap<String,String>();
	int a = fqOrderMapper.updateByPrimaryKeySelective(order);
	if (a <=0) {
		resultMap.put("errcode", "80000");
		return resultMap;
	}
	FqUserInfo user = fqUserInfoMapper.selectByPrimaryKey(order.getUserId());
	String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
	String notifyUrl = ConstantsConfigurer.getProperty(SystemConstant.PF_PAY_NOTIFYURLYF);
	 String MCH_ID = ConstantsConfigurer.getProperty(SystemConstant.PF_MCH_ID);
	 String PAY_KEY = ConstantsConfigurer.getProperty(SystemConstant.PF_PAY_KEY);
	
	String noncestr = Sha1Util.getNonceStr();
	int fee = order.getPayAmount().multiply(new BigDecimal(100)).intValue();
	SortedMap<String, String> packageParams = new TreeMap<String, String>();
	packageParams.put("service","pay.weixin.jspay" );
	packageParams.put("mch_id", MCH_ID);
	packageParams.put("is_raw", "1");
	packageParams.put("out_trade_no", order.getOrderNo());
	packageParams.put("body", order.getStoreName()+"-支付");
	packageParams.put("sub_openid", user.getOpenId());
	
	packageParams.put("total_fee", String.valueOf(fee));
	packageParams.put("mch_create_ip", request.getRemoteAddr());
	packageParams.put("notify_url", notifyUrl);
	packageParams.put("nonce_str", noncestr);
	String sign = createSign(packageParams, PAY_KEY);
	packageParams.put("sign", sign);
	String requestXML = XMLUtil.getRequestXml(packageParams);
	
	String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
	Map<String, String> notifyParamsMap =XMLUtil.doXMLParse(result);
	logger.info(">>>>>>>>>>>>>>>>>>>>浦发WX下单>>>>>"+result);
	if (notifyParamsMap.get("status").equals("0")&&notifyParamsMap.get("result_code").equals("0")) {
		String jsonString = notifyParamsMap.get("pay_info");
		resultMap.put("errcode", "0000");
		resultMap.put("jsonstr", jsonString);
		return resultMap;
	}else{
		resultMap.put("errcode", "80000");
		return resultMap;
	}
	
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
public Integer updateWftPay(Map<String, String> notifyParamsMap,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
	try{
		response.setContentType("text/html;charset=UTF-8");
		if(notifyParamsMap == null){
			  logger.info(">>>>>>>>>>>>>>>>>>微信支付回调 参数 is null");
             // response.getWriter().print("fail");
              return 0;
		}
		logger.info(">>>>>>>>>>>>>>>>>>微信支付回调 参数" + notifyParamsMap.toString());
		
		if (!"0".equals(notifyParamsMap.get("status"))) {
			logger.info(">>>>>>>>>>>>>>>>>>微信支付回调，通信异常："+notifyParamsMap.toString());
			//response.getWriter().print("fail");
			return 0;
		}
		if (!"0".equals(notifyParamsMap.get("result_code"))) {
			logger.info(">>>>>>>>>>>>>>>>>>微信支付回调失败:"+notifyParamsMap.toString());
			//response.getWriter().print("fail");
			return 0;
		}
		//微信支付回调通知验签
		if(isWftSign(notifyParamsMap)){
			
			//回调成功 -处理业务状态
			if ("0".equals(notifyParamsMap.get("status")) && "0".equals(notifyParamsMap.get("result_code"))&&"0".equals(notifyParamsMap.get("pay_result"))) {
				String tradeNo = notifyParamsMap.get("out_trade_no");		
				String wftOrderNo =notifyParamsMap.get("transaction_id") ;			
				String payTime = notifyParamsMap.get("time_end");				//支付时间
				String is_subscribe = notifyParamsMap.get("is_subscribe");				//支付时间
				
				FqOrderExample example = new FqOrderExample();
				example.createCriteria().andOrderNoEqualTo(tradeNo);
				List<FqOrder> recordlist  = fqOrderMapper.selectByExample(example);
				
				if(recordlist == null || recordlist.size() == 0){
					logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"不存在");
					//response.getWriter().print("fail");
					return 0;
				}
				FqOrder record =recordlist.get(0);
				if(!SystemConstant.PAY_WAITPAY.equals(record.getStatus())){
					logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"己支付");
					//response.getWriter().print("fail");
					return 1;
				}else{
					
					//更新订单  
					if (record.getPayType() == 1) {
						record.setStatus(2);
					}else{
						record.setStatus(1);
					}
					record.setWxOrderNo(wftOrderNo);
					Date now = new Date();
					fqOrderMapper.updateByPrimaryKeySelective(record);
					
					
					FqStore store = fqStoreMapper.selectByPrimaryKey(record.getStoreId());
					FqUserInfo user =fqUserInfoMapper.selectByPrimaryKey(record.getUserId());
					SellerInfo seller = sellerService.getSellerById(store.getSellerId());
					

					FqThirdPay thirdPay = new  FqThirdPay();
					thirdPay.setMoney(record.getPayAmount());
					thirdPay.setOrderId(record.getId());
					thirdPay.setOrderNo(record.getOrderNo());
					thirdPay.setPayTime(now);
					thirdPay.setSort(2);
					thirdPay.setThirdOrderNo(wftOrderNo);
					thirdPay.setType(3);
					thirdPay.setSellerId(store.getSellerId());
					thirdPay.setTotamt(record.getTotalAmount());
					thirdPay.setUserId(user.getId());
					thirdPay.setOpenId(user.getOpenId());
					fqThirdPayMapper.insertSelective(thirdPay);
					

					if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
						Date date0 = BaseUtil.getTimeDate(record.getCreateTime());
						FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
						fqSellerStatementExample.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatementDateEqualTo(date0);
						List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
						FqSellerStatement fsstate = null;
						if (fsslist.size()>0) {
							fsstate = fsslist.get(0);
							fsstate.setTotalMoney(fsstate.getTotalMoney().add(record.getPayAmount()));
							fsstate.setTotalNum(fsstate.getTotalNum()+1);
							fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
						}else{
							fsstate = new FqSellerStatement();
							fsstate.setCreateTime(new Date());
							fsstate.setStatementDate(date0);
							fsstate.setSellerId(store.getSellerId());
							fsstate.setState(0);
							fsstate.setTotalMoney(record.getPayAmount());
							fsstate.setTotalNum(1);
							StoreInfo storeinfo = storeService.queryStoreInfoBySeller(store.getSellerId());
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(date0);
							calendar.add(Calendar.DATE, storeinfo.getStatementPeriod());
							fsstate.setPeriodDate(calendar.getTime());
							fqSellerStatementMapper.insertSelective(fsstate);
						}
					}else{
						FqStoreCreditExample example3 = new FqStoreCreditExample();
						example3.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
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
					
					FqPushInfo fqPushInfo = new FqPushInfo();
					fqPushInfo.setContent("");
					fqPushInfo.setPushTime(new Date());
					fqPushInfo.setSellerId(thirdPay.getSellerId());
					fqPushInfo.setTitle("你已收到付款"+thirdPay.getMoney().toString()+"元");
					fqPushInfo.setType(1);
				    int a = fqPushInfoService.insertFqPushInfo(fqPushInfo);
				    logger.info("---------save--pushinfo-------"+a);
					Map<String,Object> custom = new HashMap<String,Object>(); 
					 custom.put("type",1);
					 MessageIOS messageIos = new MessageIOS();
					 messageIos.setExpireTime(60);
					 messageIos.setAlert("您已收到付款"+thirdPay.getMoney().toString()+"元");
					 messageIos.setBadge(1);
					 messageIos.setSound("12 Bar Blues Bass.caf");
					 messageIos.setCustom(custom);
					 JSONObject obj = new JSONObject();
					 JSONObject aps = new JSONObject();
					 aps.put("sound", "12 Bar Blues Bass.caf"); 
					 aps.put("alert", "您已收到付款"+thirdPay.getMoney().toString()+"元");
					 aps.put("badge", 1); 
					 obj.put("aps", aps); 
					 messageIos.setRaw(obj.toString());
					 XingeService.xingeIos.pushSingleAccount(0, thirdPay.getSellerId()+"", messageIos, XingeApp.IOSENV_PROD);
					 XingeService.xingeIos.pushSingleAccount(0, thirdPay.getSellerId()+"", messageIos, XingeApp.IOSENV_DEV);
					 Message message = new Message();
					 message.setExpireTime(60);
					 message.setTitle("支付成功");
					 message.setContent("您已收到付款"+thirdPay.getMoney().toString()+"元");
					 message.setType(Message.TYPE_NOTIFICATION);
					 message.setCustom(custom);
					 ClickAction action = new ClickAction();
					 action.setActionType(ClickAction.TYPE_ACTIVITY);
					 action.setActivity("com.freechange.seller.modules.MainActivity");
					 message.setAction(action);
					XingeService.xinge.pushSingleAccount(0, thirdPay.getSellerId()+"", message);
					
					
//					CashAccountExample example1 = new CashAccountExample();
//					example1.createCriteria().andAccountIdEqualTo(store.getSellerId()).andAccountTypeEqualTo(1);
//					List<CashAccount> list2 = cashAccountMapper.selectByExample(example1);
//					if (list2 != null && list2.size() >0){
//						CashAccount account = list2.get(0); // 商家资金帐号
//						BigDecimal sbalance = account.getBalance(); // 商家账户金额
//						account.setBalance(sbalance.add(record.getPayAmount()));
//						account.setCreateDate(new Date());
//						int row = cashAccountMapper.updateByPrimaryKey(account); // 3.商家添加金额
//						CashLog log = new CashLog(); // 添加商家销售入账记录
//						log.setRecordNo(record.getOrderNo());
//						log.setSendId(user.getId());
//						log.setReviewId(store.getSellerId());
//						log.setAmount(record.getPayAmount());
//						log.setPayType(5);
//						log.setPayWay(4);
//						log.setBusinessCode(PAYCODE.B005);
//						log.setBusinessName(PAYCODE.B005N);
//						log.setReviewBefor(sbalance);
//						log.setReviewAfter(sbalance.add(record.getPayAmount()));
//						log.setCreateTime(now);
//						row += cashLogMapper.insertSelective(log);
//					}
//					
					
					

					FqOrderDetailExample fodexample = new FqOrderDetailExample();
					fodexample.createCriteria().andOrderIdEqualTo(record.getId());
					List<FqOrderDetail> fods = fqOrderDetailMapper.selectByExample(fodexample);
					String detailinfo = "商品详情:";
					for (int i = 0; i < fods.size(); i++) {
						FqOrderDetail fod = fods.get(i);
						if (i>0) {
							detailinfo+="\n\t\t   ";
						}
						detailinfo +=fod.getProductName()+" X "+fod.getQuantity();
					}
					
					JSONObject data1 = new JSONObject();
					data1.put("touser", "ob8WbwWQWmdZoykLjOvwCLw5CRmk");
					data1.put("template_id", "jXAaCLWCcZuShw2nuHpS_SI8igVx8nZY_taD_vLnj3c");
					JSONObject data = new JSONObject();
					JSONObject params;
					String firstdata = "";
					if (!StringUtils.isEmpty(record.getDeskNo())&&record.getDeskNo().length()>0) {
						firstdata = "桌号："+record.getDeskNo()+",";
					}
					params = new JSONObject();
					params.put("value", "您好，桌号["+firstdata+"] 已点餐成功！\n\n"+detailinfo);
					params.put("color", "#000000");
					data.put("first", params);
					params = new JSONObject();
					params.put("value", record.getOrderNo());
					params.put("color", "#000000");
					data.put("keyword1", params);
					params = new JSONObject();
					params.put("value", "￥"+record.getPayAmount());
					params.put("color", "#000000");
					data.put("keyword2", params);
					
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					params = new JSONObject();
					params.put("value", sdf.format(new Date()));
					params.put("color", "#000000");
					data.put("keyword3", params);
					
					params = new JSONObject();
					params.put("value", "\n感谢您的使用！");
					params.put("color", "#000000");
					data.put("remark", params);
					
					data1.put("data", data);
					data1.put("url", "");
					
					FqUserInfoExample example2 = new FqUserInfoExample();
					example2.createCriteria().andStoreIdEqualTo(store.getSellerId());
					List<FqUserInfo> list = fqUserInfoMapper.selectByExample(example2);
					for (int i = 0; i < list.size(); i++) {
						data1.remove("touser");
						data1.put("touser", list.get(i).getOpenId());
						WeixinUtil.sendTempMessage(data1.toJSONString());
					}
//					logger.info("-----store.getId().toString()-----"+store.getId().toString());
//					logger.info("-----store.getStoreName()-----"+store.getStoreName());
//					logger.info("-----now-----"+now);
//					FqRedpackExample example4 = new FqRedpackExample();
//					example4.createCriteria().andStatusEqualTo(1).andStoreIdsLike("%"+store.getId().toString()+"%").andStoreNamesLike("%"+store.getStoreName()+"%")
//					.andBeginDateLessThanOrEqualTo(now).andEndDateGreaterThanOrEqualTo(now);
//					example4.setOrderByClause("create_time desc");
//					List<FqRedpack> list3 = fqRedpackMapper.selectByExample(example4);
//					logger.info("-----List<FqRedpack> list3-----"+list3.size());
//					if (list3.size()>0) {
//						logger.info("-----list3.get(0).getId()-----"+list3.get(0).getId());
//						FqRedpackRecordExample frrExample = new FqRedpackRecordExample();
//						frrExample.createCriteria().andOpenIdEqualTo(user.getOpenId()).andRedpackIdEqualTo(list3.get(0).getId()).andCreateTimeBetween(new Date(now.getTime()-now.getTime()%86400000 - 28800000), new Date(now.getTime()-now.getTime()%86400000 + 57600000));
//						int rpnum = fqRedpackRecordMapper.countByExample(frrExample);
//						logger.info("-----rpnum----"+rpnum);
//						if (rpnum<list3.get(0).getDaliyNum()) {
//							
//						Long minit = (now.getTime()+28800000)%86400000/1000/60;
//						logger.info("-----minit----"+minit);
//						FqRedpackTimeExample example5 = new FqRedpackTimeExample();
//						example5.createCriteria().andBeginTimeLessThanOrEqualTo(minit).andEndTimeGreaterThanOrEqualTo(minit).andRedpackIdEqualTo(list3.get(0).getId());
//						List<FqRedpackTime> list4 = fqRedpackTimeMapper.selectByExample(example5);
//						logger.info("-----list4.size()----"+list4.size());
//						if (list4.size()>0) {
//							FqRedpackDetailExample example6 = new FqRedpackDetailExample();
//							example6.createCriteria().andRedpackIdEqualTo(list3.get(0).getId());
//							example6.setOrderByClause("id");
//							List<FqRedpackDetail> list5 = fqRedpackDetailMapper.selectByExample(example6);
//							logger.info("-----list5.size()----"+list5.size());
//							if (list5.size()>0) {
//								BigDecimal rpm = BigDecimal.ZERO;
//								if (list5.get(0).getType() == 1) {
//									Integer suiji =  (Integer) redisService.getValueByKey("store"+store.getId());
//									if (suiji == null||suiji > 10) {
//										suiji = 1;
//									}
//									int num = 0;
//									for (int i = 0; i < list5.size(); i++) {
//										FqRedpackDetail frd = list5.get(i);
//										num += frd.getProbability()/10;
//										if (num>=suiji) {
//											int a = (int)(Math.random()*(frd.getMaxAmount().subtract(frd.getMinAmount()).multiply(new BigDecimal(100)).intValue()+1));
//											logger.info("-----a---"+a);
//											logger.info("-----chazhi---"+frd.getMaxAmount().subtract(frd.getMinAmount()).multiply(new BigDecimal(100)).intValue()+1);
//											rpm = new BigDecimal(a).divide(new BigDecimal(100)).add(frd.getMinAmount());
//											logger.info("-----new BigDecimal(a).divide(new BigDecimal(100))---"+new BigDecimal(a).divide(new BigDecimal(100)));
//											break;
//										}
//									}
//									if (suiji >= 10) {
//										suiji = 0;
//									}
//									logger.info("-----suiji---"+suiji);
//
//									redisService.setValueByKey("store"+store.getId(), suiji+1);	
//								}else{
//									for (int i = 0; i < list5.size(); i++) {
//										FqRedpackDetail frd = list5.get(i);
//										if (frd.getMaxMoney().compareTo(record.getPayAmount())== 1 && frd.getMinMoney().compareTo(record.getPayAmount())<1) {
//											int a = (int)(Math.random()*(frd.getMaxAmount().subtract(frd.getMinAmount()).multiply(new BigDecimal(100)).intValue()+1));
//											rpm = new BigDecimal(a).divide(new BigDecimal(100)).add(frd.getMinAmount());
//											logger.info("-----1rpm---"+rpm);
//											break;
//										}
//									}
//								}
//								logger.info("-----rpm---"+rpm);
//								if (rpm.compareTo(BigDecimal.ZERO)==1) {
//									FqRedpackRecord frr = new FqRedpackRecord();
//									frr.setConsumeMoney(record.getPayAmount());
//									frr.setCreateTime(now);
//									frr.setOrderId(record.getId()*10+1);
//									frr.setRedpackId(list3.get(0).getId());
//									frr.setRedpackMoney(rpm);
//									frr.setStatus(0);
//									frr.setStoreId(store.getId());
//									frr.setStoreName(store.getStoreName());
//									frr.setOpenId(user.getOpenId());
//									fqRedpackRecordMapper.insertSelective(frr);
//									
//									JSONObject data2 = new JSONObject();
//									data2.put("touser", user.getOpenId());
//									data2.put("msgtype", "news");
//									JSONArray articles = new JSONArray();
//									JSONObject article = new JSONObject();
//									article.put("title", "支付好礼");
//									article.put("url", "http://"+ConstantsConfigurer.getProperty("web_url")+"/user/fqRedpack/index.do?rpid="+frr.getId());
//									article.put("picurl", "http://"+ConstantsConfigurer.getProperty("web_url")+"/images/feiquan320.png");
//									articles.add(article);
//									JSONObject news = new JSONObject();
//									news.put("articles", articles);
//									data2.put("news", news);
//									JSONObject result = WeixinUtil.sendCustomMessage(data2.toJSONString());
//									logger.info("-----result---"+result);
//								}
//							}
//						}
//						}
//					}
					
					logger.error(">>>>>>>>>>>>>>>>>>订单："+record.getId()+"，回调成功");
					//更新订单成功，返回成功状态
					//response.getWriter().print("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
					return 1;
				}
			}
		}else{
			logger.error(">>>>>>>>>>>>>>>>>>回调验签失败notifyParamsMap："+notifyParamsMap.toString());
			return 0;
		}
		return 0;
	}catch(Exception e){
		logger.error("updateCheckNotify error", e);
		throw e;
	}
}

@Override
public Integer updateGdPay(Map<String, String> notifyParamsMap,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	try{
		response.setContentType("text/html;charset=UTF-8");
		if(notifyParamsMap == null){
			logger.info(">>>>>>>>>>>>>>>>>>微信支付回调 参数 is null");
			// response.getWriter().print("fail");
			return 0;
		}
		logger.info(">>>>>>>>>>>>>>>>>>微信支付回调 参数" + notifyParamsMap.toString());
		
		if (!"0".equals(notifyParamsMap.get("status"))) {
			logger.info(">>>>>>>>>>>>>>>>>>微信支付回调，通信异常："+notifyParamsMap.toString());
			//response.getWriter().print("fail");
			return 0;
		}
		if (!"0".equals(notifyParamsMap.get("result_code"))) {
			logger.info(">>>>>>>>>>>>>>>>>>微信支付回调失败:"+notifyParamsMap.toString());
			//response.getWriter().print("fail");
			return 0;
		}
		//微信支付回调通知验签
		if(isGdSign(notifyParamsMap)){
			
			//回调成功 -处理业务状态
			if ("0".equals(notifyParamsMap.get("status")) && "0".equals(notifyParamsMap.get("result_code"))&&"0".equals(notifyParamsMap.get("pay_result"))) {
				String tradeNo = notifyParamsMap.get("out_trade_no");		
				String wftOrderNo =notifyParamsMap.get("transaction_id") ;			
				String payTime = notifyParamsMap.get("time_end");				//支付时间
				String is_subscribe = notifyParamsMap.get("is_subscribe");				//支付时间
				String bankTypeCode = notifyParamsMap.get("bank_type");
				FqOrderExample example = new FqOrderExample();
				example.createCriteria().andOrderNoEqualTo(tradeNo);
				List<FqOrder> recordlist  = fqOrderMapper.selectByExample(example);
				
				if(recordlist == null || recordlist.size() == 0){
					logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"不存在");
					//response.getWriter().print("fail");
					return 0;
				}
				FqOrder record =recordlist.get(0);
				if(!SystemConstant.PAY_WAITPAY.equals(record.getStatus())){
					logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"己支付");
					//response.getWriter().print("fail");
					return 1;
				}else{
					
					//更新订单  
					if (record.getPayType() == 1) {
						record.setStatus(2);
					}else{
						record.setStatus(1);
					}
					record.setWxOrderNo(wftOrderNo);
					Date now = new Date();
					fqOrderMapper.updateByPrimaryKeySelective(record);
					
					
					FqStore store = fqStoreMapper.selectByPrimaryKey(record.getStoreId());
					FqUserInfo user =fqUserInfoMapper.selectByPrimaryKey(record.getUserId());
					SellerInfo seller = sellerService.getSellerById(store.getSellerId());
					
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
					FqThirdPay thirdPay = new  FqThirdPay();
					thirdPay.setMoney(record.getPayAmount());
					thirdPay.setOrderId(record.getId());
					thirdPay.setOrderNo(record.getOrderNo());
					thirdPay.setPayTime(now);
					thirdPay.setSort(2);
					thirdPay.setThirdOrderNo(wftOrderNo);
					thirdPay.setType(3);
					thirdPay.setSellerId(store.getSellerId());
					thirdPay.setTotamt(record.getTotalAmount());
					thirdPay.setUserId(user.getId());
					thirdPay.setOpenId(user.getOpenId());
					thirdPay.setBankType(bankType);
					thirdPay.setBankTypeCode(bankTypeCode);
					fqThirdPayMapper.insertSelective(thirdPay);
					
					
					if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
						Date date0 = BaseUtil.getTimeDate(record.getCreateTime());
						FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
						fqSellerStatementExample.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatementDateEqualTo(date0);
						List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
						FqSellerStatement fsstate = null;
						if (fsslist.size()>0) {
							fsstate = fsslist.get(0);
							fsstate.setTotalMoney(fsstate.getTotalMoney().add(record.getPayAmount()));
							fsstate.setTotalNum(fsstate.getTotalNum()+1);
							fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
						}else{
							fsstate = new FqSellerStatement();
							fsstate.setCreateTime(new Date());
							fsstate.setStatementDate(date0);
							fsstate.setSellerId(store.getSellerId());
							fsstate.setState(0);
							fsstate.setTotalMoney(record.getPayAmount());
							fsstate.setTotalNum(1);
							StoreInfo storeinfo = storeService.queryStoreInfoBySeller(store.getSellerId());
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(date0);
							calendar.add(Calendar.DATE, storeinfo.getStatementPeriod());
							fsstate.setPeriodDate(calendar.getTime());
							fqSellerStatementMapper.insertSelective(fsstate);
						}
					}else{
						FqStoreCreditExample example3 = new FqStoreCreditExample();
						example3.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
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
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					FqOrderDetailExample fodexample = new FqOrderDetailExample();
					fodexample.createCriteria().andOrderIdEqualTo(record.getId());
					List<FqOrderDetail> fods = fqOrderDetailMapper.selectByExample(fodexample);
					String detailinfo = "商品详情:";
					for (int i = 0; i < fods.size(); i++) {
						FqOrderDetail fod = fods.get(i);
						if (i>0) {
							detailinfo+="\n\t\t   ";
						}
						detailinfo +=fod.getProductName()+" X "+fod.getQuantity();
					}
					
					
					String pushInfoCount = "桌号["+record.getDeskNo()+"]已点餐成功!\n\n";
					pushInfoCount+=detailinfo;
					pushInfoCount+="\n订单号:"+ record.getOrderNo();
					pushInfoCount+="\n订单金额:￥"+record.getPayAmount();
					pushInfoCount+="\n下单时间:￥"+sdf.format(record.getCreateTime());
					
					FqPushInfo fqPushInfo = new FqPushInfo();
					fqPushInfo.setContent(pushInfoCount);
					fqPushInfo.setPushTime(new Date());
					fqPushInfo.setSellerId(thirdPay.getSellerId());
					fqPushInfo.setTitle("桌号["+record.getDeskNo()+"]已点餐成功,你已收到付款"+thirdPay.getMoney().toString()+"元");
					fqPushInfo.setType(3);
					int a = fqPushInfoService.insertFqPushInfo(fqPushInfo);
					logger.info("---------save--pushinfo-------"+a);
					Map<String,Object> custom = new HashMap<String,Object>(); 
					custom.put("type",3);
					MessageIOS messageIos = new MessageIOS();
					messageIos.setExpireTime(60);
					messageIos.setAlert("桌号["+record.getDeskNo()+"]已点餐成功,你已收到付款"+thirdPay.getMoney().toString()+"元");
					messageIos.setBadge(1);
					messageIos.setSound("diancan.caf");
					messageIos.setCustom(custom);
					JSONObject obj = new JSONObject();
					JSONObject aps = new JSONObject();
					aps.put("sound", "diancan.caf"); 
					aps.put("alert", "桌号["+record.getDeskNo()+"]已点餐成功,你已收到付款"+thirdPay.getMoney().toString()+"元");
					aps.put("badge", 1); 
					obj.put("aps", aps); 
					obj.put("type", "3"); 
					messageIos.setRaw(obj.toString());
					XingeService.xingeIos.pushSingleAccount(0, thirdPay.getSellerId()+"", messageIos, XingeApp.IOSENV_PROD);
					XingeService.xingeIos.pushSingleAccount(0, thirdPay.getSellerId()+"", messageIos, XingeApp.IOSENV_DEV);
					Message message = new Message();
					message.setExpireTime(60);
					message.setTitle("桌号["+record.getDeskNo()+"]已点餐成功,你已收到付款"+thirdPay.getMoney().toString()+"元");
					message.setContent(pushInfoCount);
					message.setType(Message.TYPE_NOTIFICATION);
					message.setCustom(custom);
					ClickAction action = new ClickAction();
					action.setActionType(ClickAction.TYPE_ACTIVITY);
					action.setActivity("com.freechange.seller.modules.MainActivity");
					message.setAction(action);
					XingeService.xinge.pushSingleAccount(0, thirdPay.getSellerId()+"", message);
					
					
//					CashAccountExample example1 = new CashAccountExample();
//					example1.createCriteria().andAccountIdEqualTo(store.getSellerId()).andAccountTypeEqualTo(1);
//					List<CashAccount> list2 = cashAccountMapper.selectByExample(example1);
//					if (list2 != null && list2.size() >0){
//						CashAccount account = list2.get(0); // 商家资金帐号
//						BigDecimal sbalance = account.getBalance(); // 商家账户金额
//						account.setBalance(sbalance.add(record.getPayAmount()));
//						account.setCreateDate(new Date());
//						int row = cashAccountMapper.updateByPrimaryKey(account); // 3.商家添加金额
//						CashLog log = new CashLog(); // 添加商家销售入账记录
//						log.setRecordNo(record.getOrderNo());
//						log.setSendId(user.getId());
//						log.setReviewId(store.getSellerId());
//						log.setAmount(record.getPayAmount());
//						log.setPayType(5);
//						log.setPayWay(4);
//						log.setBusinessCode(PAYCODE.B005);
//						log.setBusinessName(PAYCODE.B005N);
//						log.setReviewBefor(sbalance);
//						log.setReviewAfter(sbalance.add(record.getPayAmount()));
//						log.setCreateTime(now);
//						row += cashLogMapper.insertSelective(log);
//					}
//					
					
					
					
					
					
					JSONObject data1 = new JSONObject();
					data1.put("touser", "ob8WbwWQWmdZoykLjOvwCLw5CRmk");
					data1.put("template_id", "jXAaCLWCcZuShw2nuHpS_SI8igVx8nZY_taD_vLnj3c");
					JSONObject data = new JSONObject();
					JSONObject params;
					
					params = new JSONObject();
					params.put("value", "您好，桌号["+record.getDeskNo()+"] 已点餐成功！\n\n"+detailinfo);
					params.put("color", "#000000");
					data.put("first", params);
					params = new JSONObject();
					params.put("value", record.getOrderNo());
					params.put("color", "#000000");
					data.put("keyword1", params);
					params = new JSONObject();
					params.put("value", "￥"+record.getPayAmount());
					params.put("color", "#000000");
					data.put("keyword2", params);
					
					
					params = new JSONObject();
					params.put("value", sdf.format(record.getCreateTime()));
					params.put("color", "#000000");
					data.put("keyword3", params);
					
					params = new JSONObject();
					params.put("value", "\n感谢您的使用！");
					params.put("color", "#000000");
					data.put("remark", params);
					
					data1.put("data", data);
					data1.put("url", "");
					
					FqUserInfoExample example2 = new FqUserInfoExample();
					example2.createCriteria().andStoreIdEqualTo(store.getSellerId());
					List<FqUserInfo> list = fqUserInfoMapper.selectByExample(example2);
					for (int i = 0; i < list.size(); i++) {
						data1.remove("touser");
						data1.put("touser", list.get(i).getOpenId());
						WeixinUtil.sendTempMessage(data1.toJSONString());
					}
//					logger.info("-----store.getId().toString()-----"+store.getId().toString());
//					logger.info("-----store.getStoreName()-----"+store.getStoreName());
//					logger.info("-----now-----"+now);
//					FqRedpackExample example4 = new FqRedpackExample();
//					example4.createCriteria().andStatusEqualTo(1).andStoreIdsLike("%"+store.getId().toString()+"%").andStoreNamesLike("%"+store.getStoreName()+"%")
//					.andBeginDateLessThanOrEqualTo(now).andEndDateGreaterThanOrEqualTo(now);
//					example4.setOrderByClause("create_time desc");
//					List<FqRedpack> list3 = fqRedpackMapper.selectByExample(example4);
//					logger.info("-----List<FqRedpack> list3-----"+list3.size());
//					if (list3.size()>0) {
//						logger.info("-----list3.get(0).getId()-----"+list3.get(0).getId());
//						FqRedpackRecordExample frrExample = new FqRedpackRecordExample();
//						frrExample.createCriteria().andOpenIdEqualTo(user.getOpenId()).andRedpackIdEqualTo(list3.get(0).getId()).andCreateTimeBetween(new Date(now.getTime()-now.getTime()%86400000 - 28800000), new Date(now.getTime()-now.getTime()%86400000 + 57600000));
//						int rpnum = fqRedpackRecordMapper.countByExample(frrExample);
//						logger.info("-----rpnum----"+rpnum);
//						if (rpnum<list3.get(0).getDaliyNum()) {
//							
//						Long minit = (now.getTime()+28800000)%86400000/1000/60;
//						logger.info("-----minit----"+minit);
//						FqRedpackTimeExample example5 = new FqRedpackTimeExample();
//						example5.createCriteria().andBeginTimeLessThanOrEqualTo(minit).andEndTimeGreaterThanOrEqualTo(minit).andRedpackIdEqualTo(list3.get(0).getId());
//						List<FqRedpackTime> list4 = fqRedpackTimeMapper.selectByExample(example5);
//						logger.info("-----list4.size()----"+list4.size());
//						if (list4.size()>0) {
//							FqRedpackDetailExample example6 = new FqRedpackDetailExample();
//							example6.createCriteria().andRedpackIdEqualTo(list3.get(0).getId());
//							example6.setOrderByClause("id");
//							List<FqRedpackDetail> list5 = fqRedpackDetailMapper.selectByExample(example6);
//							logger.info("-----list5.size()----"+list5.size());
//							if (list5.size()>0) {
//								BigDecimal rpm = BigDecimal.ZERO;
//								if (list5.get(0).getType() == 1) {
//									Integer suiji =  (Integer) redisService.getValueByKey("store"+store.getId());
//									if (suiji == null||suiji > 10) {
//										suiji = 1;
//									}
//									int num = 0;
//									for (int i = 0; i < list5.size(); i++) {
//										FqRedpackDetail frd = list5.get(i);
//										num += frd.getProbability()/10;
//										if (num>=suiji) {
//											int a = (int)(Math.random()*(frd.getMaxAmount().subtract(frd.getMinAmount()).multiply(new BigDecimal(100)).intValue()+1));
//											logger.info("-----a---"+a);
//											logger.info("-----chazhi---"+frd.getMaxAmount().subtract(frd.getMinAmount()).multiply(new BigDecimal(100)).intValue()+1);
//											rpm = new BigDecimal(a).divide(new BigDecimal(100)).add(frd.getMinAmount());
//											logger.info("-----new BigDecimal(a).divide(new BigDecimal(100))---"+new BigDecimal(a).divide(new BigDecimal(100)));
//											break;
//										}
//									}
//									if (suiji >= 10) {
//										suiji = 0;
//									}
//									logger.info("-----suiji---"+suiji);
//
//									redisService.setValueByKey("store"+store.getId(), suiji+1);	
//								}else{
//									for (int i = 0; i < list5.size(); i++) {
//										FqRedpackDetail frd = list5.get(i);
//										if (frd.getMaxMoney().compareTo(record.getPayAmount())== 1 && frd.getMinMoney().compareTo(record.getPayAmount())<1) {
//											int a = (int)(Math.random()*(frd.getMaxAmount().subtract(frd.getMinAmount()).multiply(new BigDecimal(100)).intValue()+1));
//											rpm = new BigDecimal(a).divide(new BigDecimal(100)).add(frd.getMinAmount());
//											logger.info("-----1rpm---"+rpm);
//											break;
//										}
//									}
//								}
//								logger.info("-----rpm---"+rpm);
//								if (rpm.compareTo(BigDecimal.ZERO)==1) {
//									FqRedpackRecord frr = new FqRedpackRecord();
//									frr.setConsumeMoney(record.getPayAmount());
//									frr.setCreateTime(now);
//									frr.setOrderId(record.getId()*10+1);
//									frr.setRedpackId(list3.get(0).getId());
//									frr.setRedpackMoney(rpm);
//									frr.setStatus(0);
//									frr.setStoreId(store.getId());
//									frr.setStoreName(store.getStoreName());
//									frr.setOpenId(user.getOpenId());
//									fqRedpackRecordMapper.insertSelective(frr);
//									
//									JSONObject data2 = new JSONObject();
//									data2.put("touser", user.getOpenId());
//									data2.put("msgtype", "news");
//									JSONArray articles = new JSONArray();
//									JSONObject article = new JSONObject();
//									article.put("title", "支付好礼");
//									article.put("url", "http://"+ConstantsConfigurer.getProperty("web_url")+"/user/fqRedpack/index.do?rpid="+frr.getId());
//									article.put("picurl", "http://"+ConstantsConfigurer.getProperty("web_url")+"/images/feiquan320.png");
//									articles.add(article);
//									JSONObject news = new JSONObject();
//									news.put("articles", articles);
//									data2.put("news", news);
//									JSONObject result = WeixinUtil.sendCustomMessage(data2.toJSONString());
//									logger.info("-----result---"+result);
//								}
//							}
//						}
//						}
//					}
					
					logger.error(">>>>>>>>>>>>>>>>>>订单："+record.getId()+"，回调成功");
					//更新订单成功，返回成功状态
					//response.getWriter().print("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
					return 1;
				}
			}
		}else{
			logger.error(">>>>>>>>>>>>>>>>>>回调验签失败notifyParamsMap："+notifyParamsMap.toString());
			return 0;
		}
		return 0;
	}catch(Exception e){
		logger.error("updateCheckNotify error", e);
		throw e;
	}
}

@Override
public Integer updatePfPay(Map<String, String> notifyParamsMap,
		HttpServletRequest request, HttpServletResponse response)
				throws Exception {
	try{
		response.setContentType("text/html;charset=UTF-8");
		if(notifyParamsMap == null){
			logger.info(">>>>>>>>>>>>>>>>>>点餐浦发微信支付回调 参数 is null");
			// response.getWriter().print("fail");
			return 0;
		}
		logger.info(">>>>>>>>>>>>>>>>>>点餐浦发微信支付回调 参数" + notifyParamsMap.toString());
		
		if (!"0".equals(notifyParamsMap.get("status"))) {
			logger.info(">>>>>>>>>>>>>>>>>>点餐浦发微信支付回调，通信异常："+notifyParamsMap.toString());
			//response.getWriter().print("fail");
			return 0;
		}
		if (!"0".equals(notifyParamsMap.get("result_code"))) {
			logger.info(">>>>>>>>>>>>>>>>>>点餐浦发微信支付回调失败:"+notifyParamsMap.toString());
			//response.getWriter().print("fail");
			return 0;
		}
		//微信支付回调通知验签
		if(isPfSign(notifyParamsMap)){
			
			//回调成功 -处理业务状态
			if ("0".equals(notifyParamsMap.get("status")) && "0".equals(notifyParamsMap.get("result_code"))&&"0".equals(notifyParamsMap.get("pay_result"))) {
				String tradeNo = notifyParamsMap.get("out_trade_no");		
				String wftOrderNo =notifyParamsMap.get("transaction_id") ;			
				String payTime = notifyParamsMap.get("time_end");				//支付时间
				String is_subscribe = notifyParamsMap.get("is_subscribe");				//支付时间
				String bankTypeCode = notifyParamsMap.get("bank_type");
				FqOrderExample example = new FqOrderExample();
				example.createCriteria().andOrderNoEqualTo(tradeNo);
				List<FqOrder> recordlist  = fqOrderMapper.selectByExample(example);
				
				if(recordlist == null || recordlist.size() == 0){
					logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"不存在");
					//response.getWriter().print("fail");
					return 0;
				}
				FqOrder record =recordlist.get(0);
				if(!SystemConstant.PAY_WAITPAY.equals(record.getStatus())){
					logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"己支付");
					//response.getWriter().print("fail");
					return 1;
				}else{
					
					//更新订单  
					if (record.getPayType() == 1) {
						record.setStatus(2);
					}else{
						record.setStatus(1);
					}
					record.setWxOrderNo(wftOrderNo);
					Date now = new Date();
					fqOrderMapper.updateByPrimaryKeySelective(record);
					
					
					FqStore store = fqStoreMapper.selectByPrimaryKey(record.getStoreId());
					FqUserInfo user =fqUserInfoMapper.selectByPrimaryKey(record.getUserId());
					SellerInfo seller = sellerService.getSellerById(store.getSellerId());
					
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
					FqThirdPay thirdPay = new  FqThirdPay();
					thirdPay.setMoney(record.getPayAmount());
					thirdPay.setOrderId(record.getId());
					thirdPay.setOrderNo(record.getOrderNo());
					thirdPay.setPayTime(now);
					thirdPay.setSort(2);
					thirdPay.setThirdOrderNo(wftOrderNo);
					thirdPay.setType(3);
					thirdPay.setSellerId(store.getSellerId());
					thirdPay.setTotamt(record.getTotalAmount());
					thirdPay.setUserId(user.getId());
					thirdPay.setOpenId(user.getOpenId());
					thirdPay.setBankType(bankType);
					thirdPay.setBankTypeCode(bankTypeCode);
					fqThirdPayMapper.insertSelective(thirdPay);
					
					
					if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
						Date date0 = BaseUtil.getTimeDate(record.getCreateTime());
						FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
						fqSellerStatementExample.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatementDateEqualTo(date0);
						List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
						FqSellerStatement fsstate = null;
						if (fsslist.size()>0) {
							fsstate = fsslist.get(0);
							fsstate.setTotalMoney(fsstate.getTotalMoney().add(record.getPayAmount()));
							fsstate.setTotalNum(fsstate.getTotalNum()+1);
							fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
						}else{
							fsstate = new FqSellerStatement();
							fsstate.setCreateTime(new Date());
							fsstate.setStatementDate(date0);
							fsstate.setSellerId(store.getSellerId());
							fsstate.setState(0);
							fsstate.setTotalMoney(record.getPayAmount());
							fsstate.setTotalNum(1);
							StoreInfo storeinfo = storeService.queryStoreInfoBySeller(store.getSellerId());
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(date0);
							calendar.add(Calendar.DATE, storeinfo.getStatementPeriod());
							fsstate.setPeriodDate(calendar.getTime());
							fqSellerStatementMapper.insertSelective(fsstate);
						}
					}else{
						FqStoreCreditExample example3 = new FqStoreCreditExample();
						example3.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
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
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					FqOrderDetailExample fodexample = new FqOrderDetailExample();
					fodexample.createCriteria().andOrderIdEqualTo(record.getId());
					List<FqOrderDetail> fods = fqOrderDetailMapper.selectByExample(fodexample);
					String detailinfo = "商品详情:";
					for (int i = 0; i < fods.size(); i++) {
						FqOrderDetail fod = fods.get(i);
						if (i>0) {
							detailinfo+="\n\t\t   ";
						}
						detailinfo +=fod.getProductName()+" X "+fod.getQuantity();
					}
					
					
					String pushInfoCount = "桌号["+record.getDeskNo()+"]已点餐成功!\n\n";
					pushInfoCount+=detailinfo;
					pushInfoCount+="\n订单号:"+ record.getOrderNo();
					pushInfoCount+="\n订单金额:￥"+record.getPayAmount();
					pushInfoCount+="\n下单时间:￥"+sdf.format(record.getCreateTime());
					
					FqPushInfo fqPushInfo = new FqPushInfo();
					fqPushInfo.setContent(pushInfoCount);
					fqPushInfo.setPushTime(new Date());
					fqPushInfo.setSellerId(thirdPay.getSellerId());
					fqPushInfo.setTitle("桌号["+record.getDeskNo()+"]已点餐成功,你已收到付款"+thirdPay.getMoney().toString()+"元");
					fqPushInfo.setType(3);
					int a = fqPushInfoService.insertFqPushInfo(fqPushInfo);
					logger.info("---------save--pushinfo-------"+a);
					Map<String,Object> custom = new HashMap<String,Object>(); 
					custom.put("type",3);
					MessageIOS messageIos = new MessageIOS();
					messageIos.setExpireTime(60);
					messageIos.setAlert("桌号["+record.getDeskNo()+"]已点餐成功,你已收到付款"+thirdPay.getMoney().toString()+"元");
					messageIos.setBadge(1);
					messageIos.setSound("diancan.caf");
					messageIos.setCustom(custom);
					JSONObject obj = new JSONObject();
					JSONObject aps = new JSONObject();
					aps.put("sound", "diancan.caf"); 
					aps.put("alert", "桌号["+record.getDeskNo()+"]已点餐成功,你已收到付款"+thirdPay.getMoney().toString()+"元");
					aps.put("badge", 1); 
					obj.put("aps", aps); 
					obj.put("type", "3"); 
					messageIos.setRaw(obj.toString());
					XingeService.xingeIos.pushSingleAccount(0, thirdPay.getSellerId()+"", messageIos, XingeApp.IOSENV_PROD);
					XingeService.xingeIos.pushSingleAccount(0, thirdPay.getSellerId()+"", messageIos, XingeApp.IOSENV_DEV);
					Message message = new Message();
					message.setExpireTime(60);
					message.setTitle("桌号["+record.getDeskNo()+"]已点餐成功,你已收到付款"+thirdPay.getMoney().toString()+"元");
					message.setContent(pushInfoCount);
					message.setType(Message.TYPE_NOTIFICATION);
					message.setCustom(custom);
					ClickAction action = new ClickAction();
					action.setActionType(ClickAction.TYPE_ACTIVITY);
					action.setActivity("com.freechange.seller.modules.MainActivity");
					message.setAction(action);
					XingeService.xinge.pushSingleAccount(0, thirdPay.getSellerId()+"", message);
					
					
					
					
					
					
					
					JSONObject data1 = new JSONObject();
					data1.put("touser", "ob8WbwWQWmdZoykLjOvwCLw5CRmk");
					data1.put("template_id", "jXAaCLWCcZuShw2nuHpS_SI8igVx8nZY_taD_vLnj3c");
					JSONObject data = new JSONObject();
					JSONObject params;
					
					params = new JSONObject();
					params.put("value", "您好，桌号["+record.getDeskNo()+"] 已点餐成功！\n\n"+detailinfo);
					params.put("color", "#000000");
					data.put("first", params);
					params = new JSONObject();
					params.put("value", record.getOrderNo());
					params.put("color", "#000000");
					data.put("keyword1", params);
					params = new JSONObject();
					params.put("value", "￥"+record.getPayAmount());
					params.put("color", "#000000");
					data.put("keyword2", params);
					
					
					params = new JSONObject();
					params.put("value", sdf.format(record.getCreateTime()));
					params.put("color", "#000000");
					data.put("keyword3", params);
					
					params = new JSONObject();
					params.put("value", "\n感谢您的使用！");
					params.put("color", "#000000");
					data.put("remark", params);
					
					data1.put("data", data);
					data1.put("url", "");
					
					FqUserInfoExample example2 = new FqUserInfoExample();
					example2.createCriteria().andStoreIdEqualTo(store.getSellerId());
					List<FqUserInfo> list = fqUserInfoMapper.selectByExample(example2);
					for (int i = 0; i < list.size(); i++) {
						data1.remove("touser");
						data1.put("touser", list.get(i).getOpenId());
						WeixinUtil.sendTempMessage(data1.toJSONString());
					}
					
					logger.error(">>>>>>>>>>>>>>>>>>订单："+record.getId()+"，回调成功");
					//更新订单成功，返回成功状态
					//response.getWriter().print("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
					return 1;
				}
			}
		}else{
			logger.error(">>>>>>>>>>>>>>>>>>点餐浦发回调验签失败notifyParamsMap："+notifyParamsMap.toString());
			return 0;
		}
		return 0;
	}catch(Exception e){
		logger.error("updateCheckNotify error", e);
		throw e;
	}
}
	/**
	 * QQ付款码支付 异步回调接口 进行数据处理
	 * 详解APi 地址 https://qpay.qq.com/qpaywiki/showdocument.php?pid=1&docid=4
	 * 可能订单在付款码同步支付中已经处理完毕
	 */
	public Integer updateQPay(Map<String, String> notifyParamsMap, HttpServletRequest request,HttpServletResponse response) {
		try{
			if(notifyParamsMap == null){
				logger.debug(">>>>>>>>>>>>>>>>>>QQ钱包 付款码支付异步  支付回调 参数 is null");
				return 0;
			}
			logger.debug(">>>>>>>>>>>>>>>>>>QQ钱包 付款码支付异步 回调 参数" + notifyParamsMap.toString());
			
			String API_KEY = ConstantsConfigurer.getProperty(SystemConstant.QQ_PAY_API_KEY);
			CQpayMchSpBase cQpayMchSpBase = new CQpayMchSpBase();
			TreeMap<String, String> map = new TreeMap<>();
			map.put("api_key", API_KEY);
			cQpayMchSpBase.setRequestMapAndUrl(map, "");
			
			TreeMap<String, String>  notifyParamsTreeMap = new TreeMap<>(notifyParamsMap);
			boolean signFlag = cQpayMchSpBase.checkSign(notifyParamsTreeMap);
			//支付回调通知验签
			if(signFlag){
				//回调成功 -处理业务状态
				if ("SUCCESS".equals(notifyParamsTreeMap.get("trade_state")) && notifyParamsTreeMap.get("transaction_id")!=null) {
					
					String orderNo = notifyParamsTreeMap.get("out_trade_no");		
					String CQOrderNo =notifyParamsTreeMap.get("transaction_id") ;			
					String payTime = notifyParamsTreeMap.get("time_end");				//支付完成时间
					String bankTypeCode = notifyParamsTreeMap.get("bank_type");	
					String attach  = notifyParamsTreeMap.get("attach");	         //此信息商家id 为发起支付时，原样返回
					
					Integer totalFee = Integer.parseInt(notifyParamsTreeMap.get("total_fee"));	  //商户订单总金额
					Integer cashFee = Integer.parseInt(notifyParamsTreeMap.get("cash_fee"));	  //用户本次交易中，实际支付的金额 
					String openid = notifyParamsTreeMap.get("openid");	 // 用户在商户appid下的唯一标识   QQ公众号
					
					//查询付款码扫码 支付订单 是否支付
					FqMicroOrderExample fqMicroOrderExample = new FqMicroOrderExample();
					fqMicroOrderExample.createCriteria().andOrderNoEqualTo(orderNo);
					List<FqMicroOrder> recordlist  = fqmicroOrderMapper.selectByExample(fqMicroOrderExample);
					
					if(recordlist == null || recordlist.size() == 0){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+orderNo+"不存在");
						return 0;
					}
					FqMicroOrder record = recordlist.get(0);
					
					if(SystemConstant.PAY_ISPAY.equals(record.getStatus())){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+orderNo+"己支付");
						return 1;
					}else{
						FqThirdPay thirdPay = new FqThirdPay();
						BigDecimal money = BigDecimal.valueOf(Long.valueOf(totalFee)).divide(new BigDecimal(100)); //支付结果中的金额单位为分;
						
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
						StoreInfo store = storeService.queryStoreInfoBySeller(record.getSellerId());
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
						thirdPay.setSellerId(Long.valueOf(attach));
						thirdPay.setTotamt(calTotamt);
						thirdPay.setOrderRate(rate);
						thirdPay.setRateFee(rateMoney);
						thirdPay.setThirdOrderNo(CQOrderNo);
						thirdPay.setOpenId(openid);
						fqThirdPayMapper.insertSelective(thirdPay);
						SellerInfo seller = sellerInfoMapper.selectByPrimaryKey(Long.valueOf(attach));
						if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
							Date date0 = BaseUtil.getTimeDate(thirdPay.getPayTime());
							FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
							fqSellerStatementExample.createCriteria().andSellerIdEqualTo(Long.valueOf(attach)).andStatementDateEqualTo(date0);
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
								fsstate.setSellerId(Long.valueOf(attach));
								fsstate.setState(0);
								fsstate.setTotalMoney(calTotamt);
								fsstate.setTotalNum(1);
								StoreInfo storeinfo = storeService.queryStoreInfoBySeller(store.getSellerId());
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(date0);
								calendar.add(Calendar.DATE, storeinfo.getStatementPeriod());
								fsstate.setPeriodDate(calendar.getTime());
								fqSellerStatementMapper.insertSelective(fsstate);
							}
						} else {
							FqStoreCreditExample example3 = new FqStoreCreditExample();
							Date now = new Date();
							example3.createCriteria().andSellerIdEqualTo(Long.valueOf(attach)).andStatusEqualTo(1)
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
						
						fqMicroOrderExample = new FqMicroOrderExample();
						fqMicroOrderExample.createCriteria().andOrderNoEqualTo(orderNo).andSellerIdEqualTo(Long.valueOf(attach));
						FqMicroOrder fqMicroOrder = new FqMicroOrder();
						fqMicroOrder.setStatus(2);
						fqmicroOrderMapper.updateByExampleSelective(fqMicroOrder, fqMicroOrderExample);
						
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
						
						logger.error(">>>>>>>>>>>>>>>>>>订单："+record.getId()+"，回调成功");
						//更新订单成功，返回成功状态
						return 1;
					}
				}
			}else{
				logger.error(">>>>>>>>>>>>>>>>>>回调验签失败notifyParamsMap："+notifyParamsMap.toString());
			}
			return 0;
		}catch(Exception e){
			logger.error("updateCheckNotify error", e);
			e.printStackTrace();
		}
	
	return null;
   }
}
