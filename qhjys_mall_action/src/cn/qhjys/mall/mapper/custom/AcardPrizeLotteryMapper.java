package cn.qhjys.mall.mapper.custom;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.qhjys.mall.vo.AcardLotteryUserInfo;
import cn.qhjys.mall.vo.AcardPrizeLotteryVo;

public interface AcardPrizeLotteryMapper {

	List<AcardPrizeLotteryVo> selectAcardPrizeLotteryByRecordId(@Param("recordId") Long recordId) throws Exception;
	
	List<AcardLotteryUserInfo> selectLotteryUserInfo(@Param("recordId") Long recordId,
			@Param("prizeId") Long prizeId) throws Exception;
}
