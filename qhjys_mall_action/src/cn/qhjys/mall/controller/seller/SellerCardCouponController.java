package cn.qhjys.mall.controller.seller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.PushCard;
import cn.qhjys.mall.entity.CardCoupon;
import cn.qhjys.mall.entity.CardCouponDelivery;
import cn.qhjys.mall.entity.CardCouponDeliveryTemplateRelation;
import cn.qhjys.mall.entity.CardCouponTemplate;
import cn.qhjys.mall.entity.CardDeliveryCount;
import cn.qhjys.mall.entity.CardDeliveryProperty;
import cn.qhjys.mall.entity.FqThirdPay;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.service.CommonInfoService;
import cn.qhjys.mall.service.StoreService;
import cn.qhjys.mall.service.seller.SellerCardCouponService;
import cn.qhjys.mall.service.system.CardCouponService;
import cn.qhjys.mall.util.AppResult;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.DateUtil;
import cn.qhjys.mall.util.ExportExcelUtil;
import cn.qhjys.mall.vo.PushCardVo;
import cn.qhjys.mall.vo.seller.SellerCardCouponTemplateVo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;

/**
 * 商户后台
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/managermall/seller/kaquan")
public class SellerCardCouponController {
	@Autowired
	private SellerCardCouponService sellerCardCouponService;
	@Autowired
	private CardCouponService cardCouponService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private CommonInfoService commonInfoService;
	
	@Autowired
	private cn.qhjys.mall.service.app.SellerCardCouponService sellerCardCouponService1;
	
	@SuppressWarnings("rawtypes")
	ExportExcelUtil excelUtil = new ExportExcelUtil();
	
	/**
	 * 卡券列表
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public ModelAndView cardCouponList(HttpSession session, Long sellerId, Integer statusCfg, Integer confouCfg, Integer pageNum, Integer pageSize) throws Exception{
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		sellerId = seller.getId();
		ModelAndView view = new ModelAndView("/seller/kaquan/kaquan_huojia");
		if (pageNum == null)
			pageNum = 0;
		if (pageSize == null || pageSize < 1)
			pageSize = 6;
		if (null == statusCfg)
			statusCfg = 3;
		Page<CardCouponTemplate> page = sellerCardCouponService.querySellerCardCoupon(sellerId, statusCfg, confouCfg, pageNum, pageSize);
		view.addObject("page", page);
		return view;
	}
	
	/**
	 * 卡券详情
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/detail")
	@ResponseBody
	public ModelAndView cardCouponDetail(/*@RequestParam(value = "id", required = true)*/ Long id) throws Exception{
		ModelAndView view = new ModelAndView("/seller/kaquan/kaquan_huojia");
		CardCouponTemplate cardCouponTemplate = sellerCardCouponService.getSellerCardCoupon(id);
		view.addObject("cardCouponTemplate",cardCouponTemplate);
		return view;
	}
	
	/**
	 * 创建卡券
	 * @param response
	 * @param request
	 * @param name
	 * @param totalCount
	 * @param useExplain
	 * @param countLimit
	 * @param validityCfg
	 * @param validityDay
	 * @param validityStarttime
	 * @param validityEndtime
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/insertCardCoupon")
	@ResponseBody
	public AppResult insertCardCoupon(HttpServletResponse response, HttpServletRequest request,String name,
			Integer totalCount,String useExplain,Integer countLimit,Integer validityCfg,Integer validityDay
			,Date validityStarttime,Date validityEndtime) throws Exception{
		AppResult result = new AppResult();
		CardCouponTemplate card = new CardCouponTemplate();
		card.setName(name);
		card.setTotalCount(totalCount);
		card.setUseExplain(useExplain);
		card.setCountLimit(countLimit);
		card.setValidityCfg(validityCfg);
		if (validityCfg == 0) {
			card.setValidityDay(validityDay);
		}else if (validityCfg == 1) {
			card.setValidityStarttime(validityStarttime);
			card.setValidityEndtime(validityEndtime);
		}
		int res = sellerCardCouponService.insertSellerCardCoupon(card);
		if (res >0) {
			result.setFlag(0);
			result.setReason("添加成功！");
		}else{
			result.setFlag(1);
			result.setReason("添加失败！");
		}
		return result;	
	}
	
	/**
	 * 删除卡券
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteCardCoupon")
	@ResponseBody
	public String deleteCardCoupon(Long id){
		CardCouponTemplate cardCouponTemplate = sellerCardCouponService.getSellerCardCoupon(id);
		int res = sellerCardCouponService.deleteCardCoupon(id,cardCouponTemplate);
		if (res > 0) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	/**
	 * 投放方式页面
	 * @param storeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deliverylist")
	public ModelAndView deliveryList(HttpSession session, Long sellerId, Integer deliverType){
		try {
			
		
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		sellerId = seller.getId();
		ModelAndView view = new ModelAndView("/seller/kaquan/kaquan_toufang");
		//卡券投放
		CardCouponDelivery cardCouponDelivery = sellerCardCouponService.queryCardCouponDelivery(sellerId);
		view.addObject("cardCouponDelivery", cardCouponDelivery);
		if(null != cardCouponDelivery){
			Calendar c = Calendar.getInstance();
			JSONArray dateStrs= new JSONArray();
			JSONArray gzlq= new JSONArray();
			JSONArray gzsy= new JSONArray();
			JSONArray zylq= new JSONArray();
			JSONArray zysy= new JSONArray();
			JSONArray zbzs= new JSONArray();
			JSONArray zblq= new JSONArray();
			JSONArray zbsy= new JSONArray();
			c.add(Calendar.DATE, -7);
			for (int i = 0; i < 7; i++) {
				String cdate = DateUtil.convertDateToString("yyyy-MM-dd", c.getTime());
				dateStrs.add(DateUtil.convertDateToString("MM.dd", c.getTime()));
				gzlq.add(sellerCardCouponService.queryCountByCoupon(sellerId, 0, null, cdate, null));
				gzsy.add(sellerCardCouponService.queryCountByCoupon(sellerId, 0, 1, null, cdate));
				SellerCardCouponTemplateVo zy = sellerCardCouponService.queryCountBytype(sellerId, 1, cdate);
				zylq.add(zy.getCardReceiveDisplay());
				zysy.add(zy.getWriteOff());
				SellerCardCouponTemplateVo zb = sellerCardCouponService.queryCountBytype(sellerId, 2, cdate);
				zbzs.add(zb.getCardPushDisplay());
				zblq.add(zb.getCardReceiveDisplay());
				zbsy.add(zb.getWriteOff());
				c.add(Calendar.DATE, 1);
			}
			view.addObject("dateStrs",dateStrs);
			view.addObject("gzlq",gzlq);
			view.addObject("gzsy",gzsy);
			view.addObject("zylq",zylq);
			view.addObject("zysy",zysy);
			view.addObject("zbzs",zbzs);
			view.addObject("zblq",zblq);
			view.addObject("zbsy",zbsy);
			view.addObject("gzlqAll",sellerCardCouponService.queryCountByCoupon(sellerId, 0, null, null, null));
			view.addObject("gzsyAll",sellerCardCouponService.queryCountByCoupon(sellerId, 0, 1, null, null));
			SellerCardCouponTemplateVo zyAll = sellerCardCouponService.queryCountBytype(sellerId, 1, null);
			SellerCardCouponTemplateVo zbAll = sellerCardCouponService.queryCountBytype(sellerId, 2, null);
			view.addObject("zyAll",zyAll);
			view.addObject("zbAll",zbAll);

			//未投放的卡券
			List<CardCouponTemplate> notPut = sellerCardCouponService.queryCardCouponTemplate(cardCouponDelivery.getSellerId(), 0);
			view.addObject("notPut", notPut);
			//自营投放的卡券
			List<CardCouponTemplate> cardCouponTemplates = sellerCardCouponService.queryCardCouponTemplate(cardCouponDelivery.getSellerId(), 1);
			//view.addObject("cardCouponTemplates", cardCouponTemplates);
			List<Map<String,String>> list = new ArrayList<Map<String,String>>();
			if(cardCouponTemplates.size() > 0){
				list = sellerCardCouponService.queryCouponTemplateBySellerIdAndStatusCfg(cardCouponTemplates.get(0).getSellerId(), cardCouponTemplates.get(0).getStatusCfg());
				for (int i = 0; i < list.size(); i++) {
					Map<String,String>  templateMap = list.get(i);
					switch (String.valueOf(templateMap.get("sort"))) {
						case "1":
							view.addObject("proprietaryBySort1", templateMap);
							break;
						case "2":
							view.addObject("proprietaryBySort2", templateMap);
							break;
						case "3":
							view.addObject("proprietaryBySort3", templateMap);
							break;
						case "4":
							view.addObject("proprietaryBySort4", templateMap);
							break;
						default:
							break;
					}
				}
				view.addObject("cardCouponTemplates", list);
			}
			//自营投放卡券的数量
			view.addObject("proprietaryNum", list.size());
			//周边投放的卡券
			List<CardCouponTemplate> cardCouponTemplates1 = sellerCardCouponService.queryCardCouponTemplate(cardCouponDelivery.getSellerId(), 2);
			//view.addObject("cardCouponTemplates1", cardCouponTemplates1);
			List<Map<String,String>> around = new ArrayList<Map<String,String>>();
			if(cardCouponTemplates1.size() > 0){
				around = sellerCardCouponService.queryCouponTemplateBySellerIdAndStatusCfg(cardCouponTemplates1.get(0).getSellerId(), cardCouponTemplates1.get(0).getStatusCfg());
				for (int i = 0; i < around.size(); i++) {
					Map<String,String>  templateMap = around.get(i);
					switch (String.valueOf(templateMap.get("sort"))) {
					case "1":
						view.addObject("peripheralBySort1", templateMap);
						break;
					case "2":
						view.addObject("peripheralBySort2", templateMap);
						break;
					case "3":
						view.addObject("peripheralBySort3", templateMap);
						break;
					case "4":
						view.addObject("peripheralBySort4", templateMap);
						break;
					default:
						break;
					}
				}
				view.addObject("cardCouponTemplates1", around);
			}
			//周边投放卡券的数量
			view.addObject("peripheralNum", around.size());
			
			CardDeliveryProperty queryByType = cardCouponService.queryCardDeliveryByType(1);
			CardDeliveryProperty queryByType2 = cardCouponService.queryCardDeliveryByType(2);
			view.addObject("queryByType", queryByType);
			view.addObject("queryByType2", queryByType2);
			
			StoreInfo store = storeService.queryStoreInfoBySeller(sellerId);
			String citys = commonInfoService.getValueById(1L);
			if (!StringUtils.isEmpty(citys)&&citys.contains(store.getCity()+",")) {
				view.addObject("isshow", 1);
			}else{
				view.addObject("isshow", 0);
			}
			
			return view;
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 确认投放卡券
	 * @param sellerId
	 * @param cardId
	 * @param deliveryType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/comfirmdelivery")
	@ResponseBody
	public String comfirmDelivery(HttpSession session, Long sellerId, Long cardId, Integer deliveryType, Integer sort) throws Exception{
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		StoreInfo store = (StoreInfo) session.getAttribute(ConstantsConfigurer.getProperty("store_key"));
		sellerId = seller.getId();
		CardCouponTemplate cardCouponTemplate = sellerCardCouponService.getSellerCardCoupon(cardId);
		if (deliveryType == 0) {
			List<String> openIDSet = sellerCardCouponService1.queryConsumerBySellerId(sellerId);
			 if(openIDSet!=null&&openIDSet.size()>1){
				 PushCardVo vo = new PushCardVo();
				 vo.setCardCouponTemplate(cardCouponTemplate);
				 vo.setStore(store);
				 vo.setOpenIds(openIDSet);
				 PushCard.pushcards.add(vo);
				 return "success";
			 }else{
				 return "success";	
			 }
		}
		if(null != cardCouponTemplate){
			cardCouponTemplate.setStatusCfg(deliveryType);
			int res = sellerCardCouponService.updateStatusCfg(cardCouponTemplate.getId(), cardCouponTemplate);
			if(res > 0){
				List<CardCouponTemplate> cardCouponTemplates = sellerCardCouponService.queryCardCouponTemplate(cardCouponTemplate.getSellerId(), deliveryType);
				CardCouponDelivery cardCouponDelivery = sellerCardCouponService.queryCardCouponDelivery(cardCouponTemplate.getSellerId());
				CardCouponDeliveryTemplateRelation cardCouponDeliveryTemplateRelation = new CardCouponDeliveryTemplateRelation();
				cardCouponDeliveryTemplateRelation.setSellerId(cardCouponTemplate.getSellerId());
				cardCouponDeliveryTemplateRelation.setCardCouponTemplateId(cardCouponTemplate.getId());
				cardCouponDeliveryTemplateRelation.setStatusCfg(deliveryType);
				cardCouponDeliveryTemplateRelation.setCardCouponDeliveryId(cardCouponDelivery.getId());
				cardCouponDeliveryTemplateRelation.setSort(sort);
				if(cardCouponTemplates.size() == 4){
					if(deliveryType == 1){
						cardCouponDelivery.setProprietaryStatus(1);
					}else if(deliveryType == 2){
						cardCouponDelivery.setPeripheralStatus(1);
					}
					int result = sellerCardCouponService.insertCardCouponDeliveryTemplateRelation(cardCouponDeliveryTemplateRelation);
					int updateDelivery = sellerCardCouponService.updateCardCouponDelivery(cardCouponDelivery);
					if(result > 0 && updateDelivery > 0){
						int rt = 0;
						for (CardCouponTemplate card : cardCouponTemplates) {
							rt = rt+sellerCardCouponService.deleteCardCoupon(card.getId(), card);
						}
						if(rt == 4){
							return "start";
						}
					}
				}else if(cardCouponTemplates.size() < 4){
					int result = sellerCardCouponService.insertCardCouponDeliveryTemplateRelation(cardCouponDeliveryTemplateRelation);
					if(result > 0 ){
						return "success";
					}
				}
			}
		}
		return "fail";
	}
	
	/**
	 * 核销与统计页面
	 * @param storeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/hexiao")
	public ModelAndView hexiao() throws Exception{
		ModelAndView view = new ModelAndView("/seller/kaquan/kaquan_hexiao");	
		return view;
	}
	
	/**
	 * 卡券认证页面
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cardidentify")
	public ModelAndView cardIdentify() throws Exception{
		ModelAndView view = new ModelAndView("/seller/kaquan/kaquan_renzheng");	
		return view;
	}
	
	/**
	 * 卡券认证
	 * @param storeId
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/identify")
	@ResponseBody
	public String identifyCardCoupon(HttpSession session, Long sellerId, String code) throws Exception{
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		sellerId = seller.getId();
		CardCoupon cardCoupon = sellerCardCouponService.identifyCardCoupon(sellerId, code);
		if (null != cardCoupon) {
			if(cardCoupon.getTemplateCouponCfg() == 0){
				if(null != cardCoupon.getValidateDate()){
					return "already";
				}
				int compare = cardCoupon.getValidityEndtime().compareTo(new Date());
				if(compare < 0 && null == cardCoupon.getValidateDate()){
					return "expire";
				}
				cardCoupon.setValidateDate(new Date());
				cardCoupon.setStatusCfg(1);
				int res = sellerCardCouponService.updateCardCoupon(cardCoupon);
				if(res > 0){
					return "success";
				}
			}else if(cardCoupon.getTemplateCouponCfg() != 0){
				return "incorrect";
			}
			return "fail";
		}else{
			return "null";
		}
	}
	
	/**
	 * 卡券数据
	 * @param storeId
	 * @param couponCfg
	 * @param startDate
	 * @param endDate
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/carddata")
	public ModelAndView cardData(HttpSession session, Long sellerId, Integer couponCfg, Integer datenum, String tjsjs, String tjsje, Integer pageNum, Integer pageSize) throws Exception{
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		sellerId = seller.getId();
		ModelAndView view = new ModelAndView("/seller/kaquan/kaquan_hexiao");
		StoreInfo store = storeService.queryStoreInfoBySeller(sellerId);
		String citys = commonInfoService.getValueById(1L);
		if (!StringUtils.isEmpty(citys)&&citys.contains(store.getCity()+",")) {
			if (pageNum == null)
				pageNum = 1;
			if (pageSize == null || pageSize < 1)
				pageSize = 10;
			if (datenum!= null) {
				String pattern = "yyyy-MM-dd";
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -1);
				tjsje = DateUtil.convertDateToString(pattern, cal.getTime());
				if (datenum==1) {
					tjsjs = tjsje;//今天
				}
				if (datenum==7) {
					cal.add(Calendar.DATE, -6); // 七天
					tjsjs = DateUtil.convertDateToString(pattern, cal.getTime());
				}
				if (datenum==30) {
					cal.add(Calendar.MONTH, -1); // 一个月
					tjsjs = DateUtil.convertDateToString(pattern, cal.getTime());
				}
				if (datenum==90) {
					cal.add(Calendar.MONTH, -3); // 三个月
					tjsjs = DateUtil.convertDateToString(pattern, cal.getTime());
				}
			}
			Page<SellerCardCouponTemplateVo> page = sellerCardCouponService.queryCardDeliveryCount(sellerId, couponCfg, tjsjs, tjsje, pageNum, pageSize);
			view.addObject("page", page);
		}
		view.addObject("type", 1);
		view.addObject("startTime", tjsjs);
		view.addObject("endTime", tjsje);
		view.addObject("datenum","");
		view.addObject("couponCfg",couponCfg);
		return view;
	}
	
	/**
	 * 投放数据
	 * @param storeId
	 * @param getWay
	 * @param startDate
	 * @param endDate
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deliverydata")
	public ModelAndView deliveryData(HttpSession session, Long sellerId, Integer getWay, Integer datenum, String tjsjs, String tjsje, Integer pageNum, Integer pageSize){
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		sellerId = seller.getId();
		ModelAndView view = new ModelAndView("/seller/kaquan/kaquan_hexiao");
		StoreInfo store = storeService.queryStoreInfoBySeller(sellerId);
		String citys = commonInfoService.getValueById(1L);
		if (!StringUtils.isEmpty(citys)&&citys.contains(store.getCity()+",")) {
			if (pageNum == null)
				pageNum = 1;
			if (pageSize == null || pageSize < 1)
				pageSize = 10;
			if (datenum!= null) {
				String pattern = "yyyy-MM-dd";
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -1);
				tjsje = DateUtil.convertDateToString(pattern, cal.getTime());
				if (datenum==1) {
					tjsjs = tjsje;//今天
				}
				if (datenum==7) {
					cal.add(Calendar.DATE, -6); // 七天
					tjsjs = DateUtil.convertDateToString(pattern, cal.getTime());
				}
				if (datenum==30) {
					cal.add(Calendar.MONTH, -1); // 一个月
					tjsjs = DateUtil.convertDateToString(pattern, cal.getTime());
				}
				if (datenum==90) {
					cal.add(Calendar.MONTH, -3); // 三个月
					tjsjs = DateUtil.convertDateToString(pattern, cal.getTime());
				}
			}
			Page<SellerCardCouponTemplateVo> templateVo = sellerCardCouponService.queryDeliveryData(sellerId, getWay, tjsjs, tjsje, pageNum, pageSize);
			view.addObject("templateVo", templateVo);
			}
		view.addObject("type", 2);
		view.addObject("startTime", tjsjs);
		view.addObject("endTime", tjsje);
		view.addObject("datenum","");
		view.addObject("getWay",getWay);
		return view;
	}

	/**
	 * 核销数据
	 * @param storeId
	 * @param couponCfg
	 * @param startDate
	 * @param endDate
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/validatedata")
	public ModelAndView validateData(HttpSession session, Long sellerId, Integer couponCfg, Integer datenum, String tjsjs, String tjsje, Integer pageNum, Integer pageSize) throws Exception{
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		sellerId = seller.getId();
		ModelAndView view = new ModelAndView("/seller/kaquan/kaquan_hexiao");
		StoreInfo store = storeService.queryStoreInfoBySeller(sellerId);
		String citys = commonInfoService.getValueById(1L);
		if (!StringUtils.isEmpty(citys)&&citys.contains(store.getCity()+",")) {
		
			if (pageNum == null)
				pageNum = 0;
			if (pageSize == null || pageSize < 1)
				pageSize = 10;
			if (datenum!= null) {
				String pattern = "yyyy-MM-dd";
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -1);
				tjsje = DateUtil.convertDateToString(pattern, cal.getTime());
				if (datenum==1) {
					tjsjs = tjsje;//今天
				}
				if (datenum==7) {
					cal.add(Calendar.DATE, -6); // 七天
					tjsjs = DateUtil.convertDateToString(pattern, cal.getTime());
				}
				if (datenum==30) {
					cal.add(Calendar.MONTH, -1); // 一个月
					tjsjs = DateUtil.convertDateToString(pattern, cal.getTime());
				}
				if (datenum==90) {
					cal.add(Calendar.MONTH, -3); // 三个月
					tjsjs = DateUtil.convertDateToString(pattern, cal.getTime());
				}
			}
			Page<SellerCardCouponTemplateVo> validatePage = sellerCardCouponService.queryValidateData(sellerId, couponCfg, tjsjs, tjsje, pageNum, pageSize);
			view.addObject("validatePage",validatePage);
			}
		view.addObject("type", 3);
		view.addObject("startTime", tjsjs);
		view.addObject("endTime", tjsje);
		view.addObject("datenum","");
		view.addObject("couponCfg", couponCfg);
		return view;
	}
	
	/**
	 * 卡券数据下载表格
	 * @param response
	 * @param storeId
	 * @param couponCfg
	 * @param startDate
	 * @param endDate
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/cardExcel")
	public ModelAndView deliveryDataExcel(HttpServletResponse response,HttpSession session, Long sellerId,
		 Integer couponCfg, String startDate, String endDate, Integer pageNum,Integer pageSize) throws UnsupportedEncodingException{
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		sellerId = seller.getId();
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HHmmss");
		if (null == pageNum || pageNum < 1)
			pageNum = 0;
		if (null == pageSize || pageSize == 0)
			pageSize = 10;
		Page<SellerCardCouponTemplateVo> page = sellerCardCouponService.queryCardDeliveryCount(sellerId, couponCfg, startDate, endDate, pageNum, pageSize);
		String[] headersName = new String[] { "卡券类型", "商家名称",  "商家ID","卡券名称","领取显示","核销"};
		String[] headersId = new String[] { "couponCfg", "storeName", "storeId","name","cardReceiveDisplay","statusCfg"};

		excelUtil.exportExcel("卡券数据表" + format.format(new Date()), headersName, headersId, page.getResult(),
				response);
		return null;
	}
	
	/**
	 * 投放数据下载表格
	 * @param response
	 * @param storeId
	 * @param couponCfg
	 * @param startDate
	 * @param endDate
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/deliveryExcel")
	public ModelAndView deliveryDataExcelTwo(HttpServletResponse response,HttpSession session, Long sellerId,
		Integer couponCfg, String startDate, String endDate, Integer pageNum, Integer pageSize) throws UnsupportedEncodingException{
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		sellerId = seller.getId();
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HHmmss");
		if (null == pageNum || pageNum < 1)
			pageNum = 1;
		if (null == pageSize || pageSize == 0)
			pageSize = 10;
		Page<SellerCardCouponTemplateVo> page = sellerCardCouponService.queryDeliveryData(sellerId, couponCfg, startDate, endDate, pageNum, pageSize);
		String[] headersName = new String[] { "投放方式","商家名称","商家ID","展示显示","领取显示","分享显示","领取分享显示"};
		String[] headersId = new String[] { "getWay","storeName","storeId","cardPushDisplay","cardReceiveDisplay","cardShareDisplay","cardShareReceiveDisplay"};

		excelUtil.exportExcel("投放方式数据表" + format.format(new Date()), headersName, headersId, page.getResult(),
				response);
		return null;
	}
	
	/**
	 * 投放数据下载表格
	 * @param response
	 * @param storeId
	 * @param couponCfg
	 * @param startDate
	 * @param endDate
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/validateExcel")
	public ModelAndView deliveryDataExcelThree(HttpServletResponse response,HttpSession session, Long sellerId,
		Integer couponCfg, String startDate, String endDate, Integer pageNum,Integer pageSize) throws UnsupportedEncodingException{
		SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
		sellerId = seller.getId();
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HHmmss");
		if (null == pageNum || pageNum < 1)
			pageNum = 1;
		if (null == pageSize || pageSize == 0)
			pageSize = 10;
		Page<SellerCardCouponTemplateVo> page = sellerCardCouponService.queryValidateData(sellerId, couponCfg, startDate, endDate, pageNum, pageSize);
		String[] headersName = new String[] { "卡券类型", "商家名称",  "商家ID","卡券名称","认证编号","核销时间","用户昵称"};
		String[] headersId = new String[] { "couponCfg", "storeName", "storeId","name","code","validateDate","nickName"};
		excelUtil.exportExcel("核销数据表" + format.format(new Date()), headersName, headersId, page.getResult(),
				response);
		return null;
	}
	
}
