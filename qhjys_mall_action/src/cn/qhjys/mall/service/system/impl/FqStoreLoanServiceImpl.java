package cn.qhjys.mall.service.system.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.FqStoreLoan;
import cn.qhjys.mall.mapper.FqStoreLoanMapper;
import cn.qhjys.mall.service.system.FqStoreLoanService;

@Service("FqStoreLoanService")
public class FqStoreLoanServiceImpl implements FqStoreLoanService {
	@Autowired
	FqStoreLoanMapper fqStoreLoanMapper;
	@Override
	public int insertFqStoreLoan(Long storeId, String storeName, Long sellerId, BigDecimal loanTotal, Integer payType,
			Date loanDate, String bankName, String cardAccount, Date createTime) {
		FqStoreLoan fqStoreLoan = new FqStoreLoan();
		fqStoreLoan.setStoreId(storeId);
		fqStoreLoan.setStoreName(storeName);
		fqStoreLoan.setSellerId(sellerId);
		fqStoreLoan.setLoanTotal(loanTotal);
		fqStoreLoan.setNorepayment(loanTotal);
		fqStoreLoan.setInterestTotal(new BigDecimal(0));
		fqStoreLoan.setLoanCycle(payType);
		fqStoreLoan.setLoanDate(loanDate);
		fqStoreLoan.setCreateTime(createTime);
		fqStoreLoan.setBankName(bankName);
		fqStoreLoan.setCardAccount(cardAccount);
		Calendar cal = Calendar.getInstance();
		cal.setTime(loanDate);
		cal.add(Calendar.DATE, payType*7); // 计算放款结束时间
		Date endDate = cal.getTime();
		fqStoreLoan.setEndDate(endDate);
		return fqStoreLoanMapper.insertSelective(fqStoreLoan);
	}

}
