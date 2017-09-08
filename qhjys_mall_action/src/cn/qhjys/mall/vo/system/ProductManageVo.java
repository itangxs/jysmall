package cn.qhjys.mall.vo.system;

import java.math.BigDecimal;
import java.util.Date;
import cn.qhjys.mall.common.BaseVo;

/***
 * 总后台商品管理vo
 * 
 * @author zengrong
 *
 */
@SuppressWarnings("serial")
public class ProductManageVo extends BaseVo {
	private Long id;//
	private Long productId;// 商品编号
	private String productName;// 商品名
	private String storeName;// 商户名
	private float score;// 评分
	private BigDecimal unitPrice;// 商品折扣价
	private BigDecimal origPrice;// 商品原价
	private int quantity;// 销售数量
	private Date endDate;// 结束日期
	private Date offDate;// 下架日期
	private int enabled;// 商品是否启售(0禁售，1启售)
	private int level;

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

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
}