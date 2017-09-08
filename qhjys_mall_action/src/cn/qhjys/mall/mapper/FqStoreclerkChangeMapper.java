package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqStoreclerkChange;
import cn.qhjys.mall.entity.FqStoreclerkChangeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqStoreclerkChangeMapper {
    int countByExample(FqStoreclerkChangeExample example);

    int deleteByExample(FqStoreclerkChangeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqStoreclerkChange record);

    int insertSelective(FqStoreclerkChange record);

    List<FqStoreclerkChange> selectByExample(FqStoreclerkChangeExample example);

    FqStoreclerkChange selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqStoreclerkChange record, @Param("example") FqStoreclerkChangeExample example);

    int updateByExample(@Param("record") FqStoreclerkChange record, @Param("example") FqStoreclerkChangeExample example);

    int updateByPrimaryKeySelective(FqStoreclerkChange record);

    int updateByPrimaryKey(FqStoreclerkChange record);
}