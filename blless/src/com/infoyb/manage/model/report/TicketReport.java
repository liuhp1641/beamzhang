package com.cm.manage.model.report;

import java.io.Serializable;
import java.util.Date;

public class TicketReport implements Serializable {

	private static final long serialVersionUID = 8490484167044679131L;

	private Integer id;
	private String logDay;// 记录时间
	private String postCode;//出票口
	private Double betAmount;// 投注金额
	private Double bonusAmount;// 返奖金额
	private Double partAmount;//部分成交撤单金额
	private Double noReceipeAmount;//未回执撤单金额
	private Date createTime;//创建时间
	
	public TicketReport() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getBetAmount() {
		return betAmount;
	}

	public void setBetAmount(Double betAmount) {
		this.betAmount = betAmount;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Double getBonusAmount() {
		return bonusAmount;
	}

	public void setBonusAmount(Double bonusAmount) {
		this.bonusAmount = bonusAmount;
	}

	public Double getPartAmount() {
		return partAmount;
	}

	public void setPartAmount(Double partAmount) {
		this.partAmount = partAmount;
	}

	public Double getNoReceipeAmount() {
		return noReceipeAmount;
	}

	public void setNoReceipeAmount(Double noReceipeAmount) {
		this.noReceipeAmount = noReceipeAmount;
	}

	public String getLogDay() {
		return logDay;
	}

	public void setLogDay(String logDay) {
		this.logDay = logDay;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


}
