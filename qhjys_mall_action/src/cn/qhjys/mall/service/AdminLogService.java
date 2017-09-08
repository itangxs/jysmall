package cn.qhjys.mall.service;

import java.util.Map;
import com.github.pagehelper.Page;

public interface AdminLogService {

	/**
	 * 添加管理员日志
	 * 
	 * @param adminId
	 *            管理员编号
	 * @param info
	 *            日志内容
	 * @param type
	 *            操作类型
	 * 
	 * @param ipAddr
	 *            操作IP
	 * @return
	 * @throws Exception
	 */
	boolean insertAdminLog(Long adminId, String info, String operType, String ipAddr) throws Exception;

	/**
	 * 查询操作日志
	 * 
	 * @param pageNum
	 *            当前页数
	 * @param pageSize
	 *            每页记录数
	 * @return
	 */
	Page<Map<String, Object>> queryAdminLog(Integer pageNum, Integer pageSize) throws Exception;

}