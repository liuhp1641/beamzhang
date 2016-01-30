package com.cm.manage.model.report;

import java.io.Serializable;
import java.util.Date;

public class AccountReport implements Serializable {

	private static final long serialVersionUID = 8490484167044679131L;

	private Integer id;
	private String logDay;// 记录时间
	private Integer eventType;// 操作类型(0,出帐，1,入帐)
	private String secondType;// 二级类型
	private Double bonusAmount;// 中奖金余额
	private Double rechargeAmount;// 可用的充值金额
	private Double presentAmount;// 可用的赠送(奖励)金额
	private Double score;// 可用的积分
	private Double commission;// 可用佣金
	private Double gold;// 可用金币
	private Integer memberType;
	private Date createTime;//创建时间
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public Double getBonusAmount() {
		return bonusAmount;
	}

	public void setBonusAmount(Double bonusAmount) {
		this.bonusAmount = bonusAmount;
	}

	

	public String getLogDay() {
		return logDay;
	}

	public void setLogDay(String logDay) {
		this.logDay = logDay;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Double getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(Double rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public Double getPresentAmount() {
		return presentAmount;
	}

	public void setPresentAmount(Double presentAmount) {
		this.presentAmount = presentAmount;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public Double getGold() {
		return gold;
	}

	public void setGold(Double gold) {
		this.gold = gold;
	}

	public Integer getMemberType() {
		return memberType;
	}

	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}

	public Integer getEventType() {
		return eventType;
	}

	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}

	public String getSecondType() {
		return secondType;
	}

	public void setSecondType(String secondType) {
		this.secondType = secondType;
	}
}
