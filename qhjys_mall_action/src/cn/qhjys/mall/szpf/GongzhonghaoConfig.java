/*
 * Copyright 1999-2024 Colotnet.com All right reserved. This software is the
 * confidential and proprietary information of Colotnet.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Colotnet.com.
 */
package cn.qhjys.mall.szpf;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


/**
 * 类GongzhonghaoQuery.java的实现描述：TODO 类实现描述 
 * @author admin 2017年6月19日 下午2:16:06
 */

public class GongzhonghaoConfig {
    public static void main(String[] args) throws Exception {
        DefaultHttpClient httpClient = new SSLClient();
        HttpPost postMethod = new HttpPost("http://spdbweb.chinacardpos.com/payment-gate-web/gateway/api/backTransReq");
        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
        nvps.add(new BasicNameValuePair("requestNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())));
        nvps.add(new BasicNameValuePair("version", "V1.0"));
        nvps.add(new BasicNameValuePair("transId", "28"));
        
        nvps.add(new BasicNameValuePair("subMchId", "38814689"));//33448114
        nvps.add(new BasicNameValuePair("business", "53"));
        
        //以下3个参数  配置一次调用一次接口，一次只能选一项配置，且配置不可修改，如果需要修改，请联系运营
//        nvps.add(new BasicNameValuePair("subAppId", "wx6e0532e3ad8d3b82"));
//      nvps.add(new BasicNameValuePair("subScribeAppid", "wx6e0532e3ad8d3b82"));
        nvps.add(new BasicNameValuePair("jsApiPath", "https://www.jysmall.com/wxMall/"));
        
        nvps.add(new BasicNameValuePair("payWay", "WX"));
        nvps.add(new BasicNameValuePair("merNo", "310440300004895"));
        nvps.add(new BasicNameValuePair("signature", SignUtils.signData(nvps)));
        try { 
            postMethod.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            HttpResponse resp = httpClient.execute(postMethod);
            String str = EntityUtils.toString(resp.getEntity(), "UTF-8");
            int statusCode = resp.getStatusLine().getStatusCode();
            if (200 == statusCode) {
                System.out.println(str);
                boolean signFlag = SignUtils.verferSignData(str);
                if (!signFlag) {
                    System.out.println("验签失败");
                    return;
                }
                System.out.println("验签成功");
                return;
            }
            System.out.println("返回错误码:" + statusCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
