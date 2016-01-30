package com.cm.manage.model.order;

import java.io.Serializable;
import java.util.Date;


public class Order implements Serializable {
    private static final long serialVersionUID = 1565374566573659302L;
    private Long id;
    private String outOrderId;// 外部订单号
    private String sid;
    private String userCode;
    private String presentedUserCode;//赠送人
    private String userInfo;
    private String orderId;
    private String programsOrderId;
    private Integer orderStatus;//主要合买撤次使用
    private Integer orderType;//0，普通，1、发起合买认购，2、合买认购，3、自动跟单，4、保底转认购，5，系统保底，6、先发起后上传，7、单式上传
    private Double orderAmount;
    private Integer buyWere;//购买份数
    private Integer bonusStatus;
    private Double bonusAmount;
    private Date createTime;
    private Date acceptTime;
    private String platform;
    private String softVer;
    private Double fixBonusAmount;
    private Integer isHide;//是否隐藏
    private Integer isVerify;//是否审核

    public Order() {
    }

    public Order(Long id, String outOrderId, String sid, String userCode, String presentedUserCode, String userInfo, String orderId,
                 String programsOrderId, Integer orderStatus, Integer orderType, Double orderAmount, Integer bonusStatus, String softVer,
                 Double bonusAmount, Date createTime, Date acceptTime, String platform, Double realBonusAmount, Integer buyWere, Integer isHide, Integer isVerify) {
        this.id = id;
        this.outOrderId = outOrderId;
        this.sid = sid;
        this.userCode = userCode;
        this.presentedUserCode = presentedUserCode;
        this.userInfo = userInfo;
        this.orderId = orderId;
        this.programsOrderId = programsOrderId;
        this.orderStatus = orderStatus;
        this.orderType = orderType;
        this.orderAmount = orderAmount;
        this.bonusStatus = bonusStatus;
        this.bonusAmount = bonusAmount;
        this.createTime = createTime;
        this.acceptTime = acceptTime;
        this.platform = platform;
        this.softVer = softVer;
        this.fixBonusAmount = realBonusAmount;
        this.buyWere = buyWere;
        this.isHide = isHide;
        this.isVerify = isVerify;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOutOrderId() {
        return outOrderId;
    }

    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
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

    public String getPresentedUserCode() {
        return presentedUserCode;
    }

    public void setPresentedUserCode(String presentedUserCode) {
        this.presentedUserCode = presentedUserCode;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProgramsOrderId() {
        return programsOrderId;
    }

    public void setProgramsOrderId(String programsOrderId) {
        this.programsOrderId = programsOrderId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
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


    public Integer getBuyWere() {
        return buyWere;
    }

    public void setBuyWere(Integer buyWere) {
        this.buyWere = buyWere;
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
