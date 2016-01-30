package com.cm.manage.vo.report;

import java.io.Serializable;
import java.util.Date;

public class ReportWithdrawVO implements Serializable {

	private static final long serialVersionUID = 5349064966516712017L;
	// Fields

	private Integer id;
	private Double sumApplyWithdraw;
	private Double withholdHandlingCharge;
	private Double actualTransferAmount;
	private String reportDate;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getSumApplyWithdraw() {
		return sumApplyWithdraw;
	}

	public void setSumApplyWithdraw(Double sumApplyWithdraw) {
		this.sumApplyWithdraw = sumApplyWithdraw;
	}

	public Double getWithholdHandlingCharge() {
		return withholdHandlingCharge;
	}

	public void setWithholdHandlingCharge(Double withholdHandlingCharge) {
		this.withholdHandlingCharge = withholdHandlingCharge;
	}

	public Double getActualTransferAmount() {
		return actualTransferAmount;
	}

	public void setActualTransferAmount(Double actualTransferAmount) {
		this.actualTransferAmount = actualTransferAmount;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}


}
