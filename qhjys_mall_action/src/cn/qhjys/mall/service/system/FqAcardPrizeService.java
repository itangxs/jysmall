package cn.qhjys.mall.service.system;

import java.util.List;

import cn.qhjys.mall.entity.FqAcardPrize;

public interface FqAcardPrizeService {
	
	List<FqAcardPrize> queryAcardPrizeByAcardId(Long acardId) throws Exception;

}
