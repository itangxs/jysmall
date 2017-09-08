package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqUserInfo;
import cn.qhjys.mall.entity.FqUserInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqUserInfoMapper {
    int countByExample(FqUserInfoExample example);

    int deleteByExample(FqUserInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqUserInfo record);

    int insertSelective(FqUserInfo record);

    List<FqUserInfo> selectByExample(FqUserInfoExample example);

    FqUserInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqUserInfo record, @Param("example") FqUserInfoExample example);

    int updateByExample(@Param("record") FqUserInfo record, @Param("example") FqUserInfoExample example);

    int updateByPrimaryKeySelective(FqUserInfo record);

    int updateByPrimaryKey(FqUserInfo record);
}