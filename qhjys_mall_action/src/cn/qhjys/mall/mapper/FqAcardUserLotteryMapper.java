package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqAcardUserLottery;
import cn.qhjys.mall.entity.FqAcardUserLotteryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqAcardUserLotteryMapper {
    long countByExample(FqAcardUserLotteryExample example);

    int deleteByExample(FqAcardUserLotteryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqAcardUserLottery record);

    int insertSelective(FqAcardUserLottery record);

    List<FqAcardUserLottery> selectByExample(FqAcardUserLotteryExample example);

    FqAcardUserLottery selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqAcardUserLottery record, @Param("example") FqAcardUserLotteryExample example);

    int updateByExample(@Param("record") FqAcardUserLottery record, @Param("example") FqAcardUserLotteryExample example);

    int updateByPrimaryKeySelective(FqAcardUserLottery record);

    int updateByPrimaryKey(FqAcardUserLottery record);
}