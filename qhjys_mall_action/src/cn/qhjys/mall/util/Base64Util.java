package cn.qhjys.mall.util;

import java.io.IOException;

import sun.misc.BASE64Decoder;

import com.qq.connect.utils.http.BASE64Encoder;

public class Base64Util {
	/**  
	    * 编码  
	    * @param bstr  
	    * @return String  
	    */    
	   public static String encode(String bstr){    
	   	return BASE64Encoder.encode(bstr.getBytes());    
	   }    
	   
	   /**  
	    * 解码  
	    * @param str  
	    * @return string  
	    */    
	   public static String decode(String str){    
	   byte[] bt = null;    
	   try {    
	       BASE64Decoder decoder = new sun.misc.BASE64Decoder();    
	       bt = decoder.decodeBuffer( str );    
	   } catch (IOException e) {    
	       e.printStackTrace();    
	   }   
	       return new String(bt);    
	   }    
}
