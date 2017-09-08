package cn.qhjys.mall.util;

import java.security.MessageDigest;

public class SHA256Util {
	public static String sha256(String date) throws Exception{
		MessageDigest  digest = MessageDigest.getInstance("SHA-256");
		 byte[] hash = digest.digest(date.getBytes("UTF-8"));
		 return  bytes2Hex(hash);
	}
	
	public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }
}
