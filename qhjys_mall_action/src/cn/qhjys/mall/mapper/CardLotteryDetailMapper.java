package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.CardLotteryDetail;
import cn.qhjys.mall.entity.CardLotteryDetailExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CardLotteryDetailMapper {

	long countByExample(CardLotteryDetailExample example);

	int deleteByExample(CardLotteryDetailExample example);

	int deleteByPrimaryKey(Long id);

	int insert(CardLotteryDetail record);

	int insertSelective(CardLotteryDetail record);

	List<CardLotteryDetail> selectByExample(CardLotteryDetailExample example);

	CardLotteryDetail selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") CardLotteryDetail record,
			@Param("example") CardLotteryDetailExample example);

	int updateByExample(@Param("record") CardLotteryDetail record, @Param("example") CardLotteryDetailExample example);

	int updateByPrimaryKeySelective(CardLotteryDetail record);

	int updateByPrimaryKey(CardLotteryDetail record);

	List<Map> selectUserFriendLotteryDetail(Map map);
	
	/**
	 * 统计卡券 时间段内的分享领取数
	 * cardTemplateId
	 * startDate
 	 * endDate
	 * @param map
	 * @return
	 */
	Long countByShareReceiveNumByCouponIdAndDate(Map map);
}