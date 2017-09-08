package cn.qhjys.mall.vo.system;

import java.io.Serializable;
import java.util.Date;

public class CouponsVirifyVo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		private String openId; //消费用户
		private String nickName; //微信消费用户昵称
	
	    private Date verifyTime; //消费时间
	    
	    private String dishName; //消费菜品
	    private String rank;//奖品等级
	    
	    private String lotterName; //活动名称

		public String getOpenId() {
			return openId;
		}

		public void setOpenId(String openId) {
			this.openId = openId;
		}

		public Date getVerifyTime() {
			return verifyTime;
		}

		public void setVerifyTime(Date verifyTime) {
			this.verifyTime = verifyTime;
		}

		public String getDishName() {
			return dishName;
		}

		public void setDishName(String dishName) {
			this.dishName = dishName;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

		public String getLotterName() {
			return lotterName;
		}

		public void setLotterName(String lotterName) {
			this.lotterName = lotterName;
		}

		public String getNickName() {
			return nickName;
		}

		public void setNickName(String nickName) {
			this.nickName = nickName;
		}

		public String getRank() {
			return rank;
		}

		public void setRank(String rank) {
			this.rank = rank;
		}
	    
	    
		
	    
	    
   
}