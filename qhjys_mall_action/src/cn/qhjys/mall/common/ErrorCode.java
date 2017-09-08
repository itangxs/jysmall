package cn.qhjys.mall.common;

/**
 * 错误码定义
 * 
 * @author llw
 *
 */
public enum ErrorCode {
	
	// ======================个人中心错误定义 ====================//
	ERROR_10001(10001, "手机号已注册"),
	
	/** 验证码验证失败 */
	ERROR_10002(10002, "验证码验证失败"),
	
	/** 未注册 */
	ERROR_10003(10003, "手机未注册,请注册"),
	ERROR_10004(10004, "不能重复绑定邮箱"),
	ERROR_10005(10005, "该邮箱已被绑定"),
	// ======================个人中心错误定义 ====================//

	// ======================常规错误定义 ====================//
	/** 参数不全 */
	ERROR_40001(40001, "参数不全"),
	/** 参数格式错误 */
	ERROR_40002(40002, "参数格式错误"),
	/** 签名验证失败 */
	ERROR_40003(40003, "签名验证失败"),
	/** 资源不存在 */
	ERROR_40004(40004, "资源不存在"),
	/** 模块错误 */
	ERROR_40005(40005, "模块错误"),
	/** 方法错误 */
	ERROR_40006(40006, "方法错误"),
	/** 操作不可执行*/
	ERROR_40007(40007, "操作不可执行"),
	/** 订单不存在*/
	ERROR_40008(40008, "订单不存在"),
	/**订单己支付*/
	ERROR_40009(40009, "订单己支付"),
	/**订单己取消*/
	ERROR_40010(40010, "订单己取消"),
	/**签名失败*/
	ERROR_40011(40011, "签名失败"),
	
	// ======================系统错误定义 ====================//
	/** 系统错误 */
	ERROR_99999(99999, "系统错误");

	private Integer code;
	private String msg;

	private ErrorCode(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
