package cn.qhjys.mall.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.qhjys.mall.entity.FqChannelRole;
import cn.qhjys.mall.entity.FqChannelRoleExample;
import cn.qhjys.mall.mapper.FqChannelRoleMapper;
import cn.qhjys.mall.service.FqChannelRoleService;

@Service("fqChannelRoleService")
public class FqChannelRoleServiceImpl implements FqChannelRoleService {
	
	@Autowired
	private FqChannelRoleMapper fqChannelRoleMapper;

	@Override
	public int insertFqChannelRole(FqChannelRole fqChannelRole) {
		return fqChannelRoleMapper.insertSelective(fqChannelRole);
	}

	@Override
	public FqChannelRole getLastFqChannelRole(Integer type) {
		FqChannelRoleExample example2 = new FqChannelRoleExample();
		example2.createCriteria().andTypeEqualTo(type).andEffectiveTimeLessThanOrEqualTo(new Date());
		example2.setOrderByClause(" effective_time desc ");
		PageHelper.startPage(0, 1);
		List<FqChannelRole> roles = fqChannelRoleMapper.selectByExample(example2);
		return roles.size()>0?roles.get(0):null;
	}
	
	

}
