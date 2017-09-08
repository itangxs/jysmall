package cn.qhjys.mall.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.CouponInfo;
import cn.qhjys.mall.entity.CouponInfoExample;
import cn.qhjys.mall.mapper.CouponInfoMapper;
import cn.qhjys.mall.mapper.custom.CartProductMapper;
import cn.qhjys.mall.service.CouponInfoService;
import cn.qhjys.mall.vo.CouponVo;

@Service("couponInfoService")
public class CouponInfoServiceImpl extends Base implements CouponInfoService {
	@Autowired
	private CouponInfoMapper couponInfoMapper;
	@Autowired
	CartProductMapper cartProductMapper;

	@Override
	public int insertCouponInfo(CouponInfo couponInfo) {
		return couponInfoMapper.insertSelective(couponInfo);
	}

	@Override
	public int updateCouponInfo(CouponInfo couponInfo) {
		return couponInfoMapper.updateByPrimaryKeySelective(couponInfo);
	}

	@Override
	public int deleteCouponInfo(Long id) {
		return couponInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public CouponInfo selectCouponById(Long id) {
		return couponInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean verifyCouponInfo(Long userId, String couponId, List<Long> storeId) {
		CouponInfoExample example = new CouponInfoExample();
		example.createCriteria().andUserIdEqualTo(userId).andConsumeIpEqualTo(couponId);
		if (storeId != null && storeId.size() > 0)
			example.createCriteria().andStoreIdIn(storeId);
		List<CouponInfo> list = couponInfoMapper.selectByExample(example);
		if (list == null || list.size() < 1)
			return false;
		return true;
	}

	@Override
	public List<CouponVo> selectCouponByUser(Long userId) {
		return cartProductMapper.selectCouponByUser(userId);
	}

	@Override
	public List<CouponVo> selectCouponAllByUser(Long userId) {
		List<Long> stores = cartProductMapper.selectCartProdStoreByUser(userId);
		List<CouponVo> list = cartProductMapper.selectCouponByUser(userId);
		list.addAll(cartProductMapper.selectCouponByExample(userId, stores));
		return cartProductMapper.selectCouponByUser(userId);
	}

	@Override
	public List<CouponInfo> selectCouponByUserAndSeller(Long userId,
			Long[] sellerIds) {
		CouponInfoExample example = new CouponInfoExample();
		example.createCriteria().andUserIdEqualTo(userId).andExpireTimeGreaterThan(new Date()).andConsumeEqualTo(0);
		if(sellerIds.length>0){
			example.createCriteria().andStoreIdIn(Arrays.asList(sellerIds));
		}
		return couponInfoMapper.selectByExample(example);
	}
	
}