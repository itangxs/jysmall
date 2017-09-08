package cn.qhjys.mall.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.common.PAYCODE;
import cn.qhjys.mall.entity.BankInfo;
import cn.qhjys.mall.entity.BankInfoExample;
import cn.qhjys.mall.entity.CashAccount;
import cn.qhjys.mall.entity.CashAccountExample;
import cn.qhjys.mall.entity.CashLog;
import cn.qhjys.mall.entity.DeliveryAddr;
import cn.qhjys.mall.entity.DeliveryAddrExample;
import cn.qhjys.mall.entity.IntegralLog;
import cn.qhjys.mall.entity.SigninInfo;
import cn.qhjys.mall.entity.SigninInfoExample;
import cn.qhjys.mall.entity.UserExpand;
import cn.qhjys.mall.entity.UserExpandExample;
import cn.qhjys.mall.entity.UserInfo;
import cn.qhjys.mall.entity.UserInfoExample;
import cn.qhjys.mall.entity.UserInfoExample.Criteria;
import cn.qhjys.mall.mapper.BankInfoMapper;
import cn.qhjys.mall.mapper.CashAccountMapper;
import cn.qhjys.mall.mapper.CashLogMapper;
import cn.qhjys.mall.mapper.DeliveryAddrMapper;
import cn.qhjys.mall.mapper.SigninInfoMapper;
import cn.qhjys.mall.mapper.UserExpandMapper;
import cn.qhjys.mall.mapper.UserInfoMapper;
import cn.qhjys.mall.mapper.custom.IntegralVoMapper;
import cn.qhjys.mall.mapper.custom.OrderManageMapper;
import cn.qhjys.mall.service.IntegralExpiredService;
import cn.qhjys.mall.service.UserInfoService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.EncodeMD5;
import cn.qhjys.mall.util.MessageUtil;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private UserExpandMapper userExpandMapper;
	@Autowired
	private DeliveryAddrMapper addressMapper;
	@Autowired
	private IntegralVoMapper integralVoMapper;
	@Autowired
	private BankInfoMapper bankMapper;
	@Autowired
	private CashAccountMapper cashAccountMapper;
	@Autowired
	private OrderManageMapper orderManageMapper;
	@Autowired
	private IntegralExpiredService integralExpiredService;
	@Autowired
	private SigninInfoMapper signinInfoMapper;
	@Autowired
	private CashLogMapper cashLogMapper;

	@Override
	public boolean insertUser(UserInfo user) throws Exception {
		if (user == null)
			return false;
		if (BaseUtil.judgeNull(user.getEmail()) && BaseUtil.judgeNull(user.getPhoneNum())
				&& BaseUtil.judgeNull(user.getUsername()) && BaseUtil.judgeNull(user.getPassword()))
			return false;
		int count = 0;
		UserInfoExample example = new UserInfoExample();
		// 判断邮箱唯一
		if (!BaseUtil.judgeNull(user.getEmail())) {
			example.clear();
			example.createCriteria().andEmailEqualTo(user.getEmail());
			count += userInfoMapper.countByExample(example);
		}
		// 判断手机唯一
		if (!BaseUtil.judgeNull(user.getPhoneNum())) {
			example.clear();
			example.createCriteria().andPhoneNumEqualTo(user.getPhoneNum());
			count += userInfoMapper.countByExample(example);
		}
		// 判断帐号唯一
		if (!BaseUtil.judgeNull(user.getUsername())) {
			example.clear();
			example.createCriteria().andUsernameEqualTo(user.getUsername());
			count += userInfoMapper.countByExample(example);
		}
		// 查询该用户名是否存在
		if (count > 0)
			return false;
		count = userInfoMapper.insertSelective(user);
		CashAccount record = new CashAccount();
		BigDecimal zero = new BigDecimal(0);
		record.setAccountId(user.getId());
		record.setAccountType(0);
		record.setBalance(zero);
		record.setCreateDate(new Date());
		record.setFreezeIntegral(zero);
		record.setFreezeMoney(zero);
		record.setIntegral(zero);
		record.setPayCode(user.getPassword());
		record.setWithdrawMoney(zero);
		SimpleDateFormat sdf1 = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		BigDecimal amount = new BigDecimal(100);
		boolean a =false;
		if (date.before(sdf1.parse("2015-09-25 00:00:00"))&& date.after(sdf1.parse("2015-09-24 00:00:00"))) {
			record.setBalance(amount);
			a= true;
		}
		cashAccountMapper.insertSelective(record);
		if (a) {
			CashAccountExample example1 = new CashAccountExample();
			example1.createCriteria().andAccountIdEqualTo(user.getId()).andAccountTypeEqualTo(0);
			CashAccount cash = cashAccountMapper.selectByExample(example1).get(0);
			CashLog log = new CashLog();
			log.setAmount(amount);
			log.setBusinessCode(PAYCODE.B011);
			log.setBusinessName(PAYCODE.B011N);
			log.setReviewId(cash.getId());
			log.setReviewAfter(amount);
			log.setReviewBefor(zero);
			log.setPayType(11);
			log.setPayWay(4);
			log.setCreateTime(date);
			cashLogMapper.insertSelective(log);
		}
		integralExpiredService.saveUserIntegralExpired(user.getId());
		return count > 0;
	}

	@Override
	public UserInfo selecUserByStr(String str) throws Exception {
		UserInfoExample example = new UserInfoExample();
		if (BaseUtil.isEmail(str))
			example.createCriteria().andEmailEqualTo(str);
		else if (BaseUtil.isMobile(str))
			example.createCriteria().andPhoneNumEqualTo(str);
		else
			example.createCriteria().andUsernameEqualTo(str);
		List<UserInfo> list = userInfoMapper.selectByExample(example);
		UserInfo user = null;
		if (list != null && list.size() > 0)
			user = list.get(0);
		return user;
	}

	@Override
	public boolean updateUserById(UserInfo user) throws Exception {
		if (user == null || BaseUtil.judgeNull(user.getId()))
			return false;
		int result = userInfoMapper.updateByPrimaryKeySelective(user);
		return result > 0;
	}

	@Override
	public boolean validateUserByCardId(Long id, String CardId) throws Exception {
		UserInfoExample example = new UserInfoExample();
		boolean isSuccess = true;
		example.createCriteria().andCardIdEqualTo(CardId).andIdNotEqualTo(id);
		List<UserInfo> list = userInfoMapper.selectByExample(example);
		if (list.size() > 0) {
			isSuccess = false;
		}
		return isSuccess;
	}

	@Override
	public int updateUser(UserInfo user, String nowPassword, String nowPhoneNum, String nowEmail, String payCode,
			CashAccount cashAccount) throws Exception {
		UserInfoExample example = new UserInfoExample();
		// 判断原来的电话号码是否正确
		if (StringUtils.isNotEmpty(nowPhoneNum)) {
			example.createCriteria().andPhoneNumEqualTo(nowPhoneNum);
		}
		// 判断原来的邮箱是否正确
		if (StringUtils.isNotEmpty(nowEmail)) {
			example.createCriteria().andEmailEqualTo(nowEmail);
		}
		// 判断原来的支付密码是否正确
		if (StringUtils.isNotEmpty(payCode)) {
			return cashAccountMapper.updateByPrimaryKeySelective(cashAccount);
		}
		// 判断原来的密码是否正确
		if (StringUtils.isNotEmpty(nowPassword)) {
			example.createCriteria().andUsernameEqualTo(user.getUsername()).andPasswordEqualTo(nowPassword);
		} else {
			example.createCriteria().andUsernameEqualTo(user.getUsername());
		}
		return userInfoMapper.updateByExampleSelective(user, example);
	}

	@Override
	public UserExpand searchUserExpandByUserId(Long userId) throws Exception {
		UserExpandExample example = new UserExpandExample();
		example.createCriteria().andUserIdEqualTo(userId);
		return (UserExpand) userExpandMapper.selectByExample(example);
	}

	@Override
	public int insertUserExpand(UserExpand userExpand) throws Exception {
		UserExpandExample example = new UserExpandExample();
		// 根据用户编号查询是否完善过用户资料
		example.createCriteria().andUserIdEqualTo(userExpand.getUserId());
		int count = userExpandMapper.countByExample(example);
		if (count > 0) {
			// 已完善过用户资料，修改用户资料
			return userExpandMapper.updateByExampleSelective(userExpand, example);
		} else {
			// 未完善过用户资料，添加用户资料
			return userExpandMapper.insertSelective(userExpand);
		}
	}

	@Override
	public UserExpand getUserExpandByUserId(Long userId) throws Exception {
		UserExpandExample example = new UserExpandExample();
		// 根据用户编号查询是否完善过用户资料
		example.createCriteria().andUserIdEqualTo(userId);
		List<UserExpand> list = userExpandMapper.selectByExample(example);
		UserExpand userExpand = null;
		if (list != null && list.size() > 0)
			userExpand = list.get(0);
		return userExpand;
	}

	@Override
	public boolean updateUserExpandSelective(UserExpand expand) throws Exception {
		if (expand == null)
			return false;
		int result = 0;
		if (expand.getId() == null) {
			result = userExpandMapper.insertSelective(expand);
		} else {
			UserExpandExample example = new UserExpandExample();
			example.createCriteria().andUserIdEqualTo(expand.getUserId());
			result = userExpandMapper.updateByExampleSelective(expand, example);
		}
		return result > 0;
	}

	@Override
	public UserInfo searchUserByUserName(String username) throws Exception {
		UserInfoExample example = new UserInfoExample();
		example.createCriteria().andUsernameEqualTo(username);
		List<UserInfo> userList = userInfoMapper.selectByExample(example);
		if (userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}

	@Override
	public Page<DeliveryAddr> queryAddressList(Long userId) throws Exception {
		DeliveryAddrExample example = new DeliveryAddrExample();
		example.createCriteria().andUserIdEqualTo(userId).andEnabledEqualTo(1);
		PageHelper.startPage(1, 5);
		return (Page<DeliveryAddr>) addressMapper.selectByExample(example);
	}

	@Override
	public int insertAddress(DeliveryAddr address) throws Exception {
		return addressMapper.insertSelective(address);
	}

	@Override
	public DeliveryAddr searchAddress(Long id) throws Exception {
		return addressMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateAddress(DeliveryAddr address) throws Exception {
		return addressMapper.updateByPrimaryKeySelective(address);
	}

	@Override
	public int deleteAddress(Long id) throws Exception {
		return addressMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Page<CashLog> queryCashLog(Long userId, String[] code, Integer pageNum, Integer pageSize) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		return integralVoMapper.queryCahsLog(userId, code, null, null);
	}

	@Override
	public Page<IntegralLog> queryIntegralDetailList(Long userId, String[] code, Integer pageNum, Integer pageSize,
			String startTime, String endTime) throws Exception {
		PageHelper.startPage(pageNum, pageSize);
		Page<IntegralLog> list = integralVoMapper.queryIntegralLog(userId, code, startTime, endTime);
		return list;
	}

	@Override
	public Page<BankInfo> queryBankList(Long userId, Integer status) throws Exception {
		BankInfoExample example = new BankInfoExample();
		example.createCriteria().andUserIdEqualTo(userId).andStatusEqualTo(status);
		PageHelper.startPage(1, 5);
		return (Page<BankInfo>) bankMapper.selectByExample(example);
	}

	@Override
	public int insertBank(BankInfo bank) throws Exception {
		BankInfoExample example = new BankInfoExample();
		example.createCriteria().andStatusEqualTo(2).andUserIdEqualTo(bank.getUserId());
		int bankNum = bankMapper.countByExample(example);
		if (bankNum >= 5) {
			return 0;
		} else {
			return bankMapper.insertSelective(bank);
		}
	}

	@Override
	public int searchBankByStatus(String carkNum, Long userId) throws Exception {
		BankInfoExample example = new BankInfoExample();
		example.createCriteria().andStatusEqualTo(3).andUserIdEqualTo(userId).andCarkNumEqualTo(carkNum);
		int bankNum = bankMapper.countByExample(example);
		if (bankNum >= 3)
			return 0;
		else
			return 1;
	}

	@Override
	public UserInfo selectUserById(Long userId) throws Exception {
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(userId);
		return userInfo;
	}

	@Override
	public Page<UserInfo> querySystemMallUserPage(String reqistSource, String userName, String email, String phone,
			Date createDate, Date createEnd, Long provinceId, Long cityId, Long areaId, Integer status,
			Integer pageNum, Integer pageSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("provinceId", provinceId);
		map.put("cityId", cityId);
		map.put("areaId", areaId);
		UserInfoExample example = new UserInfoExample();
		Criteria criteria = example.createCriteria();
		if (null != reqistSource)
			criteria.andReqistSourceEqualTo(reqistSource);
		if (null != userName)
			criteria.andUsernameEqualTo(userName);

		if (null != email)
			criteria.andEmailEqualTo(email);

		if (null != phone)
			criteria.andPhoneNumEqualTo(phone);
		if (null != status)
			criteria.andEnabledEqualTo(status);

		if (null != createDate)
			criteria.andRegistTimeGreaterThan(createDate);

		if (null != createEnd)
			criteria.andRegistTimeLessThan(createEnd);
		example.setOrderByClause("id desc");
		PageHelper.startPage(pageNum, pageSize);
		return (Page<UserInfo>) userInfoMapper.selectByExample(example);
		/* selectByExampleMap(example,map); */
		/* querySystemMallUserList(map); */
	}

	// 重置密码用户
	@Override
	public Boolean updateUserPassWord(List<Long> ids) throws Exception {
		Boolean bl = true;
		for (int i = 0; i < ids.size(); i++) {
			// 随机六位数
			String password = String.valueOf(Math.round(Math.random() * 1000000));
			UserInfo userInfo = userInfoMapper.selectByPrimaryKey(ids.get(i));
			userInfo.setPassword(EncodeMD5.GetMD5Code(password));
			UserInfoExample example = new UserInfoExample();
			example.createCriteria().andIdEqualTo(userInfo.getId());
			int j = userInfoMapper.updateByExampleSelective(userInfo, example);
			if (j < 1)
				throw new Exception("重置密码异常！");
			// 发送短信告知
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("username", userInfo.getUsername());
			map.put("code", password);
			try {
				MessageUtil.SendVerification(userInfo.getPhoneNum(), map, MessageUtil.BACKSELLERPASSWORDPHONE);
			} catch (Exception e) {
				throw new Exception("重置前台用户密码——发送短信异常：", e);
			}
		}
		return bl;
	}

	@Override
	public Boolean updateUserEnabled(long l, List<Long> ids, Integer integer) throws Exception {
		Boolean bl = true;
		for (int i = 0; i < ids.size(); i++) {
			UserInfo userInfo = userInfoMapper.selectByPrimaryKey(ids.get(i));
			userInfo.setEnabled(integer);
			int j = userInfoMapper.updateByPrimaryKeySelective(userInfo);
			if (j < 1) {
				return false;
			}
		}
		return bl;
	}

	@Override
	public int updateUserAvatarById(Long id, String imgs) throws Exception {
		UserInfo userInfo = userInfoMapper.selectByPrimaryKey(id);
		userInfo.setAvatar(imgs);
		return userInfoMapper.updateByPrimaryKeySelective(userInfo);
	}

	@Override
	public BankInfo queryBankById(Long id) throws Exception {
		return bankMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateUserLevel() throws Exception {
		List<UserInfo> list = userInfoMapper.selectByExample(null);
		Date lastYear = BaseUtil.beforYear(new Date(), 1);
		BigDecimal level1 = new BigDecimal(500);
		BigDecimal level2 = new BigDecimal(1000);
		BigDecimal level3 = new BigDecimal(3000);
		BigDecimal level4 = new BigDecimal(10000);
		Integer sumLv = 0, numLv = 0;
		BigDecimal sum;
		Long number = 0L;
		for (UserInfo user : list) {
			Map<String, Object> map = orderManageMapper.getUserOrderPriceSum(user.getId(), lastYear);
			sum = (BigDecimal) map.get("total");
			sum = sum == null ? new BigDecimal(0) : sum;
			number = (Long) map.get("number");
			number = number == null ? 0L : number;
			if (sum.compareTo(level4) >= 0)
				sumLv = 4;
			else if (sum.compareTo(level3) >= 0)
				sumLv = 3;
			else if (sum.compareTo(level2) >= 0)
				sumLv = 2;
			else if (sum.compareTo(level1) >= 0)
				sumLv = 1;
			else
				sumLv = 0;
			if (number >= 40)
				numLv = 4;
			else if (number >= 20)
				numLv = 3;
			else if (number >= 10)
				numLv = 2;
			else if (number >= 3)
				numLv = 1;
			else
				numLv = 0;
			user.setLevel(sumLv > numLv ? sumLv : numLv);
			userInfoMapper.updateByPrimaryKeySelective(user);
		}
		return true;
	}

	@Override
	public boolean updateUserThirdDay() throws Exception {
		UserInfoExample example = new UserInfoExample();
		example.createCriteria().andThirdDayLessThan(10);
		List<UserInfo> list = userInfoMapper.selectByExample(example);
		for (UserInfo user : list) {
			user.setThirdDay(10);
			userInfoMapper.updateByPrimaryKeySelective(user);
		}
		return true;
	}

	@Override
	public boolean judgeSignIn(Long userId) throws Exception {
		Date today = BaseUtil.strToDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		SigninInfoExample example = new SigninInfoExample();
		example.createCriteria().andSigninDateGreaterThanOrEqualTo(today).andUserIdEqualTo(userId);
		List<SigninInfo> list = signinInfoMapper.selectByExample(example);
		return list != null && list.size() > 0;
	}

	@Override
	public boolean addSignIn(Long userId) throws Exception {
		BigDecimal give = new BigDecimal(2); // 签到赠送金元宝数量
		SigninInfo info = new SigninInfo();
		info.setUserId(userId);
		info.setSigninDate(new Date());
		signinInfoMapper.insertSelective(info);
		CashAccountExample example = new CashAccountExample();
		example.createCriteria().andAccountIdEqualTo(userId).andAccountTypeEqualTo(0);
		List<CashAccount> list = cashAccountMapper.selectByExample(example);
		if (list == null || list.size() < 1)
			throw new Exception("会员帐号异常(无法获取账户资金信息)！");
		CashAccount account = list.get(0);
		BigDecimal balance = account.getBalance(); // 签到送1个金元宝
		account.setBalance(balance.add(give));
		cashAccountMapper.updateByPrimaryKey(account);
		CashLog log = new CashLog(); // 添加会员购买记录
		Date now = new Date();
		log.setSendId(userId);
		log.setReviewId(Long.valueOf(ConstantsConfigurer.getProperty("FINANCE_USERID")));
		log.setAmount(give);
		log.setPayType(7);
		log.setPayWay(4);
		log.setBusinessCode(PAYCODE.B009);
		log.setBusinessName(PAYCODE.B009N);
		log.setReviewBefor(balance);
		log.setReviewAfter(balance.add(give));
		log.setCreateTime(now);
		cashLogMapper.insertSelective(log); // 添加签到送元宝记录
		return true;
	}

	@Override
	public UserInfo getUserByOpenId(String openId) {
		UserInfoExample example = new UserInfoExample();
		example.createCriteria().andQqOpenIdEqualTo(openId);
		List<UserInfo> users = userInfoMapper.selectByExample(example);
		return users.size()>0?users.get(0):null;
	}

	@Override
	public boolean addInviteTomat(int inviteCode,int totamt) throws Exception {
		int num = 0;
		UserInfo user =getUserByInvite(inviteCode);
		if (user != null) {
			CashAccountExample example = new CashAccountExample();
			example.createCriteria().andAccountTypeEqualTo(0).andAccountIdEqualTo(user.getId());
			CashAccount account = cashAccountMapper.selectByExample(example).get(0);
			Long jysmall = Long.valueOf(ConstantsConfigurer.getProperty("FINANCE_USERID"));
			BigDecimal after;
			CashLog log = new CashLog();
			after = account.getBalance().add(new BigDecimal(totamt));
			log.setSendId(jysmall);
			log.setReviewId(user.getId());
			log.setAmount(new BigDecimal(totamt));
			log.setPayType(10);
			log.setPayWay(4);
			log.setBusinessCode(PAYCODE.B013);
			log.setBusinessName(PAYCODE.B013N);
			log.setReviewBefor(account.getBalance());
			log.setReviewAfter(after);
			log.setCreateTime(new Date());
			integralExpiredService.updateIntegralExpiredByEvaluate(user.getId(), totamt);
			account.setBalance(after);
			account.setCreateDate(new Date());
			num += cashAccountMapper.updateByPrimaryKeySelective(account);
			log.setCreateTime(new Date());
			num += cashLogMapper.insertSelective(log);
			return num>0?true:false;
		}
		return false;
	}

	@Override
	public UserInfo getUserByInvite(Integer invite) {
		UserInfoExample example = new UserInfoExample();
		example.createCriteria().andInviteEqualTo(invite);
		List<UserInfo> users = userInfoMapper.selectByExample(example);
		return users.size()>0?users.get(0):null;
	}
}