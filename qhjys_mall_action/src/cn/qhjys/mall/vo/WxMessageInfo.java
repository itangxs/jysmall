package cn.qhjys.mall.vo;

import java.util.List;

import cn.qhjys.mall.entity.WxMessage;

public class WxMessageInfo {
	private String path;
	private WxMessage message;
	private List<String> openidslist;
	private String username;
	
	
	
	public WxMessageInfo() {
		super();
	}
	public WxMessageInfo(String path, WxMessage message,
			List<String> openidslist) {
		super();
		this.path = path;
		this.message = message;
		this.openidslist = openidslist;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public WxMessage getMessage() {
		return message;
	}
	public void setMessage(WxMessage message) {
		this.message = message;
	}
	public List<String> getOpenidslist() {
		return openidslist;
	}
	public void setOpenidslist(List<String> openidslist) {
		this.openidslist = openidslist;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
