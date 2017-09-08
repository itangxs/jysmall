package cn.qhjys.mall.mapper.custom;

import java.util.Map;

import cn.qhjys.mall.vo.StoreRebateVo;

import com.github.pagehelper.Page;

public interface StoreRebateVoMapper {
	public Page<StoreRebateVo> queryStoreRebateVo(Map<String, Object> map);
	public int updateTimeOutRebate();
}
