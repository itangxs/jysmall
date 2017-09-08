package cn.qhjys.mall.util;

public class OrderResult {
	private boolean result;
	private int flag;
	private String reason;
	private Long orderId;

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public enum OrderEnum {
		SUCCESS(1, "添加用户订单成功！"), PARMT_ERROR(3, "参数错误！"), PROD_ERROR(4, "商品错误！"), ORDER_ERROR(5, "操作订单错误！"), PAY_SUCCESS(
				6, "支付成功！"), VOLUME_ERROR(7, "消费卷错误！"), PROD_STOCK_ERROR(8, "商品库存不足！"), ACCOUNT_ERROR(9, "资金账户异常！"), PROD_MAX_PAY(
				10, "已超过限购量"), BALANCE_LOW(11, "余额不足"), POINTS_ERROR(12, "积分消费错误!"), PROD_OFF_SHELF(13, "已下架!"), PROD_LOCK(
				14, "已禁售!");

		public final int tag;
		public final String msg;

		OrderEnum(int tag, String msg) {
			this.tag = tag;
			this.msg = msg;
		}

		public int getTag() {
			return tag;
		}

		public String getMsg() {
			return msg;
		}

		public static boolean isOrderType(int tag) {
			for (OrderEnum s : OrderEnum.values())
				if (s.getTag() == tag)
					return true;
			return false;
		}
	}
}