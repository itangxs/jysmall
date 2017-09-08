package cn.qhjys.mall.service;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.qhjys.mall.entity.RebateOrder;
import cn.qhjys.mall.entity.StoreRebate;

/**
 * 微信支付 业务接口
* @Description: 
* @author llw 
* @date 2015年5月14日 下午8:20:31
 */
public interface WxPayService {
	
	/**
	 * * 获取微信支付参数
	 * @param openid
	 * @param sellerid
	 * @param sotreName
	 * @param rebateId
	 * @param needPay
	 * @param storeId
	 * @param realPay
	 * @param totamt
	 * @param noDiscount
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	 
	public Map<String,Object> insertOrderAndGetWxPars(Long couponId,String nickname,String headimgurl,String openId, long sellerId,
			String storeName, long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,BigDecimal rebate,HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	
	public Map<String,Object> insertOrderAndGetWft(String nickname,String headimgurl,String openId, long sellerId,
			String storeName, long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,BigDecimal rebate,HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	public Map<String,Object> insertOrderAndGetGd(String nickname,String headimgurl,String openId, long sellerId,
			String storeName, long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,BigDecimal rebate,HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	public Map<String,Object> insertOrderAndGetWXPF(Long couponId, String nickname,String headimgurl,String openId, long sellerId,
			String storeName, long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,BigDecimal rebate,HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	public Map<String,Object> insertOrderAndGetWXXY(Long couponId, String nickname,String headimgurl,String openId, long sellerId,
			String storeName, long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,BigDecimal rebate,HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	public Map<String,Object> insertOrderAndGetSZPFWX(Long couponId, String nickname,String headimgurl,String openId, long sellerId,
			String storeName, long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,BigDecimal rebate,HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	public Map<String,Object> insertOrderAndGetXYEQWX(Long couponId, String nickname,String headimgurl,String openId, long sellerId,
			String storeName, long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,BigDecimal rebate,HttpServletRequest request,HttpServletResponse response) throws Exception;

	public Integer updateCheckNotifyWXPF(Map<String,String> notifyParamsMap,HttpServletRequest request,HttpServletResponse response)throws Exception ;
	
	public Integer updateCheckNotifyWXXY(Map<String,String> notifyParamsMap,HttpServletRequest request,HttpServletResponse response)throws Exception ;
	
	/**
	 * 微信支付回调通知
	 * @return
	 * @throws Exception
	 */
	public String updateCheckNotify(Map<String,String> notifyParamsMap,HttpServletRequest request,HttpServletResponse response)throws Exception ;
	
	public Integer updateCheckNotifyWft(Map<String,String> notifyParamsMap,HttpServletRequest request,HttpServletResponse response)throws Exception ;
	public Integer updateCheckNotifyGd(Map<String,String> notifyParamsMap,HttpServletRequest request,HttpServletResponse response)throws Exception ;
	
	public  Map<String,Object> insertOrderAndGetAlipay(String openId, long sellerId,
			String storeName, BigDecimal rebate,long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,String nickname,String headimgurl)throws Exception;
	
	
	
	public  String updateCheckNotifyAlipay(Map<String,String> map)throws Exception;

	public  Map<String,Object> insertOrderAndGetAlipayPF(String openId, long sellerId,
			String storeName, BigDecimal rebate,long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,String nickname,String headimgurl,HttpServletRequest request,
			HttpServletResponse response)throws Exception;
	public  Map<String,Object> insertOrderAndGetAlipayXY(String openId, long sellerId,
			String storeName, BigDecimal rebate,long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,String nickname,String headimgurl,HttpServletRequest request,
			HttpServletResponse response)throws Exception;
	public  Map<String,Object> insertOrderAndGetXYEQAlipay(String openId, long sellerId,
			String storeName, BigDecimal rebate,long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,String nickname,String headimgurl,HttpServletRequest request,
			HttpServletResponse response)throws Exception;
	public  Map<String,Object> insertOrderAndGetAlipayGD(String openId, long sellerId,
			String storeName, BigDecimal rebate,long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,String nickname,String headimgurl,HttpServletRequest request,
			HttpServletResponse response)throws Exception;
	public  Map<String,Object> insertOrderAndGetQQPF(String openId, long sellerId,
			String storeName, BigDecimal rebate,long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,String nickname,String headimgurl,HttpServletRequest request,
			HttpServletResponse response)throws Exception;
	
	public  Integer updateCheckNotifyAlipayPF(Map<String,String> notifyParamsMap)throws Exception;
	public  Integer updateCheckNotifyAlipayGD(Map<String,String> notifyParamsMap)throws Exception;

	public  Integer updateCheckNotifyAlipayXY(Map<String,String> notifyParamsMap)throws Exception;
	public  Integer updateCheckNotifyXYEQAlipay(Map<String,String> notifyParamsMap)throws Exception;
	
	/**
	 * 发起qq钱包支付
	 * @param response 
	 * @param request 
	 * @param rebate 
	 * @param noDiscount 
	 * @param totamt 
	 * @param realPay2 
	 * @param storeId 
	 * @param realPay 
	 * @param rebateId 
	 * @param storeName 
	 * @param sellerId 
	 * @param openId 
	 * @param headimgurl 
	 * @param string 
	 * @return
	 * @throws Exception 
	 */
	public Map<String, String> insertOrderAndGetCQPay(String string, String headimgurl, String openId, long sellerId, String storeName, long rebateId, BigDecimal realPay, long storeId, BigDecimal realPay2, BigDecimal totamt, BigDecimal noDiscount, BigDecimal rebate, HttpServletRequest request, HttpServletResponse response) throws Exception;

	/**
	 * qq支付后回调通知
	 * @param notifyParamsMap
	 * @return
	 */
	public boolean updateCheckNotifyCQQ(Map<String, String> notifyParamsMap);
	
	public Integer updateCheckNotifyWXSZPF(Map<String,String> notifyParamsMap,HttpServletRequest request,HttpServletResponse response)throws Exception ;
	
	public Integer updateCheckNotifyXYEQWX(Map<String,String> notifyParamsMap,HttpServletRequest request,HttpServletResponse response)throws Exception ;
}
