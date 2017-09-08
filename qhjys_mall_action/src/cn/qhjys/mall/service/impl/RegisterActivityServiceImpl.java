package cn.qhjys.mall.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.RegisterActivity;
import cn.qhjys.mall.entity.RegisterActivityExample;
import cn.qhjys.mall.mapper.RegisterActivityMapper;
import cn.qhjys.mall.service.RegisterActivityService;

@Service("registerActivityService")
public class RegisterActivityServiceImpl extends Base implements RegisterActivityService {

	@Autowired
	private RegisterActivityMapper registerActivityMapper;

	@Override
	public int insertRegisterActivity(RegisterActivity registerActivity) {
		return registerActivityMapper.insertSelective(registerActivity);
	}

	@Override
	public int updateRegisterActivity(RegisterActivity registerActivity) {
		return registerActivityMapper.updateByPrimaryKeySelective(registerActivity);
	}

	/**
	 * 获取最新活动 sellerId 商家Id
	 */
	@Override
	public RegisterActivity getRegisterActivityByDate(Long storeId) {
		Date date = new Date();
		RegisterActivityExample example = new RegisterActivityExample();
		if (null != storeId)
			example.createCriteria().andEnabledEqualTo(1).andBeginTimeLessThanOrEqualTo(date)
					.andEndTimeGreaterThanOrEqualTo(date).andStoreCouponGreaterThan(0);
		else
			example.createCriteria().andEnabledEqualTo(1).andBeginTimeLessThanOrEqualTo(date)
					.andEndTimeGreaterThanOrEqualTo(date).andCommonCouponGreaterThan(0);
		List<RegisterActivity> registerActivitys = registerActivityMapper.selectByExample(example);
		return registerActivitys.size() > 0 ? registerActivitys.get(0) : null;
	}

	@Override
	public Page<RegisterActivity> queryRegisterActiveByPage(int pageNum, int pageSize) {
		RegisterActivityExample example = new RegisterActivityExample();
		example.setOrderByClause("begin_time desc");
		PageHelper.startPage(pageNum, pageSize);
		return (Page<RegisterActivity>) registerActivityMapper.selectByExample(example);
	}

	@Override
	public RegisterActivity getRegisterActivity(Long activeId) {
		return registerActivityMapper.selectByPrimaryKey(activeId);
	}

}
