package cn.qhjys.mall.mapper;

import cn.qhjys.mall.entity.ReviewsLog;
import cn.qhjys.mall.entity.ReviewsLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReviewsLogMapper {
    int countByExample(ReviewsLogExample example);

    int deleteByExample(ReviewsLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ReviewsLog record);

    int insertSelective(ReviewsLog record);

    List<ReviewsLog> selectByExample(ReviewsLogExample example);

    ReviewsLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ReviewsLog record, @Param("example") ReviewsLogExample example);

    int updateByExample(@Param("record") ReviewsLog record, @Param("example") ReviewsLogExample example);

    int updateByPrimaryKeySelective(ReviewsLog record);

    int updateByPrimaryKey(ReviewsLog record);
}