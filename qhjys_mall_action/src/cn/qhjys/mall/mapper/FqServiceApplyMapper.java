package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqServiceApply;
import cn.qhjys.mall.entity.FqServiceApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqServiceApplyMapper {
    int countByExample(FqServiceApplyExample example);

    int deleteByExample(FqServiceApplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqServiceApply record);

    int insertSelective(FqServiceApply record);

    List<FqServiceApply> selectByExampleWithBLOBs(FqServiceApplyExample example);

    List<FqServiceApply> selectByExample(FqServiceApplyExample example);

    FqServiceApply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqServiceApply record, @Param("example") FqServiceApplyExample example);

    int updateByExampleWithBLOBs(@Param("record") FqServiceApply record, @Param("example") FqServiceApplyExample example);

    int updateByExample(@Param("record") FqServiceApply record, @Param("example") FqServiceApplyExample example);

    int updateByPrimaryKeySelective(FqServiceApply record);

    int updateByPrimaryKeyWithBLOBs(FqServiceApply record);

    int updateByPrimaryKey(FqServiceApply record);
}