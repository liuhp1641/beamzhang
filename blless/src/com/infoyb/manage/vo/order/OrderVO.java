package com.cm.manage.vo.order;

import java.io.Serializable;


public class OrderVO implements Serializable {
	private static final long serialVersionUID = 8255567122123969988L;
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
    private String createTime;
    private String acceptTime;
    private String platform;
    private String softVer;
    private Double fixBonusAmount;
    private Integer isHide;//是否隐藏
    private Integer isVerify;//是否审核
    private String description;// 方案宣言
    private String backup1;
    private String backup2;
    private String backup3;
    
    private String issue;//期次
    private String playCode;//玩法编号
    
    private Integer multiple;// 倍数
    
    private Integer bonusToAccount;//是否派奖(0,1)    
    
    private String bonusNumber;//开奖号码
    
    //查询条件
    
    private boolean flag;//是否模糊查询
    
    private String userName;//用户名
    
    private String mobile;//手机号码

    private String lotteryCode;//彩种
    
    private String issueMin;//奖期最小
    
    private String issueMax;//奖期最大
    
    private String trackUserCode;//所属用户
    
    private String createStartDate;//投注开始时间
    
    private String createEndDate;//投注结束时间
    
    private Double amountMin;//投注最小
    
    private Double amountMax;//投注最大
    
    private Double bonusMin;//中奖最小
    
    private Double bonusMax;//中奖最大
    
    private Double buyProgress;//购买进度
    
    private Double lastProgress;//保底进度
    
    private Integer buyType;// 购买方式(1:代购、2:合买、4:追号)
    
    private String numberInfo;// 号码信息json
    
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

    public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(String acceptTime) {
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

	public String getLotteryCode() {
		return lotteryCode;
	}

	public void setLotteryCode(String lotteryCode) {
		this.lotteryCode = lotteryCode;
	}

	public String getIssueMin() {
		return issueMin;
	}

	public void setIssueMin(String issueMin) {
		this.issueMin = issueMin;
	}

	public String getIssueMax() {
		return issueMax;
	}

	public void setIssueMax(String issueMax) {
		this.issueMax = issueMax;
	}

	public String getTrackUserCode() {
		return trackUserCode;
	}

	public void setTrackUserCode(String trackUserCode) {
		this.trackUserCode = trackUserCode;
	}

	public String getCreateStartDate() {
		return createStartDate;
	}

	public void setCreateStartDate(String createStartDate) {
		this.createStartDate = createStartDate;
	}

	public String getCreateEndDate() {
		return createEndDate;
	}

	public void setCreateEndDate(String createEndDate) {
		this.createEndDate = createEndDate;
	}

	public Double getAmountMin() {
		return amountMin;
	}

	public void setAmountMin(Double amountMin) {
		this.amountMin = amountMin;
	}

	public Double getAmountMax() {
		return amountMax;
	}

	public void setAmountMax(Double amountMax) {
		this.amountMax = amountMax;
	}

	public Double getBonusMin() {
		return bonusMin;
	}

	public void setBonusMin(Double bonusMin) {
		this.bonusMin = bonusMin;
	}

	public Double getBonusMax() {
		return bonusMax;
	}

	public void setBonusMax(Double bonusMax) {
		this.bonusMax = bonusMax;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getPlayCode() {
		return playCode;
	}

	public void setPlayCode(String playCode) {
		this.playCode = playCode;
	}

	
	public Integer getMultiple() {
		return multiple;
	}

	public void setMultiple(Integer multiple) {
		this.multiple = multiple;
	}

	public Integer getBonusToAccount() {
		return bonusToAccount;
	}

	public void setBonusToAccount(Integer bonusToAccount) {
		this.bonusToAccount = bonusToAccount;
	}

	

	public String getBonusNumber() {
		return bonusNumber;
	}

	public void setBonusNumber(String bonusNumber) {
		this.bonusNumber = bonusNumber;
	}

	public Integer getBuyType() {
		return buyType;
	}

	public void setBuyType(Integer buyType) {
		this.buyType = buyType;
	}

	public String getNumberInfo() {
		return numberInfo;
	}

	public void setNumberInfo(String numberInfo) {
		this.numberInfo = numberInfo;
	}

	public Double getBuyProgress() {
		return buyProgress;
	}

	public void setBuyProgress(Double buyProgress) {
		this.buyProgress = buyProgress;
	}

	public Double getLastProgress() {
		return lastProgress;
	}

	public void setLastProgress(Double lastProgress) {
		this.lastProgress = lastProgress;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBackup1() {
		return backup1;
	}

	public void setBackup1(String backup1) {
		this.backup1 = backup1;
	}

	public String getBackup2() {
		return backup2;
	}

	public void setBackup2(String backup2) {
		this.backup2 = backup2;
	}

	public String getBackup3() {
		return backup3;
	}

	public void setBackup3(String backup3) {
		this.backup3 = backup3;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
