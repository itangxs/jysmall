package cn.qhjys.mall.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.common.PAYCODE;
import cn.qhjys.mall.entity.CashAccount;
import cn.qhjys.mall.entity.CashAccountExample;
import cn.qhjys.mall.entity.CashLog;
import cn.qhjys.mall.entity.CouponInfo;
import cn.qhjys.mall.entity.OrderDetail;
import cn.qhjys.mall.entity.OrderDetailExample;
import cn.qhjys.mall.entity.OrderInfo;
import cn.qhjys.mall.mapper.CashAccountMapper;
import cn.qhjys.mall.mapper.CashLogMapper;
import cn.qhjys.mall.mapper.CouponInfoMapper;
import cn.qhjys.mall.mapper.OrderDetailMapper;
import cn.qhjys.mall.mapper.VolumeInfoMapper;
import cn.qhjys.mall.service.PaymentService;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.OrderResult;
import cn.qhjys.mall.util.OrderResult.OrderEnum;

@Service
public class PaymentServiceImpl extends Base implements PaymentService {
	@Autowired
	private CashLogMapper cashLogMapper;
	@Autowired
	private CashAccountMapper cashAccountMapper;
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	@Autowired
	private VolumeInfoMapper volumeInfoMapper;
	@Autowired
	private cn.qhjys.mall.service.IntegralExpiredService integralExpiredService;
	@Autowired
	private CouponInfoMapper couponInfoMapper;
	// 兑换比例
	private BigDecimal scale = new BigDecimal(ConstantsConfigurer.getProperty("recharge_scale"));

	@Override
	public boolean orderPaymentByUser(Long userId, OrderInfo order, OrderResult result) throws Exception {
		if (userId == null || order == null)
			throw new Exception("会员和订单不能为空！");
		CashAccountExample example = new CashAccountExample();
		example.createCriteria().andAccountIdEqualTo(userId).andAccountTypeEqualTo(0);
		List<CashAccount> list = cashAccountMapper.selectByExample(example);
		if (list == null || list.size() < 1) {
			result.setResult(false);
			result.setFlag(OrderEnum.ACCOUNT_ERROR.tag);
			result.setReason("会员帐号异常(无法获取账户资金信息)！");
			return false;
		}
		BigDecimal couamount = new BigDecimal(0);
		if (order.getCouponId() != null) {
			CouponInfo ci = couponInfoMapper.selectByPrimaryKey(order.getCouponId());
			couamount = ci.getAmount();
		}
		
		CashAccount account = list.get(0); // 会员资金帐号
		BigDecimal ubalance = account.getBalance(); // 会员账户元宝
		BigDecimal ufreeze = account.getFreezeMoney(); // 会员冻结元宝
		BigDecimal orderPay = order.getIntegral(); // 订单总元宝
		BigDecimal uufreeze = new BigDecimal(0);
		
		
		if (orderPay.subtract(couamount).intValue()>0) {
			uufreeze =  orderPay.subtract(couamount);
		}
		if (ubalance.subtract(uufreeze).compareTo(BigDecimal.ZERO) == -1) {
			result.setResult(false);
			result.setFlag(OrderEnum.BALANCE_LOW.tag);
			result.setReason(OrderEnum.BALANCE_LOW.msg);
			throw new Exception("账户余额不足！");
		}
		account.setBalance(ubalance.subtract(uufreeze)); // 会员支付订单元宝
		account.setFreezeMoney(ufreeze.add(uufreeze)); // 会员冻结订单元宝
		account.setCreateDate(new Date());
		int row = cashAccountMapper.updateByPrimaryKey(account); // 1.会员减少元宝+添加冻结元宝
		Long jysmall = Long.valueOf(ConstantsConfigurer.getProperty("FINANCE_USERID"));
		example.clear();
		example.createCriteria().andAccountIdEqualTo(jysmall).andAccountTypeEqualTo(0);
		list = cashAccountMapper.selectByExample(example);
		if (list == null || list.size() < 1) {
			result.setResult(false);
			result.setFlag(OrderEnum.ACCOUNT_ERROR.tag);
			result.setReason("平台帐号异常(无法获取账户资金信息)！");
			throw new Exception("平台帐号异常(无法获取账户资金信息)！");
		}
		account = list.get(0); // 平台资金帐号
		BigDecimal pbalance = account.getBalance(); // 平台账户元宝
		account.setBalance(pbalance.add(orderPay)); // 平台添加订单元宝
		account.setCreateDate(new Date());
		row += cashAccountMapper.updateByPrimaryKey(account); // 2.平台帐号添加订单金额
		CashLog log = new CashLog(); // 添加会员购买记录
		Date now = new Date();
		log.setRecordNo(order.getOrderNo());
		log.setSendId(userId);
		log.setReviewId(jysmall);
		log.setAmount(orderPay);
		log.setPayType(5);
		log.setPayWay(4);
		log.setBusinessCode(PAYCODE.B003);
		log.setBusinessName(PAYCODE.B003N);
		log.setSendBefor(ubalance);
		log.setSendAfter(ubalance.subtract(orderPay));
		log.setCreateTime(now);
		row += cashLogMapper.insertSelective(log); // 3.添加购买资金消费记录
		integralExpiredService.updateIntegralExpiredByShop(userId, orderPay.intValue());
		if (row != 3) {
			result.setResult(false);
			result.setFlag(OrderEnum.ACCOUNT_ERROR.tag);
			result.setReason("支付订单扣款错误！");
			throw new Exception("支付订单扣款错误！");
		}
		return row == 3;
	}

