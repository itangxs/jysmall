package cn.qhjys.mall.service.seller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.qhjys.mall.entity.FqMicroOrder;
import cn.qhjys.mall.entity.FqThirdPay;
import cn.qhjys.mall.entity.SellerInfo;

/**
 * 收银台
 * @author Administrator
 *
 */
public interface SellerCashierDeskService {
	/**
	 * 查看支付订单
	 * @param sellerId
	 * @return
	 */
	public List<FqThirdPay> queryOrderList(Long sellerId);
	
	
	public FqThirdPay insertPayOrderByAliPay(Long sellerId,BigDecimal money,String authCode,FqMicroOrder order,HttpServletRequest request,HttpServletResponse response) ;

	public FqThirdPay insertPayOrderByGdPay(Long sellerId,BigDecimal money,String authCode,HttpServletRequest request,HttpServletResponse response);
	
	public FqThirdPay insertPayOrderByQqPay(Long sellerId,BigDecimal money,String authCode,HttpServletRequest request,HttpServletResponse response);


	public FqThirdPay insertPayOrderByPFPay(Long id, BigDecimal money, String authCode, HttpServletRequest request,
			HttpServletResponse response);
	public FqThirdPay insertPayOrderByWXPay(Long id, BigDecimal money, String authCode, HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	public FqThirdPay insertPayOrderByXYPay(Long id, BigDecimal money, String authCode, HttpServletRequest request,
			HttpServletResponse response);
	
	public FqThirdPay insertPayOrderByXYEQPay(Long id, BigDecimal money, String authCode, HttpServletRequest request,
			HttpServletResponse response);

	public FqThirdPay insertPayOrderByMSPay(SellerInfo seller, BigDecimal money, String authCode,
		       HttpServletRequest request, HttpServletResponse response);
	

	public FqThirdPay insertPayOrderBySZPFWXPay(Long id, BigDecimal money, String authCode,FqMicroOrder order, HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
	public Integer updateCheckNotifyWXSZPF(Map<String,String> notifyParamsMap,HttpServletRequest request,HttpServletResponse response)throws Exception ;
	
	public FqMicroOrder insertFqMicroOrder(Long id, BigDecimal money);

	public FqMicroOrder insertFqMicroOrderToAli(Long id, BigDecimal money);

}
