package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.IntegralLog;
import cn.qhjys.mall.entity.IntegralLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IntegralLogMapper {
    int countByExample(IntegralLogExample example);

    int deleteByExample(IntegralLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(IntegralLog record);

    int insertSelective(IntegralLog record);

    List<IntegralLog> selectByExample(IntegralLogExample example);

    IntegralLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") IntegralLog record, @Param("example") IntegralLogExample example);

    int updateByExample(@Param("record") IntegralLog record, @Param("example") IntegralLogExample example);

    int updateByPrimaryKeySelective(IntegralLog record);

    int updateByPrimaryKey(IntegralLog record);
}