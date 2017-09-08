package cn.qhjys.mall.szpf;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.http.message.BasicNameValuePair;

public class SignUtils {
//	static String privateKeyStr = "MIIEpQIBAAKCAQEAlr8a2OGVU1Cpz10IwuCIXQQN8O7Y0oiyXhFub7yCywX+ZRqLCN206GuC2OVsaVu4OS+afeZYMLalYBzKIteMJIUKwJsJpwa4WZNDKTU9qHLSU4He/unduXBTlQTn7vfP/Vn920t43UpUKT8tT+AW6ehmRBtzO/2c3NNIQLbDgJQyr/c84yqeSd0cJ64lxRrxowKHuYK7W0FxQJQC60RyrYj9//ppGlNdw76mgiOnifN36r29BXvz5Tsrlc8AvMLG7uW0sOd6UzNmDKOuERhKevWHdo12c7bMehLRclUQjypVrOeREnvXOX5jA/qqJsVCr8wBdKNmV6fkiJD58pDScwIDAQABAoIBABs3NWmikIsSLRY6/bHxR61DZiDxfkPEWi+Rhha2Ogbddihaum9HV7n+bwkpooEDBieM6J+CTrm8v+6yQ+w2bh7Jxj8vvnZhSDcj1yRBcHhRApgBAnmTT2yDUxO8ZwNFyk87SBGndpK9MKShu1SIaJqiUpRJQGy8RO25V3oXhQRZdggnI4vcKANFTR/Zk4HG4B5aOrxI5WJjdKcaQn2e40SNqMoFM0gLYD/eMR27DKqZDOAlx3BtpBuvDq1DBt4x+WRAO+GjDhGBgf7v3wG/8u2Avj+1rIvOHjyL+6z/6hVVMcCcI67BhnL+JuzXQhtCBLj+H8hoO2uVF4YMy6lVEUECgYEA1DTVxiyRmjUFzMKbfXp1eb3SS8V74RI6zwlM+DeZFnKkK9f/m0JIkolAWISTCPR4R23ybtho/Rz7F9kAnrXP8EKMtBbwJW8atznxHXIUfSu46HFQgbaIROg37MKlqSGjYpSc9DAlbegPvVZuJDfyzN075I3QzU6LwLa6uSJZQjcCgYEAtdtBwGJ7gcHQ5bLRgMEpUmUNEso7yDZ8s6SFUdgpWbt6xjhLLRpdWfUeN+xWwW0HDrWCndYSsVgxUDm9gPvaRMv86mfLBubgAbk2gQILfWWouKDC1eWgjTlbqtpcVzeJ6XMJQ9pvkeV/ZTlFlD6zTfJZkqJzqWWHY3Lj4+eRg6UCgYEAzjt69toht0iIlnMbQltdCC/ezRzkQ9y/oq5bS3BdK1/HBYUqOO22MhOJoX1WmIFe06yay/cuMa+Up5yR6aywpcNHk04nnXnY8PEyz3crSmrPjbdykl2fGbjFgSe6cCDs9MWRtA+GkMXTZiM1b6WgJlFr/7eDkcQgNe5gkcQ8EwkCgYEAr15rzrlpvtyHtSo27AHZ9lHqRIdvFQiuQpJR2nYokhbAM6yPmkQFZ71/0ZAArXCVULLq7YhF6G1SyLmVrG+ysYtBL06+M76qoB7al5fAOsGw7bJhkOFqbSW151HBoH23ab98XMmls7zrmFKdbXL1eCJQDJ7KCL4WzGk7qEhkQ90CgYEAi6N4CK1FNSInLNfO35IkqL2a4J+lydwKdM0pPo1ZNTJFqicT5d+6QRmTg0RK+QEJnrr8A3yweFzVkHxuE/HAMB8uYLXTR1l5XhtbLE2PJkxjSENx49jU4SxYY0kOzCBDE2KLz382462y6uQ022J6W7pZlD/LGnwDIPaniBhHYG4=";
//	static String publicKeyStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlr8a2OGVU1Cpz10IwuCIXQQN8O7Y0oiyXhFub7yCywX+ZRqLCN206GuC2OVsaVu4OS+afeZYMLalYBzKIteMJIUKwJsJpwa4WZNDKTU9qHLSU4He/unduXBTlQTn7vfP/Vn920t43UpUKT8tT+AW6ehmRBtzO/2c3NNIQLbDgJQyr/c84yqeSd0cJ64lxRrxowKHuYK7W0FxQJQC60RyrYj9//ppGlNdw76mgiOnifN36r29BXvz5Tsrlc8AvMLG7uW0sOd6UzNmDKOuERhKevWHdo12c7bMehLRclUQjypVrOeREnvXOX5jA/qqJsVCr8wBdKNmV6fkiJD58pDScwIDAQAB";
   
