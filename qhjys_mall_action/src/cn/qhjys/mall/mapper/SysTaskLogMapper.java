package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.SysTaskLog;
import cn.qhjys.mall.entity.SysTaskLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysTaskLogMapper {
    int countByExample(SysTaskLogExample example);

    int deleteByExample(SysTaskLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysTaskLog record);

    int insertSelective(SysTaskLog record);

    List<SysTaskLog> selectByExample(SysTaskLogExample example);

    SysTaskLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysTaskLog record, @Param("example") SysTaskLogExample example);

    int updateByExample(@Param("record") SysTaskLog record, @Param("example") SysTaskLogExample example);

    int updateByPrimaryKeySelective(SysTaskLog record);

    int updateByPrimaryKey(SysTaskLog record);
}