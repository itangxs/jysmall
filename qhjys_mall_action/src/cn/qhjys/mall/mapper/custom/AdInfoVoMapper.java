package cn.qhjys.mall.mapper.custom;

import java.util.List;

import cn.qhjys.mall.vo.AdInfoVo;
import cn.qhjys.mall.vo.AdVo;

public interface AdInfoVoMapper {

	public List<AdInfoVo> queryAdInfoVoList(AdInfoVo adInfoVo);
	public List<AdVo> queryAdVoList(Long positionId);
}