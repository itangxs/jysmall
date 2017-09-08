package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.AdminLog;
import cn.qhjys.mall.entity.AdminLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdminLogMapper {
    int countByExample(AdminLogExample example);

    int deleteByExample(AdminLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AdminLog record);

    int insertSelective(AdminLog record);

    List<AdminLog> selectByExample(AdminLogExample example);

    AdminLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AdminLog record, @Param("example") AdminLogExample example);

    int updateByExample(@Param("record") AdminLog record, @Param("example") AdminLogExample example);

    int updateByPrimaryKeySelective(AdminLog record);

    int updateByPrimaryKey(AdminLog record);
}