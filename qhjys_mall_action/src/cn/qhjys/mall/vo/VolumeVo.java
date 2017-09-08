package cn.qhjys.mall.vo;

import java.math.BigDecimal;
import java.util.Date;

import cn.qhjys.mall.common.BaseVo;

@SuppressWarnings("serial")
public class VolumeVo extends BaseVo {
	// 消费卷编号
	private Long id;
	// 详单编号
	private Long detailId;
	// 商品名称
	private String prodName;
	// 消费卷密码
	private String volumeCode;
	// 过期日期
	private Date expiration;
	// 使用时间
	private Date useDate;
	// 消费卷状态
	private Integer status;
	
	private BigDecimal price;
	
	private String storeName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDetailId() {
		return detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getVolumeCode() {
		return volumeCode;
	}

	public void setVolumeCode(String volumeCode) {
		this.volumeCode = volumeCode;
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}