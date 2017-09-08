package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqBcardPrize;
import cn.qhjys.mall.entity.FqBcardPrizeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqBcardPrizeMapper {
    long countByExample(FqBcardPrizeExample example);

    int deleteByExample(FqBcardPrizeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqBcardPrize record);

    int insertSelective(FqBcardPrize record);

    List<FqBcardPrize> selectByExample(FqBcardPrizeExample example);

    FqBcardPrize selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqBcardPrize record, @Param("example") FqBcardPrizeExample example);

    int updateByExample(@Param("record") FqBcardPrize record, @Param("example") FqBcardPrizeExample example);

    int updateByPrimaryKeySelective(FqBcardPrize record);

    int updateByPrimaryKey(FqBcardPrize record);
}