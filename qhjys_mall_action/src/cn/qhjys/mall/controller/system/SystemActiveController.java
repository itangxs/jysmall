package cn.qhjys.mall.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.RegisterActivity;
import cn.qhjys.mall.service.RegisterActivityService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.HtmlUtil;

@Controller
@RequestMapping("/managermall/systemmall/active")
public class SystemActiveController extends Base {

	@Autowired
	private RegisterActivityService registerActivityService;

	@RequestMapping("/list")
	public ModelAndView list(HttpSession session, @RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		if (pageNum == null || pageNum < 1)
			pageNum = 1;
		if (pageSize == null || pageSize < 0)
			pageSize = 10;
		ModelAndView view = new ModelAndView("/system/mallusermanage/activeList");
		Page<RegisterActivity> page = registerActivityService.queryRegisterActiveByPage(pageNum, pageSize);
		view.addObject("page", page);
		view.addObject("pageNum", pageNum);
		return view;
	}

	@RequestMapping("/updateEnable")
	public void updateActiveEnable(HttpServletRequest request, HttpServletResponse response, Long activeId) {
		RegisterActivity ra = registerActivityService.getRegisterActivity(activeId);
		if (ra.getEnabled() == 1) {
			ra.setEnabled(0);
		} else if (ra.getEnabled() == 0) {
			ra.setEnabled(1);
		}
		int a = registerActivityService.updateRegisterActivity(ra);
		if (a > 0)
			HtmlUtil.writerJson(response, "success");
		else
			HtmlUtil.writerJson(response, "error");
	}

	@RequestMapping("/updateActive")
	public Object updateActive(HttpServletRequest request, HttpServletResponse response, Long activeId, String beginTime,
			String endTime, Integer commonCoupon, Integer storeCoupon, Integer enabled, Integer couponValidity) {
		RegisterActivity ra = new RegisterActivity();
		ra.setBeginTime(BaseUtil.strToDate(beginTime));
		ra.setCommonCoupon(commonCoupon);
		ra.setCouponValidity(couponValidity);
		ra.setEnabled(enabled);
		ra.setEndTime(BaseUtil.strToDate(endTime));
		ra.setId(activeId);
		ra.setStoreCoupon(storeCoupon);
		int a = registerActivityService.updateRegisterActivity(ra);
		if (a > 0)
			return super.goUrl("/managermall/systemmall/active/list.do", "修改成功!");
		else
			return super.goUrl("/managermall/systemmall/active/list.do", "操作失败!");
	}

	@RequestMapping("/saveActive")
	public Object saveActive(String beginTime, String endTime, Integer commonCoupon, Integer storeCoupon, Integer enabled,
			Integer couponValidity) {
		RegisterActivity ra = new RegisterActivity();
		ra.setBeginTime(BaseUtil.strToDate(beginTime));
		ra.setCommonCoupon(commonCoupon);
		ra.setCouponValidity(couponValidity);
		ra.setEnabled(enabled);
		ra.setEndTime(BaseUtil.strToDate(endTime));
		ra.setStoreCoupon(storeCoupon);
		int a = registerActivityService.insertRegisterActivity(ra);
		if (a > 0)
			return super.goUrl("/managermall/systemmall/active/list.do", "添加成功!");
		else
			return super.goUrl("/managermall/systemmall/active/list.do", "添加失败!");
	}

	@RequestMapping("/goUpdate")
	public ModelAndView goUpdate(Long activeId) {
		RegisterActivity ra = registerActivityService.getRegisterActivity(activeId);
		ModelAndView view = new ModelAndView("/system/mallusermanage/updateActive");
		view.addObject("active", ra);
		return view;
	}

	@RequestMapping("/goSave")
	public ModelAndView goSave() {
		ModelAndView view = new ModelAndView("/system/mallusermanage/saveActive");
		return view;
	}
}
