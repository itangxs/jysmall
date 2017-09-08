package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqIndustry;
import cn.qhjys.mall.entity.FqIndustryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqIndustryMapper {
    int countByExample(FqIndustryExample example);

    int deleteByExample(FqIndustryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqIndustry record);

    int insertSelective(FqIndustry record);

    List<FqIndustry> selectByExample(FqIndustryExample example);

    FqIndustry selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqIndustry record, @Param("example") FqIndustryExample example);

    int updateByExample(@Param("record") FqIndustry record, @Param("example") FqIndustryExample example);

    int updateByPrimaryKeySelective(FqIndustry record);

    int updateByPrimaryKey(FqIndustry record);
}