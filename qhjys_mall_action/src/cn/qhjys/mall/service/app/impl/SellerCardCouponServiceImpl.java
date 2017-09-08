package cn.qhjys.mall.service.app.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.qhjys.mall.entity.CardCoupon;
import cn.qhjys.mall.entity.CardCouponDelivery;
import cn.qhjys.mall.entity.CardCouponDeliveryExample;
import cn.qhjys.mall.entity.CardCouponDeliveryTemplateRelation;
import cn.qhjys.mall.entity.CardCouponDeliveryTemplateRelationExample;
import cn.qhjys.mall.entity.CardCouponExample;
import cn.qhjys.mall.entity.CardCouponPeripheralDisplay;
import cn.qhjys.mall.entity.CardCouponPeripheralDisplayExample;
import cn.qhjys.mall.entity.CardCouponTemplate;
import cn.qhjys.mall.entity.CardCouponTemplateExample;
import cn.qhjys.mall.entity.CardDeliveryCount;
import cn.qhjys.mall.entity.CardDeliveryProperty;
import cn.qhjys.mall.entity.CardDeliveryPropertyExample;
import cn.qhjys.mall.mapper.CardCouponDeliveryMapper;
import cn.qhjys.mall.mapper.CardCouponDeliveryTemplateRelationMapper;
import cn.qhjys.mall.mapper.CardCouponMapper;
import cn.qhjys.mall.mapper.CardCouponPeripheralDisplayMapper;
import cn.qhjys.mall.mapper.CardCouponTemplateMapper;
import cn.qhjys.mall.mapper.CardDeliveryCountMapper;
import cn.qhjys.mall.mapper.CardDeliveryPropertyMapper;
import cn.qhjys.mall.mapper.CardLotteryDetailMapper;
import cn.qhjys.mall.mapper.CardUserLotteryMapper;
import cn.qhjys.mall.mapper.FqThirdPayMapper;
import cn.qhjys.mall.service.app.SellerCardCouponService;
import cn.qhjys.mall.util.AppResult;
import cn.qhjys.mall.util.DateUtils;

/**
 * @
 * @author Administrator
 *
 */
@Service
public class SellerCardCouponServiceImpl implements SellerCardCouponService {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CardCouponTemplateMapper cardCouponTemplateMapper;
	
	@Autowired
	private CardCouponDeliveryTemplateRelationMapper cardCouponDeliveryTemplateRelationMapper;
	
	@Autowired
	private CardCouponDeliveryMapper cardCouponDeliveryMapper;
	
	@Autowired
	private CardCouponMapper cardCouponMapper;
	
	@Autowired
	private CardDeliveryPropertyMapper  cardDeliveryPropertyMapper;
	
	@Autowired
	private FqThirdPayMapper  fqThirdPayMapper;
	
	@Autowired
	private CardUserLotteryMapper cardUserLotteryMapper;

	@Autowired
	private CardLotteryDetailMapper cardLotteryDetailMapper;
	
	@Autowired
	private CardDeliveryCountMapper  cardDeliveryCountMapper;
	
	@Autowired
	private CardCouponPeripheralDisplayMapper cardCouponPeripheralDisplayMapper;
	
	@Override
	public int insertSelective(CardCouponTemplate template) {
		return cardCouponTemplateMapper.insertSelective(template);
	}

	/**
	 * 根据ID查询卡券模板详情
	 * @param cardCouponTemplateId
	 * @return
	 */
	public CardCouponTemplate queryCardCouponTemplateById(Long cardCouponTemplateId){
		return cardCouponTemplateMapper.selectByPrimaryKey(cardCouponTemplateId);
	}
	
	
	@Override
	public Page<CardCouponTemplate> querySellerCardCouponBySellerId(Long sellerId,boolean equals,Integer statusCfg,Integer pageNum, Integer pageSize)
			throws Exception {
		if (sellerId == null) {
			return null;
		}
		CardCouponTemplateExample example = new  CardCouponTemplateExample();
		cn.qhjys.mall.entity.CardCouponTemplateExample.Criteria criteria = example.createCriteria();
		criteria.andSellerIdEqualTo(sellerId);
		if(!equals){
			criteria.andStatusCfgNotEqualTo(statusCfg);
		}else{
			criteria.andStatusCfgEqualTo(statusCfg);
		}
		PageHelper.startPage(pageNum, pageSize);
		example.setOrderByClause("id desc");
		Page<CardCouponTemplate> statements = (Page<CardCouponTemplate>) cardCouponTemplateMapper.selectByExample(example);
		return statements;
	}

	
	/**
	 * 查询周边/自营投放 的卡券模板 并按关联表排序返回
	 * 
	 */
	public List<Map<String, String>> queryCouponTemplateBySellerIdAndStatusCfg(Long sellerId,Integer statusCfg) {
		if (sellerId == null) {
			return null;
		}
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("sellerId", sellerId);
		paramMap.put("statusCfg", statusCfg);
		
		List<Map<String, String>>  list = cardCouponTemplateMapper.queryCouponTemplateBySellerIdAndStatusCfg(paramMap);
		if(list==null||list.size()<=0){
			return null;
		}
		return list;
	}
	
