package cn.qhjys.mall.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.liferay.util.JS;

public class Base {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	protected String goUrl(String url, String msg) {
		return "redirect:/temp.jsp?msg=" + JS.encodeURIComponent(msg) + "&url=" + url;
	}
}