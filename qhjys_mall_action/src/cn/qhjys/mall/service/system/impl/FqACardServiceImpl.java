package cn.qhjys.mall.service.system.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.qhjys.mall.entity.FqAcard;
import cn.qhjys.mall.entity.FqAcardExample;
import cn.qhjys.mall.entity.FqAcardExample.Criteria;
import cn.qhjys.mall.entity.FqAcardPrize;
import cn.qhjys.mall.entity.FqAcardPrizeExample;
import cn.qhjys.mall.entity.FqAcardTime;
import cn.qhjys.mall.entity.FqAcardTimeExample;
import cn.qhjys.mall.mapper.FqAcardMapper;
import cn.qhjys.mall.mapper.FqAcardPrizeMapper;
import cn.qhjys.mall.mapper.FqAcardTimeMapper;
import cn.qhjys.mall.service.system.FqACardService;

@Service("fqACardService")
public class FqACardServiceImpl implements FqACardService {
	
	@Autowired
	FqAcardMapper fqAcardMapper;
	@Autowired
	FqAcardPrizeMapper fqAcardPrizeMapper;
	@Autowired
	FqAcardTimeMapper fqAcardTimeMapper;

	@Override
	public boolean insertAcardAndPrizeAndTime(String acardName,Long storeId,String storeName,String beginDate,
			String endDate,String[] startTime,String[] endTime,Integer pushNum,String[] prizeName,
			Integer[] prizeLine,String[] prizeDesc,String[] imgs,Integer[] probability) throws Exception {
		
		if (StringUtils.isEmpty(acardName) || storeId == null || StringUtils.isEmpty(beginDate)
				|| StringUtils.isEmpty(endDate) || pushNum == null) {
			return false;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		FqAcard fqAcard = new FqAcard();
		fqAcard.setStoreId(storeId);
		fqAcard.setAcardName(acardName);
		fqAcard.setStoreName(storeName);
		fqAcard.setBeginDate(sdf.parse(beginDate));
		fqAcard.setEndDate(sdf.parse(endDate));
		fqAcard.setPushNum(pushNum);
		fqAcard.setStatus(0);
		fqAcard.setCreateTime(new Date());
		int result = fqAcardMapper.insertSelective(fqAcard);
		if (result <= 0) {
			throw new Exception("insert error");
		}
		
		for (int i = 0; i < prizeName.length; i++) {
			if (StringUtils.isEmpty(prizeName[i]) || prizeLine[i] == null || 
					StringUtils.isEmpty(prizeDesc[i]) || StringUtils.isEmpty(imgs[i])
					|| probability[i] == null) {
				throw new IllegalArgumentException("参数不能为空");
			}
			FqAcardPrize fqAcardPrize = new FqAcardPrize();
			fqAcardPrize.setAcardId(fqAcard.getId());
			fqAcardPrize.setPrizeName(prizeName[i]);
			fqAcardPrize.setPrizeLine(new BigDecimal(prizeLine[i]));
			fqAcardPrize.setPrizeInfo(prizeDesc[i]);
			fqAcardPrize.setPrizeImage(imgs[i]);
			fqAcardPrize.setProbability(probability[i]);
			
			int resultPrize = fqAcardPrizeMapper.insertSelective(fqAcardPrize);
			if (resultPrize <= 0) {
				throw new Exception("insert error");
			}
		}
		
		for (int i = 0; i < startTime.length; i++) {
			if (StringUtils.isEmpty(startTime[i]) || StringUtils.isEmpty(endTime[i])) {
				throw new IllegalArgumentException("参数不能为空");
			}
			String[] startStr = startTime[i].split(":");
			Integer startHour = Integer.parseInt(startStr[0]);
			Integer startMinute = Integer.parseInt(startStr[1]);
			Integer startTimeInt = startHour * 60 + startMinute;
			
			String[] endStr = endTime[i].split(":");
			Integer endHour = Integer.parseInt(endStr[0]);
			Integer endMinute = Integer.parseInt(endStr[1]);
			Integer endTimeInt = endHour * 60 + endMinute;
			
			FqAcardTime acardTime = new FqAcardTime();
			acardTime.setAcardId(fqAcard.getId());
			acardTime.setStoreId(storeId);
			acardTime.setStartTime(startTimeInt);
			acardTime.setStopTime(endTimeInt);
			
			int resultTime = fqAcardTimeMapper.insertSelective(acardTime);
			if (resultTime <= 0) {
				throw new Exception("insert error");
			}
		}
		
		return true;
	}

	@Override
	public Page<FqAcard> queryAcard(String activityName, String storeName, Integer pageNum,
			Integer pageSize) throws Exception {
		FqAcardExample example = new FqAcardExample();
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(activityName)) {
			criteria.andAcardNameLike("%"+activityName+"%");
		}
		if (StringUtils.isNotEmpty(storeName)) {
			criteria.andStoreNameLike("%"+storeName+"%");
		}
		example.setOrderByClause("create_time desc");
		PageHelper.startPage(pageNum, pageSize);
		Page<FqAcard> datas = (Page<FqAcard>) fqAcardMapper.selectByExample(example);
		return datas;
	}

	@Override
	public boolean deleteAcard(Long id) throws Exception {
		if (id == null) {
			return false;
		}
		int result = fqAcardMapper.deleteByPrimaryKey(id);
		return result > 0 ? true : false;
	}

	@Override
	public boolean updateAcardStatus(Long id, Integer status) throws Exception {
		if (id == null || status == null) {
			return false;
		}
		FqAcard acard = new FqAcard();
		acard.setId(id);
		acard.setStatus(status);
		int result = fqAcardMapper.updateByPrimaryKeySelective(acard);
		return result > 0 ? true : false;
	}

