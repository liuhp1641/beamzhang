package com.cm.manage.model.order;

import java.io.Serializable;
import java.util.Date;

public class HandworkBonus implements Serializable {

    private static final long serialVersionUID = -4177476075944412446L;

    private Long id;
    private String lotteryCode;
    private String issue;
    private String userCode;
    private String userName;
    private String programsOrderId;
    private Double orderAmount;
    private Double bonusAmount;//返奖金额
    private Double fixBonusAmount;
    private Integer bigBonus;
    private Integer status;//返奖状态(0,等待手工处理;1,已处理 2 取消处理)
    private Date createTime;//创建时间
    private Date acceptTime;// 处理时间

    //操作人
    private String operator;


    public HandworkBonus() {
    }

    public HandworkBonus(Long id, String lotteryCode, String issue, String userCode, String userName, String operator, String programsOrderId, Double orderAmount, Double bonusAmount, Double fixBonusAmount, Integer bigBonus, Integer status, Date createTime, Date acceptTime) {
        this.id = id;
        this.lotteryCode = lotteryCode;
        this.issue = issue;
        this.userCode = userCode;
        this.userName = userName;
        this.programsOrderId = programsOrderId;
        this.orderAmount = orderAmount;
        this.bonusAmount = bonusAmount;
        this.fixBonusAmount = fixBonusAmount;
        this.bigBonus = bigBonus;
        this.status = status;
        this.createTime = createTime;
        this.acceptTime = acceptTime;
        this.operator = operator;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProgramsOrderId() {
        return programsOrderId;
    }

    public void setProgramsOrderId(String programsOrderId) {
        this.programsOrderId = programsOrderId;
    }

    public Double getBonusAmount() {
        return bonusAmount;
    }

    public void setBonusAmount(Double bonusAmount) {
        this.bonusAmount = bonusAmount;
    }

    public Double getFixBonusAmount() {
        return fixBonusAmount;
    }

    public void setFixBonusAmount(Double fixBonusAmount) {
        this.fixBonusAmount = fixBonusAmount;
    }

    public Integer getBigBonus() {
        return bigBonus;
    }

    public void setBigBonus(Integer bigBonus) {
        this.bigBonus = bigBonus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
