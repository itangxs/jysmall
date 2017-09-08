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
 * 商户报备
 * 报备是讲商户信息上报给微信和支付宝上游，上游将返回一个二级编号给商户，可以理解为微信支付宝分配商户号给下游的过程
 * 15680581617正式
 */
public class WX_Baobei {
    public static void main(String[] args) throws Exception {
        DefaultHttpClient httpClient = new SSLClient();
        HttpPost postMethod = new HttpPost("http://spdbweb.chinacardpos.com/payment-gate-web/gateway/api/backTransReq");
        List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
        nvps.add(new BasicNameValuePair("requestNo", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())));
        nvps.add(new BasicNameValuePair("version", "V1.1"));
        nvps.add(new BasicNameValuePair("transId", "18"));
        nvps.add(new BasicNameValuePair("subMechantName", "深圳市前海金钥匙科技有限公司"));
        nvps.add(new BasicNameValuePair("business", "208"));
        nvps.add(new BasicNameValuePair("subMerchantShortname", "浦发银行特约商户"));
        nvps.add(new BasicNameValuePair("contact", "吴嘉"));
        nvps.add(new BasicNameValuePair("contactPhone", "13480141154"));
        nvps.add(new BasicNameValuePair("contactEmail", "109057573@qq.com"));
        nvps.add(new BasicNameValuePair("merchantRemark", "前海金钥匙科技"));
        nvps.add(new BasicNameValuePair("servicePhone", "13480141154"));
        nvps.add(new BasicNameValuePair("payWay", "WX"));
        nvps.add(new BasicNameValuePair("merNo","310440300004895"));
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
