package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.UserExpand;
import cn.qhjys.mall.entity.UserExpandExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserExpandMapper {
    int countByExample(UserExpandExample example);

    int deleteByExample(UserExpandExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserExpand record);

    int insertSelective(UserExpand record);

    List<UserExpand> selectByExample(UserExpandExample example);

    UserExpand selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserExpand record, @Param("example") UserExpandExample example);

    int updateByExample(@Param("record") UserExpand record, @Param("example") UserExpandExample example);

    int updateByPrimaryKeySelective(UserExpand record);

    int updateByPrimaryKey(UserExpand record);
}