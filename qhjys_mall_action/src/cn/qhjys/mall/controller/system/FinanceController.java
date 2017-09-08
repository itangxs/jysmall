package cn.qhjys.mall.controller.system;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.AdminUser;
import cn.qhjys.mall.entity.BankInfo;
import cn.qhjys.mall.entity.CashAccount;
import cn.qhjys.mall.entity.CashLog;
import cn.qhjys.mall.entity.CashSaveWithdraw;
import cn.qhjys.mall.entity.CityInfo;
import cn.qhjys.mall.entity.FqBranch;
import cn.qhjys.mall.entity.FqClerk;
import cn.qhjys.mall.entity.FqCommissionRole;
import cn.qhjys.mall.entity.FqStoreLoan;
import cn.qhjys.mall.entity.FqStoreRate;
import cn.qhjys.mall.entity.FqStoreRepayment;
import cn.qhjys.mall.entity.FqTeam;
import cn.qhjys.mall.entity.ProvinceInfo;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.UserInfo;
import cn.qhjys.mall.service.AdminLogService;
import cn.qhjys.mall.service.BankInfoService;
import cn.qhjys.mall.service.CashAccountService;
import cn.qhjys.mall.service.CashLogService;
import cn.qhjys.mall.service.CashSaveWithdrawService;
import cn.qhjys.mall.service.SellerService;
import cn.qhjys.mall.service.StoreService;
import cn.qhjys.mall.service.UserInfoService;
import cn.qhjys.mall.service.app.SellerUserCountService;
import cn.qhjys.mall.service.seller.SellerVoService;
import cn.qhjys.mall.service.system.FinanceService;
import cn.qhjys.mall.service.system.FqStoreLoanService;
import cn.qhjys.mall.service.system.QDManageService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.DateUtil;
import cn.qhjys.mall.util.ExportExcelUtil;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.util.ServletUtils;
import cn.qhjys.mall.util.SessionUtil;
import cn.qhjys.mall.vo.SellerUserInfoVo;
import cn.qhjys.mall.vo.seller.SellerAccoutOverviewVo;
import cn.qhjys.mall.vo.system.CashLogVo;
import cn.qhjys.mall.vo.system.FqCommissionRoleVo;
import cn.qhjys.mall.vo.system.FqStoreRateVo;
import cn.qhjys.mall.vo.system.StoreLoanVo;
import cn.qhjys.mall.vo.system.WithdrawsVo;

import com.github.pagehelper.Page;

@Controller
@RequestMapping("/managermall/systemmall/finance")
public class FinanceController extends Base {
	@Autowired
	private FinanceService financeService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private CashSaveWithdrawService cashSaveWithdrawService;
	@Autowired
	private CashAccountService cashAccountService;
	@Autowired
	private AdminLogService adminLogService;
	@Autowired
	private SellerVoService sellerVoService;
	@Autowired
	private SellerService sellerService;
	@Autowired
	private CashLogService cashLogService;
	@Autowired
	private BankInfoService bankInfoService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private FqStoreLoanService fqStoreLoanService;
	@Autowired
	private SellerUserCountService sellerUserCountService;
	@Autowired
	private QDManageService qdManageService;
	
	@SuppressWarnings("rawtypes")
	ExportExcelUtil excelUtil = new ExportExcelUtil();

	private AdminUser getAdminUserSession(HttpSession session) {
		AdminUser adminUser = (AdminUser) session.getAttribute(ConstantsConfigurer.getProperty("system_key"));
		return adminUser;
	}

