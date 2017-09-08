package cn.qhjys.mall.mapper.custom;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.qhjys.mall.vo.StorExporteVo;
import cn.qhjys.mall.vo.StoreVo;

import com.github.pagehelper.Page;

public interface StoreVoMapper {

	StoreVo getStoreById(@Param("storeId") Long storeId);

	StoreVo getStoreByProdId(@Param("prodId") Long prodId);

	Page<StoreVo> searchStoreByKeyword(Map<String, Object> map);
	
	Page<StoreVo> searchStoreAndSeller(Map<String, Object> map);
	
	List<StorExporteVo> searchStoreExporte(Map<String, Object> map);
}