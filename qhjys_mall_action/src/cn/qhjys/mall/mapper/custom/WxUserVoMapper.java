package cn.qhjys.mall.mapper.custom;

import java.util.List;

import cn.qhjys.mall.vo.WxUserVo;

public interface WxUserVoMapper {

	public List<WxUserVo> querySellerWxUserVo(Long sellerId);
	
	public List<String> querySellerWxUserOpenId(Long sellerId);
}