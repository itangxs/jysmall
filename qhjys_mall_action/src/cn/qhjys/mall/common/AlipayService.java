package cn.qhjys.mall.common;

import com.alipay.demo.trade.config.Configs;
import com.alipay.demo.trade.service.AlipayTradeService;
import com.alipay.demo.trade.service.impl.AlipayTradeServiceImpl;

public class AlipayService {
	
	public static AlipayTradeService   tradeService;
	static {
        Configs.init("config/zfbinfo.properties");
        tradeService = new AlipayTradeServiceImpl.ClientBuilder().build();
    }
	
}