	/**
	 *  @param isDel
	 *  
	 *  情况一 isDel = true
	 *  1.删除卡券  statusCfg = 3    将该卡券置为删除状态  	删除关联表并更改对应投放状态为未启用
	 *  
	 *  情况二 isDel = false						
	 *  2.周边/自营卡券-删除投放中的卡券  statusCfg = 0   将该卡券置为未投放状态  	删除关联表，并更改对应投放状态为未启用
	 *  
	 */
	@Override
	public boolean deleteByPrimaryKey(Long sellerId,boolean isDel, Long cardCouponTemplateId) {
		
		CardCouponTemplate template =  cardCouponTemplateMapper.selectByPrimaryKey(cardCouponTemplateId);
		
		Integer statusCfg  = template.getStatusCfg(); //  1:自营投放,2:周边投放
		template = new CardCouponTemplate();
		template.setId(cardCouponTemplateId);
		
		if(isDel){
			template.setStatusCfg(3);    //删除状态
		}else{
			template.setStatusCfg(0);    //未投放状态
		}
		int num = cardCouponTemplateMapper.updateByPrimaryKeySelective(template);

		if(num>0){
			CardCouponDeliveryTemplateRelationExample example = new CardCouponDeliveryTemplateRelationExample();
			example.createCriteria().andSellerIdEqualTo(sellerId).andCardCouponTemplateIdEqualTo(cardCouponTemplateId);
			List<CardCouponDeliveryTemplateRelation> list = cardCouponDeliveryTemplateRelationMapper.selectByExample(example);
			//删除投放关联表中该卡券信息  并将该卡券所属投放状态置为未启用 
			if(list!=null){ 
				for(CardCouponDeliveryTemplateRelation relation:list){
					Long id = relation.getId();
					Long deliveryId = relation.getCardCouponDeliveryId();
					if(1==statusCfg){
						CardCouponDelivery cardCouponDelivery = new CardCouponDelivery();
						cardCouponDelivery.setId(deliveryId);
						cardCouponDelivery.setProprietaryStatus(0);   //自营投放0,未启用
						cardCouponDeliveryMapper.updateByPrimaryKeySelective(cardCouponDelivery);
					}else if(2==statusCfg){
						CardCouponDelivery cardCouponDelivery = new CardCouponDelivery();
						cardCouponDelivery.setId(deliveryId);
						cardCouponDelivery.setPeripheralStatus(0);    //周边投放状态(0,未启用
						cardCouponDeliveryMapper.updateByPrimaryKeySelective(cardCouponDelivery);
					}
					cardCouponDeliveryTemplateRelationMapper.deleteByPrimaryKey(id);
				}
			}
			return true;
		}else{
			return false;
		}
		
	}

     /**
      * 扫码核销用户卡券
      * 
      * 只能核销礼品券
	  *
	  *  折扣券，代金券只能在用户支付时选择该卡券使用
      * 
      * @param sellerId
      * @param cardCouponTemplateId
      * @return
      */
	public AppResult updateByCouponCode(Long sellerId,String code) {
		AppResult result = new AppResult();
		CardCouponExample cardCouponExample = new CardCouponExample();
		cardCouponExample.createCriteria().andCodeEqualTo(code).andSellerIdEqualTo(sellerId);
		
		
		List<CardCoupon> cardCoupons = cardCouponMapper.selectByExample(cardCouponExample);
		if(cardCoupons==null){
			result.setFlag(1);
			result.setReason("未查询到该卡券信息");
			return result;
		}
		CardCoupon cardCoupon = cardCoupons.get(0);
		
		//验证券码有效性 
		Integer couponCfg = cardCoupon.getTemplateCouponCfg();
		if(couponCfg!=0){ //不是礼品券
			result.setFlag(1);
			result.setReason("商家只能核销礼品券");
			return result;
		}
		Integer statusCfg = cardCoupon.getStatusCfg();
		if(statusCfg==null|| statusCfg!=0 ){ //卡券状态只能为 未使用状态
			result.setFlag(1);
			result.setReason("该卡券状态为已使用或不可用");
			return result;
		}
		
		Date validityStarttime  = cardCoupon.getValidityStarttime();
		Date validityEndtime  = cardCoupon.getValidityEndtime();
		
		Date currentDate = new Date();
		if(validityStarttime.getTime() > currentDate.getTime() || validityEndtime.getTime() < currentDate.getTime()){
			result.setFlag(1);
			result.setReason("该卡券不在有效使用期限内");
			return result;
		}
		
		cardCoupon = new CardCoupon();
		cardCoupon.setStatusCfg(1);  //1已使用
		cardCoupon.setValidateCfg(0); //0:扫码核销 
		cardCoupon.setValidateDate(new Date());
		 int num = cardCouponMapper.updateByExampleSelective(cardCoupon, cardCouponExample);
		 if(num>0){
			 result.setFlag(0);
			 result.setReason("卡券核销成功");
			 return result;
		 }else{
			 result.setFlag(1);
			 result.setReason("卡券核销失败");
			 return result;
		 }
		
	}
	
