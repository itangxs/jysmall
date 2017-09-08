package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.CityInfo;
import cn.qhjys.mall.entity.CityInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CityInfoMapper {
    int countByExample(CityInfoExample example);

    int deleteByExample(CityInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CityInfo record);

    int insertSelective(CityInfo record);

    List<CityInfo> selectByExample(CityInfoExample example);

    CityInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CityInfo record, @Param("example") CityInfoExample example);

    int updateByExample(@Param("record") CityInfo record, @Param("example") CityInfoExample example);

    int updateByPrimaryKeySelective(CityInfo record);

    int updateByPrimaryKey(CityInfo record);
}