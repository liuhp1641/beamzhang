package com.cm.manage.vo.account;

import java.io.Serializable;
import java.util.Date;


public class AccountVO implements Serializable {
	private static final long serialVersionUID = 8305268878858528657L;
	private Long id;
	private String userCode;

	private Double bonusAmount;// 中奖金余额
	private Double bonusTotal;// 中奖存入总金额
	private Double bonusDrawTotal;// 中奖奖金取出总金额
	private Double bonusInTotal;// 中奖转入总金额
	private Double bonusOutTotal;// 中奖转出总金额
	private Double bonusPayTotal;// 中奖奖金消费总金额

	private Double rechargeAmount;// 可用的充值金额
	private Double rechargeTotal;// 充值存入总金额
	private Double rechargeDrawTotal;// 充值金取出总金额
	private Double rechargeInTotal;// 充值转入总金额
	private Double rechargeOutTotal;// 充值转出总金额
	private Double rechargePayTotal;// 充值金消费总金额

	private Double presentAmount;// 可用的赠送(奖励)金额
	private Double presentTotal;// 赠送(奖励)存入金额
	private Double presentDrawTotal;// 赠送(奖励)取出金额
	private Double presentInTotal;// 赠送(奖励)转入金额
	private Double presentOutTotal;// 赠送(奖励)转出金额
	private Double presentPayTotal;// 赠送金消费总金额

	private Double score;// 可用的积分
	private Double scoreTotal;// 积分存入总额
	private Double scoreDrawTotal;// 积分取出总额
	private Double scoreInTotal;// 积分转入总额
	private Double scoreOutTotal;// 积分转出总额
	private Double scorePayTotal;// 积分消费总额

	private Double commission;// 可用佣金
	private Double commissionTotal;// 返佣存入总金额
	private Double commissionDrawTotal;// 返佣取出总金额
	private Double commissionInTotal;// 返佣转入总金额
	private Double commissionOutTotal;// 返佣转出总金额
	private Double commissionPayTotal;// 返佣消费总金额

	private Double gold;// 可用金币
	private Double goldTotal;// 金币存入总金额
	private Double goldDrawTotal;// 金币取出总金额
	private Double goldInTotal;// 金币转入总金额
	private Double goldOutTotal;// 金币转出总金额
	private Double goldPayTotal;// 金币消费总金额

	private Date createTime;
	private Date updateTime;

    private String userName;//用户名
    
    private Double cashTotal;//现金余额
    
   
    
    //用户资金查询项
    
    private boolean flag;//是否模糊查询
    
    private String realName;//姓名
    
    private String cardCode;//身份证号
    
    private String sid;//注册渠道
    
    private String trackUserCode;//所属用户
    
    private Integer registerFrom;//注册方式
    
    private Integer equ;//
    
    private String machId;//串号
    
    private String regstart;//注册开始时间
    
    private String regend;//注册结束时间
    
    private String loginstart;//登录开始时间
    
    private String loginend;//登录结束时间 
    
    private Double leaveMin;//离开天数最低
    
    private Double leaveMax;//离开天数最高
    
    private Integer status;
    
    private Double amountMin;//余额最低
    
    private Double amountMax;//余额最高
    
    private Integer moneyType;//资金类型
    
    private Integer sumType;//汇总类型
    
    private String accountType;//账号类型
   
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

	public Double getBonusAmount() {
		return bonusAmount;
	}

	public void setBonusAmount(Double bonusAmount) {
		this.bonusAmount = bonusAmount;
	}

