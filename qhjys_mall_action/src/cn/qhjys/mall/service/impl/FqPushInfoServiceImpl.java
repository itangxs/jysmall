package cn.qhjys.mall.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tencent.xinge.ClickAction;
import com.tencent.xinge.Message;
import com.tencent.xinge.MessageIOS;
import com.tencent.xinge.XingeApp;

import cn.qhjys.mall.common.XingeService;
import cn.qhjys.mall.entity.FqPushInfo;
import cn.qhjys.mall.entity.FqPushInfoExample;
import cn.qhjys.mall.entity.FqPushInfoExample.Criteria;
import cn.qhjys.mall.mapper.FqPushInfoMapper;
import cn.qhjys.mall.service.FqPushInfoService;
import cn.qhjys.mall.util.DateUtil;

@Service("fqPushInfoService")
public class FqPushInfoServiceImpl implements FqPushInfoService {

	@Autowired
	private FqPushInfoMapper fqPushInfoMapper;
	
	@Override
	public Page<FqPushInfo> queryFqPushInfo(Long sellerId, Integer pageNum,
			Integer pageSize,String loginDate,Integer type) {
		FqPushInfoExample example = new FqPushInfoExample();
		List<Long> sellerIds = new ArrayList<Long>();
		sellerIds.add(sellerId);
		sellerIds.add(0L);
		Criteria criteria = example.createCriteria();
		criteria.andSellerIdIn(sellerIds);
		if (!StringUtils.isEmpty(loginDate)) {
			criteria.andPushTimeGreaterThanOrEqualTo(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", loginDate));
		}
		if (type != null) {
			criteria.andTypeEqualTo(type);
		}
		example.setOrderByClause("push_time DESC");
		PageHelper.startPage(pageNum, pageSize);
		return (Page<FqPushInfo>) fqPushInfoMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public int insertFqPushInfo(FqPushInfo fqPushInfo) {
		return fqPushInfoMapper.insertSelective(fqPushInfo);
	}

	@Override
	public int insertSystemFqPushInfo(FqPushInfo fqPushInfo) {
		
		Map<String,Object> custom = new HashMap<String,Object>(); 
		custom.put("type",2);
		 MessageIOS messageIos = new MessageIOS();
		 messageIos.setExpireTime(60);
		 messageIos.setAlert(fqPushInfo.getTitle());
		 messageIos.setBadge(1);
		 messageIos.setSound("beep.wav");
		 messageIos.setCustom(custom);
		 XingeService.xingeIos.pushAllDevice(0, messageIos, XingeApp.IOSENV_PROD);
		 XingeService.xingeIos.pushAllDevice(0, messageIos, XingeApp.IOSENV_DEV);
		 
		 Message message = new Message();
		 message.setExpireTime(60);
		 message.setTitle(fqPushInfo.getTitle());
		 message.setContent(fqPushInfo.getContent());
		 message.setType(Message.TYPE_NOTIFICATION);
		 message.setCustom(custom);
		 ClickAction action = new ClickAction();
		 action.setActionType(ClickAction.TYPE_ACTIVITY);
		 action.setActivity("com.freechange.seller.modules.MainActivity");
		 message.setAction(action);
		 XingeService.xinge.pushAllDevice(0, message);
		 return fqPushInfoMapper.insertSelective(fqPushInfo);
	}
	@Override
	public int insertWelcomeFqPushInfo(FqPushInfo fqPushInfo) {
		FqPushInfoExample example = new FqPushInfoExample();
		example.createCriteria().andSellerIdEqualTo(fqPushInfo.getSellerId())
		.andTypeEqualTo(2);
		if (fqPushInfoMapper.countByExample(example)>0) {
			return 0;
		}
		Map<String,Object> custom = new HashMap<String,Object>();
		custom.put("type",2);
		MessageIOS messageIos = new MessageIOS();
		messageIos.setExpireTime(60);
		messageIos.setAlert(fqPushInfo.getTitle());
		messageIos.setBadge(1);
		messageIos.setSound("beep.wav");
		messageIos.setCustom(custom);
		System.out.println(XingeService.xingeIos.pushSingleAccount(0, fqPushInfo.getSellerId()+"", messageIos, XingeApp.IOSENV_PROD));
		System.out.println(XingeService.xingeIos.pushSingleAccount(0, fqPushInfo.getSellerId()+"", messageIos, XingeApp.IOSENV_DEV));
		
		Message message = new Message();
		message.setExpireTime(60);
		message.setTitle(fqPushInfo.getTitle());
		message.setContent(fqPushInfo.getContent());
		message.setType(Message.TYPE_NOTIFICATION);
		message.setCustom(custom);
		ClickAction action = new ClickAction();
		action.setActionType(ClickAction.TYPE_ACTIVITY);
		action.setActivity("com.freechange.seller.modules.MainActivity");
		message.setAction(action);
		XingeService.xinge.pushSingleAccount(0, fqPushInfo.getSellerId()+"", message);
		return fqPushInfoMapper.insertSelective(fqPushInfo);
	}

	@Override
	public Page<FqPushInfo> queryFqPushInfo(Integer type, Integer pageNum,
			Integer pageSize) {
		FqPushInfoExample example = new FqPushInfoExample();
		example.createCriteria().andTypeEqualTo(type);
		example.setOrderByClause("push_time DESC");
		PageHelper.startPage(pageNum, pageSize);
		return (Page<FqPushInfo>) fqPushInfoMapper.selectByExampleWithBLOBs(example);
	}
}