	/***
	 * 用户资金日志列表
	 * 
	 * @param session
	 * @param payType
	 *            操作类型
	 * @param userName
	 *            会员名称
	 * @param beginTime
	 *            操作时间(开始)
	 * @param endTime
	 *            操作时间(结束)
	 * @param pageNum
	 *            分页参数
	 * @param pageSize
	 *            分页参数
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userAccountRecordList")
	public ModelAndView queryUserAccountRecordPage(HttpSession session,
			@RequestParam(value = "payType", required = false) Integer payType,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "beginTime", required = false) String beginTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("/system/finance/user_account_record");
		if (pageNum == null || pageNum < 1)
			pageNum = 1;
		if (pageSize == null || pageSize < 0)
			pageSize = 10;
		Page<CashLogVo> list = financeService.queryUserAccountRecordPage(null, payType, userName, beginTime, endTime,
				pageNum, pageSize);
		view.addObject("payType", payType);
		view.addObject("userName", userName);
		view.addObject("beginTime", beginTime);
		view.addObject("endTime", endTime);
		view.addObject("page", list);
		view.addObject("pageNum", pageNum);
		view.addObject("pageSize", pageSize);
		return view;
	}

	// ·用户资金操作日志 导出excel
	@RequestMapping("/userAccountRecordListExcel")
	public ModelAndView userAccountRecordListExcel(HttpServletResponse response, HttpSession session,
			@RequestParam(value = "payType", required = false) Integer payType,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "beginTime", required = false) String beginTime,
			@RequestParam(value = "endTime", required = false) String endTime) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HHmmss");
		Page<CashLogVo> list = financeService.queryUserAccountRecordPage(null, payType, userName, beginTime, endTime,
				null, null);
		String[] headersName = new String[] { "会员名", "操作日期", "金额", "业务类型" };
		String[] headersId = new String[] { "userName", "createDate", "amount", "payType" };

		excelUtil.exportExcel("用户资金操作日志 " + format.format(new Date()), headersName, headersId, list.getResult(),
				response);
		return null;
	}

	/***
	 * 商家资金日志列表
	 * 
	 * @param session
	 * @param payType
	 *            操作类型
	 * @param userName
	 *            会员名称
	 * @param beginTime
	 *            操作时间(开始)
	 * @param endTime
	 *            操作时间(结束)
	 * @param pageNum
	 *            分页参数
	 * @param pageSize
	 *            分页参数
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sellerAccountRecordList")
	public ModelAndView querySellerAccountRecordPage(HttpSession session,
			@RequestParam(value = "payType", required = false) Integer payType,
			@RequestParam(value = "sellerName", required = false) String sellerName,
			@RequestParam(value = "beginTime", required = false) String beginTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("/system/finance/seller_account_record");
		if (pageNum == null || pageNum < 1)
			pageNum = 1;
		if (pageSize == null || pageSize < 0)
			pageSize = 10;
		Page<CashLogVo> list = financeService.querySellerAccountRecordPage(payType, sellerName, beginTime, endTime,
				pageNum, pageSize);
		view.addObject("payType", payType);
		view.addObject("sellerName", sellerName);
		view.addObject("beginTime", beginTime);
		view.addObject("endTime", endTime);
		view.addObject("page", list);
		view.addObject("pageNum", pageNum);
		view.addObject("pageSize", pageSize);
		return view;
	}

	// ·商家资金操作日志 导出
	@RequestMapping("/sellerAccountRecordListExcel")
	public ModelAndView sellerAccountRecordListExcel(HttpServletResponse response, HttpSession session,
			@RequestParam(value = "payType", required = false) Integer payType,
			@RequestParam(value = "sellerName", required = false) String sellerName,
			@RequestParam(value = "beginTime", required = false) String beginTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("/system/finance/seller_account_record");
		if (pageNum == null || pageNum < 1)
			pageNum = 1;
		if (pageSize == null || pageSize < 0)
			pageSize = 10;
		Page<CashLogVo> list = financeService.querySellerAccountRecordPage(payType, sellerName, beginTime, endTime,
				pageNum, pageSize);
		String[] headersName = new String[] { "商家名", "操作日期", "金额", "业务类型" };
		String[] headersId = new String[] { "sellerName", "createDate", "amount", "payType" };
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HHmmss");
		excelUtil.exportExcel("商家资金操作日志 " + format.format(new Date()), headersName, headersId, list.getResult(),
				response);
		return view;
	}

	/***
	 * 平台充值
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/recharge")
	public ModelAndView myRecharge(HttpSession session, Long userId, String userName) throws Exception {
		ModelAndView view = new ModelAndView("/system/finance/recharge");
		String s = UUID.randomUUID().toString();
		view.addObject("rechargeToken", s);
		SessionUtil.setSession(session, "rechargeToken", s);
		view.addObject("userId", userId);
		view.addObject("userName", userName);
		return view;
	}

	@RequestMapping(value = "/recharge", method = RequestMethod.POST)
	public void rechargeList(HttpServletRequest req, HttpServletResponse response, HttpSession session,
			@RequestParam(value = "token", required = true) String token,
			@RequestParam(value = "userId", required = true) Long userId,
			@RequestParam(value = "money", required = true) BigDecimal money) throws Exception {
		String t1 = (String) SessionUtil.getSession(session, "rechargeToken");
		SessionUtil.removeSession(session, "rechargeToken");
		if (!t1.equals(token)) {
			HtmlUtil.writerJson(response, "表单重复提交");
			return;
		}
		String orderNum = UUID.randomUUID().toString();
		boolean result = financeService.insertRecharge(0, 1, 4, 1L, userId, money, orderNum);
		if (result) {
			CashAccount cashAccount = cashAccountService.queryCashAccount(null, userId);
			session.setAttribute(ConstantsConfigurer.getProperty("user_account_key"), cashAccount);
			HtmlUtil.writerJson(response, "充值成功");
			return;
		} else {
			HtmlUtil.writerJson(response, "充值失败");
			return;
		}
	}

	/**
	 * 
	 * @Title: list 会员管理
	 * @param session
	 *            会员名称
	 * @param pageNum
	 *            页数
	 * @param pageSize
	 *            每页显示
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping("/userList")
	public ModelAndView list(HttpSession session, @RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws ParseException, Exception {
		ModelAndView view = new ModelAndView("/system/finance/user_list");
		if (null == pageNum || pageNum < 1) {
			pageNum = 1;
		}
		if (null == userName || userName.isEmpty())
			userName = null;
		pageSize = 10;
		Page<UserInfo> page = userInfoService.querySystemMallUserPage(null, userName, null, null, null, null, null,
				null, null, null, pageNum, pageSize);

		view.addObject("userName", userName);
		view.addObject("pageNum", pageNum);
		view.addObject("page", page);
		return view;
	}

	/***
	 * 用户资金日志列表
	 * 
	 * @param session
	 * @param payType
	 *            操作类型
	 * @param userName
	 *            会员名称
	 * @param beginTime
	 *            操作时间(开始)
	 * @param endTime
	 *            操作时间(结束)
	 * @param pageNum
	 *            分页参数
	 * @param pageSize
	 *            分页参数
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/accountRecordByUserId")
	public ModelAndView queryUserAccountRecord(HttpSession session,
			@RequestParam(value = "userId", required = false) Long userId,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("/system/finance/account_record");
		if (pageNum == null || pageNum < 1)
			pageNum = 1;
		if (pageSize == null || pageSize < 0)
			pageSize = 10;
		Page<CashLogVo> list = financeService.queryUserAccountRecordPage(userId, null, null, null, null, pageNum,
				pageSize);
		view.addObject("page", list);
		view.addObject("pageNum", pageNum);
		return view;
	}

	/***
	 * 系统后台用户提现列表
	 * 
	 * @param session
	 * @param status
	 *            状态 0不成功，1成功,2未处理
	 * @param userName
	 *            用户名
	 * @param beginTime
	 *            操作时间(开始)
	 * @param endTime
	 *            操作时间(结束)
	 * @param pageNum
	 *            分页参数
	 * @param pageSize
	 *            分页参数
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userWithdrawsRecordList")
	public ModelAndView queryUserWithdrawsRecord(HttpSession session,
			@RequestParam(value = "status", required = false) Integer status,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "beginTime", required = false) String beginTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("/system/finance/user_withdraws_list");
		if (pageNum == null || pageNum < 1)
			pageNum = 1;
		if (pageSize == null || pageSize < 0)
			pageSize = 10;
		Page<WithdrawsVo> list = financeService.queryUserWithdrawsRecord(userName, status, beginTime, endTime, pageNum,
				pageSize);
		view.addObject("status", status);
		view.addObject("userName", userName);
		view.addObject("beginTime", beginTime);
		view.addObject("endTime", endTime);
		view.addObject("page", list);
		view.addObject("pageNum", pageNum);
		view.addObject("pageSize", pageSize);
		return view;
	}

	// ·会员提现 导出
	@RequestMapping("/userWithdrawsRecordListExcel")
	public ModelAndView userWithdrawsRecordListExcel(HttpServletResponse response, HttpSession session,
			@RequestParam(value = "status", required = false) Integer status,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "beginTime", required = false) String beginTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "page", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		if (pageNum == null || pageNum < 1)
			pageNum = 1;
		if (pageSize == null || pageSize < 0)
			pageSize = 10;
		Page<WithdrawsVo> list = financeService.queryUserWithdrawsRecord(userName, status, beginTime, endTime, pageNum,
				pageSize);
		String[] headersName = new String[] { "会员名", "操作日期", "提现金额", "状态" };
		String[] headersId = new String[] { "userName", "createDate", "money", "status" };
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HHmmss");
		excelUtil
				.exportExcel("会员提现日志 " + format.format(new Date()), headersName, headersId, list.getResult(), response);
		return null;
	}

	/***
	 * 系统后台商家提现列表
	 * 
	 * @param session
	 * @param status
	 *            状态 0不成功，1成功,2未处理
	 * @param sellerName
	 *            商家名
	 * @param beginTime
	 *            操作时间(开始)
	 * @param endTime
	 *            操作时间(结束)
	 * @param pageNum
	 *            分页参数
	 * @param pageSize
	 *            分页参数
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sellerWithdrawsRecordList")
	public ModelAndView querySellerWithdrawsRecord(HttpSession session,
			@RequestParam(value = "status", required = false) Integer status,
			@RequestParam(value = "sellerName", required = false) String sellerName,
			@RequestParam(value = "beginTime", required = false) String beginTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("/system/finance/seller_withdraws_list");
		if (pageNum == null || pageNum < 1)
			pageNum = 1;
		if (pageSize == null || pageSize < 0)
			pageSize = 10;
		Page<WithdrawsVo> list = financeService.querySellerWithdrawsRecord(sellerName, status, beginTime, endTime,
				pageNum, pageSize);
		view.addObject("status", status);
		view.addObject("sellerName", sellerName);
		view.addObject("beginTime", beginTime);
		view.addObject("endTime", endTime);
		view.addObject("page", list);
		view.addObject("pageNum", pageNum);
		view.addObject("pageSize", pageSize);
		view.addObject("scale", ConstantsConfigurer.getProperty("recharge_scale"));
		return view;
	}
	
	@RequestMapping("/querySellerCash")
	public ModelAndView querySellerCash(Long sellerId,Integer pageNum,Integer pageSize) throws Exception{
		ModelAndView view = new ModelAndView("/system/finance/seller_cashLog");
		if (pageNum == null || pageNum < 1)
			pageNum = 1;
		if (pageSize == null || pageSize < 0)
			pageSize = 10;
		Page<SellerAccoutOverviewVo> list = sellerVoService.querySellerAccoutOverviewVoBySid(sellerId, null,
			 	null, null, pageNum, pageSize);
		view.addObject("page", list);
		SellerInfo seller = sellerService.getSellerById(sellerId);
		view.addObject("seller", seller);
		return view;
	}
	// ·商家提现 导出
	@RequestMapping("/sellerWithdrawsRecordListExcel")
	public ModelAndView sellerWithdrawsRecordListExcel(HttpServletResponse response, HttpSession session,
			@RequestParam(value = "status", required = false) Integer status,
			@RequestParam(value = "sellerName", required = false) String sellerName,
			@RequestParam(value = "beginTime", required = false) String beginTime,
			@RequestParam(value = "endTime", required = false) String endTime) throws Exception {
		
		Page<WithdrawsVo> list = financeService.querySellerWithdrawsRecord(sellerName, status, beginTime, endTime,
				null, null);
		String[] headersName = new String[] { "商家名", "店铺ID", "操作日期", "提现金额","持卡人","银行账号","银行","支行","状态" };
		String[] headersId = new String[] { "storeName", "storeId", "createDate", "money","bankUserName","carkNum",
				"bankName","branch","status" };
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HHmmss");
		excelUtil
				.exportExcel("商家提现表 " + format.format(new Date()), headersName, headersId, list.getResult(), response);
		return null;
	}

	/***
	 * 系统审核用户提现
	 * 
	 * @param session
	 * @param response
	 * @param id
	 *            提现编号
	 * @throws Exception
	 */
	@RequestMapping(value = "/auditUserCashSaveWithd", method = RequestMethod.POST)
	public void auditUserCashSaveWithd(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = false) String id) throws Exception {
		// 管理员用户编号暂时写死
		Long adminId = 1L;
		String[] ids = id.split(",");
		boolean isSuccess = false;
		for (int i = 0; i < ids.length; i++) {
			if (StringUtils.isNotEmpty(ids[i])) {
				CashSaveWithdraw cash = cashSaveWithdrawService.queryCashSaveWithdrawById(Long.valueOf(ids[i]));
				if (cash.getStatus() != 1) {
					cash.setStatus(1);
					cash.setAdminId(adminId);
					cash.setOperDate(new Date());
					Long bankId = cash.getBankId();
					BankInfo bankInfo = userInfoService.queryBankById(bankId);
					String bankNo = bankInfo.getCarkNum();
					isSuccess = cashSaveWithdrawService.updateCashSaveWithdraw(cash, bankNo, 0, 4);
				}
			}
		}
		if (isSuccess) {
			AdminUser admin = getAdminUserSession(session);
			String info = BaseUtil.getLogInfo("系统审核用户提现", "CashAccount,CashLog", null);
			info = info.replace("null", "");
			for (int i = 0; i < ids.length; i++)
				info += ids[i].toString() + ",";
			String IP = ServletUtils.getIpAddr(request);
			adminLogService.insertAdminLog(admin.getId(), info.substring(0, info.length() - 1), "", IP);
			HtmlUtil.writerJson(response, "审核成功");
		} else
			HtmlUtil.writerJson(response, "审核失败");
	}

	/***
	 * 系统审核商家提现
	 * 
	 * @param session
	 * @param response
	 * @param id
	 *            提现编号
	 * @throws Exception
	 */
	@RequestMapping(value = "/auditSellerCashSaveWithd", method = RequestMethod.POST)
	public void auditSellerCashSaveWithd(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = false) String id) throws Exception {
		// 管理员用户编号暂时写死
		Long adminId = 1L;
		String[] ids = id.split(",");
		boolean isSuccess = false;
		for (int i = 0; i < ids.length; i++) {
			if (StringUtils.isNotEmpty(ids[i])) {
				CashSaveWithdraw cash = cashSaveWithdrawService.queryCashSaveWithdrawById(Long.valueOf(ids[i]));
				if (cash.getStatus() == 2) {
					cash.setStatus(1);
					cash.setAdminId(adminId);
					cash.setOperDate(new Date());
					Long bankId = cash.getBankId();
					BankInfo bankInfo = userInfoService.queryBankById(bankId);
					String bankNo = bankInfo.getCarkNum();
					isSuccess = cashSaveWithdrawService.updateCashSaveWithdraw(cash, bankNo, 1, 4);
				}
			}
		}
		if (isSuccess) {
			AdminUser admin = getAdminUserSession(session);
			String info = BaseUtil.getLogInfo("系统审核商家提现", "CashAccount,CashLog", null);
			info = info.replace("null", "");
			for (int i = 0; i < ids.length; i++)
				info += ids[i].toString() + ",";
			String IP = ServletUtils.getIpAddr(request);
			adminLogService.insertAdminLog(admin.getId(), info.substring(0, info.length() - 1), "", IP);
			HtmlUtil.writerJson(response, "审核成功");
		} else
			HtmlUtil.writerJson(response, "审核失败");
	}
	
	/**
	 * 出账
	 * @param session
	 * @param request
	 * @param response
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/auditSellerCashWithdrawOff", method = RequestMethod.POST)
	public void auditSellerCashWithdrawOff(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = false) String id) throws Exception {
		// 管理员用户编号暂时写死
		Long adminId = 1L;
		String[] ids = id.split(",");
		boolean isSuccess = false;
		for (int i = 0; i < ids.length; i++) {
			if (StringUtils.isNotEmpty(ids[i])) {
				CashSaveWithdraw cash = cashSaveWithdrawService.queryCashSaveWithdrawById(Long.valueOf(ids[i]));
				if (cash.getStatus() == 1) {
					cash.setStatus(3);
					cash.setAdminId(adminId);
					cash.setOperDate(new Date());
					Long bankId = cash.getBankId();
					BankInfo bankInfo = userInfoService.queryBankById(bankId);
					String bankNo = bankInfo.getCarkNum();
					isSuccess = cashSaveWithdrawService.updateCashSaveWithdraw(cash, bankNo, 1, 4);
				}
			}
		}
		if (isSuccess) {
			AdminUser admin = getAdminUserSession(session);
			String info = BaseUtil.getLogInfo("系统审核商家提现", "CashAccount,CashLog", null);
			info = info.replace("null", "");
			for (int i = 0; i < ids.length; i++)
				info += ids[i].toString() + ",";
			String IP = ServletUtils.getIpAddr(request);
			adminLogService.insertAdminLog(admin.getId(), info.substring(0, info.length() - 1), "", IP);
			HtmlUtil.writerJson(response, "审核成功");
		} else
			HtmlUtil.writerJson(response, "审核失败");
	}
	
	@RequestMapping(value = "/unauditSellerCashSaveWithd", method = RequestMethod.POST)
	public void unauditSellerCashSaveWithd(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = false) String id) throws Exception {
		// 管理员用户编号暂时写死
		Long adminId = 1L;
		String[] ids = id.split(",");
		boolean isSuccess = false;
		for (int i = 0; i < ids.length; i++) {
			if (StringUtils.isNotEmpty(ids[i])) {
				CashSaveWithdraw cash = cashSaveWithdrawService.queryCashSaveWithdrawById(Long.valueOf(ids[i]));
				if (cash.getStatus() == 2) {
					cash.setStatus(0);
					cash.setAdminId(adminId);
					cash.setOperDate(new Date());
					Long bankId = cash.getBankId();
					BankInfo bankInfo = userInfoService.queryBankById(bankId);
					String bankNo = bankInfo.getCarkNum();
					isSuccess = cashSaveWithdrawService.updateCashSaveWithdraw(cash, bankNo, 1, 4);
				}
			}
		}
		if (isSuccess) {
			AdminUser admin = getAdminUserSession(session);
			String info = BaseUtil.getLogInfo("系统审核商家提现", "CashAccount,CashLog", null);
			info = info.replace("null", "");
			for (int i = 0; i < ids.length; i++)
				info += ids[i].toString() + ",";
			String IP = ServletUtils.getIpAddr(request);
			adminLogService.insertAdminLog(admin.getId(), info.substring(0, info.length() - 1), "", IP);
			HtmlUtil.writerJson(response, "审核成功");
		} else
			HtmlUtil.writerJson(response, "审核失败");
	}
	
	@RequestMapping("/sellerBalanceRecordList")
	public ModelAndView querySellerBalanceRecordList(HttpSession session,
			@RequestParam(value = "sellerId", required = false) Long sellerId,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("/system/finance/account_overview");
		// 查询商家账户余额
		CashAccount cash = cashAccountService.queryCashAccount(sellerId, null);
		SellerUserInfoVo sellerUserInfoVo = sellerUserCountService.queryDayliyVo(sellerId, new Date());
		view.addObject("cash", cash);
		view.addObject("weijiesuan", sellerUserInfoVo.getConMoney());
		//查看资金流水
		Page<CashLog> cashLogs = cashLogService.queryCashLog(cash.getAccountId(),pageNum,pageSize);
		view.addObject("page", cashLogs);
		return view;
	}
	
	/**
	 * 新增借款
	 * @param session
	 * @param storeId
	 * @param loanTotal
	 * @param payType
	 * @param beginTime
	 * @param bankName
	 * @param cardAccount
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/financeadd")
	public String financeadd(HttpSession session,
			@RequestParam(value = "store_id", required = false) Long storeId,
			@RequestParam(value = "loanTotal", required = false) BigDecimal loanTotal,
			@RequestParam(value = "payType", required = false) Integer payType,
			@RequestParam(value = "beginTime", required = false) String beginTime,
			@RequestParam(value = "bankName", required = false) String bankName,
			@RequestParam(value = "cardAccount", required = false) String cardAccount) throws Exception {
		if(storeId != null){
			StoreInfo storeInfo = storeService.getStoreDetaile(storeId);
			if(storeInfo != null){
				if(loanTotal != null && payType != null && beginTime != null && bankName!= null && cardAccount != null){
					SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
					Date date = sf.parse(beginTime);
					int result = fqStoreLoanService.insertFqStoreLoan(storeId, storeInfo.getName(), storeInfo.getSellerId(), loanTotal, payType, date, bankName, cardAccount, new Date());
					if(result > 0){
						return "forward:/managermall/systemmall/finance/financelist.do";
					}else{
						return "forward:/managermall/systemmall/finance/queryByStoreId.do";
					}
				}
			}
		}
		return "forward:/managermall/systemmall/finance/queryByStoreId.do";
	}
	
	/**
	 * 根据店铺id查找
	 * @param session
	 * @param storeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryByStoreId")
	public ModelAndView queryByStoreId(HttpSession session,
			@RequestParam(value = "storeId", required = false) Long storeId) throws Exception {
		ModelAndView view = new ModelAndView("/system/finance/financeadd");
		if(storeId != null){
			StoreInfo storeInfo = storeService.getStoreDetaile(storeId);
			if(storeInfo != null){
				BankInfo bankInfo = bankInfoService.queryBankInfoBySellerId(storeInfo.getSellerId());
				if(bankInfo != null){
					view.addObject("store",storeInfo);
					view.addObject("bank", bankInfo);
				}
			}
		}
		return view;
	}
	
	/**
	 * 显示项目列表
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/financelist")
	public ModelAndView financeList(
			@RequestParam(value = "pageNum",required = false) Integer pageNum,
			@RequestParam(value = "pageSize",required = false) Integer pageSize) throws Exception{
		ModelAndView mav = new ModelAndView("/system/finance/financelist");
		if (pageNum == null || pageNum < 1)
			pageNum = 1;
		if (pageSize == null || pageSize < 0)
			pageSize = 10;
		Page<FqStoreLoan> page = financeService.queryStoreLoan(pageNum, pageSize);
		StoreLoanVo current = financeService.queryStoreLoanVoCurrent();
		StoreLoanVo history = financeService.queryStoreLoanVoHistory();

		mav.addObject("history", history);
		mav.addObject("current", current);
		mav.addObject("page",page);
		mav.addObject("pageNum", pageNum);
		mav.addObject("pageSize", pageSize);
		return mav;
	}
	/**
	 * 显示店铺借贷详情
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("financedetail")
	public ModelAndView financedetail(Long id) throws Exception{
		ModelAndView mav = new ModelAndView("/system/finance/financedetail");
		FqStoreLoan list = financeService.queryStoreLoanList(id);
		mav.addObject("list", list);
		return mav;
	}
	/**
	 * 还款页面信息
	 * @param id
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/financelistRepayment")
	public ModelAndView financelistRepayment(Long id,
			@RequestParam(value = "pageNum",required = false) Integer pageNum,
			@RequestParam(value = "pageSize",required = false) Integer pageSize)throws Exception{
		ModelAndView mav = new ModelAndView("system/finance/financelist_repayment");
		if (pageNum == null || pageNum < 1)
			pageNum = 1;
		if (pageSize == null || pageSize < 0)
			pageSize = 5;
		FqStoreLoan loanList = financeService.queryStoreLoanList(id);
		Page<FqStoreRepayment> page = financeService.queryStoreRepayment(id,pageNum, pageSize);
		mav.addObject("loanList", loanList);
		mav.addObject("page", page);
		return mav;
	}
	/**
	 * 删除项目
	 * @param id
	 * @param status
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteById")
	@ResponseBody
	public String deleteById(Long id,Integer status)throws Exception{
		if(id != null && status != null){
			int deleteLoan = financeService.deleteStoreLoan(id, status);
			if(deleteLoan > 0){
				return "success";
			}
		}
		return "error";
	}
	/**
	 * 点击取消更改状态
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateStatus")
	@ResponseBody
	public String updateStatus(Long id)throws Exception{
		FqStoreRepayment fqStoreRepayment = financeService.queryStoreRepaymentById(id);
		fqStoreRepayment.setStatus(3);
		int status = financeService.updateStatus(fqStoreRepayment, id);
		if(status > 0){
			return "success";
		}
		return "error";
	}
	/**
	 * 新增还款
	 * @param id
	 * @param principal
	 * @param interest
	 * @param repaymentType
	 * @param reservationTime
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/insertRepayment")
	public Object insertRepayment(
			@RequestParam(value = "id",required = false) Long id,
			@RequestParam(value = "principal",required = false) BigDecimal principal,
			@RequestParam(value = "interest",required = false) BigDecimal interest,
			@RequestParam(value = "repaymentType",required = false) Integer repaymentType,
			String reservationTime
			)throws Exception{
		FqStoreRepayment repayment = new FqStoreRepayment();
		if(null !=repaymentType && repaymentType >0){
			repayment.setRepaymentType(repaymentType);
			repayment.setPrincipal(principal);
			repayment.setInterest(interest);
			repayment.setCreateTime(new Date());
			repayment.setLoanId(id);
			if (StringUtils.isNotEmpty(reservationTime)) {
				repayment.setReservationTime(DateUtil.convertStringToDate("yyyy-MM-dd HH:mm:ss", reservationTime));
			}
			int repayment2 = financeService.insertRepayment(repayment, id);
			
			if(repayment2>0){
				return goUrl("/managermall/systemmall/finance/financelistRepayment.do?id="+id, "添加还款成功");
			}else if(repayment2 == -1){
				return goUrl("/managermall/systemmall/finance/financelistRepayment.do?id="+id, "商家账户异常");
			}else if(repayment2 == -2){
				return goUrl("/managermall/systemmall/finance/financelistRepayment.do?id="+id, "商家账户余额不足");
			}else if(repayment2 == -3){
				return goUrl("/managermall/systemmall/finance/financelistRepayment.do?id="+id, "还款本金大于剩余本金");
			}
		}
		return goUrl("/managermall/systemmall/finance/financelistRepayment.do?id="+id, "添加还款异常");
	}
	/**
	 * 费率套餐列表
	 * @param pageNum
	 * @param pageSize
	 * @param rateName
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/rateList")
	public ModelAndView reteList(
			@RequestParam(value = "pageNum",required = false) Integer pageNum,
			@RequestParam(value = "pageSize",required = false) Integer pageSize,
			@RequestParam(value = "rateName",required = false) String rateName,
			@RequestParam(value = "startTime",required = false) String startTime,
			@RequestParam(value = "endTime",required = false) String endTime)throws Exception {
		ModelAndView mav = new ModelAndView("system/finance/rate_list");
		if (pageNum == null || pageNum < 1)
			pageNum = 1;
		if (pageSize == null || pageSize < 0)
			pageSize = 5;
		Page<FqStoreRateVo> page = financeService.queryFqStoreRateVo(startTime, endTime, rateName, pageSize, pageNum);
		mav.addObject("page",page);
		mav.addObject("rateName",rateName );
		mav.addObject("startTime",startTime );
		mav.addObject("endTime",endTime);
		return mav;
	}
	/**
	 * 新增费率套餐
	 * @param session
	 * @param request
	 * @param rateName
	 * @param adminId
	 * @param adminUsername
	 * @param wechatBaseRate
	 * @param wechatAppendRate
	 * @param wechatAppendMoney
	 * @param alipayBaseRate
	 * @param alipayAppendRate
	 * @param alipayAppendMoney
	 * @param qqpayBaseRate
	 * @param qqpayAppendRate
	 * @param qqpayAppendMoney
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/insertFqStoreRate")
	@ResponseBody
	public String insertFqStoreRate(HttpSession session, HttpServletRequest request,
			String rateName,BigDecimal wechatBaseRate,
			BigDecimal wechatAppendRate, BigDecimal wechatAppendMoney,
			BigDecimal alipayBaseRate, BigDecimal alipayAppendRate,
			BigDecimal alipayAppendMoney, BigDecimal qqpayBaseRate,
			BigDecimal qqpayAppendRate, BigDecimal qqpayAppendMoney) throws Exception{
		AdminUser admin = getAdminUserSession(session);
		Long adminIds = admin.getId();//adminId
		String adminUser = admin.getUsername();//adminUserName
		int rate = financeService.insertFqStoreRate(rateName, adminIds, adminUser, wechatBaseRate, wechatAppendRate, wechatAppendMoney, alipayBaseRate, alipayAppendRate, 
				alipayAppendMoney, qqpayBaseRate, qqpayAppendRate, qqpayAppendMoney);
		if(rate > 0){
			return "success";
		}
		return "error";
	}
	/**
	 * 删除费率项目
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteFqStoreRate")
	@ResponseBody
	public String deleteFqStoreRate(Long id)throws Exception{
		int rate = financeService.deleteFqStoreRate(id);
		if(rate > 0){
			return "success";
		}
		return "error";
	}
	/**
	 * 更改项目费率
	 * @param rateName
	 * @param wechatBaseRate
	 * @param wechatAppendRate
	 * @param wechatAppendMoney
	 * @param alipayBaseRate
	 * @param alipayAppendRate
	 * @param alipayAppendMoney
	 * @param qqpayBaseRate
	 * @param qqpayAppendRate
	 * @param qqpayAppendMoney
	 * @return
	 */
	@RequestMapping("/updateFqStoreRate")
	@ResponseBody
	public String updateFqStoreRate(HttpServletRequest request, HttpServletResponse response,Long id,BigDecimal wechatBaseRate,
			BigDecimal wechatAppendRate, BigDecimal wechatAppendMoney,
			BigDecimal alipayBaseRate, BigDecimal alipayAppendRate,
			BigDecimal alipayAppendMoney, BigDecimal qqpayBaseRate,
			BigDecimal qqpayAppendRate, BigDecimal qqpayAppendMoney)throws Exception{
		FqStoreRate rate = new FqStoreRate();
		rate.setId(id);
		rate.setAlipayAppendMoney(alipayAppendMoney);
		rate.setAlipayAppendRate(alipayAppendRate);
		rate.setAlipayBaseRate(alipayBaseRate);
		rate.setQqpayAppendMoney(qqpayAppendMoney);
		rate.setQqpayAppendRate(qqpayAppendRate);
		rate.setQqpayBaseRate(qqpayBaseRate);
		rate.setWechatAppendMoney(wechatAppendMoney);
		rate.setWechatAppendRate(wechatAppendRate);
		rate.setWechatBaseRate(wechatBaseRate);
		
		int storeRate = financeService.updateFqStoreRate(rate);
		
		if(storeRate > 0){
			return "success";
		}
		return "error";
		
	}
	/**
	 * 显示未修改前数据
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/detail")
	@ResponseBody
	public FqStoreRate queryDetail(Long ids)throws Exception{
		FqStoreRate rate = financeService.queryFqStoreRate(ids);
		return rate;
	}
	
	/**
	 * 提成奖励策略列表
	 * @param roleName
	 * @param createName
	 * @param beginTime
	 * @param endTime
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/royalty_incentive_strategy")
	public ModelAndView royaltyIncentiveStrategy(@RequestParam(value = "roleName", required = false) String roleName,
			@RequestParam(value = "createName", required = false) String createName,
			@RequestParam(value = "beginTime", required = false) String beginTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize)throws Exception{
		if (null == roleName || StringUtils.isEmpty(roleName)){
			roleName = null;
		}
		if (null == createName || StringUtils.isEmpty(createName)){
			createName = null;
		}
		if (null == pageNum || pageNum < 1){
			pageNum = 1;
		}
		pageSize = 10;
		ModelAndView view = new ModelAndView("system/strategySet/royalty_incentive_strategy");
		Page<FqCommissionRoleVo> page = financeService.royaltyIncentiveStrategy(roleName, createName, StringUtils.isEmpty(beginTime) ? null : BaseUtil.toDate(beginTime+" 00:00:00"),
				StringUtils.isEmpty(endTime) ? null : BaseUtil.toDate(endTime+" 23:59:59"), pageNum, pageSize);
		view.addObject("page", page);
		view.addObject("roleName", roleName);
		view.addObject("createName", createName);
		view.addObject("beginTime", beginTime);
		view.addObject("endTime", endTime);
		view.addObject("pageNum", pageNum);
		return view;
	}
	
	/**
	 * 新增提成奖励策略
	 * @param roleName
	 * @param teamProportion
	 * @param clerkProportion
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/addFqCommissionRole")
	public String addFqCommissionRole(HttpSession session, @RequestParam(value = "roleName", required = false) String roleName,
			@RequestParam(value = "teamProportion", required = false) Double teamProportion,
			@RequestParam(value = "clerkProportion", required = false) Double clerkProportion
			) throws Exception{
		AdminUser adminUser = (AdminUser) session.getAttribute(ConstantsConfigurer.getProperty("system_key"));
		int result = financeService.addFqCommissionRole(roleName, adminUser.getId(), teamProportion, clerkProportion);
		if(result > 0){
			return "success";
		}
		return "fail";
	}
	
	/**
	 * 获取单个策略详情
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/fqCommissionRoleDetail")
	public FqCommissionRole fqCommissionRoleDetail(@RequestParam(value = "id", required = false) Long id) throws Exception{
		FqCommissionRole fqCommissionRole = financeService.fqCommissionRoleDetail(id);
		return fqCommissionRole;
	}
	
	/**
	 * 修改提成奖励策略
	 * @param id
	 * @param teamProportion
	 * @param clerkProportion
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/updateFqCommissionRole")
	public String updateFqCommissionRole(@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "teamProportion", required = false) Double teamProportion,
			@RequestParam(value = "clerkProportion", required = false) Double clerkProportion
			) throws Exception{
		int result = financeService.updateFqCommissionRole(id, teamProportion, clerkProportion);
		if(result > 0){
			return "success";
		}
		return "fail";
	}
	
	/**
	 * 删除指定提成奖励策略
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/deleteFqCommissionRole")
	public String deleteFqCommissionRole(@RequestParam(value = "id", required = false) Long id) throws Exception{
		List<FqClerk> list = qdManageService.queryAllFqClerkByCommissionId(id);
		//该策略有绑定的业务无法删除！
		if(list.size() == 0){
			int result = financeService.deleteFqCommissionRole(id);
			if(result > 0){
				return "success";
			}
			return "fail";
		}
		return "cannot";
	}
	
	/**
	 * 导出提成奖励策略表
	 * @param response
	 * @param session
	 * @param roleName
	 * @param createName
	 * @param beginTime
	 * @param endTime
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/excelFqCommissionRole")
	public ModelAndView excelFqCommissionRole(HttpServletResponse response,HttpSession session,
			@RequestParam(value = "roleName", required = false) String roleName,
			@RequestParam(value = "createName", required = false) String createName,
			@RequestParam(value = "beginTime", required = false) String beginTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HHmmss");
		if (null == roleName || StringUtils.isEmpty(roleName)){
			roleName = null;
		}
		if (null == createName || StringUtils.isEmpty(createName)){
			createName = null;
		}
		if (null == pageNum || pageNum < 1){
			pageNum = 1;
		}
		if(null != roleName){
			roleName = new String(roleName.getBytes("iso-8859-1"),"utf-8");
		}
		if(null != createName){
			createName = new String(createName.getBytes("iso-8859-1"),"utf-8");
		}
		pageSize = 10;
		Page<FqCommissionRoleVo> page = financeService.royaltyIncentiveStrategy(roleName, createName, StringUtils.isEmpty(beginTime) ? null : BaseUtil.toDate(beginTime+" 00:00:00"),
				StringUtils.isEmpty(endTime) ? null : BaseUtil.toDate(endTime+" 23:59:59"), pageNum, pageSize);
		String[] headersName = new String[] { "项次","创建时间", "创建人","策略名称","团队长提成比例", "业务员提成比例"};
		String[] headersId = new String[] { "id", "createTime", "createName","roleName","teamProportion","clerkProportion"};
		excelUtil.exportExcel("提成奖励策略" + format.format(new Date()), headersName, headersId, page.getResult(),
				response);
		return null;
	}
}