package cn.qhjys.mall.service;

import cn.qhjys.mall.entity.UserAudit;
import com.github.pagehelper.Page;

/**
 * 用户信息审核管理
 * 
 * @author LiXiang
 *
 */
public interface UserAuditService {

	/**
	 * 查询用户审核信息
	 * 
	 * @param name
	 *            用户姓名
	 * @param status
	 *            审核状态
	 * @param page
	 *            分页条件
	 * @return
	 */
	public Page<UserAudit> queryUserAudits(String name, int status, Page<UserAudit> page) throws Exception;

	/**
	 * 添加审核记录
	 * 
	 * @param audit
	 *            审核信息
	 * @return
	 */
	public boolean insertUserAudit(UserAudit audit) throws Exception;
}