package cn.qhjys.mall.weixin.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 订单相关工具类
 * @author liulw
 * @date 20160309
 */
public class OrderUtil {
	
	
	public static String getOrderNo(String ty){
		StringBuilder sb = new StringBuilder();
		sb.append(ty);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssFFF");
		sb.append(sdf.format(new Date()))
		  .append((int)(Math.random()*100000));
		return sb.toString();
	}
    
}
