package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqCity;
import cn.qhjys.mall.entity.FqCityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqCityMapper {
    int countByExample(FqCityExample example);

    int deleteByExample(FqCityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqCity record);

    int insertSelective(FqCity record);

    List<FqCity> selectByExample(FqCityExample example);

    FqCity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqCity record, @Param("example") FqCityExample example);

    int updateByExample(@Param("record") FqCity record, @Param("example") FqCityExample example);

    int updateByPrimaryKeySelective(FqCity record);

    int updateByPrimaryKey(FqCity record);
}