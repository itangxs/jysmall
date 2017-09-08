package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.StoreInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StoreInfoMapper {
    int countByExample(StoreInfoExample example);

    int deleteByExample(StoreInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(StoreInfo record);

    int insertSelective(StoreInfo record);

    List<StoreInfo> selectByExampleWithBLOBs(StoreInfoExample example);

    List<StoreInfo> selectByExample(StoreInfoExample example);

    StoreInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") StoreInfo record, @Param("example") StoreInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") StoreInfo record, @Param("example") StoreInfoExample example);

    int updateByExample(@Param("record") StoreInfo record, @Param("example") StoreInfoExample example);

    int updateByPrimaryKeySelective(StoreInfo record);

    int updateByPrimaryKeyWithBLOBs(StoreInfo record);

    int updateByPrimaryKey(StoreInfo record);
    
    Integer selectScope(Long rebateId);
}