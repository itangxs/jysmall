package cn.qhjys.mall.service;

import java.util.List;

import cn.qhjys.mall.entity.AdInfo;
import cn.qhjys.mall.vo.AdInfoVo;
import cn.qhjys.mall.vo.AdVo;

/***
 * 广告接口(首页)
 * 
 * @author zengrong
 *
 */
public interface AdInfoService {

	/***
	 * 广告接口(前台首页)
	 * 
	 * @param positionId
	 *            广告位置
	 * @param type
	 *            广告类型
	 * @param start
	 *            广告开始时间
	 * @param end
	 *            广告结束时间
	 * @return
	 */
	public List<AdInfoVo> queryAdInfoVoList(Long positionId, Integer type, String start, String end)
			throws Exception;
	public AdInfo queryAdInfo(Long id);
	public List<AdVo> queryAdInfoVoList(Long positionId, Integer pageNum,Integer pageSize);
}
