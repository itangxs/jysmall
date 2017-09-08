package cn.qhjys.mall.controller.fq;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import cn.qhjys.mall.entity.FqStore;
import cn.qhjys.mall.entity.FqStoreCredit;
import cn.qhjys.mall.service.fq.FqStoreCreditService;
import cn.qhjys.mall.service.fq.SellerWXService;
import cn.qhjys.mall.util.SessionUtil;
import cn.qhjys.mall.vo.system.FqStoreCreditVo;

/**
 * 系统借贷管理
 * @author apple
 */
@Controller
public class SystemBorrowController extends Base{
	@Autowired
	private SellerWXService sellerWXService;
	@Autowired
	FqStoreCreditService fqStoreCreditService;
	
	@RequestMapping("/managermall/systemmall/borrow/wxstorelist")
	public ModelAndView borrowStoreList(Long sellerid,Long wxstoreid,
			String wxstorename,String startdate,
			String enddate,
			Integer pageNum,Integer pagesize) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(null==pageNum)
			pageNum = 1;
		if(null==pagesize)
			pagesize = 10;
		
		Page<FqStore> pageFqStores = sellerWXService.queryFqStoreByPage(sellerid,wxstoreid,wxstorename,StringUtils.isEmpty(startdate)?null:format.parse(startdate),StringUtils.isEmpty(enddate)?null:format.parse(enddate),1,pageNum,pagesize);
		ModelAndView view = new ModelAndView("/system/borrow/all_wxstore_list");
		view.addObject("page",pageFqStores);
		view.addObject("pageNum",pageNum);
		view.addObject("sellerid",sellerid);
		view.addObject("wxstoreid",wxstoreid);
		view.addObject("wxstorename",wxstorename);
		view.addObject("startDate",startdate);
		view.addObject("endDate",enddate);
		
