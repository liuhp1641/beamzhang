package com.cm.manage.vo.order;

import java.io.Serializable;

public class HandworkBonusVO implements Serializable {

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
    private String createTime;//创建时间
    private String acceptTime;// 处理时间

    //操作人
    private String operator;
    
    private String betStartDate;//投注时间开始
    
    private String betEndDate;//投注时间结束
    
    private String bonusStartDate;//派奖时间开始
    
    private String bonusEndDate;//派奖时间结束
    
    private String issueMin;//奖期范围最小
    
    private String issueMax;//奖期范围最大
    
    private boolean flag;
    
    private String playCode;//玩法
    
    private Integer multiple;// 倍数
    
    private Integer buyType;// 购买方式(1:代购、2:合买、4:追号)
    
    public HandworkBonusVO() {
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

	public String getBetStartDate() {
		return betStartDate;
	}

	public void setBetStartDate(String betStartDate) {
		this.betStartDate = betStartDate;
	}

	public String getBetEndDate() {
		return betEndDate;
	}

	public void setBetEndDate(String betEndDate) {
		this.betEndDate = betEndDate;
	}

	public String getBonusStartDate() {
		return bonusStartDate;
	}

	public void setBonusStartDate(String bonusStartDate) {
		this.bonusStartDate = bonusStartDate;
	}

	public String getBonusEndDate() {
		return bonusEndDate;
	}

	public void setBonusEndDate(String bonusEndDate) {
		this.bonusEndDate = bonusEndDate;
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

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
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

	public Integer getBuyType() {
		return buyType;
	}

	public void setBuyType(Integer buyType) {
		this.buyType = buyType;
	}
	
}
