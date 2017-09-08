package cn.qhjys.mall.weixin.bean;

import java.util.Map;

/**
 * 用于XML 返回不定element
 */
public interface DynamicField {

	/**
	 * 生成动态属性
	 * @param dataMap xml 转换后的map数据
	 */
	void buildDynamicField(Map<String,String> dataMap);
}
