package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqRandom;
import cn.qhjys.mall.entity.FqRandomExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqRandomMapper {
    int countByExample(FqRandomExample example);

    int deleteByExample(FqRandomExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqRandom record);

    int insertSelective(FqRandom record);

    List<FqRandom> selectByExample(FqRandomExample example);

    FqRandom selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqRandom record, @Param("example") FqRandomExample example);

    int updateByExample(@Param("record") FqRandom record, @Param("example") FqRandomExample example);

    int updateByPrimaryKeySelective(FqRandom record);

    int updateByPrimaryKey(FqRandom record);
}