package cn.qhjys.mall.controller.account;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.common.PAYCODE;
import cn.qhjys.mall.entity.AdInfo;
import cn.qhjys.mall.entity.BankInfo;
import cn.qhjys.mall.entity.CashAccount;
import cn.qhjys.mall.entity.CashLog;
import cn.qhjys.mall.entity.CashSaveWithdraw;
import cn.qhjys.mall.entity.DeliveryAddr;
import cn.qhjys.mall.entity.IntegralLog;
import cn.qhjys.mall.entity.ThirdAccount;
import cn.qhjys.mall.entity.UserExpand;
import cn.qhjys.mall.entity.UserInfo;
import cn.qhjys.mall.service.AdService;
import cn.qhjys.mall.service.CashAccountService;
import cn.qhjys.mall.service.TaskInfoService;
import cn.qhjys.mall.service.ThirdAccountService;
import cn.qhjys.mall.service.UserInfoService;
import cn.qhjys.mall.service.UserTaskService;
import cn.qhjys.mall.service.seller.SellerVoService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.EncodeMD5;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.util.HttpUtil;
import cn.qhjys.mall.util.LLPayUtil;
import cn.qhjys.mall.util.MessageUtil;
import cn.qhjys.mall.util.ServletUtils;
import cn.qhjys.mall.util.SessionUtil;
import cn.qhjys.mall.vo.BankVo;
import cn.qhjys.mall.vo.DeliveryAddrVo;
import cn.qhjys.mall.vo.PaymentInfo;
import cn.qhjys.mall.vo.UserExpandVo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

/**
 * 用户信息
 * 
 * @author jxp
 *
 */
@Controller
@RequestMapping("/managermall/account")
public class UserController extends Base {

	@Autowired
	private UserInfoService userService;
	@Autowired
	private SellerVoService sellerVoService;
	@Autowired
	private CashAccountService cashAccountService;
	@Autowired
	private ThirdAccountService thirdAccountService;
	@Autowired
	private TaskInfoService taskInfoService;
	@Autowired
	private UserTaskService userTaskService;
	@Autowired
	private AdService adService;

	/**
	 * 注销用户，退出登录
	 * 
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/logoutUser")
	public ModelAndView userLogout(HttpServletRequest request, HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView("account/login");
		String user_key = ConstantsConfigurer.getUser();
		UserInfo user = (UserInfo) session.getAttribute(user_key);
		if (user == null)
			return view;
		user.setLastIp(ServletUtils.getIpAddr(request));
		user.setLastTime(new Date());
		userService.updateUserById(user);
		session.removeAttribute(user_key);
		return view;
	}

	/**
	 * 进入会员账户设置页面
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/setupAccount")
	public ModelAndView accountSetup(HttpSession session) throws Exception {
		UserInfo userInfo = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		ModelAndView view = new ModelAndView("account/setup_account");
		UserExpand userExpand = userService.getUserExpandByUserId(userInfo.getId());
		boolean isUserExpand = false;
		if (null != userExpand) {
			if (null != userExpand.getSex() && null != userExpand.getBirthday() && null != userExpand.getIdentity()
					&& null != userExpand.getMaritalStatus() && null != userExpand.getInterest()) {
				isUserExpand = true;
			}
		}
		view.addObject("isUserExpand", isUserExpand);
		return view;
	}

	/***
	 * 会员账户设置
	 * 
	 * @param user
	 *            用户信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/setupAccount", method = RequestMethod.POST)
	public void setupAccount(String nowPassword, String nowPhoneNum, String nowEmail, String nowPayCode,
			String payCode, UserInfo user, String newValidateCode, String validateCode, String emailValidateCode,
			String nowEmailValidateCode, HttpSession session, HttpServletResponse response) throws Exception {
		// JSONObject json = new JSONObject();
		UserInfo userInfo = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		CashAccount cashAccount = (CashAccount) session.getAttribute(ConstantsConfigurer
				.getProperty("user_account_key"));
		String pCode = cashAccount.getPayCode();
		String emailSessionCode = (String) session.getAttribute("emailSessionCode");
		String newEmailSessionCode = (String) session.getAttribute("newEmailSessionCode");
		if (StringUtils.isNotEmpty(emailValidateCode)) {
			if (emailSessionCode == null || !emailSessionCode.equals(emailValidateCode)) {
				HtmlUtil.writerJson(response, "验证码错误");
				return;
			}
			session.removeAttribute("emailSessionCode");
		}
		if (StringUtils.isNotBlank(nowEmailValidateCode)) {
			if (newEmailSessionCode == null || !newEmailSessionCode.equals(nowEmailValidateCode)) {
				HtmlUtil.writerJson(response, "新邮箱验证码错误");
				return;
			}
			session.removeAttribute("newEmailSessionCode");
		}
		if (userInfo == null) {
			HtmlUtil.writerJson(response, "timeout");
			return;
		} else {
			if (StringUtils.isNotEmpty(payCode)) {
				if (StringUtils.isNotEmpty(pCode)) {
					if (!(pCode.equals(nowPayCode))) {
						HtmlUtil.writerJson(response, "原支付密码错误");
						return;
					}
				}
				cashAccount.setPayCode(payCode);
			}
			if (StringUtils.isNotEmpty(nowPassword)) {
				if (StringUtils.isNotEmpty(userInfo.getPassword())) {
					if (!(userInfo.getPassword().equals(nowPassword))) {
						HtmlUtil.writerJson(response, "当前密码错误");
						return;
					}
				}
			}
			String _captcha = (String) session.getAttribute("captcha");
			String newCaptcha = (String) session.getAttribute("newCaptcha");
			if (StringUtils.isNotEmpty(validateCode)) {
				if (_captcha == null || !_captcha.equals(validateCode)) {
					HtmlUtil.writerJson(response, "codeError");
					return;
				}
				session.removeAttribute("captcha");
			} else if (StringUtils.isNotEmpty(newValidateCode)) {
				if (newCaptcha == null || !newCaptcha.equals(newValidateCode)) {
					HtmlUtil.writerJson(response, "codeError");
					return;
				}
				session.removeAttribute("newCaptcha");
			}
			if (StringUtils.isNotEmpty(user.getCardId())) {
				boolean isSuccess = userService.validateUserByCardId(userInfo.getId(), user.getCardId());
				if (isSuccess == true) {
					user.setUsername(userInfo.getUsername());
					int count = userService.updateUser(user, nowPassword, nowPhoneNum, nowEmail, payCode, cashAccount);
					userInfo = userService.selecUserByStr(userInfo.getUsername());
					session.setAttribute(ConstantsConfigurer.getUser(), userInfo);
					if (count > 0) {
						HtmlUtil.writerJson(response, "修改成功");
						return;
					} else {
						HtmlUtil.writerJson(response, "设置失败");
						return;
					}
				} else {
					HtmlUtil.writerJson(response, "身份证号码已存在");
					return;
				}
			} else {
				user.setUsername(userInfo.getUsername());
				int count = userService.updateUser(user, nowPassword, nowPhoneNum, nowEmail, payCode, cashAccount);
				userInfo = userService.selecUserByStr(userInfo.getUsername());
				session.setAttribute(ConstantsConfigurer.getUser(), userInfo);
				if (count > 0) {
//					if(userInfo.getPhoneNum()!= null && userInfo.getPhoneNum().length()>9){
//						boolean a =taskInfoService.insertNewUserTask(userInfo.getId(), NewTaskCode.N001);
//						if (a) {
//							 session.setAttribute(ConstantsConfigurer.getProperty("user_account_key"),cashAccountService.queryCashAccount(null, userInfo.getId()));
//						}
//					}
					HtmlUtil.writerJson(response, "修改成功");
					if (StringUtils.isNotBlank(nowPassword)) {
						String message = MessageUtil.UPDATEPASSWORD;
						String userName = userInfo.getUsername();
						message = message.replace("username", userName);
						MessageUtil.sendSmsContent(userInfo.getPhoneNum(), message);
					}
				} else {
					HtmlUtil.writerJson(response, "设置失败");
				}
			}
		}
	}

	/**
	 * 进入设置安全中心页面
	 * 
	 * @return
	 */
	@RequestMapping("/setupUserSafe")
	public ModelAndView setupUserSafe(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView view = new ModelAndView("account/setup_user_safe");
		UserInfo user = (UserInfo) request.getSession().getAttribute(ConstantsConfigurer.getUser());
		if (user == null) {
			view.setViewName("redirect:/account/login.do");
		} else {
			view.addObject("userInfo", user);
		}
		return view;
	}

