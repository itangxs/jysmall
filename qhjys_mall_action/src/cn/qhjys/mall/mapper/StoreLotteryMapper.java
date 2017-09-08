package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.StoreLottery;
import cn.qhjys.mall.entity.StoreLotteryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StoreLotteryMapper {
    int countByExample(StoreLotteryExample example);

    int deleteByExample(StoreLotteryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(StoreLottery record);

    int insertSelective(StoreLottery record);

    List<StoreLottery> selectByExample(StoreLotteryExample example);

    StoreLottery selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") StoreLottery record, @Param("example") StoreLotteryExample example);

    int updateByExample(@Param("record") StoreLottery record, @Param("example") StoreLotteryExample example);

    int updateByPrimaryKeySelective(StoreLottery record);

    int updateByPrimaryKey(StoreLottery record);
}