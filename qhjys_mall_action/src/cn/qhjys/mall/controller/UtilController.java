package cn.qhjys.mall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.AreaInfo;
import cn.qhjys.mall.entity.CityInfo;
import cn.qhjys.mall.entity.ProvinceInfo;
import cn.qhjys.mall.service.PhoneCodeService;
import cn.qhjys.mall.service.PositionService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.EmailSendUtil;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.util.MessageUtil;
import cn.qhjys.mall.util.ServletUtils;
import cn.qhjys.mall.util.SessionUtil;

@Controller
public class UtilController extends Base {
	@Autowired
	private PhoneCodeService phoneCodeService;
	@Autowired
	private PositionService positionService;

	/**
	 * PC端发送手机短信
	 * 
	 * @param session
	 * @param response
	 * @param phone
	 * @throws Exception
	 */
	@RequestMapping("/sendSMSCaptcha")
	public void sendSMSCaptcha(HttpSession session, HttpServletResponse response, HttpServletRequest request,
			String phone) throws Exception {
		if (!BaseUtil.isMobile(phone)) {
			HtmlUtil.writerJson(response, SMSEnum.FMT_ERROR.tag);
			return;
		}
		boolean canDo = phoneCodeService.canDoPhoneCode(phone);
		if (!canDo) {
			HtmlUtil.writerJson(response, SMSEnum.ERROR.tag);
			return;
		}
		String captcha = BaseUtil.numRandom(6);
		Map<String, String> map = new HashMap<String, String>();
		map.put("captcha", captcha);
		boolean result = MessageUtil.SendMessage(phone, map, MessageUtil.COMMCAPTCHA);
		session.setAttribute("phone", phone);
		session.setAttribute("captcha", captcha);
		logger.error("changepassword-sendmessage----phone:"+phone+"-----captcha:"+captcha);
		if (result) {
			String ip = ServletUtils.getIpAddr(request);
			phoneCodeService.insertPhondCode(phone, captcha, ip);
			HtmlUtil.writerJson(response, SMSEnum.SUCCESS.tag);
		} else
			HtmlUtil.writerJson(response, SMSEnum.ERROR.tag);
	}

	/**
	 * 发送第二个手机短信
	 * 
	 * @param session
	 * @param response
	 * @param phone
	 * @throws Exception
	 */
	@RequestMapping("/sendNewSMSCaptcha")
	public void sendNewSMSCaptcha(HttpSession session, HttpServletResponse response, HttpServletRequest request,
			String newPhone) throws Exception {
		if (!BaseUtil.isMobile(newPhone)) {
			HtmlUtil.writerJson(response, SMSEnum.FMT_ERROR.tag);
			return;
		}
		boolean can = phoneCodeService.canDoPhoneCode(newPhone);
		if (!can) {
			HtmlUtil.writerJson(response, SMSEnum.ERROR.tag);
			return;
		}
		String captcha = BaseUtil.numRandom(6);
		Map<String, String> map = new HashMap<String, String>();
		map.put("captcha", captcha);
		boolean result = MessageUtil.SendMessage(newPhone, map, MessageUtil.COMMCAPTCHA);
		session.setAttribute("newPhone", newPhone);
		session.setAttribute("newCaptcha", captcha);
		if (result) {
			String ip = ServletUtils.getIpAddr(request);
			phoneCodeService.insertPhondCode(newPhone, captcha, ip);
			
			HtmlUtil.writerJson(response, SMSEnum.SUCCESS.tag);
			return;
		} else
			HtmlUtil.writerJson(response, SMSEnum.ERROR.tag);
		return;
	}

	/**
	 * 
	 * sendRequestEmailCaptcha 发送邮件验证码
	 * 
	 * @param emailUrl
	 *            邮箱地址
	 * @param status
	 *            状态,,待定
	 * @param session
	 * @param response
	 * @param request
	 *            emailSessionCode 是放在session中的名字
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/sendRequestEmailCaptcha")
	public void sendEmailCaptcha(@RequestParam(value = "emailUrl", required = true) String emailUrl,
	/* @RequestParam (value = "status",required = false) int status, */
	HttpSession session, HttpServletResponse response) throws Exception {
		String code = BaseUtil.numRandom(6);
		SessionUtil.setSession(session, "emailSessionCode", code);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("title", "金钥匙邮箱验证");
		hashMap.put("code", code);
		Boolean send = EmailSendUtil.send(emailUrl, hashMap, EmailSendUtil.EMAILCODE);
		if (send) {
			HtmlUtil.writerJson(response, "succeess");
		} else {
			HtmlUtil.writerJson(response, "error");
		}
	}

	/**
	 * 
	 * sendRequestEmailCaptcha 发送邮件验证码
	 * 
	 * @param emailUrl
	 *            邮箱地址
	 * @param status
	 *            状态,待定
	 * @param session
	 * @param response
	 * @param request
	 *            emailSessionCode 是放在session中的名字
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/sendRequestNewEmailCaptcha")
	public void sendNewEmailCaptcha(@RequestParam(value = "emailUrl", required = true) String emailUrl,
	/* @RequestParam (value = "status",required = false) int status, */
	HttpSession session, HttpServletResponse response) throws Exception {
		String code = BaseUtil.numRandom(6);
		SessionUtil.setSession(session, "newEmailSessionCode", code);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("title", "金钥匙邮箱验证");
		hashMap.put("code", code);
		Boolean send = EmailSendUtil.send(emailUrl, hashMap, EmailSendUtil.EMAILCODE);
		if (send) {
			HtmlUtil.writerJson(response, "succeess");
		} else {
			HtmlUtil.writerJson(response, "error");
		}

	}

	/**
	 * 获取省份列表
	 * 
	 * @param response
	 */
	@RequestMapping("/queryProv")
	public void queryProvince(HttpServletResponse response) {
		try {
			List<ProvinceInfo> list = positionService.queryProvince();
			HtmlUtil.writerJson(response, list);
		} catch (Exception e) {
			this.logger.error("获取省份列表异常：", e);
		}
	}

	/**
	 * 获取城市列表
	 * 
	 * @param response
	 * @param provId
	 */
	@RequestMapping("/queryCity")
	public void queryProvince(HttpServletResponse response, Long provId) {
		try {
			List<CityInfo> list = positionService.queryCityByProvince(provId);
			HtmlUtil.writerJson(response, list);
		} catch (Exception e) {
			this.logger.error("获取城市列表异常：", e);
		}
	}

	/**
	 * 获取区域列表
	 * 
	 * @param response
	 * @param cityId
	 */
	@RequestMapping("/queryArea")
	public void queryArea(HttpServletResponse response, Long cityId) {
		try {
			List<AreaInfo> list = positionService.queryAreaByCity(cityId);
			HtmlUtil.writerJson(response, list);
		} catch (Exception e) {
			this.logger.error("获取区域列表异常：", e);
		}
	}

	public enum SMSEnum {
		SUCCESS("0000", "短信发送成功！"), FMT_ERROR("0001", "手机号码错误！"), ERROR("0002", "发送短信失败");

		public final String tag;
		public final String msg;

		SMSEnum(String tag, String msg) {
			this.tag = tag;
			this.msg = msg;
		}

		public String getTag() {
			return tag;
		}

		public String getMsg() {
			return msg;
		}

		public static boolean isSMSType(String tag) {
			for (SMSEnum s : SMSEnum.values())
				if (s.getTag().equals(tag))
					return true;
			return false;
		}
	}

}