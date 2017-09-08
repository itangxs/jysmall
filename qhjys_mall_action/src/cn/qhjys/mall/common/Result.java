package cn.qhjys.mall.common;

import java.io.Serializable;

/**
 * 响应结果对象
 * 
 * @author 研发部-llw
 *
 */
public class Result implements Serializable {
	private static final long serialVersionUID = 6288374846131788743L;

	/**
	 * 信息
	 */
	private String msg;

	/**
	 * 状态码
	 */
	private int status;

	/**
	 * 错误码
	 */
	private int code;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Result() {

	}
}
