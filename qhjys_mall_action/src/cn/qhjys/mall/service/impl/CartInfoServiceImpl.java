package cn.qhjys.mall.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.CartInfo;
import cn.qhjys.mall.entity.CartInfoExample;
import cn.qhjys.mall.entity.CartInfoExample.Criteria;
import cn.qhjys.mall.entity.ProductInfo;
import cn.qhjys.mall.mapper.CartInfoMapper;
import cn.qhjys.mall.mapper.ProductInfoMapper;
import cn.qhjys.mall.mapper.custom.CartProductMapper;
import cn.qhjys.mall.service.CartInfoService;
import cn.qhjys.mall.vo.CartProdVo;

@Service
public class CartInfoServiceImpl implements CartInfoService {
	@Autowired
	CartProductMapper cartProductMapper;
	@Autowired
	CartInfoMapper cartInfoMapper;
	@Autowired
	ProductInfoMapper productInfoMapper;

	@Override
	public List<CartProdVo> queryCartByUser(Long userId) throws Exception {
		return cartProductMapper.queryCartByUser(userId);
	}

	@Override
	public boolean hasSellProduct(Long prodId) throws Exception {
		Long count = cartProductMapper.hasSellProduct(prodId);
		if (count == null || count < 1)
			return false;
		return true;
	}

	@Override
	public boolean addCartProd(Long userId, Long prodId,Long storeId, BigDecimal payPrice, int prodNum) throws Exception {
		
		CartInfo cart = null;
		
			cart = new CartInfo();
			cart.setUserId(userId);
			cart.setProdId(prodId);
			cart.setStoreId(storeId);
			cart.setProdPrice(payPrice);
			cart.setProdNum(prodNum);
			cart.setCreateTime(new Date());
			int result = cartInfoMapper.insertSelective(cart);
		
		return result > 0 ? true : false;
	}

	@Override
	public boolean updateCartProdNum(Long prodId,Long storeId, Integer prodNum, Long userId,Date createTime) throws Exception {
		if(prodId != null){
			ProductInfo prod = productInfoMapper.selectByPrimaryKey(prodId);
		
		if ( ( prod.getUnitPrice().intValue() < prodNum) || prod.getProdStock() < 1)
			return false;
		}
		CartInfoExample example = new CartInfoExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		if (prodId == null) {
			criteria.andStoreIdEqualTo(storeId);
		}else{
			criteria.andProdIdEqualTo(prodId);
		}
		
		criteria.andCreateTimeEqualTo(createTime);
		List<CartInfo> list = cartInfoMapper.selectByExample(example);
		if (list == null || list.size() < 1)
			return false;
		CartInfo cart = list.get(0);
		cart.setProdNum(prodNum);
		int result = cartInfoMapper.updateByExample(cart, example);
		return result > 0 ? true : false;
	}

	@Override
	public boolean deleteCartProduct(Long userId, Long prodId,Date createTime,Long storeId) throws Exception {
		CartInfoExample example = new CartInfoExample();
		Criteria criteria= example.createCriteria();
				criteria.andUserIdEqualTo(userId);
				if (storeId != null ) {
					criteria.andStoreIdEqualTo(storeId);
				}
				if (prodId != null ) {
					criteria.andProdIdEqualTo(prodId);
				}
				criteria.andCreateTimeEqualTo(createTime);
		int result = cartInfoMapper.deleteByExample(example);
		return result > 0 ? true : false;
	}
}