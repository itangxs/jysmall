package cn.qhjys.mall.common;

public class FILEPATH {
	
	static String os = System.getProperty("os.name").toLowerCase();
	
	public static String getDocumentPath(){
		if (os.indexOf("windows") != -1) {
			return "D://data//index";
		} else {
			return "/data/webapp/jysmall/WEB-INF/document/index";
		}
	}

}
