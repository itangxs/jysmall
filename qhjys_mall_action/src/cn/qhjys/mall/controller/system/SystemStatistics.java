package cn.qhjys.mall.controller.system;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.CategoryInfo;
import cn.qhjys.mall.entity.FqClerkCount;
import cn.qhjys.mall.entity.RebateOrder;
import cn.qhjys.mall.entity.StoreRebate;
import cn.qhjys.mall.service.CategoryService;
import cn.qhjys.mall.service.RebateOrderService;
import cn.qhjys.mall.service.system.StoreRebateService;
import cn.qhjys.mall.service.system.SystemOperateService;
import cn.qhjys.mall.util.ExportExcelUtil;
import cn.qhjys.mall.vo.OrderCountVo;
import cn.qhjys.mall.vo.RebateOrderVo;
import cn.qhjys.mall.vo.StoreCountVo;
import cn.qhjys.mall.vo.system.SalesDetailVo;
import cn.qhjys.mall.vo.system.SalesofGoodsVo;
import cn.qhjys.mall.vo.system.ShopAreaVo;
import cn.qhjys.mall.vo.system.ShopSalesVo;

import com.github.pagehelper.Page;

/**
 * 
 * 类名称:SystemStatistics 类描述:运营统计 创建人:JiangXiaoPing 创建时间:2015年5月25日上午11:06:55 修改人
 * 修改时间: 修改备注:
 */
@Controller
@RequestMapping("/managermall/systemmall/statistics")
public class SystemStatistics extends Base {
	@Autowired
	private SystemOperateService systemOperateService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private RebateOrderService rebateOrderService;
	@Autowired
	private StoreRebateService storeRebateService;
	@SuppressWarnings("rawtypes")
	ExportExcelUtil excelUtil = new ExportExcelUtil();
	
	

	@RequestMapping("/storeAnalyzeListExcel")
	public ModelAndView storeAnalyzeListExcel(HttpServletResponse response,String startTime,String endTime,Long storeId,
			String branchName,String teamName,String clerk,String storeName,Long categoryid,Integer isEffective){
		Page<StoreCountVo> page = rebateOrderService.queryStoreCountVo(startTime, endTime, null, null, storeId, branchName, teamName, clerk, storeName, categoryid, isEffective);
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HHmmss");
		
		String[] headersName = new String[] { "开户时间", "公司名称","团队长","业务员","店铺ID","店铺名称","行业分类","费率套餐","支付笔数","套现笔数","交易金额","套现金额","手续费","到账金额","首单时间","最近消费时间","是否有效","转化有效日期" };
		String[] headersId = new String[] { "createTime", "branchName","teamName", "clerk","storeId","storeName","categoryDetails","rateName","totalNum","cashNum","totalMoney","cashMoney","totalRate","totalTotamt","firstTime","lastTime","isEffective","effectiveDate"};

		excelUtil.exportExcel("店铺分析报表" + format.format(new Date()), headersName, headersId, page.getResult(),
				response);
		
		return null;
	}
	@RequestMapping("/storeAnalyzeList")
	public ModelAndView storeAnalyzeList(String startTime,String endTime,Integer pageNum,Integer pageSize,Long storeId,
			String branchName,String teamName,String clerk,String storeName,Long categoryid,Integer isEffective){
		ModelAndView view = new ModelAndView("system/statistics/store_analyze_list");
		if(pageNum == null)
			pageNum = 1;
		if(pageSize == null)
			pageSize = 15;
		Page<StoreCountVo> page = rebateOrderService.queryStoreCountVo(startTime, endTime, pageNum, pageSize, storeId, branchName, teamName, clerk, storeName, categoryid, isEffective);
		List<CategoryInfo>list = categoryService.queryAllSon();
		view.addObject("page", page);
		view.addObject("list", list);
		view.addObject("startTime", startTime);
		view.addObject("endTime", endTime);
		view.addObject("storeId", storeId);
		view.addObject("branchName", branchName);
		view.addObject("teamName", teamName);
		view.addObject("clerk", clerk);
		view.addObject("storeName", storeName);
		view.addObject("categoryid", categoryid);
		view.addObject("isEffective", isEffective);
		return view;
	}
	@RequestMapping("/accountingList")
	public ModelAndView accountingList(String clerkTime,String countMonth,Integer pageNum,Integer pageSize,Long provinceId,
			Long cityId,Long branchId,Long teamId,Long clerkId){
		ModelAndView view = new ModelAndView("system/statistics/accounting_list");
		view.addObject("clerkTime", clerkTime);
		view.addObject("countMonth", countMonth);
		view.addObject("branchId", branchId);
		view.addObject("teamId", teamId);
		view.addObject("clerkId", clerkId);
		view.addObject("provinceId", provinceId);
		view.addObject("cityId", cityId);
		return view;
	}
	@RequestMapping("/outstandingList")
	public ModelAndView outstandingList(Integer pageNum,Integer pageSize,String startDate,Long branchId,Long teamId,Long clerkId){
		if(pageNum == null)
			pageNum = 1;
		if(pageSize == null)
			pageSize = 15;
		ModelAndView view = new ModelAndView("system/statistics/outstanding_list");
		Page<FqClerkCount> page = rebateOrderService.queryFqClerkCountBySeller(pageNum,pageSize,startDate, branchId, teamId, clerkId);
		view.addObject("page", page);
		view.addObject("startDate", startDate);
		view.addObject("branchId", branchId);
		view.addObject("teamId", teamId);
		view.addObject("clerkId", clerkId);
		return view;
	}
	@RequestMapping("/outstandingListExcel")
	public ModelAndView outstandingListExcel(HttpServletResponse response,String startDate,Long branchId,Long teamId,Long clerkId){
		
		Page<FqClerkCount> page = rebateOrderService.queryFqClerkCountBySeller(null,null,startDate, branchId, teamId, clerkId);
		
		String[] headersName = new String[] { "开始时间", "区域","公司","团队长","业务员","店铺ID","店铺名称","交易金额","套现金额","订单笔数","大于10元订单"};
		String[] headersId = new String[] { "countDate", "cityName","branchName", "teamName","clerkName","storeId","storeName","totalMoney","cashMoney","totalNum","tenNum"};

		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HHmmss");
		excelUtil.exportExcel("未核算业绩报表" + format.format(new Date()), headersName, headersId, page.getResult(),
				response);
		return null;
	}
	