	/**
	 * 商户已核销的卡券列表
	 */
	public Page<CardCoupon> querySellerValidateCardCoupon(Long sellerId,Integer pageNum, Integer pageSize)
			throws Exception {
		if (sellerId == null) {
			return null;
		}
		CardCouponExample example = new  CardCouponExample();
		example.createCriteria().andSellerIdEqualTo(sellerId).andStatusCfgEqualTo(1);  //1已使用
		example.setOrderByClause("validate_date desc");
		PageHelper.startPage(pageNum, pageSize);
		Page<CardCoupon> statements = (Page<CardCoupon>) cardCouponMapper.selectByExample(example);
		return statements;
	}
	
	
	/**
	 * 根据sellerId查询商户投放信息
	 */
	public List<CardCouponDelivery> insertOrqueryCardCouponDeliveryById(Long sellerId){
		CardCouponDeliveryExample cardCouponDeliveryExample = new CardCouponDeliveryExample();
		cardCouponDeliveryExample.createCriteria().andSellerIdEqualTo(sellerId);
		cardCouponDeliveryExample.setOrderByClause("id desc");
		List<CardCouponDelivery> list  = cardCouponDeliveryMapper.selectByExample(cardCouponDeliveryExample);
		
		//商家投放信息未配置 则新增一条
		if(list==null||list.size()<=0){
			 CardCouponDelivery delivery = new CardCouponDelivery();
			 delivery.setSellerId(sellerId);
			 delivery.setPeripheralStatus(0);
			 delivery.setProprietaryStatus(0);
			 CardDeliveryPropertyExample propertyExample = new CardDeliveryPropertyExample();
			 propertyExample.createCriteria().andDeliverTypeEqualTo(1);
			 List<CardDeliveryProperty>	propertyList = cardDeliveryPropertyMapper.selectByExample(propertyExample);
			 if(propertyList==null||propertyList.size()==0){
					delivery.setPushNum(2); //未查询到推送总设定次数 时，设定为2次
			 }else{
					CardDeliveryProperty cardDeliveryProperty = propertyList.get(0);
					//推送总设定次数从 card_delivery_property 表 查询 delivery_num字段
					delivery.setPushNum(cardDeliveryProperty.getDeliveryNum());
			}
			int num = insertCardCouponDelivery(delivery);
			if(num>0){
				 list.add(delivery);
			}
		}
		return list;
	}
	
	/**
	 * 更新商户投放次数-1
	 */
	public int updateReducePushNum(Long sellerId){
		int num  = cardCouponDeliveryMapper.updateReducePushNum(sellerId);
		return num;
	}
	
