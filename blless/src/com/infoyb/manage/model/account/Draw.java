package com.cm.manage.model.account;

import java.io.Serializable;
import java.util.Date;

/**
 * User: 邓玉明 Date: 11-3-27 下午4:38
 */
public class Draw implements Serializable {

	private static final long serialVersionUID = -269890018695198718L;
	private Long id;
	private String userCode;
	private String orderId;
	private String drawResources;// 提现类型
	private String drawInfo;// 提现信息
	private Double amount;
	private Date createTime;
	private Date acceptTime;
	private Integer status;
	private Double fee;
	private Double realFee;
	private String memo;
	private String peopleAccepted;// 受理人
	private String peopleRemittance;// 汇款人
	private Integer type; // t+0 0 t+1 1
	private String sid;
	private String platform;
	private String softVer;

	private Double drawLimit;
	private Double bonusAmountNew;//
    private String transferBatchId; //转账批次号
    private String errorMsg;//支付宝错误描述信息
    private String alipayDrawNo;//支付宝内部体现流水号

    public String getPeopleAccepted() {
		return peopleAccepted;
	}

	public String getPeopleRemittance() {
		return peopleRemittance;
	}

	public void setPeopleAccepted(String peopleAccepted) {
		this.peopleAccepted = peopleAccepted;
	}

	public void setPeopleRemittance(String peopleRemittance) {
		this.peopleRemittance = peopleRemittance;
	}

	public Draw() {
	}

    public Draw(String userCode, String orderId, String drawResources, String drawInfo, Double amount, Date createTime, Date acceptTime, Integer status, Double fee, Double realFee,String memo, String peopleAccepted, String peopleRemittance, Integer type, String sid, String platform, String softVer, Double bonusAmountNew, String transferBatchId) {
        this.userCode = userCode;
        this.orderId = orderId;
        this.drawResources = drawResources;
        this.drawInfo = drawInfo;
        this.amount = amount;
        this.createTime = createTime;
        this.acceptTime = acceptTime;
        this.status = status;
        this.fee = fee;
        this.realFee = realFee;
        this.memo = memo;
        this.peopleAccepted = peopleAccepted;
        this.peopleRemittance = peopleRemittance;
        this.type = type;
        this.sid = sid;
        this.platform = platform;
        this.softVer = softVer;
        this.bonusAmountNew = bonusAmountNew;
        this.transferBatchId = transferBatchId;
    }

    public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getDrawResources() {
		return drawResources;
	}

	public void setDrawResources(String drawResources) {
		this.drawResources = drawResources;
	}

	public String getDrawInfo() {
		return drawInfo;
	}

	public void setDrawInfo(String drawInfo) {
		this.drawInfo = drawInfo;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getSoftVer() {
		return softVer;
	}

	public void setSoftVer(String softVer) {
		this.softVer = softVer;
	}

	public Double getBonusAmountNew() {
		return bonusAmountNew;
	}

	public void setBonusAmountNew(Double bonusAmountNew) {
		this.bonusAmountNew = bonusAmountNew;
	}

    public String getTransferBatchId() {
        return transferBatchId;
    }

    public void setTransferBatchId(String transferBatchId) {
        this.transferBatchId = transferBatchId;
    }

	public Double getRealFee() {
		return realFee;
	}

	public void setRealFee(Double realFee) {
		this.realFee = realFee;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getAlipayDrawNo() {
		return alipayDrawNo;
	}

	public void setAlipayDrawNo(String alipayDrawNo) {
		this.alipayDrawNo = alipayDrawNo;
	}

	public Double getDrawLimit() {
		return drawLimit;
	}

	public void setDrawLimit(Double drawLimit) {
		this.drawLimit = drawLimit;
	}
	
    
    
}
