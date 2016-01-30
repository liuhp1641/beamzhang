package com.cm.manage.vo.account;

import java.io.Serializable;
/**
 * @describe 用于转账列表的VO
 * @author sunjf
 *
 */
public class BatchTransferVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7247036793367125594L;
	private Long id;
	private String batchNo;
	private String createTime;
	private String acceptTime;
	private Double totalAmount;
	private Integer totalNumber;
	private Integer status; 
	private Double successAmount;
	private Double failAmount;
	private Integer failNumber;
	private String peopleAccepted;
	private String transferFilename;
	private String errorCode;//支付宝返回的错误码
	private Integer successNumber;
	private boolean flag;//是否模糊查询
	private String createStartDate;
	private String createEndDate;
	private String acceptStartDate;
	private String acceptEndDate;
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getAcceptTime() {
		return acceptTime;
	}
	public void setAcceptTime(String acceptTime) {
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
	public boolean getFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getCreateStartDate() {
		return createStartDate;
	}
	public void setCreateStartDate(String createStartDate) {
		this.createStartDate = createStartDate;
	}
	public String getCreateEndDate() {
		return createEndDate;
	}
	public void setCreateEndDate(String createEndDate) {
		this.createEndDate = createEndDate;
	}
	public String getAcceptStartDate() {
		return acceptStartDate;
	}
	public void setAcceptStartDate(String acceptStartDate) {
		this.acceptStartDate = acceptStartDate;
	}
	public String getAcceptEndDate() {
		return acceptEndDate;
	}
	public void setAcceptEndDate(String acceptEndDate) {
		this.acceptEndDate = acceptEndDate;
	}
}