package cn.qhjys.mall.service;

import java.util.List;
import cn.qhjys.mall.entity.AreaInfo;

public interface AreaInfoService {
	/**
	 * 获取城市下的区域
	 * 
	 * @param cityId
	 *            城市编号
	 * @return
	 */
	List<AreaInfo> selectAreaByCity(Long cityId) throws Exception;
}