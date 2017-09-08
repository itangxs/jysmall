package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqAcard;
import cn.qhjys.mall.entity.FqAcardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqAcardMapper {
    long countByExample(FqAcardExample example);

    int deleteByExample(FqAcardExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqAcard record);

    int insertSelective(FqAcard record);

    List<FqAcard> selectByExample(FqAcardExample example);

    FqAcard selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqAcard record, @Param("example") FqAcardExample example);

    int updateByExample(@Param("record") FqAcard record, @Param("example") FqAcardExample example);

    int updateByPrimaryKeySelective(FqAcard record);

    int updateByPrimaryKey(FqAcard record);
}