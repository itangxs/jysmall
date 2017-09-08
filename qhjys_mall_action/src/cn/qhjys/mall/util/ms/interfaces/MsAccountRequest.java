package cn.qhjys.mall.util.ms.interfaces;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.qhjys.mall.util.ms.BaseRequest;
import cn.qhjys.mall.util.ms.MsConstant;
import cn.qhjys.mall.util.ms.RequestNo;

/**
 * 
 * 标题：民生账户类接口请求
 * 作者：huangjr
 * 描述：TODO
 * 版本：V1.0 
 * 创建时间：2017年7月17日 下午12:40:35
 * 修改时间：
 *
 */
public class MsAccountRequest extends BaseRequest{

	public final static Logger logger = LoggerFactory.getLogger(MsAccountRequest.class);
	
	/**
	 * 
	 * 描述: 查询商户余额
	 * @param merNo 商户号
	 * @return Map 包含：总余额、已入账金额、未入账金额
	 */
	public static Map<String,Object> getBalanceInfo(String merNo) {
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			//查询商户余额
			List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
	        nvps.add(new BasicNameValuePair("requestNo", RequestNo.request_no));
	        nvps.add(new BasicNameValuePair("version", "V2.0"));
	        nvps.add(new BasicNameValuePair("transId", "09"));//余额查询
	        nvps.add(new BasicNameValuePair("merNo", merNo));
	        Map<String, String> itemMap = getSignToSend(nvps);
	        
	        BigDecimal sumMoney = new BigDecimal(0.00);		//总余额
	        BigDecimal allFeeMoney = new BigDecimal(0.00);	//已入账金额
	        BigDecimal notFeeMoney = new BigDecimal(0.00);	//未入账金额
	        
	        if (null != itemMap) {
	        	String respCode = itemMap.get("respCode");//响应码
	        	String respDesc = itemMap.get("respDesc");//响应信息
	        	if (MsConstant.SUCCEE.equals(respCode)) {
	        		sumMoney = new BigDecimal(itemMap.get("avaBal")).divide(new BigDecimal(100));
	        		allFeeMoney = new BigDecimal(itemMap.get("cwcBal")).divide(new BigDecimal(100));
	        		notFeeMoney = sumMoney.subtract(allFeeMoney);
	        	} else {
	        		logger.info("--商户余额查询接口返回状态失败--");
	        		logger.info("响应码："+respCode);
	        		logger.info("响应描述："+respDesc);
	        	}
	        } else {
	        	logger.error("--商户余额查询接口请求失败--");
	        }
	        map.put("sumMoney", sumMoney);
	        map.put("allFeeMoney", allFeeMoney);
	        map.put("notFeeMoney", notFeeMoney);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("--商户余额查询请求出现异常--",e);
		}
		return map;
	}
}
