package cn.qhjys.mall.controller.system;

import java.math.BigDecimal;
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
import cn.qhjys.mall.service.system.AuditProductService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.util.ServletUtils;
import cn.qhjys.mall.vo.system.AuditProductVo;
import com.github.pagehelper.Page;

/**
 * 系统后台商品审核
 */
@Controller
@RequestMapping("/managermall/systemmall/product/audit")
public class AuditProductController {
	@Autowired
	private AuditProductService auditProductService;
	@Autowired
	private AdminLogService adminLogService;

	private AdminUser getAdminUserSession(HttpSession session) {
		AdminUser adminUser = (AdminUser) session.getAttribute(ConstantsConfigurer.getProperty("system_key"));
		return adminUser;
	}

	@RequestMapping("/list")
	public ModelAndView list(HttpSession session,
			@RequestParam(value = "productName", required = false) String productName,
			@RequestParam(value = "storeName", required = false) String storeName,
			@RequestParam(value = "startPrice", required = false) BigDecimal startPrice,
			@RequestParam(value = "endPrice", required = false) BigDecimal endPrice,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "status", required = false) Long status,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		
		ModelAndView view = new ModelAndView("system/product/audit_list");
		if (pageNum == null || pageNum < 1)
			pageNum = 1;
		if (pageSize == null || pageSize < 0)
			pageSize = 10;
		if (null == status)
			status = -1L;
		Page<AuditProductVo> page = auditProductService.queryAuditProductList(productName, storeName, startPrice,
				endPrice, startTime, endTime, status, pageNum, pageSize);
		view.addObject("page", page);
		view.addObject("productName", productName);
		view.addObject("storeName", storeName);
		view.addObject("startPrice", startPrice);
		view.addObject("endPrice", endPrice);
		view.addObject("startTime", startTime);
		view.addObject("endTime", endTime);
		view.addObject("status", status);
		return view;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(HttpSession session, HttpServletRequest request, HttpServletResponse response, String id,
			Integer status) throws Exception {
		String[] ids = id.split(",");
		AdminUser admin = getAdminUserSession(session);
		boolean isSuccess = auditProductService.updateAuditProduct(ids, status, admin.getId());
		if (isSuccess) {
			String info = BaseUtil.getLogInfo("审核商品", "ProductInfo", null);
			info = info.replace("null", "");
			for (int i = 0; i < ids.length; i++)
				info += ids[i].toString() + ",";
			String IP = ServletUtils.getIpAddr(request);
			adminLogService.insertAdminLog(admin.getId(), info.substring(0, info.length() - 1), "商品管理", IP);
			HtmlUtil.writerJson(response, "审核成功");
		} else
			HtmlUtil.writerJson(response, "审核失败");
	}
}