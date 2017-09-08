package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TaskInfo implements Serializable {
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

    private String projectPass;

    private String infoUrl;

    private String infoName;

    private String bgimg;

    private String bgcolor;

    private String taskDetail;

    private String fulfilDetail;

    private static final long serialVersionUID = 1L;

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
        this.taskName = taskName == null ? null : taskName.trim();
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
        this.taskFrom = taskFrom == null ? null : taskFrom.trim();
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
        this.planTime = planTime == null ? null : planTime.trim();
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
        this.images = images == null ? null : images.trim();
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
        this.project = project == null ? null : project.trim();
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl == null ? null : projectUrl.trim();
    }

    public String getProjectPass() {
        return projectPass;
    }

    public void setProjectPass(String projectPass) {
        this.projectPass = projectPass == null ? null : projectPass.trim();
    }

    public String getInfoUrl() {
        return infoUrl;
    }

    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl == null ? null : infoUrl.trim();
    }

    public String getInfoName() {
        return infoName;
    }

    public void setInfoName(String infoName) {
        this.infoName = infoName == null ? null : infoName.trim();
    }

    public String getBgimg() {
        return bgimg;
    }

    public void setBgimg(String bgimg) {
        this.bgimg = bgimg == null ? null : bgimg.trim();
    }

    public String getBgcolor() {
        return bgcolor;
    }

    public void setBgcolor(String bgcolor) {
        this.bgcolor = bgcolor == null ? null : bgcolor.trim();
    }

    public String getTaskDetail() {
        return taskDetail;
    }

    public void setTaskDetail(String taskDetail) {
        this.taskDetail = taskDetail == null ? null : taskDetail.trim();
    }

    public String getFulfilDetail() {
        return fulfilDetail;
    }

    public void setFulfilDetail(String fulfilDetail) {
        this.fulfilDetail = fulfilDetail == null ? null : fulfilDetail.trim();
    }
}