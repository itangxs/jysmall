package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqMicroOrder;
import cn.qhjys.mall.entity.FqMicroOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqMicroOrderMapper {
    int countByExample(FqMicroOrderExample example);

    int deleteByExample(FqMicroOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqMicroOrder record);

    int insertSelective(FqMicroOrder record);

    List<FqMicroOrder> selectByExample(FqMicroOrderExample example);

    FqMicroOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqMicroOrder record, @Param("example") FqMicroOrderExample example);

    int updateByExample(@Param("record") FqMicroOrder record, @Param("example") FqMicroOrderExample example);

    int updateByPrimaryKeySelective(FqMicroOrder record);

    int updateByPrimaryKey(FqMicroOrder record);
    
    //半小时内旺 pos未支付成功的订单
    List<FqMicroOrder> selectWPosOrderByUnPay();
}