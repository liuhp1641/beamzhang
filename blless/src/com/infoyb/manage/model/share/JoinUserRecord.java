package com.cm.manage.model.share;

import java.io.Serializable;
import java.util.Date;

public class JoinUserRecord implements Serializable {
	private static final long serialVersionUID = -5599938464215707141L;
	private Integer id;
	private String joinUserId;// 参与用户ID
	private String shareUserId;// 分享用户ID
	private String shareId;// 分享id
	private String userCode;
	private String userName;
	private String mobile;
	private Integer accountType;// 资金类型
	private Double amount;// 金额
	private Integer status; // 状态 默认 0 未返奖 1 已返奖
	private Date createTime;
	private Date bonusTime;// 奖励时间

	public JoinUserRecord() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJoinUserId() {
		return joinUserId;
	}

	public void setJoinUserId(String joinUserId) {
		this.joinUserId = joinUserId;
	}

	public String getShareUserId() {
		return shareUserId;
	}

	public void setShareUserId(String shareUserId) {
		this.shareUserId = shareUserId;
	}

	public String getShareId() {
		return shareId;
	}

	public void setShareId(String shareId) {
		this.shareId = shareId;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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

	public Date getBonusTime() {
		return bonusTime;
	}

	public void setBonusTime(Date bonusTime) {
		this.bonusTime = bonusTime;
	}
}