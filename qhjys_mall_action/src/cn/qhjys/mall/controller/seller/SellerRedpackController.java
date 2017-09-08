package cn.qhjys.mall.controller.seller;

import java.math.BigDecimal;
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
import cn.qhjys.mall.entity.CashAccount;
import cn.qhjys.mall.entity.CashIntegralLog;
import cn.qhjys.mall.entity.FqRedpack;
import cn.qhjys.mall.entity.FqRedpackDetail;
import cn.qhjys.mall.entity.FqRedpackRecord;
import cn.qhjys.mall.entity.FqRedpackTime;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.service.CashAccountService;
import cn.qhjys.mall.service.CashIntegralLogService;
import cn.qhjys.mall.service.StoreService;
import cn.qhjys.mall.service.system.RedPackService;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.EncodeMD5;
import cn.qhjys.mall.util.SessionUtil;
import cn.qhjys.mall.vo.system.RedpackRecordVo;

@Controller
@RequestMapping("/managermall/seller/redpack")
public class SellerRedpackController extends Base{
	
	@Autowired
	CashAccountService cashAccountService;
	@Autowired
	RedPackService redPackService;
	@Autowired
	StoreService storeService;
	@Autowired
	CashIntegralLogService cashIntegralLogService;
	
	@RequestMapping("/activityDetail")
	public ModelAndView activityDetail() throws Exception{
		ModelAndView view = new ModelAndView("/seller/ad/huodong_detail");
	
		return view;
	}
	@RequestMapping("/chongzhi")
	public ModelAndView chongzhi(HttpSession session) throws Exception{
		ModelAndView view = new ModelAndView("/seller/ad/zhanghu_chongzhi");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		// 查询商家账户余额
		CashAccount cash = cashAccountService.queryCashAccount(seller.getId(), null);
		view.addObject("cash", cash);
		return view;
	}
	
	@RequestMapping("/chongzhitijiao")
	@ResponseBody
	public String chongzhitijiao(HttpSession session,String password,BigDecimal money) throws Exception{
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		// 查询商家账户余额
		CashAccount cash = cashAccountService.queryCashAccount(seller.getId(), null);
		if (!EncodeMD5.GetMD5Code(password).equals(seller.getPassword())) {
			return "密码错误";
		}
		if (cash.getBalance().compareTo(money) == -1) {
			return "账户余额不足";
		}
		int a = cashAccountService.updateBalanceToIntegral(seller.getId(), money);
		if (a == -1) {
			return "账户余额不足";
		}else if (a == 1) {
			return "充值成功";
		}else{
			return "充值失败";
		}
		
	}
	
	@RequestMapping("/activity")
	public ModelAndView activity(HttpSession session) throws Exception{
		ModelAndView view = new ModelAndView("/seller/ad/huodong_create");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		// 查询商家账户余额
		CashAccount cash = cashAccountService.queryCashAccount(seller.getId(), null);
		List<CashIntegralLog> list = cashIntegralLogService.queryCashIntegralLog(seller.getId());
		view.addObject("cash", cash);
		view.addObject("list", list);
		if (session.getAttribute("istanchu") == null) {
			view.addObject("istanchu", 1);
			session.setAttribute("istanchu","1");
		}else{
			view.addObject("istanchu", 0);
		}
		return view;
	}
	
	
	
	@RequestMapping("/createRedpack")
	public ModelAndView createRedpack(HttpSession session) throws Exception{
		ModelAndView view = new ModelAndView("/seller/ad/hongbao_ad");
		String token = UUID.randomUUID().toString();
		SessionUtil.setSession(session, "add_redpack_seller", token);
		view.addObject("token", token);
		return view;
	}
	
