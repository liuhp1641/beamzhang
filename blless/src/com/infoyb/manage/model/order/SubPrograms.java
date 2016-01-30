package com.cm.manage.model.order;

import java.io.Serializable;
import java.util.Date;

public class  SubPrograms implements Serializable {
    private static final long serialVersionUID = -5676973961738063964L;
    private Long id;
    private String sid;//代理商号
    private String programsOrderId;//主方案号
    private String subProgramsOrderId;//子方案号
    private String lotteryCode;//彩种编号
    private String playCode;//玩法编号
    private String pollCode;//选号方式
    private String issue;//期次
    private Integer multiple;// 倍数
    private Double amount;
    private Integer item;
    private Integer ticketCount;//票总数
    private Integer successTicket;
    private Integer failureTicket;// 失败票数
    private Integer bonusTicket;// 中奖票数
    private Integer status;//子方案状态，同programs.orderStatus部分（等待，成功，失败）
    private Integer sendStatus;//发送状态，同programs.sendStatus
    private Date createTime;//创建时间
    private Date sendTime;//发送时间
    private Date returnTime;//返回时间
    private Date bonusTime;
    private Double fixBonusAmount;//子方案税前奖金
    private Double bonusAmount;// 子方案中奖金额
    private String postCode;

    private String backup1;
    private String backup2;
    private String backup3;

    public SubPrograms() {
    }

    public SubPrograms(String subProgramsOrderId) {
        this.subProgramsOrderId = subProgramsOrderId;
    }

    public SubPrograms(String programsOrderId, String subProgramsOrderId, String lotteryCode) {
        this.programsOrderId = programsOrderId;
        this.subProgramsOrderId = subProgramsOrderId;
        this.lotteryCode = lotteryCode;
    }

    public SubPrograms(Long id, String sid, String programsOrderId, String subProgramsOrderId, String lotteryCode, String playCode, String pollCode, String issue, Integer multiple, Double amount, Integer item, Integer ticketCount, Integer successTicket, Integer failureTicket, Integer bonusTicket, Integer status, Integer sendStatus, Date createTime, Date sendTime, Date returnTime, Date bonusTime, Double fixBonusAmount, Double bonusAmount, String postCode, String backup1, String backup2, String backup3) {
        this.id = id;
        this.sid = sid;
        this.programsOrderId = programsOrderId;
        this.subProgramsOrderId = subProgramsOrderId;
        this.lotteryCode = lotteryCode;
        this.playCode = playCode;
        this.pollCode = pollCode;
        this.issue = issue;
        this.multiple = multiple;
        this.amount = amount;
        this.item = item;
        this.ticketCount = ticketCount;
        this.successTicket = successTicket;
        this.failureTicket = failureTicket;
        this.bonusTicket = bonusTicket;
        this.status = status;
        this.sendStatus = sendStatus;
        this.createTime = createTime;
        this.sendTime = sendTime;
        this.returnTime = returnTime;
        this.bonusTime = bonusTime;
        this.fixBonusAmount = fixBonusAmount;
        this.bonusAmount = bonusAmount;
        this.postCode = postCode;
        this.backup1 = backup1;
        this.backup2 = backup2;
        this.backup3 = backup3;
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

    public Integer getMultiple() {
        return multiple;
    }

    public void setMultiple(Integer multiple) {
        this.multiple = multiple;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public Integer getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(Integer ticketCount) {
        this.ticketCount = ticketCount;
    }

    public Integer getSuccessTicket() {
        return successTicket;
    }

    public void setSuccessTicket(Integer successTicket) {
        this.successTicket = successTicket;
    }

    public Integer getFailureTicket() {
        return failureTicket;
    }

    public void setFailureTicket(Integer failureTicket) {
        this.failureTicket = failureTicket;
    }

    public Integer getBonusTicket() {
        return bonusTicket;
    }

    public void setBonusTicket(Integer bonusTicket) {
        this.bonusTicket = bonusTicket;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public Date getBonusTime() {
        return bonusTime;
    }

    public void setBonusTime(Date bonusTime) {
        this.bonusTime = bonusTime;
    }

    public Double getFixBonusAmount() {
        return fixBonusAmount;
    }

    public void setFixBonusAmount(Double fixBonusAmount) {
        this.fixBonusAmount = fixBonusAmount;
    }

    public Double getBonusAmount() {
        return bonusAmount;
    }

    public void setBonusAmount(Double bonusAmount) {
        this.bonusAmount = bonusAmount;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
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