	/***
	 * 完善个人资料
	 * 
	 * @param userExpand
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateUserExpand", method = RequestMethod.POST)
	public void updateUserExpand(@RequestParam(value = "imgs", required = false) String imgs,
			UserExpandVo userExpandVo,String nickname, HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject json = new JSONObject();
		UserInfo user = (UserInfo) request.getSession().getAttribute(ConstantsConfigurer.getUser());
		if (user == null) {
			response.sendRedirect("/account/login.do");
		} else {
			UserExpand userExpand = new UserExpand();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String birthday = userExpandVo.getBirthday();
			Date date = simpleDateFormat.parse(birthday);
			userExpand.setBirthday(date);
			userExpand.setIdentity(userExpandVo.getIdentity());
			userExpand.setInterest(userExpandVo.getInterest());
			userExpand.setSex(userExpandVo.getSex());
			userExpand.setMaritalStatus(userExpandVo.getMaritalStatus());
			userExpand.setUserId(user.getId());
			int count = userService.insertUserExpand(userExpand);
			// 修改头像
			if (!StringUtils.isEmpty(imgs)) {
				int o = userService.updateUserAvatarById(user.getId(), imgs);
				if (o > 0) {
					user = userService.selectUserById(user.getId());
					request.getSession().setAttribute(ConstantsConfigurer.getUser(), user);
				}
			}
			if (!nickname.equals(user.getNickname())) {
				user.setNickname(nickname);
				if(userService.updateUserById(user)){
					user = userService.selectUserById(user.getId());
					request.getSession().setAttribute(ConstantsConfigurer.getUser(), user);
				}
			}
			if (count > 0) {
				json.put("message", "修改成功");
//				if(!StringUtils.isEmpty(user.getEmail())&&userExpand.getSex() !=0 && userExpand.getBirthday() != null && userExpand.getIdentity() != null && userExpand.getMaritalStatus() != null){
//					boolean a =taskInfoService.insertNewUserTask(user.getId(), NewTaskCode.N002);
//					if (a) {
//						request.getSession().setAttribute(ConstantsConfigurer.getProperty("user_account_key"),cashAccountService.queryCashAccount(null, user.getId()));
//					}
//				}
			} else {
				json.put("message", "设置失败");
			}
		}
		HtmlUtil.writerJson(response, json);
	}

	/**
	 * 进入完善个人资料页面
	 * 
	 * @return
	 */
	@RequestMapping("/setupUserInfo")
	public ModelAndView setupUserInfo(HttpSession session) {
		UserInfo userInfo = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		ModelAndView view = new ModelAndView("account/setup_userInfo");
		if (userInfo == null)
			view.setViewName("redirect:/account/login.do");
		return view;
	}