	@Override
	public boolean orderSuccessBySeller(Long userId, Long seller, String detaNo, BigDecimal totamt) throws Exception {
		if (userId == null || seller == null || detaNo == null || totamt == null)
			throw new Exception("会员、商家、订单流水号和消费总价不能为空！");
		CashAccountExample example = new CashAccountExample();
		example.createCriteria().andAccountIdEqualTo(userId).andAccountTypeEqualTo(0);
		List<CashAccount> list = cashAccountMapper.selectByExample(example);
		if (list == null || list.size() < 1)
			throw new Exception("会员帐号异常(无法获取账户资金信息)！");
		OrderDetailExample example2 = new OrderDetailExample();
		example2.createCriteria().andDetailNoEqualTo(detaNo);
		OrderDetail od = orderDetailMapper.selectByExample(example2).get(0);
		BigDecimal couamount = new BigDecimal(0);
		if (od.getConponId() != null) {
			CouponInfo ci = couponInfoMapper.selectByPrimaryKey(od.getConponId());
			couamount = ci.getAmount();
		}
		CashAccount account = list.get(0); // 会员资金帐号
		BigDecimal ufreeze = account.getFreezeMoney(); // 会员冻结元宝
		BigDecimal uufreeze = new BigDecimal(0);
		if (totamt.subtract(couamount).intValue()>0) {
			uufreeze =  totamt.subtract(couamount);
		}
		account.setFreezeMoney(ufreeze.subtract(uufreeze));
		account.setCreateDate(new Date());
		int row = cashAccountMapper.updateByPrimaryKey(account); // 1.会员减少冻结元宝
		Long jysmall = Long.valueOf(ConstantsConfigurer.getProperty("FINANCE_USERID"));
		example.clear();
		example.createCriteria().andAccountIdEqualTo(jysmall).andAccountTypeEqualTo(0);
		list = cashAccountMapper.selectByExample(example);
		if (list == null || list.size() < 1)
			throw new Exception("平台帐号异常(无法获取账户资金信息)！");
		account = list.get(0); // 平台资金帐号
		BigDecimal ubalance = account.getBalance(); // 平台账户元宝
		account.setBalance(ubalance.subtract(totamt));
		account.setCreateDate(new Date());
		row += cashAccountMapper.updateByPrimaryKey(account); // 2.平台减少元宝
		example.clear();
		example.createCriteria().andAccountIdEqualTo(seller).andAccountTypeEqualTo(1);
		list = cashAccountMapper.selectByExample(example);
		if (list == null || list.size() < 1)
			throw new Exception("商家帐号异常(无法获取账户资金信息)！");
		account = list.get(0); // 商家资金帐号
		BigDecimal sbalance = account.getBalance(); // 商家账户元宝
		account.setBalance(sbalance.add(totamt));
		account.setCreateDate(new Date());
		row += cashAccountMapper.updateByPrimaryKey(account); // 3.商家添加元宝
		CashLog log = new CashLog(); // 添加商家销售入账记录
		Date now = new Date();
		log.setRecordNo(detaNo);
		log.setSendId(userId);
		log.setReviewId(seller);
		log.setAmount(totamt);
		log.setPayType(5);
		log.setPayWay(4);
		log.setBusinessCode(PAYCODE.B005);
		log.setBusinessName(PAYCODE.B005N);
		log.setReviewBefor(sbalance);
		log.setReviewAfter(sbalance.add(totamt));
		log.setCreateTime(now);
		row += cashLogMapper.insertSelective(log); // 4.添加销售资金入账记录
		if (row != 4)
			throw new Exception("商家销售入账错误！");
		return row == 4;
	}

