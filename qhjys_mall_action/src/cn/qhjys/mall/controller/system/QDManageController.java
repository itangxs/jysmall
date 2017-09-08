package cn.qhjys.mall.controller.system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
import cn.qhjys.mall.entity.CityInfo;
import cn.qhjys.mall.entity.FqBranch;
import cn.qhjys.mall.entity.FqClerk;
import cn.qhjys.mall.entity.FqCommissionRole;
import cn.qhjys.mall.entity.FqTeam;
import cn.qhjys.mall.entity.ProvinceInfo;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.service.AddressService;
import cn.qhjys.mall.service.system.QDManageService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ExportExcelUtil;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.vo.system.FqClerkVo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

/**
 * 渠道管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/managermall/systemmall/qdmanage")
public class QDManageController extends Base{
	
	@Autowired
	private QDManageService qdManageService;
	@Autowired
	private AddressService addressService;
	@SuppressWarnings("rawtypes")
	ExportExcelUtil excelUtil = new ExportExcelUtil();
	
	/**
	 * 服务商列表
	 * @param createStart
	 * @param createEnd
	 * @param provinceId
	 * @param cityId
	 * @param fwsId
	 * @param branchName
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/fwsList")
	public ModelAndView fqBranchList(@RequestParam(value = "createStart", required = false) String createStart,
			@RequestParam(value = "createEnd", required = false) String createEnd,
			@RequestParam(value = "provinceId", required = false) Long provinceId,
			@RequestParam(value = "cityId", required = false) Long cityId,
			@RequestParam(value = "fwsId", required = false) Long fwsId,
			@RequestParam(value = "branchName", required = false) String branchName,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception{
		ModelAndView view = new ModelAndView("/system/qdmanage/fws_list");
		if (null == provinceId || provinceId < 1)
			provinceId = null;
		if (null == cityId || cityId < 1)
			cityId = null;
		if (null == fwsId || fwsId < 1)
			fwsId = null;
		if (null == branchName || StringUtils.isEmpty(branchName)){
			branchName = null;
		}
		if (null == pageNum || pageNum < 1){
			pageNum = 1;
		}
		pageSize = 10;
		Page<FqBranch> fqBranch = qdManageService.queryFwsList(StringUtils.isEmpty(createStart) ? null : BaseUtil.toDate(createStart+" 00:00:00"), 
				StringUtils.isEmpty(createEnd) ? null : BaseUtil.toDate(createEnd+" 23:59:59"), provinceId, cityId, branchName, fwsId, pageNum, pageSize);
		view.addObject("page", fqBranch);
		view.addObject("createStart", createStart);
		view.addObject("createEnd", createEnd);
		view.addObject("provinceId", provinceId);
		view.addObject("cityId", cityId);
		view.addObject("fwsId", fwsId);
		view.addObject("branchName", branchName);
		view.addObject("pageNum", pageNum);
		return view;
	}
	
	/**
	 * 删除服务商信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/deleteFqBranch")
	public String deleteFqBranch(@RequestParam(value = "id", required = false) Long id) throws Exception{
		List<FqTeam> list = qdManageService.queryFqTeamByBranchId(id);
		//该企业有绑定的下属信息不能删除！
		if(null == list){
			int result = qdManageService.deleteFqBranch(id);
			if(result > 0){
				return "success";
			}
			return "fail";
		}
		return "cannot";
	}
	
	/**
	 * 服务商信息详情
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/fqBranchDetail")
	public FqBranch fqBranchDetail(@RequestParam(value = "id", required = false) Long id) throws Exception{
		FqBranch fqBranch = qdManageService.fqBranchDetail(id);
		return fqBranch;
	}
	
	/**
	 * 修改服务商信息
	 * @param id
	 * @param branchName
	 * @param principal
	 * @param phoneNum
	 * @param provinceId
	 * @param cityId
	 * @param address
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/updateFqBranch")
	public String updateFqBranch(@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "principal", required = false) String principal,
			@RequestParam(value = "phoneNum", required = false) String phoneNum,
			@RequestParam(value = "provinceId", required = false) Long provinceId,
			@RequestParam(value = "cityId", required = false) Long cityId,
			@RequestParam(value = "address", required = false) String address
			) throws Exception{
		ProvinceInfo provinceInfo = addressService.getProvinceInfoById(provinceId);
		CityInfo cityInfo = addressService.getCityInfoById(cityId);
		int result = qdManageService.updateFqBranch(id, principal, phoneNum, provinceId, provinceInfo.getName(), cityId, cityInfo.getName(), address);
		if(result > 0){
			return "success";
		}
		return "fail";
	}
	
	/**
	 * 新增服务商
	 * @param branchName
	 * @param principal
	 * @param phoneNum
	 * @param provinceId
	 * @param cityId
	 * @param address
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/addFqBranch")
	public String addFqBranch(@RequestParam(value = "branchName", required = false) String branchName,
			@RequestParam(value = "principal", required = false) String principal,
			@RequestParam(value = "phoneNum", required = false) String phoneNum,
			@RequestParam(value = "provinceId", required = false) Long provinceId,
			@RequestParam(value = "cityId", required = false) Long cityId,
			@RequestParam(value = "address", required = false) String address
			) throws Exception{
		ProvinceInfo provinceInfo = addressService.getProvinceInfoById(provinceId);
		CityInfo cityInfo = addressService.getCityInfoById(cityId);
		int result = qdManageService.addFqBranch(branchName, principal, phoneNum, provinceId, provinceInfo.getName(), cityId, cityInfo.getName(), address);
		if(result > 0){
			return "success";
		}
		return "fail";
	}
	
	/**
	 * 团队长信息列表
	 * @param createStart
	 * @param createEnd
	 * @param branchName
	 * @param id
	 * @param principal
	 * @param phoneNum
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/tdzList")
	public ModelAndView fqTeamList(@RequestParam(value = "createStart", required = false) String createStart,
			@RequestParam(value = "createEnd", required = false) String createEnd,
			@RequestParam(value = "branchName", required = false) String branchName,
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "principal", required = false) String principal,
			@RequestParam(value = "phoneNum", required = false) String phoneNum,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception{
		ModelAndView view = new ModelAndView("/system/qdmanage/tdz_list");
		if (null == id || id < 1)
			id = null;
		if (null == branchName || StringUtils.isEmpty(branchName)){
			branchName = null;
		}
		if (null == principal || StringUtils.isEmpty(principal)){
			principal = null;
		}
		if (null == phoneNum || StringUtils.isEmpty(phoneNum)){
			phoneNum = null;
		}
		if (null == pageNum || pageNum < 1){
			pageNum = 1;
		}
		pageSize = 10;
		Page<FqTeam> fqTeam = qdManageService.queryFqTeamList(StringUtils.isEmpty(createStart) ? null : BaseUtil.toDate(createStart+" 00:00:00"),
				StringUtils.isEmpty(createEnd) ? null : BaseUtil.toDate(createEnd+" 23:59:59"), branchName, id, principal, phoneNum, pageNum, pageSize);
		view.addObject("page", fqTeam);
		view.addObject("createStart", createStart);
		view.addObject("createEnd", createEnd);
		view.addObject("id", id);
		view.addObject("branchName", branchName);
		view.addObject("principal", principal);
		view.addObject("phoneNum", phoneNum);
		view.addObject("pageNum", pageNum);
		List<FqBranch> fqBranchs = qdManageService.queryAllFqBranch();
		view.addObject("fqBranchs", fqBranchs);
		return view;
	}
	
	/**
	 * 新增团队长信息
	 * @param principal
	 * @param phoneNum
	 * @param branchId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/addFqTeam")
	public String addFqTeam(@RequestParam(value = "principal", required = false) String principal,
			@RequestParam(value = "phoneNum", required = false) String phoneNum,
			@RequestParam(value = "branchId", required = false) Long branchId) throws Exception{
		int result = qdManageService.addFqTeam(principal, phoneNum, branchId);	
		if(result > 0){
			return "success";
		}
		return "fail";
	}
	
	/**
	 * 删除团队长信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/deleteFqTeam")
	public String deleteFqTeam(@RequestParam(value = "id", required = false) Long id) throws Exception{
		List<FqClerk> list = qdManageService.queryFqClerkByTeamId(id);
		//该团队长有绑定的业务员信息不能删除！
		if(null == list){
			int result = qdManageService.deleteFqTeam(id);
			if(result > 0){
				return "success";
			}
			return "fail";
		}
		return "cannot";
	}
	
	/**
	 * 团队长信息详情
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/fqTeamDetail")
	public FqTeam fqTeamDetail(@RequestParam(value = "id", required = false) Long id) throws Exception{
		FqTeam fqTeam = qdManageService.fqTeamDetail(id);
		return fqTeam;
	}
	
	/**
	 * 修改团队长信息
	 * @param id
	 * @param principal
	 * @param phoneNum
	 * @param branchId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/updateFqTeam")
	public String updateFqTeam(@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "principal", required = false) String principal,
			@RequestParam(value = "phoneNum", required = false) String phoneNum,
			@RequestParam(value = "branchId", required = false) Long branchId) throws Exception{
		int result = qdManageService.updateFqTeam(id, principal, phoneNum, branchId);
		if(result > 0){
			return "success";
		}
		return "fail";
	}
	
	/**
	 * 业务员信息列表
	 * @param createStart
	 * @param createEnd
	 * @param branchName
	 * @param teamName
	 * @param id
	 * @param clerk
	 * @param phoneNum
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ywyList")
	public ModelAndView fqClerkList(@RequestParam(value = "createStart", required = false) String createStart,
			@RequestParam(value = "createEnd", required = false) String createEnd,
			@RequestParam(value = "branchName", required = false) String branchName,
			@RequestParam(value = "teamName", required = false) String teamName,
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "clerk", required = false) String clerk,
			@RequestParam(value = "phoneNum", required = false) String phoneNum,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception{
		ModelAndView view = new ModelAndView("/system/qdmanage/ywy_list");
		if (null == id || id < 1)
			id = null;
		if (null == branchName || StringUtils.isEmpty(branchName)){
			branchName = null;
		}
		if (null == teamName || StringUtils.isEmpty(teamName)){
			teamName = null;
		}
		if (null == clerk || StringUtils.isEmpty(clerk)){
			clerk = null;
		}
		if (null == phoneNum || StringUtils.isEmpty(phoneNum)){
			phoneNum = null;
		}
		if (null == pageNum || pageNum < 1){
			pageNum = 1;
		}
		pageSize = 10;
		Page<FqClerkVo> fqClerk = qdManageService.queryFqClerkList(StringUtils.isEmpty(createStart) ? null : BaseUtil.toDate(createStart+" 00:00:00"), 
				StringUtils.isEmpty(createEnd) ? null : BaseUtil.toDate(createEnd+" 23:59:59"), branchName, teamName, id, clerk, phoneNum, pageNum, pageSize);
		view.addObject("page", fqClerk);
		view.addObject("createStart", createStart);
		view.addObject("createEnd", createEnd);
		view.addObject("id", id);
		view.addObject("branchName", branchName);
		view.addObject("teamName", teamName);
		view.addObject("clerk", clerk);
		view.addObject("phoneNum", phoneNum);
		view.addObject("pageNum", pageNum);
		List<FqCommissionRole> FqCommissionRoles = qdManageService.fqCommissionRoleList();
		view.addObject("FqCommissionRoles", FqCommissionRoles);
		return view;
	}
	
	/**
	 * 删除业务员信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/deleteFqClerk")
	public String deleteFqClerk(@RequestParam(value = "id", required = false) Long id) throws Exception{
		List<StoreInfo> list = qdManageService.getStoreInfoByClerkId(id);
		//该业务员有绑定的商户信息不能删除！
		if(null == list){
			int result = qdManageService.deleteFqClerk(id);
			if(result > 0){
				return "success";
			}
			return "fail";
		}
		return "cannot";
	}
	
	/**
	 * 业务员信息详情
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/fqClerkDetail")
	public FqClerkVo fqClerkDetail(@RequestParam(value = "id", required = false) Long id) throws Exception{
		FqClerkVo fqClerkVo = qdManageService.fqClerkDetail(id);
		return fqClerkVo;
	}
	
	
	/**
	 * 修改业务员信息
	 * @param id
	 * @param clerk
	 * @param phoneNum
	 * @param branchId
	 * @param branchName
	 * @param teamId
	 * @param teamName
	 * @param workDate
	 * @param commission
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/updateFqClerk")
	public String updateFqClerk(@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "clerk", required = false) String clerk,
			@RequestParam(value = "phoneNum", required = false) String phoneNum,
			@RequestParam(value = "branchId", required = false) Long branchId,
			@RequestParam(value = "branchName", required = false) String branchName,
			@RequestParam(value = "teamId", required = false) Long teamId,
			@RequestParam(value = "teamName", required = false) String teamName,
			@RequestParam(value = "workDate", required = false) String workDate,
			@RequestParam(value = "commission", required = false) Long commission) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		int result = qdManageService.updateFqClerk(id, clerk, phoneNum, teamId, teamName, StringUtils.
				isEmpty(workDate) ? null : format.parse(workDate), commission);
		if(result > 0){
			return "success";
		}
		return "fail";
	}

	/**
	 * 新增业务员信息
	 * @param clerk
	 * @param phoneNum
	 * @param teamId
	 * @param teamName
	 * @param workDate
	 * @param commission
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/addFqClerk")
	public String addFqClerk(@RequestParam(value = "clerk", required = false) String clerk,
			@RequestParam(value = "phoneNum", required = false) String phoneNum,
			@RequestParam(value = "teamId", required = false) Long teamId,
			@RequestParam(value = "teamName", required = false) String teamName,
			@RequestParam(value = "workDate", required = false) String workDate,
			@RequestParam(value = "commission", required = false) Long commission) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		int result = qdManageService.addFqClerk(clerk, phoneNum, teamId, teamName, StringUtils.
				isEmpty(workDate) ? null : format.parse(workDate), commission);
		if(result > 0){
			return "success";
		}
		return "fail";
	}
	
	@RequestMapping(value = "/queryFqBranch", method = RequestMethod.POST)
	public void queryFqBranch(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject obj = new JSONObject();
		List<FqBranch> fqBranchList = qdManageService.queryAllFqBranch();
		obj.put("fqBranchList", JSON.toJSON(fqBranchList));
		HtmlUtil.writerJson(response, obj);
	}
	
	@RequestMapping(value = "/queryFqTeam", method = RequestMethod.POST)
	public void queryFqTeam(Long branchId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject obj = new JSONObject();
		List<FqTeam> fqTeamList = qdManageService.queryAllFqTeam(branchId);
		obj.put("fqTeamList", JSON.toJSON(fqTeamList));
		HtmlUtil.writerJson(response, obj);
	}
	
	@RequestMapping(value = "/queryFqClerk", method = RequestMethod.POST)
	public void queryFqClerk(Long teamId, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject obj = new JSONObject();
		List<FqClerk> fqClerkList = qdManageService.queryAllFqClerk(teamId);
		obj.put("fqClerkList", JSON.toJSON(fqClerkList));
		HtmlUtil.writerJson(response, obj);
	}
	
	/**
	 * 服务商列表数据导出
	 * @param response
	 * @param session
	 * @param createStart
	 * @param createEnd
	 * @param provinceId
	 * @param cityId
	 * @param fwsId
	 * @param branchName
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/excelFqBranchList")
	public ModelAndView excelFqBranchList(HttpServletResponse response,HttpSession session,
			@RequestParam(value = "createStart", required = false) String createStart,
			@RequestParam(value = "createEnd", required = false) String createEnd,
			@RequestParam(value = "provinceId", required = false) Long provinceId,
			@RequestParam(value = "cityId", required = false) Long cityId,
			@RequestParam(value = "fwsId", required = false) Long fwsId,
			@RequestParam(value = "branchName", required = false) String branchName,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HHmmss");
		if (null == provinceId || provinceId < 1)
			provinceId = null;
		if (null == cityId || cityId < 1)
			cityId = null;
		if (null == fwsId || fwsId < 1)
			fwsId = null;
		if (null == branchName || StringUtils.isEmpty(branchName)){
			branchName = null;
		}
		if (null == pageNum || pageNum < 1){
			pageNum = 1;
		}
		pageSize = 10;
		Page<FqBranch> page = qdManageService.queryFwsList(StringUtils.isEmpty(createStart) ? null : BaseUtil.toDate(createStart+" 00:00:00"), 
				StringUtils.isEmpty(createEnd) ? null : BaseUtil.toDate(createEnd+" 23:59:59"), provinceId, cityId, branchName, fwsId, pageNum, pageSize);
		String[] headersName = new String[] { "创建时间", "服务商ID","省","市", "公司名称","联系人","电话","详细地址"};
		String[] headersId = new String[] { "createTime", "id", "provinceName","cityName","branchName","principal","phoneNum","address"};
		excelUtil.exportExcel("服务商列表" + format.format(new Date()), headersName, headersId, page.getResult(),
				response);
		return null;
	}
	
	/**
	 * 团队长列表数据导出
	 * @param response
	 * @param session
	 * @param createStart
	 * @param createEnd
	 * @param branchName
	 * @param id
	 * @param principal
	 * @param phoneNum
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/excelFqTeamList")
	public ModelAndView excelFqTeamList(HttpServletResponse response,HttpSession session,
			@RequestParam(value = "createStart", required = false) String createStart,
			@RequestParam(value = "createEnd", required = false) String createEnd,
			@RequestParam(value = "branchName", required = false) String branchName,
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "principal", required = false) String principal,
			@RequestParam(value = "phoneNum", required = false) String phoneNum,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HHmmss");
		if (null == id || id < 1)
			id = null;
		if (null == branchName || StringUtils.isEmpty(branchName)){
			branchName = null;
		}
		if (null == principal || StringUtils.isEmpty(principal)){
			principal = null;
		}
		if (null == phoneNum || StringUtils.isEmpty(phoneNum)){
			phoneNum = null;
		}
		if (null == pageNum || pageNum < 1){
			pageNum = 1;
		}
		pageSize = 10;
		Page<FqTeam> page = qdManageService.queryFqTeamList(StringUtils.isEmpty(createStart) ? null : BaseUtil.toDate(createStart+" 00:00:00"),
				StringUtils.isEmpty(createEnd) ? null : BaseUtil.toDate(createEnd+" 23:59:59"), branchName, id, principal, phoneNum, pageNum, pageSize);
		String[] headersName = new String[] { "创建时间", "团队长ID", "公司名称","团队长名称","电话"};
		String[] headersId = new String[] { "createTime", "id", "branchName","principal","phoneNum"};
		excelUtil.exportExcel("团队长列表" + format.format(new Date()), headersName, headersId, page.getResult(),
				response);
		return null;
	}
	
	/**
	 * 业务员列表数据导出
	 * @param response
	 * @param session
	 * @param createStart
	 * @param createEnd
	 * @param branchName
	 * @param teamName
	 * @param id
	 * @param clerk
	 * @param phoneNum
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/excelFqClerk")
	public ModelAndView excelFqClerk(HttpServletResponse response,HttpSession session,
			@RequestParam(value = "createStart", required = false) String createStart,
			@RequestParam(value = "createEnd", required = false) String createEnd,
			@RequestParam(value = "branchName", required = false) String branchName,
			@RequestParam(value = "teamName", required = false) String teamName,
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "clerk", required = false) String clerk,
			@RequestParam(value = "phoneNum", required = false) String phoneNum,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HHmmss");
		if (null == id || id < 1)
			id = null;
		if (null == branchName || StringUtils.isEmpty(branchName)){
			branchName = null;
		}
		if (null == teamName || StringUtils.isEmpty(teamName)){
			teamName = null;
		}
		if (null == clerk || StringUtils.isEmpty(clerk)){
			clerk = null;
		}
		if (null == phoneNum || StringUtils.isEmpty(phoneNum)){
			phoneNum = null;
		}
		if (null == pageNum || pageNum < 1)
			pageNum = 1;
		if (null == pageSize || pageSize == 0)
			pageSize = 10;
		Page<FqClerkVo> page = qdManageService.queryFqClerkList(StringUtils.isEmpty(createStart) ? null : BaseUtil.toDate(createStart+" 00:00:00"), 
				StringUtils.isEmpty(createEnd) ? null : BaseUtil.toDate(createEnd+" 23:59:59"), branchName, teamName, id, clerk, phoneNum, pageNum, pageSize);
		String[] headersName = new String[] { "创建时间", "业务员ID", "公司名称","团队长名称","业务员名称","电话","入职时间","提成策略"};
		String[] headersId = new String[] { "createTime", "id", "branchName","teamName","clerk","phoneNum","workDate","commission"};
		excelUtil.exportExcel("业务员列表" + format.format(new Date()), headersName, headersId, page.getResult(),
				response);
		return null;
	}
}
