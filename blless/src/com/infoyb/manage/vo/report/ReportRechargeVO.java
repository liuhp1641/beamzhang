package com.cm.manage.vo.report;

import java.io.Serializable;
import java.util.Date;

public class ReportRechargeVO implements Serializable {

	private static final long serialVersionUID = 5349064966516712017L;
	// Fields

	private Integer id;
	private Double zhiFuBao;
	private Double baiFuBao;
	private Double lianLianZhifu;
	private Double shenZhouFu;
	private Double xianxiRecharge;
	private String reportDate;
	private Double sumRecharge;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getZhiFuBao() {
		return this.zhiFuBao;
	}

	public void setZhiFuBao(Double zhiFuBao) {
		this.zhiFuBao = zhiFuBao;
	}

	public Double getBaiFuBao() {
		return this.baiFuBao;
	}

	public void setBaiFuBao(Double baiFuBao) {
		this.baiFuBao = baiFuBao;
	}

	public Double getLianLianZhifu() {
		return this.lianLianZhifu;
	}

	public void setLianLianZhifu(Double lianLianZhifu) {
		this.lianLianZhifu = lianLianZhifu;
	}

	public Double getShenZhouFu() {
		return this.shenZhouFu;
	}

	public void setShenZhouFu(Double shenZhouFu) {
		this.shenZhouFu = shenZhouFu;
	}

	public Double getXianxiRecharge() {
		return this.xianxiRecharge;
	}

	public void setXianxiRecharge(Double xianxiRecharge) {
		this.xianxiRecharge = xianxiRecharge;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public Double getSumRecharge() {
		return this.sumRecharge;
	}

	public void setSumRecharge(Double sumRecharge) {
		this.sumRecharge = sumRecharge;
	}


}
