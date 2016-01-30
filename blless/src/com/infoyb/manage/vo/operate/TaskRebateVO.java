package com.cm.manage.vo.operate;

import java.io.Serializable;

public class TaskRebateVO implements Serializable {
	private static final long serialVersionUID = 1229499083442476221L;
	private Long id;
	private String rebateId;// 返利id
	private String rebateName;// 返利描述
	private String outUserCode;// 总帐账户
	private String lotteryCodes;// 格式 001,002,003
	private Integer vipLow;// vip下限
	private Integer vipHigh;// vip上限
	private Double scoreRate;// 积分返利率
	private Double preRate;// 红包返利率
	private String startTime;// 任务开始时间
	private String endTime;// 任务结束时间
	private Integer status;// 0待上线 1 已上线 2已下线
	private String createTime;
	private String updateTime;
	private Integer type;// 返利类型 0投注
	private String lotteryNames;

	private String outUserName;//总账账户
	
	private boolean flag;
	
    private String startTimeStart;
	
	private String startTimeEnd;
	
	private String endTimeStart;
	
	private String endTimeEnd;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRebateId() {
		return rebateId;
	}

	public void setRebateId(String rebateId) {
		this.rebateId = rebateId;
	}

	public String getRebateName() {
		return rebateName;
	}

	public void setRebateName(String rebateName) {
		this.rebateName = rebateName;
	}

	public String getOutUserCode() {
		return outUserCode;
	}

	public void setOutUserCode(String outUserCode) {
		this.outUserCode = outUserCode;
	}

	public String getLotteryCodes() {
		return lotteryCodes;
	}

	public void setLotteryCodes(String lotteryCodes) {
		this.lotteryCodes = lotteryCodes;
	}

	public Integer getVipLow() {
		return vipLow;
	}

	public void setVipLow(Integer vipLow) {
		this.vipLow = vipLow;
	}

	public Integer getVipHigh() {
		return vipHigh;
	}

	public void setVipHigh(Integer vipHigh) {
		this.vipHigh = vipHigh;
	}

	public Double getScoreRate() {
		return scoreRate;
	}

	public void setScoreRate(Double scoreRate) {
		this.scoreRate = scoreRate;
	}

	public Double getPreRate() {
		return preRate;
	}

	public void setPreRate(Double preRate) {
		this.preRate = preRate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	

	public String getLotteryNames() {
		return lotteryNames;
	}

	public void setLotteryNames(String lotteryNames) {
		this.lotteryNames = lotteryNames;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getOutUserName() {
		return outUserName;
	}

	public void setOutUserName(String outUserName) {
		this.outUserName = outUserName;
	}

	public String getStartTimeStart() {
		return startTimeStart;
	}

	public void setStartTimeStart(String startTimeStart) {
		this.startTimeStart = startTimeStart;
	}

	public String getStartTimeEnd() {
		return startTimeEnd;
	}

	public void setStartTimeEnd(String startTimeEnd) {
		this.startTimeEnd = startTimeEnd;
	}

	public String getEndTimeStart() {
		return endTimeStart;
	}

	public void setEndTimeStart(String endTimeStart) {
		this.endTimeStart = endTimeStart;
	}

	public String getEndTimeEnd() {
		return endTimeEnd;
	}

	public void setEndTimeEnd(String endTimeEnd) {
		this.endTimeEnd = endTimeEnd;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
}
