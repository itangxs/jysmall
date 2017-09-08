package cn.qhjys.mall.mapper;

import java.util.Map;

import cn.qhjys.mall.entity.ProviceCityAreaCode;

public interface ProviceCityAreaCodeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProviceCityAreaCode record);

    int insertSelective(ProviceCityAreaCode record);

    ProviceCityAreaCode selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProviceCityAreaCode record);

    int updateByPrimaryKey(ProviceCityAreaCode record);
    
    ProviceCityAreaCode findByProviceNameAndCode(Map<String, Object> map);
}