package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.SigninInfo;
import cn.qhjys.mall.entity.SigninInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SigninInfoMapper {
    int countByExample(SigninInfoExample example);

    int deleteByExample(SigninInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SigninInfo record);

    int insertSelective(SigninInfo record);

    List<SigninInfo> selectByExample(SigninInfoExample example);

    SigninInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SigninInfo record, @Param("example") SigninInfoExample example);

    int updateByExample(@Param("record") SigninInfo record, @Param("example") SigninInfoExample example);

    int updateByPrimaryKeySelective(SigninInfo record);

    int updateByPrimaryKey(SigninInfo record);
}