package com.cm.manage.model.share;

import java.util.Date;

public class ShareUserLog {

    private Integer id;
    private String shareLogId;
    private String shareUserId;//分享用户id
    private String privateKey;//唯一标识(订单号 积分竞猜ID 活动ID)
    private String shareUrl;
    private Integer userCount;// 用户个数
    private Date createTime;

    public ShareUserLog() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getShareLogId() {
        return shareLogId;
    }

    public void setShareLogId(String shareLogId) {
        this.shareLogId = shareLogId;
    }

    public String getShareUserId() {
        return shareUserId;
    }

    public void setShareUserId(String shareUserId) {
        this.shareUserId = shareUserId;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}