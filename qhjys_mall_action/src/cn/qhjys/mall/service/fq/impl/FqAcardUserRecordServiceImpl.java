package cn.qhjys.mall.service.fq.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.qhjys.mall.entity.FqAcardUserRecord;
import cn.qhjys.mall.entity.FqAcardUserRecordExample;
import cn.qhjys.mall.entity.FqAcardUserRecordExample.Criteria;
import cn.qhjys.mall.mapper.FqAcardUserRecordMapper;
import cn.qhjys.mall.service.fq.FqAcardUserRecordService;

@Service("fqAcardUserRecordService")
public class FqAcardUserRecordServiceImpl implements FqAcardUserRecordService{

	@Autowired
	FqAcardUserRecordMapper fqAcardUserRecordMapper;
	
	@Override
	public Long insertUserRecordOnce(Long orderId, Long storeId, Long acardId, Long userId
			,Integer type) throws Exception {
		if (orderId == null || storeId == null || acardId == null
				 || userId == null) {
			return null;
		}
		
		// 先查询是否已经存在
		FqAcardUserRecordExample example = new FqAcardUserRecordExample();
		Criteria criteria = example.createCriteria();
		criteria.andAcardIdEqualTo(acardId);
		criteria.andUserIdEqualTo(userId);
		criteria.andOrderIdEqualTo(orderId);
		criteria.andStoreIdEqualTo(storeId);
		List<FqAcardUserRecord> records = fqAcardUserRecordMapper.selectByExample(example);
		if (records != null && records.size() > 0) {
			return records.get(0).getId();
		}
		
		FqAcardUserRecord record = new FqAcardUserRecord();
		record.setAcardId(acardId);
		record.setUserId(userId);
		record.setOrderId(orderId);
		record.setStoreId(storeId);
		record.setStatus(0);
		if (type != null && type == 1) {
			record.setType(1);
		}
		record.setCreateTime(new Date());
		fqAcardUserRecordMapper.insert(record);
		return record.getId();
	}

	@Override
	public boolean updateAcardUserRecordById(Long recordId,Integer status) throws Exception {
		if (recordId == null || status == null) {
			return false;
		}
		FqAcardUserRecord record = new FqAcardUserRecord();
		record.setId(recordId);
		record.setStatus(status);
		return true;
	}

	@Override
	public FqAcardUserRecord queryAcardUserRecordById(Long recordId) throws Exception {
		if (recordId == null) {
			return null;
		}
		FqAcardUserRecord acardUserRecord = fqAcardUserRecordMapper.selectByPrimaryKey(recordId);
		return acardUserRecord;
	}

	@Override
	public boolean isExchangeAcard(Long recordId) throws Exception {
		FqAcardUserRecord record = fqAcardUserRecordMapper.selectByPrimaryKey(recordId);
		if (record != null) {
			if (record.getStatus() == 1) {
				// 已经兑换
				return true;
			}else {
				return false;
			}
		}
		return true;
	}
}
