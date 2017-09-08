package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.ScheduleInfo;
import cn.qhjys.mall.entity.ScheduleInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScheduleInfoMapper {
    int countByExample(ScheduleInfoExample example);

    int deleteByExample(ScheduleInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ScheduleInfo record);

    int insertSelective(ScheduleInfo record);

    List<ScheduleInfo> selectByExample(ScheduleInfoExample example);

    ScheduleInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ScheduleInfo record, @Param("example") ScheduleInfoExample example);

    int updateByExample(@Param("record") ScheduleInfo record, @Param("example") ScheduleInfoExample example);

    int updateByPrimaryKeySelective(ScheduleInfo record);

    int updateByPrimaryKey(ScheduleInfo record);
}