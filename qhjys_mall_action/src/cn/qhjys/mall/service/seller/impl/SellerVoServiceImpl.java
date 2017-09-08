package cn.qhjys.mall.service.seller.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.common.PAYCODE;
import cn.qhjys.mall.entity.BankInfo;
import cn.qhjys.mall.entity.BankInfoExample;
import cn.qhjys.mall.entity.CashAccount;
import cn.qhjys.mall.entity.CashAccountExample;
import cn.qhjys.mall.entity.CashLog;
import cn.qhjys.mall.entity.CashSaveWithdraw;
import cn.qhjys.mall.entity.CashSaveWithdrawExample;
import cn.qhjys.mall.entity.CompanyInfo;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.SellerInfoExample;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.StoreInfoExample;
import cn.qhjys.mall.mapper.BankInfoMapper;
import cn.qhjys.mall.mapper.CashAccountMapper;
import cn.qhjys.mall.mapper.CashLogMapper;
import cn.qhjys.mall.mapper.CashSaveWithdrawMapper;
import cn.qhjys.mall.mapper.CompanyInfoMapper;
import cn.qhjys.mall.mapper.SellerInfoMapper;
import cn.qhjys.mall.mapper.StoreInfoMapper;
import cn.qhjys.mall.mapper.custom.SellerBankVoMapper;
import cn.qhjys.mall.mapper.custom.SellerCenterMapper;
import cn.qhjys.mall.service.seller.SellerVoService;
import cn.qhjys.mall.vo.seller.SellerAccoutOverviewVo;
import cn.qhjys.mall.vo.seller.SellerBankVo;
import cn.qhjys.mall.vo.seller.SellerCenterVo;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class SellerVoServiceImpl implements SellerVoService {
	@Autowired
	private BankInfoMapper bankInfoMapper;
	@Autowired
	private SellerBankVoMapper bankVoMapper;
	@Autowired
	private SellerCenterMapper sellerCenterMapper;
	@Autowired
	private CashAccountMapper cashAccountMapper;
	@Autowired
	private CashSaveWithdrawMapper cashSaveWithdrawMapper;
	@Autowired
	private CashLogMapper cashLogMapper;
	@Autowired
	private SellerInfoMapper sellerInfoMapper;
	@Autowired
	private CompanyInfoMapper companyMapper;
	@Autowired
	private StoreInfoMapper storeInfoMapper;

	@Override
	public SellerCenterVo querySellerCenterVoByUiaD(Long id) throws Exception {
		SellerCenterVo sellerCenter = sellerCenterMapper.queryStoreBySellerId(id);
		SellerCenterVo seller = sellerCenterMapper.querySellerBySellerId(id);
		SellerCenterVo sellerReview = sellerCenterMapper.queryReviewBySellerId(id);
		if (null != sellerReview) {
			seller.setToDayComment1(sellerReview.getToDayComment1());
			seller.setToDayComment2(sellerReview.getToDayComment2());
			seller.setToDayComment3(sellerReview.getToDayComment3());
			seller.setToDayComment4(sellerReview.getToDayComment4());
			seller.setToDayComment5(sellerReview.getToDayComment5());
			seller.setYesterDayComment1(sellerReview.getYesterDayComment1());
			seller.setYesterDayComment2(sellerReview.getYesterDayComment2());
			seller.setYesterDayComment3(sellerReview.getYesterDayComment3());
			seller.setYesterDayComment4(sellerReview.getYesterDayComment4());
			seller.setYesterDayComment5(sellerReview.getYesterDayComment5());
			seller.setMark(sellerReview.getMark());
		}
		seller.setReleaseGoods(sellerCenter.getReleaseGoods());
		seller.setTradeOrder(sellerCenter.getTradeOrder());
		seller.setRefundmentOrder(sellerCenter.getRefundmentOrder());
		seller.setDate(new Date());
		Map<String, Object> map = new HashMap<String, Object>();
		Date beginDate = new Date();
		map.put("sellerId", id);
		map.put("time", beginDate);
		// 今天的交易数据
		BigDecimal toDayTurnover = sellerCenterMapper.queryOrderAmountBysellerId(map);
		BigDecimal toDayIntegral = sellerCenterMapper.queryOrderIntegralBysellerId(map);
		if (null == toDayTurnover)
			toDayTurnover = new BigDecimal(0);
		if (null == toDayIntegral)
			toDayIntegral = new BigDecimal(0);
		seller.setToDayTurnover(toDayTurnover);
		seller.setToDayIntegral(toDayIntegral);
		int toDayUserNumber = sellerCenterMapper.queryOrderPersonNumBysellerId(map);
		seller.setToDayUserNumber(toDayUserNumber);
		int toDayRefundOrders = sellerCenterMapper.queryRefundOrderNumBysellerId(map);
		seller.setToDayRefundOrders(toDayRefundOrders);
		// 昨天的交易数据
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
		try {
			Date endDate = dft.parse(dft.format(date.getTime()));
			map.put("time", endDate);
		} catch (ParseException e) {
			throw new Exception(e);
		}
		BigDecimal yesterDayTurnover = sellerCenterMapper.queryOrderAmountBysellerId(map);
		BigDecimal yesterDayIntegral = sellerCenterMapper.queryOrderIntegralBysellerId(map);
		if (null == yesterDayTurnover)
			yesterDayTurnover = new BigDecimal(0);
		if (null == yesterDayIntegral)
			yesterDayIntegral = new BigDecimal(0);
		seller.setYesterDayTurnover(yesterDayTurnover);
		seller.setYesterDayIntegral(yesterDayIntegral);
		int yesterDayUserNumber = sellerCenterMapper.queryOrderPersonNumBysellerId(map);
		seller.setYesterDayUserNumber(yesterDayUserNumber);
		int yesterDayRefundOrders = sellerCenterMapper.queryRefundOrderNumBysellerId(map);
		seller.setYesterDayRefundOrders(yesterDayRefundOrders);
		return seller;
	}

	@Override
	public Page<SellerAccoutOverviewVo> querySellerAccoutOverviewVoBySid(Long id, Integer status, String startTime,
			String endTime, Integer pageNum, Integer pageSize) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("sellerId", id);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		PageHelper.startPage(pageNum, pageSize);
		Page<SellerAccoutOverviewVo> list = sellerCenterMapper.querySellerAccoutOverviewVoBySid(map);
		return list;
	}

	@Override
	public Integer querySellerBankCountBySid(Long id) throws Exception {
		BankInfoExample example = new BankInfoExample();
		example.createCriteria().andSellerIdEqualTo(id);
		int i = bankInfoMapper.countByExample(example);
		Integer integer = new Integer(i);
		return integer;
	}

	@Override
	public Page<SellerBankVo> querysellerBankPage(Long id, Integer page, Integer pageSize) throws Exception {
		PageHelper.startPage(page, pageSize);
		Page<SellerBankVo> bankVos = bankVoMapper.getSellerBankVoPage(id);
		return bankVos;
	}

	@Override
	public Boolean deleteSellerBankById(Long uid, Long bid) throws Exception {
		BankInfoExample example = new BankInfoExample();
		example.createCriteria().andIdEqualTo(bid);
		example.createCriteria().andSellerIdEqualTo(uid);
		int row = bankInfoMapper.deleteByExample(example);
		return row > 0 ? true : false;
	}

	@Override
	public Boolean updateSellerBankDefault(Long uid, Long bid) throws Exception {
		bankVoMapper.updateByDefaullOff(uid);
		// BankInfoExample example = new BankInfoExample();
		// BankInfo bankInfo = new BankInfo();
		// bankInfo.setId(bid);
		// example.createCriteria().andIsdefaultEqualTo(1);
		// int row = bankInfoMapper.updateByExample(bankInfo, example);
		int row = bankVoMapper.updateByDefaullOn(uid, bid);
		return row > 0 ? true : false;

	}

	@Override
	public String saveSellerBank(Long uid, String theBank, String subbranchName, String realName, String bankAccout)
			throws Exception {

		BankInfoExample example = new BankInfoExample();
		example.createCriteria().andSellerIdEqualTo(uid).andCarkNumEqualTo(bankAccout);
		List<BankInfo> list = bankInfoMapper.selectByExample(example);
		if (list.size() > 0) {
			return "0002";
		}

		BankInfo bankInfo = new BankInfo();
		bankInfo.setSellerId(uid);
		bankInfo.setName(theBank);
		bankInfo.setBranch(subbranchName);
		bankInfo.setCardholder(realName);
		bankInfo.setCarkNum(bankAccout);
		bankInfo.setIsdefault(0);
		int row = bankInfoMapper.insertSelective(bankInfo);
		return row > 0 ? "0000" : "0001";
	}

	@Override
	public CashAccount queryAccountBySellerId(Integer accountType, Long sellerId) throws Exception {
		CashAccountExample example = new CashAccountExample();
		example.createCriteria().andAccountTypeEqualTo(accountType).andAccountIdEqualTo(sellerId);
		List<CashAccount> list = cashAccountMapper.selectByExample(example);
		CashAccount cashAccount = null;
		if (list.size() > 0) {
			cashAccount = list.get(0);
		}
		return cashAccount;
	}

	@Override
	public Page<CashSaveWithdraw> queryCashSaveWithdraw(Integer pageNum, Integer pageSize, Integer pyaType, Long cashId)
			throws Exception {
		CashSaveWithdrawExample example = new CashSaveWithdrawExample();
		example.createCriteria().andOperTypeEqualTo(pyaType).andCashIdEqualTo(cashId);
		example.setOrderByClause("create_date desc");
		PageHelper.startPage(pageNum, pageSize);
		Page<CashSaveWithdraw> list = (Page<CashSaveWithdraw>) cashSaveWithdrawMapper.selectByExample(example);
		return list;
	}

	@Override
	public boolean insertSaveWithdraw(CashSaveWithdraw cash) throws Exception {
		int row = cashSaveWithdrawMapper.insertSelective(cash);
		return row > 0 ? true : false;
	}

	@Override
	public boolean updateSaveWithdraw(CashSaveWithdraw cash) throws Exception {
		int row = 0;
		CashSaveWithdrawExample example = new CashSaveWithdrawExample();
		example.createCriteria().andOrderNumEqualTo(cash.getOrderNum());
		row = cashSaveWithdrawMapper.updateByExampleSelective(cash, example);
		return row > 0 ? true : false;
	}

	@Override
	public boolean updateCashAccount(CashAccount cashAccount) throws Exception {
		int row = cashAccountMapper.updateByPrimaryKeySelective(cashAccount);
		return row > 0 ? true : false;
	}

	@Override
	public boolean insertCashLog(CashLog cashLog) throws Exception {
		int row = cashLogMapper.insertSelective(cashLog);
		return row > 0 ? true : false;
	}

	@Override
	public boolean insertRechargeIsSucess(CashSaveWithdraw cash, CashAccount cashAccount, CashLog cashLog)
			throws Exception {
		boolean isSuccess = false;
		boolean b = this.updateSaveWithdraw(cash);
		if (b == true) {
			boolean bo = this.updateCashAccount(cashAccount);
			if (bo == true) {
				isSuccess = this.insertCashLog(cashLog);
			}
		}
		return isSuccess;
	}

	@Override
	public boolean insertWithdraws(CashAccount account, CashSaveWithdraw cashSave) throws Exception {
		boolean isSuccess = false;
		boolean b = this.updateCashAccount(account);
		if (!b)
			throw new Exception("修改账号表错误");
		isSuccess = this.insertSaveWithdraw(cashSave);
		if (!isSuccess)
			throw new Exception("添加操作资金表错误");
		CashLog cashLog = new CashLog();
		cashLog.setAmount(cashSave.getMoney());
		cashLog.setSendAfter(account.getBalance());
		cashLog.setSendBefor(account.getBalance().add(cashSave.getMoney()));
		cashLog.setSendId(account.getAccountId());
		cashLog.setBusinessCode(PAYCODE.B008);
		cashLog.setBusinessName(PAYCODE.B008N);
		cashLog.setPayType(4);
		cashLog.setPayWay(4);
		cashLog.setCreateTime(new Date());
		cashLogMapper.insertSelective(cashLog);
		return isSuccess;
	}

	@Override
	public SellerInfo updateSellerByAccountPasswordLogin(String contactname, String password) throws Exception {
		SellerInfoExample example = new SellerInfoExample();
		example.createCriteria().andUsernameEqualTo(contactname.trim());
		
		List<SellerInfo> list = sellerInfoMapper.selectByExample(example);
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public CompanyInfo queryCompanyBySellerId(Long sellerId) throws Exception {
		SellerInfo seller = sellerInfoMapper.selectByPrimaryKey(sellerId);
		CompanyInfo company = companyMapper.selectByPrimaryKey(seller.getCompanyId());
		return company;
	}

	@Override
	public SellerInfo updateSellerByPhone(String phone) throws Exception {
		SellerInfoExample example = new SellerInfoExample();
		example.createCriteria().andPhoneEqualTo(phone);
		List<SellerInfo> list = sellerInfoMapper.selectByExample(example);
		if (list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public CashSaveWithdraw queryCashSaveWithdrawByOrderNum(String orderNum) throws Exception {
		CashSaveWithdrawExample example = new CashSaveWithdrawExample();
		example.createCriteria().andOrderNumEqualTo(orderNum);
		CashSaveWithdraw cashSave = null;
		List<CashSaveWithdraw> cashSaveList = cashSaveWithdrawMapper.selectByExample(example);
		if (cashSaveList.size() > 0)
			cashSave = cashSaveList.get(0);
		return cashSave;
	}

	@Override
	public boolean queryStoreBySellerId(Long sellerId) throws Exception {
		StoreInfoExample example = new StoreInfoExample();
		example.createCriteria().andSellerIdEqualTo(sellerId).andStatusEqualTo(2);
		List<StoreInfo> list = storeInfoMapper.selectByExample(example);
		if (null == list || list.size() == 0)
			return false;
		return true;
	}

	@Override
	public StoreInfo queryStore(Long sellerId) throws Exception {
		StoreInfo storeInfo = new StoreInfo();
		StoreInfoExample example = new StoreInfoExample();
		example.createCriteria().andSellerIdEqualTo(sellerId);
		List<StoreInfo> list = storeInfoMapper.selectByExample(example);
		if (list.size() > 0)
			return list.get(0);
		return storeInfo;
	}
}
