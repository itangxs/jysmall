package cn.qhjys.mall.controller.fq;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.FqCuisine;
import cn.qhjys.mall.entity.FqLocation;
import cn.qhjys.mall.entity.FqRebate;
import cn.qhjys.mall.entity.FqStore;
import cn.qhjys.mall.entity.FqStoreCheck;
import cn.qhjys.mall.entity.FqWithdrawChangeinfo;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.service.SellerService;
import cn.qhjys.mall.service.fq.FqWithdrawChangeInfoService;
import cn.qhjys.mall.service.fq.SellerWXService;
import cn.qhjys.mall.util.SessionUtil;

/**
 * 
 * 类名称:SystemWxStoreController
 * 类描述:系统后台管理微信店铺
 * 创建人:JiangXiaoPing
 * 创建时间:2016年5月10日上午10:41:11
 * 修改人
 * 修改时间:
 * 修改备注:
 */
@Controller
public class SystemWxStoreController extends Base {
	@Autowired
	private SellerWXService sellerWXService;
	@Autowired
	SellerService sellerService;
	@Autowired
	FqWithdrawChangeInfoService fqWithdrawChangeInfoService;
	
	@RequestMapping("/managermall/systemmall/store/wxlist")
	public ModelAndView wxStoreList(
			Long sellerid,Long wxstoreid,
			String wxstorename,String startdate,
			String enddate,
			Integer status,Integer pageNum,Integer pagesize
			) throws ParseException, Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(null==pageNum)
			pageNum = 1;
		if(null==pagesize)
			pagesize = 10;
		
		Page<FqStore> pageFqStores = sellerWXService.queryFqStoreByPage(sellerid,wxstoreid,wxstorename,StringUtils.isEmpty(startdate)?null:format.parse(startdate),StringUtils.isEmpty(enddate)?null:format.parse(enddate),status,pageNum,pagesize);
		ModelAndView view = new ModelAndView("/system/store/wx_store_list");
		view.addObject("page",pageFqStores);
		view.addObject("pageNum",pageNum);
		view.addObject("sellerid",sellerid);
		view.addObject("wxstoreid",wxstoreid);
		view.addObject("wxstorename",wxstorename);
		view.addObject("startDate",startdate);
		view.addObject("endDate",enddate);
		view.addObject("status",status);
		
