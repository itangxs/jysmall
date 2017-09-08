package cn.qhjys.mall.vo;

import java.math.BigDecimal;


/**
 *  打折店铺信息
 *  @author llw
 *  @date 20160309
 *
 */
public class RebateStoreVo {
	
    private String name;//店铺名字

    private String logo;//店铺logo图片

    private String area;//店铺所在区域

    private String address;//店铺地址
    
    private Long rebateId;//打折id
    
    private BigDecimal rebate;//折扣

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getRebateId() {
		return rebateId;
	}

	public void setRebateId(Long rebateId) {
		this.rebateId = rebateId;
	}

	public BigDecimal getRebate() {
		return rebate;
	}

	public void setRebate(BigDecimal rebate) {
		this.rebate = rebate;
	}

}
