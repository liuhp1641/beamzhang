package com.cm.manage.model.account;

import java.io.Serializable;
import java.util.Date;

/**
 * User: 邓玉明
 * Date: 11-3-27 下午4:26
 */
public class Fill implements Serializable {


    private static final long serialVersionUID = -2708128804776448543L;
    private Long id;
    private String userCode;
    private String orderId;//充值订单号
    private String outOrderId;//外部订单号
    private String fillResources;//充值来源(支付宝)
    private Double amount;//充值金额
    private Double realAmount;//到帐金额
    private Date createTime;//充值金额
    private Date acceptTime;//受理时间
    private Integer status;//充值状态(0,等待，1,成功，2,失败)
    private String sid;//渠道号
    private String platform;//平台
    private String softVer;//软件版本
    private Double rechargeAmountNew;
    private String memo;//说明


    public Fill(String orderId, Integer status) {
        this.orderId = orderId;
        this.status = status;
    }

    public Fill() {
    }

    public Fill(Long id, String userCode, String orderId, String outOrderId, String fillResources, Double amount, Double realAmount, Date createTime, Date acceptTime, Integer status, String sid, String platform, String softVer, Double rechargeAmountNew, String memo) {
        this.id = id;
        this.userCode = userCode;
        this.orderId = orderId;
        this.outOrderId = outOrderId;
        this.fillResources = fillResources;
        this.amount = amount;
        this.realAmount = realAmount;
        this.createTime = createTime;
        this.acceptTime = acceptTime;
        this.status = status;
        this.sid = sid;
        this.platform = platform;
        this.softVer = softVer;
        this.rechargeAmountNew = rechargeAmountNew;
        this.memo = memo;
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

    public String getOutOrderId() {
        return outOrderId;
    }

    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
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

    public String getFillResources() {
        return fillResources;
    }

    public void setFillResources(String fillResources) {
        this.fillResources = fillResources;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(Double realAmount) {
        this.realAmount = realAmount;
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

    public String getSoftVer() {
        return softVer;
    }

    public void setSoftVer(String softVer) {
        this.softVer = softVer;
    }

    public Double getRechargeAmountNew() {
        return rechargeAmountNew;
    }

    public void setRechargeAmountNew(Double rechargeAmountNew) {
        this.rechargeAmountNew = rechargeAmountNew;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
