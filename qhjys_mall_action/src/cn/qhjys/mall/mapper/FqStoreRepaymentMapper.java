package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqStoreRepayment;
import cn.qhjys.mall.entity.FqStoreRepaymentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqStoreRepaymentMapper {
    int countByExample(FqStoreRepaymentExample example);

    int deleteByExample(FqStoreRepaymentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqStoreRepayment record);

    int insertSelective(FqStoreRepayment record);

    List<FqStoreRepayment> selectByExample(FqStoreRepaymentExample example);

    FqStoreRepayment selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqStoreRepayment record, @Param("example") FqStoreRepaymentExample example);

    int updateByExample(@Param("record") FqStoreRepayment record, @Param("example") FqStoreRepaymentExample example);

    int updateByPrimaryKeySelective(FqStoreRepayment record);

    int updateByPrimaryKey(FqStoreRepayment record);
}