	@Override
	public boolean orderRefundByUser(Long userId, String detaNo, BigDecimal totamt) throws Exception {
		if (userId == null || detaNo == null || totamt == null)
			throw new Exception("会员、订单流水号和退款总价不能为空！");
		CashAccountExample example = new CashAccountExample();
		Long jysmall = Long.valueOf(ConstantsConfigurer.getProperty("FINANCE_USERID"));
		example.createCriteria().andAccountIdEqualTo(jysmall).andAccountTypeEqualTo(0);
		List<CashAccount> list = cashAccountMapper.selectByExample(example);
		if (list == null || list.size() < 1)
			throw new Exception("平台帐号异常(无法获取账户资金信息)！");
		CashAccount account = list.get(0); // 平台资金帐号
		BigDecimal pbalance = account.getBalance(); // 平台账户元宝
		account.setBalance(pbalance.subtract(totamt));
		account.setCreateDate(new Date());
		int row = cashAccountMapper.updateByPrimaryKey(account); // 1.平台减少余额
		example.clear();
		example.createCriteria().andAccountIdEqualTo(userId).andAccountTypeEqualTo(0);
		list = cashAccountMapper.selectByExample(example);
		if (list == null || list.size() < 1)
			throw new Exception("会员帐号异常(无法获取账户资金信息)！");
		account = list.get(0); // 会员资金帐号
		BigDecimal ubalance = account.getBalance(); // 会员账户余额
		BigDecimal ufreeze = account.getFreezeMoney(); // 会员冻结金额
		account.setFreezeMoney(ufreeze.subtract(totamt));
		account.setBalance(ubalance.add(totamt));
		account.setCreateDate(new Date());
		row += cashAccountMapper.updateByPrimaryKey(account); // 2.会员减少冻结金额+添加余额
		CashLog log = new CashLog(); // 添加会员退款记录
		Date now = new Date();
		log.setRecordNo(detaNo);
		log.setSendId(jysmall);
		log.setReviewId(userId);
		log.setAmount(totamt);
		log.setPayType(6);
		log.setPayWay(4);
		log.setBusinessCode(PAYCODE.B004);
		log.setBusinessName(PAYCODE.B004N);
		log.setReviewBefor(ubalance);
		log.setReviewAfter(ubalance.add(totamt));
		log.setCreateTime(now);
		row += cashLogMapper.insertSelective(log); // 4.添加退款资金入账记录
		if (row != 3)
			throw new Exception("订单退款错误！");
		return row == 3;
	}

	@Override
	public boolean updateExchangeBySeller(Long sellerId, BigDecimal exchangeIntegral) throws Exception {
		CashAccountExample example = new CashAccountExample();
		example.createCriteria().andAccountIdEqualTo(sellerId).andAccountTypeEqualTo(1);
		List<CashAccount> list = cashAccountMapper.selectByExample(example);
		if (list == null || list.size() < 1)
			throw new Exception("商家帐号异常(无法获取账户资金信息)！");
		CashAccount cashAccount = list.get(0); // 商家资金帐号
		BigDecimal upbalance = cashAccount.getBalance(); // 商家账户余额
		BigDecimal uintegral = cashAccount.getIntegral();// 商家账户积分
		BigDecimal balance = exchangeIntegral.divide(scale);
		Long jysmall = Long.valueOf(ConstantsConfigurer.getProperty("FINANCE_USERID"));
		example.clear();
		example.createCriteria().andAccountIdEqualTo(jysmall).andAccountTypeEqualTo(0);
		list = cashAccountMapper.selectByExample(example);
		if (list == null || list.size() < 1)
			throw new Exception("平台帐号异常(无法获取账户资金信息)！");
		CashAccount account = list.get(0); // 平台资金帐号
		BigDecimal pbalance = account.getBalance(); // 平台账户余额
		BigDecimal pintegral = account.getIntegral(); // 平台账户积分
		account.setBalance(upbalance.subtract(balance));
		account.setIntegral(uintegral.add(exchangeIntegral));
		account.setCreateDate(new Date());
		int row = cashAccountMapper.updateByPrimaryKeySelective(account);
		cashAccount.setBalance(pbalance.add(balance));
		cashAccount.setIntegral(pintegral.subtract(exchangeIntegral));
		cashAccount.setCreateDate(new Date());
		row += cashAccountMapper.updateByPrimaryKeySelective(cashAccount);
		Date now = new Date();
		CashLog log = new CashLog(); // 添加商家积分兑换记录
		log.setRecordNo("");
		log.setSendId(jysmall);
		log.setReviewId(sellerId);
		log.setAmount(balance);
		log.setPayType(8);
		log.setPayWay(4);
		log.setBusinessCode(PAYCODE.B008);
		log.setBusinessName(PAYCODE.B008N);
		log.setReviewBefor(upbalance);
		log.setReviewAfter(cashAccount.getBalance());
		log.setSendBefor(pbalance);
		log.setSendAfter(account.getBalance());
		log.setCreateTime(now);
		row += cashLogMapper.insertSelective(log);
		return row > 3;
	}

