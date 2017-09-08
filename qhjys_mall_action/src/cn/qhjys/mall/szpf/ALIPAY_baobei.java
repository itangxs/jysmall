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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * 类ALIPAY_baobei.java的实现描述：TODO 类实现描述 
 * @author admin 2017年6月22日 上午11:04:01
 */
public class ALIPAY_baobei {

    public static void main(String[] args) throws Exception {
        DefaultHttpClient httpClient = new SSLClient();
        HttpPost postMethod = new HttpPost("http://spdbweb.chinacardpos.com/payment-gate-web/gateway/api/backTransReq");
        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
        nvps.add(new BasicNameValuePair("requestNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()))); //必填
        nvps.add(new BasicNameValuePair("version", "V1.1"));//固定 必填
        nvps.add(new BasicNameValuePair("transId", "18"));//固定 必填
        nvps.add(new BasicNameValuePair("subMechantName", "深圳市前海金钥匙科技有限公司"));//商户名称 必填
        
        /********************************支付宝必须有的字段 开始 其他字段微信，支付宝都要传************************************/
        /***联系人信息**/
        Map<String,String> contact = new HashMap<>();
        contact.put("name", "吴嘉");//姓名  必填
//        contact.put("phone", "0734-5820311");//电话 选填
//        contact.put("mobile", "13012345678");//手机 选填
//        contact.put("email", "13012345678@qq.com");//邮件 选填
        contact.put("type", "AGENT");//联系人类型 取值范围：LEGAL_PERSON：法人；CONTROLLER：实际控制人；AGENT：代理人；OTHER：其他   必填
        contact.put("id_card_no", "440301198209256674");//身份证号 必填
        /**地址信息*/
        Map<String,String> address = new HashMap<>();
        address.put("province_code", "440000");//省级编码 必填
        address.put("city_code", "440300");//市级编码 必填
        address.put("district_code", "440304");//区县编码 必填
        address.put("address", "深圳市福田区滨河大道5003号爱地大厦西座7D-F");//详细地址 必填

        /**结算信息*/
//        Map<String,String> cardInfo = new HashMap<>();
//        cardInfo.put("card_no", "6214836559081829");//卡号 必填
//        cardInfo.put("card_name", "张三");//户名 必填
        
        nvps.add(new BasicNameValuePair("contactInfo", JSONObject.toJSONString(contact))); //必填
        nvps.add(new BasicNameValuePair("addressInfo", JSONObject.toJSONString(address))); //选填
//        nvps.add(new BasicNameValuePair("bankCardInfo", JSONObject.toJSONString(cardInfo)));//选填
//        nvps.add(new BasicNameValuePair("businessLicenseType", "NATIONAL_LEGAL"));//商户证件类型  取值：NATIONAL_LEGAL营业执照，NATIONAL_LEGAL_MERGE营业执照多证合一，INST_GRST_CTF事业单位法人证书 选填
//        nvps.add(new BasicNameValuePair("businessLicense", "91440300MA5DGR1111"));//商户证件编码 企业或者个体工商户提供营业执照，事业单位提供事证号 选填
        
         /********************************支付宝必须有的字段 结束  其他字段微信，支付宝都要传************************************/
        
        nvps.add(new BasicNameValuePair("contactEmail", "109057573@qq.com"));//邮件 必填
        nvps.add(new BasicNameValuePair("contactPhone", "13480141154"));//手机号 必填
//        nvps.add(new BasicNameValuePair("business", "2016062900190124"));//类别编码 必填
//        nvps.add(new BasicNameValuePair("business", "2015050700000000"));//类别编码 必填
        nvps.add(new BasicNameValuePair("business", "2015063000020189"));//类别编码 必填
        nvps.add(new BasicNameValuePair("contact", "吴嘉"));//联系人 必填
        nvps.add(new BasicNameValuePair("subMerchantShortname", "浦发银行特约商户"));//商户简称 必填
        nvps.add(new BasicNameValuePair("merchantRemark", "前海金钥匙科技"));//备注 必填
        nvps.add(new BasicNameValuePair("servicePhone", "13480141154"));//联系电话 必填
        nvps.add(new BasicNameValuePair("payWay", "ALIPAY"));//类型 WX 或 ALIPAY 必填
        nvps.add(new BasicNameValuePair("merNo", "310440300004895"));//商户号 必填
        
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
