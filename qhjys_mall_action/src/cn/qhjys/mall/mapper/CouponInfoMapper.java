package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.CouponInfo;
import cn.qhjys.mall.entity.CouponInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CouponInfoMapper {
    int countByExample(CouponInfoExample example);

    int deleteByExample(CouponInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CouponInfo record);

    int insertSelective(CouponInfo record);

    List<CouponInfo> selectByExample(CouponInfoExample example);

    CouponInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CouponInfo record, @Param("example") CouponInfoExample example);

    int updateByExample(@Param("record") CouponInfo record, @Param("example") CouponInfoExample example);

    int updateByPrimaryKeySelective(CouponInfo record);

    int updateByPrimaryKey(CouponInfo record);
}