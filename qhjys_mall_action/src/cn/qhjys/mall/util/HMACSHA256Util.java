package cn.qhjys.mall.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class HMACSHA256Util {
	
	public static String HMACSHA256(String data,String key){
		try {
				Mac	mac = Mac.getInstance("HmacSHA256");
			 byte[] secretByte = key.getBytes("UTF-8");
		        byte[] dataBytes = data.getBytes("UTF-8");
		        SecretKey secret = new SecretKeySpec(secretByte, "HMACSHA256");

		        mac.init(secret);
		        byte[] doFinal = mac.doFinal(dataBytes);
		        byte[] hexB = new Hex().encode(doFinal);
		        return new String(hexB);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
       
	}
}
