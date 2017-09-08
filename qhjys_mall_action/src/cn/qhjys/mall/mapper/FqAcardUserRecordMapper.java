package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqAcardUserRecord;
import cn.qhjys.mall.entity.FqAcardUserRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqAcardUserRecordMapper {
    long countByExample(FqAcardUserRecordExample example);

    int deleteByExample(FqAcardUserRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqAcardUserRecord record);

    int insertSelective(FqAcardUserRecord record);

    List<FqAcardUserRecord> selectByExample(FqAcardUserRecordExample example);

    FqAcardUserRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqAcardUserRecord record, @Param("example") FqAcardUserRecordExample example);

    int updateByExample(@Param("record") FqAcardUserRecord record, @Param("example") FqAcardUserRecordExample example);

    int updateByPrimaryKeySelective(FqAcardUserRecord record);

    int updateByPrimaryKey(FqAcardUserRecord record);
}