package cn.qhjys.mall.mapper.custom;

import java.math.BigDecimal;
import java.util.Map;

import cn.qhjys.mall.vo.OrderCountVo;

public interface ThirdPayMapper {
	public BigDecimal queryThirdPaySum(Map<String, Object> map);
	
	public int updateThirdPayByOpenid(Map<String, Object> map);
	public BigDecimal querySellerThirdPaySum(Long sellerId);
	public int querySellerByRegis(Long sellerId);
	
	public OrderCountVo queryOrderCountVo(Map<String, Object> map);
}