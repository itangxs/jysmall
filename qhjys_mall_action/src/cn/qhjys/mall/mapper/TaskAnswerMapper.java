package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.TaskAnswer;
import cn.qhjys.mall.entity.TaskAnswerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskAnswerMapper {
    int countByExample(TaskAnswerExample example);

    int deleteByExample(TaskAnswerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TaskAnswer record);

    int insertSelective(TaskAnswer record);

    List<TaskAnswer> selectByExample(TaskAnswerExample example);

    TaskAnswer selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TaskAnswer record, @Param("example") TaskAnswerExample example);

    int updateByExample(@Param("record") TaskAnswer record, @Param("example") TaskAnswerExample example);

    int updateByPrimaryKeySelective(TaskAnswer record);

    int updateByPrimaryKey(TaskAnswer record);
}