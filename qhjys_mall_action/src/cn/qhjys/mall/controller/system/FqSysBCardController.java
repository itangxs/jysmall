package cn.qhjys.mall.controller.system;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.FqArea;
import cn.qhjys.mall.entity.FqBcard;
import cn.qhjys.mall.entity.FqBcardPrize;
import cn.qhjys.mall.entity.FqBcardRule;
import cn.qhjys.mall.entity.FqCity;
import cn.qhjys.mall.entity.FqDistrict;
import cn.qhjys.mall.entity.FqIndustry;
import cn.qhjys.mall.entity.FqStore;
import cn.qhjys.mall.service.fq.FqStoreService;
import cn.qhjys.mall.service.fq.SellerWXService;
import cn.qhjys.mall.service.system.FqBcardRuleService;
import cn.qhjys.mall.service.system.FqBcardService;
import cn.qhjys.mall.service.system.FqCityService;
import cn.qhjys.mall.service.system.FqIndustryService;
import cn.qhjys.mall.util.SessionUtil;
import cn.qhjys.mall.vo.system.FqBcardRuleNameVo;

@Controller
@RequestMapping("/managermall/systemmall/ad")
public class FqSysBCardController extends Base{
	
	@Autowired
	FqBcardService fqBcardService;
	@Autowired
	FqCityService fqCityService;
	@Autowired
	FqIndustryService fqIndustryService;
	@Autowired
	FqStoreService fqStoreService;
	@Autowired
	SellerWXService sellerWXService;
	@Autowired
	FqBcardRuleService fqBcardRuleService;
	
	@RequestMapping("/bcard_create")
	public ModelAndView bcardCreate(HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView("/system/ad/bcard_ad");
		String token = UUID.randomUUID().toString();
		SessionUtil.setSession(session, "add_bcard", token);
		view.addObject("token", token);
		return view;
	}
	
