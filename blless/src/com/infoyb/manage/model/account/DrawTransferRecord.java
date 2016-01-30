package com.cm.manage.model.account;

import java.io.Serializable;
import java.util.Date;

public class DrawTransferRecord implements Serializable{

	private static final long serialVersionUID = 1849069954324903128L;
	
	private Long id;
	private String batchNo;
	private Date createTime;
	private Date acceptTime;
	private Double totalAmount;
	private Integer totalNumber;
	private Integer status; 
	private Double successAmount;
	private Double failAmount;
	private Integer failNumber;
	private String peopleAccepted;
	private String transferFilename;
	private String finished;
	private String errorCode;//支付宝返回的错误码
	private Integer successNumber;
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getAcceptTime() {
		return acceptTime;
	}
	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Integer getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Double getSuccessAmount() {
		return successAmount;
	}
	public void setSuccessAmount(Double successAmount) {
		this.successAmount = successAmount;
	}
	public Double getFailAmount() {
		return failAmount;
	}
	public void setFailAmount(Double failAmount) {
		this.failAmount = failAmount;
	}
	public Integer getFailNumber() {
		return failNumber;
	}
	public void setFailNumber(Integer failNumber) {
		this.failNumber = failNumber;
	}
	public String getPeopleAccepted() {
		return peopleAccepted;
	}
	public void setPeopleAccepted(String peopleAccepted) {
		this.peopleAccepted = peopleAccepted;
	}
	public String getTransferFilename() {
		return transferFilename;
	}
	public void setTransferFilename(String transferFilename) {
		this.transferFilename = transferFilename;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFinished() {
		return finished;
	}
	public void setFinished(String finished) {
		this.finished = finished;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public Integer getSuccessNumber() {
		return successNumber;
	}
	public void setSuccessNumber(Integer successNumber) {
		this.successNumber = successNumber;
	}
	
	
}
