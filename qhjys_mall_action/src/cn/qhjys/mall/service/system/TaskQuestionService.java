package cn.qhjys.mall.service.system;

import java.util.List;

import cn.qhjys.mall.entity.TaskQuestion;

public interface TaskQuestionService {
	public int addTaskQuestion(TaskQuestion taskQuestion);
	
	public int updateTaskQuestion(TaskQuestion taskQuestion);
	
	public int deleteTaskQuestion(Long id);
	
	public List<TaskQuestion> listAllTaskQuestion(Long taskId);
	
	public List<TaskQuestion> listTaskQuestionNo(Long taskId,Integer no);
	
	public TaskQuestion getTaskQuestion(Long id);
	
	public TaskQuestion getTaskQuestion(Long taskId,Integer no);
	
	public int countQuestionByTask(Long taskId);
}
