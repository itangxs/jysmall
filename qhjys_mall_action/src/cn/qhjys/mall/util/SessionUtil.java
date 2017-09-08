package cn.qhjys.mall.util;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

public class SessionUtil {
	@Resource
	private static MemcachedClientTemplate memcachedClientTemplate;

	public MemcachedClientTemplate getMemcachedClientTemplate() {
		return memcachedClientTemplate;
	}

	public void setMemcachedClientTemplate(MemcachedClientTemplate memcachedClientTemplate) {
		SessionUtil.memcachedClientTemplate = memcachedClientTemplate;
	}

	public static boolean setSession(HttpSession session, String name, Object ob) throws Exception {
		session.setAttribute(name, ob);
		return true;
	}

	public static boolean removeSession(HttpSession session, String name) throws Exception {
		session.removeAttribute(name);
		return true;
	}

	public static Object getSession(HttpSession session, String name) throws Exception {
		return session.getAttribute(name);
	}

	public static List<String> getSessionAttributeNames(HttpSession session) throws Exception {
		Enumeration<String> e = session.getAttributeNames();
		List<String> list = new ArrayList<String>();
		while (e.hasMoreElements()) {
			list.add(e.nextElement().toString());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public static boolean setSessionByCache(String sessionId, String name, Object ob) throws Exception {
		Object obj = memcachedClientTemplate.getObjectByKey(sessionId, null);
		Map<String, Object> con = new HashMap<String, Object>();
		if (null != obj && obj instanceof HashMap) {
			con = (Map<String, Object>) obj;
			con.put(name, ob);
			memcachedClientTemplate.replaceKey(sessionId, con, null);
		} else {
			con.put(name, ob);
			memcachedClientTemplate.addKey(sessionId, con, null);
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public static boolean removeSessionByCahche(String sessionId, String name) throws Exception {
		Object obj = memcachedClientTemplate.getObjectByKey(sessionId, null);
		Map<String, Object> con = new HashMap<String, Object>();
		if (null != obj && obj instanceof HashMap) {
			con = (Map<String, Object>) obj;
			con.put(name, null);
			memcachedClientTemplate.replaceKey(sessionId, con, null);
		} else {
			con.put(name, null);
			memcachedClientTemplate.addKey(sessionId, con, null);
		}
		return true;
	}

	public static Object getSessionByCache(String sessionId, String name) throws Exception {
		Object obj = memcachedClientTemplate.getObjectByKey(sessionId, null);
		if (null != obj && obj instanceof HashMap) {
			Map<?, ?> con = (Map<?, ?>) obj;
			return con.get(name);
		} else {
			return null;
		}
	}

	public static List<String> getSessionAttributeNamesByCache(String sessionId) throws Exception {
		Object obj = memcachedClientTemplate.getObjectByKey(sessionId, null);
		if (null != obj && obj instanceof HashMap) {
			@SuppressWarnings("unchecked")
			Map<String, Object> con = (Map<String, Object>) obj;
			Set<String> set = con.keySet();
			return new ArrayList<String>(set);
		} else {
			return null;
		}
	}
}
