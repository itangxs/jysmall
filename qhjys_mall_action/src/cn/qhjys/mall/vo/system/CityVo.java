package cn.qhjys.mall.vo.system;

import java.util.List;

public class CityVo {
	
	private Long id;
	private String name;
	private List<DistrictVo> districts;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<DistrictVo> getDistricts() {
		return districts;
	}
	public void setDistricts(List<DistrictVo> districts) {
		this.districts = districts;
	}
	
	
}