	private static String  privateKeyStr = "MIIEpAIBAAKCAQEAyb0rlTY4nE/Hdn1LV6p58+7xJ8axHxOJo66I7hUjVO2nFJEL3KkKQog/q0uF/hWfMCWVvTPd4e7fyM5IeCMz9MgOBWPGYn1kfjtaMmbR+/ESFIzg+NCzJQqn9LHs0/mEu6tgCEToJ9P8VXvdgRlk5hc+bhQFE0DpAPn7OKamyrGQMhPoczN4ysX0CLn8Km5RZ0znNccfuKWoc0CWl9BxrLlBte9LhUHanGc/EmiKu/RVbXiX6OhDKCp8pQAex8iOW8LZONRy8uo9yxx84Tjfqo3yaGEOoR2JDg8i43TzNMwsFZIU6rsm9TdjnGcuMFfmV7VLuHTy98S4N19VeBbMqQIDAQABAoIBAARi8Zyn9ikn7u1bohTA5y/vpRy5TEahtR9y/xU2LnN9S2G+GaTtpAR4U/IUjVBOBg0U9CGODCgKeqEyIw9BrLKI2w0w8zJXCaVZAwgPhkdVIZCC0611VD/c8HP4nm6pgUJXYporbWTYTidJrzJMbA44uSnJk6ashaR4ywabUQsdMVpDnM8bPiDjWWS0O6Z+5RVpaonvYmkdqgPXe2P6B+5EcxCG65iL3HxtUCib4EjnB4ggiNBeJJVbad9D0AZGLx9RJ9/pBnLeZoH4ci2+W+C8QRc9hsd4CQALjvJ+x8htv7drobcJaAKlUBW3jRVXJdKaiNOFEFdqGJeyl4hj+JUCgYEA+JkTULFilehRezQPZ6vUzMbds2sLbgoxtQdCsEvLJ+5TJb/+LqC0B/StYvrw3MHw27a5PkeL4O4Lk7Dic/1qLSyQg8Q7dJxBCArB3ac9M6xOpxHGgvtqmNCupyY4geYtw4enQYkj3QCr0iHoRiUpnMZVo5UWelk2Pe2dG/mhcX8CgYEAz77qH59QZRtRY81eKl7iLAEs67CEqMntAsfiAyNYH3j8ani6qDd4QlhbcFDy8CCU6clrSPDGBrIgfFLY/Tvek+9fCDHJQS/iRVBw3yXEDHKi2eQOamm6wjwy2pWVI6k+/RgBQezMGObVc9S+t7zP8TmkBp+0ukj3CD98TLg3BdcCgYEAhtehHnfZHa7x434NjRIrTxffUV6SyJFG/QZ/KdkyzOn8t9E4MlqAzH/vPG0ZEOUxnDu0pw9v1AXKycV8T6X5XZ96k75w8YN/0rjjBzObgtfHod+Gaa2t2E35LjkRFYSg9hoyot2cUlf22vgBYWru81axRRWtJ5gUT+r++RmidEkCgYAkGUydihVyPDj8WqdOPYaXluyzV8z1d4ETxp/jY7TgYymZZrkomdrvzGK3NZnBWCgIJ9PYrN38M9+pOswx1MFse1/MELqj9SogWUYDwJA8iX761cIbg5vMPKGpkeyEcdhcKlutlPQyL4pPwlwRmWGYzSdPb1j8NcmXSVDUE/6bqQKBgQC8Npo367M11XUcHScbo7d+PERn2qSOx4btfCUYaTA+Dezbwt4wkxQcUAHp++ava6X8LnquDBsSmWVYCe7OEZKiHhO6TqQa/Mz2mRHSFft9kmcq0cZ+qlNznA73F8deWPmwjvEiksXcd2sEFi180z8ey52+riZr/lM41dk0dJY5kw==";
	private static String  publicKeyStr = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyb0rlTY4nE/Hdn1LV6p58+7xJ8axHxOJo66I7hUjVO2nFJEL3KkKQog/q0uF/hWfMCWVvTPd4e7fyM5IeCMz9MgOBWPGYn1kfjtaMmbR+/ESFIzg+NCzJQqn9LHs0/mEu6tgCEToJ9P8VXvdgRlk5hc+bhQFE0DpAPn7OKamyrGQMhPoczN4ysX0CLn8Km5RZ0znNccfuKWoc0CWl9BxrLlBte9LhUHanGc/EmiKu/RVbXiX6OhDKCp8pQAex8iOW8LZONRy8uo9yxx84Tjfqo3yaGEOoR2JDg8i43TzNMwsFZIU6rsm9TdjnGcuMFfmV7VLuHTy98S4N19VeBbMqQIDAQAB";
	
	public static String signData(List<BasicNameValuePair> nvps) throws Exception {
        TreeMap<String, String> tempMap = new TreeMap<String, String>();
        for (BasicNameValuePair pair : nvps) {
            if (StringUtils.isNotBlank(pair.getValue())) {
                tempMap.put(pair.getName(), pair.getValue());
            }
        }
        StringBuffer buf = new StringBuffer();
        for (String key : tempMap.keySet()) {
            buf.append(key).append("=").append((String) tempMap.get(key)).append("&");
        }
        String signatureStr = buf.substring(0, buf.length() - 1);
        /*KeyInfo keyInfo = RSAUtil.getPFXPrivateKey(ConfigUtils.getProperty("private_key_pfx_path"),
                                                   ConfigUtils.getProperty("private_key_pwd"));
        String signData = RSAUtil.signByPrivate(signatureStr, keyInfo.getPrivateKey(), "UTF-8");*/
        String signData = null;
        signData = RSAUtil.signByPrivate(signatureStr,privateKeyStr, "UTF-8");
        return signData;
    }

    public static boolean verferSignData(String str) {
        String data[] = str.split("&");
        StringBuffer buf = new StringBuffer();
        String signature = "";
        for (int i = 0; i < data.length; i++) {
            String tmp[] = data[i].split("=", 2);
            if ("signature".equals(tmp[0])) {
                signature = tmp[1];
            } else {
                buf.append(tmp[0]).append("=").append(tmp[1]).append("&");
            }
        }
        String signatureStr = buf.substring(0, buf.length() - 1);
        
        return RSAUtil.verifyByKeyPath(signatureStr, signature, publicKeyStr, "UTF-8");
    }
    
    
    public static boolean notifyVerify(String content, String sign) {
		return RSAUtil.verify(content, sign, publicKeyStr, "UTF-8");

    }
}
