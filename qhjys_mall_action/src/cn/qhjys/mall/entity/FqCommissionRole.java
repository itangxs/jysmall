package cn.qhjys.mall.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FqCommissionRole implements Serializable {
    private Long id;

    private Long adminId;

    private String roleName;

    private BigDecimal teamProportion;

    private BigDecimal clerkProportion;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public BigDecimal getTeamProportion() {
        return teamProportion;
    }

    public void setTeamProportion(BigDecimal teamProportion) {
        this.teamProportion = teamProportion;
    }

    public BigDecimal getClerkProportion() {
        return clerkProportion;
    }

    public void setClerkProportion(BigDecimal clerkProportion) {
        this.clerkProportion = clerkProportion;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}