	@RequestMapping("/bcard_add")
	public Object bcardAdd(HttpSession session,
			@RequestParam(value = "token", required = true) String token,
			Long storeId,String beginDate,String endDate,Integer validityDate,
			String cardDescript,Integer pushNum,String[] prizeName,String[] prizeLine,String[] prizeInfo, String[] imgs,
			Long[] city,Long[] district,Long[] area,Long[] industry,Long[] industryDetail) throws Exception {
		Object tokenSession = SessionUtil.getSession(session, "add_bcard");
		SessionUtil.removeSession(session, "add_bcard");
		if (tokenSession != null && tokenSession.equals(token)) {
			
			FqStore fqStore = sellerWXService.queryFqStoreById(storeId);
			if (fqStore == null) {
				return goUrl("/managermall/systemmall/ad/bcard_list.do", "商家ID异常");
			}
			boolean result = false;
			try {
				result = fqBcardService.insertBcardAndPrizeAndRule(storeId,fqStore.getStoreName(),beginDate,
						endDate, validityDate, cardDescript, pushNum,prizeName, prizeLine,prizeInfo,
						imgs, city, district, area, industry, industryDetail);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (result) {
				return goUrl("/managermall/systemmall/ad/bcard_list.do", "添加成功");
			}else {
				return goUrl("/managermall/systemmall/ad/bcard_list.do", "添加失败,请检查参数,不要填错");
			}
		}else{
			return goUrl("/managermall/systemmall/ad/bcard_list.do", "请不要重复提交");
		}
	}
	
	@RequestMapping("/bcard_detail")
	public ModelAndView bcardDetail(@RequestParam(value = "bcardId", required = true) Long bcardId,
			@RequestParam(value = "storeId", required = true) Long storeId) throws Exception {
		ModelAndView view = new ModelAndView("/system/ad/bcard_detail");
		FqBcard bcard = fqBcardService.queryBcard(bcardId);
		List<FqBcardPrize> prizes = fqBcardService.queryBcardPrize(bcardId);
		List<FqBcardRuleNameVo> ruleNames = fqBcardService.queryBcardRuleName(bcardId);
		view.addObject("bcard", bcard);
		view.addObject("prizes", prizes);
		view.addObject("ruleNames", ruleNames);
		return view;
	}
	
	@RequestMapping("/bcard_modify")
	public ModelAndView bcardModify(HttpSession session,
			@RequestParam(value = "bcardId", required = true) Long bcardId
			) throws Exception {
		ModelAndView view = new ModelAndView("/system/ad/bcard_modify");
		FqBcard bcard = fqBcardService.queryBcard(bcardId);
		List<FqBcardPrize> prizes = fqBcardService.queryBcardPrize(bcardId);
		List<FqBcardRule> rules = fqBcardService.queryBcardRule(bcardId);
		String token = UUID.randomUUID().toString();
		SessionUtil.setSession(session, "update_bcard", token);
		view.addObject("token", token);
		view.addObject("bcard", bcard);
		view.addObject("prizes", prizes);
		view.addObject("rules", rules);
		return view;
	}
	
	@RequestMapping("/bcard_update")
	public Object bcardUpdate(HttpSession session,
			@RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "bcardId", required = true) Long bcardId,
			Long storeId,String beginDate,String endDate,Integer validityDate,
			String cardDescript,Integer pushNum,Long[] prizeIds,String[] prizeName,
			String[] prizeLine,String[] prizeInfo, String[] imgs,
			Long[] ruleIds,Long[] city,Long[] district,Long[] area,Long[] industry,Long[] industryDetail,
			Long[] cityNew,Long[] distNew,Long[] areaNew,Long[] induNew,Long[] indudeNew)
					throws Exception{
		Object tokenSession = SessionUtil.getSession(session, "update_bcard");
		SessionUtil.removeSession(session, "update_bcard");
		if (tokenSession != null && tokenSession.equals(token)) {
			boolean result = false;
			try {
				result = fqBcardService.updateFqBcardAndPrizeAndRule(bcardId,
						storeId, beginDate, endDate, validityDate, cardDescript,pushNum,
						prizeIds, prizeName, prizeLine,prizeInfo, imgs, ruleIds, 
						city, district, area, industry, industryDetail,
						cityNew,distNew,areaNew,induNew,indudeNew);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (result) {
				return goUrl("/managermall/systemmall/ad/bcard_list.do", "保存成功");
			}else {
				return goUrl("/managermall/systemmall/ad/bcard_list.do", "保存失败,请检查参数,不要填错");
			}
		}else {
			return goUrl("/managermall/systemmall/ad/bcard_list.do", "请不要重复提交");
		}
	}
	
	@RequestMapping("/bcard_delete_rule")
	@ResponseBody
	public String deleteRule(Long ruleId) throws Exception {
		boolean result = fqBcardRuleService.deleteRuleById(ruleId);
		if (result) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	
	@RequestMapping("/bcard_list")
	public ModelAndView bcardList(String storeName,Integer pageNum) throws Exception {
		if (pageNum == null) {
			pageNum = 1;
		}
		ModelAndView view = new ModelAndView("/system/ad/bcard_list");
		Page<FqBcard> page = fqBcardService.queryBcardByStoreName(storeName, pageNum, 10);
		view.addObject("storeName", storeName);
		view.addObject("page", page);
		return view;
	}
	
	@RequestMapping("/bcard_update_status")
	@ResponseBody
	public String updateStatus(Long id,Integer status) throws Exception {
		boolean result = fqBcardService.updateBcardStatusById(id, status);
		if (result) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	@RequestMapping("/bcard_delete")
	@ResponseBody
	public String delete(Long id) throws Exception {
		boolean result = fqBcardService.deleteBcardById(id);
		if (result) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	@RequestMapping("/bcard_query_city")
	@ResponseBody
	public List<FqCity> queryCity() throws Exception {
		List<FqCity> citys = fqCityService.queryCitys();
		return citys;
	}
	
	@RequestMapping("/bcard_query_district")
	@ResponseBody
	public List<FqDistrict> queryDistrict(Long cityId) throws Exception {
		List<FqDistrict> districts = fqCityService.queryDistricts(cityId);
		return districts;
	}
	
	@RequestMapping("/bcard_query_area")
	@ResponseBody
	public List<FqArea> queryArea(Long districtId) throws Exception {
		List<FqArea> areas = fqCityService.queryAreas(districtId);
		return areas;
	}
	
	@RequestMapping("/bcard_query_industry")
	@ResponseBody
	public List<FqIndustry> queryIndustryDetail(Long parentId) throws Exception {
		List<FqIndustry> fqIndustries = fqIndustryService.queryIndustryDetail(parentId);
		return fqIndustries;
	}
	
}
