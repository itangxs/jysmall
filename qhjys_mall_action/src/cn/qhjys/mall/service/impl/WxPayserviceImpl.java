package cn.qhjys.mall.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;

import cn.qhjys.mall.alipay.util.AlipaySubmit;
import cn.qhjys.mall.common.AlipayConfig;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.common.SzPfPost;
import cn.qhjys.mall.entity.CardCoupon;
import cn.qhjys.mall.entity.FqSellerStatement;
import cn.qhjys.mall.entity.FqSellerStatementExample;
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
import cn.qhjys.mall.mapper.CardCouponMapper;
import cn.qhjys.mall.mapper.FqRedpackDetailMapper;
import cn.qhjys.mall.mapper.FqRedpackMapper;
import cn.qhjys.mall.mapper.FqRedpackRecordMapper;
import cn.qhjys.mall.mapper.FqRedpackTimeMapper;
import cn.qhjys.mall.mapper.FqSellerStatementMapper;
import cn.qhjys.mall.mapper.FqStoreCreditMapper;
import cn.qhjys.mall.mapper.FqStoreMapper;
import cn.qhjys.mall.mapper.FqThirdPayMapper;
import cn.qhjys.mall.mapper.FqUserInfoMapper;
import cn.qhjys.mall.mapper.RebateCashMapper;
import cn.qhjys.mall.mapper.RebateOrderMapper;
import cn.qhjys.mall.mapper.StoreInfoMapper;
import cn.qhjys.mall.mapper.StoreRebateMapper;
import cn.qhjys.mall.mapper.WeixinUserinfoMapper;
import cn.qhjys.mall.quartz.PushPayInfoTask;
import cn.qhjys.mall.service.AuthenticationChnnelService;
import cn.qhjys.mall.service.FqPushInfoService;
import cn.qhjys.mall.service.FqStoreRateService;
import cn.qhjys.mall.service.RedisService;
import cn.qhjys.mall.service.SellerService;
import cn.qhjys.mall.service.StoreService;
import cn.qhjys.mall.service.WxPayService;
import cn.qhjys.mall.szpf.SignUtils;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.DateUtil;
import cn.qhjys.mall.weixin.qqpay.CQpayAPIURL;
import cn.qhjys.mall.weixin.qqpay.CQpayMchSpBase;
import cn.qhjys.mall.weixin.util.HttpClientUtil;
import cn.qhjys.mall.weixin.util.MD5Util;
import cn.qhjys.mall.weixin.util.OrderUtil;
import cn.qhjys.mall.weixin.util.RequestHandler;
import cn.qhjys.mall.weixin.util.Sha1Util;
import cn.qhjys.mall.weixin.util.SystemConstant;
import cn.qhjys.mall.weixin.util.XMLUtil;


/**
 * 微信支付业务实现类
* @Description: 
* @author llw  
* @date 20160309
 */
@Service
public class WxPayserviceImpl extends Base implements WxPayService {
	
	private final Log logger = LogFactory.getLog(WxPayserviceImpl.class);
	
	@Autowired
	private RebateOrderMapper orderMapper;
	
	@Autowired
	private RebateCashMapper cashMapper;
	
	@Autowired
	private StoreRebateMapper storeRebateMapper;
	@Autowired
	private WeixinUserinfoMapper weixinUserinfoMapper;
	
	@Autowired
	private StoreInfoMapper storeInfoMapper;
	@Autowired
	private FqThirdPayMapper fqThirdPayMapper;
	@Autowired
	private FqStoreCreditMapper fqStoreCreditMapper;
	@Autowired
	private FqStoreMapper fqStoreMapper;
	
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
	private FqUserInfoMapper fqUserInfoMapper;
	@Autowired
	private FqPushInfoService fqPushInfoService;
	@Autowired
	AuthenticationChnnelService authenticationChnnelService;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired
	private CardCouponMapper cardCouponMapper;
	
	
	@Autowired
	private FqStoreRateService fqStoreRateService;
	@Autowired
	private StoreService storeService;
	
