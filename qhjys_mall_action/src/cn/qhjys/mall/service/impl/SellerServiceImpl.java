package cn.qhjys.mall.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.common.PAYCODE;
import cn.qhjys.mall.entity.CashAccount;
import cn.qhjys.mall.entity.CashAccountExample;
import cn.qhjys.mall.entity.CashLog;
import cn.qhjys.mall.entity.SellerExpand;
import cn.qhjys.mall.entity.SellerExpandExample;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.SellerInfoExample;
import cn.qhjys.mall.entity.SellerInfoExample.Criteria;
import cn.qhjys.mall.mapper.CashAccountMapper;
import cn.qhjys.mall.mapper.CashLogMapper;
import cn.qhjys.mall.mapper.SellerExpandMapper;
import cn.qhjys.mall.mapper.SellerInfoMapper;
import cn.qhjys.mall.service.SellerService;
import cn.qhjys.mall.util.ConstantsConfigurer;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/***
 * 
 * @author LiXiang
 *
 */
@Service
public class SellerServiceImpl implements SellerService {
	@Autowired
	private SellerInfoMapper sellerInfoMapper;
	@Autowired
	private SellerExpandMapper sellerExpandMapper;
	@Autowired
	private CashAccountMapper cashAccountMapper;
	@Autowired
	private CashLogMapper cashLogMapper;

	@Override
	public SellerInfo getSellerById(Long sellerId) throws Exception {
		return sellerInfoMapper.selectByPrimaryKey(sellerId);
	}

	@Override
	public SellerExpand getSellerExpandById(Long sellerId) throws Exception {
		SellerExpandExample example = new SellerExpandExample();
		example.createCriteria().andSellerIdEqualTo(sellerId);
		List<SellerExpand> list = sellerExpandMapper.selectByExample(example);
		return (list == null || list.size() < 1) ? null : list.get(0);
	}

	@Override
	public boolean updateSellerExpand(SellerExpand expand) throws Exception {
		SellerExpandExample example = new SellerExpandExample();
		example.createCriteria().andSellerIdEqualTo(expand.getSellerId());
		List<SellerExpand> list = sellerExpandMapper.selectByExample(example);
		int result = 0;
		if (list == null || list.size() < 1) {
			expand.setTime(new Date());
			result = sellerExpandMapper.insertSelective(expand);
		} else {
			SellerExpand temp = list.get(0);
			temp.setSellerSex(expand.getSellerSex());
			temp.setProvince(expand.getProvince());
			temp.setCity(expand.getCity());
			temp.setArea(expand.getArea());
			temp.setAddress(expand.getAddress());
			result = sellerExpandMapper.updateByPrimaryKeySelective(temp);
		}
		return result > 0 ? true : false;
	}

	@Override
	public boolean querySeller(String username, String phone) throws Exception {
		SellerInfoExample example = new SellerInfoExample();
		example.createCriteria().andPhoneEqualTo(phone);
		long count = sellerInfoMapper.countByExample(example);
		example.clear();
		example.createCriteria().andUsernameEqualTo(username);
		count += sellerInfoMapper.countByExample(example);
		return count == 0 ? true : false;
	}

	@Override
	public boolean updateSellerById(SellerInfo seller) throws Exception {
		int result = sellerInfoMapper.updateByPrimaryKeySelective(seller);
		return result > 0 ? true : false;
	}

	@Override
	public boolean addSellerInfo(SellerInfo seller) throws Exception {
		int result = sellerInfoMapper.insertSelective(seller);
		CashAccount record = new CashAccount();
		BigDecimal zero = new BigDecimal(0);
		record.setAccountId(seller.getId());
		record.setAccountType(1);
		record.setBalance(zero);
		record.setCreateDate(new Date());
		record.setFreezeIntegral(zero);
		record.setFreezeMoney(zero);
		record.setIntegral(zero);
		record.setPayCode(seller.getPassword());
		record.setWithdrawMoney(zero);
		cashAccountMapper.insertSelective(record);
		return result > 0 ? true : false;
	}

