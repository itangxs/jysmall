package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqRedpackDetail;
import cn.qhjys.mall.entity.FqRedpackDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqRedpackDetailMapper {
    int countByExample(FqRedpackDetailExample example);

    int deleteByExample(FqRedpackDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqRedpackDetail record);

    int insertSelective(FqRedpackDetail record);

    List<FqRedpackDetail> selectByExample(FqRedpackDetailExample example);

    FqRedpackDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqRedpackDetail record, @Param("example") FqRedpackDetailExample example);

    int updateByExample(@Param("record") FqRedpackDetail record, @Param("example") FqRedpackDetailExample example);

    int updateByPrimaryKeySelective(FqRedpackDetail record);

    int updateByPrimaryKey(FqRedpackDetail record);
}