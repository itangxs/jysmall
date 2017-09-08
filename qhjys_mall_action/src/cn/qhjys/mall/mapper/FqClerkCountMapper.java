package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqClerkCount;
import cn.qhjys.mall.entity.FqClerkCountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqClerkCountMapper {
    int countByExample(FqClerkCountExample example);

    int deleteByExample(FqClerkCountExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqClerkCount record);

    int insertSelective(FqClerkCount record);

    List<FqClerkCount> selectByExample(FqClerkCountExample example);

    FqClerkCount selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqClerkCount record, @Param("example") FqClerkCountExample example);

    int updateByExample(@Param("record") FqClerkCount record, @Param("example") FqClerkCountExample example);

    int updateByPrimaryKeySelective(FqClerkCount record);

    int updateByPrimaryKey(FqClerkCount record);
}