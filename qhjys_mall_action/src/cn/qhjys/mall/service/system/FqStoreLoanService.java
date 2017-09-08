package cn.qhjys.mall.service.system;

import java.math.BigDecimal;
import java.util.Date;

public interface FqStoreLoanService {
	public int insertFqStoreLoan(Long storeId, String storeName, Long sellerId, BigDecimal loanTotal, Integer payType, 
			Date beginTime, String bankName, String cardAccount, Date createTime);
}
