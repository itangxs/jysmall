package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqStore;
import cn.qhjys.mall.entity.FqStoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqStoreMapper {
    long countByExample(FqStoreExample example);

    int deleteByExample(FqStoreExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqStore record);

    int insertSelective(FqStore record);

    List<FqStore> selectByExample(FqStoreExample example);

    FqStore selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqStore record, @Param("example") FqStoreExample example);

    int updateByExample(@Param("record") FqStore record, @Param("example") FqStoreExample example);

    int updateByPrimaryKeySelective(FqStore record);

    int updateByPrimaryKey(FqStore record);
}