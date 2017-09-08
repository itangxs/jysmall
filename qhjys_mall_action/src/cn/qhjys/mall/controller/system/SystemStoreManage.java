package cn.qhjys.mall.controller.system;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.AdminUser;
import cn.qhjys.mall.entity.AreaInfo;
import cn.qhjys.mall.entity.AuthenticationChnnel;
import cn.qhjys.mall.entity.BankInfo;
import cn.qhjys.mall.entity.CityInfo;
import cn.qhjys.mall.entity.CompanyInfo;
import cn.qhjys.mall.entity.FqStoreApply;
import cn.qhjys.mall.entity.FqStoreRate;
import cn.qhjys.mall.entity.ManagerCategory;
import cn.qhjys.mall.entity.MsAuthentication;
import cn.qhjys.mall.entity.ProviceCityAreaCode;
import cn.qhjys.mall.entity.ProvinceInfo;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.StoreCheck;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.mapper.AreaInfoMapper;
import cn.qhjys.mall.mapper.BankInfoMapper;
import cn.qhjys.mall.mapper.CityInfoMapper;
import cn.qhjys.mall.mapper.CompanyInfoMapper;
import cn.qhjys.mall.mapper.ProvinceInfoMapper;
import cn.qhjys.mall.mapper.SellerInfoMapper;
import cn.qhjys.mall.mapper.StoreInfoMapper;
import cn.qhjys.mall.service.AdminLogService;
import cn.qhjys.mall.service.AuthenticationChnnelService;
import cn.qhjys.mall.service.BankInfoService;
import cn.qhjys.mall.service.ManagerCategoryService;
import cn.qhjys.mall.service.MsAuthenticationService;
import cn.qhjys.mall.service.ProviceCityAreaCodeService;
import cn.qhjys.mall.service.SellerAssignedService;
import cn.qhjys.mall.service.SellerService;
import cn.qhjys.mall.service.StoreService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.EncodeMD5;
import cn.qhjys.mall.util.ExportExcelUtil;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.util.MessageUtil;
import cn.qhjys.mall.util.ServletUtils;
import cn.qhjys.mall.util.ms.BaseRequest;
import cn.qhjys.mall.util.ms.ConfigUtils;
import cn.qhjys.mall.util.ms.RequestNo;
import cn.qhjys.mall.vo.PayChnnelVo;
import cn.qhjys.mall.vo.SellerAuthenticationInfo;
import cn.qhjys.mall.vo.StorExporteVo;
import cn.qhjys.mall.vo.StoreVo;
import cn.qhjys.mall.vo.system.FqClerkVo;
import cn.qhjys.mall.weixin.util.JsonUtil;
import cn.qhjys.mall.weixin.util.SystemConstant;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

