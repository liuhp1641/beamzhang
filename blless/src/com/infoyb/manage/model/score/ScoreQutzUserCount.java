package com.cm.manage.model.score;

import java.io.Serializable;
import java.util.Date;

public class ScoreQutzUserCount implements Serializable {
	private static final long serialVersionUID = 1942874307570993602L;
	private Integer id;
	private String qutzId;// 竞猜ID
	private String userCode;
	private Integer attendOptions;// 参与选项次数
	private Double amount;// 参与金额
	private Double bonusAmount;// 中奖金额
	private String firstOptionId;// 首次参与选项
	private String firstOptionNote;// 首次参与选项内容
	private Date createTime;//
	private Date updateTime;//

	public ScoreQutzUserCount() {

	}

	public ScoreQutzUserCount(Integer id, String qutzId, String userCode, Integer attendOptions, Double amount, Double bonusAmount, String firstOptionId, String firstOptionNote, Date createTime,
			Date updateTime) {
		this.id = id;
		this.qutzId = qutzId;
		this.userCode = userCode;
		this.attendOptions = attendOptions;
		this.amount = amount;
		this.bonusAmount = bonusAmount;
		this.firstOptionId = firstOptionId;
		this.firstOptionNote = firstOptionNote;
		this.createTime = createTime;
		this.updateTime = updateTime;
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

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Integer getAttendOptions() {
		return attendOptions;
	}

	public void setAttendOptions(Integer attendOptions) {
		this.attendOptions = attendOptions;
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

	public String getFirstOptionId() {
		return firstOptionId;
	}

	public void setFirstOptionId(String firstOptionId) {
		this.firstOptionId = firstOptionId;
	}

	public String getFirstOptionNote() {
		return firstOptionNote;
	}

	public void setFirstOptionNote(String firstOptionNote) {
		this.firstOptionNote = firstOptionNote;
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
