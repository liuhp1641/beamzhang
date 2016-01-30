package com.cm.manage.model.report;

import java.util.Date;

/**
 * ReportWithdraw entity. @author MyEclipse Persistence Tools
 */

public class ReportWithdraw implements java.io.Serializable {

	// Fields

	private Integer id;
	private Double sumApplyWithdraw;
	private Double withholdHandlingCharge;
	private Double actualTransferAmount;
	private Date reportDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getSumApplyWithdraw() {
		return this.sumApplyWithdraw;
	}

	public void setSumApplyWithdraw(Double sumApplyWithdraw) {
		this.sumApplyWithdraw = sumApplyWithdraw;
	}

	public Double getWithholdHandlingCharge() {
		return this.withholdHandlingCharge;
	}

	public void setWithholdHandlingCharge(Double withholdHandlingCharge) {
		this.withholdHandlingCharge = withholdHandlingCharge;
	}

	public Double getActualTransferAmount() {
		return this.actualTransferAmount;
	}

	public void setActualTransferAmount(Double actualTransferAmount) {
		this.actualTransferAmount = actualTransferAmount;
	}

	public Date getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

}