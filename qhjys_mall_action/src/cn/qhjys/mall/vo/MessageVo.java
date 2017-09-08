package cn.qhjys.mall.vo;

import java.util.Date;

import cn.qhjys.mall.common.BaseVo;

/**
 * 
 * @author Administrator
 * 前台用户站内,会员消息Vo
 */
public class MessageVo  extends BaseVo{

	private Long id;
	
	//前台用户ID
	private Long userId;
	//类型
	private int type;
	//状态
	private int status;
	//创建时间
	private  Date createDate;
	//消息内容
	private String msg;
	//标题
	private String title;
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
