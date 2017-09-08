package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.ProvinceInfo;
import cn.qhjys.mall.entity.ProvinceInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProvinceInfoMapper {
    int countByExample(ProvinceInfoExample example);

    int deleteByExample(ProvinceInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProvinceInfo record);

    int insertSelective(ProvinceInfo record);

    List<ProvinceInfo> selectByExample(ProvinceInfoExample example);

    ProvinceInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProvinceInfo record, @Param("example") ProvinceInfoExample example);

    int updateByExample(@Param("record") ProvinceInfo record, @Param("example") ProvinceInfoExample example);

    int updateByPrimaryKeySelective(ProvinceInfo record);

    int updateByPrimaryKey(ProvinceInfo record);
}