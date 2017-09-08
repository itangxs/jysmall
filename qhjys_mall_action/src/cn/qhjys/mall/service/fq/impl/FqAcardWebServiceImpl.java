package cn.qhjys.mall.service.fq.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.FqAcard;
import cn.qhjys.mall.entity.FqAcardExample;
import cn.qhjys.mall.entity.FqAcardTimeExample;
import cn.qhjys.mall.entity.FqAcardExample.Criteria;
import cn.qhjys.mall.entity.FqAcardTime;
import cn.qhjys.mall.mapper.FqAcardMapper;
import cn.qhjys.mall.mapper.FqAcardTimeMapper;
import cn.qhjys.mall.service.fq.FqAcardWebService;

@Service("fqAcardWebService")
public class FqAcardWebServiceImpl implements FqAcardWebService{

	@Autowired
	FqAcardMapper fqAcardMapper;
	@Autowired
	FqAcardTimeMapper fqAcardTimeMapper;
	
	@Override
	public FqAcard queryAcardByStoreIdAndStatus(Long storeId) throws Exception {
		if (storeId == null) {
			return null;
		}
		// 判断该商家是否有A券活动,判断状态,判断是否在开始和结束日期内
		FqAcardExample example = new FqAcardExample();
		Criteria criteria = example.createCriteria();
		criteria.andStoreIdEqualTo(storeId);
		criteria.andStatusEqualTo(1);
		// 当前时间
		Date today = new Date();
		criteria.andBeginDateLessThanOrEqualTo(today);
		criteria.andEndDateGreaterThanOrEqualTo(today);
		example.setOrderByClause("create_time desc");
		List<FqAcard> acards = fqAcardMapper.selectByExample(example);
		if (acards == null || acards.size() == 0) {
			return null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String timeStr = sdf.format(today);
		String[] times = timeStr.split(":");
		Integer hour = Integer.parseInt(times[0]);
		Integer minute = Integer.parseInt(times[1]);
		Integer cardTime = hour * 60 + minute;
		
		FqAcardTimeExample timeExample = new FqAcardTimeExample();
		FqAcardTimeExample.Criteria timeCriteria = timeExample.createCriteria();
		timeCriteria.andStoreIdEqualTo(storeId);
		timeCriteria.andStartTimeLessThanOrEqualTo(cardTime);
		timeCriteria.andStopTimeGreaterThanOrEqualTo(cardTime);
		List<FqAcardTime> acardTimes = fqAcardTimeMapper.selectByExample(timeExample);
		if (acardTimes == null || acardTimes.size() == 0) {
			return null;
		}
		
		// 判断是否在指定时间段内
		for (int i = 0; i < acards.size(); i++) {
			FqAcard fqAcard = acards.get(i);
			for (int j = 0; j < acardTimes.size(); j++) {
				FqAcardTime acardTime = acardTimes.get(j);
				if (fqAcard.getId() == acardTime.getAcardId()) {
					return fqAcard;
				}
			}
		}
		
		return null;
	}
	
}
