package com.cm.manage.model.share;

import java.io.Serializable;
import java.util.Date;

public class ShareInfo implements Serializable {

	private Integer id;
	private String shareId;// 分享ID
	private String shareName;// 分享名称
	private String shareType;// 分享类型 //中奖 bonus 积分竞猜 scoreQutz 活动 active
	private String privateKey;// 关联id
	private String shareUrl;// 分享链接
	private String outUserCode;// 出帐资金账户
	private Integer rewardTimes;// 可完成奖励次数
	private Date startTime;
	private Date endTime;
	private Integer status;// 0未上线 1 上线 2已下线
    private String shareTitle;
    private String shareContent;
    private String shareImgUrl;
	private Date createTime;
	private Date updateTime;

	public ShareInfo() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShareName() {
		return shareName;
	}

	public void setShareName(String shareName) {
		this.shareName = shareName;
	}

	public String getShareId() {
		return shareId;
	}

	public void setShareId(String shareId) {
		this.shareId = shareId;
	}

	public String getShareType() {
		return shareType;
	}

	public void setShareType(String shareType) {
		this.shareType = shareType;
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

	public String getOutUserCode() {
		return outUserCode;
	}

	public void setOutUserCode(String outUserCode) {
		this.outUserCode = outUserCode;
	}

	public Integer getRewardTimes() {
		return rewardTimes;
	}

	public void setRewardTimes(Integer rewardTimes) {
		this.rewardTimes = rewardTimes;
	}

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareContent() {
        return shareContent;
    }

    public void setShareContent(String shareContent) {
        this.shareContent = shareContent;
    }

    public String getShareImgUrl() {
        return shareImgUrl;
    }

    public void setShareImgUrl(String shareImgUrl) {
        this.shareImgUrl = shareImgUrl;
    }

    public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}