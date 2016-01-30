package com.cm.manage.model.share;

import java.io.Serializable;
import java.util.Date;

/**
 * 分享人奖励
 */
public class ShareReward implements Serializable {
	private static final long serialVersionUID = 166854863125527107L;
	private Integer id;
	private String shareRewardId;// 分享ID
	private String shareId;// 分享ID
	private String outUserCode;// 出帐资金账户
	private Integer baseType; // 0 基本奖励 1 额外奖励
	private Integer awardType; // 0 红包 1 积分 2 彩票
	private Integer num;// 注册人数
	private Integer awardMax;// 奖励上限
	private String lotteryCode;// 彩种编码
	private Double amount; // 彩票 注数
	private Date createTime;

	public ShareReward() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShareRewardId() {
		return shareRewardId;
	}

	public void setShareRewardId(String shareRewardId) {
		this.shareRewardId = shareRewardId;
	}

	public String getShareId() {
		return shareId;
	}

	public void setShareId(String shareId) {
		this.shareId = shareId;
	}

	public String getOutUserCode() {
		return outUserCode;
	}

	public void setOutUserCode(String outUserCode) {
		this.outUserCode = outUserCode;
	}

	public Integer getBaseType() {
		return baseType;
	}

	public void setBaseType(Integer baseType) {
		this.baseType = baseType;
	}

	public Integer getAwardType() {
		return awardType;
	}

	public void setAwardType(Integer awardType) {
		this.awardType = awardType;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getAwardMax() {
		return awardMax;
	}

	public void setAwardMax(Integer awardMax) {
		this.awardMax = awardMax;
	}

	public String getLotteryCode() {
		return lotteryCode;
	}

	public void setLotteryCode(String lotteryCode) {
		this.lotteryCode = lotteryCode;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}