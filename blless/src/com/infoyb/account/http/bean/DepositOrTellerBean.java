package com.cm.account.http.bean;

import java.io.Serializable;

public class DepositOrTellerBean implements Serializable {

	private static final long serialVersionUID = -4677201474293221186L;

	private String orderId;
	private String userCode;// 存入账户
	private String eventCode;//
	private Integer type; // 1 存入 2 取出
	private Integer amountType;// 1 充值金 2 奖金 3 红包 4 积分
	private Double amount;
	private String memo;// 操作描述

	public DepositOrTellerBean() {

	}

	@Override
	public String toString() {
		return "DepositOrTellerBean [orderId=" + orderId + ", userCode=" + userCode + ", eventCode=" + eventCode + ", type=" + type + ", amountType=" + amountType + ", amount=" + amount + ", memo="
				+ memo + "]";
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getAmountType() {
		return amountType;
	}

	public void setAmountType(Integer amountType) {
		this.amountType = amountType;
	}

	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}
}
