package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqLocation;
import cn.qhjys.mall.entity.FqLocationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqLocationMapper {
    int countByExample(FqLocationExample example);

    int deleteByExample(FqLocationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqLocation record);

    int insertSelective(FqLocation record);

    List<FqLocation> selectByExample(FqLocationExample example);

    FqLocation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqLocation record, @Param("example") FqLocationExample example);

    int updateByExample(@Param("record") FqLocation record, @Param("example") FqLocationExample example);

    int updateByPrimaryKeySelective(FqLocation record);

    int updateByPrimaryKey(FqLocation record);
}