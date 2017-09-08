package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.RebateOrder;
import cn.qhjys.mall.entity.RebateOrderExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RebateOrderMapper {
    int countByExample(RebateOrderExample example);

    int deleteByExample(RebateOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RebateOrder record);

    int insertSelective(RebateOrder record);

    List<RebateOrder> selectByExample(RebateOrderExample example);

    RebateOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RebateOrder record, @Param("example") RebateOrderExample example);

    int updateByExample(@Param("record") RebateOrder record, @Param("example") RebateOrderExample example);

    int updateByPrimaryKeySelective(RebateOrder record);

    int updateByPrimaryKey(RebateOrder record);
    
    /**
     * 根据id查询订单详情信息
     */
    Map<String, Object> selectOrderDetailById(Long id);
}