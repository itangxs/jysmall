package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.SysTask;
import cn.qhjys.mall.entity.SysTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysTaskMapper {
    int countByExample(SysTaskExample example);

    int deleteByExample(SysTaskExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysTask record);

    int insertSelective(SysTask record);

    List<SysTask> selectByExampleWithBLOBs(SysTaskExample example);

    List<SysTask> selectByExample(SysTaskExample example);

    SysTask selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysTask record, @Param("example") SysTaskExample example);

    int updateByExampleWithBLOBs(@Param("record") SysTask record, @Param("example") SysTaskExample example);

    int updateByExample(@Param("record") SysTask record, @Param("example") SysTaskExample example);

    int updateByPrimaryKeySelective(SysTask record);

    int updateByPrimaryKeyWithBLOBs(SysTask record);

    int updateByPrimaryKey(SysTask record);
}