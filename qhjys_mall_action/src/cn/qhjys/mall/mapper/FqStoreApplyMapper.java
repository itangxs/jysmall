package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqStoreApply;
import cn.qhjys.mall.entity.FqStoreApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqStoreApplyMapper {
    int countByExample(FqStoreApplyExample example);

    int deleteByExample(FqStoreApplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqStoreApply record);

    int insertSelective(FqStoreApply record);

    List<FqStoreApply> selectByExample(FqStoreApplyExample example);

    FqStoreApply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqStoreApply record, @Param("example") FqStoreApplyExample example);

    int updateByExample(@Param("record") FqStoreApply record, @Param("example") FqStoreApplyExample example);

    int updateByPrimaryKeySelective(FqStoreApply record);

    int updateByPrimaryKey(FqStoreApply record);
}