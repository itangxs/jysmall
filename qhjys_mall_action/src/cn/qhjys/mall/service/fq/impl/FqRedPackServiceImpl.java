package cn.qhjys.mall.service.fq.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.security.KeyStore;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import javax.net.ssl.SSLContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qhjys.mall.entity.FqOrder;
import cn.qhjys.mall.entity.FqRedpack;
import cn.qhjys.mall.entity.FqRedpackDetail;
import cn.qhjys.mall.entity.FqRedpackDetailExample;
import cn.qhjys.mall.entity.FqRedpackExample;
import cn.qhjys.mall.entity.FqRedpackRecord;
import cn.qhjys.mall.entity.FqRedpackRecordExample;
import cn.qhjys.mall.entity.FqRedpackTime;
import cn.qhjys.mall.entity.FqRedpackTimeExample;
import cn.qhjys.mall.entity.FqStore;
import cn.qhjys.mall.entity.FqStoreExample;
import cn.qhjys.mall.entity.FqUserInfo;
import cn.qhjys.mall.entity.RebateOrder;
import cn.qhjys.mall.entity.RebateOrderExample;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.StoreInfoExample;
import cn.qhjys.mall.entity.StoreRebate;
import cn.qhjys.mall.entity.WeixinUserinfo;
import cn.qhjys.mall.entity.WeixinUserinfoExample;
import cn.qhjys.mall.mapper.FqOrderMapper;
import cn.qhjys.mall.mapper.FqRedpackDetailMapper;
import cn.qhjys.mall.mapper.FqRedpackMapper;
import cn.qhjys.mall.mapper.FqRedpackRecordMapper;
import cn.qhjys.mall.mapper.FqRedpackTimeMapper;
import cn.qhjys.mall.mapper.FqStoreMapper;
import cn.qhjys.mall.mapper.FqUserInfoMapper;
import cn.qhjys.mall.mapper.RebateOrderMapper;
import cn.qhjys.mall.mapper.StoreInfoMapper;
import cn.qhjys.mall.mapper.StoreRebateMapper;
import cn.qhjys.mall.mapper.WeixinUserinfoMapper;
import cn.qhjys.mall.service.RedisService;
import cn.qhjys.mall.service.fq.FqRedpackService;
import cn.qhjys.mall.util.BaseUtil;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.weixin.util.MD5Util;
import cn.qhjys.mall.weixin.util.SystemConstant;
import cn.qhjys.mall.weixin.util.XMLUtil;

@Service("fqRedPackService")
public class FqRedPackServiceImpl implements FqRedpackService {
	private final Log logger = LogFactory.getLog(FqRedPackServiceImpl.class);
	@Autowired
	private FqRedpackRecordMapper fqRedpackRecordMapper;
	@Autowired
	private WeixinUserinfoMapper weixinUserinfoMapper;
	@Autowired
	private RebateOrderMapper orderMapper;
	@Autowired
	private StoreRebateMapper storeRebateMapper;
	@Autowired
	private StoreInfoMapper storeInfoMapper;
	@Autowired
	private RedisService redisService;
	@Autowired
	private FqStoreMapper fqStoreMapper;
	@Autowired
	private FqRedpackTimeMapper fqRedpackTimeMapper;
	@Autowired
	private FqRedpackMapper fqRedpackMapper;
	@Autowired
	private FqRedpackDetailMapper fqRedpackDetailMapper;
	@Autowired
	private FqUserInfoMapper fqUserInfoMapper;
	@Autowired
	private FqOrderMapper fqOrderMapper;
	
