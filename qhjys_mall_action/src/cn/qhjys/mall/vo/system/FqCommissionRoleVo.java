package cn.qhjys.mall.vo.system;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FqCommissionRoleVo implements Serializable {
    private Long id;

    private Date createTime;

    private Long adminId;
    
    private String createName;

    private String roleName;

    private BigDecimal teamProportion;

    private BigDecimal clerkProportion;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }
	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
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
}