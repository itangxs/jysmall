package cn.qhjys.mall.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.SortedMap;
import java.util.TreeMap;

import cn.qhjys.mall.weixin.util.XMLUtil;

public class Bdcx {
	public static void main(String[] args) {
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		String a = "{out_transaction_id=4004782001201611170000547529, status=0, is_subscribe=N, sign_type=MD5, sub_openid=ob8WbwclE19m9ZqSaC6ZTJn-maMk, charset=UTF-8, fee_type=CNY, nonce_str=1479365089902, out_trade_no=WF2016111714444300389318, transaction_id=101510001886201611171198426709, trade_type=pay.weixin.jspay, version=2.0, result_code=0, sign=EA7FD720CF8655109EC75F75DCA5A99E, mch_id=101510001886, sub_appid=wx6e0532e3ad8d3b82, sub_is_subscribe=N, total_fee=11600, pay_result=0, time_end=20161117144449, openid=oMDLljrUQYU9xSTD5R_w_OINrk8k, bank_type=COMM_DEBIT}";
		a = a.replace("{", "").replace("}", "");
		
		String [] b = a.split(",");
		for (int i = 0; i < b.length; i++) {
			String [] d = b[i].trim().split("=");
			packageParams.put(d[0], d[1]);
		}
		String requestXML = XMLUtil.getRequestXml(packageParams);
		System.out.println(requestXML);
		testPost("http://www.jysmall.com/wechatPay/checkNotifyWft.do", requestXML);
	}
	static void testPost(String urlStr,String xmlInfo) {  
        try {  
            URL url = new URL(urlStr);  
            URLConnection con = url.openConnection();  
            con.setDoOutput(true);  
            con.setRequestProperty("Pragma:", "no-cache");  
            con.setRequestProperty("Cache-Control", "no-cache");  
            con.setRequestProperty("Content-Type", "text/xml");  
  
            OutputStreamWriter out = new OutputStreamWriter(con  
                    .getOutputStream());      
             
            out.write(new String(xmlInfo.getBytes("UTF-8")));  
            out.flush();  
            out.close();  
            BufferedReader br = new BufferedReader(new InputStreamReader(con  
                    .getInputStream()));  
            String line = "";  
            for (line = br.readLine(); line != null; line = br.readLine()) {  
                System.out.println(line);  
            }  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}
