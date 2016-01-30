package com.cm.manage.model.operate;

import java.io.Serializable;
import java.util.Date;

public class TaskUserExchange implements Serializable {
	private static final long serialVersionUID = 4091414275650188678L;
	private Long id;
	private String sid;
	private String userCode;
	private String userExchangeId;
	private String exchangeId;// 兑换ID
	private String exchangeDetailId;// 兑换选项明细ID
	private Integer outAccountType;// 消费资金类型 0现金1 红包 2 积分 3 金币
	private Double outAmount;// 消费资金金额
	private Integer inAccountType;// 兑换资金类型 0现金1 红包 2 积分 3 金币
	private Double inAmount;// 兑换资金金额
	private String exchangeUserCode;// 兑换对应的总帐账户
	private String ip;
	private String machineCode;
	private Date createTime;
	private Integer returnNumbers;

	public TaskUserExchange() {

	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
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

	public Integer getOutAccountType() {
		return outAccountType;
	}

	public void setOutAccountType(Integer outAccountType) {
		this.outAccountType = outAccountType;
	}

	public Double getOutAmount() {
		return outAmount;
	}

	public void setOutAmount(Double outAmount) {
		this.outAmount = outAmount;
	}

	public Integer getInAccountType() {
		return inAccountType;
	}

	public void setInAccountType(Integer inAccountType) {
		this.inAccountType = inAccountType;
	}

	public Double getInAmount() {
		return inAmount;
	}

	public void setInAmount(Double inAmount) {
		this.inAmount = inAmount;
	}

	public String getExchangeUserCode() {
		return exchangeUserCode;
	}

	public void setExchangeUserCode(String exchangeUserCode) {
		this.exchangeUserCode = exchangeUserCode;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMachineCode() {
		return machineCode;
	}

	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getReturnNumbers() {
		return returnNumbers;
	}
	public void setReturnNumbers(Integer returnNumbers) {
		this.returnNumbers = returnNumbers;
	}
	
}
