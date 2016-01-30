package com.cm.account.http.bean;

import java.io.Serializable;

public class IncomingOperationBean implements Serializable {

    private static final long serialVersionUID = 6978491786230678785L;

    public static final String R00500 = "0000400";// 提现失败

    private String userCode;
    private String resources;//处理来源
    private String orderId;//订单号(合买保底使用方案号）
    private Double amount;//金额
    private String eventCode;//操作业务码(001 充值 )
    private String memo;

    public IncomingOperationBean() {

    }

    public IncomingOperationBean(String userCode, String resources, String orderId, Double amount, String eventCode, String memo) {
        this.userCode = userCode;
        this.resources = resources;
        this.orderId = orderId;
        this.amount = amount;
        this.eventCode = eventCode;
        this.memo = memo;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
