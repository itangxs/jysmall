package cn.qhjys.mall.vo.seller;

import cn.qhjys.mall.common.BaseVo;

public class SellerScheduleInfoVo extends BaseVo{
	//商品ID
	private Long productId;
	//商品名称
	private String productTitle;
	//day第一天可以预约的数量
	private int day1;
	//day第二天可以预约的数量
	private int day2;
	private int day3;
	private int day4;
	private int day5;
	private int day6;
	private int day7;
	
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public int getDay1() {
		return day1;
	}
	public void setDay1(int day1) {
		this.day1 = day1;
	}
	public int getDay2() {
		return day2;
	}
	public void setDay2(int day2) {
		this.day2 = day2;
	}
	public int getDay3() {
		return day3;
	}
	public void setDay3(int day3) {
		this.day3 = day3;
	}
	public int getDay4() {
		return day4;
	}
	public void setDay4(int day4) {
		this.day4 = day4;
	}
	public int getDay5() {
		return day5;
	}
	public void setDay5(int day5) {
		this.day5 = day5;
	}
	public int getDay6() {
		return day6;
	}
	public void setDay6(int day6) {
		this.day6 = day6;
	}
	public int getDay7() {
		return day7;
	}
	public void setDay7(int day7) {
		this.day7 = day7;
	}

	
}
