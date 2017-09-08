package cn.qhjys.mall.service.fq;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.JDOMException;

import cn.qhjys.mall.entity.FqOrder;

public interface FqWxPayService {
	Map<String,Object> updateAndPayOrder(FqOrder order,HttpServletRequest request,HttpServletResponse response);
	Map<String,Object> updateAndPayOrderWk(FqOrder order,HttpServletRequest request,HttpServletResponse response);
	Integer updateYudingPay(Map<String,String> notifyParamsMap,HttpServletRequest request ,HttpServletResponse response)throws Exception;
	Integer updateWeiKuanPay(Map<String,String> notifyParamsMap,HttpServletRequest request ,HttpServletResponse response)throws Exception;
	Map<String,Object> updateAndPayOrderByAlipay(FqOrder order);
	Integer updateAlipay(String orderNo,String trandno)throws Exception;
	
	Map<String,String> updateAndPayOrderByWft(FqOrder order,HttpServletRequest request,HttpServletResponse response)throws JDOMException, IOException;
	Map<String,String> updateAndPayOrderByGd(FqOrder order,HttpServletRequest request,HttpServletResponse response)throws JDOMException, IOException;
	Map<String,String> updateAndPayOrderByPf(FqOrder order,HttpServletRequest request,HttpServletResponse response)throws JDOMException, IOException;
	Integer updateWftPay(Map<String,String> notifyParamsMap,HttpServletRequest request ,HttpServletResponse response)throws Exception;
	Integer updateGdPay(Map<String,String> notifyParamsMap,HttpServletRequest request ,HttpServletResponse response)throws Exception;
	Integer updatePfPay(Map<String,String> notifyParamsMap,HttpServletRequest request ,HttpServletResponse response)throws Exception;
	Integer updateQPay(Map<String, String> notifyParamsMap, HttpServletRequest request, HttpServletResponse response);
}
