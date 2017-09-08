package cn.qhjys.mall.controller.system;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.AdminUser;
import cn.qhjys.mall.entity.IntegralLog;
import cn.qhjys.mall.entity.UserInfo;
import cn.qhjys.mall.service.AdminLogService;
import cn.qhjys.mall.service.UserInfoService;
import cn.qhjys.mall.service.system.IntegralLogService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.util.ServletUtils;
import com.allinpay.ets.client.StringUtil;
import com.github.pagehelper.Page;

/**
 * 系统后台 商城前台用户管理 会员列表
 * 
 * @author JiangXiaoPing
 *
 */
@Controller
@RequestMapping("/managermall/systemmall/malluser")
public class SystemnMallUserManageController extends Base {
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private IntegralLogService clogService;
	@Autowired
	private AdminLogService adminLogService;

	/**
	 * 
	 * @Title: list 会员管理
	 * @param session
	 * 
	 * @param userId
	 *            会员ID
	 * @param userName
	 *            会员名称
	 * @param Email
	 *            邮件
	 * @param createDate
	 *            大于创建时间
	 * @param createEnd
	 *            小于创建时间
	 * @param pageNum
	 *            页数
	 * @param pageSize
	 *            每页显示
	 * @param provinceId
	 *            省级ID
	 * @param cityId
	 *            市级ID
	 * @param Area
	 *            地区ID
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public ModelAndView list(HttpSession session, @RequestParam(value = "reqistSource", required = false) String reqistSource,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "Email", required = false) String Email,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "licenseProvince", required = false) Long provinceId,
			@RequestParam(value = "licenseCity", required = false) Long cityId,
			@RequestParam(value = "licenseArea", required = false) Long areaId,
			@RequestParam(value = "createStart", required = false) String createStart,
			@RequestParam(value = "createEnd", required = false) String createEnd,
			@RequestParam(value = "status", required = false) Integer status,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws ParseException, Exception {
		ModelAndView view = new ModelAndView("/system/mallusermanage/list");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (null == pageNum || pageNum < 1) {
			pageNum = 1;
		}
		if (null != reqistSource &&("" == reqistSource || reqistSource.length() < 1))
			reqistSource = null;
		if (null == userName || userName.isEmpty())
			userName = null;
		if (null == Email || Email.isEmpty())
			Email = null;
		if (null == phone || phone.isEmpty())
			phone = null;
		if (null == provinceId || provinceId < 1)
			provinceId = null;
		if (null == cityId || cityId < 1)
			cityId = null;
		if (null == areaId || areaId < 1)
			areaId = null;
		if (null == status)
			status = null;
		
		pageSize = 10;
		Page<UserInfo> page = userInfoService.querySystemMallUserPage(reqistSource, userName, Email, phone, StringUtil
				.isEmpty(createStart) ? null : format.parse(createStart+" 00:00:00"),
				StringUtil.isEmpty(createEnd) ? null : format.parse(createEnd+" 23:59:59"), provinceId, cityId, areaId, status,
				pageNum, pageSize);
		view.addObject("reqistSource", reqistSource);
		view.addObject("userName", userName);
		view.addObject("Email", Email);
		view.addObject("phone", phone);
		view.addObject("createStart", createStart);
		view.addObject("createEnd", createEnd);
		view.addObject("provinceId", provinceId);
		view.addObject("cityId", cityId);
		view.addObject("areaId", areaId);
		view.addObject("status", status);
		view.addObject("pageNum", pageNum);
		view.addObject("page", page);
		return view;
	}

	/**
	 * 
	 * @Title: detail 商家前台用户详情
	 * @param id
	 *            用户ID
	 * @return
	 * @return ModelAndView
	 * @throws Exception
	 */
	@RequestMapping("/detail")
	public ModelAndView detail(@RequestParam(value = "id", required = true) Long id) throws Exception {
		ModelAndView view = new ModelAndView("/system/mallusermanage/detail_page");
		UserInfo userInfo = userInfoService.selectUserById(id);
		view.addObject("userInfo", userInfo);
		return view;
	}

	@RequestMapping("/resetPassword")
	public void resetPassword(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "ids", required = true) Long[] ids) throws Exception {
		List<Long> strlist = new ArrayList<>();
		for (Long strings : ids)
			if (null != strings)
				strlist.add(strings);
		Boolean bln = userInfoService.updateUserPassWord(strlist);
		if (bln) {
			AdminUser admin = (AdminUser) session.getAttribute(ConstantsConfigurer.getProperty("system_key"));
			String info = BaseUtil.getLogInfo("批量重置帐号密码", "UserInfo", null);
			info = info.replace("null", "");
			for (int i = 0; i < ids.length; i++)
				info += ids[i].toString() + ",";
			String IP = ServletUtils.getIpAddr(request);
			adminLogService.insertAdminLog(admin.getId(), info.substring(0, info.length() - 1), "会员管理", IP);
			HtmlUtil.writerJson(response, "success");
		} else
			HtmlUtil.writerJson(response, "error");
	}

	/**
	 * 是否启用 状态修改
	 * 
	 * @param response
	 * @param type
	 * @param ids
	 * @throws Exception
	 */
	@RequestMapping("/openandClose")
	public void openandClose(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "type", required = true) String type,
			@RequestParam(value = "ids", required = true) Long[] ids) throws Exception {
		List<Long> strlist = new ArrayList<>();
		for (Long strings : ids)
			if (null != strings)
				strlist.add(strings);
		Integer integer = 0;
		if (type.equals("on"))
			integer = 1;
		Boolean bln = userInfoService.updateUserEnabled(1L, strlist, integer);
		if (bln) {
			AdminUser admin = (AdminUser) session.getAttribute(ConstantsConfigurer.getProperty("system_key"));
			String action = type.equals("on") ? "批量启用帐号" : "批量禁用帐号";
			String info = BaseUtil.getLogInfo(action, "UserInfo", null);
			info = info.replace("null", "");
			for (int i = 0; i < ids.length; i++)
				info += ids[i].toString() + ",";
			String IP = ServletUtils.getIpAddr(request);
			adminLogService.insertAdminLog(admin.getId(), info.substring(0, info.length() - 1), "会员管理", IP);
			HtmlUtil.writerJson(response, "success");
		} else
			HtmlUtil.writerJson(response, "error");
	}

	/**
	 * 消费积分操作日记 分页
	 * 
	 * @param userId
	 *            用户ID
	 * @param type
	 *            类型
	 * @param changeStart
	 *            创建开始
	 * @param changeEnd
	 *            创建时间结束
	 * @param pageNum
	 *            页数
	 * @param pageSize
	 *            每页显示
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/consumptionLogList")
	public ModelAndView consumptionLogList(@RequestParam(value = "userId", required = false) Long userId,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "changeStart", required = false) String changeStart,
			@RequestParam(value = "changeEnd", required = false) String changeEnd,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize, HttpSession session) throws Exception {
		if (null == pageNum)
			pageNum = 1;
		if (null == pageSize)
			pageSize = 10;
		ModelAndView view = new ModelAndView("/system/mallusermanage/consumption_log_list");
		Page<IntegralLog> page = clogService.queryPage(userId, type, changeStart, changeEnd, pageNum, pageSize);
		view.addObject("page", page);
		view.addObject("userId", userId);
		view.addObject("type", type);
		view.addObject("changeStart", changeStart);
		view.addObject("changeEnd", changeEnd);
		view.addObject("pageNum", pageNum);
		return view;
	}
}