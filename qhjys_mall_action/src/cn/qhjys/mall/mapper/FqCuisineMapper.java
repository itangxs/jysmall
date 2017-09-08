package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqCuisine;
import cn.qhjys.mall.entity.FqCuisineExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqCuisineMapper {
    int countByExample(FqCuisineExample example);

    int deleteByExample(FqCuisineExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqCuisine record);

    int insertSelective(FqCuisine record);

    List<FqCuisine> selectByExample(FqCuisineExample example);

    FqCuisine selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqCuisine record, @Param("example") FqCuisineExample example);

    int updateByExample(@Param("record") FqCuisine record, @Param("example") FqCuisineExample example);

    int updateByPrimaryKeySelective(FqCuisine record);

    int updateByPrimaryKey(FqCuisine record);
}