package com.cm.manage.model.share;

import java.io.Serializable;
import java.util.Date;

public class ShareUserRecordBase implements Serializable {
	private static final long serialVersionUID = -1449898959130882477L;
	private Integer id;
	private String shareBaseId;// 分享ID
	private String shareId;// 分享ID
	private String shareUserId;// 分享用户id
	private String shareRewardId;//
	private String outUserCode;//
	private String userCode;// 分享用户编号
	private String joinUserCode;// 参与用户编号
	private String joinUserName;// 参与用户名称
	private String joinMoblie;// 参与用户手机
	private Integer accountType;// 基本奖励类型
	private Integer status;// 0 未返 1已返
	private Double amount;// 基本奖励金额
	private String lotteryCode;
	private Date joinTime;// 参与时间
	private Date createTime;
	private Date bonusTime;// 奖励时间

	public ShareUserRecordBase() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShareBaseId() {
		return shareBaseId;
	}

	public void setShareBaseId(String shareBaseId) {
		this.shareBaseId = shareBaseId;
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

	public String getOutUserCode() {
		return outUserCode;
	}

	public void setOutUserCode(String outUserCode) {
		this.outUserCode = outUserCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getJoinUserCode() {
		return joinUserCode;
	}

	public void setJoinUserCode(String joinUserCode) {
		this.joinUserCode = joinUserCode;
	}

	public String getJoinUserName() {
		return joinUserName;
	}

	public void setJoinUserName(String joinUserName) {
		this.joinUserName = joinUserName;
	}

	public String getJoinMoblie() {
		return joinMoblie;
	}

	public void setJoinMoblie(String joinMoblie) {
		this.joinMoblie = joinMoblie;
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

	public Date getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
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