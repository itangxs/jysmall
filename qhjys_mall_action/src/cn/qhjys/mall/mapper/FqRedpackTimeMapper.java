package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqRedpackTime;
import cn.qhjys.mall.entity.FqRedpackTimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqRedpackTimeMapper {
    int countByExample(FqRedpackTimeExample example);

    int deleteByExample(FqRedpackTimeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqRedpackTime record);

    int insertSelective(FqRedpackTime record);

    List<FqRedpackTime> selectByExample(FqRedpackTimeExample example);

    FqRedpackTime selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqRedpackTime record, @Param("example") FqRedpackTimeExample example);

    int updateByExample(@Param("record") FqRedpackTime record, @Param("example") FqRedpackTimeExample example);

    int updateByPrimaryKeySelective(FqRedpackTime record);

    int updateByPrimaryKey(FqRedpackTime record);
}