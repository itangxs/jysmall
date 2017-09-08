package cn.qhjys.mall.service.system;

import cn.qhjys.mall.entity.SysTaskLog;

public interface SysTaskLogService {
	
	public int insertSysTaskLog(SysTaskLog sysTaskLog);
	
	public SysTaskLog getLastSysTaskLog(Long userTaskId);
	
}
