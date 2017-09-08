package cn.qhjys.mall.service.system;

import java.math.BigDecimal;
import java.util.List;

import cn.qhjys.mall.entity.SysUserTask;


public interface SysUserTaskService {
	
	public int insertSysUserTask(SysUserTask SysUserTask)throws Exception;
	
	public SysUserTask getSysUserTask(Long sysid,Long uid)throws Exception;
	
	public SysUserTask getSysUserTask(Long id)throws Exception;
	
	public List<SysUserTask> getSysUserTasksListByTaskIdAndStatus(Long taskid,Integer status)throws Exception;

	public void updateSysUserTaskStatus(Long id, Integer status,BigDecimal totamt)throws Exception;
	

	public int updateSysUserTask(SysUserTask SysUserTask);

	public Boolean updatetiming()throws Exception;
	
	public boolean updateWenjuanTiming()throws Exception;
	
	public boolean updatePinlunTiming()throws Exception;
}
