package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.CardCouponDeliveryTemplateRelation;
import cn.qhjys.mall.entity.CardCouponDeliveryTemplateRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardCouponDeliveryTemplateRelationMapper {
    int countByExample(CardCouponDeliveryTemplateRelationExample example);

    int deleteByExample(CardCouponDeliveryTemplateRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CardCouponDeliveryTemplateRelation record);

    int insertSelective(CardCouponDeliveryTemplateRelation record);

    List<CardCouponDeliveryTemplateRelation> selectByExample(CardCouponDeliveryTemplateRelationExample example);

    CardCouponDeliveryTemplateRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CardCouponDeliveryTemplateRelation record, @Param("example") CardCouponDeliveryTemplateRelationExample example);

    int updateByExample(@Param("record") CardCouponDeliveryTemplateRelation record, @Param("example") CardCouponDeliveryTemplateRelationExample example);

    int updateByPrimaryKeySelective(CardCouponDeliveryTemplateRelation record);

    int updateByPrimaryKey(CardCouponDeliveryTemplateRelation record);
}