package cn.qhjys.mall.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.qhjys.mall.common.AccessToken;
import cn.qhjys.mall.entity.CardCoupon;
import cn.qhjys.mall.entity.CardCouponDelivery;
import cn.qhjys.mall.entity.CardCouponDeliveryExample;
import cn.qhjys.mall.entity.CardCouponExample;
import cn.qhjys.mall.entity.CardCouponPeripheralDisplay;
import cn.qhjys.mall.entity.CardCouponPeripheralDisplayExample;
import cn.qhjys.mall.entity.CardCouponTemplate;
import cn.qhjys.mall.entity.CardDeliveryProperty;
import cn.qhjys.mall.entity.CardDeliveryPropertyExample;
import cn.qhjys.mall.entity.CardLotteryDetail;
import cn.qhjys.mall.entity.CardLotteryDetailExample;
import cn.qhjys.mall.entity.CardUserLottery;
import cn.qhjys.mall.entity.CardUserLotteryExample;
import cn.qhjys.mall.entity.FqSellerStatement;
import cn.qhjys.mall.entity.FqSellerStatementExample;
import cn.qhjys.mall.entity.FqThirdPay;
import cn.qhjys.mall.entity.FqThirdPayExample;
import cn.qhjys.mall.entity.FqUserInfo;
import cn.qhjys.mall.entity.FqUserInfoExample;
import cn.qhjys.mall.entity.RebateOrder;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.StoreRebate;
import cn.qhjys.mall.mapper.CardCouponDeliveryMapper;
import cn.qhjys.mall.mapper.CardCouponMapper;
import cn.qhjys.mall.mapper.CardCouponPeripheralDisplayMapper;
import cn.qhjys.mall.mapper.CardCouponTemplateMapper;
import cn.qhjys.mall.mapper.CardDeliveryPropertyMapper;
import cn.qhjys.mall.mapper.CardLotteryDetailMapper;
import cn.qhjys.mall.mapper.CardUserLotteryMapper;
import cn.qhjys.mall.mapper.FqThirdPayMapper;
import cn.qhjys.mall.mapper.FqUserInfoMapper;
import cn.qhjys.mall.mapper.RebateOrderMapper;
import cn.qhjys.mall.mapper.StoreInfoMapper;
import cn.qhjys.mall.mapper.StoreRebateMapper;
import cn.qhjys.mall.service.WxMallService;
import cn.qhjys.mall.util.AppResult;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.DateUtil;
import cn.qhjys.mall.util.DateUtils;
import cn.qhjys.mall.util.MyUUID;
import cn.qhjys.mall.util.SendPushPost;
import cn.qhjys.mall.vo.CardCouponVo;
import cn.qhjys.mall.vo.PushCardVo;
import cn.qhjys.mall.vo.RebateStoreVo;
import sun.misc.FpUtils;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class WxMallServiceImpl implements WxMallService {

	@Autowired
	private StoreRebateMapper storeRebateMapper;
	
	@Autowired
	private RebateOrderMapper rebateOrderMapper;
	
	@Autowired
	private StoreInfoMapper storeInfoMapper;
	
	@Autowired
	private CardCouponMapper cardCouponMapper;
	
	@Autowired
	private CardCouponTemplateMapper cardCouponTemplateMapper;
	
	@Autowired
	private CardCouponDeliveryMapper cardCouponDeliveryMapper;
	
	@Autowired
	private FqUserInfoMapper fqUserInfoMapper;
	
	@Autowired
	private FqThirdPayMapper fqThirdPayMapper;
	
	@Autowired
	private CardUserLotteryMapper cardUserLotteryMapper;
	
	@Autowired
	private CardLotteryDetailMapper cardLotteryDetailMapper;
	
	@Autowired
	private CardCouponPeripheralDisplayMapper cardCouponPeripheralDisplayMapper;
	
	@Autowired
	private CardDeliveryPropertyMapper cardDeliveryPropertyMapper;
	
	
	@Override
	public Page<RebateStoreVo> selectStoreRebateList(Integer pageNum, Integer pageSize) {
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null || pageSize < 10)
			pageSize = 10;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentTime", new Date());
		PageHelper.startPage(pageNum, pageSize);
		Page<RebateStoreVo> page = (Page<RebateStoreVo>) storeRebateMapper.selectRebateStoreList(map);
		return page;
	}

	@Override
	public Map<String, Object> selectRebateDetailById(Long rebateId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", rebateId);
		map.put("currentTime", new Date());
		return storeRebateMapper.selectRebateDetailById(map);
	}

	@Override
	public Map<String, Object> selectOrderDetailById(Long orderId) {
		return rebateOrderMapper.selectOrderDetailById(orderId);
	}

	@Override
	public Integer selectScope(Long rebateId) {
		return storeInfoMapper.selectScope(rebateId);
	}

	//根据订单号查询商家 卡券投放状态
	public CardCouponDelivery selectSellerDeliveryStatus(Long orderId){
		RebateOrder rebateOrder = rebateOrderMapper.selectByPrimaryKey(orderId);
		
//		FqThirdPayExample example = new FqThirdPayExample();
//		example.createCriteria().andOrderIdEqualTo(orderId);
//		List<FqThirdPay> list = fqThirdPayMapper.selectByExample(example);
		//报错 所以添加判断  2017-07-25
		if(rebateOrder == null){
			CardCouponDelivery card = new CardCouponDelivery();
			card.setSellerId(110120L);
			return card;
		}
		StoreRebate storeRebate = storeRebateMapper.selectByPrimaryKey(rebateOrder.getRebateId());
		Long sellerId = storeRebate.getSellerId();
		
		CardCouponDeliveryExample dExample = new CardCouponDeliveryExample();
		dExample.createCriteria().andSellerIdEqualTo(sellerId);
		List<CardCouponDelivery>  dlist = cardCouponDeliveryMapper.selectByExample(dExample);
		if(dlist==null||dlist.size()==0){
			CardCouponDelivery card = new CardCouponDelivery();
			card.setSellerId(sellerId);
			return card;
		}else{
			return dlist.get(0);
		}
	}
	
	
	/**
	 * 我的卡包
	 */
	@Override
	public Page<CardCouponVo> getMyCardCoupon(String openId,Long couponCfg,Integer pageNum, Integer pageSize) {
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null || pageSize < 10)
			pageSize = 30;

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("openId", openId);
		if(couponCfg!=null){
			map.put("couponCfg", couponCfg);
		}
		PageHelper.startPage(pageNum, pageSize);
		Page<CardCouponVo> page = (Page<CardCouponVo>) cardCouponMapper.getMyCardCoupon(map);
		return page;
	}

	
	/**
	 * 查询卡券详情
	 */
	public CardCoupon getMyCardCouponDetail(Long cardCouponId) { 
		return cardCouponMapper.selectByPrimaryKey(cardCouponId);
	}
	
	/**
	 * 查询周边投放开启的商家
	 * @throws Exception 
	 */
	public List<Map> insertAndSelectSellerPeripheralList(Long orderId) throws Exception{
		//查询订单 商家所在区域， 根据同一个区域 查找开启的商家
		RebateOrder rebateOrder = rebateOrderMapper.selectByPrimaryKey(orderId);
		StoreRebate storeRebate = storeRebateMapper.selectByPrimaryKey(rebateOrder.getRebateId());
		StoreInfo store = storeInfoMapper.selectByPrimaryKey(storeRebate.getStoreId());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cityId", store.getCity());
		params.put("sellerId", store.getSellerId());
		if (store.getCategoryDetails() != null && !store.getCategoryDetails().contains("其他")) {
			params.put("categoryId", store.getCategoryId());
		}
		List<Map> list = cardCouponDeliveryMapper.selectSellerPeripheralList(params);
		
		//增加周边商家的展示推送次数
		Date date0 = BaseUtil.getTimeDate(new Date());
		for(int i=0;i<list.size();i++){
			Map map = list.get(i);
			long sellerId = Long.valueOf(map.get("sellerId").toString());
			
			CardCouponPeripheralDisplayExample cardCouponPeripheralDisplayExample = new CardCouponPeripheralDisplayExample();
			cardCouponPeripheralDisplayExample.createCriteria().andSellerIdEqualTo(sellerId).andCreateTimeEqualTo(date0);
			List<CardCouponPeripheralDisplay>  perList = cardCouponPeripheralDisplayMapper.selectByExample(cardCouponPeripheralDisplayExample);
			if (perList!=null && perList.size()>0) {
				CardCouponPeripheralDisplay display = perList.get(0);
				display.setPeripheralDisplayNum(display.getPeripheralDisplayNum()+1);
				cardCouponPeripheralDisplayMapper.updateByPrimaryKeySelective(display);
			}else{
				CardCouponPeripheralDisplay display = new CardCouponPeripheralDisplay();
				display.setSellerId(sellerId);
				display.setCreateTime(date0);
				display.setPeripheralDisplayNum(1);
				cardCouponPeripheralDisplayMapper.insertSelective(display);
			}
		}
		return list;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CardCoupon selectUserAvailableCardCoupon(BigDecimal totalPay,Long storeId,String openId){
		Map params = new HashMap<>();
		params.put("storeId", storeId);
		params.put("openId", openId);
		List<CardCoupon>  list = cardCouponMapper.selectUserAvailableCardCoupon(params);
		Map<Double,Integer> map = new TreeMap<Double,Integer>();
		
		//计算出最优惠的卡券
		for(int i=0;i<list.size();i++){
			CardCoupon card = list.get(i);
			//满多少金额才可以使用    0不限制
			BigDecimal useOverAmount = card.getUseOverAmount();
			if(useOverAmount.doubleValue()>0){
				if(totalPay.doubleValue()<useOverAmount.doubleValue()){ //不满足 满足多少金额使用，跳过
					continue;
				}
			}
			int curHour = DateUtils.getHour(new Date());
			Integer uSHour = card.getValidityUseStarttime();
			Integer uEHour = card.getValidityUserEndtime();
			if(uSHour!=0&&uEHour!=0){ 	//限制使用时段 
				if(curHour<uSHour|| curHour>uEHour){
					continue;
				}
			}
			Integer couponCfg = card.getTemplateCouponCfg();
			//1:代金券   2:折扣券
			if(couponCfg==1){
				BigDecimal discountPay = new BigDecimal(card.getTemplateCouponAmount());
				Double tempPay = totalPay.subtract(discountPay).doubleValue();   
				map.put(tempPay, i);
			}else if(couponCfg==2){
				BigDecimal discount = new BigDecimal(card.getTemplateCouponAmount());//折扣 0-100 之间的整数
				discount =  discount.divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);  //转成小数 示例0.85
				Double tempPay = totalPay.multiply(discount).doubleValue();   
				map.put(tempPay, i);
			}
		}
		if(map.size()==0){
			return null;
		}else{
			Double lastKey = ((TreeMap<Double, Integer>) map).firstKey();
			Integer i = map.get(lastKey);
			return list.get(i);
		}
	}
	@Override
	public List<CardCoupon> selectUserAllCardCoupon(Long storeId,String openId){
		HashMap<String, Object> params = new HashMap<>();
		params.put("storeId", storeId);
		params.put("openId", openId);
		List<CardCoupon>  list = cardCouponMapper.selectUserAvailableCardCoupon(params);
		return list;
	}
	

	public CardUserLottery insertCardUserLotteryInfo(Integer statusCfg,String openId,Long sellerId,Long deliveryId,Long orderId){
		//每个订单只记录一次
		CardUserLotteryExample lotteryExample = new CardUserLotteryExample();
		lotteryExample.createCriteria().andOpenIdEqualTo(openId).andOrderIdEqualTo(orderId).andSellerIdEqualTo(sellerId);
		List<CardUserLottery> exitList = cardUserLotteryMapper.selectByExample(lotteryExample);
		if(exitList==null||exitList.size()==0){
				CardUserLottery cardUserLottery = new CardUserLottery();
				cardUserLottery.setCreateTime(new Date());
				cardUserLottery.setCardDeliveryId(deliveryId);
				cardUserLottery.setSellerId(sellerId);
				cardUserLottery.setStatus(0);
				cardUserLottery.setStatusCfg(statusCfg);
				cardUserLottery.setFromWhere(-1);
				cardUserLottery.setOpenId(openId);
				cardUserLottery.setOrderId(orderId);
				cardUserLottery.setShareNum(0);
				FqUserInfoExample example = new FqUserInfoExample();
				example.createCriteria().andOpenIdEqualTo(openId);
				List<FqUserInfo>  list = fqUserInfoMapper.selectByExample(example);
				if(list!=null&&list.size()>0){
					cardUserLottery.setUserId(list.get(0).getId());
				}
				//记录用户进去抽奖页面信息
				cardUserLotteryMapper.insertSelective(cardUserLottery);
				return cardUserLottery;
		}else{
			CardUserLottery lottery = exitList.get(0);
			return lottery;
		}
	}
	
	
	/**
	 * 更新用户分享次数
	 * @param openId
	 * @param orderId
	 * shareNum = shareNum+1
	 * @return
	 */
	public boolean updateCardCouponShareCount(Long userLotteryId){
	
		CardUserLottery lottery = new CardUserLottery();
		lottery.setId(userLotteryId);
		int num = cardUserLotteryMapper.updateShareNumByOpenIdAndOrderId(lottery);
	   return num > 0 ? true:false;
	}
	
	
	//查询商家 周边投放或者自营投放的 卡券模板
	public List<Map> selectSellerDeliveryCardTemplateList(Integer statusCfg,Long sellerId){
		HashMap<String,Object> params = new HashMap<>();
		params.put("sellerId", sellerId);
		params.put("statusCfg", statusCfg);
		List<Map> dataList = cardCouponDeliveryMapper.selectSellerDeliveryCardTemplateList(params);
		return  dataList;
	}
	
	//查询  周边投放或者自营投放的  抽奖概率
	public CardDeliveryProperty selectDeliveryRankProbability(Integer statusCfg){
		CardDeliveryPropertyExample example = new CardDeliveryPropertyExample();
		example.createCriteria().andDeliverTypeEqualTo(statusCfg);
		List<CardDeliveryProperty>  list = cardDeliveryPropertyMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	
	/**
	 * 
	 * 查询用户是否已抽过奖
	 * 
	 * @param userLotteryId
	 * @param openId
	 * @return
	 */
	public List<CardLotteryDetail> selectIsUserLottery(Long userLotteryId,String openId,String forOpenId,Long orderId){
		CardLotteryDetailExample example = new CardLotteryDetailExample();
		if(userLotteryId!=null&&userLotteryId>0){//主动参与抽奖的
			example.createCriteria().andUserLotteryIdEqualTo(userLotteryId)
									.andOpenIdEqualTo(openId);
		}else{//朋友分享参与抽奖的
			example.createCriteria().andForOrderIdEqualTo(orderId)
									.andOpenIdEqualTo(openId)
									.andForOpenIdEqualTo(forOpenId);
		}
		List<CardLotteryDetail> list = cardLotteryDetailMapper.selectByExample(example);
		return list;
	}
	
	
	
	
	public int insertCardLotteryDetail(Long userLotteryId,String openId,Long cardTemplateId,Long orderId, String forOpenId){
		CardLotteryDetail cardLotteryDetail = new CardLotteryDetail();
		cardLotteryDetail.setOpenId(openId);
		cardLotteryDetail.setUserLotteryId(userLotteryId);
		cardLotteryDetail.setCardTemplateId(cardTemplateId);
		FqUserInfoExample example = new FqUserInfoExample();
		example.createCriteria().andOpenIdEqualTo(openId);
		List<FqUserInfo>  list = fqUserInfoMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			cardLotteryDetail.setUserId(list.get(0).getId());
		}
		//是否帮朋友抽的
		if(StringUtils.isEmpty(forOpenId)){
			forOpenId = openId;
		}
		example = new FqUserInfoExample();
		example.createCriteria().andOpenIdEqualTo(forOpenId);
		list = fqUserInfoMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			cardLotteryDetail.setForUserId(list.get(0).getId());
		}
		cardLotteryDetail.setForOpenId(forOpenId);
		cardLotteryDetail.setForOrderId(orderId);
		cardLotteryDetail.setCreateTime(new Date());
		int num = cardLotteryDetailMapper.insertSelective(cardLotteryDetail);
		return num;
	}
	
	/**
	 * 查询朋友帮自己抽到的奖品
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, List<Map>> selectUserFriendLottery(Long orderId,String openId,Long userLotteryId){
		
		Map params = new HashMap<>();
		params.put("openId", openId);
		params.put("orderId", orderId);
		params.put("userLotteryId", userLotteryId);
		List<Map>  list = cardLotteryDetailMapper.selectUserFriendLotteryDetail(params);
		
		//分组计算出 每种卡券有多少人帮抽到 分别是谁
		Map<String,List<Map>> resultMap = new HashMap<String,List<Map>>();
		for(Map map : list){
			String name = map.get("name").toString();
			if(resultMap.containsKey(name)){
				resultMap.get(name).add(map);
			}else{
				List<Map> mList = new ArrayList<Map>();
				mList.add(map);
				resultMap.put(name,mList);
			}
		}
		return resultMap;
	}
	
	
	/**
	 * 领取卡券-公众号直接领取
	 * @param openId
	 * @param cardCoupontemplateId
	 * @param source
	 * @return
	 */
	public AppResult insertUserCardCoupon(String openId,Long cardCoupontemplateId,Integer source,Long userLotteryId,Integer isFriend,String forOpenId){
		AppResult result = new AppResult();
		CardCouponTemplate	template = cardCouponTemplateMapper.selectByPrimaryKey(cardCoupontemplateId);
		
		//此卡券模板已被商家删除，不能领取
		if(template.getStatusCfg()==3){
			result.setFlag(1);
			result.setReason("此卡券已被商家删除,不能领取咯!");
			return result;
		}
		
		//判断领券限制,查询用户是否领取过且未使用的数量
		CardCouponExample cardExample = new CardCouponExample();
		cardExample.createCriteria().andOpenIdEqualTo(openId).andTemplateIdEqualTo(cardCoupontemplateId);
//		.andStatusCfgEqualTo(0);
		int count = cardCouponMapper.countByExample(cardExample);

		//用户领取这个卡券的次数    要  小于商家设定的领券限制
		if(count >= template.getCountLimit()){
			result.setFlag(1);
			result.setReason("领取的本张卡券超过商家设定的次数啦!");
			return result;
		}
		
		//查询用户是否兑换抽奖的卡券
		if(source==1|| source==2){
			CardLotteryDetailExample example = new CardLotteryDetailExample();
			if(isFriend!=null&&isFriend==1){
				example.createCriteria().andUserLotteryIdEqualTo(userLotteryId)
				 						.andOpenIdEqualTo(forOpenId)
				 						 .andForOpenIdEqualTo(openId)
			    						.andStatusCfgEqualTo(1); //已兑换 
			}else{
				example.createCriteria().andUserLotteryIdEqualTo(userLotteryId)
									    .andOpenIdEqualTo(openId)
									    .andStatusCfgEqualTo(1); //已兑换 
			}
			
			Long num = cardLotteryDetailMapper.countByExample(example);
			if(num>0){
				result.setFlag(1);
				result.setReason("中奖的卡券您已兑换过啦!");
				return result;
			}
			
			//设置卡券已兑换
			example = new CardLotteryDetailExample();
			if(isFriend!=null&&isFriend==1){
				example.createCriteria().andUserLotteryIdEqualTo(userLotteryId)
				 						.andOpenIdEqualTo(forOpenId)
				 						 .andForOpenIdEqualTo(openId);
			}else{
				example.createCriteria().andUserLotteryIdEqualTo(userLotteryId)
									    .andOpenIdEqualTo(openId);
			}
			CardLotteryDetail detail = new CardLotteryDetail();
			detail.setStatusCfg(1);
			cardLotteryDetailMapper.updateByExampleSelective(detail, example);
		}
	
		
		CardCoupon record = new CardCoupon();
		record.setReceiveDate(new Date());
		record.setCode(MyUUID.getMyUUID());
		record.setGetWay(source);
		record.setSellerId(template.getSellerId());
		record.setStatusCfg(0);
		record.setStoreId(template.getStoreId());
		record.setStoreName(template.getStoreName());
		
		if(template.getUseCfg()==1){ //设置使用门槛
			record.setUseOverAmount(template.getUseOverAmount());
		}
		record.setTemplateCouponCfg(template.getCouponCfg());
		record.setTemplateCouponName(template.getName());
		record.setTemplateId(cardCoupontemplateId);
		record.setTemplateUseExplain(template.getUseExplain());
		
		if(template.getCouponCfg()==1){//代金券
			record.setTemplateCouponAmount(""+template.getAmount());
		}
		if(template.getCouponCfg()==2){//折扣券
			record.setTemplateCouponAmount(""+template.getDiscount());
		}
		
		FqUserInfoExample example = new FqUserInfoExample();
		example.createCriteria().andOpenIdEqualTo(openId);
		List<FqUserInfo>  list = fqUserInfoMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			record.setUserId(list.get(0).getId());
		}
		record.setOpenId(openId);
		
		if(template.getValidityCfg()==1){//领取多少日内有效
			Date date = DateUtils.getDateAfter(new Date(), template.getValidityDay());
			record.setValidityStarttime(new Date());
			record.setValidityEndtime(date);
		}else{
			record.setValidityStarttime(template.getValidityStarttime());
			record.setValidityEndtime(template.getValidityEndtime());
		}
		record.setValidityUseStarttime(template.getValidityUseStarttime());
		record.setValidityUserEndtime(template.getValidityUserEndtime());
		//record.setZxingImg("");
		
		int num = cardCouponMapper.insertSelective(record);
		if(num>0){
			result.setFlag(0);
			result.setReason("领取成功");
		}else{
			result.setFlag(1);
			result.setReason("领取失败");
		}
		return result;
	}
	
	
	@Override
	public boolean insertPushCard(PushCardVo cardVo) {
		CardCouponTemplate	template = cardVo.getCardCouponTemplate();
		
		if(template.getStatusCfg()==3){
			return false;
		}
		int num = 0;
		List<String> openIds = cardVo.getOpenIds();
		Date now = new Date();
		JSONObject data2 = new JSONObject();
		
		data2.put("template_id", "8ikQXmMNE69ptWGO6lStZoK_Y5AOfdy-qoBqSWtpcuo");
		JSONObject data3 = new JSONObject();
		JSONObject params1;
		params1 = new JSONObject();
		params1.put("value", "恭喜你获得的一张["+template.getName()+"]券");
		params1.put("color", "#fb784b");
		data3.put("first", params1);
		
		params1 = new JSONObject();
		params1.put("value", "大人您嘞");
		params1.put("color", "#000000");
		data3.put("keyword1", params1);
		
		params1 = new JSONObject();
		params1.put("value", template.getName());
		params1.put("color", "#000000");
		data3.put("keyword2", params1);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		params1 = new JSONObject();
		params1.put("value",sdf.format(now));
		params1.put("color", "#000000");
		data3.put("keyword3", params1);
		
		params1 = new JSONObject();
		params1.put("value", "\n快点击查看您的卡包吧~");
		params1.put("color", "#fb784b");
		data3.put("remark", params1);
		
		data2.put("data", data3);
		data2.put("url", ConstantsConfigurer.getProperty("web_url")+"/wxMall/jump/getMyCardCoupon.do");
		
		
		for (int i = 0; i < openIds.size(); i++) {
			String openId = openIds.get(i);
			//判断领券限制,查询用户是否领取过且未使用的数量
			CardCouponExample cardExample = new CardCouponExample();
			cardExample.createCriteria().andOpenIdEqualTo(openId).andTemplateIdEqualTo(template.getId());
//			.andStatusCfgEqualTo(0);
			int count = cardCouponMapper.countByExample(cardExample);
			
			//用户领取这个卡券的次数    要  小于商家设定的领券限制
			if(count < template.getCountLimit()){

				CardCoupon record = new CardCoupon();
				record.setReceiveDate(now);
				record.setCode(MyUUID.getMyUUID());
				record.setGetWay(0);
				record.setSellerId(template.getSellerId());
				record.setStatusCfg(0);
				record.setStoreId(template.getStoreId());
				record.setStoreName(template.getStoreName());
				
				if(template.getUseCfg()==1){ //设置使用门槛
					record.setUseOverAmount(template.getUseOverAmount());
				}
				record.setTemplateCouponCfg(template.getCouponCfg());
				record.setTemplateCouponName(template.getName());
				record.setTemplateId(template.getId());
				record.setTemplateUseExplain(template.getUseExplain());
				
				if(template.getCouponCfg()==1){//代金券
					record.setTemplateCouponAmount(""+template.getAmount());
				}
				if(template.getCouponCfg()==2){//折扣券
					record.setTemplateCouponAmount(""+template.getDiscount());
				}
				
				FqUserInfoExample example = new FqUserInfoExample();
				example.createCriteria().andOpenIdEqualTo(openId);
				List<FqUserInfo>  list = fqUserInfoMapper.selectByExample(example);
				if(list!=null&&list.size()>0){
					record.setUserId(list.get(0).getId());
				}
				record.setOpenId(openId);
				
				if(template.getValidityCfg()==1){//领取多少日内有效
					Date date = DateUtils.getDateAfter(new Date(), template.getValidityDay());
					record.setValidityStarttime(new Date());
					record.setValidityEndtime(date);
				}else{
					record.setValidityStarttime(template.getValidityStarttime());
					record.setValidityEndtime(template.getValidityEndtime());
				}
				record.setValidityUseStarttime(template.getValidityUseStarttime());
				record.setValidityUserEndtime(template.getValidityUserEndtime());
				//record.setZxingImg("");
				 num += cardCouponMapper.insertSelective(record);
				 data2.put("touser", openId);
				 String SEND_TEMP_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+AccessToken.getAccessToken();
				 String result = SendPushPost.sendPost(SEND_TEMP_MESSAGE, data2.toJSONString());
			}
		}
		return num > 0?true:false;
	}

	@Override
	public CardCouponTemplate getCardCouponTemplateById(Long id) {
		return cardCouponTemplateMapper.selectByPrimaryKey(id);
	}
	
	
}
