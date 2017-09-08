package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.TagProd;
import cn.qhjys.mall.entity.TagProdExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TagProdMapper {
    int countByExample(TagProdExample example);

    int deleteByExample(TagProdExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TagProd record);

    int insertSelective(TagProd record);

    List<TagProd> selectByExample(TagProdExample example);

    TagProd selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TagProd record, @Param("example") TagProdExample example);

    int updateByExample(@Param("record") TagProd record, @Param("example") TagProdExample example);

    int updateByPrimaryKeySelective(TagProd record);

    int updateByPrimaryKey(TagProd record);
}