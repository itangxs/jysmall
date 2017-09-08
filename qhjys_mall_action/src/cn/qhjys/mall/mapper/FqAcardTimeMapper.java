package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqAcardTime;
import cn.qhjys.mall.entity.FqAcardTimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqAcardTimeMapper {
    long countByExample(FqAcardTimeExample example);

    int deleteByExample(FqAcardTimeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqAcardTime record);

    int insertSelective(FqAcardTime record);

    List<FqAcardTime> selectByExample(FqAcardTimeExample example);

    FqAcardTime selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqAcardTime record, @Param("example") FqAcardTimeExample example);

    int updateByExample(@Param("record") FqAcardTime record, @Param("example") FqAcardTimeExample example);

    int updateByPrimaryKeySelective(FqAcardTime record);

    int updateByPrimaryKey(FqAcardTime record);
}