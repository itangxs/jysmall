package cn.qhjys.mall.service.fq;

import cn.qhjys.mall.entity.FqAcard;

public interface FqAcardWebService {

	FqAcard queryAcardByStoreIdAndStatus(Long storeId) throws Exception;
	
}
