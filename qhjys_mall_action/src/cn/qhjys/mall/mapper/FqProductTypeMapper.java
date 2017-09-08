package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqProductType;
import cn.qhjys.mall.entity.FqProductTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqProductTypeMapper {
    int countByExample(FqProductTypeExample example);

    int deleteByExample(FqProductTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqProductType record);

    int insertSelective(FqProductType record);

    List<FqProductType> selectByExample(FqProductTypeExample example);

    FqProductType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqProductType record, @Param("example") FqProductTypeExample example);

    int updateByExample(@Param("record") FqProductType record, @Param("example") FqProductTypeExample example);

    int updateByPrimaryKeySelective(FqProductType record);

    int updateByPrimaryKey(FqProductType record);
}