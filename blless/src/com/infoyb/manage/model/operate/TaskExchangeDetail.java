package com.cm.manage.model.operate;

import java.io.Serializable;
import java.util.Date;

public class TaskExchangeDetail implements Serializable {
	private static final long serialVersionUID = 5008347378978934211L;

	private Long id;
	private String exchangeId;// 兑换ID
	private String exchangeDetailId;// 兑换选项明细ID
	private String exchangeDetail;// 兑换选项明细说明
	private Integer outAccountType;// 消费资金类型 0 充值 1奖金 2 红包 3 积分 4 金币
	private Double outAmountLow;// 消费资金金额上限
	private Double outAmountHigh;// 消费资金金额下限
	private Integer inAccountType;// 兑换资金类型 0 充值 1 奖金 2 红包 3 积分 4 金币
	private Integer inAmountRate;// 兑换比例
	private Integer status;// 状态 0 可用 1 停用
	private Integer times;// 可以兑换次数 -1代表无限制
	private Date createTime;
	private Date updateTime;//

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


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
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

	
}
