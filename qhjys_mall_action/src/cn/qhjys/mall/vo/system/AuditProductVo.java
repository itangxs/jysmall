package cn.qhjys.mall.vo.system;

import java.math.BigDecimal;
import java.util.Date;
import cn.qhjys.mall.common.BaseVo;

@SuppressWarnings("serial")
public class AuditProductVo extends BaseVo {
	private Long id;
	private Long productId;// 商品编号
	private String productName;// 商品名
	private String storeName;// 商户名
	private BigDecimal unitPrice;// 商品单价
	private BigDecimal origPrice;// 商品原价
	private Date createTime;// 商品创建时间
	private Date endDate;// 结束日期
	private Date offDate;// 下架日期
	private int status;// 审核状态

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getOrigPrice() {
		return origPrice;
	}

	public void setOrigPrice(BigDecimal origPrice) {
		this.origPrice = origPrice;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getOffDate() {
		return offDate;
	}

	public void setOffDate(Date offDate) {
		this.offDate = offDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}