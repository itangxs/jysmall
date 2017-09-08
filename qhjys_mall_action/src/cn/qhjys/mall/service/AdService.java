package cn.qhjys.mall.service;

import java.util.List;

import com.github.pagehelper.Page;

import cn.qhjys.mall.entity.AdInfo;
import cn.qhjys.mall.entity.AdPosition;

public interface AdService {
	public List<AdPosition> listAdPositions();
	public int insertAdInfo(AdInfo adInfo);
	public int deleteAdInfoById(Long id);
	public AdInfo getAdInfoByAp(Long apId,Long cityId);
	public List<AdInfo> listAdInfoByAp(Long apId,Long cid,int page,int pageSize);
	public Page<AdInfo> listAdInfo(Long cityId,Long apId, int page,int pageSize);
}
