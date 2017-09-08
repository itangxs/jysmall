package cn.qhjys.mall.controller.app;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;

import cn.qhjys.mall.common.AccessToken;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.common.PushCard;
import cn.qhjys.mall.entity.CardCoupon;
import cn.qhjys.mall.entity.CardCouponDelivery;
import cn.qhjys.mall.entity.CardCouponTemplate;
import cn.qhjys.mall.entity.CardDeliveryProperty;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.service.SellerService;
import cn.qhjys.mall.service.StoreService;
import cn.qhjys.mall.service.app.SellerCardCouponService;
import cn.qhjys.mall.util.AppResult;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.DateUtil;
import cn.qhjys.mall.vo.PushCardVo;
import cn.qhjys.mall.weixin.api.MessageAPI;
import cn.qhjys.mall.weixin.bean.message.MessageSendResult;
import cn.qhjys.mall.weixin.bean.message.massmessage.MassTextMessage;

@Controller
@RequestMapping("/appSellerCardCoupon")
public class AppSellerCardCouponController extends Base{
	
	private static Logger logger = LoggerFactory.getLogger(AppSellerCardCouponController.class);

	@Autowired
	private StoreService storeService;
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private SellerCardCouponService sellerCardCouponService;
	
	
	/**
	 *app新增卡券
	 */
	@RequestMapping("/add")
	@ResponseBody
	public AppResult add(HttpServletRequest request,HttpServletResponse response,Long sellerId,
			String name,Integer couponCfg,Integer totalCount,Integer countLimit,String useExplain,BigDecimal amount,Integer discount,
			Integer validityCfg,Integer validityDay,String validityStarttime,String validityEndtime,Integer validityUseStarttime,
			Integer validityUserEndtime,Integer useCfg,BigDecimal useOverAmount){
		String url = request.getRequestURI()+(StringUtils.isEmpty(request.getQueryString())?"":"?"+request.getQueryString());
		logger.info("url--------"+url);
		AppResult result = new AppResult();
		try {
			if (sellerId == null) {
				result.setFlag(1);
				result.setReason("商户编号错误！");
				return result;
			 }
			SellerInfo seller = sellerService.getSellerById(sellerId);
			if (seller == null) {
				result.setFlag(1);
				result.setReason("商户不存在！");
				return result;
			}
			StoreInfo store = storeService.queryStoreInfoBySeller(sellerId);
			if(store==null){
				result.setFlag(1);
				result.setReason("暂无店铺信息");
			}else if(couponCfg==null || couponCfg<0 || couponCfg>2 ){
				result.setFlag(1);
				result.setReason("卡券类型错误");
			}else{
				CardCouponTemplate template = new CardCouponTemplate();
				template.setSellerId(sellerId);
				template.setName(name);
				template.setCouponCfg(couponCfg);
				template.setTotalCount(totalCount);
				template.setSurplusCount(totalCount);//新建卡券的剩余数量等于总数量totalCount
				template.setCountLimit(countLimit);
				template.setUseExplain(useExplain);
				template.setAmount(amount);
				if (discount!= null) {
					template.setDiscount(discount<10?discount*10:discount);
				}
				if(validityCfg!=null&&0==validityCfg){
					template.setValidityCfg(validityCfg);
					template.setValidityStarttime(DateUtil.convertStringToDate("yyyy-MM-dd",validityStarttime));
					template.setValidityEndtime(DateUtil.convertStringToDate("yyyy-MM-dd",validityEndtime));
				}else if(validityCfg!=null&&1==validityCfg){
					template.setValidityCfg(validityCfg);
					template.setValidityDay(validityDay);
				}else{
					result.setFlag(1);
					result.setReason("卡券有效期类型错误");
					return result;
				}
				
				if(validityUseStarttime!=null&&validityUserEndtime!=null){
					template.setValidityUseStarttime(validityUseStarttime);
					template.setValidityUserEndtime(validityUserEndtime);
				}
				if(useCfg!=null&&1==useCfg){
					template.setUseCfg(useCfg);
					template.setUseOverAmount(useOverAmount);
				}
				template.setStoreId(store.getId());
				template.setStoreName(store.getName());
				int res = sellerCardCouponService.insertSelective(template);
				if (res >0) {
					result.setFlag(0);
					result.setReason("新增卡券成功！");
				}else{
					result.setFlag(1);
					result.setReason("新增卡券失败！");
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setFlag(1);
			result.setReason("新增卡券失败");
			return result;
		}
		
	}
	
	/**
	 *app卡券模板列表
	 */
	@RequestMapping("/list")
	@ResponseBody
	public AppResult list(HttpServletRequest request,HttpServletResponse response,Long sellerId,Integer pageNum,Integer pageSize){
		AppResult result = new AppResult();
		try {
			if (sellerId == null) {
				result.setFlag(1);
				result.setReason("商户编号错误！");
				return result;
			 }
			SellerInfo seller = sellerService.getSellerById(sellerId);
			if (seller == null) {
				result.setFlag(1);
				result.setReason("商户不存在！");
				return result;
			}
			//查询未删除的卡券模板
			Page<CardCouponTemplate> statements = sellerCardCouponService.querySellerCardCouponBySellerId(sellerId,false,3, pageNum, pageSize);
			result.setFlag(0);
			result.setReason("查询成功");
			JSONObject json = new JSONObject();
			json.put("list", statements.getResult());
			json.put("ishasmore", statements.getPages()>pageNum);
			result.setData(json);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setFlag(1);
			result.setReason("查询失败");
			return result;
		}
		
	}
	
	
	/**
	 *app删除卡券
	 */
	@RequestMapping("/del")
	@ResponseBody
	public AppResult delById(Long sellerId,Long cardCouponId) throws Exception{
		AppResult result = new AppResult();
		if (sellerId == null) {
			result.setFlag(1);
			result.setReason("商户编号错误！");
			return result;
		 }
		SellerInfo seller = sellerService.getSellerById(sellerId);
		if (seller == null) {
			result.setFlag(1);
			result.setReason("商户不存在！");
			return result;
		}
		if (cardCouponId == null) {
			result.setFlag(1);
			result.setReason("卡券参数不能为空!");
			return result;	
		}
		boolean flag = sellerCardCouponService.deleteByPrimaryKey(sellerId,true,cardCouponId);
		if (!flag) {
			result.setFlag(1);
			result.setReason("删除卡券信息失败");
		}else{
			result.setFlag(0);
			result.setReason("删除卡券信息成功！");
		}
		return result;	
	}
	
	
	/**
	 *app扫码核销卡券
	 *
	 *只能核销礼品券
	 *
	 * 折扣券，代金券只能在用户支付时选择该卡券使用
	 *@param 商户id
	 *@param 用户卡券code
	 *
	 */
	@RequestMapping("/coupon/scanner")
	@ResponseBody
	public AppResult couponScanner(Long sellerId,String code) throws Exception{
		AppResult result = new AppResult();
		if (sellerId == null) {
			result.setFlag(1);
			result.setReason("商户编号错误！");
			return result;
		 }
		SellerInfo seller = sellerService.getSellerById(sellerId);
		if (seller == null) {
			result.setFlag(1);
			result.setReason("商户不存在！");
			return result;
		}
		if (code == null) {
			result.setFlag(1);
			result.setReason("卡券code不能为空!");
			return result;	
		}
		
	    result = sellerCardCouponService.updateByCouponCode(sellerId,code);
		return result;	
	}
	
	/**
	 *app商户核销已使用卡券列表
	 */
	@RequestMapping("/validateCardCouponList")
	@ResponseBody
	public AppResult validateCardCouponList(HttpServletRequest request,HttpServletResponse response,Long sellerId,Integer pageNum,Integer pageSize){
		AppResult result = new AppResult();
		try {
			if (sellerId == null) {
				result.setFlag(1);
				result.setReason("商户编号错误！");
				return result;
			 }
			SellerInfo seller = sellerService.getSellerById(sellerId);
			if (seller == null) {
				result.setFlag(1);
				result.setReason("商户不存在！");
				return result;
			}
			Page<CardCoupon> statements = sellerCardCouponService.querySellerValidateCardCoupon(sellerId, pageNum, pageSize);
			result.setFlag(0);
			result.setReason("查询成功");
			JSONObject json = new JSONObject();
			json.put("list", statements.getResult());
			json.put("ishasmore", statements.getPages()>pageNum);
			result.setData(json);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setFlag(1);
			result.setReason("查询失败");
			return result;
		}
		
	}
	
	
	
	/**
	 *app查询未投放的卡券模板列表
	 */
	@RequestMapping("/notDeliveryList")
	@ResponseBody
	public AppResult notDeliveryList(HttpServletRequest request,HttpServletResponse response,Long sellerId,Integer pageNum,Integer pageSize){
		AppResult result = new AppResult();
		try {
			if (sellerId == null) {
				result.setFlag(1);
				result.setReason("商户编号错误！");
				return result;
			 }
			SellerInfo seller = sellerService.getSellerById(sellerId);
			if (seller == null) {
				result.setFlag(1);
				result.setReason("商户不存在！");
				return result;
			}
			//查询未投放的卡券模板
			Page<CardCouponTemplate> statements = sellerCardCouponService.querySellerCardCouponBySellerId(sellerId,true,0, pageNum, pageSize);
			result.setFlag(0);
			result.setReason("查询成功");
			JSONObject json = new JSONObject();
			json.put("list", statements.getResult());
			json.put("ishasmore", statements.getPages()>pageNum);
			result.setData(json);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setFlag(1);
			result.setReason("查询失败");
			return result;
		}
		
	}
	
	
	/**
	 *app查询周边/自营投放的卡券模板列表
	 *
	 * 要按关联表排序顺序返回
	 */
	@RequestMapping("/selfDeliveryList")
	@ResponseBody
	public AppResult selfDeliveryList(HttpServletRequest request,HttpServletResponse response,Long sellerId,Integer cfg,Integer pageNum,Integer pageSize){
		AppResult result = new AppResult();
		try {
			if (sellerId == null) {
				result.setFlag(1);
				result.setReason("商户编号错误！");
				return result;
			 }
			SellerInfo seller = sellerService.getSellerById(sellerId);
			if (seller == null) {
				result.setFlag(1);
				result.setReason("商户不存在！");
				return result;
			}
			if(cfg==null||(cfg!=1&&cfg!=2)){
				result.setFlag(1);
				result.setReason("投放类型错误！");
				return result;
			}
			
			List<Map<String, String>> list = sellerCardCouponService.queryCouponTemplateBySellerIdAndStatusCfg(sellerId,cfg);
			CardDeliveryProperty cdp = sellerCardCouponService.getCardDeliveryPropertyByCfg(cfg);
			result.setFlag(0);
			result.setReason("查询成功");
			JSONObject json = new JSONObject();
			json.put("list", list);
			json.put("firstRank", cdp.getFirstRankProbability());
			json.put("secondRank", cdp.getSecondRankProbability() );
			json.put("thirdRank",  cdp.getThirdRankProbability());
			json.put("fourthRank", cdp.getFourthRankProbability() );
			result.setData(json);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setFlag(1);
			result.setReason("查询失败");
			return result;
		}
		
	}
	
	
	/**
	 * app删除投放的卡券
	 * 周边/自营卡券 删除投放中的卡券(置为未投放状态)
	 * 
	 */
	@RequestMapping("/delDelivery")
	@ResponseBody
	public AppResult delDelivery(Long sellerId,Long cardCouponTemplateId) throws Exception{
		AppResult result = new AppResult();
		if (sellerId == null) {
			result.setFlag(1);
			result.setReason("商户编号错误！");
			return result;
		 }
		SellerInfo seller = sellerService.getSellerById(sellerId);
		if (seller == null) {
			result.setFlag(1);
			result.setReason("商户不存在！");
			return result;
		}
		if (cardCouponTemplateId == null) {
			result.setFlag(1);
			result.setReason("卡券参数不能为空!");
			return result;	
		}
		boolean flag = sellerCardCouponService.deleteByPrimaryKey(sellerId,false,cardCouponTemplateId);
		if (!flag) {
			result.setFlag(1);
			result.setReason("删除卡券失败");
		}else{
			result.setFlag(0);
			result.setReason("删除卡券成功！");
		}
		return result;	
	}
	
	
	/**
	 * app投放卡券 周边/自营
	 * @param cfg  投放类型  1:自营投放,2:周边投放
	 * @param cardCouponTemplateId 卡券模板id
	 * 
	 */
	@RequestMapping("/selfDelivery")
	@ResponseBody
	public AppResult selfDelivery(Long sellerId,Integer cfg,Long cardCouponTemplateId,Integer sort) throws Exception{
		AppResult result = new AppResult();
		if (sellerId == null) {
			result.setFlag(1);
			result.setReason("商户编号错误！");
			return result;
		 }
		SellerInfo seller = sellerService.getSellerById(sellerId);
		if (seller == null) {
			result.setFlag(1);
			result.setReason("商户不存在！");
			return result;
		}
		if(cfg==null||(cfg!=1&&cfg!=2)){
			result.setFlag(1);
			result.setReason("投放类型错误！");
			return result;
		}
		if (cardCouponTemplateId == null||sort==null) {
			result.setFlag(1);
			result.setReason("卡券参数不能为空!");
			return result;	
		}
		String[] retArray = sellerCardCouponService.updateCouponRelation(sellerId,cfg,cardCouponTemplateId,sort);
		if(retArray[0].equals("1")){
			result.setFlag(1);
			result.setReason(retArray[1]);
		}else{
			sellerCardCouponService.updateCouponDeliveryStatus(sellerId, cfg, Long.valueOf(retArray[1]));
			result.setFlag(0);
			result.setReason("投放卡券成功！");
		}
		return result;	
	}
	
	
	/*
	 * 投放方式
	 * 展示 公众号投放、自营投放、周边投放的数据
	 * 
	 */
	@RequestMapping("/delivery")
	@ResponseBody
	public AppResult delivery(HttpServletRequest request,HttpServletResponse response,Long sellerId,Integer pageNum,Integer pageSize){
		AppResult result = new AppResult();
		try {
			if (sellerId == null) {
				result.setFlag(1);
				result.setReason("商户编号错误！");
				return result;
			 }
			SellerInfo seller = sellerService.getSellerById(sellerId);
			if (seller == null) {
				result.setFlag(1);
				result.setReason("商户不存在！");
				return result;
			}
			JSONObject json = new JSONObject();
			List<CardCouponDelivery> list = sellerCardCouponService.insertOrqueryCardCouponDeliveryById(sellerId);
			if(list!=null&&list.size()>0){
				CardCouponDelivery cardCouponDelivery = list.get(0);
				json.put("deliveryCount", cardCouponDelivery.getPushNum());
			}else{
				json.put("deliveryCount", "0");
			}
			//查询自营卡券
			List<Map<String, String>> statements1 = sellerCardCouponService.queryCouponTemplateBySellerIdAndStatusCfg(sellerId,1);
			
			//查询周边卡券
			List<Map<String, String>> statements2 = sellerCardCouponService.queryCouponTemplateBySellerIdAndStatusCfg(sellerId,2);
			
			result.setFlag(0);
			result.setReason("查询成功");
			
			json.put("list1", statements1);
			json.put("list2", statements2);
			result.setData(json);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setFlag(1);
			result.setReason("查询失败");
			return result;
		}
		
	}
	
	
	
	/*
	 * 
	 * 直接投放卡券(向在本店消费过的用户推送)
	 * (公众号投放向用户推送消息)
	 * 
	 */
	@RequestMapping("/directDelivery")
	@ResponseBody
	public AppResult directDelivery(HttpServletRequest request,HttpServletResponse response,Long sellerId,Long cardCouponTemplateId){
		AppResult result = new AppResult();
		try {
			if (sellerId == null) {
				result.setFlag(1);
				result.setReason("商户编号错误！");
				return result;
			 }
			SellerInfo seller = sellerService.getSellerById(sellerId);
			if (seller == null) {
				result.setFlag(1);
				result.setReason("商户不存在！");
				return result;
			}
			JSONObject json = new JSONObject();
			
			StoreInfo store = storeService.queryStoreInfoBySeller(sellerId);
			
			
			List<CardCouponDelivery> list = sellerCardCouponService.insertOrqueryCardCouponDeliveryById(sellerId);
			CardCouponDelivery cardCouponDelivery = list.get(0);
			if(cardCouponDelivery.getPushNum()<=0){
				result.setFlag(1);
				result.setReason("当前商户可推送信息次数为0！");
				return result;
			}
			//查询在本店消费过的用户openId并去重  
			List<String> openIDSet = sellerCardCouponService.queryConsumerBySellerId(sellerId);
			 if(openIDSet!=null&&openIDSet.size()>1){
				 CardCouponTemplate cardCouponTemplate = sellerCardCouponService.queryCardCouponTemplateById(cardCouponTemplateId);
				 PushCardVo vo = new PushCardVo();
				 vo.setCardCouponTemplate(cardCouponTemplate);
				 vo.setStore(store);
				 vo.setOpenIds(openIDSet);
				 PushCard.pushcards.add(vo);
				 result.setFlag(0);
				 result.setReason("群发任务提交成功");
			 }else{
				 	logger.error("商户[{}] 公众号投放卡券:{} 失败 ，返回结果 DATA:{}",sellerId,cardCouponTemplateId,"未查询到在该店消费过的客户");
					result.setFlag(1);
					result.setReason("未查询到在该店消费过的微信用户");
			 }
			result.setData(json);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.setFlag(1);
			result.setReason("查询失败");
			return result;
		}
		
	}
	
	
}

