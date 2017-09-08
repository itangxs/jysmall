package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.StoreCheck;
import cn.qhjys.mall.entity.StoreCheckExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StoreCheckMapper {
    int countByExample(StoreCheckExample example);

    int deleteByExample(StoreCheckExample example);

    int deleteByPrimaryKey(Long id);

    int insert(StoreCheck record);

    int insertSelective(StoreCheck record);

    List<StoreCheck> selectByExampleWithBLOBs(StoreCheckExample example);

    List<StoreCheck> selectByExample(StoreCheckExample example);

    StoreCheck selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") StoreCheck record, @Param("example") StoreCheckExample example);

    int updateByExampleWithBLOBs(@Param("record") StoreCheck record, @Param("example") StoreCheckExample example);

    int updateByExample(@Param("record") StoreCheck record, @Param("example") StoreCheckExample example);

    int updateByPrimaryKeySelective(StoreCheck record);

    int updateByPrimaryKeyWithBLOBs(StoreCheck record);

    int updateByPrimaryKey(StoreCheck record);
}