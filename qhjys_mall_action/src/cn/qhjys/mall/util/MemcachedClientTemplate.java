package cn.qhjys.mall.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import javax.annotation.Resource;
import net.rubyeye.xmemcached.MemcachedClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MemcachedClientTemplate implements Serializable {

	private static final long serialVersionUID = -6831249356115019482L;

	@Resource
	private MemcachedClient memcachedClient;

	@Value("1800")
	private String memcacheExpDefalut;
	@Value("1200")
	private String memcacheTimeoutDefalut;

	public MemcachedClient getMemcachedClient() {
		return memcachedClient;
	}

	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}

	/**
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @param exp
	 *            过期时间单位是秒
	 * @param timeout
	 * @throws Exception
	 */
	public boolean setCacheValue(String key, Object value, Integer exp, Long timeout) throws Exception {
		if (null == exp)
			exp = Integer.parseInt(memcacheExpDefalut);
		if (null == timeout)
			timeout = Long.parseLong(memcacheTimeoutDefalut);
		return memcachedClient.set(key, exp, value, timeout);
	}

	/**
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @param exp
	 *            过期时间单位是秒
	 * @param timeout
	 */
	public boolean setCacheValue(String key, Object value, Integer exp) throws Exception {
		if (null == exp)
			exp = Integer.parseInt(memcacheExpDefalut);
		return memcachedClient.set(key, exp, value);
	}

	/**
	 * 
	 * @param key
	 *            健
	 * @param value
	 *            值
	 * @param exp
	 *            过期时间
	 * @return
	 */
	public boolean replaceKey(String key, Object value, Integer exp) throws Exception {
		if (null == exp)
			exp = Integer.parseInt(memcacheExpDefalut);
		return memcachedClient.replace(key, exp, value);
	}

	/**
	 * 
	 * @param key
	 *            健
	 * @param value
	 *            值
	 * @param exp
	 *            过期时间
	 * @param timeout
	 * @return
	 */
	public boolean replaceKey(String key, Object value, Integer exp, Long timeout) throws Exception {
		if (null == exp)
			exp = Integer.parseInt(memcacheExpDefalut);
		if (null == timeout)
			timeout = Long.parseLong(memcacheTimeoutDefalut);
		return memcachedClient.replace(key, exp, value, timeout);
	}

	public boolean addKey(String key, Object value, Integer exp, Long timeout) throws Exception {
		if (null == exp)
			exp = Integer.parseInt(memcacheExpDefalut);
		if (null == timeout)
			timeout = Long.parseLong(memcacheTimeoutDefalut);
		return memcachedClient.add(key, exp, value, timeout);
	}

	public boolean addKey(String key, Object value, Integer exp) throws Exception {
		if (null == exp)
			exp = Integer.parseInt(memcacheExpDefalut);
		return memcachedClient.add(key, exp, value);
	}

	public boolean deleteByKey(String key) throws Exception {
		return memcachedClient.delete(key);
	}

	/**
	 * 
	 * @param key
	 *            健
	 * @param opTimeout
	 *            操作超时
	 * @return
	 */
	public boolean deleteByKey(String key, Long opTimeout) throws Exception {
		return memcachedClient.delete(key, opTimeout);
	}

	public Object getObjectByKey(String key, Long timeout) throws Exception {
		if (null == timeout)
			timeout = Long.parseLong(memcacheTimeoutDefalut);
		return memcachedClient.get(key, timeout);
	}

	public Map<String, Object> getObjectByKeyCollections(Collection<String> keyCollections, Long timeout)
			throws Exception {
		if (null == timeout)
			timeout = Long.parseLong(memcacheTimeoutDefalut);
		return memcachedClient.get(keyCollections, timeout);
	}
}