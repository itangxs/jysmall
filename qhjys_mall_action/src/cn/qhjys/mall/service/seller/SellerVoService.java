package cn.qhjys.mall.service.seller;

import cn.qhjys.mall.entity.CashAccount;
import cn.qhjys.mall.entity.CashLog;
import cn.qhjys.mall.entity.CashSaveWithdraw;
import cn.qhjys.mall.entity.CompanyInfo;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.vo.seller.SellerAccoutOverviewVo;
import cn.qhjys.mall.vo.seller.SellerBankVo;
import cn.qhjys.mall.vo.seller.SellerCenterVo;
import com.github.pagehelper.Page;

public interface SellerVoService {

	/**
	 * 
	 * @Title: querySellerCenterVoByUiaD 通过商家ID 已经当天时间获取到商家中心需要的数据VO
	 * @param id
	 *            商家ID
	 * @return
	 * @return SellerCenterVo
	 */
	public SellerCenterVo querySellerCenterVoByUiaD(Long id) throws Exception;

	/***
	 * 判断该商家注册的店铺是否审核通过
	 * 
	 * @param sellerId
	 *            商家编号
	 * @return
	 */
	public boolean queryStoreBySellerId(Long sellerId) throws Exception;

	/**
	 * 
	 * @Title: querySellerAccoutOverviewVoBySid 通过商家ID找出商家总览所需要得分页数据VO
	 * @param id
	 *            商家ID
	 * @param status
	 *            状态 0 :表示全部 1:收入 2:退款
	 * @param date
	 *            操作时间 开始 注:可空
	 * @param date2
	 *            操作时间 结束 注:可空
	 * @param pageNum
	 *            页数
	 * @param pageSize
	 *            每页显示
	 * @return
	 * @throws Exception
	 * @return Page<SellerAccoutOverviewVo>
	 */
	public Page<SellerAccoutOverviewVo> querySellerAccoutOverviewVoBySid(Long id, Integer status, String startTime,
			String endTime, Integer pageNum, Integer pageSize) throws Exception;

	/**
	 * 
	 * @Title: querySellerBankCountBySid 通过商家ID 找出商家的银行卡总数量
	 * @param id
	 *            商家ID
	 * @return
	 * @throws Exception
	 * @return Integer
	 */
	public Integer querySellerBankCountBySid(Long id) throws Exception;

	/**
	 * 
	 * @Title: querysellerBankPage 通过商家的ID找出商家银行卡的分页数据
	 * @param id
	 *            商家ID
	 * @param page
	 *            页数
	 * @param pageSize
	 *            每页显示
	 * @return
	 * @throws Exception
	 * @return Page<SellerBankVo>
	 */
	public Page<SellerBankVo> querysellerBankPage(Long id, Integer page, Integer pageSize) throws Exception;

	/**
	 * 
	 * @Title: deleteSellerBankById 删除商家银行卡
	 * @param uid
	 *            商家ID
	 * @param bid
	 *            银行卡ID
	 * @return
	 * @throws Exception
	 * @return Boolean true :成功 false:失败
	 */
	public Boolean deleteSellerBankById(Long uid, Long bid) throws Exception;

	/**
	 * 
	 * @Title: updateSellerBankDefault 将商家的银行卡设置成默认的
	 * @param uid
	 *            商家ID
	 * @param bid
	 *            银行卡ID
	 * @return
	 * @throws Exception
	 * 
	 * @return Boolean true :成功 false:失败
	 */
	public Boolean updateSellerBankDefault(Long uid, Long bid) throws Exception;

	/**
	 * 
	 * @Title: saveSellerBank 添加银行卡
	 * @param uid
	 *            商家ID
	 * @param theBank
	 *            所属银行
	 * @param subbranchName
	 *            支行名称
	 * @param realName
	 *            真实姓名
	 * @param bankAccout
	 *            银行账户
	 * @return String "0000":成功 "0001":失败 "0002":银行卡已经存在 "0003":银行卡错误
	 */
	public String saveSellerBank(Long uid, String theBank, String subbranchName, String realName, String bankAccout)
			throws Exception;

	/***
	 * 根据商家编号查询商家账户对象
	 * 
	 * @param sellerId
	 *            商家编号
	 * @return
	 */
	public CashAccount queryAccountBySellerId(Integer accountType, Long sellerId) throws Exception;

	/***
	 * 根据商家编号和操作类型查询充值或提现列表
	 * 
	 * @param pageNum
	 *            分页参数
	 * @param pageSize
	 *            分页参数
	 * @param pyaType
	 *            操作类型
	 * @param cashId
	 *            账户编号
	 * @return
	 */
	public Page<CashSaveWithdraw> queryCashSaveWithdraw(Integer pageNum, Integer pageSize, Integer pyaType, Long cashId)
			throws Exception;

	/***
	 * 添加充值或提现记录
	 * 
	 * @param cash
	 *            充值提现对象
	 * @return
	 */
	public boolean insertSaveWithdraw(CashSaveWithdraw cash) throws Exception;

	/***
	 * 修改充值记录的状态(支付结果返回时调用)
	 * 
	 * @param cash
	 * @return
	 */
	public boolean updateSaveWithdraw(CashSaveWithdraw cash) throws Exception;

	/***
	 * 修改账号余额
	 * 
	 * @param cashAccount
	 * @return
	 */
	public boolean updateCashAccount(CashAccount cashAccount) throws Exception;

	/***
	 * 添加账号交易记录表
	 * 
	 * @param cashLog
	 * @return
	 */
	public boolean insertCashLog(CashLog cashLog) throws Exception;

	/***
	 * 调用连连支付回调函数更新充值信息记录
	 * 
	 * @param cash
	 * @param cashAccount
	 * @param cashLog
	 * @return
	 */
	public boolean insertRechargeIsSucess(CashSaveWithdraw cash, CashAccount cashAccount, CashLog cashLog)
			throws Exception;

	/***
	 * 商家提现
	 * 
	 * @param account
	 * @param cashSave
	 * @return
	 */
	public boolean insertWithdraws(CashAccount account, CashSaveWithdraw cashSave) throws Exception;

	/**
	 * 用户登录
	 * 
	 * @param contactname
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * @throws Exception
	 */
	public SellerInfo updateSellerByAccountPasswordLogin(String contactname, String password) throws Exception;

	/***
	 * 根据商家编号查询商家公司法人姓名和身份证号码
	 * 
	 * @param sellerId
	 * @return
	 */
	public CompanyInfo queryCompanyBySellerId(Long sellerId) throws Exception;

	/***
	 * 
	 * @param orderNum
	 * @return
	 */
	public CashSaveWithdraw queryCashSaveWithdrawByOrderNum(String orderNum) throws Exception;

	/**
	 * 
	 * @Title: updateSellerByPhone 通过手机号码获取Sellser用户
	 * @param phone
	 *            手机号码
	 * @return
	 * @return SellerInfo
	 */
	public SellerInfo updateSellerByPhone(String phone) throws Exception;

	/***
	 * 根据商家编号查询商家店铺信息(商家登录接口用)
	 * 
	 * @param sellerId
	 * @return
	 */
	public StoreInfo queryStore(Long sellerId) throws Exception;
}
