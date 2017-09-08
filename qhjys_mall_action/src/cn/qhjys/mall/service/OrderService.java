package cn.qhjys.mall.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import cn.qhjys.mall.entity.CashAccount;
import cn.qhjys.mall.entity.OrderDetail;
import cn.qhjys.mall.entity.OrderInfo;
import cn.qhjys.mall.entity.ProductInfo;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.UserRefund;
import cn.qhjys.mall.util.OrderResult;
import cn.qhjys.mall.vo.OrderDetailVo;
import cn.qhjys.mall.vo.OrderVo;
import cn.qhjys.mall.vo.RefundVo;

import com.github.pagehelper.Page;

/**
 * 订单管理
 * 
 * @author LiXiang
 *
 */
public interface OrderService {

	/**
	 * 会员查询订单列表
	 * 
	 * @param userId
	 *            用户编号
	 * @param status
	 *            订单状态
	 * @param pageNum
	 *            当前页数
	 * @param pageSize
	 *            每页记录数
	 * @return
	 * @throws Exception
	 */
	Page<OrderVo> queryOrderByUser(Long userId, Long[] status, int pageNum, int pageSize) throws Exception;

	/**
	 * 获取大订单信息
	 * 
	 * @param orderId
	 *            大订单编号
	 * @return
	 */
	OrderInfo getOrderInfoByExample(Long orderId, Long userId) throws Exception;

	/**
	 * 获取小订单信息
	 * 
	 * @param detailId
	 *            小订单编号
	 * @return
	 */
	OrderDetail getOrderDetail(Long detailId) throws Exception;

	/**
	 * 查询订单详情
	 * 
	 * @param userId
	 *            用户ID
	 * @param orderId
	 *            订单编号
	 * @return
	 */
	OrderVo getOrderInfo(Long userId, Long orderId) throws Exception;

	/**
	 * 删除订单
	 * 
	 * @param userId
	 *            用户ID
	 * @param orderid
	 *            订单ID
	 * @return
	 */
	boolean deleteOrderById(Long userId, Long orderId) throws Exception;

	/**
	 * 查询详单列表
	 * 
	 * @param userId
	 *            用户ID
	 * @param status
	 *            订单状态
	 * @param pageNum
	 *            当前页数
	 * @param pageSize
	 *            每页记录数
	 * @return
	 */
	Page<OrderDetailVo> queryOrderDetailList(Long userId, Long[] status, int pageNum, int pageSize) throws Exception;

	/**
	 * 获取详单信息
	 * 
	 * @param userId
	 *            用户ID
	 * @param detailId
	 *            订单编号
	 * @return
	 */
	OrderDetailVo getOrderDetail(Long userId, Long detailId) throws Exception;

	/**
	 * 添加退款记录
	 * 
	 * @param refund
	 *            退款信息
	 * @return
	 */
	boolean insertRefund(UserRefund refund) throws Exception;

	/**
	 * 根据商家编号查询商品订单(商家后台)
	 * 
	 * @param sellerId
	 *            商家编号
	 * @param detailNo
	 *            订单编号
	 * @param prodName
	 *            商品名称
	 * @param orstat
	 *            订单状态
	 * @param begin
	 *            下单开始时间
	 * @param ending
	 *            下单结束时间
	 * @param userName
	 *            会员名称
	 * @param restat
	 *            预约状态
	 * @param pageNum
	 *            当前页数
	 * @param pageSize
	 *            每页记录数
	 * @return
	 */
	Page<OrderDetailVo> searchOrderListBySellerId(Long sellerId, String detailNo, String prodName, Long[] orstat,
			String begin, String ending, String userName, Long[] restat, Integer pageNum, Integer pageSize)
			throws Exception;

	/**
	 * 查询未发货订单总数
	 * 
	 * @param sellerId
	 *            商家编号
	 * @param detailNo
	 *            订单编号
	 * @param prodName
	 *            商品名称
	 * @param orstat
	 *            订单状态
	 * @param begin
	 *            下单开始时间
	 * @param ending
	 *            下单结束时间
	 * @param userName
	 *            会员名称
	 * @return
	 */
	int getOrderCountBySeller(Long sellerId, String detailNo, String prodName, Long[] orstat, String begin,
			String ending, String userName) throws Exception;

	/***
	 * 根据商家编号查询退款记录
	 * 
	 * @param sellerId
	 *            商家编号
	 * @param detailNo
	 *            订单号
	 * @param orderStatus
	 *            订单状态
	 * @param pageNum
	 *            当前页数
	 * @param pageSize
	 *            每页记录数
	 * @return
	 */
	Page<RefundVo> queryRefundListBySellerId(Long sellerId, String detailNo, Long orderStatus, int pageNum, int pageSize)
			throws Exception;

	/**
	 * 获取退款记录详情
	 * 
	 * @param id
	 *            退款编号
	 * @return
	 */
	UserRefund getRefundById(Long id) throws Exception;

	/**
	 * 后台查询订单列表
	 * 
	 * @param orderNo
	 *            订单编号
	 * @param detailNo
	 *            详单编号
	 * @param status
	 *            订单状态
	 * @param storeName
	 *            商铺名称
	 * @param userName
	 *            会员帐号
	 * @param begin
	 *            开始时间
	 * @param ending
	 *            结束时间
	 * @param pageNum
	 *            当前页数
	 * @param pageSize
	 *            每页记录
	 * @return
	 */
	Page<OrderDetailVo> queryOrderListByAdmin(String orderNo, String detailNo, Integer status, String storeName,
			String userName, String begin, String ending, Integer pageNum, Integer pageSize) throws ParseException;