	@RequestMapping("/salesDiscountList")
	public ModelAndView listrebateOrder(String orderNo,String rebateName,String storeName,
			Long sellerId,String benginTime,String endTime,String openId,Integer pageNum,Integer pageSize){
		ModelAndView view = new ModelAndView("system/statistics/sales_Discount_list");
		if(pageNum == null)
			pageNum = 1;
		if(pageSize == null)
			pageSize = 15;
		Page<RebateOrderVo> page = rebateOrderService.listRebateOrderVo(orderNo, rebateName, storeName, sellerId, benginTime, endTime, openId, pageNum, pageSize);
		view.addObject("page", page);
		view.addObject("orderNo", orderNo);
		view.addObject("rebateName", rebateName);
		view.addObject("storeName", storeName);
		view.addObject("sellerId", sellerId);
		view.addObject("benginTime", benginTime);
		view.addObject("endTime", endTime);
		view.addObject("openId", openId);
		return view;
	}
	
	@RequestMapping("/discountList")
	public ModelAndView listThirdPay(String orderNo,String storeName,
			Long sellerId,String benginTime,String endTime,String openId,Integer pageNum,Integer pageSize,Integer bankType,Integer payType,Integer isSelect){
		ModelAndView view = new ModelAndView("system/statistics/discount_list");
		if (null == isSelect) {
			return view;
		}
		if(pageNum == null)
			pageNum = 1;
		if(pageSize == null)
			pageSize = 15;
		
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
		Page<RebateOrderVo> page = rebateOrderService.listRebateOrderVoByThird(orderNo, storeName, sellerId, benginTime, endTime, openId,bankType, pageNum, pageSize,type,null,null,null);
		OrderCountVo orderCountVo = rebateOrderService.queryOrderCountVo(sellerId, orderNo, storeName, openId, benginTime, endTime, pageNum, pageSize, 2, type, null);
		view.addObject("orderCountVo", orderCountVo);
		view.addObject("page", page);
		view.addObject("orderNo", orderNo);
		view.addObject("storeName", storeName);
		view.addObject("sellerId", sellerId);
		view.addObject("benginTime", benginTime);
		view.addObject("endTime", endTime);
		view.addObject("openId", openId);
		view.addObject("bankType", bankType);
		view.addObject("payType", payType);
		return view;
	}
	@RequestMapping("/cashDiscountList")
	public ModelAndView cashDiscountList(String orderNo,String storeName,
			Long storeId,String benginTime,String endTime,String openId,Integer pageNum
			,Integer pageSize,Integer bankType,Integer payType,Integer isSelect,Integer isCash){
		ModelAndView view = new ModelAndView("system/statistics/cash_order_list");
		if (null == isSelect) {
			return view;
		}
		if(pageNum == null)
			pageNum = 1;
		if(pageSize == null)
			pageSize = 15;
		
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
		Page<RebateOrderVo> page = rebateOrderService.listRebateOrderVoByThird(orderNo, storeName, null, benginTime, endTime, openId,bankType, pageNum, pageSize,type,isCash,1,storeId);
		view.addObject("page", page);
		view.addObject("orderNo", orderNo);
		view.addObject("storeName", storeName);
		view.addObject("benginTime", benginTime);
		view.addObject("endTime", endTime);
		view.addObject("openId", openId);
		view.addObject("bankType", bankType);
		view.addObject("payType", payType);
		view.addObject("isCash", isCash);
		return view;
	}
	
