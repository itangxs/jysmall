package cn.qhjys.mall.controller.seller;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.CashAccount;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.service.CashAccountService;
import cn.qhjys.mall.service.SellerService;
import cn.qhjys.mall.service.seller.SellerVoService;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.EncodeMD5;
import cn.qhjys.mall.util.SessionUtil;

/**
 * 用户登录 .找回密码
 * 
 * @author JiangXiaoPing
 *
 */
@Controller
@RequestMapping("/seller")
public class SellerLoginController extends Base {

	@Autowired
	private SellerVoService sellerVoService;

	@Autowired
	private SellerService sellerService;

	@Autowired
	private CashAccountService cashAccountService;

	public SellerVoService getSellerVoService() {
		return sellerVoService;
	}

	public void setSellerVoService(SellerVoService sellerVoService) {
		this.sellerVoService = sellerVoService;
	}
	
	
	@RequestMapping("/activity_step")
	public String activity_step(){
		return "/seller/ad/activity_step";
	}
	
	@RequestMapping("/financial_support")
	public String financial_support(){
		return "/seller/ad/financial_support";
	}

	/**
	 * 
	 * @Title: login 登录页面
	 * @param session
	 * @return
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/login")
	public ModelAndView login(HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView();
		String token = UUID.randomUUID().toString();
		SessionUtil.setSession(session, "seller_login", token);
		view.addObject("token", token);
		view.setViewName("/seller/login");
		return view;
	}

	/**
	 * 
	 * @Title: loginCommit 登录提交
	 * @param session
	 * @param token
	 * @param contactname
	 * @param password
	 * @throws Exception
	 * @return void
	 */
	@RequestMapping("/loginCommit")
	public Object loginCommit(HttpSession session, @RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "contactname", required = true) String contactname,
			@RequestParam(value = "password", required = true) String password) {
		try {
	
		session.setMaxInactiveInterval(24*60*60*1000);
		Object tokenSession = SessionUtil.getSession(session, "seller_login");
		logger.info("----contactname---"+contactname);
		SessionUtil.removeSession(session, "seller_login");
		if (null != tokenSession && tokenSession.equals(token)) {
			SellerInfo si = sellerVoService.updateSellerByAccountPasswordLogin(contactname,
					EncodeMD5.GetMD5Code(password));
			if (null == si) {
				return super.goUrl("/seller/login.do", "没有此账号");
			}
			if (!si.getPassword().equals(EncodeMD5.GetMD5Code(password))) {
				return super.goUrl("/seller/login.do", "账号或者密码错误");
			}

			if (null != si && 1 == si.getEnabled()) {
				SessionUtil.setSession(session, ConstantsConfigurer.getSeller(), si);
				StoreInfo store = null;
				store = sellerVoService.queryStore(si.getId());
				if (null == store.getStatus()) {
					store.setStatus(0);
				}
				CashAccount cashAccount = cashAccountService.queryCashAccount(si.getId(), null);
				SessionUtil.setSession(session, ConstantsConfigurer.getProperty("seller_account_key"), cashAccount);
				SessionUtil.setSession(session, ConstantsConfigurer.getProperty("store_key"), store);
				return "redirect:/managermall/seller/funds/advancelist.do";
			} else if (null != si && 0 == si.getEnabled()) {
				return super.goUrl("/seller/login.do", "账号异常,请联系管理员");
			}
		} else {
			return super.goUrl("/seller/login.do", "请不要重复提交");
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/seller/login.do";

	}

	/**
	 * 
	 * out 退出登入
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/out")
	public Object out(HttpSession session) throws Exception {
		SellerInfo si = (SellerInfo) SessionUtil.getSession(session, ConstantsConfigurer.getSeller());
		if (si != null)
			SessionUtil.removeSession(session, ConstantsConfigurer.getSeller());
			SessionUtil.removeSession(session, "istanchu");
			SessionUtil.removeSession(session, "istanc");
		return "redirect:/seller/login.do";
	}

	/**
	 * 
	 * @Title: toBackPassword 找回密码1
	 * @param session
	 * @return
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/toBackPassword")
	public ModelAndView toBackPassword(HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView();
		String token = UUID.randomUUID().toString();
		SessionUtil.setSession(session, "seller_back_password", token);
		view.addObject("token", token);
		view.setViewName("/seller/back_password");
		return view;
	}

	/**
	 * 
	 * @Title: toBackPassword2 找回密码2
	 * @param session
	 * @param token
	 * @param phone
	 *            手机号码
	 * @return
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/toBackPassword2")
	public Object toBackPassword2(HttpSession session, @RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "phone", required = true) String phone) throws Exception {
		ModelAndView view = new ModelAndView();
		String tk = (String) SessionUtil.getSession(session, "seller_back_password");
		SessionUtil.removeSession(session, "seller_back_password");
		if (!token.equals(tk)) {
			super.goUrl("/seller/toBackPassword.do", "请不要重复提交");
			return null;
		}
		logger.info("username-----"+phone);
		SellerInfo seller = sellerService.getSeller(phone);
		if (null == seller) {
			return super.goUrl("/seller/toBackPassword.do", "对不起,没有此用户");
		}
		logger.info("seller---username-----"+seller.getUsername());
		SessionUtil.setSession(session, "backPasswordPhone", phone);
		String token1 = UUID.randomUUID().toString();
		SessionUtil.setSession(session, "seller_back_password2", token1);
		view.addObject("token", token);
		view.addObject("seller", seller);
		view.setViewName("/seller/back_password2");
		return view;
	}

	/**
	 * 
	 * @Title: verificationCode 那到手机号码
	 * @return void
	 * @throws Exception
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/verificationCode") public void verificationCode(
	 * HttpServletResponse response, HttpSession session ) throws Exception{
	 * String phone = (String) SessionUtil.getSession(session,
	 * "backPasswordPhone");
	 * 
	 * 
	 * if(null==phone){ HtmlUtil.writerJson(response, "phoneNull"); }else{
	 * //发送短信
	 * 
	 * }
	 * 
	 * 
	 * }
	 */

	@RequestMapping("/backPassword")
	public Object verificationCode(HttpServletResponse response, HttpSession session,
			@RequestParam(value = "jym", required = true) String yzm,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "ymyzm", required = true) String ymyzm) throws Exception {
		String phone = (String) SessionUtil.getSession(session, "backPasswordPhone");
		SessionUtil.removeSession(session, "backPasswordPhone");
		String tk = (String) SessionUtil.getSession(session, "seller_back_password2");
		SessionUtil.removeSession(session, "seller_back_password2");
		if (!token.equals(tk)) {
			return super.goUrl("/seller/toBackPassword.do", "请不要重复提交");
		}
		// 获取session 手机号码
		String sessionPhone = (String) SessionUtil.getSession(session, "phone");
		SessionUtil.removeSession(session, "phone");
		if (null == sessionPhone) {
			return super.goUrl("/seller/toBackPassword.do", "服务器检测到您的手机号码异常");
		}
		ModelAndView view = new ModelAndView("/seller/back_password3");
		// 获取session 验证码
		String sessionYzm = (String) SessionUtil.getSession(session, "captcha");
		SessionUtil.removeSession(session, "captcha");
		if (!yzm.equals(sessionYzm)) {
			return super.goUrl("/seller/toBackPassword.do", "手机验证码错误,请重新获取");
		} else {// 修改密码
			SellerInfo seller = sellerService.getSeller(phone);
			if (seller == null) {
				return super.goUrl("/seller/toBackPassword.do", "没有此账号");
			}
			Boolean bl = sellerService.updateSellerPassWordById(seller, EncodeMD5.GetMD5Code(password));
			if (bl) {// 修改成功
				view.addObject("msg", "恭喜你已成功设定密码！");
			} else {
				view.addObject("msg", "重置密码失败");
			}
			session.setAttribute(ConstantsConfigurer.getSeller(), seller);
		}
		return view;
	}
}