		return view;
	}
	


	@RequestMapping("/managermall/systemmall/store/wx_store")
	public ModelAndView wx_store(
			HttpSession session,
			@RequestParam(value="id",required = true)Long id
			) throws Exception{
		ModelAndView view = new ModelAndView("/system/store/wx_store");
		FqStore fqStore = sellerWXService.queryFqStoreById(id);
		List<FqCuisine> fqCuisines =sellerWXService.queryFqCuisineListByEnabled(null);
		List<FqLocation> Location =sellerWXService.querFqLocationsListByEnabled(null);
		SellerInfo sellerInfo = sellerService.getSellerById(fqStore.getSellerId());
		FqWithdrawChangeinfo changeinfo = fqWithdrawChangeInfoService.queryFqWithdrawChangeInfoBySellerIdAndDate(sellerInfo.getId(), new Date());
		if (changeinfo != null) {
			view.addObject("withdrawStatus", changeinfo.getStatusAfter());
		}else {
			view.addObject("withdrawStatus", sellerInfo.getWithdrawStatus());
		}
		view.addObject("fqCuisines",fqCuisines);
		view.addObject("fqLocation",Location);
		view.addObject("fqStore",fqStore);
		
		if(fqStore.getType()==1){
			List<FqRebate> fqRebates = sellerWXService.queryFqRebateListByfqStoreId(fqStore.getId());
			view.addObject("fqRebates",fqRebates);
			String weixinRebateToken = UUID.randomUUID().toString();
			SessionUtil.setSession(session, "weixinRebateToken", weixinRebateToken);
			view.addObject("weixinRebateToken",weixinRebateToken);
		}
		return view;
	}
	
	@RequestMapping("/managermall/systemmall/store/updateWxStore")
	public Object  updateStore(
			@RequestParam(value="id",required = true)Long id,
			@RequestParam(value="status",required = true)Integer status,
			@RequestParam(value="level",required = true)Integer level,
			@RequestParam(value="type",required = true)Integer type,
			@RequestParam(value="clerkPhone",required = true)String clerkPhone,
			@RequestParam(value="daliyCredit",required = true)BigDecimal daliyCredit,
			@RequestParam(value="withdrawStatus",required = true)Integer withdrawStatus
			) throws Exception{
		if (StringUtils.isEmpty(clerkPhone)) {
			clerkPhone = null;
		}
		int o = sellerWXService.updateFqStore(id, status, null,type,level,clerkPhone,daliyCredit,withdrawStatus);
		if(o==1){
			return super.goUrl("/managermall/systemmall/store/wxlist.do", "修改成功");
		}else{
			return	super.goUrl("/managermall/systemmall/store/wxlist.do", "修改失败");
		}
	}
	

	//折扣列表
	@RequestMapping("/managermall/systemmall/store/wx_store_rebate_list")
	public ModelAndView wx_store_rebate_list(
			@RequestParam(value = "id",required = true)Long id,
			@RequestParam(value = "sellerid",required = true)Long sellerid,
			HttpSession session,
			Integer pageNum,Integer pagesize
			) throws ParseException, Exception{
		if(null==pageNum)
			pageNum = 1;
		if(null==pagesize)
			pagesize = 10;
		ModelAndView view = new ModelAndView("/system/store/wx_store_rebate_list");
		Page<FqRebate> page =sellerWXService.queryFqRebateByStoreIdList(id,pageNum,pagesize);
		view.addObject("page",page);
		String weixinRebateToken = UUID.randomUUID().toString();
		SessionUtil.setSession(session, "weixinRebateToken", weixinRebateToken);
		view.addObject("weixinRebateToken",weixinRebateToken);
		view.addObject("id",id);
		view.addObject("sellerid",sellerid);
		return view;
	}
	
	@RequestMapping("/managermall/systemmall/store/wx_store_rebate_page")
	public ModelAndView storewx_store_rebate_page(
			@RequestParam(value = "id",required = false)Long id,
			@RequestParam(value = "sellerid",required = true)Long sellerid,
			@RequestParam(value = "sid",required = true)Long sid,
			HttpSession session
			) throws ParseException, Exception{
		ModelAndView view = new ModelAndView("/system/store/wx_store_rebate_page");
		String weixinRebateToken = UUID.randomUUID().toString();
		SessionUtil.setSession(session, "weixinRebateToken", weixinRebateToken);
		view.addObject("weixinRebateToken", weixinRebateToken);
		if(null!=id){
			FqRebate fqRebate=  sellerWXService.queryFqRebateById(id);
			view.addObject("fqRebate", fqRebate);
		}
		view.addObject("sid",sid);
		view.addObject("sellerid",sellerid);
		return view;
	}
	
	
	@RequestMapping("/managermall/systemmall/store/saveorupdate")
	public Object saveorupdate(
			String token,
			Long sellerid,
			Long id,
			Long sid,
			String rebate,
			String rebateInfo,
			String beginTime,
			String endTime,
			Integer enable,
			HttpSession session
			) throws Exception{
		String string = SessionUtil.getSession(session, "weixinRebateToken").toString();
		String url = "/managermall/systemmall/store/wx_store_rebate_list.do?id="+sid+"%26sellerid="+sellerid;
		if(StringUtils.isEmpty(string)||!token.equals(string)){
			return super.goUrl(url, "请不要重复提交");
		}
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		Date parse2 =null;
		Date parse =null;
		try {
			 parse2 = format.parse(beginTime);
			 parse = format.parse(endTime);
		} catch (ParseException e) {
			return super.goUrl(url, "开始或者结束时间错误");
		}
		if(parse2.getTime()>=parse.getTime()){
			return super.goUrl(url, "结束时间必须大于开始时间");
		}
		FqStore fqStore = sellerWXService.queryFqStoreBySellerId(sellerid);
		if(fqStore.getType()!=1){
			return super.goUrl(url,  "用户不是金融商家.不能做操作");
		}
		Boolean l=sellerWXService.saveOrUpdateFqRebate(sellerid,id,new BigDecimal(rebate) ,rebateInfo,beginTime,endTime,enable);
		if(l){
			return super.goUrl(url,  "成功");
		}else{
			return super.goUrl(url,  "失败");
		}
	}
	
	@RequestMapping("/managermall/systemmall/store/wx_store_rebate_delete")
	public Object saveorupdate(
			@RequestParam(value="sellerid",required =true)Long sellerid,
			@RequestParam(value="id",required =true)Long id,
			@RequestParam(value="sid",required =true)Long sid
			) throws Exception{
		String url = "/managermall/systemmall/store/wx_store_rebate_list.do?id="+sid+"%26sellerid="+sellerid;
		
		Boolean boolean1 =  sellerWXService.deleteFqrebateByIdAndStroeId(id,sid);
		if(boolean1){
			return super.goUrl(url,  "删除成功");
		}else{
			return super.goUrl(url,  "删除失败");
		}
		
	}
	
	@RequestMapping("/managermall/systemmall/store/wxChecklist")
	public ModelAndView wxStoreCheckList(
			Long wxstoreid,
			String wxstorename,String startdate,
			String enddate,
			Integer status,Integer pageNum,Integer pagesize
			) throws ParseException, Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(null==pageNum)
			pageNum = 1;
		if(null==pagesize)
			pagesize = 10;
		
		Page<FqStoreCheck> pageFqStores = sellerWXService.queryFqStoreCheckByPage(wxstoreid,wxstorename,StringUtils.isEmpty(startdate)?null:format.parse(startdate+" 00:00:00"),StringUtils.isEmpty(enddate)?null:format.parse(enddate+" 23:59:59"),status,pageNum,pagesize);
		ModelAndView view = new ModelAndView("/system/store/wx_store_check_list");
		view.addObject("page",pageFqStores);
		view.addObject("pageNum",pageNum);
		view.addObject("wxstoreid",wxstoreid);
		view.addObject("wxstorename",wxstorename);
		view.addObject("startDate",startdate);
		view.addObject("endDate",enddate);
		view.addObject("status",status);
		
		return view;
	}
	@RequestMapping("/managermall/systemmall/store/wxStoreCheck")
	public ModelAndView wxStoreCheck(Long id) throws Exception{
		ModelAndView view = new ModelAndView("/system/store/wx_store_check");
		FqStoreCheck storeCheck = sellerWXService.getFqStoreCheckById(id);
		List<FqLocation> locations =sellerWXService.querFqLocationsListByEnabled(null);
		view.addObject("storeCheck", storeCheck);
		view.addObject("fqLocation", locations);
		return view;
	}
	
	@RequestMapping("/managermall/systemmall/store/updateWxStoreCheck")
	@ResponseBody
	public String updateWxStoreCheck(Long id,Integer status){
		int a = sellerWXService.updateWxStoreCheck(id, status);
		if (a>0) {
			return "success";
		}
		return "error";
	}
}
