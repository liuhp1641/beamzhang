package com.cm.manage.model.report;

import java.util.Date;


/**
 * ReportRecharge entity. @author MyEclipse Persistence Tools
 */

public class ReportRecharge  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Double zhiFuBao;
     private Double baiFuBao;
     private Double lianLianZhifu;
     private Double shenZhouFu;
     private Double xianxiRecharge;
     private Date reportDate;
     private Double sumRecharge;
     private Double zhiFuBaoWap;
     private Double baiFuBaoWap;




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

    public Date getReportDate() {
        return this.reportDate;
    }
    
    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Double getSumRecharge() {
        return this.sumRecharge;
    }
    
    public void setSumRecharge(Double sumRecharge) {
        this.sumRecharge = sumRecharge;
    }


	public Double getZhiFuBaoWap() {
		return zhiFuBaoWap;
	}


	public void setZhiFuBaoWap(Double zhiFuBaoWap) {
		this.zhiFuBaoWap = zhiFuBaoWap;
	}


	public Double getBaiFuBaoWap() {
		return baiFuBaoWap;
	}


	public void setBaiFuBaoWap(Double baiFuBaoWap) {
		this.baiFuBaoWap = baiFuBaoWap;
	}
   

}