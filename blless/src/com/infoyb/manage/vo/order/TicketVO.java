package com.cm.manage.vo.order;

import java.io.Serializable;

public class TicketVO implements Serializable {
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
	private String postCode;//01 量彩02 睿朗阳光 03 德彩福彩 04 德彩体彩
	private Double amount;
	private Integer ticketStatus;
	private Integer bonusStatus;
	private Double bonusAmount;
	private String createTime;
	private String acceptTime;
	private String platform;
    private String softVer;
	private String bonusClass;
	private Double fixBonusAmount;


	private String backup1;
	private String backup2;
	private String backup3;
	
	private boolean flag;
	
	private String userName;
	
	private String mobile;//手机号码
	
    private String issueMin;//奖期范围最小
    
    private String issueMax;//奖期范围最大
    
    private String createStartTime;//创建开始时间
    
    private String crateEndTime;//创建结束时间
    
    private String acceptStartTime;//处理开始时间
    
    private String acceptEndTime;//处理结束时间
    
    private Integer saleStatus;//销售状态
    
    private Integer buyType;// 购买方式(1:代购、2:合买、4:追号)
    private String sendTime;
    
	public TicketVO() {
	}

	public TicketVO(String ticketId) {
		this.ticketId = ticketId;
	}

	public TicketVO(String ticketId, String programsOrderId, String subProgramsOrderId, String backup1) {
		this.ticketId = ticketId;
		this.programsOrderId = programsOrderId;
		this.subProgramsOrderId = subProgramsOrderId;
		this.backup1 = backup1;
	}

	public TicketVO(String ticketId, String programsOrderId, String subProgramsOrderId, String backup1, String lotteryCode, String playCode) {
		this.ticketId = ticketId;
		this.programsOrderId = programsOrderId;
		this.subProgramsOrderId = subProgramsOrderId;
		this.backup1 = backup1;
		this.lotteryCode = lotteryCode;
		this.playCode = playCode;
	}

	public TicketVO(String ticketId, String programsOrderId, String subProgramsOrderId, Double bonusAmount, String bonusClass, Double fixBonusAmount) {
		this.ticketId = ticketId;
		this.programsOrderId = programsOrderId;
		this.subProgramsOrderId = subProgramsOrderId;
		this.bonusAmount = bonusAmount;
		this.bonusClass = bonusClass;
		this.fixBonusAmount = fixBonusAmount;
	}

	public TicketVO(String ticketId, Double bonusAmount, String bonusClass, Double fixBonusAmount) {
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

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIssueMin() {
		return issueMin;
	}

	public void setIssueMin(String issueMin) {
		this.issueMin = issueMin;
	}

	public String getIssueMax() {
		return issueMax;
	}

	public void setIssueMax(String issueMax) {
		this.issueMax = issueMax;
	}

	public String getCreateStartTime() {
		return createStartTime;
	}

	public void setCreateStartTime(String createStartTime) {
		this.createStartTime = createStartTime;
	}

	public String getCrateEndTime() {
		return crateEndTime;
	}

	public void setCrateEndTime(String crateEndTime) {
		this.crateEndTime = crateEndTime;
	}

	public String getAcceptStartTime() {
		return acceptStartTime;
	}

	public void setAcceptStartTime(String acceptStartTime) {
		this.acceptStartTime = acceptStartTime;
	}

	public String getAcceptEndTime() {
		return acceptEndTime;
	}

	public void setAcceptEndTime(String acceptEndTime) {
		this.acceptEndTime = acceptEndTime;
	}

	public Integer getSaleStatus() {
		return saleStatus;
	}

	public void setSaleStatus(Integer saleStatus) {
		this.saleStatus = saleStatus;
	}

	public Integer getBuyType() {
		return buyType;
	}

	public void setBuyType(Integer buyType) {
		this.buyType = buyType;
	}

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