	@Override
	public boolean addUserBalanceTrade(Long userId, Integer type, BigDecimal integral) throws Exception {
		if (userId == null || type == null || integral == null)
			throw new Exception("会员、交易类型和交易积分不能为空！");
		CashAccountExample example = new CashAccountExample();
		example.createCriteria().andAccountIdEqualTo(userId).andAccountTypeEqualTo(0);
		List<CashAccount> list = cashAccountMapper.selectByExample(example);
		if (list == null || list.size() < 1)
			throw new Exception("会员帐号异常(无法获取账户资金信息)！");
		CashAccount account = list.get(0); // 用户资金帐号
		BigDecimal after;
		CashLog log = new CashLog();
		Long jysmall = Long.valueOf(ConstantsConfigurer.getProperty("FINANCE_USERID"));

		if (type == 1) {
			after = account.getBalance().add(integral);
			log.setSendId(jysmall);
			log.setReviewId(userId);
			log.setAmount(integral);
			log.setPayType(9);
			log.setPayWay(4);
			log.setBusinessCode(PAYCODE.B006);
			log.setBusinessName(PAYCODE.B006N);
			log.setReviewBefor(account.getBalance());
			log.setReviewAfter(after);
			log.setCreateTime(new Date());
			integralExpiredService.updateIntegralExpiredByEvaluate(userId, integral.intValue());
		} else if (type == 2) {
			after = account.getBalance().subtract(integral);
			if (after.compareTo(BigDecimal.ZERO) >= 0) {
				log.setSendId(userId);
				log.setReviewId(jysmall);
				log.setAmount(integral);
				log.setPayType(5);
				log.setPayWay(4);
				log.setBusinessCode(PAYCODE.B003);
				log.setBusinessName(PAYCODE.B003N);
				log.setReviewBefor(account.getBalance());
				log.setReviewAfter(after);
				log.setCreateTime(new Date());
			} else
				throw new Exception("账户余额不足!");
		} else
			throw new Exception("交易类型错误!");
		account.setBalance(after);
		account.setCreateDate(new Date());
		int result = cashAccountMapper.updateByPrimaryKeySelective(account);
		log.setCreateTime(new Date());
		result += cashLogMapper.insertSelective(log);
		
		return result > 2 ? true : false;
	}

	@Override
	public boolean updateExchangeByRecomId(Long recomId,
			BigDecimal exchangeIntegral) throws Exception {
		CashAccountExample example = new CashAccountExample();
		Long jysmall = Long.valueOf(ConstantsConfigurer.getProperty("FINANCE_USERID"));
		example.createCriteria().andAccountIdEqualTo(jysmall).andAccountTypeEqualTo(0);
		List<CashAccount> list = cashAccountMapper.selectByExample(example);
		if (list == null || list.size() < 1)
			throw new Exception("平台帐号异常(无法获取账户资金信息)！");
		CashAccount account = list.get(0); // 平台资金帐号
		BigDecimal pbalance = account.getBalance(); // 平台账户元宝
		account.setBalance(pbalance.subtract(exchangeIntegral));
		account.setCreateDate(new Date());
		int row = cashAccountMapper.updateByPrimaryKey(account); // 1.平台减少余额
		example.clear();
		example.createCriteria().andAccountIdEqualTo(recomId).andAccountTypeEqualTo(0);
		list = cashAccountMapper.selectByExample(example);
		if (list == null || list.size() < 1)
			throw new Exception("会员帐号异常(无法获取账户资金信息)！");
		account = list.get(0); // 会员资金帐号
		BigDecimal ubalance = account.getBalance(); // 会员账户余额
		account.setBalance(ubalance.add(exchangeIntegral));
		account.setCreateDate(new Date());
		row += cashAccountMapper.updateByPrimaryKey(account); // 2.会员减少冻结金额+添加余额
		CashLog log = new CashLog(); // 添加会员退款记录
		Date now = new Date();
		log.setSendId(jysmall);
		log.setReviewId(recomId);
		log.setAmount(exchangeIntegral);
		log.setPayType(6);
		log.setPayWay(4);
		log.setBusinessCode(PAYCODE.B013);
		log.setBusinessName(PAYCODE.B013N);
		log.setReviewBefor(ubalance);
		log.setReviewAfter(ubalance.add(exchangeIntegral));
		log.setCreateTime(now);
		row += cashLogMapper.insertSelective(log); // 4.添加退款资金入账记录
		if (row != 3)
			throw new Exception("邀请好友奖励错误！");
		return row == 3;
	}

}