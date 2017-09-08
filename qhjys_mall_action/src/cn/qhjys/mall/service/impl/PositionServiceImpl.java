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
import cn.qhjys.mall.service.PositionService;

@Service
public class PositionServiceImpl implements PositionService {
	@Autowired
	private ProvinceInfoMapper provinceInfoMapper;
	@Autowired
	private CityInfoMapper cityInfoMapper;
	@Autowired
	private AreaInfoMapper areaInfoMapper;

	@Override
	public List<ProvinceInfo> queryProvince() throws Exception {
		ProvinceInfoExample example = new ProvinceInfoExample();
		example.createCriteria().andEnabledEqualTo(1);
		return provinceInfoMapper.selectByExample(example);
	}

	@Override
	public List<CityInfo> queryCityByProvince(Long provId) throws Exception {
		CityInfoExample example = new CityInfoExample();
		cn.qhjys.mall.entity.CityInfoExample.Criteria crit = example.createCriteria();
		if (provId != null)
			crit.andProvinceIdEqualTo(provId);
		crit.andEnabledEqualTo(1);
		return cityInfoMapper.selectByExample(example);
	}

	@Override
	public List<AreaInfo> queryAreaByCity(Long cityId) throws Exception {
		AreaInfoExample example = new AreaInfoExample();
		cn.qhjys.mall.entity.AreaInfoExample.Criteria crit = example.createCriteria();
		if (cityId != null)
			crit.andCityIdEqualTo(cityId);
		crit.andEnabledEqualTo(1);
		List<AreaInfo> list = areaInfoMapper.selectByExample(example);
		return list;
	}
}