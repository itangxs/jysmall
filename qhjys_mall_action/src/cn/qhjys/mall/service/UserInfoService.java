package cn.qhjys.mall.service;

import java.util.Date;
import java.util.List;

import cn.qhjys.mall.entity.BankInfo;
import cn.qhjys.mall.entity.CashAccount;
import cn.qhjys.mall.entity.CashLog;
import cn.qhjys.mall.entity.DeliveryAddr;
import cn.qhjys.mall.entity.IntegralLog;
import cn.qhjys.mall.entity.UserExpand;
import cn.qhjys.mall.entity.UserInfo;

import com.github.pagehelper.Page;

/***
 * 会员信息
 * 
 * @author zengrong
 *
 */
public interface UserInfoService {

	/***
	 * 注册会员信息
	 * 
	 * @param user
	 * @return
	 */
	boolean insertUser(UserInfo user) throws Exception;

	/**
	 * 查找用户(用户名、邮箱、手机)
	 * 
	 * @param str
	 * @return
	 */
	UserInfo selecUserByStr(String str) throws Exception;

	/**
	 * 更新用户信息
	 * 
	 * @param user
	 * @return
	 */
	boolean updateUserById(UserInfo user) throws Exception;

	/***
	 * 交易身份证号码是否唯一
	 * 
	 * @param id
	 *            用户编号
	 * @param CardId
	 *            身份证号码
	 * @return
	 */
	boolean validateUserByCardId(Long id, String CardId) throws Exception;

	/***
	 * 会员账户设置
	 * 
	 * @param user
	 *            用户信息对象
	 * @param nowPassword
	 *            当前密码
	 * @param nowPhoneNum
	 *            当前手机号码
	 * @param nowEmail
	 *            当前邮箱
	 * @param payCode
	 *            支付密码
	 * @param cashAccount
	 *            账户信息对象
	 * @return
	 */
	int updateUser(UserInfo user, String nowPassword, String nowPhoneNum, String nowEmail, String payCode,
			CashAccount cashAccount) throws Exception;

	/***
	 * 根据用户编号查询要修改的用户资料
	 * 
	 * @param userId
	 *            用户编号
	 * @return
	 */
	UserExpand searchUserExpandByUserId(Long userId) throws Exception;

	/***
	 * 完善个人资料(添加个人资料)
	 * 
	 * @param userExpand
	 * @return
	 */
	int insertUserExpand(UserExpand userExpand) throws Exception;

	/***
	 * 查询用户扩展信息
	 * 
	 * @param userId
	 *            用户编号
	 * @return
	 */
	UserExpand getUserExpandByUserId(Long userId) throws Exception;

	/**
	 * 保存用户扩展信息(修改或添加)
	 * 
	 * @param expand
	 * @return
	 */
	boolean updateUserExpandSelective(UserExpand expand) throws Exception;

	/***
	 * 根据用户名获取用户对象
	 * 
	 * @param username
	 *            用户名
	 * @return
	 */
	UserInfo searchUserByUserName(String username) throws Exception;

	/***
	 * 根据用户编号查询收货地址
	 * 
	 * @param userId
	 *            用户编号
	 * @return
	 */
	Page<DeliveryAddr> queryAddressList(Long userId) throws Exception;

	/***
	 * 添加收货地址(我的账户)
	 * 
	 * @param address
	 * @return
	 */
	int insertAddress(DeliveryAddr address) throws Exception;

	/***
	 * 根据收货地址表的id获取收货信息(以作修改)
	 * 
	 * @param id
	 * @return
	 */
	DeliveryAddr searchAddress(Long id) throws Exception;

	/***
	 * 修改收货地址(我的账户)
	 * 
	 * @param address
	 * @return
	 */
	int updateAddress(DeliveryAddr address) throws Exception;

	/***
	 * 删除收货地址(我的账户)
	 * 
	 * @param id
	 * @return
	 */
	int deleteAddress(Long id) throws Exception;

