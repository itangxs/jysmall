package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqWithdrawChangeinfo;
import cn.qhjys.mall.entity.FqWithdrawChangeinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqWithdrawChangeinfoMapper {
    long countByExample(FqWithdrawChangeinfoExample example);

    int deleteByExample(FqWithdrawChangeinfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqWithdrawChangeinfo record);

    int insertSelective(FqWithdrawChangeinfo record);

    List<FqWithdrawChangeinfo> selectByExample(FqWithdrawChangeinfoExample example);

    FqWithdrawChangeinfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqWithdrawChangeinfo record, @Param("example") FqWithdrawChangeinfoExample example);

    int updateByExample(@Param("record") FqWithdrawChangeinfo record, @Param("example") FqWithdrawChangeinfoExample example);

    int updateByPrimaryKeySelective(FqWithdrawChangeinfo record);

    int updateByPrimaryKey(FqWithdrawChangeinfo record);
}