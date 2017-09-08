package cn.qhjys.mall.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class ConstantsConfigurer extends PropertyPlaceholderConfigurer {
	private static Map<String, String> ctxPropertiesMap;
	private static String userKey = "user_key";
	private static String sellerKey = "seller_key";
	private static String wxuserkey = "wx_user_key";

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props)
			throws BeansException {
		super.processProperties(beanFactory, props);
		ctxPropertiesMap = new HashMap<String, String>();
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			ctxPropertiesMap.put(keyStr, value);
		}
	}

	public static String getProperty(String name) {
		return ctxPropertiesMap.get(name);
	}

	public static String getUser() {
		return ctxPropertiesMap.get(userKey);
	}

	public static String getSeller() {
		return ctxPropertiesMap.get(sellerKey);
	}
	public static String getWxUser() {
		return ctxPropertiesMap.get(wxuserkey);
	}
	
}