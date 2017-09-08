package cn.qhjys.mall.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.MessageInfo;
import cn.qhjys.mall.entity.OrderDetail;
import cn.qhjys.mall.entity.ProductInfo;
import cn.qhjys.mall.entity.ReviewsLog;
import cn.qhjys.mall.mapper.MessageInfoMapper;
import cn.qhjys.mall.mapper.OrderDetailMapper;
import cn.qhjys.mall.mapper.ProductInfoMapper;
import cn.qhjys.mall.mapper.ReviewsLogMapper;
import cn.qhjys.mall.mapper.StoreInfoMapper;
import cn.qhjys.mall.mapper.UserInfoMapper;
import cn.qhjys.mall.mapper.custom.ReviewMapper;
import cn.qhjys.mall.service.PaymentService;
import cn.qhjys.mall.service.ReviewService;
import cn.qhjys.mall.vo.EvaluateVo;
import cn.qhjys.mall.vo.ReviewVo;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/***
 * 
 * @author zengrong
 *
 */
@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	private ReviewsLogMapper reviewsLogMapper;
	@Autowired
	private ReviewMapper reviewMapper;
	@Autowired
	private StoreInfoMapper storeInfoMapper;
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private ProductInfoMapper productInfoMapper;
	@Autowired
	private MessageInfoMapper messageInfoMapper;

	@Override
	public ReviewVo getReviewByPordId(Long prodId) throws Exception {
		return reviewMapper.searchReviewByProdId(prodId);
	}

	@Override
	public ReviewVo getReviewByStoreId(Long storeId) throws Exception {
		return reviewMapper.searchReviewByStoreId(storeId);
	}

	@Override
	public Page<EvaluateVo> selectReviewLevelByProdId(Long prodId, Integer level, Integer pageNum, Integer pageSize)
			throws Exception {
		if (pageNum == null)
			pageNum = 0;
		if (pageSize == null)
			pageSize = 10;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productId", prodId);
		if (level == null) {
			map.put("startScore", null);
			map.put("endScore", null);
		} else if (level == 2) {
			// 好评
			map.put("startScore", 4);
			map.put("endScore", 5);
		} else if (level == 3) {
			// 中评
			map.put("startScore", 2);
			map.put("endScore", 3);
		} else if (level == 4) {
			// 差评
			map.put("startScore", 0);
			map.put("endScore", 1);
		} else if (level == 5) {
			// 图片
			map.put("reviewImg", "1");
		}
		PageHelper.startPage(pageNum, pageSize, true, false);
		return reviewMapper.selectProductReviewByLevel(map);
	}

	@Override
	public Page<EvaluateVo> selectReviewLevelByStoreId(Long storeId, Integer level, Integer pageNum, Integer pageSize)
			throws Exception {
		if (pageNum == null)
			pageNum = 0;
		if (pageSize == null)
			pageSize = 10;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("storeId", storeId);
		if (level == null) {
			map.put("startScore", null);
			map.put("endScore", null);
		} else if (level == 2) {
			// 好评
			map.put("startScore", 3);
			map.put("endScore", 5);
		} else if (level == 3) {
			// 中评
			map.put("startScore", 1);
			map.put("endScore", 3);
		} else if (level == 4) {
			// 差评
			map.put("startScore", 0);
			map.put("endScore", 1);
		} else if (level == 5) {
			// 图片
			map.put("reviewImg", "1");
		}
		PageHelper.startPage(pageNum, pageSize, true, false);
		return reviewMapper.selectStoreReviewByLevel(map);
	}

	@Override
	public Page<EvaluateVo> selectReviewByUserId(Long prodId, Long userId, Integer status, Integer page,
			Integer pageSize) throws Exception {
		/**
		 * 查询我的评论
		 */
		return null;
	}

	@Override
	public boolean addProductEvaluate(ReviewsLog reviews) throws Exception {
		ProductInfo product = productInfoMapper.selectByPrimaryKey(reviews.getProdId());
		OrderDetail orderDetail = null;
		if (reviews.getDetailId() != null) {
			 orderDetail = orderDetailMapper.selectByPrimaryKey(reviews.getDetailId());
			orderDetail.setStatus(4);
			orderDetailMapper.updateByPrimaryKeySelective(orderDetail);
		}
		//StoreInfoExample example = new StoreInfoExample();
		//example.createCriteria().andSellerIdEqualTo(orderDetail.getSellerId());
		//List<StoreInfo> list = storeInfoMapper.selectByExample(example);
		//Long storeId = list.get(0).getId();
		Long storeId = product.getStoreId();
		reviews.setStoreId(storeId);
		int row = reviewsLogMapper.insertSelective(reviews);
		
		// 修改订单状态未已评论
		
//		UserInfo user = userInfoMapper.selectByPrimaryKey(reviews.getReplyId());
//		Long diffDay = BaseUtil.getDiffDay(orderDetail.getCreateTime(), new Date());
//		if (user != null && diffDay < 90) { // 评论获取积分
//			int level = user.getLevel(), integral = 0;
//			switch (level) {
//			case 6:
//				integral = 20;
//				break;
//			case 5:
//				integral = 18;
//				break;
//			case 4:
//				integral = 16;
//				break;
//			case 3:
//				integral = 14;
//				break;
//			case 2:
//				integral = 12;
//				break;
//			case 1:
//				integral = 8;
//				break;
//			default:
//				integral = 4;
//				break;
//			}
//			paymentService.addUserBalanceTrade(user.getId(), 1, new BigDecimal(integral));
//		}
		
		int hp = product.getHaoping();
		int zp = product.getZhongping();
		int cp = product.getChaping();
		int score = reviews.getScore();
		MessageInfo message = new MessageInfo();
		message.setContent(reviews.getReviews());
		message.setCreateDate(new Date());
		message.setMsgType(0);
		
		message.setType(2);
		Float pj = product.getScoreAvg();
		if (pj == null) {
			pj = 0f;
		}
		product.setScoreAvg((pj*(hp+zp+cp)+score)/(hp+zp+cp+1));
		if (score >3) {
			product.setHaoping(hp+1);
			message.setTitle("高分评价");
		}
		if (4>score && score >1) {
			product.setZhongping(zp+1);
			message.setTitle("中等评价");
		}
		if(score<2){
			product.setChaping(cp+1);
			message.setTitle("低分评价");
		}
		productInfoMapper.updateByPrimaryKey(product);
		if (orderDetail != null) {
			message.setSendee(orderDetail.getSellerId());
			messageInfoMapper.insert(message);
		}
		
		return row > 0 ? true : false;
	}

	@Override
	public List<EvaluateVo> findEvaluateBySaUoSId(Long prodId, Long uid, Long did) throws Exception {
		return reviewMapper.findEvaluateBySaUoSId(prodId, uid, did);
	}

	@Override
	public boolean countReviewsLogByUserAndTime(Long userId, Date date,
			Long sellerId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("date", date);
		map.put("sellerId", sellerId);
		return reviewMapper.countReviewsLogByUserAndTime(map)>0?true:false;
	}
}