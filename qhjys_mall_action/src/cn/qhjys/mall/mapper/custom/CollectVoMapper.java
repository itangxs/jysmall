package cn.qhjys.mall.mapper.custom;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.qhjys.mall.vo.CollectVo;

public interface CollectVoMapper {
	/**
	 * 查询收藏的店铺
	 * 
	 * @param userid
	 *            用户编号
	 * @return
	 */
	List<CollectVo> selectStoreCollectByUser(@Param("userId") Long userId);

	/**
	 * 查找收藏的产品
	 * 
	 * @param userid
	 *            用户编号
	 * @return
	 */
	List<CollectVo> selectProductCollectByUser(@Param("userId") Long userId);
}