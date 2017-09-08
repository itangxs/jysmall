package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.CollectInfo;
import cn.qhjys.mall.entity.CollectInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectInfoMapper {
    int countByExample(CollectInfoExample example);

    int deleteByExample(CollectInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CollectInfo record);

    int insertSelective(CollectInfo record);

    List<CollectInfo> selectByExample(CollectInfoExample example);

    CollectInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CollectInfo record, @Param("example") CollectInfoExample example);

    int updateByExample(@Param("record") CollectInfo record, @Param("example") CollectInfoExample example);

    int updateByPrimaryKeySelective(CollectInfo record);

    int updateByPrimaryKey(CollectInfo record);
}