	/**
	 * 获取微信支付参数
	 * @return
	 */
	@Override
	public Map<String,Object> insertOrderAndGetWxPars(Long couponId,String nickname,String headimgurl,String openId, long sellerId,
			String storeName, long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,BigDecimal rebate,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try{
		 /*校验订单请求参数 主要针对店铺信息 折扣等是否相符*/
		 Map<String,Object> conditionMap = new HashMap<String,Object>();
		 conditionMap.put("id", rebateId);
		 conditionMap.put("storeId", storeId);
		 conditionMap.put("sellerId", sellerId);
		 conditionMap.put("storeName", storeName);
		 conditionMap.put("rebate", rebate); 
		 conditionMap.put("currentTime", new Date());
		 Map<String,Object> rebateMap = storeRebateMapper.selectRebateDetailById(conditionMap);
		 if(null == rebateMap){
			 resultMap.put("errcode", SystemConstant.DATA_DIFF);
	         logger.info(">>>>>>>>>>>>>>>>>>>>订单参数打折信息不一致："+rebateId+","+storeId+","+sellerId+","+storeName+","+rebate);
			 return resultMap;
		 }
		 
		 CardCoupon card = null;
		 //校验卡券信息
		 if(couponId!=null){
			 //查询当前用户的该卡券信息
			  card  = cardCouponMapper.selectByPrimaryKey(couponId);
			 if(!openId.equals(card.getOpenId())){ //使用的卡券不是当前用户的
				 resultMap.put("errcode", SystemConstant.DATA_DIFF);
		         logger.info(">>>>>>>>>>>>>>>>>>>>订单参数使用卡券"+couponId+"不一致：卡券拥有者="+card.getOpenId()+" 使用者="+openId);
				 return resultMap;
			 }
			 if(storeId!=card.getStoreId()){ //不是本店的优惠券
				 resultMap.put("errcode", SystemConstant.DATA_DIFF);
		         logger.info(">>>>>>>>>>>>>>>>>>>>订单参数不是本店的优惠券"+storeId+" 卡券的storeId="+card.getStoreId());
				 return resultMap;
			 }
			 if(new Date().getTime()<card.getValidityStarttime().getTime() || new Date().getTime()>card.getValidityEndtime().getTime()){
				 resultMap.put("errcode", SystemConstant.DATA_DIFF);
		         logger.info(">>>>>>>>>>>>>>>>>>>>优惠券不在使用有效期内id="+couponId+" 当前用户="+openId);
				 return resultMap;
			 }
			 
			 if(card.getStatusCfg()!=0){
				 resultMap.put("errcode", SystemConstant.DATA_DIFF);
		         logger.info(">>>>>>>>>>>>>>>>>>>>订单参数使用卡券"+couponId+" 已经被使用");
				 return resultMap;
			 }
			 
			Integer couponCfg = card.getTemplateCouponCfg();
			Double tempPay = 0.00;
			//1:代金券   2:折扣券
			if(couponCfg==1){
				BigDecimal discountPay = new BigDecimal(card.getTemplateCouponAmount());
				 tempPay = needPay.subtract(discountPay).doubleValue();   
			}else if(couponCfg==1){
				BigDecimal discount = new BigDecimal(card.getTemplateCouponAmount());//折扣 0-100 之间的整数
				discount =  discount.divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);  //转成小数 示例0.85
				 tempPay = needPay.multiply(discount).doubleValue();   
			}
			
			if(tempPay>0){
				if(realPay.doubleValue()!=tempPay){ //计算出的实际金额和前台传的实际金额不符
					 resultMap.put("errcode", SystemConstant.DATA_DIFF);
			         logger.info(">>>>>>>>>>>>>>>>>>>>订单实际金额不一致：计算出金额="+tempPay+" 前台金额="+realPay);
					 return resultMap;
				}
			}
			
		 }
		 
		 //设置卡券已使用
		 if(couponId != null){
			  card = new CardCoupon();
			 card.setStatusCfg(1);
			 card.setId(couponId);
			 card.setValidateCfg(1);
			 card.setValidateDate(new Date());
			 cardCouponMapper.updateByPrimaryKeySelective(card);
		 }else{
			 couponId = 0l;
		 }
		 
		 
		 /*根据参数创建订单*/
		
		 //随机生成订单号
		 String orderNo = OrderUtil.getOrderNo(SystemConstant.WX_PAY_TYPE);
		 //插入订单表
//		 RebateOrder record = new RebateOrder(orderNo, openId, rebateId, 0, BigDecimal.ZERO, 0, needPay, realPay, totamt, noDiscount, new Date(), 1,nickname,headimgurl);
		 RebateOrder record = new RebateOrder(orderNo, openId, rebateId, couponId, BigDecimal.ZERO, 0, needPay, realPay, totamt, noDiscount, new Date(), 1,nickname,headimgurl);

			if(realPay.doubleValue()==0){   //0元支付
				    //设置订单已支付
					record.setStatus(SystemConstant.PAY_ISPAY);
					record.setPayTime(new Date());	//支付时间
					orderMapper.insertSelective(record);
					
					FqThirdPay thirdPay = new  FqThirdPay();
					thirdPay.setMoney(realPay);
					thirdPay.setOrderId(record.getId());
					thirdPay.setOrderNo(orderNo);
					thirdPay.setPayTime(record.getPayTime());
					thirdPay.setSort(1);
					thirdPay.setThirdOrderNo("");
					thirdPay.setType(88); //完全卡券代金支付
					thirdPay.setSellerId(sellerId);
					thirdPay.setTotamt(totamt);
					FqUserInfoExample fuieExample = new FqUserInfoExample();
					fuieExample.createCriteria().andOpenIdEqualTo(openId);
					List<FqUserInfo> users = fqUserInfoMapper.selectByExample(fuieExample);
					if (users.size()>0) {
						thirdPay.setUserId(users.get(0).getId());
					}
					thirdPay.setOpenId(openId);
					fqThirdPayMapper.insertSelective(thirdPay);
					
					SellerInfo seller = sellerService.getSellerById(sellerId);
					if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
						Date date0 = BaseUtil.getTimeDate(record.getCreateTime());
						FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
						fqSellerStatementExample.createCriteria().andSellerIdEqualTo(sellerId).andStatementDateEqualTo(date0);
						List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
						FqSellerStatement fsstate = null;
						if (fsslist.size()>0) {
							fsstate = fsslist.get(0);
							fsstate.setTotalMoney(fsstate.getTotalMoney().add(realPay));
							fsstate.setTotalNum(fsstate.getTotalNum()+1);
							fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
						}else{
							fsstate = new FqSellerStatement();
							fsstate.setCreateTime(new Date());
							fsstate.setStatementDate(date0);
							fsstate.setSellerId(sellerId);
							fsstate.setState(0);
							fsstate.setTotalMoney(realPay);
							fsstate.setTotalNum(1);
							StoreInfo store = storeService.queryStoreInfoBySeller(sellerId);
							Calendar calendar = Calendar.getInstance();
							calendar.setTime(date0);
							calendar.add(Calendar.DATE, store.getStatementPeriod());
							fsstate.setPeriodDate(calendar.getTime());
							fqSellerStatementMapper.insertSelective(fsstate);
						}
					}else{
						FqStoreCreditExample example3 = new FqStoreCreditExample();
						example3.createCriteria().andSellerIdEqualTo(sellerId).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(new Date()).andEndTimeGreaterThanOrEqualTo(new Date());
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
					//推送信息
					HashSet<Integer> pushSet = new HashSet<Integer>();
					pushSet.add(1);
					PushPayInfoTask task = new PushPayInfoTask();
					task.setSellerId(sellerId);
					task.setPushTypeArr(pushSet);
					task.setMoney(thirdPay.getMoney());
					task.setTradeType("coupon_"+card.getTemplateCouponAmount());
					task.setTransactionId(card.getCode());
					task.setOpenId(thirdPay.getOpenId());
					task.setPayTime(thirdPay.getPayTime());
					taskExecutor.execute(task);
					
					// 插入对应的统计表 修改数据 
					RebateCash cash = new RebateCash();
					cash.setRebateId(record.getRebateId());
					cash.setTotamtTotal(record.getTotamt());
					cash.setNeedTotal(record.getNeedPay());
					cash.setRealTotal(record.getRealPay());
					cash.setIntegralTotal(record.getIntegral());
					
					cashMapper.updateStatMoney(cash);
					logger.error(">>>>>>>>>>>>>>>>>>订单："+record.getId()+"，0元支付成功,cardNo="+card.getCode());
					//更新订单成功，返回成功状态
					resultMap.put("errcode", "00009527"); //直接返回成功界面
					resultMap.put("orderId",record.getId().toString());
					return resultMap;
			 }else{
		 orderMapper.insertSelective(record);
		 
		//调取微信统一支付接口	
		String JSAPI_URL = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_PAY_JSAPI_URL);
		String appId = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_APPID);
		String appSecret =ConstantsConfigurer.getProperty(SystemConstant.WECHAT_APPSECRET);
		String partner = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_PAY_PARTNER);
		String pkey = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_PAY_PKEY);
		String notifyUrl = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_PAY_NOTIFYURL);
			
		RequestHandler reqHandler = new RequestHandler(request, response);
		//初始化 
	    reqHandler.init(appId, appSecret, null,partner, pkey);
	    String noncestr = Sha1Util.getNonceStr();
			
	        //设置package订单参数
	        SortedMap<String, String> packageParams = new TreeMap<String, String>();
	        packageParams.put("appid", appId); //支付appId
	        packageParams.put("body", storeName+"-移动支付"); //商品描述   
	        packageParams.put("device_info","WEB"); //商品描述   
	        packageParams.put("mch_id", partner); 							//商家号
	        packageParams.put("nonce_str", noncestr);  
	        packageParams.put("notify_url", notifyUrl); 					//通知地址  
	        packageParams.put("openid", openId);
	        packageParams.put("out_trade_no", orderNo); 				//商户订单号  
	        packageParams.put("spbill_create_ip",request.getRemoteAddr()); 	//订单生成的机器IP，指用户浏览器端IP
	        int fee = realPay.multiply(new BigDecimal(100)).intValue();
	        packageParams.put("total_fee", String.valueOf(fee)); 			//商品总金额,以分为单位
	        packageParams.put("trade_type", "JSAPI"); 						//js支付
			
	        
	        //第一次签名
	        String sign1  = reqHandler.createSign(packageParams);
	        packageParams.put("sign", sign1);
	        
			
	        String requestXML = XMLUtil.getRequestXml(packageParams);
	        String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
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
	        resultMap.put("orderId", record.getId().toString());
			
	        resultMap.put("errcode", SystemConstant.SUCCESS);
	        
			return resultMap;
			 }
		}catch(Exception e){
			logger.error("getWxPayParams error", e);
			resultMap.put("errcode", SystemConstant.ERROR);
			throw e;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		  Map<String, String> signParams = new HashMap<String, String>();
		  signParams.put("sign", "84D6A16B5B632F561485C4ECF627D73F");
		  signParams.put("is_subscribe", "Y");
		  signParams.put("appid", "wx21dee8a8cec34f0b");
		  signParams.put("fee_type", "CNY");
		  signParams.put("nonce_str", "bf9ce4f69ab045fb497f79b7b5d7622e");
		  signParams.put("out_trade_no", "15_201506181636540320");
		  signParams.put("transaction_id", "1008410217201506180266900465");
		  signParams.put("trade_type", "JSAPI");
		  signParams.put("result_code", "SUCCESS");
		  signParams.put("mch_id", "1247562001");
		  signParams.put("total_fee", "1");
		  signParams.put("time_end", "20150618163755");
		  signParams.put("openid", "oXQ7St0_QzKZr1-CswQoC1G2wsF4");
		  signParams.put("bank_type", "CFT");
		  signParams.put("return_code", "SUCCESS");
		  signParams.put("cash_fee", "1");
		  
	}


	/**
	 * 微信支付回调通知
	 * @return
	 * @throws Exception
	 */
	@Override
	public  String updateCheckNotify(Map<String,String> notifyParamsMap,HttpServletRequest request ,HttpServletResponse response)throws Exception {
		
		try{
			
			response.setContentType("text/html;charset=UTF-8");
			if(notifyParamsMap == null){
				  logger.info(">>>>>>>>>>>>>>>>>>微信支付回调 参数 is null");
	              return "-1";
			}
			
			logger.info(">>>>>>>>>>>>>>>>>>微信支付回调 参数" + notifyParamsMap.toString());
			
			if ("FAIL".equals(notifyParamsMap.get("return_code"))) {
				logger.info(">>>>>>>>>>>>>>>>>>微信支付回调，通信异常："+notifyParamsMap.toString());
				 return "-1";
			}
			if ("FAIL".equals(notifyParamsMap.get("result_code"))) {
				logger.info(">>>>>>>>>>>>>>>>>>微信支付回调失败:"+notifyParamsMap.toString());
				 return "-1";
			}
			//微信支付回调通知验签
			if(isWxSign(notifyParamsMap)){
				
				//回调成功 -处理业务状态
				if ("SUCCESS".equals(notifyParamsMap.get("return_code")) && "SUCCESS".equals(notifyParamsMap.get("result_code"))) {
					
					String tradeNo =notifyParamsMap.get("out_trade_no") ;			
					String weixinOrderNo =notifyParamsMap.get("transaction_id") ;			
					String payTime = notifyParamsMap.get("time_end");				//支付时间
					
					RebateOrderExample example = new RebateOrderExample();
					example.createCriteria().andOrderNoEqualTo(tradeNo);
					List<RebateOrder> recordlist  = orderMapper.selectByExample(example);
					
					if(recordlist == null || recordlist.size() == 0){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"不存在");
						 return "-1";
					}
					RebateOrder record =recordlist.get(0);
					if(SystemConstant.PAY_ISPAY.equals(record.getStatus())){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"己支付");
						return "-1";
					}else{
						Date now = new Date();
						
						StoreRebate rebate = storeRebateMapper.selectByPrimaryKey(record.getRebateId());
						StoreInfo store = storeInfoMapper.selectByPrimaryKey(rebate.getStoreId());
						
						BigDecimal calTotamt =  record.getTotamt();
						BigDecimal rate = new BigDecimal(0); 
						BigDecimal rateMoney = new BigDecimal(0); 
						//查询本店费率
						Integer type = 1;   // 1WX   2ALi   3QQ
						HashMap<String, BigDecimal>  rateMap = fqStoreRateService.calculateOrderRate(record.getTotamt(), store, type);
						
						if(rateMap!=null&&rateMap.size()>0){
							rate = rateMap.get("smallRate");	 //费率
							rateMoney = rateMap.get("rateMoney");	 //手续费
							calTotamt = rateMap.get("calTotamt");	  //扣除手续费结算的金额 
						}
						
						
						//更新订单  
						RebateOrder uprecord = new RebateOrder();
						uprecord.setId(Long.valueOf(record.getId()));
						uprecord.setStatus(SystemConstant.PAY_ISPAY);
						uprecord.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss", payTime));	//支付时间
						uprecord.setWeixinOrderNo(weixinOrderNo);
						uprecord.setTotamt(calTotamt);
						uprecord.setOrderRate(rate);
						uprecord.setRateFee(rateMoney);
						orderMapper.updateByPrimaryKeySelective(uprecord);
						

						FqThirdPay thirdPay = new  FqThirdPay();
						thirdPay.setMoney(record.getRealPay());
						thirdPay.setOrderId(record.getId());
						thirdPay.setOrderNo(record.getOrderNo());
						thirdPay.setPayTime(uprecord.getPayTime());
						thirdPay.setSort(1);
						thirdPay.setThirdOrderNo(weixinOrderNo);
						thirdPay.setType(1);
						thirdPay.setSellerId(store.getSellerId());
						thirdPay.setTotamt(calTotamt);
						thirdPay.setOrderRate(rate);
						thirdPay.setRateFee(rateMoney);
						FqUserInfoExample fuieExample = new FqUserInfoExample();
						fuieExample.createCriteria().andOpenIdEqualTo(record.getOpenId());
						List<FqUserInfo> users = fqUserInfoMapper.selectByExample(fuieExample);
						if (users.size()>0) {
							thirdPay.setUserId(users.get(0).getId());
						}
						thirdPay.setOpenId(record.getOpenId());
						fqThirdPayMapper.insertSelective(thirdPay);
						
						
						SellerInfo seller = sellerService.getSellerById(store.getSellerId());
						if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
							Date date0 = BaseUtil.getTimeDate(record.getCreateTime());
							FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
							fqSellerStatementExample.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatementDateEqualTo(date0);
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
								fsstate.setSellerId(store.getSellerId());
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
							example3.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
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
						pushSet.add(1);
						PushPayInfoTask task = new PushPayInfoTask();
						task.setSellerId(store.getSellerId());
						task.setPushTypeArr(pushSet);
						task.setMoney(thirdPay.getMoney());
						task.setTradeType("weixin");
						task.setTransactionId(tradeNo);
						task.setOpenId(thirdPay.getOpenId());
						task.setPayTime(thirdPay.getPayTime());
						taskExecutor.execute(task);
						
					
						// 插入对应的统计表 修改数据 
						RebateCash cash = new RebateCash();
						cash.setRebateId(record.getRebateId());
						cash.setTotamtTotal(calTotamt);
						cash.setNeedTotal(record.getNeedPay());
						cash.setRealTotal(record.getRealPay());
						cash.setIntegralTotal(record.getIntegral());
						
						cashMapper.updateStatMoney(cash);
						
						logger.error(">>>>>>>>>>>>>>>>>>订单："+record.getId()+"，回调成功");
						//更新订单成功，返回成功状态
						 return "1";
					}
				}else{
					 return "-1";
				}
			}else{
				logger.error(">>>>>>>>>>>>>>>>>>回调验签失败notifyParamsMap："+notifyParamsMap.toString());
				 return "-1";
			}
		}catch(Exception e){
			logger.error("updateCheckNotify error", e);
			 return "-1";
			
		}
	}
	
	@Override
	public  Integer updateCheckNotifyWft(Map<String,String> notifyParamsMap,HttpServletRequest request ,HttpServletResponse response)throws Exception {
		
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
					String is_subscribe = notifyParamsMap.get("is_subscribe");	
					
					RebateOrderExample example = new RebateOrderExample();
					example.createCriteria().andOrderNoEqualTo(tradeNo);
					List<RebateOrder> recordlist  = orderMapper.selectByExample(example);
					
					if(recordlist == null || recordlist.size() == 0){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"不存在");
						return 0;
					}
					RebateOrder record =recordlist.get(0);
					if(SystemConstant.PAY_ISPAY.equals(record.getStatus())){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"己支付");
						return 0;
					}else{

						StoreRebate rebate = storeRebateMapper.selectByPrimaryKey(record.getRebateId());
						StoreInfo store = storeInfoMapper.selectByPrimaryKey(rebate.getStoreId());
						
						BigDecimal calTotamt =  record.getTotamt();
						BigDecimal rate = new BigDecimal(0); 
						BigDecimal rateMoney = new BigDecimal(0); 
						//查询本店费率
						Integer type = 1;   // 1WX   2ALi   3QQ
						HashMap<String, BigDecimal>  rateMap = fqStoreRateService.calculateOrderRate(record.getTotamt(), store, type);
						
						if(rateMap!=null&&rateMap.size()>0){
							rate = rateMap.get("smallRate");	 //费率
							rateMoney = rateMap.get("rateMoney");	 //手续费
							calTotamt = rateMap.get("calTotamt");	  //扣除手续费结算的金额 
						}
						
						//更新订单  
						RebateOrder uprecord = new RebateOrder();
						uprecord.setId(Long.valueOf(record.getId()));
						uprecord.setStatus(SystemConstant.PAY_ISPAY);
						uprecord.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss", payTime));	//支付时间
						uprecord.setWeixinOrderNo(wftOrderNo);
						uprecord.setTotamt(calTotamt);
						uprecord.setOrderRate(rate);
						uprecord.setRateFee(rateMoney);
						orderMapper.updateByPrimaryKeySelective(uprecord);
						
						Date now = new Date();
						
						FqThirdPay thirdPay = new  FqThirdPay();
						thirdPay.setMoney(record.getRealPay());
						thirdPay.setOrderId(record.getId());
						thirdPay.setOrderNo(record.getOrderNo());
						thirdPay.setPayTime(uprecord.getPayTime());
						thirdPay.setSort(1);
						thirdPay.setThirdOrderNo(wftOrderNo);
						thirdPay.setType(3);
						thirdPay.setSellerId(store.getSellerId());
						thirdPay.setTotamt(calTotamt);
						thirdPay.setOrderRate(rate);
						thirdPay.setRateFee(rateMoney);
						
						FqUserInfoExample fuieExample = new FqUserInfoExample();
						fuieExample.createCriteria().andOpenIdEqualTo(record.getOpenId());
						List<FqUserInfo> users = fqUserInfoMapper.selectByExample(fuieExample);
						if (users.size()>0) {
							thirdPay.setUserId(users.get(0).getId());
						}
						thirdPay.setOpenId(record.getOpenId());
						fqThirdPayMapper.insertSelective(thirdPay);
					
						SellerInfo seller = sellerService.getSellerById(store.getSellerId());
						if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
							Date date0 = BaseUtil.getTimeDate(record.getCreateTime());
							FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
							fqSellerStatementExample.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatementDateEqualTo(date0);
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
								fsstate.setSellerId(store.getSellerId());
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
							example3.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
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
						pushSet.add(1);
						PushPayInfoTask task = new PushPayInfoTask();
						task.setSellerId(store.getSellerId());
						task.setPushTypeArr(pushSet);
						task.setMoney(thirdPay.getMoney());
						task.setTradeType("weixin");
						task.setTransactionId(tradeNo);
						task.setOpenId(thirdPay.getOpenId());
						task.setPayTime(thirdPay.getPayTime());
						taskExecutor.execute(task);
						 
						// 插入对应的统计表 修改数据 
						RebateCash cash = new RebateCash();
						cash.setRebateId(record.getRebateId());
						cash.setTotamtTotal(calTotamt);
						cash.setNeedTotal(record.getNeedPay());
						cash.setRealTotal(record.getRealPay());
						cash.setIntegralTotal(record.getIntegral());
						
						cashMapper.updateStatMoney(cash);
						
						logger.error(">>>>>>>>>>>>>>>>>>订单："+record.getId()+"，回调成功");
						//更新订单成功，返回成功状态
						return 1;
					}
				}
			}else{
				logger.error(">>>>>>>>>>>>>>>>>>回调验签失败notifyParamsMap："+notifyParamsMap.toString());
			}
			return null;
		}catch(Exception e){
			logger.error("updateCheckNotify error", e);
			throw e;
		}
	}
	@Override
	public  Integer updateCheckNotifyGd(Map<String,String> notifyParamsMap,HttpServletRequest request ,HttpServletResponse response)throws Exception {
		
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
					
					
					RebateOrderExample example = new RebateOrderExample();
					example.createCriteria().andOrderNoEqualTo(tradeNo);
					List<RebateOrder> recordlist  = orderMapper.selectByExample(example);
					
					if(recordlist == null || recordlist.size() == 0){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"不存在");
						return 0;
					}
					RebateOrder record =recordlist.get(0);
					if(SystemConstant.PAY_ISPAY.equals(record.getStatus())){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"己支付");
						return 0;
					}else{
						
						String bankTypeCode = notifyParamsMap.get("bank_type");
						
						StoreRebate rebate = storeRebateMapper.selectByPrimaryKey(record.getRebateId());
						StoreInfo store = storeInfoMapper.selectByPrimaryKey(rebate.getStoreId());
						
						BigDecimal calTotamt =  record.getTotamt();
						BigDecimal rate = new BigDecimal(0); 
						BigDecimal rateMoney = new BigDecimal(0); 
						//查询本店费率
						Integer type = 1;   // 1WX   2ALi   3QQ
						HashMap<String, BigDecimal>  rateMap = fqStoreRateService.calculateOrderRate(record.getTotamt(), store, type);
						
						if(rateMap!=null&&rateMap.size()>0){
							rate = rateMap.get("smallRate");	 //费率
							rateMoney = rateMap.get("rateMoney");	 //手续费
							calTotamt = rateMap.get("calTotamt");	  //扣除手续费结算的金额 
						}
						
						//更新订单  
						RebateOrder uprecord = new RebateOrder();
						uprecord.setId(Long.valueOf(record.getId()));
						uprecord.setStatus(SystemConstant.PAY_ISPAY);
						uprecord.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss", payTime));	//支付时间
						uprecord.setWeixinOrderNo(wftOrderNo);
						uprecord.setTotamt(calTotamt);
						uprecord.setOrderRate(rate);
						uprecord.setRateFee(rateMoney);
						orderMapper.updateByPrimaryKeySelective(uprecord);
						Date now = new Date();
						
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
						thirdPay.setMoney(record.getRealPay());
						thirdPay.setOrderId(record.getId());
						thirdPay.setOrderNo(record.getOrderNo());
						thirdPay.setPayTime(uprecord.getPayTime());
						thirdPay.setSort(1);
						thirdPay.setThirdOrderNo(wftOrderNo);
						thirdPay.setType(3);
						thirdPay.setSellerId(store.getSellerId());
						thirdPay.setTotamt(calTotamt);
						thirdPay.setOrderRate(rate);
						thirdPay.setRateFee(rateMoney);
						
						thirdPay.setBankType(bankType);
						thirdPay.setBankTypeCode(bankTypeCode);
						FqUserInfoExample fuieExample = new FqUserInfoExample();
						fuieExample.createCriteria().andOpenIdEqualTo(record.getOpenId());
						List<FqUserInfo> users = fqUserInfoMapper.selectByExample(fuieExample);
						if (users.size()>0) {
							thirdPay.setUserId(users.get(0).getId());
						}
						thirdPay.setOpenId(record.getOpenId());
						fqThirdPayMapper.insertSelective(thirdPay);
						
						SellerInfo seller = sellerService.getSellerById(store.getSellerId());
						if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
							Date date0 = BaseUtil.getTimeDate(record.getCreateTime());
							FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
							fqSellerStatementExample.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatementDateEqualTo(date0);
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
								fsstate.setSellerId(store.getSellerId());
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
							example3.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
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
						pushSet.add(1);
						PushPayInfoTask task = new PushPayInfoTask();
						task.setSellerId(store.getSellerId());
						task.setPushTypeArr(pushSet);
						task.setMoney(thirdPay.getMoney());
						task.setTradeType("weixin");
						task.setTransactionId(tradeNo);
						task.setOpenId(thirdPay.getOpenId());
						task.setPayTime(uprecord.getPayTime());
						taskExecutor.execute(task);
						
						// 插入对应的统计表 修改数据 
						RebateCash cash = new RebateCash();
						cash.setRebateId(record.getRebateId());
						cash.setTotamtTotal(calTotamt);
						cash.setNeedTotal(record.getNeedPay());
						cash.setRealTotal(record.getRealPay());
						cash.setIntegralTotal(record.getIntegral());
						
						cashMapper.updateStatMoney(cash);
						
						logger.error(">>>>>>>>>>>>>>>>>>订单："+record.getId()+"，回调成功");
						//更新订单成功，返回成功状态
						return 1;
					}
				}
			}else{
				logger.error(">>>>>>>>>>>>>>>>>>回调验签失败notifyParamsMap："+notifyParamsMap.toString());
			}
			return null;
		}catch(Exception e){
			logger.error("updateCheckNotify error", e);
			throw e;
		}
	}
	
	@Override
	public  String updateCheckNotifyAlipay(Map<String,String> paramsMap)throws Exception {
		try{
					//商户订单号
					String orderNo = paramsMap.get("out_trade_no");
					//支付宝交易号
					String tradeNo = paramsMap.get("trade_no");
					String pay_time = paramsMap.get("gmt_payment");
					
					//支付宝用户实际支付金额
					double totalFee = Double.valueOf(paramsMap.get("total_fee").trim());
					
					RebateOrderExample example = new RebateOrderExample();
					example.createCriteria().andOrderNoEqualTo(orderNo);
					List<RebateOrder> recordlist  = orderMapper.selectByExample(example);
					
					if(recordlist == null || recordlist.size() == 0){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"不存在");
						return "0";
					}
					RebateOrder record =recordlist.get(0);
					
					double fee = record.getRealPay().doubleValue();
					if(fee!=totalFee){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+orderNo+"：所需要实际支付的金额不等于用户在钱包中支付的金额");
						return "0";
					}
					
					if(SystemConstant.PAY_ISPAY.equals(record.getStatus())){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"己支付");
						return "1";
					}else{
						
						StoreRebate rebate = storeRebateMapper.selectByPrimaryKey(record.getRebateId());
						StoreInfo store = storeInfoMapper.selectByPrimaryKey(rebate.getStoreId());
						
						BigDecimal calTotamt =  record.getTotamt();
						BigDecimal rate = new BigDecimal(0); 
						BigDecimal rateMoney = new BigDecimal(0); 
						//查询本店费率
						Integer type = 2;   // 1WX   2ALi   3QQ
						HashMap<String, BigDecimal>  rateMap = fqStoreRateService.calculateOrderRate(record.getTotamt(), store, type);
						
						if(rateMap!=null&&rateMap.size()>0){
							rate = rateMap.get("smallRate");	 //费率
							rateMoney = rateMap.get("rateMoney");	 //手续费
							calTotamt = rateMap.get("calTotamt");	  //扣除手续费结算的金额 
						}
						
						Date payTime = DateUtil.convertStringToDate(pay_time);
						//更新订单  
						RebateOrder uprecord = new RebateOrder();
						uprecord.setId(Long.valueOf(record.getId()));
						uprecord.setStatus(SystemConstant.PAY_ISPAY);
						uprecord.setPayTime(payTime);	//支付时间
						uprecord.setWeixinOrderNo(tradeNo);
						uprecord.setTotamt(calTotamt);
						uprecord.setOrderRate(rate);
						uprecord.setRateFee(rateMoney);
						orderMapper.updateByPrimaryKeySelective(uprecord);
						
						
						FqThirdPay thirdPay = new  FqThirdPay();
						thirdPay.setMoney(record.getRealPay());
						thirdPay.setOrderId(record.getId());
						thirdPay.setOrderNo(record.getOrderNo());
						thirdPay.setPayTime(payTime);
						thirdPay.setSort(1);
						thirdPay.setThirdOrderNo(tradeNo);
						thirdPay.setType(2);
						thirdPay.setSellerId(store.getSellerId());
						thirdPay.setTotamt(calTotamt);
						thirdPay.setOrderRate(rate);
						thirdPay.setRateFee(rateMoney);
						FqUserInfoExample fuieExample = new FqUserInfoExample();
						fuieExample.createCriteria().andOpenIdEqualTo(record.getOpenId());
						List<FqUserInfo> users = fqUserInfoMapper.selectByExample(fuieExample);
						if (users.size()>0) {
							thirdPay.setUserId(users.get(0).getId());
						}
						thirdPay.setOpenId(record.getOpenId());
						fqThirdPayMapper.insertSelective(thirdPay);
						
						SellerInfo seller = sellerService.getSellerById(store.getSellerId());
						if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
							Date date0 = BaseUtil.getTimeDate(thirdPay.getPayTime());
							FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
							fqSellerStatementExample.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatementDateEqualTo(date0);
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
								fsstate.setSellerId(store.getSellerId());
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
							example3.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(payTime).andEndTimeGreaterThanOrEqualTo(payTime);
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
						task.setSellerId(store.getSellerId());
						task.setPushTypeArr(pushSet);
						task.setMoney(thirdPay.getMoney());
						task.setTradeType("alipay");
						task.setTransactionId(orderNo);
						taskExecutor.execute(task);
						
						// 插入对应的统计表 修改数据 
						RebateCash cash = new RebateCash();
						cash.setRebateId(record.getRebateId());
						cash.setTotamtTotal(calTotamt);
						cash.setNeedTotal(record.getNeedPay());
						cash.setRealTotal(record.getRealPay());
						cash.setIntegralTotal(record.getIntegral());
						
						cashMapper.updateStatMoney(cash);
						
						logger.error(">>>>>>>>>alipay>>>>>>>>>订单："+record.getId()+"，回调成功");
						//更新订单成功，返回成功状态
						return "1";
					}
		}catch(Exception e){
			logger.error("updateCheckNotify error", e);
			throw e;
		}
	}
	
	
	/**
	 * 微信支付回调通知验签
	 * @param paramsMap
	 * @return
	 */
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
	public static boolean isPFSign(Map<String,String> paramsMap) throws Exception{
		
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
	
	public  Map<String,Object> insertOrderAndGetAlipay(String openId, long sellerId,
			String storeName, BigDecimal rebate,long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,String nickname,String headimgurl)throws Exception{
		
		 Map<String,Object> resultMap = new HashMap<String,Object>();
		
		 /*校验订单请求参数 主要针对店铺信息 折扣等是否相符*/
		 Map<String,Object> conditionMap = new HashMap<String,Object>();
		 conditionMap.put("id", rebateId);
		 conditionMap.put("storeId", storeId);
		 conditionMap.put("sellerId", sellerId);
		 conditionMap.put("storeName", storeName);
		 conditionMap.put("rebate", rebate);
		 conditionMap.put("currentTime", new Date());
		 Map<String,Object> rebateMap = storeRebateMapper.selectRebateDetailById(conditionMap);
		 if(null == rebateMap){
			 resultMap.put("errcode", SystemConstant.DATA_DIFF);
	         logger.info(">>>>>>>>>>>>>>>>>>>>订单参数打折信息不一致："+rebateId+","+storeId+","+sellerId+","+storeName+","+rebate);
			 return resultMap;
		 }
		 String orderNo = OrderUtil.getOrderNo(SystemConstant.ALI_PAY_TYPE);
		 //插入订单表
		 RebateOrder record = new RebateOrder(orderNo, openId, rebateId, 0L, BigDecimal.ZERO, 1, realPay, realPay, totamt, noDiscount, new Date(), 1,nickname,headimgurl);
		int size = orderMapper.insertSelective(record);
		if(size<=0){
			 resultMap.put("errcode", SystemConstant.ERROR);
	         logger.info(">>>>>>>>>>>>>>>>>>>>支付宝保存订单失败");
			 return resultMap;
		}
		
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", AlipayConfig.service);
	    sParaTemp.put("partner", AlipayConfig.partner);
	    sParaTemp.put("seller_id", AlipayConfig.seller_id);
	    sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("payment_type", AlipayConfig.payment_type);
		sParaTemp.put("notify_url", AlipayConfig.notify_url1);
		sParaTemp.put("return_url", AlipayConfig.return_url1);
		sParaTemp.put("out_trade_no",orderNo);
		sParaTemp.put("subject", storeName.trim()+"-移动支付");
		sParaTemp.put("total_fee", realPay.toString());
		//sParaTemp.put("show_url", "");
		//sParaTemp.put("app_pay","Y");//启用此参数可唤起钱包APP支付。
		//sParaTemp.put("body", "");
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
		 resultMap.put("errcode", SystemConstant.SUCCESS);
		 resultMap.put("sHtmlText", sHtmlText);
		 
		return resultMap;
	}
	
	/**
	 * 光大银行的支付宝支付 jspay
	 */
	public  Map<String,Object> insertOrderAndGetAlipayGD(String openId, long sellerId,
			String storeName, BigDecimal rebate,long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,String nickname,String headimgurl,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		 Map<String,Object> resultMap = new HashMap<String,Object>();
		
		 /*校验订单请求参数 主要针对店铺信息 折扣等是否相符*/
		 Map<String,Object> conditionMap = new HashMap<String,Object>();
		 conditionMap.put("id", rebateId);
		 conditionMap.put("storeId", storeId);
		 conditionMap.put("sellerId", sellerId);
		 conditionMap.put("storeName", storeName);
		 conditionMap.put("rebate", rebate);
		 conditionMap.put("currentTime", new Date());
		 Map<String,Object> rebateMap = storeRebateMapper.selectRebateDetailById(conditionMap);
		 if(null == rebateMap){
			 resultMap.put("errcode", SystemConstant.DATA_DIFF);
	         logger.info(">>>>>>>>>>>>>>>>>>>>订单参数打折信息不一致："+rebateId+","+storeId+","+sellerId+","+storeName+","+rebate);
			 return resultMap;
		 }
		 String orderNo = OrderUtil.getOrderNo(SystemConstant.GD_ALI_PAY_TYPE);
		 //插入订单表
		 RebateOrder record = new RebateOrder(orderNo, openId, rebateId, 0L, BigDecimal.ZERO, 1, realPay, realPay, totamt, noDiscount, new Date(), 1,nickname,headimgurl);
		int size = orderMapper.insertSelective(record);
		if(size<=0){
			 resultMap.put("errcode", SystemConstant.ERROR);
	         logger.info(">>>>>>>>>>>>>>>>>>>>光大支付宝保存订单失败");
			 return resultMap;
		}
		
		String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
		String notifyUrl = ConstantsConfigurer.getProperty(SystemConstant.GD_PAY_AL_NOTIFYURL);
		String MCH_ID = ConstantsConfigurer.getProperty(SystemConstant.GD_MCH_ID);
		String PAY_KEY = ConstantsConfigurer.getProperty(SystemConstant.GD_PAY_KEY);
		
		 String noncestr = Sha1Util.getNonceStr();
		 int fee = realPay.multiply(new BigDecimal(100)).intValue();
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("service","pay.alipay.jspay");
		packageParams.put("mch_id", MCH_ID);
		packageParams.put("out_trade_no", orderNo);
		packageParams.put("body", storeName+"-移动支付");
		packageParams.put("total_fee", String.valueOf(fee));
		packageParams.put("mch_create_ip", request.getRemoteAddr());
		packageParams.put("notify_url", notifyUrl);
		packageParams.put("nonce_str", noncestr);
		packageParams.put("buyer_id", openId);
		String sign = createSign(packageParams, PAY_KEY);;
		packageParams.put("sign", sign);
		String requestXML = XMLUtil.getRequestXml(packageParams);
		String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
		logger.info(">>>>>>>>GD WAP PAY ALI>>>>>>>>>>>>："+result);
		Map<String, String> notifyParamsMap =XMLUtil.doXMLParse(result);
		
		if (notifyParamsMap.get("status").equals("0")&&notifyParamsMap.get("result_code").equals("0")) {
			
			String jsonString = notifyParamsMap.get("pay_info");
			 Map maps = (Map) JSONObject.parse(jsonString);
			resultMap.put("errcode", "0000");
			resultMap.put("tradeNO", maps.get("tradeNO"));
			resultMap.put("orderId",record.getId().toString());
			return resultMap;
		}else{
			resultMap.put("errcode", "80000");
			return resultMap;
		}
	}
	/**
	 * 兴业银行的支付宝支付 jspay
	 */
	public  Map<String,Object> insertOrderAndGetAlipayXY(String openId, long sellerId,
			String storeName, BigDecimal rebate,long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,String nickname,String headimgurl,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		/*校验订单请求参数 主要针对店铺信息 折扣等是否相符*/
		Map<String,Object> conditionMap = new HashMap<String,Object>();
		conditionMap.put("id", rebateId);
		conditionMap.put("storeId", storeId);
		conditionMap.put("sellerId", sellerId);
		conditionMap.put("storeName", storeName);
		conditionMap.put("rebate", rebate);
		conditionMap.put("currentTime", new Date());
		Map<String,Object> rebateMap = storeRebateMapper.selectRebateDetailById(conditionMap);
		if(null == rebateMap){
			resultMap.put("errcode", SystemConstant.DATA_DIFF);
			logger.info(">>>>>>>>>>>>>>>>>>>>订单参数打折信息不一致："+rebateId+","+storeId+","+sellerId+","+storeName+","+rebate);
			return resultMap;
		}
		String orderNo = OrderUtil.getOrderNo(SystemConstant.XY_ALI_PAY_TYPE);
		//插入订单表
		RebateOrder record = new RebateOrder(orderNo, openId, rebateId, 0L, BigDecimal.ZERO, 1, realPay, realPay, totamt, noDiscount, new Date(), 1,nickname,headimgurl);
		int size = orderMapper.insertSelective(record);
		if(size<=0){
			resultMap.put("errcode", SystemConstant.ERROR);
			logger.info(">>>>>>>>>>>>>>>>>>>>兴业支付宝保存订单失败");
			return resultMap;
		}
	
		String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
		String notifyUrl = ConstantsConfigurer.getProperty(SystemConstant.XY_PAY_AL_NOTIFYURL);
		String MCH_ID = authenticationChnnelService.findByXyAuthcationInfo(storeId).getXyMerchantNum();
		String PAY_KEY = authenticationChnnelService.findByXyAuthcationInfo(storeId).getXyBankKey();
		logger.info(">>>>>>>>>>>>>>>>>>MCH_ID:" + MCH_ID+",PAY_KEY:"+PAY_KEY);
		String noncestr = Sha1Util.getNonceStr();
		int fee = realPay.multiply(new BigDecimal(100)).intValue();
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("service","pay.alipay.jspay");
		packageParams.put("mch_id", MCH_ID);
		packageParams.put("out_trade_no", orderNo);
		packageParams.put("body", storeName+"-移动支付");
		packageParams.put("total_fee", String.valueOf(fee));
		packageParams.put("mch_create_ip", request.getRemoteAddr());
		packageParams.put("notify_url", notifyUrl);
		packageParams.put("nonce_str", noncestr);
		packageParams.put("buyer_id", openId);
		String sign = createSign(packageParams, PAY_KEY);;
		packageParams.put("sign", sign);
		String requestXML = XMLUtil.getRequestXml(packageParams);
		logger.info(">>>>>>>>>>>>>>>>>>兴业支付宝支付请求参数:" + requestXML);
		String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
		Map<String, String> notifyParamsMap =XMLUtil.doXMLParse(result);
		logger.info(">>>>>>>>>>>>>>>>>>兴业支付宝支付响应:" + notifyParamsMap.toString());
		if (notifyParamsMap.get("status").equals("0")&&notifyParamsMap.get("result_code").equals("0")) {
			
			String jsonString = notifyParamsMap.get("pay_info");
			Map maps = (Map) JSONObject.parse(jsonString);
			resultMap.put("errcode", "0000");
			resultMap.put("tradeNO", maps.get("tradeNO"));
			resultMap.put("orderId",record.getId().toString());
			resultMap.put("orderNo",orderNo);
			return resultMap;
		}else{
			resultMap.put("errcode", "80000");
			return resultMap;
		}
	}
	/**
	 * 浦发银行的支付宝支付 jspay
	 */
	public  Map<String,Object> insertOrderAndGetAlipayPF(String openId, long sellerId,
			String storeName, BigDecimal rebate,long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,String nickname,String headimgurl,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		/*校验订单请求参数 主要针对店铺信息 折扣等是否相符*/
		Map<String,Object> conditionMap = new HashMap<String,Object>();
		conditionMap.put("id", rebateId);
		conditionMap.put("storeId", storeId);
		conditionMap.put("sellerId", sellerId);
		conditionMap.put("storeName", storeName);
		conditionMap.put("rebate", rebate);
		conditionMap.put("currentTime", new Date());
		Map<String,Object> rebateMap = storeRebateMapper.selectRebateDetailById(conditionMap);
		if(null == rebateMap){
			resultMap.put("errcode", SystemConstant.DATA_DIFF);
			logger.info(">>>>>>>>>>>>>>>>>>>>订单参数打折信息不一致："+rebateId+","+storeId+","+sellerId+","+storeName+","+rebate);
			return resultMap;
		}
		String orderNo = OrderUtil.getOrderNo(SystemConstant.PF_ALI_PAY_TYPE);
		//插入订单表
		RebateOrder record = new RebateOrder(orderNo, openId, rebateId, 0L, BigDecimal.ZERO, 1, realPay, realPay, totamt, noDiscount, new Date(), 1,nickname,headimgurl);
		int size = orderMapper.insertSelective(record);
		if(size<=0){
			resultMap.put("errcode", SystemConstant.ERROR);
			logger.info(">>>>>>>>>>>>>>>>>>>>浦发支付宝保存订单失败");
			return resultMap;
		}
		
		String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
		String notifyUrl = ConstantsConfigurer.getProperty(SystemConstant.PF_PAY_AL_NOTIFYURL);
		String MCH_ID = ConstantsConfigurer.getProperty(SystemConstant.PF_MCH_ID);
		String PAY_KEY = ConstantsConfigurer.getProperty(SystemConstant.PF_PAY_KEY);
		
		String noncestr = Sha1Util.getNonceStr();
		int fee = realPay.multiply(new BigDecimal(100)).intValue();
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("service","pay.alipay.jspay");
		packageParams.put("mch_id", MCH_ID);
		packageParams.put("out_trade_no", orderNo);
		packageParams.put("body", storeName+"-移动支付");
		packageParams.put("total_fee", String.valueOf(fee));
		packageParams.put("mch_create_ip", request.getRemoteAddr());
		packageParams.put("notify_url", notifyUrl);
		packageParams.put("nonce_str", noncestr);
		packageParams.put("buyer_id", openId);
		String sign = createSign(packageParams, PAY_KEY);;
		packageParams.put("sign", sign);
		String requestXML = XMLUtil.getRequestXml(packageParams);
		String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
		Map<String, String> notifyParamsMap =XMLUtil.doXMLParse(result);
		
		if (notifyParamsMap.get("status").equals("0")&&notifyParamsMap.get("result_code").equals("0")) {
			
			String jsonString = notifyParamsMap.get("pay_info");
			Map maps = (Map) JSONObject.parse(jsonString);
			resultMap.put("errcode", "0000");
			resultMap.put("tradeNO", maps.get("tradeNO"));
			resultMap.put("orderId",record.getId().toString());
			return resultMap;
		}else{
			resultMap.put("errcode", "80000");
			return resultMap;
		}
	}
	/**
	 * 浦发银行的QQ支付 jspay
	 */
	@Override
	public  Map<String,Object> insertOrderAndGetQQPF(String openId, long sellerId,
			String storeName, BigDecimal rebate,long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,String nickname,String headimgurl,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		/*校验订单请求参数 主要针对店铺信息 折扣等是否相符*/
		Map<String,Object> conditionMap = new HashMap<String,Object>();
		conditionMap.put("id", rebateId);
		conditionMap.put("storeId", storeId);
		conditionMap.put("sellerId", sellerId);
		conditionMap.put("storeName", storeName);
		conditionMap.put("rebate", rebate);
		conditionMap.put("currentTime", new Date());
		Map<String,Object> rebateMap = storeRebateMapper.selectRebateDetailById(conditionMap);
		if(null == rebateMap){
			resultMap.put("errcode", SystemConstant.DATA_DIFF);
			logger.info(">>>>>>>>>>>>>>>>>>>>订单参数打折信息不一致："+rebateId+","+storeId+","+sellerId+","+storeName+","+rebate);
			return resultMap;
		}
		String orderNo = OrderUtil.getOrderNo(SystemConstant.PF_QQ_PAY_TYPE);
		//插入订单表
		RebateOrder record = new RebateOrder(orderNo, openId, rebateId, 0L, BigDecimal.ZERO, 1, realPay, realPay, totamt, noDiscount, new Date(), 1,nickname,headimgurl);
		int size = orderMapper.insertSelective(record);
		if(size<=0){
			resultMap.put("errcode", SystemConstant.ERROR);
			logger.info(">>>>>>>>>>>>>>>>>>>>浦发QQ保存订单失败");
			return resultMap;
		}
		
		String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
		String notifyUrl = ConstantsConfigurer.getProperty(SystemConstant.PF_PAY_QQ_NOTIFYURL);
		String MCH_ID = ConstantsConfigurer.getProperty(SystemConstant.PF_MCH_ID);
		String PAY_KEY = ConstantsConfigurer.getProperty(SystemConstant.PF_PAY_KEY);
		
		String noncestr = Sha1Util.getNonceStr();
		int fee = realPay.multiply(new BigDecimal(100)).intValue();
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("service","pay.tenpay.jspay");
		packageParams.put("mch_id", MCH_ID);
		packageParams.put("out_trade_no", orderNo);
		packageParams.put("body", storeName+"-移动支付");
		packageParams.put("total_fee", String.valueOf(fee));
		packageParams.put("mch_create_ip", request.getRemoteAddr());
		packageParams.put("notify_url", notifyUrl);
		packageParams.put("nonce_str", noncestr);
		String sign = createSign(packageParams, PAY_KEY);;
		packageParams.put("sign", sign);
		String requestXML = XMLUtil.getRequestXml(packageParams);
		String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
		Map<String, String> notifyParamsMap =XMLUtil.doXMLParse(result);
		logger.info(">>>>>>>>>>>>>>>>>>>>浦发QQ下单>>>>>"+result);
		if (notifyParamsMap.get("status").equals("0")&&notifyParamsMap.get("result_code").equals("0")) {
			
			String jsonString = notifyParamsMap.get("pay_info");
			Map maps = (Map) JSONObject.parse(jsonString);
			resultMap.put("errcode", "0000");
//			resultMap.put("tradeNO", maps.get("tradeNO"));
			resultMap.put("orderId",record.getId().toString());
			return resultMap;
		}else{
			resultMap.put("errcode", "80000");
			return resultMap;
		}
	}
	
	/**
	 * 浦发银行的支付宝支付 jspay 回调通知
	 */
	public  Integer updateCheckNotifyAlipayPF(Map<String,String> notifyParamsMap)throws Exception {
		
		try{
			if(notifyParamsMap == null){
				logger.info(">>>>>>>>>>>>>>>>>>浦发支付宝支付回调 参数 is null");
				// response.getWriter().print("fail");
				return 0;
			}
			logger.info(">>>>>>>>>>>>>>>>>>浦发支付宝支付回调 参数" + notifyParamsMap.toString());
			
			if (!"0".equals(notifyParamsMap.get("status"))) {
				logger.info(">>>>>>>>>>>>>>>>>>浦发支付宝支付回调，通信异常："+notifyParamsMap.toString());
				//response.getWriter().print("fail");
				return 0;
			}
			if (!"0".equals(notifyParamsMap.get("result_code"))) {
				logger.info(">>>>>>>>>>>>>>>>>>浦发支付宝支付回调失败:"+notifyParamsMap.toString());
				//response.getWriter().print("fail");
				return 0;
			}
			//浦发支付宝支付回调通知验签
			if(isPFSign(notifyParamsMap)){
				
				//回调成功 -处理业务状态
				if ("0".equals(notifyParamsMap.get("status")) && "0".equals(notifyParamsMap.get("result_code"))&&"0".equals(notifyParamsMap.get("pay_result"))) {
					
					String tradeNo = notifyParamsMap.get("out_trade_no");		
					String wftOrderNo =notifyParamsMap.get("transaction_id") ;			
					String payTime = notifyParamsMap.get("time_end");				//支付时间
					String openid = notifyParamsMap.get("openid");				//用户支付宝账户名
					
					RebateOrderExample example = new RebateOrderExample();
					example.createCriteria().andOrderNoEqualTo(tradeNo);
					List<RebateOrder> recordlist  = orderMapper.selectByExample(example);
					
					if(recordlist == null || recordlist.size() == 0){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"不存在");
						return 0;
					}
					RebateOrder record =recordlist.get(0);
					if(SystemConstant.PAY_ISPAY.equals(record.getStatus())){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"己支付");
						return 1;
					}else{
						
						StoreRebate rebate = storeRebateMapper.selectByPrimaryKey(record.getRebateId());
						StoreInfo store = storeInfoMapper.selectByPrimaryKey(rebate.getStoreId());
						
						BigDecimal calTotamt =  record.getTotamt();
						BigDecimal rate = new BigDecimal(0); 
						BigDecimal rateMoney = new BigDecimal(0); 
						//查询本店费率
						Integer type = 2;   // 1WX   2ALi   3QQ
						HashMap<String, BigDecimal>  rateMap = fqStoreRateService.calculateOrderRate(record.getTotamt(), store, type);
						
						if(rateMap!=null&&rateMap.size()>0){
							rate = rateMap.get("smallRate");	 //费率
							rateMoney = rateMap.get("rateMoney");	 //手续费
							calTotamt = rateMap.get("calTotamt");	  //扣除手续费结算的金额 
						}
						
						//更新订单  
						RebateOrder uprecord = new RebateOrder();
						uprecord.setId(Long.valueOf(record.getId()));
						uprecord.setStatus(SystemConstant.PAY_ISPAY);
						uprecord.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss", payTime));	//支付时间
						uprecord.setWeixinOrderNo(wftOrderNo);
						uprecord.setTotamt(calTotamt);
						uprecord.setOrderRate(rate);
						uprecord.setRateFee(rateMoney);
						orderMapper.updateByPrimaryKeySelective(uprecord);
						
						Date now = new Date();
						
						FqThirdPay thirdPay = new  FqThirdPay();
						thirdPay.setMoney(record.getRealPay());
						thirdPay.setOrderId(record.getId());
						thirdPay.setOrderNo(record.getOrderNo());
						thirdPay.setPayTime(now);
						thirdPay.setSort(1);
						thirdPay.setThirdOrderNo(wftOrderNo);
						thirdPay.setType(6);//浦发支付宝
						thirdPay.setSellerId(store.getSellerId());
						thirdPay.setTotamt(calTotamt);
						thirdPay.setOrderRate(rate);
						thirdPay.setRateFee(rateMoney);
						
						FqUserInfoExample fuieExample = new FqUserInfoExample();
						fuieExample.createCriteria().andOpenIdEqualTo(record.getOpenId());
						List<FqUserInfo> users = fqUserInfoMapper.selectByExample(fuieExample);
						if (users.size()>0) {
							thirdPay.setUserId(users.get(0).getId());
						}
						thirdPay.setOpenId(openid);
						fqThirdPayMapper.insertSelective(thirdPay);
						
						SellerInfo seller = sellerService.getSellerById(store.getSellerId());
						if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
							Date date0 = BaseUtil.getTimeDate(record.getCreateTime());
							FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
							fqSellerStatementExample.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatementDateEqualTo(date0);
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
								fsstate.setSellerId(store.getSellerId());
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
							example3.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
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
						task.setSellerId(store.getSellerId());
						task.setPushTypeArr(pushSet);
						task.setMoney(thirdPay.getMoney());
						task.setTradeType("alipay");
						task.setTransactionId(tradeNo);
						taskExecutor.execute(task);
						
						
						// 插入对应的统计表 修改数据 
						RebateCash cash = new RebateCash();
						cash.setRebateId(record.getRebateId());
						cash.setTotamtTotal(calTotamt);
						cash.setNeedTotal(record.getNeedPay());
						cash.setRealTotal(record.getRealPay());
						cash.setIntegralTotal(record.getIntegral());
						
						cashMapper.updateStatMoney(cash);
						
						logger.error(">>>>>>>>>>>>>>>>>>订单："+record.getId()+"，回调成功");
						//更新订单成功，返回成功状态
						return 1;
					}
				}
			}else{
				logger.error(">>>>>>>>>>>>>>>>>>回调验签失败notifyParamsMap："+notifyParamsMap.toString());
			}
			return null;
		}catch(Exception e){
			logger.error("updateCheckNotify error", e);
			throw e;
		}
	}
	
	/**
	 * 兴业银行的支付宝支付 jspay 回调通知
	 */
	public  Integer updateCheckNotifyAlipayXY(Map<String,String> notifyParamsMap)throws Exception {
		
		try{
			if(notifyParamsMap == null){
				logger.info(">>>>>>>>>>>>>>>>>>兴业支付宝支付回调 参数 is null");
				// response.getWriter().print("fail");
				return 0;
			}
			logger.info(">>>>>>>>>>>>>>>>>>兴业支付宝支付回调 参数" + notifyParamsMap.toString());
			
			if (!"0".equals(notifyParamsMap.get("status"))) {
				logger.info(">>>>>>>>>>>>>>>>>>兴业支付宝支付回调，通信异常："+notifyParamsMap.toString());
				//response.getWriter().print("fail");
				return 0;
			}
			if (!"0".equals(notifyParamsMap.get("result_code"))) {
				logger.info(">>>>>>>>>>>>>>>>>>兴业支付宝支付回调失败:"+notifyParamsMap.toString());
				//response.getWriter().print("fail");
				return 0;
			}
			logger.info(">>>>>>>>>>>>>>>>>>兴业支付宝支付回调 参数:"+notifyParamsMap.toString());
				//回调成功 -处理业务状态
				if ("0".equals(notifyParamsMap.get("status")) && "0".equals(notifyParamsMap.get("result_code"))&&"0".equals(notifyParamsMap.get("pay_result"))) {
					
					String tradeNo = notifyParamsMap.get("out_trade_no");		
					String wftOrderNo =notifyParamsMap.get("transaction_id") ;			
					String payTime = notifyParamsMap.get("time_end");				//支付时间
					String openid = notifyParamsMap.get("openid");				//用户支付宝账户名
					
					RebateOrderExample example = new RebateOrderExample();
					example.createCriteria().andOrderNoEqualTo(tradeNo);
					List<RebateOrder> recordlist  = orderMapper.selectByExample(example);
					
					if(recordlist == null || recordlist.size() == 0){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"不存在");
						return 0;
					}
					RebateOrder record =recordlist.get(0);
					if(SystemConstant.PAY_ISPAY.equals(record.getStatus())){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"己支付");
						return 1;
					}else{
						
						StoreRebate rebate = storeRebateMapper.selectByPrimaryKey(record.getRebateId());
						StoreInfo store = storeInfoMapper.selectByPrimaryKey(rebate.getStoreId());
						
						BigDecimal calTotamt =  record.getTotamt();
						BigDecimal rate = new BigDecimal(0); 
						BigDecimal rateMoney = new BigDecimal(0); 
						//查询本店费率
						Integer type = 2;   // 1WX   2ALi   3QQ
						HashMap<String, BigDecimal>  rateMap = fqStoreRateService.calculateOrderRate(record.getTotamt(), store, type);
						
						if(rateMap!=null&&rateMap.size()>0){
							rate = rateMap.get("smallRate");	 //费率
							rateMoney = rateMap.get("rateMoney");	 //手续费
							calTotamt = rateMap.get("calTotamt");	  //扣除手续费结算的金额 
						}
						
						//更新订单  
						RebateOrder uprecord = new RebateOrder();
						uprecord.setId(Long.valueOf(record.getId()));
						uprecord.setStatus(SystemConstant.PAY_ISPAY);
						uprecord.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss", payTime));	//支付时间
						uprecord.setWeixinOrderNo(wftOrderNo);
						uprecord.setTotamt(calTotamt);
						uprecord.setOrderRate(rate);
						uprecord.setRateFee(rateMoney);
						orderMapper.updateByPrimaryKeySelective(uprecord);
						
						Date now = new Date();
						
						FqThirdPay thirdPay = new  FqThirdPay();
						thirdPay.setMoney(record.getRealPay());
						thirdPay.setOrderId(record.getId());
						thirdPay.setOrderNo(record.getOrderNo());
						thirdPay.setPayTime(now);
						thirdPay.setSort(98);	//兴业
						thirdPay.setThirdOrderNo(wftOrderNo);
						thirdPay.setType(6);//兴业支付宝
						thirdPay.setSellerId(store.getSellerId());
						thirdPay.setTotamt(calTotamt);
						thirdPay.setOrderRate(rate);
						thirdPay.setRateFee(rateMoney);
						
						FqUserInfoExample fuieExample = new FqUserInfoExample();
						fuieExample.createCriteria().andOpenIdEqualTo(record.getOpenId());
						List<FqUserInfo> users = fqUserInfoMapper.selectByExample(fuieExample);
						if (users.size()>0) {
							thirdPay.setUserId(users.get(0).getId());
						}
						thirdPay.setOpenId(openid);
						fqThirdPayMapper.insertSelective(thirdPay);
						
						SellerInfo seller = sellerService.getSellerById(store.getSellerId());
						if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
							Date date0 = BaseUtil.getTimeDate(record.getCreateTime());
							FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
							fqSellerStatementExample.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatementDateEqualTo(date0).andStateEqualTo(98);
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
								fsstate.setSellerId(store.getSellerId());
								fsstate.setState(98);		//98  兴业银行
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
							example3.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
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
						task.setSellerId(store.getSellerId());
						task.setPushTypeArr(pushSet);
						task.setMoney(thirdPay.getMoney());
						task.setTradeType("alipay");
						task.setTransactionId(tradeNo);
						taskExecutor.execute(task);
						
						
						// 插入对应的统计表 修改数据 
						RebateCash cash = new RebateCash();
						cash.setRebateId(record.getRebateId());
						cash.setTotamtTotal(calTotamt);
						cash.setNeedTotal(record.getNeedPay());
						cash.setRealTotal(record.getRealPay());
						cash.setIntegralTotal(record.getIntegral());
						
						cashMapper.updateStatMoney(cash);
						
						logger.error(">>>>>>>>>>>>>>>>>>订单："+record.getId()+"，回调成功");
						//更新订单成功，返回成功状态
						return 1;
					}
				}
			return null;
		}catch(Exception e){
			logger.error("updateCheckNotify error", e);
			throw e;
		}
	}
	/**
	 * 光大银行的支付宝支付 jspay 回调通知
	 */
	public  Integer updateCheckNotifyAlipayGD(Map<String,String> notifyParamsMap)throws Exception {
		
		try{
			if(notifyParamsMap == null){
				logger.info(">>>>>>>>>>>>>>>>>>光大支付宝支付回调 参数 is null");
				// response.getWriter().print("fail");
				return 0;
			}
			logger.info(">>>>>>>>>>>>>>>>>>光大支付宝支付回调 参数" + notifyParamsMap.toString());
			
			if (!"0".equals(notifyParamsMap.get("status"))) {
				logger.info(">>>>>>>>>>>>>>>>>>光大支付宝支付回调，通信异常："+notifyParamsMap.toString());
				//response.getWriter().print("fail");
				return 0;
			}
			if (!"0".equals(notifyParamsMap.get("result_code"))) {
				logger.info(">>>>>>>>>>>>>>>>>>光大支付宝支付回调失败:"+notifyParamsMap.toString());
				//response.getWriter().print("fail");
				return 0;
			}
			//浦发支付宝支付回调通知验签
			if(isGdSign(notifyParamsMap)){
				
				//回调成功 -处理业务状态
				if ("0".equals(notifyParamsMap.get("status")) && "0".equals(notifyParamsMap.get("result_code"))&&"0".equals(notifyParamsMap.get("pay_result"))) {
					
					String tradeNo = notifyParamsMap.get("out_trade_no");		
					String wftOrderNo =notifyParamsMap.get("transaction_id") ;			
					String payTime = notifyParamsMap.get("time_end");				//支付时间
					String openid = notifyParamsMap.get("openid");				//用户支付宝账户名
					
					RebateOrderExample example = new RebateOrderExample();
					example.createCriteria().andOrderNoEqualTo(tradeNo);
					List<RebateOrder> recordlist  = orderMapper.selectByExample(example);
					
					if(recordlist == null || recordlist.size() == 0){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"不存在");
						return 0;
					}
					RebateOrder record =recordlist.get(0);
					if(SystemConstant.PAY_ISPAY.equals(record.getStatus())){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"己支付");
						return 1;
					}else{
						
						StoreRebate rebate = storeRebateMapper.selectByPrimaryKey(record.getRebateId());
						StoreInfo store = storeInfoMapper.selectByPrimaryKey(rebate.getStoreId());
						
						BigDecimal calTotamt =  record.getTotamt();
						BigDecimal rate = new BigDecimal(0); 
						BigDecimal rateMoney = new BigDecimal(0); 
						//查询本店费率
						Integer type = 2;   // 1WX   2ALi   3QQ
						HashMap<String, BigDecimal>  rateMap = fqStoreRateService.calculateOrderRate(record.getTotamt(), store, type);
						
						if(rateMap!=null&&rateMap.size()>0){
							rate = rateMap.get("smallRate");	 //费率
							rateMoney = rateMap.get("rateMoney");	 //手续费
							calTotamt = rateMap.get("calTotamt");	  //扣除手续费结算的金额 
						}
						
						//更新订单  
						RebateOrder uprecord = new RebateOrder();
						uprecord.setId(Long.valueOf(record.getId()));
						uprecord.setStatus(SystemConstant.PAY_ISPAY);
						uprecord.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss", payTime));	//支付时间
						uprecord.setWeixinOrderNo(wftOrderNo);
						uprecord.setTotamt(calTotamt);
						uprecord.setOrderRate(rate);
						uprecord.setRateFee(rateMoney);
						orderMapper.updateByPrimaryKeySelective(uprecord);
						
						Date now = new Date();
						
						FqThirdPay thirdPay = new  FqThirdPay();
						thirdPay.setMoney(record.getRealPay());
						thirdPay.setOrderId(record.getId());
						thirdPay.setOrderNo(record.getOrderNo());
						thirdPay.setPayTime(now);
						thirdPay.setSort(1);
						thirdPay.setThirdOrderNo(wftOrderNo);
						thirdPay.setType(6);//光大支付宝
						thirdPay.setSellerId(store.getSellerId());
						thirdPay.setTotamt(calTotamt);
						thirdPay.setOrderRate(rate);
						thirdPay.setRateFee(rateMoney);
						
						FqUserInfoExample fuieExample = new FqUserInfoExample();
						fuieExample.createCriteria().andOpenIdEqualTo(record.getOpenId());
						List<FqUserInfo> users = fqUserInfoMapper.selectByExample(fuieExample);
						if (users.size()>0) {
							thirdPay.setUserId(users.get(0).getId());
						}
						thirdPay.setOpenId(openid);
						fqThirdPayMapper.insertSelective(thirdPay);
						
						SellerInfo seller = sellerService.getSellerById(store.getSellerId());
						if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
							Date date0 = BaseUtil.getTimeDate(record.getCreateTime());
							FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
							fqSellerStatementExample.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatementDateEqualTo(date0);
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
								fsstate.setSellerId(store.getSellerId());
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
							example3.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
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
						task.setSellerId(store.getSellerId());
						task.setPushTypeArr(pushSet);
						task.setMoney(thirdPay.getMoney());
						task.setTradeType("alipay");
						task.setTransactionId(tradeNo);
						taskExecutor.execute(task);
						
						
						// 插入对应的统计表 修改数据 
						RebateCash cash = new RebateCash();
						cash.setRebateId(record.getRebateId());
						cash.setTotamtTotal(calTotamt);
						cash.setNeedTotal(record.getNeedPay());
						cash.setRealTotal(record.getRealPay());
						cash.setIntegralTotal(record.getIntegral());
						
						cashMapper.updateStatMoney(cash);
						
						logger.error(">>>>>>>>>>>>>>>>>>订单："+record.getId()+"，回调成功");
						//更新订单成功，返回成功状态
						return 1;
					}
				}
			}else{
				logger.error(">>>>>>>>>>>>>>>>>>回调验签失败notifyParamsMap："+notifyParamsMap.toString());
			}
			return null;
		}catch(Exception e){
			logger.error("updateCheckNotify error", e);
			throw e;
		}
	}
	
	
	
	
	@Override
	public Map<String, Object> insertOrderAndGetGd(String nickname,
			String headimgurl, String openId, long sellerId, String storeName,
			long rebateId, BigDecimal needPay, long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,
			BigDecimal rebate, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		 /*校验订单请求参数 主要针对店铺信息 折扣等是否相符*/
		 Map<String,Object> conditionMap = new HashMap<String,Object>();
		 conditionMap.put("id", rebateId);
		 conditionMap.put("storeId", storeId);
		 conditionMap.put("sellerId", sellerId);
		 conditionMap.put("storeName", storeName);
		 conditionMap.put("rebate", rebate);
		 conditionMap.put("currentTime", new Date());
		 Map<String,Object> rebateMap = storeRebateMapper.selectRebateDetailById(conditionMap);
		 if(null == rebateMap){
			 resultMap.put("errcode", SystemConstant.DATA_DIFF);
	         logger.info(">>>>>>>>>>>>>>>>>>>>订单参数打折信息不一致："+rebateId+","+storeId+","+sellerId+","+storeName+","+rebate);
			 return resultMap;
		 }
		 
		 /*根据参数创建订单*/
		
		 //随机生成订单号
		 String orderNo = OrderUtil.getOrderNo(SystemConstant.GD_PAY_TYPE);
		 //插入订单表
		 RebateOrder record = new RebateOrder(orderNo, openId, rebateId, 0, BigDecimal.ZERO, 2, needPay, realPay, totamt, noDiscount, new Date(), 1,nickname,headimgurl);

		 orderMapper.insertSelective(record);
		 
		 String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
			String notifyUrl = ConstantsConfigurer.getProperty(SystemConstant.GD_PAY_NOTIFYURL);
			
			String MCH_ID = ConstantsConfigurer.getProperty(SystemConstant.GD_MCH_ID);
			String PAY_KEY = ConstantsConfigurer.getProperty(SystemConstant.GD_PAY_KEY);
			
			 String noncestr = Sha1Util.getNonceStr();
			 int fee = realPay.multiply(new BigDecimal(100)).intValue();
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
			packageParams.put("service","pay.weixin.jspay" );
			packageParams.put("mch_id", MCH_ID);
			packageParams.put("is_raw", "1");
			packageParams.put("out_trade_no", orderNo);
			packageParams.put("body", storeName+"-移动支付");
			packageParams.put("sub_openid", record.getOpenId());
			
			packageParams.put("total_fee", String.valueOf(fee));
			packageParams.put("mch_create_ip", request.getRemoteAddr());
			packageParams.put("notify_url", notifyUrl);
			packageParams.put("nonce_str", noncestr);
			String sign = createSign(packageParams, PAY_KEY);;
			packageParams.put("sign", sign);
			String requestXML = XMLUtil.getRequestXml(packageParams);
			
			String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
			 logger.info(">>>>>>>>GD WAP PAY WX>>>>>>>>>>>>："+result);
			Map<String, String>notifyParamsMap =XMLUtil.doXMLParse(result);
			
			if (notifyParamsMap.get("status").equals("0")&&notifyParamsMap.get("result_code").equals("0")) {
				
				String jsonString = notifyParamsMap.get("pay_info");
				resultMap.put("errcode", "0000");
				resultMap.put("jsonstr", jsonString);
				resultMap.put("orderId",record.getId().toString());
				return resultMap;
			}else{
				resultMap.put("errcode", "80000");
				return resultMap;
			}
	}

	@Override
	public Map<String, Object> insertOrderAndGetWft(String nickname,
			String headimgurl, String openId, long sellerId, String storeName,
			long rebateId, BigDecimal needPay, long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,
			BigDecimal rebate, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		 /*校验订单请求参数 主要针对店铺信息 折扣等是否相符*/
		 Map<String,Object> conditionMap = new HashMap<String,Object>();
		 conditionMap.put("id", rebateId);
		 conditionMap.put("storeId", storeId);
		 conditionMap.put("sellerId", sellerId);
		 conditionMap.put("storeName", storeName);
		 conditionMap.put("rebate", rebate);
		 conditionMap.put("currentTime", new Date());
		 Map<String,Object> rebateMap = storeRebateMapper.selectRebateDetailById(conditionMap);
		 if(null == rebateMap){
			 resultMap.put("errcode", SystemConstant.DATA_DIFF);
	         logger.info(">>>>>>>>>>>>>>>>>>>>订单参数打折信息不一致："+rebateId+","+storeId+","+sellerId+","+storeName+","+rebate);
			 return resultMap;
		 }
		 
		 /*根据参数创建订单*/
		
		 //随机生成订单号
		 String orderNo = OrderUtil.getOrderNo(SystemConstant.WFT_PAY_TYPE);
		 //插入订单表
		 RebateOrder record = new RebateOrder(orderNo, openId, rebateId, 0, BigDecimal.ZERO, 2, needPay, realPay, totamt, noDiscount, new Date(), 1,nickname,headimgurl);

		 orderMapper.insertSelective(record);
		 
		 String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
			String notifyUrl = ConstantsConfigurer.getProperty(SystemConstant.WFT_PAY_NOTIFYURL);
			
			String MCH_ID = ConstantsConfigurer.getProperty(SystemConstant.WFT_MCH_ID);
			String PAY_KEY = ConstantsConfigurer.getProperty(SystemConstant.WFT_PAY_KEY);
			
			 String noncestr = Sha1Util.getNonceStr();
			 int fee = realPay.multiply(new BigDecimal(100)).intValue();
			SortedMap<String, String> packageParams = new TreeMap<String, String>();
			packageParams.put("service","pay.weixin.jspay" );
			packageParams.put("mch_id", MCH_ID);
			packageParams.put("is_raw", "1");
			packageParams.put("out_trade_no", orderNo);
			packageParams.put("body", storeName+"-移动支付");
			packageParams.put("sub_openid", record.getOpenId());
			
			packageParams.put("total_fee", String.valueOf(fee));
			packageParams.put("mch_create_ip", request.getRemoteAddr());
			packageParams.put("notify_url", notifyUrl);
			packageParams.put("nonce_str", noncestr);
			String sign = createSign(packageParams, PAY_KEY);;
			packageParams.put("sign", sign);
			String requestXML = XMLUtil.getRequestXml(packageParams);
			
			String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
			
			Map<String, String>notifyParamsMap =XMLUtil.doXMLParse(result);
			
			if (notifyParamsMap.get("status").equals("0")&&notifyParamsMap.get("result_code").equals("0")) {
				
				String jsonString = notifyParamsMap.get("pay_info");
				resultMap.put("errcode", "0000");
				resultMap.put("jsonstr", jsonString);
				resultMap.put("orderId",record.getId().toString());
				return resultMap;
			}else{
				resultMap.put("errcode", "80000");
				return resultMap;
			}
	}
	
	/**
	 * 浦发银行 微信支付
	 */
	public Map<String, Object> insertOrderAndGetWXPF(Long couponId,String nickname,String headimgurl, String openId, long sellerId, String storeName,
												long rebateId, BigDecimal needPay, long storeId,BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,BigDecimal rebate, HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		 Map<String,Object> resultMap = new HashMap<String,Object>();
		
		 /*校验订单请求参数 主要针对店铺信息 折扣等是否相符*/
		 Map<String,Object> conditionMap = new HashMap<String,Object>();
		 conditionMap.put("id", rebateId);
		 conditionMap.put("storeId", storeId);
		 conditionMap.put("sellerId", sellerId);
		 conditionMap.put("storeName", storeName);
		 conditionMap.put("rebate", rebate);
		 conditionMap.put("currentTime", new Date());
		 Map<String,Object> rebateMap = storeRebateMapper.selectRebateDetailById(conditionMap);
		 if(null == rebateMap){
			 resultMap.put("errcode", SystemConstant.DATA_DIFF);
	         logger.info(">>>>>>>>>>>>>>>>>>>>订单参数打折信息不一致："+rebateId+","+storeId+","+sellerId+","+storeName+","+rebate);
			 return resultMap;
		 }
		 CardCoupon card = null;
		 //校验卡券信息
		 if(couponId!=null){
			 //查询当前用户的该卡券信息
			  card  = cardCouponMapper.selectByPrimaryKey(couponId);
			 if(!openId.equals(card.getOpenId())){ //使用的卡券不是当前用户的
				 resultMap.put("errcode", SystemConstant.DATA_DIFF);
		         logger.info(">>>>>>>>>>>>>>>>>>>>订单参数使用卡券"+couponId+"不一致：卡券拥有者="+card.getOpenId()+" 使用者="+openId);
				 return resultMap;
			 }
			 if(storeId!=card.getStoreId()){ //不是本店的优惠券
				 resultMap.put("errcode", SystemConstant.DATA_DIFF);
		         logger.info(">>>>>>>>>>>>>>>>>>>>订单参数不是本店的优惠券"+storeId+" 卡券的storeId="+card.getStoreId());
				 return resultMap;
			 }
			 if(new Date().getTime()<card.getValidityStarttime().getTime() || new Date().getTime()>card.getValidityEndtime().getTime()){
				 resultMap.put("errcode", SystemConstant.DATA_DIFF);
		         logger.info(">>>>>>>>>>>>>>>>>>>>优惠券不在使用有效期内id="+couponId+" 当前用户="+openId);
				 return resultMap;
			 }
			 
			 if(card.getStatusCfg()!=0){
				 resultMap.put("errcode", SystemConstant.DATA_DIFF);
		         logger.info(">>>>>>>>>>>>>>>>>>>>订单参数使用卡券"+couponId+" 已经被使用");
				 return resultMap;
			 }
			 
			Integer couponCfg = card.getTemplateCouponCfg();
			Double tempPay = 0.00;
			//1:代金券   2:折扣券
			if(couponCfg==1){
				BigDecimal discountPay = new BigDecimal(card.getTemplateCouponAmount());
				 tempPay = needPay.subtract(discountPay).doubleValue();   
			}else if(couponCfg==1){
				BigDecimal discount = new BigDecimal(card.getTemplateCouponAmount());//折扣 0-100 之间的整数
				discount =  discount.divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);  //转成小数 示例0.85
				 tempPay = needPay.multiply(discount).doubleValue();   
			}
			
			if(tempPay>0){
				if(realPay.doubleValue()!=tempPay){ //计算出的实际金额和前台传的实际金额不符
					 resultMap.put("errcode", SystemConstant.DATA_DIFF);
			         logger.info(">>>>>>>>>>>>>>>>>>>>订单实际金额不一致：计算出金额="+tempPay+" 前台金额="+realPay);
					 return resultMap;
				}
			}
			
		 }
		 
		 //设置卡券已使用
		 if(couponId != null){
			  card = new CardCoupon();
			 card.setStatusCfg(1);
			 card.setId(couponId);
			 card.setValidateCfg(1);
			 card.setValidateDate(new Date());
			 cardCouponMapper.updateByPrimaryKeySelective(card);
		 }else{
			 couponId = 0l;
		 }
		 
		 /*根据参数创建订单*/
		
		 //随机生成订单号
		 String orderNo = OrderUtil.getOrderNo(SystemConstant.PF_WX_PAY_TYPE);
		 //插入订单表
		 RebateOrder record = new RebateOrder(orderNo, openId, rebateId, couponId, BigDecimal.ZERO, 2, needPay, realPay, totamt, noDiscount, new Date(), 1,nickname,headimgurl);

		if(realPay.doubleValue()==0){   //0元支付
			    //设置订单已支付
				record.setStatus(SystemConstant.PAY_ISPAY);
				record.setPayTime(new Date());	//支付时间
				orderMapper.insertSelective(record);
				
				FqThirdPay thirdPay = new  FqThirdPay();
				thirdPay.setMoney(realPay);
				thirdPay.setOrderId(record.getId());
				thirdPay.setOrderNo(orderNo);
				thirdPay.setPayTime(record.getPayTime());
				thirdPay.setSort(1);
				thirdPay.setThirdOrderNo("");
				thirdPay.setType(88); //完全卡券代金支付
				thirdPay.setSellerId(sellerId);
				thirdPay.setTotamt(totamt);
				FqUserInfoExample fuieExample = new FqUserInfoExample();
				fuieExample.createCriteria().andOpenIdEqualTo(openId);
				List<FqUserInfo> users = fqUserInfoMapper.selectByExample(fuieExample);
				if (users.size()>0) {
					thirdPay.setUserId(users.get(0).getId());
				}
				thirdPay.setOpenId(openId);
				fqThirdPayMapper.insertSelective(thirdPay);
				
				SellerInfo seller = sellerService.getSellerById(sellerId);
				if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
					Date date0 = BaseUtil.getTimeDate(record.getCreateTime());
					FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
					fqSellerStatementExample.createCriteria().andSellerIdEqualTo(sellerId).andStatementDateEqualTo(date0);
					List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
					FqSellerStatement fsstate = null;
					if (fsslist.size()>0) {
						fsstate = fsslist.get(0);
						fsstate.setTotalMoney(fsstate.getTotalMoney().add(realPay));
						fsstate.setTotalNum(fsstate.getTotalNum()+1);
						fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
					}else{
						fsstate = new FqSellerStatement();
						fsstate.setCreateTime(new Date());
						fsstate.setStatementDate(date0);
						fsstate.setSellerId(sellerId);
						fsstate.setState(0);
						fsstate.setTotalMoney(realPay);
						fsstate.setTotalNum(1);
						StoreInfo store = storeService.queryStoreInfoBySeller(sellerId);
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(date0);
						calendar.add(Calendar.DATE, store.getStatementPeriod());
						fsstate.setPeriodDate(calendar.getTime());
						fqSellerStatementMapper.insertSelective(fsstate);
					}
				}else{
					FqStoreCreditExample example3 = new FqStoreCreditExample();
					example3.createCriteria().andSellerIdEqualTo(sellerId).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(new Date()).andEndTimeGreaterThanOrEqualTo(new Date());
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
				//推送信息
				HashSet<Integer> pushSet = new HashSet<Integer>();
				pushSet.add(1);
				PushPayInfoTask task = new PushPayInfoTask();
				task.setSellerId(sellerId);
				task.setPushTypeArr(pushSet);
				task.setMoney(thirdPay.getMoney());
				task.setTradeType("coupon_"+card.getTemplateCouponAmount());
				task.setTransactionId(card.getCode());
				task.setOpenId(thirdPay.getOpenId());
				task.setPayTime(thirdPay.getPayTime());
				taskExecutor.execute(task);
				
				// 插入对应的统计表 修改数据 
				RebateCash cash = new RebateCash();
				cash.setRebateId(record.getRebateId());
				cash.setTotamtTotal(record.getTotamt());
				cash.setNeedTotal(record.getNeedPay());
				cash.setRealTotal(record.getRealPay());
				cash.setIntegralTotal(record.getIntegral());
				
				cashMapper.updateStatMoney(cash);
				logger.error(">>>>>>>>>>>>>>>>>>订单："+record.getId()+"，0元支付成功,cardNo="+card.getCode());
				//更新订单成功，返回成功状态
				resultMap.put("errcode", "00009527"); //直接返回成功界面
				resultMap.put("orderId",record.getId().toString());
				return resultMap;
		 }else{
			 orderMapper.insertSelective(record);
			 
			 String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
			 String notifyUrl = ConstantsConfigurer.getProperty(SystemConstant.PF_PAY_WX_NOTIFYURL);
			 String MCH_ID = ConstantsConfigurer.getProperty(SystemConstant.PF_MCH_ID);
			 String PAY_KEY = ConstantsConfigurer.getProperty(SystemConstant.PF_PAY_KEY);
			 
			 String noncestr = Sha1Util.getNonceStr();
			 int fee = realPay.multiply(new BigDecimal(100)).intValue();
			 SortedMap<String, String> packageParams = new TreeMap<String, String>();
			 packageParams.put("service","pay.weixin.jspay" );
			 packageParams.put("mch_id", MCH_ID);
			 packageParams.put("is_raw", "1");
			 packageParams.put("out_trade_no", orderNo);
			 packageParams.put("body", storeName+"-移动支付");
			 packageParams.put("sub_openid", record.getOpenId());
			 
			 packageParams.put("total_fee", String.valueOf(fee));
			 packageParams.put("mch_create_ip", request.getRemoteAddr());
			 packageParams.put("notify_url", notifyUrl);
			 packageParams.put("nonce_str", noncestr);
			 String sign = createSign(packageParams, PAY_KEY);;
			 packageParams.put("sign", sign);
			 String requestXML = XMLUtil.getRequestXml(packageParams);
			 
			 String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
			 logger.info(result);
			 Map<String, String>notifyParamsMap =XMLUtil.doXMLParse(result);
			 
			 if (notifyParamsMap.get("status").equals("0")&&notifyParamsMap.get("result_code").equals("0")) {
				 
				 String jsonString = notifyParamsMap.get("pay_info");
				 resultMap.put("errcode", "0000");
				 resultMap.put("jsonstr", jsonString);
				 resultMap.put("orderId",record.getId().toString());
				 return resultMap;
			 }else{
				 resultMap.put("errcode", "80000");
				 return resultMap;
			 }
		 }
		 
	}
	
	/**
	 * 兴业银行的 微信支付
	 */
	public Map<String, Object> insertOrderAndGetWXXY(Long couponId,String nickname,String headimgurl, String openId, long sellerId, String storeName,
												long rebateId, BigDecimal needPay, long storeId,BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,BigDecimal rebate, HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		 Map<String,Object> resultMap = new HashMap<String,Object>();
		
		 /*校验订单请求参数 主要针对店铺信息 折扣等是否相符*/
		 Map<String,Object> conditionMap = new HashMap<String,Object>();
		 conditionMap.put("id", rebateId);
		 conditionMap.put("storeId", storeId);
		 conditionMap.put("sellerId", sellerId);
		 conditionMap.put("storeName", storeName);
		 conditionMap.put("rebate", rebate);
		 conditionMap.put("currentTime", new Date());
		 Map<String,Object> rebateMap = storeRebateMapper.selectRebateDetailById(conditionMap);
		 if(null == rebateMap){
			 resultMap.put("errcode", SystemConstant.DATA_DIFF);
	         logger.info(">>>>>>>>>>>>>>>>>>>>订单参数打折信息不一致："+rebateId+","+storeId+","+sellerId+","+storeName+","+rebate);
			 return resultMap;
		 }
		 CardCoupon card = null;
		 //校验卡券信息
		 if(couponId!=null){
			 //查询当前用户的该卡券信息
			  card  = cardCouponMapper.selectByPrimaryKey(couponId);
			 if(!openId.equals(card.getOpenId())){ //使用的卡券不是当前用户的
				 resultMap.put("errcode", SystemConstant.DATA_DIFF);
		         logger.info(">>>>>>>>>>>>>>>>>>>>订单参数使用卡券"+couponId+"不一致：卡券拥有者="+card.getOpenId()+" 使用者="+openId);
				 return resultMap;
			 }
			 if(storeId!=card.getStoreId()){ //不是本店的优惠券
				 resultMap.put("errcode", SystemConstant.DATA_DIFF);
		         logger.info(">>>>>>>>>>>>>>>>>>>>订单参数不是本店的优惠券"+storeId+" 卡券的storeId="+card.getStoreId());
				 return resultMap;
			 }
			 if(new Date().getTime()<card.getValidityStarttime().getTime() || new Date().getTime()>card.getValidityEndtime().getTime()){
				 resultMap.put("errcode", SystemConstant.DATA_DIFF);
		         logger.info(">>>>>>>>>>>>>>>>>>>>优惠券不在使用有效期内id="+couponId+" 当前用户="+openId);
				 return resultMap;
			 }
			 
			 if(card.getStatusCfg()!=0){
				 resultMap.put("errcode", SystemConstant.DATA_DIFF);
		         logger.info(">>>>>>>>>>>>>>>>>>>>订单参数使用卡券"+couponId+" 已经被使用");
				 return resultMap;
			 }
			 
			Integer couponCfg = card.getTemplateCouponCfg();
			Double tempPay = 0.00;
			//1:代金券   2:折扣券
			if(couponCfg==1){
				BigDecimal discountPay = new BigDecimal(card.getTemplateCouponAmount());
				 tempPay = needPay.subtract(discountPay).doubleValue();   
			}else if(couponCfg==1){
				BigDecimal discount = new BigDecimal(card.getTemplateCouponAmount());//折扣 0-100 之间的整数
				discount =  discount.divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);  //转成小数 示例0.85
				 tempPay = needPay.multiply(discount).doubleValue();   
			}
			
			if(tempPay>0){
				if(realPay.doubleValue()!=tempPay){ //计算出的实际金额和前台传的实际金额不符
					 resultMap.put("errcode", SystemConstant.DATA_DIFF);
			         logger.info(">>>>>>>>>>>>>>>>>>>>订单实际金额不一致：计算出金额="+tempPay+" 前台金额="+realPay);
					 return resultMap;
				}
			}
			
		 }
		 
		 //设置卡券已使用
		 if(couponId != null){
			  card = new CardCoupon();
			 card.setStatusCfg(1);
			 card.setId(couponId);
			 card.setValidateCfg(1);
			 card.setValidateDate(new Date());
			 cardCouponMapper.updateByPrimaryKeySelective(card);
		 }else{
			 couponId = 0l;
		 }
		 
		 /*根据参数创建订单*/
		
		 //随机生成订单号
		 String orderNo = OrderUtil.getOrderNo(SystemConstant.XY_WX_PAY_TYPE);
		 //插入订单表
		 RebateOrder record = new RebateOrder(orderNo, openId, rebateId, couponId, BigDecimal.ZERO, 2, needPay, realPay, totamt, noDiscount, new Date(), 1,nickname,headimgurl);

		if(realPay.doubleValue()==0){   //0元支付
			    //设置订单已支付
				record.setStatus(SystemConstant.PAY_ISPAY);
				record.setPayTime(new Date());	//支付时间
				orderMapper.insertSelective(record);
				
				FqThirdPay thirdPay = new  FqThirdPay();
				thirdPay.setMoney(realPay);
				thirdPay.setOrderId(record.getId());
				thirdPay.setOrderNo(orderNo);
				thirdPay.setPayTime(record.getPayTime());
				thirdPay.setSort(1);
				thirdPay.setThirdOrderNo("");
				thirdPay.setType(88); //完全卡券代金支付
				thirdPay.setSellerId(sellerId);
				thirdPay.setTotamt(totamt);
				FqUserInfoExample fuieExample = new FqUserInfoExample();
				fuieExample.createCriteria().andOpenIdEqualTo(openId);
				List<FqUserInfo> users = fqUserInfoMapper.selectByExample(fuieExample);
				if (users.size()>0) {
					thirdPay.setUserId(users.get(0).getId());
				}
				thirdPay.setOpenId(openId);
				fqThirdPayMapper.insertSelective(thirdPay);
				
				SellerInfo seller = sellerService.getSellerById(sellerId);
				if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
					Date date0 = BaseUtil.getTimeDate(record.getCreateTime());
					FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
					fqSellerStatementExample.createCriteria().andSellerIdEqualTo(sellerId).andStatementDateEqualTo(date0);
					List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
					FqSellerStatement fsstate = null;
					if (fsslist.size()>0) {
						fsstate = fsslist.get(0);
						fsstate.setTotalMoney(fsstate.getTotalMoney().add(realPay));
						fsstate.setTotalNum(fsstate.getTotalNum()+1);
						fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
					}else{
						fsstate = new FqSellerStatement();
						fsstate.setCreateTime(new Date());
						fsstate.setStatementDate(date0);
						fsstate.setSellerId(sellerId);
						fsstate.setState(0);
						fsstate.setTotalMoney(realPay);
						fsstate.setTotalNum(1);
						StoreInfo store = storeService.queryStoreInfoBySeller(sellerId);
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(date0);
						calendar.add(Calendar.DATE, store.getStatementPeriod());
						fsstate.setPeriodDate(calendar.getTime());
						fqSellerStatementMapper.insertSelective(fsstate);
					}
				}else{
					FqStoreCreditExample example3 = new FqStoreCreditExample();
					example3.createCriteria().andSellerIdEqualTo(sellerId).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(new Date()).andEndTimeGreaterThanOrEqualTo(new Date());
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
				//推送信息
				HashSet<Integer> pushSet = new HashSet<Integer>();
				pushSet.add(1);
				PushPayInfoTask task = new PushPayInfoTask();
				task.setSellerId(sellerId);
				task.setPushTypeArr(pushSet);
				task.setMoney(thirdPay.getMoney());
				task.setTradeType("coupon_"+card.getTemplateCouponAmount());
				task.setTransactionId(card.getCode());
				task.setOpenId(thirdPay.getOpenId());
				task.setPayTime(thirdPay.getPayTime());
				taskExecutor.execute(task);
				
				// 插入对应的统计表 修改数据 
				RebateCash cash = new RebateCash();
				cash.setRebateId(record.getRebateId());
				cash.setTotamtTotal(record.getTotamt());
				cash.setNeedTotal(record.getNeedPay());
				cash.setRealTotal(record.getRealPay());
				cash.setIntegralTotal(record.getIntegral());
				
				cashMapper.updateStatMoney(cash);
				logger.error(">>>>>>>>>>>>>>>>>>订单："+record.getId()+"，0元支付成功,cardNo="+card.getCode());
				//更新订单成功，返回成功状态
				resultMap.put("errcode", "00009527"); //直接返回成功界面
				resultMap.put("orderId",record.getId().toString());
				return resultMap;
		 }else{
			 orderMapper.insertSelective(record);
			 
			 String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
			 String notifyUrl = ConstantsConfigurer.getProperty(SystemConstant.XY_PAY_WX_NOTIFYURL);
//			 String MCH_ID = ConstantsConfigurer.getProperty(SystemConstant.XY_MCH_ID);
			 String MCH_ID = authenticationChnnelService.findByXyAuthcationInfo(storeId).getXyMerchantNum();
			 String PAY_KEY = authenticationChnnelService.findByXyAuthcationInfo(storeId).getXyBankKey();
			 
			 String noncestr = Sha1Util.getNonceStr();
			 int fee = realPay.multiply(new BigDecimal(100)).intValue();
			 SortedMap<String, String> packageParams = new TreeMap<String, String>();
			 packageParams.put("service","pay.weixin.jspay" );
			 packageParams.put("mch_id", MCH_ID);
//			 packageParams.put("sub_mch_id", SUB_MCH_ID);
			 packageParams.put("is_raw", "1");
			 packageParams.put("out_trade_no", orderNo);
			 packageParams.put("body", storeName+"-移动支付");
			 packageParams.put("sub_openid", record.getOpenId());
			 
			 packageParams.put("total_fee", String.valueOf(fee));
			 packageParams.put("mch_create_ip", request.getRemoteAddr());
			 packageParams.put("notify_url", notifyUrl);
			 packageParams.put("nonce_str", noncestr);
			 String sign = createSign(packageParams, PAY_KEY);;
			 packageParams.put("sign", sign);
			 String requestXML = XMLUtil.getRequestXml(packageParams);
			 logger.info(">>>>>>>>>>>>>>>>>>兴业微信支付请求参数:" + requestXML);
			 String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
			 logger.info(result);
			 Map<String, String>notifyParamsMap =XMLUtil.doXMLParse(result);
			 logger.info(">>>>>>>>>>>>>>>>>>兴业微信支付响应参数:" + notifyParamsMap.toString());
			 if (notifyParamsMap.get("status").equals("0")&&notifyParamsMap.get("result_code").equals("0")) {
				 
				 String jsonString = notifyParamsMap.get("pay_info");
				 resultMap.put("errcode", "0000");
				 resultMap.put("jsonstr", jsonString);
				 resultMap.put("orderId",record.getId().toString());
				 resultMap.put("orderNo",orderNo);
				 return resultMap;
			 }else{
				 resultMap.put("errcode", "80000");
				 return resultMap;
			 }
		 }
		 
	}
	
	/**
	 * 浦发银行微信支付异步回调通知
	 */
	public  Integer updateCheckNotifyWXPF(Map<String,String> notifyParamsMap,HttpServletRequest request ,HttpServletResponse response)throws Exception {
		
		try{
			response.setContentType("text/html;charset=UTF-8");
			if(notifyParamsMap == null){
				logger.info(">>>>>>>>>>>>>>>>>>浦发微信支付回调 参数 is null");
				// response.getWriter().print("fail");
				return 0;
			}
			logger.info(">>>>>>>>>>>>>>>>>>浦发微信支付回调 参数" + notifyParamsMap.toString());
			
			if (!"0".equals(notifyParamsMap.get("status"))) {
				logger.info(">>>>>>>>>>>>>>>>>>微信支付回调，通信异常："+notifyParamsMap.toString());
				//response.getWriter().print("fail");
				return 0;
			}
			if (!"0".equals(notifyParamsMap.get("result_code"))) {
				logger.info(">>>>>>>>>>>>>>>>>>浦发微信支付回调失败:"+notifyParamsMap.toString());
				//response.getWriter().print("fail");
				return 0;
			}
			//微信支付回调通知验签
			if(isPFSign(notifyParamsMap)){
				
				//回调成功 -处理业务状态
				if ("0".equals(notifyParamsMap.get("status")) && "0".equals(notifyParamsMap.get("result_code"))&&"0".equals(notifyParamsMap.get("pay_result"))) {
					
					String tradeNo = notifyParamsMap.get("out_trade_no");		
					String wftOrderNo =notifyParamsMap.get("transaction_id") ;			
					String payTime = notifyParamsMap.get("time_end");				//支付时间
					
					
					RebateOrderExample example = new RebateOrderExample();
					example.createCriteria().andOrderNoEqualTo(tradeNo);
					List<RebateOrder> recordlist  = orderMapper.selectByExample(example);
					
					if(recordlist == null || recordlist.size() == 0){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"不存在");
						return 0;
					}
					RebateOrder record =recordlist.get(0);
					if(SystemConstant.PAY_ISPAY.equals(record.getStatus())){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"己支付");
						return 1;
					}else{
						
						StoreRebate rebate = storeRebateMapper.selectByPrimaryKey(record.getRebateId());
						StoreInfo store = storeInfoMapper.selectByPrimaryKey(rebate.getStoreId());
						
						BigDecimal calTotamt =  record.getTotamt();
						BigDecimal rate = new BigDecimal(0); 
						BigDecimal rateMoney = new BigDecimal(0); 
						//查询本店费率
						Integer type = 1;   // 1WX   2ALi   3QQ
						HashMap<String, BigDecimal>  rateMap = fqStoreRateService.calculateOrderRate(record.getTotamt(), store, type);
						
						if(rateMap!=null&&rateMap.size()>0){
							rate = rateMap.get("smallRate");	 //费率
							rateMoney = rateMap.get("rateMoney");	 //手续费
							calTotamt = rateMap.get("calTotamt");	  //扣除手续费结算的金额 
						}
						
						String bankTypeCode = notifyParamsMap.get("bank_type");
						
						//更新订单  
						RebateOrder uprecord = new RebateOrder();
						uprecord.setId(Long.valueOf(record.getId()));
						uprecord.setStatus(SystemConstant.PAY_ISPAY);
						uprecord.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss", payTime));	//支付时间
						uprecord.setWeixinOrderNo(wftOrderNo);
						uprecord.setTotamt(calTotamt);
						uprecord.setOrderRate(rate);
						uprecord.setRateFee(rateMoney);
						orderMapper.updateByPrimaryKeySelective(uprecord);

						Date now = new Date();
					
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
						thirdPay.setMoney(record.getRealPay());
						thirdPay.setOrderId(record.getId());
						thirdPay.setOrderNo(record.getOrderNo());
						thirdPay.setPayTime(uprecord.getPayTime());
						thirdPay.setSort(1);
						thirdPay.setThirdOrderNo(wftOrderNo);
						thirdPay.setType(5);
						thirdPay.setSellerId(store.getSellerId());
						thirdPay.setTotamt(calTotamt);
						thirdPay.setOrderRate(rate);
						thirdPay.setRateFee(rateMoney);
						
						thirdPay.setBankType(bankType);
						thirdPay.setBankTypeCode(bankTypeCode);
						FqUserInfoExample fuieExample = new FqUserInfoExample();
						fuieExample.createCriteria().andOpenIdEqualTo(record.getOpenId());
						List<FqUserInfo> users = fqUserInfoMapper.selectByExample(fuieExample);
						if (users.size()>0) {
							thirdPay.setUserId(users.get(0).getId());
						}
						thirdPay.setOpenId(record.getOpenId());
						fqThirdPayMapper.insertSelective(thirdPay);
						
						SellerInfo seller = sellerService.getSellerById(store.getSellerId());
						if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
							Date date0 = BaseUtil.getTimeDate(record.getCreateTime());
							FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
							fqSellerStatementExample.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatementDateEqualTo(date0);
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
								fsstate.setSellerId(store.getSellerId());
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
							example3.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
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
						pushSet.add(1);
						PushPayInfoTask task = new PushPayInfoTask();
						task.setSellerId(store.getSellerId());
						task.setPushTypeArr(pushSet);
						task.setMoney(thirdPay.getMoney());
						task.setTradeType("weixin");
						task.setTransactionId(tradeNo);
						task.setOpenId(thirdPay.getOpenId());
						task.setPayTime(uprecord.getPayTime());
						taskExecutor.execute(task);
						
						// 插入对应的统计表 修改数据 
						RebateCash cash = new RebateCash();
						cash.setRebateId(record.getRebateId());
						cash.setTotamtTotal(calTotamt);
						cash.setNeedTotal(record.getNeedPay());
						cash.setRealTotal(record.getRealPay());
						cash.setIntegralTotal(record.getIntegral());
						
						cashMapper.updateStatMoney(cash);
						
						logger.error(">>>>>>>>>>>>>>>>>>订单："+record.getId()+"，回调成功");
						//更新订单成功，返回成功状态
						return 1;
					}
				}
			}else{
				logger.error(">>>>>>>>>>>>>>>>>>浦发微信回调验签失败notifyParamsMap："+notifyParamsMap.toString());
			}
			return null;
		}catch(Exception e){
			logger.error("浦发微信 error", e);
			throw e;
		}
	}
	
	/**
	 * 兴业银行的微信支付异步回调通知
	 */
	public  Integer updateCheckNotifyWXXY(Map<String,String> notifyParamsMap,HttpServletRequest request ,HttpServletResponse response)throws Exception {
		
		try{
			response.setContentType("text/html;charset=UTF-8");
			if(notifyParamsMap == null){
				logger.info(">>>>>>>>>>>>>>>>>>兴业微信支付回调 参数 is null");
				// response.getWriter().print("fail");
				return 0;
			}
			logger.info(">>>>>>>>>>>>>>>>>>兴业微信支付回调 参数" + notifyParamsMap.toString());
			
			if (!"0".equals(notifyParamsMap.get("status"))) {
				logger.info(">>>>>>>>>>>>>>>>>>兴业微信支付回调，通信异常："+notifyParamsMap.toString());
				//response.getWriter().print("fail");
				return 0;
			}
			if (!"0".equals(notifyParamsMap.get("result_code"))) {
				logger.info(">>>>>>>>>>>>>>>>>>兴业微信支付回调失败:"+notifyParamsMap.toString());
				//response.getWriter().print("fail");
				return 0;
			}
			logger.info(">>>>>>>>>>>>>>>>>>兴业微信支付回调:" + notifyParamsMap.toString());
				//回调成功 -处理业务状态
				if ("0".equals(notifyParamsMap.get("status")) && "0".equals(notifyParamsMap.get("result_code"))&&"0".equals(notifyParamsMap.get("pay_result"))) {
					
					String tradeNo = notifyParamsMap.get("out_trade_no");		
					String wftOrderNo =notifyParamsMap.get("transaction_id") ;			
					String payTime = notifyParamsMap.get("time_end");				//支付时间
					
					
					RebateOrderExample example = new RebateOrderExample();
					example.createCriteria().andOrderNoEqualTo(tradeNo);
					List<RebateOrder> recordlist  = orderMapper.selectByExample(example);
					
					if(recordlist == null || recordlist.size() == 0){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"不存在");
						return 0;
					}
					RebateOrder record =recordlist.get(0);
					if(SystemConstant.PAY_ISPAY.equals(record.getStatus())){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"己支付");
						return 1;
					}else{
						
						StoreRebate rebate = storeRebateMapper.selectByPrimaryKey(record.getRebateId());
						StoreInfo store = storeInfoMapper.selectByPrimaryKey(rebate.getStoreId());
						
						BigDecimal calTotamt =  record.getTotamt();
						BigDecimal rate = new BigDecimal(0); 
						BigDecimal rateMoney = new BigDecimal(0); 
						//查询本店费率
						Integer type = 1;   // 1WX   2ALi   3QQ
						HashMap<String, BigDecimal>  rateMap = fqStoreRateService.calculateOrderRate(record.getTotamt(), store, type);
						
						if(rateMap!=null&&rateMap.size()>0){
							rate = rateMap.get("smallRate");	 //费率
							rateMoney = rateMap.get("rateMoney");	 //手续费
							calTotamt = rateMap.get("calTotamt");	  //扣除手续费结算的金额 
						}
						
						String bankTypeCode = notifyParamsMap.get("bank_type");
						
						//更新订单  
						RebateOrder uprecord = new RebateOrder();
						uprecord.setId(Long.valueOf(record.getId()));
						uprecord.setStatus(SystemConstant.PAY_ISPAY);
						uprecord.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss", payTime));	//支付时间
						uprecord.setWeixinOrderNo(wftOrderNo);
						uprecord.setTotamt(calTotamt);
						uprecord.setOrderRate(rate);
						uprecord.setRateFee(rateMoney);
						orderMapper.updateByPrimaryKeySelective(uprecord);

						Date now = new Date();
					
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
						thirdPay.setMoney(record.getRealPay());
						thirdPay.setOrderId(record.getId());
						thirdPay.setOrderNo(record.getOrderNo());
						thirdPay.setPayTime(uprecord.getPayTime());
						thirdPay.setSort(98);	// 兴业通道
						thirdPay.setThirdOrderNo(wftOrderNo);
						thirdPay.setType(5);	//兴业微信
						thirdPay.setSellerId(store.getSellerId());
						thirdPay.setTotamt(calTotamt);
						thirdPay.setOrderRate(rate);
						thirdPay.setRateFee(rateMoney);
						
						thirdPay.setBankType(bankType);
						thirdPay.setBankTypeCode(bankTypeCode);
						FqUserInfoExample fuieExample = new FqUserInfoExample();
						fuieExample.createCriteria().andOpenIdEqualTo(record.getOpenId());
						List<FqUserInfo> users = fqUserInfoMapper.selectByExample(fuieExample);
						if (users.size()>0) {
							thirdPay.setUserId(users.get(0).getId());
						}
						thirdPay.setOpenId(record.getOpenId());
						fqThirdPayMapper.insertSelective(thirdPay);
						
						SellerInfo seller = sellerService.getSellerById(store.getSellerId());
						if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
							Date date0 = BaseUtil.getTimeDate(record.getCreateTime());
							FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
							fqSellerStatementExample.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatementDateEqualTo(date0).andStateEqualTo(98);
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
								fsstate.setSellerId(store.getSellerId());
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
							example3.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
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
						pushSet.add(1);
						PushPayInfoTask task = new PushPayInfoTask();
						task.setSellerId(store.getSellerId());
						task.setPushTypeArr(pushSet);
						task.setMoney(thirdPay.getMoney());
						task.setTradeType("weixin");
						task.setTransactionId(tradeNo);
						task.setOpenId(thirdPay.getOpenId());
						task.setPayTime(uprecord.getPayTime());
						taskExecutor.execute(task);
						
						// 插入对应的统计表 修改数据 
						RebateCash cash = new RebateCash();
						cash.setRebateId(record.getRebateId());
						cash.setTotamtTotal(calTotamt);
						cash.setNeedTotal(record.getNeedPay());
						cash.setRealTotal(record.getRealPay());
						cash.setIntegralTotal(record.getIntegral());
						
						cashMapper.updateStatMoney(cash);
						
						logger.error(">>>>>>>>>>>>>>>>>>订单："+record.getId()+"，回调成功");
						//更新订单成功，返回成功状态
						return 1;
					}
				}
			return null;
		}catch(Exception e){
			logger.error("兴业微信 error", e);
			throw e;
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


	/**
	 * =========================================================================
	 * 							QQ钱包支付 start
	 * =========================================================================
	 */
	/**
	 * 预下单
	 */
	public Map<String, String> insertOrderAndGetCQPay(String nickname,
			String headimgurl, String openId, long sellerId, String storeName,
			long rebateId, BigDecimal needPay, long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,
			BigDecimal rebate, HttpServletRequest request,HttpServletResponse response) throws Exception {
		 Map<String, String>  resultMap  = new HashMap<>();
		
		 /*校验订单请求参数 主要针对店铺信息 折扣等是否相符*/
		 Map<String,Object> conditionMap = new HashMap<String,Object>();
		 conditionMap.put("id", rebateId);
		 conditionMap.put("storeId", storeId);
		 conditionMap.put("sellerId", sellerId);
		 conditionMap.put("storeName", storeName);
		 conditionMap.put("rebate", rebate);
		 conditionMap.put("currentTime", new Date());
		 Map<String,Object> rebateMap = storeRebateMapper.selectRebateDetailById(conditionMap);
		 if(null == rebateMap){
	         logger.info(">>>>>>>>>>>>>>>>>>>>订单参数打折信息不一致："+rebateId+","+storeId+","+sellerId+","+storeName+","+rebate);
			 return resultMap;
		 }
		 
		 /*根据参数创建订单*/
		
		 //随机生成订单号
		 String orderNo = OrderUtil.getOrderNo(SystemConstant.QQ_PAY_TYPE);
		 //插入订单表
		 RebateOrder record = new RebateOrder(orderNo, openId, rebateId, 0, BigDecimal.ZERO, 7, needPay, realPay, totamt, noDiscount, new Date(), 1,nickname,headimgurl);

		 orderMapper.insertSelective(record);
		 
		String MCH_ID = ConstantsConfigurer.getProperty(SystemConstant.QQ_MCH_ID);
		String NOTIFYURL = ConstantsConfigurer.getProperty(SystemConstant.QQ_PAY_NOTIFYURL);
		String API_KEY = ConstantsConfigurer.getProperty(SystemConstant.QQ_PAY_API_KEY);
		
		 int fee = realPay.multiply(new BigDecimal(100)).intValue();
		
		CQpayMchSpBase obj = new CQpayMchSpBase();
		TreeMap<String, String> map = new TreeMap<>();
		map.put("mch_id", MCH_ID);//商户id
		map.put("api_key", API_KEY);//商户api请求秘钥
		map.put("nonce_str", UUID.randomUUID().toString().toUpperCase().replace("-", "")); //随机请求字符串
		map.put("out_trade_no", orderNo);//测试定义订单号
		map.put("sub_mch_id", MCH_ID);
		map.put("body", storeName.trim()+"-移动支付");
		//map.put("attach","aaa");//附加数据，此数据会在回调通知里返回
		map.put("fee_type", "CNY");
		map.put("notify_url", NOTIFYURL);
		map.put("spbill_create_ip", request.getRemoteAddr());// 用户端实际ip 
		map.put("total_fee", String.valueOf(fee)); //金额分
		map.put("trade_type", "JSAPI"); //公众号支付
		
		
		obj.setRequestMapAndUrl(map, CQpayAPIURL.getUnifiedOrderUrl());
		 resultMap = obj.call();
		 resultMap.put("orderId",record.getId().toString());
		return resultMap;
	}

	/**
	 * 支付回调
	 */
	public boolean updateCheckNotifyCQQ(Map<String, String> notifyParamsMap) {
		try{
			if(notifyParamsMap == null){
				logger.debug(">>>>>>>>>>>>>>>>>>QQ钱包 支付回调 参数 is null");
				return false;
			}
			logger.debug(">>>>>>>>>>>>>>>>>>QQ钱包 支付回调 参数" + notifyParamsMap.toString());
			
			String API_KEY = ConstantsConfigurer.getProperty(SystemConstant.QQ_PAY_API_KEY);
			CQpayMchSpBase cQpayMchSpBase = new CQpayMchSpBase();
			TreeMap<String, String> map = new TreeMap<>();
			map.put("api_key", API_KEY);
			cQpayMchSpBase.setRequestMapAndUrl(map, "");
			
			TreeMap<String, String>  notifyParamsTreeMap = new TreeMap<>(notifyParamsMap);
			boolean signFlag = cQpayMchSpBase.checkSign(notifyParamsTreeMap);
			//微信支付回调通知验签
			if(signFlag){
				//回调成功 -处理业务状态
				if ("SUCCESS".equals(notifyParamsTreeMap.get("trade_state")) && notifyParamsTreeMap.get("transaction_id")!=null) {
					
					String tradeNo = notifyParamsTreeMap.get("out_trade_no");		
					String CQOrderNo =notifyParamsTreeMap.get("transaction_id") ;			
					String payTime = notifyParamsTreeMap.get("time_end");				//支付完成时间
					String bankType = notifyParamsTreeMap.get("bank_type");	
					
					Integer totalFee = Integer.parseInt(notifyParamsTreeMap.get("total_fee"));	  //商户订单总金额
					Integer cashFee = Integer.parseInt(notifyParamsTreeMap.get("cash_fee"));	  //用户本次交易中，实际支付的金额 
					String openid = notifyParamsTreeMap.get("openid");	 // 用户在商户appid下的唯一标识   QQ公众号
					
					
					RebateOrderExample example = new RebateOrderExample();
					example.createCriteria().andOrderNoEqualTo(tradeNo);
					List<RebateOrder> recordlist  = orderMapper.selectByExample(example);
					
					if(recordlist == null || recordlist.size() == 0){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"不存在");
						return false;
					}
					RebateOrder record =recordlist.get(0);
					
					int fee = record.getRealPay().multiply(new BigDecimal(100)).intValue();
					if(fee!=totalFee.intValue()){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"：所需要实际支付的金额不等于用户在钱包中支付的金额");
						return false;
					}
					if(SystemConstant.PAY_ISPAY.equals(record.getStatus())){
						logger.info(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"己支付");
						return true;
					}else{
						
						StoreRebate rebate = storeRebateMapper.selectByPrimaryKey(record.getRebateId());
						StoreInfo store = storeInfoMapper.selectByPrimaryKey(rebate.getStoreId());
						
						BigDecimal calTotamt =  record.getTotamt();
						BigDecimal rate = new BigDecimal(0); 
						BigDecimal rateMoney = new BigDecimal(0); 
						//查询本店费率
						Integer type = 3;   // 1WX   2ALi   3QQ
						HashMap<String, BigDecimal>  rateMap = fqStoreRateService.calculateOrderRate(record.getTotamt(), store, type);
						
						if(rateMap!=null&&rateMap.size()>0){
							rate = rateMap.get("smallRate");	 //费率
							rateMoney = rateMap.get("rateMoney");	 //手续费
							calTotamt = rateMap.get("calTotamt");	  //扣除手续费结算的金额 
						}
						
						//更新订单  
						RebateOrder uprecord = new RebateOrder();
						uprecord.setId(Long.valueOf(record.getId()));
						uprecord.setStatus(SystemConstant.PAY_ISPAY);
						uprecord.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss", payTime));	//支付时间
						uprecord.setWeixinOrderNo(CQOrderNo);
						uprecord.setTotamt(calTotamt);
						uprecord.setOrderRate(rate);
						uprecord.setRateFee(rateMoney);
						orderMapper.updateByPrimaryKeySelective(uprecord);
						Date now = new Date();
						
						FqThirdPay thirdPay = new  FqThirdPay();
						thirdPay.setMoney(record.getRealPay());
						thirdPay.setOrderId(record.getId());
						thirdPay.setOrderNo(record.getOrderNo());
						thirdPay.setPayTime(now);
						thirdPay.setSort(1);
						thirdPay.setThirdOrderNo(CQOrderNo);
						thirdPay.setType(7); //qq钱包支付
						thirdPay.setSellerId(store.getSellerId());
						thirdPay.setTotamt(calTotamt);
						thirdPay.setOrderRate(rate);
						thirdPay.setRateFee(rateMoney);
						
						/*FqUserInfoExample fuieExample = new FqUserInfoExample();
						fuieExample.createCriteria().andOpenIdEqualTo(order.getOpenId());
						List<FqUserInfo> users = fqUserInfoMapper.selectByExample(fuieExample);
						if (users.size()>0) {
							thirdPay.setUserId(users.get(0).getId());
						}*/
						thirdPay.setOpenId(openid);  //设置为qq公众号的openid
						if(bankType.equals("BALANCE")){
							//QQ零钱
							thirdPay.setBankType(1);
						}else if(bankType.contains("CREDIT")){
							//信用卡
							thirdPay.setBankType(2);
						}else if(bankType.contains("DEBIT")){
							//储蓄卡
							thirdPay.setBankType(3);
						}
						thirdPay.setBankTypeCode(bankType);
						
						fqThirdPayMapper.insertSelective(thirdPay);
						
						SellerInfo seller = sellerService.getSellerById(store.getSellerId());
						if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
							Date date0 = BaseUtil.getTimeDate(record.getCreateTime());
							FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
							fqSellerStatementExample.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatementDateEqualTo(date0);
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
								fsstate.setSellerId(store.getSellerId());
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
							example3.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
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
						task.setSellerId(store.getSellerId());
						task.setPushTypeArr(pushSet);
						task.setMoney(thirdPay.getMoney());
						task.setTradeType("qq");
						task.setTransactionId(tradeNo);
						taskExecutor.execute(task);
					
						
						// 插入对应的统计表 修改数据 
						RebateCash cash = new RebateCash();
						cash.setRebateId(record.getRebateId());
						cash.setTotamtTotal(calTotamt);
						cash.setNeedTotal(record.getNeedPay());
						cash.setRealTotal(record.getRealPay());
						cash.setIntegralTotal(record.getIntegral());
						
						cashMapper.updateStatMoney(cash);
						
						logger.error(">>>>>>>>>>>>>>>>>>订单："+record.getId()+"，回调成功");
						//更新订单成功，返回成功状态
						return true;
					}
				}
			}else{
				logger.error(">>>>>>>>>>>>>>>>>>回调验签失败notifyParamsMap："+notifyParamsMap.toString());
			}
			return false;
		}catch(Exception e){
			logger.error("updateCheckNotify error", e);
			e.printStackTrace();
		}
		return false;
	}

	 /**
		 * =========================================================================
		 * 							QQ钱包支付 end
		 * =========================================================================
		 */
		
	@Override
	public Map<String, Object> insertOrderAndGetSZPFWX(Long couponId,
			String nickname, String headimgurl, String openId, long sellerId,
			String storeName, long rebateId, BigDecimal needPay, long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,
			BigDecimal rebate, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		 Map<String,Object> resultMap = new HashMap<String,Object>();
			
		 /*校验订单请求参数 主要针对店铺信息 折扣等是否相符*/
		 Map<String,Object> conditionMap = new HashMap<String,Object>();
		 conditionMap.put("id", rebateId);
		 conditionMap.put("storeId", storeId);
		 conditionMap.put("sellerId", sellerId);
		 conditionMap.put("storeName", storeName);
		 conditionMap.put("rebate", rebate);
		 conditionMap.put("currentTime", new Date());
		 Map<String,Object> rebateMap = storeRebateMapper.selectRebateDetailById(conditionMap);
		 if(null == rebateMap){
			 resultMap.put("errcode", SystemConstant.DATA_DIFF);
	         logger.info(">>>>>>>>>>>>>>>>>>>>订单参数打折信息不一致："+rebateId+","+storeId+","+sellerId+","+storeName+","+rebate);
			 return resultMap;
		 }
		 
		 CardCoupon card = null;
		 
		 //校验卡券信息
		 if(couponId!=null && couponId>0L){
			 //查询当前用户的该卡券信息
			  card  = cardCouponMapper.selectByPrimaryKey(couponId);
			 if(!openId.equals(card.getOpenId())){ //使用的卡券不是当前用户的
				 resultMap.put("errcode", SystemConstant.DATA_DIFF);
		         logger.info(">>>>>>>>>>>>>>>>>>>>订单参数使用卡券"+couponId+"不一致：卡券拥有者="+card.getOpenId()+" 使用者="+openId);
				 return resultMap;
			 }
			 if(storeId!=card.getStoreId()){ //不是本店的优惠券
				 resultMap.put("errcode", SystemConstant.DATA_DIFF);
		         logger.info(">>>>>>>>>>>>>>>>>>>>订单参数不是本店的优惠券"+storeId+" 卡券的storeId="+card.getStoreId());
				 return resultMap;
			 }
			 if(new Date().getTime()<card.getValidityStarttime().getTime() || new Date().getTime()>card.getValidityEndtime().getTime()){
				 resultMap.put("errcode", SystemConstant.DATA_DIFF);
		         logger.info(">>>>>>>>>>>>>>>>>>>>优惠券不在使用有效期内id="+couponId+" 当前用户="+openId);
				 return resultMap;
			 }
			 
			 if(card.getStatusCfg()!=0){
				 resultMap.put("errcode", SystemConstant.DATA_DIFF);
		         logger.info(">>>>>>>>>>>>>>>>>>>>订单参数使用卡券"+couponId+" 已经被使用");
				 return resultMap;
			 }
			 
			Integer couponCfg = card.getTemplateCouponCfg();
			Double tempPay = 0.00;
			//1:代金券   2:折扣券
			if(couponCfg==1){
				BigDecimal discountPay = new BigDecimal(card.getTemplateCouponAmount());
				 tempPay = needPay.subtract(discountPay).doubleValue();   
			}else if(couponCfg==1){
				BigDecimal discount = new BigDecimal(card.getTemplateCouponAmount());//折扣 0-100 之间的整数
				discount =  discount.divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);  //转成小数 示例0.85
				 tempPay = needPay.multiply(discount).doubleValue();   
			}
			
			if(tempPay>0){
				if(realPay.doubleValue()!=tempPay){ //计算出的实际金额和前台传的实际金额不符
					 resultMap.put("errcode", SystemConstant.DATA_DIFF);
			         logger.info(">>>>>>>>>>>>>>>>>>>>订单实际金额不一致：计算出金额="+tempPay+" 前台金额="+realPay);
					 return resultMap;
				}
			}
			
		 }
		 
		 //设置卡券已使用
		 if(couponId != null){
			  card = new CardCoupon();
			 card.setStatusCfg(1);
			 card.setId(couponId);
			 card.setValidateCfg(1);
			 card.setValidateDate(new Date());
			 cardCouponMapper.updateByPrimaryKeySelective(card);
		 }else{
			 couponId = 0l;
		 }
		
		 /*根据参数创建订单*/
		
		 //随机生成订单号
		 String orderNo = OrderUtil.getOrderNo(SystemConstant.SZ_PF_WX_PAY_TYPE);
		 //插入订单表
		 RebateOrder record = new RebateOrder(orderNo, openId, rebateId, 0L, BigDecimal.ZERO, 2, needPay, realPay, totamt, noDiscount, new Date(), 1,nickname,headimgurl);

		 if(realPay.doubleValue()==0){   //0元支付
			    //设置订单已支付
				record.setStatus(SystemConstant.PAY_ISPAY);
				record.setPayTime(new Date());	//支付时间
				orderMapper.insertSelective(record);
				
				FqThirdPay thirdPay = new  FqThirdPay();
				thirdPay.setMoney(realPay);
				thirdPay.setOrderId(record.getId());
				thirdPay.setOrderNo(orderNo);
				thirdPay.setPayTime(record.getPayTime());
				thirdPay.setSort(1);
				thirdPay.setThirdOrderNo("");
				thirdPay.setType(88); //完全卡券代金支付
				thirdPay.setSellerId(sellerId);
				thirdPay.setTotamt(totamt);
				FqUserInfoExample fuieExample = new FqUserInfoExample();
				fuieExample.createCriteria().andOpenIdEqualTo(openId);
				List<FqUserInfo> users = fqUserInfoMapper.selectByExample(fuieExample);
				if (users.size()>0) {
					thirdPay.setUserId(users.get(0).getId());
				}
				thirdPay.setOpenId(openId);
				fqThirdPayMapper.insertSelective(thirdPay);
				
				SellerInfo seller = sellerService.getSellerById(sellerId);
				if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
					Date date0 = BaseUtil.getTimeDate(record.getCreateTime());
					FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
					fqSellerStatementExample.createCriteria().andSellerIdEqualTo(sellerId).andStatementDateEqualTo(date0);
					List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
					FqSellerStatement fsstate = null;
					if (fsslist.size()>0) {
						fsstate = fsslist.get(0);
						fsstate.setTotalMoney(fsstate.getTotalMoney().add(realPay));
						fsstate.setTotalNum(fsstate.getTotalNum()+1);
						fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
					}else{
						fsstate = new FqSellerStatement();
						fsstate.setCreateTime(new Date());
						fsstate.setStatementDate(date0);
						fsstate.setSellerId(sellerId);
						fsstate.setState(0);
						fsstate.setTotalMoney(realPay);
						fsstate.setTotalNum(1);
						StoreInfo store = storeService.queryStoreInfoBySeller(sellerId);
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(date0);
						calendar.add(Calendar.DATE, store.getStatementPeriod());
						fsstate.setPeriodDate(calendar.getTime());
						fqSellerStatementMapper.insertSelective(fsstate);
					}
				}else{
					FqStoreCreditExample example3 = new FqStoreCreditExample();
					example3.createCriteria().andSellerIdEqualTo(sellerId).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(new Date()).andEndTimeGreaterThanOrEqualTo(new Date());
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
				//推送信息
				HashSet<Integer> pushSet = new HashSet<Integer>();
				pushSet.add(1);
				PushPayInfoTask task = new PushPayInfoTask();
				task.setSellerId(sellerId);
				task.setPushTypeArr(pushSet);
				task.setMoney(thirdPay.getMoney());
				task.setTradeType("coupon_"+card.getTemplateCouponAmount());
				task.setTransactionId(card.getCode());
				task.setOpenId(thirdPay.getOpenId());
				task.setPayTime(thirdPay.getPayTime());
				taskExecutor.execute(task);
				
				// 插入对应的统计表 修改数据 
				RebateCash cash = new RebateCash();
				cash.setRebateId(record.getRebateId());
				cash.setTotamtTotal(record.getTotamt());
				cash.setNeedTotal(record.getNeedPay());
				cash.setRealTotal(record.getRealPay());
				cash.setIntegralTotal(record.getIntegral());
				
				cashMapper.updateStatMoney(cash);
				logger.error(">>>>>>>>>>>>>>>>>>订单："+record.getId()+"，0元支付成功,cardNo="+card.getCode());
				//更新订单成功，返回成功状态
				resultMap.put("errcode", "00009527"); //直接返回成功界面
				resultMap.put("orderId",record.getId().toString());
				return resultMap;
		 }else{
			 orderMapper.insertSelective(record);
			 
			
			 String notifyUrl = ConstantsConfigurer.getProperty(SystemConstant.SZ_PF_PAY_WX_NOTIFYURL);
			 String returnUrl = ConstantsConfigurer.getProperty(SystemConstant.SZ_PF_PAY_WX_RETURNURL);
			 String merNo = ConstantsConfigurer.getProperty(SystemConstant.SZ_PF_MCH_ID);
			 String subMerNo = ConstantsConfigurer.getProperty(SystemConstant.SZ_PF_WX_MCH_ID);
			 int fee = realPay.multiply(new BigDecimal(100)).intValue();
			 SortedMap<String, String> packageParams = new TreeMap<String, String>();
			 packageParams.put("productId","0112");
			 packageParams.put("transId", "16");
			 packageParams.put("merNo",merNo);
			 packageParams.put("subMchId",subMerNo);
			 
			 packageParams.put("orderDate", DateUtil.getCurrentDayStr());
			 packageParams.put("orderNo", record.getOrderNo());
			 packageParams.put("subOpenId", record.getOpenId());
			 packageParams.put("clientIp", request.getRemoteAddr());
			 packageParams.put("returnUrl", returnUrl);
			 packageParams.put("notifyUrl", notifyUrl);
			 packageParams.put("transAmt", String.valueOf(fee));
			 packageParams.put("commodityName", storeName+"-移动支付");
			 Map<String, String> notifyParamsMap =  SzPfPost.postPay(packageParams);
			 logger.info("szpf xiadan ---"+notifyParamsMap.toString());
			 if (notifyParamsMap.get("signcode").equals("0")&&notifyParamsMap.get("respCode").equals("0000")) {
				 String jsonString = notifyParamsMap.get("formfield");
				 resultMap.put("errcode", "0000");
				 resultMap.put("jsonstr", jsonString);
				 resultMap.put("orderId",record.getId().toString());
				 return resultMap;
			 }else{
				 resultMap.put("errcode", "1000");
				 return resultMap;
			 }
		 }
	}
	
	@Override
	public Map<String, Object> insertOrderAndGetXYEQWX(Long couponId,
			String nickname, String headimgurl, String openId, long sellerId,
			String storeName, long rebateId, BigDecimal needPay, long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,
			BigDecimal rebate, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 Map<String,Object> resultMap = new HashMap<String,Object>();
			
		 /*校验订单请求参数 主要针对店铺信息 折扣等是否相符*/
		 Map<String,Object> conditionMap = new HashMap<String,Object>();
		 conditionMap.put("id", rebateId);
		 conditionMap.put("storeId", storeId);
		 conditionMap.put("sellerId", sellerId);
		 conditionMap.put("storeName", storeName);
		 conditionMap.put("rebate", rebate);
		 conditionMap.put("currentTime", new Date());
		 Map<String,Object> rebateMap = storeRebateMapper.selectRebateDetailById(conditionMap);
		 if(null == rebateMap){
			 resultMap.put("errcode", SystemConstant.DATA_DIFF);
	         logger.info(">>>>>>>>>>>>>>>>>>>>订单参数打折信息不一致："+rebateId+","+storeId+","+sellerId+","+storeName+","+rebate);
			 return resultMap;
		 }
		 CardCoupon card = null;
		 //校验卡券信息
		 if(couponId!=null){
			 //查询当前用户的该卡券信息
			  card  = cardCouponMapper.selectByPrimaryKey(couponId);
			  if(null == card){
				  resultMap.put("errcode", SystemConstant.DATA_DIFF);
		         logger.info(">>>>>>>>>>>>>>>>>>>>卡券信息不存在couponId:"+couponId);
				 return resultMap;
			  }
			 if(!openId.equals(card.getOpenId())){ //使用的卡券不是当前用户的
				 resultMap.put("errcode", SystemConstant.DATA_DIFF);
		         logger.info(">>>>>>>>>>>>>>>>>>>>订单参数使用卡券"+couponId+"不一致：卡券拥有者="+card.getOpenId()+" 使用者="+openId);
				 return resultMap;
			 }
			 if(storeId!=card.getStoreId()){ //不是本店的优惠券
				 resultMap.put("errcode", SystemConstant.DATA_DIFF);
		         logger.info(">>>>>>>>>>>>>>>>>>>>订单参数不是本店的优惠券"+storeId+" 卡券的storeId="+card.getStoreId());
				 return resultMap;
			 }
			 if(new Date().getTime()<card.getValidityStarttime().getTime() || new Date().getTime()>card.getValidityEndtime().getTime()){
				 resultMap.put("errcode", SystemConstant.DATA_DIFF);
		         logger.info(">>>>>>>>>>>>>>>>>>>>优惠券不在使用有效期内id="+couponId+" 当前用户="+openId);
				 return resultMap;
			 }
			 
			 if(card.getStatusCfg()!=0){
				 resultMap.put("errcode", SystemConstant.DATA_DIFF);
		         logger.info(">>>>>>>>>>>>>>>>>>>>订单参数使用卡券"+couponId+" 已经被使用");
				 return resultMap;
			 }
			 
			Integer couponCfg = card.getTemplateCouponCfg();
			Double tempPay = 0.00;
			//1:代金券   2:折扣券
			if(couponCfg==1){
				BigDecimal discountPay = new BigDecimal(card.getTemplateCouponAmount());
				 tempPay = needPay.subtract(discountPay).doubleValue();   
			}else if(couponCfg==1){
				BigDecimal discount = new BigDecimal(card.getTemplateCouponAmount());//折扣 0-100 之间的整数
				discount =  discount.divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);  //转成小数 示例0.85
				 tempPay = needPay.multiply(discount).doubleValue();   
			}
			
			if(tempPay>0){
				if(realPay.doubleValue()!=tempPay){ //计算出的实际金额和前台传的实际金额不符
					 resultMap.put("errcode", SystemConstant.DATA_DIFF);
			         logger.info(">>>>>>>>>>>>>>>>>>>>订单实际金额不一致：计算出金额="+tempPay+" 前台金额="+realPay);
					 return resultMap;
				}
			}
			
		 }
		 
		 //设置卡券已使用
		 if(couponId != null){
			  card = new CardCoupon();
			 card.setStatusCfg(1);
			 card.setId(couponId);
			 card.setValidateCfg(1);
			 card.setValidateDate(new Date());
			 cardCouponMapper.updateByPrimaryKeySelective(card);
		 }else{
			 couponId = 0l;
		 }
		 
		 /*根据参数创建订单*/
		
		 //随机生成订单号
		 String orderNo = OrderUtil.getOrderNo(SystemConstant.XY_WX_PAY_TYPE);
		 //插入订单表
		 RebateOrder record = new RebateOrder(orderNo, openId, rebateId, couponId, BigDecimal.ZERO, 2, needPay, realPay, totamt, noDiscount, new Date(), 1,nickname,headimgurl);

		if(realPay.doubleValue()==0){   //0元支付
			    //设置订单已支付
				record.setStatus(SystemConstant.PAY_ISPAY);
				record.setPayTime(new Date());	//支付时间
				orderMapper.insertSelective(record);
				
				FqThirdPay thirdPay = new  FqThirdPay();
				thirdPay.setMoney(realPay);
				thirdPay.setOrderId(record.getId());
				thirdPay.setOrderNo(orderNo);
				thirdPay.setPayTime(record.getPayTime());
				thirdPay.setSort(1);
				thirdPay.setThirdOrderNo("");
				thirdPay.setType(88); //完全卡券代金支付
				thirdPay.setSellerId(sellerId);
				thirdPay.setTotamt(totamt);
				FqUserInfoExample fuieExample = new FqUserInfoExample();
				fuieExample.createCriteria().andOpenIdEqualTo(openId);
				List<FqUserInfo> users = fqUserInfoMapper.selectByExample(fuieExample);
				if (users.size()>0) {
					thirdPay.setUserId(users.get(0).getId());
				}
				thirdPay.setOpenId(openId);
				fqThirdPayMapper.insertSelective(thirdPay);
				
				SellerInfo seller = sellerService.getSellerById(sellerId);
				if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
					Date date0 = BaseUtil.getTimeDate(record.getCreateTime());
					FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
					fqSellerStatementExample.createCriteria().andSellerIdEqualTo(sellerId).andStatementDateEqualTo(date0);
					List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
					FqSellerStatement fsstate = null;
					if (fsslist.size()>0) {
						fsstate = fsslist.get(0);
						fsstate.setTotalMoney(fsstate.getTotalMoney().add(realPay));
						fsstate.setTotalNum(fsstate.getTotalNum()+1);
						fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
					}else{
						fsstate = new FqSellerStatement();
						fsstate.setCreateTime(new Date());
						fsstate.setStatementDate(date0);
						fsstate.setSellerId(sellerId);
						fsstate.setState(0);
						fsstate.setTotalMoney(realPay);
						fsstate.setTotalNum(1);
						StoreInfo store = storeService.queryStoreInfoBySeller(sellerId);
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(date0);
						calendar.add(Calendar.DATE, store.getStatementPeriod());
						fsstate.setPeriodDate(calendar.getTime());
						fqSellerStatementMapper.insertSelective(fsstate);
					}
				}else{
					FqStoreCreditExample example3 = new FqStoreCreditExample();
					example3.createCriteria().andSellerIdEqualTo(sellerId).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(new Date()).andEndTimeGreaterThanOrEqualTo(new Date());
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
				//推送信息
				HashSet<Integer> pushSet = new HashSet<Integer>();
				pushSet.add(1);
				PushPayInfoTask task = new PushPayInfoTask();
				task.setSellerId(sellerId);
				task.setPushTypeArr(pushSet);
				task.setMoney(thirdPay.getMoney());
				task.setTradeType("coupon_"+card.getTemplateCouponAmount());
				task.setTransactionId(card.getCode());
				task.setOpenId(thirdPay.getOpenId());
				task.setPayTime(thirdPay.getPayTime());
				taskExecutor.execute(task);
				
				// 插入对应的统计表 修改数据 
				RebateCash cash = new RebateCash();
				cash.setRebateId(record.getRebateId());
				cash.setTotamtTotal(record.getTotamt());
				cash.setNeedTotal(record.getNeedPay());
				cash.setRealTotal(record.getRealPay());
				cash.setIntegralTotal(record.getIntegral());
				
				cashMapper.updateStatMoney(cash);
				logger.error(">>>>>>>>>>>>>>>>>>订单："+record.getId()+"，0元支付成功,cardNo="+card.getCode());
				//更新订单成功，返回成功状态
				resultMap.put("errcode", "00009527"); //直接返回成功界面
				resultMap.put("orderId",record.getId().toString());
				return resultMap;
		 }else{
			 orderMapper.insertSelective(record);
			 
			 String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
			 String notifyUrl = ConstantsConfigurer.getProperty("xy_eqpay_wx_notifyurl");
//			 String MCH_ID = ConstantsConfigurer.getProperty(SystemConstant.XY_MCH_ID);
			 String MCH_ID = ConstantsConfigurer.getProperty("xy_eqpay_mch_id");
			 String PAY_KEY = ConstantsConfigurer.getProperty("xy_eqpay_key");
			 
			 String noncestr = Sha1Util.getNonceStr();
			 int fee = realPay.multiply(new BigDecimal(100)).intValue();
			 SortedMap<String, String> packageParams = new TreeMap<String, String>();
			 packageParams.put("service","pay.weixin.jspay" );
			 packageParams.put("mch_id", MCH_ID);
//			 packageParams.put("sub_mch_id", SUB_MCH_ID);
			 packageParams.put("is_raw", "1");
			 packageParams.put("out_trade_no", orderNo);
			 packageParams.put("body", storeName+"-移动支付");
			 packageParams.put("sub_openid", record.getOpenId());
			 
			 packageParams.put("total_fee", String.valueOf(fee));
			 packageParams.put("mch_create_ip", request.getRemoteAddr());
			 packageParams.put("notify_url", notifyUrl);
			 packageParams.put("nonce_str", noncestr);
			 String sign = createSign(packageParams, PAY_KEY);;
			 packageParams.put("sign", sign);
			 String requestXML = XMLUtil.getRequestXml(packageParams);
			 logger.info(">>>>>>>>>>>>>>>>>>兴业微信支付【二清】请求参数:" + requestXML);
			 String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
			 logger.info(result);
			 Map<String, String>notifyParamsMap =XMLUtil.doXMLParse(result);
			 logger.info(">>>>>>>>>>>>>>>>>>兴业微信支付【二清】响应参数:" + notifyParamsMap.toString());
			 if (notifyParamsMap.get("status").equals("0")&&notifyParamsMap.get("result_code").equals("0")) {
				 
				 String jsonString = notifyParamsMap.get("pay_info");
				 resultMap.put("errcode", "0000");
				 resultMap.put("jsonstr", jsonString);
				 resultMap.put("orderId",record.getId().toString());
				 resultMap.put("orderNo",orderNo);
				 return resultMap;
			 }else{
				 resultMap.put("errcode", "80000");
				 return resultMap;
			 }
		 }
		 
	}



	@Override
	public Integer updateCheckNotifyWXSZPF(Map<String, String> notifyParamsMap,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
			response.setContentType("text/html;charset=UTF-8");
			if(notifyParamsMap == null){
				logger.info(">>>>>>>>>>>>>>>>>>深圳浦发微信支付回调 参数 is null");
				// response.getWriter().print("fail");
				return 0;
			}
			logger.info(">>>>>>>>>>>>>>>>>>深圳浦发微信支付回调 参数" + notifyParamsMap.toString());
			

			if (!"0000".equals(notifyParamsMap.get("respCode"))) {
				logger.info(">>>>>>>>>>>>>>>>>>深圳浦发微信支付回调失败:"+notifyParamsMap.toString());
				//response.getWriter().print("fail");
				return 0;
			}
			
			
	         StringBuffer buf = new StringBuffer();
	         String signature = "";
	         for (Map.Entry<String, String> m : notifyParamsMap.entrySet()) {
	         if ("signature".equals(m.getKey())) {
	                    signature = m.getValue();
	                    continue;
	           }
	            buf.append(m.getKey()).append("=").append(m.getValue()).append("&");
	          }
	          String signatureStr = buf.substring(0, buf.length() - 1);

	          boolean f = SignUtils.notifyVerify(signatureStr, signature);
			//微信支付回调通知验签
			if(f){
				
				//回调成功 -处理业务状态
				if ("0000".equals(notifyParamsMap.get("respCode"))) {
					
					String tradeNo = notifyParamsMap.get("orderNo");		
					String wftOrderNo =notifyParamsMap.get("transactionId") ;			
					String payTime = notifyParamsMap.get("timeEnd");				//支付时间
					
					
					RebateOrderExample example = new RebateOrderExample();
					example.createCriteria().andOrderNoEqualTo(tradeNo);
					List<RebateOrder> recordlist  = orderMapper.selectByExample(example);
					
					if(recordlist == null || recordlist.size() == 0){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"不存在");
						return 0;
					}
					RebateOrder record =recordlist.get(0);
					if(SystemConstant.PAY_ISPAY.equals(record.getStatus())){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"己支付");
						return 1;
					}else{
						
						StoreRebate rebate = storeRebateMapper.selectByPrimaryKey(record.getRebateId());
						StoreInfo store = storeInfoMapper.selectByPrimaryKey(rebate.getStoreId());
						
						BigDecimal calTotamt =  record.getTotamt();
						BigDecimal rate = new BigDecimal(0); 
						BigDecimal rateMoney = new BigDecimal(0); 
						//查询本店费率
						Integer type = 1;   // 1WX   2ALi   3QQ
						HashMap<String, BigDecimal>  rateMap = fqStoreRateService.calculateOrderRate(record.getTotamt(), store, type);
						
						if(rateMap!=null&&rateMap.size()>0){
							rate = rateMap.get("smallRate");	 //费率
							rateMoney = rateMap.get("rateMoney");	 //手续费
							calTotamt = rateMap.get("calTotamt");	  //扣除手续费结算的金额 
						}
						
						String bankTypeCode = notifyParamsMap.get("bankType");
						
						//更新订单  
						RebateOrder uprecord = new RebateOrder();
						uprecord.setId(Long.valueOf(record.getId()));
						uprecord.setStatus(SystemConstant.PAY_ISPAY);
						uprecord.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss", payTime));	//支付时间
						uprecord.setWeixinOrderNo(wftOrderNo);
						uprecord.setTotamt(calTotamt);
						uprecord.setOrderRate(rate);
						uprecord.setRateFee(rateMoney);
						orderMapper.updateByPrimaryKeySelective(uprecord);

						Date now = new Date();
					
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
						thirdPay.setMoney(record.getRealPay());
						thirdPay.setOrderId(record.getId());
						thirdPay.setOrderNo(record.getOrderNo());
						thirdPay.setPayTime(uprecord.getPayTime());
						thirdPay.setSort(1);
						thirdPay.setThirdOrderNo(wftOrderNo);
						thirdPay.setType(5);
						thirdPay.setSellerId(store.getSellerId());
						thirdPay.setTotamt(calTotamt);
						thirdPay.setOrderRate(rate);
						thirdPay.setRateFee(rateMoney);
						
						thirdPay.setBankType(bankType);
						thirdPay.setBankTypeCode(bankTypeCode);
						FqUserInfoExample fuieExample = new FqUserInfoExample();
						fuieExample.createCriteria().andOpenIdEqualTo(record.getOpenId());
						List<FqUserInfo> users = fqUserInfoMapper.selectByExample(fuieExample);
						if (users.size()>0) {
							thirdPay.setUserId(users.get(0).getId());
						}
						thirdPay.setOpenId(record.getOpenId());
						fqThirdPayMapper.insertSelective(thirdPay);
						
						SellerInfo seller = sellerService.getSellerById(store.getSellerId());
						if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
							Date date0 = BaseUtil.getTimeDate(record.getCreateTime());
							FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
							fqSellerStatementExample.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatementDateEqualTo(date0).andStateEqualTo(0);
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
								fsstate.setSellerId(store.getSellerId());
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
							example3.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
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
						pushSet.add(1);
						PushPayInfoTask task = new PushPayInfoTask();
						task.setSellerId(store.getSellerId());
						task.setPushTypeArr(pushSet);
						task.setMoney(thirdPay.getMoney());
						task.setTradeType("weixin");
						task.setTransactionId(tradeNo);
						task.setOpenId(thirdPay.getOpenId());
						task.setPayTime(uprecord.getPayTime());
						taskExecutor.execute(task);
						
						// 插入对应的统计表 修改数据 
						RebateCash cash = new RebateCash();
						cash.setRebateId(record.getRebateId());
						cash.setTotamtTotal(calTotamt);
						cash.setNeedTotal(record.getNeedPay());
						cash.setRealTotal(record.getRealPay());
						cash.setIntegralTotal(record.getIntegral());
						
						cashMapper.updateStatMoney(cash);
						
						logger.error(">>>>>>>>>>>>>>>>>>订单："+record.getId()+"，回调成功");
						//更新订单成功，返回成功状态
						return 1;
					}
				}
			}else{
				logger.error(">>>>>>>>>>>>>>>>>>深圳浦发微信回调验签失败notifyParamsMap："+notifyParamsMap.toString());
			}
			return null;
		}catch(Exception e){
			logger.error("深圳浦发微信 error", e);
			throw e;
		}
	}


	@Override
	public Integer updateCheckNotifyXYEQWX(Map<String, String> notifyParamsMap,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
			response.setContentType("text/html;charset=UTF-8");
			if(notifyParamsMap == null){
				logger.info(">>>>>>>>>>>>>>>>>>兴业二清微信支付回调 参数 is null");
				// response.getWriter().print("fail");
				return 0;
			}
			logger.info(">>>>>>>>>>>>>>>>>>兴业二清微信支付回调 参数" + notifyParamsMap.toString());
			
			if (!"0".equals(notifyParamsMap.get("status"))) {
				logger.info(">>>>>>>>>>>>>>>>>>兴业二清微信支付回调，通信异常："+notifyParamsMap.toString());
				//response.getWriter().print("fail");
				return 0;
			}
			if (!"0".equals(notifyParamsMap.get("result_code"))) {
				logger.info(">>>>>>>>>>>>>>>>>>兴业二清微信支付回调失败:"+notifyParamsMap.toString());
				//response.getWriter().print("fail");
				return 0;
			}
			logger.info(">>>>>>>>>>>>>>>>>>兴业银行二清微信支付回调:" + notifyParamsMap.toString());
			//回调成功 -处理业务状态
			if ("0".equals(notifyParamsMap.get("status")) && "0".equals(notifyParamsMap.get("result_code"))&&"0".equals(notifyParamsMap.get("pay_result"))) {
				
				String tradeNo = notifyParamsMap.get("out_trade_no");		
				String wftOrderNo =notifyParamsMap.get("transaction_id") ;			
				String payTime = notifyParamsMap.get("time_end");				//支付时间
				
				
				RebateOrderExample example = new RebateOrderExample();
				example.createCriteria().andOrderNoEqualTo(tradeNo);
				List<RebateOrder> recordlist  = orderMapper.selectByExample(example);
				
				if(recordlist == null || recordlist.size() == 0){
					logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"不存在");
					return 0;
				}
				RebateOrder record =recordlist.get(0);
				if(SystemConstant.PAY_ISPAY.equals(record.getStatus())){
					logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"己支付");
					return 1;
				}else{
					
					StoreRebate rebate = storeRebateMapper.selectByPrimaryKey(record.getRebateId());
					StoreInfo store = storeInfoMapper.selectByPrimaryKey(rebate.getStoreId());
					
					BigDecimal calTotamt =  record.getTotamt();
					BigDecimal rate = new BigDecimal(0); 
					BigDecimal rateMoney = new BigDecimal(0); 
					//查询本店费率
					Integer type = 1;   // 1WX   2ALi   3QQ
					HashMap<String, BigDecimal>  rateMap = fqStoreRateService.calculateOrderRate(record.getTotamt(), store, type);
					
					if(rateMap!=null&&rateMap.size()>0){
						rate = rateMap.get("smallRate");	 //费率
						rateMoney = rateMap.get("rateMoney");	 //手续费
						calTotamt = rateMap.get("calTotamt");	  //扣除手续费结算的金额 
					}
					
					String bankTypeCode = notifyParamsMap.get("bank_type");
					
					//更新订单  
					RebateOrder uprecord = new RebateOrder();
					uprecord.setId(Long.valueOf(record.getId()));
					uprecord.setStatus(SystemConstant.PAY_ISPAY);
					uprecord.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss", payTime));	//支付时间
					uprecord.setWeixinOrderNo(wftOrderNo);
					uprecord.setTotamt(calTotamt);
					uprecord.setOrderRate(rate);
					uprecord.setRateFee(rateMoney);
					orderMapper.updateByPrimaryKeySelective(uprecord);

					Date now = new Date();
				
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
					thirdPay.setMoney(record.getRealPay());
					thirdPay.setOrderId(record.getId());
					thirdPay.setOrderNo(record.getOrderNo());
					thirdPay.setPayTime(uprecord.getPayTime());
					thirdPay.setSort(1);	
					thirdPay.setThirdOrderNo(wftOrderNo);
					thirdPay.setType(5);	//兴业微信
					thirdPay.setSellerId(store.getSellerId());
					thirdPay.setTotamt(calTotamt);
					thirdPay.setOrderRate(rate);
					thirdPay.setRateFee(rateMoney);
					
					thirdPay.setBankType(bankType);
					thirdPay.setBankTypeCode(bankTypeCode);
					FqUserInfoExample fuieExample = new FqUserInfoExample();
					fuieExample.createCriteria().andOpenIdEqualTo(record.getOpenId());
					List<FqUserInfo> users = fqUserInfoMapper.selectByExample(fuieExample);
					if (users.size()>0) {
						thirdPay.setUserId(users.get(0).getId());
					}
					thirdPay.setOpenId(record.getOpenId());
					fqThirdPayMapper.insertSelective(thirdPay);
					
					SellerInfo seller = sellerService.getSellerById(store.getSellerId());
					if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
						Date date0 = BaseUtil.getTimeDate(record.getCreateTime());
						FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
						fqSellerStatementExample.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatementDateEqualTo(date0).andStateEqualTo(0);
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
							fsstate.setSellerId(store.getSellerId());
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
						example3.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
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
					pushSet.add(1);
					PushPayInfoTask task = new PushPayInfoTask();
					task.setSellerId(store.getSellerId());
					task.setPushTypeArr(pushSet);
					task.setMoney(thirdPay.getMoney());
					task.setTradeType("weixin");
					task.setTransactionId(tradeNo);
					task.setOpenId(thirdPay.getOpenId());
					task.setPayTime(uprecord.getPayTime());
					taskExecutor.execute(task);
					
					// 插入对应的统计表 修改数据 
					RebateCash cash = new RebateCash();
					cash.setRebateId(record.getRebateId());
					cash.setTotamtTotal(calTotamt);
					cash.setNeedTotal(record.getNeedPay());
					cash.setRealTotal(record.getRealPay());
					cash.setIntegralTotal(record.getIntegral());
					
					cashMapper.updateStatMoney(cash);
					
					logger.error(">>>>>>>>>>>>>>>>>>订单："+record.getId()+"，回调成功");
					//更新订单成功，返回成功状态
					return 1;
				}
			}
			return null;
		}catch(Exception e){
			logger.error("兴业微信 error", e);
			throw e;
		}
	}


	/**
	 * 兴业银行的支付宝二清
	 */
	public  Map<String,Object> insertOrderAndGetXYEQAlipay(String openId, long sellerId,
			String storeName, BigDecimal rebate,long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,String nickname,String headimgurl,HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		/*校验订单请求参数 主要针对店铺信息 折扣等是否相符*/
		Map<String,Object> conditionMap = new HashMap<String,Object>();
		conditionMap.put("id", rebateId);
		conditionMap.put("storeId", storeId);
		conditionMap.put("sellerId", sellerId);
		conditionMap.put("storeName", storeName);
		conditionMap.put("rebate", rebate);
		conditionMap.put("currentTime", new Date());
		Map<String,Object> rebateMap = storeRebateMapper.selectRebateDetailById(conditionMap);
		if(null == rebateMap){
			resultMap.put("errcode", SystemConstant.DATA_DIFF);
			logger.info(">>>>>>>>>>>>>>>>>>>>订单参数打折信息不一致："+rebateId+","+storeId+","+sellerId+","+storeName+","+rebate);
			return resultMap;
		}
		String orderNo = OrderUtil.getOrderNo(SystemConstant.XY_ALI_EQPAY_TYPE);
		//插入订单表
		RebateOrder record = new RebateOrder(orderNo, openId, rebateId, 0L, BigDecimal.ZERO, 1, realPay, realPay, totamt, noDiscount, new Date(), 1,nickname,headimgurl);
		int size = orderMapper.insertSelective(record);
		if(size<=0){
			resultMap.put("errcode", SystemConstant.ERROR);
			logger.info(">>>>>>>>>>>>>>>>>>>>兴业支付宝保存订单失败");
			return resultMap;
		}
	
		String JSAPI_URL = "https://pay.swiftpass.cn/pay/gateway";
		String notifyUrl = ConstantsConfigurer.getProperty("xy_eqpay_al_notifyurl");
		String MCH_ID = ConstantsConfigurer.getProperty("xy_eqpay_mch_id");
		String PAY_KEY = ConstantsConfigurer.getProperty("xy_eqpay_key");
		String noncestr = Sha1Util.getNonceStr();
		int fee = realPay.multiply(new BigDecimal(100)).intValue();
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("service","pay.alipay.jspay");
		packageParams.put("mch_id", MCH_ID);
		packageParams.put("out_trade_no", orderNo);
		packageParams.put("body", storeName+"-移动支付");
		packageParams.put("total_fee", String.valueOf(fee));
		packageParams.put("mch_create_ip", request.getRemoteAddr());
		packageParams.put("notify_url", notifyUrl);
		packageParams.put("nonce_str", noncestr);
		packageParams.put("buyer_id", openId);
		String sign = createSign(packageParams, PAY_KEY);;
		packageParams.put("sign", sign);
		String requestXML = XMLUtil.getRequestXml(packageParams);
		logger.info(">>>>>>>>>>>>>>>>>>兴业支付宝支付【二清】请求参数:" + requestXML);
		String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
		Map<String, String> notifyParamsMap =XMLUtil.doXMLParse(result);
		logger.info(">>>>>>>>>>>>>>>>>>兴业支付宝支付【二清】响应:" + notifyParamsMap.toString());
		if (notifyParamsMap.get("status").equals("0")&&notifyParamsMap.get("result_code").equals("0")) {
			
			String jsonString = notifyParamsMap.get("pay_info");
			Map maps = (Map) JSONObject.parse(jsonString);
			resultMap.put("errcode", "0000");
			resultMap.put("tradeNO", maps.get("tradeNO"));
			resultMap.put("orderId",record.getId().toString());
			resultMap.put("orderNo",orderNo);
			return resultMap;
		}else{
			resultMap.put("errcode", "80000");
			return resultMap;
		}
	}
	
	/**
	 * 兴业银行的支付宝支付 二清 回调通知
	 */
	public  Integer updateCheckNotifyXYEQAlipay(Map<String,String> notifyParamsMap)throws Exception {
		
		try{
			if(notifyParamsMap == null){
				logger.info(">>>>>>>>>>>>>>>>>>兴业支付宝支付回调 参数 is null");
				// response.getWriter().print("fail");
				return 0;
			}
			logger.info(">>>>>>>>>>>>>>>>>>兴业支付宝支付回调 参数" + notifyParamsMap.toString());
			
			if (!"0".equals(notifyParamsMap.get("status"))) {
				logger.info(">>>>>>>>>>>>>>>>>>兴业支付宝支付回调，通信异常："+notifyParamsMap.toString());
				//response.getWriter().print("fail");
				return 0;
			}
			if (!"0".equals(notifyParamsMap.get("result_code"))) {
				logger.info(">>>>>>>>>>>>>>>>>>兴业支付宝支付回调失败:"+notifyParamsMap.toString());
				//response.getWriter().print("fail");
				return 0;
			}
			logger.info(">>>>>>>>>>>>>>>>>>兴业支付宝支付回调 参数:"+notifyParamsMap.toString());
				//回调成功 -处理业务状态
				if ("0".equals(notifyParamsMap.get("status")) && "0".equals(notifyParamsMap.get("result_code"))&&"0".equals(notifyParamsMap.get("pay_result"))) {
					
					String tradeNo = notifyParamsMap.get("out_trade_no");		
					String wftOrderNo =notifyParamsMap.get("transaction_id") ;			
					String payTime = notifyParamsMap.get("time_end");				//支付时间
					String openid = notifyParamsMap.get("openid");				//用户支付宝账户名
					
					RebateOrderExample example = new RebateOrderExample();
					example.createCriteria().andOrderNoEqualTo(tradeNo);
					List<RebateOrder> recordlist  = orderMapper.selectByExample(example);
					
					if(recordlist == null || recordlist.size() == 0){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"不存在");
						return 0;
					}
					RebateOrder record =recordlist.get(0);
					if(SystemConstant.PAY_ISPAY.equals(record.getStatus())){
						logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"己支付");
						return 1;
					}else{
						
						StoreRebate rebate = storeRebateMapper.selectByPrimaryKey(record.getRebateId());
						StoreInfo store = storeInfoMapper.selectByPrimaryKey(rebate.getStoreId());
						
						BigDecimal calTotamt =  record.getTotamt();
						BigDecimal rate = new BigDecimal(0); 
						BigDecimal rateMoney = new BigDecimal(0); 
						//查询本店费率
						Integer type = 2;   // 1WX   2ALi   3QQ
						HashMap<String, BigDecimal>  rateMap = fqStoreRateService.calculateOrderRate(record.getTotamt(), store, type);
						
						if(rateMap!=null&&rateMap.size()>0){
							rate = rateMap.get("smallRate");	 //费率
							rateMoney = rateMap.get("rateMoney");	 //手续费
							calTotamt = rateMap.get("calTotamt");	  //扣除手续费结算的金额 
						}
						
						//更新订单  
						RebateOrder uprecord = new RebateOrder();
						uprecord.setId(Long.valueOf(record.getId()));
						uprecord.setStatus(SystemConstant.PAY_ISPAY);
						uprecord.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss", payTime));	//支付时间
						uprecord.setWeixinOrderNo(wftOrderNo);
						uprecord.setTotamt(calTotamt);
						uprecord.setOrderRate(rate);
						uprecord.setRateFee(rateMoney);
						orderMapper.updateByPrimaryKeySelective(uprecord);
						
						Date now = new Date();
						
						FqThirdPay thirdPay = new  FqThirdPay();
						thirdPay.setMoney(record.getRealPay());
						thirdPay.setOrderId(record.getId());
						thirdPay.setOrderNo(record.getOrderNo());
						thirdPay.setPayTime(now);
						thirdPay.setSort(1);	//兴业
						thirdPay.setThirdOrderNo(wftOrderNo);
						thirdPay.setType(6);//兴业支付宝
						thirdPay.setSellerId(store.getSellerId());
						thirdPay.setTotamt(calTotamt);
						thirdPay.setOrderRate(rate);
						thirdPay.setRateFee(rateMoney);
						
						FqUserInfoExample fuieExample = new FqUserInfoExample();
						fuieExample.createCriteria().andOpenIdEqualTo(record.getOpenId());
						List<FqUserInfo> users = fqUserInfoMapper.selectByExample(fuieExample);
						if (users.size()>0) {
							thirdPay.setUserId(users.get(0).getId());
						}
						thirdPay.setOpenId(openid);
						fqThirdPayMapper.insertSelective(thirdPay);
						
						SellerInfo seller = sellerService.getSellerById(store.getSellerId());
						if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
							Date date0 = BaseUtil.getTimeDate(record.getCreateTime());
							FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
							fqSellerStatementExample.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatementDateEqualTo(date0).andStateEqualTo(0);
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
								fsstate.setSellerId(store.getSellerId());
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
							example3.createCriteria().andSellerIdEqualTo(store.getSellerId()).andStatusEqualTo(1).andStartTimeLessThanOrEqualTo(now).andEndTimeGreaterThanOrEqualTo(now);
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
						task.setSellerId(store.getSellerId());
						task.setPushTypeArr(pushSet);
						task.setMoney(thirdPay.getMoney());
						task.setTradeType("alipay");
						task.setTransactionId(tradeNo);
						taskExecutor.execute(task);
						
						
						// 插入对应的统计表 修改数据 
						RebateCash cash = new RebateCash();
						cash.setRebateId(record.getRebateId());
						cash.setTotamtTotal(calTotamt);
						cash.setNeedTotal(record.getNeedPay());
						cash.setRealTotal(record.getRealPay());
						cash.setIntegralTotal(record.getIntegral());
						
						cashMapper.updateStatMoney(cash);
						
						logger.error(">>>>>>>>>>>>>>>>>>订单："+record.getId()+"，回调成功");
						//更新订单成功，返回成功状态
						return 1;
					}
				}
			return null;
		}catch(Exception e){
			logger.error("updateCheckNotify error", e);
			throw e;
		}
	}
	
}
