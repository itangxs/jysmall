package cn.qhjys.mall.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.FqStoreRate;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.mapper.FqStoreRateMapper;
import cn.qhjys.mall.service.FqStoreRateService;

@Service
public class FqStoreRateServiceImpl implements FqStoreRateService {
	
	@Autowired
	private FqStoreRateMapper fqStoreRateMapper;

	
	/**
	 * 
	 * @param totamt
	 * @param store
	 * @param type    1WX   2ALi   3QQ
	 */
	public HashMap<String,BigDecimal> calculateOrderRate(BigDecimal totamt,StoreInfo store,Integer type){
		
		HashMap<String,BigDecimal> ret = new HashMap<>();
		//查询本店费率
		if(store.getRateId()!=null ){
			FqStoreRate  fqStoreRate = fqStoreRateMapper.selectByPrimaryKey(store.getRateId());
			if(fqStoreRate!=null){
				if(type==1){
					 BigDecimal baseRate   = fqStoreRate.getWechatBaseRate();
					 BigDecimal appendMoney = fqStoreRate.getWechatAppendMoney();
					 BigDecimal appendRate = fqStoreRate.getWechatAppendRate();
					 BigDecimal smallRate = new BigDecimal(0);
					 BigDecimal rateMoney = new BigDecimal(0);
					 if(totamt.doubleValue()>appendMoney.doubleValue()){ //订单金额  大于  设定的金额  使用附加费率
						    //0.23  %    转成0.0023
						     smallRate =  appendRate.divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_UP);    
						     rateMoney = totamt.multiply(smallRate);
						     //保留两位小数
						     rateMoney = rateMoney.setScale(2,   BigDecimal.ROUND_HALF_UP);
					 }else{ //使用基础费率
						     smallRate =  baseRate.divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_UP);    
						     rateMoney = totamt.multiply(smallRate);
						    //保留两位小数
						    rateMoney = rateMoney.setScale(2,   BigDecimal.ROUND_HALF_UP);
					 }
					ret.put("smallRate", smallRate.multiply(new BigDecimal(100))); //费率    转成百分比小数
					ret.put("rateMoney", rateMoney); //手续费
					ret.put("calTotamt",  totamt.subtract(rateMoney)); //扣除手续费结算的金额 
				}else if(type==2){
					 BigDecimal baseRate   = fqStoreRate.getAlipayBaseRate();
					 BigDecimal appendMoney = fqStoreRate.getAlipayAppendMoney();
					 BigDecimal appendRate = fqStoreRate.getAlipayAppendRate();
					 BigDecimal smallRate = new BigDecimal(0);
					 BigDecimal rateMoney = new BigDecimal(0);
					 if(totamt.doubleValue()>appendMoney.doubleValue()){ //订单金额  大于  设定的金额  使用附加费率
						    //0.23  %    转成0.0023
						     smallRate =  appendRate.divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_UP);    
						     rateMoney = totamt.multiply(smallRate);
						    //保留两位小数
						    rateMoney = rateMoney.setScale(2,   BigDecimal.ROUND_HALF_UP);
					 }else{ //使用基础费率
						     smallRate =  baseRate.divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_UP);    
						     rateMoney = totamt.multiply(smallRate);
						    //保留两位小数
						    rateMoney = rateMoney.setScale(2,   BigDecimal.ROUND_HALF_UP);
					 }
					ret.put("smallRate", smallRate.multiply(new BigDecimal(100))); //费率    转成百分比小数
					ret.put("rateMoney", rateMoney); //手续费
					ret.put("calTotamt",  totamt.subtract(rateMoney)); //扣除手续费结算的金额 
				}else if(type==3){
					 BigDecimal baseRate   = fqStoreRate.getQqpayBaseRate();
					 BigDecimal appendMoney = fqStoreRate.getQqpayAppendMoney();
					 BigDecimal appendRate = fqStoreRate.getQqpayAppendRate();
					 BigDecimal smallRate = new BigDecimal(0);
					 BigDecimal rateMoney = new BigDecimal(0);
					 if(totamt.doubleValue()>appendMoney.doubleValue()){ //订单金额  大于  设定的金额  使用附加费率
						    //0.23  %    转成0.0023
						     smallRate =  appendRate.divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_UP);    
						     rateMoney = totamt.multiply(smallRate);
						    //保留两位小数
						    rateMoney = rateMoney.setScale(2,   BigDecimal.ROUND_HALF_UP);
					 }else{ //使用基础费率
						     smallRate =  baseRate.divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_UP);    
						     rateMoney = totamt.multiply(smallRate);
						    //保留两位小数
						    rateMoney = rateMoney.setScale(2,   BigDecimal.ROUND_HALF_UP);
					 }
					 ret.put("smallRate", smallRate.multiply(new BigDecimal(100))); //费率    转成百分比小数
					ret.put("rateMoney", rateMoney); //手续费
					ret.put("calTotamt",  totamt.subtract(rateMoney)); //扣除手续费结算的金额 
				}
			}
			return ret;
		}else{
			return null;
		}
		
	}
	
	
}
