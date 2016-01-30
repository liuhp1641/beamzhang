package com.cm.manage.vo.score;

import java.io.Serializable;
import java.util.Date;

public class ScoreQutzVO implements Serializable {
	private static final long serialVersionUID = 1942874307570993602L;
	private Integer id;
	private String qutzId;// 竞猜ID
	private String qutzName;// 竞猜名称
	private String qutzTopic;// 竞猜主题
	private String qutzIssue;// 期次
	private String qutzTypeId;// 竞猜类型ID
	private Integer bonusType;// 0 能滚存 1 一次性
	private String startTime;// 开始时间
	private String endTime;// 结束时间
	private Integer vipLow;// vip等级下限 默认0
	private Integer vipHigh;// vip等级上限 默认0
	private Integer status;// 状态 0 新建 1 上线 2待公布 3暂停4 异常结束
	private Integer bonusStatus;// 0 未开 1已公布未返奖 2 已返奖
	private String sid;// 渠道 默认 00000
	private String qutzAnswerId;// 竞猜答案Id
	private String qutzAnswerNote;// 竞猜答案说明
	private Double qutzAmount;// 上期滚存金额
	private Integer attendNumbers;// 参与人数
	private Double attendAmount;// 参与金额
	private Double lastBonusAmount;// 本期滚存奖金 一次性竞猜填0
	private Date createTime;//
	private Date updateTime;//

	private Integer isHide;//是否隐藏
	private Integer isRecommend;// 是否推荐 0 不推荐 1 推荐
	private String imgUrl;// 是否推荐 0 不推荐 1 推荐
	
	private Integer isShare;// 是否能分享 0 可分享，1不可分享
	private boolean flag;
	
	private String qutzType;//竞猜类型
	
	private String startTimeStart;
	
	private String startTimeEnd;
	
	private String endTimeStart;
	
	private String endTimeEnd;
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

	

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
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

	
	public Integer getBonusType() {
		return bonusType;
	}

	public void setBonusType(Integer bonusType) {
		this.bonusType = bonusType;
	}

	public Double getQutzAmount() {
		return qutzAmount;
	}

	public void setQutzAmount(Double qutzAmount) {
		this.qutzAmount = qutzAmount;
	}

	public Double getLastBonusAmount() {
		return lastBonusAmount;
	}

	public void setLastBonusAmount(Double lastBonusAmount) {
		this.lastBonusAmount = lastBonusAmount;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getQutzType() {
		return qutzType;
	}

	public void setQutzType(String qutzType) {
		this.qutzType = qutzType;
	}

	public String getStartTimeStart() {
		return startTimeStart;
	}

	public void setStartTimeStart(String startTimeStart) {
		this.startTimeStart = startTimeStart;
	}

	public String getStartTimeEnd() {
		return startTimeEnd;
	}

	public void setStartTimeEnd(String startTimeEnd) {
		this.startTimeEnd = startTimeEnd;
	}

	public String getEndTimeStart() {
		return endTimeStart;
	}

	public void setEndTimeStart(String endTimeStart) {
		this.endTimeStart = endTimeStart;
	}

	public String getEndTimeEnd() {
		return endTimeEnd;
	}

	public void setEndTimeEnd(String endTimeEnd) {
		this.endTimeEnd = endTimeEnd;
	}

	public String getQutzTypeId() {
		return qutzTypeId;
	}

	public void setQutzTypeId(String qutzTypeId) {
		this.qutzTypeId = qutzTypeId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
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

	public Integer getBonusStatus() {
		return bonusStatus;
	}

	public void setBonusStatus(Integer bonusStatus) {
		this.bonusStatus = bonusStatus;
	}

	public String getQutzAnswerId() {
		return qutzAnswerId;
	}

	public void setQutzAnswerId(String qutzAnswerId) {
		this.qutzAnswerId = qutzAnswerId;
	}

	public String getQutzAnswerNote() {
		return qutzAnswerNote;
	}

	public void setQutzAnswerNote(String qutzAnswerNote) {
		this.qutzAnswerNote = qutzAnswerNote;
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
