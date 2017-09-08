package cn.qhjys.mall.controller.seller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.SellerExpand;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.service.SellerService;
import cn.qhjys.mall.service.seller.SellerVoService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.vo.seller.SellerCenterVo;

/**
 * 卖家(商家)中心
 * 
 * @author JiangXiaoPing
 *
 */
@Controller
@RequestMapping("/managermall/seller/center")
public class SellerCenterController extends Base {
	@Autowired
	private SellerService sellerService;
	@Autowired
	private SellerVoService sellerVoService;

	/**
	 * 
	 * @Title: view 中心页面
	 * @param session
	 * 
	 * @return
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping("/page")
	public ModelAndView view(HttpSession session) throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		ModelAndView view = new ModelAndView("/seller/center/page");
		logger.info("-111111--");
		if (seller == null) {
			view.setViewName("redirect:/seller/login.do");
			return view;
		}
		// 根据商家编号查询店铺是否审核通过，不通过则跳转到对应的入驻步骤
		boolean isSuccess = sellerVoService.queryStoreBySellerId(seller.getId());
		if (isSuccess == false){
			view.setViewName("redirect:/managermall/seller/serviceAgreement.do");
			logger.info("-222222--");
		}else {
			SellerCenterVo vo = sellerVoService.querySellerCenterVoByUiaD(seller.getId());// 获取商家中心数据Vo
			view.addObject("vo", vo);
			logger.info("-33333--");
		}
		return view;
	}

	/**
	 * 跳转设置商家扩展信息
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/setSellerInfo")
	public ModelAndView setSellerInfo(HttpSession session) throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		ModelAndView view = new ModelAndView("/seller/security/sellerInfo");
		if (seller == null) {
			view.setViewName("redirect:/seller/login.do");
			return view;
		}
		seller = sellerService.getSellerById(seller.getId());
		SellerExpand expand = sellerService.getSellerExpandById(seller.getId());
		view.addObject("seller", seller);
		view.addObject("expand", expand);
		return view;
	}

	/**
	 * 设置商家扩展信息
	 * 
	 * @param session
	 * @param name
	 * @param sex
	 * @param prov
	 * @param city
	 * @param area
	 * @param address
	 * @return
	 */
	@RequestMapping("/changeSellerExpand")
	public Object changeSellerExpand(HttpSession session, String name, Integer sex, Long prov, Long city, Long area,
			String address) throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		ModelAndView view = new ModelAndView("redirect:/managermall/seller/center/setSellerInfo.do");
		if (seller == null) {
			view.setViewName("redirect:/seller/login.do");
			return view;
		}
		seller = sellerService.getSellerById(seller.getId());
		seller.setRealname(name);
		sellerService.updateSellerById(seller);
		session.setAttribute(ConstantsConfigurer.getSeller(), seller);
		SellerExpand expand = new SellerExpand();
		expand.setSellerId(seller.getId());
		expand.setSellerSex(sex);
		expand.setProvince(prov);
		expand.setCity(city);
		expand.setArea(area);
		expand.setAddress(address);
		sellerService.updateSellerExpand(expand);
		return super.goUrl("/managermall/seller/center/setSellerInfo.do", "修改成功");
	}

	/**
	 * 安全中心
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/center")
	public ModelAndView toSecurityCenter(HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView("/seller/security/center");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			view.setViewName("redirect:/seller/login.do");
			return view;
		}
		seller = sellerService.getSellerById(seller.getId());
		session.setAttribute(ConstantsConfigurer.getSeller(), seller);
		view.addObject("seller", seller);
		return view;
	}

	/**
	 * 跳转修改密码页
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/updatePassWord")
	public ModelAndView toUpdatePassWord(HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView("/seller/security/password");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) { 
			view.setViewName("redirect:/seller/login.do");
			return view;
		}
		seller = sellerService.getSellerById(seller.getId());
		view.addObject("seller", seller);
		return view;
	}

	/**
	 * 修改密码
	 * 
	 * @param session
	 * @param response
	 * @param captcha
	 * @param oldCode
	 * @param newCode
	 */
	@RequestMapping("/changePassWord")
	public void updatePassWord(HttpSession session, HttpServletResponse response, String captcha, String oldCode,
			String newCode) throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			HtmlUtil.writerJson(response, "timeout");
			return;
		}
		String _captcha = (String) session.getAttribute("captcha");
		logger.error("changepassword-sessioncaptcha:"+_captcha+"----Phone------"+seller.getPhone()+"---inputcaptcha:"+captcha+"----oldCode:"+oldCode+"--newCode:"+newCode);
		if (_captcha == null || !_captcha.equals(captcha)) {
			HtmlUtil.writerJson(response, "codeError");
			return;
		}
		seller = sellerService.getSellerById(seller.getId());
		if (!seller.getPassword().equals(oldCode)) {
			HtmlUtil.writerJson(response, "error");
			return;
		}
		session.removeAttribute("captcha");
		seller.setPassword(newCode);
		boolean result = sellerService.updateSellerById(seller);
		session.setAttribute(ConstantsConfigurer.getSeller(), seller);
		HtmlUtil.writerJson(response, result);
	}

	/**
	 * 跳转验证手机页
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/updatePhone")
	public ModelAndView toUpdatePhone(HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView("/seller/security/verifyPhone");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			view.setViewName("redirect:/seller/login.do");
			return view;
		}
		view.addObject("seller", seller);
		return view;
	}

	/**
	 * 验证原手机
	 * 
	 * @param session
	 * @param response
	 * @param captcha
	 */
	@RequestMapping("/verifyPhone")
	public void verifyPhone(HttpSession session, HttpServletResponse response, String captcha) throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			HtmlUtil.writerJson(response, "timeout");
			return;
		}
		String _captcha = (String) session.getAttribute("captcha");
		boolean result = _captcha != null && _captcha.equals(captcha);
		session.setAttribute("verifyPhone", String.valueOf(result));
		session.removeAttribute("captcha");
		HtmlUtil.writerJson(response, result);
	}

	/**
	 * 跳转修改手机页
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/updateNewPhone")
	public ModelAndView updateNewPhone(HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView("/seller/security/newPhone");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			view.setViewName("redirect:/seller/login.do");
			return view;
		}
		String verify = (String) session.getAttribute("verifyPhone");
		if (!"true".equals(verify)) {
			view.setViewName("/seller/security/verifyPhone");
			return view;
		}
		return view;
	}

	/**
	 * 绑定新手机
	 * 
	 * @param session
	 * @param response
	 */
	@RequestMapping("/changePhone")
	public void changePhone(HttpSession session, HttpServletResponse response, String newPhone, String captcha)
			throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			HtmlUtil.writerJson(response, "timeout");
			return;
		}
		String _captcha = (String) session.getAttribute("captcha");
		if (_captcha == null || !_captcha.equals(captcha)) {
			HtmlUtil.writerJson(response, "codeError");
			return;
		}
		session.removeAttribute("captcha");
		seller = sellerService.getSellerById(seller.getId());
		seller.setPhone(newPhone);
		boolean result = sellerService.updateSellerById(seller);
		session.setAttribute(ConstantsConfigurer.getSeller(), seller);
		HtmlUtil.writerJson(response, result);
	}

	/**
	 * 跳转手机绑定成功页
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/updatedPhone")
	public String updatedPhone(HttpSession session) throws Exception {
		session.removeAttribute("verifyPhone");
		return "/seller/security/updatedPhone";
	}

	/**
	 * 跳转绑定邮箱页
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/toChangEmail")
	public ModelAndView toChangEmail(HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView("/seller/security/verifyEmail");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			view.setViewName("redirect:/seller/login.do");
			return view;
		}
		if (BaseUtil.judgeNull(seller.getEmail()))
			session.setAttribute("hasEmail", false);
		else
			session.setAttribute("hasEmail", true);
		view.addObject("seller", seller);
		return view;
	}

	/**
	 * 添加邮箱地址
	 * 
	 * @param session
	 * @param response
	 * @param email
	 */
	@RequestMapping("addEmail")
	public void addEmail(HttpSession session, HttpServletResponse response, String email, String captcha)
			throws Exception {
		if (!BaseUtil.isEmail(email)) {
			HtmlUtil.writerJson(response, false);
			return;
		}
		/**
		 * 邮箱验证码
		 */
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			HtmlUtil.writerJson(response, "timeout");
			return;
		}
		String emailSessionCode = (String) session.getAttribute("emailSessionCode");
		if (StringUtils.isNotEmpty(captcha)) {
			if (emailSessionCode == null || !emailSessionCode.equals(captcha)) {
				HtmlUtil.writerJson(response, "验证码错误");
				return;
			}
			session.removeAttribute("emailSessionCode");
		}
		seller = sellerService.getSellerById(seller.getId());
		if (seller.getEmail().equals(email)) {
			HtmlUtil.writerJson(response, "您修改的邮箱和原邮箱重复！");
			return;
		}
		seller.setEmail(email);
		boolean result = sellerService.updateSellerById(seller);
		session.setAttribute(ConstantsConfigurer.getSeller(), seller);
		HtmlUtil.writerJson(response, result);
	}

	/**
	 * 验证原邮箱
	 * 
	 * @param session
	 * @param response
	 * @param captcha
	 */
	@RequestMapping("/verifyEmail")
	public void verifyEmail(HttpSession session, HttpServletResponse response, String captcha) throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			HtmlUtil.writerJson(response, "timeout");
			return;
		}
		String emailSessionCode = (String) session.getAttribute("emailSessionCode");
		if (StringUtils.isNotEmpty(captcha)) {
			if (emailSessionCode == null || !emailSessionCode.equals(captcha)) {
				HtmlUtil.writerJson(response, "验证码错误");
				return;
			}
			session.removeAttribute("emailSessionCode");
		}
		boolean result = true;
		session.setAttribute("verifyEmail", String.valueOf(result));
		/**
		 * 验证原邮箱
		 */
		HtmlUtil.writerJson(response, result);
	}

	/**
	 * 跳转更新邮箱页
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/changNewEmail")
	public ModelAndView changNewEmail(HttpSession session) throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		ModelAndView view = new ModelAndView("/seller/security/newEmail");
		if (seller == null) {
			view.setViewName("redirect:/seller/login.do");
			return view;
		}
		String verify = (String) session.getAttribute("verifyEmail");
		if (!"true".equals(verify)) {
			view.setViewName("/seller/security/verifyEmail");
			return view;
		}
		return view;
	}

	/**
	 * 邮箱绑定成功
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/changedEmail")
	public ModelAndView changedEmail(HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView("/seller/security/changedEmail");
		return view;
	}

	/**
	 * 跳转修改密保问题页
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/setQuestion")
	public ModelAndView setQuestion(HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView("/seller/security/setQuestion");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			view.setViewName("redirect:/seller/login.do");
			return view;
		}
		view.addObject("seller", seller);
		return view;
	}

	/**
	 * 添加密保问题
	 * 
	 * @param session
	 * @param response
	 * @param question
	 * @param answer
	 */
	@RequestMapping("/addQuestion")
	public void addQuestion(HttpSession session, HttpServletResponse response, String question, String answer)
			throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			HtmlUtil.writerJson(response, "timeout");
			return;
		}
		if (BaseUtil.judgeNull(question) || BaseUtil.judgeNull(answer)) {
			HtmlUtil.writerJson(response, "error");
			return;
		}
		seller = sellerService.getSellerById(seller.getId());
		seller.setQuestion(question);
		seller.setAnswer(answer);
		boolean result = sellerService.updateSellerById(seller);
		session.setAttribute(ConstantsConfigurer.getSeller(), seller);
		HtmlUtil.writerJson(response, result);
	}

	/**
	 * 验证密保答案
	 * 
	 * @param session
	 * @param response
	 * @param answer
	 */
	@RequestMapping("/verifyAnswer")
	public void verifyAnswer(HttpSession session, HttpServletResponse response, String answer) throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			HtmlUtil.writerJson(response, "timeout");
			return;
		}
		if (BaseUtil.judgeNull(answer) || !answer.equals(seller.getAnswer())) {
			HtmlUtil.writerJson(response, false);
			return;
		}
		session.setAttribute("verifyAnswer", "true");
		HtmlUtil.writerJson(response, true);
	}

	/**
	 * 跳转更新密保问题页
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping("/toSetQuestion")
	public ModelAndView toSetQuestion(HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView("/seller/security/newAnswer");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			view.setViewName("redirect:/seller/login.do");
			return view;
		}
		String verify = (String) session.getAttribute("verifyAnswer");
		if (!"true".equals(verify))
			view.setViewName("/seller/security/setQuestion");
		return view;
	}
}