package cn.qhjys.mall.service.system;

import java.util.List;
import cn.qhjys.mall.entity.AdminGroup;
import cn.qhjys.mall.entity.AdminRoleGroup;
import cn.qhjys.mall.entity.RoleMenu;

public interface AdminUserRoleService {

	/**
	 * 得到后台会员组
	 * 
	 * @return
	 */
	public List<AdminGroup> getAdminGroup() throws Exception;

	/**
	 * 得到具体的某一个组
	 * 
	 * @param groupId
	 * @return
	 */
	public AdminGroup getAdminGroupById(Long groupId) throws Exception;

	/**
	 * 修改或者增加用户组信息
	 * 
	 * @param id
	 * @param groupName
	 * @param adminUserId
	 *            操作者ID
	 * @return
	 */
	public boolean updateAdminGroup(Long id, String groupName, Long adminUserId) throws Exception;

	/**
	 * 得到权限菜单URL
	 * 
	 * @return
	 */
	public List<RoleMenu> getRoleMenu() throws Exception;

	/**
	 * 更新某一权限
	 * 
	 * @param id
	 *            权限ID
	 * @param name
	 *            权限菜单名称
	 * @param roleText
	 *            权限对应URL
	 * @param adminUserId
	 *            操作者ID
	 * @return
	 */
	public boolean updateAdminRoleMenu(Long id, String name, String roleText, Long adminUserId) throws Exception;

	/**
	 * 得到某一组对应的权限集合
	 * 
	 * @param id
	 * @return
	 */
	public List<AdminRoleGroup> getAdminGroupRole(Long id) throws Exception;

	/**
	 * 更新某一组下面的权限
	 * 
	 * @param roleId
	 * @param groupName
	 */
	public void updateAdminGroupRole(Long[] roleId, String groupName) throws Exception;

	/**
	 * 得到某一个组下面的权限
	 * 
	 * @param groupId
	 * @return
	 */
	public List<RoleMenu> getRoleMenuByGroup(Long groupId) throws Exception;

	/**
	 * 得到具体的某一个权限
	 * 
	 * @param id
	 * @return
	 */
	public RoleMenu getRoleMenuById(Long id) throws Exception;

}
