package cn.qhjys.mall.service.seller;

import cn.qhjys.mall.entity.MessageInfo;
import com.github.pagehelper.Page;

/***
 * 
 * @author zengrong 2015-05-09
 */
public interface SellerMessageService {

	/***
	 * 根据商家编号和是否已读查询商家消息
	 * 
	 * @param sellerId
	 *            商家编号
	 * @param seen
	 *            是否已读(0未读，1已读)
	 * @param pageNum
	 *            分页参数
	 * @param pageSize
	 *            分页参数
	 * @return
	 */
	public Page<MessageInfo> queryMessageList(Long sellerId, Integer seen, Integer pageNum, Integer pageSize)
			throws Exception;

	/***
	 * 删除消息
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteMessage(Long id) throws Exception;

	/****
	 * 查看消息
	 * 
	 * @param id
	 */
	public MessageInfo queryMessage(Long id) throws Exception;
}
