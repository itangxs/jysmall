package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqBcardRule;
import cn.qhjys.mall.entity.FqBcardRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqBcardRuleMapper {
    int countByExample(FqBcardRuleExample example);

    int deleteByExample(FqBcardRuleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqBcardRule record);

    int insertSelective(FqBcardRule record);

    List<FqBcardRule> selectByExample(FqBcardRuleExample example);

    FqBcardRule selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqBcardRule record, @Param("example") FqBcardRuleExample example);

    int updateByExample(@Param("record") FqBcardRule record, @Param("example") FqBcardRuleExample example);

    int updateByPrimaryKeySelective(FqBcardRule record);

    int updateByPrimaryKey(FqBcardRule record);
}