package cn.qhjys.mall.controller.system;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.AdminGroup;
import cn.qhjys.mall.entity.AdminRoleGroup;
import cn.qhjys.mall.entity.AdminUser;
import cn.qhjys.mall.entity.RoleMenu;
import cn.qhjys.mall.service.AdminLogService;
import cn.qhjys.mall.service.system.AdminUserRoleService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.ServletUtils;
import com.github.pagehelper.Page;

@Controller
@RequestMapping("/managermall/systemmall/userrole")
public class AdminUserRoleController extends Base {
	@Autowired
	private AdminUserRoleService adminUserRoleService;
	@Autowired
	private AdminLogService adminLogService;

	private AdminUser getAdminUserSession(HttpSession session) {
		AdminUser adminUser = (AdminUser) session.getAttribute(ConstantsConfigurer.getProperty("system_key"));
		return adminUser;
	}

	@RequestMapping("/addGroup")
	public ModelAndView addGroup(HttpSession session, @RequestParam(value = "id", required = false) Long id)
			throws Exception {
		ModelAndView view = new ModelAndView("/system/adminuser/add_group");
		if (null != id) {
			AdminGroup admin = adminUserRoleService.getAdminGroupById(id);
			view.addObject("adminGroup", admin);
			List<AdminRoleGroup> list3 = adminUserRoleService.getAdminGroupRole(admin.getId());
			view.addObject("adminRoleGroupList", list3);
		}
		List<RoleMenu> list2 = adminUserRoleService.getRoleMenu();
		view.addObject("roleMenuList", list2);
		return view;
	}

	@RequestMapping("/saveGroup")
	public Object saveGroup(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "groupName", required = true) String groupName,
			@RequestParam(value = "roleId", required = false) Long[] roleId) throws Exception {
		AdminUser admin = getAdminUserSession(session);
		boolean result = adminUserRoleService.updateAdminGroup(id, groupName, admin.getId());
		if (result && null != roleId) {
			adminUserRoleService.updateAdminGroupRole(roleId, groupName);
			String action = id == null ? "添加" : "修改" + "后台工作组";
			String info = BaseUtil.getLogInfo(action, "AdminGroup,AdminRoleGroup", id);
			String IP = ServletUtils.getIpAddr(request);
			adminLogService.insertAdminLog(admin.getId(), info, "用户权限", IP);
			return super.goUrl("/managermall/systemmall/userrole/groupList.do", "操作成功");
		}
		return super.goUrl("/managermall/systemmall/userrole/groupList.do", "操作失败");

	}

	@RequestMapping("/groupList")
	public ModelAndView groupList(HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView("/system/adminuser/list_group");
		List<AdminGroup> list2 = adminUserRoleService.getAdminGroup();
		view.addObject("adminGroupList", list2);
		return view;
	}

	@RequestMapping("/addRole")
	public ModelAndView addRole(HttpSession session, @RequestParam(value = "id", required = false) Long id)
			throws Exception {
		ModelAndView view = new ModelAndView("/system/adminuser/add_role");
		if (null != id) {
			RoleMenu admin = adminUserRoleService.getRoleMenuById(id);
			view.addObject("roleMenu", admin);
		}
		return view;
	}

	@RequestMapping("/saveRole")
	public Object saveRole(HttpSession session, HttpServletRequest request,
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "roleText", required = true) String roleText) throws Exception {
		AdminUser admin = getAdminUserSession(session);
		boolean result = adminUserRoleService.updateAdminRoleMenu(id, name, roleText, admin.getId());
		if (result) {
			String action = id == null ? "添加" : "修改" + "角色权限";
			String info = BaseUtil.getLogInfo(action, "RoleMenu", id);
			String IP = ServletUtils.getIpAddr(request);
			adminLogService.insertAdminLog(admin.getId(), info, "用户权限", IP);
			return super.goUrl("/managermall/systemmall/userrole/roleList.do", "操作成功");
		} else
			return super.goUrl("/managermall/systemmall/userrole/roleList.do", "操作失败");
	}

	@RequestMapping("/roleList")
	public ModelAndView roleList(HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView("/system/adminuser/list_role");
		List<RoleMenu> list2 = adminUserRoleService.getRoleMenu();
		view.addObject("roleMenuList", list2);
		return view;
	}

	@RequestMapping("/adminLogs")
	public ModelAndView adminLogs(Integer pageNum, Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("/system/adminuser/adminLogs");
		Page<Map<String, Object>> page = adminLogService.queryAdminLog(pageNum, pageSize);
		view.addObject("page", page);
		return view;
	}
}