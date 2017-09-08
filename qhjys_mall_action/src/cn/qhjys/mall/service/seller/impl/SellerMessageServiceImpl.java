package cn.qhjys.mall.service.seller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.qhjys.mall.entity.MessageInfo;
import cn.qhjys.mall.entity.MessageInfoExample;
import cn.qhjys.mall.mapper.MessageInfoMapper;
import cn.qhjys.mall.service.seller.SellerMessageService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class SellerMessageServiceImpl implements SellerMessageService {

	@Autowired
	private MessageInfoMapper messageMapper;

	@Override
	public Page<MessageInfo> queryMessageList(Long sellerId, Integer seen, Integer pageNum, Integer pageSize)
			throws Exception {
		MessageInfoExample example = new MessageInfoExample();
		example.createCriteria().andTypeEqualTo(2).andSendeeEqualTo(sellerId).andSeenEqualTo(seen);
		PageHelper.startPage(pageNum, pageSize);
		Page<MessageInfo> page = (Page<MessageInfo>) messageMapper.selectByExampleWithBLOBs(example);
		return page;
	}

	@Override
	public boolean deleteMessage(Long id) throws Exception {
		int row = messageMapper.deleteByPrimaryKey(id);
		return row > 0 ? true : false;
	}

	@Override
	public MessageInfo queryMessage(Long id) throws Exception {
		return messageMapper.selectByPrimaryKey(id);
	}

}
