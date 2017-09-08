package cn.qhjys.mall.common;

import java.util.HashMap;
import java.util.Map;

/**
 * JSONResult : Response JSONResult for RESTful,封装返回JSON格式的数据
 * 
 * @author 研发部-鄢松涛
 *
 * @param <T>
 */
public class JSONResult extends Result {

	private static final long serialVersionUID = 7880907731807860636L;

	/**
	 * 数据
	 */
	private Map<String, Object> data;

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	/**
	 * 默认正确响应
	 */
	public JSONResult() {
		super();
		super.setCode(0);
		super.setStatus(1);
		super.setMsg("");
	}

	/**
	 * 错误响应
	 * 
	 * @param error
	 */
	public JSONResult(ErrorCode error) {
		super();
		super.setCode(error.getCode());
		super.setMsg(error.getMsg());
		super.setStatus(0);
		this.data = new HashMap<String, Object>();
	}
	/**
	 * 错误响应和提示
	 * @param error
	 */
	public JSONResult(ErrorCode error,String msg) {
		super();
		super.setCode(error.getCode());
		super.setMsg(error.getMsg());
		if(msg !=null){
			super.setMsg(msg);
		}
		super.setStatus(0);
		this.data = new HashMap<String, Object>();
	}

	/**
	 * 正确响应
	 * 
	 * @param t
	 */
	public JSONResult(Map<String, Object> map) {
		super();
		super.setCode(0);
		super.setStatus(1);
		this.data = map;
	}

	/**
	 * 添加参数
	 * 
	 * @param key
	 * @param obj
	 */
	public JSONResult put(String key, Object obj) {
		if (data == null) {
			data = new HashMap<String, Object>();
		}
		data.put(key, obj);
		return this;
	}
}