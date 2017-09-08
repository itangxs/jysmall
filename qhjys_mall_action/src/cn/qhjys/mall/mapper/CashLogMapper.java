package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.CashLog;
import cn.qhjys.mall.entity.CashLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CashLogMapper {
    int countByExample(CashLogExample example);

    int deleteByExample(CashLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CashLog record);

    int insertSelective(CashLog record);

    List<CashLog> selectByExample(CashLogExample example);

    CashLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CashLog record, @Param("example") CashLogExample example);

    int updateByExample(@Param("record") CashLog record, @Param("example") CashLogExample example);

    int updateByPrimaryKeySelective(CashLog record);

    int updateByPrimaryKey(CashLog record);
}