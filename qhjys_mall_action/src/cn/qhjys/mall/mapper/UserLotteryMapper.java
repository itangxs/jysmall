package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.UserLottery;
import cn.qhjys.mall.entity.UserLotteryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserLotteryMapper {
    int countByExample(UserLotteryExample example);

    int deleteByExample(UserLotteryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserLottery record);

    int insertSelective(UserLottery record);

    List<UserLottery> selectByExample(UserLotteryExample example);

    UserLottery selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserLottery record, @Param("example") UserLotteryExample example);

    int updateByExample(@Param("record") UserLottery record, @Param("example") UserLotteryExample example);

    int updateByPrimaryKeySelective(UserLottery record);

    int updateByPrimaryKey(UserLottery record);
}