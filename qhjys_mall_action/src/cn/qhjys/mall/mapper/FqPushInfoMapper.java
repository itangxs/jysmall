package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqPushInfo;
import cn.qhjys.mall.entity.FqPushInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqPushInfoMapper {
    int countByExample(FqPushInfoExample example);

    int deleteByExample(FqPushInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqPushInfo record);

    int insertSelective(FqPushInfo record);

    List<FqPushInfo> selectByExampleWithBLOBs(FqPushInfoExample example);

    List<FqPushInfo> selectByExample(FqPushInfoExample example);

    FqPushInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqPushInfo record, @Param("example") FqPushInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") FqPushInfo record, @Param("example") FqPushInfoExample example);

    int updateByExample(@Param("record") FqPushInfo record, @Param("example") FqPushInfoExample example);

    int updateByPrimaryKeySelective(FqPushInfo record);

    int updateByPrimaryKeyWithBLOBs(FqPushInfo record);

    int updateByPrimaryKey(FqPushInfo record);
}