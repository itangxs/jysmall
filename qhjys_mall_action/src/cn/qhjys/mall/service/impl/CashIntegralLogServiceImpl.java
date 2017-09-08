package cn.qhjys.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.qhjys.mall.entity.CashIntegralLog;
import cn.qhjys.mall.entity.CashIntegralLogExample;
import cn.qhjys.mall.entity.CashIntegralLogExample.Criteria;
import cn.qhjys.mall.mapper.CashIntegralLogMapper;
import cn.qhjys.mall.service.CashIntegralLogService;

@Service("cashIntegralLogService")
public class CashIntegralLogServiceImpl implements CashIntegralLogService {

	@Autowired
	CashIntegralLogMapper cashIntegralLogMapper;
	
	@Override
	public List<CashIntegralLog> queryCashIntegralLog(Long sellerId) {
		CashIntegralLogExample example = new CashIntegralLogExample();
		example.createCriteria().andSellerIdEqualTo(sellerId);
		example.setOrderByClause("create_time desc");
		return cashIntegralLogMapper.selectByExample(example);
	}

	@Override
	public Page<CashIntegralLog> queryCashIntegralLog(Long storeId,
			String storeName,Integer type, Integer pageNum, Integer pageSize) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		CashIntegralLogExample example = new CashIntegralLogExample();
		Criteria criteria = example.createCriteria();
		if (storeId != null) {
			criteria.andStoreIdEqualTo(storeId);
		}
		if (type != null) {
			criteria.andTypeEqualTo(type);
		}
		if (!StringUtils.isEmpty(storeName)) {
			criteria.andStoreNameLike("%"+storeName+"%");
		}
		example.setOrderByClause("create_time desc");
		PageHelper.startPage(pageNum, pageSize);
		return (Page<CashIntegralLog>) cashIntegralLogMapper.selectByExample(example);
	}

}
