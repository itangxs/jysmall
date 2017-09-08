package cn.qhjys.mall.service.system.impl;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.AdminGroup;
import cn.qhjys.mall.entity.AdminUser;
import cn.qhjys.mall.entity.AdminUserExample;
import cn.qhjys.mall.mapper.AdminUserMapper;
import cn.qhjys.mall.service.system.AdminUserRoleService;
import cn.qhjys.mall.service.system.AdminUserService;
import cn.qhjys.mall.util.EncodeMD5;

@Service
public class AdminUserServiceImpl extends Base implements AdminUserService {
	@Autowired
	private AdminUserMapper adminUserMapper;
	@Autowired
	private AdminUserRoleService adminUserRoleService;

	public AdminUserRoleService getAdminUserRoleService() throws Exception {
		return adminUserRoleService;
	}

	public void setAdminUserRoleService(AdminUserRoleService adminUserRoleService) throws Exception {
		this.adminUserRoleService = adminUserRoleService;
	}

	public AdminUserMapper getAdminUserMapper() throws Exception {
		return adminUserMapper;
	}

	public void setAdminUserMapper(AdminUserMapper adminUserMapper) throws Exception {
		this.adminUserMapper = adminUserMapper;
	}

	@Override
	public AdminUser getAdminUserById(Long id) throws Exception {
		return adminUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean saveAdminUser(Long id, Long groupId, String username, String password, Long adminUserId,
			String realname) throws Exception {
		AdminGroup ag = adminUserRoleService.getAdminGroupById(groupId);
		if (null != ag) {
			AdminUser ad = new AdminUser();
			ad.setUsername(username);
			ad.setPassword(EncodeMD5.GetMD5Code(password));
			ad.setGroupId(groupId);
			ad.setRealname(realname);
			ad.setEnabled(1);
			ad.setAddTime(new Date());
			ad.setLastLogin(new Date());
			int num = 0;
			if (null != id) {
				ad.setId(id);
				num = adminUserMapper.updateByPrimaryKeySelective(ad);
			} else {
				num = adminUserMapper.insertSelective(ad);
			}
			if (num > 0)
				return true;
		}

		return false;
	}

	@Override
	public List<AdminUser> queryAdminUser(String username, String realname, Long groupId, Integer enabled,
			Long adminUserId) throws Exception {
		AdminUserExample example = new AdminUserExample();
		example.clear();
		if (StringUtils.isNotBlank(username))
			example.createCriteria().andUsernameEqualTo(username);
		if (StringUtils.isNotBlank(realname))
			example.createCriteria().andRealnameEqualTo(realname);
		if (null != groupId)
			example.createCriteria().andGroupIdEqualTo(groupId);
		if (null != enabled)
			example.createCriteria().andEnabledEqualTo(enabled);

		return adminUserMapper.selectByExample(example);
	}

	@Override
	public boolean updateAdminUser(Long id, Long groupId, Integer enabled, Long adminUserId) throws Exception {
		AdminUser ad = adminUserMapper.selectByPrimaryKey(id);
		if (null != ad) {
			if (null != groupId && null != adminUserRoleService.getAdminGroupById(groupId))
				ad.setGroupId(groupId);
			if (null != enabled)
				ad.setEnabled(enabled);
			int num = adminUserMapper.updateByPrimaryKeySelective(ad);
			if (num > 0)
				return true;
		}
		return false;
	}

	@Override
	public AdminUser updateAdminUserByLogin(String username, String password, String ipAddr) throws Exception {
		AdminUserExample example = new AdminUserExample();
		example.createCriteria().andUsernameEqualTo(username.trim()).andPasswordEqualTo(EncodeMD5.GetMD5Code(password))
				.andEnabledEqualTo(1);
		List<AdminUser> list = adminUserMapper.selectByExample(example);
		if (list.size() > 0) {
			AdminUser ad = list.get(0);
			ad.setLastIp(ipAddr);
			ad.setLastLogin(new Date());
			adminUserMapper.updateByPrimaryKeySelective(ad);
			return ad;
		}

		return null;
	}
}
