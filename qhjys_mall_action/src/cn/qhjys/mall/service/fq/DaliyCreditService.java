package cn.qhjys.mall.service.fq;

import java.math.BigDecimal;
import java.util.Date;

public interface DaliyCreditService {
	public BigDecimal queryDaliyCredit(Long sellerId,Long storeId,Date beginTime,Date endTime);
}
