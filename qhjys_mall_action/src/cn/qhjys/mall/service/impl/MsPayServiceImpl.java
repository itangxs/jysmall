package cn.qhjys.mall.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.qhjys.mall.common.XingeService;
import cn.qhjys.mall.entity.AuthenticationChnnel;
import cn.qhjys.mall.entity.CardCoupon;
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
import cn.qhjys.mall.mapper.CardCouponMapper;
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
import cn.qhjys.mall.mapper.RebateCashMapper;
import cn.qhjys.mall.mapper.RebateOrderMapper;
import cn.qhjys.mall.mapper.SellerInfoMapper;
import cn.qhjys.mall.mapper.StoreInfoMapper;
import cn.qhjys.mall.mapper.StoreRebateMapper;
import cn.qhjys.mall.mapper.WeixinUserinfoMapper;
import cn.qhjys.mall.quartz.PushPayInfoTask;
import cn.qhjys.mall.service.AuthenticationChnnelService;
import cn.qhjys.mall.service.FqPushInfoService;
import cn.qhjys.mall.service.FqStoreRateService;
import cn.qhjys.mall.service.MsPayService;
import cn.qhjys.mall.service.RedisService;
import cn.qhjys.mall.service.SellerService;
import cn.qhjys.mall.service.StoreService;
import cn.qhjys.mall.service.fq.FqStoreService;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.DateUtil;
import cn.qhjys.mall.util.WeixinUtil;
import cn.qhjys.mall.util.ms.BaseRequest;
import cn.qhjys.mall.util.ms.MsConstant;
import cn.qhjys.mall.util.ms.RequestNo;
import cn.qhjys.mall.weixin.util.OrderUtil;
import cn.qhjys.mall.weixin.util.SystemConstant;

import com.alibaba.fastjson.JSONObject;
import com.tencent.xinge.ClickAction;
import com.tencent.xinge.Message;
import com.tencent.xinge.MessageIOS;
import com.tencent.xinge.XingeApp;

/**
 * @author tangxs
 *
 * 2017年7月14日
 */
@Service
public class MsPayServiceImpl implements MsPayService {

private final Log logger = LogFactory.getLog(MsPayServiceImpl.class);
	
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
	private SellerService sellerService;
	@Autowired
	private FqUserInfoMapper fqUserInfoMapper;
	@Autowired
	private FqPushInfoService fqPushInfoService;
	@Autowired
	private FqOrderDetailMapper fqOrderDetailMapper;
	@Autowired
	private CardCouponMapper cardCouponMapper;
	
	@Autowired
	private FqMicroOrderMapper fqmicroOrderMapper;
	@Autowired
	private FqSellerStatementMapper fqSellerStatementMapper;
	@Autowired
	private SellerInfoMapper sellerInfoMapper;
	@Autowired
	private StoreService storeService;
	@Autowired
	private FqStoreService fqStoreService;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;

	@Autowired
	private FqStoreRateService fqStoreRateService;
	
	@Autowired
	private AuthenticationChnnelService authenticationChnnelService;
	@Autowired
	private FqOrderMapper fqOrderMapper;
	/**
	 * 民生银行的支付宝支付
	 */
	@Override
	public Map<String, Object> insertOrderAndGetAlipayMS(String openId,
			long sellerId, String storeName, BigDecimal rebate, long rebateId,
			BigDecimal needPay, long storeId, BigDecimal realPay,
			BigDecimal totamt, BigDecimal noDiscount, String nickname,
			String headimgurl, HttpServletRequest request,
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
		String orderNo = OrderUtil.getOrderNo(SystemConstant.MS_ALI_PAY_TYPE);
		//插入订单表					...pay_type  民生支付宝  99
		RebateOrder record = new RebateOrder(orderNo, openId, rebateId, 0L, BigDecimal.ZERO, 99, needPay, realPay, totamt, noDiscount, new Date(), 1,nickname,headimgurl);
		int size = orderMapper.insertSelective(record);
		if(size<=0){
			resultMap.put("errcode", SystemConstant.ERROR);
			logger.info(">>>>>>>>>>>>>>>>>>>>民生支付宝保存订单失败");
			return resultMap;
		}
		AuthenticationChnnel au = authenticationChnnelService.findByStoreIdAndBankIdAndPayChannelAndState(storeId,1,2,2);
			//调用民生支付宝窗口支付接口
        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
        nvps.add(new BasicNameValuePair("requestNo", RequestNo.request_no));
        nvps.add(new BasicNameValuePair("version", "V2.0"));
        nvps.add(new BasicNameValuePair("productId", "0115"));
        nvps.add(new BasicNameValuePair("transId", "10"));
        nvps.add(new BasicNameValuePair("merNo", ConstantsConfigurer.getProperty(SystemConstant.MS_MCH_ID)));
        nvps.add(new BasicNameValuePair("orderDate", new SimpleDateFormat("yyyyMMdd").format(new Date())));
        nvps.add(new BasicNameValuePair("orderNo", orderNo));			
        nvps.add(new BasicNameValuePair("returnUrl", ConstantsConfigurer.getProperty(SystemConstant.MS_PAY_RETURNURL)));  //页面通知地址
        nvps.add(new BasicNameValuePair("notifyUrl", ConstantsConfigurer.getProperty(SystemConstant.MS_PAY_ALL_NOTIFYURL)));  //异步通知地址
        nvps.add(new BasicNameValuePair("transAmt", realPay.multiply(new BigDecimal(100)).intValue()+""));
        nvps.add(new BasicNameValuePair("commodityName", storeName+"-移动支付"));
        nvps.add(new BasicNameValuePair("storeId", storeId+""));	//storeId 
        nvps.add(new BasicNameValuePair("terminalId", storeName)); 	// 自定义32位终端号
        nvps.add(new BasicNameValuePair("userId", openId));		//openId
        nvps.add(new BasicNameValuePair("subChnlMerNo", au.getApplyId()));		//  民生系统 	民生子商户号 "100000001059"
        Map<String, String> result = BaseRequest.getSignToSend(nvps);
        if(result == null ){
        	resultMap.put("errcode", "80000");
			return resultMap;
        }
        if (MsConstant.DOING_SUCCEE.equals(result.get("respCode")) 
        		|| SystemConstant.SUCCESS.equals(result.get("respCode"))) {
        	
			resultMap.put("errcode", "0000");
			resultMap.put("tradeNo", result.get("tradeNo"));
			resultMap.put("orderNo", orderNo);
			resultMap.put("orderId",record.getId().toString());
			return resultMap;
		}else{
			resultMap.put("errcode", "80000");
			return resultMap;
		}
	}

