package cn.qhjys.mall.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.qhjys.mall.common.AccessToken;
import cn.qhjys.mall.common.FROMWHERE;
import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.WxMessage;
import cn.qhjys.mall.entity.WxMessageExample;
import cn.qhjys.mall.entity.WxMessageExample.Criteria;
import cn.qhjys.mall.mapper.StoreInfoMapper;
import cn.qhjys.mall.mapper.WxMessageMapper;
import cn.qhjys.mall.service.WxMessageService;
import cn.qhjys.mall.service.app.SellerCardCouponService;
import cn.qhjys.mall.util.ConstantsConfigurer;
import cn.qhjys.mall.util.InternetImage;
import cn.qhjys.mall.util.WeixinUtil;
import cn.qhjys.mall.vo.WxMessageInfo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service("wxMessageService")
public class WxMessageServiceImpl implements WxMessageService {
	
	@Autowired
	private WxMessageMapper wxMessageMapper;

	@Autowired
	private StoreInfoMapper storeInfoMapper;
	@Autowired
	private SellerCardCouponService sellerCardCouponService;

	
	@Override
	public int insertWxMessage(WxMessage wxMessage) {
		return wxMessageMapper.insertSelective(wxMessage);
	}

	@Override
	public int updateWxMessage(WxMessage wxMessage) {
		return wxMessageMapper.updateByPrimaryKeySelective(wxMessage);
	}

	@Override
	public Page<WxMessage> queryWxmessage(Long sellerId, Integer status,Integer pageNum,Integer pageSize) {
		WxMessageExample example = new WxMessageExample();
		Criteria criteria = example.createCriteria();
		criteria.andSellerIdEqualTo(sellerId);
		if (status != null) {
			criteria.andStatusEqualTo(status);
		}
		PageHelper.startPage(pageNum, pageSize);
		example.setOrderByClause("create_time desc");
		return (Page<WxMessage>) wxMessageMapper.selectByExample(example);
	}

	@Override
	public WxMessage getWxMessage(Long messageId) {
		return wxMessageMapper.selectByPrimaryKey(messageId);
	}

	@Override
	public Page<WxMessage> WxmessageList(Long storeId, String storeName, Integer status,
			Integer pageNum, Integer pageSize) {
		WxMessageExample example = new WxMessageExample();
		Criteria criteria = example.createCriteria();
		if (storeId != null) {
			criteria.andStoreIdEqualTo(storeId);
		}
		if (storeName != null) {
			criteria.andStoreNameEqualTo(storeName);
		}
		if (status != null) {
			criteria.andStatusEqualTo(status);
		}
		PageHelper.startPage(pageNum, pageSize);
		example.setOrderByClause(" create_time DESC");
		return (Page<WxMessage>) wxMessageMapper.selectByExampleWithBLOBs(example);
	}
	
	@Override
	public int updateMessageNum(StoreInfo storeInfo, Integer messageNum) {
		if(null != storeInfo){
			storeInfo.setMessageNum(messageNum);
			return storeInfoMapper.updateByPrimaryKeySelective(storeInfo);
		}
		return -1;
	}

