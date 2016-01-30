package com.cm.manage.vo.account;

import java.io.Serializable;


public class FillVO implements Serializable {

	private static final long serialVersionUID = 1712090033903716995L;
	private Long id;
    private String userCode;
    private String orderId;//充值订单号
    private String outOrderId;//外部订单号
    private String fillResources;//充值来源(支付宝)
    private Double amount;//充值金额
    private Double realAmount;//到帐金额
    private String createTime;//充值金额
    private String acceptTime;//受理时间
    private Integer status;//充值状态(0,等待，1,成功，2,失败)
    private String sid;//渠道号
    private String platform;//平台
    private String softVer;//软件版本
    private Double rechargeAmountNew;
    private String memo;//说明

    //查询项
    
    private boolean flag;//是否模糊查询
    
    private String userName;//用户名
    
    private Double amountMin;//金额最低
    
    private Double amountMax;//金额最高
    
    private String regSid;//注册渠道
    
    private String trackUserCode;//所属用户
    
    private String createStart;//发起开始时间
    
    private String createEnd;//发起结束时间
    
    private String acceptStart;//受理开始时间
    
    private String acceptEnd;//受理结束时间

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

	public String getRegSid() {
		return regSid;
	}

	public void setRegSid(String regSid) {
		this.regSid = regSid;
	}

	public String getTrackUserCode() {
		return trackUserCode;
	}

	public void setTrackUserCode(String trackUserCode) {
		this.trackUserCode = trackUserCode;
	}

	public String getCreateStart() {
		return createStart;
	}

	public void setCreateStart(String createStart) {
		this.createStart = createStart;
	}

	public String getCreateEnd() {
		return createEnd;
	}

	public void setCreateEnd(String createEnd) {
		this.createEnd = createEnd;
	}

	public String getAcceptStart() {
		return acceptStart;
	}

	public void setAcceptStart(String acceptStart) {
		this.acceptStart = acceptStart;
	}

	public String getAcceptEnd() {
		return acceptEnd;
	}

	public void setAcceptEnd(String acceptEnd) {
		this.acceptEnd = acceptEnd;
	}
	
}