	/**
	 * 查询用户交易明细
	 * 
	 * @param userId
	 * @param code
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	Page<CashLog> queryCashLog(Long userId, String[] code, Integer pageNum, Integer pageSize) throws Exception;

	/**
	 * 查询会员积分明细
	 * 
	 * @param userId
	 * @param code
	 * @param pageNum
	 * @param pageSize
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 */
	Page<IntegralLog> queryIntegralDetailList(Long userId, String[] code, Integer pageNum, Integer pageSize,
			String startTime, String endTime) throws Exception;

	/***
	 * 根据用户编号查询用户所拥有的银行卡信息
	 * 
	 * @param userId
	 * @return
	 */
	Page<BankInfo> queryBankList(Long userId, Integer status) throws Exception;

	/***
	 * 添加银行卡信息
	 * 
	 * @param bank
	 * @return
	 */
	int insertBank(BankInfo bank) throws Exception;

	/***
	 * 根据用户编号和卡号查询用户是否认证错误超过三次(添加时校验)
	 * 
	 * @param carkNum
	 *            银行卡号
	 * @param userId
	 *            用户编号
	 * @return
	 */
	int searchBankByStatus(String carkNum, Long userId) throws Exception;

	/**
	 * 
	 * @Title: selectUserById 通过用户ID获取用户
	 * @param userId
	 *            用户ID
	 * @return
	 * @throws Exception
	 * @return UserInfo 返回用户
	 */
	UserInfo selectUserById(Long userId) throws Exception;

	/**
	 * 系统用户找出商城前端用户分页
	 * 
	 * @param userId
	 *            会员ID
	 * @param userName
	 *            会员名称
	 * @param phone
	 *            手机号码
	 * @param Email
	 *            邮件
	 * @param createDate
	 *            大于创建时间
	 * @param createEnd
	 *            小于创建时间
	 * @param pageNum
	 *            页数
	 * @param pageSize
	 *            每页显示
	 * @param provinceId
	 *            省级ID
	 * @param cityId
	 *            市级ID
	 * @param status
	 *            状态
	 * @param Area
	 *            地区ID
	 * @return
	 * @throws Exception
	 * @return Page<UserInfo>
	 */
	Page<UserInfo> querySystemMallUserPage(String reqistSource, String userName, String email, String phone, Date createDate,
			Date createEnd, Long provinceId, Long cityId, Long areaId, Integer status, Integer pageNum, Integer pageSize)
			throws Exception;

	/**
	 * 重置密码
	 * 
	 * @param strlist
	 *            商城前台用户
	 * @return
	 * @return Boolean
	 */
	Boolean updateUserPassWord(List<Long> strlist) throws Exception;

	/**
	 * 是否开启
	 * 
	 * @param l
	 *            系统用户
	 * @param strlist
	 *            商城前台用户 数组
	 * @param integer
	 *            状态码
	 * @return
	 * @return Boolean
	 */
	Boolean updateUserEnabled(long l, List<Long> strlist, Integer integer) throws Exception;

	/**
	 * 
	 * updateUserAvatarById 修改用户头像
	 * 
	 * @param id
	 *            用户ID
	 * @param imgs
	 *            头像地址
	 * @return
	 */
	int updateUserAvatarById(Long id, String imgs) throws Exception;

	/***
	 * 根据id查询银行详情
	 * 
	 * @param id
	 * @return
	 */
	BankInfo queryBankById(Long id) throws Exception;

	/**
	 * 更新用户等级
	 * 
	 * @return
	 */
	boolean updateUserLevel() throws Exception;

	/**
	 * 更新用户关联第三方账号次数
	 * 
	 * @return
	 * @throws Exception
	 */
	boolean updateUserThirdDay() throws Exception;

	/**
	 * 判断用户是否签到
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	boolean judgeSignIn(Long userId) throws Exception;

	/**
	 * 用户签到
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	boolean addSignIn(Long userId) throws Exception;
	
	public UserInfo getUserByOpenId(String openId);
	
	public boolean addInviteTomat(int inviteCode,int totamt) throws Exception;
	
	public UserInfo getUserByInvite(Integer invite);
}