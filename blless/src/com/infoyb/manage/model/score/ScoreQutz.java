package com.cm.manage.model.score;

import java.io.Serializable;
import java.util.Date;

public class ScoreQutz implements Serializable {
	private static final long serialVersionUID = 1942874307570993602L;
	private Integer id;
	private String qutzId;// 竞猜ID
	private String qutzName;// 竞猜名称
	private String qutzTopic;// 竞猜主题
	private String qutzIssue;// 期次
	private String qutzTypeId;// 竞猜类型ID
	private Integer bonusType;// 0 能滚存 1 一次性
	private Date startTime;// 开始时间
	private Date endTime;// 结束时间
	private Integer vipLow;// vip等级下限 默认0
	private Integer vipHigh;// vip等级上限 默认0
	private Integer status;// 状态 0 新建 1 上线 2待公布 3暂停4 异常结束
	private Integer bonusStatus;// 0 未开 1已公布未返奖 2 已返奖
	private Double bonusAmountTotal;// 0 未开 1已公布未返奖 2 已返奖
	private String sid;// 渠道 默认 00000
	private String qutzAnswerId;// 竞猜答案Id
	private String qutzAnswerNote;// 竞猜答案说明
	private Double qutzAmount;// 上期滚存金额
	private Integer attendNumbers;// 参与人数
	private Double attendAmount;// 参与金额
	private Double lastBonusAmount;// 本期滚存奖金 一次性竞猜填0
	private Double commission;// 佣金
	private Integer isHide;// 是否隐藏
	private Integer isRecommend;// 是否推荐 0 不推荐 1 推荐
	private String imgUrl;//
	private Integer isShare;// 是否能分享 0 可分享，1不可分享
	private Date createTime;//
	private Date updateTime;//
	private Date bonusTime;//

	public ScoreQutz() {

	}

	public ScoreQutz(Integer id, String qutzId, String qutzName, String qutzTopic, String qutzIssue, String qutzTypeId, Integer bonusType, Date startTime, Date endTime, Integer vipLow,
			Integer vipHigh, Integer status, Integer bonusStatus, Double bonusAmountTotal, String sid, String qutzAnswerId, String qutzAnswerNote, Double qutzAmount, Integer attendNumbers,
			Double attendAmount, Double lastBonusAmount, Double commission, Integer isHide, Integer isRecommend, String imgUrl, Integer isShare, Date createTime, Date updateTime, Date bonusTime) {
		this.id = id;
		this.qutzId = qutzId;
		this.qutzName = qutzName;
		this.qutzTopic = qutzTopic;
		this.qutzIssue = qutzIssue;
		this.qutzTypeId = qutzTypeId;
		this.bonusType = bonusType;
		this.startTime = startTime;
		this.endTime = endTime;
		this.vipLow = vipLow;
		this.vipHigh = vipHigh;
		this.status = status;
		this.bonusStatus = bonusStatus;
		this.bonusAmountTotal = bonusAmountTotal;
		this.sid = sid;
		this.qutzAnswerId = qutzAnswerId;
		this.qutzAnswerNote = qutzAnswerNote;
		this.qutzAmount = qutzAmount;
		this.attendNumbers = attendNumbers;
		this.attendAmount = attendAmount;
		this.lastBonusAmount = lastBonusAmount;
		this.commission = commission;
		this.isHide = isHide;
		this.isRecommend = isRecommend;
		this.imgUrl = imgUrl;
		this.isShare = isShare;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.bonusTime = bonusTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQutzId() {
		return qutzId;
	}

	public void setQutzId(String qutzId) {
		this.qutzId = qutzId;
	}

	public String getQutzName() {
		return qutzName;
	}

	public void setQutzName(String qutzName) {
		this.qutzName = qutzName;
	}

	public String getQutzTopic() {
		return qutzTopic;
	}

	public void setQutzTopic(String qutzTopic) {
		this.qutzTopic = qutzTopic;
	}

	public String getQutzIssue() {
		return qutzIssue;
	}

	public void setQutzIssue(String qutzIssue) {
		this.qutzIssue = qutzIssue;
	}

	public String getQutzTypeId() {
		return qutzTypeId;
	}

	public void setQutzTypeId(String qutzTypeId) {
		this.qutzTypeId = qutzTypeId;
	}

	public Integer getBonusType() {
		return bonusType;
	}

	public void setBonusType(Integer bonusType) {
		this.bonusType = bonusType;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getBonusStatus() {
		return bonusStatus;
	}

	public Double getBonusAmountTotal() {
		return bonusAmountTotal;
	}

	public void setBonusAmountTotal(Double bonusAmountTotal) {
		this.bonusAmountTotal = bonusAmountTotal;
	}

	public void setBonusStatus(Integer bonusStatus) {
		this.bonusStatus = bonusStatus;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getQutzAnswerNote() {
		return qutzAnswerNote;
	}

	public void setQutzAnswerNote(String qutzAnswerNote) {
		this.qutzAnswerNote = qutzAnswerNote;
	}

	public Double getQutzAmount() {
		return qutzAmount;
	}

	public void setQutzAmount(Double qutzAmount) {
		this.qutzAmount = qutzAmount;
	}

	public Integer getAttendNumbers() {
		return attendNumbers;
	}

	public void setAttendNumbers(Integer attendNumbers) {
		this.attendNumbers = attendNumbers;
	}

	public Double getAttendAmount() {
		return attendAmount;
	}

	public void setAttendAmount(Double attendAmount) {
		this.attendAmount = attendAmount;
	}

	public Double getLastBonusAmount() {
		return lastBonusAmount;
	}

	public void setLastBonusAmount(Double lastBonusAmount) {
		this.lastBonusAmount = lastBonusAmount;
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

	public String getQutzAnswerId() {
		return qutzAnswerId;
	}

	public void setQutzAnswerId(String qutzAnswerId) {
		this.qutzAnswerId = qutzAnswerId;
	}

	public Date getBonusTime() {
		return bonusTime;
	}

	public void setBonusTime(Date bonusTime) {
		this.bonusTime = bonusTime;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public Integer getIsHide() {
		return isHide;
	}

	public void setIsHide(Integer isHide) {
		this.isHide = isHide;
	}

	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getIsShare() {
		return isShare;
	}

	public void setIsShare(Integer isShare) {
		this.isShare = isShare;
	}
}
