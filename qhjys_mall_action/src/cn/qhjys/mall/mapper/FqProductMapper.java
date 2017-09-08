package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqProduct;
import cn.qhjys.mall.entity.FqProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqProductMapper {
    int countByExample(FqProductExample example);

    int deleteByExample(FqProductExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqProduct record);

    int insertSelective(FqProduct record);

    List<FqProduct> selectByExample(FqProductExample example);

    FqProduct selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqProduct record, @Param("example") FqProductExample example);

    int updateByExample(@Param("record") FqProduct record, @Param("example") FqProductExample example);

    int updateByPrimaryKeySelective(FqProduct record);

    int updateByPrimaryKey(FqProduct record);
}