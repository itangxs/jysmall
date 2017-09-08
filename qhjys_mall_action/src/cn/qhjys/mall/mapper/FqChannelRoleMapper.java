package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqChannelRole;
import cn.qhjys.mall.entity.FqChannelRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqChannelRoleMapper {
    int countByExample(FqChannelRoleExample example);

    int deleteByExample(FqChannelRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqChannelRole record);

    int insertSelective(FqChannelRole record);

    List<FqChannelRole> selectByExample(FqChannelRoleExample example);

    FqChannelRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqChannelRole record, @Param("example") FqChannelRoleExample example);

    int updateByExample(@Param("record") FqChannelRole record, @Param("example") FqChannelRoleExample example);

    int updateByPrimaryKeySelective(FqChannelRole record);

    int updateByPrimaryKey(FqChannelRole record);
}