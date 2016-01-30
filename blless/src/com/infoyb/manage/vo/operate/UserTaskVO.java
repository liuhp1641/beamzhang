package com.cm.manage.vo.operate;

import java.io.Serializable;

public class UserTaskVO implements Serializable{

	private static final long serialVersionUID = -7572953903324145336L;
	
	private String userCode;
	private String userTaskId;// 用户任务的唯一标识
	private String taskId;// 任务的唯一标识
	private String name;// 任务名称
	private String describe;// 目标描述
	private String startTime;// 任务开始时间
	private String endTime;// 任务结束时间
	private Integer type;// 任务类型，如0-条件型 1-关联
	private Integer taskType;// 任务类型，0.消费 1.充值 2.中奖 3个人资料
	private Integer status;// 1.进行中 2.已完成 3.已领取奖励 4.不可接 5已结束
	private String dateLimitDesc;// 任务期限描述
	private Integer isProgressDisplay;// 是否显示进度条 0 -不显示 1-显示
	private Integer times;// 次数，若type为0，则times表示消费次数，其他类推 ，对注册无效
	private Double totalAmount;// 累计金额，若type为0，则total_amount表示累计消费金额，其他类推，对注册无效
	private String rewardDescribe;// 任务奖励描述
	private String taskDetail;// 任务详情
	private Integer completeTimes;// 完成次数
	private Double completeAmount;// 完成金额
	private String acceptTime;// 任务接受时间
	private String completeTime;// 任务完成时间
	private String createTime;
	private Integer taskOrder;// 任务顺序
	private boolean flag;//是否模糊查询
	private String userName;
	private String rewardAccountName;//奖励来源
	private Integer funddingType;//资金类型
	private String startAcceptDate;
	private String endAcceptDate;
	private String startCompleteDate;
	private String endCompleteDate;
	private String statusName;
	private String funddingTypeName;
	private Double rewardAmount;//奖励金额
	private String rewardAmountText;//奖励金额Text
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserTaskId() {
		return userTaskId;
	}
	public void setUserTaskId(String userTaskId) {
		this.userTaskId = userTaskId;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getTaskType() {
		return taskType;
	}
	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDateLimitDesc() {
		return dateLimitDesc;
	}
	public void setDateLimitDesc(String dateLimitDesc) {
		this.dateLimitDesc = dateLimitDesc;
	}
	public Integer getIsProgressDisplay() {
		return isProgressDisplay;
	}
	public void setIsProgressDisplay(Integer isProgressDisplay) {
		this.isProgressDisplay = isProgressDisplay;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getRewardDescribe() {
		return rewardDescribe;
	}
	public void setRewardDescribe(String rewardDescribe) {
		this.rewardDescribe = rewardDescribe;
	}
	public String getTaskDetail() {
		return taskDetail;
	}
	public void setTaskDetail(String taskDetail) {
		this.taskDetail = taskDetail;
	}
	public Integer getCompleteTimes() {
		return completeTimes;
	}
	public void setCompleteTimes(Integer completeTimes) {
		this.completeTimes = completeTimes;
	}
	public Double getCompleteAmount() {
		return completeAmount;
	}
	public void setCompleteAmount(Double completeAmount) {
		this.completeAmount = completeAmount;
	}
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getAcceptTime() {
		return acceptTime;
	}
	public void setAcceptTime(String acceptTime) {
		this.acceptTime = acceptTime;
	}
	public String getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getTaskOrder() {
		return taskOrder;
	}
	public void setTaskOrder(Integer taskOrder) {
		this.taskOrder = taskOrder;
	}
	public boolean getFlag() {
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
	public String getRewardAccountName() {
		return rewardAccountName;
	}
	public void setRewardAccountName(String rewardAccountName) {
		this.rewardAccountName = rewardAccountName;
	}
	public Integer getFunddingType() {
		return funddingType;
	}
	public void setFunddingType(Integer funddingType) {
		this.funddingType = funddingType;
	}
	public String getStartAcceptDate() {
		return startAcceptDate;
	}
	public void setStartAcceptDate(String startAcceptDate) {
		this.startAcceptDate = startAcceptDate;
	}
	public String getEndAcceptDate() {
		return endAcceptDate;
	}
	public void setEndAcceptDate(String endAcceptDate) {
		this.endAcceptDate = endAcceptDate;
	}
	public String getStartCompleteDate() {
		return startCompleteDate;
	}
	public void setStartCompleteDate(String startCompleteDate) {
		this.startCompleteDate = startCompleteDate;
	}
	public String getEndCompleteDate() {
		return endCompleteDate;
	}
	public void setEndCompleteDate(String endCompleteDate) {
		this.endCompleteDate = endCompleteDate;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getFunddingTypeName() {
		return funddingTypeName;
	}
	public void setFunddingTypeName(String funddingTypeName) {
		this.funddingTypeName = funddingTypeName;
	}
	public Double getRewardAmount() {
		return rewardAmount;
	}
	public void setRewardAmount(Double rewardAmount) {
		this.rewardAmount = rewardAmount;
	}
	public String getRewardAmountText() {
		return rewardAmountText;
	}
	public void setRewardAmountText(String rewardAmountText) {
		this.rewardAmountText = rewardAmountText;
	}
	
	

}
