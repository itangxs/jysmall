package cn.qhjys.mall.filter;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import cn.qhjys.mall.entity.RoleMenu;
import cn.qhjys.mall.entity.SellerInfo;
import cn.qhjys.mall.entity.UserInfo;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.HtmlUtil;
import cn.qhjys.mall.util.ServletUtils;

public class LoginInterceptor implements HandlerInterceptor {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private static String[] tempUrl;
	private static List<RoleMenu> groupAndRoles;

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView view)
			throws Exception {
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		String URI = request.getRequestURI() + (request.getQueryString() != null ? "?" + request.getQueryString() : "");
		HttpSession session = request.getSession();
		 if (URI.indexOf("/managermall/seller/orderlist.do") > -1){
			 Cache cache = Cache.getInstance();
				cache.increment(ServletUtils.getIpAddr(request) + URI);
				if (cache.isUpCount(ServletUtils.getIpAddr(request) + URI)) {
					response.setCharacterEncoding("UTF-8");
					response.setContentType("text/html;charset=UTF-8");
					response.setLocale(new java.util.Locale("zh", "CN"));
					response.getWriter().println("到达请求次数，不允许请求，请不要频繁刷新页面，请稍候再试！");
					return false;
				}
		 }
		
		if (URI.indexOf("getCartProd.do") > -1 || URI.indexOf("addCartProd.do") > -1) {
			return true;
		} else if (URI.indexOf("/managermall/account/") > -1) {
			UserInfo user = (UserInfo) session.getAttribute(ConstantsConfigurer.getUser());
			if (user == null) {
				response.sendRedirect("/account/login.do?j=" + HtmlUtil.encodeString(URI, HtmlUtil.UTF_8));
				return false;
			}
		} else if (URI.indexOf("/managermall/seller/") > -1) {
			SellerInfo seller = (SellerInfo) session.getAttribute(ConstantsConfigurer.getSeller());
			if (seller == null) {
				response.sendRedirect("/seller/login.do?j=" + HtmlUtil.encodeString(URI, HtmlUtil.UTF_8));
				return false;
			}
		} else if (URI.indexOf("/managermall/systemmall/") > -1) {
			Object system = session.getAttribute(ConstantsConfigurer.getProperty("system_key"));
			if (system == null) {
				response.sendRedirect("/systemlogin/login.do?j=" + HtmlUtil.encodeString(URI, HtmlUtil.UTF_8));
				return false;
			} else {
				boolean haveRole = false;
				groupAndRoles = (List<RoleMenu>) session.getAttribute("systemmallurl");
				for (RoleMenu groupAndRole : groupAndRoles) {
					tempUrl = groupAndRole.getRoleText().split(",");
					for (String roleUrl : tempUrl)
						if (URI.indexOf(roleUrl) == 0) {
							haveRole = true;
							break;
						}
					if (haveRole)
						break;
				}
				if (!haveRole) {
					response.sendRedirect(request.getContextPath() + "/managermall/systemmall/norole.do");
					return false;
				}
			}
		}
		return true;
	}

	private static class Cache {
		private static final ConcurrentHashMap<String, CopyOnWriteArrayList<Long>> map = new ConcurrentHashMap<String, CopyOnWriteArrayList<Long>>();
		private static final long EXPIRE_TIME = 1000 * 10L;
		private static final long CLEAR_TIME = 1000 * 2L;
		private static final int MAX_REFRESH_COUNT = 30;

		private static final Cache cache = new Cache();

		private Cache() {
			new Thread(new ClearCacheRunnable()).start();
		}

		public static Cache getInstance() {
			return cache;
		}

		// 增长指定URL的点击次数
		public void increment(String key) {
			CopyOnWriteArrayList<Long> list = map.get(key);
			if (list == null) {
				map.put(key, new CopyOnWriteArrayList<Long>());
			}
			map.get(key).add(new Long(System.currentTimeMillis()));
		}

		// 是否到达指定数量
		public boolean isUpCount(String key) {
			CopyOnWriteArrayList<Long> list = map.get(key);
			if (list == null) {
				return false;
			}
			return list.size() > MAX_REFRESH_COUNT;
		}

		// 清理过期数据线程
		private static class ClearCacheRunnable implements Runnable {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(Cache.CLEAR_TIME);
						clear();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

			private void clear() {
				for (String key : map.keySet()) {
					CopyOnWriteArrayList<Long> list = map.get(key);
					for (Long date : list) {
						if (System.currentTimeMillis() - date > Cache.EXPIRE_TIME) {
							list.remove(date);
						}
					}
				}
			}
		}
	}
}