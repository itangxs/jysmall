package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqClerk;
import cn.qhjys.mall.entity.FqClerkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqClerkMapper {
    int countByExample(FqClerkExample example);

    int deleteByExample(FqClerkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqClerk record);

    int insertSelective(FqClerk record);

    List<FqClerk> selectByExample(FqClerkExample example);

    FqClerk selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqClerk record, @Param("example") FqClerkExample example);

    int updateByExample(@Param("record") FqClerk record, @Param("example") FqClerkExample example);

    int updateByPrimaryKeySelective(FqClerk record);

    int updateByPrimaryKey(FqClerk record);
}