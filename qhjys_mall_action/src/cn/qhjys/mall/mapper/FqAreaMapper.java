package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqArea;
import cn.qhjys.mall.entity.FqAreaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqAreaMapper {
    int countByExample(FqAreaExample example);

    int deleteByExample(FqAreaExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqArea record);

    int insertSelective(FqArea record);

    List<FqArea> selectByExample(FqAreaExample example);

    FqArea selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqArea record, @Param("example") FqAreaExample example);

    int updateByExample(@Param("record") FqArea record, @Param("example") FqAreaExample example);

    int updateByPrimaryKeySelective(FqArea record);

    int updateByPrimaryKey(FqArea record);
}