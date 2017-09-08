package cn.qhjys.mall.service.impl;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.FqSellerStatement;
import cn.qhjys.mall.entity.FqSellerStatementExample;
import cn.qhjys.mall.entity.FqStoreCredit;
import cn.qhjys.mall.entity.FqStoreCreditExample;
import cn.qhjys.mall.entity.FqThirdPay;
import cn.qhjys.mall.entity.FqUserInfo;
import cn.qhjys.mall.entity.FqUserInfoExample;
import cn.qhjys.mall.entity.RebateOrder;
import cn.qhjys.mall.entity.RebateOrderExample;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.StoreRebate;
import cn.qhjys.mall.mapper.FqSellerStatementMapper;
import cn.qhjys.mall.mapper.FqThirdPayMapper;
import cn.qhjys.mall.mapper.FqUserInfoMapper;
import cn.qhjys.mall.mapper.RebateOrderMapper;
import cn.qhjys.mall.mapper.StoreInfoMapper;
import cn.qhjys.mall.mapper.StoreRebateMapper;
import cn.qhjys.mall.quartz.PushPayInfoTask;
import cn.qhjys.mall.service.FqStoreRateService;
import cn.qhjys.mall.service.PayCheckService;
import cn.qhjys.mall.service.SellerService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.DateUtil;
import cn.qhjys.mall.weixin.util.HttpClientUtil;
import cn.qhjys.mall.weixin.util.MD5Util;
import cn.qhjys.mall.weixin.util.SystemConstant;
import cn.qhjys.mall.weixin.util.XMLUtil;

@Service("payCheckService")
public class PayCheckServiceImpl extends Base implements PayCheckService {

	@Autowired
	private RebateOrderMapper rebateOrderMapper;
	
	@Autowired
	private StoreRebateMapper storeRebateMapper;
	
	@Autowired
	private StoreInfoMapper storeInfoMapper;
	@Autowired
	private FqStoreRateService fqStoreRateService;
	@Autowired
	private FqThirdPayMapper fqThirdPayMapper;
	@Autowired
	private FqUserInfoMapper fqUserInfoMapper;
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private FqSellerStatementMapper fqSellerStatementMapper;
	
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	
	@Override
	public int updatePayCheck(Date beginDate, Date endDate) {
		try {
			int a = 0 ;			
			String JSAPI_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
			String appId = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_APPID);
			String partner = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_PAY_PARTNER);
			String pkey = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_PAY_PKEY);
			RebateOrderExample example = new RebateOrderExample();
			example.createCriteria().andCreateTimeBetween(beginDate, endDate).andStatusEqualTo(1).andPayTypeEqualTo(0);
			List<RebateOrder> orders = rebateOrderMapper.selectByExample(example);
			for (int i = 0; i < orders.size(); i++) {
				RebateOrder order = orders.get(i);
				if (order.getPayType() == 0) {
					String orderNo = order.getOrderNo();
					SortedMap<String, String> cxQueryParams = new TreeMap<String, String>();
					cxQueryParams.put("appid", appId);
					cxQueryParams.put("mch_id", partner);
					cxQueryParams.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
					cxQueryParams.put("out_trade_no", orderNo);
					String sign = createSign(cxQueryParams, pkey);
					cxQueryParams.put("sign", sign);
					
					String requestXML = XMLUtil.getRequestXml(cxQueryParams);
					String result = HttpClientUtil.httpsRequest(JSAPI_URL, "POST", requestXML);
					Map<String, String> notifyParamsMap = XMLUtil.doXMLParse(result);
					logger.info("result--------"+notifyParamsMap.toString());
					if ("FAIL".equals(notifyParamsMap.get("return_code"))) {
						
						continue;
					}
					
						if ("SUCCESS".equals(notifyParamsMap.get("return_code"))) {
							if (("SUCCESS").equals(notifyParamsMap.get("result_code"))) {
								if (("SUCCESS").equals(notifyParamsMap.get("trade_state"))) {
								
									// 交易成功
								String tradeNo =notifyParamsMap.get("out_trade_no") ;			
								String weixinOrderNo =notifyParamsMap.get("transaction_id") ;			
								String payTime = notifyParamsMap.get("time_end");				//支付时间
								
								
								if(SystemConstant.PAY_ISPAY.equals(order.getStatus())){
									logger.error(">>>>>>>>>>>>>>>>>>订单"+tradeNo+"己支付");
									continue;
								}else{
									Date now = new Date();
									
									StoreRebate rebate = storeRebateMapper.selectByPrimaryKey(order.getRebateId());
									StoreInfo store = storeInfoMapper.selectByPrimaryKey(rebate.getStoreId());
									
									BigDecimal calTotamt =  order.getTotamt();
									BigDecimal rate = new BigDecimal(0); 
									BigDecimal rateMoney = new BigDecimal(0); 
									//查询本店费率
									Integer type = 1;   // 1WX   2ALi   3QQ
									HashMap<String, BigDecimal>  rateMap = fqStoreRateService.calculateOrderRate(order.getTotamt(), store, type);
									
									if(rateMap!=null&&rateMap.size()>0){
										rate = rateMap.get("smallRate");	 //费率
										rateMoney = rateMap.get("rateMoney");	 //手续费
										calTotamt = rateMap.get("calTotamt");	  //扣除手续费结算的金额 
									}
									
									
									//更新订单  
									RebateOrder uprecord = new RebateOrder();
									uprecord.setId(Long.valueOf(order.getId()));
									uprecord.setStatus(SystemConstant.PAY_ISPAY);
									uprecord.setPayTime(DateUtil.convertStringToDate("yyyyMMddHHmmss", payTime));	//支付时间
									uprecord.setWeixinOrderNo(weixinOrderNo);
									uprecord.setTotamt(calTotamt);
									uprecord.setOrderRate(rate);
									uprecord.setRateFee(rateMoney);
									rebateOrderMapper.updateByPrimaryKeySelective(uprecord);
									
	
									FqThirdPay thirdPay = new  FqThirdPay();
									thirdPay.setMoney(order.getRealPay());
									thirdPay.setOrderId(order.getId());
									thirdPay.setOrderNo(order.getOrderNo());
									thirdPay.setPayTime(uprecord.getPayTime());
									thirdPay.setSort(1);
									thirdPay.setThirdOrderNo(weixinOrderNo);
									thirdPay.setType(1);
									thirdPay.setSellerId(store.getSellerId());
									thirdPay.setTotamt(calTotamt);
									thirdPay.setOrderRate(rate);
									thirdPay.setRateFee(rateMoney);
									FqUserInfoExample fuieExample = new FqUserInfoExample();
									fuieExample.createCriteria().andOpenIdEqualTo(order.getOpenId());
									List<FqUserInfo> users = fqUserInfoMapper.selectByExample(fuieExample);
									if (users.size()>0) {
										thirdPay.setUserId(users.get(0).getId());
									}
									thirdPay.setOpenId(order.getOpenId());
									a+=fqThirdPayMapper.insertSelective(thirdPay);
									
									
									SellerInfo seller = sellerService.getSellerById(store.getSellerId());
									if (seller.getWithdrawStatus() != null && seller.getWithdrawStatus() == 1) {
										Date date0 = BaseUtil.getTimeDate(order.getCreateTime());
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
								}
								}
							}
						}
					
				}
			}
			System.out.println(a);
			return a;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
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

}
