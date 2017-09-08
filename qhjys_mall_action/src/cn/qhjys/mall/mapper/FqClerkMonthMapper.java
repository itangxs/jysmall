package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqClerkMonth;
import cn.qhjys.mall.entity.FqClerkMonthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqClerkMonthMapper {
    int countByExample(FqClerkMonthExample example);

    int deleteByExample(FqClerkMonthExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqClerkMonth record);

    int insertSelective(FqClerkMonth record);

    List<FqClerkMonth> selectByExample(FqClerkMonthExample example);

    FqClerkMonth selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqClerkMonth record, @Param("example") FqClerkMonthExample example);

    int updateByExample(@Param("record") FqClerkMonth record, @Param("example") FqClerkMonthExample example);

    int updateByPrimaryKeySelective(FqClerkMonth record);

    int updateByPrimaryKey(FqClerkMonth record);
}