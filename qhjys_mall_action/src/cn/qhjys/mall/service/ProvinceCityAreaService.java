package cn.qhjys.mall.service;

import java.util.List;
import cn.qhjys.mall.entity.AreaInfo;
import cn.qhjys.mall.entity.CityInfo;
import cn.qhjys.mall.entity.ProvinceInfo;

public interface ProvinceCityAreaService {

	/**
	 * 通过城市id 得到具体城市
	 * 
	 * @param id
	 * @return
	 */
	public CityInfo getCityInfoById(Long id) throws Exception;

	/**
	 * 得到某一省份ID下面的所有城市集合
	 * 
	 * @param provinceInfoid
	 * @return
	 */
	public List<CityInfo> getCityInfoByProvinceInfoId(Long provinceInfoid) throws Exception;

	/**
	 * 通过省份id 得到具体省份
	 * 
	 * @param id
	 * @return
	 */
	public ProvinceInfo getProvinceInfoById(Long id) throws Exception;

	/**
	 * 通过地区id 得到具体地区
	 * 
	 * @param id
	 * @return
	 */
	public AreaInfo getAreaInfoById(Long id) throws Exception;

	/**
	 * 通过城市id 得到所有的地区集合
	 * 
	 * @param cityInfoid
	 * @return
	 */
	public List<AreaInfo> getAreaInfoByCityInfoId(Long cityInfoid) throws Exception;

}
