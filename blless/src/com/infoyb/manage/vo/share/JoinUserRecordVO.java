package com.cm.manage.vo.share;

import java.io.Serializable;

public class JoinUserRecordVO implements Serializable {
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
	private String createTime;
	private String bonusTime;// 奖励时间
	
	private boolean flag;
	private String shareUserCode;//分享人编号
	private String shareUserName;//分享人
	private String createTimeStart;
	private String createTimeEnd;
	
	private String bonusTimeStart;
	private String bonusTimeEnd;
	
	private String shareType;//分享类型 //中奖 bonus 积分竞猜 scoreQutz 活动 active
	
	 private String shareName;//分享名称

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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getBonusTime() {
		return bonusTime;
	}

	public void setBonusTime(String bonusTime) {
		this.bonusTime = bonusTime;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getShareUserName() {
		return shareUserName;
	}

	public void setShareUserName(String shareUserName) {
		this.shareUserName = shareUserName;
	}

	public String getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getBonusTimeStart() {
		return bonusTimeStart;
	}

	public void setBonusTimeStart(String bonusTimeStart) {
		this.bonusTimeStart = bonusTimeStart;
	}

	public String getBonusTimeEnd() {
		return bonusTimeEnd;
	}

	public void setBonusTimeEnd(String bonusTimeEnd) {
		this.bonusTimeEnd = bonusTimeEnd;
	}

	public String getShareType() {
		return shareType;
	}

	public void setShareType(String shareType) {
		this.shareType = shareType;
	}

	public String getShareName() {
		return shareName;
	}

	public void setShareName(String shareName) {
		this.shareName = shareName;
	}

	public String getShareUserCode() {
		return shareUserCode;
	}

	public void setShareUserCode(String shareUserCode) {
		this.shareUserCode = shareUserCode;
	}
	
	
}