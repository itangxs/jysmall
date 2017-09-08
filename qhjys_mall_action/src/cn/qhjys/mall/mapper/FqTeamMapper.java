package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqTeam;
import cn.qhjys.mall.entity.FqTeamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqTeamMapper {
    int countByExample(FqTeamExample example);

    int deleteByExample(FqTeamExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqTeam record);

    int insertSelective(FqTeam record);

    List<FqTeam> selectByExample(FqTeamExample example);

    FqTeam selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqTeam record, @Param("example") FqTeamExample example);

    int updateByExample(@Param("record") FqTeam record, @Param("example") FqTeamExample example);

    int updateByPrimaryKeySelective(FqTeam record);

    int updateByPrimaryKey(FqTeam record);
}