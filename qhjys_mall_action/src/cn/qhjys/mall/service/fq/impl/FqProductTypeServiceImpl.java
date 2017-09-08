package cn.qhjys.mall.service.fq.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.FqProductType;
import cn.qhjys.mall.entity.FqProductTypeExample;
import cn.qhjys.mall.entity.FqProductTypeExample.Criteria;
import cn.qhjys.mall.mapper.FqProductTypeMapper;
import cn.qhjys.mall.service.fq.FqProductTypeService;

@Service("fqProductTypeService")
public class FqProductTypeServiceImpl implements FqProductTypeService{

	@Autowired
	private FqProductTypeMapper fqProductTypeMapper;
	
	@Override
	public List<FqProductType> queryEnableProductTypeByStoreId(Long storeId) {
		FqProductTypeExample example = new FqProductTypeExample();
		Criteria criteria = example.createCriteria();
		criteria.andStoreIdEqualTo(storeId);
		criteria.andEnableEqualTo(1);
		example.setOrderByClause(" level desc ");
		return fqProductTypeMapper.selectByExample(example);
	}

}
