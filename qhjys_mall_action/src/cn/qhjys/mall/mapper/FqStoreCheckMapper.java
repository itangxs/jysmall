package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqStoreCheck;
import cn.qhjys.mall.entity.FqStoreCheckExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqStoreCheckMapper {
    int countByExample(FqStoreCheckExample example);

    int deleteByExample(FqStoreCheckExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqStoreCheck record);

    int insertSelective(FqStoreCheck record);

    List<FqStoreCheck> selectByExample(FqStoreCheckExample example);

    FqStoreCheck selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqStoreCheck record, @Param("example") FqStoreCheckExample example);

    int updateByExample(@Param("record") FqStoreCheck record, @Param("example") FqStoreCheckExample example);

    int updateByPrimaryKeySelective(FqStoreCheck record);

    int updateByPrimaryKey(FqStoreCheck record);
}