package com.cm.manage.model.order;

import java.io.Serializable;
import java.util.Date;


public class AutoOrder implements Serializable {
    private static final long serialVersionUID = 1883343133134944384L;
    private Long id;
    private String sid;
    private String userCode;
    private String outOrderId;// 外部订单号
    private String autoOrderId;
    private String lotteryCode;
    private String playCode;
    private String startIssue;
    private String endIssue;
    private Integer totalIssue;// 总期数
    private Double completeAmount;
    private Integer successIssue;
    private Integer failureIssue;
    private Integer cancelIssue;
    private Integer orderStatus;// 1.进行中 2.已完成
    private Double orderAmount;
    private Integer bonusStatus;// 中奖状态(0,等待开奖,1未中奖，2,中奖)
    private Double bonusAmount;
    private Integer winStop;//0,中奖后继续，1，中奖后停止，2、中奖后多少金额停追
    private Double winAmount;//中奖后多少金额停止
    private Date createTime;// 创建时间
    private Date acceptTime;

    private String platform;
    private String softVer;
    private Double fixBonusAmount;

    private Integer isHide;//是否隐藏
    private Integer isVerify;//是否审核

    public AutoOrder() {
    }

    public AutoOrder(Long id, String sid, String userCode, String outOrderId, String autoOrderId, String lotteryCode, String playCode, String startIssue, String endIssue,
                     Integer totalIssue, Double completeAmount, Integer successIssue, Integer failureIssue, Integer cancelIssue, Integer orderStatus, Double orderAmount,
                     Integer bonusStatus, Double bonusAmount, Integer winStop, Double winAmount, Date createTime, Date acceptTime, String platform, Double fixBonusAmount,
                     String softVer, Integer isHide, Integer isVerify) {
        this.id = id;
        this.sid = sid;
        this.userCode = userCode;
        this.outOrderId = outOrderId;
        this.autoOrderId = autoOrderId;
        this.lotteryCode = lotteryCode;
        this.playCode = playCode;
        this.startIssue = startIssue;
        this.endIssue = endIssue;
        this.totalIssue = totalIssue;
        this.completeAmount = completeAmount;
        this.successIssue = successIssue;
        this.failureIssue = failureIssue;
        this.cancelIssue = cancelIssue;
        this.orderStatus = orderStatus;
        this.orderAmount = orderAmount;
        this.bonusStatus = bonusStatus;
        this.bonusAmount = bonusAmount;
        this.winStop = winStop;
        this.winAmount = winAmount;
        this.createTime = createTime;
        this.acceptTime = acceptTime;
        this.platform = platform;
        this.fixBonusAmount = fixBonusAmount;
        this.softVer = softVer;
        this.isHide = isHide;
        this.isVerify = isVerify;
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

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getAutoOrderId() {
        return autoOrderId;
    }

    public void setAutoOrderId(String autoOrderId) {
        this.autoOrderId = autoOrderId;
    }

    public String getOutOrderId() {
        return outOrderId;
    }

    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
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

    public String getStartIssue() {
        return startIssue;
    }

    public void setStartIssue(String startIssue) {
        this.startIssue = startIssue;
    }

    public String getEndIssue() {
        return endIssue;
    }

    public void setEndIssue(String endIssue) {
        this.endIssue = endIssue;
    }

    public Integer getTotalIssue() {
        return totalIssue;
    }

    public void setTotalIssue(Integer totalIssue) {
        this.totalIssue = totalIssue;
    }

    public Integer getSuccessIssue() {
        return successIssue;
    }

    public void setSuccessIssue(Integer successIssue) {
        this.successIssue = successIssue;
    }

    public Integer getFailureIssue() {
        return failureIssue;
    }

    public void setFailureIssue(Integer failureIssue) {
        this.failureIssue = failureIssue;
    }

    public Integer getCancelIssue() {
        return cancelIssue;
    }

    public void setCancelIssue(Integer cancelIssue) {
        this.cancelIssue = cancelIssue;
    }

    public Integer getWinStop() {
        return winStop;
    }

    public void setWinStop(Integer winStop) {
        this.winStop = winStop;
    }

    public Double getWinAmount() {
        return winAmount;
    }

    public void setWinAmount(Double winAmount) {
        this.winAmount = winAmount;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
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

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Double getCompleteAmount() {
        return completeAmount;
    }

    public void setCompleteAmount(Double completeAmount) {
        this.completeAmount = completeAmount;
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

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Double getFixBonusAmount() {
        return fixBonusAmount;
    }

    public void setFixBonusAmount(Double fixBonusAmount) {
        this.fixBonusAmount = fixBonusAmount;
    }

    public String getSoftVer() {
        return softVer;
    }

    public void setSoftVer(String softVer) {
        this.softVer = softVer;
    }

    public Integer getIsHide() {
        return isHide;
    }

    public void setIsHide(Integer isHide) {
        this.isHide = isHide;
    }

    public Integer getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(Integer isVerify) {
        this.isVerify = isVerify;
    }
}
