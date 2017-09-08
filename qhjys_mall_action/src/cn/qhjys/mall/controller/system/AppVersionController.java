package cn.qhjys.mall.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.AdminUser;
import cn.qhjys.mall.entity.AppVersion;
import cn.qhjys.mall.service.AdminLogService;
import cn.qhjys.mall.service.system.AppVersionService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.ServletUtils;
import com.github.pagehelper.Page;

@Controller
@RequestMapping("/managermall/systemmall/app")
public class AppVersionController extends Base {
	@Autowired
	private AppVersionService appVersionService;
	@Autowired
	private AdminLogService adminLogService;

	private AdminUser getAdminUserSession(HttpSession session) {
		AdminUser adminUser = (AdminUser) session.getAttribute(ConstantsConfigurer.getProperty("system_key"));
		return adminUser;
	}

	/**
	 * 跳转添加APP版本页面
	 * 
	 * @param session
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addAppVersion")
	public ModelAndView addAppVersion(HttpSession session, @RequestParam(value = "id", required = false) Long id)
			throws Exception {
		ModelAndView view = new ModelAndView("/system/app/add_app_version");
		return view;
	}

	/**
	 * 保存APP版本
	 * 
	 * @param id
	 * @param versionCode
	 *            版本号
	 * @param versionName
	 *            版本名称
	 * @param downloadUrl
	 *            下载地址
	 * @param content
	 *            版本简介
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveAppVersion")
	public Object saveAppVersion(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "versionCode", required = false) Long versionCode,
			@RequestParam(value = "versionName", required = false) String versionName,
			@RequestParam(value = "downloadUrl", required = false) String downloadUrl,
			@RequestParam(value = "content", required = false) String content) throws Exception {
		int status = appVersionService.saveAppVersion(id, versionCode, versionName, downloadUrl, content);
		if (status == 1) {
			AdminUser admin = getAdminUserSession(session);
			String info = BaseUtil.getLogInfo("添加APP新版本", "saveAppVersion", id);
			String IP = ServletUtils.getIpAddr(request);
			adminLogService.insertAdminLog(admin.getId(), info, "CMS管理", IP);
			return super.goUrl("/managermall/systemmall/app/appVersionPage.do", "操作成功!");
		} else if (status == -1) {
			return super.goUrl("/managermall/systemmall/app/appVersionPage.do", "操作失败,版本号低于最新版本!");
		}
		return super.goUrl("/managermall/systemmall/app/appVersionPage.do", "操作失败!");
	}

	/**
	 * 分页查询APP版本列表
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/appVersionPage")
	public ModelAndView appVersionPage(@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("/system/app/app_version_list");
		if (null == pageNum || pageNum < 1)
			pageNum = 1;
		pageSize = 10;
		Page<AppVersion> appVersionPage = appVersionService.queryAppVersionPage(pageNum, pageSize);
		view.addObject("page", appVersionPage);
		view.addObject("pageNum", pageNum);
		return view;
	}
}