	/***
	 * 根据用户编号查询用户拓展信息(以作修改)
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchExpandByUserId", method = RequestMethod.POST)
	public void searchExpandByUserId(HttpServletRequest request, HttpServletResponse response) throws Exception {
		JSONObject json = new JSONObject();
		UserInfo user = (UserInfo) request.getSession().getAttribute(ConstantsConfigurer.getUser());
		if (user == null) {
			response.sendRedirect("/account/login.do");
		} else {
			UserExpand userExpand = userService.getUserExpandByUserId(user.getId());
			if (null != userExpand) {
				UserExpandVo userExpandVo = new UserExpandVo();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				if (null != userExpand.getBirthday()) {
					String birthday = simpleDateFormat.format(userExpand.getBirthday());
					userExpandVo.setBirthday(birthday);
				}
				if (null != userExpand.getIdentity()) {
					userExpandVo.setIdentity(userExpand.getIdentity());
				}
				if (null != userExpand.getInterest()) {
					userExpandVo.setInterest(userExpand.getInterest());
				}
				if (null != userExpand.getSex()) {
					userExpandVo.setSex(userExpand.getSex());
				}
				if (null != userExpand.getMaritalStatus()) {
					userExpandVo.setMaritalStatus(userExpand.getMaritalStatus());
				}
				userExpandVo.setUserId(user.getId());
				json.put("userExpand", userExpandVo);
			}
		}
		HtmlUtil.writerJson(response, json);
	}

	/**
	 * 进入我的收货地址
	 * 
	 * @return
	 */
	@RequestMapping("/setupAddress")
	public ModelAndView setupAddress(HttpSession session) {
		UserInfo userInfo = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		ModelAndView view = new ModelAndView("account/setup_address");
		if (userInfo == null)
			view.setViewName("redirect:/account/login.do");
		return view;
	}

