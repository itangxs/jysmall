package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqDistrict;
import cn.qhjys.mall.entity.FqDistrictExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqDistrictMapper {
    int countByExample(FqDistrictExample example);

    int deleteByExample(FqDistrictExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqDistrict record);

    int insertSelective(FqDistrict record);

    List<FqDistrict> selectByExample(FqDistrictExample example);

    FqDistrict selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqDistrict record, @Param("example") FqDistrictExample example);

    int updateByExample(@Param("record") FqDistrict record, @Param("example") FqDistrictExample example);

    int updateByPrimaryKeySelective(FqDistrict record);

    int updateByPrimaryKey(FqDistrict record);
}