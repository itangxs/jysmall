package cn.qhjys.mall.service.fq;

import java.io.IOException;
import java.util.List;

import com.github.pagehelper.Page;

import cn.qhjys.mall.entity.FqStoreCredit;
import cn.qhjys.mall.entity.FqThirdPay;
import cn.qhjys.mall.vo.system.FqStoreCreditVo;

public interface FqStoreCreditService {

	Page<FqStoreCredit> queryFqStoreCredit(Long wxstoreid,Integer period,Integer page,Integer pagesize) throws Exception;
	
	boolean updateCreditStatus(Long[] ids,Integer status) throws Exception;
	
	Page<FqStoreCreditVo> queryFqStoreFromCredit(Long wxstoreid,String wxstorename,Integer pageNum,Integer pagesize) throws Exception;
	
	FqStoreCredit queryLastCreditByStore(Long storeId) throws Exception;
	
	boolean insertFqStoreCredit(FqStoreCredit fqStoreCredit) throws Exception;
	
	Page<FqStoreCredit> queryFqStoreCredit(String wxstorename,Integer page,Integer pagesize) throws Exception;
	
	public FqStoreCredit getLastCredit(Long sellerId);
	
	public List<FqThirdPay> queryFqThirdPayBySeller(Long sellerId,Integer pageSize,Integer pageNum);
	
	public boolean updateStoreCreditStatus()throws IOException;
}
