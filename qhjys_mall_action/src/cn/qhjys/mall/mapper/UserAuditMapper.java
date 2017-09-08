package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.UserAudit;
import cn.qhjys.mall.entity.UserAuditExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAuditMapper {
    int countByExample(UserAuditExample example);

    int deleteByExample(UserAuditExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserAudit record);

    int insertSelective(UserAudit record);

    List<UserAudit> selectByExample(UserAuditExample example);

    UserAudit selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserAudit record, @Param("example") UserAuditExample example);

    int updateByExample(@Param("record") UserAudit record, @Param("example") UserAuditExample example);

    int updateByPrimaryKeySelective(UserAudit record);

    int updateByPrimaryKey(UserAudit record);
}