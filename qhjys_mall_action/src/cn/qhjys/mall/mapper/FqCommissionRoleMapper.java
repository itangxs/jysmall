package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqCommissionRole;
import cn.qhjys.mall.entity.FqCommissionRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqCommissionRoleMapper {
    int countByExample(FqCommissionRoleExample example);

    int deleteByExample(FqCommissionRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqCommissionRole record);

    int insertSelective(FqCommissionRole record);

    List<FqCommissionRole> selectByExample(FqCommissionRoleExample example);

    FqCommissionRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqCommissionRole record, @Param("example") FqCommissionRoleExample example);

    int updateByExample(@Param("record") FqCommissionRole record, @Param("example") FqCommissionRoleExample example);

    int updateByPrimaryKeySelective(FqCommissionRole record);

    int updateByPrimaryKey(FqCommissionRole record);
}