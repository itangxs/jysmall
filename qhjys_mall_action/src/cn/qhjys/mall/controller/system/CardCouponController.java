package cn.qhjys.mall.controller.system;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.CardCouponTemplate;
import cn.qhjys.mall.entity.CardDeliveryProperty;
import cn.qhjys.mall.service.system.CardCouponService;
import cn.qhjys.mall.util.ExportExcelUtil;
import cn.qhjys.mall.vo.system.CardDeliveryCountVo;

import com.allinpay.ets.client.StringUtil;
import com.github.pagehelper.Page;

/**
 * 商家卡券管理
 * 
 * @author wudupeng
 *
 */
@Controller
@RequestMapping("/managermall/systemmall/cardcoupon")
public class CardCouponController extends Base {
	@Autowired
	private CardCouponService cardCouponTemplateService;

	/**
	 * 显示列表详情
	 * 
	 * @param sellerId
	 * @param storeName
	 * @param couponCfg
	 * @param statusCfg
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/card_list")
	public ModelAndView cardList(
			@RequestParam(value = "storeId", required = false) Long storeId,
			@RequestParam(value = "storeName", required = false) String storeName,
			@RequestParam(value = "couponCfg", required = false) Integer couponCfg,
			@RequestParam(value = "statusCfg", required = false) Integer statusCfg,
			@RequestParam(value = "deleveryType", required = false) Integer deleveryType,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize)
			throws Exception {
		/*logger.info("-----店铺Id------->"+storeId);*/
		ModelAndView mav = new ModelAndView("system/kaquan/kaquan_list");
		if (null == storeId || storeId < 1)
			storeId = null;
		if (null == storeName || StringUtil.isEmpty(storeName))
			storeName = null;
		if (null == couponCfg || couponCfg < 0)
			couponCfg = null;
		if (null == statusCfg || statusCfg < 0)
			statusCfg = null;
		if (null == deleveryType || deleveryType <0)
			deleveryType = null;
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null) {
			pageSize = 10;
		}
		Page<CardCouponTemplate> page = cardCouponTemplateService
				.searchCardListByPage(storeId, storeName, couponCfg,
						statusCfg,deleveryType, pageNum, pageSize);
		mav.addObject("storeId", storeId);
		mav.addObject("page", page);
		mav.addObject("storeName", storeName);
		mav.addObject("couponCfg", couponCfg);
		mav.addObject("statusCfg", statusCfg);
		mav.addObject("deleveryType",deleveryType);
		mav.addObject("pageNum", pageNum);
		return mav;
	}

	/**
	 * 展示详情
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/detail")
	@ResponseBody
	public CardCouponTemplate detail(Long id) throws Exception {
		CardCouponTemplate byId = cardCouponTemplateService.queryCardCouponTempById(id);
		return byId;
	}

	/**
	 * 删除投放
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteCardStatusCfg")
	@ResponseBody
	public String deleteCardStatusCfg(Long sellerId,Long id) throws Exception {
		boolean statusCfg = cardCouponTemplateService.deleteCardStatusCfg(id,true,sellerId);
		if (statusCfg) {
			return "success";
		} else {
			return "error";
		}
	}
	/**
	 * 更改投放状态
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateStatus")
	@ResponseBody
	public boolean changeCouponStatus(Long sellerId,Long id) throws Exception {
		boolean updateStatus = cardCouponTemplateService.updateStatus(sellerId, id);
		if(updateStatus){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 查询卡券数据
	 * 
	 * @param sellerId
	 * @param storeName
	 * @param couponCfg
	 * @param name
	 * @throws Exception
	 */
	@RequestMapping("/kaquanData_list")
	public ModelAndView hexiao_list(
			@RequestParam(value = "storeId", required = false) Long storeId,
			@RequestParam(value = "storeName", required = false) String storeName,
			@RequestParam(value = "couponCfg" ,required = false) Integer couponCfg,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate,
			@RequestParam(value = "pageSize", required = false ) Integer pageSize,
			@RequestParam(value = "time", required = false ) Integer time) throws Exception {
		ModelAndView mav = new ModelAndView("system/kaquan/kaquan_hexiao");
		if(null == time || time <0)
			time = null;
		if (null == storeId || storeId <1)
			storeId = null;
		if (null == storeName || StringUtil.isEmpty(storeName))
			storeName = null;
		if(null == couponCfg || couponCfg <0)
			couponCfg = null;
		if(null == time || time<1)
			time = null;
		if (pageNum == null || pageNum < 1)
			pageNum = 1;
		pageSize = 10;
		Page<CardDeliveryCountVo> page = cardCouponTemplateService.queryCardDeliveryCount(storeId, storeName,couponCfg,startDate,endDate, pageNum,pageSize);
		mav.addObject("page", page);
		mav.addObject("storeId", storeId);
		mav.addObject("storeName", storeName);
		mav.addObject("couponCfg", couponCfg);
		mav.addObject("startDate", startDate);
		mav.addObject("endDate", endDate);
		mav.addObject("pageNum", pageNum);
		mav.addObject("time", time);
		mav.addObject("hexiao_type", 1);
		
		return mav;
	}
	/**
	 * 查询投放数据
	 * @param sellerId
	 * @param storeName
	 * @param deliverType
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deliverData_list")
	public ModelAndView queryDeliverData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "storeId", required = false) Long storeId,
			@RequestParam(value = "storeName", required = false) String storeName,Integer deliverType,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false ) Integer pageSize,
			@RequestParam(value = "time", required = false ) Integer time) throws Exception {
		ModelAndView mav = new ModelAndView("system/kaquan/kaquan_hexiao");
		if(null == time || time <0)
			time = null;
		if (null == storeId || storeId <1)
			storeId = null;
		if (null == storeName || StringUtil.isEmpty(storeName))
			storeName = null;
		if(null == deliverType || deliverType <0)
			deliverType = null;
		if (pageNum == null || pageNum < 1)
  			pageNum = 1;
		pageSize = 10;
		Page<CardDeliveryCountVo> page1 = cardCouponTemplateService.queryDeliverData(storeId, storeName, deliverType,startDate,endDate, pageNum, pageSize);
		mav.addObject("storeId", storeId);
		mav.addObject("deliverType", deliverType);
		mav.addObject("storeName", storeName);
		mav.addObject("startDate", startDate);
		mav.addObject("endDate", endDate);
		mav.addObject("pageNum", pageNum);
		mav.addObject("time", time);
		mav.addObject("page1", page1);
		mav.addObject("hexiao_type", 2);
		return mav;
	}
	

	/**
	 * 查询核销数据
	 * @param request
	 * @param response
	 * @param storeId
	 * @param storeName
	 * @param couponCfg
	 * @param startDate
	 * @param endDate
	 * @param pageNum
	 * @param pageSize
	 * @param time
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/valiData_list")
	public ModelAndView queryValiData(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "storeId", required = false) Long storeId,
			@RequestParam(value = "storeName", required = false) String storeName,
			@RequestParam(value = "couponCfg", required = false) Integer couponCfg,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "time", required = false ) Integer time
			)throws Exception{
		ModelAndView mav = new ModelAndView("system/kaquan/kaquan_hexiao");
		if(null == time || time <0)
			time = null;
		if (null == storeId || storeId <1)
			storeId = null;
		if (null == storeName || StringUtil.isEmpty(storeName))
			storeName = null;
		if(null == couponCfg || couponCfg<0)
			couponCfg = null;
		if (pageNum == null || pageNum < 1)
  			pageNum = 1;
		pageSize = 10;
		Page<CardDeliveryCountVo> page2 = cardCouponTemplateService.queryValiData(storeId, storeName, couponCfg,startDate, endDate,pageNum, pageSize);
		mav.addObject("storeId", storeId);
		mav.addObject("storeName", storeName);
		mav.addObject("couponCfg", couponCfg);
		mav.addObject("startDate", startDate);
		mav.addObject("endDate", endDate);
		mav.addObject("page2", page2);
		mav.addObject("time", time);
		mav.addObject("hexiao_type",3);
		return mav;
	}
	/**
	 * 跳转到卡券投放页面并查询所有
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/kaquanToufang")
	public ModelAndView toufanglist(Integer type) throws Exception {
		ModelAndView mav = new ModelAndView("system/kaquan/kaquan_toufang");
		CardDeliveryProperty queryByType = cardCouponTemplateService.queryCardDeliveryByType(1);
		CardDeliveryProperty queryByType2 = cardCouponTemplateService.queryCardDeliveryByType(2);
		mav.addObject("queryByType", queryByType);
		mav.addObject("queryByType2", queryByType2);
		return mav;
	}
	/**	
	 * 自营投放设定
	 */
	@RequestMapping("/updateZyDeliver")
	@ResponseBody
	public String updateZyDeliver(HttpServletRequest request, HttpServletResponse response,Long id,Integer deliverType, 
			Integer cardReceiveMin,Integer cardReceiveMax, 
			Integer cardShareMin, Integer cardShareMax,
			Integer cardShareReceiveMin, Integer cardShareReceiveMax,
			Integer firstRankProbability, Integer secondRankProbability, Integer thirdRankProbability,
			Integer fourthRankProbability) throws Exception {
		CardDeliveryProperty property = new CardDeliveryProperty();
		property.setId(id);
		property.setCardReceiveMax(cardReceiveMax);
		property.setCardReceiveMin(cardReceiveMin);
		property.setCardShareMax(cardShareMax);
		property.setCardShareMin(cardShareMin);
		property.setCardShareReceiveMax(cardShareReceiveMax);
		property.setCardShareReceiveMin(cardShareReceiveMin);
		property.setFirstRankProbability(firstRankProbability);
		property.setSecondRankProbability(secondRankProbability);
		property.setThirdRankProbability(thirdRankProbability);
		property.setFourthRankProbability(fourthRankProbability);
		int updateByType = cardCouponTemplateService.updateCardCouponZyDelivery(property);
		if (updateByType>0) {
			return "success";
		}
		return "error";
	}
	/**
	 * 周边投放设定
	 */
	@RequestMapping("/updateZbDeliver")
	@ResponseBody
	public String updateZbDeliver(HttpServletRequest request, HttpServletResponse response,Long id,Integer deliverType,Integer cardPushMin,Integer cardPushMax, Integer cardReceiveMin,
			Integer cardReceiveMax, Integer cardShareMin, Integer cardShareMax,
			Integer cardShareReceiveMin, Integer cardShareReceiveMax,
			Integer firstRankProbability, Integer secondRankProbability, Integer thirdRankProbability,
			Integer fourthRankProbability) throws Exception {
		CardDeliveryProperty property = new CardDeliveryProperty();
		property.setId(id);
		property.setCardPushMin(cardPushMin);
		property.setCardPushMax(cardPushMax);
		property.setCardReceiveMax(cardReceiveMax);
		property.setCardReceiveMin(cardReceiveMin);
		property.setCardShareMax(cardShareMax);
		property.setCardShareMin(cardShareMin);
		property.setCardShareReceiveMax(cardShareReceiveMax);
		property.setCardShareReceiveMin(cardShareReceiveMin);
		property.setFirstRankProbability(firstRankProbability);
		property.setSecondRankProbability(secondRankProbability);
		property.setThirdRankProbability(thirdRankProbability);
		property.setFourthRankProbability(fourthRankProbability);
		
		int update = cardCouponTemplateService.updateCardCouponZbDelivery(property);
		if (update>0) {
			return "success";
		}
		return "error";
	}
	/**
	 *公众号投放设定 
	 */
	@RequestMapping("/updateGzDeliver")
	@ResponseBody
	public String updateGzDeliver(HttpServletRequest request, HttpServletResponse response,Long id,Long sellerId,Integer deliveryNum) throws Exception{
		CardDeliveryProperty delivery = new CardDeliveryProperty();
		delivery.setId(id);
		delivery.setDeliveryNum(deliveryNum);
		int gzDelivery = cardCouponTemplateService.updateCardCouponGzDelivery(delivery,sellerId);
		
		if(gzDelivery == 2){
			return "isExist";
		}else if(gzDelivery == 1){
			return "success";
		}
		return "error";
	}
	/**
	 * 下载表格(卡券数据)
	 * @throws Exception 
	 */
	@RequestMapping("/kaquanListExcel")
	public ModelAndView kaquanListExcel(HttpServletResponse response,HttpSession session,Long storeId ,String storeName,Integer couponCfg,String startTime,String endTime,Integer pageNum,Integer pageSize) throws Exception{
			Page<CardDeliveryCountVo> page = cardCouponTemplateService.queryCardDeliveryCount(storeId, storeName,couponCfg,startTime,endTime, 1, 1000000);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HHmmss");
			
			String[] headersName = new String[] { "卡券类型", "商家名称",  "商家ID","卡券名称","实际","显示","核销"};
			String[] headersId = new String[] { "couponCfg", "storeName", "storeId","name","cardReciveTrue","cardReciveDisplay","valiDateCfg"};

			new ExportExcelUtil().exportExcel("卡券数据表" + format.format(new Date()), headersName, headersId, page.getResult(),
					response);
		return null;
	}
	/**
	 * 下载表格(投放数据)
	 * @throws Exception 
	 */
	@RequestMapping("/deliverDataListExcel")
	public ModelAndView deliverDataListExcel(HttpServletResponse response,HttpSession session,Integer deliverType,Long storeId ,String storeName,String startTime,String endTime,Integer pageNum,Integer pageSize) throws Exception{
			Page<CardDeliveryCountVo> page = cardCouponTemplateService.queryDeliverData(storeId, storeName, deliverType,startTime,endTime, 1, 1000000);
			 
			SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HHmmss");
			
			String[] headersName = new String[] { "投放方式", "商家名称",  "商家ID","实际","显示","实际","显示","实际","显示","实际","显示"};
			String[] headersId = new String[] { "deliverType", "storeName", "storeId","cardPushTrue","cardPushDisplay","cardReciveTrue","cardReciveDisplay","cardShareTrue","cardShareDisplay","cardShareReciveTrue","cardShareReciveDisplay"};
			new ExportExcelUtil().exportExcel("投放数据表" + format.format(new Date()), headersName, headersId, page.getResult(),
					response);
		return null;
	}
	/**
	 * 下载表格(核销数据)
	 * @throws Exception 
	 */
	@RequestMapping("/valiDataListExcel")
	public ModelAndView valiDataListExcel(HttpServletResponse response,HttpSession session,Long storeId ,Integer couponCfg,String storeName,String startTime,String endTime,Integer pageNum,Integer pageSize) throws Exception{
			Page<CardDeliveryCountVo> page = cardCouponTemplateService.queryValiData(storeId, storeName, couponCfg,startTime,endTime, 1, 1000000);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HHmmss");
			
			String[] headersName = new String[] { "卡券类型", "商家名称",  "商家ID","卡券名称","认证编号","核销时间","用户昵称"};
			String[] headersId = new String[] { "couponCfg", "storeName", "storeId","name","code","valiDate","nickName"};
			new ExportExcelUtil().exportExcel("核销数据表" + format.format(new Date()), headersName, headersId, page.getResult(),
					response);
		return null;
	}
}
