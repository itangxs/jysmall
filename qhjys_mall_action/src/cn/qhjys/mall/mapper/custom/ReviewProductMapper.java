package cn.qhjys.mall.mapper.custom;

import java.util.List;
import java.util.Map;
import cn.qhjys.mall.vo.system.ReviewProductVo;

/***
 * 总后台商品评论接口
 * @author zengrong
 *
 */
public interface ReviewProductMapper {

	public List<ReviewProductVo> queryReviewProductList(Map<String,Object> map);
	
}