@Controller
@RequestMapping("/managermall/systemmall/store")
public class SystemStoreManage extends Base {
	@Autowired
	private StoreService storeService;
	@Autowired
	private SellerAssignedService sellerAssignedService;
	@Autowired
	private SellerService sellerService;
	@Autowired
	private AdminLogService adminLogService;
	@Autowired
	private BankInfoService bankInfoService;
	@Autowired
	private CompanyInfoMapper companyInfoMapper;
	@Autowired
	private AuthenticationChnnelService authenticationChnnelService;
	@Autowired
	private MsAuthenticationService msAuthenticationService;
	@Autowired
	private ProvinceInfoMapper provinceInfoMapper;
	@Autowired
	private CityInfoMapper cityInfoMapper;
	@Autowired
	private AreaInfoMapper areaInfoMapper;
	@Autowired
	private ManagerCategoryService managerCategoryService;
	@Autowired
	private ProviceCityAreaCodeService proviceCityAreaCodeService;
	@Autowired
	private BankInfoMapper bankInfoMapper;
	@Autowired
	private StoreInfoMapper storeInfoMapper;
	@Autowired
	private SellerInfoMapper sellerInfoMapper;

	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "storeName", required = false) String storeName,
			@RequestParam(value = "storeId", required = false) Long storeId,
			@RequestParam(value = "yewuyuan", required = false) String yewuyuan,
			@RequestParam(value = "licenseProvince", required = false) Long provinceId,
			@RequestParam(value = "licenseCity", required = false) Long cityId,
			@RequestParam(value = "licenseArea", required = false) Long areaId,
			@RequestParam(value = "createStart", required = false) String createStart,
			@RequestParam(value = "createEnd", required = false) String createEnd,
			@RequestParam(value = "status", required = false) Integer status,
			@RequestParam(value = "cashierStatus", required = false) Integer cashierStatus,
			@RequestParam(value = "orderStatus", required = false) Integer orderStatus,
			@RequestParam(value = "rateStatus", required = false) Integer rateStatus,
			@RequestParam(value = "wxAuthState", required = false) Integer wxAuthState,
			@RequestParam(value = "zfbAuthState", required = false) Integer zfbAuthState,
			@RequestParam(value = "hangyeType", required = false) Integer hangyeType,
			@RequestParam(value = "hangyeType2", required = false) Integer hangyeType2,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize){
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			ModelAndView view = new ModelAndView("/system/store/store_list");
			if (null == username || username == "")
				username = null;
			if (null == storeName || StringUtils.isEmpty(storeName))
				storeName = null;
			if (null == storeId || storeId < 1)
				storeId = null;
			if (null == provinceId || provinceId < 1)
				provinceId = null;
			if (null == cityId || cityId < 1)
				cityId = null;
			if (null == areaId || areaId < 1)
				areaId = null;
			if (null == status)
				status = null;
			if (null == cashierStatus)
				cashierStatus = null;
			if (null == orderStatus)
				orderStatus = null;
			if (null == rateStatus || rateStatus < 1)
				rateStatus = null;
			if (null == pageNum || pageNum < 1)
				pageNum = 1;
			pageSize = 10;
			Page<StoreVo> page = storeService.queryStoreVoByPage(username, storeName, storeId, provinceId, cityId, areaId, StringUtils
						.isEmpty(createStart) ? null : format.parse(createStart), StringUtils.isEmpty(createEnd) ? null
						: format.parse(createEnd), status, cashierStatus, orderStatus, rateStatus,wxAuthState, 
						zfbAuthState,hangyeType2,hangyeType,yewuyuan,pageNum, pageSize);
			
			view.addObject("page", page);
			view.addObject("username", username);
			view.addObject("storeName", storeName);
			view.addObject("yewuyuan", yewuyuan);
			view.addObject("storeId", storeId);
			view.addObject("provinceId", provinceId);
			view.addObject("cityId", cityId);
			view.addObject("areaId", areaId);
			view.addObject("createStart", createStart);
			view.addObject("createEnd", createEnd);
			view.addObject("status", status);
			view.addObject("cashierStatus", cashierStatus);
			view.addObject("orderStatus", orderStatus);
			view.addObject("rateStatus", rateStatus);
			view.addObject("wxAuthState", wxAuthState);
			view.addObject("zfbAuthState", zfbAuthState);
			view.addObject("hangyeType", hangyeType);
			view.addObject("hangyeType2", hangyeType2);
			view.addObject("pageNum", pageNum); 
			List<FqStoreRate> fqStoreRates = storeService.getFqStoreRate();
			view.addObject("fqStoreRates", fqStoreRates);
			return view;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 描述: 导出
	 * @return
	 */
	@RequestMapping("/list_export")
	public String getListExport(@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "storeName", required = false) String storeName,
			@RequestParam(value = "storeId", required = false) Long storeId,
			@RequestParam(value = "yewuyuan", required = false) String yewuyuan,
			@RequestParam(value = "licenseProvince", required = false) Long provinceId,
			@RequestParam(value = "licenseCity", required = false) Long cityId,
			@RequestParam(value = "licenseArea", required = false) Long areaId,
			@RequestParam(value = "createStart", required = false) String createStart,
			@RequestParam(value = "createEnd", required = false) String createEnd,
			@RequestParam(value = "status", required = false) Integer status,
			@RequestParam(value = "cashierStatus", required = false) Integer cashierStatus,
			@RequestParam(value = "orderStatus", required = false) Integer orderStatus,
			@RequestParam(value = "rateStatus", required = false) Integer rateStatus,
			@RequestParam(value = "wxAuthState", required = false) Integer wxAuthState,
			@RequestParam(value = "zfbAuthState", required = false) Integer zfbAuthState,
			@RequestParam(value = "hangyeType", required = false) Integer hangyeType,
			@RequestParam(value = "hangyeType2", required = false) Integer hangyeType2,
			HttpServletResponse response){
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			List<StorExporteVo> list = storeService.queryStoreVoByExcel(username, storeName, storeId, provinceId, cityId, areaId, StringUtils
					.isEmpty(createStart) ? null : format.parse(createStart), StringUtils.isEmpty(createEnd) ? null
					: format.parse(createEnd), status, cashierStatus, orderStatus, rateStatus,wxAuthState,zfbAuthState,hangyeType);
			ExportExcelUtil<StorExporteVo> excelUtil = new ExportExcelUtil<StorExporteVo>();
			String[] headersName = new String[] {"注册号", "店铺名称", "联系人", "联系电话", "区域", "详细街道", "商户名称",
					"营业执照号", "银行信息", "开户人", "银行卡号", "预留手机号", "身份证号", "行业", "费率套餐", 
					"绑定渠道", "支付宝进件状态", "微信进件状态"};
			String[] headersId = new String[] {"userName", "name", "contactName", "contactTel", "area", "address", "companyName", 
					"licenseNumber",  "bankName", "cardholder","carkNum", "phone", "corpnId", "categoryName", 
					"rateName", "clerkName", "wxAuthState", "zfbAuthState"};
			format = new SimpleDateFormat("yyyy年MM月dd日HHmmss");
			excelUtil.exportExcel("店铺报表 " + format.format(new Date()), headersName, headersId, list, response);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("--店铺报表导出出现异常--",e);
		}
		return null;
	}
	
	/**
	 * 编辑店铺信息
	 * @param storeId
	 * @param rateName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/editStore")
	@ResponseBody
	public ModelAndView editStore(Long storeId) throws Exception {
		ModelAndView view = new ModelAndView("/system/store/editor_store");
		StoreInfo storeInfo = storeService.getStoreDetaile(storeId);
		SellerInfo sellerInfo = sellerAssignedService.querySellerById(storeInfo.getSellerId());
		CompanyInfo byId = sellerAssignedService.queryCompanyInfo(sellerInfo.getCompanyId());
		BankInfo bankInfo = sellerAssignedService.queryBankInfo(storeInfo.getSellerId());  
		
		view.addObject("bankInfo", bankInfo);
	    view.addObject("companyInfo", byId);
		view.addObject("storeInfo", storeInfo);
		view.addObject("sellerInfo", sellerInfo);
		return view;
	}
	
	/**
	 * 编辑保存店铺信息
	 * @param storeId
	 * @param rateName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/editStoreSave")
	@ResponseBody
	public void editStoreSave(SellerAuthenticationInfo vo, HttpServletResponse response) throws Exception {
		try {
			if (vo.getStoreId() != null) {
				StoreInfo storeInfo = storeService.getStoreDetaile(vo.getStoreId());
				SellerInfo seller = sellerAssignedService.querySellerById(storeInfo.getSellerId());
				CompanyInfo companyInfo = sellerAssignedService.queryCompanyInfo(seller.getCompanyId());
				BankInfo bankInfo = sellerAssignedService.queryBankInfo(storeInfo.getSellerId());
				storeInfo.setContactName(vo.getContactName());
				storeInfo.setContactType(vo.getContactType());
				storeInfo.setAddress(vo.getAddress());
				storeInfo.setName(vo.getStoreName());
				//storeInfo.setContactPhone(vo.getContactTel());
				//storeInfo.setLogo(vo.getLogo());
				storeInfo.setProvince(vo.getProvince());
				storeInfo.setCity(vo.getCity());
				storeInfo.setArea(vo.getArea());
				storeInfo.setStatus(0);
				companyInfo.setName(vo.getCompanyName());
				companyInfo.setCorpnName(vo.getCorpnName()); 
				companyInfo.setLicenseNumber(vo.getLicenseNumber());
				//companyInfo.setLicenseCard(vo.getLicenseCard());
				//companyInfo.setCorpnCard(vo.getCorpnCard());
				companyInfo.setCorpnId(vo.getCorpnId());
				companyInfo.setBusinessLicenseType(vo.getBusinessLicenseType());
				bankInfo.setName(vo.getBankName());
				bankInfo.setCarkNum(vo.getCarkNum());
				bankInfo.setBranch(vo.getBranch());
				bankInfo.setCardholder(vo.getCardholder());
				bankInfo.setPhone(vo.getPhone());
				storeService.updateByPrimaryKeySelective(storeInfo);
				companyInfoMapper.updateByPrimaryKeySelective(companyInfo);
				bankInfoService.updateByPrimaryKeySelective(bankInfo);
				
				seller.setPhone(vo.getContactTel());
				sellerInfoMapper.updateByPrimaryKeySelective(seller);
				HtmlUtil.writerJson(response, "success");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		HtmlUtil.writerJson(response, "error");
	}
	
	/**
	 * 店铺审批通过 保存进件信息
	 * @param storeId
	 * @param rateName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/approveSave")
	@ResponseBody
	public void approveSave(SellerAuthenticationInfo vo, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		try {
			if (vo.getStoreId() != null) {
				MsAuthentication isAuthentication = msAuthenticationService.findByStoreId(vo.getStoreId());
				StoreInfo storeInfo = storeService.getStoreDetaile(vo.getStoreId());
				Long storeId = storeInfo.getId();
				/*SellerInfo seller = sellerAssignedService.querySellerById(storeId);
				BankInfo bankInfo = sellerAssignedService.queryBankInfo(storeId);
				CompanyInfo companyInfo = sellerAssignedService.queryCompanyInfo(seller.getCompanyId());*/
				
				BankInfo bankInfo = sellerAssignedService.queryBankInfo(storeInfo.getSellerId());
				
				MsAuthentication msAuthentication = new MsAuthentication();
				msAuthentication.setSellerId(storeInfo.getSellerId());  //商户ID
				msAuthentication.setStoreId(storeId);	//店铺ID
				msAuthentication.setBankInfoId(bankInfo.getId()); //银行卡ID
				msAuthentication.setBankSqName(vo.getCompanyName()); //公司名称
				msAuthentication.setAliasName(storeInfo.getName()); //店铺名称
				msAuthentication.setBankAppId(ConfigUtils.getProperty(SystemConstant.MS_WEIXIN_APPID)); //飞券公司公众号ID
				msAuthentication.setAuthPaydir(ConfigUtils.getProperty(SystemConstant.MS_AUTH_PAYDIR));  //支付授权目录  (公众号支付必须) 
				String categoryName = storeInfo.getCategoryDetails();
				if (null != categoryName && !"".equals(categoryName)) {
					ManagerCategory managerCategory = managerCategoryService.findByName(categoryName);
					if (null != managerCategory) {
						msAuthentication.setWxCategoryId(managerCategory.getWxId()); //微信经营目录ID
						msAuthentication.setZfbCategoryId(managerCategory.getZfbId());//支付宝经营目录ID
					}
				} else {
					//默认是餐饮
					ManagerCategory managerCategoryCanYin = managerCategoryService.findByName("餐饮");
					msAuthentication.setWxCategoryId(managerCategoryCanYin.getWxId()); //微信经营目录ID
					msAuthentication.setZfbCategoryId(managerCategoryCanYin.getZfbId());//支付宝经营目录ID
				}
				msAuthentication.setServicePhone("4006333088"); //飞券客服电话
				msAuthentication.setContactName("吴嘉");  //联系人姓名
				msAuthentication.setContactPhone("13480141154"); //联系人电话
				msAuthentication.setContactMobile("13480141154");//联系人手机	
				msAuthentication.setContactEmail("109057573@qq.com");//联系人邮箱	
				//联系人类型
				if (null != storeInfo.getContactType() && !"".equals(storeInfo.getContactType())) {
					msAuthentication.setContactType(storeInfo.getContactType()); 
				} else {
					msAuthentication.setContactType("OTHER"); 
				}
				msAuthentication.setContactIdCardNo(vo.getCorpnId()); //身份证号
				ProvinceInfo provinceInfo = provinceInfoMapper.selectByPrimaryKey(storeInfo.getProvince());
				CityInfo cityInfo = cityInfoMapper.selectByPrimaryKey(storeInfo.getCity());
				AreaInfo areaInfo = areaInfoMapper.selectByPrimaryKey(storeInfo.getArea());
				
				ProviceCityAreaCode proviceCode = proviceCityAreaCodeService.findByProviceNameAndCode(provinceInfo.getName(), "1");
				if (null != proviceCode) {
					ProviceCityAreaCode cityCode = proviceCityAreaCodeService.findByProviceNameAndCode(cityInfo.getName(), proviceCode.getAreaCode());
					if (null != cityCode) {
						ProviceCityAreaCode areaCode = proviceCityAreaCodeService.findByProviceNameAndCode(areaInfo.getName(), cityCode.getAreaCode());
						if (null != areaCode) {
							msAuthentication.setProvinceCode(proviceCode.getAreaCode()); //省份
							msAuthentication.setCityCode(cityCode.getAreaCode());  //城市
							msAuthentication.setAreaCode(areaCode.getAreaCode());  //区域
						}
					}
				}
				msAuthentication.setAddressType("BUSINESS_ADDRESS"); //地址类型
				msAuthentication.setAddress(storeInfo.getAddress()); //经营地址
				msAuthentication.setBusinessLicense(vo.getLicenseNumber()); //营业执照编号
				
				//营业执照类型
				msAuthentication.setBusinessLicenseType(vo.getBusinessLicenseType()); 
				msAuthentication.setLogonId(null); //支付宝帐号   留空
				msAuthentication.setPayCodeInfo(null); //支付二维码信息	  留空
				//msAuthentication.setChnlType("WEIXIN"); //渠道类型
				msAuthentication.setWeiXinChannelId(ConfigUtils.getProperty(SystemConstant.MS_WEIXIN_CHANNEL_ID)); //统一飞券渠道号
				msAuthentication.setSubscribeAppid(ConfigUtils.getProperty(SystemConstant.MS_WEIXIN_APPID)); //统一飞券公众号
				//msAuthentication.setContactWechatid("gh_5b0e3f4d037d"); //统一飞券微信账号
				msAuthentication.setAcceptFlag("Y"); // 民生PID 默认是Y  
				//msAuthentication.setBankAcceptAppid(ConfigUtils.getProperty(SystemConstant.MS_ZFB_BANK_ACCEPT_APPID));  //受理商户来源机构标识(pid)
				msAuthentication.setCreateTime(new Date());
				
				if (null != isAuthentication) {
					msAuthentication.setId(isAuthentication.getId());
					msAuthenticationService.updateByPrimaryKeySelective(msAuthentication);
				} else {
					msAuthenticationService.insert(msAuthentication);
				}
				
				
				StoreInfo record = storeService.selectByPrimaryKey(vo.getStoreId());
				record.setStatus(2);
				int count = storeService.updateByPrimaryKeySelective(record);
				if (count > 0) {
					AdminUser admin = (AdminUser) session.getAttribute(ConstantsConfigurer.getProperty("system_key"));
					String info = BaseUtil.getLogInfo("审核商家店铺", "StoreInfo", null);
					info = info.replace("null", "");
					info += vo.getStoreId().toString() + ",";
					String IP = ServletUtils.getIpAddr(request);
					adminLogService.insertAdminLog(admin.getId(), info.substring(0, info.length() - 1), "店铺管理", IP);
					HtmlUtil.writerJson(response, "success");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		HtmlUtil.writerJson(response, "error");
	}
	
	/**
	 * 民生银行微信或支付宝进件
	 * @param storeId  店铺ID
	 * @param anthenticationType  进件渠道（微信，支付宝）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/msBankAnthentication")
	@ResponseBody
	public String msBankAnthentication(Long storeId, Integer anthenticationType, Integer bankId) throws Exception {
		//StoreInfo storeInfo = storeService.getStoreDetaile(storeId);
		try {
			StoreInfo storeInfo = storeService.getStoreDetaile(storeId);
			if (null != storeInfo) {
				MsAuthentication msAuthentication = msAuthenticationService.findByStoreId(storeInfo.getId());
				if (null != msAuthentication) {
					Long storeInfoId = storeInfo.getId();
					Long sellerId = storeInfo.getSellerId();
					if (anthenticationType == 1) { //微信进件
						AuthenticationChnnel authenticationChnnel = authenticationChnnelService.findByIsValidAuthentication(storeId, bankId, 1, 1, 2);
						if (null != authenticationChnnel) {
							return "repeat";
						}
						return wxAnthentication(msAuthentication, bankId, storeInfoId, sellerId);
					} else if (anthenticationType == 2) { //支付宝进件
						AuthenticationChnnel authenticationChnnel = authenticationChnnelService.findByIsValidAuthentication(storeId, bankId, 2, 1, 2);
						if (null != authenticationChnnel) {
							return "repeat";
						}
						return zfbAnthentication(msAuthentication, bankId, storeInfoId, sellerId);
					} else {
						AuthenticationChnnel wxAuthenticationChnnel = authenticationChnnelService.findByIsValidAuthentication(storeId, bankId, 1, 1, 2);
						AuthenticationChnnel zfbAuthenticationChnnel = authenticationChnnelService.findByIsValidAuthentication(storeId, bankId, 2, 1, 2);
						if (null != wxAuthenticationChnnel || null != zfbAuthenticationChnnel) {
							return "wxOrzfbRepeat";
						} 
						String wxMsg1 = wxAnthentication(msAuthentication, bankId, storeInfoId, sellerId);
						String zfbMsg2 = zfbAnthentication(msAuthentication, bankId, storeInfoId, sellerId);
						if ("success".equals(wxMsg1) && "success".equals(zfbMsg2)) {
							return "success";
						} else {
							return "fail";
						}
					}
				} else {
					return "anthFail";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}
	
	/**
	 * 微信进件
	 * @param msAuthentication
	 * @return
	 * @throws Exception
	 */
	public String wxAnthentication (MsAuthentication msAuthentication, Integer bankId, Long storeId, Long sellerId) throws Exception {
		try {
			List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
	        nvps.add(new BasicNameValuePair("requestNo", RequestNo.request_no));
	        nvps.add(new BasicNameValuePair("version", "V1.0"));
	        nvps.add(new BasicNameValuePair("transId", "17"));//进件
	        nvps.add(new BasicNameValuePair("merNo", ConfigUtils.getProperty(SystemConstant.MS_MCH_ID)));
	        nvps.add(new BasicNameValuePair("applyType", "BANKSP")); //报件类型 ，同报件接口
	        JSONObject jsonObject = new JSONObject(); 
	        jsonObject.put("bankSpName", msAuthentication.getBankSqName());
	        jsonObject.put("aliasName", msAuthentication.getAliasName());
	        
	        /*jsonObject.put("acceptFlag", "N");  // ?
	        jsonObject.put("bankAcceptAppid", "wx6e0532e3ad8d3b82"); // ?
*/	        
	        
	        jsonObject.put("bankAppId", msAuthentication.getBankAppId());
	        jsonObject.put("authPaydir", msAuthentication.getAuthPaydir());
	        jsonObject.put("categoryId", msAuthentication.getWxCategoryId());
	        jsonObject.put("servicePhone", msAuthentication.getServicePhone());
	        jsonObject.put("contactName", msAuthentication.getContactName());
	        jsonObject.put("contactPhone", msAuthentication.getContactPhone());
	        jsonObject.put("contactMobile", msAuthentication.getContactMobile());
	        jsonObject.put("contactEmail", msAuthentication.getContactEmail());
	        jsonObject.put("chnlType", "WEIXIN");
	        jsonObject.put("weiXinChannelId", msAuthentication.getWeiXinChannelId());
	        jsonObject.put("subscribeAppid", msAuthentication.getSubscribeAppid());
	        jsonObject.put("contactWechatid", msAuthentication.getContactWechatid());
	        
	        nvps.add(new BasicNameValuePair("applyCont", jsonObject.toString())); //微信进件 json 字符串
	        Map<String, String> itemMap = BaseRequest.getSignToSend(nvps);
	        if (null != itemMap) {
	        	String applyId = itemMap.get("applyId");//报件ID
	        	String respCode = itemMap.get("respCode");//响应码
	        	//String respDesc = itemMap.get("respDesc");//响应信息
	        	if (null != respCode && "P000".equals(respCode)) {
	        		//银行已受理，待审核
	        		AuthenticationChnnel authenticationChnnel = new AuthenticationChnnel();
	        		authenticationChnnel.setStoreId(storeId);
	        		authenticationChnnel.setBankNameId(bankId); //广州民生银行
	        		authenticationChnnel.setPayChannelId(1); //微信
	        		authenticationChnnel.setState(1); //审核中
	        		authenticationChnnel.setCreateTime(new Date());
	        		authenticationChnnel.setApplyId(applyId);
	        		authenticationChnnelService.insert(authenticationChnnel);
	        		
	        		StoreInfo storeInfo = new StoreInfo();
	        		storeInfo.setId(storeId);
	        		storeInfo.setWxAuthState(1);
	        		storeInfoMapper.updateByPrimaryKeySelective(storeInfo);
	        		return "success";
	        	}
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}
	
	/**
	 * 支付宝进件
	 * @param msAuthentication
	 * @return
	 * @throws Exception
	 */
	public String zfbAnthentication (MsAuthentication msAuthentication, Integer bankId, Long storeId, Long sellerId) throws Exception {
		try {
			BankInfo bankInfo = bankInfoMapper.selectByPrimaryKey(msAuthentication.getBankInfoId());
			List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
	        nvps.add(new BasicNameValuePair("requestNo", RequestNo.request_no));
	        nvps.add(new BasicNameValuePair("version", "V1.0"));
	        nvps.add(new BasicNameValuePair("transId", "17"));//进件
	        nvps.add(new BasicNameValuePair("merNo", ConfigUtils.getProperty(SystemConstant.MS_MCH_ID)));
	        nvps.add(new BasicNameValuePair("applyType", "BANKSP")); //报件类型 ，同报件接口
	        JSONObject jsonObject = new JSONObject(); 
	        jsonObject.put("bankSpName", msAuthentication.getBankSqName());
	        jsonObject.put("aliasName", msAuthentication.getAliasName());
	        jsonObject.put("acceptFlag", msAuthentication.getAcceptFlag());
	        jsonObject.put("bankAcceptAppid", msAuthentication.getBankAcceptAppid());
	        jsonObject.put("categoryId", msAuthentication.getZfbCategoryId());
	        jsonObject.put("servicePhone", msAuthentication.getServicePhone());
	        jsonObject.put("contactName", msAuthentication.getContactName());
	        jsonObject.put("contactPhone", msAuthentication.getContactPhone());
	        jsonObject.put("contactMobile", msAuthentication.getContactMobile());
	        jsonObject.put("contactEmail", msAuthentication.getContactEmail());
	        jsonObject.put("contactType", msAuthentication.getContactType());
	        jsonObject.put("contactIdCardNo", "ID"+msAuthentication.getContactIdCardNo());
	        jsonObject.put("provinceCode", msAuthentication.getProvinceCode());
	        jsonObject.put("cityCode", msAuthentication.getCityCode());
	        jsonObject.put("districtCode", msAuthentication.getAreaCode());
	        jsonObject.put("addressType", msAuthentication.getAddressType());
	        jsonObject.put("address", msAuthentication.getAddress());
	        String businessLicense = msAuthentication.getBusinessLicense();
	        String businessLicenseType = msAuthentication.getBusinessLicenseType();
	        if (null != businessLicense && !"".equals(businessLicense)) {
	        	jsonObject.put("businessLicense", businessLicense);
	        	if (null != businessLicenseType && !"".equals(businessLicenseType)) {
	        		jsonObject.put("businessLicenseType", businessLicenseType);
	        	} else {
	        		jsonObject.put("businessLicenseType", "NATIONAL_LEGAL_MERGE");
	        	}
	        }
	        jsonObject.put("cardNo", "ID"+bankInfo.getCarkNum());
	        jsonObject.put("cardName", bankInfo.getCardholder());
	        jsonObject.put("chnlType", "ALIPAY");
	        //jsonObject.put("logonId", msAuthentication.getContactWechatid());   //支付宝账号
	        //jsonObject.put("payCodeInfo", msAuthentication.getContactWechatid()); //支付二维码信息
	        
	        nvps.add(new BasicNameValuePair("applyCont", jsonObject.toString())); //支付宝进件 json 字符串
	        Map<String, String> itemMap = BaseRequest.getSignToSend(nvps);
	        if (null != itemMap) {
	        	String applyId = itemMap.get("applyId");//报件ID
	        	String respCode = itemMap.get("respCode");//响应码
	        	//String respDesc = itemMap.get("respDesc");//响应信息
	        	if (null != respCode && "P000".equals(respCode)) {
	        		//银行已受理，待审核
	        		AuthenticationChnnel authenticationChnnel = new AuthenticationChnnel();
	        		authenticationChnnel.setStoreId(storeId);
	        		authenticationChnnel.setBankNameId(bankId);  //广州民生银行
	        		authenticationChnnel.setPayChannelId(2); //支付宝
	        		authenticationChnnel.setState(1); //审核中
	        		authenticationChnnel.setCreateTime(new Date());
	        		authenticationChnnel.setApplyId(applyId);
	        		authenticationChnnelService.insert(authenticationChnnel);
	        		
	        		StoreInfo storeInfo = new StoreInfo();
	        		storeInfo.setId(storeId);
	        		storeInfo.setZfbAuthState(1);
	        		storeInfoMapper.updateByPrimaryKeySelective(storeInfo);
	        		return "success";
	        	}
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}
	
	/**
	 * 支付宝批量进件处理
	 */
	@RequestMapping("/allZfbAuthcation")
	@ResponseBody
	public void zfbTaskAuthencation() {
		try {
			List<MsAuthentication> msAuthenticationList = msAuthenticationService.listByAll();
			if (null != msAuthenticationList && msAuthenticationList.size() > 0) {
				for (MsAuthentication msAuthentication : msAuthenticationList) {
					BankInfo bankInfo = bankInfoMapper.selectByPrimaryKey(msAuthentication.getBankInfoId());
					List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
			        nvps.add(new BasicNameValuePair("requestNo", RequestNo.request_no));
			        nvps.add(new BasicNameValuePair("version", "V1.0"));
			        nvps.add(new BasicNameValuePair("transId", "17"));//进件
			        nvps.add(new BasicNameValuePair("merNo", ConfigUtils.getProperty(SystemConstant.MS_MCH_ID)));
			        nvps.add(new BasicNameValuePair("applyType", "BANKSP")); //报件类型 ，同报件接口
			        JSONObject jsonObject = new JSONObject(); 
			        jsonObject.put("bankSpName", msAuthentication.getBankSqName());
			        jsonObject.put("aliasName", msAuthentication.getAliasName());
			        jsonObject.put("acceptFlag", msAuthentication.getAcceptFlag());
			        jsonObject.put("bankAcceptAppid", msAuthentication.getBankAcceptAppid());
			        jsonObject.put("categoryId", msAuthentication.getZfbCategoryId());
			        jsonObject.put("servicePhone", msAuthentication.getServicePhone());
			        jsonObject.put("contactName", msAuthentication.getContactName());
			        jsonObject.put("contactPhone", msAuthentication.getContactPhone());
			        jsonObject.put("contactMobile", msAuthentication.getContactMobile());
			        jsonObject.put("contactEmail", msAuthentication.getContactEmail());
			        jsonObject.put("contactType", msAuthentication.getContactType());
			        jsonObject.put("contactIdCardNo", "ID"+msAuthentication.getContactIdCardNo());
			        jsonObject.put("provinceCode", msAuthentication.getProvinceCode());
			        jsonObject.put("cityCode", msAuthentication.getCityCode());
			        jsonObject.put("districtCode", msAuthentication.getAreaCode());
			        jsonObject.put("addressType", msAuthentication.getAddressType());
			        jsonObject.put("address", msAuthentication.getAddress());
			        String businessLicense = msAuthentication.getBusinessLicense();
			        String businessLicenseType = msAuthentication.getBusinessLicenseType();
			        if (null != businessLicense && !"".equals(businessLicense)) {
			        	jsonObject.put("businessLicense", businessLicense);
			        	if (null != businessLicenseType && !"".equals(businessLicenseType)) {
			        		jsonObject.put("businessLicenseType", businessLicenseType);
			        	} else {
			        		jsonObject.put("businessLicenseType", "NATIONAL_LEGAL_MERGE");
			        	}
			        }
			        jsonObject.put("cardNo", "ID"+bankInfo.getCarkNum());
			        jsonObject.put("cardName", bankInfo.getCardholder());
			        jsonObject.put("chnlType", "ALIPAY");
			        
			        nvps.add(new BasicNameValuePair("applyCont", jsonObject.toString())); //支付宝进件 json 字符串
			        Map<String, String> itemMap = BaseRequest.getSignToSend(nvps);
			        if (null != itemMap) {
			        	String applyId = itemMap.get("applyId");//报件ID
			        	String respCode = itemMap.get("respCode");//响应码
			        	//String respDesc = itemMap.get("respDesc");//响应信息
			        	if (null != respCode && "P000".equals(respCode)) {
			        		//银行已受理，待审核
			        		AuthenticationChnnel authenticationChnnel = new AuthenticationChnnel();
			        		authenticationChnnel.setStoreId(msAuthentication.getStoreId());
			        		authenticationChnnel.setBankNameId(1);  //广州民生银行
			        		authenticationChnnel.setPayChannelId(2); //支付宝
			        		authenticationChnnel.setState(1); //审核中
			        		authenticationChnnel.setCreateTime(new Date());
			        		authenticationChnnel.setApplyId(applyId);
			        		authenticationChnnelService.insert(authenticationChnnel);
			        		
			        		StoreInfo storeInfo = new StoreInfo();
			        		storeInfo.setId(msAuthentication.getStoreId());
			        		storeInfo.setZfbAuthState(1);
			        		storeInfoMapper.updateByPrimaryKeySelective(storeInfo);
			        		//return "success";
			        	}
			        }
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取某一店铺已有的支付渠道
	 * @param storeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getStoreIdPayChnnel")
	@ResponseBody
	public String getStoreIdPayChnnel(Long storeId) throws Exception {
		List<PayChnnelVo> payChnnelVoList = new ArrayList<PayChnnelVo>();
		PayChnnelVo payChnnelVo = new PayChnnelVo();
		payChnnelVo.setChannelId(0);
		payChnnelVo.setChannelName("原生通道");
		payChnnelVoList.add(payChnnelVo);
		Integer count = 0;
		List<AuthenticationChnnel> list = authenticationChnnelService.findByStoreId(storeId);
		StoreInfo storeInfo = storeInfoMapper.selectByPrimaryKey(storeId);
		if (null != list && list.size() > 0) {
			for (AuthenticationChnnel payChnnel : list) {
				if (payChnnel.getBankNameId() == 1) {
					count += 1;
				} else if (payChnnel.getBankNameId() == 2) {
					PayChnnelVo payChnnelVo1 = new PayChnnelVo();
					payChnnelVo1.setChannelId(2);
					payChnnelVo1.setChannelName("深圳兴业");
					if (storeInfo.getChannelValidation() == 2) {
						payChnnelVo1.setTarget("true");
					}
					payChnnelVoList.add(payChnnelVo1);
				}
			}
			if (count == 2) {
				PayChnnelVo payChnnelVo2 = new PayChnnelVo();
				payChnnelVo2.setChannelId(1);
				payChnnelVo2.setChannelName("广州民生");
				if (storeInfo.getChannelValidation() == 1) {
					payChnnelVo2.setTarget("true");
				}
				payChnnelVoList.add(payChnnelVo2);
			}
		}
		return JsonUtil.toJson(payChnnelVoList);
	}
	
	@RequestMapping("/setPayTongDao")
	@ResponseBody
	public Object setStoreIdPayChnnel(Long[] ids, Integer tongdaoVal) throws Exception {
		try {
			//------------------信息变量声明--开始----------------
			int count = 0;//总执行数
			int success_count = 0;//处理成功的笔数
			int error_count = 0;//处理失败的笔数
			List<Map<String,Object>> msgListMap = new ArrayList<Map<String,Object>>();//异常信息定义
			Map<String,Object> msgMap = new HashMap<String,Object>();
			String msError = "msError";//银行异常信息，Map对应的Key值
			String storeId = "storeId";//记录哪一条数据出现异常
			//------------------信息变量声明--结束----------------
			
			List<Long> strlist = new ArrayList<>();
			for (Long strings : ids) {
				if (null != strings) {
					count++;//记录总执行数
					strlist.add(strings);
				}
			}
			if (tongdaoVal == 1) { // 切换广州民生支付通道
				for (int i = 0; i < strlist.size(); i++) {
					msgMap = new HashMap<String,Object>();
					msgMap.put(storeId, strlist.get(i));//店铺ID，用来记录响应的异常信息
					
					List<AuthenticationChnnel> list = authenticationChnnelService.findByStoreIdAndCountIsSuccess(strlist.get(i), 1, 2);
					if (null == list || list.size() < 2) { //民生进件没有成功
						msgMap.put(msError, "民生进件没有成功");//保存系统异常信息
						error_count++;//记录失败次数
						msgListMap.add(msgMap);//添加异常信息
					} else {
						StoreInfo storeInfo = new StoreInfo();
						storeInfo.setId(strlist.get(i));
						storeInfo.setChannelValidation(tongdaoVal);
						int result = storeService.updateByPrimaryKeySelective(storeInfo);
						success_count++; //记录执行成功的交易笔数
		        		msgMap.put(msError, "执行成功");//保存系统返回的异常信息
					}
				}
			} else if (tongdaoVal == 2) { //切换深圳兴业一清支付通道
				for (int i = 0; i < strlist.size(); i++) {
					msgMap = new HashMap<String,Object>();
					msgMap.put(storeId, strlist.get(i));//店铺ID，用来记录响应的异常信息
					
					AuthenticationChnnel authenticationChnnel = authenticationChnnelService.findByXyyqAuthcationInfo(strlist.get(i), 2, 2);
					if (null != authenticationChnnel) {
						StoreInfo storeInfo = new StoreInfo();
						storeInfo.setId(strlist.get(i));
						storeInfo.setChannelValidation(tongdaoVal);
						int result = storeService.updateByPrimaryKeySelective(storeInfo);
						success_count++; //记录执行成功的交易笔数
		        		msgMap.put(msError, "执行成功");//保存系统返回的异常信息
					} else {
						msgMap.put(msError, "深圳兴业没有进件");//保存系统异常信息
						error_count++;//记录失败次数
						msgListMap.add(msgMap);//添加异常信息
					}
				}
				
			} else {     //切换原生通道和 深圳兴业二清支付通道
				for (int i = 0; i < strlist.size(); i++) {
					msgMap = new HashMap<String,Object>();
					msgMap.put(storeId, strlist.get(i));//店铺ID，用来记录响应的异常信息
					
					StoreInfo storeInfo = new StoreInfo();
					storeInfo.setId(strlist.get(i));
					storeInfo.setChannelValidation(tongdaoVal);
					int result = storeService.updateByPrimaryKeySelective(storeInfo);
					
					success_count++; //记录执行成功的交易笔数
	        		msgMap.put(msError, "执行成功");//保存系统返回的异常信息
				}
			}
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("sumCount", count);//总记录数
			map.put("successCount", success_count);//成功的记录数
			map.put("errorCount", error_count);//失败的记录数
			map.put("textInfo", msgListMap);//异常信息
			return JSON.toJSON(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/setStoreIdPayChnnel")
	@ResponseBody
	public String setStoreIdPayChnnel(Long storeId, Integer channelValidation) throws Exception{
		StoreInfo storeInfo = new StoreInfo();
		storeInfo.setId(storeId);
		storeInfo.setChannelValidation(channelValidation);
		int result = storeService.updateByPrimaryKeySelective(storeInfo);
		if(result > 0){
			return "success";
		}
		return "fail";
	}
	
	@RequestMapping("/setXyEditAucation")
	@ResponseBody
	public String setXyEditAucation(Long storeId, String xyNumber, String xyKey) throws Exception {
		AuthenticationChnnel authenticationChnnel = authenticationChnnelService.findByXyAuthcationInfo(storeId);
		if (null != authenticationChnnel) {
			authenticationChnnel.setXyBankKey(xyKey);
			authenticationChnnel.setXyMerchantNum(xyNumber);
			int result = authenticationChnnelService.updateByPrimaryKeySelective(authenticationChnnel);
			if (result > 0) {
				return "success";
			}
		} else {
			AuthenticationChnnel newAuthcation = new AuthenticationChnnel();
			newAuthcation.setStoreId(storeId);
			newAuthcation.setBankNameId(2);
			newAuthcation.setPayChannelId(3);
			newAuthcation.setState(2);
			newAuthcation.setCreateTime(new Date());
			newAuthcation.setXyBankKey(xyKey);
			newAuthcation.setXyMerchantNum(xyNumber);
			authenticationChnnelService.insert(newAuthcation);
			return "success";
		}
		return "fail";
	}
	
	@RequestMapping("/getStoreIdXyAuthcation")
	@ResponseBody
	public String getStoreIdXyAuthcation(Long storeId) throws Exception {
		/*String id = ids.toString();
		Long storeId = Long.parseLong(id);*/
		AuthenticationChnnel authenticationChnnel = authenticationChnnelService.findByXyAuthcationInfo(storeId);
		if (null == authenticationChnnel) {
			authenticationChnnel = new AuthenticationChnnel();
			authenticationChnnel.setStoreId(storeId);
		}
		return JsonUtil.toJson(authenticationChnnel);
	}
	
	/**
	 * 设置店铺提现费率
	 * @param storeId
	 * @param rateName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/setStoreRate")
	@ResponseBody
	public String setStoreRate(Long storeId, Long rateId,Integer period) throws Exception{
			int result = storeService.updateStoreInfoRateId(storeId, rateId,period);
			if(result > 0){
				return "success";
			}
		return "fail";
	}
	@RequestMapping("/setStoreCategory")
	@ResponseBody
	public String setStoreCategory(Long storeId, Long categoryId,String categoryDetail) throws Exception{
		int result = storeService.updateStoreInfoCategory(storeId, categoryId,categoryDetail);
		if(result > 0){
			return "success";
		}
		if (result == 0) {
			return "nochange";
		}
		return "fail";
	}
	
	@ResponseBody
	@RequestMapping("/updateStoreStauts")
	public void updateStauts(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "ids", required = true) Long[] ids,
			@RequestParam(value = "status", required = true) Integer staus) throws Exception {
		// 1L 系统用户
		List<Long> strlist = new ArrayList<>();
		for (Long strings : ids)
			
			if (null != strings)
				strlist.add(strings);
		Boolean bln = storeService.updateStoreStatusBySystem(1L, strlist, staus);
		if (bln) {
			AdminUser admin = (AdminUser) session.getAttribute(ConstantsConfigurer.getProperty("system_key"));
			String info = BaseUtil.getLogInfo("批量审核商家店铺", "StoreInfo", null);
			info = info.replace("null", "");
			for (int i = 0; i < ids.length; i++)
				info += ids[i].toString() + ",";
			String IP = ServletUtils.getIpAddr(request);
			adminLogService.insertAdminLog(admin.getId(), info.substring(0, info.length() - 1), "店铺管理", IP);
			HtmlUtil.writerJson(response, "success");
		} else
			HtmlUtil.writerJson(response, "error");
	}
	@ResponseBody
	@RequestMapping("/updateScope")
	public void updateScope(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "ids", required = true) Long[] ids,
			@RequestParam(value = "scope", required = true) Integer scope) throws Exception {
		List<Long> strlist = new ArrayList<>();
		for (Long strings : ids)
			if (null != strings)
				strlist.add(strings);
		Boolean bln = storeService.updateScope(strlist, scope);
		if (bln) {
			HtmlUtil.writerJson(response, "success");
		} else
			HtmlUtil.writerJson(response, "error");
	}
	
	@ResponseBody
	@RequestMapping("/updateCashierStatus")
	public void updateCashierStauts(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "ids", required = true) Long[] ids,
			@RequestParam(value = "cashierStatus", required = true) Integer cashierStatus) throws Exception {
		List<Long> strlist = new ArrayList<>();
		for (Long strings : ids){
			if (null != strings){
				strlist.add(strings);
			}
		}
			
		Boolean bln = storeService.updateCashierStatus(strlist, cashierStatus);
		if (bln) {
			HtmlUtil.writerJson(response, "success");
		} else
			HtmlUtil.writerJson(response, "error");
	}
	
	@ResponseBody
	@RequestMapping("/updateOrderStatus")
	public void updateOrderStauts(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "ids", required = true) Long[] ids,
			@RequestParam(value = "orderStatus", required = true) Integer orderStatus,
			@RequestParam(value = "judge", required = true) Integer judge) throws Exception {
		List<Long> strlist = new ArrayList<>();
		for (Long strings : ids){
			if (null != strings){
				strlist.add(strings);
			}
		}
		Boolean bln = storeService.updateOrderStatus(strlist, orderStatus, judge);
		if (bln) {
			HtmlUtil.writerJson(response, "success");
		} else
			HtmlUtil.writerJson(response, "error");
	}
	
	@RequestMapping("/checkStoreName")
	@ResponseBody
	public String checkStoreName(Long storeId,String storeName){
		StoreInfo store = sellerAssignedService.queryStoreByName(storeId, storeName);
		if (store != null) {
			return "already";
		}
		return "noproblem";
	}

	/**
	 * 去店铺详情
	 * 
	 * @param id
	 *            店铺ID
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getStoreDetail")
	public ModelAndView toStoreDetail(@RequestParam(value = "id", required = true) Long id) throws Exception {
		ModelAndView view = new ModelAndView("/system/store/store_detail");
		StoreInfo storeInfo = storeService.getStoreDetaile(id);
		SellerInfo sellerInfo = sellerAssignedService.querySellerById(storeInfo.getSellerId());
		CompanyInfo byId = sellerAssignedService.queryCompanyInfo(sellerInfo.getCompanyId());
		BankInfo bankInfo = sellerAssignedService.queryBankInfo(storeInfo.getSellerId());  
		 
		ProvinceInfo provinceInfo = provinceInfoMapper.selectByPrimaryKey(storeInfo.getProvince());
		CityInfo cityInfo = cityInfoMapper.selectByPrimaryKey(storeInfo.getCity());
		AreaInfo areaInfo = areaInfoMapper.selectByPrimaryKey(storeInfo.getArea());
		
		view.addObject("bankInfo", bankInfo);
	    view.addObject("companyInfo", byId);
		view.addObject("storeInfo", storeInfo);
		view.addObject("provinceName", provinceInfo.getName());
		view.addObject("cityName", cityInfo.getName());
		view.addObject("areaName", areaInfo.getName());
		view.addObject("sellerInfo", sellerInfo);
		return view;
	}

	
	@RequestMapping("/storeAccountList")
	public ModelAndView storeAccountList(@RequestParam(value = "account", required = false) String account,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws ParseException, Exception {
		ModelAndView view = new ModelAndView("/system/store/store_account_list");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (null == pageNum || pageNum < 1)
			pageNum = 1;
		pageSize = 10;
		Page<SellerInfo> page = sellerService.selectSllerInfoBySystem(account, phone,
				StringUtils.isEmpty(startDate) ? null : format.parse(startDate), StringUtils.isEmpty(endDate) ? null
						: format.parse(endDate), pageNum, pageSize);
		view.addObject("page", page);
		view.addObject("account", account);
		view.addObject("phone", phone);
		view.addObject("startDate", startDate);
		view.addObject("endDate", endDate);
		view.addObject("pageNum", pageNum);
		return view;
	}

	/**
	 * 修改商家的 启用状态
	 * 
	 * @param ids
	 *            集合ID
	 * @param staus
	 *            状态 0 关闭 1 开启
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/updateSellerEnabled")
	public void updateSellerEnabled(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "ids", required = true) Long[] ids,
			@RequestParam(value = "status", required = true) Integer staus) throws Exception {
		List<Long> strlist = new ArrayList<>();
		for (Long strings : ids)
			if (null != strings)
				strlist.add(strings);
		Boolean bln = sellerService.updateSellerEnabledBySystem(1L, strlist, staus);
		if (bln) {
			AdminUser admin = (AdminUser) session.getAttribute(ConstantsConfigurer.getProperty("system_key"));
			String info = BaseUtil.getLogInfo(staus == 0 ? "禁用" : "启用" + "商家店铺", "SellerInfo", null);
			info = info.replace("null", "");
			for (int i = 0; i < ids.length; i++)
				info += ids[i].toString() + ",";
			String IP = ServletUtils.getIpAddr(request);
			adminLogService.insertAdminLog(admin.getId(), info.substring(0, info.length() - 1), "店铺管理", IP);
			HtmlUtil.writerJson(response, "success");
		} else
			HtmlUtil.writerJson(response, "error");
	}

	/**
	 * 商家重置密码 密码:363144039
	 * 
	 * @param response
	 * @param id
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/updateResetSellerPassWord")
	public void updateResetSellerPassWord(HttpSession session, HttpServletRequest request,
			HttpServletResponse response, @RequestParam(value = "id", required = true) Long id) throws Exception {
		SellerInfo seller = sellerService.getSellerById(id);
		// 随机六位数
		String password = String.valueOf(Math.round(Math.random() * 1000000));
		seller.setPassword(EncodeMD5.GetMD5Code(password));
		Boolean bln = sellerService.updateSellerById(seller);
		if (bln) {
			// 发送短信告知
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("username", seller.getUsername());
			map.put("code", password);
			MessageUtil.SendVerification(seller.getPhone(), map, MessageUtil.BACKSELLERPASSWORDPHONE);
			HtmlUtil.writerJson(response, "success");
			AdminUser admin = (AdminUser) session.getAttribute(ConstantsConfigurer.getProperty("system_key"));
			String info = BaseUtil.getLogInfo("重置商家密码", "SellerInfo", id);
			String IP = ServletUtils.getIpAddr(request);
			adminLogService.insertAdminLog(admin.getId(), info, "店铺管理", IP);
		} else
			HtmlUtil.writerJson(response, "error");
	}

	/***
	 * 店铺修改审核列表
	 * 
	 * @param storeName
	 *            店铺名称
	 * @param pageNum
	 *            分页参数
	 * @param pageSize
	 *            分页参数
	 * @return
	 * @throws ParseException
	 * @throws Exception
	 */
	@RequestMapping("/storeCheckLsit")
	public ModelAndView list(@RequestParam(value = "storeName", required = false) String storeName,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("/system/store/store_check_list");
		if (null == storeName || StringUtils.isEmpty(storeName))
			storeName = null;
		if (null == pageNum || pageNum < 1)
			pageNum = 1;
		pageSize = 10;
		Page<StoreCheck> page = storeService.querySystemManageStoreCheckByPage(storeName, pageNum, pageSize);
		view.addObject("page", page);
		view.addObject("storeName", storeName);
		view.addObject("pageNum", pageNum);
		return view;
	}

	@ResponseBody
	@RequestMapping("/updateStoreCheckStauts")
	public void updateStoreCheckStauts(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "ids", required = true) Long[] ids,
			@RequestParam(value = "status", required = true) Integer staus) throws Exception {
		List<Long> strlist = new ArrayList<>();
		for (Long strings : ids)
			if (null != strings)
				strlist.add(strings);
		AdminUser admin = (AdminUser) session.getAttribute(ConstantsConfigurer.getProperty("system_key"));
		Boolean bln = storeService.updateStoreCheckStatusBySystem(admin.getId(), strlist, staus);
		if (bln) {
			String info = BaseUtil.getLogInfo("审核商家店铺", "StoreInfo,StoreCheck", null);
			info = info.replace("null", "");
			for (int i = 0; i < ids.length; i++)
				info += ids[i].toString() + ",";
			String IP = ServletUtils.getIpAddr(request);
			adminLogService.insertAdminLog(admin.getId(), info.substring(0, info.length() - 1), "店铺管理", IP);
			HtmlUtil.writerJson(response, "success");
		} else
			HtmlUtil.writerJson(response, "error");
	}
	
	@RequestMapping("/queryFqStoreApply")
	public ModelAndView queryFqStoreApply(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			Long sellerId, Integer pageNum, Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("/system/store/store_apply_list");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if(null != seller){
			sellerId = seller.getId();			
		}
		Page<FqStoreApply> fqStoreApply = storeService.queryFqStoreApply(sellerId,pageNum,pageSize);
		view.addObject("page", fqStoreApply);
		return view;
	}
	
	/**
	 * 修改绑定信息
	 * @param storeId
	 * @param provinceId
	 * @param cityId
	 * @param clerkId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/storeInfoBinding")
	public String storeInfoBinding(Long storeId, Long provinceId, Long cityId, Long clerkId) throws Exception {
		int result = storeService.updateStoreInfoBinding(storeId, provinceId, cityId, clerkId);
		if(result > 0){
			return "success";
		}
		return "fail";
	}
	
	/**
	 * 获取绑定信息
	 * @param storeId
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getClerkAndStore")
	public FqClerkVo getClerkAndStore(Long storeId) throws Exception {
		return storeService.getClerkAndStore(storeId);
	}
}