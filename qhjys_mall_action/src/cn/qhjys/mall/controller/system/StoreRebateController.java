package cn.qhjys.mall.controller.system;

import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.StoreRebate;
import cn.qhjys.mall.service.StoreService;
import cn.qhjys.mall.service.system.StoreRebateService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.vo.StoreRebateVo;

import com.github.pagehelper.Page;

@Controller
@RequestMapping("/managermall/systemmall/rebate")
public class StoreRebateController extends Base{
	
	@Autowired
	private StoreRebateService storeRebateService;
	
	@Autowired
	private StoreService storeService;
	
	@RequestMapping("/list")
	public ModelAndView listRebate(String storeName,String rebateName, String benginTime,
			String endTime, Integer status,Integer pageNum ,Integer pageSize){
		ModelAndView view = new ModelAndView("system/product/discount_list");
		if (pageNum == null) {
			pageNum = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		Page<StoreRebateVo> page = storeRebateService.queryStoreRebateVos(storeName, rebateName, benginTime, endTime, status, pageNum, pageSize);
		view.addObject("page", page);
		view.addObject("storeName", storeName);
		view.addObject("rebateName", rebateName);
		view.addObject("benginTime", benginTime);
		view.addObject("endTime", endTime);
		view.addObject("status", status);
		return view;
	}
	
	@RequestMapping("/addRebate")
	public Object addRebate(Long id,Long storeId,String rebateName,String beginDate,String endDate,BigDecimal ortherRebate,BigDecimal rebate,String zexplain){
		StoreRebate srebate = new StoreRebate();
		if (!StringUtils.isEmpty(beginDate)) {
			srebate.setBeginDate(BaseUtil.toDate(beginDate+" 00:00:00"));
		}
		if (!StringUtils.isEmpty(endDate)) {
			srebate.setEndDate(BaseUtil.toDate(endDate+" 23:59:59"));
		}
		srebate.setCreateTime(new Date());
		srebate.setLevel(0);
		srebate.setOrtherRebate(ortherRebate);
		srebate.setRebate(rebate);
		srebate.setRebateName(rebateName);
		srebate.setStatus(0);
		srebate.setStoreId(storeId);
		srebate.setZexplain(zexplain);
		StoreInfo store = storeService.getStoreInfoById(storeId);
		srebate.setSellerId(store.getSellerId());
		int a= 0;
		if (id != null) {
			srebate.setId(id);
			a = storeRebateService.updateStoreRebate(srebate);
		}else{
			a = storeRebateService.insertStoreRebate(srebate);
		}
		if (a > 0) {
			return super.goUrl("/managermall/systemmall/rebate/list.do", "操作成功");
		}
		return super.goUrl("/managermall/systemmall/rebate/list.do", "操作失败");
	}
	
	@RequestMapping("/getStoreName")
	public void getStoreName(Long storeId,HttpServletResponse response){
		StoreInfo store = storeService.getStoreInfoById(storeId);
		String json = "";
		if (store != null) {
			json += store.getName();
		}
		HtmlUtil.writerJson(response, json);
	}
	
	@RequestMapping("/changeLevel")
	public void changeLevel(Long rebateId,Integer level,HttpServletResponse response){
		StoreRebate srebate = new StoreRebate();
		srebate.setId(rebateId);
		srebate.setLevel(level);
		int a = storeRebateService.updateStoreRebate(srebate);
		String json = "";
		if (a >0) {
			json = "success";
		}
		HtmlUtil.writerJson(response, json);
	}
	@RequestMapping("/changestatus")
	public void changeStatus(String ids,Integer status,HttpServletResponse response){
		int a = storeRebateService.updateRebateStatus(ids, status);
		if (a == 0) {
			HtmlUtil.writerJson(response, "操作成功");
		}else if(a == 1){
			HtmlUtil.writerJson(response, "请确认选择的折扣商家没有已上架的折扣");
		}else{
			HtmlUtil.writerJson(response, "操作失败");
		}
	}
}
