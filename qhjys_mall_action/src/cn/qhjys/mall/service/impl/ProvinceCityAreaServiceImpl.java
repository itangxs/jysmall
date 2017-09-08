package cn.qhjys.mall.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.AreaInfo;
import cn.qhjys.mall.entity.AreaInfoExample;
import cn.qhjys.mall.entity.CityInfo;
import cn.qhjys.mall.entity.CityInfoExample;
import cn.qhjys.mall.entity.ProvinceInfo;
import cn.qhjys.mall.mapper.AreaInfoMapper;
import cn.qhjys.mall.mapper.CityInfoMapper;
import cn.qhjys.mall.mapper.ProvinceInfoMapper;
import cn.qhjys.mall.service.ProvinceCityAreaService;

@Service
public class ProvinceCityAreaServiceImpl extends Base implements ProvinceCityAreaService {
	@Autowired
	private ProvinceInfoMapper provinceInfoMapper;
	@Autowired
	private CityInfoMapper cityInfoMapper;
	@Autowired
	private AreaInfoMapper areaInfoMapper;

	@Override
	public CityInfo getCityInfoById(Long id) throws Exception {
		return cityInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<CityInfo> getCityInfoByProvinceInfoId(Long provinceInfoid) throws Exception {
		CityInfoExample example = new CityInfoExample();
		example.createCriteria().andProvinceIdEqualTo(provinceInfoid);
		return cityInfoMapper.selectByExample(example);
	}

	@Override
	public ProvinceInfo getProvinceInfoById(Long id) throws Exception {
		return provinceInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public AreaInfo getAreaInfoById(Long id) throws Exception {
		return areaInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<AreaInfo> getAreaInfoByCityInfoId(Long cityInfoid) throws Exception {
		AreaInfoExample example = new AreaInfoExample();
		example.createCriteria().andCityIdEqualTo(cityInfoid);
		return areaInfoMapper.selectByExample(example);
	}

}