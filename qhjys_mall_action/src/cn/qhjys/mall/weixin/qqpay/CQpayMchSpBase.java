package cn.qhjys.mall.weixin.qqpay;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.TreeMap;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import cn.qhjys.mall.weixin.util.HttpClientUtil;


public  class CQpayMchSpBase{
	
	private static Logger logger = LoggerFactory.getLogger(CQpayMchSpBase.class);
	
	private static final String JKS_CA_ALIAS = "tenpay";
	
	private static final String JKS_CA_FILENAME = "tenpay_cacert.jks";
	
	/** 请求参数  */
	private TreeMap<String, String> map = new TreeMap<>();
	
	/** 请求地址 */
	private String requestUrl;
	
	/** ca证书文件 */
	private File caFile;

	/** 证书文件 */
	private File certFile;

	/** 证书密码 */
	private String certPasswd;

	/** 是否需要证书 */
	private boolean needClientPem = false;
	
	
	/**
	 * 设置证书信息
	 * 
	 * @param certFile
	 *            证书文件
	 * @param certPasswd
	 *            证书密码
	 *  @param caFile         
	 *           设置ca
	 */
	public void setCertInfo(File certFile, String certPasswd,File caFile) {
		this.certFile = certFile;
		this.certPasswd = certPasswd;
		this.caFile = caFile;
	}

	/**
	 * 设置是否需要证书
	 * @param needClientPem
	 */
	public void setNeedClientPem(boolean needClientPem) {
		this.needClientPem = needClientPem;
	}

	/**
	 * 
	 * @param map
	 * @param requestUrl
	 */
	public void setRequestMapAndUrl(TreeMap<String, String> map,String requestUrl) {
		this.map = map;
		this.requestUrl = requestUrl;
	}

	public boolean checkSign(TreeMap<String, String> responseMap)
			throws NoSuchAlgorithmException {
		String responseSign = responseMap.get("sign");
		String calSign = generateSign(responseMap,map.get("api_key"));
		return responseSign.equalsIgnoreCase(calSign);
	}

	public TreeMap<String, String> call() throws Exception {
		URL url = new URL(requestUrl);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("charset", "UTF-8");

		String sPostXml = this.getPostXml();
		conn.setDoOutput(true);
		// 不使用缓存
		conn.setUseCaches(false);

		// 允许输入输出
		conn.setDoInput(true);
		conn.setDoOutput(true);
		// 是否需要双向证书。
		if (needClientPem) {
			if (null == this.caFile || null == this.certFile
					|| null == this.certPasswd) {
				throw new Exception("NEED CLIENT PEM");
			}

			// ca目录
			String caPath = this.caFile.getParent();
			File jksCAFile = new File(caPath + "/" + JKS_CA_FILENAME);
			if (!jksCAFile.isFile()) {
				X509Certificate cert = (X509Certificate) HttpClientUtil.getCertificate(this.caFile);
				FileOutputStream out = new FileOutputStream(jksCAFile);
				// store jks file
				HttpClientUtil.storeCACert(cert, JKS_CA_ALIAS, "",out);
				out.close();
			}

			FileInputStream trustStream = new FileInputStream(jksCAFile);
			FileInputStream keyStream = new FileInputStream(this.certFile);

			SSLContext sslContext = HttpClientUtil.getSSLContext(trustStream,
					"", keyStream, this.certPasswd);

			// 关闭流
			keyStream.close();
			trustStream.close();

			SSLSocketFactory sf = sslContext.getSocketFactory();
			conn.setSSLSocketFactory(sf);
		}

		// 发送请求
		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.write(sPostXml.getBytes(StandardCharsets.UTF_8));
		wr.flush();
		wr.close();

		int responseCode = conn.getResponseCode();
		if (200 != responseCode) {
			throw new Exception("CALL HTTP ERROR " + responseCode);
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(
				conn.getInputStream(),StandardCharsets.UTF_8));
		String tmpInputLine;
		StringBuffer responseBuffer = new StringBuffer();
		while ((tmpInputLine = in.readLine()) != null) {
			responseBuffer.append(tmpInputLine);
		}
		in.close();
		TreeMap<String, String> responseMap = new TreeMap<String, String>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new ByteArrayInputStream(responseBuffer.toString().getBytes(StandardCharsets.UTF_8)));
		NodeList nodeList = doc.getChildNodes().item(0).getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node n = nodeList.item(i);
			responseMap.put(n.getNodeName(), n.getTextContent());
		}
		logger.debug("QQ 支付预下单返回 结果:"+responseBuffer.toString());
		return responseMap;
		
	}

	private String getPostXml() throws NoSuchAlgorithmException {
		map.put("sign",generateSign(map, map.get("api_key")));

		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Iterator<String> ite = map.keySet().iterator();
		while (ite.hasNext()) {
			String tmpKey = ite.next();
			if (map.get(tmpKey).isEmpty()) {
				continue;
			}
			sb.append("<" + tmpKey + ">");
			sb.append(map.get(tmpKey));
			sb.append("</" + tmpKey + ">");

		}
		sb.append("</xml>");
		return sb.toString();
	}

	/**
	 * 生成商户签名
	 * 
	 * @throws NoSuchAlgorithmException
	 */
	private static String generateSign(TreeMap<String, String> srcMap,
			String key) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		byte[] inputByteArray = (generateSignSrc(srcMap) + key)
				.getBytes(StandardCharsets.UTF_8);
		messageDigest.update(inputByteArray);
		byte[] resultByteArray = messageDigest.digest();
		return byteArrayToHex(resultByteArray);
	}

	private static String byteArrayToHex(byte[] byteArray) {

		// 首先初始化一个字符数组，用来存放每个16进制字符
		char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };

		// new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
		char[] resultCharArray = new char[byteArray.length * 2];

		// 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
		int index = 0;
		for (byte b : byteArray) {
			resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
			resultCharArray[index++] = hexDigits[b & 0xf];
		}

		// 字符数组组合成字符串返回
		return new String(resultCharArray);
	}

	/**
	 * 生成签名原串。
	 * 
	 * @return
	 */
	private static String generateSignSrc(TreeMap<String, String> srcMap) {
		StringBuffer sb = new StringBuffer();
		Iterator<java.util.Map.Entry<String, String>> ite = srcMap.entrySet().iterator();
		while (ite.hasNext()) {
			java.util.Map.Entry<String,String> tmpEntry = ite.next();
			if ("sign".equalsIgnoreCase(tmpEntry.getKey()) //
					|| tmpEntry.getValue().isEmpty()) {
				continue;
			}
			sb.append(tmpEntry.getKey()).append("=").append(tmpEntry.getValue())
					.append("&");
		}
		sb.append("key=");
		return sb.toString();
	}
}
