package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.CardUserLottery;
import cn.qhjys.mall.entity.CardUserLotteryExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CardUserLotteryMapper {
 
	long countByExample(CardUserLotteryExample example);

	int deleteByExample(CardUserLotteryExample example);

	int deleteByPrimaryKey(Long id);

	int insert(CardUserLottery record);

	int insertSelective(CardUserLottery record);

	List<CardUserLottery> selectByExample(CardUserLotteryExample example);

	CardUserLottery selectByPrimaryKey(Long id);

	int updateByExampleSelective(@Param("record") CardUserLottery record,
			@Param("example") CardUserLotteryExample example);

	int updateByExample(@Param("record") CardUserLottery record, @Param("example") CardUserLotteryExample example);

	int updateByPrimaryKeySelective(CardUserLottery record);

	int updateByPrimaryKey(CardUserLottery record);

	int updateShareNumByOpenIdAndOrderId(CardUserLottery record);
	
	/**
	 * 统计商家  在时间段内的分享数
	 * sellerId,
       startDate
       endDate
	 * @param map
	 * @return
	 */
	Long countByShareNumBySellerIdAndDate(Map map);
}