package cn.qhjys.mall.vo;

import java.util.Date;

public class FeedbackVo {
	  	private Long id;

	    private Long sellerId;

	    private String title;


	    private Date feedbackTime;

	    private String content;
	    
	    private String storeName;
	    
	    private Long storeId;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Long getSellerId() {
			return sellerId;
		}

		public void setSellerId(Long sellerId) {
			this.sellerId = sellerId;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public Date getFeedbackTime() {
			return feedbackTime;
		}

		public void setFeedbackTime(Date feedbackTime) {
			this.feedbackTime = feedbackTime;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getStoreName() {
			return storeName;
		}

		public void setStoreName(String storeName) {
			this.storeName = storeName;
		}

		public Long getStoreId() {
			return storeId;
		}

		public void setStoreId(Long storeId) {
			this.storeId = storeId;
		}

		public FeedbackVo(Long id, Long sellerId, String title,
				Date feedbackTime, String content, String storeName,
				Long storeId) {
			super();
			this.id = id;
			this.sellerId = sellerId;
			this.title = title;
			this.feedbackTime = feedbackTime;
			this.content = content;
			this.storeName = storeName;
			this.storeId = storeId;
		}

		public FeedbackVo() {
			super();
		}
	    
	    
}
