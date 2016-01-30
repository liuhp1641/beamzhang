package com.cm.manage.model.share;

import java.io.Serializable;
import java.util.Date;

/**
 * 参与人奖励
 */
public class JoinReward implements Serializable {
	private static final long serialVersionUID = 5201963064161201599L;
	private Integer id;
	private String shareId;// 分享ID
	private String outUserCode;// 出帐资金账户
	private Integer awardType; // 0 红包 1 积分 2 彩票
	private String lotteryCode;
	private Double amount;
	private Date createTime;

	public JoinReward() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getAwardType() {
		return awardType;
	}

	public void setAwardType(Integer awardType) {
		this.awardType = awardType;
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