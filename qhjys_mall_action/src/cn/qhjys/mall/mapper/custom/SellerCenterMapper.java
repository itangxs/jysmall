package cn.qhjys.mall.mapper.custom;

import java.math.BigDecimal;
import java.util.Map;
import cn.qhjys.mall.vo.seller.SellerAccoutOverviewVo;
import cn.qhjys.mall.vo.seller.SellerCenterVo;
import com.github.pagehelper.Page;

public interface SellerCenterMapper {

	SellerCenterVo querySellerBySellerId(Long sellerId);

	SellerCenterVo queryStoreBySellerId(Long sellerId);

	SellerCenterVo queryReviewBySellerId(Long sellerId);

	SellerCenterVo queryOrderBySellerId(Long sellerId);

	Page<SellerAccoutOverviewVo> querySellerAccoutOverviewVoBySid(Map<String, Object> map);

	BigDecimal queryOrderAmountBysellerId(Map<String, Object> map);
	
	BigDecimal queryOrderIntegralBysellerId(Map<String, Object> map);

	int queryOrderPersonNumBysellerId(Map<String, Object> map);

	int queryRefundOrderNumBysellerId(Map<String, Object> map);
}
