package cn.qhjys.mall.controller.seller;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.entity.FqMicroOrder;
import cn.qhjys.mall.entity.FqThirdPay;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.service.MsPayService;
import cn.qhjys.mall.service.StoreService;
import cn.qhjys.mall.service.seller.SellerCashierDeskService;
import cn.qhjys.mall.util.AppResult;
import cn.qhjys.mall.util.ConstantsConfigurer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


/**
 * 收银台
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/managermall/seller")
public class SellerCashierDeskController {
	
	protected final Logger logger = LoggerFactory.getLogger(SellerCashierDeskController.class);
	
	@Autowired
	private SellerCashierDeskService sellerCashierDeskService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private MsPayService msPayService;
	
	/**
	 * 查看最新支付订单
	 * @param sellerId
	 * @return
	 */
	@RequestMapping("/orderlist")
	public ModelAndView orderList(HttpSession session,Long sellerId){
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		ModelAndView view = new ModelAndView("/seller/seller_header");
		List<FqThirdPay> orderList = sellerCashierDeskService.queryOrderList(seller.getId());

		view.addObject("orderList", orderList);
		return view;
	}
	
	/**
	 * 收款总额
	 * @param money
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/paymoney")
	@ResponseBody
	public AppResult payMoney(HttpServletResponse response, HttpServletRequest request,HttpSession session,BigDecimal money,String authCode) throws Exception{
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>进入收银台/paymoney start, money:"+money+",authCode:"+authCode);
		AppResult result = new AppResult();
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if(seller==null){
			result.setFlag(1);
			result.setReason("商户登录信息异常");
			return result;
		}
		
		StoreInfo sinfo = storeService.queryStoreInfoBySeller(seller.getId());
		if(sinfo==null){
			result.setFlag(1);
			result.setReason("门店信息异常");
			return result;
		}
		
		if (StringUtils.isEmpty(authCode)||authCode.length() != 18) {
			result.setFlag(1);
			result.setReason("付款码错误");
			return result;
		}
		if (money == null || money.compareTo(BigDecimal.ZERO)<1) {
			result.setFlag(1);
			result.setReason("支付金额错误");
			return result;
		}
		
		FqThirdPay thirdPay = null;
		Integer authCodeBegin = Integer.valueOf(authCode.substring(0, 2));
		

		 if ((authCodeBegin.intValue() > 9) && (authCodeBegin.intValue() < 16))
		    {
		      if (sinfo.getChannelValidation().intValue() == 1)
		      {
		        thirdPay = this.msPayService.insertPayOrderByMSPay(seller, money, authCode, request, response);
		      }
		      else if (sinfo.getChannelValidation().intValue() == 2)
		      {
		        thirdPay = this.sellerCashierDeskService.insertPayOrderByXYPay(seller.getId(), money, authCode, request, response);
		      }
		      else if (sinfo.getChannelValidation().intValue() == 3)
		      {
			        thirdPay = this.sellerCashierDeskService.insertPayOrderByXYEQPay(seller.getId(), money, authCode, request, response);
		      }
		      else {
		        thirdPay = this.sellerCashierDeskService.insertPayOrderByWXPay(seller.getId(), money, authCode, request, response);
		      }


		    }
		    else if (authCodeBegin.intValue() == 28)
		    {
		      if (sinfo.getChannelValidation().intValue() == 1) 
		      {
		        thirdPay = this.msPayService.insertPayOrderByMSPay(seller, money, authCode, request, response);
		      } 
		      else if (sinfo.getChannelValidation().intValue() == 2) 
		      {
		        thirdPay = this.sellerCashierDeskService.insertPayOrderByXYPay(seller.getId(), money, authCode, request, response);
		      } 
		      else if (sinfo.getChannelValidation().intValue() == 3)
		      {
			        thirdPay = this.sellerCashierDeskService.insertPayOrderByXYEQPay(seller.getId(), money, authCode, request, response);
		      }
		      else 
		      {
//		        FqMicroOrder order = this.sellerCashierDeskService.insertFqMicroOrderToAli(seller.getId(), money);
//		        thirdPay = this.sellerCashierDeskService.insertPayOrderByAliPay(seller.getId(), money, authCode, order, request, response);
		    	  thirdPay = this.sellerCashierDeskService.insertPayOrderByXYEQPay(seller.getId(), money, authCode, request, response);
		      }
		    }
		    else if (authCodeBegin.intValue() == 91) {
		      thirdPay = this.sellerCashierDeskService.insertPayOrderByQqPay(seller.getId(), money, authCode, request, response);
		    } else {
		      result.setFlag(1);
		      result.setReason("付款码错误");
		      return result;
		    }
		if (thirdPay==null) {
			result.setFlag(1);
			result.setReason("支付失败");
		}else{
			if (thirdPay.getType() == -1) {
				result.setFlag(1);
				result.setReason("等待用户输入密码,支付以用户支付结果及订单通知为准");
			} else {
				result.setFlag(0);
				result.setReason("支付成功");
				result.setData((JSONObject) JSON.toJSON(thirdPay));
			}
		}
		return result;
	}
	

//	/**
//	 * 民生收银台收款
//	 * @param money
//	 * @return
//	 * @throws Exception 
//	 */
//	@RequestMapping("/msPaymoney")
//	@ResponseBody
//	public AppResult msPaymoney(HttpServletResponse response, HttpServletRequest request,HttpSession session,BigDecimal money,String authCode) throws Exception{
//		AppResult result = new AppResult();
//		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
//		if(seller==null){
//			result.setFlag(1);
//			result.setReason("商户登录信息异常");
//			return result;
//		}
//		if (StringUtils.isEmpty(authCode)||authCode.length() != 18) {
//			result.setFlag(1);
//			result.setReason("付款码错误");
//			return result;
//		}
//		if (money == null || money.compareTo(BigDecimal.ZERO)<1) {
//			result.setFlag(1);
//			result.setReason("支付金额错误");
//			return result;
//		}
//		
//		FqThirdPay thirdPay = null;
//		Integer authCodeBegin = Integer.valueOf(authCode.substring(0, 2));
//		
//		if ((authCodeBegin>9 && authCodeBegin < 16) || authCodeBegin == 28 || authCodeBegin == 91 ) { 
//			//广州民生银行的微信、支付宝、QQ合为一个
////			thirdPay = sellerCashierDeskService.insertPayOrderByMSPay(seller,money, authCode, request, response);
//		}else{
//			result.setFlag(1);
//			result.setReason("付款码错误");
//			return result;
//		}
//		
//		if (thirdPay==null) {
//			result.setFlag(1);
//			result.setReason("支付失败");
//		}else{
//			if (thirdPay.getType() == -1) {
//				result.setFlag(1);
//				result.setReason("等待用户输入密码,支付以用户支付结果及订单通知为准");
//			} else {
//				result.setFlag(0);
//				result.setReason("支付成功");
//				result.setData((JSONObject) JSON.toJSON(money));
//			}
//		}
//		return result;
//	}

	/**
	 * 民生收银台收款
	 * @param money
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/msPaymoney")
	@ResponseBody
	public AppResult msPaymoney(HttpServletResponse response, HttpServletRequest request,HttpSession session,BigDecimal money,String authCode) throws Exception{
		AppResult result = new AppResult();
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if(seller==null){
			result.setFlag(1);
			result.setReason("商户登录信息异常");
			return result;
		}
		if (StringUtils.isEmpty(authCode)||authCode.length() != 18) {
			result.setFlag(1);
			result.setReason("付款码错误");
			return result;
		}
		if (money == null || money.compareTo(BigDecimal.ZERO)<1) {
			result.setFlag(1);
			result.setReason("支付金额错误");
			return result;
		}
		
		FqThirdPay thirdPay = null;
		Integer authCodeBegin = Integer.valueOf(authCode.substring(0, 2));
		
		if ((authCodeBegin>9 && authCodeBegin < 16) || authCodeBegin == 28 || authCodeBegin == 91 ) { 
			//广州民生银行的微信、支付宝、QQ合为一个
			thirdPay = sellerCashierDeskService.insertPayOrderByMSPay(seller,money, authCode, request, response);
		}else{
			result.setFlag(1);
			result.setReason("付款码错误");
			return result;
		}
		
		if (thirdPay==null) {
			result.setFlag(1);
			result.setReason("支付失败");
		}else{
			if (thirdPay.getType() == -1) {
				result.setFlag(1);
				result.setReason("等待用户输入密码,支付以用户支付结果及订单通知为准");
			} else {
				result.setFlag(0);
				result.setReason("支付成功");
				/*HashMap map = new HashMap();
				map.put("money", money);*/
				result.setData((JSONObject) JSON.toJSON(thirdPay));
			}
		}
		return result;
	}

	
	@RequestMapping("/desk/desk_detail")
	public String desk_detail(){
		return "seller/ad/desk_detail";
	}
	
	@RequestMapping("/checkNotifyWXMCSZPF")
	public void checkNotifyWXMCSZPF(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String param = request.getQueryString();
		String url = request.getRequestURI()+(StringUtils.isEmpty(param)?"":"?"+param);
		logger.info("notify url----"+url);
		//解析微信回调信息
		Map<String, String> notifyParamsMap = new TreeMap<String, String>();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                System.out.println(en + "=" + value);
                notifyParamsMap.put(en, value);
            }
        }
		
		if(notifyParamsMap != null){
			Integer a = sellerCashierDeskService.updateCheckNotifyWXSZPF(notifyParamsMap,request,response);
			if (a == 1) {
				response.getWriter().print("SUCCESS");
			}else{
				response.getWriter().print("fail");
			}
		}
	}
}
