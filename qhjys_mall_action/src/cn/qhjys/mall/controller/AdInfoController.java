package cn.qhjys.mall.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.service.AdInfoService;
import cn.qhjys.mall.vo.AdInfoVo;

/***
 * 
 * @author zengrong
 *
 */
@Controller
@RequestMapping("/adInfo")
public class AdInfoController  extends Base{
	
	@Autowired
	private AdInfoService adInfoService;
	
	/**
	 * 广告接口(前台首页)
	 * @param positionId 广告位置
	 * @param type 广告类型
	 * @param start 广告开始时间
	 * @param end 广告结束时间
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryAdInfoVoList", method = RequestMethod.POST)
	public List<AdInfoVo> queryAdInfoVoList(Long positionId, Integer type, String start, String end, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return adInfoService.queryAdInfoVoList(positionId, type, start, end);
	}
	
}
