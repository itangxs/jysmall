package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.UserBargain;
import cn.qhjys.mall.entity.UserBargainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserBargainMapper {
    int countByExample(UserBargainExample example);

    int deleteByExample(UserBargainExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserBargain record);

    int insertSelective(UserBargain record);

    List<UserBargain> selectByExample(UserBargainExample example);

    UserBargain selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserBargain record, @Param("example") UserBargainExample example);

    int updateByExample(@Param("record") UserBargain record, @Param("example") UserBargainExample example);

    int updateByPrimaryKeySelective(UserBargain record);

    int updateByPrimaryKey(UserBargain record);
}