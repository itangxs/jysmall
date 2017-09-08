package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.DeliveryAddr;
import cn.qhjys.mall.entity.DeliveryAddrExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeliveryAddrMapper {
    int countByExample(DeliveryAddrExample example);

    int deleteByExample(DeliveryAddrExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DeliveryAddr record);

    int insertSelective(DeliveryAddr record);

    List<DeliveryAddr> selectByExample(DeliveryAddrExample example);

    DeliveryAddr selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DeliveryAddr record, @Param("example") DeliveryAddrExample example);

    int updateByExample(@Param("record") DeliveryAddr record, @Param("example") DeliveryAddrExample example);

    int updateByPrimaryKeySelective(DeliveryAddr record);

    int updateByPrimaryKey(DeliveryAddr record);
}