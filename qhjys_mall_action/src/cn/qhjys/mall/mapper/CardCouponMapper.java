package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.CardCoupon;
import cn.qhjys.mall.entity.CardCouponExample;
import cn.qhjys.mall.vo.CardCouponVo;
import cn.qhjys.mall.vo.RebateStoreVo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CardCouponMapper {
    int countByExample(CardCouponExample example);

    int deleteByExample(CardCouponExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CardCoupon record);

    int insertSelective(CardCoupon record);

    List<CardCoupon> selectByExample(CardCouponExample example);

    CardCoupon selectByPrimaryKey(Long id);
    
    List<CardCoupon> selectUserAvailableCardCoupon(Map  map);

    int updateByExampleSelective(@Param("record") CardCoupon record, @Param("example") CardCouponExample example);

    int updateByExample(@Param("record") CardCoupon record, @Param("example") CardCouponExample example);

    int updateByPrimaryKeySelective(CardCoupon record);

    int updateByPrimaryKey(CardCoupon record);
    
    List<CardCouponVo>  getMyCardCoupon(Map<String, Object> map);
}