package cn.qhjys.mall.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.TaskAnswer;
import cn.qhjys.mall.entity.TaskAnswerExample;
import cn.qhjys.mall.mapper.TaskAnswerMapper;
import cn.qhjys.mall.service.system.TaskAnswerService;

@Service("taskAnswerService")
public class TaskAnswerServiceImpl implements TaskAnswerService {

	@Autowired
	private TaskAnswerMapper taskAnswerMapper;
	@Override
	public List<TaskAnswer> listAllTaskAnswer(Long questionId) {
		TaskAnswerExample example = new TaskAnswerExample();
		example.createCriteria().andQyestionIdEqualTo(questionId);
		return taskAnswerMapper.selectByExample(example);
	}

	@Override
	public int insertTaskAnswer(TaskAnswer taskAnswer) {
		return taskAnswerMapper.insertSelective(taskAnswer);
	}

	@Override
	public int updateTaskAnswer(TaskAnswer taskAnswer) {
		return taskAnswerMapper.updateByPrimaryKeySelective(taskAnswer);
	}

	@Override
	public int deleteTaskAnswer(Long id) {
		return taskAnswerMapper.deleteByPrimaryKey(id);
	}

	@Override
	public TaskAnswer getTaskAnswer(Long id) {
		return taskAnswerMapper.selectByPrimaryKey(id);
	}

}
