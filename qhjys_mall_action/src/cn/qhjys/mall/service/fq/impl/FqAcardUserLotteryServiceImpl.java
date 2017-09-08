package cn.qhjys.mall.service.fq.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.qhjys.mall.entity.FqAcardPrize;
import cn.qhjys.mall.entity.FqAcardUserLottery;
import cn.qhjys.mall.entity.FqAcardUserLotteryExample;
import cn.qhjys.mall.mapper.FqAcardUserLotteryMapper;
import cn.qhjys.mall.mapper.custom.AcardPrizeLotteryMapper;
import cn.qhjys.mall.service.fq.FqAcardUserLotteryService;
import cn.qhjys.mall.service.system.FqAcardPrizeService;
import cn.qhjys.mall.vo.AcardLotteryInfoVo;
import cn.qhjys.mall.vo.AcardLotteryUserInfo;
import cn.qhjys.mall.vo.AcardPrizeLotteryVo;

@Service("fqAcardUserLotteryService")
public class FqAcardUserLotteryServiceImpl implements FqAcardUserLotteryService{

	@Autowired
	FqAcardPrizeService fqAcardPrizeService;
	@Autowired
	FqAcardUserLotteryMapper fqAcardUserLotteryMapper;
	@Autowired
	AcardPrizeLotteryMapper acardPrizeLotteryMapper;
	
	@Override
	public FqAcardUserLottery queryAcardLotteryByUser(Long userId, Long recordId) throws Exception {
		FqAcardUserLotteryExample example = new FqAcardUserLotteryExample();
		example.createCriteria().andUserIdEqualTo(userId).andAcardRecordIdEqualTo(recordId);
		List<FqAcardUserLottery> lotteries = fqAcardUserLotteryMapper.selectByExample(example);
		if (lotteries != null && lotteries.size() > 0) {
			return lotteries.get(0);
		}
		return null;
	}
	
	@Override
	public FqAcardPrize acardRaffle(Long acardId, Long userId, Long recordId) throws Exception {
		if (acardId == null || userId == null || recordId == null) {
			return null;
		}
		// 根据A券id查询奖券
		List<FqAcardPrize> acardPrizes = fqAcardPrizeService.queryAcardPrizeByAcardId(acardId);
		if (acardPrizes == null || acardPrizes.size() == 0) {
			return null;
		}
		
		FqAcardPrize acardPrize = null;
        int randomNumber;
        randomNumber = (int) (Math.random() * 100) + 1;
        int d1 = 0;
        int d2 = 0;
        for(int i=0;i<acardPrizes.size();i++){
        	d2 += acardPrizes.get(i).getProbability();
            if(i==0) {
                d1 = 1;
            }else{
            	d1 += acardPrizes.get(i-1).getProbability();
            }
            if(randomNumber >= d1 && randomNumber <= d2){
            	acardPrize = acardPrizes.get(i);
                break;
            }
        }
        
        FqAcardUserLottery record = new FqAcardUserLottery();
        record.setAcardRecordId(recordId);
        record.setAcardId(acardId);
        record.setUserId(userId);
        record.setAcardPrizeId(acardPrize.getId());
        record.setAcardPrizeName(acardPrize.getPrizeName());
        record.setCreateTime(new Date());
        int result = fqAcardUserLotteryMapper.insertSelective(record);
        return result > 0 ? acardPrize : null;
	}

	@Override
	public List<AcardPrizeLotteryVo> queryAcardPrizeLotteryByRecordId(Long recordId) throws Exception {
		if (recordId == null) {
			return null;
		}
		List<AcardPrizeLotteryVo> lotteryVos = acardPrizeLotteryMapper.selectAcardPrizeLotteryByRecordId(recordId);
		return lotteryVos;
	}

	@Override
	public List<AcardLotteryInfoVo> queryAcardLotteryInfo(Long recordId) throws Exception {
		if (recordId == null) {
			return null;
		}
		// 先查抽中有几种奖品
		List<AcardPrizeLotteryVo> acardPrizeLotteryVos = queryAcardPrizeLotteryByRecordId(recordId);
		if (acardPrizeLotteryVos != null && acardPrizeLotteryVos.size() > 0) {
			List<AcardLotteryInfoVo> lotteryInfoVos = new ArrayList<>();
			for (int i = 0; i < acardPrizeLotteryVos.size(); i++) {
				// 查每一种奖品的抽中人信息
				AcardPrizeLotteryVo acardPrizeLotteryVo = acardPrizeLotteryVos.get(i);
				List<AcardLotteryUserInfo> lotteryUserInfos = acardPrizeLotteryMapper.selectLotteryUserInfo(recordId, acardPrizeLotteryVo.getPrizeId());
				AcardLotteryInfoVo acardLotteryInfoVo = new AcardLotteryInfoVo();
				acardLotteryInfoVo.setPrizeId(acardPrizeLotteryVo.getPrizeId());
				acardLotteryInfoVo.setPrizeName(acardPrizeLotteryVo.getPrizeName());
				acardLotteryInfoVo.setInfos(lotteryUserInfos);
				lotteryInfoVos.add(acardLotteryInfoVo);
			}
			return lotteryInfoVos;
		}
		return null;
	}

}
