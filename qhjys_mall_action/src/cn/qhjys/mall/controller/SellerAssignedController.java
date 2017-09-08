package cn.qhjys.mall.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.BankInfo;
import cn.qhjys.mall.entity.CategoryInfo;
import cn.qhjys.mall.entity.CompanyInfo;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.StoreAudit;
import cn.qhjys.mall.entity.StoreCheck;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.service.SellerAssignedService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.util.SessionUtil;
import cn.qhjys.mall.vo.CompanyInfoVo;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/managermall/seller")
public class SellerAssignedController extends Base {

	@Autowired
	private SellerAssignedService sellerAssignedService;

	/***
	 * 进入注册卖家信息协议页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/serviceAgreement", method = RequestMethod.GET)
	public ModelAndView saveSellerInfo(HttpSession session,Integer step) throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		ModelAndView view = new ModelAndView();
		if(step == null){
			step=0;}
		if (seller == null) {
			view.setViewName("redirect:/seller/login.do");
		} else {
			SellerInfo sell = sellerAssignedService.querySellerById(seller.getId());
			if (sell.getStep() == 6 && step == 2) {
				sell.setStep(2);
				SellerInfo sellerInfo = new SellerInfo();
				Long sellerId = seller.getId();
				sellerInfo.setId(sellerId);
				sellerInfo.setStep(2);
				sellerAssignedService.updateSellerInfo(sellerInfo);
				seller = sellerAssignedService.querySellerById(sellerId);
				session.setAttribute(ConstantsConfigurer.getSeller(), seller);
			}
			if (sell.getStep() == 1) {
				view.setViewName("seller/jysmall_step1");
			} else if (sell.getStep() == 2) {
				view.setViewName("seller/jysmall_step2");
				view.addObject("seller", sell);
			} else if (sell.getStep() == 3) {
				view.setViewName("seller/jysmall_step3");
				if (sell.getCompanyId() != null) {
					view.addObject("companyInfo", sellerAssignedService.queryCompanyInfo(sell.getCompanyId()));
				}
			} else if (sell.getStep() == 4) {
				view.setViewName("seller/jysmall_step5");
				view.addObject("storyInfo",sellerAssignedService.queryStoreBySellerId(seller.getId()));
				view.addObject("bankInfo",sellerAssignedService.queryBankInfo(seller.getId()));
				String token = UUID.randomUUID().toString();
				view.addObject("addStoreInfotoken",token);
				session.setAttribute("addStoreInfotoken", token);
			} else if (sell.getStep() == 5) {
				view.setViewName("seller/jysmall_step6");
				view.addObject("storyInfo",sellerAssignedService.queryStoreBySellerId(seller.getId()));
			} else if (sell.getStep() == 6) {
				view.setViewName("redirect:/managermall/seller/addBankInfo.do");
			}
		}
		return view;
	}

	/**
	 * 进入完善卖家信息页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveSellerLinkman", method = RequestMethod.GET)
	public ModelAndView serviceAgreement(HttpSession session) throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		ModelAndView view = new ModelAndView("seller/jysmall_step2");
		if (seller == null) {
			view.setViewName("redirect:/seller/login.do");
		} else {
			SellerInfo sellerInfo = new SellerInfo();
			sellerInfo.setId(seller.getId());
			sellerInfo.setStep(2);
			sellerAssignedService.updateSellerById(sellerInfo);
			session.setAttribute(ConstantsConfigurer.getSeller(), seller);
			view.addObject("seller", seller);
		}
		return view;
	}

	@RequestMapping(value = "/updateSeller", method = RequestMethod.POST)
	public void updateSellerInfo(SellerInfo sellerInfo, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			response.sendRedirect("/seller/login.do");
		} else {
			Long sellerId = seller.getId();
			sellerInfo.setId(sellerId);
			sellerInfo.setStep(3);
			sellerAssignedService.updateSellerInfo(sellerInfo);
			seller = sellerAssignedService.querySellerById(sellerId);
			session.setAttribute(ConstantsConfigurer.getSeller(), seller);
		}
	}

	@RequestMapping(value = "/addCompany", method = RequestMethod.GET)
	public ModelAndView updateSellerInfo(HttpSession session) throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		ModelAndView view = new ModelAndView("seller/jysmall_step3");
		if (seller == null)
			view.setViewName("redirect:/seller/login.do");
		if (seller.getCompanyId() != null) {
			view.addObject("companyInfo", sellerAssignedService.queryCompanyInfo(seller.getCompanyId()));
		}
		return view;
	}

	@RequestMapping(value = "/saveCompany", method = RequestMethod.POST)
	public void saveCompanyInfo(CompanyInfoVo companyInfoVo, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		JSONObject json = new JSONObject();
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			response.sendRedirect("/seller/login.do");
		} else {
			if(companyInfoVo.getCompanyId() != null){
				CompanyInfo companyInfo = new CompanyInfo();
				companyInfo.setId(companyInfoVo.getCompanyId());
				companyInfo.setName(companyInfoVo.getName());
				companyInfo.setLicenseNumber(companyInfoVo.getLicenseNumber());
				companyInfo.setCorpnName(companyInfoVo.getCorpnName());
				companyInfo.setCorpnId(companyInfoVo.getCorpnId());
				companyInfo.setCorpnCard(companyInfoVo.getCorpnCard());
				companyInfo.setLicenseProvince(companyInfoVo.getLicenseProvince());
				companyInfo.setLicenseCity(companyInfoVo.getLicenseCity());
				companyInfo.setLicenseArea(companyInfoVo.getLicenseArea());
				companyInfo.setLicenseAddress(companyInfoVo.getLicenseAddress());
				String setUpDate = companyInfoVo.getSetUpDate();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date = simpleDateFormat.parse(setUpDate);
				companyInfo.setSetUpDate(date);
				companyInfo.setCapital(companyInfoVo.getCapital());
				companyInfo.setScope(companyInfoVo.getScope());
				companyInfo.setLicenseCard(companyInfoVo.getLicenseCard());
				companyInfo.setOrganizationCode(companyInfoVo.getOrganizationCode()); 
				companyInfo.setOrganizationImage(companyInfoVo.getOrganizationImage());
				sellerAssignedService.updateCompanyInfo(companyInfo);
				request.getSession().setAttribute("companyId", companyInfo.getId());
				SellerInfo se = new SellerInfo();
				se.setId(seller.getId());
				se.setStep(4);
				sellerAssignedService.updateSellerById(se);
				seller = sellerAssignedService.querySellerById(seller.getId());
				session.setAttribute(ConstantsConfigurer.getSeller(), seller);
				json.put("message", "成功");
			}else{
			//boolean b = sellerAssignedService.validateCompanyByLicenseNumber(companyInfoVo.getLicenseNumber());
				boolean b = true;
				if (b == true) {
				/*boolean bo = sellerAssignedService.validateCompanyByorganizationCode(companyInfoVo
						.getOrganizationCode());*/
				boolean bo =true;//组织机构代码证
				if (bo == true) {
					SellerInfo sellerInfo = new SellerInfo();
					CompanyInfo companyInfo = new CompanyInfo();
					companyInfo.setName(companyInfoVo.getName());
					companyInfo.setLicenseNumber(companyInfoVo.getLicenseNumber());
					companyInfo.setCorpnName(companyInfoVo.getCorpnName());
					companyInfo.setCorpnId(companyInfoVo.getCorpnId());
					companyInfo.setCorpnCard(companyInfoVo.getCorpnCard());
					companyInfo.setLicenseProvince(companyInfoVo.getLicenseProvince());
					companyInfo.setLicenseCity(companyInfoVo.getLicenseCity());
					companyInfo.setLicenseArea(companyInfoVo.getLicenseArea());
					companyInfo.setLicenseAddress(companyInfoVo.getLicenseAddress());
					String setUpDate = companyInfoVo.getSetUpDate();
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date date = null;
					if(StringUtils.isEmpty(setUpDate)){
						 date = new Date();
					}else{
						 date = simpleDateFormat.parse(setUpDate);
					}
					companyInfo.setSetUpDate(date);
					companyInfo.setCapital(companyInfoVo.getCapital());
					companyInfo.setScope(companyInfoVo.getScope());
					companyInfo.setLicenseCard(companyInfoVo.getLicenseCard());
					companyInfo.setOrganizationCode(companyInfoVo.getOrganizationCode());
					companyInfo.setOrganizationImage(companyInfoVo.getOrganizationImage());
					Long sellerId = seller.getId();
					sellerInfo.setId(sellerId);
					sellerAssignedService.saveCompanyInfo(companyInfo);
					sellerInfo.setCompanyId(companyInfo.getId());
					sellerAssignedService.updateSellerInfo(sellerInfo);
					request.getSession().setAttribute("companyId", companyInfo.getId());
					SellerInfo se = new SellerInfo();
					se.setId(seller.getId());
					se.setStep(4);
					sellerAssignedService.updateSellerById(se);
					seller = sellerAssignedService.querySellerById(sellerId);
					session.setAttribute(ConstantsConfigurer.getSeller(), seller);
					json.put("message", "成功");
				} else {
					json.put("message", "组织机构代码证已存在");
				}
			} else {
				json.put("message", "营业执照编号已存在");
			}
			}
		}
		HtmlUtil.writerJson(response, json);

	}

	@RequestMapping(value = "/addBankInfo", method = RequestMethod.GET)
	public ModelAndView saveCompanyInfo(HttpSession session) throws Exception {
		SellerInfo SellerInfo = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		ModelAndView view = new ModelAndView("seller/jysmall_step4");
		if (SellerInfo == null) {
			view.setViewName("redirect:/seller/login.do");
		} else {
			StoreInfo store = sellerAssignedService.queryStoreBySellerId(SellerInfo.getId());
			StoreAudit storeAudit = sellerAssignedService.queryStoreAuditById(store.getId());
			view.addObject("store", store);
			view.addObject("storeAudit", storeAudit);
		}
		return view;
	}

	@RequestMapping(value = "/saveBankInfo", method = RequestMethod.POST)
	public void saveBankInfo(BankInfo bankInfo, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) throws Exception {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			response.sendRedirect("/seller/login.do");
		} else {
			sellerAssignedService.saveBankInfo(bankInfo);
			request.getSession().setAttribute("bankId", bankInfo.getId());
		}
	}

	@RequestMapping(value = "/addStoreInfo", method = RequestMethod.GET)
	public ModelAndView saveBankInfo(HttpSession session) throws Exception {
		SellerInfo SellerInfo = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		ModelAndView view = new ModelAndView("seller/jysmall_step5");
		if (SellerInfo == null)
			view.setViewName("redirect:/seller/login.do");
		view.addObject("storyInfo",sellerAssignedService.queryStoreBySellerId(SellerInfo.getId()));
		view.addObject("bankInfo",sellerAssignedService.queryBankInfo(SellerInfo.getId()));
		String token = UUID.randomUUID().toString();
		view.addObject("addStoreInfotoken",token);
		session.setAttribute("addStoreInfotoken", token);
		return view;
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
	
	
	@RequestMapping(value = "/saveStoreInfo", method = RequestMethod.POST)
	public void saveStoreInfo(Long storeId,Long bankId,String bankName, BankInfo bankInfo, StoreInfo storeInfo, HttpServletRequest request,
			HttpServletResponse response, HttpSession session,String addStoreInfotoken) throws Exception {
		Object tokenSession = SessionUtil.getSession(session, "addStoreInfotoken");
		SessionUtil.removeSession(session, "addStoreInfotoken");
		JSONObject json = new JSONObject();
		if (null != tokenSession && tokenSession.equals(addStoreInfotoken)) {
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			response.sendRedirect("/seller/login.do");
		} else {
			Long sellerId = seller.getId();
			storeInfo.setSellerId(sellerId);
			bankInfo.setSellerId(seller.getId());
			bankInfo.setIsdefault(1);
			bankInfo.setStatus(2);
			bankInfo.setEnabled(1);
			bankInfo.setName(bankName);
			boolean isSuccess = false;
			if (storeId != null && bankId !=null) {
				storeInfo.setId(storeId);
				bankInfo.setId(bankId);
				isSuccess = sellerAssignedService.updateStoreInfo(bankInfo, storeInfo);
			}else{
				 isSuccess = sellerAssignedService.saveStoreInfo(bankInfo, storeInfo);
			}
			if (isSuccess == true) {
				json.put("message", "添加成功");
				request.getSession().setAttribute("storeId", storeInfo.getId());
				SellerInfo se = new SellerInfo();
				se.setId(seller.getId());
				se.setStep(5);
				sellerAssignedService.updateSellerById(se);
				seller = sellerAssignedService.querySellerById(sellerId);
				session.setAttribute(ConstantsConfigurer.getSeller(), seller);
			} else {
				json.put("message", "添加失败");
			}
		}
		}else{
			json.put("message", "tokenEorro");
		}
		HtmlUtil.writerJson(response, json);
	}

	@RequestMapping(value = "/addCategory", method = RequestMethod.GET)
	public ModelAndView addCategory(HttpSession session) throws Exception {
		SellerInfo SellerInfo = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		ModelAndView view = new ModelAndView("seller/jysmall_step6");
		if (SellerInfo == null)
			view.setViewName("redirect:/seller/login.do");
		view.addObject("storyInfo", sellerAssignedService.queryStoreBySellerId(SellerInfo.getId()));
		return view;
	}

	@RequestMapping("/addCategory")
	public void addCategory(StoreInfo storeInfo, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JSONObject json = new JSONObject();
		SellerInfo seller = (SellerInfo) request.getSession().getAttribute(
				ConstantsConfigurer.getSeller());
		StoreInfo sto = sellerAssignedService.queryStoreBySellerId(seller.getId());
		storeInfo.setId(sto.getId());
		boolean isSuccess = sellerAssignedService.addCategory(storeInfo);
		if (isSuccess == true) {
			json.put("message", "添加成功");
			SellerInfo se = new SellerInfo();
			se.setId(seller.getId());
			se.setStep(6);
			sellerAssignedService.updateSellerById(se);
			StoreInfo store = new StoreInfo();
			store.setId(sto.getId());
			store.setCreateTime(new Date());
			store.setStatus(0);
			sellerAssignedService.updateStoreById(store);
			StoreAudit storeAudit = sellerAssignedService.queryStoreAuditById(store.getId());
			if (storeAudit != null) {
				storeAudit.setStatus(0);
				storeAudit.setTime(new Date());
			}
		} else {
			json.put("message", "添加失败");
		}
		HtmlUtil.writerJson(response, json);
	}

	@RequestMapping(value = "/queryStoreInfo")
	public Object queryStoreInfo(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws Exception {
		ModelAndView view = new ModelAndView("seller/storeInfo");
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			view.setViewName("redirect:/seller/login.do");
		} else {
			Long sellerId = seller.getId();
			StoreInfo store = sellerAssignedService.queryStoreInfo(sellerId);
			if (null != store) {
				request.getSession().setAttribute("storeId", store.getId());
				view.addObject("store", store);
			} else {
				view.setViewName("seller/storeInfo_center");
				StoreInfo sto = sellerAssignedService.queryStoreBySellerId(seller.getId());
				if(null==sto){
					return super.goUrl("/managermall/seller/serviceAgreement.do", "请先填写完认证");
				}
				view.addObject("status", sto.getStatus());
			}
		}
		return view;
	}
	
	
	
	
	

	@RequestMapping(value = "updateStore", method = RequestMethod.POST)
	public void updateStore(StoreCheck store, HttpSession session, HttpServletResponse response) throws Exception {
		JSONObject json = new JSONObject();
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		if (seller == null) {
			response.sendRedirect("/seller/login.do");
		} else {
			Long storeId = (Long) session.getAttribute("storeId");
			store.setStoreId(storeId);
			if (!BaseUtil.judgeNull(store.getImages())) {
				String[] images = store.getImages().split(",");
				String image = "";
				for (String temp : images)
					if (!BaseUtil.judgeNull(temp))
						image += "," + temp;
				image = image.substring(1);
				store.setImages(image);
			}
			boolean isSuccess = sellerAssignedService.updateStore(store);
			if (isSuccess == true)
				json.put("message", "添加成功");
			else
				json.put("message", "添加失败");
		}
		HtmlUtil.writerJson(response, json);
	}

	@RequestMapping(value = "/queryCategory", method = RequestMethod.POST)
	public void queryCategoryInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<CategoryInfo> categoryList = sellerAssignedService.queryCategoryInfo();
		HtmlUtil.writerJson(response, categoryList);
	}

}
