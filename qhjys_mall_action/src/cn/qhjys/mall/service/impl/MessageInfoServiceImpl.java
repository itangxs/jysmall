package cn.qhjys.mall.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.MessageInfo;
import cn.qhjys.mall.entity.MessageInfoExample;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.SellerInfoExample;
import cn.qhjys.mall.entity.UserInfo;
import cn.qhjys.mall.entity.UserInfoExample;
import cn.qhjys.mall.mapper.MessageInfoMapper;
import cn.qhjys.mall.mapper.SellerInfoMapper;
import cn.qhjys.mall.mapper.UserInfoMapper;
import cn.qhjys.mall.service.MessageInfoService;
import cn.qhjys.mall.util.DateTimeUtil;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class MessageInfoServiceImpl extends Base implements MessageInfoService {

	@Autowired
	private MessageInfoMapper infoMapper;

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Autowired
	private SellerInfoMapper sellerInfoMapper;

	@Override
	public boolean readUserMessage(long id) throws Exception {
		return false;
	}

	@Override
	public Page<MessageInfo> queryUserMessages(long userid, Page<MessageInfo> page) throws Exception {
		MessageInfoExample example = new MessageInfoExample();
		if (userid > 0) {
			example.createCriteria().andSendeeEqualTo(userid);
		}
		example.setOrderByClause("create_date desc");
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		/* infoMapper.selectByExampleWithBLOBs(example) */
		return (Page<MessageInfo>) infoMapper.selectByExample(example);
		/* return page; */
	}

	@Override
	public Page<MessageInfo> queryUserMessagesDatail(Long id, Long uid, Page<MessageInfo> page) throws Exception {
		MessageInfoExample example = new MessageInfoExample();
		if (null != id) {
			example.createCriteria().andIdEqualTo(id);
		}
		if (null != uid) {
			example.createCriteria().andSendeeEqualTo(uid);
		}
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		return (Page<MessageInfo>) infoMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public Page<MessageInfo> queryMessagesByParams(String account, String title, String beginTime, String endTime,
			int seen, int pageNum, int pageSize) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		MessageInfoExample example = new MessageInfoExample();
		
		if (!StringUtils.isEmpty(title)) {
			example.createCriteria().andTitleLike(title);
		}
		if (!StringUtils.isEmpty(beginTime)) {
			example.createCriteria().andCreateDateGreaterThanOrEqualTo(DateTimeUtil.getStartTime(sdf.parse(beginTime)));
		}
		if (!StringUtils.isEmpty(endTime)) {
			example.createCriteria().andCreateDateLessThanOrEqualTo(DateTimeUtil.getEndTime(sdf.parse(endTime)));
		}
		if (seen >= 0) {
			example.createCriteria().andSeenEqualTo(seen);
		}
		example.setOrderByClause(" create_date DESC ");
		PageHelper.startPage(pageNum, pageSize);
		return (Page<MessageInfo>) infoMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public boolean saveMessage(int sendType, int vipRank, Long adminId, String title, String content) throws Exception {
		if (sendType == 2) {
			SellerInfoExample example = new SellerInfoExample();
			List<SellerInfo> listSeller = sellerInfoMapper.selectByExample(example);
			for (int i = 0; i < listSeller.size(); i++) {
				SellerInfo si = listSeller.get(i);
				MessageInfo info = new MessageInfo();
				info.setMsgType(2);
				info.setAdminId(adminId);
				info.setContent(content);
				info.setCreateDate(new Date());
				info.setSeen(0);
				info.setTitle(title);
				info.setType(sendType);
				info.setSendee(si.getId());
				infoMapper.insertSelective(info);
			}
			return true;
		} else if (sendType == 1) {
			UserInfoExample example = new UserInfoExample();
			if (vipRank != 0)
				example.createCriteria().andLevelEqualTo(vipRank);
			List<UserInfo> listUser = userInfoMapper.selectByExample(example);
			for (int i = 0; i < listUser.size(); i++) {
				UserInfo ui = listUser.get(i);
				MessageInfo info = new MessageInfo();
				info.setMsgType(2);
				info.setAdminId(adminId);
				info.setContent(content);
				info.setCreateDate(new Date());
				info.setSeen(0);
				info.setTitle(title);
				info.setType(sendType);
				info.setSendee(ui.getId());
				infoMapper.insertSelective(info);
			}
			return true;
		}
		return false;
	}

	@Override
	public MessageInfo getMessage(Long id) throws Exception {
		if (id != null)
			return infoMapper.selectByPrimaryKey(id);
		return null;
	}

	@Override
	public boolean deleteMessage(Long[] ids) throws Exception {
		for (int i = 0; i < ids.length; i++) {
			int numDel = infoMapper.deleteByPrimaryKey(ids[i]);
			if (numDel <= 0)
				return false;
		}
		return true;
	}

	@Override
	public int updateMessage(MessageInfo message) {
		return infoMapper.updateByPrimaryKeySelective(message);
	}

	@Override
	public int countMessageByNotRead(Long userId) {
		MessageInfoExample example = new MessageInfoExample();
			example.createCriteria().andSendeeEqualTo(userId).andSeenEqualTo(0);
		return infoMapper.countByExample(example);
	}

	@Override
	public List<MessageInfo> queryMessageByType(Long id, Integer type, Integer[] msgtypes,
			int pageNum, int pageSize) {
		MessageInfoExample example = new MessageInfoExample();
		example.createCriteria().andSendeeEqualTo(id).andTypeEqualTo(type).andMsgTypeIn(Arrays.asList(msgtypes));
		example.setOrderByClause("create_date desc,seen asc");
		PageHelper.startPage(pageNum, pageSize);
		return infoMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public int insertMessageInfo(MessageInfo message) {
		return infoMapper.insertSelective(message);
	}

	@Override
	public boolean insertMessageInfo(List<MessageInfo> messages) {
		for (int i = 0; i < messages.size(); i++) {
			MessageInfo message = messages.get(i);
			int a = infoMapper.insertSelective(message);
			if (a<=0) {
				return false;
			}
		}
		return true;
	}
}