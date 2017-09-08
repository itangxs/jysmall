package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqFinancialSupport;
import cn.qhjys.mall.entity.FqFinancialSupportExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqFinancialSupportMapper {
    int countByExample(FqFinancialSupportExample example);

    int deleteByExample(FqFinancialSupportExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqFinancialSupport record);

    int insertSelective(FqFinancialSupport record);

    List<FqFinancialSupport> selectByExample(FqFinancialSupportExample example);

    FqFinancialSupport selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqFinancialSupport record, @Param("example") FqFinancialSupportExample example);

    int updateByExample(@Param("record") FqFinancialSupport record, @Param("example") FqFinancialSupportExample example);

    int updateByPrimaryKeySelective(FqFinancialSupport record);

    int updateByPrimaryKey(FqFinancialSupport record);
}