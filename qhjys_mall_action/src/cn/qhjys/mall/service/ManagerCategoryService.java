package cn.qhjys.mall.service;

import cn.qhjys.mall.entity.ManagerCategory;

/**
 *  
 * @author huangsy
 *
 */
public interface ManagerCategoryService {
	
	int deleteByPrimaryKey(Long id);

    int insert(ManagerCategory record);

    int insertSelective(ManagerCategory record);

    ManagerCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ManagerCategory record);

    int updateByPrimaryKey(ManagerCategory record);
    
    ManagerCategory findByName(String name);
    
    

}