	/***
	 * 根据会员编号查询账号对象
	 * 
	 * @param accountId
	 *            会员编号
	 * @return
	 */
	CashAccount queryCashAccount(Long accountId) throws Exception;

	/***
	 * 根据商品编号查询商品信息
	 * 
	 * @param productId
	 *            商品编号
	 * @return
	 */
	ProductInfo queryProductById(Long productId) throws Exception;

	/**
	 * 更新账户资金
	 * 
	 * @param cashAccount
	 * @return
	 */
	boolean updateCahsAccount(CashAccount cashAccount) throws Exception;

	/**
	 * 更新商品信息
	 * 
	 * @param info
	 *            商品信息
	 * @return
	 */
	boolean updateProductById(ProductInfo info) throws Exception;

	/***
	 * 添加订单详情
	 * 
	 * @param orderDetail
	 * @return
	 */
	boolean insertOrderDetail(OrderDetail orderDetail) throws Exception;

	/***
	 * 添加订单总表
	 * 
	 * @param orderInfo
	 * @return
	 */
	boolean insertOrderInfo(OrderInfo orderInfo) throws Exception;

	/**
	 * 添加用户订单
	 * 
	 * @param userId
	 *            用户编号
	 * @param prodIds
	 *            商品编号
	 * @param payPrics
	 *            购买单价
	 * @param payNums
	 *            购买数量
	 * @param addrId
	 *            收货地址编号
	 * @param phone
	 *            手机号
	 * @param couponId
	 *            优惠卷
	 * @return
	 * @throws Exception
	 */
	OrderResult insertUserOrder(Long userId, Long[] prodIds,Long[] storeIds, BigDecimal[] payPrics, Integer[] payNums, Long addrId,
			String phone, Long couponId,Date [] createTimes) throws Exception;

	/***
	 * 根据店铺编号查找店铺对象
	 * 
	 * @param storeId
	 *            店铺编号
	 * @return
	 * @throws Exception
	 */
	StoreInfo queryStoreById(Long storeId) throws Exception;

	/**
	 * 删除购物车商品
	 * 
	 * @param userId
	 *            用户编号
	 * @param prodId
	 *            商品编号
	 * @return
	 */
	boolean deleteCartProduct(Long userId, Long prodId,Long storeId,Date createTime) throws Exception;

	/***
	 * 支付订单
	 * 
	 * @param userId
	 *            用户编号
	 * @param orderId
	 *            大订单号
	 * @param result
	 *            支付结果
	 * @throws Exception
	 */
	public boolean orderPayment(Long userId, Long orderId, OrderResult result) throws Exception;

	/***
	 * 根据用户编号查询订单总表订单
	 * 
	 * @param userId
	 *            用户编号
	 * @return
	 */
	List<OrderInfo> queryOrderInfoList(Long userId) throws Exception;

	/***
	 * 根据总订单编号查询详细订单
	 * 
	 * @param orderId
	 *            总订单编号
	 * @return
	 */
	List<OrderDetail> queryOrderDetailList(Long orderId) throws Exception;

	/***
	 * 根据详细订单编号生成消费劵信息
	 * 
	 * @param detailId
	 *            详细订单编号
	 * @param volumeCode
	 *            消费卷密码
	 * @param productEndDate
	 *            商品结束时间
	 * 
	 * @return
	 */
	boolean insertVolume(Long detailId, String volumeCode, Date productEndDate) throws Exception;

	/***
	 * 根据详细订单编号修改详细订单状态
	 * 
	 * @param id
	 *            详细订单编号
	 * @param status
	 *            状态(1未付款；2已付款；3已消费(已收货)；4已评论；5申请退款；6退款成功；7申请售后；8归档；9已过期)
	 * @return
	 */
	boolean updateOrderDetail(Long id, int status) throws Exception;

	boolean updateOrderDetailPoints(Long id, BigDecimal points) throws Exception;

	/***
	 * 商家根据退款编号审核退款信息
	 * 
	 * @param isRefund
	 *            是否通过
	 * @param refund
	 *            退款对象
	 * @return
	 */
	boolean updateRefund(int isRefund, UserRefund refund) throws Exception;

	/***
	 * 确认收货
	 * 
	 * @param userId
	 *            用户编号
	 * @param detailId
	 *            小订单号
	 * @param status
	 *            状态(1未付款；2已付款；3已消费(已收货)；4已评论；5申请退款；6退款成功；7申请售后；8归档；9已过期)
	 * @return
	 * @throws Exception
	 */
	boolean updateDelivery(Long userId, Long detailId, int status) throws Exception;

	List<OrderDetailVo> queryOrderDetaiVoByProdIdAndUserId(Long prodId, Long uid, int i) throws Exception;

	/***
	 * 根据用户编号和小订单号取消用户退款
	 * 
	 * @param userId
	 *            用户编号
	 * @param detailId
	 *            订单编号
	 * @return
	 * @throws Exception
	 */
	boolean updateCancelRefund(Long userId, Long detailId) throws Exception;

	/***
	 * 修改订单详情
	 * 
	 * @param orderDetail
	 * @return
	 */
	boolean updateOrderDetailById(OrderDetail orderDetail) throws Exception;

	/**
	 * 删除超时未支付订单
	 * 
	 * @return
	 * @throws Exception
	 */
	boolean deleteOvertimeOrder() throws Exception;

	/**
	 * 修改总订单的积分使用
	 * 
	 * @param id
	 * @param payPoint
	 * @return
	 */
	boolean updateOrderInfo(Long id, Integer payPoint) throws Exception;
	public OrderInfo getOrderInfoByOrderNo(String orderNo);
	
	public OrderDetail getOrderDetailByOrderId(Long orderId);
	public String getVolumeCode() throws Exception ;
}