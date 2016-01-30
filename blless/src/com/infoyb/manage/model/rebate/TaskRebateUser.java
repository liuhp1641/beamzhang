package com.cm.manage.model.rebate;

import java.io.Serializable;
import java.util.Date;

public class TaskRebateUser implements Serializable {
	private static final long serialVersionUID = 1229499083442476221L;
	private Long id;
	private String rebateUserId;// 返利用户id
	private String rebateId;// 返利id
	private String userCode;// 用户code
	private String rebateName;// 返利描述
	private String outUserCode;// 总帐账户
	private String lotteryCode;// 彩种
	private Integer type;// 0 投注
	private String orderId;// 投注订单号
	private Integer vip;// vip
	private Double amount;// 投注金额
	private Double scoreRate;// 积分返利率
	private Double returnScore;// 返积分数
	private Double preRate;// 红包返利率
	private Double returnPre;// 返红包时间
	private Integer status;// 0 未返 1已返
	private Date createTime;
	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRebateUserId() {
		return rebateUserId;
	}

	public void setRebateUserId(String rebateUserId) {
		this.rebateUserId = rebateUserId;
	}

	public String getRebateId() {
		return rebateId;
	}

	public void setRebateId(String rebateId) {
		this.rebateId = rebateId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
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

	public String getLotteryCode() {
		return lotteryCode;
	}

	public void setLotteryCode(String lotteryCode) {
		this.lotteryCode = lotteryCode;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getVip() {
		return vip;
	}

	public void setVip(Integer vip) {
		this.vip = vip;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getScoreRate() {
		return scoreRate;
	}

	public void setScoreRate(Double scoreRate) {
		this.scoreRate = scoreRate;
	}

	public Double getReturnScore() {
		return returnScore;
	}

	public void setReturnScore(Double returnScore) {
		this.returnScore = returnScore;
	}

	public Double getPreRate() {
		return preRate;
	}

	public void setPreRate(Double preRate) {
		this.preRate = preRate;
	}

	public Double getReturnPre() {
		return returnPre;
	}

	public void setReturnPre(Double returnPre) {
		this.returnPre = returnPre;
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
}
