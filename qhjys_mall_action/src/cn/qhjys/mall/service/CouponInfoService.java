package cn.qhjys.mall.service;

import java.util.List;
import cn.qhjys.mall.entity.CouponInfo;
import cn.qhjys.mall.vo.CouponVo;

public interface CouponInfoService {

	int insertCouponInfo(CouponInfo couponInfo);

	int updateCouponInfo(CouponInfo couponInfo);

	int deleteCouponInfo(Long id);

	CouponInfo selectCouponById(Long id);

	boolean verifyCouponInfo(Long userId, String couponId, List<Long> storeId);

	List<CouponVo> selectCouponByUser(Long userId);

	List<CouponVo> selectCouponAllByUser(Long userId);
	
	public List<CouponInfo> selectCouponByUserAndSeller(Long userId,Long[] sellerIds);
}