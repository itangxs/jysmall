package cn.qhjys.mall.util;

import java.util.Random;
import java.util.UUID;

public class MyUUID {

    /**
     * 根据生成的UUID进行sha1 base64
     * 随机截取12位
     * 
     * @param args
     */
	public static String getMyUUID(){
		   UUID uuid = UUID.randomUUID();
           String guid = uuid.toString().replace("-", "").toUpperCase();
           Random random = new Random(System. currentTimeMillis());
           int k = random.nextInt();
           int start = Math.abs(k % 32);
           Sha1 sha1 = new Sha1();
          //guid = EncodeMD5.GetMD5Code(guid);
           guid = sha1.getDigestOfString(guid.getBytes());
           guid = Base64Util.encode(guid);
           guid = guid.substring(start, start + 12);
           guid = guid.toUpperCase();
           return guid;
	}
    public static void main(String[] args) {
		String code = MyUUID.getMyUUID();
		code = code.substring(0, 4)+"-"+code.substring(4, 8)+"-"+code.substring(8, 12);;
		
		System.out.println(code);
	}
    
    
}