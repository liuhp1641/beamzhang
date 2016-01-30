package com.cm.manage.model.share;

import java.util.Date;

public class ShareUserInfo {

	private Integer id;
	private String shareId;// 分享ID
	private String shareName;// 分享名称
	private String shareType;// 分享类型
	private String shareUserId;// 分享用户id
	private String privateKey;// 唯一标识(订单号 积分竞猜ID 活动ID)
	private String userCode;// 用户编号
	private Integer userCount;// 用户个数
	private Integer accountType;// 基本奖励类型
	private Double amount;// 基本奖励金额
	private String lotteryCode;

	private Double extraAmount; // 额外红包
	private Integer extraLot; // 额外彩票
	private Double extraScore; // 额外积分

	private String shareUrl;
	private Date createTime;
	private Date updateTime;
	private Date bonusTime;// 奖励时间

	public ShareUserInfo() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShareName() {
		return shareName;
	}

	public void setShareName(String shareName) {
		this.shareName = shareName;
	}

	public String getShareType() {
		return shareType;
	}

	public void setShareType(String shareType) {
		this.shareType = shareType;
	}

	public Date getBonusTime() {
		return bonusTime;
	}

	public void setBonusTime(Date bonusTime) {
		this.bonusTime = bonusTime;
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

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Integer getUserCount() {
		return userCount;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
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

	public String getLotteryCode() {
		return lotteryCode;
	}

	public void setLotteryCode(String lotteryCode) {
		this.lotteryCode = lotteryCode;
	}

	public Double getExtraAmount() {
		return extraAmount;
	}

	public void setExtraAmount(Double extraAmount) {
		this.extraAmount = extraAmount;
	}

	public Integer getExtraLot() {
		return extraLot;
	}

	public void setExtraLot(Integer extraLot) {
		this.extraLot = extraLot;
	}

	public Double getExtraScore() {
		return extraScore;
	}

	public void setExtraScore(Double extraScore) {
		this.extraScore = extraScore;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
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