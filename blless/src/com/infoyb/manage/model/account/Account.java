package com.cm.manage.model.account;

import java.io.Serializable;
import java.util.Date;

/**
 * User: 邓玉明 Date: 11-3-27 下午3:58
 */
public class Account implements Serializable {
	private static final long serialVersionUID = 8305268878858528657L;
	private Long id;
	private String userCode;

	private Double bonusAmount;// 中奖金余额
	private Double bonusAmountFreeze;// 冻结的奖金
	private Double bonusTotal;// 中奖存入总金额
	private Double bonusDrawTotal;// 中奖奖金取出总金额
	private Double bonusInTotal;// 中奖转入总金额
	private Double bonusOutTotal;// 中奖转出总金额
	private Double bonusPayTotal;// 中奖奖金消费总金额

	private Double rechargeAmount;// 可用的充值金额
	private Double rechargeAmountFreeze;// 冻结的充值金额
	private Double rechargeTotal;// 充值存入总金额
	private Double rechargeDrawTotal;// 充值金取出总金额
	private Double rechargeInTotal;// 充值转入总金额
	private Double rechargeOutTotal;// 充值转出总金额
	private Double rechargePayTotal;// 充值金消费总金额

	private Double presentAmount;// 可用的赠送(奖励)金额
	private Double presentAmountFreeze;// 冻结的赠送金额
	private Double presentTotal;// 赠送(奖励)存入金额
	private Double presentDrawTotal;// 赠送(奖励)取出金额
	private Double presentInTotal;// 赠送(奖励)转入金额
	private Double presentOutTotal;// 赠送(奖励)转出金额
	private Double presentPayTotal;// 赠送金消费总金额

	private Double score;// 可用的积分
	private Double scoreFreeze;// 冻结的积分
	private Double scoreTotal;// 积分存入总额
	private Double scoreDrawTotal;// 积分取出总额
	private Double scoreInTotal;// 积分转入总额
	private Double scoreOutTotal;// 积分转出总额
	private Double scorePayTotal;// 积分消费总额

	private Double commission;// 可用佣金
	private Double commissionFreeze;// 冻结佣金
	private Double commissionTotal;// 返佣存入总金额
	private Double commissionDrawTotal;// 返佣取出总金额
	private Double commissionInTotal;// 返佣转入总金额
	private Double commissionOutTotal;// 返佣转出总金额
	private Double commissionPayTotal;// 返佣消费总金额

	private Double gold;// 可用金币
	private Double goldFreeze;// 冻结金币
	private Double goldTotal;// 金币存入总金额
	private Double goldDrawTotal;// 金币取出总金额
	private Double goldInTotal;// 金币转入总金额
	private Double goldOutTotal;// 金币转出总金额
	private Double goldPayTotal;// 金币消费总金额

	private Date createTime;
	private Date updateTime;

	public Account() {
		this.bonusAmount = 0d;
		this.bonusAmountFreeze = 0d;
		this.bonusTotal = 0d;
		this.bonusDrawTotal = 0d;
		this.bonusInTotal = 0d;
		this.bonusOutTotal = 0d;
		this.bonusPayTotal = 0d;

		this.rechargeAmount = 0d;
		this.rechargeAmountFreeze = 0d;
		this.rechargeTotal = 0d;
		this.rechargeDrawTotal = 0d;
		this.rechargeInTotal = 0d;
		this.rechargeOutTotal = 0d;
		this.rechargePayTotal = 0d;

		this.presentAmount = 0d;
		this.presentAmountFreeze = 0d;
		this.presentTotal = 0d;
		this.presentDrawTotal = 0d;
		this.presentInTotal = 0d;
		this.presentOutTotal = 0d;
		this.presentPayTotal = 0d;

		this.score = 0d;
		this.scoreFreeze = 0d;
		this.scoreTotal = 0d;
		this.scoreDrawTotal = 0d;
		this.scoreInTotal = 0d;
		this.scoreOutTotal = 0d;
		this.scorePayTotal = 0d;

		this.commission = 0d;
		this.commissionFreeze = 0d;
		this.commissionTotal = 0d;
		this.commissionDrawTotal = 0d;
		this.commissionInTotal = 0d;
		this.commissionOutTotal = 0d;
		this.commissionPayTotal = 0d;

		this.gold = 0d;
		this.bonusAmountFreeze = 0d;
		this.goldTotal = 0d;
		this.goldDrawTotal = 0d;
		this.goldInTotal = 0d;
		this.goldOutTotal = 0d;
		this.goldPayTotal = 0d;
	}

