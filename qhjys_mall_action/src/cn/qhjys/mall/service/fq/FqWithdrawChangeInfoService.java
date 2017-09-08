package cn.qhjys.mall.service.fq;

import java.util.Date;
import java.util.List;

import cn.qhjys.mall.entity.FqWithdrawChangeinfo;

public interface FqWithdrawChangeInfoService {

	boolean updateOrInsertWithdrawChangeInfo(Long sellerId,Integer statusAfter) throws Exception;
	
	List<FqWithdrawChangeinfo> queryFqWithdrawChangeInfoByDate(Date date) throws Exception;
	
	FqWithdrawChangeinfo queryFqWithdrawChangeInfoBySellerIdAndDate(Long sellerId,Date date) throws Exception;
}
