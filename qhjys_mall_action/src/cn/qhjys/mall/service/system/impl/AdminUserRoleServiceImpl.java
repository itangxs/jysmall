package cn.qhjys.mall.service.system.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.AdminGroup;
import cn.qhjys.mall.entity.AdminGroupExample;
import cn.qhjys.mall.entity.AdminRoleGroup;
import cn.qhjys.mall.entity.AdminRoleGroupExample;
import cn.qhjys.mall.entity.RoleMenu;
import cn.qhjys.mall.entity.RoleMenuExample;
import cn.qhjys.mall.mapper.AdminGroupMapper;
import cn.qhjys.mall.mapper.AdminRoleGroupMapper;
import cn.qhjys.mall.mapper.RoleMenuMapper;
import cn.qhjys.mall.service.system.AdminUserRoleService;

@Service
public class AdminUserRoleServiceImpl extends Base implements AdminUserRoleService {
	@Autowired
	private AdminRoleGroupMapper adminRoleGroupMapper;
	@Autowired
	private RoleMenuMapper roleMenuMapper;
	@Autowired
	private AdminGroupMapper adminGroupMapper;

	public AdminGroupMapper getAdminGroupMapper() throws Exception {
		return adminGroupMapper;
	}

	public void setAdminGroupMapper(AdminGroupMapper adminGroupMapper) throws Exception {
		this.adminGroupMapper = adminGroupMapper;
	}

	public AdminRoleGroupMapper getAdminRoleGroupMapper() throws Exception {
		return adminRoleGroupMapper;
	}

	public void setAdminRoleGroupMapper(AdminRoleGroupMapper adminRoleGroupMapper) throws Exception {
		this.adminRoleGroupMapper = adminRoleGroupMapper;
	}

	public RoleMenuMapper getRoleMenuMapper() throws Exception {
		return roleMenuMapper;
	}

	public void setRoleMenuMapper(RoleMenuMapper roleMenuMapper) throws Exception {
		this.roleMenuMapper = roleMenuMapper;
	}

	@Override
	public List<AdminGroup> getAdminGroup() throws Exception {
		AdminGroupExample example = new AdminGroupExample();
		return adminGroupMapper.selectByExample(example);
	}

	@Override
	public AdminGroup getAdminGroupById(Long groupId) throws Exception {
		return adminGroupMapper.selectByPrimaryKey(groupId);
	}

	@Override
	public boolean updateAdminGroup(Long id, String groupName, Long adminUserId) throws Exception {
		AdminGroup ag = new AdminGroup();
		ag.setGroupName(groupName.trim());
		ag.setCreateTime(new Date());
		int num = 0;
		if (null != id) {
			ag.setId(id);
			num = adminGroupMapper.updateByPrimaryKeySelective(ag);
		} else
			num = adminGroupMapper.insertSelective(ag);
		id = ag.getId();
		if (num > 0)
			return true;
		return false;
	}

	@Override
	public List<RoleMenu> getRoleMenu() throws Exception {
		RoleMenuExample example = new RoleMenuExample();
		return roleMenuMapper.selectByExample(example);
	}

	@Override
	public boolean updateAdminRoleMenu(Long id, String name, String roleText, Long adminUserId) throws Exception {
		RoleMenu ag = new RoleMenu();
		ag.setCreateTime(new Date());
		ag.setName(name);
		ag.setRoleText(roleText);
		int num = 0;
		if (null != id) {
			ag.setId(id);
			num = roleMenuMapper.updateByPrimaryKeySelective(ag);
		} else
			num = roleMenuMapper.insertSelective(ag);
		id = ag.getId();
		if (num > 0)
			return true;
		return false;
	}

	@Override
	public List<AdminRoleGroup> getAdminGroupRole(Long id) throws Exception {
		AdminRoleGroupExample example = new AdminRoleGroupExample();
		example.clear();
		example.createCriteria().andGroupIdEqualTo(id);
		return adminRoleGroupMapper.selectByExample(example);
	}

	@Override
	public void updateAdminGroupRole(Long[] roleId, String groupName) throws Exception {
		AdminGroupExample example = new AdminGroupExample();
		example.createCriteria().andGroupNameEqualTo(groupName.trim());
		List<AdminGroup> list = adminGroupMapper.selectByExample(example);
		if (list.size() > 0) {
			AdminGroup ag = list.get(0);
			AdminRoleGroupExample example1 = new AdminRoleGroupExample();
			example1.createCriteria().andGroupIdEqualTo(ag.getId());
			adminRoleGroupMapper.deleteByExample(example1);
			for (Long role : roleId) {
				AdminRoleGroup record = new AdminRoleGroup();
				record.setGroupId(ag.getId());
				record.setRoleId(role);
				adminRoleGroupMapper.insertSelective(record);
			}
		}
	}

	@Override
	public List<RoleMenu> getRoleMenuByGroup(Long groupId) throws Exception {
		AdminRoleGroupExample example = new AdminRoleGroupExample();
		example.createCriteria().andGroupIdEqualTo(groupId);
		List<AdminRoleGroup> list = adminRoleGroupMapper.selectByExample(example);
		List<Long> oba = new ArrayList<Long>();
		for (AdminRoleGroup arg : list) {
			oba.add(arg.getRoleId());
		}
		RoleMenuExample example1 = new RoleMenuExample();
		example1.createCriteria().andIdIn(oba);
		List<RoleMenu> list3 = roleMenuMapper.selectByExample(example1);
		return list3;
	}

	@Override
	public RoleMenu getRoleMenuById(Long id) throws Exception {
		return roleMenuMapper.selectByPrimaryKey(id);
	}

}