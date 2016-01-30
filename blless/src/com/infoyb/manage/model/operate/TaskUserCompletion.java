package com.cm.manage.model.operate;
import java.io.Serializable;
import java.util.Date;

public class TaskUserCompletion implements Serializable {
	private static final long serialVersionUID = -7444470055569821822L;

	private Long id;
	private String userCode;
	private String userTaskId;// 用户任务的唯一标识
	private String taskId;// 任务的唯一标识
	private String name;// 任务名称
	private String describe;// 目标描述
	private Date startTime;// 任务开始时间
	private Date endTime;// 任务结束时间
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
	private Date acceptTime;// 任务接受时间
	private Date completeTime;// 任务完成时间
	private Date createTime;
	private Integer taskOrder;// 任务顺序

	public TaskUserCompletion() {

	}

	public TaskUserCompletion(Long id, String userCode, String userTaskId, String taskId, String name, String describe, Date startTime, Date endTime, Integer type, Integer taskType, Integer status,
			String dateLimitDesc, Integer isProgressDisplay, Integer times, Double totalAmount, String rewardDescribe, String taskDetail, Integer completeTimes, Double completeAmount,
			Date acceptTime, Date completeTime, Date createTime, Integer taskOrder) {
		super();
		this.id = id;
		this.userCode = userCode;
		this.userTaskId = userTaskId;
		this.taskId = taskId;
		this.name = name;
		this.describe = describe;
		this.startTime = startTime;
		this.endTime = endTime;
		this.type = type;
		this.taskType = taskType;
		this.status = status;
		this.dateLimitDesc = dateLimitDesc;
		this.isProgressDisplay = isProgressDisplay;
		this.times = times;
		this.totalAmount = totalAmount;
		this.rewardDescribe = rewardDescribe;
		this.taskDetail = taskDetail;
		this.completeTimes = completeTimes;
		this.completeAmount = completeAmount;
		this.acceptTime = acceptTime;
		this.completeTime = completeTime;
		this.createTime = createTime;
		this.taskOrder = taskOrder;
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	public Date getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getTaskOrder() {
		return taskOrder;
	}

	public void setTaskOrder(Integer taskOrder) {
		this.taskOrder = taskOrder;
	}
}
