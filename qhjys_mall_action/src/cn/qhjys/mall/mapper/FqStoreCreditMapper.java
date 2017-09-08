package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqStoreCredit;
import cn.qhjys.mall.entity.FqStoreCreditExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqStoreCreditMapper {
    int countByExample(FqStoreCreditExample example);

    int deleteByExample(FqStoreCreditExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqStoreCredit record);

    int insertSelective(FqStoreCredit record);

    List<FqStoreCredit> selectByExample(FqStoreCreditExample example);

    FqStoreCredit selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqStoreCredit record, @Param("example") FqStoreCreditExample example);

    int updateByExample(@Param("record") FqStoreCredit record, @Param("example") FqStoreCreditExample example);

    int updateByPrimaryKeySelective(FqStoreCredit record);

    int updateByPrimaryKey(FqStoreCredit record);
}