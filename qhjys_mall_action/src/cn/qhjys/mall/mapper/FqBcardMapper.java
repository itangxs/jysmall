package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqBcard;
import cn.qhjys.mall.entity.FqBcardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqBcardMapper {
    long countByExample(FqBcardExample example);

    int deleteByExample(FqBcardExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqBcard record);

    int insertSelective(FqBcard record);

    List<FqBcard> selectByExample(FqBcardExample example);

    FqBcard selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqBcard record, @Param("example") FqBcardExample example);

    int updateByExample(@Param("record") FqBcard record, @Param("example") FqBcardExample example);

    int updateByPrimaryKeySelective(FqBcard record);

    int updateByPrimaryKey(FqBcard record);
}