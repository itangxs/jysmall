package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.ActivitiesInfo;
import cn.qhjys.mall.entity.ActivitiesInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivitiesInfoMapper {
    int countByExample(ActivitiesInfoExample example);

    int deleteByExample(ActivitiesInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ActivitiesInfo record);

    int insertSelective(ActivitiesInfo record);

    List<ActivitiesInfo> selectByExample(ActivitiesInfoExample example);

    ActivitiesInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ActivitiesInfo record, @Param("example") ActivitiesInfoExample example);

    int updateByExample(@Param("record") ActivitiesInfo record, @Param("example") ActivitiesInfoExample example);

    int updateByPrimaryKeySelective(ActivitiesInfo record);

    int updateByPrimaryKey(ActivitiesInfo record);
}