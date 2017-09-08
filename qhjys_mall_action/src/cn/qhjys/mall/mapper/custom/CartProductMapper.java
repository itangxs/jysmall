package cn.qhjys.mall.mapper.custom;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import cn.qhjys.mall.vo.CartProdVo;
import cn.qhjys.mall.vo.CouponVo;

public interface CartProductMapper {
	List<CartProdVo> queryCartByUser(Long userId);

	Long hasSellProduct(Long prodId);

	List<CouponVo> selectCouponByUser(Long userId);

	List<Long> selectCartProdStoreByUser(Long userId);

	List<CouponVo> selectCouponByExample(@Param("userId") Long userId, @Param("stores") List<Long> stores);
}