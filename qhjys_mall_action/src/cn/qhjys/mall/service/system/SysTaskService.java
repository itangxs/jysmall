package cn.qhjys.mall.service.system;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.Page;

import cn.qhjys.mall.entity.SysTask;
import cn.qhjys.mall.vo.SysTaskVo;

public interface SysTaskService {
	
	public int insertSysTask(SysTask sysTask);
	
	public int updateSysTask(SysTask sysTask);
	
	public SysTask getSysTask(Long id);
	
	public Page<SysTask> listSysTask(Integer taskType,Integer status,Long sellerId,Integer pageNum,Integer pageSize);

	public List<SysTask> getSysTask(Integer type,Integer status,Date date);
	
	public List<SysTask> listSysTaskByUserAndStatus(Long userId,Integer level,Integer taskType,int page,int pageSize);
	
	public List<SysTaskVo> listSysTaskByUser(Long userId,Integer[] status,Integer taskType,int page,int pageSize);
}
