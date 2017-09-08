package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.CardCouponTemplate;
import cn.qhjys.mall.entity.CardCouponTemplateExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CardCouponTemplateMapper {
    int countByExample(CardCouponTemplateExample example);

    int deleteByExample(CardCouponTemplateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CardCouponTemplate record);

    int insertSelective(CardCouponTemplate record);

    List<CardCouponTemplate> selectByExample(CardCouponTemplateExample example);

    CardCouponTemplate selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CardCouponTemplate record, @Param("example") CardCouponTemplateExample example);

    int updateByExample(@Param("record") CardCouponTemplate record, @Param("example") CardCouponTemplateExample example);

    int updateByPrimaryKeySelective(CardCouponTemplate record);

    int updateByPrimaryKey(CardCouponTemplate record);
    
    /**
     * 查询周边/自营卡券模板列表
     * 卡券模板表关联查询卡券投放关联表 按关联表sort字段返回指定字段
     * @param map  
     * 		  sellerId  商户id
     * 		  statusCfg 投放类型 1：自营 2：周边
     * @return
     */
    List<Map<String, String>> queryCouponTemplateBySellerIdAndStatusCfg(Map<String, Object> map);
}