	/**
	 * 更新所有商户重置投放次数
	 */
	 public int  updateAllSellerDeliveryPushNum(){
		 CardCouponDelivery delivery = new CardCouponDelivery();
		 CardDeliveryPropertyExample propertyExample = new CardDeliveryPropertyExample();
		 propertyExample.createCriteria().andDeliverTypeEqualTo(1);
		 List<CardDeliveryProperty>	propertyList = cardDeliveryPropertyMapper.selectByExample(propertyExample);
		 if(propertyList==null||propertyList.size()==0){
				delivery.setPushNum(2); //未查询到推送总设定次数 时，设定为2次
		 }else{
				CardDeliveryProperty cardDeliveryProperty = propertyList.get(0);
				//推送总设定次数从 card_delivery_property 表 查询 delivery_num字段
				delivery.setPushNum(cardDeliveryProperty.getDeliveryNum());
		}
		CardCouponDeliveryExample deliveryExample = new CardCouponDeliveryExample(); 
		int num = cardCouponDeliveryMapper.updateByExampleSelective(delivery, deliveryExample);
		return num;
	 }
	
	
	/**
	 *  周边/自营投放卡券
	 *  投放卡券
	 */
	public String[] updateCouponRelation(Long sellerId,Integer cfg, Long cardCouponTemplateId,Integer sort) {
		String[] retArray = new String[]{"1",""};
		
		
		 //2.根据投放类型，查询投放关联表 中卡券是否 <=4     大于4不可添加
		 List<CardCouponDelivery> list  = insertOrqueryCardCouponDeliveryById(sellerId);
		 Long deliveryId;
		 CardCouponDelivery cardCouponDelivery = list.get(0);
		 deliveryId = cardCouponDelivery.getId();
		 
		 CardCouponDeliveryTemplateRelationExample relationExample = new CardCouponDeliveryTemplateRelationExample();
		 relationExample.createCriteria().andSellerIdEqualTo(sellerId).andCardCouponDeliveryIdEqualTo(deliveryId).andStatusCfgEqualTo(cfg);
		 int count = cardCouponDeliveryTemplateRelationMapper.countByExample(relationExample);
		 if(count>=4){
			retArray[0] = "1";
			retArray[1] = "商家投放卡券数据已达4条";
			return retArray;
		 }
		
		//判断该投放id 商家  卡券模板id 记录是否存在 否则不给添加相同记录
	     relationExample = new CardCouponDeliveryTemplateRelationExample();
		relationExample.createCriteria().andSellerIdEqualTo(sellerId)
										.andCardCouponDeliveryIdEqualTo(deliveryId)
										.andCardCouponTemplateIdEqualTo(cardCouponTemplateId);
		int exitsNum = cardCouponDeliveryTemplateRelationMapper.countByExample(relationExample);
		if(exitsNum>0){
			retArray[0] = "1";
			retArray[1] = "商家该卡券已经在投放中不允许添加相同记录";
			return retArray;
		}
		//3.更改该卡券为自营投放或周边投放
	    CardCouponTemplate template = new CardCouponTemplate();
		template = new CardCouponTemplate();
		template.setId(cardCouponTemplateId);
		template.setStatusCfg(cfg);  //  1:自营投放,2:周边投放
		int num = cardCouponTemplateMapper.updateByPrimaryKeySelective(template);
		if( num >0 ){
			//4.添加关联表数据
			CardCouponDeliveryTemplateRelation relation = new CardCouponDeliveryTemplateRelation();
			relation.setSellerId(sellerId);
			relation.setCardCouponDeliveryId(deliveryId);
			relation.setCardCouponTemplateId(cardCouponTemplateId);
			relation.setStatusCfg(cfg); //设置投放类型(自营/周边)
			relation.setSort(sort);
			int insertNum = cardCouponDeliveryTemplateRelationMapper.insertSelective(relation);
			if(insertNum>0){
				retArray[0] = "0";
				retArray[1] = String.valueOf(deliveryId);
				return retArray;
			}
		}
		return retArray;
	}
	
	
	/**
	 * 判断投放关联表中是否够4条卡券，达到更改投放状态为启用
	 * @param sellerId
	 * @param cfg
	 * @param deliveryId
	 * @param cardCouponTemplateId
	 * @return
	 */
	public boolean updateCouponDeliveryStatus(Long sellerId,Integer cfg,Long deliveryId) {
		CardCouponDeliveryTemplateRelationExample relationExample = new CardCouponDeliveryTemplateRelationExample();
		relationExample.createCriteria().andSellerIdEqualTo(sellerId).andCardCouponDeliveryIdEqualTo(deliveryId).andStatusCfgEqualTo(cfg);
		//5.查询是否已够4条记录  是，更改对应投放状态为已启用
		int count = cardCouponDeliveryTemplateRelationMapper.countByExample(relationExample);
		if(count>=4){
			if(1==cfg){
				CardCouponDelivery  cardCouponDelivery = new CardCouponDelivery();
				cardCouponDelivery.setId(deliveryId);
				cardCouponDelivery.setProprietaryStatus(1);   //自营投放1,启用
				int num = cardCouponDeliveryMapper.updateByPrimaryKeySelective(cardCouponDelivery);
				if(num>0){
					return true;
				}
			}else if(2==cfg){
				CardCouponDelivery cardCouponDelivery = new CardCouponDelivery();
				cardCouponDelivery.setId(deliveryId);
				cardCouponDelivery.setPeripheralStatus(1);    //周边投放状态(1,启用
				int num = cardCouponDeliveryMapper.updateByPrimaryKeySelective(cardCouponDelivery);
				if(num>0){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 查询在本店消费过的用户openId并去重
	 * @param sellerId
	 * @return
	 */
	public List<String> queryConsumerBySellerId(Long sellerId){
		return fqThirdPayMapper.queryConsumerBySellerId(sellerId);
	}
	
	
	
	
	/**
	 * 
	 * 每日统计商家卡券数据
	 */
	@SuppressWarnings("unchecked")
	public void updateSellerStatementByQuartz(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1); 		  //昨天
		Date yesterday = cal.getTime();
		
		//昨日商家实际交易笔数
		Map paramMap = new HashMap<>();
		paramMap.put("startDate",DateUtils.getFirstSecondOfDay(yesterday));
		paramMap.put("endDate",DateUtils.getLastSecondOfDay(yesterday));
		List<Map>  retList = fqThirdPayMapper.querySellerTransactionNumberByDate(paramMap);
		
		
		CardDeliveryPropertyExample example = new CardDeliveryPropertyExample();
		example.createCriteria().andDeliverTypeBetween(1, 2);
		List<CardDeliveryProperty> list = cardDeliveryPropertyMapper.selectByExample(example);
    	logger.info("投放数据配置信息表list="+list.size());
		
	    for(int i=0;i<retList.size();i++){
	    	Map map  = retList.get(i);
	    	Integer num = Integer.parseInt(map.get("num").toString());
	    	Long sellerId = Long.valueOf(map.get("sellerId").toString());
	    	
	    	if(list!=null&&list.size()>0){
				for(int j = 0;j<list.size();j++){
					CardDeliveryProperty property  = list.get(j);
					Integer deliverType = property.getDeliverType();
					if(deliverType==1){  	  //1,自营投放,   
						 //查询 自营投放的卡券
						CardCouponDeliveryTemplateRelationExample relationExample = new CardCouponDeliveryTemplateRelationExample();
						relationExample.createCriteria().andSellerIdEqualTo(sellerId).andStatusCfgEqualTo(1);
						
						List<CardCouponDeliveryTemplateRelation>  cardList = cardCouponDeliveryTemplateRelationMapper.selectByExample(relationExample);
						logger.info("商家sellerId 自营投放的卡券数量为"+cardList.size());
						 //等于4个卡券的才是投放中的 进行统计
						if(cardList!=null && cardList.size()==4){
							
							Integer receiveMin = property.getCardReceiveMin();
							Integer receiveMax = property.getCardReceiveMax();
							//产生receiveMin 到	receiveMax 之间的随机数			
							Integer receiveRandom = getNumBetweenRandom(receiveMin,receiveMax);
							BigDecimal receiveRandomDec = new BigDecimal(receiveRandom).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP); 
							//卡券领取数 （展示数据）= 实际交易笔数 × 百分比
							receiveRandomDec = new BigDecimal(num).multiply(receiveRandomDec);
							receiveRandom = receiveRandomDec.intValue();
							Integer receiveRandomshengyu = receiveRandom;
							// 计算分享数 start
							Integer shareMin = property.getCardShareMin();
							Integer shareMax = property.getCardShareMax();
							//产生shareMin 到	shareMax 之间的随机数			
							Integer shareRandom = getNumBetweenRandom(shareMin,shareMax);
							BigDecimal shareRandomDec = new BigDecimal(shareRandom).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);  
							//分享数（展示数据）= 实际交易笔数 × 百分比
							shareRandomDec = new BigDecimal(num).multiply(shareRandomDec);
							shareRandom = shareRandomDec.intValue();
							
							Integer shareReceiveMin = property.getCardShareReceiveMin();
							Integer shareReceiveMax = property.getCardShareReceiveMax();
							//产生shareReceiveMin 到	shareReceiveMax 之间的随机数			
							Integer shareReceiveRandom = getNumBetweenRandom(shareReceiveMin,shareReceiveMax);
							BigDecimal shareReceiveRandomDec = new BigDecimal(shareReceiveRandom).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);    
							//分享领取数（展示数据）= 活动分享数（展示数据）× 百分比
							shareReceiveRandomDec = new BigDecimal(shareRandom).multiply(shareReceiveRandomDec);
							shareReceiveRandom = shareReceiveRandomDec.intValue();
							//设置分享领取数 （展示）
							Integer shareReceiveRandomshengyu = shareReceiveRandom;
							for(int k=0;k<cardList.size();k++){
								
								CardCouponDeliveryTemplateRelation relation  = cardList.get(k);
								Long cardTemplateId = relation.getCardCouponTemplateId();
								CardDeliveryCount count = new CardDeliveryCount();
								count.setSellerId(sellerId);
								count.setDeliverType(deliverType);
								count.setCountDate(yesterday);
								count.setCardTemplateId(cardTemplateId);
								Integer temreceiveRandom = 0;
								if (k<3) {
									switch (relation.getSort()) {
									case 1:
										temreceiveRandom = receiveRandom * property.getFirstRankProbability()/100;
										break;
									case 2:
										temreceiveRandom = receiveRandom * property.getSecondRankProbability()/100;
										break;
									case 3:
										temreceiveRandom = receiveRandom * property.getThirdRankProbability()/100;
										break;
									case 4:
										temreceiveRandom = receiveRandom * property.getFourthRankProbability()/100;
										break;

									}
									receiveRandomshengyu = receiveRandomshengyu -temreceiveRandom;
								}else{
									temreceiveRandom = receiveRandomshengyu>0?receiveRandomshengyu:1;
								}
								//设置卡券领取数 （展示）
								count.setCardReceiveDisplay(temreceiveRandom);
								
								CardCouponExample cardCouponExample = new CardCouponExample();
								cardCouponExample.createCriteria().andTemplateIdEqualTo(cardTemplateId).andSellerIdEqualTo(sellerId)
								.andReceiveDateBetween(DateUtils.getFirstSecondOfDay(yesterday), DateUtils.getLastSecondOfDay(yesterday));
								;
								Integer receiveTrueNum = cardCouponMapper.countByExample(cardCouponExample);
								//设置卡券领取数 （真实）
								count.setCardReceiveTrue(receiveTrueNum);
								
								cardCouponExample.clear();
								cardCouponExample.createCriteria().andTemplateIdEqualTo(cardTemplateId).andValidateDateBetween(DateUtils.getFirstSecondOfDay(yesterday), DateUtils.getLastSecondOfDay(yesterday));
								Integer writeOff = cardCouponMapper.countByExample(cardCouponExample);
								//设置卡券核销数 （真实）
								count.setWriteOff(writeOff);
								
								
								
								
								
								//设置卡券分享数 （展示）
								count.setCardShareDisplay(shareRandom);
								
								//设置卡券分享数 （真实） 因 卡券是4个一起的 所以直接设置分享数相同
								//统计昨天这个商家所有的分享数
								Map shareParamNum = new HashMap<>();
								shareParamNum.put("sellerId", sellerId);
								shareParamNum.put("startDate",DateUtils.getFirstSecondOfDay(yesterday));
								shareParamNum.put("endDate",DateUtils.getLastSecondOfDay(yesterday));
								shareParamNum.put("statusCfg",1);
								Long  trueShareNum = cardUserLotteryMapper.countByShareNumBySellerIdAndDate(shareParamNum);
								//设置卡券分享数 （真实）
								if (trueShareNum != null) {
									count.setCardShareTrue(trueShareNum.intValue());
								}else{
									count.setCardShareTrue(0);
								}
								
								// ***** end
								Integer temshareReceiveRandom = 0;
								if (k<3) {
									switch (relation.getSort()) {
									case 1:
										temshareReceiveRandom = shareReceiveRandom * property.getFirstRankProbability()/100;
										break;
									case 2:
										temshareReceiveRandom = shareReceiveRandom * property.getSecondRankProbability()/100;
										break;
									case 3:
										temshareReceiveRandom = shareReceiveRandom * property.getThirdRankProbability()/100;
										break;
									case 4:
										temshareReceiveRandom = shareReceiveRandom * property.getFourthRankProbability()/100;
										break;

									}
									shareReceiveRandomshengyu = shareReceiveRandomshengyu -temshareReceiveRandom;
								}else{
									temshareReceiveRandom = shareReceiveRandomshengyu>0?shareReceiveRandomshengyu:1;
								}
								
								
								count.setCardShareReceiveDisplay(temshareReceiveRandom);
								
								//查询分享抽奖表 查询统计cardTemplateId 领取数
								Map shareReceiveParamNum = new HashMap<>();
								shareReceiveParamNum.put("cardTemplateId",cardTemplateId);
								shareReceiveParamNum.put("startDate",DateUtils.getFirstSecondOfDay(yesterday));
								shareReceiveParamNum.put("endDate",DateUtils.getLastSecondOfDay(yesterday));
								
								Long  trueShareReceive = cardLotteryDetailMapper.countByShareReceiveNumByCouponIdAndDate(shareReceiveParamNum);
								//设置分享领取数 （真实）
								if (trueShareReceive != null) {
									count.setCardShareReceiveTrue(trueShareReceive.intValue());
								}else{
									count.setCardShareReceiveTrue(0);
								}
								
								cardDeliveryCountMapper.insertSelective(count);
							}
						}
					}else if(deliverType==2){ //  2,周边投放
						//查询周边投放的卡券
						CardCouponDeliveryTemplateRelationExample relationExample = new CardCouponDeliveryTemplateRelationExample();
						relationExample.createCriteria().andSellerIdEqualTo(sellerId).andStatusCfgEqualTo(2);
						List<CardCouponDeliveryTemplateRelation>  cardList = cardCouponDeliveryTemplateRelationMapper.selectByExample(relationExample);
						logger.info("商家sellerId 周边投放的卡券数量为"+cardList.size());
						 //等于4个卡券的才是投放中的 进行统计
						if(cardList!=null && cardList.size()==4){
							Integer pushMin = property.getCardPushMin();
							Integer pushMax = property.getCardPushMax();
							//产生 pushMin 到	pushMax 之间的随机数			
							Integer pushRandom = getNumBetweenRandom(pushMin,pushMax);
							BigDecimal pushRandomDec = new BigDecimal(pushRandom).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP); 
							//卡券推送次数（展示数据）= 实际交易笔数 × 百分比
							pushRandomDec = new BigDecimal(num).multiply(pushRandomDec);
							pushRandom = pushRandomDec.intValue();
							
							Integer receiveMin = property.getCardReceiveMin();
							Integer receiveMax = property.getCardReceiveMax();
							//产生receiveMin 到	receiveMax 之间的随机数			
							Integer receiveRandom = getNumBetweenRandom(receiveMin,receiveMax);
							BigDecimal receiveRandomDec = new BigDecimal(receiveRandom).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP); 
							//卡券领取数（展示数据）= 卡券推送次数（展示数据）× 百分比
							receiveRandomDec = new BigDecimal(pushRandom).multiply(receiveRandomDec);
							receiveRandom = receiveRandomDec.intValue();
							int shengyureceiveRandom = receiveRandom;
							
							// ***** start
							Integer shareMin = property.getCardShareMin();
							Integer shareMax = property.getCardShareMax();
							//产生shareMin 到	shareMax 之间的随机数			
							Integer shareRandom = getNumBetweenRandom(shareMin,shareMax);
							BigDecimal shareRandomDec = new BigDecimal(shareRandom).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);  
							//活动分享数（展示数据）= 卡券推送次数（展示数据） × 百分比
							shareRandomDec = new BigDecimal(pushRandom).multiply(shareRandomDec);
							shareRandom = shareRandomDec.intValue();
							

							Integer shareReceiveMin = property.getCardShareReceiveMin();
							Integer shareReceiveMax = property.getCardShareReceiveMax();
							//产生shareReceiveMin 到	shareReceiveMax 之间的随机数			
							Integer shareReceiveRandom = getNumBetweenRandom(shareReceiveMin,shareReceiveMax);
							BigDecimal shareReceiveRandomDec = new BigDecimal(shareReceiveRandom).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);    
							//分享领取数（展示数据）= 活动分享数（展示数据）× 百分比
							shareReceiveRandomDec = new BigDecimal(shareRandom).multiply(shareReceiveRandomDec);
							shareReceiveRandom = shareReceiveRandomDec.intValue();
							Integer shengyushareReceiveRandom = shareReceiveRandom;
							
							for(int k=0;k<cardList.size();k++){
									CardCouponDeliveryTemplateRelation relation  = cardList.get(k);
									Long cardTemplateId = relation.getCardCouponTemplateId();
									CardDeliveryCount count = new CardDeliveryCount();
									count.setSellerId(sellerId);
									count.setDeliverType(deliverType);
									count.setCountDate(yesterday);
									count.setCardTemplateId(cardTemplateId);
									
									//设置卡券推送次数 =（展示）
									count.setCardPushDisplay(pushRandom);
									
									CardCouponPeripheralDisplayExample perDisExample =  new CardCouponPeripheralDisplayExample();
									perDisExample.createCriteria().andSellerIdEqualTo(sellerId).andCreateTimeEqualTo(yesterday);
									List<CardCouponPeripheralDisplay>  peripheralDisplayList = cardCouponPeripheralDisplayMapper.selectByExample(perDisExample);
									//设置卡券推送次数 （真实）
									if(peripheralDisplayList!=null&&peripheralDisplayList.size()>0){
										count.setCardPushTrue(peripheralDisplayList.get(0).getPeripheralDisplayNum());
									}else{
										count.setCardPushTrue(0);
									}
									
									Integer temreceiveRandom = 0;
									if (k<3) {
										switch (relation.getSort()) {
										case 1:
											temreceiveRandom = receiveRandom * property.getFirstRankProbability()/100;
											break;
										case 2:
											temreceiveRandom = receiveRandom * property.getSecondRankProbability()/100;
											break;
										case 3:
											temreceiveRandom = receiveRandom * property.getThirdRankProbability()/100;
											break;
										case 4:
											temreceiveRandom = receiveRandom * property.getFourthRankProbability()/100;
											break;

										}
										shengyureceiveRandom = shengyureceiveRandom -temreceiveRandom;
									}else{
										temreceiveRandom = shengyureceiveRandom>0?shengyureceiveRandom:1;
									}
									
									
									//设置卡券领取数 （展示）
									count.setCardReceiveDisplay(temreceiveRandom);
									
									CardCouponExample cardCouponExample = new CardCouponExample();
									cardCouponExample.createCriteria().andTemplateIdEqualTo(cardTemplateId).andSellerIdEqualTo(sellerId)
									.andReceiveDateBetween(DateUtils.getFirstSecondOfDay(yesterday), DateUtils.getLastSecondOfDay(yesterday));
									Integer receiveTrueNum = cardCouponMapper.countByExample(cardCouponExample);
									//设置卡券领取数 （真实）
									count.setCardReceiveTrue(receiveTrueNum);
									
									cardCouponExample.clear();
									cardCouponExample.createCriteria().andTemplateIdEqualTo(cardTemplateId).andValidateDateBetween(DateUtils.getFirstSecondOfDay(yesterday), DateUtils.getLastSecondOfDay(yesterday));
									Integer writeOff = cardCouponMapper.countByExample(cardCouponExample);
									//设置卡券核销数 （真实）
									count.setWriteOff(writeOff);
									
									
									//设置卡券分享数 （展示）
									count.setCardShareDisplay(shareRandom);
									
									//设置卡券分享数 （真实） 因 卡券是4个一起的 所以直接设置分享数相同
									//统计昨天这个商家所有的分享数
									Map shareParamNum = new HashMap<>();
									shareParamNum.put("sellerId", sellerId);
									shareParamNum.put("startDate",DateUtils.getFirstSecondOfDay(yesterday));
									shareParamNum.put("endDate",DateUtils.getLastSecondOfDay(yesterday));
									shareParamNum.put("statusCfg",2);
									Long  trueShareNum = cardUserLotteryMapper.countByShareNumBySellerIdAndDate(shareParamNum);
									//设置卡券分享数 （真实）
									if (trueShareNum != null) {
										count.setCardShareTrue(trueShareNum.intValue());
									}else{
										count.setCardShareTrue(0);
									}
									
									// ***** end
									
									Integer temshareReceiveRandom = 0;
									if (k<3) {
										switch (relation.getSort()) {
										case 1:
											temshareReceiveRandom = shareReceiveRandom * property.getFirstRankProbability()/100;
											break;
										case 2:
											temshareReceiveRandom = shareReceiveRandom * property.getSecondRankProbability()/100;
											break;
										case 3:
											temshareReceiveRandom = shareReceiveRandom * property.getThirdRankProbability()/100;
											break;
										case 4:
											temshareReceiveRandom = shareReceiveRandom * property.getFourthRankProbability()/100;
											break;

										}
										shengyushareReceiveRandom = shengyushareReceiveRandom -temshareReceiveRandom;
									}else{
										temshareReceiveRandom = shengyushareReceiveRandom>0?shengyushareReceiveRandom:1;
									}
									
									//设置分享领取数 （展示）
									count.setCardShareReceiveDisplay(temshareReceiveRandom);
									
									//查询分享抽奖表 查询统计cardTemplateId 领取数
									Map shareReceiveParamNum = new HashMap<>();
									shareReceiveParamNum.put("cardTemplateId",cardTemplateId);
									shareReceiveParamNum.put("startDate",DateUtils.getFirstSecondOfDay(yesterday));
									shareReceiveParamNum.put("endDate",DateUtils.getLastSecondOfDay(yesterday));
									Long  trueShareReceive = cardLotteryDetailMapper.countByShareReceiveNumByCouponIdAndDate(shareReceiveParamNum);
									//设置分享领取数 （真实）
									if (trueShareReceive != null) {
										count.setCardShareReceiveTrue(trueShareReceive.intValue());
									}else{
										count.setCardShareReceiveTrue(0);
									}
									
									
									cardDeliveryCountMapper.insertSelective(count);
									
							}
						}
				   }
				}
			}
	    	
	    }
		
	}
	
	private int getNumBetweenRandom(int min,int max){
		double random = Math.random();
		int aaa = (int) (random*(max - min)+min);
		return aaa;
	}

	@Override
	public CardDeliveryProperty getCardDeliveryPropertyByCfg(Integer cfg) {
		return cardDeliveryPropertyMapper.selectByPrimaryKey(Long.valueOf(cfg.toString()));
	}

	@Override
	public int insertCardCouponDelivery(CardCouponDelivery cardCouponDelivery) {
		return cardCouponDeliveryMapper.insertSelective(cardCouponDelivery);
	}
}
