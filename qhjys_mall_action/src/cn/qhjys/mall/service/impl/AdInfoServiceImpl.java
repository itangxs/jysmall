package cn.qhjys.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cn.qhjys.mall.entity.AdInfo;
import cn.qhjys.mall.mapper.AdInfoMapper;
import cn.qhjys.mall.mapper.custom.AdInfoVoMapper;
import cn.qhjys.mall.service.AdInfoService;
import cn.qhjys.mall.vo.AdInfoVo;
import cn.qhjys.mall.vo.AdVo;

/***
 * 
 * @author zengrong
 *
 */
@Service
public class AdInfoServiceImpl implements AdInfoService {

	@Autowired
	private AdInfoVoMapper adInfoVoMapper;
	@Autowired
	private AdInfoMapper adInfoMapper;

	@Override
	public List<AdInfoVo> queryAdInfoVoList(Long positionId, Integer type, String start, String end) throws Exception {
		AdInfoVo adInfoVo = new AdInfoVo();
		adInfoVo.setPositionId(positionId);
		adInfoVo.setMediaType(type);
		adInfoVo.setStartTime(start);
		adInfoVo.setEndTime(end);
		return adInfoVoMapper.queryAdInfoVoList(adInfoVo);
	}

	@Override
	public AdInfo queryAdInfo(Long id) {
		return adInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<AdVo> queryAdInfoVoList(Long positionId, Integer pageNum,
			Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return adInfoVoMapper.queryAdVoList(positionId);
	}
}