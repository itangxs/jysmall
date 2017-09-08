package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqAcardUserExchange;
import cn.qhjys.mall.entity.FqAcardUserExchangeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqAcardUserExchangeMapper {
    long countByExample(FqAcardUserExchangeExample example);

    int deleteByExample(FqAcardUserExchangeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqAcardUserExchange record);

    int insertSelective(FqAcardUserExchange record);

    List<FqAcardUserExchange> selectByExample(FqAcardUserExchangeExample example);

    FqAcardUserExchange selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqAcardUserExchange record, @Param("example") FqAcardUserExchangeExample example);

    int updateByExample(@Param("record") FqAcardUserExchange record, @Param("example") FqAcardUserExchangeExample example);

    int updateByPrimaryKeySelective(FqAcardUserExchange record);

    int updateByPrimaryKey(FqAcardUserExchange record);
}