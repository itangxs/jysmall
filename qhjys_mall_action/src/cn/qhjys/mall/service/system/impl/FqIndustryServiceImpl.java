package cn.qhjys.mall.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.FqIndustry;
import cn.qhjys.mall.entity.FqIndustryExample;
import cn.qhjys.mall.mapper.FqIndustryMapper;
import cn.qhjys.mall.service.system.FqIndustryService;

@Service("fqIndustryService")
public class FqIndustryServiceImpl implements FqIndustryService{

	@Autowired
	FqIndustryMapper fqIndustryMapper;

	@Override
	public List<FqIndustry> queryIndustryDetail(Long parentId) throws Exception {
		FqIndustryExample example = new FqIndustryExample();
		if (parentId != null) {
			example.createCriteria().andParentIdEqualTo(parentId);
		}
		return fqIndustryMapper.selectByExample(example);
	}

	@Override
	public FqIndustry queryIndustryById(Long id) throws Exception {
		if (id == null) {
			return null;
		}
		return fqIndustryMapper.selectByPrimaryKey(id);
	}
}
