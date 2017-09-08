package cn.qhjys.mall.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.AdminUser;
import cn.qhjys.mall.entity.CmsCategory;
import cn.qhjys.mall.entity.CmsInfo;
import cn.qhjys.mall.service.AdminLogService;
import cn.qhjys.mall.service.system.CmsService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.ServletUtils;
import cn.qhjys.mall.vo.system.CmsInfoVo;
import com.github.pagehelper.Page;

@Controller
@RequestMapping("/managermall/systemmall/cms")
public class CmsController extends Base {
	@Autowired
	private CmsService cmsService;
	@Autowired
	private AdminLogService adminLogService;

	private AdminUser getAdminUserSession(HttpSession session) {
		AdminUser adminUser = (AdminUser) session.getAttribute(ConstantsConfigurer.getProperty("system_key"));
		return adminUser;
	}

	@RequestMapping("/addCms")
	public ModelAndView addCms(HttpSession session, @RequestParam(value = "id", required = false) Long id)
			throws Exception {
		ModelAndView view = new ModelAndView("/system/cms/add_cms");
		List<CmsCategory> list = cmsService.getCmsCategoryEnandle1Yezi1();
		if (null != id) {
			CmsInfo cms = cmsService.getCmsInfoById(id);
			view.addObject("cms", cms);
		}
		view.addObject("cmsCategoryList", list);
		return view;
	}

	@RequestMapping("/saveCms")
	public Object saveCms(HttpSession session, HttpServletRequest request, Long id, String name, String title,
			String keywords, String description, Integer enabled, String content, Long parentId) throws Exception {
		AdminUser admin = getAdminUserSession(session);
		boolean result = cmsService.saveCms(id, parentId, name, title, keywords, description, enabled, content,
				admin.getId());
		if (result) {
			String action = id == null ? "添加" : "修改" + "CMS信息";
			String info = BaseUtil.getLogInfo(action, "CmsInfo", id);
			String IP = ServletUtils.getIpAddr(request);
			adminLogService.insertAdminLog(admin.getId(), info, "CMS管理", IP);
			return super.goUrl("/managermall/systemmall/cms/cmsList.do", "操作成功!");
		} else
			return super.goUrl("/managermall/systemmall/cms/cmsList.do", "操作失败!");
	}

	@RequestMapping("/cmsList")
	public ModelAndView cmsList(HttpSession session,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "parentId", required = false) Long parentId,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "enabled", required = false) Integer enabled) throws Exception {
		ModelAndView view = new ModelAndView("/system/cms/list_cms");
		if (pageNum == null || pageNum < 1)
			pageNum = 1;
		if (pageSize == null || pageSize < 0)
			pageSize = 10;
		if (null != parentId)
			view.addObject("parentId", parentId);
		if (StringUtils.isNotBlank(name))
			view.addObject("name", name);
		if (null != enabled)
			view.addObject("enabled", enabled);
		List<CmsCategory> list = cmsService.getCmsCategoryEnandle1Yezi1();
		view.addObject("cmsCategoryList", list);
		Page<CmsInfoVo> page = cmsService.getCmsInfoVoByPage(pageNum, pageSize, parentId, name, enabled);
		view.addObject("page", page);
		return view;
	}

	@RequestMapping("/addCmsCategory")
	public ModelAndView addCmsCategory(HttpSession session, @RequestParam(value = "id", required = false) Long id)
			throws Exception {
		ModelAndView view = new ModelAndView("/system/cms/add_cms_category");
		List<CmsCategory> list = cmsService.getCmsCategoryEnandle1();
		if (null != id) {
			CmsCategory cmsCategory = cmsService.getCmsCategoryById(id);
			view.addObject("cmsCategory", cmsCategory);
		}
		view.addObject("cmsCategoryList", list);
		return view;
	}

	@RequestMapping("/saveCmsCategory")
	public Object saveCmsCategory(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "fatherId", required = true) Long fatherId,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "paixuhao", required = true) Integer paixuhao,
			@RequestParam(value = "yezi", required = true) Integer yezi,
			@RequestParam(value = "enabled", required = true) Integer enabled) throws Exception {
		AdminUser admin = getAdminUserSession(session);
		boolean result = cmsService.saveCmsCategory(id, fatherId, name, paixuhao, yezi, enabled, admin.getId());
		if (result) {
			String action = id == null ? "添加" : "修改" + "CMS类别";
			String info = BaseUtil.getLogInfo(action, "saveCmsCategory", id);
			String IP = ServletUtils.getIpAddr(request);
			adminLogService.insertAdminLog(admin.getId(), info, "CMS管理", IP);
			return super.goUrl("/managermall/systemmall/cms/cmsListCategory.do", "操作成功!");
		} else
			return super.goUrl("/managermall/systemmall/cms/cmsListCategory.do", "操作失败!");
	}

	@RequestMapping("/cmsListCategory")
	public ModelAndView cmsListCategory(HttpSession session,
			@RequestParam(value = "fatherId", required = false) Long fatherId,
			@RequestParam(value = "enabled", required = false) Integer enabled) throws Exception {
		ModelAndView view = new ModelAndView("/system/cms/list_category");
		List<CmsCategory> list = cmsService.getCmsCategory(fatherId, enabled);
		view.addObject("cmsCategoryList", list);
		if (null != fatherId)
			view.addObject("fatherId", fatherId);
		if (null != enabled)
			view.addObject("enabled", enabled);
		return view;
	}
}