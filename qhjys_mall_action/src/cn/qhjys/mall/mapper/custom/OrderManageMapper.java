package cn.qhjys.mall.mapper.custom;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import cn.qhjys.mall.vo.OrderDetailVo;
import cn.qhjys.mall.vo.OrderVo;
import cn.qhjys.mall.vo.RefundVo;

public interface OrderManageMapper {

	OrderVo getOrderVoByOrder(@Param("userId") Long userId, @Param("orderId") Long orderId);

	List<OrderVo> queryOrderList(@Param("userId") Long userId, @Param("status") Long[] status);

	OrderDetailVo getOrderDetail(@Param("userId") Long userId, @Param("detailId") Long detailId);

	List<OrderDetailVo> queryOrderDetail(@Param("orderId") Long orderId, @Param("userId") Long userId,
			@Param("status") Long[] status);

	List<OrderDetailVo> queryOrderDetailList(@Param("userId") Long userId, @Param("status") Long[] status);

	Integer getNotReceiptOrder(Map<String, Object> map);

	List<OrderDetailVo> searchOrderListBySellerId(Map<String, Object> map);

	List<RefundVo> queryRefundListBySellerId(@Param("sellerId") Long sellerId, @Param("detailNo") String detailNo,
			@Param("orderStatus") Long orderStatus);

	List<OrderDetailVo> queryOrderListByAdmin(Map<String, Object> map);

	Integer queryProdSumNumByProdId(@Param("prodId") Long prodId, @Param("userId") Long userId);

	List<OrderDetailVo> queryOrderDetaiVoByProdIdAndUserId(@Param("prodId") Long prodId, @Param("userId") Long userId,
			@Param("status") int status);

	List<Long> queryOvertimeOrder();

	Map<String, Object> getUserOrderPriceSum(@Param("userId") Long userId, @Param("begin") Date begin);
}