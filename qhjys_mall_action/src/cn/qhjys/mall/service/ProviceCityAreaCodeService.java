package cn.qhjys.mall.service;

import cn.qhjys.mall.entity.ProviceCityAreaCode;

/**
 *  
 * @author huangsy
 *
 */
public interface ProviceCityAreaCodeService {
	
	int deleteByPrimaryKey(Long id);

    int insert(ProviceCityAreaCode record);

    int insertSelective(ProviceCityAreaCode record);

    ProviceCityAreaCode selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProviceCityAreaCode record);

    int updateByPrimaryKey(ProviceCityAreaCode record);
    
    ProviceCityAreaCode findByProviceNameAndCode(String name, String areaParentId);
    
    

}