package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.CardDeliveryCount;
import cn.qhjys.mall.entity.CardDeliveryCountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardDeliveryCountMapper {
    int countByExample(CardDeliveryCountExample example);

    int deleteByExample(CardDeliveryCountExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CardDeliveryCount record);

    int insertSelective(CardDeliveryCount record);

    List<CardDeliveryCount> selectByExample(CardDeliveryCountExample example);

    CardDeliveryCount selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CardDeliveryCount record, @Param("example") CardDeliveryCountExample example);

    int updateByExample(@Param("record") CardDeliveryCount record, @Param("example") CardDeliveryCountExample example);

    int updateByPrimaryKeySelective(CardDeliveryCount record);

    int updateByPrimaryKey(CardDeliveryCount record);
}