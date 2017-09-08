package cn.qhjys.mall.controller.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.Page;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.FqRedpack;
import cn.qhjys.mall.entity.FqRedpackDetail;
import cn.qhjys.mall.entity.FqRedpackRecord;
import cn.qhjys.mall.entity.FqRedpackTime;
import cn.qhjys.mall.entity.FqStore;
import cn.qhjys.mall.service.fq.SellerWXService;
import cn.qhjys.mall.service.system.RedPackService;
import cn.qhjys.mall.util.ExportExcelExtUtil;
import cn.qhjys.mall.util.SessionUtil;
import cn.qhjys.mall.vo.system.RedpackRecordVo;

@Controller
@RequestMapping("/managermall/systemmall/ad")
public class FqRedPackController extends Base {
	
	@Autowired
	RedPackService redPackService;
	@Autowired
	SellerWXService sellerWXService;
	@Autowired
	SellerWXService cashAccountService;

	@RequestMapping("/hongbao_create")
	public ModelAndView honeBaoCreate(HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView("/system/ad/hongbao_ad");
		// 查询店铺列表
		List<FqStore> fqStores = sellerWXService.queryFqStoreByStatus(1);
		String token = UUID.randomUUID().toString();
		SessionUtil.setSession(session, "add_redpack", token);
		view.addObject("fqStores", fqStores);
		view.addObject("token", token);
		view.addObject("randType", 1);
		return view;
	}
	
	@RequestMapping("/hongbao_add")
	public Object honeBaoAdd(HttpSession session,
			String token,
			String activityName,Long storeId,String storeName, String startDate,String endDate,
			Long[] startHour,Long[] startMinute,Long[] endHour,
			Long[] endMinute,String packMaxMoney,String packMinMoney,
			String[] subMoneyMin,String[] subMoneyMax,Integer[] fenduanGaiLv,Integer randType,
			String[] zhifujineMin,String[] zhifujineMax,Integer maxPackNum,String totalMoney,Integer quanxian) throws Exception {
		Object tokenSession = SessionUtil.getSession(session, "add_redpack");
		SessionUtil.removeSession(session, "add_redpack");
		if (tokenSession != null && tokenSession.equals(token)) {
			
			int result = 0;
			try {
				 result = redPackService.insertRedPackAndDetailAndTime(activityName, storeId, storeName, 
						startDate, endDate, startHour, startMinute, endHour, endMinute,
						packMaxMoney, packMinMoney, subMoneyMin, subMoneyMax, 
						fenduanGaiLv, randType, zhifujineMin, zhifujineMax, maxPackNum,totalMoney,quanxian);
				 
				if (result == 1) {
					return goUrl("/managermall/systemmall/ad/hongbao_list.do", "添加成功");
				}
				if (result == -2) {
					return goUrl("/managermall/systemmall/ad/hongbao_list.do", "添加异常,请检查参数,不要填错");
				}
				if (result == -1) {
					return goUrl("/managermall/systemmall/ad/hongbao_list.do", "营销账户余额不足,请充值");
				}
				if (result == 0) {
					return goUrl("/managermall/systemmall/ad/hongbao_list.do", "添加失败,请检查参数");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return goUrl("/managermall/systemmall/ad/hongbao_list.do", "添加失败");
			
		}else {
			return goUrl("/managermall/systemmall/ad/hongbao_list.do", "请不要重复提交");
		}
	}
	
	@RequestMapping("/hongbao_detail")
	public ModelAndView honeBaoDetail(@RequestParam(value = "redpackId", required = true) Long redpackId
			) throws Exception {
		ModelAndView view = new ModelAndView("/system/ad/hongbao_detail");
		
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
	
	@RequestMapping("/sendExcel")
	public ModelAndView sendExcel(
			HttpServletResponse response,@RequestParam(value = "redpackId", required = true) Long redpackId
			) throws Exception{
		
//		RedpackRecordVo recordVo = redPackService.queryRedpackRecordVoByRedpackId(redpackId);
		List<FqRedpackRecord> page = redPackService.queryRedpackRecordByRedpackId(redpackId);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HHmmss");
		String[] headersName = new String[] { "发放商家", "消费金额", "发放金额","发放时间"};
		String[] headersId = new String[] { "storeName", "consumeMoney", "redpackMoney","createTime"};
		
		ExportExcelExtUtil<FqRedpackRecord> excelUtil = new ExportExcelExtUtil<FqRedpackRecord>();
		excelUtil.exportExcel("红包报表" + format.format(new Date()), headersName, headersId, page,
				response);
		return null;
	}
	
	
	@RequestMapping("/hongbao_modify")
	public ModelAndView hongBaoModify(HttpSession session,@RequestParam(value = "redpackId", required = true) 
		Long redpackId) throws Exception {
		ModelAndView view = new ModelAndView("/system/ad/hongbao_modify");
		// 查询redpack
		FqRedpack fqRedpack = redPackService.queryRedpackById(redpackId);
		// 查询红包详情
		List<FqRedpackDetail> details = redPackService.queryFqRedpackDetailByRedpackId(redpackId);
		// 查询红包时间
		List<FqRedpackTime> redpackTimes = redPackService.queryRedpackTimeByRedpackId(redpackId);
		List<FqStore> fqStores = sellerWXService.queryFqStoreByStatus(1);
		view.addObject("fqStores", fqStores);
		view.addObject("fqRedpack", fqRedpack);
		view.addObject("details", details);
		view.addObject("redpackTimes", redpackTimes);
		view.addObject("redpackId", redpackId);
		String token = UUID.randomUUID().toString();
		SessionUtil.setSession(session, "update_redpack", token);
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
		Object tokenSession = SessionUtil.getSession(session, "update_redpack");
		SessionUtil.removeSession(session, "update_redpack");
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
				return goUrl("/managermall/systemmall/ad/hongbao_list.do", "保存成功");
			}
			
			return goUrl("/managermall/systemmall/ad/hongbao_list.do", "保存异常,请检查参数,不要填错");
			
		}else {
			return goUrl("/managermall/systemmall/ad/hongbao_list.do", "请不要重复提交");
		}
	}
	
	
	@RequestMapping("/hongbao_list")
	public ModelAndView hongBaoList(Integer pageNum) throws Exception {
		ModelAndView view = new ModelAndView("/system/ad/hongbao_list");
		if (pageNum == null ) {
			pageNum = 1;
		}
		Page<FqRedpack> datas = redPackService.queryRedpackList(pageNum, 10);
		view.addObject("page", datas);
		view.addObject("pageNum", pageNum);
		return view;
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
}
