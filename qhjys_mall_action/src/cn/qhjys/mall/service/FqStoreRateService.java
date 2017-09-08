package cn.qhjys.mall.service;

import java.math.BigDecimal;
import java.util.HashMap;
import cn.qhjys.mall.entity.StoreInfo;

public interface FqStoreRateService {

	public HashMap<String,BigDecimal> calculateOrderRate(BigDecimal totamt,StoreInfo store,Integer type);

}
