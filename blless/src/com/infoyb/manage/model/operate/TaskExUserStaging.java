package com.cm.manage.model.operate;

import java.io.Serializable;
import java.util.Date;

public class TaskExUserStaging implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String userCode;
	private String userExchangeId;
	private String exchangeId;// 兑换ID
	private String exchangeDetailId;// 兑换选项明细ID
	private Integer returnAccountType;// 消费资金类型 0现金1 红包 2 积分 3 金币
	private Date returnDate;// 返款时间
	private Double returnAmount;// 返款金额
	private Integer status;// 状态 0 未返 1 已返
	private String exchangeUserCode;// 兑换对应的总帐账户
	private Date createTime;//
	private Date updateTime;//

	public TaskExUserStaging() {

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

	public String getUserExchangeId() {
		return userExchangeId;
	}

	public void setUserExchangeId(String userExchangeId) {
		this.userExchangeId = userExchangeId;
	}

	public String getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(String exchangeId) {
		this.exchangeId = exchangeId;
	}

	public String getExchangeDetailId() {
		return exchangeDetailId;
	}

	public void setExchangeDetailId(String exchangeDetailId) {
		this.exchangeDetailId = exchangeDetailId;
	}

	public Integer getReturnAccountType() {
		return returnAccountType;
	}

	public void setReturnAccountType(Integer returnAccountType) {
		this.returnAccountType = returnAccountType;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Double getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(Double returnAmount) {
		this.returnAmount = returnAmount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getExchangeUserCode() {
		return exchangeUserCode;
	}

	public void setExchangeUserCode(String exchangeUserCode) {
		this.exchangeUserCode = exchangeUserCode;
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
