package cn.qhjys.mall.weixin.qqpay;

import java.util.TreeMap;

/**
 * 仅供示例
 * 不能调用此类
 * @author Administrator
 *
 */
public class Demo {
	public static final String DEMO_MCH_ID = "1395796901";

	public static final String DEMO_OUT_TRADE_NO = "20160512161914_BBC";

	public void qpay_unified_order() throws Exception {
		CQpayMchSpBase obj = new CQpayMchSpBase();
		TreeMap<String, String> map = new TreeMap<>();
		map.put("mch_id", DEMO_MCH_ID);
		map.put("api_key", "");
		map.put("nonce_str", "b49b24740506ac9b5e36aadccd8237fe");
		map.put("out_trade_no", DEMO_OUT_TRADE_NO);
		map.put("sub_mch_id", "1900005911");
		map.put("body", "body_test_中文");
		map.put("device_info", "WP00000001");
		map.put("fee_type", "CNY");
		map.put("sub_mch_id", "1900005911");
		map.put("notify_url", "http://10.222.146.71:80/success.xml");
		map.put("spbill_create_ip", "127.0.0.1");
		map.put("total_fee", "1");
		map.put("trade_type", "NATIVE");

		obj.setRequestMapAndUrl(map, CQpayAPIURL.getUnifiedOrderUrl());
		System.out.println("=========================qpay_unified_order=======");
		System.out.println(obj.call());
	}
	

	public void qpay_refund() throws Exception {
		CQpayMchSpBase obj = new CQpayMchSpBase();
		TreeMap<String, String> map = new TreeMap<>();
		map.put("mch_id", DEMO_MCH_ID);
		map.put("api_key", "");
		map.put("nonce_str", "b49b24740506ac9b5e36aadccd8237fe");
		map.put("out_trade_no", DEMO_OUT_TRADE_NO);
		map.put("sub_mch_id", "1900005911");
		map.put("out_refund_no", DEMO_OUT_TRADE_NO + "_out_refund_1");
		map.put("refund_fee", "99999");
		map.put("op_user_id", "1900005911");
		map.put("op_user_passwd", "");
		
		obj.setNeedClientPem(true);
		/*
		 * 设置证书
		 * 传文件
		 */
		//obj.setCertInfo(certFile, certPasswd, caFile);
		obj.setRequestMapAndUrl(map, CQpayAPIURL.getRefundUrl());
		
		System.out.println("=========================qpay_refund=======");
		System.out.println(obj.call());
	}

}
