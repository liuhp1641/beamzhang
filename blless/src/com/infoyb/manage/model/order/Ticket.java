package com.cm.manage.model.order;

import java.io.Serializable;
import java.util.Date;

public class Ticket implements Serializable {
	private static final long serialVersionUID = -5277193494280077954L;
	private Long id;
	private String sid;// 代理商号
	private String userCode;
	private String programsOrderId;
	private String subProgramsOrderId;
	private String orderId;
	private String ticketId;
	private String lotteryCode;
	private String playCode;
	private String pollCode;
	private String issue;
	private Integer item;
	private Integer multiple;
	private String numberInfo;
	private String userInfo;
	private String sequence;
	private String saleCode;
	private String errCode;
	private String errMsg;
	private String postCode;
	private Double amount;
	private Integer ticketStatus;
	private Integer bonusStatus;
	private Double bonusAmount;
	private Date createTime;
	private Date acceptTime;
	private String platform;
    private String softVer;
	private String bonusClass;
	private Double fixBonusAmount;


	private String backup1;
	private String backup2;
	private String backup3;

	public Ticket() {
	}

	public Ticket(String ticketId) {
		this.ticketId = ticketId;
	}

	public Ticket(String ticketId, String programsOrderId, String subProgramsOrderId, String backup1) {
		this.ticketId = ticketId;
		this.programsOrderId = programsOrderId;
		this.subProgramsOrderId = subProgramsOrderId;
		this.backup1 = backup1;
	}

	public Ticket(String ticketId, String programsOrderId, String subProgramsOrderId, String backup1, String lotteryCode, String playCode) {
		this.ticketId = ticketId;
		this.programsOrderId = programsOrderId;
		this.subProgramsOrderId = subProgramsOrderId;
		this.backup1 = backup1;
		this.lotteryCode = lotteryCode;
		this.playCode = playCode;
	}

	public Ticket(String ticketId, String programsOrderId, String subProgramsOrderId, Double bonusAmount, String bonusClass, Double fixBonusAmount) {
		this.ticketId = ticketId;
		this.programsOrderId = programsOrderId;
		this.subProgramsOrderId = subProgramsOrderId;
		this.bonusAmount = bonusAmount;
		this.bonusClass = bonusClass;
		this.fixBonusAmount = fixBonusAmount;
	}

	public Ticket(String ticketId, Double bonusAmount, String bonusClass, Double fixBonusAmount) {
		this.ticketId = ticketId;
		this.bonusAmount = bonusAmount;
		this.bonusClass = bonusClass;
		this.fixBonusAmount = fixBonusAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getProgramsOrderId() {
		return programsOrderId;
	}

	public void setProgramsOrderId(String programsOrderId) {
		this.programsOrderId = programsOrderId;
	}

	public String getSubProgramsOrderId() {
		return subProgramsOrderId;
	}

	public void setSubProgramsOrderId(String subProgramsOrderId) {
		this.subProgramsOrderId = subProgramsOrderId;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getLotteryCode() {
		return lotteryCode;
	}

	public void setLotteryCode(String lotteryCode) {
		this.lotteryCode = lotteryCode;
	}

	public String getPlayCode() {
		return playCode;
	}

	public void setPlayCode(String playCode) {
		this.playCode = playCode;
	}

	public String getPollCode() {
		return pollCode;
	}

	public void setPollCode(String pollCode) {
		this.pollCode = pollCode;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public Integer getItem() {
		return item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

	public Integer getMultiple() {
		return multiple;
	}

	public void setMultiple(Integer multiple) {
		this.multiple = multiple;
	}

	public String getNumberInfo() {
		return numberInfo;
	}

	public void setNumberInfo(String numberInfo) {
		this.numberInfo = numberInfo;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getSaleCode() {
		return saleCode;
	}

	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getTicketStatus() {
		return ticketStatus;
	}

	public void setTicketStatus(Integer ticketStatus) {
		this.ticketStatus = ticketStatus;
	}

	public Integer getBonusStatus() {
		return bonusStatus;
	}

	public void setBonusStatus(Integer bonusStatus) {
		this.bonusStatus = bonusStatus;
	}

	public Double getBonusAmount() {
		return bonusAmount;
	}

	public void setBonusAmount(Double bonusAmount) {
		this.bonusAmount = bonusAmount;
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

	public String getBonusClass() {
		return bonusClass;
	}

	public void setBonusClass(String bonusClass) {
		this.bonusClass = bonusClass;
	}

	public Double getFixBonusAmount() {
		return fixBonusAmount;
	}

	public void setFixBonusAmount(Double fixBonusAmount) {
		this.fixBonusAmount = fixBonusAmount;
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

    public String getBackup1() {
		return backup1;
	}

	public void setBackup1(String backup1) {
		this.backup1 = backup1;
	}

	public String getBackup2() {
		return backup2;
	}

	public void setBackup2(String backup2) {
		this.backup2 = backup2;
	}

	public String getBackup3() {
		return backup3;
	}

	public void setBackup3(String backup3) {
		this.backup3 = backup3;
	}
}
