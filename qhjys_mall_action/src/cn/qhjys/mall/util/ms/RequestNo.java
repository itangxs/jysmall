package cn.qhjys.mall.util.ms;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 
 * 标题：请求流水号
 * 作者：huangjr
 * 描述：TODO
 * 版本：V1.0 
 * 创建时间：2017年7月17日 上午10:13:26
 * 修改时间：
 *
 */
public class RequestNo {
	
	public static String request_no = getRequestNo();
	
	/**
     * 获取商户业务流水号32位（yyyyMMddhhmmssSSS + 15位随机数）
     * @return
     */
    public static String getRequestNo(){
    	return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+random(15);
    }
    
	/** 
     * 返回一个指定位数随机字符串(只包含大小写字母、数字) 
     *  
     * @return 随机字符串 
     */  
    public static String random(int j) {  
		String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";  
        StringBuffer sb = new StringBuffer();  
        Random random = new Random();  
        for (int i = 0; i < j; i++) {  
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));  
        }  
        return sb.toString();  
    }
    
    public static void main(String[] args) {
		System.out.println(RequestNo.request_no);
	}
}

