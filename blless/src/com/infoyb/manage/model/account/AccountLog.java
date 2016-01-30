package com.cm.manage.model.account;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * User: 邓玉明 Date: 11-3-27 下午4:14
 */
public class AccountLog implements Serializable {
	private static final long serialVersionUID = 3529532806061025845L;
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

	private Double bonusAmountFreezeOld;// 操作前冻结中奖金额
	private Double bonusAmountFreezeNew;// 操作后冻结中奖金额

	private Double rechargeAmountOld;// 操作前可用的充值金额
	private Double rechargeAmount;// 操作的充值金额
	private Double rechargeAmountNew;// 操作后可用的充值金额

	private Double rechargeAmountFreezeOld;// 操作前冻结的充值金额
	private Double rechargeAmountFreezeNew;// 操作后冻结的充值金额

	private Double presentAmountOld;// 操作前可用的赠送金额
	private Double presentAmount;// 操作的赠送金额
	private Double presentAmountNew;// 操作后可用的赠送金额

	private Double presentAmountFreezeOld;// 操作前冻结的赠送金额
	private Double presentAmountFreezeNew;// 操作后冻结的赠送金额

	private Double scoreOld;// 操作前可用的积分
	private Double score;// 操作的的积分
	private Double scoreNew;// 操作后可用的积分

	private Double scoreFreezeOld;// 操作前冻结可用的积分
	private Double scoreFreezeNew;// 操作后冻结可用的积分

	private Double commissionOld;// 操作前可用佣金
	private Double commission;// 操作的佣金
	private Double commissionNew;// 操作后可用佣金

	private Double commissionFreezeOld;// 操作前冻结可用佣金
	private Double commissionFreezeNew;// 操作后冻结可用佣金

	private Double goldOld;// 操作前可用金币
	private Double gold;// 操作的金币
	private Double goldNew;// 操作后可用金币

	private Double goldFreezeOld;// 操作前冻结可用金币
	private Double goldFreezeNew;// 操作后冻结可用金币

	private String outAccount;// 转出账户
	private String intoAccount;// 转入账户
	private Date createTime;
	private String memo;// 备注
	private String desc;
	private String backup1;//
	private String backup2;//
	private String backup3;

	public AccountLog() {
		this.desc = "-";
	}

	public AccountLog(Long id, String userCode, String orderId, String resources, Integer eventType, String secondType, String type, String eventCode, Double bonusAmountOld, Double bonusAmount,
			Double bonusAmountNew, Double bonusAmountFreezeOld, Double bonusAmountFreezeNew, Double rechargeAmountOld, Double rechargeAmount, Double rechargeAmountNew, Double rechargeAmountFreezeOld,
			Double rechargeAmountFreezeNew, Double presentAmountOld, Double presentAmount, Double presentAmountNew, Double presentAmountFreezeOld, Double presentAmountFreezeNew, Double scoreOld,
			Double score, Double scoreNew, Double scoreFreezeOld, Double scoreFreezeNew, Double commissionOld, Double commission, Double commissionNew, Double commissionFreezeOld,
			Double commissionFreezeNew, Double goldOld, Double gold, Double goldNew, Double goldFreezeOld, Double goldFreezeNew, String outAccount, String intoAccount, Date createTime, String memo,
			String desc, String backup1, String backup2, String backup3) {
		this.id = id;
		this.userCode = userCode;
		this.orderId = orderId;
		this.resources = resources;
		this.eventType = eventType;
		this.secondType = secondType;
		this.type = type;
		this.eventCode = eventCode;
		this.bonusAmountOld = bonusAmountOld;
		this.bonusAmount = bonusAmount;
		this.bonusAmountNew = bonusAmountNew;
		this.bonusAmountFreezeOld = bonusAmountFreezeOld;
		this.bonusAmountFreezeNew = bonusAmountFreezeNew;
		this.rechargeAmountOld = rechargeAmountOld;
		this.rechargeAmount = rechargeAmount;
		this.rechargeAmountNew = rechargeAmountNew;
		this.rechargeAmountFreezeOld = rechargeAmountFreezeOld;
		this.rechargeAmountFreezeNew = rechargeAmountFreezeNew;
		this.presentAmountOld = presentAmountOld;
		this.presentAmount = presentAmount;
		this.presentAmountNew = presentAmountNew;
		this.presentAmountFreezeOld = presentAmountFreezeOld;
		this.presentAmountFreezeNew = presentAmountFreezeNew;
		this.scoreOld = scoreOld;
		this.score = score;
		this.scoreNew = scoreNew;
		this.scoreFreezeOld = scoreFreezeOld;
		this.scoreFreezeNew = scoreFreezeNew;
		this.commissionOld = commissionOld;
		this.commission = commission;
		this.commissionNew = commissionNew;
		this.commissionFreezeOld = commissionFreezeOld;
		this.commissionFreezeNew = commissionFreezeNew;
		this.goldOld = goldOld;
		this.gold = gold;
		this.goldNew = goldNew;
		this.goldFreezeOld = goldFreezeOld;
		this.goldFreezeNew = goldFreezeNew;
		this.outAccount = outAccount;
		this.intoAccount = intoAccount;
		this.createTime = createTime;
		this.memo = memo;
		this.desc = desc;
		this.backup1 = backup1;
		this.backup2 = backup2;
		this.backup3 = backup3;
	}

