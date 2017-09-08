package cn.qhjys.mall.service;

import java.util.List;
import cn.qhjys.mall.entity.AreaInfo;
import cn.qhjys.mall.entity.CityInfo;
import cn.qhjys.mall.entity.ProvinceInfo;

public interface PositionService {

	/**
	 * 获取省份
	 * 
	 * @return
	 */
	List<ProvinceInfo> queryProvince() throws Exception;

	/**
	 * 获取城市
	 * 
	 * @param provId
	 * @return
	 */
	List<CityInfo> queryCityByProvince(Long provId) throws Exception;

	/**
	 * 获取区域
	 * 
	 * @param cityId
	 * @return
	 */
	List<AreaInfo> queryAreaByCity(Long cityId) throws Exception;
}