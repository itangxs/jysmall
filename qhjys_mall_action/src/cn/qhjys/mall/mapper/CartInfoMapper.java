package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.CartInfo;
import cn.qhjys.mall.entity.CartInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CartInfoMapper {
    int countByExample(CartInfoExample example);

    int deleteByExample(CartInfoExample example);

    int insert(CartInfo record);

    int insertSelective(CartInfo record);

    List<CartInfo> selectByExample(CartInfoExample example);

    int updateByExampleSelective(@Param("record") CartInfo record, @Param("example") CartInfoExample example);

    int updateByExample(@Param("record") CartInfo record, @Param("example") CartInfoExample example);
}