	@RequestMapping("/cashDiscountListExcel")
	public ModelAndView cashDiscountList(HttpServletResponse response,String orderNo,String storeName,
			Long storeId,String benginTime,String endTime,String openId,Integer bankType,Integer payType,Integer isCash){
		
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
		Page<RebateOrderVo> page = rebateOrderService.listRebateOrderVoByThird(orderNo, storeName, null, benginTime, endTime, openId,bankType, null, null,type,isCash,1,storeId);
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HHmmss");
		
		String[] headersName = new String[] { "订单号", "店铺ID","店铺名称","openId","支付方式","交易金额","支付时间","消费来源","是否套现","会员支付笔数" };
		String[] headersId = new String[] { "orderNo", "storeId", "storeName","openId", "type",  "realPay","payTime","bankType","isCash","payNum"};

		excelUtil.exportExcel("套现订单表" + format.format(new Date()), headersName, headersId, page.getResult(),
				response);
		
		return null;
	}
	
	@RequestMapping("/updateOrderCash")
	@ResponseBody
	public String updateOrderCash(String[] iscashs){
		int a = rebateOrderService.updateOrderCash(iscashs);
		if (a>0) {
			return "SUCCESS";
		}
		return "ERROR";
	}
	
	@RequestMapping("/discountListExcel")
	public ModelAndView discountListExcel(
			HttpServletResponse response,
			String orderNo,String storeName,
			Long sellerId,String benginTime,String endTime,String openId,Integer bankType,Integer payType ){
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
		Page<RebateOrderVo> page = rebateOrderService.listRebateOrderVoByThird(orderNo, storeName, sellerId, benginTime, endTime, openId,bankType, null, null,type,null,null,null);
	
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HHmmss");
		
		String[] headersName = new String[] { "订单号","店铺ID", "店铺名称","openId","支付方式","支付金额","手续费率","手续费","到账金额","支付时间","消费来源","是否套现" };
		String[] headersId = new String[] { "orderNo", "storeId","storeName","openId", "type",  "realPay","orderRate","rateFee","totamt","payTime","bankType","isCash"};

		excelUtil.exportExcel("支付明细报表" + format.format(new Date()), headersName, headersId, page.getResult(),
				response);
		
		return null;
	}
	@RequestMapping("/salesDiscountListExcel")
	public ModelAndView salesDetailListExcel(
			HttpServletResponse response,
			String orderNo,String rebateName,String storeName,
			Long sellerId,String benginTime,String endTime,String openId){
		
		
		Page<RebateOrderVo> page = rebateOrderService.listRebateOrderVo(orderNo, rebateName, storeName, sellerId, benginTime, endTime, openId, null, null);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HHmmss");
		
		String[] headersName = new String[] { "订单号", "店铺名称", "买家", "商家ID", "折扣名称", "抵用券","金元宝","微信支付","实付金额","未参与折扣金额","实付消费权","支付时间" };
		String[] headersId = new String[] { "orderNo", "storeName", "openId", "sellerId", "rebateName","couponId","integral","needPay","realPay","noDiscount","totamt","payTime"
		};
		
		excelUtil.exportExcel("折扣明细报表" + format.format(new Date()), headersName, headersId, page.getResult(),
				response);
		
		return null;
	}
	
	
	@RequestMapping("/getRebateOrder")
	public ModelAndView getRebateOrder(Long id){
			ModelAndView view = new ModelAndView("system/statistics/sales_Discount_list1");	
			RebateOrder order = rebateOrderService.getRebateOrder(id);
			StoreRebate rebate = storeRebateService.getStoreRebate(order.getRebateId());
			view.addObject("order", order);
			view.addObject("rebate", rebate);
			return view;
		}
	// ·商品销售排行报表
	/**
	 * 
	 * SalesRanking 商品销售排行
	 * 
	 * @param proName
	 *            商品名称
	 * @param stoName
	 *            店铺名称
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/salesRankingList")
	public ModelAndView SalesRanking(@RequestParam(value = "proName", required = false) String proName,
			@RequestParam(value = "stoName", required = false) String stoName,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("/system/statistics/sales_ranking_list");
		if (null == pageNum || pageNum < 1)
			pageNum = 1;
		if (null == pageSize || pageSize == 0)
			pageSize = 10;
		Page<SalesofGoodsVo> page = systemOperateService.querySalesofGoodsVoPage(proName, stoName, pageNum, pageSize);
		view.addObject("page", page);
		view.addObject("stoName", stoName);
		view.addObject("proName", proName);
		view.addObject("pageNum", pageNum);
		view.addObject("pageSize", pageSize);
		return view;
	}

	@RequestMapping("/salesRankingListExcel")
	public ModelAndView salesRankingListExcel(HttpServletResponse response,
			@RequestParam(value = "proName", required = false) String proName,
			@RequestParam(value = "stoName", required = false) String stoName,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HHmmss");
		if (null == pageNum || pageNum < 1)
			pageNum = 1;
		if (null == pageSize || pageSize == 0)
			pageSize = 10;
		Page<SalesofGoodsVo> page = systemOperateService.querySalesofGoodsVoPage(proName, stoName, pageNum, pageSize);
		String[] headersName = new String[] { "商品名称", "店铺名称", "销售数量", "销售额度" };
		String[] headersId = new String[] { "productName", "storeName", "statisticsCount", "salesVolume" };

		excelUtil.exportExcel("商品销售排行导出" + format.format(new Date()), headersName, headersId, page.getResult(),
				response);
		return null;
	}

	/**
	 * 
	 * salesDetailList ·商品销售明细报表
	 * 
	 * @param orderNo
	 * @param proName
	 * @param stoName
	 * @param startDate
	 * @param endDate
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/salesDetailList")
	public ModelAndView salesDetailList(@RequestParam(value = "orderNo", required = false) String orderNo,
			@RequestParam(value = "proName", required = false) String proName,
			@RequestParam(value = "stoName", required = false) String stoName,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("/system/statistics/sales_detail_list");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (null == pageNum || pageNum < 1)
			pageNum = 1;
		if (null == pageSize || pageSize == 0)
			pageSize = 10;
		Page<SalesDetailVo> page = systemOperateService.querySalesofGoodsVoPage(orderNo, proName, StringUtils
				.isEmpty(startDate) ? null : format.parse(startDate),
				StringUtils.isEmpty(endDate) ? null : format.parse(endDate), stoName, pageNum, pageSize);
		view.addObject("page", page);
		view.addObject("orderNo", orderNo);
		view.addObject("stoName", stoName);
		view.addObject("proName", proName);
		view.addObject("startDate", startDate);
		view.addObject("endDate", endDate);
		view.addObject("pageNum", pageNum);
		view.addObject("pageSize", pageSize);
		return view;
	}

	@RequestMapping("/salesDetailListExcel")
	public ModelAndView salesDetailListExcel(HttpServletResponse response,
			@RequestParam(value = "orderNo", required = false) String orderNo,
			@RequestParam(value = "proName", required = false) String proName,
			@RequestParam(value = "stoName", required = false) String stoName,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HHmmss");
		if (null == pageNum || pageNum < 1)
			pageNum = 1;
		if (null == pageSize || pageSize == 0)
			pageSize = 10;
		Page<SalesDetailVo> page = systemOperateService.querySalesofGoodsVoPage(orderNo, proName, StringUtils
				.isEmpty(startDate) ? null : format.parse(startDate),
				StringUtils.isEmpty(endDate) ? null : format.parse(endDate), stoName, pageNum, pageSize);
		String[] headersName = new String[] { "订单号", "店铺名称", "商家ID", "商品名称", "交易金额", "交易时间" };
		String[] headersId = new String[] { "orderNo", "storeName", "sellerId", "productName", "tradingMoeny",
				"tradingDate" };

		excelUtil.exportExcel("商品销售明细报表" + format.format(new Date()), headersName, headersId, page.getResult(),
				response);
		return null;
	}

	/**
	 * 
	 * shopAreaList 店铺区域分布
	 * 
	 * @param city
	 *            市
	 * @param area
	 *            区域
	 * @param startDate
	 *            交易开始时间
	 * @param endDate
	 *            交易结束时间
	 * @param pageNum
	 *            页数
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/shopAreaList")
	public ModelAndView shopAreaList(@RequestParam(value = "licenseProvince", required = false) String licenseProvince,
			@RequestParam(value = "licenseCity", required = false) String city,
			@RequestParam(value = "licenseArea", required = false) String area,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("/system/statistics/shop_area_list");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (null == pageNum || pageNum < 1)
			pageNum = 1;
		if (null == pageSize || pageSize == 0)
			pageSize = 10;
		Page<ShopAreaVo> page = systemOperateService.queryShopAreaVoPage(city, area,
				StringUtils.isEmpty(startDate) ? null : format.parse(startDate), StringUtils.isEmpty(endDate) ? null
						: format.parse(endDate), pageNum, pageSize);
		view.addObject("page", page);
		view.addObject("licenseProvince", licenseProvince);
		view.addObject("city", city);
		view.addObject("area", area);
		view.addObject("startDate", startDate);
		view.addObject("endDate", endDate);
		view.addObject("pageNum", pageNum);
		view.addObject("pageSize", pageSize);
		return view;
	}

	@RequestMapping("/shopAreaListExcel")
	public ModelAndView shopAreaListExcel(HttpServletResponse response,
			@RequestParam(value = "licenseProvince", required = false) String licenseProvince,
			@RequestParam(value = "licenseCity", required = false) String city,
			@RequestParam(value = "licenseArea", required = false) String area,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws ParseException, Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HHmmss");
		if (null == pageNum || pageNum < 1)
			pageNum = 1;
		if (null == pageSize || pageSize == 0)
			pageSize = 10;
		Page<ShopAreaVo> page = systemOperateService.queryShopAreaVoPage(city, area,
				StringUtils.isEmpty(startDate) ? null : format.parse(startDate), StringUtils.isEmpty(endDate) ? null
						: format.parse(endDate), pageNum, pageSize);
		String[] headersName = new String[] { "市", "区", "商家数量", "交易金额" };
		String[] headersId = new String[] { "cityName", "area", "sellerCount", "tradingMoney" };
		excelUtil.exportExcel("店铺区域分布" + format.format(new Date()), headersName, headersId, page.getResult(), response);
		return null;
	}

	/**
	 * 
	 * shopSalesList ·店铺销售额统计
	 * 
	 * @param sellerId
	 *            商家ID
	 * @param storeName
	 *            店铺名称
	 * @param pageNum
	 *            页数
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/shopSalesList")
	public ModelAndView shopSalesList(@RequestParam(value = "sellerId", required = false) String sellerId,
			@RequestParam(value = "storeName", required = false) String storeName,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("/system/statistics/shop_sales_list");
		if (null == pageNum || pageNum < 1)
			pageNum = 1;
		if (null == pageSize || pageSize == 0)
			pageSize = 10;
		Page<ShopSalesVo> page = systemOperateService.queryShopSalesVoPage(sellerId, storeName, pageNum, pageSize);
		view.addObject("page", page);
		view.addObject("sellerId", sellerId);
		view.addObject("storeName", storeName);
		view.addObject("pageNum", pageNum);
		view.addObject("pageSize", pageSize);
		return view;
	}

	@RequestMapping("/shopSalesListExcel")
	public ModelAndView shopSalesListExcel(HttpServletResponse response,
			@RequestParam(value = "sellerId", required = false) String sellerId,
			@RequestParam(value = "storeName", required = false) String storeName,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HHmmss");
		if (null == pageNum || pageNum < 1)
			pageNum = 1;
		if (null == pageSize || pageSize == 0)
			pageSize = 10;
		Page<ShopSalesVo> page = systemOperateService.queryShopSalesVoPage(sellerId, storeName, pageNum, pageSize);
		String[] headersName = new String[] { "商家ID", "店铺名称", "销售额" };
		String[] headersId = new String[] { "sellerId", "storeName", "salesVolume" };

		excelUtil
				.exportExcel("店铺销售额统计" + format.format(new Date()), headersName, headersId, page.getResult(), response);
		return null;
	}

	/**
	 * 
	 * shopDaySalesList ·店铺每日收入报表
	 * 
	 * @param sellerId
	 *            商家ID
	 * @param storeName
	 *            店铺名称
	 * @param pageNum
	 *            页数
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/shopDaySalesList")
	public ModelAndView shopDaySalesList(@RequestParam(value = "sellerId", required = false) String sellerId,
			@RequestParam(value = "storeName", required = false) String storeName,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("/system/statistics/shop_day_sales_list");
		if (null == pageNum || pageNum < 1)
			pageNum = 1;
		if (null == pageSize || pageSize == 0)
			pageSize = 10;
		// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD hh:mm");
		Date date = new Date();
		// format.parse("2015-05-07 10:52:03");
		Page<ShopSalesVo> page = systemOperateService.queryShopDaySalesVoPage(sellerId, storeName, date, pageNum,
				pageSize);
		view.addObject("page", page);
		view.addObject("sellerId", sellerId);
		view.addObject("storeName", storeName);
		view.addObject("pageNum", pageNum);
		view.addObject("pageSize", pageSize);
		return view;
	}

	@RequestMapping("/shopDaySalesListExcel")
	public ModelAndView shopDaySalesListExcel(HttpServletResponse response,
			@RequestParam(value = "sellerId", required = false) String sellerId,
			@RequestParam(value = "storeName", required = false) String storeName,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HHmmss");
		if (null == pageNum || pageNum < 1)
			pageNum = 1;
		if (null == pageSize || pageSize == 0)
			pageSize = 10;
		Date date = new Date();
		Page<ShopSalesVo> page = systemOperateService.queryShopDaySalesVoPage(sellerId, storeName, date, pageNum,
				pageSize);
		String[] headersName = new String[] { "商家ID", "店铺名称", "销售额", "时间" };
		String[] headersId = new String[] { "sellerId", "storeName", "date", "salesVolume" };

		excelUtil.exportExcel("店铺每日收入报表" + format.format(new Date()), headersName, headersId, page.getResult(),
				response);
		return null;
	}

	@RequestMapping("/shopMonthSalesList")
	public ModelAndView view(@RequestParam(value = "sellerId", required = false) String sellerId,
			@RequestParam(value = "storeName", required = false) String storeName,
			@RequestParam(value = "monthStart", required = false) String monthStart,
			@RequestParam(value = "monthEnd", required = false) String monthEnd,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		ModelAndView view = new ModelAndView("/system/statistics/shop_month_sales_list");
		if (null == pageNum || pageNum < 1)
			pageNum = 1;
		if (null == pageSize || pageSize == 0)
			pageSize = 10;
		/* SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss"); */
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Page<ShopSalesVo> page = systemOperateService.queryShopMonthSalesVoPage(sellerId, storeName,
				StringUtils.isEmpty(monthStart) ? null : format.parse((monthStart + " 00:00:00")),
				StringUtils.isEmpty(monthEnd) ? null : format.parse((monthEnd + " 00:00:00")), pageNum, pageSize);
		view.addObject("page", page);
		view.addObject("sellerId", sellerId);
		view.addObject("storeName", storeName);
		view.addObject("monthStart", monthStart);
		view.addObject("monthEnd", monthEnd);
		view.addObject("pageNum", pageNum);
		view.addObject("pageSize", pageSize);
		return view;
	}

	@RequestMapping("/shopMonthSalesListExcel")
	public ModelAndView shopMonthSalesListExcel(HttpServletResponse response,
			@RequestParam(value = "sellerId", required = false) String sellerId,
			@RequestParam(value = "storeName", required = false) String storeName,
			@RequestParam(value = "monthStart", required = false) String monthStart,
			@RequestParam(value = "monthEnd", required = false) String monthEnd,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HHmmss");
		if (null == pageNum || pageNum < 1)
			pageNum = 1;
		if (null == pageSize || pageSize == 0)
			pageSize = 10;
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Page<ShopSalesVo> page = systemOperateService.queryShopMonthSalesVoPage(sellerId, storeName,
				StringUtils.isEmpty(monthStart) ? null : format1.parse((monthStart + " 00:00:00")),
				StringUtils.isEmpty(monthEnd) ? null : format1.parse((monthEnd + " 00:00:00")), pageNum, pageSize);
		String[] headersName = new String[] { "商家ID", "店铺名称", "销售额", "时间" };
		String[] headersId = new String[] { "sellerId", "storeName", "date", "salesVolume" };

		excelUtil
				.exportExcel("店铺月收入报表" + format.format(new Date()), headersName, headersId, page.getResult(), response);
		return null;
	}

}