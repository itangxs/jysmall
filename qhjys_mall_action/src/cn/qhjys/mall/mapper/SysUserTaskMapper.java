package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.SysUserTask;
import cn.qhjys.mall.entity.SysUserTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserTaskMapper {
    int countByExample(SysUserTaskExample example);

    int deleteByExample(SysUserTaskExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysUserTask record);

    int insertSelective(SysUserTask record);

    List<SysUserTask> selectByExample(SysUserTaskExample example);

    SysUserTask selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysUserTask record, @Param("example") SysUserTaskExample example);

    int updateByExample(@Param("record") SysUserTask record, @Param("example") SysUserTaskExample example);

    int updateByPrimaryKeySelective(SysUserTask record);

    int updateByPrimaryKey(SysUserTask record);
}