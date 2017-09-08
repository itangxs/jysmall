package cn.qhjys.mall.vo;

import java.math.BigDecimal;
import java.util.Date;

import cn.qhjys.mall.common.BaseVo;

public class StoreRebateVo extends BaseVo{
	 private Long id;

	    private Long storeId;
	    
	    private String rebateName;

	    private BigDecimal rebate;

	    private BigDecimal ortherRebate;

	    private Integer status;

	    private Date beginDate;

	    private Date endDate;

	    private Integer level;

	    private Date createTime;
	    
	    private String storeName;

	    private String storeLogo;

	    private String zexplain;
	    
	    
	    
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getStoreId() {
			return storeId;
		}

		public void setStoreId(Long storeId) {
			this.storeId = storeId;
		}

		public BigDecimal getRebate() {
			return rebate;
		}

		public void setRebate(BigDecimal rebate) {
			this.rebate = rebate;
		}

		public BigDecimal getOrtherRebate() {
			return ortherRebate;
		}

		public void setOrtherRebate(BigDecimal ortherRebate) {
			this.ortherRebate = ortherRebate;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public Date getBeginDate() {
			return beginDate;
		}

		public void setBeginDate(Date beginDate) {
			this.beginDate = beginDate;
		}

		public Date getEndDate() {
			return endDate;
		}

		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}

		public Integer getLevel() {
			return level;
		}

		public void setLevel(Integer level) {
			this.level = level;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public String getStoreName() {
			return storeName;
		}

		public void setStoreName(String storeName) {
			this.storeName = storeName;
		}

		public String getStoreLogo() {
			return storeLogo;
		}

		public void setStoreLogo(String storeLogo) {
			this.storeLogo = storeLogo;
		}

		public String getRebateName() {
			return rebateName;
		}

		public void setRebateName(String rebateName) {
			this.rebateName = rebateName;
		}

		public String getZexplain() {
			return zexplain;
		}

		public void setZexplain(String zexplain) {
			this.zexplain = zexplain;
		}
		
}
