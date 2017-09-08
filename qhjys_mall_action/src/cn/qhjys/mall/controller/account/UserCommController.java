package cn.qhjys.mall.controller.account;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.CashAccount;
import cn.qhjys.mall.entity.CouponInfo;
import cn.qhjys.mall.entity.RegisterActivity;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.UserInfo;
import cn.qhjys.mall.service.CashAccountService;
import cn.qhjys.mall.service.CouponInfoService;
import cn.qhjys.mall.service.RegisterActivityService;
import cn.qhjys.mall.service.SellerService;
import cn.qhjys.mall.service.TaskInfoService;
import cn.qhjys.mall.service.UserInfoService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.EmailSendUtil;
import cn.qhjys.mall.util.EncodeMD5;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.util.SessionUtil;

import com.alibaba.fastjson.JSONObject;

/**
 * 用户信息
 * 
 * @author jxp
 *
 */
@Controller
@RequestMapping("/account")
public class UserCommController extends Base {

	@Autowired
	private UserInfoService userService;
	@Autowired
	private CashAccountService cashAccountService;
	@Autowired
	private RegisterActivityService registerActivityService;
	@Autowired
	private CouponInfoService couponInfoService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private TaskInfoService taskInfoService;
	@Autowired
	private SellerService sellerService;
	

	/**
	 * 进入会员登录页面
	 * 
	 * @return
	 */
	@RequestMapping("/login")
	public String Login() {
		return "account/login";
	}

	/***
	 * 会员登录
	 * 
	 * @param username
	 *            用户信息(用户名、手机号、邮箱)
	 * @param password
	 *            密码
	 * @param session
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/loginUser", method = RequestMethod.POST)
	public void userLogin(HttpSession session, HttpServletResponse response, String username, String password)
			throws Exception {
		JSONObject json = new JSONObject();
		UserInfo user = userService.selecUserByStr(username);
		if (user == null || !user.getPassword().equals(password)) {
			json.put("message", "用户名或密码错误");
		} else {
			CashAccount cashAccount = cashAccountService.queryCashAccount(null, user.getId());
			boolean judge = userInfoService.judgeSignIn(user.getId());
			session.setAttribute(ConstantsConfigurer.getProperty("user_account_key"), cashAccount);
			session.setAttribute(ConstantsConfigurer.getUser(), user);
			session.setAttribute("judgeSignIn", judge);
			json.put("message", "登录成功");
			String openId = (String) session.getAttribute("openID");
			if (!StringUtils.isEmpty(openId)) {
				user.setQqOpenId(openId);
				userInfoService.updateUserById(user);
//            	boolean a = taskInfoService.insertNewUserTask(user.getId(), NewTaskCode.N005);
//            	if (a) {
//            		session.setAttribute(ConstantsConfigurer.getProperty("user_account_key"),cashAccountService.queryCashAccount(null, user.getId()));
//				}
			}
		}
		HtmlUtil.writerJson(response, json);
	}

	/**
	 * 进入会员信息注册页面(邮箱注册)
	 * 
	 * @return
	 */
	@RequestMapping("/registrationByEmail")
	public String doRegistrationByEmail() {
		return "account/registration_email";
	}

	/**
	 * 进入会员信息注册页面(手机号码注册)
	 * 
	 * @return
	 */
	@RequestMapping("/registrationByTel")
	public String doRegistrationByTel() {
		return "account/registration_telephone";
	}

