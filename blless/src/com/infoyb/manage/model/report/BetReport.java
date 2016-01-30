package com.cm.manage.model.report;

import java.io.Serializable;
import java.util.Date;

public class BetReport implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2295573874765901911L;

	private Integer id;

	private String logDay;// 记录时间
	private Double betAmount;// 投注金额
	private Double ticketAmount;// 出票金额
	
	private Date createTime;//创建时间
	public BetReport() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogDay() {
		return logDay;
	}

	public void setLogDay(String logDay) {
		this.logDay = logDay;
	}

	public Double getBetAmount() {
		return betAmount;
	}

	public void setBetAmount(Double betAmount) {
		this.betAmount = betAmount;
	}

	public Double getTicketAmount() {
		return ticketAmount;
	}

	public void setTicketAmount(Double ticketAmount) {
		this.ticketAmount = ticketAmount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
