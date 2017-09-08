package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.AdInfo;
import cn.qhjys.mall.entity.AdInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdInfoMapper {
    int countByExample(AdInfoExample example);

    int deleteByExample(AdInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdInfo record);

    int insertSelective(AdInfo record);

    List<AdInfo> selectByExampleWithBLOBs(AdInfoExample example);

    List<AdInfo> selectByExample(AdInfoExample example);

    AdInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdInfo record, @Param("example") AdInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") AdInfo record, @Param("example") AdInfoExample example);

    int updateByExample(@Param("record") AdInfo record, @Param("example") AdInfoExample example);

    int updateByPrimaryKeySelective(AdInfo record);

    int updateByPrimaryKeyWithBLOBs(AdInfo record);

    int updateByPrimaryKey(AdInfo record);
}