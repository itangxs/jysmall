package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.TaskInfo;
import cn.qhjys.mall.entity.TaskInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskInfoMapper {
    int countByExample(TaskInfoExample example);

    int deleteByExample(TaskInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TaskInfo record);

    int insertSelective(TaskInfo record);

    List<TaskInfo> selectByExampleWithBLOBs(TaskInfoExample example);

    List<TaskInfo> selectByExample(TaskInfoExample example);

    TaskInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TaskInfo record, @Param("example") TaskInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") TaskInfo record, @Param("example") TaskInfoExample example);

    int updateByExample(@Param("record") TaskInfo record, @Param("example") TaskInfoExample example);

    int updateByPrimaryKeySelective(TaskInfo record);

    int updateByPrimaryKeyWithBLOBs(TaskInfo record);

    int updateByPrimaryKey(TaskInfo record);
}