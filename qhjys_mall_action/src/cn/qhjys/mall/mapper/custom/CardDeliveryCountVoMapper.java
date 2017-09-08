package cn.qhjys.mall.mapper.custom;

import java.util.Map;

import cn.qhjys.mall.vo.system.CardDeliveryCountVo;

import com.github.pagehelper.Page;

public interface CardDeliveryCountVoMapper {
	
	public Page<CardDeliveryCountVo> queryCardDeliveryCount(Map<String, Object> map);
	
	public Page<CardDeliveryCountVo> queryDeliverData(Map<String,Object> map);
	
	public Page<CardDeliveryCountVo> queryValiData(Map<String,Object> map );
}
