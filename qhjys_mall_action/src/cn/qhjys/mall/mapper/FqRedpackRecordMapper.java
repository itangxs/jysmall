package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.FqRedpackRecord;
import cn.qhjys.mall.entity.FqRedpackRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FqRedpackRecordMapper {
    int countByExample(FqRedpackRecordExample example);

    int deleteByExample(FqRedpackRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FqRedpackRecord record);

    int insertSelective(FqRedpackRecord record);

    List<FqRedpackRecord> selectByExample(FqRedpackRecordExample example);

    FqRedpackRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FqRedpackRecord record, @Param("example") FqRedpackRecordExample example);

    int updateByExample(@Param("record") FqRedpackRecord record, @Param("example") FqRedpackRecordExample example);

    int updateByPrimaryKeySelective(FqRedpackRecord record);

    int updateByPrimaryKey(FqRedpackRecord record);
}