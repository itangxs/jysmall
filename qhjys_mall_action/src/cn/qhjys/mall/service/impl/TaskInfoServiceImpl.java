package cn.qhjys.mall.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.qhjys.mall.entity.TaskInfo;
import cn.qhjys.mall.entity.TaskInfoExample;
import cn.qhjys.mall.entity.TaskInfoExample.Criteria;
import cn.qhjys.mall.entity.UserTask;
import cn.qhjys.mall.mapper.TaskInfoMapper;
import cn.qhjys.mall.mapper.UserTaskMapper;
import cn.qhjys.mall.mapper.custom.TaskMapper;
import cn.qhjys.mall.service.TaskInfoService;
import cn.qhjys.mall.service.UserTaskService;
import cn.qhjys.mall.vo.TaskVo;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
@Service("taskInfoService")
public class TaskInfoServiceImpl implements TaskInfoService {

	@Autowired
	private TaskInfoMapper taskInfoMapper;
	
	@Autowired
	private TaskMapper taskMapper;
	
	@Autowired
	private UserTaskService userTaskService;
	@Autowired
	private UserTaskMapper userTaskMapper;
	
	@Override
	public int insertTaskInfo(TaskInfo taskInfo) {
		return taskInfoMapper.insertSelective(taskInfo);
	}

	@Override
	public int updateTaskInfo(TaskInfo taskInfo) {
		return taskInfoMapper.updateByPrimaryKeySelective(taskInfo);
	}

	@Override
	public Page<TaskInfo> listTaskInfo(Integer taskType, String taskFrom,
			Integer taskLevel, String bbeginTime, String bendTime, String ebeginTime,
			String eendTime,String boff,String eoff, Integer status,int pageNum,int pageSize,String orderby) throws ParseException {
		TaskInfoExample example = new TaskInfoExample();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		Criteria criteria = example.createCriteria();
		if (taskType !=null && taskType >-1) {
			criteria.andTaskTypeEqualTo(taskType);
		}
		if (taskLevel !=null && taskLevel >-1) {
			criteria.andTaskLevelEqualTo(taskLevel);
		}
		if (status !=null && status >-1) {
			criteria.andStatusEqualTo(status);
		}
		if (!StringUtils.isEmpty(bbeginTime)) {
			criteria.andBeginTimeGreaterThan(sim.parse(bbeginTime));
		}
		if (!StringUtils.isEmpty(bendTime)) {
			criteria.andBeginTimeLessThan(sim.parse(bendTime));
		}
		if (!StringUtils.isEmpty(ebeginTime)) {
			criteria.andEndTimeGreaterThan(sim.parse(ebeginTime));
		}
		if (!StringUtils.isEmpty(eendTime)) {
			criteria.andEndTimeLessThan(sim.parse(eendTime));
		}
		if (!StringUtils.isEmpty(boff)) {
			criteria.andOffShelfGreaterThan(sim.parse(eendTime));
		}
		if (!StringUtils.isEmpty(eoff)) {
			criteria.andOffShelfLessThan(sim.parse(eoff));
		}
		if (!StringUtils.isEmpty(taskFrom)) {
			criteria.andTaskFromLike(taskFrom);
		}
		if (!StringUtils.isEmpty(orderby)) {
			example.setOrderByClause(orderby);
		}
		PageHelper.startPage(pageNum, pageSize);
		return (Page<TaskInfo>) taskInfoMapper.selectByExample(example);
	}

	@Override
	public Page<TaskInfo> listTaskInfoByParam(Integer taskType,
			String taskName, String taskFrom, String startTime, String endTime,
			Integer status, int pageNum, int pageSize)
			throws ParseException {
		TaskInfoExample example = new TaskInfoExample();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Criteria criteria = example.createCriteria();
		PageHelper.startPage(pageNum, pageSize);
		if (taskType !=null && taskType >-1) {
			criteria.andTaskTypeEqualTo(taskType);
		}
		if (status !=null && status >-1) {
			criteria.andStatusEqualTo(status);
		}
		if (!StringUtils.isEmpty(taskName)) {
			criteria.andTaskNameEqualTo(taskName);
		}
		if (!StringUtils.isEmpty(taskFrom)) {
			criteria.andTaskFromLike(taskFrom);
		}
		if (!StringUtils.isEmpty(startTime)) {
			criteria.andCreateTimeGreaterThan(sim.parse(startTime+" 00:00:00"));
		}
		if (!StringUtils.isEmpty(endTime)) {
			criteria.andOffShelfLessThan(sim.parse(endTime+" 23:59:59"));
		}
		example.setOrderByClause("create_time desc");
		return (Page<TaskInfo>) taskInfoMapper.selectByExample(example);
	}

