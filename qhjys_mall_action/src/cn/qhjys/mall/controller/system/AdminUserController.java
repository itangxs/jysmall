package cn.qhjys.mall.controller.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.AccessToken;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.AdminGroup;
import cn.qhjys.mall.entity.AdminUser;
import cn.qhjys.mall.service.AdminLogService;
import cn.qhjys.mall.service.system.AdminUserRoleService;
import cn.qhjys.mall.service.system.AdminUserService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.util.ServletUtils;

@Controller
@RequestMapping("/managermall/systemmall/user")
public class AdminUserController extends Base {
	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private AdminUserRoleService adminUserRoleService;
	@Autowired
	private AdminLogService adminLogService;

	private AdminUser getAdminUserSession(HttpSession session) {
		AdminUser adminUser = (AdminUser) session.getAttribute(ConstantsConfigurer.getProperty("system_key"));
		return adminUser;
	}

	/**
	 * 增加或者修改后台用户信息
	 * 
	 * @param session
	 * @param id
	 *            被修改这的ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addUser")
	public ModelAndView addUser(HttpSession session, @RequestParam(value = "id", required = false) Long id)
			throws Exception {
		ModelAndView view = new ModelAndView("/system/adminuser/add_user");
		if (null != id) {
			AdminUser admin = adminUserService.getAdminUserById(id);
			view.addObject("adminUser", admin);
		}
		List<AdminGroup> list = adminUserRoleService.getAdminGroup();
		view.addObject("adminGroupList", list);
		return view;
	}

	/**
	 * 修改后台会员信息
	 * 
	 * @param session
	 * @param id
	 * @param username
	 * @param password
	 * @param password1
	 * @param realname
	 * @param groupId
	 * @throws Exception
	 */
	@RequestMapping("/saveUser")
	public Object saveUser(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password,
			@RequestParam(value = "password1", required = true) String password1,
			@RequestParam(value = "realname", required = true) String realname,
			@RequestParam(value = "groupId", required = true) Long groupId) throws Exception {
		if (password.equals(password1) && password.length() >= 5) {
			AdminUser admin = getAdminUserSession(session);
			boolean result = adminUserService.saveAdminUser(id, groupId, username, password, admin.getId(), realname);
			if (result) {
				String info = BaseUtil.getLogInfo("修改账户信息", "AdminUser", id);
				String IP = ServletUtils.getIpAddr(request);
				adminLogService.insertAdminLog(admin.getId(), info, "用户权限", IP);
				return super.goUrl("/managermall/systemmall/user/userList.do", "操作成功!");
			}
		}
		return super.goUrl("/managermall/systemmall/user/userList.do", "操作失败!");
	}

	/**
	 * 后台用户列表
	 * 
	 * @param session
	 * @param pageNum
	 * @param pageSize
	 * @param username
	 * @param realname
	 * @param groupId
	 * @param enabled
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userList")
	public ModelAndView userList(HttpSession session,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "realname", required = false) String realname,
			@RequestParam(value = "groupId", required = false) Long groupId,
			@RequestParam(value = "enabled", required = false) Integer enabled) throws Exception {
		ModelAndView view = new ModelAndView("/system/adminuser/list_user");
		if (pageNum == null || pageNum < 1)
			pageNum = 1;
		if (pageSize == null || pageSize < 0)
			pageSize = 10;
		if (StringUtils.isNotBlank(realname))
			view.addObject("realname", realname);
		if (StringUtils.isNotBlank(username))
			view.addObject("username", username);
		if (null != groupId)
			view.addObject("groupId", groupId);
		if (null != enabled)
			view.addObject("enabled", enabled);
		List<AdminUser> list = adminUserService.queryAdminUser(username, realname, groupId, enabled,
				getAdminUserSession(session).getId());
		List<AdminGroup> list2 = adminUserRoleService.getAdminGroup();
		view.addObject("adminGroupList", list2);
		view.addObject("adminUserList", list);
		return view;
	}

	/**
	 * 修改后台用户所在工作组
	 * 
	 * @param id
	 *            被修改者
	 * @param valuezhi
	 *            工作组ID
	 * @throws Exception
	 */
	@RequestMapping("/adminUserGroupAjax")
	public void adminUserGroupAjax(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = true) Long id,
			@RequestParam(value = "valuezhi", required = true) Long valuezhi) throws Exception {
		AdminUser admin = getAdminUserSession(session);
		boolean result = adminUserService.updateAdminUser(id, valuezhi, null, getAdminUserSession(session).getId());
		if (result) {
			String info = BaseUtil.getLogInfo("修改账户所在组", "AdminUser", id);
			String IP = ServletUtils.getIpAddr(request);
			adminLogService.insertAdminLog(admin.getId(), info, "用户权限", IP);
			HtmlUtil.writerJson(response, "sucess");
		} else {
			HtmlUtil.writerJson(response, "error");
		}

	}

	/**
	 * 修改后台某一个用户的启用
	 * 
	 * @param session
	 * @param response
	 * @param id
	 *            被修改者
	 * @param valuezhi
	 *            是否启用
	 * @throws Exception
	 */
	@RequestMapping("/adminUserEnabledAjax")
	public void adminUserEnabledAjax(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = true) Long id,
			@RequestParam(value = "valuezhi", required = true) Integer valuezhi) throws Exception {
		AdminUser admin = getAdminUserSession(session);
		boolean result = adminUserService.updateAdminUser(id, null, valuezhi, getAdminUserSession(session).getId());
		if (result) {
			String action = valuezhi == 1 ? "启用" : "禁用" + "后台用户!";
			String info = BaseUtil.getLogInfo(action, "AdminUser", id);
			String IP = ServletUtils.getIpAddr(request);
			adminLogService.insertAdminLog(admin.getId(), info, "用户权限", IP);
			HtmlUtil.writerJson(response, "sucess");
		} else
			HtmlUtil.writerJson(response, "error");
	}
	@RequestMapping("/getAccessToken")
	@ResponseBody
	public String getAccessToken(){
		return AccessToken.getAccessToken();
	}
	@RequestMapping("/updateAccessToken")
	@ResponseBody
	public String updateAccessToken(){
		 AccessToken.updateAccessToken();
		 return AccessToken.getAccessToken();
	}
}