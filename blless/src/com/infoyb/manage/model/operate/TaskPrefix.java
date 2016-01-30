package com.cm.manage.model.operate;

import java.io.Serializable;
import java.util.Date;
/***
 * @describe 任务前置条件
 * @date 2014-10-16
 * @author sunjf
 *
 */
public class TaskPrefix implements Serializable {


	private static final long serialVersionUID = -9029653288225062792L;
	
	private Long id;
	private String taskId;//任务的唯一标识
	private Integer type;//前置条件类型  0-消费 1-充值 2-中奖
	private Integer times;//次数，若type为0，则times表示消费次数，其他类推
	private Double amount;//单次金额，若type为0，则amount表示首次消费金额，其他类推
	private Double totalAmount;//累计金额，若type为0，则total_amount表示累计消费金额，其他类推
	private String prefixTaskId;//前置任务ID
	private String prefixTaskName;//前置任务名称
	private Double firstAmount;//首次金额
	private String lotteryType;//彩种
	private String lotteryTypeName;//彩种名称
	private Integer vipRank;//vip等级
	private Integer listType;//前置任务名单类型,0--白名单 1--黑名单
	private Integer completeFlag;//条件标志 0-手机1--姓名2身份证3--vip4
	private String sid;//渠道号
	private String sidName;//渠道名称
	private Date createTime;//创建时间
	private String backup1;
	private String backup2;
	private String backup3;
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
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public String getPrefixTaskId() {
		return prefixTaskId;
	}
	public void setPrefixTaskId(String prefixTaskId) {
		this.prefixTaskId = prefixTaskId;
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
	public Double getFirstAmount() {
		return firstAmount;
	}
	public void setFirstAmount(Double firstAmount) {
		this.firstAmount = firstAmount;
	}
	public String getLotteryType() {
		return lotteryType;
	}
	public void setLotteryType(String lotteryType) {
		this.lotteryType = lotteryType;
	}
	public String getLotteryTypeName() {
		return lotteryTypeName;
	}
	public void setLotteryTypeName(String lotteryTypeName) {
		this.lotteryTypeName = lotteryTypeName;
	}
	public Integer getVipRank() {
		return vipRank;
	}
	public void setVipRank(Integer vipRank) {
		this.vipRank = vipRank;
	}
	public Integer getListType() {
		return listType;
	}
	public void setListType(Integer listType) {
		this.listType = listType;
	}
	public Integer getCompleteFlag() {
		return completeFlag;
	}
	public void setCompleteFlag(Integer completeFlag) {
		this.completeFlag = completeFlag;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSidName() {
		return sidName;
	}
	public void setSidName(String sidName) {
		this.sidName = sidName;
	}
	public String getPrefixTaskName() {
		return prefixTaskName;
	}
	public void setPrefixTaskName(String prefixTaskName) {
		this.prefixTaskName = prefixTaskName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
