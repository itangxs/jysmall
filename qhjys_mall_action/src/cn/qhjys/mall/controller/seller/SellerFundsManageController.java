package cn.qhjys.mall.controller.seller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.common.PAYCODE;
import cn.qhjys.mall.common.Query;
import cn.qhjys.mall.entity.CashAccount;
import cn.qhjys.mall.entity.CashLog;
import cn.qhjys.mall.entity.CashSaveWithdraw;
import cn.qhjys.mall.entity.CompanyInfo;
import cn.qhjys.mall.entity.FqSellerStatement;
import cn.qhjys.mall.entity.IntegralLog;
import cn.qhjys.mall.entity.MsWithdraw;
import cn.qhjys.mall.entity.RebateCash;
import cn.qhjys.mall.entity.RebateOrder;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.SellerLoginLog;
import cn.qhjys.mall.service.CashAccountService;
import cn.qhjys.mall.service.CashLogService;
import cn.qhjys.mall.service.CashSaveWithdrawService;
import cn.qhjys.mall.service.PaymentService;
import cn.qhjys.mall.service.RebateCashService;
import cn.qhjys.mall.service.RebateOrderService;
import cn.qhjys.mall.service.SellerLoginLogService;
import cn.qhjys.mall.service.fq.FqSellerStatementService;
import cn.qhjys.mall.service.fq.MsWithdrawService;
import cn.qhjys.mall.service.seller.SellerVoService;
import cn.qhjys.mall.service.system.IntegralLogService;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.DateUtil;
import cn.qhjys.mall.util.DateUtils;
import cn.qhjys.mall.util.ExportExcelExtUtil;
import cn.qhjys.mall.util.ExportExcelUtil;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.util.LLPayUtil;
import cn.qhjys.mall.util.SessionUtil;
import cn.qhjys.mall.vo.OrderCountVo;
import cn.qhjys.mall.vo.PaymentInfo;
import cn.qhjys.mall.vo.RebateOrderVo;
import cn.qhjys.mall.vo.seller.SellerBankVo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

/**
 * 商家(卖家) 资金管理
 * 
 * @author JiangXiaoPing
 *
 */
@Controller
@RequestMapping("/managermall/seller/funds")
public class SellerFundsManageController extends Base {
	@Autowired
	private SellerVoService sellerVoService;
	@Autowired
	private CashAccountService cashAccountService;
	@Autowired
	private IntegralLogService integralLogService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private RebateOrderService rebateOrderService;
	@Autowired
	private CashSaveWithdrawService cashSaveWithdrawService;
	@Autowired
	private FqSellerStatementService fqSellerStatementService;
	@Autowired
	private RebateCashService rebateCashService;
	@Autowired
	private SellerLoginLogService sellerLoginLogService;
	@Autowired
	private CashLogService cashLogService;
	@Autowired
	private MsWithdrawService msWithdrawService;		//民生提现服务
	
