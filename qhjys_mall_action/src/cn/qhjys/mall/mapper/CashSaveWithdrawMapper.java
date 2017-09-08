package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.CashSaveWithdraw;
import cn.qhjys.mall.entity.CashSaveWithdrawExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CashSaveWithdrawMapper {
    long countByExample(CashSaveWithdrawExample example);

    int deleteByExample(CashSaveWithdrawExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CashSaveWithdraw record);

    int insertSelective(CashSaveWithdraw record);

    List<CashSaveWithdraw> selectByExample(CashSaveWithdrawExample example);

    CashSaveWithdraw selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CashSaveWithdraw record, @Param("example") CashSaveWithdrawExample example);

    int updateByExample(@Param("record") CashSaveWithdraw record, @Param("example") CashSaveWithdrawExample example);

    int updateByPrimaryKeySelective(CashSaveWithdraw record);

    int updateByPrimaryKey(CashSaveWithdraw record);
}