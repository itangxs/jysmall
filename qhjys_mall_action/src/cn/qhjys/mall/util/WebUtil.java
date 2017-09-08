package cn.qhjys.mall.util;


/*
				 _ooOoo_
				o8888888o
				88" . "88
				(| -_- |)
				O\  =  /O
			 ____/`---'\____
		   .'  \\|     |//  `.
		  /  \\|||  :  |||//  \
		 /  _||||| -:- |||||-  \
		 |   | \\\  -  /// |   |
		 | \_|  ''\---/''  |   |
		 \  .-\__  `-`  ___/-. /
		___`. .'  /--.--\  `. . __
	."" '<  `.___\_<|>_/___.'  >'"".
	| | :  `- \`.;`\ _ /`;.`/ - ` : | |
	\  \ `-.   \_ __\ /__ _/   .-` /  /
	======`-.____`-.___\_____/___.-`____.-'======
`=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
佛祖保佑       永无BUG
*/

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 封装jsp常用操作.
 * 
 */
public class WebUtil {

	private WebUtil() {
	}

	/**
	 * 从Spring的ApplicationContext中获取bean.
	 * 
	 * 其中Spring的ApplicationContext通过web.xml中的ContextLoaderListener载入到ServletContext中.
	 */
	public static Object getBean(ServletContext application, String beanName) {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application);
		return context.getBean(beanName);
	}

	/**
	 * 借助Spring的ServletRequestDataBinder 通过反射将request中的参数绑定到目标对象.
	 */
	public static void bind(ServletRequest request, Object target) {
		ServletRequestDataBinder binder = new ServletRequestDataBinder(target);
		binder.bind(request);
	}
}