	@Override
	public FqAcard queryAcardById(Long id) throws Exception {
		if (id == null) {
			return null;
		}
		return fqAcardMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<FqAcardTime> queryAcardTimeByAcardId(Long acardId) throws Exception {
		if (acardId == null) {
			return null;
		}
		FqAcardTimeExample example = new FqAcardTimeExample();
		example.createCriteria().andAcardIdEqualTo(acardId);
		return fqAcardTimeMapper.selectByExample(example);
	}

	@Override
	public List<FqAcardPrize> queryAcardPrizeByAcardId(Long acardId) throws Exception {
		if (acardId == null) {
			return null;
		}
		FqAcardPrizeExample example = new FqAcardPrizeExample();
		example.createCriteria().andAcardIdEqualTo(acardId);
		return fqAcardPrizeMapper.selectByExample(example);
	}

	@Override
	public boolean updateAcardAndPrizeAndTime(Long acardId, String acardName, Long storeId,String storeName,
			String beginDate,String endDate, Long[] timeIds, String[] startTime, String[] endTime,
			 String[] startTimeNew,String[] endTimeNew, Integer pushNum, Long[] prizeIds,
			String[] prizeName, String[] prizeLine,
			String[] prizeDesc, String[] imgs, Integer[] probability) throws Exception {
		
		if (acardId == null || StringUtils.isEmpty(acardName) || storeId == null
				|| StringUtils.isEmpty(beginDate) || StringUtils.isEmpty(endDate)
				|| pushNum == null || StringUtils.isEmpty(storeName)) {
			return false;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		FqAcard fqAcard = new FqAcard();
		fqAcard.setId(acardId);
		fqAcard.setStoreId(storeId);
		fqAcard.setAcardName(acardName);
		fqAcard.setStoreName(storeName);
		fqAcard.setBeginDate(sdf.parse(beginDate));
		fqAcard.setEndDate(sdf.parse(endDate));
		fqAcard.setPushNum(pushNum);
		fqAcard.setStatus(0);
		int result = fqAcardMapper.updateByPrimaryKeySelective(fqAcard);
		if (result <= 0) {
			throw new Exception("update error");
		}
		
		for (int i = 0; i < prizeName.length; i++) {
			if (StringUtils.isEmpty(prizeName[i]) || StringUtils.isEmpty(prizeLine[i]) || 
					StringUtils.isEmpty(prizeDesc[i]) || StringUtils.isEmpty(imgs[i])
					|| probability[i] == null || prizeIds[i] == null) {
				throw new IllegalArgumentException("参数不能为空");
			}
			FqAcardPrize fqAcardPrize = new FqAcardPrize();
			fqAcardPrize.setId(prizeIds[i]);
			fqAcardPrize.setAcardId(fqAcard.getId());
			fqAcardPrize.setPrizeName(prizeName[i]);
			fqAcardPrize.setPrizeLine(new BigDecimal(prizeLine[i]));
			fqAcardPrize.setPrizeInfo(prizeDesc[i]);
			fqAcardPrize.setPrizeImage(imgs[i]);
			fqAcardPrize.setProbability(probability[i]);
			int resultPrize = fqAcardPrizeMapper.updateByPrimaryKeySelective(fqAcardPrize);
			if (resultPrize <= 0) {
				throw new Exception("update error");
			}
		}
		
		for (int i = 0; i < startTime.length; i++) {
			if (StringUtils.isEmpty(startTime[i]) || StringUtils.isEmpty(endTime[i])
					|| timeIds[i] == null) {
				throw new IllegalArgumentException("参数不能为空");
			}
			String[] startStr = startTime[i].split(":");
			Integer startHour = Integer.parseInt(startStr[0]);
			Integer startMinute = Integer.parseInt(startStr[1]);
			Integer startTimeInt = startHour * 60 + startMinute;
			
			String[] endStr = endTime[i].split(":");
			Integer endHour = Integer.parseInt(endStr[0]);
			Integer endMinute = Integer.parseInt(endStr[1]);
			Integer endTimeInt = endHour * 60 + endMinute;
			
			FqAcardTime acardTime = new FqAcardTime();
			acardTime.setId(timeIds[i]);
			acardTime.setAcardId(fqAcard.getId());
			acardTime.setStoreId(storeId);
			acardTime.setStartTime(startTimeInt);
			acardTime.setStopTime(endTimeInt);
			
			int resultTime = fqAcardTimeMapper.updateByPrimaryKeySelective(acardTime);
			if (resultTime <= 0) {
				throw new Exception("update error");
			}
		}
		
		if (startTimeNew != null && startTimeNew.length > 0) {
			for (int j = 0; j < startTimeNew.length; j++) {
				if (StringUtils.isEmpty(startTimeNew[j]) || StringUtils.isEmpty(endTimeNew[j])) {
					throw new IllegalArgumentException("参数不能为空");
				}
				String[] startStr = startTimeNew[j].split(":");
				Integer startHour = Integer.parseInt(startStr[0]);
				Integer startMinute = Integer.parseInt(startStr[1]);
				Integer startTimeInt = startHour * 60 + startMinute;
				
				String[] endStr = endTimeNew[j].split(":");
				Integer endHour = Integer.parseInt(endStr[0]);
				Integer endMinute = Integer.parseInt(endStr[1]);
				Integer endTimeInt = endHour * 60 + endMinute;
				
				FqAcardTime acardTime = new FqAcardTime();
				acardTime.setAcardId(fqAcard.getId());
				acardTime.setStoreId(storeId);
				acardTime.setStartTime(startTimeInt);
				acardTime.setStopTime(endTimeInt);
				int resultNewTime = fqAcardTimeMapper.insertSelective(acardTime);
				if (resultNewTime <= 0) {
					throw new Exception("insert error");
				}
			}
		}
		
		return true;
	}
	
}
