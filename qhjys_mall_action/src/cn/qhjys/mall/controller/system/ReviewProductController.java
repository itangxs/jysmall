package cn.qhjys.mall.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import cn.qhjys.mall.entity.AdminUser;
import cn.qhjys.mall.service.AdminLogService;
import cn.qhjys.mall.service.system.ReviewProductService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.util.ServletUtils;
import cn.qhjys.mall.vo.system.ReviewProductVo;
import com.github.pagehelper.Page;

/***
 * 系统后台商品评论
 * 
 * @author zengrong
 *
 */
@Controller
@RequestMapping("/managermall/systemmall/product/review")
public class ReviewProductController {
	@Autowired
	private ReviewProductService reviewProductService;
	@Autowired
	private AdminLogService adminLogService;

	private AdminUser getAdminUserSession(HttpSession session) {
		AdminUser adminUser = (AdminUser) session.getAttribute(ConstantsConfigurer.getProperty("system_key"));
		return adminUser;
	}

	@RequestMapping("/list")
	public ModelAndView list(HttpSession session,
			@RequestParam(value = "productName", required = false) String productName,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "storeName", required = false) String storeName,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("system/product/review_list");
		if (pageNum == null || pageNum < 1)
			pageNum = 1;
		if (pageSize == null || pageSize < 0)
			pageSize = 10;
		Page<ReviewProductVo> page = reviewProductService.queryReviewProductList(productName, userName, startTime,
				endTime, storeName, pageNum, pageSize);
		view.addObject("page", page);
		view.addObject("productName", productName);
		view.addObject("userName", userName);
		view.addObject("startTime", startTime);
		view.addObject("endTime", endTime);
		view.addObject("storeName", storeName);
		return view;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(HttpSession session, HttpServletRequest request, HttpServletResponse response, String id)
			throws Exception {
		String[] ids = id.split(",");
		boolean isSuccess = reviewProductService.deleteReviewProduct(ids);
		if (isSuccess) {
			AdminUser admin = getAdminUserSession(session);
			String info = BaseUtil.getLogInfo("删除商品评论", "ProductInfo", null);
			info = info.replace("null", "");
			for (int i = 0; i < ids.length; i++)
				info += ids[i].toString() + ",";
			String IP = ServletUtils.getIpAddr(request);
			adminLogService.insertAdminLog(admin.getId(), info.substring(0, info.length() - 1), "商品管理", IP);
			HtmlUtil.writerJson(response, "刪除成功");
		} else
			HtmlUtil.writerJson(response, "刪除失败");
	}
}