	@Override
	public TaskInfo getTask(Long id) {
		return taskInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<TaskInfo> listTaskInfos(Long userId, Integer level,int page,int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userLevel", level);
		map.put("userId", userId);
		PageHelper.startPage(page, pageSize);
		return taskMapper.selectTaskInfo(map);
	}
	@Override
	public int countTaskInfos(Long userId, Integer level) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userLevel", level);
		map.put("userId", userId);
		return taskMapper.countTaskInfo(map);
	}

	@Override
	public Page<TaskVo> listTaskInfoByUser(Long userId,String [] status,Integer taskType,int page,int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("status", status);
		map.put("taskType", taskType);
		PageHelper.startPage(page, pageSize);
		return taskMapper.selectTaskInfoByUser(map);
	}

	@Override
	public TaskInfo getTaskInfo(String project) {
		TaskInfoExample example = new TaskInfoExample();
		example.createCriteria().andProjectEqualTo(project).andStatusEqualTo(2);
		List<TaskInfo> list = taskInfoMapper.selectByExampleWithBLOBs(example);
		return list.size()>0?list.get(0):null;
	}

	@Override
	public List<TaskVo> selectTaskInfoByNewUser(Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		return taskMapper.selectTaskInfoByNewUser(map);
	}

	@Override
	public int updateTaskInfos(List<TaskInfo> taskInfos) {
		int num = 0;
		for (int i = 0; i < taskInfos.size(); i++) {
			num += taskInfoMapper.updateByPrimaryKeySelective(taskInfos.get(i));
		}
		return num;
	}

	@Override
	public boolean insertNewUserTask(Long userId, String projectId) throws Exception {
		TaskInfo task = getTaskInfo(projectId);
		if (task !=null && task.getStatus() == 2) {
			UserTask usertask = userTaskService.getUserTask(userId, task.getId());
			if(usertask != null){
				return false;
			}else{
				UserTask ut = new UserTask();
				ut.setCreateTime(new Date());
				ut.setStatus("c");
				ut.setTaskId(task.getId());
				ut.setTotamt(task.getFulfilReward());
				ut.setUserId(userId);
				int a = userTaskMapper.insertSelective(ut);
				if (a>0){
					return true;
				}
				return false;
			}
		}
		return false;
	}

	@Override
	public Page<TaskInfo> listTaskInfos(Long userId, Integer level,
			Integer taskType, int page, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userLevel", level);
		map.put("userId", userId);
		map.put("taskType", taskType);
		PageHelper.startPage(page, pageSize);
		return taskMapper.selectTaskInfo(map);
	}

	@Override
	public TaskInfo getTaskInfoByStatus(String project) {
		Date date = new Date();
		TaskInfoExample example = new TaskInfoExample();
		example.createCriteria().andProjectEqualTo(project).andBeginTimeLessThan(date).andOffShelfGreaterThan(date).andStatusEqualTo(2);
		List<TaskInfo> list = taskInfoMapper.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}

	@Override
	public List<TaskVo> listTaskInfoByC(String status, Integer taskType,Date beginTime,Date endTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("taskType", taskType);
		map.put("beginTime", beginTime);
		map.put("endTime", endTime);
		return taskMapper.selectTaskInfoByC(map);
	}

	@Override
	public List<TaskInfo> listTaskInfo(Integer status) {
		TaskInfoExample example = new TaskInfoExample();
		if (status != null) {
			example.createCriteria().andStatusEqualTo(status);
		}
		return taskInfoMapper.selectByExample(example);
	}
	@Override
	public Page<TaskVo> listTaskAll(Long userId,String [] status,Integer [] status1,int page,int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("status", status);
		map.put("status1", status1);
		PageHelper.startPage(page, pageSize);
		return taskMapper.selectTaskAll(map);
	}

	@Override
	public TaskInfo getTaskInfoNoStatus(String project) {
		TaskInfoExample example = new TaskInfoExample();
		example.createCriteria().andProjectEqualTo(project);
		List<TaskInfo> list = taskInfoMapper.selectByExampleWithBLOBs(example);
		return list.size()>0?list.get(0):null;
	}
}
