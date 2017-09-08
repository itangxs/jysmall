package cn.qhjys.mall.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.qhjys.mall.entity.SysTaskLog;
import cn.qhjys.mall.entity.SysTaskLogExample;
import cn.qhjys.mall.mapper.SysTaskLogMapper;
import cn.qhjys.mall.service.system.SysTaskLogService;

@Service("sysTaskLogService")
public class SysTaskLogServiceImpl implements SysTaskLogService {
	
	@Autowired
	private SysTaskLogMapper sysTaskLogMapper;

	@Override
	public int insertSysTaskLog(SysTaskLog sysTaskLog) {
		return sysTaskLogMapper.insertSelective(sysTaskLog);
	}

	@Override
	public SysTaskLog getLastSysTaskLog(Long userTaskId) {
		SysTaskLogExample example = new SysTaskLogExample();
		example.createCriteria().andUserTaskIdEqualTo(userTaskId);
		example.setOrderByClause("question_id desc");
		PageHelper.startPage(0, 1);
		List<SysTaskLog> list = sysTaskLogMapper.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}

}
