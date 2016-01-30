package com.cm.manage.model.account;

import java.io.Serializable;
import java.util.Date;


public class InternalAccess implements Serializable {
	
	private static final long serialVersionUID = 5349064966516712017L;
	private Long id;
	private String serialNo;//流水编号
	private String relateSerialNo;//对方流水号
	private String userCode;
	
	private String eventCode;// 操作业务码(001:充值,)
	
	private Integer eventType;// 操作类型(0,转入，1,转出,2:存入，3：取出)
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
	private Date createTime;
	private Date acceptTime;//受理时间
	private Integer status;//处理状态(0,等待，1,成功，2,失败)
	private String memo;// 备注
	private String operator;//操作员
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
	public Integer getEventType() {
		return eventType;
	}
	public void setEventType(Integer eventType) {
		this.eventType = eventType;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getAcceptTime() {
		return acceptTime;
	}
	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
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
	public String getEventCode() {
		return eventCode;
	}
	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}
}