	@RequestMapping("/advancelist")
	public ModelAndView rebateOrderList(HttpSession session,String orderNo ,String storeName,String openId,String startTime ,String endTime,
			Integer pageNum,Integer pageSize,Integer payType,Integer sort,Integer datenum) throws UnsupportedEncodingException{
		ModelAndView view = new ModelAndView("/seller/order/advancelist");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			view.setViewName("redirect:/seller/login.do");
		} else {
			Long sellerId = seller.getId();
			if (pageNum == null) {
				pageNum =1;
			}
			if (pageSize == null) {
				pageSize =10;
			}
			if (datenum!= null) {
				String pattern = "yyyy-MM-dd";
				Calendar cal = Calendar.getInstance();
				endTime = DateUtil.convertDateToString(pattern, cal.getTime());
				if (datenum==1) {
					startTime = endTime;
				}
				if (datenum==7) {
					cal.add(Calendar.DATE, -7); // 昨天
					startTime = DateUtil.convertDateToString(pattern, cal.getTime());
				}
				if (datenum==30) {
					cal.add(Calendar.MONTH, -1); // 昨天
					startTime = DateUtil.convertDateToString(pattern, cal.getTime());
				}
				if (datenum==90) {
					cal.add(Calendar.MONTH, -3); // 昨天
					startTime = DateUtil.convertDateToString(pattern, cal.getTime());
				}
				
			}
			List<Integer> type=new ArrayList<Integer>();
			if (null != payType) {
				if (payType==1) {
					type.add(1);
					type.add(3);
					type.add(5);
				}
				if (payType==2) {
					type.add(2);
					type.add(6);
				}
				if (payType==3) {
					type.add(7);
				}
				if (payType==4) {
					type.add(4);
				}
			}
			Page<RebateOrderVo> page = rebateOrderService.listRebateOrderVo(sellerId,orderNo, storeName, openId, startTime, endTime, pageNum, pageSize,2, type, sort);
			OrderCountVo orderCountVo = rebateOrderService.queryOrderCountVo(sellerId, orderNo, storeName, openId, startTime, endTime, pageNum, pageSize, 2, type, sort);
			view.addObject("page", page);
			view.addObject("orderNo",orderNo );
			view.addObject("storeName", storeName);
			view.addObject("openId", openId);
			view.addObject("startTime", startTime);
			view.addObject("endTime", endTime);
			view.addObject("payType", payType);
			view.addObject("sort", sort);
			view.addObject("datenum", "");
			view.addObject("orderCountVo", orderCountVo);
		
			int a = sellerLoginLogService.countSellerLoginLogToday(sellerId, 1);
			if (a > 0) {
				view.addObject("istanc", 0);
			}else{
				view.addObject("istanc", 1);
				SellerLoginLog log = new SellerLoginLog();
				log.setCreateTime(new Date());
				log.setIsLogin(1);
				log.setLoginType(1);
				log.setSellerId(sellerId);
				sellerLoginLogService.insertSellerLoginLog(log);
			}
			
		}
		return view;
	}
	@RequestMapping("/advancelistExcel")
	public ModelAndView rebateOrderListExcel(HttpServletResponse response,HttpSession session,String orderNo ,String storeName,String openId,String startTime ,String endTime,Integer payType,Integer sort,Integer datenum) throws UnsupportedEncodingException{
		
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			ModelAndView view = new ModelAndView("redirect:/seller/login.do");
			return view;
		} else {
			if (datenum!= null) {
				String pattern = "yyyy-MM-dd";
				Calendar cal = Calendar.getInstance();
				endTime = DateUtil.convertDateToString(pattern, cal.getTime());
				if (datenum==1) {
					startTime = endTime;
				}
				if (datenum==7) {
					cal.add(Calendar.DATE, -6); // 昨天
					startTime = DateUtil.convertDateToString(pattern, cal.getTime());
				}
				if (datenum==30) {
					cal.add(Calendar.MONTH, -1); // 昨天
					startTime = DateUtil.convertDateToString(pattern, cal.getTime());
				}
				if (datenum==90) {
					cal.add(Calendar.MONTH, -3); // 昨天
					startTime = DateUtil.convertDateToString(pattern, cal.getTime());
				}
				
			}
			List<Integer> type=new ArrayList<Integer>();
			if (null != payType) {
				if (payType==1) {
					type.add(1);
					type.add(3);
					type.add(5);
				}
				if (payType==2) {
					type.add(2);
					type.add(6);
				}
				if (payType==3) {
					type.add(7);
				}
				if (payType==4) {
					type.add(4);
				}
			}
			Long sellerId = seller.getId();
			Page<RebateOrderVo> page = rebateOrderService.listRebateOrderVo(sellerId,orderNo, storeName, openId, startTime, endTime, 1, 10000000,2,type,sort);
			SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HHmmss");
			
			String[] headersName = new String[] { "订单编号","消费人","支付方式" ,"支付金额","手续费率","手续费","到账金额","支付时间","消费来源"};
			String[] headersId = new String[] { "orderNo","openId", "realPay","orderRate","rateFee","totamt","payTime","bankType"
					};

			new ExportExcelUtil().exportExcel("预付费消费订单报表" + format.format(new Date()), headersName, headersId, page.getResult(),
					response);
			
		}
		return null;
	}

	/**
	 * 
	 * @Title: accountOverview 账号总览
	 * @param session
	 * 
	 * @param page
	 *            页数
	 * @param status
	 *            状态 (0:全部 1:收入 2:退款)
	 * @param dates
	 *            操作时间 - 开始 注:可空
	 * @param datee
	 *            操作时间 - 结束 注:可空
	 * @return
	 * @throws Exception
	 * @return ModelAndView
	 */
