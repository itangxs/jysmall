package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.LotteryDish;
import cn.qhjys.mall.entity.LotteryDishExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LotteryDishMapper {
    int countByExample(LotteryDishExample example);

    int deleteByExample(LotteryDishExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LotteryDish record);

    int insertSelective(LotteryDish record);

    List<LotteryDish> selectByExample(LotteryDishExample example);

    LotteryDish selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LotteryDish record, @Param("example") LotteryDishExample example);

    int updateByExample(@Param("record") LotteryDish record, @Param("example") LotteryDishExample example);

    int updateByPrimaryKeySelective(LotteryDish record);

    int updateByPrimaryKey(LotteryDish record);
}