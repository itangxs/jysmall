package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.CashIntegralLog;
import cn.qhjys.mall.entity.CashIntegralLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CashIntegralLogMapper {
    int countByExample(CashIntegralLogExample example);

    int deleteByExample(CashIntegralLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CashIntegralLog record);

    int insertSelective(CashIntegralLog record);

    List<CashIntegralLog> selectByExample(CashIntegralLogExample example);

    CashIntegralLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CashIntegralLog record, @Param("example") CashIntegralLogExample example);

    int updateByExample(@Param("record") CashIntegralLog record, @Param("example") CashIntegralLogExample example);

    int updateByPrimaryKeySelective(CashIntegralLog record);

    int updateByPrimaryKey(CashIntegralLog record);
}