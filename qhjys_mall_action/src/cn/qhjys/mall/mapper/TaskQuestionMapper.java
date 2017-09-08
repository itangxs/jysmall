package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.TaskQuestion;
import cn.qhjys.mall.entity.TaskQuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskQuestionMapper {
    int countByExample(TaskQuestionExample example);

    int deleteByExample(TaskQuestionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TaskQuestion record);

    int insertSelective(TaskQuestion record);

    List<TaskQuestion> selectByExample(TaskQuestionExample example);

    TaskQuestion selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TaskQuestion record, @Param("example") TaskQuestionExample example);

    int updateByExample(@Param("record") TaskQuestion record, @Param("example") TaskQuestionExample example);

    int updateByPrimaryKeySelective(TaskQuestion record);

    int updateByPrimaryKey(TaskQuestion record);
}