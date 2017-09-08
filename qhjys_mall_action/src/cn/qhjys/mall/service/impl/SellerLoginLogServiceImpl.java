package cn.qhjys.mall.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.SellerLoginLog;
import cn.qhjys.mall.entity.SellerLoginLogExample;
import cn.qhjys.mall.mapper.SellerLoginLogMapper;
import cn.qhjys.mall.service.SellerLoginLogService;

@Service("sellerLoginLogService")
public class SellerLoginLogServiceImpl implements SellerLoginLogService {
	
	@Autowired
	private SellerLoginLogMapper sellerLoginLogMapper;

	@Override
	public int insertSellerLoginLog(SellerLoginLog sellerLoginLog) {
		return sellerLoginLogMapper.insertSelective(sellerLoginLog);
	}

	@Override
	public int countSellerLoginLog(Long sellerId,Integer type) {
		SellerLoginLogExample example = new SellerLoginLogExample();
		example.createCriteria().andSellerIdEqualTo(sellerId).andLoginTypeEqualTo(type);
		return sellerLoginLogMapper.countByExample(example);
	}

	@Override
	public int countSellerLoginLogToday(Long sellerId, Integer type) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR, 0);  
        c.set(Calendar.MINUTE, 0);  
        c.set(Calendar.SECOND, 0);  
        c.set(Calendar.MILLISECOND, 0); 
        Date startTime = c.getTime();
        c.set(Calendar.HOUR, 23);  
        c.set(Calendar.MINUTE, 59);  
        c.set(Calendar.SECOND, 59);  
        c.set(Calendar.MILLISECOND, 999);  
        Date endTime = c.getTime();
		SellerLoginLogExample example = new SellerLoginLogExample();
		example.createCriteria().andSellerIdEqualTo(sellerId).andLoginTypeEqualTo(type).andCreateTimeBetween(startTime, endTime);
		return sellerLoginLogMapper.countByExample(example);
	}

}
