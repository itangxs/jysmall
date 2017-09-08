package cn.qhjys.mall.service.system;

import java.util.List;
import cn.qhjys.mall.entity.AdminUser;

public interface AdminUserService {

	/**
	 * 得到后台某一会员信息
	 * 
	 * @param id
	 * @return
	 */
	public AdminUser getAdminUserById(Long id) throws Exception;

	/**
	 * 增加或者修改后台用户
	 * 
	 * @param id
	 *            后台用户ID
	 * @param groupId
	 *            用户组ID
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param adminUserId
	 *            操作者ID
	 * @param realname
	 *            该账户的使用者姓名
	 * @return
	 */
	public boolean saveAdminUser(Long id, Long groupId, String username, String password, Long adminUserId,
			String realname) throws Exception;

	/**
	 * 查找符合条件的用户
	 * 
	 * @param username
	 *            用户名
	 * @param realname
	 *            姓名
	 * @param groupId
	 *            用户组
	 * @param enabled
	 *            是否显示
	 * @param adminUserId
	 *            操作者ID
	 * @return
	 */
	public List<AdminUser> queryAdminUser(String username, String realname, Long groupId, Integer enabled,
			Long adminUserId) throws Exception;

	/**
	 * 修改某一个用户的组或者状态
	 * 
	 * @param id
	 *            需要修改的用户ID
	 * @param groupId
	 *            用户组
	 * @param enabled
	 *            是否显示
	 * @param adminUserId
	 *            操作者的ID
	 * @return
	 */
	public boolean updateAdminUser(Long id, Long groupId, Integer enabled, Long adminUserId) throws Exception;

	/**
	 * 后台用户登录
	 * 
	 * @param username
	 * @param password
	 * @param ipAddr
	 * @return
	 */
	public AdminUser updateAdminUserByLogin(String username, String password, String ipAddr) throws Exception;

}
