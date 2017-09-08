package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.CardDeliveryProperty;
import cn.qhjys.mall.entity.CardDeliveryPropertyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardDeliveryPropertyMapper {
    int countByExample(CardDeliveryPropertyExample example);

    int deleteByExample(CardDeliveryPropertyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CardDeliveryProperty record);

    int insertSelective(CardDeliveryProperty record);

    List<CardDeliveryProperty> selectByExample(CardDeliveryPropertyExample example);

    CardDeliveryProperty selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CardDeliveryProperty record, @Param("example") CardDeliveryPropertyExample example);

    int updateByExample(@Param("record") CardDeliveryProperty record, @Param("example") CardDeliveryPropertyExample example);

    int updateByPrimaryKeySelective(CardDeliveryProperty record);

    int updateByPrimaryKey(CardDeliveryProperty record);
}