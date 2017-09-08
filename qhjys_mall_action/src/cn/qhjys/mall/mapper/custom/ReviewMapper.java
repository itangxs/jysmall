package cn.qhjys.mall.mapper.custom;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import cn.qhjys.mall.vo.EvaluateVo;
import cn.qhjys.mall.vo.ReviewVo;
import com.github.pagehelper.Page;

public interface ReviewMapper {

	ReviewVo searchReviewByProdId(@Param("prodId") Long prodId);

	ReviewVo searchReviewByStoreId(@Param("storeId") Long storeId);

	Page<EvaluateVo> selectProductReviewByLevel(Map<String, Object> map);

	Page<EvaluateVo> selectStoreReviewByLevel(Map<String, Object> map);

	List<EvaluateVo> queryEvaluateListByName(Map<String, Object> map);

	List<EvaluateVo> findEvaluateBySaUoSId(@Param("proId") Long prodId,@Param("userId") Long userId,@Param("detailId") Long detailId);
	
	int countReviewsLogByUserAndTime(Map<String, Object> map);
}