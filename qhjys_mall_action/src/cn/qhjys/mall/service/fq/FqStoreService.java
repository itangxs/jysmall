package cn.qhjys.mall.service.fq;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

import cn.qhjys.mall.entity.FqStore;

public interface FqStoreService {
	public int insertFqStore(FqStore fqStore);
	public int updateFqStore(FqStore fqStore);
	public FqStore getFqStoreBySellerId(Long sellerId);
	public FqStore getFqStoreById(Long id);
	public Page<FqStore> queryFqStore(Map<String, Object> map);
	
	public List<FqStore> listFqStoreByDaliy();
}
