package cn.qhjys.mall.util;

import com.alibaba.fastjson.JSONObject;

public class AppResult {
	// 操作结果：0成功，1失败
	private int flag;
	// 操作结果
	private String reason;
	// 返回JSON数据
	private JSONObject data;

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

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}
}