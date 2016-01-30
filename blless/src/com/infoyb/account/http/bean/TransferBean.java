package com.cm.account.http.bean;

import java.io.Serializable;

public class TransferBean implements Serializable {
	private static final long serialVersionUID = -3013820759705714126L;
	private String orderId;
	private String outUserCode;
	private String outEventCode;
	private String inUserCode;
	private String inEventCode;
	private Integer type;// 1 充值金 2 奖金 3 红包 4 积分
	private Double amount;
	private String desc;// 操作描述

	public TransferBean() {

	}

	@Override
	public String toString() {
		return "TransferBean [orderId=" + orderId + ", outUserCode=" + outUserCode + ", outEventCode=" + outEventCode + ", inUserCode=" + inUserCode + ", inEventCode=" + inEventCode + ", type="
				+ type + ", amount=" + amount + ", desc=" + desc + "]";
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOutUserCode() {
		return outUserCode;
	}

	public void setOutUserCode(String outUserCode) {
		this.outUserCode = outUserCode;
	}

	public String getOutEventCode() {
		return outEventCode;
	}

	public void setOutEventCode(String outEventCode) {
		this.outEventCode = outEventCode;
	}

	public String getInUserCode() {
		return inUserCode;
	}

	public void setInUserCode(String inUserCode) {
		this.inUserCode = inUserCode;
	}

	public String getInEventCode() {
		return inEventCode;
	}

	public void setInEventCode(String inEventCode) {
		this.inEventCode = inEventCode;
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
