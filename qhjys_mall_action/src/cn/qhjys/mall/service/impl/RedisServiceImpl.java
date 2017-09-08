package cn.qhjys.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import cn.qhjys.mall.common.Base;
import cn.qhjys.mall.service.RedisService;

@Service
public class RedisServiceImpl extends Base implements RedisService {
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public RedisTemplate<String, Object> getRedisTemplate() throws Exception {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) throws Exception {
		this.redisTemplate = redisTemplate;
	}

	/**
	 * 得到某一个Key对应的缓存的值
	 * 
	 * @param key
	 * @return
	 */
	@Override
	public Object getValueByKey(String key) {
		return redisTemplate.boundValueOps(key).get();
	}

	/**
	 * 往某一个KEY里面设置Value值
	 * 
	 * @param key
	 * @param value
	 */
	@Override
	public void setValueByKey(String key, Object value){
		redisTemplate.boundValueOps(key).set(value);
		
	}

	/**
	 * 删除某一个key
	 * 
	 * @param key
	 */
	@Override
	public void delKey(String key){
		redisTemplate.delete(key);
	}

}