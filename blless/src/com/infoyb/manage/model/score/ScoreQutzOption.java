package com.cm.manage.model.score;

import java.io.Serializable;
import java.util.Date;

public class ScoreQutzOption implements Serializable {
	private static final long serialVersionUID = -7261282243899004565L;
	private Integer id;
	private String qutzId;// 竞猜ID
	private String optionId;// 选项ID
	private Integer optionOrder;// 顺序
	private String optionNote;// 选项内容
	private Integer attendNumbers;// 参与人数
	private Double attendAmount;// 参与金额
	private Date createTime;//
	private Date updateTime;//

	public ScoreQutzOption() {

	}

	public ScoreQutzOption(Integer id, String qutzId, String optionId, Integer optionOrder, String optionNote, Integer attendNumbers, Double attendAmount, Date createTime, Date updateTime) {
		this.id = id;
		this.qutzId = qutzId;
		this.optionId = optionId;
		this.optionOrder = optionOrder;
		this.optionNote = optionNote;
		this.attendNumbers = attendNumbers;
		this.attendAmount = attendAmount;
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

	public String getOptionNote() {
		return optionNote;
	}

	public void setOptionNote(String optionNote) {
		this.optionNote = optionNote;
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
}
