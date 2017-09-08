package cn.qhjys.mall.service.fq;

import java.util.List;

import com.github.pagehelper.Page;

import cn.qhjys.mall.entity.FqOrder;
import cn.qhjys.mall.entity.FqOrderDetail;

/**
 * 订单管理
 * 
 * @author apple
 */
public interface FqOrderService {
	
	Page<FqOrder> searchOrderListByStoreId(Long storeId, String orderNo, Integer status,
			String createTimeBegin, String createTimeEnding, String bookTimeBegin,
			String bookTimeEnding,Integer pageNum, Integer pageSize)
			throws Exception;
	
	Page<FqOrder> searchOrderListByParams(String storeName, String orderNo, Integer status,Integer payType,
			String createTimeBegin, String createTimeEnding, String bookTimeBegin,
			String bookTimeEnding,Integer pageNum, Integer pageSize)
					throws Exception;
	
	
	FqOrder getFqOrder(Long id);
	FqOrder getFqOrderByOrderNo(String orderNo);
	
	List<FqOrderDetail> queryOrderDetailByOrderId(Long orderId);
	
	Long insertFqOrderAndDetail(String ids,String nums,Long storeId,long userId,String deskNo,String username ,String phoneNum,String peopleNum)throws Exception;
	Long insertFqOrderAndDetailGd(String ids,String nums,Long storeId,long userId,String deskNo,String username ,String phoneNum,String peopleNum)throws Exception;
	Long insertFqOrderAndDetailPf(String ids,String nums,Long storeId,long userId,String deskNo,String username ,String phoneNum,String peopleNum)throws Exception;
	
	int deleteOrderNoPay();
	
	Page<FqOrder> listFqOrderByStoreIdAndOpenId(Long sellerId,String openId,Integer pageSize,Integer pageNum);

	public Long insertFqOrderAndDetailMs(String orderHead,String ids, String nums,
			Long storeId,long userId,String deskNo,String username ,String phoneNum,String peopleNum) throws Exception;
}
