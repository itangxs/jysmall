package cn.qhjys.mall.service.fq.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.mapper.custom.DaliyCreditMapper;
import cn.qhjys.mall.service.fq.DaliyCreditService;

@Service("daliyCreditService")
public class DaliyCreditServiceImpl implements DaliyCreditService {
	
	@Autowired
	private DaliyCreditMapper daliyCreditMapper;
	
	@Override
	public BigDecimal queryDaliyCredit(Long sellerId,Long storeId,Date beginTime,Date endTime){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sellerId", sellerId);
		map.put("storeId", storeId);
		map.put("beginTime", beginTime);
		map.put("endTime", endTime);
		BigDecimal storeSum = daliyCreditMapper.queryFqOrderSum(map);
		BigDecimal rebateSum = daliyCreditMapper.queryRebateOrderSum(map);
		return storeSum.add(rebateSum);
	}
}
