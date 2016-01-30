package com.cm.manage.model.score;

import java.io.Serializable;
import java.util.Date;

public class ScoreQutzUser implements Serializable {
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
	private Date createTime;//
	private Date updateTime;//
	private Date bonusTime;//
	public ScoreQutzUser() {

	}

	public ScoreQutzUser(Integer id, String qutzUserId, String qutzId, String userCode, String qutzIssue, String optionId, Integer optionOrder, Double amount, Double bonusAmount, Integer status,
			Date createTime, Date updateTime) {
		this.id = id;
		this.qutzUserId = qutzUserId;
		this.qutzId = qutzId;
		this.userCode = userCode;
		this.qutzIssue = qutzIssue;
		this.optionId = optionId;
		this.optionOrder = optionOrder;
		this.amount = amount;
		this.bonusAmount = bonusAmount;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

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

	public Date getBonusTime() {
		return bonusTime;
	}

	public void setBonusTime(Date bonusTime) {
		this.bonusTime = bonusTime;
	}
}
