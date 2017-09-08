package cn.qhjys.mall.service.fq;

import java.util.List;

import cn.qhjys.mall.entity.FqAcardPrize;
import cn.qhjys.mall.entity.FqAcardUserLottery;
import cn.qhjys.mall.vo.AcardLotteryInfoVo;
import cn.qhjys.mall.vo.AcardPrizeLotteryVo;

public interface FqAcardUserLotteryService {

	FqAcardPrize acardRaffle (Long acardId,
			Long userId,Long recordId) throws Exception;
	
	FqAcardUserLottery queryAcardLotteryByUser (
			Long userId,Long recordId) throws Exception;
	
	List<AcardPrizeLotteryVo> queryAcardPrizeLotteryByRecordId(Long recordId) throws Exception;
	
	List<AcardLotteryInfoVo> queryAcardLotteryInfo(Long recordId) throws Exception;
	
}
