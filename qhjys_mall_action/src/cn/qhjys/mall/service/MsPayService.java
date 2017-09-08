package cn.qhjys.mall.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.JDOMException;

import cn.qhjys.mall.entity.FqOrder;
import cn.qhjys.mall.entity.FqThirdPay;
import cn.qhjys.mall.entity.SellerInfo;

/**
 * @author tangxs
 *
 * 2017年7月14日
 */
public interface MsPayService {

	
	public  Map<String,Object> insertOrderAndGetAlipayMS(String openId, long sellerId,
			String storeName, BigDecimal rebate,long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,String nickname,String headimgurl,HttpServletRequest request,
			HttpServletResponse response)throws Exception;
	
	public Map<String, Object> insertOrderAndGetWXMS(Long couponId,String nickname,String headimgurl, String openId, long sellerId, 
			String storeName,long rebateId, BigDecimal needPay, long storeId,BigDecimal realPay, BigDecimal totamt, 
			BigDecimal noDiscount,BigDecimal rebate, HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	
	public  Map<String,Object> insertOrderAndGetQQMS(String openId, long sellerId,
			String storeName, BigDecimal rebate,long rebateId, BigDecimal needPay,long storeId,
			BigDecimal realPay, BigDecimal totamt, BigDecimal noDiscount,String nickname,String headimgurl,HttpServletRequest request,
			HttpServletResponse response)throws Exception;

	public FqThirdPay insertPayOrderByMSPay(SellerInfo seller, BigDecimal money, String authCode,HttpServletRequest request, HttpServletResponse response)throws Exception; 
	
	public FqThirdPay insertPayOrderByMSPayAPP(SellerInfo seller, BigDecimal money, String authCode,
		       HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	
	public String updateCheckNotifyMSPay(Map<String,String> notifyParamsMap,HttpServletRequest request,HttpServletResponse response)throws Exception ;
	
	public Map<String, String> insertOrderFqStoreByWXMS(FqOrder order,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
}