	@RequestMapping("/addRedpack")
	public Object honeBaoAdd(HttpSession session,
			String token,
			String activityName, String startDate,String endDate,
			Long[] startHour,Long[] startMinute,Long[] endHour,
			Long[] endMinute,String packMaxMoney,String packMinMoney,
			String[] subMoneyMin,String[] subMoneyMax,Integer[] fenduanGaiLv,
			String[] zhifujineMin,String[] zhifujineMax,Integer maxPackNum,String totalMoney) throws Exception {
		Object tokenSession = SessionUtil.getSession(session, "add_redpack_seller");
		SessionUtil.removeSession(session, "add_redpack_seller");
		if (tokenSession != null && tokenSession.equals(token)) {
			SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
			StoreInfo store = storeService.queryStoreInfoBySeller(seller.getId());
			int result = 0;
			try {
				result = redPackService.insertRedPackAndDetailAndTime(activityName, store.getId(), store.getName(), 
						startDate, endDate, startHour, startMinute, endHour, endMinute,
						packMaxMoney, packMinMoney, subMoneyMin, subMoneyMax, 
						fenduanGaiLv, 2, zhifujineMin, zhifujineMax, maxPackNum,totalMoney,1);
				if (result == -2) {
					return goUrl("/managermall/seller/redpack/redpacklist.do", "添加异常,请检查参数,不要填错");
				}
				if (result == -1) {
					return goUrl("/managermall/seller/redpack/redpacklist.do.do", "营销账户余额不足,请充值");
				}
				if (result == 0) {
					return goUrl("/managermall/seller/redpack/redpacklist.do", "添加失败,请检查参数");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return goUrl("/managermall/seller/redpack/redpacklist.do", "添加成功");
			
		}else {
			return goUrl("/managermall/seller/redpack/redpacklist.do", "请不要重复提交");
		}
	}
	
	@RequestMapping("/redpacklist")
	public ModelAndView hongBaoList(HttpSession session,Integer pageNum) throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		StoreInfo store = storeService.queryStoreInfoBySeller(seller.getId());
		ModelAndView view = new ModelAndView("/seller/ad/hongbao_list");
		if (pageNum == null ) {
			pageNum = 1;
		}
		Page<FqRedpack> datas = redPackService.queryRedpackList(store.getId(),pageNum, 10);
		view.addObject("page", datas);
		view.addObject("pageNum", pageNum);
		return view;
	}
	
	@RequestMapping("/hongbao_detail")
	public ModelAndView honeBaoDetail(@RequestParam(value = "redpackId", required = true) Long redpackId
			) throws Exception {
		ModelAndView view = new ModelAndView("/seller/ad/hongbao_detail");
		
		// 查询redpack
		FqRedpack fqRedpack = redPackService.queryRedpackById(redpackId);
		// 查询红包详情
		List<FqRedpackDetail> details = redPackService.queryFqRedpackDetailByRedpackId(redpackId);
		// 查询红包时间
		List<FqRedpackTime> redpackTimes = redPackService.queryRedpackTimeByRedpackId(redpackId);
		
		List<FqRedpackRecord> page = redPackService.queryRedpackRecordByRedpackId(redpackId);
		
		RedpackRecordVo recordVo = redPackService.queryRedpackRecordVoByRedpackId(redpackId);
		
		view.addObject("fqRedpack", fqRedpack);
		view.addObject("redpackId", redpackId);
		view.addObject("details", details);
		view.addObject("redpackTimes", redpackTimes);
		view.addObject("recordVo", recordVo);
		view.addObject("page", page);
		return view;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(Long id) throws Exception {
		boolean result = redPackService.deleteRedpack(id);
		if (result) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	@RequestMapping("/update_status")
	@ResponseBody
	public String updateStatus(Long id,Integer status)throws Exception {
		int result = redPackService.updateRedpackStatus(id, status);
		if (result == 1) {
			return "success";
		}else if (result == -2) {
			return "already";
		}else{
			return "fail";
		}
	}
	@RequestMapping("/hongbao_modify")
	public ModelAndView hongBaoModify(HttpSession session,@RequestParam(value = "redpackId", required = true) 
		Long redpackId) throws Exception {
		ModelAndView view = new ModelAndView("/seller/ad/hongbao_modify");
		// 查询redpack
		FqRedpack fqRedpack = redPackService.queryRedpackById(redpackId);
		// 查询红包详情
		List<FqRedpackDetail> details = redPackService.queryFqRedpackDetailByRedpackId(redpackId);
		// 查询红包时间
		List<FqRedpackTime> redpackTimes = redPackService.queryRedpackTimeByRedpackId(redpackId);
		
		view.addObject("fqRedpack", fqRedpack);
		view.addObject("details", details);
		view.addObject("redpackTimes", redpackTimes);
		view.addObject("redpackId", redpackId);
		String token = UUID.randomUUID().toString();
		SessionUtil.setSession(session, "seller_update_redpack", token);
		view.addObject("token", token);
		// details 不会为空，为空说明数据异常
		FqRedpackDetail detail = details.get(0);
		view.addObject("randType", detail.getType());
		return view;
	}
	
	@RequestMapping("/hongbao_update")
	public Object hongBaoUpdate(HttpSession session,
			@RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "redpackId", required = true) Long redpackId,
			Long[] detailIds,Integer[] detailNewIds,Long[] timeIds,Integer[] timeNewIds,
			String activityName,Long storeId,String storeName, String startDate,String endDate,
			Long[] startHour,Long[] startMinute,Long[] endHour,Long[] endMinute,
			Long[] startHourNew,Long[] startMinuteNew,Long[] endHourNew,Long[] endMinuteNew,
			String packMaxMoney,String packMinMoney,
			String[] subMoneyMin,String[] subMoneyMax,String[] subMoneyNewMin,String[] subMoneyNewMax,
			Integer[] fenduanGaiLv,Integer[] fenduanGaiLvNew,Integer randType,
			String[] zhifujineMin,String[] zhifujineMax,String[] zhifujineNewMin,String[] zhifujineNewMax,
			Integer maxPackNum,String totalMoney,Integer quanxian) throws Exception {
		Object tokenSession = SessionUtil.getSession(session, "seller_update_redpack");
		SessionUtil.removeSession(session, "seller_update_redpack");
		if (tokenSession != null && tokenSession.equals(token)) {
			boolean result = false;
			try {
				result = redPackService.updateRedpackAndDetailAndTime(redpackId, detailIds,
						detailNewIds, timeIds, timeNewIds, activityName, storeId, storeName, 
						startDate, endDate, startHour, startMinute, endHour, endMinute, startHourNew,
						startMinuteNew, endHourNew, endMinuteNew, packMaxMoney, packMinMoney, 
						subMoneyMin, subMoneyMax, subMoneyNewMin, subMoneyNewMax, fenduanGaiLv,
						fenduanGaiLvNew, randType, zhifujineMin, zhifujineMax, zhifujineNewMin, 
						zhifujineNewMax, maxPackNum,totalMoney,quanxian);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (result) {
				return goUrl("/managermall/seller/redpack/redpacklist.do", "保存成功");
			}
			
			return goUrl("/managermall/seller/redpack/redpacklist.do", "保存异常,请检查参数,不要填错");
			
		}else {
			return goUrl("/managermall/seller/redpack/redpacklist.do", "请不要重复提交");
		}
	}
}
