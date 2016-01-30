package com.cm.manage.vo.share;

import java.io.Serializable;

public class ShareInfoVO implements Serializable{

    private Integer id;
    private String shareId;//分享ID
    private String shareName;//分享名称
    private String shareType;//分享类型 //中奖 bonus 积分竞猜 scoreQutz 活动 active
    private String privateKey;//关联id
    private String shareUrl;//分享链接
    private String outUserCode;//出帐资金账户
    private Integer rewardTimes;//可完成奖励次数
    private String startTime;
    private String endTime;
    private Integer status;//0未上线 1 上线
    
    private String shareTitle;
    private String shareContent;
    private String shareImgUrl;
    
    private String createTime;
    private String updateTime;

    private boolean flag;
    
    private String startTimeStart;
	
	private String startTimeEnd;
	
	private String endTimeStart;
	
	private String endTimeEnd;
	
	private Integer awardType; // 0 红包 1 积分 2 彩票
	
	private Double amount; // 彩票 注数
	
	private String outUserName;//总账账户
	
	private String isBase;//基本奖励
	
	private Integer shareAwardType;// 0 红包 1 积分 2 彩票
	private Double shareAmount;
	private Integer shareAwardMax;
	
	private String isExtra;//额外奖励
	
	private Integer joinAwardType;// 0 红包 1 积分 2 彩票
	private Double joinAmount;
	
	private Integer num;// 注册人数
	
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
   
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getStartTimeStart() {
		return startTimeStart;
	}

	public void setStartTimeStart(String startTimeStart) {
		this.startTimeStart = startTimeStart;
	}

	public String getStartTimeEnd() {
		return startTimeEnd;
	}

	public void setStartTimeEnd(String startTimeEnd) {
		this.startTimeEnd = startTimeEnd;
	}

	public String getEndTimeStart() {
		return endTimeStart;
	}

	public void setEndTimeStart(String endTimeStart) {
		this.endTimeStart = endTimeStart;
	}

	public String getEndTimeEnd() {
		return endTimeEnd;
	}

	public void setEndTimeEnd(String endTimeEnd) {
		this.endTimeEnd = endTimeEnd;
	}

	public Integer getAwardType() {
		return awardType;
	}

	public void setAwardType(Integer awardType) {
		this.awardType = awardType;
	}

	public String getOutUserName() {
		return outUserName;
	}

	public void setOutUserName(String outUserName) {
		this.outUserName = outUserName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	

	public String getIsBase() {
		return isBase;
	}

	public void setIsBase(String isBase) {
		this.isBase = isBase;
	}

	public String getIsExtra() {
		return isExtra;
	}

	public void setIsExtra(String isExtra) {
		this.isExtra = isExtra;
	}

	public Integer getShareAwardType() {
		return shareAwardType;
	}

	public void setShareAwardType(Integer shareAwardType) {
		this.shareAwardType = shareAwardType;
	}

	public Double getShareAmount() {
		return shareAmount;
	}

	public void setShareAmount(Double shareAmount) {
		this.shareAmount = shareAmount;
	}

	public Integer getShareAwardMax() {
		return shareAwardMax;
	}

	public void setShareAwardMax(Integer shareAwardMax) {
		this.shareAwardMax = shareAwardMax;
	}

	public Integer getJoinAwardType() {
		return joinAwardType;
	}

	public void setJoinAwardType(Integer joinAwardType) {
		this.joinAwardType = joinAwardType;
	}

	public Double getJoinAmount() {
		return joinAmount;
	}

	public void setJoinAmount(Double joinAmount) {
		this.joinAmount = joinAmount;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
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
	
}