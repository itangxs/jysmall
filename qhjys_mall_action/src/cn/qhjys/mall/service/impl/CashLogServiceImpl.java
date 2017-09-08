package cn.qhjys.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.CashLog;
import cn.qhjys.mall.entity.CashLogExample;
import cn.qhjys.mall.entity.CashLogExample.Criteria;
import cn.qhjys.mall.mapper.CashLogMapper;
import cn.qhjys.mall.service.CashLogService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service("CashLogService")
public class CashLogServiceImpl implements CashLogService{
	@Autowired
	private CashLogMapper cashLogMapper;
	
	@Override
	public Page<CashLog> queryCashLog(Long accountId, Integer pageNum, Integer pageSize) {
		if (accountId == null) {
			return null;
		}
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 15;
		}
		CashLogExample example = new CashLogExample();
		Criteria criteria = example.createCriteria();
		Criteria criteriaOr = example.or();
		criteria.andSendIdEqualTo(accountId);
		criteriaOr.andReviewIdEqualTo(accountId);
		example.setOrderByClause("create_time desc,id desc");
		PageHelper.startPage(pageNum, pageSize);
		return (Page<CashLog>) cashLogMapper.selectByExample(example);
	}
}