	@Override
	public Integer updateWxContent() {
		try {
			String path = ConstantsConfigurer.getProperty("real_path");
			List<Integer> statuss = new ArrayList<Integer>();
			statuss.add(1);
			statuss.add(3);
			WxMessageExample example = new WxMessageExample();
			example.createCriteria().andTypeIn(statuss).andWxImageIsNull().andStatusIn(statuss);
			List<WxMessage> list = wxMessageMapper.selectByExampleWithBLOBs(example);
			for (int i = 0; i < list.size(); i++) {
				WxMessage message = list.get(0);
				File file = new File(path+message.getImage());
				
				JSONObject json = WeixinUtil.uploadImage(AccessToken.getAccessToken(), "image", file);
				String media_id = json.getString("media_id");
				
				String content = message.getContent();
				System.out.println("content---------"+content);
				List<String> iamges = getImgSrc(content);
				System.out.println("iamges---------"+iamges.size());
				while (iamges.size()>0) {
					String image = iamges.get(0);
					System.out.println("image---------"+image);
					File file1 = null;
					if (image.contains(ConstantsConfigurer.getProperty("web_url"))) {
						file1 = new File(path+image.replace("http://"+ConstantsConfigurer.getProperty("web_url"), ""));
					}else{
						file1 = new File(path+InternetImage.download(image,path));
						
					}
					System.out.println("file1---------"+file1.getCanonicalPath());
					String jsonurl = WeixinUtil.uploadImg(AccessToken.getAccessToken(), file1);
					System.out.println("jsonurl---------"+jsonurl);
					if (!StringUtils.isEmpty(jsonurl)) {
						content = content.replace(image, jsonurl);
					}
					iamges.remove(image);
				}
				System.out.println("content1---------"+content);
				WxMessage message1 = new WxMessage();
				message1.setWxContent(content);
				message1.setWxImage(media_id);
				message1.setId(message.getId());
				wxMessageMapper.updateByPrimaryKeySelective(message1);
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	 public static List<String> getImgSrc(String content){
		 List<String> list = new ArrayList<String>();
         	if (StringUtils.isEmpty(content)) {
				return list;
			}
	       
	        Pattern p_img = Pattern.compile("<(img|IMG)(.*?)(/>|></img>|>)");
	        Matcher m_img = p_img.matcher(content);
	        boolean result_img = m_img.find();
	        if (result_img) {
	            while (result_img) {
	                String str_img = m_img.group(2);
	                 
	                Pattern p_src = Pattern.compile("(src|SRC)=(\"|\')(.*?)(\"|\')");
	                Matcher m_src = p_src.matcher(str_img);
	                if (m_src.find()) {
	                    String str_src = m_src.group(3);
	                    list.add(str_src);
	                }
	                 
	                result_img = m_img.find();
	            }
	        }
	        return list;
	    }

	@Override
	public Integer updateWxSendMessage() {
		try {
			WxMessage messageup = null;
			WxMessageExample example = new WxMessageExample();
			example.createCriteria().andStatusEqualTo(3);
			List<WxMessage> list = wxMessageMapper.selectByExampleWithBLOBs(example);
			for (int i = 0; i < list.size(); i++) {
				WxMessage message = list.get(0);
				if (message.getType()==1) {
					if (!StringUtils.isEmpty(message.getWxImage())) {
						JSONObject json1  = new JSONObject();
						json1.put("thumb_media_id", message.getWxImage());
						json1.put("author", "飞券");
						json1.put("title", message.getTitle());
						json1.put("content_source_url", null);
						json1.put("content", message.getWxContent());
						json1.put("show_cover_pic", 1);
						JSONArray array = new JSONArray();
						array.add(json1);
						JSONObject jsonnews = new JSONObject();
						jsonnews.put("articles", array);
						JSONObject json2 = WeixinUtil.uploadFodder(AccessToken.getAccessToken(), jsonnews.toJSONString());
						String mediaId = json2.getString("media_id");
						List<String> openIDSet = sellerCardCouponService.queryConsumerBySellerId(message.getSellerId());
						String [] openids = openIDSet.toArray(new String [openIDSet.size()]);
						JSONObject media_id1 = new JSONObject();
						media_id1.put("media_id", mediaId);
						JSONObject news = new JSONObject();
						news.put("touser", openids);
						news.put("mpnews", media_id1);
						news.put("msgtype", "mpnews");
						JSONObject json3 = WeixinUtil.sendMessage(AccessToken.getAccessToken(), news.toJSONString());
						System.out.println("fasong-----json3----"+json3);
						if (json3.getInteger("errcode").equals(0)) {
							messageup = new WxMessage();
							messageup.setId(message.getId());
							messageup.setStatus(4);
							wxMessageMapper.updateByPrimaryKeySelective(messageup);
						}
					}
					
				}else if (message.getType()==2) {
					JSONObject content = new JSONObject();
					content.put("content", message.getContent());
					JSONObject news = new JSONObject();
					List<String> openIDSet = sellerCardCouponService.queryConsumerBySellerId(message.getSellerId());
					String [] openids = openIDSet.toArray(new String [openIDSet.size()]);
					news.put("touser", openids);
					news.put("text", content);
					news.put("msgtype", "text");
					JSONObject json3 = WeixinUtil.sendMessage(AccessToken.getAccessToken(), news.toJSONString());
					System.out.println("fasong-----json3----"+json3);
					if (json3.getInteger("errcode").equals(0)) {
						messageup = new WxMessage();
						messageup.setId(message.getId());
						messageup.setStatus(4);
						wxMessageMapper.updateByPrimaryKeySelective(messageup);
					}
				}else{
					if (!StringUtils.isEmpty(message.getWxImage())) {
							JSONObject content = new JSONObject();
							content.put("media_id", message.getWxImage());
							JSONObject news = new JSONObject();
							List<String> openIDSet = sellerCardCouponService.queryConsumerBySellerId(message.getSellerId());
							String [] openids = openIDSet.toArray(new String [openIDSet.size()]);
							news.put("touser", openids);
							news.put("image", content);
							news.put("msgtype", "image");
							JSONObject json3 = WeixinUtil.sendMessage(AccessToken.getAccessToken(), news.toJSONString());
							System.out.println("fasong-----json3----"+json3);
							if (json3.getInteger("errcode").equals(0)) {
								messageup = new WxMessage();
								messageup.setId(message.getId());
								messageup.setStatus(4);
								wxMessageMapper.updateByPrimaryKeySelective(messageup);
							}
						}
					}
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public Integer updateWxMessageBySend(Long id,Long sellerId,Long storeId) {
		StoreInfo store = storeInfoMapper.selectByPrimaryKey(storeId);
		if (store == null || store.getMessageNum()<1) {
			return -2;
		}
		WxMessage message = wxMessageMapper.selectByPrimaryKey(id);
		if (message == null || !message.getSellerId().equals(sellerId)) {
			return -1;
		}
		message.setStatus(3);
		wxMessageMapper.updateByPrimaryKeySelective(message);
		store.setMessageNum(store.getMessageNum()-1);
		storeInfoMapper.updateByPrimaryKeySelective(store);
		return 1;
	}
	
}
