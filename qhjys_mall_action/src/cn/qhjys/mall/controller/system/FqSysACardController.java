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
import cn.qhjys.mall.entity.FqAcard;
import cn.qhjys.mall.entity.FqAcardPrize;
import cn.qhjys.mall.entity.FqAcardTime;
import cn.qhjys.mall.entity.FqStore;
import cn.qhjys.mall.service.fq.SellerWXService;
import cn.qhjys.mall.service.system.FqACardService;
import cn.qhjys.mall.service.system.FqAcardTimeService;
import cn.qhjys.mall.util.SessionUtil;

@Controller
@RequestMapping("/managermall/systemmall/ad")
public class FqSysACardController extends Base{
	
	@Autowired
	FqACardService fqACardService;
	@Autowired
	SellerWXService sellerWXService;
	@Autowired
	FqAcardTimeService fqAcardTimeService;
	
	@RequestMapping("/acard_create")
	public ModelAndView aCardCreate(HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView("/system/ad/acard_create");
		String token = UUID.randomUUID().toString();
		SessionUtil.setSession(session, "add_acard", token);
		view.addObject("token", token);
		return view;
	}
	
	@RequestMapping("/acard_add")
	public Object aCardAdd(HttpSession session,
			@RequestParam(value = "token", required = true) String token,
			String acardName,Long storeId,String beginDate,String endDate,
			String[] startTime,String[] endTime,Integer pushNum,String[] prizeName,
			Integer[] prizeLine,String[] prizeDesc,String[] imgs,Integer[] probability) throws Exception {
		Object tokenSession = SessionUtil.getSession(session, "add_acard");
		SessionUtil.removeSession(session, "add_acard");
		if (tokenSession != null && tokenSession.equals(token)) {
			FqStore fqStore = sellerWXService.queryFqStoreById(storeId);
			if (fqStore == null) {
				return goUrl("/managermall/systemmall/ad/acard_list.do", "店铺异常,请检查店铺ID");
			}
			boolean result = false;
			try {
				result = fqACardService.insertAcardAndPrizeAndTime(acardName, storeId, 
						fqStore.getStoreName(), beginDate, endDate, startTime, endTime, pushNum, 
						prizeName, prizeLine, prizeDesc, imgs, probability);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (result) {
				return goUrl("/managermall/systemmall/ad/acard_list.do", "添加成功");
			}else {
				return goUrl("/managermall/systemmall/ad/acard_list.do", "添加异常,请检查参数,不要留空");
			}
			
		}else {
			return goUrl("/managermall/systemmall/ad/acard_list.do", "请不要重复提交");
		}
	}
	
	@RequestMapping("/acard_list")
	public ModelAndView aCardList(String activityName,String storeName,Integer pageNum) throws Exception {
		ModelAndView view = new ModelAndView("/system/ad/acard_list");
		if (pageNum == null) {
			pageNum = 1;
		}
		Page<FqAcard> page = fqACardService.queryAcard(activityName, storeName, pageNum, 10);
		view.addObject("page", page);
		view.addObject("activityName", activityName);
		view.addObject("storeName", storeName);
		return view;
	}
	
	@RequestMapping("/acard_update_status")
	@ResponseBody
	public String updateAcardStatus(Long id,Integer status) throws Exception {
		boolean result = fqACardService.updateAcardStatus(id, status);
		if (result) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	@RequestMapping("/acard_delete")
	@ResponseBody
	public String deleteAcard(Long id) throws Exception {
		boolean result = fqACardService.deleteAcard(id);
		if (result) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	@RequestMapping("/acard_detail")
	public ModelAndView aCardDetail(Long acardId,Long storeId) throws Exception {
		ModelAndView view = new ModelAndView("/system/ad/acard_detail");
		FqAcard acard = fqACardService.queryAcardById(acardId);
		List<FqAcardPrize> prizes = fqACardService.queryAcardPrizeByAcardId(acardId);
		List<FqAcardTime> times = fqACardService.queryAcardTimeByAcardId(acardId);
		view.addObject("acard", acard);
		view.addObject("prizes", prizes);
		view.addObject("times", times);
		return view;
	}
	
	@RequestMapping("/acard_modify")
	public ModelAndView aCardModify(HttpSession session,
			@RequestParam(value = "acardId", required = true) Long acardId) throws Exception {
		ModelAndView view = new ModelAndView("/system/ad/acard_modify");
		FqAcard acard = fqACardService.queryAcardById(acardId);
		List<FqAcardPrize> prizes = fqACardService.queryAcardPrizeByAcardId(acardId);
		List<FqAcardTime> times = fqACardService.queryAcardTimeByAcardId(acardId);
		view.addObject("acard", acard);
		view.addObject("prizes", prizes);
		view.addObject("times", times);
		String token = UUID.randomUUID().toString();
		SessionUtil.setSession(session, "update_acard", token);
		view.addObject("token", token);
		view.addObject("acardId", acardId);
		return view;
	}
	
	@RequestMapping("/acard_update")
	public Object aCardUpdate(HttpSession session,
			@RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "acardId", required = true) Long acardId,
			String acardName,Long storeId,String storeName,String beginDate,String endDate,
			Long[] timeIds,String[] startTime,String[] endTime,String[] startTimeNew,
			String[] endTimeNew,Integer pushNum,
			Long[] prizeIds,String[] prizeName,String[] prizeLine,String[] prizeDesc,
			String[] imgs,Integer[] probability) throws Exception {
		Object tokenSession = SessionUtil.getSession(session, "update_acard");
		SessionUtil.removeSession(session, "update_acard");
		if (tokenSession != null && tokenSession.equals(token)) {
			
			boolean result = false;
			try {
				result = fqACardService.updateAcardAndPrizeAndTime(acardId, acardName, storeId, 
						storeName, beginDate, endDate, timeIds, startTime, endTime, startTimeNew, 
						endTimeNew, pushNum, prizeIds, prizeName, prizeLine, prizeDesc, imgs, probability);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (result) {
				return goUrl("/managermall/systemmall/ad/acard_list.do", "保存成功");
			}else {
				return goUrl("/managermall/systemmall/ad/acard_list.do", "保存失败,请检查参数");
			}
		}else {
			return goUrl("/managermall/systemmall/ad/acard_list.do", "请不要重复提交");
		}
	}
	
	@RequestMapping("/acard_delete_time")
	@ResponseBody
	public String deleteTime(Long timeId) throws Exception {
		boolean result = fqAcardTimeService.deleteAcardTimeById(timeId);
		if (result) {
			return "success";
		}else {
			return "fail";
		}
	}
}
