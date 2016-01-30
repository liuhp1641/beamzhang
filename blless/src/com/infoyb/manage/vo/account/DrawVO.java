package com.cm.manage.vo.account;

import java.io.Serializable;
import java.util.Date;

/**
 * User: 邓玉明 Date: 11-3-27 下午4:38
 */
public class DrawVO implements Serializable {

	private static final long serialVersionUID = -269890018695198718L;
	private Long id;
	private String userCode;
    private String userName;
    private String realName;
    private String mobile;
    private String cardCode;
	private String orderId;
	private String drawResources;// 提现类型
	private String drawInfo;// 提现信息
	private Double amount;
	private String createTime;
	private String acceptTime;
	private Integer status;
	private Double fee;
	private Double realFee;
	private String memo;
	private String peopleAccepted;// 受理人
	private String peopleRemittance;// 汇款人
	private Integer type; // t+0 0 t+1 1
	private String sid;
	private String platform;
	private String softVer;

	private Double bonusAmountNew;//
    private String transferBatchId; //转账批次号

    private Double amountMin;
    private Double amountMax;

    private String createStartDate;
    private String createEndDate;

    private String acceptStartDate;
    private String acceptEndDate;

    private boolean flag;//是否模糊查询
    
    private String errorMsg;//支付宝返回的错误信息
    
    private Double rechargeTotal;//充值金总额
    private Double rechargeDrawTotal;//充值金提出
    private Double bonusAmount;//可用的奖金余额
    private Double drawBeforeLimit;//提现前额度
    private Double drawAfterLimit;//提现后额度
    
    private Integer vip;
    private String vipName;//中文显示
    
    private Double realAmount;//实际转账金额
    private String statusName;

	public String getPeopleAccepted() {
		return peopleAccepted;
	}

	public String getPeopleRemittance() {
		return peopleRemittance;
	}

	public void setPeopleAccepted(String peopleAccepted) {
		this.peopleAccepted = peopleAccepted;
	}

	public void setPeopleRemittance(String peopleRemittance) {
		this.peopleRemittance = peopleRemittance;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public String getDrawResources() {
		return drawResources;
	}

	public void setDrawResources(String drawResources) {
		this.drawResources = drawResources;
	}

	public String getDrawInfo() {
		return drawInfo;
	}

	public void setDrawInfo(String drawInfo) {
		this.drawInfo = drawInfo;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

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

	public String getSoftVer() {
		return softVer;
	}

	public void setSoftVer(String softVer) {
		this.softVer = softVer;
	}

	public Double getBonusAmountNew() {
		return bonusAmountNew;
	}

	public void setBonusAmountNew(Double bonusAmountNew) {
		this.bonusAmountNew = bonusAmountNew;
	}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getTransferBatchId() {
        return transferBatchId;
    }

    public void setTransferBatchId(String transferBatchId) {
        this.transferBatchId = transferBatchId;
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

    public String getAcceptStartDate() {
        return acceptStartDate;
    }

    public void setAcceptStartDate(String acceptStartDate) {
        this.acceptStartDate = acceptStartDate;
    }

    public String getAcceptEndDate() {
        return acceptEndDate;
    }

    public void setAcceptEndDate(String acceptEndDate) {
        this.acceptEndDate = acceptEndDate;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

	public Double getRealFee() {
		return realFee;
	}

	public void setRealFee(Double realFee) {
		this.realFee = realFee;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Integer getVip() {
		return vip;
	}

	public void setVip(Integer vip) {
		this.vip = vip;
	}

	public Double getRechargeTotal() {
		return rechargeTotal;
	}

	public void setRechargeTotal(Double rechargeTotal) {
		this.rechargeTotal = rechargeTotal;
	}

	public Double getRechargeDrawTotal() {
		return rechargeDrawTotal;
	}

	public void setRechargeDrawTotal(Double rechargeDrawTotal) {
		this.rechargeDrawTotal = rechargeDrawTotal;
	}

	public Double getBonusAmount() {
		return bonusAmount;
	}

	public void setBonusAmount(Double bonusAmount) {
		this.bonusAmount = bonusAmount;
	}

	public Double getDrawBeforeLimit() {
		return drawBeforeLimit;
	}

	public void setDrawBeforeLimit(Double drawBeforeLimit) {
		this.drawBeforeLimit = drawBeforeLimit;
	}

	public Double getDrawAfterLimit() {
		return drawAfterLimit;
	}

	public void setDrawAfterLimit(Double drawAfterLimit) {
		this.drawAfterLimit = drawAfterLimit;
	}

	public String getVipName() {
		return vipName;
	}

	public void setVipName(String vipName) {
		this.vipName = vipName;
	}

	public Double getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(Double realAmount) {
		this.realAmount = realAmount;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	
	
    
}
