package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqOrder;
import cn.qhjys.mall.entity.FqOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqOrderMapper {
    int countByExample(FqOrderExample example);

    int deleteByExample(FqOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqOrder record);

    int insertSelective(FqOrder record);

    List<FqOrder> selectByExample(FqOrderExample example);

    FqOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqOrder record, @Param("example") FqOrderExample example);

    int updateByExample(@Param("record") FqOrder record, @Param("example") FqOrderExample example);

    int updateByPrimaryKeySelective(FqOrder record);

    int updateByPrimaryKey(FqOrder record);
}