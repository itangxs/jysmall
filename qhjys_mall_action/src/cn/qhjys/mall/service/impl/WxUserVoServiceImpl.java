package cn.qhjys.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.qhjys.mall.mapper.custom.WxUserVoMapper;
import cn.qhjys.mall.service.WxUserVoService;
import cn.qhjys.mall.vo.WxUserVo;

@Service("wxUserVoService")
public class WxUserVoServiceImpl implements WxUserVoService {
	
	@Autowired
	private WxUserVoMapper wxUserVoMapper;
	
	@Override
	public Page<WxUserVo> listWxUserBySeller(Long sellerId,Integer pageSize,Integer pageNum) {
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		PageHelper.startPage(pageNum, pageSize);
		return (Page<WxUserVo>) wxUserVoMapper.querySellerWxUserVo(sellerId);
	}

	@Override
	public List<String> listWxUserOpenIdBySeller(Long sellerId) {
		return wxUserVoMapper.querySellerWxUserOpenId(sellerId);
	}

}
