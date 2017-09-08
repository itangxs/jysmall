package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqStoreRate;
import cn.qhjys.mall.entity.FqStoreRateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqStoreRateMapper {
    int countByExample(FqStoreRateExample example);

    int deleteByExample(FqStoreRateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqStoreRate record);

    int insertSelective(FqStoreRate record);

    List<FqStoreRate> selectByExample(FqStoreRateExample example);

    FqStoreRate selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqStoreRate record, @Param("example") FqStoreRateExample example);

    int updateByExample(@Param("record") FqStoreRate record, @Param("example") FqStoreRateExample example);

    int updateByPrimaryKeySelective(FqStoreRate record);

    int updateByPrimaryKey(FqStoreRate record);
}