package cn.qhjys.mall.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.TaskQuestion;
import cn.qhjys.mall.entity.TaskQuestionExample;
import cn.qhjys.mall.mapper.TaskQuestionMapper;
import cn.qhjys.mall.service.system.TaskQuestionService;

@Service("taskQuestionService")
public class TaskQuestionServiceImpl implements TaskQuestionService {

	@Autowired
	private TaskQuestionMapper taskQuestionMapper;
	
	@Override
	public int addTaskQuestion(TaskQuestion taskQuestion) {
		return taskQuestionMapper.insertSelective(taskQuestion);
	}

	@Override
	public int updateTaskQuestion(TaskQuestion taskQuestion) {
		return taskQuestionMapper.updateByPrimaryKeySelective(taskQuestion);
	}

	@Override
	public int deleteTaskQuestion(Long id) {
		return taskQuestionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<TaskQuestion> listAllTaskQuestion(Long taskId) {
		TaskQuestionExample example = new TaskQuestionExample();
		example.createCriteria().andTaskIdEqualTo(taskId);
		example.setOrderByClause("question_no");
		return taskQuestionMapper.selectByExample(example);
	}

	@Override
	public TaskQuestion getTaskQuestion(Long id) {
		return taskQuestionMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TaskQuestion> listTaskQuestionNo(Long taskId, Integer no) {
		TaskQuestionExample example = new TaskQuestionExample();
		example.createCriteria().andTaskIdEqualTo(taskId).andQuestionNoGreaterThan(no);
		example.setOrderByClause("question_no");
		return taskQuestionMapper.selectByExample(example);
	}

	@Override
	public TaskQuestion getTaskQuestion(Long taskId, Integer no) {
		TaskQuestionExample example = new TaskQuestionExample();
		example.createCriteria().andTaskIdEqualTo(taskId).andQuestionNoEqualTo(no);
		List<TaskQuestion> list = taskQuestionMapper.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}

	@Override
	public int countQuestionByTask(Long taskId) {
		TaskQuestionExample example = new TaskQuestionExample();
		example.createCriteria().andTaskIdEqualTo(taskId);
		return taskQuestionMapper.countByExample(example);
	}

}
