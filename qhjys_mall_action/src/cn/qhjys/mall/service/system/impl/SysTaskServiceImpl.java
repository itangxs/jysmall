package cn.qhjys.mall.service.system.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.qhjys.mall.entity.SysTask;
import cn.qhjys.mall.entity.SysTaskExample;
import cn.qhjys.mall.entity.SysTaskExample.Criteria;
import cn.qhjys.mall.mapper.SysTaskMapper;
import cn.qhjys.mall.mapper.custom.TaskSysMapper;
import cn.qhjys.mall.service.system.SysTaskService;
import cn.qhjys.mall.vo.SysTaskVo;

@Service("sysTaskService")
public class SysTaskServiceImpl implements SysTaskService {
	
	@Autowired
	private SysTaskMapper sysTaskMapper;
	@Autowired
	private TaskSysMapper taskSysMapper;

	@Override
	public int insertSysTask(SysTask sysTask) {
		return sysTaskMapper.insertSelective(sysTask);
	}

	@Override
	public int updateSysTask(SysTask sysTask) {
		return sysTaskMapper.updateByPrimaryKeySelective(sysTask);
	}

	@Override
	public Page<SysTask> listSysTask(Integer taskType, Integer status,
			Long sellerId,Integer pageNum,Integer pageSize) {
		SysTaskExample example = new SysTaskExample();
		Criteria criteria = example.createCriteria();
		if (taskType != null) {
			criteria.andTaskTypeEqualTo(taskType);
		}
		if (status != null) {
			criteria.andStatusEqualTo(status);
		}
		if (sellerId != null) {
			criteria.andSellerIdEqualTo(sellerId);
		}
		example.setOrderByClause("create_time desc ");
		PageHelper.startPage(pageNum, pageSize);
		return (Page<SysTask>) sysTaskMapper.selectByExample(example);
	}

	@Override
	public SysTask getSysTask(Long id) {
		return sysTaskMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SysTask> getSysTask(Integer type, Integer status,Date date) {
		SysTaskExample example = new SysTaskExample();
		Criteria criteria = example.createCriteria();
		if(type!=null){
			criteria.andTaskTypeEqualTo(type);
		}
		if(status!=null){
			criteria.andStatusEqualTo(status);
		}
		if(date!=null){
			criteria.andBeginTimeLessThan(date);
			criteria.andEndTimeGreaterThan(date);
		}
		List<SysTask> list = sysTaskMapper.selectByExample(example);
		return list;
	}

	@Override
	public List<SysTask> listSysTaskByUserAndStatus(Long userId, Integer level,
			Integer taskType, int page, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userLevel", level);
		map.put("userId", userId);
		map.put("taskType", taskType);
		PageHelper.startPage(page, pageSize);
		return taskSysMapper.selectSysTask(map);
	}

	@Override
	public List<SysTaskVo> listSysTaskByUser(Long userId, Integer[] status,
			Integer taskType, int page, int pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("userId", userId);
		map.put("taskType", taskType);
		PageHelper.startPage(page, pageSize);
		return taskSysMapper.selectSysTaskByUser(map);
	}

}