	public Account(Long id, String userCode, Double bonusAmount, Double bonusAmountFreeze, Double bonusTotal, Double bonusDrawTotal, Double bonusInTotal, Double bonusOutTotal, Double bonusPayTotal,
			Double rechargeAmount, Double rechargeAmountFreeze, Double rechargeTotal, Double rechargeDrawTotal, Double rechargeInTotal, Double rechargeOutTotal, Double rechargePayTotal,
			Double presentAmount, Double presentAmountFreeze, Double presentTotal, Double presentDrawTotal, Double presentInTotal, Double presentOutTotal, Double presentPayTotal, Double score,
			Double scoreFreeze, Double scoreTotal, Double scoreDrawTotal, Double scoreInTotal, Double scoreOutTotal, Double scorePayTotal, Double commission, Double commissionFreeze,
			Double commissionTotal, Double commissionDrawTotal, Double commissionInTotal, Double commissionOutTotal, Double commissionPayTotal, Double gold, Double goldFreeze, Double goldTotal,
			Double goldDrawTotal, Double goldInTotal, Double goldOutTotal, Double goldPayTotal, Date createTime, Date updateTime) {
		this.id = id;
		this.userCode = userCode;
		this.bonusAmount = bonusAmount;
		this.bonusAmountFreeze = bonusAmountFreeze;
		this.bonusTotal = bonusTotal;
		this.bonusDrawTotal = bonusDrawTotal;
		this.bonusInTotal = bonusInTotal;
		this.bonusOutTotal = bonusOutTotal;
		this.bonusPayTotal = bonusPayTotal;
		this.rechargeAmount = rechargeAmount;
		this.rechargeAmountFreeze = rechargeAmountFreeze;
		this.rechargeTotal = rechargeTotal;
		this.rechargeDrawTotal = rechargeDrawTotal;
		this.rechargeInTotal = rechargeInTotal;
		this.rechargeOutTotal = rechargeOutTotal;
		this.rechargePayTotal = rechargePayTotal;
		this.presentAmount = presentAmount;
		this.presentAmountFreeze = presentAmountFreeze;
		this.presentTotal = presentTotal;
		this.presentDrawTotal = presentDrawTotal;
		this.presentInTotal = presentInTotal;
		this.presentOutTotal = presentOutTotal;
		this.presentPayTotal = presentPayTotal;
		this.score = score;
		this.scoreFreeze = scoreFreeze;
		this.scoreTotal = scoreTotal;
		this.scoreDrawTotal = scoreDrawTotal;
		this.scoreInTotal = scoreInTotal;
		this.scoreOutTotal = scoreOutTotal;
		this.scorePayTotal = scorePayTotal;
		this.commission = commission;
		this.commissionFreeze = commissionFreeze;
		this.commissionTotal = commissionTotal;
		this.commissionDrawTotal = commissionDrawTotal;
		this.commissionInTotal = commissionInTotal;
		this.commissionOutTotal = commissionOutTotal;
		this.commissionPayTotal = commissionPayTotal;
		this.gold = gold;
		this.goldFreeze = goldFreeze;
		this.goldTotal = goldTotal;
		this.goldDrawTotal = goldDrawTotal;
		this.goldInTotal = goldInTotal;
		this.goldOutTotal = goldOutTotal;
		this.goldPayTotal = goldPayTotal;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Double getBonusAmount() {
		return bonusAmount;
	}

	public void setBonusAmount(Double bonusAmount) {
		this.bonusAmount = bonusAmount;
	}

	public Double getBonusAmountFreeze() {
		return bonusAmountFreeze;
	}

	public void setBonusAmountFreeze(Double bonusAmountFreeze) {
		this.bonusAmountFreeze = bonusAmountFreeze;
	}

	public Double getBonusTotal() {
		return bonusTotal;
	}

	public void setBonusTotal(Double bonusTotal) {
		this.bonusTotal = bonusTotal;
	}

	public Double getBonusDrawTotal() {
		return bonusDrawTotal;
	}

	public void setBonusDrawTotal(Double bonusDrawTotal) {
		this.bonusDrawTotal = bonusDrawTotal;
	}

	public Double getBonusInTotal() {
		return bonusInTotal;
	}

	public void setBonusInTotal(Double bonusInTotal) {
		this.bonusInTotal = bonusInTotal;
	}

	public Double getBonusOutTotal() {
		return bonusOutTotal;
	}

	public void setBonusOutTotal(Double bonusOutTotal) {
		this.bonusOutTotal = bonusOutTotal;
	}

	public Double getBonusPayTotal() {
		return bonusPayTotal;
	}

	public void setBonusPayTotal(Double bonusPayTotal) {
		this.bonusPayTotal = bonusPayTotal;
	}

	public Double getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(Double rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public Double getRechargeAmountFreeze() {
		return rechargeAmountFreeze;
	}

	public void setRechargeAmountFreeze(Double rechargeAmountFreeze) {
		this.rechargeAmountFreeze = rechargeAmountFreeze;
	}

	public Double getRechargeTotal() {
		return rechargeTotal;
	}

	public void setRechargeTotal(Double rechargeTotal) {
		this.rechargeTotal = rechargeTotal;
	}

	public Double getRechargeDrawTotal() {
		return rechargeDrawTotal;
	}

	public void setRechargeDrawTotal(Double rechargeDrawTotal) {
		this.rechargeDrawTotal = rechargeDrawTotal;
	}

	public Double getRechargeInTotal() {
		return rechargeInTotal;
	}

	public void setRechargeInTotal(Double rechargeInTotal) {
		this.rechargeInTotal = rechargeInTotal;
	}

	public Double getRechargeOutTotal() {
		return rechargeOutTotal;
	}

	public void setRechargeOutTotal(Double rechargeOutTotal) {
		this.rechargeOutTotal = rechargeOutTotal;
	}

	public Double getRechargePayTotal() {
		return rechargePayTotal;
	}

	public void setRechargePayTotal(Double rechargePayTotal) {
		this.rechargePayTotal = rechargePayTotal;
	}

	public Double getPresentAmount() {
		return presentAmount;
	}

	public void setPresentAmount(Double presentAmount) {
		this.presentAmount = presentAmount;
	}

	public Double getPresentAmountFreeze() {
		return presentAmountFreeze;
	}

	public void setPresentAmountFreeze(Double presentAmountFreeze) {
		this.presentAmountFreeze = presentAmountFreeze;
	}

	public Double getPresentTotal() {
		return presentTotal;
	}

	public void setPresentTotal(Double presentTotal) {
		this.presentTotal = presentTotal;
	}

	public Double getPresentDrawTotal() {
		return presentDrawTotal;
	}

	public void setPresentDrawTotal(Double presentDrawTotal) {
		this.presentDrawTotal = presentDrawTotal;
	}

	public Double getPresentInTotal() {
		return presentInTotal;
	}

	public void setPresentInTotal(Double presentInTotal) {
		this.presentInTotal = presentInTotal;
	}

	public Double getPresentOutTotal() {
		return presentOutTotal;
	}

	public void setPresentOutTotal(Double presentOutTotal) {
		this.presentOutTotal = presentOutTotal;
	}

	public Double getPresentPayTotal() {
		return presentPayTotal;
	}

	public void setPresentPayTotal(Double presentPayTotal) {
		this.presentPayTotal = presentPayTotal;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Double getScoreFreeze() {
		return scoreFreeze;
	}

	public void setScoreFreeze(Double scoreFreeze) {
		this.scoreFreeze = scoreFreeze;
	}

	public Double getScoreTotal() {
		return scoreTotal;
	}

	public void setScoreTotal(Double scoreTotal) {
		this.scoreTotal = scoreTotal;
	}

	public Double getScoreDrawTotal() {
		return scoreDrawTotal;
	}

	public void setScoreDrawTotal(Double scoreDrawTotal) {
		this.scoreDrawTotal = scoreDrawTotal;
	}

	public Double getScoreInTotal() {
		return scoreInTotal;
	}

	public void setScoreInTotal(Double scoreInTotal) {
		this.scoreInTotal = scoreInTotal;
	}

	public Double getScoreOutTotal() {
		return scoreOutTotal;
	}

	public void setScoreOutTotal(Double scoreOutTotal) {
		this.scoreOutTotal = scoreOutTotal;
	}

	public Double getScorePayTotal() {
		return scorePayTotal;
	}

	public void setScorePayTotal(Double scorePayTotal) {
		this.scorePayTotal = scorePayTotal;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public Double getCommissionFreeze() {
		return commissionFreeze;
	}

	public void setCommissionFreeze(Double commissionFreeze) {
		this.commissionFreeze = commissionFreeze;
	}

	public Double getCommissionTotal() {
		return commissionTotal;
	}

	public void setCommissionTotal(Double commissionTotal) {
		this.commissionTotal = commissionTotal;
	}

	public Double getCommissionDrawTotal() {
		return commissionDrawTotal;
	}

	public void setCommissionDrawTotal(Double commissionDrawTotal) {
		this.commissionDrawTotal = commissionDrawTotal;
	}

	public Double getCommissionInTotal() {
		return commissionInTotal;
	}

	public void setCommissionInTotal(Double commissionInTotal) {
		this.commissionInTotal = commissionInTotal;
	}

	public Double getCommissionOutTotal() {
		return commissionOutTotal;
	}

	public void setCommissionOutTotal(Double commissionOutTotal) {
		this.commissionOutTotal = commissionOutTotal;
	}

	public Double getCommissionPayTotal() {
		return commissionPayTotal;
	}

	public void setCommissionPayTotal(Double commissionPayTotal) {
		this.commissionPayTotal = commissionPayTotal;
	}

	public Double getGold() {
		return gold;
	}

	public void setGold(Double gold) {
		this.gold = gold;
	}

	public Double getGoldFreeze() {
		return goldFreeze;
	}

	public void setGoldFreeze(Double goldFreeze) {
		this.goldFreeze = goldFreeze;
	}

	public Double getGoldTotal() {
		return goldTotal;
	}

	public void setGoldTotal(Double goldTotal) {
		this.goldTotal = goldTotal;
	}

	public Double getGoldDrawTotal() {
		return goldDrawTotal;
	}

	public void setGoldDrawTotal(Double goldDrawTotal) {
		this.goldDrawTotal = goldDrawTotal;
	}

	public Double getGoldInTotal() {
		return goldInTotal;
	}

	public void setGoldInTotal(Double goldInTotal) {
		this.goldInTotal = goldInTotal;
	}

	public Double getGoldOutTotal() {
		return goldOutTotal;
	}

	public void setGoldOutTotal(Double goldOutTotal) {
		this.goldOutTotal = goldOutTotal;
	}

	public Double getGoldPayTotal() {
		return goldPayTotal;
	}

	public void setGoldPayTotal(Double goldPayTotal) {
		this.goldPayTotal = goldPayTotal;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
