package com.cm.manage.vo.operate;

import java.io.Serializable;
public class ExchangeVO implements Serializable{

	private static final long serialVersionUID = -4340309485737634049L;
	
	private Long id;
	private String sid;//渠道
	private String sidName;
	private String exchangeId;// 兑换ID
	private String exchangeName;// 兑换名称
	private String exchangeNote;// 兑换内容
	private String startTime;// 开始时间
	private String endTime;// 结束时间
	private Integer status;// 状态 0 新建 1 上线 2 停用
	private String exchangeUserCode;// 兑换对应的总帐账户
	private String exchangeUserName;//
	private Integer returnType;// 是否分期 0 不分 1分期
	private Integer returnNumbers;// 分期数量
	private Integer eachNumber;// 每期间隔
	private Integer eachUnit;// 每期间隔单位 0 天 1 周 2 月 必填
	private String createTime;
	private String updateTime;//
	private Integer exchangeGroup;//分组
	private Integer isBindingMobile;
	private String exchangeDetailId;// 兑换选项明细ID
	private String exchangeDetail;// 兑换选项明细说明
	private String outAmountLowText;// 消费资金金额上限
	private String outAmountHighText;// 消费资金金额下限
	private Double outAmountLow;// 消费资金金额上限
	private Double outAmountHigh;// 消费资金金额下限
	private Integer inAmountRate;// 兑换比例
	private Integer outAccountType;// 消费资金类型 0 充值 1奖金 2 红包 3 积分 4 金币
	private Integer inAccountType;// 兑换资金类型 0 充值 1 奖金 2 红包 3 积分 4 金币
	private Integer itemStatus;// 状态 0 可用 1 停用
	private Integer times;// 可以兑换次数 -1代表无限制
	private String itemCreateTime;
	private String itemUpdateTime;//
	
	private String startFromDate;
	private String startToDate;
	private String endFromDate;
	private String endToDate;
	
	private boolean flag;//是否模糊查询
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
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
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
	public Integer getReturnType() {
		return returnType;
	}
	public void setReturnType(Integer returnType) {
		this.returnType = returnType;
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getExchangeDetailId() {
		return exchangeDetailId;
	}
	public void setExchangeDetailId(String exchangeDetailId) {
		this.exchangeDetailId = exchangeDetailId;
	}
	public String getExchangeDetail() {
		return exchangeDetail;
	}
	public void setExchangeDetail(String exchangeDetail) {
		this.exchangeDetail = exchangeDetail;
	}
	public Integer getOutAccountType() {
		return outAccountType;
	}
	public void setOutAccountType(Integer outAccountType) {
		this.outAccountType = outAccountType;
	}
	public Integer getInAccountType() {
		return inAccountType;
	}
	public void setInAccountType(Integer inAccountType) {
		this.inAccountType = inAccountType;
	}
	public Integer getItemStatus() {
		return itemStatus;
	}
	public void setItemStatus(Integer itemStatus) {
		this.itemStatus = itemStatus;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
	public String getItemCreateTime() {
		return itemCreateTime;
	}
	public void setItemCreateTime(String itemCreateTime) {
		this.itemCreateTime = itemCreateTime;
	}
	public String getItemUpdateTime() {
		return itemUpdateTime;
	}
	public void setItemUpdateTime(String itemUpdateTime) {
		this.itemUpdateTime = itemUpdateTime;
	}
	public String getExchangeUserName() {
		return exchangeUserName;
	}
	public void setExchangeUserName(String exchangeUserName) {
		this.exchangeUserName = exchangeUserName;
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
	public Double getOutAmountLow() {
		return outAmountLow;
	}
	public void setOutAmountLow(Double outAmountLow) {
		this.outAmountLow = outAmountLow;
	}
	public Double getOutAmountHigh() {
		return outAmountHigh;
	}
	public void setOutAmountHigh(Double outAmountHigh) {
		this.outAmountHigh = outAmountHigh;
	}
	public Integer getInAmountRate() {
		return inAmountRate;
	}
	public void setInAmountRate(Integer inAmountRate) {
		this.inAmountRate = inAmountRate;
	}
	public String getOutAmountLowText() {
		return outAmountLowText;
	}
	public void setOutAmountLowText(String outAmountLowText) {
		this.outAmountLowText = outAmountLowText;
	}
	public String getOutAmountHighText() {
		return outAmountHighText;
	}
	public void setOutAmountHighText(String outAmountHighText) {
		this.outAmountHighText = outAmountHighText;
	}
	public String getStartFromDate() {
		return startFromDate;
	}
	public void setStartFromDate(String startFromDate) {
		this.startFromDate = startFromDate;
	}
	public String getStartToDate() {
		return startToDate;
	}
	public void setStartToDate(String startToDate) {
		this.startToDate = startToDate;
	}
	public String getEndFromDate() {
		return endFromDate;
	}
	public void setEndFromDate(String endFromDate) {
		this.endFromDate = endFromDate;
	}
	public String getEndToDate() {
		return endToDate;
	}
	public void setEndToDate(String endToDate) {
		this.endToDate = endToDate;
	}
	public boolean getFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
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
