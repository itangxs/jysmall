package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.CardCouponDelivery;
import cn.qhjys.mall.entity.CardCouponDeliveryExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CardCouponDeliveryMapper {
    int countByExample(CardCouponDeliveryExample example);

    int deleteByExample(CardCouponDeliveryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CardCouponDelivery record);

    int insertSelective(CardCouponDelivery record);

    List<CardCouponDelivery> selectByExample(CardCouponDeliveryExample example);

    CardCouponDelivery selectByPrimaryKey(Long id);
    
    int updateByExampleSelective(@Param("record") CardCouponDelivery record, @Param("example") CardCouponDeliveryExample example);

    int updateByExample(@Param("record") CardCouponDelivery record, @Param("example") CardCouponDeliveryExample example);

    int updateByPrimaryKeySelective(CardCouponDelivery record);

    int updateByPrimaryKey(CardCouponDelivery record);
    
    int updateReducePushNum(Long sellerId);
    
    //查询周边投放开启的商家列表
    List<Map> selectSellerPeripheralList(Map map);
    
    //查询商家 周边投放或者自营投放的 卡券模板
    List<Map> selectSellerDeliveryCardTemplateList(Map map);
}