package cn.qhjys.mall.service;

import cn.qhjys.mall.vo.MessageVo;

import com.github.pagehelper.Page;

public interface MessageService {

	/**
	 * 
	 * @Title: queryMessage 获取消息分页
	 * @Description: TODO
	 * @param userid
	 *            用户ID 可空
	 * @param status
	 *            状态 0站内消息；1用户消息；2商家消息；
	 * @param pageNum
	 *            页数
	 * @param pageSize
	 *            每页显示
	 * @return
	 * @throws Exception
	 * @return Page<MessageVo> 返回对象
	 */
	public Page<MessageVo> queryMessage(Long userid, Long status, int pageNum, int pageSize) throws Exception;
	

}
