package cn.qhjys.mall.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.qhjys.mall.entity.AreaInfo;
import cn.qhjys.mall.entity.AreaInfoExample;
import cn.qhjys.mall.mapper.AreaInfoMapper;
import cn.qhjys.mall.service.AreaInfoService;

/**
 * @author LiXiang
 * @date 2015-05-16 11:05:09
 */
@Service
public class AreaInfoServiceImpl implements AreaInfoService {
	@Autowired
	private AreaInfoMapper areaInfoMapper;

	@Override
	public List<AreaInfo> selectAreaByCity(Long cityId) throws Exception {
		if (cityId == null)
			return new ArrayList<AreaInfo>();
		AreaInfoExample example = new AreaInfoExample();
		example.createCriteria().andCityIdEqualTo(cityId).equals(1);
		List<AreaInfo> list = areaInfoMapper.selectByExample(example);
		return list;
	}
}