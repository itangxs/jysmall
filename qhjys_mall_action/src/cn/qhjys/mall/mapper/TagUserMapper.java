package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.TagUser;
import cn.qhjys.mall.entity.TagUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TagUserMapper {
    int countByExample(TagUserExample example);

    int deleteByExample(TagUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TagUser record);

    int insertSelective(TagUser record);

    List<TagUser> selectByExample(TagUserExample example);

    TagUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TagUser record, @Param("example") TagUserExample example);

    int updateByExample(@Param("record") TagUser record, @Param("example") TagUserExample example);

    int updateByPrimaryKeySelective(TagUser record);

    int updateByPrimaryKey(TagUser record);
}