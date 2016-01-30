package com.cm.manage.model.operate;

import java.util.Date;

public class TaskExchange {
	private Long id;
	private String exchangeId;// 兑换ID
	private String exchangeName;// 兑换名称
	private String exchangeNote;// 兑换内容
	private Date startTime;// 开始时间
	private Date endTime;// 结束时间
	private Integer status;// 状态 0 新建 1 上线 2 停用
	private String exchangeUserCode;// 兑换对应的总帐账户
	private Integer returnType;// 是否分期 0 不分 1分期
	private Integer returnNumbers;// 分期数量
	private Integer eachNumber;// 每期间隔
	private Integer eachUnit;// 每期间隔单位 0 天 1 周 2 月 必填
	private Date createTime;
	private Date updateTime;//
	private String sid;//渠道
	private String sidName;//渠道
	private String exchangeUserName;
	private Integer exchangeGroup;
	private Integer isBindingMobile;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(String exchangeId) {
		this.exchangeId = exchangeId;
	}

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public String getExchangeNote() {
		return exchangeNote;
	}

	public void setExchangeNote(String exchangeNote) {
		this.exchangeNote = exchangeNote;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	public Integer getReturnNumbers() {
		return returnNumbers;
	}

	public void setReturnNumbers(Integer returnNumbers) {
		this.returnNumbers = returnNumbers;
	}

	public Integer getEachNumber() {
		return eachNumber;
	}

	public void setEachNumber(Integer eachNumber) {
		this.eachNumber = eachNumber;
	}

	public Integer getEachUnit() {
		return eachUnit;
	}

	public void setEachUnit(Integer eachUnit) {
		this.eachUnit = eachUnit;
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

	public Integer getReturnType() {
		return returnType;
	}

	public void setReturnType(Integer returnType) {
		this.returnType = returnType;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getExchangeUserName() {
		return exchangeUserName;
	}

	public void setExchangeUserName(String exchangeUserName) {
		this.exchangeUserName = exchangeUserName;
	}

	public String getSidName() {
		return sidName;
	}

	public void setSidName(String sidName) {
		this.sidName = sidName;
	}

	public Integer getExchangeGroup() {
		return exchangeGroup;
	}

	public void setExchangeGroup(Integer exchangeGroup) {
		this.exchangeGroup = exchangeGroup;
	}

	public Integer getIsBindingMobile() {
		return isBindingMobile;
	}

	public void setIsBindingMobile(Integer isBindingMobile) {
		this.isBindingMobile = isBindingMobile;
	}
	

}
