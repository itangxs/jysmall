package cn.qhjys.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.ManagerCategory;
import cn.qhjys.mall.mapper.ManagerCategoryMapper;
import cn.qhjys.mall.service.ManagerCategoryService;

@Service("managerCategoryService")
public class ManagerCategoryServiceImpl implements ManagerCategoryService {
	
	@Autowired
	private ManagerCategoryMapper managerCategoryMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return managerCategoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ManagerCategory record) {
		return managerCategoryMapper.insert(record);
	}

	@Override
	public int insertSelective(ManagerCategory record) {
		return managerCategoryMapper.insertSelective(record);
	}

	@Override
	public ManagerCategory selectByPrimaryKey(Long id) {
		return managerCategoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ManagerCategory record) {
		return managerCategoryMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ManagerCategory record) {
		return managerCategoryMapper.updateByPrimaryKey(record);
	}

	@Override
	public ManagerCategory findByName(String name) {
		return managerCategoryMapper.findByName(name).get(0);
	}

}