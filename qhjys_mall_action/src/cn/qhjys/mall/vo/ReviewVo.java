package cn.qhjys.mall.vo;

import cn.qhjys.mall.common.BaseVo;

@SuppressWarnings("serial")
public class ReviewVo extends BaseVo {
	private Long id;
	// 评论人数
	private Integer personNum;
	// 平均评分
	private Double avgScore;
	// 好评人数
	private Integer praiseNum;
	// 中评人数
	private Integer generalNum;
	// 差评人数
	private Integer badNum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPersonNum() {
		return personNum;
	}

	public void setPersonNum(Integer personNum) {
		this.personNum = personNum;
	}

	public Double getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(Double avgScore) {
		this.avgScore = avgScore;
	}

	public Integer getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(Integer praiseNum) {
		this.praiseNum = praiseNum;
	}

	public Integer getGeneralNum() {
		return generalNum;
	}

	public void setGeneralNum(Integer generalNum) {
		this.generalNum = generalNum;
	}

	public Integer getBadNum() {
		return badNum;
	}

	public void setBadNum(Integer badNum) {
		this.badNum = badNum;
	}
}