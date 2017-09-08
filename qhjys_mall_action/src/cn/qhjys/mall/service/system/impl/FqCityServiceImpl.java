package cn.qhjys.mall.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.FqArea;
import cn.qhjys.mall.entity.FqAreaExample;
import cn.qhjys.mall.entity.FqCity;
import cn.qhjys.mall.entity.FqCityExample;
import cn.qhjys.mall.entity.FqDistrict;
import cn.qhjys.mall.entity.FqDistrictExample;
import cn.qhjys.mall.mapper.FqAreaMapper;
import cn.qhjys.mall.mapper.FqCityMapper;
import cn.qhjys.mall.mapper.FqDistrictMapper;
import cn.qhjys.mall.service.system.FqCityService;

@Service("fqCityService")
public class FqCityServiceImpl implements FqCityService{
	
	@Autowired
	FqCityMapper fqCityMapper;
	@Autowired
	FqDistrictMapper fqDistrictMapper;
	@Autowired
	FqAreaMapper fqAreaMapper;

	@Override
	public List<FqCity> queryCitys() throws Exception {
		FqCityExample example = new FqCityExample();
		return fqCityMapper.selectByExample(example);
	}

	@Override
	public List<FqDistrict> queryDistricts(Long cityId) throws Exception {
		FqDistrictExample example = new FqDistrictExample();
		if (cityId != null) {
			example.createCriteria().andCityIdEqualTo(cityId);
		}
		return fqDistrictMapper.selectByExample(example);
	}

	@Override
	public List<FqArea> queryAreas(Long districtId) throws Exception {
		FqAreaExample example = new FqAreaExample();
		if (districtId != null) {
			example.createCriteria().andDistrictIdEqualTo(districtId);
		}
		return fqAreaMapper.selectByExample(example);
	}

	@Override
	public FqCity queryCityById(Long id) throws Exception {
		if (id == null) {
			return null;
		}
		return fqCityMapper.selectByPrimaryKey(id);
	}

	@Override
	public FqDistrict queryDistrictById(Long id) throws Exception {
		if (id == null) {
			return null;
		}
		return fqDistrictMapper.selectByPrimaryKey(id);
	}

	@Override
	public FqArea queryAreaById(Long id) throws Exception {
		if (id == null) {
			return null;
		}
		return fqAreaMapper.selectByPrimaryKey(id);
	}
	
}
