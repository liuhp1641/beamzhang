package com.cm.manage.vo.account;

import java.io.Serializable;
import java.util.Date;


public class InternalAccessVO implements Serializable {
	
	private static final long serialVersionUID = 5349064966516712017L;
	private Long id;
	private String serialNo;//流水编号
	private String relateSerialNo;//对方流水号
	private String userCode;
	
	private String secondType;// 二级类型
	private String eventCode;// 操作业务码(001:充值,)
	
	private Double bonusAmount;// 操作中奖金额
	private Double rechargeAmount;// 操作的充值金额
	private Double presentAmount;// 操作的赠送金额
	private Double score;// 操作的积分
	private Double commission;// 操作的佣金
	private Double gold;// 操作的金币
	
	private Double bonusAmountOld;// 操作前可用中奖金额
	private Double rechargeAmountOld;// 操作前可用的充值金额
	private Double presentAmountOld;// 操作前可用的赠送金额
	private Double scoreOld;// 操作前可用的积分
	private Double commissionOld;// 操作前可用的佣金
	private Double goldOld;// 操作前可用的金币
	
	private Double bonusAmountNew;// 操作后可用中奖金额
	private Double rechargeAmountNew;// 操作后可用的充值金额
	private Double presentAmountNew;// 操作后可用的赠送金额
	private Double scoreNew;// 操作后可用的积分
	private Double commissionNew;// 操作后可用的佣金
	private Double goldNew;// 操作后可用的金币
	
	private String outAccount;// 转出账户
	private String intoAccount;// 转入账户
	private String createTime;
	private String acceptTime;//受理时间
	private Integer status;//处理状态(0,等待，1,成功，2,失败)
	private String memo;// 备注
	private String operator;//操作员
	
	private Double amount;//金额
	
	private Integer accountType;//账户类型
	
	private String account;//系统账户编码
	
	private String accountName;//系统账户名
	
	//查询项
    private boolean flag;//
	
	private String userName;//用户名
	
    private Double adjustMin;//调整金额最低
	
	private Double adjustMax;//调整金额最高
	
    private String acceptStart;//受理开始时间
    
    private String acceptEnd;//受理结束时间
	
	
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
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
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
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Double getCommission() {
		return commission;
	}
	public void setCommission(Double commission) {
		this.commission = commission;
	}
	public Double getGold() {
		return gold;
	}
	public void setGold(Double gold) {
		this.gold = gold;
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
	public Double getScoreOld() {
		return scoreOld;
	}
	public void setScoreOld(Double scoreOld) {
		this.scoreOld = scoreOld;
	}
	public Double getCommissionOld() {
		return commissionOld;
	}
	public void setCommissionOld(Double commissionOld) {
		this.commissionOld = commissionOld;
	}
	public Double getGoldOld() {
		return goldOld;
	}
	public void setGoldOld(Double goldOld) {
		this.goldOld = goldOld;
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
	public Double getScoreNew() {
		return scoreNew;
	}
	public void setScoreNew(Double scoreNew) {
		this.scoreNew = scoreNew;
	}
	public Double getCommissionNew() {
		return commissionNew;
	}
	public void setCommissionNew(Double commissionNew) {
		this.commissionNew = commissionNew;
	}
	public Double getGoldNew() {
		return goldNew;
	}
	public void setGoldNew(Double goldNew) {
		this.goldNew = goldNew;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Integer getAccountType() {
		return accountType;
	}
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
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
	public String getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	public String getRelateSerialNo() {
		return relateSerialNo;
	}
	public void setRelateSerialNo(String relateSerialNo) {
		this.relateSerialNo = relateSerialNo;
	}
	public Double getAdjustMin() {
		return adjustMin;
	}
	public void setAdjustMin(Double adjustMin) {
		this.adjustMin = adjustMin;
	}
	public Double getAdjustMax() {
		return adjustMax;
	}
	public void setAdjustMax(Double adjustMax) {
		this.adjustMax = adjustMax;
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
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
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
	public String getSecondType() {
		return secondType;
	}
	public void setSecondType(String secondType) {
		this.secondType = secondType;
	}
	public String getEventCode() {
		return eventCode;
	}
	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}
	
}
