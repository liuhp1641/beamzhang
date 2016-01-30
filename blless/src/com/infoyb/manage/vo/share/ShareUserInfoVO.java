package com.cm.manage.vo.share;


public class ShareUserInfoVO {

    private Integer id;
    private String shareId;//分享ID
    private String shareName;//分享名称
    private String shareType;//分享类型
    private String shareUserId;//分享用户id
    private String privateKey;//唯一标识(订单号 积分竞猜ID 活动ID)
    private String userCode;//用户编号
    private Integer userCount;//用户个数
    private Integer accountType;//基本奖励类型
    private Double amount;//基本奖励金额

    private Double extraAmount; //额外红包
    private Integer extraLot; //额外彩票
    private Double extraScore; //额外积分

    private String shareUrl;
    private String createTime;
    private String updateTime;
    private String bonusTime;//奖励时间
    
    private boolean flag;
    
    private String userName;//分享人
    
    private String createTimeStart;
	private String createTimeEnd;
	
	private String bonusTimeStart;
	private String bonusTimeEnd;

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

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

   

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
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

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getExtraAmount() {
        return extraAmount;
    }

    public void setExtraAmount(Double extraAmount) {
        this.extraAmount = extraAmount;
    }

    public Integer getExtraLot() {
        return extraLot;
    }

    public void setExtraLot(Integer extraLot) {
        this.extraLot = extraLot;
    }

    public Double getExtraScore() {
        return extraScore;
    }

    public void setExtraScore(Double extraScore) {
        this.extraScore = extraScore;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
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

	public String getBonusTime() {
		return bonusTime;
	}

	public void setBonusTime(String bonusTime) {
		this.bonusTime = bonusTime;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getBonusTimeStart() {
		return bonusTimeStart;
	}

	public void setBonusTimeStart(String bonusTimeStart) {
		this.bonusTimeStart = bonusTimeStart;
	}

	public String getBonusTimeEnd() {
		return bonusTimeEnd;
	}

	public void setBonusTimeEnd(String bonusTimeEnd) {
		this.bonusTimeEnd = bonusTimeEnd;
	}
}