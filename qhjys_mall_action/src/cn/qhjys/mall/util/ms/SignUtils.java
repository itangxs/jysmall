package cn.qhjys.mall.util.ms;

import java.util.List;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 签名：1、所有值非空的参数，2、按a-z自然顺序排序，3、去除前后空格
 * 验签：1、获取所有参数，2、按a-z自然顺序排序，3、去除前后空格
 * @author tulu
 *
 */
public class SignUtils {
	protected final static Logger logger = LoggerFactory.getLogger(SignUtils.class);
	private final static String PRIVATE_KEY ="MIIEowIBAAKCAQEAo5Vfkt077WwMREpBrGj2G+cfFtwP713YJHudJ02wFgTOpCwJ63cNZPZu/5nCGMjjjntcQxKCy1F85eg8Fxf6pvV6h0T//XqC65V/+ytJ78s7BF2TXYTP/rp1feS9yYexXsr39xcwbOHhmUc6pHETCrmJ8vq8/xYhWlU+quNUK3mPJsDBzHTVGVr1vNyi9os3kow3Tk1xZSvaLn4slZQjXmcGGhvfRj5FnQPBI7HqwL3WqqpQpSSrynngI/HjZAnWQ2UBDHYAGUKUHZRdgcE0TxAfwOdCczMHTiCW7uYgOPBqBAmj3EvjIMLhzbZE3+nvzgTUf8EJtz3GpJwz6mB04wIDAQABAoIBAGe19kgYdzwJeqV5JrX2PcJm/7DwiDHMH5LRxEr82By9o302ZnugmR9flfP7ObGY+5yD3XSwsbfRzAu+9wn1K/baP3qvZlUAbSyLNf657UBYauxTn3B1GaF3+bF9ak3w96UPrbI8Q3lkpDEXlbhX8CimhS1Fhrl7AF5TGU4inmSIwaZJOcDdrhlnDS5bjTFqzGXnJv3jgLEirnK0lJoctiPkh2FizeIBe3O/WBbasaI3wDXOYGbw5u/OUd92dbeDWuy4/di/YcPWJCpO+6JTaixp7mjQTfRgtaapHTYgbvDo1y/hMhHhSe9G2gBqt3TYxbzM6XIOBxKBrTgrhideNhECgYEA5145+7otwN+z3G0hk13PSsXaJ/J0b9bUY2qudRL8I+68wXrDYIDdDseYVpDfP3rc5qXGh1LgXPmwNOrUfgcO54ttPq4kMFUksB/6d4By9SMbPLwATJFtSv76kJe6JbDZCxByI/wE6Qb+4oGwyc99hR66uzIUU6K0mvUeHmDu4RcCgYEAtP+501q/8coaMySfQHYxtGIClQCSZQHLiDrKiIhQy/xrwq6jA42mIyWlgW44Y2cK1NnINRX6I7UEdiOcKZ+2V74qoqbkPJpuKMv1hEFD6ty45VGhSMiUxAoNMho27UCC3y/4PmmNy6dVgX0cHjEwCGpXTprHoQSMhiiFAeOqshUCgYEAgcoeBNRgLj5Pi2u81gpd6rgqtRYRs1t0MYD69Ln9r7qRMIhJaa1gVJYrIwSJ9/ZYTRrZz7fyHLYE3uqM4VXcSo+wbOULcAGTXynRO5BW7KUQ9nUQReOex99ZeBtlVRdr6gUDwqPxrTc7bJ0MK2m2JCMk5TYmqPQxFGxExQ3jc2kCgYBJsrq6aYAwdodWfbnuaHHyrdm5TANP53w1WyLulvq3JG/IVI2Bu+KC+yg53GRPbTyG0Kppmyo/8cwAQp2qgSKg9Yiss+HJH2RtbibpFB4QepE6ppNSbIHOlP0sgmoNDrB7KjAdUaNv4hG56PRTAto5AvvLwNA6Kv0A/2yxCT+OxQKBgE3mPE9DjhcL0V3nEivva0yRY3KcAaMDhD+zeR/ywCpLk1c/2vfIWrQ/D8yh76n/rioox1XwSIq+d8qJmlrsI65QjaDDp2mDVFA601txoHN+oI7Z4RmzYGCbGSfpGnI1hOP45qFR3y71cS6yi8EzwvEeVpdJzcgK04lngGroJPQK";
	private final static String PUBLIC_KEY ="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAo5Vfkt077WwMREpBrGj2G+cfFtwP713YJHudJ02wFgTOpCwJ63cNZPZu/5nCGMjjjntcQxKCy1F85eg8Fxf6pvV6h0T//XqC65V/+ytJ78s7BF2TXYTP/rp1feS9yYexXsr39xcwbOHhmUc6pHETCrmJ8vq8/xYhWlU+quNUK3mPJsDBzHTVGVr1vNyi9os3kow3Tk1xZSvaLn4slZQjXmcGGhvfRj5FnQPBI7HqwL3WqqpQpSSrynngI/HjZAnWQ2UBDHYAGUKUHZRdgcE0TxAfwOdCczMHTiCW7uYgOPBqBAmj3EvjIMLhzbZE3+nvzgTUf8EJtz3GpJwz6mB04wIDAQAB";
	
	public static TreeMap<String, String> getTreeMap(List<BasicNameValuePair> nvps) {
		TreeMap<String, String> map = new TreeMap<String, String>();
        for (BasicNameValuePair pair : nvps) {
            if (StringUtils.isNotBlank(pair.getValue())) {
            	map.put(pair.getName(), pair.getValue().trim());
            }
        }
        return map;
	}
	
	public static String getSignData(TreeMap<String, String> map) throws Exception {
		StringBuffer buf = new StringBuffer();
        for (String key : map.keySet()) {
            buf.append(key).append("=").append((String) map.get(key)).append("&");
        }
        String signatureStr = buf.substring(0, buf.length() - 1);
        return RSAUtil.signByPrivate(signatureStr, PRIVATE_KEY, "UTF-8");
//        return RSAUtil.signByPrivate(signatureStr, RSAUtil.readFile("/"+SignUtils.class.getClassLoader().getResource("").toString().replaceAll("file:/", "") + ConfigUtils.getProperty(SystemConstant.MS_PRIVATE_KEY_PATH), "UTF-8"), "UTF-8");
	}
	
	public static TreeMap<String, String> strToTreeMap(String str) {
		TreeMap<String, String> map = new TreeMap<String, String>();
        String data[] = str.split("&");
        for (int i = 0; i < data.length; i++) {
            String tmp[] = data[i].split("=", 2);
            map.put(tmp[0], tmp[1].trim());
        }
        return map;
    }
	
	public static boolean verferSignData(TreeMap<String, String> map) {
		StringBuffer buf = new StringBuffer();
        for (String key : map.keySet()) {
            if (StringUtils.isNotBlank(map.get(key)) && !"signature".equals(key)) {
                buf.append(key + "=" + map.get(key) + "&");
            }
        }
        String signatureStr = buf.substring(0, buf.length() - 1);
        return StringUtils.isEmpty(map.get("signature")) ? true : RSAUtil.verifyByKeyPath(signatureStr, map.get("signature"),PUBLIC_KEY, "UTF-8");
//        return StringUtils.isEmpty(map.get("signature")) ? true : RSAUtil.verifyByKeyPath(signatureStr, map.get("signature"),"/"+SignUtils.class.getClassLoader().getResource("").toString().replaceAll("file:/", "") + ConfigUtils.getProperty(SystemConstant.MS_PUBLIC_KEY_PATH), "UTF-8");
    }
}
