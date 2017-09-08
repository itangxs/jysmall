package cn.qhjys.mall.vo;

import java.util.List;

public class AcardLotteryInfoVo {
	
	private Long prizeId;
	private String prizeName;
	
	private List<AcardLotteryUserInfo> infos;
	
	public Long getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(Long prizeId) {
		this.prizeId = prizeId;
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public List<AcardLotteryUserInfo> getInfos() {
		return infos;
	}

	public void setInfos(List<AcardLotteryUserInfo> infos) {
		this.infos = infos;
	}

}
