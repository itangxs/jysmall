package cn.qhjys.mall.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import cn.qhjys.mall.entity.TaskInfo;
import cn.qhjys.mall.vo.TaskVo;

import com.github.pagehelper.Page;

public interface TaskInfoService {
	public int insertTaskInfo(TaskInfo taskInfo);
	public int updateTaskInfo(TaskInfo taskInfo);
	public int updateTaskInfos(List<TaskInfo> taskInfos);
	public Page<TaskInfo> listTaskInfo(Integer taskType,String taskFrom,Integer taskLevel,String bbeginTime,
			String bendTime,String ebeginTime,String eendTime,String boff,String eoff,Integer status,int pageNum,int pageSize,String orderby) throws ParseException;
	public Page<TaskInfo> listTaskInfoByParam(Integer taskType,String taskName,String taskFrom,String startTime,String endTime,Integer status,int pageNum,int pageSize) throws ParseException;
	public TaskInfo getTask(Long id);
	
	public Page<TaskInfo> listTaskInfos(Long userId,Integer level,int page,int pageSize);
	public Page<TaskInfo> listTaskInfos(Long userId,Integer level,Integer taskType,int page,int pageSize);
	
	public int countTaskInfos(Long userId,Integer level);
	
	public Page<TaskVo> listTaskInfoByUser(Long userId,String [] status ,Integer taskType,int page,int pageSize);
	
	public List<TaskVo> selectTaskInfoByNewUser(Long userId);
	
	public TaskInfo getTaskInfo(String project);
	public TaskInfo getTaskInfoByStatus(String project);
	public TaskInfo getTaskInfoNoStatus(String project);
	public List<TaskInfo> listTaskInfo(Integer status);
	
	public boolean insertNewUserTask(Long userId,String projectId)throws Exception;
	
	public List<TaskVo> listTaskInfoByC(String status ,Integer taskType,Date beginTime,Date endTime);
	
	public Page<TaskVo> listTaskAll(Long userId,String [] status,Integer [] status1,int page,int pageSize);
}