	/***
	 * 注册会员信息
	 * 
	 * @param user
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertUser", method = RequestMethod.POST)
	public void insertUser(String validateCode, UserInfo user, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String _captcha = (String) request.getSession().getAttribute("captcha");
		if (StringUtils.isNotEmpty(validateCode)) {
			if (_captcha == null || !_captcha.equals(validateCode)) {
				HtmlUtil.writerJson(response, "codeError");
				return;
			}
		}
		request.getSession().removeAttribute("captcha");
		String nickname = "JYS_" + BaseUtil.numRandom(5);
		user.setNickname(nickname);
		user.setDefaultCity(200L);
		user.setRegistTime(new Date());
		user.setReqistSource("商城");
		String openId = (String) request.getSession().getAttribute("openID");
		if (!StringUtils.isEmpty(openId)) {
			user.setQqOpenId(openId);
		}
		if (user.getInviteCode() != null) {
			UserInfo userInfo = userInfoService.getUserByInvite(user.getInviteCode());
			SellerInfo seller = sellerService.getSellerInfoByInvite(user.getInviteCode());
			if (userInfo == null && seller == null) {
				user.setInviteCode(null);
				user.setNotice(1);
			}
		}
		boolean result = userService.insertUser(user);
		if (result) {
			UserInfo userinfo = userService.selecUserByStr(user.getPhoneNum() == null ? user.getEmail() : user.getPhoneNum());
			System.out.println(100000+userinfo.getId().intValue()+60);
			userinfo.setInvite(100000+userinfo.getId().intValue()+60);
			userinfo.setNoticeTime(new Date(System.currentTimeMillis()+24*60*60*1000L));
			System.out.println(userinfo.getInvite());
			userService.updateUserById(userinfo);
			RegisterActivity registerActivity = registerActivityService.getRegisterActivityByDate(null);
			if (userinfo.getInviteCode() != null) {
				if (userinfo.getInviteCode() <200000) {
					userInfoService.addInviteTomat(userinfo.getInviteCode(), 40);
				}else{
					sellerService.addInviteTomat(userinfo.getInviteCode(), 40);
				}
			}
			if (registerActivity != null) {
				CouponInfo ci = new CouponInfo();
				ci.setAmount(new BigDecimal(registerActivity.getCommonCoupon()));
				ci.setConsume(0);
				ci.setCouponId(BaseUtil.couponRandom());
				ci.setExpireTime(new Date(System.currentTimeMillis() + registerActivity.getCouponValidity() * 24 * 60
						* 60 * 1000L));
				ci.setName("通用元宝抵用卷: " + registerActivity.getCommonCoupon());
				ci.setStoreId(null);
				ci.setUserId(userinfo.getId());
				couponInfoService.insertCouponInfo(ci);
			}
			if(!StringUtils.isEmpty(userinfo.getQqOpenId())){
				userInfoService.updateUserById(userinfo);
//	        	boolean a =taskInfoService.insertNewUserTask(user.getId(), NewTaskCode.N005);
//	        	if (a) {
//	        		request.getSession().setAttribute(ConstantsConfigurer.getProperty("user_account_key"),cashAccountService.queryCashAccount(null, user.getId()));
//				}
			}
//			if (!StringUtils.isEmpty(userinfo.getPhoneNum())) {
//				boolean a =taskInfoService.insertNewUserTask(userinfo.getId(), NewTaskCode.N001);
//				if (a) {
//	        		request.getSession().setAttribute(ConstantsConfigurer.getProperty("user_account_key"),cashAccountService.queryCashAccount(null, user.getId()));
//				}
//			}
			HtmlUtil.writerJson(response, "注册成功");
		} else {
			HtmlUtil.writerJson(response, "用户名已存在");
		}
	}

	/**
	 * 
	 * forgetpassword 找回密码
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/forgetpassword")
	public ModelAndView forgetpassword(HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView("/account/forgetpassword1");
		String token = UUID.randomUUID().toString();
		SessionUtil.setSession(session, "forgetPassword1Token", token);
		view.addObject("forgetPassword1Token", token);
		return view;
	}

	/**
	 * 
	 * forgetpassword2 找回密码第二步
	 * 
	 * @param account
	 *            账号
	 * @param token
	 * 
	 * @param session
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/forgetpassword2")
	public Object forgetpassword2(@RequestParam(value = "userName", required = true) String account,
			@RequestParam(value = "token", required = true) String token, HttpSession session) throws Exception {
		String tk = (String) SessionUtil.getSession(session, "forgetPassword1Token");
		SessionUtil.removeSession(session, "forgetPassword1Token");
		if (!tk.equals(token)) {
			super.goUrl("/account/forgetpassword1.do", "请不要重复提交");
		}
		UserInfo info = userService.selecUserByStr(account);
		if (null == info) {
			return super.goUrl("/account/forgetpassword.do", "系统检测没有该用户");
		}
		SessionUtil.setSession(session, "forgetUserInfo", info);
		ModelAndView view = new ModelAndView("/account/forgetpassword2");
		String token2 = UUID.randomUUID().toString();
		SessionUtil.setSession(session, "forgetPassword2Token", token2);
		view.addObject("forgetPassword2Token", token2);
		return view;
	}

	/**
	 * 
	 * forgetpassword3 找回密码第三步
	 * 
	 * @param type
	 *            1 手机 2 邮箱 3密保
	 * @param token
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/forgetpassword3")
	public Object forgetpassword3(@RequestParam(value = "type", required = true) Integer type,
			@RequestParam(value = "token", required = true) String token, HttpSession session) throws Exception {
		String tk = (String) SessionUtil.getSession(session, "forgetPassword2Token");
		SessionUtil.removeSession(session, "forgetPassword2Token");
		if (!tk.equals(token))
			return super.goUrl("/account/forgetpassword.do", "请不要重复提交");
		UserInfo info = (UserInfo) SessionUtil.getSession(session, "forgetUserInfo");
		if (1 == type)
			if (StringUtils.isEmpty(info.getPhoneNum()))
				return super.goUrl("/account/forgetpassword.do", "系统检测该您没有填写手机号码,请选择其他方式");
		if (2 == type) {
			if (StringUtils.isEmpty(info.getEmail()))
				return super.goUrl("/account/forgetpassword.do", "系统检测该您没有填写邮箱,请选择其他方式");
			else {
				// 发送重置密码链接
				SessionUtil.setSession(session, "forgetpassworMailUserId", info.getId());
				String emailToken = UUID.randomUUID().toString();
				SessionUtil.setSession(session, "emailToken", emailToken);
				HashMap<String, String> hashMap = new HashMap<String, String>();
				hashMap.put("title", "飞券网找回密码");
				hashMap.put("username", info.getUsername());
				hashMap.put("url", "http://" + ConstantsConfigurer.getProperty("web_url")
						+ "/account/emailBackPage.do?infoId=" + info.getId() + "&emailToken=" + emailToken);
				EmailSendUtil.send(info.getEmail(), hashMap, EmailSendUtil.BACKEAMILPASSWORD);
				return super.goUrl("/", "邮件已发送,请检查.");
			}
		}
		if (3 == type) // 密保
			if (StringUtils.isEmpty(info.getQuestion()) || StringUtils.isEmpty(info.getAnswer()))
				return super.goUrl("/account/forgetpassword.do", "系统检测该您没有填写密保资料,请选择其他方式");
		ModelAndView view = new ModelAndView("/account/forgetpassword3");
		String token2 = UUID.randomUUID().toString();
		SessionUtil.setSession(session, "forgetPassword3Token", token2);
		view.addObject("forgetPassword3Token", token2);
		view.addObject("type", type);
		return view;
	}

	/**
	 * 
	 * phoneBack 手机找回密码
	 * 
	 * @param yzm
	 * @param token
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/phoneBack")
	public Object phoneBack(@RequestParam(value = "forgetYzm", required = true) String yzm,
			@RequestParam(value = "token", required = true) String token,

			HttpSession session, HttpServletResponse response) throws Exception {
		String tk = (String) SessionUtil.getSession(session, "forgetPassword3Token");
		SessionUtil.removeSession(session, "forgetPassword3Token");
		if (!token.equals(tk)) {
			return super.goUrl("/account/forgetpassword.do", "请不要重复提交");
		}
		UserInfo info = (UserInfo) SessionUtil.getSession(session, "forgetUserInfo");
		if (null == info) {
			SessionUtil.removeSession(session, "forgetUserInfo");// 删除
			return super.goUrl("/account/forgetpassword.do", "请求时间过长");
		}
		String captcha = (String) SessionUtil.getSession(session, "captcha");
		SessionUtil.removeSession(session, "captcha");
		if (!yzm.equals(captcha)) {
			SessionUtil.removeSession(session, "forgetUserInfo");// 删除
			return super.goUrl("/account/forgetpassword.do", "手机验证码错误");// 请求超时
		} else {// 正确的.
			ModelAndView view = new ModelAndView("/account/forgetpassword3_3");
			String updatePasswordToken = UUID.randomUUID().toString();
			SessionUtil.setSession(session, "updatePasswordToken", updatePasswordToken);
			view.addObject("updatePasswordToken", updatePasswordToken);
			return view;
		}

	}

	/**
	 * 
	 * emailBack 邮箱找回密码
	 * 
	 * @param yzm
	 * @param token
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/emailBackPage")
	public Object emailBack(@RequestParam(value = "infoId", required = true) String infoId,
			@RequestParam(value = "emailToken", required = true) String emailToken, HttpSession session,
			HttpServletResponse response) throws Exception {

		String id = SessionUtil.getSession(session, "forgetpassworMailUserId").toString();
		SessionUtil.removeSession(session, "forgetpassworMailUserId");
		String token = SessionUtil.getSession(session, "emailToken").toString();
		SessionUtil.removeSession(session, "emailToken");
		if (!infoId.equals(id) || !emailToken.equals(token)) {
			return super.goUrl("/account/forgetpassword.do", "邮箱找回已失效,请重新找回");
		}
		UserInfo info = userService.selectUserById(Long.parseLong(id));
		SessionUtil.setSession(session, "forgetUserInfo", info);

		ModelAndView view = new ModelAndView("/account/forgetpassword3_3");
		String updatePasswordToken = UUID.randomUUID().toString();
		SessionUtil.setSession(session, "updatePasswordToken", updatePasswordToken);
		view.addObject("updatePasswordToken", updatePasswordToken);

		/*
		 * String tk = (String) SessionUtil.getSession(session,
		 * "forgetPassword3Token"); SessionUtil.removeSession(session,
		 * "forgetPassword3Token"); if(!token.equals(tk)){ return
		 * super.goUrl("/account/forgetpassword.do","请不要重复提交"); } UserInfo info
		 * = (UserInfo) SessionUtil.getSession(session, "forgetUserInfo");
		 * if(null==info){ SessionUtil.removeSession(session,
		 * "forgetUserInfo");//删除 return
		 * super.goUrl("/account/forgetpassword.do", "请求时间过长"); } String captcha
		 * = (String) SessionUtil.getSession(session, "captcha");
		 * SessionUtil.removeSession(session, "captcha");
		 * if(!yzm.equals(captcha)){ SessionUtil.removeSession(session,
		 * "forgetUserInfo");//删除 return
		 * super.goUrl("/account/forgetpassword.do","手机验证码错误");//请求超时
		 * }else{//正确的. ModelAndView view = new
		 * ModelAndView("/account/forgetpassword3_3"); String
		 * updatePasswordToken = UUID.randomUUID().toString();
		 * SessionUtil.setSession(session, "updatePasswordToken",
		 * updatePasswordToken);
		 * view.addObject("updatePasswordToken",updatePasswordToken); return
		 * view; }
		 */
		return view;

	}

	/**
	 * 
	 * problemBack 问题找回密码
	 * 
	 * @param question
	 *            提问
	 * @param answer
	 *            回答
	 * @param token
	 * @param session
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/problemBack")
	public Object problemBack(@RequestParam(value = "question", required = true) String question,
			@RequestParam(value = "answer", required = true) String answer,
			@RequestParam(value = "token", required = true) String token, HttpSession session,
			HttpServletResponse response) throws Exception {
		String tk = (String) SessionUtil.getSession(session, "forgetPassword3Token");
		SessionUtil.removeSession(session, "forgetPassword3Token");
		if (!token.equals(tk)) {
			return super.goUrl("/account/forgetpassword.do", "请不要重复提交");
		}
		UserInfo info = (UserInfo) SessionUtil.getSession(session, "forgetUserInfo");
		if (null == info) {
			SessionUtil.removeSession(session, "forgetUserInfo");// 删除
			return super.goUrl("/account/forgetpassword.do", "请求时间过长");
		}
		if (!info.getQuestion().equals(question) || !info.getAnswer().equals(answer)) {
			SessionUtil.removeSession(session, "forgetUserInfo");// 删除
			return super.goUrl("/account/forgetpassword.do", "资料不正确,请仔细想想");// 请求超时
		} else {// 正确的.
			ModelAndView view = new ModelAndView("/account/forgetpassword3_3");
			String updatePasswordToken = UUID.randomUUID().toString();
			SessionUtil.setSession(session, "updatePasswordToken", updatePasswordToken);
			view.addObject("updatePasswordToken", updatePasswordToken);
			return view;
		}
	}

	/**
	 * 找回密码 修改. backPassword
	 * 
	 * @param token
	 * @param password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/backPassword")
	public Object backPassword(HttpSession session, @RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "password", required = true) String password) throws Exception {
		ModelAndView view = new ModelAndView("/account/forgetpassword4");
		String tk = (String) SessionUtil.getSession(session, "updatePasswordToken");
		SessionUtil.removeSession(session, "updatePasswordToken");
		if (!token.equals(tk)) {
			return super.goUrl("/account/forgetpassword.do", "请不要重复提交");
		}
		UserInfo info = (UserInfo) SessionUtil.getSession(session, "forgetUserInfo");
		SessionUtil.removeSession(session, "forgetUserInfo");// 删除
		if (null == info) {
			return super.goUrl("/account/forgetpassword.do", "请求时间过长");
		}
		String o = info.getPassword();
		info.setPassword(EncodeMD5.GetMD5Code(password));
		int i = userService.updateUser(info, o, null, null, null, null);
		if (i > 0) {
			view.addObject(
					"msg",
					"<img src='/images/tongguo.jpg' />您的登录密码已经重新设置，请妥善保管 <br /><br /> <a href='/account/login.do' class='button_red'>立即登陆</a>");
		} else {
			view.addObject("msg", "修改失败");
		}
		return view;
	}
	@RequestMapping("/about")
	public String about(){
		return "/account/abouts";
	}
}