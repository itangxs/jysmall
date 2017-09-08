package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.CashAccount;
import cn.qhjys.mall.entity.CashAccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CashAccountMapper {
    int countByExample(CashAccountExample example);

    int deleteByExample(CashAccountExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CashAccount record);

    int insertSelective(CashAccount record);

    List<CashAccount> selectByExample(CashAccountExample example);

    CashAccount selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CashAccount record, @Param("example") CashAccountExample example);

    int updateByExample(@Param("record") CashAccount record, @Param("example") CashAccountExample example);

    int updateByPrimaryKeySelective(CashAccount record);

    int updateByPrimaryKey(CashAccount record);
}