package cn.qhjys.mall.service.fq.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.FqStoreApply;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.mapper.FqStoreApplyMapper;
import cn.qhjys.mall.service.fq.FqStoreApplyService;

@Service("fqStoreApplyService")
public class FqStoreApplyServiceImpl implements FqStoreApplyService{
	
	@Autowired
	private FqStoreApplyMapper fqStoreApplyMapper;
	
	public int insertFqStoreApply(SellerInfo sellerInfo, StoreInfo storeInfo){
		FqStoreApply fsa = new FqStoreApply();
		fsa.setSellerId(sellerInfo.getId());
		fsa.setUsername(sellerInfo.getUsername());
		fsa.setStatus(0);
		fsa.setCreateTime(new Date()); 
		if(null != storeInfo){
			fsa.setStoreId(storeInfo.getId());
			fsa.setStoreName(storeInfo.getName());
		}
		return fqStoreApplyMapper.insertSelective(fsa);
	}
}
