package cn.qhjys.mall.service.fq;

import java.math.BigDecimal;

import cn.qhjys.mall.entity.FqUserInfo;

public interface FqUserInfoService {
	public FqUserInfo getFqUserInfoById(Long id);
	public FqUserInfo updateFqUserInfo(String openId,String nickname,String portrait);
	public FqUserInfo getFqUserInfoByOpenId(String openId) throws Exception;
	
	public int updateThirdPayByOpenid();
	
	public BigDecimal querySellerThirdPaySum(Long sellerId);
	public int querySellerByRegis(Long sellerId);
}
