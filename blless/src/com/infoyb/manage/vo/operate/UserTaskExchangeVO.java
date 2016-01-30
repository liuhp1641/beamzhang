package com.cm.manage.vo.operate;

import java.io.Serializable;

public class UserTaskExchangeVO implements Serializable{

	private static final long serialVersionUID = 113523309057028118L;
	
	private Long id;
	private String sid;//渠道
	private String sidName;
	private String exchangeId;// 兑换ID
	private String exchangeName;// 兑换名称
	private String exchangeNote;// 兑换内容
	private String exchangeUserCode;// 兑换对应的总帐账户
	private String exchangeUserName;//
	private Integer returnType;// 是否分期 0 不分 1分期
	private String exchangeDetailId;// 兑换选项明细ID
	private Double returnAmount;// 返款金额
	private Double returnAmountLow;// 返款金额下限
	private Double returnAmountHigh;// 返款金额上限
	private String returnAmountText;
	private String createTime;//参与时间
	private String createFromDate;//参与时间
	private String createToDate;//参与时间截至时间
	private Integer funddingType;//资金类型
	private String userExchangeId;//返赠流水
	private String userCode;
	private String userName;
	private Integer returnNumbers;//分期数量
	private Double inAmount;//返赠总金额
	private String inAmountText;//返赠总金额
	
	private String returnDate;//返赠时间
	private String updateTime;//返赠处理时间
	private Integer status;//返赠状态 0未返 1已返奖
	private boolean flag;//是否模糊查询
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
	public String getSidName() {
		return sidName;
	}
	public void setSidName(String sidName) {
		this.sidName = sidName;
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
	public String getExchangeUserCode() {
		return exchangeUserCode;
	}
	public void setExchangeUserCode(String exchangeUserCode) {
		this.exchangeUserCode = exchangeUserCode;
	}
	public String getExchangeUserName() {
		return exchangeUserName;
	}
	public void setExchangeUserName(String exchangeUserName) {
		this.exchangeUserName = exchangeUserName;
	}
	public Integer getReturnType() {
		return returnType;
	}
	public void setReturnType(Integer returnType) {
		this.returnType = returnType;
	}
	public String getExchangeDetailId() {
		return exchangeDetailId;
	}
	public void setExchangeDetailId(String exchangeDetailId) {
		this.exchangeDetailId = exchangeDetailId;
	}
	public Double getReturnAmount() {
		return returnAmount;
	}
	public void setReturnAmount(Double returnAmount) {
		this.returnAmount = returnAmount;
	}
	public Double getReturnAmountLow() {
		return returnAmountLow;
	}
	public void setReturnAmountLow(Double returnAmountLow) {
		this.returnAmountLow = returnAmountLow;
	}
	public Double getReturnAmountHigh() {
		return returnAmountHigh;
	}
	public void setReturnAmountHigh(Double returnAmountHigh) {
		this.returnAmountHigh = returnAmountHigh;
	}
	public String getCreateFromDate() {
		return createFromDate;
	}
	public void setCreateFromDate(String createFromDate) {
		this.createFromDate = createFromDate;
	}
	public String getCreateToDate() {
		return createToDate;
	}
	public void setCreateToDate(String createToDate) {
		this.createToDate = createToDate;
	}
	public Integer getFunddingType() {
		return funddingType;
	}
	public void setFunddingType(Integer funddingType) {
		this.funddingType = funddingType;
	}
	public String getUserExchangeId() {
		return userExchangeId;
	}
	public void setUserExchangeId(String userExchangeId) {
		this.userExchangeId = userExchangeId;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getReturnNumbers() {
		return returnNumbers;
	}
	public void setReturnNumbers(Integer returnNumbers) {
		this.returnNumbers = returnNumbers;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Double getInAmount() {
		return inAmount;
	}
	public void setInAmount(Double inAmount) {
		this.inAmount = inAmount;
	}
	public boolean getFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getInAmountText() {
		return inAmountText;
	}
	public void setInAmountText(String inAmountText) {
		this.inAmountText = inAmountText;
	}
	public String getReturnAmountText() {
		return returnAmountText;
	}
	public void setReturnAmountText(String returnAmountText) {
		this.returnAmountText = returnAmountText;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	

}
