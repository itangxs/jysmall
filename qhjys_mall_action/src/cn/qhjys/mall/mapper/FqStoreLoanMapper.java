package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqStoreLoan;
import cn.qhjys.mall.entity.FqStoreLoanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqStoreLoanMapper {
    int countByExample(FqStoreLoanExample example);

    int deleteByExample(FqStoreLoanExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqStoreLoan record);

    int insertSelective(FqStoreLoan record);

    List<FqStoreLoan> selectByExample(FqStoreLoanExample example);

    FqStoreLoan selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqStoreLoan record, @Param("example") FqStoreLoanExample example);

    int updateByExample(@Param("record") FqStoreLoan record, @Param("example") FqStoreLoanExample example);

    int updateByPrimaryKeySelective(FqStoreLoan record);

    int updateByPrimaryKey(FqStoreLoan record);
}