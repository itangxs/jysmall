package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.CouponsInfo;
import cn.qhjys.mall.entity.CouponsInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CouponsInfoMapper {
    int countByExample(CouponsInfoExample example);

    int deleteByExample(CouponsInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CouponsInfo record);

    int insertSelective(CouponsInfo record);

    List<CouponsInfo> selectByExample(CouponsInfoExample example);

    CouponsInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CouponsInfo record, @Param("example") CouponsInfoExample example);

    int updateByExample(@Param("record") CouponsInfo record, @Param("example") CouponsInfoExample example);

    int updateByPrimaryKeySelective(CouponsInfo record);

    int updateByPrimaryKey(CouponsInfo record);
}