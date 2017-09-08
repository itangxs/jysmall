package cn.qhjys.mall.service.fq;

import cn.qhjys.mall.entity.FqAcardUserExchange;

public interface FqAcardUserExchangeService {

	int insertAcardUserExchangeOnce(Long userId,Long recordId,
			Long prizeId,Long storeId) throws Exception;
	
	boolean updateAcardUserExchangeByKey(FqAcardUserExchange acardUserExchange) throws Exception;
	
	boolean updateAcardUserExchangeByOpenId(String openId) throws Exception;
}
