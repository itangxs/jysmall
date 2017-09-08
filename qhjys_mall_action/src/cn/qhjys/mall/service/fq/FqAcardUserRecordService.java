package cn.qhjys.mall.service.fq;

import cn.qhjys.mall.entity.FqAcardUserRecord;

public interface FqAcardUserRecordService {

	Long insertUserRecordOnce(Long orderId,Long storeId,Long acardId,Long userId,Integer type) throws Exception;
	
	boolean updateAcardUserRecordById(Long recordId,Integer status) throws Exception;
	
	FqAcardUserRecord queryAcardUserRecordById(Long recordId) throws Exception;
	
	boolean isExchangeAcard(Long recordId) throws Exception;
}
