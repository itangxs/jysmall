package cn.qhjys.mall.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.ProviceCityAreaCode;
import cn.qhjys.mall.mapper.ProviceCityAreaCodeMapper;
import cn.qhjys.mall.service.ProviceCityAreaCodeService;

@Service("proviceCityAreaCodeService")
public class ProviceCityAreaCodeServiceImpl implements ProviceCityAreaCodeService {
	
	@Autowired
	ProviceCityAreaCodeMapper proviceCityAreaCodeMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return proviceCityAreaCodeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ProviceCityAreaCode record) {
		return proviceCityAreaCodeMapper.insert(record);
	}

	@Override
	public int insertSelective(ProviceCityAreaCode record) {
		return proviceCityAreaCodeMapper.insertSelective(record);
	}

	@Override
	public ProviceCityAreaCode selectByPrimaryKey(Long id) {
		return proviceCityAreaCodeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ProviceCityAreaCode record) {
		return proviceCityAreaCodeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ProviceCityAreaCode record) {
		return proviceCityAreaCodeMapper.updateByPrimaryKey(record);
	}

	@Override
	public ProviceCityAreaCode findByProviceNameAndCode(String name,
			String areaParentId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("areaName", name);
		map.put("areaParentId", areaParentId);
		return proviceCityAreaCodeMapper.findByProviceNameAndCode(map);
	}
	

}