package cn.qhjys.mall.service.system;

import java.util.List;

import cn.qhjys.mall.entity.FqIndustry;

public interface FqIndustryService {

	List<FqIndustry> queryIndustryDetail(Long parentId) throws Exception;
	
	FqIndustry queryIndustryById(Long id) throws Exception;
}
