package cn.qhjys.mall.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.qhjys.mall.entity.AreaInfo;
import cn.qhjys.mall.entity.AreaInfoExample;
import cn.qhjys.mall.entity.CityInfo;
import cn.qhjys.mall.entity.CityInfoExample;
import cn.qhjys.mall.entity.ProvinceInfo;
import cn.qhjys.mall.entity.ProvinceInfoExample;
import cn.qhjys.mall.mapper.AreaInfoMapper;
import cn.qhjys.mall.mapper.CityInfoMapper;
import cn.qhjys.mall.mapper.ProvinceInfoMapper;
import cn.qhjys.mall.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private ProvinceInfoMapper provinceInfoMapper;
	@Autowired
	private CityInfoMapper cityInfoMapper;
	@Autowired
	private AreaInfoMapper areaInfoMapper;

	@Override
	public List<ProvinceInfo> queryProvinceInfo() throws Exception {
		ProvinceInfoExample example = new ProvinceInfoExample();
		example.createCriteria().andEnabledEqualTo(1);
		return provinceInfoMapper.selectByExample(example);
	}

	@Override
	public List<CityInfo> queryCityInfo(Long provinceId) throws Exception {
		CityInfoExample example = new CityInfoExample();
		example.createCriteria().andEnabledEqualTo(1).andProvinceIdEqualTo(provinceId);
		return cityInfoMapper.selectByExample(example);
	}

	@Override
	public List<AreaInfo> queryAreaInfo(Long cityId) throws Exception {
		AreaInfoExample example = new AreaInfoExample();
		example.createCriteria().andEnabledEqualTo(1).andCityIdEqualTo(cityId);
		return areaInfoMapper.selectByExample(example);
	}

	@Override
	public List<CityInfo> queryCityInfo() throws Exception {
		CityInfoExample example = new CityInfoExample();
		return cityInfoMapper.selectByExample(example);
	}

	@Override
	public List<AreaInfo> queryAreaInfo() throws Exception {
		AreaInfoExample example = new AreaInfoExample();
		return areaInfoMapper.selectByExample(example);
	}

	@Override
	public CityInfo getCityInfoById(Long cityId) throws Exception {
		return cityInfoMapper.selectByPrimaryKey(cityId);
	}

	@Override
	public ProvinceInfo getProvinceInfoById(Long provinceId) throws Exception {
		return provinceInfoMapper.selectByPrimaryKey(provinceId);
	}

	@Override
	public AreaInfo getAreaInfoById(Long areaId) throws Exception {
		return areaInfoMapper.selectByPrimaryKey(areaId);
	}
}