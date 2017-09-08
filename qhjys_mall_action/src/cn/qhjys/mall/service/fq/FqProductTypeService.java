package cn.qhjys.mall.service.fq;

import java.util.List;

import cn.qhjys.mall.entity.FqProductType;

public interface FqProductTypeService {

	List<FqProductType> queryEnableProductTypeByStoreId(Long storeId);
	
}
