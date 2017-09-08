package cn.qhjys.mall.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.entity.CashAccount;
import cn.qhjys.mall.entity.CashLog;
import cn.qhjys.mall.entity.CashSaveWithdraw;
import cn.qhjys.mall.entity.UserInfo;
import cn.qhjys.mall.mapper.CashAccountMapper;
import cn.qhjys.mall.service.PayService;
import cn.qhjys.mall.service.UserInfoService;
import cn.qhjys.mall.service.seller.SellerVoService;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.LLPayUtil;
import cn.qhjys.mall.util.MessageUtil;
import cn.qhjys.mall.util.RetBean;
import com.alibaba.fastjson.JSON;

@Service
public class PayServiceImpl extends Base implements PayService {

	@Autowired
	private SellerVoService sellerVoService;
	@Autowired
	private CashAccountMapper cashAccountMapper;
	@Autowired
	private UserInfoService userInfoService;

	@Override
	public String saveSellerPayLL(String reqStr, String cardNo, String orderNum, CashSaveWithdraw cashSaveWithdra)
			throws Exception {
		CashSaveWithdraw cash = new CashSaveWithdraw();
		cash.setOrderNum(orderNum);
		RetBean retBean = new RetBean();
		if (LLPayUtil.isnull(reqStr)) {
			retBean.setRet_code("9999");
			retBean.setRet_msg("交易失败");
			cash.setStatus(0);
			sellerVoService.updateSaveWithdraw(cash);
			return JSON.toJSONString(retBean);
		}
		try {
			if (!LLPayUtil.checkSign(reqStr, ConstantsConfigurer.getProperty("yt_pub_key"),
					ConstantsConfigurer.getProperty("md5_key"))) {
				retBean.setRet_code("9999");
				retBean.setRet_msg("交易失败");
				cash.setStatus(0);
				sellerVoService.updateSaveWithdraw(cash);
			}
		} catch (Exception e) {
			retBean.setRet_code("9999");
			retBean.setRet_msg("交易失败");
			return JSON.toJSONString(retBean);
		}
		// 交易成功
		retBean.setRet_code("0000");
		retBean.setRet_msg("交易成功");
		// 解析异步通知对象
		// PayDataBean payDataBean = JSON.parseObject(reqStr,
		// PayDataBean.class);
		// 更新订单，发货等后续处理
		cash.setStatus(1);
		BigDecimal amount = cashSaveWithdra.getMoney().multiply(new BigDecimal(ConstantsConfigurer.getProperty("recharge_scale")));
		long sellerId = cashSaveWithdra.getCashId();
		CashAccount cashAccount = sellerVoService.queryAccountBySellerId(1, sellerId);
		BigDecimal balance = cashAccount.getBalance();
		BigDecimal reviewAfter = balance.add(amount);
		CashAccount account = new CashAccount();
		account.setBalance(reviewAfter);
		account.setId(cashAccount.getId());
		CashLog cashLog = new CashLog();
		cashLog.setReviewId(sellerId);
		cashLog.setBankno(cardNo);
		cashLog.setAmount(amount);
		cashLog.setRecordNo(orderNum);
		cashLog.setPayType(3);
		cashLog.setPayWay(1);
		cashLog.setBusinessCode("001");
		cashLog.setBusinessName("充值");
		cashLog.setReviewBefor(balance);
		cashLog.setReviewAfter(reviewAfter);
		cashLog.setCreateTime(new Date());
		sellerVoService.insertRechargeIsSucess(cash, account, cashLog);
		return JSON.toJSONString(retBean);
	}

	@Override
	public String saveUserPayLL(String reqStr, String orderNum, String cardNo, CashSaveWithdraw cashSaveWithdraw)
			throws Exception {
		CashSaveWithdraw cash = new CashSaveWithdraw();
		cash.setOrderNum(orderNum);
		RetBean retBean = new RetBean();
		if (LLPayUtil.isnull(reqStr)) {
			retBean.setRet_code("9999");
			retBean.setRet_msg("交易失败");
			cash.setStatus(0);
			sellerVoService.updateSaveWithdraw(cash);
			return JSON.toJSONString(retBean);
		}
		try {
			if (!LLPayUtil.checkSign(reqStr, ConstantsConfigurer.getProperty("yt_pub_key"),
					ConstantsConfigurer.getProperty("md5_key"))) {
				retBean.setRet_code("9999");
				retBean.setRet_msg("交易失败");
				cash.setStatus(0);
				sellerVoService.updateSaveWithdraw(cash);
			}
		} catch (Exception e) {
			logger.info("异步通知报文解析异常：" + e);
			retBean.setRet_code("9999");
			retBean.setRet_msg("交易失败");
			return JSON.toJSONString(retBean);
		}
		// 交易成功
		retBean.setRet_code("0000");
		retBean.setRet_msg("交易成功");
		Long cashId = cashSaveWithdraw.getCashId();
		// 更新订单，发货等后续处理
		cash.setStatus(1);
		BigDecimal amount = cashSaveWithdraw.getMoney().multiply(new BigDecimal(ConstantsConfigurer.getProperty("recharge_scale")));
		CashAccount cashAccount = cashAccountMapper.selectByPrimaryKey(cashId);
		Long userId = cashAccount.getAccountId();
		BigDecimal balance = cashAccount.getBalance();
		BigDecimal reviewAfter = balance.add(amount);
		CashAccount account = new CashAccount();
		account.setId(cashAccount.getId());
		account.setBalance(reviewAfter);
		CashLog cashLog = new CashLog();
		cashLog.setReviewId(userId);
		cashLog.setBankno(cardNo);
		cashLog.setAmount(amount);
		cashLog.setRecordNo(orderNum);
		cashLog.setPayType(1);
		cashLog.setPayWay(1);
		cashLog.setBusinessCode("001");
		cashLog.setBusinessName("充值");
		cashLog.setReviewBefor(balance);
		cashLog.setReviewAfter(reviewAfter);
		cashLog.setCreateTime(new Date());
		sellerVoService.insertRechargeIsSucess(cash, account, cashLog);
		String message = MessageUtil.ACCOUNTRECHARGE;
		UserInfo user = userInfoService.selectUserById(userId);
		String userName = user.getUsername();
		message = message.replace("username", userName);
		message = message.replace("money", String.valueOf(amount));
		message = message.replace("balance", String.valueOf(reviewAfter));
		try {
			MessageUtil.sendSmsContent(user.getPhoneNum(), message);
		} catch (Exception e) {
			throw new Exception("连连支付用户充值成功回调——发送短信异常：", e);
		}
		return JSON.toJSONString(retBean);
	}

}
