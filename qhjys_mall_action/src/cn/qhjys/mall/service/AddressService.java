package cn.qhjys.mall.service;

import java.util.List;
import cn.qhjys.mall.entity.AreaInfo;
import cn.qhjys.mall.entity.CityInfo;
import cn.qhjys.mall.entity.ProvinceInfo;

public interface AddressService {

	/**
	 * 
	 * @Title: queryProvinceInfo 查詢省份信息
	 * @Description: TODO
	 * @param
	 * @return
	 * @throws Exception
	 * @return ProvinceInfo
	 */
	public List<ProvinceInfo> queryProvinceInfo() throws Exception;

	/**
	 * 
	 * @Title: queryCityInfo 查詢城市信息
	 * @Description: TODO
	 * @param provinceId
	 *            省份编号
	 * @return
	 * @throws Exception
	 * @return CityInfo
	 */
	public List<CityInfo> queryCityInfo(Long provinceId) throws Exception;

	/**
	 * 
	 * @Title: queryDistrictInfo 查詢区域信息
	 * @Description: TODO
	 * @param cityId
	 *            城市编号
	 * @return
	 * @throws Exception
	 * @return DistrictInfo
	 */
	public List<AreaInfo> queryAreaInfo(Long cityId) throws Exception;

	public List<CityInfo> queryCityInfo() throws Exception;

	public List<AreaInfo> queryAreaInfo() throws Exception;

	public CityInfo getCityInfoById(Long cityId) throws Exception;

	/**
	 * 
	 * @Title: queryProvinceInfo 查詢省份信息
	 * @Description: TODO
	 * @param provinceId
	 *            省份编号
	 * @return
	 * @throws Exception
	 * @return ProvinceInfo
	 */
	public ProvinceInfo getProvinceInfoById(Long provinceId) throws Exception;

	/**
	 * 
	 * @Title: queryProvinceInfo 查詢区域信息
	 * @Description: TODO
	 * @param areaId
	 *            区域编号
	 * @return
	 * @throws Exception
	 * @return ProvinceInfo
	 */
	public AreaInfo getAreaInfoById(Long areaId) throws Exception;

}