	/***
	 * 查询会员的收获地址
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryAddressList")
	public ModelAndView queryAddressList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView view = new ModelAndView("account/setup_address");
		UserInfo user = (UserInfo) request.getSession().getAttribute(ConstantsConfigurer.getUser());
		if (user == null) {
			view.setViewName("redirect:/account/login.do");
		} else {
			Page<DeliveryAddr> list = userService.queryAddressList(user.getId());
			view.addObject("list", list);
		}
		return view;
	}

	/***
	 * 添加会员收货地址
	 * 
	 * @param address
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertAddress", method = RequestMethod.POST)
	public void insertAddress(DeliveryAddrVo address, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserInfo user = (UserInfo) request.getSession().getAttribute(ConstantsConfigurer.getUser());
		JSONObject json = new JSONObject();
		if (user == null) {
			response.sendRedirect("/account/login.do");
		} else {
			DeliveryAddr deliveryAddr = new DeliveryAddr();
			deliveryAddr.setUserId(user.getId());
			deliveryAddr.setProvince(address.getProvince());
			deliveryAddr.setCity(address.getCity());
			deliveryAddr.setArea(address.getArea());
			deliveryAddr.setAddress(address.getAddress());
			deliveryAddr.setZip(address.getZip());
			deliveryAddr.setRecipient(address.getRecipient());
			deliveryAddr.setPhone(address.getPhone());
			deliveryAddr.setEnabled(1);
			deliveryAddr.setIsdefault(0);
			int count = userService.insertAddress(deliveryAddr);
			if (count > 0) {
				json.put("message", "添加成功");
			}
		}
		HtmlUtil.writerJson(response, json);
	}

	/***
	 * 根据收货地址表的id获取收货信息(以作修改)
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchAddressById", method = RequestMethod.POST)
	public void searchAddressById(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JSONObject json = new JSONObject();
		UserInfo user = (UserInfo) request.getSession().getAttribute(ConstantsConfigurer.getUser());
		if (user == null) {
			response.sendRedirect("/account/login.do");
		} else {
			DeliveryAddr address = userService.searchAddress(id);
			json.put("address", address);
		}
		HtmlUtil.writerJson(response, json);
	}

	/***
	 * 修改会员收货地址
	 * 
	 * @param address
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateAddress", method = RequestMethod.POST)
	public void updateAddress(DeliveryAddrVo address, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject json = new JSONObject();
		UserInfo user = (UserInfo) request.getSession().getAttribute(ConstantsConfigurer.getUser());
		if (user == null) {
			response.sendRedirect("/account/login.do");
		} else {
			DeliveryAddr deliveryAddr = new DeliveryAddr();
			deliveryAddr.setId(address.getId());
			deliveryAddr.setProvince(address.getProvince());
			deliveryAddr.setCity(address.getCity());
			deliveryAddr.setArea(address.getArea());
			deliveryAddr.setAddress(address.getAddress());
			deliveryAddr.setZip(address.getZip());
			deliveryAddr.setRecipient(address.getRecipient());
			deliveryAddr.setPhone(address.getPhone());
			deliveryAddr.setCreateTime(new Date());
			int count = userService.updateAddress(deliveryAddr);
			if (count > 0) {
				json.put("message", "修改成功");
			}
		}
		HtmlUtil.writerJson(response, json);
	}

	/***
	 * 删除会员收货地址
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteAddress", method = RequestMethod.POST)
	public void deleteAddress(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserInfo user = (UserInfo) request.getSession().getAttribute(ConstantsConfigurer.getUser());
		JSONObject json = new JSONObject();
		if (user == null) {
			response.sendRedirect("/account/login.do");
		} else {
			DeliveryAddr deliveryAddr = new DeliveryAddr();
			deliveryAddr.setId(id);
			deliveryAddr.setEnabled(0);
			int count = userService.updateAddress(deliveryAddr);
			if (count > 0) {
				json.put("message", "删除成功");
			}
		}
		HtmlUtil.writerJson(response, json);
	}

	/**
	 * 进入我的余额页面(充值记录页面)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/rechargeList")
	public ModelAndView rechargeList(Integer page, Integer pageSize, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		ModelAndView view = new ModelAndView("account/my_recharge_record");
		UserInfo user = (UserInfo) request.getSession().getAttribute(ConstantsConfigurer.getUser());
		if (page == null || page < 1)
			page = 1;
		if (pageSize == null || pageSize < 0)
			pageSize = 10;
		if (user == null) {
			view.setViewName("redirect:/account/login.do");
			return view;
		}
		String[] code = { PAYCODE.B001 };
		Page<CashLog> list = userService.queryCashLog(user.getId(), code, page, pageSize);
		view.addObject("page", list);
		return view;
	}
	@RequestMapping(value = "/myrecharge")
	public ModelAndView myrecharge(Integer page, Integer pageSize, HttpServletRequest request,
			HttpServletResponse response,HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView("account/myrechar");
		UserInfo user = (UserInfo) request.getSession().getAttribute(ConstantsConfigurer.getUser());
		if (page == null || page < 1)
			page = 1;
		if (pageSize == null || pageSize < 0)
			pageSize = 10;
		if (user == null) {
			view.setViewName("redirect:/account/login.do");
			return view;
		}
		String[] code = { PAYCODE.B001,PAYCODE.B006,PAYCODE.B009,PAYCODE.B010 };
		Page<CashLog> list = userService.queryCashLog(user.getId(), code, page, pageSize);
		view.addObject("page", list);
		UserExpand ue = userService.getUserExpandByUserId(user.getId());
		int a = 0;
		if (ue != null) {
			
		if(ue.getSex() != null && ue.getSex() != 0)
			a +=1;
		if(ue.getBirthday() != null)
			a +=1;
		if(ue.getIdentity() != null)
			a +=1;
		if(ue.getMaritalStatus() != null)
			a +=1;
		if(ue.getInterest() != null)
			a +=1;
		if(ue.getInterest() != null)
			a +=1;

		}
		view.addObject("jindu", a);
		Long cityId = (Long) session.getAttribute("cityId");
		if (cityId == null) {
			cityId = (user != null && user.getDefaultCity() != null) ? user.getDefaultCity() : 200L;
			session.setAttribute("cityId", cityId);
		}

		List<AdInfo> ads1 = adService.listAdInfoByAp(7L,cityId, 0, 1);
		view.addObject("ad", ads1.size()>0?ads1.get(0):new AdInfo());
		return view;
	}
	@RequestMapping(value = "/myexchange")
	public ModelAndView myexchange(Integer page, Integer pageSize, HttpServletRequest request,
			HttpServletResponse response,HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView("account/myexchange");
		UserInfo user = (UserInfo) request.getSession().getAttribute(ConstantsConfigurer.getUser());
		if (page == null || page < 1)
			page = 1;
		if (pageSize == null || pageSize < 0)
			pageSize = 10;
		if (user == null) {
			view.setViewName("redirect:/account/login.do");
			return view;
		}
		String[] code = { PAYCODE.B002,PAYCODE.B003,PAYCODE.B008,PAYCODE.B007 };
		Page<CashLog> list = userService.queryCashLog(user.getId(), code, page, pageSize);
		view.addObject("page", list);
		UserExpand ue = userService.getUserExpandByUserId(user.getId());
		int a = 0;
		if (ue != null) {
		if(ue.getSex() != null && ue.getSex() != 0)
			a +=1;
		if(ue.getBirthday() != null)
			a +=1;
		if(ue.getIdentity() != null)
			a +=1;
		if(ue.getMaritalStatus() != null)
			a +=1;
		if(ue.getInterest() != null)
			a +=1;
		if(ue.getInterest() != null)
			a +=1;
		}
		view.addObject("jindu", a);
		Long cityId = (Long) session.getAttribute("cityId");
		if (cityId == null) {
			cityId = (user != null && user.getDefaultCity() != null) ? user.getDefaultCity() : 200L;
			session.setAttribute("cityId", cityId);
		}

		List<AdInfo> ads1 = adService.listAdInfoByAp(7L, cityId,0, 1);
		view.addObject("ad", ads1.size()>0?ads1.get(0):new AdInfo());
		return view;
	}

	/***
	 * 进入我的余额页面(提现记录页面)
	 * 
	 * @param page
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/withdrawlist")
	public ModelAndView withdrawlist(Integer page, Integer pageSize, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView view = new ModelAndView("account/my_withdraws_record");
		UserInfo user = (UserInfo) request.getSession().getAttribute(ConstantsConfigurer.getUser());
		if (page == null || page < 1)
			page = 1;
		if (pageSize == null || pageSize < 0)
			pageSize = 10;
		if (user == null)
			view.setViewName("redirect:/account/login.do");
		else {
			String[] code = { PAYCODE.B002 };
			Page<CashLog> list = userService.queryCashLog(user.getId(), code, page, pageSize);
			view.addObject("page", list);
		}
		return view;
	}

	/***
	 * 查询我的积分明细列表
	 * 
	 * @param pagNum
	 * @param pageSize
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/pointsDetailList")
	public ModelAndView integralDetailList(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView view = new ModelAndView("account/my_points_detail");
		UserInfo user = (UserInfo) request.getSession().getAttribute(ConstantsConfigurer.getUser());
		if (page == null || page < 1)
			page = 1;
		if (pageSize == null || pageSize < 0)
			pageSize = 12;
		if (user == null)
			view.setViewName("redirect:/account/login.do");
		else {
			String[] code = { PAYCODE.B001, PAYCODE.B002, PAYCODE.B003, PAYCODE.B004, PAYCODE.B006, PAYCODE.B007 };
			List<IntegralLog> list = userService.queryIntegralDetailList(user.getId(), code, page, pageSize, startTime,
					endTime);
			view.addObject("page", list);
		}
		return view;
	}

	/**
	 * 进入我的积分页面
	 * 
	 * @return
	 */
	@RequestMapping("/myPoints")
	public ModelAndView myPoints(HttpSession session) {
		UserInfo userInfo = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		ModelAndView view = new ModelAndView("account/my_points");
		if (userInfo == null)
			view.setViewName("redirect:/account/login.do");
		return view;
	}

