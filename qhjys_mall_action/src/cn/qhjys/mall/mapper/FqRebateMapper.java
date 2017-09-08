package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqRebate;
import cn.qhjys.mall.entity.FqRebateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqRebateMapper {
    int countByExample(FqRebateExample example);

    int deleteByExample(FqRebateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqRebate record);

    int insertSelective(FqRebate record);

    List<FqRebate> selectByExample(FqRebateExample example);

    FqRebate selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqRebate record, @Param("example") FqRebateExample example);

    int updateByExample(@Param("record") FqRebate record, @Param("example") FqRebateExample example);

    int updateByPrimaryKeySelective(FqRebate record);

    int updateByPrimaryKey(FqRebate record);
}