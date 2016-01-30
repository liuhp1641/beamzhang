package com.cm.manage.model.rebate;

import java.io.Serializable;
import java.util.Date;

public class TaskRebate implements Serializable {
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
	private Date startTime;// 任务开始时间
	private Date endTime;// 任务结束时间
	private Integer status;// 0待上线 1 已上线 2已下线
	private Date createTime;
	private Date updateTime;
	private Integer type;// 返利类型 0投注
	public TaskRebate(Long id, String rebateId, String rebateName, String outUserCode, String lotteryCodes, Integer vipLow, Integer vipHigh, Double scoreRate, Double preRate, Date startTime,
			Date endTime, Integer status, Date createTime, Date updateTime) {
		this.id = id;
		this.rebateId = rebateId;
		this.rebateName = rebateName;
		this.outUserCode = outUserCode;
		this.lotteryCodes = lotteryCodes;
		this.vipLow = vipLow;
		this.vipHigh = vipHigh;
		this.scoreRate = scoreRate;
		this.preRate = preRate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}
