package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.VolumeInfo;
import cn.qhjys.mall.entity.VolumeInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VolumeInfoMapper {
    int countByExample(VolumeInfoExample example);

    int deleteByExample(VolumeInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(VolumeInfo record);

    int insertSelective(VolumeInfo record);

    List<VolumeInfo> selectByExample(VolumeInfoExample example);

    VolumeInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") VolumeInfo record, @Param("example") VolumeInfoExample example);

    int updateByExample(@Param("record") VolumeInfo record, @Param("example") VolumeInfoExample example);

    int updateByPrimaryKeySelective(VolumeInfo record);

    int updateByPrimaryKey(VolumeInfo record);
}