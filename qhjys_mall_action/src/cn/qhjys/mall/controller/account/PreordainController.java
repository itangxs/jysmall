package cn.qhjys.mall.controller.account;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.UserInfo;
import cn.qhjys.mall.service.ProductService;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.vo.ProdSchedule;
import com.github.pagehelper.Page;

/**
 * 我的预定
 * 
 * @author JiangXiaoPing
 *
 */
@Controller
@RequestMapping("/managermall/account/preordain")
public class PreordainController extends Base {
	@Autowired
	ProductService productService;

	/**
	 * 
	 * @Title: list 我的预定分页
	 * @param session
	 * @return
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/list")
	public ModelAndView list(HttpSession session, @RequestParam(value = "status", required = false) Integer status,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("/account/preordain/list");
		if (null == page || page < 1)
			page = 1;
		if (null != status && status > 0)
			view.addObject("status", status);
		pageSize = 10;
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		Page<ProdSchedule> schedules = productService.queryScheduleProduct(user.getId(), status, page, pageSize);
		view.addObject("pageinfo", schedules);
		return view;
	}

	/**
	 * 取消预订
	 * 
	 * @Title: deletePrordain
	 * @param response
	 * @param id
	 * @throws Exception
	 * @return void
	 */
	@ResponseBody
	@RequestMapping("/deletePrordain")
	public void deletePrordain(HttpSession session, HttpServletResponse response, Long id) throws Exception {
		UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
		logger.info("-------------id-------------------"+id);
		logger.info("-------------用户ID-------------------"+user.getId());
		if (productService.deleteScheduleProduct(id, user.getId())) {
			HtmlUtil.writerJson(response, "success");
		} else {
			HtmlUtil.writerJson(response, "error");
		}

	}

}
