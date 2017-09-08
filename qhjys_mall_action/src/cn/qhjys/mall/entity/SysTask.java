package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SysTask implements Serializable {
    private Long id;

    private String taskName;

    private String project;

    private Integer taskType;

    private Integer taskLevel;

    private Date beginTime;

    private Date endTime;

    private Integer taskTotal;

    private Integer taskFulfil;

    private BigDecimal fulfilReward;

    private BigDecimal unfulfilReward;

    private Long taskFrom;

    private String staff;

    private Integer verifyTime;

    private Integer appraiseRank;

    private Integer status;

    private String appid;

    private String secret;

    private String accessToken;

    private Date createTime;

    private Long sellerId;

    private String taskContent;

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

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project == null ? null : project.trim();
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
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

    public Integer getTaskTotal() {
        return taskTotal;
    }

    public void setTaskTotal(Integer taskTotal) {
        this.taskTotal = taskTotal;
    }

    public Integer getTaskFulfil() {
        return taskFulfil;
    }

    public void setTaskFulfil(Integer taskFulfil) {
        this.taskFulfil = taskFulfil;
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

    public Long getTaskFrom() {
        return taskFrom;
    }

    public void setTaskFrom(Long taskFrom) {
        this.taskFrom = taskFrom;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff == null ? null : staff.trim();
    }

    public Integer getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Integer verifyTime) {
        this.verifyTime = verifyTime;
    }

    public Integer getAppraiseRank() {
        return appraiseRank;
    }

    public void setAppraiseRank(Integer appraiseRank) {
        this.appraiseRank = appraiseRank;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret == null ? null : secret.trim();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent == null ? null : taskContent.trim();
    }
}