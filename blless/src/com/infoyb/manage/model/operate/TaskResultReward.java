package com.cm.manage.model.operate;

import java.io.Serializable;
import java.util.Date;
/***
 * @describe 任务完成奖励
 * @date 2014-10-16
 * @author sunjf
 *
 */
public class TaskResultReward implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String taskId;//任务的唯一标识
	private Integer type;//奖励类型 0--资金 1--头衔 2--其他待扩展
	private Integer fundingType;//资金类型 0--红包  1---积分 2--金币
	private String outUserCode;//奖励来源，对应账户的usercode
	private Double amount;//奖励数量
	private String describe;//奖励描述
	private Integer vipRank;//奖励vip等级
	private String accountName;//账户名称
	private Date createTime;//创建时间
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getFundingType() {
		return fundingType;
	}
	public void setFundingType(Integer fundingType) {
		this.fundingType = fundingType;
	}
	
	public String getOutUserCode() {
		return outUserCode;
	}
	public void setOutUserCode(String outUserCode) {
		this.outUserCode = outUserCode;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public Integer getVipRank() {
		return vipRank;
	}
	public void setVipRank(Integer vipRank) {
		this.vipRank = vipRank;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
