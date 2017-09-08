package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.TaskExpand;
import cn.qhjys.mall.entity.TaskExpandExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskExpandMapper {
    int countByExample(TaskExpandExample example);

    int deleteByExample(TaskExpandExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TaskExpand record);

    int insertSelective(TaskExpand record);

    List<TaskExpand> selectByExample(TaskExpandExample example);

    TaskExpand selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TaskExpand record, @Param("example") TaskExpandExample example);

    int updateByExample(@Param("record") TaskExpand record, @Param("example") TaskExpandExample example);

    int updateByPrimaryKeySelective(TaskExpand record);

    int updateByPrimaryKey(TaskExpand record);
}