//	@RequestMapping("/account/overview")
//	public ModelAndView accountOverview(HttpSession session,
//			@RequestParam(value = "page", required = false) Integer pageNum,
//			@RequestParam(value = "pageSize", required = false) Integer pageSize,
//			@RequestParam(value = "status", required = false) Integer status,
//			@RequestParam(value = "startTime", required = false) String startTime,
//			@RequestParam(value = "endTime", required = false) String endTime) throws Exception {
//		ModelAndView view = new ModelAndView("/seller/funds/account_overview");
//		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
//		if (seller == null) {
//			view.setViewName("redirect:/seller/login.do");
//		} else {
//			Long sellerId = seller.getId();
//			if (pageNum == null || pageNum < 1)
//				pageNum = 1;
//			if (pageSize == null || pageSize < 0)
//				pageSize = 10;
//			try {
//				Page<SellerAccoutOverviewVo> list = sellerVoService.querySellerAccoutOverviewVoBySid(sellerId, status,
//						startTime, endTime, pageNum, pageSize);
//				view.addObject("page", list);
//			} catch (Exception e) {
//				this.logger.error("查询账号总览异常：", e);
//			}
//			view.addObject("startTime", startTime);
//			view.addObject("endTime", endTime);
//		}
//		return view;
//	}
	
	@RequestMapping("/account/overview")
	public ModelAndView accountOverview(HttpSession session,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime) throws Exception {
		ModelAndView view = new ModelAndView();
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			view.setViewName("redirect:/seller/login.do");
		} else {
			Integer withdrawStatus = seller.getWithdrawStatus();
			if (withdrawStatus != null) {
				if (withdrawStatus == 0) {
					view.setViewName("/seller/funds/account_overviewtwo");
					Long sellerId = seller.getId();
					RebateCash cash = rebateCashService.queryRebateCashLast(sellerId);
					if (cash != null) {
						if (pageNum == null || pageNum < 1) {
							pageNum = 1;
						}
						if (pageSize == null || pageSize < 0) {
							pageSize = 15;
						}
						Page<RebateOrder> page = rebateOrderService.listRebateOrderByRebateId(cash.getRebateId(),2, pageNum, pageSize,startTime,endTime);
						view.addObject("page", page);
					}
					view.addObject("cash", cash);
					view.addObject("startTime", startTime);
					view.addObject("endTime", endTime);
				}else if (withdrawStatus == 1) {
					view.setViewName("/seller/funds/account_overview");
					if (pageNum == null || pageNum < 1){
						pageNum = 1;
					}
					if (pageSize == null || pageSize < 0){
						pageSize = 10;
					}
					Long sellerId = seller.getId();
					// 查询商家账户余额
					CashAccount cash = cashAccountService.queryCashAccount(sellerId, null);
					view.addObject("cash", cash);
					// 查询提现记录
					List<CashSaveWithdraw> withdraws = cashSaveWithdrawService.queryCashSaveWithdrawBySellerId(sellerId);
					view.addObject("withdraws", withdraws);
					// 查询结算记录
					Page<FqSellerStatement> statements = fqSellerStatementService.querySellerStatementBySellerId(sellerId, pageNum, pageSize);
					view.addObject("page", statements);
					String s = UUID.randomUUID().toString();
					view.addObject("withdrawsToken", s);
					SessionUtil.setSession(session, "withdrawsToken", s);
					//查看资金流水
					Page<CashLog> cashLogs = cashLogService.queryCashLog(cash.getAccountId(),pageNum,pageSize);
					view.addObject("cashLog", cashLogs);
				}
			}
		}
		return view;
	}
	
	/**
	 * 
	 * 描述: 商户后台提现分页查询
	 * @param msWithdrawParm
	 * @param query
	 * @return
	 */
	@RequestMapping("/withdraw_list")
	public ModelAndView getMsWithdrawList(Query query,HttpSession session){
		ModelAndView view = new ModelAndView("/seller/funds/ms_account_overview");
		try {
			SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
	        //查询列表内容
			Page<MsWithdraw> pageList = msWithdrawService.queryMsWithdrawBySellerId(1922L, query);
			view.addObject("page", pageList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("--商户提现查询出现异常--",e);
		}
		return view;
	}
	
	/**
	 * 
	 * 描述: 商户后台提现导出
	 * @param msWithdrawParm
	 * @param query
	 * @return
	 */
	@RequestMapping("/withdraw_export")
	public String getMsWithdrawExport(HttpServletResponse response,HttpSession session){
		try {
			SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
			List<MsWithdraw> list = msWithdrawService.queryMsWithdrawBySellerExport(1922L);
			ExportExcelUtil<MsWithdraw> excelUtil = new ExportExcelUtil<MsWithdraw>();
			String[] headersName = new String[] { "日期", "金额", "状态"};
			String[] headersId = new String[] { "createDate", "withdrawMoeny", "state"};
			SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HHmmss");
			excelUtil.exportExcel("商户提现报表 " + format.format(new Date()), headersName, headersId, list, response);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("--商户提现导出出现异常--",e);
		}
		return null;
	}
	
	@RequestMapping("/cashLog")
	public ModelAndView queryCashLog(HttpSession session,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("/seller/funds/cash_log");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		Long sellerId = seller.getId();
		// 查询商家账户余额
		CashAccount cash = cashAccountService.queryCashAccount(sellerId, null);
		view.addObject("cash", cash);
		//查看资金流水
		Page<CashLog> cashLogs = cashLogService.queryCashLog(cash.getAccountId(),pageNum,pageSize);
		view.addObject("page", cashLogs);
		return view;
	}
	
	@RequestMapping("/account/sellerWithdraw")
	@ResponseBody
	public Object sellerWithdraw(HttpSession session,
			@RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "withdrawsMoney", required = true) String withdrawsMoney) throws Exception{
		
		//每天凌晨00:00:00 到 00:01:00 之间不允许提现 ， 定时器正在结算商家余额  并发极小概率冲突导致 金额错误
		int hour = DateUtils.getHour(new Date());
		if(hour==0){//当前是0点
			int minute = DateUtils.getMinute(new Date());
			if(minute==0){
				return "timelimit";
			}
		}
		
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			// 返回提示登录
			return "login";
		}
		String t1 = (String) SessionUtil.getSession(session, "withdrawsToken");
		SessionUtil.removeSession(session, "withdrawsToken");
		if (t1!=null && t1.equals(token)) {
			int result = -1;
			try {
				result = fqSellerStatementService.insertWithdraw(seller.getId(), withdrawsMoney);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (result == 0) {
				// 余额不足 
				return "less";
			}else if (result == 1) {
				// 银行卡异常
				return "bank";
			}else if (result == 2) {
				return "success";
			}else {
				return "fail";
			}
		}else {
			return "tokenError";
		}
	}
	
	@RequestMapping("/account/overviewExcel")
	public ModelAndView accountOverviewExcel(HttpServletResponse response,HttpSession session
			) throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			ModelAndView view = new ModelAndView();
			view.setViewName("redirect:/seller/login.do");
			return view;
		} else {
			Long sellerId = seller.getId();
			List<FqSellerStatement> statements = fqSellerStatementService.querySellerStatementBySellerId(sellerId);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HHmmss");
			String[] headersName = new String[] { "日期", "金额", "状态"};
			String[] headersId = new String[] { "statementDate", "totalMoney", "state"};
			ExportExcelExtUtil<FqSellerStatement> excelUtil = new ExportExcelExtUtil<FqSellerStatement>();
			excelUtil.exportExcel("结算报表" + format.format(new Date()), headersName, headersId, statements,
					response);
		}
		return null;
	}
	
	@RequestMapping("/account/overviewExcelTwo")
	public ModelAndView accountOverviewExcelTwo(HttpServletResponse response,HttpSession session,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime) throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			ModelAndView view = new ModelAndView();
			view.setViewName("redirect:/seller/login.do");
			return view;
		} else {
			Long sellerId = seller.getId();
			RebateCash cash = rebateCashService.queryRebateCashLast(sellerId);
			if (cash != null) {
				Page<RebateOrder> page = rebateOrderService.listRebateOrderByRebateId(cash.getRebateId(),2, 1, 100000000,startTime,endTime);
				SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HHmmss");
				
				String[] headersName = new String[] { "日期", "抵用券", "微信支付金额","实付金额","实付消费权","备注"};
				String[] headersId = new String[] { "createTime", "couponId", "needPay","realPay","totamt","status"};

				new ExportExcelUtil().exportExcel("销售入账详情" + format.format(new Date()), headersName, headersId, page.getResult(),
						response);
			}
		}
		return null;
	}

	/**
	 * 
	 * @Title: bank 银行卡 列表
	 * @param session
	 * @param page
	 *            页数
	 * @return
	 * @throws Exception
	 * @return ModelAndView
	 */
	@RequestMapping("/bank")
	public ModelAndView bank(HttpSession session, @RequestParam(value = "page", required = false) Integer page)
			throws Exception {
		ModelAndView view = new ModelAndView("/seller/funds/bank_list");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			view.setViewName("redirect:/seller/login.do");
		} else {
			if (null == page || page < 1)
				page = 1;
			view.addObject("page", page);
			Integer pageSize = 10;
			/* SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); */
			Integer bankCount = sellerVoService.querySellerBankCountBySid(seller.getId());
			view.addObject("bankCount", bankCount);
			Page<SellerBankVo> bankVos = sellerVoService.querysellerBankPage(seller.getId(), page, pageSize);
			view.addObject("vos", bankVos);
		}
		return view;
	}

	/**
	 * @Title: deleteBank 删除商家银行卡
	 * @param session
	 * @param response
	 * @param id
	 *            银行卡ID
	 * @return void
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/deleteBank")
	public void deleteBank(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "id", required = true) Long id) throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			response.sendRedirect("/seller/login.do");
		} else {
			Boolean bl = sellerVoService.deleteSellerBankById(seller.getId(), id);
			if (bl) {
				HtmlUtil.writerJson(response, "success");
			} else {
				HtmlUtil.writerJson(response, "error");
			}
		}
	}

	/**
	 * 
	 * @Title: setBank 设置商家银行卡默认
	 * @param session
	 * @param response
	 * @param id
	 *            银行卡ID
	 * @return void
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/setBank")
	public void setBank(HttpSession session, HttpServletResponse response,
			@RequestParam(value = "id", required = true) Long id) throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			response.sendRedirect("/seller/login.do");
		} else {
			Boolean bl = sellerVoService.updateSellerBankDefault(seller.getId(), id);
			if (bl) {
				HtmlUtil.writerJson(response, "success");
			} else {
				HtmlUtil.writerJson(response, "error");
			}
		}
	}

	/**
	 * 
	 * @Title: toAddBank 添加银行卡页面
	 * @return ModelAndView
	 * @throws Exception
	 * 
	 */
	@RequestMapping("/toAddBank")
	public ModelAndView toAddBank(HttpSession session) throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		ModelAndView view = new ModelAndView("/seller/funds/add_bank");
		if (seller == null) {
			view.setViewName("redirect:/seller/login.do");
		} else {
			Page<SellerBankVo> bankVos = sellerVoService.querysellerBankPage(seller.getId(), 1, 10);
			if(null!= bankVos &&bankVos.getResult().size()>0){
				SellerBankVo bankVo = bankVos.getResult().get(0);
				view.addObject("bankNamer", bankVo.getRealName());
			}
			String s = UUID.randomUUID().toString();
			view.addObject("toAddBank", s);
			SessionUtil.removeSession(session, "toAddBank");
			SessionUtil.setSession(session, "toAddBank", s);
		}
		return view;
	}

	/**
	 * 
	 * @Title: addBank 添加银行卡
	 * @param response
	 * @param session
	 * @param theBank
	 *            所属银行
	 * @param subbranchName
	 *            支行名称
	 * @param realName
	 *            真实姓名
	 * @param bankAccout
	 *            银行账户
	 * @param mobile
	 *            手机号码
	 * @throws Exception
	 * @return void
	 */
	@ResponseBody
	@RequestMapping("/addBank")
	public void addBank(HttpServletResponse response, HttpSession session,
			@RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "theBank", required = true) String theBank,
			@RequestParam(value = "subbranchName", required = true) String subbranchName,
			@RequestParam(value = "realName", required = false) String realName,
			@RequestParam(value = "bankAccout", required = true) String bankAccout
	/* @RequestParam(value = "mobile",required= true) String mobile */
	) throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		
		Page<SellerBankVo> bankVos = sellerVoService.querysellerBankPage(seller.getId(), 1, 10);
	
		if (seller == null ) {
			response.sendRedirect("/seller/login.do");
		} else {
			String t1 = SessionUtil.getSession(session, "toAddBank").toString();
			SessionUtil.removeSession(session, "toAddBank");
			if (!t1.equals(token)) {
				HtmlUtil.writerJson(response, "tokenError");
			} else {
				if(null!= bankVos &&bankVos.getResult().size()>0){
					SellerBankVo bankVo = bankVos.getResult().get(0);
					if(StringUtils.isEmpty(realName)&&StringUtils.isEmpty(bankVo.getRealName())){
						HtmlUtil.writerJson(response, "realNameError");
					}else if(!StringUtils.isEmpty(bankVo.getRealName())){
						realName = bankVo.getRealName();
					}
				}
				if(!StringUtils.isEmpty(realName)){
					logger.info("------------theBank----------------------" + theBank);
					logger.info("--------------subbranchName--------------------" + subbranchName);
					logger.info("---------------realName-------------------" + realName);
					logger.info("-----------------bankAccout-----------------" + bankAccout);
					logger.info("--------------------seller--------------" + seller.getId());
					String status = sellerVoService.saveSellerBank(seller.getId(), theBank, subbranchName, realName,
							bankAccout);
					HtmlUtil.writerJson(response, status);
				}else{
					HtmlUtil.writerJson(response, "realNameError");
				}
			}
		}
	}

	/***
	 * 账户充值页面
	 * 
	 * @param pageNum
	 *            分页参数
	 * @param pageSize
	 *            分页参数
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toRecharge")
	public ModelAndView toRecharge(HttpSession session,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("/seller/funds/recharge");
		String s = UUID.randomUUID().toString();
		view.addObject("rechargeToken", s);
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			view.setViewName("redirect:/seller/login.do");
		} else {
			Long sellerId = seller.getId();
			CashAccount cashAccount = sellerVoService.queryAccountBySellerId(1, sellerId);
			view.addObject("cashAccount", cashAccount);
			if (pageNum == null || pageNum < 1)
				pageNum = 1;
			if (pageSize == null || pageSize < 0)
				pageSize = 10;
			List<CashSaveWithdraw> cashList = sellerVoService.queryCashSaveWithdraw(pageNum, pageSize, 0, sellerId);
			Page<SellerBankVo> bankVos = sellerVoService.querysellerBankPage(seller.getId(), 1, 20);
			view.addObject("bank", bankVos);
			view.addObject("page", cashList);
			SessionUtil.setSession(session, "rechargeToken", s);
		}
		return view;
	}

	@RequestMapping("/toExchange")
	public ModelAndView toExchange(HttpSession session,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("/seller/funds/exchange");
		String s = UUID.randomUUID().toString();
		view.addObject("exchangeToken", s);
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			view.setViewName("redirect:/seller/login.do");
		} else {
			Long sellerId = seller.getId();
			CashAccount cashAccount = sellerVoService.queryAccountBySellerId(1, sellerId);
			view.addObject("cashAccount", cashAccount);
			if (pageNum == null || pageNum < 1)
				pageNum = 1;
			if (pageSize == null || pageSize < 0)
				pageSize = 10;
			Page<IntegralLog> logList = integralLogService.queryPageBySellerExchange(sellerId, PAYCODE.B008, null,
					null, pageNum, pageSize);
			view.addObject("page", logList);
			SessionUtil.setSession(session, "exchangeToken", s);
		}
		return view;
	}

	@ResponseBody
	@RequestMapping("/account/exchange")
	public void exchange(HttpServletResponse response, HttpSession session,
			@RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "exchangeIntegral", required = true) BigDecimal exchangeIntegral) throws Exception {
		JSONObject json = new JSONObject();
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			response.sendRedirect("/seller/login.do");
		} else {
			Long sellerId = seller.getId();
			String t1 = (String) SessionUtil.getSession(session, "exchangeToken");
			SessionUtil.removeSession(session, "exchangeToken");
			if (!t1.equals(token)) {
				HtmlUtil.writerJson(response, "tokenError");
			} else {
				CashAccount cashAccount = cashAccountService.queryCashAccount(seller.getId(), null);
				BigDecimal uintegral = cashAccount.getIntegral();// 用户账户积分
				BigDecimal isIntegral = uintegral.subtract(exchangeIntegral);
				if (isIntegral.compareTo(BigDecimal.ZERO) == -1) {
					json.put("message", "积分不足");
				} else {
					boolean isTrue = paymentService.updateExchangeBySeller(sellerId, exchangeIntegral);
					if (isTrue) {
						CashAccount cash = cashAccountService.queryCashAccount(seller.getId(), null);
						session.setAttribute(ConstantsConfigurer.getProperty("seller_account_key"), cash);
						json.put("message", "兑换成功!");
					} else {
						json.put("message", "兑换失败!");
					}
				}
			}
		}
		HtmlUtil.writerJson(response, json);
	}

	/***
	 * 商家充值
	 * 
	 * @param response
	 * @param session
	 * @param token
	 * @param payType
	 *            支付方式
	 * @param rechargeMoney
	 *            充值金额
	 * @throws Exception
	 */
	@RequestMapping("/account/recharge")
	public ModelAndView recharge(HttpServletRequest req, HttpServletResponse response, HttpSession session,
			@RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "cardNo", required = false) String cardNo,
			@RequestParam(value = "payType", required = false) String payType,
			@RequestParam(value = "rechargeMoney", required = true) BigDecimal rechargeMoney) throws Exception {
		ModelAndView view = new ModelAndView("/seller/funds/gotoPrepositPay");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			view.setViewName("redirect:/seller/login.do");
		} else {
			Long sellerId = seller.getId();
			String t1 = (String) SessionUtil.getSession(session, "rechargeToken");
			SessionUtil.removeSession(session, "rechargeToken");
			if (!t1.equals(token)) {
				HtmlUtil.writerJson(response, "tokenError");
			} else {
				CashAccount cashAccount = cashAccountService.queryCashAccount(seller.getId(), null);
				CashSaveWithdraw cash = new CashSaveWithdraw();
				cash.setMoney(rechargeMoney.divide(new BigDecimal(ConstantsConfigurer.getProperty("recharge_scale"))));
				cash.setOperType(0);
				cash.setCashId(cashAccount.getId());
				Date date = new Date();
				cash.setCreateDate(date);
				cash.setStatus(2);
				String orderNum = String.valueOf(UUID.randomUUID());
				orderNum = orderNum.replaceAll("-", "");
				cash.setOrderNum(orderNum);
				boolean isSuccess = sellerVoService.insertSaveWithdraw(cash);
				if (isSuccess == true) {
					// 构造支付请求对象
					PaymentInfo paymentInfo = new PaymentInfo();
					paymentInfo.setVersion("1.0");
					paymentInfo.setOid_partner(ConstantsConfigurer.getProperty("OID_PARTNER"));// 商户编号
					paymentInfo.setUser_id("" + cashAccount.getId());
					paymentInfo.setSign_type("MD5");
					// 业务类型，连连支付根据商户业务为商户开设的业务类型；
					// （101001：虚拟商品销售、109001：实物商品销售、108001：外部账户充值）
					paymentInfo.setBusi_partner("101001");
					paymentInfo.setNo_order(orderNum);// 商户唯一订单号(只在充值时有)
					SimpleDateFormat sim = new SimpleDateFormat("yyyyMMddHHmmss");
					paymentInfo.setDt_order(sim.format(date));// 商户订单时间
					// 商品名称
					paymentInfo.setName_goods("充值");// 这里是充值，所以随便写个充值
					// paymentInfo.setInfo_order(order.getInfo_orrder());//订单备注信息(非必填)
					// 交易金额
					paymentInfo.setMoney_order("" + rechargeMoney.divide(new BigDecimal(ConstantsConfigurer.getProperty("recharge_scale"))));
					// 服务器异步通知地址(异步请求.do,做成功或失败后的操作)
					paymentInfo.setNotify_url(ConstantsConfigurer.getProperty("SELLER_NOTIFY_URL") + "?cardNo="
							+ cardNo);
					// 支付结束回显url(这里先跳转到充值页面)
					paymentInfo.setUrl_return(ConstantsConfigurer.getProperty("SELLER_RETURN_URL"));
					// 用户端申请IP(暂时不填)
					// paymentInfo.setUserreq_ip(LLPayUtil.getIpAddr(req).replaceAll("\\.","_"));
					paymentInfo.setUrl_order("");
					// 订单有效时间(单位分钟，可以为空，默认7天)
					// paymentInfo.setValid_order("10080");
					// 风险控制参数
					paymentInfo.setRisk_item(createRiskItem());
					// 时间戳
					paymentInfo.setTimestamp(LLPayUtil.getCurrentDateTimeStr());
					// 商戶从自己系统中获取用户身份信息（认证支付必须将用户身份信息传输给连连，且修改标记flag_modify设置成1：不可修改）
					// 证件类型（0身份证）
					paymentInfo.setId_type("0");
					// 通过商家编号获取银行卡号和身份证号
					CompanyInfo company = sellerVoService.queryCompanyBySellerId(sellerId);
					// 证件号码
					paymentInfo.setId_no(company.getCorpnId());
					// 银行账号姓名
					paymentInfo.setAcct_name(company.getCorpnName());
					paymentInfo.setFlag_modify("1");
					if (!LLPayUtil.isnull(req.getParameter("no_agree"))) {// 协议号和卡号同时存在时，优先将协议号送给连连，不要将协议号和卡号都送给连连
						paymentInfo.setNo_agree(req.getParameter("no_agree"));
					} else {
						// 银行卡号
						paymentInfo.setCard_no(cardNo);
					}
					// 返回修改信息地址(银行卡号前置，需要修改卡号时，用户点击返回的 url链接地址)
					paymentInfo.setBack_url(ConstantsConfigurer.getProperty("SELLER_BACK_URL"));
					// 加签名
					String sign = LLPayUtil.addSign(JSON.parseObject(JSON.toJSONString(paymentInfo)),
							ConstantsConfigurer.getProperty("trader_pri_key"),
							ConstantsConfigurer.getProperty("md5_key"));
					paymentInfo.setSign(sign);
					req.setAttribute("version", paymentInfo.getVersion());
					req.setAttribute("oid_partner", paymentInfo.getOid_partner());
					req.setAttribute("user_id", paymentInfo.getUser_id());
					req.setAttribute("sign_type", paymentInfo.getSign_type());
					req.setAttribute("busi_partner", paymentInfo.getBusi_partner());
					req.setAttribute("no_order", paymentInfo.getNo_order());
					req.setAttribute("dt_order", paymentInfo.getDt_order());
					req.setAttribute("name_goods", paymentInfo.getName_goods());
					req.setAttribute("info_order", paymentInfo.getInfo_order());
					req.setAttribute("money_order", paymentInfo.getMoney_order());
					req.setAttribute("notify_url", paymentInfo.getNotify_url());
					req.setAttribute("url_return", paymentInfo.getUrl_return());
					req.setAttribute("userreq_ip", paymentInfo.getUserreq_ip());
					req.setAttribute("url_order", paymentInfo.getUrl_order());
					req.setAttribute("valid_order", paymentInfo.getValid_order());
					req.setAttribute("timestamp", paymentInfo.getTimestamp());
					req.setAttribute("sign", paymentInfo.getSign());
					req.setAttribute("risk_item", paymentInfo.getRisk_item());
					req.setAttribute("no_agree", paymentInfo.getNo_agree());
					req.setAttribute("id_type", paymentInfo.getId_type());
					req.setAttribute("id_no", paymentInfo.getId_no());
					req.setAttribute("acct_name", paymentInfo.getAcct_name());
					req.setAttribute("flag_modify", paymentInfo.getFlag_modify());
					req.setAttribute("card_no", paymentInfo.getCard_no());
					req.setAttribute("back_url", paymentInfo.getBack_url());
					req.setAttribute("req_url", ConstantsConfigurer.getProperty("PAY_URL"));
				}
			}
		}
		return view;
	}

	/***
	 * 充值成功后跳转页面
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/urlReturn")
	public ModelAndView urlReturn(HttpSession session) throws Exception {
		ModelAndView view = new ModelAndView("/seller/funds/urlReturn");
		return view;
	}

	/***
	 * 账户提现页面
	 * 
	 * @param pageNum
	 *            分页参数
	 * @param pageSize
	 *            分页参数
	 * @param session
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping("/toWithdraws")
//	public ModelAndView toWithdraws(HttpSession session,
//			@RequestParam(value = "page", required = false) Integer pageNum,
//			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
//		ModelAndView view = new ModelAndView("/seller/funds/withdraws");
//		String s = UUID.randomUUID().toString();
//		view.addObject("withdrawsToken", s);
//		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
//		if (seller == null) {
//			view.setViewName("redirect:/seller/login.do");
//		} else {
//			Long sellerId = seller.getId();
//			CashAccount cashAccount = cashAccountService.queryCashAccount(sellerId, null);
//			view.addObject("cashAccount", cashAccount);
//			if (pageNum == null || pageNum < 1)
//				pageNum = 1;
//			if (pageSize == null || pageSize < 0)
//				pageSize = 10;
//			Page<CashSaveWithdraw> cashList = sellerVoService.queryCashSaveWithdraw(pageNum, pageSize, 1,
//					cashAccount.getId());
//			Page<SellerBankVo> bankVos = sellerVoService.querysellerBankPage(seller.getId(), 1, 20);
//			view.addObject("bank", bankVos);
//			view.addObject("page", cashList);
//			view.addObject("scale", ConstantsConfigurer.getProperty("recharge_scale"));
//			SessionUtil.setSession(session, "withdrawsToken", s);
//		}
//		return view;
//	}

	/***
	 * 商家提现
	 * 
	 * @param response
	 * @param session
	 * @param token
	 * @param bankId
	 *            银行卡号
	 * @param payCode
	 *            提现密码
	 * @param withdrawsMoney
	 *            提现金额
	 * @throws Exception
	 */
