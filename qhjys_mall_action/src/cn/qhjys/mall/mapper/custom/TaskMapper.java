package cn.qhjys.mall.mapper.custom;

import java.util.List;
import java.util.Map;

import cn.qhjys.mall.entity.TaskInfo;
import cn.qhjys.mall.vo.TaskVo;

import com.github.pagehelper.Page;

public interface TaskMapper {
	Page<TaskInfo> selectTaskInfo(Map<String, Object> map);
	int countTaskInfo(Map<String, Object> map);
	Page<TaskVo> selectTaskInfoByUser(Map<String, Object> map);
	List<TaskVo> selectTaskInfoByNewUser(Map<String, Object> map);
	List<TaskVo> selectTaskInfoByC(Map<String, Object> map);
	int countMoney(Map<String, Object> map);
	Page<TaskVo> selectTaskAll(Map<String, Object> map);
}