	/**
	 * 民生银行的支付宝支付  回调
	 */
	public String updateCheckNotifyAlipayMS(Map<String, String> notifyParamsMap)
			throws Exception {
		try{
			if(notifyParamsMap == null){
				logger.info(">>>>>>>>>>>>>>>>>>民生支付宝支付回调 参数 is null");
				return "FAIL";
			}else
			
			if ("9999".equals(notifyParamsMap.get("respCode"))) {
				logger.info(">>>>>>>>>>>>>>>>>>民生支付宝支付回调，系统异常（出现9999错误码时，建议与上游确认再处理）："+notifyParamsMap.toString());
				return "FAIL";
			}else
			if ("9997".equals(notifyParamsMap.get("respCode"))) {
				logger.info(">>>>>>>>>>>>>>>>>>民生支付宝支付回调,交易结果未知（出现9997错误码时，建议与上游确认再处理）:"+notifyParamsMap.toString());
				return "FAIL";
			}else
			if ("0028".equals(notifyParamsMap.get("respCode"))) {
				logger.info(">>>>>>>>>>>>>>>>>>民生支付宝支付回调,原交易不存在（出现0028错误码时，建议与上游确认再处理）:"+notifyParamsMap.toString());
				return "FAIL";
			}else{
				
				logger.info(">>>>>>>>>>>>>>>>>>民生支付宝支付回调 参数 :" + notifyParamsMap.toString());
			}
				//回调成功 -处理业务状态 
			if (SystemConstant.SUCCESS.equals(notifyParamsMap.get("respCode"))) {
				
				String tradeNo = notifyParamsMap.get("orderNo");		
				String wftOrderNo =notifyParamsMap.get("payId") ;		//	..民生订单号	
				String payTime = notifyParamsMap.get("payTime");				//支付时间
				String openid = notifyParamsMap.get("chnlUserId");				//用户支付宝账户名
				
				RebateOrderExample example = new RebateOrderExample();
				example.createCriteria().andOrderNoEqualTo(tradeNo);
				List<RebateOrder> recordlist  = orderMapper.selectByExample(example);
				
				if(recordlist == null || recordlist.size() == 0){
					logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"不存在");
					return "FAIL";
				}
				RebateOrder record =recordlist.get(0);
				if(SystemConstant.PAY_ISPAY.equals(record.getStatus())){
					logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"己支付");
					return "SUCCESS";
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
					uprecord.setPayTime(new Date());	//支付时间
					uprecord.setWeixinOrderNo(wftOrderNo);
					uprecord.setTotamt(calTotamt);
					uprecord.setOrderRate(rate);
					uprecord.setRateFee(rateMoney);
					orderMapper.updateByPrimaryKeySelective(uprecord);
					
					String bankTypeCode = notifyParamsMap.get("bank_type");
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
					
					Date now = new Date();
					
					FqThirdPay thirdPay = new  FqThirdPay();
					thirdPay.setMoney(record.getRealPay());
					thirdPay.setOrderId(record.getId());
					thirdPay.setOrderNo(record.getOrderNo());
					thirdPay.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss",payTime));
					thirdPay.setSort(99); 	//   99..为民生通道  	
					thirdPay.setThirdOrderNo(wftOrderNo);
					thirdPay.setType(17); //  17 .. 民生支付宝
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
					thirdPay.setOpenId(openid);
					fqThirdPayMapper.insertSelective(thirdPay);
					
					SellerInfo seller = sellerService.getSellerById(store.getSellerId());
					if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
						Date statementDay = thirdPay.getPayTime();
						if(is11hBetween12h(thirdPay.getPayTime()))
						{
							statementDay = addOneDay(statementDay);
						}
						String dateend = DateUtil.convertDateToString("yyyy-MM-dd",statementDay);
						Date enddate0 = DateUtil.convertStringToDate("yyyy-MM-dd hh:mm:ss",dateend+" 23:00:00");
						Date startdate0 = DateUtil.convertStringToDate("yyyy-MM-dd hh:mm:ss",DateUtil.getYesDateStr(statementDay,"yyyy-MM-dd")+" 23:00:00");
						logger.info("now:"+dateend+" 23:00:00"+"=================="+DateUtil.getYesDateStr(statementDay,"yyyy-MM-dd")+" 23:00:00");
						FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
						fqSellerStatementExample.createCriteria().andSellerIdEqualTo(store.getSellerId()).
						andCreateTimeGreaterThan(startdate0).andCreateTimeLessThanOrEqualTo(enddate0).andStateBetween(2, 4);
						List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
						FqSellerStatement fsstate = null;
						if (fsslist.size()>0) {
							fsstate = fsslist.get(0);
							fsstate.setTotalMoney(fsstate.getTotalMoney().add(calTotamt));
							fsstate.setTotalNum(fsstate.getTotalNum()+1);
							fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
						}else{
							
							fsstate = new FqSellerStatement();
							fsstate.setCreateTime(statementDay);
							fsstate.setStatementDate(statementDay);
							fsstate.setSellerId(store.getSellerId());
							fsstate.setState(2);		//   2..为民生通道  	
							fsstate.setTotalMoney(calTotamt);
							fsstate.setTotalNum(1);
							fsstate.setPeriodDate(addOneDay(statementDay));
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
					
					logger.info(">>>>>>>>>>>>>>>>>>订单："+record.getId()+"，民生【支付宝】支付【回调成功】！");
					//更新订单成功，返回成功状态
					return "SUCCESS";
				}
			}
			return null;
		}catch(Exception e){
			logger.error("民生支付宝回调 error", e);
			throw e;
		}
	}
	
	/**
	 * 民生银行的微信支付
	 */
	public Map<String, Object> insertOrderAndGetWXMS(Long couponId,String nickname,String headimgurl, String openId, long sellerId, 
			String storeName,long rebateId, BigDecimal needPay, long storeId,BigDecimal realPay, BigDecimal totamt, 
			BigDecimal noDiscount,BigDecimal rebate, HttpServletRequest request,
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
		 if(null!=couponId){
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
			}else if(couponCfg==2){
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
		 String orderNo = OrderUtil.getOrderNo(SystemConstant.MS_WX_PAY_TYPE);
		 //插入订单表				...pay_type  民生微信  98
		 RebateOrder record = new RebateOrder(orderNo, openId, rebateId, couponId, BigDecimal.ZERO, 98, needPay, realPay, totamt, noDiscount, new Date(), 1,nickname,headimgurl);

		if(realPay.doubleValue()==0){   //0元支付
			    //设置订单已支付
				record.setStatus(SystemConstant.PAY_ISPAY);
				record.setPayTime(new Date());	//支付时间
				orderMapper.insertSelective(record);
				
				FqThirdPay thirdPay = new  FqThirdPay();
				thirdPay.setMoney(realPay);
				thirdPay.setOrderId(record.getId());
				thirdPay.setOrderNo(orderNo);
				thirdPay.setPayTime(new Date());
				thirdPay.setSort(99);  //   99..为民生通道  	
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
					Date statementDay = thirdPay.getPayTime();
					if(is11hBetween12h(thirdPay.getPayTime()))
					{
						statementDay = addOneDay(statementDay);
					}
					String dateend = DateUtil.convertDateToString("yyyy-MM-dd",statementDay);
					Date enddate0 = DateUtil.convertStringToDate("yyyy-MM-dd hh:mm:ss",dateend+" 23:00:00");
					Date startdate0 = DateUtil.convertStringToDate("yyyy-MM-dd hh:mm:ss",DateUtil.getYesDateStr(statementDay,"yyyy-MM-dd")+" 23:00:00");
					logger.info("now:"+dateend+" 23:00:00"+"=================="+DateUtil.getYesDateStr(statementDay,"yyyy-MM-dd")+" 23:00:00");
					FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
					fqSellerStatementExample.createCriteria().andSellerIdEqualTo(sellerId).
					andCreateTimeGreaterThan(startdate0).andCreateTimeLessThanOrEqualTo(enddate0).andStateBetween(2, 4);
					List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
					FqSellerStatement fsstate = null;
					if (fsslist.size()>0) {
						fsstate = fsslist.get(0);
						fsstate.setTotalMoney(fsstate.getTotalMoney().add(realPay));
						fsstate.setTotalNum(fsstate.getTotalNum()+1);
						fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
					}else{
						fsstate = new FqSellerStatement();
						fsstate.setCreateTime(statementDay);
						fsstate.setStatementDate(statementDay);
						fsstate.setSellerId(sellerId);
						fsstate.setState(2);		//   2..为民生通道  	
						fsstate.setTotalMoney(realPay);
						fsstate.setTotalNum(1);
						fsstate.setPeriodDate(addOneDay(statementDay));
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
				task.setPayTime(new Date());
				taskExecutor.execute(task);
				
				// 插入对应的统计表 修改数据 
				RebateCash cash = new RebateCash();
				cash.setRebateId(record.getRebateId());
				cash.setTotamtTotal(record.getTotamt());
				cash.setNeedTotal(record.getNeedPay());
				cash.setRealTotal(record.getRealPay());
				cash.setIntegralTotal(record.getIntegral());
				
				cashMapper.updateStatMoney(cash);
				logger.error(">>>>>>>>>>>>>>>>>>订单："+record.getId()+"，0元支付成功,cardNo="+card.getId());
				//更新订单成功，返回成功状态
				resultMap.put("errcode", "00009527"); //直接返回成功界面
				resultMap.put("orderId",record.getId().toString());
				resultMap.put("orderNo",record.getOrderNo());
				return resultMap;
		 }else{
			 orderMapper.insertSelective(record);
			 AuthenticationChnnel au = authenticationChnnelService.findByStoreIdAndBankIdAndPayChannelAndState(storeId,1,1,2);
		        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
		        nvps.add(new BasicNameValuePair("requestNo", RequestNo.request_no));
		        nvps.add(new BasicNameValuePair("version", "V2.0"));
		        nvps.add(new BasicNameValuePair("productId", "0105"));
		        nvps.add(new BasicNameValuePair("transId", "10"));
		        nvps.add(new BasicNameValuePair("merNo", ConstantsConfigurer.getProperty(SystemConstant.MS_MCH_ID)));
		        nvps.add(new BasicNameValuePair("orderDate", new SimpleDateFormat("yyyyMMdd").format(new Date())));
		        nvps.add(new BasicNameValuePair("orderNo", orderNo));
		        nvps.add(new BasicNameValuePair("returnUrl", ConstantsConfigurer.getProperty(SystemConstant.MS_PAY_RETURNURL)));  //页面通知地址
		        nvps.add(new BasicNameValuePair("notifyUrl", ConstantsConfigurer.getProperty(SystemConstant.MS_PAY_ALL_NOTIFYURL)));  //异步通知地址
		        nvps.add(new BasicNameValuePair("transAmt", realPay.multiply(new BigDecimal(100)).intValue()+""));
		        nvps.add(new BasicNameValuePair("commodityName", storeName+"-移动支付"));
		        nvps.add(new BasicNameValuePair("subMerNo", String.valueOf(storeId)));
		        nvps.add(new BasicNameValuePair("subMerName", storeName));
		        nvps.add(new BasicNameValuePair("openid", openId));
		        nvps.add(new BasicNameValuePair("subChnlMerNo", au.getApplyId()));		//  民生系统 	民生子商户号  "100000001060"
		        
		        logger.info("start:"+new Date());
		        Map<String, String> result = BaseRequest.getSignToSend(nvps);
		        logger.info("end:"+new Date());
		        if(result == null ){
		        	resultMap.put("errcode", "80000");
					return resultMap;
		        }
		        if (MsConstant.DOING_SUCCEE.equals(result.get("respCode"))
		        		||MsConstant.SUCCEE.equals(result.get("respCode"))) {
		        	
		        	String jsonString = result.get("payInfo");
					resultMap.put("errcode", "0000");
					resultMap.put("tradeNo", result.get("tradeNo"));
					resultMap.put("orderNo", orderNo);
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
	 * 民生银行的微信支付 回调
	 */
	public  String updateCheckNotifyWXMS(Map<String,String> notifyParamsMap,HttpServletRequest request ,HttpServletResponse response)throws Exception {
		
		try{
			response.setContentType("text/html;charset=UTF-8");
			if(notifyParamsMap == null){
				logger.info(">>>>>>>>>>>>>>>>>>民生微信支付回调 参数 is null");
				return "FAIL";
			}
			
			if ("9999".equals(notifyParamsMap.get("respCode"))) {
				logger.info(">>>>>>>>>>>>>>>>>>民生微信支付回调，系统异常（出现9999错误码时，建议与上游确认再处理）："+notifyParamsMap.toString());
				return "FAIL";
			}
			if ("9997".equals(notifyParamsMap.get("respCode"))) {
				logger.info(">>>>>>>>>>>>>>>>>>民生微信支付回调,交易结果未知（出现9997错误码时，建议与上游确认再处理）:"+notifyParamsMap.toString());
				return "FAIL";
			}
			if ("0028".equals(notifyParamsMap.get("respCode"))) {
				logger.info(">>>>>>>>>>>>>>>>>>民生微信支付回调,原交易不存在（出现0028错误码时，建议与上游确认再处理）:"+notifyParamsMap.toString());
				return "FAIL";
			}
				
			logger.info(">>>>>>>>>>>>>>>>>>民生微信支付回调 参数 :" + notifyParamsMap.toString());
				//回调成功 -处理业务状态 
			if (SystemConstant.SUCCESS.equals(notifyParamsMap.get("respCode"))) {
				
				String tradeNo = notifyParamsMap.get("orderNo");		
				String wftOrderNo =notifyParamsMap.get("payId") ;			
				String payTime = notifyParamsMap.get("payTime");				//支付时间
				
				RebateOrderExample example = new RebateOrderExample();
				example.createCriteria().andOrderNoEqualTo(tradeNo);
				List<RebateOrder> recordlist  = orderMapper.selectByExample(example);
				
				if(recordlist == null || recordlist.size() == 0){
					logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"不存在");
					return "FAIL";
				}
				RebateOrder record =recordlist.get(0);
				if(SystemConstant.PAY_ISPAY.equals(record.getStatus())){
					logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"己支付");
					return "SUCCESS";
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
					uprecord.setPayTime(new Date());	//支付时间
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
					thirdPay.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss",payTime));
					thirdPay.setSort(99);	// 民生通道
					thirdPay.setThirdOrderNo(wftOrderNo);
					thirdPay.setType(18);		// 18 .. 民生微信支付
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
						Date statementDay = thirdPay.getPayTime();
						if(is11hBetween12h(thirdPay.getPayTime()))
						{
							statementDay = addOneDay(statementDay);
						}
						String dateend = DateUtil.convertDateToString("yyyy-MM-dd",statementDay);
						Date enddate0 = DateUtil.convertStringToDate("yyyy-MM-dd hh:mm:ss",dateend+" 23:00:00");
						Date startdate0 = DateUtil.convertStringToDate("yyyy-MM-dd hh:mm:ss",DateUtil.getYesDateStr(statementDay,"yyyy-MM-dd")+" 23:00:00");
						logger.info("now:"+dateend+" 23:00:00"+"=================="+DateUtil.getYesDateStr(statementDay,"yyyy-MM-dd")+" 23:00:00");
						FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
						fqSellerStatementExample.createCriteria().andSellerIdEqualTo(store.getSellerId()).
						andCreateTimeGreaterThan(startdate0).andCreateTimeLessThanOrEqualTo(enddate0).andStateBetween(2, 4);
						List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
						FqSellerStatement fsstate = null;
						if (fsslist.size()>0) {
							fsstate = fsslist.get(0);
							fsstate.setTotalMoney(fsstate.getTotalMoney().add(calTotamt));
							fsstate.setTotalNum(fsstate.getTotalNum()+1);
							fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
						}else{
							
							fsstate = new FqSellerStatement();
							fsstate.setCreateTime(statementDay);
							fsstate.setStatementDate(statementDay);
							fsstate.setSellerId(store.getSellerId());
							fsstate.setState(2);		//   2..为民生通道  	
							fsstate.setTotalMoney(calTotamt);
							fsstate.setTotalNum(1);
							fsstate.setPeriodDate(addOneDay(statementDay));
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
					task.setPayTime(new Date());
					taskExecutor.execute(task);
					
					// 插入对应的统计表 修改数据 
					RebateCash cash = new RebateCash();
					cash.setRebateId(record.getRebateId());
					cash.setTotamtTotal(calTotamt);
					cash.setNeedTotal(record.getNeedPay());
					cash.setRealTotal(record.getRealPay());
					cash.setIntegralTotal(record.getIntegral());
					
					cashMapper.updateStatMoney(cash);
					
					logger.info(">>>>>>>>>>>>>>>>>>订单："+record.getId()+"，民生【微信】支付【回调成功】！");
					//更新订单成功，返回成功状态
					return "SUCCESS";
				}
			}
			return null;
		}catch(Exception e){
			logger.error("民生微信回调 error", e);
			throw e;
		}
	}

	/**
	 * 民生银行QQ支付
	 */
	@Override
	public  Map<String,Object> insertOrderAndGetQQMS(String openId, long sellerId,
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
		RebateOrder record = new RebateOrder(orderNo, openId, rebateId, 0L, BigDecimal.ZERO, 1, needPay, realPay, totamt, noDiscount, new Date(), 1,nickname,headimgurl);
		int size = orderMapper.insertSelective(record);
		if(size<=0){
			resultMap.put("errcode", SystemConstant.ERROR);
			logger.info(">>>>>>>>>>>>>>>>>>>>民生QQ保存订单失败");
			return resultMap;
		}
		 AuthenticationChnnel au = authenticationChnnelService.findByStoreIdAndBankIdAndPayChannelAndState(storeId,1,1,2);
        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
        nvps.add(new BasicNameValuePair("requestNo", RequestNo.request_no));
        nvps.add(new BasicNameValuePair("version", "V2.0"));
        nvps.add(new BasicNameValuePair("productId", "0119"));
        nvps.add(new BasicNameValuePair("transId", "10"));
        nvps.add(new BasicNameValuePair("merNo", ConstantsConfigurer.getProperty(SystemConstant.MS_MCH_ID)));
        nvps.add(new BasicNameValuePair("orderDate", new SimpleDateFormat("yyyyMMdd").format(new Date())));
        nvps.add(new BasicNameValuePair("orderNo", orderNo));
        nvps.add(new BasicNameValuePair("returnUrl", ConstantsConfigurer.getProperty(SystemConstant.MS_PAY_RETURNURL)));  //页面通知地址
        nvps.add(new BasicNameValuePair("notifyUrl", ConstantsConfigurer.getProperty(SystemConstant.MS_PAY_ALL_NOTIFYURL)));  //异步通知地址
        nvps.add(new BasicNameValuePair("transAmt", realPay.multiply(new BigDecimal(100)).intValue()+""));
        nvps.add(new BasicNameValuePair("commodityName", storeName+"-移动支付"));
        nvps.add(new BasicNameValuePair("subMerNo", String.valueOf(storeId)));
        nvps.add(new BasicNameValuePair("subMerName", storeName));
        nvps.add(new BasicNameValuePair("subChnlMerNo", au.getApplyId()));		//  民生系统 	民生子商户号 
        Map<String, String> result = BaseRequest.getSignToSend(nvps);
        if(result == null ){
        	resultMap.put("errcode", "80000");
			return resultMap;
        }
        if (MsConstant.DOING_SUCCEE.equals(result.get("respCode")) 
        		|| SystemConstant.SUCCESS.equals(result.get("respCode"))) {
        	String jsonString = result.get("payInfo");
			resultMap.put("jsonstr", jsonString);
			resultMap.put("errcode", "0000");
			resultMap.put("orderNo", orderNo);
			resultMap.put("orderId",record.getId().toString());
			return resultMap;
		}else{
			resultMap.put("errcode", "80000");
			return resultMap;
		}
	}


	/**
	 * 民生银行QQ支付回调
	 */
	public String updateCheckNotifyQQMS(Map<String, String> notifyParamsMap,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
			if(notifyParamsMap == null){
				logger.info(">>>>>>>>>>>>>>>>>>民生QQ支付回调 参数 is null");
				return "FAIL";
			}
			
			if ("9999".equals(notifyParamsMap.get("respCode"))) {
				logger.info(">>>>>>>>>>>>>>>>>>民生QQ支付回调，系统异常（出现9999错误码时，建议与上游确认再处理）："+notifyParamsMap.toString());
				return "FAIL";
			}
			if ("9997".equals(notifyParamsMap.get("respCode"))) {
				logger.info(">>>>>>>>>>>>>>>>>>民生QQ支付回调,交易结果未知（出现9997错误码时，建议与上游确认再处理）:"+notifyParamsMap.toString());
				return "FAIL";
			}
			if ("0028".equals(notifyParamsMap.get("respCode"))) {
				logger.info(">>>>>>>>>>>>>>>>>>民生QQ支付回调,原交易不存在（出现0028错误码时，建议与上游确认再处理）:"+notifyParamsMap.toString());
				return "FAIL";
			}
				
			logger.info(">>>>>>>>>>>>>>>>>>民生QQ支付回调 参数 :" + notifyParamsMap.toString());
				//回调成功 -处理业务状态 
			if (SystemConstant.SUCCESS.equals(notifyParamsMap.get("respCode"))) {
				
				String tradeNo = notifyParamsMap.get("orderNo");		
				String CQOrderNo =notifyParamsMap.get("payId") ;			
				String payTime = notifyParamsMap.get("payTime");				//支付完成时间
				String bankType = notifyParamsMap.get("bankType");	
				
				String openid = notifyParamsMap.get("chnlUserId");	 // 用户在商户appid下的唯一标识   QQ公众号
				
				
				RebateOrderExample example = new RebateOrderExample();
				example.createCriteria().andOrderNoEqualTo(tradeNo);
				List<RebateOrder> recordlist  = orderMapper.selectByExample(example);
				
				if(recordlist == null || recordlist.size() == 0){
					logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"不存在");
					return "FAIL";
				}
				RebateOrder record =recordlist.get(0);
				
				if(SystemConstant.PAY_ISPAY.equals(record.getStatus())){
					logger.info(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"己支付");
					return "FAIL";
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
					uprecord.setPayTime(new Date());	//支付时间
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
					thirdPay.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss",payTime));
					thirdPay.setSort(99);
					thirdPay.setThirdOrderNo(CQOrderNo);
					thirdPay.setType(18);		// 18 .. 民生QQPAY
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
					if(bankType.equals("CFT")){
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
						Date statementDay = thirdPay.getPayTime();
						if(is11hBetween12h(thirdPay.getPayTime()))
						{
							statementDay = addOneDay(statementDay);
						}
						String dateend = DateUtil.convertDateToString("yyyy-MM-dd",statementDay);
						Date enddate0 = DateUtil.convertStringToDate("yyyy-MM-dd hh:mm:ss",dateend+" 23:00:00");
						Date startdate0 = DateUtil.convertStringToDate("yyyy-MM-dd hh:mm:ss",DateUtil.getYesDateStr(statementDay,"yyyy-MM-dd")+" 23:00:00");
						logger.info("now:"+dateend+" 23:00:00"+"=================="+DateUtil.getYesDateStr(statementDay,"yyyy-MM-dd")+" 23:00:00");
						FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
						fqSellerStatementExample.createCriteria().andSellerIdEqualTo(store.getSellerId()).
						andCreateTimeGreaterThan(startdate0).andCreateTimeLessThanOrEqualTo(enddate0).andStateBetween(2, 4);
						List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
						FqSellerStatement fsstate = null;
						if (fsslist.size()>0) {
							fsstate = fsslist.get(0);
							fsstate.setTotalMoney(fsstate.getTotalMoney().add(calTotamt));
							fsstate.setTotalNum(fsstate.getTotalNum()+1);
							fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
						}else{
						
								fsstate = new FqSellerStatement();
								fsstate.setCreateTime(statementDay);
								fsstate.setStatementDate(statementDay);
								fsstate.setSellerId(store.getSellerId());
								fsstate.setState(2);		//   2..为民生通道  	
								fsstate.setTotalMoney(calTotamt);
								fsstate.setTotalNum(1);
								fsstate.setPeriodDate(addOneDay(statementDay));
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
					
					logger.info(">>>>>>>>>>>>>>>>>>订单："+record.getId()+"，民生【QQPAY】支付【回调成功】！");
					//更新订单成功，返回成功状态
					return "SUCCESS";
				}
			}
			return null;
		}catch(Exception e){
			logger.error("民生QQPAY回调 error", e);
			throw e;
		}
	}

	/**
	 * 民生统一支付 回调
	 */
	@Override
	public String updateCheckNotifyMSPay(Map<String, String> notifyParamsMap,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(notifyParamsMap == null)
		{
			
			logger.info(">>>>>>>>>>>>>>>>>>民生统一支付回调 参数 is null");
			return "FAIL";
		}
		logger.info(">>>>>>>>>>>>>>>>>>民生统一支付 回调:notifyParamsMap is not null>>>>>>productId:" +notifyParamsMap.get("productId")+"wxStoreOrder:" +notifyParamsMap.get("wxStoreOrder"));
		
		if("0105".equals(notifyParamsMap.get("productId")))				//  	..0105-微信公众号支付 
		{
			if("yes".equals(notifyParamsMap.get("wxStoreOrder"))){
				
				String flag = updateCheckNotifyStroeWXMS(notifyParamsMap,request,response);	//微信点餐回调
				return flag;
				
			}else{
				//微信回调方法
				String flag = updateCheckNotifyWXMS(notifyParamsMap,request,response);
				return flag;
			}
		
		} 
		
		else if("0115".equals(notifyParamsMap.get("productId"))) 	//  	..0115-支付宝支付窗支付
		{
			//支付宝回调方法
			String flag = updateCheckNotifyAlipayMS(notifyParamsMap);
			return flag;
		}
		
		else if("0119".equals(notifyParamsMap.get("productId")))	 //  	..0119-QQ公众号支付 
		{
				//QQPAY回调方法
					
			String flag = updateCheckNotifyQQMS(notifyParamsMap,request,response);
			return flag;
		}
			// 	扫码枪反扫回调  	..0120-QQ刷卡（反扫）	0110-支付宝刷卡支付		0106-微信刷卡（反扫）
		
		else if("0120".equals(notifyParamsMap.get("productId")) 
				|| "0110".equals(notifyParamsMap.get("productId")) 
				|| "0106".equals(notifyParamsMap.get("productId")))  
		{
			
			String flag = updateCheckNotifyMicorMS(notifyParamsMap,request,response);
			return flag;
		}
		
		return "FAIL";
	}
	
	/**
	 * 民生银行反扫支付回调
	 */
	public String updateCheckNotifyMicorMS(Map<String, String> notifyParamsMap,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
			if(notifyParamsMap == null){
				logger.info(">>>>>>>>>>>>>>>>>>民生反扫支付回调 参数 is null");
				return "FAIL";
			}
			
			if ("9999".equals(notifyParamsMap.get("respCode"))) {
				logger.info(">>>>>>>>>>>>>>>>>>民生反扫支付回调，系统异常（出现9999错误码时，建议与上游确认再处理）："+notifyParamsMap.toString());
				return "FAIL";
			}
			if ("9997".equals(notifyParamsMap.get("respCode"))) {
				logger.info(">>>>>>>>>>>>>>>>>>民生反扫支付回调,交易结果未知（出现9997错误码时，建议与上游确认再处理）:"+notifyParamsMap.toString());
				return "FAIL";
			}
			if ("0028".equals(notifyParamsMap.get("respCode"))) {
				logger.info(">>>>>>>>>>>>>>>>>>民生反扫支付回调,原交易不存在（出现0028错误码时，建议与上游确认再处理）:"+notifyParamsMap.toString());
				return "FAIL";
			}
				
			logger.info(">>>>>>>>>>>>>>>>>>民生反扫支付回调 参数 :" + notifyParamsMap.toString());
				//回调成功 -处理业务状态 
			if (SystemConstant.SUCCESS.equals(notifyParamsMap.get("respCode"))) {
				
				String tradeNo = notifyParamsMap.get("orderNo");		
				String CQOrderNo =notifyParamsMap.get("payId") ;			
				String payTime = notifyParamsMap.get("payTime");				//支付完成时间transAmt
				String bankType = notifyParamsMap.get("bankType");	
				String transAmt = notifyParamsMap.get("transAmt");	
				
				String openid = notifyParamsMap.get("chnlUserId");	 // 用户在商户appid下的唯一标识   QQ公众号
				
				
				FqMicroOrderExample example = new FqMicroOrderExample();
				example.createCriteria().andOrderNoEqualTo(tradeNo);
				List<FqMicroOrder> recordlist  = fqmicroOrderMapper.selectByExample(example);
				
				if(recordlist == null || recordlist.size() == 0){
					logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"不存在");
					return "FAIL";
				}
				FqMicroOrder record =recordlist.get(0);
				
				if(SystemConstant.PAY_ISPAY.equals(record.getStatus())){
					logger.info(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"己支付");
					return "FAIL";
				}else{
					StoreInfo store = storeService.queryStoreInfoBySeller(record.getSellerId());
					doHandleMsPaySuccess(record.getSellerId(),store,notifyParamsMap);
					
					logger.info(">>>>>>>>>>>>>>>>>>订单："+record.getId()+"，民生【反扫】支付【回调成功】！");
					//更新订单成功，返回成功状态
					return "SUCCESS";
				}
			}
			return null;
		}catch(Exception e){
			logger.error("民生反扫回调 error", e);
			throw e;
		}
	}
	
	/**
	 * 民生银行（微信，支付宝）反扫支付
	 */
	@Override
	public FqThirdPay insertPayOrderByMSPay(SellerInfo seller, BigDecimal money, String authCode,
				       HttpServletRequest request, HttpServletResponse response) {
		logger.info(">>>>>>>>>>>>>>>>>>民生银行（微信，支付宝）反扫支付>>>>>>  Start");
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
	        //nvps.add(new BasicNameValuePair("subChnlMerNo", "100000001060")); 			//au.getApplyId()//"100000001059"
			
		    if(authCodeBegin>9 && authCodeBegin < 16) {
		    	 orderNo = OrderUtil.getOrderNo(SystemConstant.MS_WX_MICOR_PAY_TYPE);		//微信
		    	 payType = 106;
		    	 nvps.add(new BasicNameValuePair("productId", "0106"));		//0106-微信刷卡（反扫）
		    	 nvps.add(new BasicNameValuePair("subMerNo", String.valueOf(store.getId())));
			     nvps.add(new BasicNameValuePair("subMerName", store.getName()));
			     AuthenticationChnnel au = authenticationChnnelService.findByStoreIdAndBankIdAndPayChannelAndState(store.getId(),1,1,2);
			     nvps.add(new BasicNameValuePair("subChnlMerNo", au.getApplyId())); 			//au.getApplyId()//"100000001059"
		    }else if(authCodeBegin == 28) {
		    	 orderNo = OrderUtil.getOrderNo(SystemConstant.MS_ALI_MICOR_PAY_TYPE);		//支付宝
		    	 payType = 110;
		    	 nvps.add(new BasicNameValuePair("productId", "0110"));		//0110-支付宝刷卡支付
		    	 nvps.add(new BasicNameValuePair("storeId", String.valueOf(store.getId())));
			     nvps.add(new BasicNameValuePair("terminalId", store.getName()));
			     AuthenticationChnnel au = authenticationChnnelService.findByStoreIdAndBankIdAndPayChannelAndState(store.getId(),1,2,2);
			     nvps.add(new BasicNameValuePair("subChnlMerNo", au.getApplyId())); 	
		    }else if(authCodeBegin == 91){
		    	orderNo = OrderUtil.getOrderNo(SystemConstant.MS_QQ_MICOR_PAY_TYPE);		//QQPay
		    	 payType = 120;
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
			if (SystemConstant.SUCCESS.equals(result.get("respCode")) || MsConstant.DOING_SUCCEE.equals(result.get("respCode"))) {
				Map<String, String> selectMap = queryPayStatus(orderNo);
				selectMap.put("productId", result.get("productId"));
				logger.info(">>>>>>>>>>>>>>>>>>MS 扫码枪  MICRO 支付响应 参数selectMap=" + selectMap.toString());
				if (("SUCCESS").equals(selectMap.get("selectCode"))){
					// 交易成功
					FqThirdPay fqThirdPay = doHandleMsPaySuccess(seller.getId(),store,selectMap);
					logger.info("当前订单" + fqThirdPay.getOrderNo() +"----------用户支付成功");
					return fqThirdPay;
				}
				
				while(cxNum > 0){
					selectMap = queryPayStatus(orderNo);
					selectMap.put("productId", result.get("productId"));
					//正在交易  
					if (("USERPAYING").equals(selectMap.get("selectCode"))){
						// 还未成功 等待10秒再次发起查询
						if(cxNum!=1){
							microOrder.setCxNum(cxNum-1);
							fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
						}
						logger.info("当前订单" + orderNo +"----------"+cxNum+ "交易用户正在支付中，再次等待10秒后发起查询");
						Thread.sleep(10000);
						cxNum--;
					}else if (("SUCCESS").equals(selectMap.get("selectCode"))){
						// 交易成功
						FqThirdPay fqThirdPay = doHandleMsPaySuccess(seller.getId(),store,selectMap);
						logger.info("当前订单" + fqThirdPay.getOrderNo() +"----------用户支付成功");
						return fqThirdPay;
					} else {
						if(cxNum!=1){
							microOrder.setCxNum(cxNum-1);
							fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
						}
						logger.info("当前订单" + orderNo +"----------"+cxNum+ "支付状态未知");
						Thread.sleep(10000);
						cxNum--;
					}
				}
				return null;
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
	
	/**
	 * 民生银行（微信，支付宝）反扫支付 APP
	 */
	@Override
	public FqThirdPay insertPayOrderByMSPayAPP(SellerInfo seller, BigDecimal money, String authCode,
				       HttpServletRequest request, HttpServletResponse response) {
		logger.info(">>>>>>>>>>>>>>>>>>进入民生银行（微信，支付宝）反扫支付 APP>>>>>>  Start");
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
			
		    if(authCodeBegin>9 && authCodeBegin < 16) {
		    	 orderNo = OrderUtil.getOrderNo(SystemConstant.MS_WX_MICOR_PAY_TYPE);		//微信
		    	 payType = 106;
		    	 nvps.add(new BasicNameValuePair("productId", "0106"));		//0106-微信刷卡（反扫）
		    	 nvps.add(new BasicNameValuePair("subMerNo", String.valueOf(store.getId())));
			     nvps.add(new BasicNameValuePair("subMerName", store.getName()));
			     AuthenticationChnnel au = authenticationChnnelService.findByStoreIdAndBankIdAndPayChannelAndState(store.getId(),1,1,2);
			     nvps.add(new BasicNameValuePair("subChnlMerNo", au.getApplyId())); 			//au.getApplyId()//"100000001059"
		    }else if(authCodeBegin == 28) {
		    	 orderNo = OrderUtil.getOrderNo(SystemConstant.MS_ALI_MICOR_PAY_TYPE);		//支付宝
		    	 payType = 110;
		    	 nvps.add(new BasicNameValuePair("productId", "0110"));		//0110-支付宝刷卡支付
		    	 nvps.add(new BasicNameValuePair("storeId", String.valueOf(store.getId())));
			     nvps.add(new BasicNameValuePair("terminalId", store.getName()));
			     AuthenticationChnnel au = authenticationChnnelService.findByStoreIdAndBankIdAndPayChannelAndState(store.getId(),1,2,2);
			     nvps.add(new BasicNameValuePair("subChnlMerNo", au.getApplyId())); 	
		    }else if(authCodeBegin == 91){
		    	orderNo = OrderUtil.getOrderNo(SystemConstant.MS_QQ_MICOR_PAY_TYPE);		//QQPay
		    	 payType = 120;
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
			
			logger.info(">>>>>>>>>>>>>>>>>>MS MICRO支付 组装参数，发送请求.......");
			
			Map<String, String> result = BaseRequest.getSignToSend(nvps);
			
			if (result == null) {
				logger.info(">>>>>>>>>>>>>>>>>>MS MICRO支付响应 参数 is null");
				microOrder.setStatus(1);
				microOrder.setCxNum(6);
				microOrder.setState("USERPAYING");
				fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
				return null;
			}
			
			logger.info(">>>>>>>>>>>>>>>>>>MS 扫码枪  MICRO支付 响应参数Map=" + result.toString());
			int cxNum = 6;
			if (SystemConstant.SUCCESS.equals(result.get("respCode")) || MsConstant.DOING_SUCCEE.equals(result.get("respCode"))) {
				Map<String, String> selectMap = queryPayStatus(orderNo);
				selectMap.put("productId", result.get("productId"));
				if (("SUCCESS").equals(selectMap.get("selectCode"))){
					// 交易成功
					FqThirdPay fqThirdPay = doHandleMsPaySuccess(seller.getId(),store,selectMap);
					logger.info("当前订单" + fqThirdPay.getOrderNo() +"----------用户支付成功");
					return fqThirdPay;
				}else {
					microOrder.setStatus(1);
					microOrder.setCxNum(1);
					microOrder.setState("USERPAYING");
					fqmicroOrderMapper.updateByPrimaryKeySelective(microOrder);
					logger.info("当前订单" + orderNo +"----------用户APP支付失败");
					return null;
				}
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
	
	
	/*
	 * 支付状态查询
	 */
	private Map<String, String> queryPayStatus(String orderNo){
		logger.info(">>>>>>>>>>>>>>>>>>进入民生银行统一支付状态查询接口>>>>>>  Start");
		List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
        nvps.add(new BasicNameValuePair("requestNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())));
        nvps.add(new BasicNameValuePair("version", "V2.0"));
        nvps.add(new BasicNameValuePair("transId", "04"));
        nvps.add(new BasicNameValuePair("merNo", ConstantsConfigurer.getProperty(SystemConstant.MS_MCH_ID)));
        nvps.add(new BasicNameValuePair("orderDate", new SimpleDateFormat("yyyyMMdd").format(new Date())));
        nvps.add(new BasicNameValuePair("orderNo", orderNo));
		
        Map<String, String> notifyParamsMap = BaseRequest.getSignToSend(nvps);
        
        if(notifyParamsMap == null ){
        	logger.info(">>>>>>>>>>>>>>>>>>进入民生银行统一支付状态查询接口：执行异常 map is null，orderNo："+orderNo);
        	notifyParamsMap = new HashMap<String, String>();
        	notifyParamsMap.put("selectCode","FAIL");
			return notifyParamsMap;
        }
		if(MsConstant.DOING_SUCCEE.equals(notifyParamsMap.get("respCode")) 
        		|| SystemConstant.SUCCESS.equals(notifyParamsMap.get("respCode")))
		{
			logger.info(">>>>>>>>>>>>>>>>>>进入民生银行统一支付状态查询接口："+notifyParamsMap.get("origRespDesc")+"，orderNo："+orderNo);
			if(SystemConstant.SUCCESS.equals(notifyParamsMap.get("origRespCode")))
			{
				notifyParamsMap.put("selectCode","SUCCESS");
				return notifyParamsMap;
			}else if(MsConstant.DOING_SUCCEE.equals(notifyParamsMap.get("origRespCode")))
			{
				notifyParamsMap.put("selectCode","USERPAYING");
				return notifyParamsMap;
			}
		}
		notifyParamsMap.put("selectCode","FAIL");
		return notifyParamsMap;
	}

	private FqThirdPay doHandleMsPaySuccess(Long  sellerId,StoreInfo store, Map<String, String> signMap) {
		try {
			
		
		logger.info(">>>>>>>>>>>>>>>>>>进入民生银行统一支付 MS MICRO支付 doHandleMsPaySuccess：start，store："+store);
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
		if (type == 1) {
			thirdPay.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss",payTime));
			thirdPay.setThirdOrderNo(weixinOrderNo);
			thirdPay.setOpenId(openid);
		}else{
			if (StringUtils.isEmpty(payTime)) {
				thirdPay.setPayTime(new Date());
			}else{
				thirdPay.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss",payTime));
			}
			if (!StringUtils.isEmpty(weixinOrderNo)) {
				thirdPay.setThirdOrderNo(weixinOrderNo);
			}
			
		}
		
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
		fqThirdPayMapper.insertSelective(thirdPay);
		SellerInfo seller = sellerInfoMapper.selectByPrimaryKey(sellerId);
		if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
			Date statementDay = thirdPay.getPayTime();
			if(is11hBetween12h(thirdPay.getPayTime()))
			{
				statementDay = addOneDay(statementDay);
			}
			String dateend = DateUtil.convertDateToString("yyyy-MM-dd",statementDay);
			Date enddate0 = DateUtil.convertStringToDate("yyyy-MM-dd hh:mm:ss",dateend+" 23:00:00");
			Date startdate0 = DateUtil.convertStringToDate("yyyy-MM-dd hh:mm:ss",DateUtil.getYesDateStr(statementDay,"yyyy-MM-dd")+" 23:00:00");
			logger.info("now:"+dateend+" 23:00:00"+"=================="+DateUtil.getYesDateStr(statementDay,"yyyy-MM-dd")+" 23:00:00");
			FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
			fqSellerStatementExample.createCriteria().andSellerIdEqualTo(store.getSellerId()).
			andCreateTimeGreaterThan(startdate0).andCreateTimeLessThanOrEqualTo(enddate0).andStateBetween(2, 4);
			List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
			FqSellerStatement fsstate = null;
			if (fsslist.size()>0) {
				fsstate = fsslist.get(0);
				fsstate.setTotalMoney(fsstate.getTotalMoney().add(calTotamt));
				fsstate.setTotalNum(fsstate.getTotalNum()+1);
				fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
			}else{
			
				fsstate = new FqSellerStatement();
				fsstate.setCreateTime(statementDay);
				fsstate.setStatementDate(statementDay);
				fsstate.setSellerId(sellerId);
				fsstate.setState(2);		//   2..为民生通道  	
				fsstate.setTotalMoney(calTotamt);
				fsstate.setTotalNum(1);
				fsstate.setPeriodDate(addOneDay(statementDay));
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
		task.setPayTime(new Date());
		taskExecutor.execute(task);
		
		
		return thirdPay;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Map<String, String> insertOrderFqStoreByWXMS(FqOrder order,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info(">>>>>>>>>>>>>>>>>>进入【点餐】 民生微信支付>>>>>>  Start");
		Map<String,String> resultMap = new HashMap<String,String>();
		int a = fqOrderMapper.updateByPrimaryKeySelective(order);
		if (a <=0) {
			resultMap.put("errcode", "80000");
			return resultMap;
		}
		try {
			FqUserInfo user = fqUserInfoMapper.selectByPrimaryKey(order.getUserId());
			FqStore store = fqStoreService.getFqStoreById(order.getStoreId());
			StoreInfo store1 = storeService.queryStoreInfoBySeller(store.getSellerId());
			AuthenticationChnnel au = authenticationChnnelService.findByStoreIdAndBankIdAndPayChannelAndState(store1.getId(),1,1,2);
			logger.info(">>>>>>>>>>>>>>>>>>进入【点餐】 民生微信支付>>>au.getId()"+au.getId()+"store.getId()"+store.getId()+"store1.getId()"+store1.getId());
			List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
		        nvps.add(new BasicNameValuePair("requestNo", RequestNo.request_no));
		        nvps.add(new BasicNameValuePair("version", "V2.0"));
		        nvps.add(new BasicNameValuePair("productId", "0105"));
		        nvps.add(new BasicNameValuePair("transId", "10"));
		        nvps.add(new BasicNameValuePair("merNo", ConstantsConfigurer.getProperty(SystemConstant.MS_MCH_ID)));
		        nvps.add(new BasicNameValuePair("orderDate", new SimpleDateFormat("yyyyMMdd").format(new Date())));
		        nvps.add(new BasicNameValuePair("orderNo", order.getOrderNo()));
		        nvps.add(new BasicNameValuePair("returnUrl", ConstantsConfigurer.getProperty(SystemConstant.MS_PAY_RETURNURL)));  //页面通知地址
		        nvps.add(new BasicNameValuePair("notifyUrl", ConstantsConfigurer.getProperty(SystemConstant.MS_PAY_ALL_NOTIFYURL)));  //异步通知地址
		        nvps.add(new BasicNameValuePair("transAmt", order.getRebateAmount().multiply(new BigDecimal(100)).intValue()+""));
		        nvps.add(new BasicNameValuePair("commodityName", order.getStoreName()+"-移动支付"));
		        nvps.add(new BasicNameValuePair("subMerNo", String.valueOf(order.getStoreId())));
		        nvps.add(new BasicNameValuePair("subMerName", order.getStoreName()));
		        nvps.add(new BasicNameValuePair("openid", user.getOpenId()));
		        nvps.add(new BasicNameValuePair("subChnlMerNo", au.getApplyId()));		//  民生系统 	民生子商户号  "100000001060"
		        Map<String, String> notifyParamsMap = BaseRequest.getSignToSend(nvps);
		        if (MsConstant.DOING_SUCCEE.equals(notifyParamsMap.get("respCode")) 
		        		|| SystemConstant.SUCCESS.equals(notifyParamsMap.get("respCode"))) {
					String jsonString = notifyParamsMap.get("payInfo");
					resultMap.put("errcode", "0000");
					resultMap.put("tradeNo", notifyParamsMap.get("tradeNo"));
					resultMap.put("orderNo", order.getOrderNo());
					resultMap.put("jsonstr", jsonString);
					resultMap.put("orderId",order.getId()+"");
					logger.info(">>>>>>>>>>>>>>>>>>【点餐】 民生微信支付>>>>>>  响应成功！");
					return resultMap;
				}else{
					resultMap.put("errcode", "80000");
					logger.info(">>>>>>>>>>>>>>>>>>【点餐】 民生微信支付>>>>>>  响应失败！");
					return resultMap;
				}
		} catch (Exception e) {
			resultMap.put("errcode", "80000");
			logger.info(">>>>>>>>>>>>>>>>>>【点餐】 民生微信支付>>>>>>  响应失败！"+e.getMessage());
			return resultMap;
		}
		
	}
	
	/**
	 * 民生银行的点餐微信支付 回调
	 */
	public  String updateCheckNotifyStroeWXMS(Map<String,String> notifyParamsMap,HttpServletRequest request ,HttpServletResponse response)throws Exception {
		logger.info(">>>>>>>>>>>>>>>>>>进入【点餐】 民生微信支付回调Start");
		try{
			response.setContentType("text/html;charset=UTF-8");
			if(notifyParamsMap == null){
				logger.info(">>>>>>>>>>>>>>>>>>点餐民生微信支付回调 参数 is null");
				return "FAIL";
			}
			
			if ("9999".equals(notifyParamsMap.get("respCode"))) {
				logger.info(">>>>>>>>>>>>>>>>>>点餐民生微信支付回调，系统异常（出现9999错误码时，建议与上游确认再处理）："+notifyParamsMap.toString());
				return "FAIL";
			}
			if ("9997".equals(notifyParamsMap.get("respCode"))) {
				logger.info(">>>>>>>>>>>>>>>>>>点餐民生微信支付回调,交易结果未知（出现9997错误码时，建议与上游确认再处理）:"+notifyParamsMap.toString());
				return "FAIL";
			}
			if ("0028".equals(notifyParamsMap.get("respCode"))) {
				logger.info(">>>>>>>>>>>>>>>>>>点餐民生微信支付回调,原交易不存在（出现0028错误码时，建议与上游确认再处理）:"+notifyParamsMap.toString());
				return "FAIL";
			}
				
			logger.info(">>>>>>>>>>>>>>>>>>点餐民生微信支付回调 参数 :" + notifyParamsMap.toString());
				//回调成功 -处理业务状态 
			if (SystemConstant.SUCCESS.equals(notifyParamsMap.get("respCode"))) {
				String tradeNo = notifyParamsMap.get("orderNo");		
				String wftOrderNo =notifyParamsMap.get("payId") ;			
				String bankTypeCode = notifyParamsMap.get("bankType");
				String payTime = notifyParamsMap.get("payTime");	
				FqOrderExample example = new FqOrderExample();
				example.createCriteria().andOrderNoEqualTo(tradeNo);
				List<FqOrder> recordlist  = fqOrderMapper.selectByExample(example);
				
				if(recordlist == null || recordlist.size() == 0){
					logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"不存在");
					//response.getWriter().print("fail");
					return "FAIL";
				}
				FqOrder record =recordlist.get(0);
				if(!SystemConstant.PAY_WAITPAY.equals(record.getStatus())){
					logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"己支付");
					//response.getWriter().print("fail");
					return "SUCCESS";
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
					thirdPay.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss",payTime));
					thirdPay.setSort(99);	//99为民生通道
					thirdPay.setThirdOrderNo(wftOrderNo);
					thirdPay.setType(18);	// 18 为微信支付
					thirdPay.setSellerId(store.getSellerId());
					thirdPay.setTotamt(record.getTotalAmount());
					thirdPay.setUserId(user.getId());
					thirdPay.setOpenId(user.getOpenId());
					thirdPay.setBankType(bankType);
					thirdPay.setBankTypeCode(bankTypeCode);
					fqThirdPayMapper.insertSelective(thirdPay);
					
					
					if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
						Date statementDay = thirdPay.getPayTime();
						if(is11hBetween12h(thirdPay.getPayTime()))
						{
							statementDay = addOneDay(statementDay);
						}
						String dateend = DateUtil.convertDateToString("yyyy-MM-dd",statementDay);
						Date enddate0 = DateUtil.convertStringToDate("yyyy-MM-dd hh:mm:ss",dateend+" 23:00:00");
						Date startdate0 = DateUtil.convertStringToDate("yyyy-MM-dd hh:mm:ss",DateUtil.getYesDateStr(statementDay,"yyyy-MM-dd")+" 23:00:00");
						logger.info("now:"+dateend+" 23:00:00"+"=================="+DateUtil.getYesDateStr(statementDay,"yyyy-MM-dd")+" 23:00:00");
						FqSellerStatementExample fqSellerStatementExample = new FqSellerStatementExample();
						fqSellerStatementExample.createCriteria().andSellerIdEqualTo(store.getSellerId()).
						andCreateTimeGreaterThan(startdate0).andCreateTimeLessThanOrEqualTo(enddate0).andStateBetween(2, 4);
						List<FqSellerStatement> fsslist = fqSellerStatementMapper.selectByExample(fqSellerStatementExample);
						FqSellerStatement fsstate = null;
						if (fsslist.size()>0) {
							fsstate = fsslist.get(0);
							fsstate.setTotalMoney(fsstate.getTotalMoney().add(record.getPayAmount()));
							fsstate.setTotalNum(fsstate.getTotalNum()+1);
							fqSellerStatementMapper.updateByPrimaryKeySelective(fsstate);
						}else{
							
							fsstate = new FqSellerStatement();
							fsstate.setCreateTime(statementDay);
							fsstate.setStatementDate(statementDay);
							fsstate.setSellerId(store.getSellerId());
							fsstate.setState(2);
							fsstate.setTotalMoney(record.getPayAmount());
							fsstate.setTotalNum(1);
							fsstate.setPeriodDate(addOneDay(statementDay));
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
					//ob8WbwWQWmdZoykLjOvwCLw5CRmk
					data1.put("touser", "ob8Wbwc0J-UcF6_QZiJfBjCWJ_LY");
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
					
					logger.info(">>>>>>>>>>>>>>>>>>点餐订单："+record.getId()+"，回调成功");
					//更新订单成功，返回成功状态
					//response.getWriter().print("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
					return "SUCCESS";
				}
			}
			return null;
		}catch(Exception e){
			logger.error("点餐民生微信支付回调 error", e);
			throw e;
		}
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
	public Date addOneDay(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, 1);
		//System.out.print(calendar.getTime());
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 10);
		return calendar.getTime();
		
	}
	public static void main(String[] args) {
		
		//is11hBetween12h(DateUtil.convertStringToDate("yyyyMMddHHmmss","20161108230508"));
		
	}
}

