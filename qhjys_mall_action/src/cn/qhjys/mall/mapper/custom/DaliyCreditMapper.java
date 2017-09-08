package cn.qhjys.mall.mapper.custom;

import java.math.BigDecimal;
import java.util.Map;

public interface DaliyCreditMapper {

	public BigDecimal queryFqOrderSum(Map<String, Object> map);
	public BigDecimal queryRebateOrderSum(Map<String, Object> map);
	public BigDecimal queryMaxDaliyCount(Long sellerId);
	
	public Integer countOrderNum(Long sellerId);
}