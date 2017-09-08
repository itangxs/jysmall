package cn.qhjys.mall.weixin.qqpay;

public class CQpayAPIURL {

    /**
	 * 统一下单
	 * 
	 */
	public static String getUnifiedOrderUrl() {
		return "https://qpay.qq.com/cgi-bin/pay/qpay_unified_order.cgi";
	}
	
	/**
	 * 关闭订单
	 * @return
	 */
	public static String getCloseOrderUrl() {
		return "https://qpay.qq.com/cgi-bin/pay/qpay_close_order.cgi";
	}
	
	/**
	 * 申请退款
	 * @return
	 */
	public static String getRefundUrl() {
		return "https://api.qpay.qq.com/cgi-bin/pay/qpay_refund.cgi";
	}
	

	/**
	 * 订单查询
	 * 
	 */
	public static String getOrderQueryUrl() {
		return "https://qpay.qq.com/cgi-bin/pay/qpay_order_query.cgi";
	}
	
	
	/**
	 * 
	 */
	public static String getMicroPayUrl() {
		return "https://qpay.qq.com/cgi-bin/pay/qpay_micro_pay.cgi";
	}
	

	/**
	 * 
	 * @return
	 */
	public static String getReverseUrl() {
		return "https://api.qpay.qq.com/cgi-bin/pay/qpay_reverse.cgi";
	}

	
}
