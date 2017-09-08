package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.AdminRoleGroup;
import cn.qhjys.mall.entity.AdminRoleGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminRoleGroupMapper {
    int countByExample(AdminRoleGroupExample example);

    int deleteByExample(AdminRoleGroupExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdminRoleGroup record);

    int insertSelective(AdminRoleGroup record);

    List<AdminRoleGroup> selectByExample(AdminRoleGroupExample example);

    AdminRoleGroup selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdminRoleGroup record, @Param("example") AdminRoleGroupExample example);

    int updateByExample(@Param("record") AdminRoleGroup record, @Param("example") AdminRoleGroupExample example);

    int updateByPrimaryKeySelective(AdminRoleGroup record);

    int updateByPrimaryKey(AdminRoleGroup record);
}