	public Double getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(Double rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public Double getPresentAmount() {
		return presentAmount;
	}

	public void setPresentAmount(Double presentAmount) {
		this.presentAmount = presentAmount;
	}

	public Double getBonusTotal() {
		return bonusTotal;
	}

	public void setBonusTotal(Double bonusTotal) {
		this.bonusTotal = bonusTotal;
	}

	public Double getRechargeTotal() {
		return rechargeTotal;
	}

	public void setRechargeTotal(Double rechargeTotal) {
		this.rechargeTotal = rechargeTotal;
	}

	public Double getPresentTotal() {
		return presentTotal;
	}

	public void setPresentTotal(Double presentTotal) {
		this.presentTotal = presentTotal;
	}

	

	public Double getRechargePayTotal() {
		return rechargePayTotal;
	}

	public void setRechargePayTotal(Double rechargePayTotal) {
		this.rechargePayTotal = rechargePayTotal;
	}

	public Double getBonusPayTotal() {
		return bonusPayTotal;
	}

	public void setBonusPayTotal(Double bonusPayTotal) {
		this.bonusPayTotal = bonusPayTotal;
	}

	public Double getPresentPayTotal() {
		return presentPayTotal;
	}

	public void setPresentPayTotal(Double presentPayTotal) {
		this.presentPayTotal = presentPayTotal;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public Double getCommissionTotal() {
		return commissionTotal;
	}

	public void setCommissionTotal(Double commissionTotal) {
		this.commissionTotal = commissionTotal;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getTrackUserCode() {
		return trackUserCode;
	}

	public void setTrackUserCode(String trackUserCode) {
		this.trackUserCode = trackUserCode;
	}

	public String getMachId() {
		return machId;
	}

	public void setMachId(String machId) {
		this.machId = machId;
	}

	public String getRegstart() {
		return regstart;
	}

	public void setRegstart(String regstart) {
		this.regstart = regstart;
	}

	public String getRegend() {
		return regend;
	}

	public void setRegend(String regend) {
		this.regend = regend;
	}

	public String getLoginstart() {
		return loginstart;
	}

	public void setLoginstart(String loginstart) {
		this.loginstart = loginstart;
	}

	public String getLoginend() {
		return loginend;
	}

	public void setLoginend(String loginend) {
		this.loginend = loginend;
	}

	public Double getLeaveMin() {
		return leaveMin;
	}

	public void setLeaveMin(Double leaveMin) {
		this.leaveMin = leaveMin;
	}

	public Double getLeaveMax() {
		return leaveMax;
	}

	public void setLeaveMax(Double leaveMax) {
		this.leaveMax = leaveMax;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	public Double getCashTotal() {
		return cashTotal;
	}

	public void setCashTotal(Double cashTotal) {
		this.cashTotal = cashTotal;
	}

	public Double getBonusDrawTotal() {
		return bonusDrawTotal;
	}

	public void setBonusDrawTotal(Double bonusDrawTotal) {
		this.bonusDrawTotal = bonusDrawTotal;
	}

	public Double getBonusInTotal() {
		return bonusInTotal;
	}

	public void setBonusInTotal(Double bonusInTotal) {
		this.bonusInTotal = bonusInTotal;
	}

	public Double getBonusOutTotal() {
		return bonusOutTotal;
	}

	public void setBonusOutTotal(Double bonusOutTotal) {
		this.bonusOutTotal = bonusOutTotal;
	}

	public Double getRechargeDrawTotal() {
		return rechargeDrawTotal;
	}

	public void setRechargeDrawTotal(Double rechargeDrawTotal) {
		this.rechargeDrawTotal = rechargeDrawTotal;
	}

	public Double getRechargeInTotal() {
		return rechargeInTotal;
	}

	public void setRechargeInTotal(Double rechargeInTotal) {
		this.rechargeInTotal = rechargeInTotal;
	}

	public Double getRechargeOutTotal() {
		return rechargeOutTotal;
	}

	public void setRechargeOutTotal(Double rechargeOutTotal) {
		this.rechargeOutTotal = rechargeOutTotal;
	}

	public Double getPresentDrawTotal() {
		return presentDrawTotal;
	}

	public void setPresentDrawTotal(Double presentDrawTotal) {
		this.presentDrawTotal = presentDrawTotal;
	}

	public Double getPresentInTotal() {
		return presentInTotal;
	}

	public void setPresentInTotal(Double presentInTotal) {
		this.presentInTotal = presentInTotal;
	}

	public Double getPresentOutTotal() {
		return presentOutTotal;
	}

	public void setPresentOutTotal(Double presentOutTotal) {
		this.presentOutTotal = presentOutTotal;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Double getScoreTotal() {
		return scoreTotal;
	}

	public void setScoreTotal(Double scoreTotal) {
		this.scoreTotal = scoreTotal;
	}

	public Double getScoreDrawTotal() {
		return scoreDrawTotal;
	}

	public void setScoreDrawTotal(Double scoreDrawTotal) {
		this.scoreDrawTotal = scoreDrawTotal;
	}

	public Double getScoreInTotal() {
		return scoreInTotal;
	}

	public void setScoreInTotal(Double scoreInTotal) {
		this.scoreInTotal = scoreInTotal;
	}

	public Double getScoreOutTotal() {
		return scoreOutTotal;
	}

	public void setScoreOutTotal(Double scoreOutTotal) {
		this.scoreOutTotal = scoreOutTotal;
	}

	public Double getScorePayTotal() {
		return scorePayTotal;
	}

	public void setScorePayTotal(Double scorePayTotal) {
		this.scorePayTotal = scorePayTotal;
	}

	public Double getCommissionDrawTotal() {
		return commissionDrawTotal;
	}

	public void setCommissionDrawTotal(Double commissionDrawTotal) {
		this.commissionDrawTotal = commissionDrawTotal;
	}

	public Double getCommissionInTotal() {
		return commissionInTotal;
	}

	public void setCommissionInTotal(Double commissionInTotal) {
		this.commissionInTotal = commissionInTotal;
	}

	public Double getCommissionOutTotal() {
		return commissionOutTotal;
	}

	public void setCommissionOutTotal(Double commissionOutTotal) {
		this.commissionOutTotal = commissionOutTotal;
	}

	public Double getCommissionPayTotal() {
		return commissionPayTotal;
	}

	public void setCommissionPayTotal(Double commissionPayTotal) {
		this.commissionPayTotal = commissionPayTotal;
	}

	public Double getGold() {
		return gold;
	}

	public void setGold(Double gold) {
		this.gold = gold;
	}

	public Double getGoldTotal() {
		return goldTotal;
	}

	public void setGoldTotal(Double goldTotal) {
		this.goldTotal = goldTotal;
	}

	public Double getGoldDrawTotal() {
		return goldDrawTotal;
	}

	public void setGoldDrawTotal(Double goldDrawTotal) {
		this.goldDrawTotal = goldDrawTotal;
	}

	public Double getGoldInTotal() {
		return goldInTotal;
	}

	public void setGoldInTotal(Double goldInTotal) {
		this.goldInTotal = goldInTotal;
	}

	public Double getGoldOutTotal() {
		return goldOutTotal;
	}

	public void setGoldOutTotal(Double goldOutTotal) {
		this.goldOutTotal = goldOutTotal;
	}

	public Double getGoldPayTotal() {
		return goldPayTotal;
	}

	public void setGoldPayTotal(Double goldPayTotal) {
		this.goldPayTotal = goldPayTotal;
	}

	public Integer getRegisterFrom() {
		return registerFrom;
	}

	public void setRegisterFrom(Integer registerFrom) {
		this.registerFrom = registerFrom;
	}

	public Integer getEqu() {
		return equ;
	}

	public void setEqu(Integer equ) {
		this.equ = equ;
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

	public Integer getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(Integer moneyType) {
		this.moneyType = moneyType;
	}

	public Integer getSumType() {
		return sumType;
	}

	public void setSumType(Integer sumType) {
		this.sumType = sumType;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
}
