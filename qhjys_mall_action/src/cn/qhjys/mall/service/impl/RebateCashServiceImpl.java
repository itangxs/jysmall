package cn.qhjys.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.RebateCash;
import cn.qhjys.mall.mapper.RebateCashMapper;
import cn.qhjys.mall.service.RebateCashService;

@Service
public class RebateCashServiceImpl implements RebateCashService {
	
	@Autowired
	private RebateCashMapper rebateCashMapper;

	@Override
	public RebateCash queryRebateCashLast(Long sellerId) {
		List<RebateCash> list = rebateCashMapper.selectLastRebateCashBySerller(sellerId);
		return list.size()>0?list.get(0):null;
	}

}