		return view;
	}
	
	/**
	 * 借贷商家列表
	 * @param sellerid
	 * @param wxstoreid
	 * @param wxstorename
	 * @param pageNum
	 * @param pagesize
	 * @return
	 * @throws ParseException
	 * @throws Exception
	 */
	@RequestMapping("/managermall/systemmall/borrow/brstorelist")
	public ModelAndView borrowStoreList(
			Long wxstoreid,String wxstorename,Integer pageNum,Integer pagesize
			) throws ParseException, Exception {
		if(null==pageNum)
			pageNum = 1;
		if(null==pagesize)
			pagesize = 10;
		
		Page<FqStoreCreditVo> fqStoreCreditVos = fqStoreCreditService.queryFqStoreFromCredit(
				wxstoreid, wxstorename, pageNum, pagesize);
		ModelAndView view = new ModelAndView("/system/borrow/borrow_wxstore_list");
		view.addObject("page",fqStoreCreditVos);
		view.addObject("pageNum",pageNum);
		view.addObject("wxstoreid",wxstoreid);
		view.addObject("wxstorename",wxstorename);
		return view;
	}
	
	/**
	 * 店铺借贷信息列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/managermall/systemmall/borrow/storeborrowlist")
	public ModelAndView storeBorrowList(Long wxstoreid,Integer period,String storeName,
			Integer pageNum,Integer pagesize) throws Exception {
		ModelAndView view = new ModelAndView("/system/borrow/store_borrow_list");
		if (pageNum == null)
			pageNum = 1;
		if (pagesize == null) {
			pagesize = 10;
		}
		Page<FqStoreCredit> credits = fqStoreCreditService.queryFqStoreCredit(wxstoreid, period, pageNum, pagesize);
		if (StringUtils.isEmpty(storeName)) {
			FqStore fqStore = sellerWXService.queryFqStoreById(wxstoreid);
			storeName = fqStore.getStoreName();
		}
		view.addObject("page", credits);
		view.addObject("pageNum", pageNum);
		view.addObject("wxstoreid", wxstoreid);
		view.addObject("wxstorename", storeName);
		return view;
	}
	
	@RequestMapping("/managermall/systemmall/borrow/updatecreditstatus")
	@ResponseBody
	public String  updateCreditStatus(@RequestParam(value = "ids", required = true) Long[] ids,
			@RequestParam(value = "status", required = true) Integer status) throws Exception {
		if (ids == null || ids.length < 1) {
			return "fail";
		}
		boolean result = fqStoreCreditService.updateCreditStatus(ids, status);
		if (result) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	/**
	 * 给店铺添加借贷信息
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/managermall/systemmall/borrow/addborrowinfo")
	public ModelAndView addBorrowInfo(HttpSession session,
			@RequestParam(value = "storeId", required = true) Long storeId,
			@RequestParam(value = "storeName", required = true) String storeName) throws Exception {
		ModelAndView view = new ModelAndView("/system/borrow/add_borrow_info");
		// 查最新一期的借款信息
		FqStoreCredit fqStoreCredit = fqStoreCreditService.queryLastCreditByStore(storeId);
		if (fqStoreCredit != null) {
			int period = fqStoreCredit.getPeriod() + 1;
			view.addObject("period", period);
			view.addObject("beforeMoney", fqStoreCredit.getNoRevert());
		}else {
			view.addObject("period", 1);
		}
		
		view.addObject("storeId", storeId);
		view.addObject("storeName", storeName);
		String token = UUID.randomUUID().toString();
		SessionUtil.setSession(session, "add_credit", token);
		view.addObject("token", token);
		return view;
	}
	
	@RequestMapping("/managermall/systemmall/borrow/add")
	public Object add(HttpSession session,FqStoreCredit fqStoreCredit,String token,
			@RequestParam(value = "ksrq", required = true) String ksrq,
			@RequestParam(value = "jsrq", required = true) String jsrq) throws Exception {
		Object tokenSession = SessionUtil.getSession(session, "add_credit");
		SessionUtil.removeSession(session, "add_credit");
		if (tokenSession != null && tokenSession.equals(token)) {
			if (fqStoreCredit == null) {
				return goUrl("/managermall/systemmall/borrow/wxstorelist.do", "提交异常");
			}
			
			BigDecimal beforeMoney = fqStoreCredit.getBeforeMoney();
			if (beforeMoney != null) {
				// 上期未还+本期借款金额，就是本期未还
				BigDecimal currentNoRevert = fqStoreCredit.getCreditMoney().add(beforeMoney);
				fqStoreCredit.setNoRevert(currentNoRevert);
				fqStoreCredit.setTotalMoney(currentNoRevert);
			}else {
				fqStoreCredit.setNoRevert(fqStoreCredit.getCreditMoney());
				fqStoreCredit.setTotalMoney(fqStoreCredit.getCreditMoney());
			}
			fqStoreCredit.setStatus(0);
			fqStoreCredit.setCreateTime(new Date());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
			fqStoreCredit.setStartTime(sdf.parse(ksrq));
			fqStoreCredit.setEndTime(sdf.parse(jsrq));
			
			Long storeId = fqStoreCredit.getStoreId();
			FqStore fqStore = sellerWXService.queryFqStoreById(storeId);
			fqStoreCredit.setSellerId(fqStore.getSellerId());
			boolean result = fqStoreCreditService.insertFqStoreCredit(fqStoreCredit);
			if (result) {
				return goUrl("/managermall/systemmall/borrow/storeborrowlist.do?wxstoreid="+storeId,
						"提交成功");
			}
		}else {
			return goUrl("/managermall/systemmall/borrow/wxstorelist.do", "请不要重复提交");
		}
		return goUrl("/managermall/systemmall/borrow/wxstorelist.do", "提交失败");
	}
	
	/**
	 * 借贷信息总列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/managermall/systemmall/borrow/allborrowlist")
	public ModelAndView allBorrowList(String wxstorename,Integer pageNum,Integer pagesize) throws Exception {
		ModelAndView view = new ModelAndView("/system/borrow/all_borrow_list");
		if (pageNum == null)
			pageNum = 1;
		if (pagesize == null) {
			pagesize = 10;
		}
		Page<FqStoreCredit> data = fqStoreCreditService.queryFqStoreCredit(wxstorename, pageNum, pagesize);
		view.addObject("page", data);
		view.addObject("wxstorename", wxstorename);
		view.addObject("pageNum",pageNum);
		return view;
	}
}
