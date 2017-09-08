package cn.qhjys.mall.service.system;

import java.util.List;

import cn.qhjys.mall.entity.TaskAnswer;

public interface TaskAnswerService {
	
	public List<TaskAnswer> listAllTaskAnswer(Long questionId);
	
	public int insertTaskAnswer(TaskAnswer taskAnswer);
	
	public int updateTaskAnswer(TaskAnswer taskAnswer);
	
	public int deleteTaskAnswer(Long id);

	public TaskAnswer getTaskAnswer(Long id);
}
