package cn.qhjys.mall.common;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext application = this.getServletContext();
		String os = System.getProperty("os.name").toLowerCase();
		if (os.indexOf("windows") != -1) {
			application.setAttribute("logonurl", "https://localhost:8081");
			application.setAttribute("webPath", application.getContextPath());
			application.setAttribute("cssVersion", "0.001");
			application.setAttribute("jsVersion", "0.001");
			application.setAttribute("staticrealurl", "http://localhost8081/real/");
			application.setAttribute("statictempurl", "http://localhost:8081/temp/");
			application.setAttribute("static", "http://localhost:8081");
			application.setAttribute("p2purl", "http://localhost:8081");
			application.setAttribute("userregCount", 0);
		} else {
			application.setAttribute("logonurl", "https://www.qhjys.cn");
			application.setAttribute("webPath", application.getContextPath());
			application.setAttribute("cssVersion", "0.001");
			application.setAttribute("jsVersion", "0.001");
			application.setAttribute("staticrealurl", "http://www.jysp2p.com/real/");
			application.setAttribute("statictempurl", "http://www.jysp2p.com/temp/");
			application.setAttribute("static", "http://www.jysp2p.com");
			application.setAttribute("p2purl", "http://www.qhjys.cn");
			application.setAttribute("userregCount", 0);
		}
	}
}
