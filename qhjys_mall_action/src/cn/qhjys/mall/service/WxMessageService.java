package cn.qhjys.mall.service;

import com.github.pagehelper.Page;

import cn.qhjys.mall.entity.StoreInfo;
import cn.qhjys.mall.entity.WxMessage;

public interface WxMessageService {
	public int insertWxMessage(WxMessage wxMessage);
	public int updateWxMessage(WxMessage wxMessage);
	public Page<WxMessage> queryWxmessage(Long sellerId,Integer status,Integer pageNum,Integer pageSize);
	public WxMessage getWxMessage(Long messageId);
	public Page<WxMessage> WxmessageList(Long storeId, String storeName,Integer status,Integer pageNum,Integer pageSize);
	/**
	 * 重置群发次数
	 * @param storeInfo  重置店铺
	 * @param messageNum 群发次数
	 * @return
	 */
	public int updateMessageNum(StoreInfo storeInfo, Integer messageNum);
	public Integer updateWxContent();
	public Integer updateWxSendMessage();
	
	public Integer updateWxMessageBySend(Long id,Long sellerId,Long storeId);
}
