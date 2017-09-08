package cn.qhjys.mall.service;

import java.util.List;

import com.github.pagehelper.Page;

import cn.qhjys.mall.vo.WxUserVo;

public interface WxUserVoService {

	public Page<WxUserVo> listWxUserBySeller(Long sellerId,Integer pageSize,Integer pageNum);
	public List<String> listWxUserOpenIdBySeller(Long sellerId);
	
}
