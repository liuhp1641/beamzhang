package com.cm.manage.vo.order;

import java.io.Serializable;

public class MainIssueVO implements Serializable{

    private static final long serialVersionUID = -9170187568993057008L;
    private Long id;
    private String lotteryCode;
    private String name;
    private String startTime;//官方开始时间
    private String simplexTime;//单式结束时间
    private String duplexTime;//复式结束时间
    private String endTime;//官方停止时间
    private Integer status;//状态(0,预售，1,销售，2,暂停，3,结期)
    private Integer sendStatus;//是否可发单(0,不可，1,可)
    private String bonusTime;//开奖时间
    private Integer bonusStatus;//返奖状态(0,等待返奖，1,已返奖)
    private String bonusNumber;//开奖号码
    private Double bonusTotal;//中奖总金额
    private String prizePool;//下期奖池信息
    private String bonusClass;//奖级信息[{'n1':'奖级','c1':'单注奖金','m1':'全国个数','t1':'奖级中文名','a1':地方个数(默认0)},{'n1':'奖级','c1':'单注奖金','m1':'全国个数','t1':'奖级中文名','a1':地方个数(默认0)}]
    private String saleTotal;//销售总金额
    private String globalSaleTotal;//全国销量
    private String backup1;
    private String backup2;
    private String backup3;
    private Integer operatorsAward;//是否算奖（0等待算奖，1算奖中，2完成算奖）
    private Integer bonusToAccount;//是否派奖(0,1 2 通知派奖)

    private String programsHandleTime;//方案最后处理时间
    private Integer programsHandle;//方案是否最后处理

    
    private boolean flag;
    
    private String issueMin;//奖期范围最小
    
    private String issueMax;//奖期范围最大
    
    private String lotteryName;//
    
    
    public MainIssueVO() {
    }

    public String getPrizePool() {
        return prizePool;
    }

    public void setPrizePool(String prizePool) {
        this.prizePool = prizePool;
    }

    public String getBonusClass() {
        return bonusClass;
    }

    public void setBonusClass(String bonusClass) {
        this.bonusClass = bonusClass;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Integer getBonusStatus() {
        return bonusStatus;
    }

    public void setBonusStatus(Integer bonusStatus) {
        this.bonusStatus = bonusStatus;
    }

    public String getBonusNumber() {
        return bonusNumber;
    }

    public void setBonusNumber(String bonusNumber) {
        this.bonusNumber = bonusNumber;
    }

    public Double getBonusTotal() {
        return bonusTotal;
    }

    public void setBonusTotal(Double bonusTotal) {
        this.bonusTotal = bonusTotal;
    }

    public String getGlobalSaleTotal() {
        return globalSaleTotal;
    }

    public void setGlobalSaleTotal(String globalSaleTotal) {
        this.globalSaleTotal = globalSaleTotal;
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

    public Integer getOperatorsAward() {
        return operatorsAward;
    }

    public void setOperatorsAward(Integer operatorsAward) {
        this.operatorsAward = operatorsAward;
    }

    public String getSaleTotal() {
        return saleTotal;
    }

    public void setSaleTotal(String saleTotal) {
        this.saleTotal = saleTotal;
    }

    public Integer getBonusToAccount() {
        return bonusToAccount;
    }

    public void setBonusToAccount(Integer bonusToAccount) {
        this.bonusToAccount = bonusToAccount;
    }

    public Integer getProgramsHandle() {
        return programsHandle;
    }

    public void setProgramsHandle(Integer programsHandle) {
        this.programsHandle = programsHandle;
    }

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getSimplexTime() {
		return simplexTime;
	}

	public void setSimplexTime(String simplexTime) {
		this.simplexTime = simplexTime;
	}

	public String getDuplexTime() {
		return duplexTime;
	}

	public void setDuplexTime(String duplexTime) {
		this.duplexTime = duplexTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getBonusTime() {
		return bonusTime;
	}

	public void setBonusTime(String bonusTime) {
		this.bonusTime = bonusTime;
	}

	public String getProgramsHandleTime() {
		return programsHandleTime;
	}

	public void setProgramsHandleTime(String programsHandleTime) {
		this.programsHandleTime = programsHandleTime;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
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

	public String getLotteryName() {
		return lotteryName;
	}

	public void setLotteryName(String lotteryName) {
		this.lotteryName = lotteryName;
	}
	
}
