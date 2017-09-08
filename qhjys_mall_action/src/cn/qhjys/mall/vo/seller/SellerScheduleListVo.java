package cn.qhjys.mall.vo.seller;

import java.util.List;

import cn.qhjys.mall.common.BaseVo;

public class SellerScheduleListVo extends BaseVo{
	
	private List<SellerScheduleInfoVo> dateList;

	public List<SellerScheduleInfoVo> getDateList() {
		return dateList;
	}

	public void setDateList(List<SellerScheduleInfoVo> dateList) {
		this.dateList = dateList;
	}
	
	

}
