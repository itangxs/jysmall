package cn.qhjys.mall.service;

import java.text.ParseException;
import java.util.List;

import cn.qhjys.mall.entity.MessageInfo;

import com.github.pagehelper.Page;

/**
 * 用户消息管理
 * 
 * @author LiXiang
 *
 */
public interface MessageInfoService {

	/**
	 * 已读用户消息
	 * 
	 * @param id
	 *            消息编号
	 * @return
	 */
	public boolean readUserMessage(long id) throws Exception;

	/**
	 * 查询用户消息
	 * 
	 * @param userid
	 *            用户编号
	 * @param page
	 *            分页条件
	 * @return
	 */
	public Page<MessageInfo> queryUserMessages(long userid, Page<MessageInfo> page) throws Exception;

	/**
	 * 
	 * @Title: queryUserMessagesDatail 查找详情
	 * @Description: TODO
	 * @param id
	 *            消息ID
	 * @param uid
	 *            用户ID
	 * @param infos
	 * @return
	 * @return Page<MessageInfo>
	 */
	public Page<MessageInfo> queryUserMessagesDatail(Long id, Long uid, Page<MessageInfo> page) throws Exception;

	/**
	 * 按条件查询
	 * 
	 * @param account
	 * @param title
	 * @param beginTime
	 * @param endTime
	 * @param seen
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Page<MessageInfo> queryMessagesByParams(String account, String title, String beginTime, String endTime,
			int seen, int pageNum, int pageSize) throws ParseException;

	/**
	 * 新增信息
	 * 
	 * @param type
	 *            消息类型
	 * @param sendType
	 *            接收人类型 0 商户 1 会员
	 * @param vipRank
	 *            VIP等级
	 * @param adminId
	 *            发送人ID
	 * @param title
	 *            标题
	 * @param content
	 *            内容
	 * @return
	 */
	public boolean saveMessage(int sendType, int vipRank, Long adminId, String title, String content) throws Exception;

	/**
	 * 根据主键获取消息
	 * 
	 * @param id
	 * @return
	 */
	public MessageInfo getMessage(Long id) throws Exception;

	/**
	 * 根据主键数组删除消息
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteMessage(Long id[]) throws Exception;
	
	public int updateMessage(MessageInfo message);
	
	public int countMessageByNotRead(Long userId);
	
	public List<MessageInfo> queryMessageByType(Long id,Integer type,Integer[] msgtypes,int pageNum,int pageSize);

	public int insertMessageInfo(MessageInfo message);
	
	public boolean insertMessageInfo(List<MessageInfo> messages);
}