package cn.qhjys.mall.vo;

import java.util.List;

import cn.qhjys.mall.entity.CardCouponTemplate;
import cn.qhjys.mall.entity.StoreInfo;

public class PushCardVo {
	private StoreInfo store;
	private CardCouponTemplate cardCouponTemplate;
	private List<String> openIds;
	
	public StoreInfo getStore() {
		return store;
	}
	public void setStore(StoreInfo store) {
		this.store = store;
	}
	public CardCouponTemplate getCardCouponTemplate() {
		return cardCouponTemplate;
	}
	public void setCardCouponTemplate(CardCouponTemplate cardCouponTemplate) {
		this.cardCouponTemplate = cardCouponTemplate;
	}
	public List<String> getOpenIds() {
		return openIds;
	}
	public void setOpenIds(List<String> openIds) {
		this.openIds = openIds;
	}
	
	
}
