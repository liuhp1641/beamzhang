package com.cm.manage.vo.score;

import java.io.Serializable;

public class ScoreQutzUserVO implements Serializable {
	private static final long serialVersionUID = 1942874307570993602L;
	private Integer id;
	private String qutzUserId;// 竞猜ID
	private String qutzId;// 竞猜ID
	private String userCode;
	private String qutzIssue;// 期次
	private String optionId;// 选项ID
	private Integer optionOrder;// 顺序
	private Double amount;// 参与金额
	private Double bonusAmount;// 中奖金额
	private Integer status;// 状态 0 参与中 1已参与 2 已中奖 3 未中奖 4 参与失败
	private String createTime;//
	private String updateTime;//
	private String bonusTime;//
	
	private boolean flag;
	
	private String userName;
	private String qutzTypeId;
	private String qutzType;
	private String createTimeStart;
	private String createTimeEnd;
	private String updateTimeStart;
	private String updateTimeEnd;
	private Integer trueOrder;// 正答顺序
	private String trueNote;//正答
	private String optionNote;//用户选择答案
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQutzUserId() {
		return qutzUserId;
	}

	public void setQutzUserId(String qutzUserId) {
		this.qutzUserId = qutzUserId;
	}

	public String getQutzId() {
		return qutzId;
	}

	public void setQutzId(String qutzId) {
		this.qutzId = qutzId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getQutzIssue() {
		return qutzIssue;
	}

	public void setQutzIssue(String qutzIssue) {
		this.qutzIssue = qutzIssue;
	}

	public String getOptionId() {
		return optionId;
	}

	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}

	public Integer getOptionOrder() {
		return optionOrder;
	}

	public void setOptionOrder(Integer optionOrder) {
		this.optionOrder = optionOrder;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getBonusAmount() {
		return bonusAmount;
	}

	public void setBonusAmount(Double bonusAmount) {
		this.bonusAmount = bonusAmount;
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

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getQutzTypeId() {
		return qutzTypeId;
	}

	public void setQutzTypeId(String qutzTypeId) {
		this.qutzTypeId = qutzTypeId;
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

	public String getUpdateTimeStart() {
		return updateTimeStart;
	}

	public void setUpdateTimeStart(String updateTimeStart) {
		this.updateTimeStart = updateTimeStart;
	}

	public String getUpdateTimeEnd() {
		return updateTimeEnd;
	}

	public void setUpdateTimeEnd(String updateTimeEnd) {
		this.updateTimeEnd = updateTimeEnd;
	}

	public String getQutzType() {
		return qutzType;
	}

	public void setQutzType(String qutzType) {
		this.qutzType = qutzType;
	}

	public Integer getTrueOrder() {
		return trueOrder;
	}

	public void setTrueOrder(Integer trueOrder) {
		this.trueOrder = trueOrder;
	}

	public String getTrueNote() {
		return trueNote;
	}

	public void setTrueNote(String trueNote) {
		this.trueNote = trueNote;
	}

	public String getOptionNote() {
		return optionNote;
	}

	public void setOptionNote(String optionNote) {
		this.optionNote = optionNote;
	}
}
