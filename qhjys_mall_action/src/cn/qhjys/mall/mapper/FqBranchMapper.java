package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqBranch;
import cn.qhjys.mall.entity.FqBranchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqBranchMapper {
    int countByExample(FqBranchExample example);

    int deleteByExample(FqBranchExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqBranch record);

    int insertSelective(FqBranch record);

    List<FqBranch> selectByExample(FqBranchExample example);

    FqBranch selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqBranch record, @Param("example") FqBranchExample example);

    int updateByExample(@Param("record") FqBranch record, @Param("example") FqBranchExample example);

    int updateByPrimaryKeySelective(FqBranch record);

    int updateByPrimaryKey(FqBranch record);
}