	/**
	 * 进入我的关联账号
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myThirdAccount")
	public ModelAndView myThirdAccount(HttpSession session) throws Exception {
		UserInfo userInfo = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		ModelAndView view = new ModelAndView("account/my_relation");
		if (userInfo == null) {
			view.setViewName("redirect:/account/login.do");
		} else {
			ThirdAccount thirdAccount = thirdAccountService.queryThirdAccount(userInfo.getId(), "001");
			view.addObject("jysp2p", thirdAccount);
		}
		return view;
	}

	/***
	 * 进入充值页面
	 * 
	 * @return
	 */
	@RequestMapping("/myRecharge")
	public ModelAndView myRecharge(HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView("/account/my_recharge");
		UserInfo userInfo = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		if (userInfo == null) {
			view.setViewName("redirect:/account/login.do");
		} else {
			String s = UUID.randomUUID().toString();
			view.addObject("myRechargeToken", s);
			view.addObject("rechargeScale", ConstantsConfigurer.getProperty("recharge_scale"));
			SessionUtil.setSession(session, "myRechargeToken", s);
		}
		return view;
	}

	/***
	 * 进入提现页面
	 * 
	 * @return
	 */
	@RequestMapping("/myWithdraws")
	public ModelAndView myWithdraws(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ModelAndView view = new ModelAndView("account/my_withdraws");
		UserInfo user = (UserInfo) request.getSession().getAttribute(ConstantsConfigurer.getUser());
		if (user == null) {
			view.setViewName("redirect:/account/login.do");
		} else {
			List<BankInfo> list = userService.queryBankList(user.getId(), 2);
			view.addObject("page", list);
			String s = UUID.randomUUID().toString();
			view.addObject("myWithdrawsToken", s);
			SessionUtil.setSession(session, "myWithdrawsToken", s);
		}
		return view;
	}

	/***
	 * 进入设置银行卡号页面
	 * 
	 * @return
	 */
	@RequestMapping("/myBank")
	public ModelAndView myBank(@RequestParam(value = "status", required = true) Integer status,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView view = new ModelAndView("account/my_bank");
		UserInfo user = (UserInfo) request.getSession().getAttribute(ConstantsConfigurer.getUser());
		if (user == null) {
			view.setViewName("redirect:/account/login.do");
		} else {
			List<BankInfo> list = userService.queryBankList(user.getId(), status);
			view.addObject("page", list);
			view.addObject("status", status);
		}
		return view;
	}

