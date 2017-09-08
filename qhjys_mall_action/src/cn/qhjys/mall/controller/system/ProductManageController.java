package cn.qhjys.mall.controller.system;

import java.math.BigDecimal;
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
import cn.qhjys.mall.entity.AdminUser;
import cn.qhjys.mall.entity.ProductInfo;
import cn.qhjys.mall.service.AdminLogService;
import cn.qhjys.mall.service.system.ProductManageService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.util.ServletUtils;
import cn.qhjys.mall.vo.system.ProductManageVo;
import com.github.pagehelper.Page;

/**
 * 系统后台商品审核
 */
@Controller
@RequestMapping("/managermall/systemmall/product")
public class ProductManageController extends Base {
	@Autowired
	private ProductManageService productManageService;
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
			@RequestParam(value = "startPrice", required = false) String startPrice,
			@RequestParam(value = "endPrice", required = false) String endPrice,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "enabled", required = false) Long enabled,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		
		ModelAndView view = new ModelAndView("system/product/manage_list");
		if (pageNum == null || pageNum < 1)
			pageNum = 1;
		if (pageSize == null || pageSize < 0)
			pageSize = 10;
		if (null == enabled)
			enabled = -1L;
		Page<ProductManageVo> page = productManageService.queryManageProductList(productName, storeName, StringUtils
				.isEmpty(startPrice) ? null : new BigDecimal(startPrice), StringUtils.isEmpty(endPrice) ? null
				: new BigDecimal(endPrice), startTime, endTime, enabled, pageNum, pageSize);
		view.addObject("page", page);
		view.addObject("productName", productName);
		view.addObject("storeName", storeName);
		view.addObject("startPrice", startPrice);
		view.addObject("endPrice", endPrice);
		view.addObject("startTime", startTime);
		view.addObject("endTime", endTime);
		view.addObject("enabled", enabled);
		return view;
	}

	@RequestMapping("/getProdTitle")
	public ModelAndView getProdTitle(Long prodId) throws Exception {
		ModelAndView view = new ModelAndView("system/product/prod_update");
		ProductInfo info = productManageService.getProductById(prodId);
		view.addObject("info", info);
		return view;
	}

	@RequestMapping("/updateProdTitle")
	public Object updateProdTitle(HttpServletResponse response, Long prodId,Integer level, String keywords, String description)
			throws Exception {
		boolean result = productManageService.updateProductTitleById(prodId,level, keywords, description);
		if (result)
			return super.goUrl("/managermall/systemmall/product/list.do", "修改成功!");
		else
			return super.goUrl("/managermall/systemmall/product/getProdTitle.do?prodId=" + prodId, "修改失败!");
	}

	@RequestMapping("/update")
	public void update(HttpSession session, HttpServletRequest request, HttpServletResponse response, String id,
			Integer enabled) throws Exception {
		String[] ids = id.split(",");
		boolean isSuccess = productManageService.updateProduct(enabled, ids);
		if (isSuccess) {
			AdminUser admin = getAdminUserSession(session);
			String action = enabled == 1 ? "启售" : "禁售" + "商品";
			String info = BaseUtil.getLogInfo(action, "ProductInfo", null);
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