	@Override
	public FqRedpack getRedpackByStore(Long storeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FqRedpackRecord getredpackRecord(Long id) {
		return fqRedpackRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public synchronized int  updateRedpackRecordStatus(Long id,String path) throws Exception {
		FqRedpackRecord record = fqRedpackRecordMapper.selectByPrimaryKey(id);
		logger.info("--id-"+id);
		logger.info("---record.getStatus()-"+record.getStatus());
		if (record.getStatus().equals(1)){
			return 0;
		}
		String JSAPI_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
		String appId = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_APPID);
//		String appSecret =ConstantsConfigurer.getProperty(SystemConstant.WECHAT_APPSECRET);
		String partner = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_PAY_PARTNER);
		String pkey = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_PAY_PKEY);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		  SortedMap<String, String> packageParams = new TreeMap<String, String>();
		  packageParams.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
		  packageParams.put("mch_billno", partner+formatter.format(new Date())+BaseUtil.numRandom(10));
		  packageParams.put("mch_id", partner);
		  packageParams.put("wxappid", appId);
		  packageParams.put("send_name", "飞券生活");
		  packageParams.put("re_openid", record.getOpenId());
		  packageParams.put("total_amount", String.valueOf(record.getRedpackMoney().multiply(new BigDecimal(100)).intValue()));
		  packageParams.put("total_num", "1");
		  packageParams.put("wishing", "支付有礼,红包不停!");
		  packageParams.put("client_ip", "192.168.1.1");
		  packageParams.put("act_name", "支付有礼");
		  packageParams.put("remark", "支付越多抢的越多!");
		  String sign = createSign(packageParams, pkey);
		  packageParams.put("sign", sign);
		  String requestXML = XMLUtil.getRequestXml(packageParams);
	      String result = null;
		 
		  KeyStore keyStore  = KeyStore.getInstance("PKCS12");
	        FileInputStream instream = new FileInputStream(new File(path+"/cert/apiclient_cert.p12"));
	        try {
	            keyStore.load(instream, partner.toCharArray());
	        } finally {
	            instream.close();
	        }

