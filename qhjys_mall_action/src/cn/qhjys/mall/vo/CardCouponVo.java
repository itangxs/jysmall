package cn.qhjys.mall.vo;

import java.util.Date;

/**
 * 我的卡包 卡券VO
 * @author Administrator
 *
 */
public class CardCouponVo {
	
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;
	
	private Integer couponCfg;

	private String storeName;
	
	private Date validityStarttime;
	
	private Date validityEndtime;

	private Integer statusCfg;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCouponCfg() {
		return couponCfg;
	}

	public void setCouponCfg(Integer couponCfg) {
		this.couponCfg = couponCfg;
	}


	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Date getValidityStarttime() {
		return validityStarttime;
	}

	public void setValidityStarttime(Date validityStarttime) {
		this.validityStarttime = validityStarttime;
	}

	public Date getValidityEndtime() {
		return validityEndtime;
	}

	public void setValidityEndtime(Date validityEndtime) {
		this.validityEndtime = validityEndtime;
	}

	public Integer getStatusCfg() {
		return statusCfg;
	}

	public void setStatusCfg(Integer statusCfg) {
		this.statusCfg = statusCfg;
	}

	

}