package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.IntegralExpired;
import cn.qhjys.mall.entity.IntegralExpiredExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface IntegralExpiredMapper {
    int countByExample(IntegralExpiredExample example);

    int deleteByExample(IntegralExpiredExample example);

    int deleteByPrimaryKey(Long id);

    int insert(IntegralExpired record);

    int insertSelective(IntegralExpired record);

    List<IntegralExpired> selectByExample(IntegralExpiredExample example);

    IntegralExpired selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") IntegralExpired record, @Param("example") IntegralExpiredExample example);

    int updateByExample(@Param("record") IntegralExpired record, @Param("example") IntegralExpiredExample example);

    int updateByPrimaryKeySelective(IntegralExpired record);

    int updateByPrimaryKey(IntegralExpired record);
}