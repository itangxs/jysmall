package cn.qhjys.mall.weixin.util;

import java.net.URLEncoder; 




/** 


 * 获取微信的code 


 * 获取当前微信opendId




 */ 


public class GetWeiXinCode { 


    public static String  GetCodeRequest = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect"; 

    public static String  Getopen ="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    public static String getCodeRequest(){ 


        String result = null; 


        GetCodeRequest  = GetCodeRequest.replace("APPID", urlEnodeUTF8("wxd903e7fc9e27de2b")); 

        
        GetCodeRequest  = GetCodeRequest.replace("REDIRECT_URI",urlEnodeUTF8("http://jysweixinwebone.ngrok.natapp.cn/TextWeiXieUrl2.do")); 


        GetCodeRequest = GetCodeRequest.replace("SCOPE","snsapi_base"); 


        result = GetCodeRequest; 

        return result; 

    }
    public static String getCodeRequest(String appid,String url){ 


        String result = null; 


        GetCodeRequest  = GetCodeRequest.replace("APPID", urlEnodeUTF8(appid)); 

        
        GetCodeRequest  = GetCodeRequest.replace("REDIRECT_URI",urlEnodeUTF8(url)); 


        //GetCodeRequest = GetCodeRequest.replace("SCOPE","snsapi_base"); 
        GetCodeRequest = GetCodeRequest.replace("SCOPE","snsapi_userinfo"); 
        
        result = GetCodeRequest; 

        return result; 

    }
    public static String getCodeRequestByBase(String appid,String url){ 
    
    	String  result = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri="+url+"&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect"; 	
    	return result; 
    	
    }
    public static String getCodeRequestByUserInfo(String appid,String url){ 
    	
    	String  result = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri="+url+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect"; 	
    	return result; 
    	
    }
    public static String getCodeRequestByScopeInfo(String appid,String url){ 
    	
    	String  result = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri="+url+"&state=STATE#wechat_redirect"; 	
    	return result; 
    	
    }

    public static String getCurrentOpenId(String code){ 

        String result = null; 


        Getopen  = Getopen.replace("APPID", urlEnodeUTF8("wxd903e7fc9e27de2b")); 

        Getopen  = Getopen.replace("SECRET",urlEnodeUTF8("3a61f67c2c13cb4e91aca246e24557ab")); 
        Getopen  = Getopen.replace("CODE",urlEnodeUTF8(code)); 
        result = Getopen; 
        return result; 

    } 
    public static String getCurrentOpenId(String code,String appid,String SECRET){ 
        String  url ="https://api.weixin.qq.com/sns/oauth2/access_token?appid="+appid+"&secret="+SECRET+"&code="+code+"&grant_type=authorization_code";

        return url; 

    } 
    
    public static String getCurrentUserInfo(String access_token,String openid){ 
    	String  url ="https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN";
   
    	return url; 
    	
    } 


    public static String urlEnodeUTF8(String str){ 


        String result = str; 


        try { 


            result = URLEncoder.encode(str,"UTF-8"); 


        } catch (Exception e) { 


            e.printStackTrace(); 


        } 


        return result; 


    } 

} 