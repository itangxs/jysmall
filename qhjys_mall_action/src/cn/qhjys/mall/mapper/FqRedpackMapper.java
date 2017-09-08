package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqRedpack;
import cn.qhjys.mall.entity.FqRedpackExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqRedpackMapper {
    int countByExample(FqRedpackExample example);

    int deleteByExample(FqRedpackExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqRedpack record);

    int insertSelective(FqRedpack record);

    List<FqRedpack> selectByExample(FqRedpackExample example);

    FqRedpack selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqRedpack record, @Param("example") FqRedpackExample example);

    int updateByExample(@Param("record") FqRedpack record, @Param("example") FqRedpackExample example);

    int updateByPrimaryKeySelective(FqRedpack record);

    int updateByPrimaryKey(FqRedpack record);
}