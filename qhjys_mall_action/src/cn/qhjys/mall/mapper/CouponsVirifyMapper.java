package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.CouponsVirify;
import cn.qhjys.mall.entity.CouponsVirifyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CouponsVirifyMapper {
    int countByExample(CouponsVirifyExample example);

    int deleteByExample(CouponsVirifyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CouponsVirify record);

    int insertSelective(CouponsVirify record);

    List<CouponsVirify> selectByExample(CouponsVirifyExample example);

    CouponsVirify selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CouponsVirify record, @Param("example") CouponsVirifyExample example);

    int updateByExample(@Param("record") CouponsVirify record, @Param("example") CouponsVirifyExample example);

    int updateByPrimaryKeySelective(CouponsVirify record);

    int updateByPrimaryKey(CouponsVirify record);
}