package cn.qhjys.mall.vo;

import java.math.BigDecimal;
import java.util.Date;

import cn.qhjys.mall.common.BaseVo;

public class TaskVo extends BaseVo{
	
	 	private Long id;

	    private String taskName;

	    private Integer taskType;

	    private String taskFrom;

	    private Integer taskLevel;

	    private Date beginTime;

	    private Date endTime;

	    private Date offShelf;

	    private BigDecimal taskTotal;

	    private BigDecimal taskFulfil;

	    private String planTime;

	    private BigDecimal fulfilReward;

	    private BigDecimal unfulfilReward;

	    private String images;

	    private Integer status;

	    private Date createTime;

	    private String project;

	    private String projectUrl;

	    
	    private Long uid;
	    
	    private Long userId;

	    private Long taskId;

	    private String ustatus;

	    private BigDecimal totamt;

	    private Date ucreateTime;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTaskName() {
			return taskName;
		}

		public void setTaskName(String taskName) {
			this.taskName = taskName;
		}

		public Integer getTaskType() {
			return taskType;
		}

		public void setTaskType(Integer taskType) {
			this.taskType = taskType;
		}

		public String getTaskFrom() {
			return taskFrom;
		}

		public void setTaskFrom(String taskFrom) {
			this.taskFrom = taskFrom;
		}

		public Integer getTaskLevel() {
			return taskLevel;
		}

		public void setTaskLevel(Integer taskLevel) {
			this.taskLevel = taskLevel;
		}

		public Date getBeginTime() {
			return beginTime;
		}

		public void setBeginTime(Date beginTime) {
			this.beginTime = beginTime;
		}

		public Date getEndTime() {
			return endTime;
		}

		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}

		public Date getOffShelf() {
			return offShelf;
		}

		public void setOffShelf(Date offShelf) {
			this.offShelf = offShelf;
		}

		public BigDecimal getTaskTotal() {
			return taskTotal;
		}

		public void setTaskTotal(BigDecimal taskTotal) {
			this.taskTotal = taskTotal;
		}

		public BigDecimal getTaskFulfil() {
			return taskFulfil;
		}

		public void setTaskFulfil(BigDecimal taskFulfil) {
			this.taskFulfil = taskFulfil;
		}

		public String getPlanTime() {
			return planTime;
		}

		public void setPlanTime(String planTime) {
			this.planTime = planTime;
		}

		public BigDecimal getFulfilReward() {
			return fulfilReward;
		}

		public void setFulfilReward(BigDecimal fulfilReward) {
			this.fulfilReward = fulfilReward;
		}

		public BigDecimal getUnfulfilReward() {
			return unfulfilReward;
		}

		public void setUnfulfilReward(BigDecimal unfulfilReward) {
			this.unfulfilReward = unfulfilReward;
		}

		public String getImages() {
			return images;
		}

		public void setImages(String images) {
			this.images = images;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public String getProject() {
			return project;
		}

		public void setProject(String project) {
			this.project = project;
		}

		public String getProjectUrl() {
			return projectUrl;
		}

		public void setProjectUrl(String projectUrl) {
			this.projectUrl = projectUrl;
		}

		public Long getUid() {
			return uid;
		}

		public void setUid(Long uid) {
			this.uid = uid;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public Long getTaskId() {
			return taskId;
		}

		public void setTaskId(Long taskId) {
			this.taskId = taskId;
		}

		public String getUstatus() {
			return ustatus;
		}

		public void setUstatus(String ustatus) {
			this.ustatus = ustatus;
		}

		public BigDecimal getTotamt() {
			return totamt;
		}

		public void setTotamt(BigDecimal totamt) {
			this.totamt = totamt;
		}

		public Date getUcreateTime() {
			return ucreateTime;
		}

		public void setUcreateTime(Date ucreateTime) {
			this.ucreateTime = ucreateTime;
		}
	    
	    
}
