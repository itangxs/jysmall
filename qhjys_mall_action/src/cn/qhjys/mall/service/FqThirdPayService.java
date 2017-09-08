package cn.qhjys.mall.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.JDOMException;

import cn.qhjys.mall.entity.FqMicroOrder;
import cn.qhjys.mall.entity.FqThirdPay;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.vo.OrderCountVo;

import com.github.pagehelper.Page;

public interface FqThirdPayService {
	public Page<FqThirdPay> queryFqThirdPays(Long sellerId,Integer year,Integer month,Integer day ,Integer pageNum,Integer pageSize);
	
	public FqThirdPay insertPayOrderByWxPay(Long sellerId,BigDecimal money,String authCode,HttpServletRequest request,HttpServletResponse response);
	
	public FqThirdPay insertPayOrderByAliPay(Long sellerId,BigDecimal money,String authCode,FqMicroOrder order,HttpServletRequest request,HttpServletResponse response) throws ParseException;

	public FqThirdPay insertPayOrderByGdPay(Long sellerId,BigDecimal money,String authCode,HttpServletRequest request,HttpServletResponse response);
	
	public FqThirdPay insertPayOrderByQqPay(Long sellerId,BigDecimal money,String authCode,HttpServletRequest request,HttpServletResponse response);
	
	public FqThirdPay insertPayOrderByPFPay(Long sellerId,BigDecimal money,String authCode,HttpServletRequest request,HttpServletResponse response);
	
	public FqThirdPay insertPayOrderByXYPayAPP(Long sellerId, BigDecimal money, String authCode,
		       HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public FqThirdPay insertPayOrderByXYEQPayAPP(Long sellerId, BigDecimal money, String authCode,
		       HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public int updateMicroOrder() throws Exception ;
	public int reverseMicroOrder(String orderNo) throws JDOMException, IOException;

	public String insertPosOrder(Long sellerId, BigDecimal money);

	public Integer updateCheckNotifyWangPos(Map<String, String> notifyParamsMap) throws Exception;

	public Integer updateCheckNotifyWangPosByApp(String data, Long sellerId) throws Exception;

	public int updateWPosOrder() throws Exception;

	public int getFinancialQuota(Long sellerId);
	
	public int updateCashOrder();
	public FqMicroOrder insertFqMicroOrder(Long id, BigDecimal money);
	public FqMicroOrder insertFqMicroOrderToAli(Long id, BigDecimal money);
	
	public FqThirdPay insertPayOrderBySZPFWXPay(Long id, BigDecimal money, String authCode,FqMicroOrder order, HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
}
