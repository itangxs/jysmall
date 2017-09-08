package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.RegisterActivity;
import cn.qhjys.mall.entity.RegisterActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RegisterActivityMapper {
    int countByExample(RegisterActivityExample example);

    int deleteByExample(RegisterActivityExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RegisterActivity record);

    int insertSelective(RegisterActivity record);

    List<RegisterActivity> selectByExample(RegisterActivityExample example);

    RegisterActivity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RegisterActivity record, @Param("example") RegisterActivityExample example);

    int updateByExample(@Param("record") RegisterActivity record, @Param("example") RegisterActivityExample example);

    int updateByPrimaryKeySelective(RegisterActivity record);

    int updateByPrimaryKey(RegisterActivity record);
}