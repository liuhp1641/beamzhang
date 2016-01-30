package com.cm.manage.vo.account;

import java.io.Serializable;


public class AccountLogVO implements Serializable {
	private static final long serialVersionUID = -6865628298045854331L;
	private Long id;
	private String userCode;
	private String orderId;// 订单号
	private String resources;// 处理来源
	private Integer eventType;// 操作类型(0,出帐，1,入帐)
	private String secondType;// 二级类型
	private String type;// 三级类型
	private String eventCode;// 操作业务码(001:充值,)

	private Double bonusAmountOld;// 操作前可用中奖金额
	private Double bonusAmount;// 操作中奖金额
	private Double bonusAmountNew;// 操作后可用中奖金额

	private Double rechargeAmountOld;// 操作前可用的充值金额
	private Double rechargeAmount;// 操作的充值金额
	private Double rechargeAmountNew;// 操作后可用的充值金额

	private Double presentAmountOld;// 操作前可用的赠送金额
	private Double presentAmount;// 操作的赠送金额
	private Double presentAmountNew;// 操作后可用的赠送金额

	private Double scoreOld;// 可用的积分
	private Double score;// 可用的积分
	private Double scoreNew;// 可用的积分

	private Double commissionOld;// 可用佣金
	private Double commission;// 可用佣金
	private Double commissionNew;// 可用佣金

	private Double goldOld;// 可用金币
	private Double gold;// 可用金币
	private Double goldNew;// 可用金币

	private String outAccount;// 转出账户
	private String intoAccount;// 转入账户
	private String createTime;
	private String memo;// 备注
	private String desc;
	private String backup1;//
	private String backup2;//
	private String backup3;

	 //查询项
	
	private boolean flag;//
	
	private String userName;//用户名
	
	private Double amount;//金额
	
	private Double amountOld;//交易前金额
	
	private Double amountNew;//交易后金额
	
	private String accountType;//账户类型
	
	private Double tradeMin;//交易金额最低
	
	private Double tradeMax;//交易金额最高
	
	private String tradeStart;//交易开始时间
	
	private String tradeEnd;//交易结束时间
	
	private String eventName;//交易类型名
    
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getResources() {
		return resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}

	public Integer getEventType() {
		return eventType;
	}

	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}

	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
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

	public Double getBonusAmountNew() {
		return bonusAmountNew;
	}

	public void setBonusAmountNew(Double bonusAmountNew) {
		this.bonusAmountNew = bonusAmountNew;
	}

	public Double getRechargeAmountNew() {
		return rechargeAmountNew;
	}

	public void setRechargeAmountNew(Double rechargeAmountNew) {
		this.rechargeAmountNew = rechargeAmountNew;
	}

	public Double getPresentAmountNew() {
		return presentAmountNew;
	}

	public void setPresentAmountNew(Double presentAmountNew) {
		this.presentAmountNew = presentAmountNew;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Double getBonusAmountOld() {
		return bonusAmountOld;
	}

	public void setBonusAmountOld(Double bonusAmountOld) {
		this.bonusAmountOld = bonusAmountOld;
	}

	public Double getRechargeAmountOld() {
		return rechargeAmountOld;
	}

	public void setRechargeAmountOld(Double rechargeAmountOld) {
		this.rechargeAmountOld = rechargeAmountOld;
	}

	public Double getPresentAmountOld() {
		return presentAmountOld;
	}

	public void setPresentAmountOld(Double presentAmountOld) {
		this.presentAmountOld = presentAmountOld;
	}

	public String getOutAccount() {
		return outAccount;
	}

	public void setOutAccount(String outAccount) {
		this.outAccount = outAccount;
	}

	public String getIntoAccount() {
		return intoAccount;
	}

	public void setIntoAccount(String intoAccount) {
		this.intoAccount = intoAccount;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Double getTradeMin() {
		return tradeMin;
	}

	public void setTradeMin(Double tradeMin) {
		this.tradeMin = tradeMin;
	}

	public Double getTradeMax() {
		return tradeMax;
	}

	public void setTradeMax(Double tradeMax) {
		this.tradeMax = tradeMax;
	}

	public String getTradeStart() {
		return tradeStart;
	}

	public void setTradeStart(String tradeStart) {
		this.tradeStart = tradeStart;
	}

	public String getTradeEnd() {
		return tradeEnd;
	}

	public void setTradeEnd(String tradeEnd) {
		this.tradeEnd = tradeEnd;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAmountOld() {
		return amountOld;
	}

	public void setAmountOld(Double amountOld) {
		this.amountOld = amountOld;
	}

	public Double getAmountNew() {
		return amountNew;
	}

	public void setAmountNew(Double amountNew) {
		this.amountNew = amountNew;
	}

	public Double getScoreOld() {
		return scoreOld;
	}

	public void setScoreOld(Double scoreOld) {
		this.scoreOld = scoreOld;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Double getScoreNew() {
		return scoreNew;
	}

	public void setScoreNew(Double scoreNew) {
		this.scoreNew = scoreNew;
	}

	public Double getCommissionOld() {
		return commissionOld;
	}

	public void setCommissionOld(Double commissionOld) {
		this.commissionOld = commissionOld;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public Double getCommissionNew() {
		return commissionNew;
	}

	public void setCommissionNew(Double commissionNew) {
		this.commissionNew = commissionNew;
	}

	public Double getGoldOld() {
		return goldOld;
	}

	public void setGoldOld(Double goldOld) {
		this.goldOld = goldOld;
	}

	public Double getGold() {
		return gold;
	}

	public void setGold(Double gold) {
		this.gold = gold;
	}

	public Double getGoldNew() {
		return goldNew;
	}

	public void setGoldNew(Double goldNew) {
		this.goldNew = goldNew;
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

	public String getSecondType() {
		return secondType;
	}

	public void setSecondType(String secondType) {
		this.secondType = secondType;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
}
