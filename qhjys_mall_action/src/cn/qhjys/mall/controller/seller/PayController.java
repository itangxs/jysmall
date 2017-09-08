package cn.qhjys.mall.controller.seller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.CashSaveWithdraw;
import cn.qhjys.mall.service.PayService;
import cn.qhjys.mall.service.seller.SellerVoService;
import cn.qhjys.mall.util.LLPayUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/***
 * 支付回调
 * 
 * @author zengrong
 *
 */
@Controller
@RequestMapping("/pay")
public class PayController extends Base {

	@Autowired
	private SellerVoService sellerVoService;
	@Autowired
	private PayService payService;

	/***
	 * 调用连连支付回调函数(商家充值)
	 * 
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("/seller/rechargeIsSucess")
	public synchronized void sellerRechargeIsSucess(HttpServletResponse response, HttpServletRequest request, String cardNo)
			throws Exception {
		String reqStr = LLPayUtil.readReqStr(request);
		JSONObject json = JSON.parseObject(reqStr);
		CashSaveWithdraw cashSaveWithdraw = sellerVoService.queryCashSaveWithdrawByOrderNum(json.get("no_order")
				.toString());
		CashSaveWithdraw cash = new CashSaveWithdraw();
		cash.setOrderNum(json.get("no_order").toString());
		String result_pay = String.valueOf(json.get("result_pay"));
		if (result_pay.toUpperCase().trim().equals("SUCCESS") && StringUtils.isNotEmpty(cash.getOrderNum())
				&& cashSaveWithdraw.getStatus().equals(2)
				&& json.get("money_order").toString().equals(cashSaveWithdraw.getMoney().toString())) {

			response.getWriter().write(payService.saveSellerPayLL(reqStr, cardNo, cash.getOrderNum(), cashSaveWithdraw));
			response.getWriter().flush();

			// return view;
		}

	}

	/***
	 * 调用连连支付回调函数(用户充值)
	 * 
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("/user/rechargeIsSucess")
	public synchronized void rechargeIsSucess(HttpServletResponse response, HttpServletRequest request, String cardNo)
			throws Exception {
		String reqStr = LLPayUtil.readReqStr(request);
		JSONObject json = JSON.parseObject(reqStr);
		CashSaveWithdraw cashSaveWithdraw = sellerVoService.queryCashSaveWithdrawByOrderNum(json.get("no_order")
				.toString());
		CashSaveWithdraw cash = new CashSaveWithdraw();
		cash.setOrderNum(json.get("no_order").toString());
		String result_pay = String.valueOf(json.get("result_pay"));
		if (result_pay.toUpperCase().trim().equals("SUCCESS") && StringUtils.isNotEmpty(cash.getOrderNum())
				&& cashSaveWithdraw.getStatus().equals(2)
				&& json.get("money_order").toString().equals(cashSaveWithdraw.getMoney().toString())) {

			response.getWriter().write(payService.saveUserPayLL(reqStr, cash.getOrderNum(), cardNo, cashSaveWithdraw));
			response.getWriter().flush();

			// view.addObject("retBean",retBean);
			// return view;
		}

	}

}
