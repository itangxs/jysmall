package cn.qhjys.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.qhjys.mall.mapper.custom.MessageVoMapper;
import cn.qhjys.mall.service.MessageService;
import cn.qhjys.mall.vo.MessageVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageVoMapper messageVoMapper;

	@Override
	public Page<MessageVo> queryMessage(Long userid, Long status, int pageNum, int pageSize) throws Exception {
		Page<MessageVo> page = new Page<MessageVo>();
		PageHelper.startPage(pageNum, pageSize);
		page = messageVoMapper.queryOrderList(userid, status);
		return page;
	}
}