package cn.qhjys.mall.controller.wap;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.CouponInfo;
import cn.qhjys.mall.entity.RegisterActivity;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.UserInfo;
import cn.qhjys.mall.service.CouponInfoService;
import cn.qhjys.mall.service.PaymentService;
import cn.qhjys.mall.service.RegisterActivityService;
import cn.qhjys.mall.service.SellerService;
import cn.qhjys.mall.service.TaskInfoService;
import cn.qhjys.mall.service.UserInfoService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.EncodeMD5;
import cn.qhjys.mall.util.SessionUtil;

/**
 * 
 * 类名称:UserCommController wap用户
 * 类描述:
 * 创建人:JiangXiaoPing
 * 创建时间:2015年9月17日上午9:34:34
 * 修改人
 * 修改时间:
 * 修改备注:
 */ 
@Controller
@RequestMapping("/account/wap")
public class UserWapCommController extends Base {

	@Autowired
	private UserInfoService userService;
	@Autowired
	private RegisterActivityService registerActivityService;
	@Autowired
	private CouponInfoService couponInfoService;
	@Autowired
	private TaskInfoService taskInfoService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private SellerService sellerService;
	
	/**
	 * 进入会员信息注册页面(邮箱注册)
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/registrationByEmail")
	public ModelAndView doRegistrationByEmail(
			@RequestParam(value ="recomId",required = false)String recomId,
			@RequestParam(value ="inviteCode",required = false)String inviteCode,
			HttpSession session
			) throws Exception {
		ModelAndView view = new ModelAndView("/wap/registration_email");
		String token = UUID.randomUUID().toString();
		SessionUtil.setSession(session, "wapRegisterToken", token);
		view.addObject("wapRegisterToken", token);
		view.addObject("recomId",recomId);
		view.addObject("inviteCode",inviteCode);
		return view;
		//return "account/registration_email";
	}

	/**
	 * 进入会员信息注册页面(手机号码注册)
	 * 
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/registrationByTel")
	public ModelAndView doRegistrationByTel(
			@RequestParam(value ="recomId",required = false)String recomId,
			@RequestParam(value ="inviteCode",required = false)String inviteCode,
			HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView("/wap/registration_telephone");
		String token = UUID.randomUUID().toString();
		SessionUtil.setSession(session, "wapRegisterToken", token);
		view.addObject("wapRegisterToken", token);
		view.addObject("recomId",recomId);
		view.addObject("inviteCode",inviteCode);
		return view;
		//return "account/registration_telephone";
	}
	
	
	
	
	@RequestMapping("/saveRegister")
	public Object savePhoneRegister(
			@RequestParam(value ="inviteCode",required = false)String inviteCode,
			@RequestParam(value ="recomId",required = false)String recomId,
			@RequestParam(value ="token",required = true)String token,
			@RequestParam(value ="page",required = true)Integer page,//1 手机 2邮箱
			String validateCode,UserInfo user, 
			HttpSession session,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String stoken = (String) SessionUtil.getSession(session, "wapRegisterToken");
		SessionUtil.removeSession(session, "wapRegisterToken");
		if(!token.equals(stoken)){
			if(1==page){
				 return	super.goUrl("/account/wap/registrationByEmail.do?recomId="+recomId+"&inviteCode="+inviteCode, "请不要重复提交");
			}else{
				 return	super.goUrl("/account/wap/registrationByTel.do?recomId="+recomId+"&inviteCode="+inviteCode, "请不要重复提交");
			}
		
		}
		
		String _captcha = (String) request.getSession().getAttribute("captcha");
		logger.info("--------------_captcha----------------"+_captcha);
		logger.info("--------------validateCode----------------"+validateCode);
		if (StringUtils.isNotEmpty(validateCode)) {
			if (_captcha == null || !_captcha.equals(validateCode)) {
				if(1==page){
					 return	super.goUrl("/account/wap/registrationByEmail.do?recomId="+recomId+"&inviteCode="+inviteCode, "手机验证码错误");
				}else{
					 return	super.goUrl("/account/wap/registrationByTel.do?recomId="+recomId+"&inviteCode="+inviteCode, "手机验证码错误");
				}
			}
		}
		if (StringUtils.isNotEmpty(inviteCode)) {
			UserInfo userInfo = userInfoService.getUserByInvite(user.getInviteCode());
			SellerInfo seller = sellerService.getSellerInfoByInvite(user.getInviteCode());
			if (userInfo == null && seller == null) {
				user.setInviteCode(null);
				user.setNotice(1);
			}else{
				user.setInviteCode(Integer.valueOf(inviteCode));
			}
			
		}
		
		
		request.getSession().removeAttribute("captcha");
		String nickname = "JYS_" + BaseUtil.numRandom(5);
		user.setNickname(nickname);
		user.setDefaultCity(200L);
		user.setRegistTime(new Date());
		user.setPassword(EncodeMD5.GetMD5Code(user.getPassword()));
		user.setReqistSource("商城wap");
		String openId = (String) request.getSession().getAttribute("openID");
		if (!StringUtils.isEmpty(openId)) {
			user.setQqOpenId(openId);
		}
		if (!StringUtils.isEmpty(recomId)) {
			user.setRecomId(Long.valueOf(recomId));
		}
		boolean result = userService.insertUser(user);
		if (result) {
			UserInfo userinfo = userService.selecUserByStr(user.getPhoneNum() == null ? user.getEmail() : user.getPhoneNum());
			userinfo.setInvite(100000+userinfo.getId().intValue()+60);
			userinfo.setNoticeTime(new Date(System.currentTimeMillis()+24*60*60*1000L));
			userInfoService.updateUserById(userinfo);
			logger.info("--------------userinfo----------------"+userinfo.getId());
			RegisterActivity registerActivity = registerActivityService.getRegisterActivityByDate(null);
			logger.info("--------------registerActivity----------------"+registerActivity);
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
				userInfoService.updateUserById(user);
//	        	taskInfoService.insertNewUserTask(user.getId(), NewTaskCode.N005);
			}
//			if (!StringUtils.isEmpty(userinfo.getPhoneNum())) {
//				taskInfoService.insertNewUserTask(userinfo.getId(), NewTaskCode.N001);
//			}
			if(!StringUtils.isEmpty(userinfo.getPhoneNum())&&userinfo.getId()>0&&userinfo.getInviteCode()!=null){
				if (userinfo.getInviteCode() <200000) {
					userInfoService.addInviteTomat(userinfo.getInviteCode(), 40);
				}else{
					sellerService.addInviteTomat(userinfo.getInviteCode(), 40);
				}
			}
			return super.goUrl("/account/wap/saveSuccess.do", "注册成功");
		} else {
			if(1==page){
				 return	super.goUrl("/account/wap/registrationByEmail.do?recomId="+recomId+"&inviteCode="+inviteCode,"用户名或者邮箱已经存在");
			}else{
				 return	super.goUrl("/account/wap/registrationByTel.do?recomId="+recomId+"&inviteCode="+inviteCode, "用户名或者手机号已经存在");
			}
		}
	}
	
	/**
	 * saveSuccess  注册成功
	 * @return
	 *
	 */
	@RequestMapping("/saveSuccess")
	public  ModelAndView saveSuccess(){
		ModelAndView view = new ModelAndView("/wap/register_success");
		return view;
	}
	
}
