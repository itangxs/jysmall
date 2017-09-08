package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqSellerStatement;
import cn.qhjys.mall.entity.FqSellerStatementExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface FqSellerStatementMapper {
    int countByExample(FqSellerStatementExample example);

    int deleteByExample(FqSellerStatementExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqSellerStatement record);

    int insertSelective(FqSellerStatement record);

    List<FqSellerStatement> selectByExample(FqSellerStatementExample example);

    FqSellerStatement selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqSellerStatement record, @Param("example") FqSellerStatementExample example);

    int updateByExample(@Param("record") FqSellerStatement record, @Param("example") FqSellerStatementExample example);

    int updateByPrimaryKeySelective(FqSellerStatement record);

    int updateByPrimaryKey(FqSellerStatement record);
    
    Map<String,Object> selectThirdCount(Map<String,Object> wheremap);
}