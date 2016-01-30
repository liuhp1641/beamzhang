package com.cm.manage.vo.share;

import java.io.Serializable;

public class ShareUserRecordExtraVO implements Serializable {
	private static final long serialVersionUID = -1449898959130882477L;
	private Integer id;
	private String shareExtraId;// 分享ID
	private String shareId;// 分享ID
	private String shareUserId;// 分享用户id
	private String shareRewardId;
	private String userCode;
	private String outUserCode;
	private Integer num;// 数量
	private Integer joinNum;// 参与数量
	private Integer accountType;// 奖励类型
	private Double amount;// 奖励金额
	private String lotteryCode;
	private Integer status;// 0未返 1已返
	private String createTime;
	private String bonusTime;// 奖励时间

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShareExtraId() {
		return shareExtraId;
	}

	public void setShareExtraId(String shareExtraId) {
		this.shareExtraId = shareExtraId;
	}

	public String getShareId() {
		return shareId;
	}

	public void setShareId(String shareId) {
		this.shareId = shareId;
	}

	public String getShareUserId() {
		return shareUserId;
	}

	public void setShareUserId(String shareUserId) {
		this.shareUserId = shareUserId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getOutUserCode() {
		return outUserCode;
	}

	public void setOutUserCode(String outUserCode) {
		this.outUserCode = outUserCode;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getJoinNum() {
		return joinNum;
	}

	public void setJoinNum(Integer joinNum) {
		this.joinNum = joinNum;
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

	public String getShareRewardId() {
		return shareRewardId;
	}

	public void setShareRewardId(String shareRewardId) {
		this.shareRewardId = shareRewardId;
	}

	public String getLotteryCode() {
		return lotteryCode;
	}

	public void setLotteryCode(String lotteryCode) {
		this.lotteryCode = lotteryCode;
	}

}