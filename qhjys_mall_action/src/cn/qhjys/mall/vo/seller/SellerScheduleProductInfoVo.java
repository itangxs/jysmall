package cn.qhjys.mall.vo.seller;

import cn.qhjys.mall.common.BaseVo;

public class SellerScheduleProductInfoVo extends BaseVo{
	
	//产品ID
	private Long productId;
	//产品标题
	private String title;
	//可预约数量
	private Integer scheduleer;
	//是否可以预约
	private Integer scheduleType;
	
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getScheduleer() {
		return scheduleer;
	}
	public void setScheduleer(Integer scheduleer) {
		this.scheduleer = scheduleer;
	}
	public Integer getScheduleType() {
		return scheduleType;
	}
	public void setScheduleType(Integer scheduleType) {
		this.scheduleType = scheduleType;
	}
	
	

}
