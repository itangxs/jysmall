package cn.qhjys.mall.service;

public interface RedisService {

	/**
	 * 得到某一个Key对应的缓存的值
	 * 
	 * @param key
	 * @return
	 */
	public Object getValueByKey(String key) ;

	/**
	 * 往某一个KEY里面设置Value值
	 * 
	 * @param key
	 * @param value
	 */
	public void setValueByKey(String key, Object value);

	/**
	 * 删除某一个key
	 * 
	 * @param key
	 */
	public void delKey(String key);

}
