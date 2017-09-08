package cn.qhjys.mall.service.system.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.qhjys.mall.entity.TaskExpand;
import cn.qhjys.mall.entity.TaskExpandExample;
import cn.qhjys.mall.mapper.TaskExpandMapper;
import cn.qhjys.mall.service.system.TaskExpandService;
@Service
public class TaskExpandServiceImpl implements TaskExpandService {
	
	@Autowired
	private TaskExpandMapper taskExpandMapper;
	
	@Override
	public int saveTaskExpand(TaskExpand taskExpand) {
		int insert = taskExpandMapper.insert(taskExpand);
		return insert;
	}

	@Override
	public int updateTaskExpand(TaskExpand taskExpand) {
		int updateByPrimaryKey = taskExpandMapper.updateByPrimaryKey(taskExpand);
		return updateByPrimaryKey;
	}

	@Override
	public TaskExpand getTaskExpandById(Long tEId) {
		TaskExpand taskExpand = taskExpandMapper.selectByPrimaryKey(tEId);
		return taskExpand;
	}

	@Override
	public List<TaskExpand> getTaskExpandListByTaskId(Long taskId) {
		TaskExpandExample example = new TaskExpandExample();
		example.createCriteria().andTaskIdEqualTo(taskId);
		List<TaskExpand> list = taskExpandMapper.selectByExample(example );
		return list;
	}

}
