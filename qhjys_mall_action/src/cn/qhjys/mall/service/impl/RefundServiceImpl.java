package cn.qhjys.mall.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.qhjys.mall.entity.UserRefund;
import cn.qhjys.mall.entity.UserRefundExample;
import cn.qhjys.mall.mapper.UserRefundMapper;
import cn.qhjys.mall.service.RefundService;

@Service
public class RefundServiceImpl implements RefundService {
	@Autowired
	private UserRefundMapper userRefundMapper;

	@Override
	public UserRefund getRefundByuidAid(Long userId, Long detailId) throws Exception {
		UserRefundExample example = new UserRefundExample();
		example.createCriteria().andUserIdEqualTo(userId).andDetailIdEqualTo(detailId);
		List<UserRefund> list = userRefundMapper.selectByExample(example);
		if (list == null || list.size() < 1)
			return null;
		return list.get(0);
	}

}