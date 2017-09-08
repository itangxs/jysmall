package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.CardCouponPeripheralDisplay;
import cn.qhjys.mall.entity.CardCouponPeripheralDisplayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardCouponPeripheralDisplayMapper {
    long countByExample(CardCouponPeripheralDisplayExample example);

    int deleteByExample(CardCouponPeripheralDisplayExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CardCouponPeripheralDisplay record);

    int insertSelective(CardCouponPeripheralDisplay record);

    List<CardCouponPeripheralDisplay> selectByExample(CardCouponPeripheralDisplayExample example);

    CardCouponPeripheralDisplay selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CardCouponPeripheralDisplay record, @Param("example") CardCouponPeripheralDisplayExample example);

    int updateByExample(@Param("record") CardCouponPeripheralDisplay record, @Param("example") CardCouponPeripheralDisplayExample example);

    int updateByPrimaryKeySelective(CardCouponPeripheralDisplay record);

    int updateByPrimaryKey(CardCouponPeripheralDisplay record);
}