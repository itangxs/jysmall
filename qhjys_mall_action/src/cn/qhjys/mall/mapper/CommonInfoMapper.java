package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.CommonInfo;
import cn.qhjys.mall.entity.CommonInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommonInfoMapper {
    int countByExample(CommonInfoExample example);

    int deleteByExample(CommonInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CommonInfo record);

    int insertSelective(CommonInfo record);

    List<CommonInfo> selectByExample(CommonInfoExample example);

    CommonInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CommonInfo record, @Param("example") CommonInfoExample example);

    int updateByExample(@Param("record") CommonInfo record, @Param("example") CommonInfoExample example);

    int updateByPrimaryKeySelective(CommonInfo record);

    int updateByPrimaryKey(CommonInfo record);
}