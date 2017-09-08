package cn.qhjys.mall.vo.system;

import java.util.List;

public class ProvinceVo {
	
	private Long id;
	private String name;
	private List<CityVo> citys;
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
	public List<CityVo> getCitys() {
		return citys;
	}
	public void setCitys(List<CityVo> citys) {
		this.citys = citys;
	}
	

}