	/***
	 * 会员添加银行卡
	 * 
	 * @param bank
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertBank", method = RequestMethod.POST)
	public void insertBank(BankVo bankVo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UserInfo user = (UserInfo) request.getSession().getAttribute(ConstantsConfigurer.getUser());
		JSONObject json = new JSONObject();
		if (user == null) {
			response.sendRedirect("/account/login.do");
		} else {
			int countNum = userService.searchBankByStatus(bankVo.getCarkNum(), user.getId());
			if (countNum == 0) {
				json.put("message", "无效的银行卡");
			} else {
				BankInfo bank = new BankInfo();
				bank.setCardholder(bankVo.getCardholder());
				bank.setName(bankVo.getName());
				bank.setBranch(bankVo.getBranch());
				bank.setCarkNum(bankVo.getCarkNum());
				bank.setUserId(user.getId());
				int count = userService.insertBank(bank);
				if (count > 0) {
					json.put("message", "添加成功");
				} else {
					json.put("message", "银行卡数量超标");
				}
			}
		}
		HtmlUtil.writerJson(response, json);
	}

	/***
	 * 用户会员充值
	 * 
	 * @param response
	 * @param session
	 * @param token
	 * @param payType
	 *            支付方式
	 * @param rechargeMoney
	 *            充值金额
	 * @throws Exception
	 */
	@RequestMapping("/recharge")
	public ModelAndView recharge(HttpServletRequest req, HttpServletResponse response, HttpSession session,
			@RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "cardNo", required = false) String cardNo,
			@RequestParam(value = "payType", required = false) String payType,
			@RequestParam(value = "rechargeMoney", required = true) BigDecimal rechargeMoney) throws Exception {
		ModelAndView view = new ModelAndView("/seller/funds/gotoPrepositPay");
		UserInfo user = (UserInfo) req.getSession().getAttribute(ConstantsConfigurer.getUser());
		if (user == null) {
			view.setViewName("redirect:/account/login.do");
		} else {
			String t1 = (String) SessionUtil.getSession(session, "myRechargeToken");
			SessionUtil.removeSession(session, "myRechargeToken");
			if (!t1.equals(token)) {
				HtmlUtil.writerJson(response, "tokenError");
			} else {
				CashAccount cashAccount = cashAccountService.queryCashAccount(null, user.getId());
				CashSaveWithdraw cash = new CashSaveWithdraw();
				cash.setMoney(rechargeMoney.divide(new BigDecimal(ConstantsConfigurer.getProperty("recharge_scale"))));
				cash.setOperType(0);
				cash.setCashId(cashAccount.getId());
				Date date = new Date();
				cash.setCreateDate(date);
				cash.setStatus(2);
				String orderNum = String.valueOf(UUID.randomUUID());
				orderNum = orderNum.replaceAll("-", "");
				cash.setOrderNum(orderNum);
				boolean isSuccess = sellerVoService.insertSaveWithdraw(cash);
				if (isSuccess == true) {
					// 构造支付请求对象
					PaymentInfo paymentInfo = new PaymentInfo();
					paymentInfo.setVersion("1.0");
					paymentInfo.setOid_partner(ConstantsConfigurer.getProperty("OID_PARTNER"));// 商户编号
					paymentInfo.setUser_id("" + cashAccount.getId());
					paymentInfo.setSign_type("MD5");
					// 业务类型，连连支付根据商户业务为商户开设的业务类型；
					// （101001：虚拟商品销售、109001：实物商品销售、108001：外部账户充值）
					paymentInfo.setBusi_partner("101001");
					paymentInfo.setNo_order(orderNum);// 商户唯一订单号(只在充值时有)
					SimpleDateFormat sim = new SimpleDateFormat("yyyyMMddHHmmss");
					paymentInfo.setDt_order(sim.format(date));// 商户订单时间
					// 商品名称
					paymentInfo.setName_goods("充值");// 这里是充值，所以随便写个充值
					// paymentInfo.setInfo_order(order.getInfo_order());//订单备注信息(非必填)
					// 交易金额
					paymentInfo.setMoney_order("" + rechargeMoney.divide(new BigDecimal(ConstantsConfigurer.getProperty("recharge_scale"))));
					// 服务器异步通知地址(异步请求.do,做成功或失败后的操作)
					paymentInfo.setNotify_url(ConstantsConfigurer.getProperty("USER_NOTIFY_URL") + "?cardNo=" + cardNo);
					// 支付结束回显url(这里先跳转到充值页面)
					paymentInfo.setUrl_return(ConstantsConfigurer.getProperty("USER_RETURN_URL"));
					// 用户端申请IP(暂时不填)
					// paymentInfo.setUserreq_ip(LLPayUtil.getIpAddr(req).replaceAll("\\.","_"));
					paymentInfo.setUrl_order("");
					// 订单有效时间(单位分钟，可以为空，默认7天)
					// paymentInfo.setValid_order("10080");
					// 风险控制参数
					paymentInfo.setRisk_item(createRiskItem());
					// 时间戳
					paymentInfo.setTimestamp(LLPayUtil.getCurrentDateTimeStr());
					// 商戶从自己系统中获取用户身份信息（认证支付必须将用户身份信息传输给连连，且修改标记flag_modify设置成1：不可修改）
					// 证件类型（0身份证）
					paymentInfo.setId_type("0");
					// 根据用户编号查询用户姓名和银行卡号
					// 证件号码
					paymentInfo.setId_no(user.getCardId());
					// 银行账号姓名
					paymentInfo.setAcct_name(user.getRealname());
					paymentInfo.setFlag_modify("1");
					if (!LLPayUtil.isnull(req.getParameter("no_agree"))) {// 协议号和卡号同时存在时，优先将协议号送给连连，不要将协议号和卡号都送给连连
						paymentInfo.setNo_agree(req.getParameter("no_agree"));
					} else {
						// 银行卡号
						paymentInfo.setCard_no(cardNo);
					}
					// 返回修改信息地址(银行卡号前置，需要修改卡号时，用户点击返回的 url链接地址)
					paymentInfo.setBack_url(ConstantsConfigurer.getProperty("USER_BACK_URL"));
					// 加签名
					String sign = LLPayUtil.addSign(JSON.parseObject(JSON.toJSONString(paymentInfo)),
							ConstantsConfigurer.getProperty("trader_pri_key"),
							ConstantsConfigurer.getProperty("md5_key"));
					paymentInfo.setSign(sign);
					req.setAttribute("version", paymentInfo.getVersion());
					req.setAttribute("oid_partner", paymentInfo.getOid_partner());
					req.setAttribute("user_id", paymentInfo.getUser_id());
					req.setAttribute("sign_type", paymentInfo.getSign_type());
					req.setAttribute("busi_partner", paymentInfo.getBusi_partner());
					req.setAttribute("no_order", paymentInfo.getNo_order());
					req.setAttribute("dt_order", paymentInfo.getDt_order());
					req.setAttribute("name_goods", paymentInfo.getName_goods());
					req.setAttribute("info_order", paymentInfo.getInfo_order());
					req.setAttribute("money_order", paymentInfo.getMoney_order());
					req.setAttribute("notify_url", paymentInfo.getNotify_url());
					req.setAttribute("url_return", paymentInfo.getUrl_return());
					req.setAttribute("userreq_ip", paymentInfo.getUserreq_ip());
					req.setAttribute("url_order", paymentInfo.getUrl_order());
					req.setAttribute("valid_order", paymentInfo.getValid_order());
					req.setAttribute("timestamp", paymentInfo.getTimestamp());
					req.setAttribute("sign", paymentInfo.getSign());
					req.setAttribute("risk_item", paymentInfo.getRisk_item());
					req.setAttribute("no_agree", paymentInfo.getNo_agree());
					req.setAttribute("id_type", paymentInfo.getId_type());
					req.setAttribute("id_no", paymentInfo.getId_no());
					req.setAttribute("acct_name", paymentInfo.getAcct_name());
					req.setAttribute("flag_modify", paymentInfo.getFlag_modify());
					req.setAttribute("card_no", paymentInfo.getCard_no());
					req.setAttribute("back_url", paymentInfo.getBack_url());
					req.setAttribute("req_url", ConstantsConfigurer.getProperty("PAY_URL"));
				}
			}
		}
		return view;
	}

	/***
	 * 用户会员提现
	 * 
	 * @param response
	 * @param session
	 * @param token
	 * @param bankId
	 *            银行卡号
	 * @param payCode
	 *            提现密码
	 * @param withdrawsMoney
	 *            提现金额
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/withdraws")
	public void withdraws(HttpServletResponse response, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "bankId", required = true) Long bankId,
			@RequestParam(value = "payCode", required = true) String payCode,
			@RequestParam(value = "withdrawsMoney", required = true) BigDecimal withdrawsMoney) throws Exception {
		JSONObject json = new JSONObject();
		UserInfo user = (UserInfo) request.getSession().getAttribute(ConstantsConfigurer.getUser());
		logger.info("-------------bankId--------------------" + bankId);
		logger.info("-------------payCode--------------------" + payCode);
		logger.info("-------------withdrawsMoney--------------------" + withdrawsMoney);
		BigDecimal moe = new BigDecimal(100000);
		BigDecimal mo = moe.subtract(withdrawsMoney);
		if (mo.compareTo(BigDecimal.ZERO) == -1) {
			String message = MessageUtil.ANOMALYEXTRACTIOSINGLE;
			String userName = user.getUsername();
			message = message.replace("username", userName);
			json.put("message", message);
			HtmlUtil.writerJson(response, json.toJSONString());
			return;
		}
		if (user == null) {
			response.sendRedirect("/account/login.do");
			return;
		}
		logger.info("-------------userId--------------------" + user.getId());
		String t1 = (String) SessionUtil.getSession(session, "myWithdrawsToken");
		SessionUtil.removeSession(session, "myWithdrawsToken");
		if (!t1.equals(token)) {
			HtmlUtil.writerJson(response, "tokenError");
		} else {
			// 先判断支付密码是否正确
			CashAccount cashAccount = cashAccountService.queryCashAccount(null, user.getId());
			if (cashAccount.getPayCode().equals(payCode)) {
				BigDecimal balance = cashAccount.getBalance();
				BigDecimal isBalance = balance.subtract(withdrawsMoney);
				if (isBalance.compareTo(BigDecimal.ZERO) == -1) {
					String message = MessageUtil.ANOMALYEXTRACTIONBALANCE;
					String userName = user.getUsername();
					message = message.replace("username", userName);
					MessageUtil.sendSmsContent(user.getPhoneNum(), message);
					json.put("message", "余额不足");
					HtmlUtil.writerJson(response, json.toJSONString());
					return;
				}
				CashAccount account = new CashAccount();
				account.setAccountId(user.getId());
				account.setId(cashAccount.getId());
				account.setBalance(isBalance);
				account.setFreezeMoney(withdrawsMoney);
				account.setWithdrawMoney(withdrawsMoney);
				CashSaveWithdraw cashSave = new CashSaveWithdraw();
				cashSave.setMoney(withdrawsMoney);
				cashSave.setBankId(bankId);
				cashSave.setOperType(1);
				cashSave.setCashId(cashAccount.getId());
				cashSave.setCreateDate(new Date());
				boolean isSuccess = false;
				try {
					isSuccess = sellerVoService.insertWithdraws(account, cashSave);
				} catch (Exception e) {
					this.logger.error("用户提现异常：", e);
					json.put("message", "提现异常");
				}
				if (isSuccess == true) {
					CashAccount cash = cashAccountService.queryCashAccount(null, user.getId());
					request.getSession().setAttribute(ConstantsConfigurer.getProperty("user_account_key"), cash);
					json.put("message", "提现成功");
					String message = MessageUtil.ACCOUNTEXTRACTION;
					String userName = user.getUsername();
					String money = String.valueOf(withdrawsMoney);
					String balance1 = String.valueOf(cash.getBalance());
					message = message.replace("username", userName);
					message = message.replace("money", money);
					message = message.replace("balance", balance1);
					MessageUtil.sendSmsContent(user.getPhoneNum(), message);
				} else {
					json.put("message", "提现失败");
				}
			} else {
				json.put("message", "商城支付密码错误");
			}
		}
		HtmlUtil.writerJson(response, json);
	}

	/**
	 * 根据连连支付风控部门要求的参数进行构造风控参数
	 * 
	 * @return
	 */
	private String createRiskItem() {
		JSONObject riskItemObj = new JSONObject();
		riskItemObj.put("user_info_full_name", "你好");
		riskItemObj.put("frms_ware_category", "1999");
		return riskItemObj.toString();
	}

	@ResponseBody
	@RequestMapping("/gettoken")
	public void gettoken(HttpServletResponse response, HttpServletRequest request) throws IOException {
		byte[] a = HttpUtil.doSend("http://www.jysp2p.com/third/thirdstep1.do", "GET", null);
		String b = new String(a);
		JSONObject jo = JSON.parseObject(b);
		String token = jo.getString("jyst");
		String tokentime = jo.getString("jystt");
		request.getSession().setAttribute("thirdtoken", token);
		request.getSession().setAttribute("thirdtokentime", tokentime);
		JSONObject json = new JSONObject();
		json.put("thirdtoken", token);
		json.put("thirdtokentime", tokentime);
		json.put("issuccess", "success");
		HtmlUtil.writerJson(response, json);
	}

	@ResponseBody
	@RequestMapping("/verifyAccount")
	public void verifyAccount(HttpServletResponse response, HttpServletRequest request, String username, String phone,
			String token, String tokentime) throws Exception {
		logger.info("----------username------" + username);
		logger.info("----------phone------" + phone);
		logger.info("----------token------" + token);
		logger.info("----------tokentime------" + tokentime);
		JSONObject json = new JSONObject();
		UserInfo user = (UserInfo) request.getSession().getAttribute(ConstantsConfigurer.getUser());
		logger.info("----------user------" + user.getThirdDay());
		if (user.getThirdDay() <= 0) {
			json.put("message", "thirddayerror");
			logger.info("----------thirddayerror------");
			HtmlUtil.writerJson(response, json);
		}
		String thirdtoken = (String) request.getSession().getAttribute("thirdtoken");
		logger.info("----------thirdtoken------" + thirdtoken);
		Long thirdtokentime = new Long((String) request.getSession().getAttribute("thirdtokentime"));
		logger.info("----------thirdtokentime------" + thirdtokentime);
		if (new Date().getTime() - thirdtokentime > 5 * 60 * 1000) {
			json.put("message", "timeerror");
			logger.info("----------timeerror------");
			HtmlUtil.writerJson(response, json);
		}
		if (!thirdtoken.equals(token)) {
			logger.info("----------tokenError------");
			HtmlUtil.writerJson(response, "tokenError");
		} else {
			StringBuffer sb = new StringBuffer();
			Map<String, String> map = new HashMap<String, String>();
			map.put("username", username);
			sb.append("username=" + username + "&");
			map.put("phone", phone);
			sb.append("phone=" + phone + "&");
			map.put("token", token);
			sb.append("token=" + token + "&");
			map.put("tokentime", tokentime);
			sb.append("tokentime=" + tokentime + "&");
			sb.append("md5key=" + ConstantsConfigurer.getProperty("jysmd5"));
			map.put("sign", EncodeMD5.GetMD5Code(sb.toString()));
			byte[] a = HttpUtil.doSend("http://www.jysp2p.com/third/thirdstep2.do", "GET", map);
			String b = new String(a);
			JSONObject jo = JSON.parseObject(b);
			String[] msgs = ((String) jo.get("msg")).split(",");
			logger.info("----------msgs------" + msgs[0]);
			if (msgs[0].equals("001")) {
				String thirdcode = BaseUtil.numRandom(6);
				String content = "尊敬的用户,您正在进行金钥匙网贷账户绑定操作,本次操作的验证码为(" + thirdcode + ")";
				logger.info("----------thirdcode------" + thirdcode);
				if (MessageUtil.sendSmsContent(phone, content)) {
					request.getSession().setAttribute("thirdcode", thirdcode);
					json.put("message", "success");
					json.put("username", username);
					json.put("phone", phone);
					json.put("thirdcode", thirdcode);
				} else {
					logger.info("----------messageerror------");
					json.put("message", "messageerror");
				}
			} else {
				logger.info("----------accounterror------");
				json.put("message", "accounterror");
			}
			user.setThirdDay(user.getThirdDay() - 1);
			userService.updateUserById(user);
			request.getSession().setAttribute(ConstantsConfigurer.getUser(), user);
		}
		logger.info("----------json------" + json.toString());
		HtmlUtil.writerJson(response, json);
	}

	@ResponseBody
	@RequestMapping("/verifyCode")
	public void verifyCode(HttpServletResponse response, HttpServletRequest request, String username, String phone,
			String code) throws Exception {
		logger.info("----------username------" + username);
		logger.info("----------phone------" + phone);
		logger.info("----------code------" + code);
		JSONObject json = new JSONObject();
		UserInfo user = (UserInfo) request.getSession().getAttribute(ConstantsConfigurer.getUser());
		String thirdcode = (String) request.getSession().getAttribute("thirdcode");
		if (!code.equals(thirdcode)) {
			json.put("message", "codeerror");
		} else {
			ThirdAccount ta = new ThirdAccount();
			ta.setAccount(username);
			ta.setCreateDate(new Date());
			ta.setPhone(phone);
			ta.setThirdCode("001");
			ta.setThirdName("金钥匙网贷");
			ta.setUserId(user.getId());
			boolean issuccess = thirdAccountService.insertThirdAccount(ta);
			if (issuccess) {
				json.put("message", "success");
				//userTaskService.insertUserTaskByC(user.getId(), NewTaskCode.N004);
			} else {
				json.put("message", "saveerror");
			}
		}
		HtmlUtil.writerJson(response, json);
	}
	
	@RequestMapping("/goVerifiUserInfo")
	public ModelAndView goVerifiUserInfo(HttpServletResponse response, HttpServletRequest request) throws Exception{
		UserInfo user = (UserInfo) request.getSession().getAttribute(ConstantsConfigurer.getUser());
		UserExpand userExpand = userService.getUserExpandByUserId(user.getId());
		ModelAndView view = new ModelAndView("redirect:/managermall/account/setupUserInfo.do");
		if(null!=userExpand&&userExpand.getSex() !=0 && userExpand.getBirthday() != null && userExpand.getIdentity() != null && userExpand.getMaritalStatus() != null){
			view.setViewName("redirect:/managermall/account/setupUserSafe.do");
		}
		return view;
	}
}