package cn.qhjys.mall.service;

import cn.qhjys.mall.entity.FqPushInfo;

import com.github.pagehelper.Page;

public interface FqPushInfoService {
	public Page<FqPushInfo> queryFqPushInfo(Long sellerId,Integer pageNum,Integer pageSize,String loginDate,Integer type);
	public int insertFqPushInfo(FqPushInfo fqPushInfo);
	public int insertSystemFqPushInfo(FqPushInfo fqPushInfo);
	public int insertWelcomeFqPushInfo(FqPushInfo fqPushInfo);
	public Page<FqPushInfo> queryFqPushInfo(Integer type,Integer pageNum,Integer pageSize);
}
