package cn.qhjys.mall.mapper.custom;

import java.util.List;
import java.util.Map;

import cn.qhjys.mall.entity.SysTask;
import cn.qhjys.mall.vo.SysTaskVo;

import com.github.pagehelper.Page;

public interface TaskSysMapper {
	Page<SysTask> selectSysTask(Map<String, Object> map);
	int countSysTask(Map<String, Object> map);
	Page<SysTaskVo> selectSysTaskByUser(Map<String, Object> map);
//	List<TaskVo> selectTaskInfoByNewUser(Map<String, Object> map);
//	List<TaskVo> selectTaskInfoByC(Map<String, Object> map);
//	int countMoney(Map<String, Object> map);
	List<SysTaskVo> selectSysUserTaskByType(Map<String, Object> map);
}
