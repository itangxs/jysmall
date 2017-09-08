package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqAcardPrize;
import cn.qhjys.mall.entity.FqAcardPrizeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqAcardPrizeMapper {
    long countByExample(FqAcardPrizeExample example);

    int deleteByExample(FqAcardPrizeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqAcardPrize record);

    int insertSelective(FqAcardPrize record);

    List<FqAcardPrize> selectByExample(FqAcardPrizeExample example);

    FqAcardPrize selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqAcardPrize record, @Param("example") FqAcardPrizeExample example);

    int updateByExample(@Param("record") FqAcardPrize record, @Param("example") FqAcardPrizeExample example);

    int updateByPrimaryKeySelective(FqAcardPrize record);

    int updateByPrimaryKey(FqAcardPrize record);
}