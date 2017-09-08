package cn.qhjys.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.CommonInfo;
import cn.qhjys.mall.entity.CommonInfoExample;
import cn.qhjys.mall.mapper.CommonInfoMapper;
import cn.qhjys.mall.service.CommonInfoService;

@Service("commonInfoService")
public class CommonInfoServiceImpl implements CommonInfoService {

	@Autowired
	private CommonInfoMapper commonInfoMapper;
	
	@Override
	public String getValueById(Long id) {
		CommonInfo info = commonInfoMapper.selectByPrimaryKey(id);
		return info == null ? null : info.getJvalue();
	}

	@Override
	public String getValueBykey(String key) {
		CommonInfoExample example = new CommonInfoExample();
		example.createCriteria().andJkeyEqualTo(key);
		List<CommonInfo> list = commonInfoMapper.selectByExample(example);
		return list.size()>0?list.get(0).getJvalue():null;
	}

}
