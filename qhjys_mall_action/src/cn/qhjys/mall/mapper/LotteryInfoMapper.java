package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.LotteryInfo;
import cn.qhjys.mall.entity.LotteryInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LotteryInfoMapper {
    int countByExample(LotteryInfoExample example);

    int deleteByExample(LotteryInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LotteryInfo record);

    int insertSelective(LotteryInfo record);

    List<LotteryInfo> selectByExample(LotteryInfoExample example);

    LotteryInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LotteryInfo record, @Param("example") LotteryInfoExample example);

    int updateByExample(@Param("record") LotteryInfo record, @Param("example") LotteryInfoExample example);

    int updateByPrimaryKeySelective(LotteryInfo record);

    int updateByPrimaryKey(LotteryInfo record);
}