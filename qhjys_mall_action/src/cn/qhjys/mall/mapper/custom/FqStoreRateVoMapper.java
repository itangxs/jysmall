package cn.qhjys.mall.mapper.custom;

import java.util.Map;

import cn.qhjys.mall.vo.system.FqStoreRateVo;

import com.github.pagehelper.Page;

public interface FqStoreRateVoMapper {
	
	public Page<FqStoreRateVo> queryFqStoreRateVo(Map<String, Object> map);
}