	@Override
	public SellerInfo getSeller(String username) throws Exception {
		
		if (StringUtils.isNotBlank(username)) {
			SellerInfoExample example = new SellerInfoExample();
			example.clear();
			example.createCriteria().andUsernameEqualTo(username);
			List<SellerInfo> list = sellerInfoMapper.selectByExample(example);
			if (list.size() > 0)
				return list.get(0);
		}
		return null;
	}

	@Override
	public Page<SellerInfo> selectSllerInfoBySystem(String account, String phone, Date date, Date date2,
			Integer pageNum, Integer pageSize) throws Exception {
		SellerInfoExample example = new SellerInfoExample();
		example.clear();
		Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(account))
			criteria.andUsernameEqualTo(account);

		if (!StringUtils.isEmpty(phone))
			criteria.andPhoneEqualTo(phone);

		if (null != date)
			criteria.andRegisTimeGreaterThan(date);

		if (null != date2)
			criteria.andRegisTimeLessThan(date2);
		example.setOrderByClause("id desc");

		PageHelper.startPage(pageNum, pageSize);
		return (Page<SellerInfo>) sellerInfoMapper.selectByExample(example);
	}

	@Override
	public Boolean updateSellerEnabledBySystem(long l, List<Long> strlist, Integer staus) throws Exception {
		for (int i = 0; i < strlist.size(); i++) {
			SellerInfo record = sellerInfoMapper.selectByPrimaryKey(strlist.get(i));
			record.setEnabled(staus);
			int j = sellerInfoMapper.updateByPrimaryKeySelective(record);
			if (j == 0)
				return false;
		}
		return true;
	}

	@Override
	public Boolean updateSellerPassWordById(SellerInfo seller, String getMD5Code) throws Exception {
		SellerInfoExample example = new SellerInfoExample();
		example.createCriteria().andIdEqualTo(seller.getId());
		seller.setPassword(getMD5Code);
		int j = sellerInfoMapper.updateByExample(seller, example);
		if (j == 0)
			return false;
		return true;
	}

	@Override
	public SellerInfo getSellerInfoByInvite(Integer invite) {
		SellerInfoExample example = new SellerInfoExample();
		example.createCriteria().andInviteEqualTo(invite);
		List<SellerInfo> list = sellerInfoMapper.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}
	
	@Override
	public boolean addInviteTomat(int inviteCode,int totamt) throws Exception {
		int num = 0;
		SellerInfo seller = getSellerInfoByInvite(inviteCode);
		if (seller != null) {
			CashAccountExample example = new CashAccountExample();
			example.createCriteria().andAccountTypeEqualTo(1).andAccountIdEqualTo(seller.getId());
			CashAccount account = cashAccountMapper.selectByExample(example).get(0);
			Long jysmall = Long.valueOf(ConstantsConfigurer.getProperty("FINANCE_USERID"));
			BigDecimal after;
			CashLog log = new CashLog();
			after = account.getBalance().add(new BigDecimal(totamt));
			log.setSendId(jysmall);
			log.setReviewId(seller.getId());
			log.setAmount(new BigDecimal(totamt));
			log.setPayType(11);
			log.setPayWay(4);
			log.setBusinessCode(PAYCODE.B014);
			log.setBusinessName(PAYCODE.B014N);
			log.setReviewBefor(account.getBalance());
			log.setReviewAfter(after);
			log.setCreateTime(new Date());
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
	public boolean updateSellerWithdrawStatus(Long sellerId,Integer status) throws Exception {
		if (sellerId == null || status == null) {
			return false;
		}
		SellerInfo sellerInfo = new SellerInfo();
		sellerInfo.setId(sellerId);
		sellerInfo.setWithdrawStatus(status);
		int result = sellerInfoMapper.updateByPrimaryKeySelective(sellerInfo);
		return result > 0;
	}
}