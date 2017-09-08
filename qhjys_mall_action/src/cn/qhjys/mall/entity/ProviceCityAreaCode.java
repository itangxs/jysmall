package cn.qhjys.mall.entity;

import java.io.Serializable;

/**
 * 省市code码表
 * @author huangsy
 *
 */
public class ProviceCityAreaCode implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
    private String areaCode;
    private String areaName;
    private String areaParentId;
    private Integer areaType;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAreaParentId() {
		return areaParentId;
	}
	public void setAreaParentId(String areaParentId) {
		this.areaParentId = areaParentId;
	}
	public Integer getAreaType() {
		return areaType;
	}
	public void setAreaType(Integer areaType) {
		this.areaType = areaType;
	}
}