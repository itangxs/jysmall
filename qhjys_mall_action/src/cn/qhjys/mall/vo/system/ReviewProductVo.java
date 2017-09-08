package cn.qhjys.mall.vo.system;

import java.util.Date;
import cn.qhjys.mall.common.BaseVo;

/***
 * 总后台商品评论vo
 * @author zengrong
 *
 */
public class ReviewProductVo extends BaseVo{

	private Long id;//评价编号
	private String storeName;//店铺名称
	private Long productId;//商品编号
	private String productName;//商品名称
	private String userName;//用户名称
	private Date createTime;//评论时间
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
