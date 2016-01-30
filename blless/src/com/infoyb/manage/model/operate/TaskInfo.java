package com.cm.manage.model.operate;

import java.io.Serializable;
import java.util.Date;
/***
 * @describe 任务主表
 * @date 2014-10-16
 * @author sunjf
 *
 */
public class TaskInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1942874307570993602L;
	private Long id;
	private String taskId;//任务的唯一标识
	private String name;//任务名称
	private String describe;//任务描述
	private Date startTime;//任务开始时间
	private Date endTime;//任务结束时间
	private Integer type;//任务类型，如0-条件型 1-关联
	private Integer status;//任务状态  0-待发布 1-已上线 2-已暂停 3-已结束
	private Integer taskOrder;//任务顺序
	private String dateLimitDesc;//任务期限描述
	private Integer displayCondition;//显示条件，0-可见可做，1-可见不可做，2-不可见不可做,,如果选择0可见可做，则该任务则没有任何前置条件,即has_prefix_task为0
	private Integer receiveTimes;//可领取次数
	private Integer isProgressDisplay;//是否显示进度条 0 -不显示 1-显示
	private Integer hasPrefixCondition;//是否有前置显示条件 0-没有 1-有
	private String rewardDescribe;//任务奖励描述
	private String taskDetail;//任务详情
	private String rewardTarget;//奖励目标
	private Integer completeNum;//完成条件数量
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getTaskOrder() {
		return taskOrder;
	}
	public void setTaskOrder(Integer taskOrder) {
		this.taskOrder = taskOrder;
	}
	public String getDateLimitDesc() {
		return dateLimitDesc;
	}
	public void setDateLimitDesc(String dateLimitDesc) {
		this.dateLimitDesc = dateLimitDesc;
	}
	public Integer getDisplayCondition() {
		return displayCondition;
	}
	public void setDisplayCondition(Integer displayCondition) {
		this.displayCondition = displayCondition;
	}
	public Integer getReceiveTimes() {
		return receiveTimes;
	}
	public void setReceiveTimes(Integer receiveTimes) {
		this.receiveTimes = receiveTimes;
	}
	public Integer getIsProgressDisplay() {
		return isProgressDisplay;
	}
	public void setIsProgressDisplay(Integer isProgressDisplay) {
		this.isProgressDisplay = isProgressDisplay;
	}
	public Integer getHasPrefixCondition() {
		return hasPrefixCondition;
	}
	public void setHasPrefixCondition(Integer hasPrefixCondition) {
		this.hasPrefixCondition = hasPrefixCondition;
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
	public String getRewardTarget() {
		return rewardTarget;
	}
	public void setRewardTarget(String rewardTarget) {
		this.rewardTarget = rewardTarget;
	}
	public Integer getCompleteNum() {
		return completeNum;
	}
	public void setCompleteNum(Integer completeNum) {
		this.completeNum = completeNum;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
