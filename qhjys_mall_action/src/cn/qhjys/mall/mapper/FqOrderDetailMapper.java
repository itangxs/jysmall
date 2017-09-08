package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqOrderDetail;
import cn.qhjys.mall.entity.FqOrderDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqOrderDetailMapper {
    int countByExample(FqOrderDetailExample example);

    int deleteByExample(FqOrderDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqOrderDetail record);

    int insertSelective(FqOrderDetail record);

    List<FqOrderDetail> selectByExample(FqOrderDetailExample example);

    FqOrderDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqOrderDetail record, @Param("example") FqOrderDetailExample example);

    int updateByExample(@Param("record") FqOrderDetail record, @Param("example") FqOrderDetailExample example);

    int updateByPrimaryKeySelective(FqOrderDetail record);

    int updateByPrimaryKey(FqOrderDetail record);
}