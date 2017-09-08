package cn.qhjys.mall.service.fq;

import cn.qhjys.mall.entity.FqRedpack;
import cn.qhjys.mall.entity.FqRedpackRecord;

public interface FqRedpackService {
	public FqRedpack getRedpackByStore(Long storeId);
	
	public FqRedpackRecord getredpackRecord(Long id);
	
	public int updateRedpackRecordStatus(Long id,String path) throws Exception;
	
	public FqRedpackRecord getFqRedpackRecordByOrderId(Long orderId);
	
	public int updateRedpackRecordStatusByDo(Long id);
	
	public FqRedpackRecord insertFqRedpackRecordByorder(Long orderId)throws Exception;
	
	public FqRedpackRecord insertFqRedpackRecordByfqorder(Long orderId)throws Exception;
	
	public int updateFqRedpackRecordBySend()throws Exception;
	
	public int updateFqRedpackRecordByOpenidAndstatus(String openId);
	
}
