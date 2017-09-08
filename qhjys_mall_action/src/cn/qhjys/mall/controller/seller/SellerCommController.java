package cn.qhjys.mall.controller.seller;

import java.util.Date;
import java.util.UUID;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.service.SellerService;
import cn.qhjys.mall.util.HtmlUtil;

@Controller
@RequestMapping("/seller")
public class SellerCommController extends Base {
	@Autowired
	private SellerService sellerService;

	/**
	 * 跳转商家帐号注册
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/regsiter")
	public ModelAndView regsiter(HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView();
		String token = UUID.randomUUID().toString();
		session.setAttribute("seller_regsiter", token);
		view.addObject("token", token);
		view.setViewName("/seller/regsiter");
		return view;
	}

	/**
	 * 注册商家帐号
	 * 
	 * @param seller
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/addSeller")
	public void addSeller(HttpSession session, HttpServletResponse response, SellerInfo seller, String captcha,
			String token) throws Exception {
		Object tokenSession = session.getAttribute("seller_regsiter");
		if (tokenSession == null || !tokenSession.equals(token)) {
			HtmlUtil.writerJson(response, "timeout");
			return;
		}
//		String phone = (String) session.getAttribute("phone");
//		String _captcha = (String) session.getAttribute("captcha");
//		if (_captcha == null || !_captcha.equals(captcha)) {
//			HtmlUtil.writerJson(response, "codeError");
//			return;
//		}
//		seller.setPhone(phone);
		logger.info("seller.getUsername()-----"+seller.getUsername());
		SellerInfo sellerinfo = sellerService.getSeller(seller.getUsername());
		if (sellerinfo != null) {
			logger.info("sellerinfo-----"+sellerinfo.getUsername());
			HtmlUtil.writerJson(response, "error");
			return;
		}
		session.removeAttribute("phone");
		session.removeAttribute("captcha");
		session.removeAttribute("seller_regsiter");
		seller.setRegisTime(new Date());
		boolean result = sellerService.addSellerInfo(seller);
		if (result) {
			SellerInfo sellerInfo = sellerService.getSeller(seller.getUsername());
			sellerInfo.setInvite(200000+sellerInfo.getId().intValue()+78);
			sellerService.updateSellerById(sellerInfo);
		}
		HtmlUtil.writerJson(response, result);
	}
	@RequestMapping("/addSellerHtml")
	public void addSellerHtml(HttpSession session, HttpServletResponse response, SellerInfo seller, String captcha
			) throws Exception {
		
		System.out.println(11111111);
//		Object tokenSession = session.getAttribute("seller_regsiter");
//		if (tokenSession == null || !tokenSession.equals(token)) {
//			HtmlUtil.writerJson(response, "timeout");
//			return;
//		}
		String phone = (String) session.getAttribute("phone");
		String _captcha = (String) session.getAttribute("captcha");
		if (_captcha == null || !_captcha.equals(captcha)) {
			HtmlUtil.writerJson(response, "codeError");
			return;
		}
		seller.setPhone(phone);
		logger.info("seller.getUsername()-----"+seller.getUsername());
		SellerInfo sellerinfo = sellerService.getSeller(seller.getUsername());
		if (sellerinfo != null) {
			logger.info("sellerinfo-----"+sellerinfo.getUsername());
			HtmlUtil.writerJson(response, "error");
			return;
		}
		session.removeAttribute("phone");
		session.removeAttribute("captcha");
//		session.removeAttribute("seller_regsiter");
		seller.setRegisTime(new Date());
		boolean result = sellerService.addSellerInfo(seller);
		if (result) {
			SellerInfo sellerInfo = sellerService.getSeller(seller.getUsername());
			sellerInfo.setInvite(200000+sellerInfo.getId().intValue()+78);
			sellerService.updateSellerById(sellerInfo);
		}
		HtmlUtil.writerJson(response, result);
	}
}