//	@ResponseBody
//	@RequestMapping("/account/withdraws")
//	public void withdraws(HttpServletResponse response, HttpSession session,
//			@RequestParam(value = "token", required = true) String token,
//			@RequestParam(value = "bankId", required = true) Long bankId,
//			@RequestParam(value = "withdrawsMoney", required = true) BigDecimal withdrawsMoney) throws Exception {
//		JSONObject json = new JSONObject();
//		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
//		BigDecimal moe = new BigDecimal(100000);
//		BigDecimal mo = moe.subtract(withdrawsMoney);
//		if (mo.compareTo(BigDecimal.ZERO) == -1) {
//			String message = MessageUtil.ANOMALYEXTRACTIOSINGLE;
//			String userName = seller.getUsername();
//			message = message.replace("username", userName);
//			json.put("message", message);
//			HtmlUtil.writerJson(response, json);
//			return;
//		}
//		if (seller == null) {
//			response.sendRedirect("/seller/login.do");
//		} else {
//			Long sellerId = seller.getId();
//			String t1 = (String) SessionUtil.getSession(session, "withdrawsToken");
//			SessionUtil.removeSession(session, "withdrawsToken");
//			if (!t1.equals(token)) {
//				HtmlUtil.writerJson(response, "tokenError");
//			} else {
//				CashAccount cashAccount = cashAccountService.queryCashAccount(seller.getId(), null);
//				if (true) {
//					BigDecimal balance = cashAccount.getBalance();
//					BigDecimal isBalance = balance.subtract(withdrawsMoney);
//					if (isBalance.compareTo(BigDecimal.ZERO) == -1) {
//						json.put("message", "余额不足");
//						String message = MessageUtil.ANOMALYEXTRACTIONBALANCE;
//						String userName = seller.getUsername();
//						message = message.replace("username", userName);
//						MessageUtil.sendSmsContent(seller.getPhone(), message);
//					} else {
//						CashAccount account = new CashAccount();
//						account.setAccountId(sellerId);
//						account.setId(cashAccount.getId());
//						account.setBalance(isBalance);
//						account.setFreezeMoney(cashAccount.getFreezeMoney().add(withdrawsMoney));
//						account.setWithdrawMoney(cashAccount.getWithdrawMoney().add(withdrawsMoney));
//						CashSaveWithdraw cashSave = new CashSaveWithdraw();
//						cashSave.setMoney(withdrawsMoney);
//						cashSave.setOperType(1);
//						cashSave.setBankId(bankId);
//						cashSave.setCashId(cashAccount.getId());
//						cashSave.setCreateDate(new Date());
//						boolean isSuccess = sellerVoService.insertWithdraws(account, cashSave);
//						if (isSuccess == true) {
//							CashAccount cash = cashAccountService.queryCashAccount(seller.getId(), null);
//							session.setAttribute(ConstantsConfigurer.getProperty("seller_account_key"), cash);
//							json.put("message", "提现成功");
//							String message = MessageUtil.ACCOUNTEXTRACTION;
//							String userName = seller.getUsername();
//							String money = String.valueOf(withdrawsMoney);
//							String balance1 = String.valueOf(cash.getBalance());
//							message = message.replace("username", userName);
//							message = message.replace("money", money);
//							message = message.replace("balance", balance1);
//							MessageUtil.sendSmsContent(seller.getPhone(), message);
//						} else {
//							json.put("message", "提现失败");
//						}
//					}
//				} 
//			}
//		}
//		HtmlUtil.writerJson(response, json);
//	}

	/**
	 * 根据连连支付风控部门要求的参数进行构造风控参数
	 * 
	 * @return
	 */
	private String createRiskItem() {
		JSONObject riskItemObj = new JSONObject();
		riskItemObj.put("user_info_full_name", "你好");
		riskItemObj.put("frms_ware_category", "1999");
		return riskItemObj.toString();
	}
}
