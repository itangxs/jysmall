package cn.qhjys.mall.mapper;

import java.util.List;

import cn.qhjys.mall.entity.ManagerCategory;

public interface ManagerCategoryMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(ManagerCategory record);

    int insertSelective(ManagerCategory record);

    ManagerCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ManagerCategory record);

    int updateByPrimaryKey(ManagerCategory record);
    
    List<ManagerCategory> findByName(String name);
}