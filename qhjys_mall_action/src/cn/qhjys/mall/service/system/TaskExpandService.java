package cn.qhjys.mall.service.system;

import java.util.List;
import cn.qhjys.mall.entity.TaskExpand;


public interface TaskExpandService {
	//添加
	public int saveTaskExpand(TaskExpand taskExpand)throws Exception;
	//修改
	public int updateTaskExpand(TaskExpand taskExpand)throws Exception;
	//获取某个
	public TaskExpand getTaskExpandById(Long tEId)throws Exception;
	//获取某个任务所E
	public List<TaskExpand> getTaskExpandListByTaskId(Long taskId)throws Exception;
}
