package cn.qhjys.mall.vo.system;

import java.math.BigDecimal;

import cn.qhjys.mall.common.BaseVo;

public class StoreLoanVo extends BaseVo {
	
	private Integer historyNum;// 历史项目数
	private Integer currentNum;// 当前项目数
	private BigDecimal historyMoney;// 历史放款金额
	private BigDecimal currentMoney; // 当前放款金额
	private BigDecimal currentNorepayment;// 当前未回收本金
	private BigDecimal historyInterestTotal;// 历史服务费收入

	public Integer getHistoryNum() {
		return historyNum;
	}

	public void setHistoryNum(Integer historyNum) {
		this.historyNum = historyNum;
	}

	public Integer getCurrentNum() {
		return currentNum;
	}

	public void setCurrentNum(Integer currentNum) {
		this.currentNum = currentNum;
	}

	public BigDecimal getHistoryMoney() {
		return historyMoney;
	}

	public void setHistoryMoney(BigDecimal historyMoney) {
		this.historyMoney = historyMoney;
	}

	public BigDecimal getCurrentMoney() {
		return currentMoney;
	}

	public void setCurrentMoney(BigDecimal currentMoney) {
		this.currentMoney = currentMoney;
	}

	public BigDecimal getCurrentNorepayment() {
		return currentNorepayment;
	}

	public void setCurrentNorepayment(BigDecimal currentNorepayment) {
		this.currentNorepayment = currentNorepayment;
	}

	public BigDecimal getHistoryInterestTotal() {
		return historyInterestTotal;
	}

	public void setHistoryInterestTotal(BigDecimal historyInterestTotal) {
		this.historyInterestTotal = historyInterestTotal;
	}

}
