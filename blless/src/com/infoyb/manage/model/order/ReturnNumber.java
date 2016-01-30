package com.cm.manage.model.order;

import java.io.Serializable;
import java.util.Date;

public class ReturnNumber implements Serializable {
    private static final long serialVersionUID = 5145605611522653620L;
    private Long id;
    private String lotteryCode;
    private String issue;
    private String userCode;
    private String programsOrderId;
    private String subProgramsOrderId;
    private String ticketId;
    private String numberInfo;//号码信息
    private Double bonusAmount;//税后奖金
    private Date createTime;
    private Double fixBonusAmount;//税前奖金
    private String bonusClass;//中奖级别
    private Integer bonusStatus;// 中奖状态(0,等待开奖,1中奖，2,未中奖)


    public ReturnNumber() {
    }

    public ReturnNumber(Long id, String lotteryCode, String issue, String userCode, String programsOrderId, String subProgramsOrderId, String ticketId, String numberInfo, Double bonusAmount, Date createTime, Double fixBonusAmount, String bonusClass, Integer bonusStatus) {
        this.id = id;
        this.lotteryCode = lotteryCode;
        this.issue = issue;
        this.userCode = userCode;
        this.programsOrderId = programsOrderId;
        this.subProgramsOrderId = subProgramsOrderId;
        this.ticketId = ticketId;
        this.numberInfo = numberInfo;
        this.bonusAmount = bonusAmount;
        this.createTime = createTime;
        this.fixBonusAmount = fixBonusAmount;
        this.bonusClass = bonusClass;
        this.bonusStatus = bonusStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLotteryCode() {
        return lotteryCode;
    }

    public void setLotteryCode(String lotteryCode) {
        this.lotteryCode = lotteryCode;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
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

    public String getNumberInfo() {
        return numberInfo;
    }

    public void setNumberInfo(String numberInfo) {
        this.numberInfo = numberInfo;
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

    public Double getFixBonusAmount() {
        return fixBonusAmount;
    }

    public void setFixBonusAmount(Double fixBonusAmount) {
        this.fixBonusAmount = fixBonusAmount;
    }

    public String getBonusClass() {
        return bonusClass;
    }

    public void setBonusClass(String bonusClass) {
        this.bonusClass = bonusClass;
    }

    public Integer getBonusStatus() {
        return bonusStatus;
    }

    public void setBonusStatus(Integer bonusStatus) {
        this.bonusStatus = bonusStatus;
    }
}
