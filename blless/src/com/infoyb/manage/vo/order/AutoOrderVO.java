package com.cm.manage.vo.order;

import java.io.Serializable;


public class AutoOrderVO implements Serializable {
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
    private String createTime;// 创建时间
    private String acceptTime;

    private String platform;
    private String softVer;
    private Double fixBonusAmount;

    private Integer isHide;//是否隐藏
    private Integer isVerify;//是否审核
    
    private Integer totalAuto;//已追
    

    //查询条件
    
    private boolean flag;//是否模糊查询
    
    private String userName;//用户名
    
    private String mobile;//手机号码
    
    private String trackUserCode;//所属用户

    private String issue;//包含奖期
    
    private String createStartDate;//发起开始时间
    
    private String createEndDate;//发起结束时间
    
    private Double amountMin;//方案金额最小
    
    private Double amountMax;//方案金额最大
    
    private Double buyAmountMin;//成交金额最小
    
    private Double buyAmountMax;//成交金额最大
    
    private Double bonusMin;//中奖金额最小
    
    private Double bonusMax;//中奖金额最大

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

	

	public String getTrackUserCode() {
		return trackUserCode;
	}

	public void setTrackUserCode(String trackUserCode) {
		this.trackUserCode = trackUserCode;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
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

	public Double getBuyAmountMin() {
		return buyAmountMin;
	}

	public void setBuyAmountMin(Double buyAmountMin) {
		this.buyAmountMin = buyAmountMin;
	}

	public Double getBuyAmountMax() {
		return buyAmountMax;
	}

	public void setBuyAmountMax(Double buyAmountMax) {
		this.buyAmountMax = buyAmountMax;
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

	public Integer getTotalAuto() {
		return totalAuto;
	}

	public void setTotalAuto(Integer totalAuto) {
		this.totalAuto = totalAuto;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
