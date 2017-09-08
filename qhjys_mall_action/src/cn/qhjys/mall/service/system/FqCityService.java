package cn.qhjys.mall.service.system;

import java.util.List;
import cn.qhjys.mall.entity.FqArea;
import cn.qhjys.mall.entity.FqCity;
import cn.qhjys.mall.entity.FqDistrict;

public interface FqCityService {

	List<FqCity> queryCitys() throws Exception;
	
	List<FqDistrict> queryDistricts(Long cityId) throws Exception;
	
	List<FqArea> queryAreas(Long districtId) throws Exception;
	
	FqCity queryCityById(Long id) throws Exception;
	FqDistrict queryDistrictById(Long id) throws Exception;
	FqArea queryAreaById(Long id) throws Exception;
}