	public Map<String, Double> getCapitalComposition() {
		Map<String, Double> result = new HashMap<String, Double>();

		result.put("bonusAmount", bonusAmount);
		result.put("rechargeAmount", rechargeAmount);
		result.put("presentAmount", presentAmount);
		result.put("total", bonusAmount + rechargeAmount + presentAmount);
		result.put("drawTotal", bonusAmount + rechargeAmount);
		return result;
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

	public String getSecondType() {
		return secondType;
	}

	public void setSecondType(String secondType) {
		this.secondType = secondType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

	public Double getBonusAmountOld() {
		return bonusAmountOld;
	}

	public void setBonusAmountOld(Double bonusAmountOld) {
		this.bonusAmountOld = bonusAmountOld;
	}

	public Double getBonusAmount() {
		return bonusAmount;
	}

	public void setBonusAmount(Double bonusAmount) {
		this.bonusAmount = bonusAmount;
	}

	public Double getBonusAmountNew() {
		return bonusAmountNew;
	}

	public void setBonusAmountNew(Double bonusAmountNew) {
		this.bonusAmountNew = bonusAmountNew;
	}

	public Double getBonusAmountFreezeOld() {
		return bonusAmountFreezeOld;
	}

	public void setBonusAmountFreezeOld(Double bonusAmountFreezeOld) {
		this.bonusAmountFreezeOld = bonusAmountFreezeOld;
	}

	public Double getBonusAmountFreezeNew() {
		return bonusAmountFreezeNew;
	}

	public void setBonusAmountFreezeNew(Double bonusAmountFreezeNew) {
		this.bonusAmountFreezeNew = bonusAmountFreezeNew;
	}

	public Double getRechargeAmountOld() {
		return rechargeAmountOld;
	}

	public void setRechargeAmountOld(Double rechargeAmountOld) {
		this.rechargeAmountOld = rechargeAmountOld;
	}

	public Double getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(Double rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public Double getRechargeAmountNew() {
		return rechargeAmountNew;
	}

	public void setRechargeAmountNew(Double rechargeAmountNew) {
		this.rechargeAmountNew = rechargeAmountNew;
	}

	public Double getRechargeAmountFreezeOld() {
		return rechargeAmountFreezeOld;
	}

	public void setRechargeAmountFreezeOld(Double rechargeAmountFreezeOld) {
		this.rechargeAmountFreezeOld = rechargeAmountFreezeOld;
	}

	public Double getRechargeAmountFreezeNew() {
		return rechargeAmountFreezeNew;
	}

	public void setRechargeAmountFreezeNew(Double rechargeAmountFreezeNew) {
		this.rechargeAmountFreezeNew = rechargeAmountFreezeNew;
	}

	public Double getPresentAmountOld() {
		return presentAmountOld;
	}

	public void setPresentAmountOld(Double presentAmountOld) {
		this.presentAmountOld = presentAmountOld;
	}

	public Double getPresentAmount() {
		return presentAmount;
	}

	public void setPresentAmount(Double presentAmount) {
		this.presentAmount = presentAmount;
	}

	public Double getPresentAmountNew() {
		return presentAmountNew;
	}

	public void setPresentAmountNew(Double presentAmountNew) {
		this.presentAmountNew = presentAmountNew;
	}

	public Double getPresentAmountFreezeOld() {
		return presentAmountFreezeOld;
	}

	public void setPresentAmountFreezeOld(Double presentAmountFreezeOld) {
		this.presentAmountFreezeOld = presentAmountFreezeOld;
	}

	public Double getPresentAmountFreezeNew() {
		return presentAmountFreezeNew;
	}

	public void setPresentAmountFreezeNew(Double presentAmountFreezeNew) {
		this.presentAmountFreezeNew = presentAmountFreezeNew;
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

	public Double getScoreFreezeOld() {
		return scoreFreezeOld;
	}

	public void setScoreFreezeOld(Double scoreFreezeOld) {
		this.scoreFreezeOld = scoreFreezeOld;
	}

	public Double getScoreFreezeNew() {
		return scoreFreezeNew;
	}

	public void setScoreFreezeNew(Double scoreFreezeNew) {
		this.scoreFreezeNew = scoreFreezeNew;
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

	public Double getCommissionFreezeOld() {
		return commissionFreezeOld;
	}

	public void setCommissionFreezeOld(Double commissionFreezeOld) {
		this.commissionFreezeOld = commissionFreezeOld;
	}

	public Double getCommissionFreezeNew() {
		return commissionFreezeNew;
	}

	public void setCommissionFreezeNew(Double commissionFreezeNew) {
		this.commissionFreezeNew = commissionFreezeNew;
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

	public Double getGoldFreezeOld() {
		return goldFreezeOld;
	}

	public void setGoldFreezeOld(Double goldFreezeOld) {
		this.goldFreezeOld = goldFreezeOld;
	}

	public Double getGoldFreezeNew() {
		return goldFreezeNew;
	}

	public void setGoldFreezeNew(Double goldFreezeNew) {
		this.goldFreezeNew = goldFreezeNew;
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
}
