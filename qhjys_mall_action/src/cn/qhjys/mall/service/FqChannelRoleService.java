package cn.qhjys.mall.service;

import cn.qhjys.mall.entity.FqChannelRole;

public interface FqChannelRoleService {
	public int insertFqChannelRole(FqChannelRole fqChannelRole);
	
	public FqChannelRole getLastFqChannelRole(Integer type);
}