	        // Trust own CA and all self-signed certs
	        SSLContext sslcontext = SSLContexts.custom()
	                .loadKeyMaterial(keyStore, partner.toCharArray())
	                .build();
	        // Allow TLSv1 protocol only
	        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
	                sslcontext,
	                new String[] { "TLSv1" },
	                null,
	                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
	        CloseableHttpClient httpclient = HttpClients.custom()
	                .setSSLSocketFactory(sslsf)
	                .build();
	        try {

//	            HttpGet httpget = new HttpGet("https://api.mch.weixin.qq.com/secapi/pay/refund");
	            HttpPost httppost = new HttpPost(JSAPI_URL);
//	            System.out.println("executing request" + httpget.getRequestLine());
	            
	            StringEntity stringentity = new StringEntity(requestXML,"UTF-8");
	            httppost.setEntity(stringentity);
	            CloseableHttpResponse response = httpclient.execute(httppost);
	            try {
	                HttpEntity entity = response.getEntity();

	                if (entity != null) {
	                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
	                    StringBuffer sb = new StringBuffer();
	                    String text;
	                    while ((text = bufferedReader.readLine()) != null) {
	                    	sb.append(text);
	                    }
	                   result = sb.toString();
	                }
	                EntityUtils.consume(entity);
	            } finally {
	                response.close();
	            }
	        } finally {
	            httpclient.close();
	        }
	        logger.info("redpack---result---"+result);
	        Map<String, String>notifyParamsMap =XMLUtil.doXMLParse(result);
	        if (notifyParamsMap.get("return_code").equalsIgnoreCase("SUCCESS")) {
	  	     record.setStatus(1);
	  	     return fqRedpackRecordMapper.updateByPrimaryKeySelective(record);
			}
	      return -1;
	}
	public String createSign(SortedMap<String, String> packageParams,String key) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)
					&& !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + key);
		String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8")
				.toUpperCase();

		return sign;

	}

	@Override
	public FqRedpackRecord getFqRedpackRecordByOrderId(Long orderId) {
		// TODO Auto-generated method stub
		FqRedpackRecordExample example = new FqRedpackRecordExample();
		example.createCriteria().andOrderIdEqualTo(orderId);
		List<FqRedpackRecord> list = fqRedpackRecordMapper.selectByExample(example);
		return list.size()>0?list.get(0):null;
	}

	@Override
	public int updateRedpackRecordStatusByDo(Long id) {
		FqRedpackRecord fr = fqRedpackRecordMapper.selectByPrimaryKey(id);
		if (fr != null) {
			WeixinUserinfoExample example = new WeixinUserinfoExample();
			example.createCriteria().andOpenIdEqualTo(fr.getOpenId());
			List<WeixinUserinfo> list = weixinUserinfoMapper.selectByExample(example);
			if (list.size()>0) {
				fr.setStatus(0);
			}else{
				fr.setStatus(5);
			}
			fr.setCreateTime(new Date());
			fqRedpackRecordMapper.updateByPrimaryKeySelective(fr);
			return fr.getStatus();
		}
		return -1;
	}

	@Override
	public FqRedpackRecord insertFqRedpackRecordByorder(Long orderId) throws Exception {
		Date now = new Date();
		//SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		//String nowday = sd.format(now);
		RebateOrder rebateOrder =  orderMapper.selectByPrimaryKey(orderId);
		StoreRebate storeR = storeRebateMapper.selectByPrimaryKey(rebateOrder.getRebateId());
		FqRedpackRecordExample example = new FqRedpackRecordExample();
		//example.createCriteria().andOrderIdEqualTo(orderId*10+5);		//修改此处   客户没点go，不消耗红包次数
		example.createCriteria().andOpenIdEqualTo(rebateOrder.getOpenId()).andStoreIdEqualTo(storeR.getStoreId()).andStatusEqualTo(6);
		List<FqRedpackRecord> fqRedpackRecords = fqRedpackRecordMapper.selectByExample(example);
		if (fqRedpackRecords.size()>0) {
			return fqRedpackRecords.get(0);
		}
		RebateOrder order = orderMapper.selectByPrimaryKey(orderId);
		StoreRebate rebate = storeRebateMapper.selectByPrimaryKey(order.getRebateId());
		StoreInfo store = storeInfoMapper.selectByPrimaryKey(rebate.getStoreId());
		FqStoreExample storeexample = new FqStoreExample();
		storeexample.createCriteria().andSellerIdEqualTo(store.getSellerId());
		List<FqStore> fqstores = fqStoreMapper.selectByExample(storeexample);
		if (fqstores.size()>0) {
			FqStore  fs = fqstores.get(0);
			FqRedpackExample example4 = new FqRedpackExample();
			example4.createCriteria().andStatusEqualTo(1).andStoreIdEqualTo(store.getId()).andStoreNameEqualTo(store.getName())
			.andBeginDateLessThanOrEqualTo(now).andEndDateGreaterThanOrEqualTo(now);
			example4.setOrderByClause("create_time desc");
			List<FqRedpack> list3 = fqRedpackMapper.selectByExample(example4);
			if (list3.size()>0) {
				FqRedpack fqRedpack = list3.get(0);
				if (fqRedpack.getLaveMoney().compareTo(BigDecimal.ONE)<1) {
					return null;
				}
				FqRedpackRecordExample frrExample = new FqRedpackRecordExample();
				frrExample.createCriteria().andOpenIdEqualTo(order.getOpenId()).andRedpackIdEqualTo(list3.get(0).getId()).andCreateTimeBetween(new Date(now.getTime()-now.getTime()%86400000 - 28800000), new Date(now.getTime()-now.getTime()%86400000 + 57600000));
				int rpnum = fqRedpackRecordMapper.countByExample(frrExample);
				if (rpnum<list3.get(0).getDaliyNum()) {
					
				Long minit = (now.getTime()+28800000)%86400000/1000/60;
				FqRedpackTimeExample example5 = new FqRedpackTimeExample();
				example5.createCriteria().andBeginTimeLessThanOrEqualTo(minit).andEndTimeGreaterThanOrEqualTo(minit).andRedpackIdEqualTo(list3.get(0).getId());
				List<FqRedpackTime> list4 = fqRedpackTimeMapper.selectByExample(example5);
				if (list4.size()>0) {
					FqRedpackDetailExample example6 = new FqRedpackDetailExample();
					example6.createCriteria().andRedpackIdEqualTo(list3.get(0).getId());
					example6.setOrderByClause("id");
					List<FqRedpackDetail> list5 = fqRedpackDetailMapper.selectByExample(example6);
					if (list5.size()>0) {
						BigDecimal rpm = BigDecimal.ZERO;
						if (list5.get(0).getType() == 1) {
							Integer suiji =  (Integer) redisService.getValueByKey("store"+fs.getId());
							if (suiji == null||suiji > 10) {
								suiji = 1;
							}
							int num = 0;
							for (int i = 0; i < list5.size(); i++) {
								FqRedpackDetail frd = list5.get(i);
								num += frd.getProbability()/10;
								if (num>=suiji) {
									int a = (int)(Math.random()*(frd.getMaxAmount().subtract(frd.getMinAmount()).multiply(new BigDecimal(100)).intValue()+1));
									rpm = new BigDecimal(a).divide(new BigDecimal(100)).add(frd.getMinAmount());
									break;
								}
							}
							if (suiji >= 10) {
								suiji = 0;
							}
							redisService.setValueByKey("store"+store.getId(), suiji+1);	
						}else{
							for (int i = 0; i < list5.size(); i++) {
								FqRedpackDetail frd = list5.get(i);
								if (frd.getMaxMoney().compareTo(order.getRealPay())== 1 && frd.getMinMoney().compareTo(order.getRealPay())<1) {
									int a = (int)(Math.random()*(frd.getMaxAmount().subtract(frd.getMinAmount()).multiply(new BigDecimal(100)).intValue()+1));
									rpm = new BigDecimal(a).divide(new BigDecimal(100)).add(frd.getMinAmount());
									break;
								}
							}
						}
						if (rpm.compareTo(BigDecimal.ONE)>-1) {
							if (fqRedpack.getLaveMoney().compareTo(rpm)<0) {
								return null;
							}else{
								if ((fqRedpack.getLaveMoney().subtract(rpm)).compareTo(fqRedpack.getMinAmount())==-1) {
									rpm = fqRedpack.getLaveMoney();
									fqRedpack.setStatus(0);
								}
							}
							FqRedpackRecord frr = new FqRedpackRecord();
							frr.setConsumeMoney(order.getRealPay());
							frr.setCreateTime(now);
							frr.setOrderId(order.getId()*10+5);
							frr.setRedpackId(list3.get(0).getId());
							frr.setRedpackMoney(rpm);
							frr.setStatus(6);
							frr.setStoreId(store.getId());
							frr.setStoreName(store.getName());
							frr.setOpenId(order.getOpenId());
							fqRedpackRecordMapper.insertSelective(frr);
							fqRedpack.setLaveMoney(fqRedpack.getLaveMoney().subtract(rpm));
							if (fqRedpack.getLaveMoney().compareTo(fqRedpack.getMinAmount()) == -1) {
								fqRedpack.setStatus(0);
							}
							fqRedpackMapper.updateByPrimaryKeySelective(fqRedpack);
							return frr;
//							JSONObject data2 = new JSONObject();
//							data2.put("touser", order.getOpenId());
//							data2.put("msgtype", "news");
//							JSONArray articles = new JSONArray();
//							JSONObject article = new JSONObject();
//							article.put("title", "支付好礼");
//							article.put("url", "http://"+ConstantsConfigurer.getProperty("web_url")+"/user/fqRedpack/index.do?rpid="+frr.getId());
//							article.put("picurl", "http://"+ConstantsConfigurer.getProperty("web_url")+"/images/feiquan320.png");
//							articles.add(article);
//							JSONObject news = new JSONObject();
//							news.put("articles", articles);
//							data2.put("news", news);
//							WeixinUtil.sendCustomMessage(data2.toJSONString());
						}
					}
				}
			}
		}
		}else{
			FqRedpackExample example4 = new FqRedpackExample();
			example4.createCriteria().andStatusEqualTo(1).andStoreIdEqualTo(store.getId()).andStoreNameEqualTo(store.getName())
			.andBeginDateLessThanOrEqualTo(now).andEndDateGreaterThanOrEqualTo(now);
			example4.setOrderByClause("create_time desc");
			List<FqRedpack> list3 = fqRedpackMapper.selectByExample(example4);
			if (list3.size()>0) {
				FqRedpack fqRedpack = list3.get(0);
				if (fqRedpack.getLaveMoney().compareTo(BigDecimal.ONE)<1) {
					return null;
				}
				FqRedpackRecordExample frrExample = new FqRedpackRecordExample();
				frrExample.createCriteria().andOpenIdEqualTo(order.getOpenId()).andRedpackIdEqualTo(list3.get(0).getId()).andCreateTimeBetween(new Date(now.getTime()-now.getTime()%86400000 - 28800000), new Date(now.getTime()-now.getTime()%86400000 + 57600000));
				int rpnum = fqRedpackRecordMapper.countByExample(frrExample);
				if (rpnum<list3.get(0).getDaliyNum()) {
					
				Long minit = (now.getTime()+28800000)%86400000/1000/60;
				FqRedpackTimeExample example5 = new FqRedpackTimeExample();
				example5.createCriteria().andBeginTimeLessThanOrEqualTo(minit).andEndTimeGreaterThanOrEqualTo(minit).andRedpackIdEqualTo(list3.get(0).getId());
				List<FqRedpackTime> list4 = fqRedpackTimeMapper.selectByExample(example5);
				if (list4.size()>0) {
					FqRedpackDetailExample example6 = new FqRedpackDetailExample();
					example6.createCriteria().andRedpackIdEqualTo(list3.get(0).getId());
					example6.setOrderByClause("id");
					List<FqRedpackDetail> list5 = fqRedpackDetailMapper.selectByExample(example6);
					if (list5.size()>0) {
						BigDecimal rpm = BigDecimal.ZERO;
						if (list5.get(0).getType() == 1) {
							Integer suiji =  (Integer) redisService.getValueByKey("store"+store.getId());
							if (suiji == null||suiji > 10) {
								suiji = 1;
							}
							int num = 0;
							for (int i = 0; i < list5.size(); i++) {
								FqRedpackDetail frd = list5.get(i);
								num += frd.getProbability()/10;
								if (num>=suiji) {
									int a = (int)(Math.random()*(frd.getMaxAmount().subtract(frd.getMinAmount()).multiply(new BigDecimal(100)).intValue()+1));
									rpm = new BigDecimal(a).divide(new BigDecimal(100)).add(frd.getMinAmount());
									break;
								}
							}
							if (suiji >= 10) {
								suiji = 0;
							}
							redisService.setValueByKey("store"+store.getId(), suiji+1);	
						}else{
							for (int i = 0; i < list5.size(); i++) {
								FqRedpackDetail frd = list5.get(i);
								if (frd.getMaxMoney().compareTo(order.getRealPay())== 1 && frd.getMinMoney().compareTo(order.getRealPay())<1) {
									int a = (int)(Math.random()*(frd.getMaxAmount().subtract(frd.getMinAmount()).multiply(new BigDecimal(100)).intValue()+1));
									rpm = new BigDecimal(a).divide(new BigDecimal(100)).add(frd.getMinAmount());
									break;
								}
							}
						}
						if (rpm.compareTo(BigDecimal.ONE)>-1) {
							if (fqRedpack.getLaveMoney().compareTo(rpm)<0) {
								return null;
							}else{
								if ((fqRedpack.getLaveMoney().subtract(rpm)).compareTo(fqRedpack.getMinAmount())==-1) {
									rpm = fqRedpack.getLaveMoney();
									fqRedpack.setStatus(0);
								}
							}
							FqRedpackRecord frr = new FqRedpackRecord();
							frr.setConsumeMoney(order.getRealPay());
							frr.setCreateTime(now);
							frr.setOrderId(order.getId()*10+5);
							frr.setRedpackId(list3.get(0).getId());
							frr.setRedpackMoney(rpm);
							frr.setStatus(6);
							frr.setStoreId(store.getId());
							frr.setStoreName(store.getName());
							frr.setOpenId(order.getOpenId());
							fqRedpackRecordMapper.insertSelective(frr);
							fqRedpack.setLaveMoney(fqRedpack.getLaveMoney().subtract(rpm));
							if (fqRedpack.getLaveMoney().compareTo(fqRedpack.getMinAmount()) == -1) {
								fqRedpack.setStatus(0);
							}
							fqRedpackMapper.updateByPrimaryKeySelective(fqRedpack);
							return frr;
						}
					}
				}
			}
		}
		}
		return null;
	}

	@Override
	public FqRedpackRecord insertFqRedpackRecordByfqorder(Long orderId)
			throws Exception {
		Date now = new Date();
		FqRedpackRecordExample example = new FqRedpackRecordExample();
		example.createCriteria().andOrderIdEqualTo(orderId*10+1);
		List<FqRedpackRecord> fqRedpackRecords = fqRedpackRecordMapper.selectByExample(example);
		if (fqRedpackRecords.size()>0) {
			if (fqRedpackRecords.get(0).getStatus()==6) {
				return fqRedpackRecords.get(0);
			}
			return null;
		}
		FqOrder record = fqOrderMapper.selectByPrimaryKey(orderId);
		FqStore store = fqStoreMapper.selectByPrimaryKey(record.getStoreId());
		StoreInfoExample siExample = new StoreInfoExample();
		siExample.createCriteria().andSellerIdEqualTo(store.getSellerId());
		StoreInfo si = storeInfoMapper.selectByExample(siExample).get(0);
		FqUserInfo user =fqUserInfoMapper.selectByPrimaryKey(record.getUserId());
		FqRedpackExample example4 = new FqRedpackExample();
		example4.createCriteria().andStatusEqualTo(1).andStoreIdEqualTo(si.getId()).andStoreNameEqualTo(si.getName())
		.andBeginDateLessThanOrEqualTo(now).andEndDateGreaterThanOrEqualTo(now);
		example4.setOrderByClause("create_time desc");
		List<FqRedpack> list3 = fqRedpackMapper.selectByExample(example4);
		logger.info("-----List<FqRedpack> list3-----"+list3.size());
		if (list3.size()>0) {
			logger.info("-----list3.get(0).getId()-----"+list3.get(0).getId());
			FqRedpackRecordExample frrExample = new FqRedpackRecordExample();
			frrExample.createCriteria().andOpenIdEqualTo(user.getOpenId()).andRedpackIdEqualTo(list3.get(0).getId()).andCreateTimeBetween(new Date(now.getTime()-now.getTime()%86400000 - 28800000), new Date(now.getTime()-now.getTime()%86400000 + 57600000));
			int rpnum = fqRedpackRecordMapper.countByExample(frrExample);
			logger.info("-----rpnum----"+rpnum);
			if (rpnum<list3.get(0).getDaliyNum()) {
				
			Long minit = (now.getTime()+28800000)%86400000/1000/60;
			logger.info("-----minit----"+minit);
			FqRedpackTimeExample example5 = new FqRedpackTimeExample();
			example5.createCriteria().andBeginTimeLessThanOrEqualTo(minit).andEndTimeGreaterThanOrEqualTo(minit).andRedpackIdEqualTo(list3.get(0).getId());
			List<FqRedpackTime> list4 = fqRedpackTimeMapper.selectByExample(example5);
			logger.info("-----list4.size()----"+list4.size());
			if (list4.size()>0) {
				FqRedpackDetailExample example6 = new FqRedpackDetailExample();
				example6.createCriteria().andRedpackIdEqualTo(list3.get(0).getId());
				example6.setOrderByClause("id");
				List<FqRedpackDetail> list5 = fqRedpackDetailMapper.selectByExample(example6);
				logger.info("-----list5.size()----"+list5.size());
				if (list5.size()>0) {
					BigDecimal rpm = BigDecimal.ZERO;
					if (list5.get(0).getType() == 1) {
						Integer suiji =  (Integer) redisService.getValueByKey("store"+store.getId());
						if (suiji == null||suiji > 10) {
							suiji = 1;
						}
						int num = 0;
						for (int i = 0; i < list5.size(); i++) {
							FqRedpackDetail frd = list5.get(i);
							num += frd.getProbability()/10;
							if (num>=suiji) {
								int a = (int)(Math.random()*(frd.getMaxAmount().subtract(frd.getMinAmount()).multiply(new BigDecimal(100)).intValue()+1));
								logger.info("-----a---"+a);
								logger.info("-----chazhi---"+frd.getMaxAmount().subtract(frd.getMinAmount()).multiply(new BigDecimal(100)).intValue()+1);
								rpm = new BigDecimal(a).divide(new BigDecimal(100)).add(frd.getMinAmount());
								logger.info("-----new BigDecimal(a).divide(new BigDecimal(100))---"+new BigDecimal(a).divide(new BigDecimal(100)));
								break;
							}
						}
						if (suiji >= 10) {
							suiji = 0;
						}
						logger.info("-----suiji---"+suiji);

						redisService.setValueByKey("store"+store.getId(), suiji+1);	
					}else{
						for (int i = 0; i < list5.size(); i++) {
							FqRedpackDetail frd = list5.get(i);
							if (frd.getMaxMoney().compareTo(record.getPayAmount())== 1 && frd.getMinMoney().compareTo(record.getPayAmount())<1) {
								int a = (int)(Math.random()*(frd.getMaxAmount().subtract(frd.getMinAmount()).multiply(new BigDecimal(100)).intValue()+1));
								rpm = new BigDecimal(a).divide(new BigDecimal(100)).add(frd.getMinAmount());
								logger.info("-----1rpm---"+rpm);
								break;
							}
						}
					}
					logger.info("-----rpm---"+rpm);
					if (rpm.compareTo(BigDecimal.ZERO)==1) {
						FqRedpackRecord frr = new FqRedpackRecord();
						frr.setConsumeMoney(record.getPayAmount());
						frr.setCreateTime(now);
						frr.setOrderId(record.getId()*10+1);
						frr.setRedpackId(list3.get(0).getId());
						frr.setRedpackMoney(rpm);
						frr.setStatus(6);
						frr.setStoreId(si.getId());
						frr.setStoreName(si.getName());
						frr.setOpenId(user.getOpenId());
						fqRedpackRecordMapper.insertSelective(frr);
						return frr;
//						JSONObject data2 = new JSONObject();
//						data2.put("touser", user.getOpenId());
//						data2.put("msgtype", "news");
//						JSONArray articles = new JSONArray();
//						JSONObject article = new JSONObject();
//						article.put("title", "支付好礼");
//						article.put("url", "http://"+ConstantsConfigurer.getProperty("web_url")+"/user/fqRedpack/index.do?rpid="+frr.getId());
//						article.put("picurl", "http://"+ConstantsConfigurer.getProperty("web_url")+"/images/feiquan320.png");
//						articles.add(article);
//						JSONObject news = new JSONObject();
//						news.put("articles", articles);
//						data2.put("news", news);
//						JSONObject result = WeixinUtil.sendCustomMessage(data2.toJSONString());
//						logger.info("-----result---"+result);
					}
				}
			}
			}
		}
		return null;
	}

	@Override
	public int updateFqRedpackRecordBySend() throws Exception{
		FqRedpackRecordExample example = new FqRedpackRecordExample();
		example.createCriteria().andStatusEqualTo(0);
		List<FqRedpackRecord> list = fqRedpackRecordMapper.selectByExample(example);
		if (list.size()>0) {
			int num = 0;
			String JSAPI_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
			String appId = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_APPID);
//			String appSecret =ConstantsConfigurer.getProperty(SystemConstant.WECHAT_APPSECRET);
			String partner = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_PAY_PARTNER);
			String pkey = ConstantsConfigurer.getProperty(SystemConstant.WECHAT_PAY_PKEY);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			  SortedMap<String, String> packageParams = new TreeMap<String, String>();
			
			  packageParams.put("mch_id", partner);
			  packageParams.put("wxappid", appId);
			  packageParams.put("send_name", "飞券生活");
			
			  packageParams.put("total_num", "1");
			  packageParams.put("wishing", "支付有礼,红包不停!");
			  packageParams.put("client_ip", "192.168.1.1");
			  packageParams.put("act_name", "支付有礼");
			  packageParams.put("remark", "支付越多抢的越多!");
			 
		      String result = null;
			 
			  KeyStore keyStore  = KeyStore.getInstance("PKCS12");
		        FileInputStream instream = new FileInputStream(new File("/data/webapp/jysmall/cert/apiclient_cert.p12"));
		        try {
		            keyStore.load(instream, partner.toCharArray());
		        } finally {
		            instream.close();
		        }

		        // Trust own CA and all self-signed certs
		        SSLContext sslcontext = SSLContexts.custom()
		                .loadKeyMaterial(keyStore, partner.toCharArray())
		                .build();
		        // Allow TLSv1 protocol only
		        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
		                sslcontext,
		                new String[] { "TLSv1" },
		                null,
		                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
		        
		        for (int i = 0; i < list.size(); i++) {
		        	FqRedpackRecord record = list.get(i);
		        	
		        	  packageParams.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
					  packageParams.put("mch_billno", partner+formatter.format(new Date())+BaseUtil.numRandom(10));
					  packageParams.put("re_openid", record.getOpenId());
					  packageParams.put("total_amount", String.valueOf(record.getRedpackMoney().multiply(new BigDecimal(100)).intValue()));
					  String sign = createSign(packageParams, pkey);
					  packageParams.put("sign", sign);
					  String requestXML = XMLUtil.getRequestXml(packageParams);
					  
					  CloseableHttpClient httpclient = HttpClients.custom()
				                .setSSLSocketFactory(sslsf)
				                .build();
				        try {

//				            HttpGet httpget = new HttpGet("https://api.mch.weixin.qq.com/secapi/pay/refund");
				            HttpPost httppost = new HttpPost(JSAPI_URL);
//				            System.out.println("executing request" + httpget.getRequestLine());
				            
				            StringEntity stringentity = new StringEntity(requestXML,"UTF-8");
				            httppost.setEntity(stringentity);
				            CloseableHttpResponse response = httpclient.execute(httppost);
				            try {
				                HttpEntity entity = response.getEntity();

				                if (entity != null) {
				                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(),"UTF-8"));
				                    StringBuffer sb = new StringBuffer();
				                    String text;
				                    while ((text = bufferedReader.readLine()) != null) {
				                    	sb.append(text);
				                    }
				                   result = sb.toString();
				                }
				                EntityUtils.consume(entity);
				            } finally {
				                response.close();
				            }
				        } finally {
				            httpclient.close();
				        }
				        logger.info("redpack---result---"+result);
				        Map<String, String>notifyParamsMap =XMLUtil.doXMLParse(result);
				        if (notifyParamsMap.get("return_code").equalsIgnoreCase("SUCCESS")&&notifyParamsMap.get("result_code").equalsIgnoreCase("SUCCESS")) {
				  	     record.setStatus(1);
				  	     num+= fqRedpackRecordMapper.updateByPrimaryKeySelective(record);
				  	     
						}
				}
		        
		}
		return 0;
	}

	@Override
	public int updateFqRedpackRecordByOpenidAndstatus(String openId) {
		FqRedpackRecordExample example = new FqRedpackRecordExample();
		example.createCriteria().andStatusEqualTo(5).andOpenIdEqualTo(openId);
		int num = 0;
		List<FqRedpackRecord> list = fqRedpackRecordMapper.selectByExample(example);
		for (int i = 0; i < list.size(); i++) {
			FqRedpackRecord frr = list.get(i);
			frr.setStatus(0);
			num +=fqRedpackRecordMapper.updateByPrimaryKeySelective(frr);
		}
		return num;
	}

}
