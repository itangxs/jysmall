package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.UserBrowsed;
import cn.qhjys.mall.entity.UserBrowsedExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserBrowsedMapper {
    int countByExample(UserBrowsedExample example);

    int deleteByExample(UserBrowsedExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserBrowsed record);

    int insertSelective(UserBrowsed record);

    List<UserBrowsed> selectByExample(UserBrowsedExample example);

    UserBrowsed selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserBrowsed record, @Param("example") UserBrowsedExample example);

    int updateByExample(@Param("record") UserBrowsed record, @Param("example") UserBrowsedExample example);

    int updateByPrimaryKeySelective(UserBrowsed record);

    int updateByPrimaryKey(UserBrowsed record);
}