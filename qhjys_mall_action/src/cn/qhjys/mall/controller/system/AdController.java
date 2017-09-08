package cn.qhjys.mall.controller.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.AdInfo;
import cn.qhjys.mall.entity.AdPosition;
import cn.qhjys.mall.service.AdService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.HtmlUtil;

@Controller
@RequestMapping("/managermall/systemmall/ad")
public class AdController extends Base{
	@Autowired
	private AdService adService;
	
	@RequestMapping("/list")
	public ModelAndView lisdtAd(Long city,Long apId,Integer page,Integer pageSize){

		if (page == null) {
			page = 1;
		}
		if(pageSize == null){
			pageSize = 10;
		}
		ModelAndView view = new ModelAndView("system/ad/list");
		Page<AdInfo> pages = adService.listAdInfo(city,apId, page, pageSize);
		List<AdPosition> aps = adService.listAdPositions();
		view.addObject("page", pages);
		view.addObject("aps", aps);
		view.addObject("apId", apId);
		view.addObject("city", city);
		return view;
	}
	
	@RequestMapping("/goSave")
	public ModelAndView goSave(){
		
		ModelAndView view = new ModelAndView("system/ad/saveAd");
		List<AdPosition> aps = adService.listAdPositions();
		view.addObject("aps", aps);
		return view;
	}
	@RequestMapping("/saveAd")
	public Object saveAd(String name,String image,String link ,Long positionId,String startTime,String endTime,Long city){
		AdInfo ad = new AdInfo();
		ad.setName(name);
		ad.setLink(link);
		ad.setImage(image);
		ad.setPositionId(positionId);
		ad.setStartTime(BaseUtil.strToDate(startTime));
		ad.setEndTime(BaseUtil.strToDate(endTime));
		if (null==city||city<1) {
			city=200L;
		}
		ad.setCityId(city);
		int a = adService.insertAdInfo(ad);
		if (a>0) {
			return super.goUrl("/managermall/systemmall/ad/list.do", "添加成功");
		}
		return super.goUrl("/managermall/systemmall/ad/list.do", "添加失败");
	}
	
	@RequestMapping("deleteAd") 
	public void deleteAd(Long id,HttpServletRequest request, HttpServletResponse response){
		int a = adService.deleteAdInfoById(id);
		if (a>0) {
			HtmlUtil.writerJson(response, "success");
		}else{
			HtmlUtil.writerJson(response, "error");
		}
	}
}
