package com.cm.manage.model.operate;

import java.io.Serializable;
import java.util.Date;
/***
 * @describe 任务完成条件
 * @date 2014-10-16
 * @author sunjf
 *
 */      

public class TaskPost implements Serializable{

	private static final long serialVersionUID = 2322182010522528478L;
	
	private Long id;
	private String taskId;//任务的唯一标识
	private Integer type;//前置条件类型  0-消费， 1-充值 ，2-中奖 ，3-注册，4-不中奖
	private Integer times;//次数，若type为0，则times表示消费次数，其他类推 ，对注册无效
	private Double amount;//单次金额下限，若type为0，则amount表示首次消费金额，其他类推，对注册无效
	private Double totalAmount;//累计金额，若type为0，则total_amount表示累计消费金额，其他类推，对注册无效
	private Integer completeFlag;//条件满足参数 0无限制  手机号1，身份证2，姓名3，vip4验证  ,对注册类型的有效
	private Double firstAmountHigh;//首次金额上限
	private String lotteryType;//彩种
	private String lotteryTypeName;//彩种名称
	private Integer vipRank;//vip等级0,1,2,3..
	private Double firstAmount;//首次金额下限
	private Double amountHigh;//单次金额上限
	private Integer buyType;//购买方式 0全部1代购2合买3追号
	private Integer firstNoLottery;//首次不中奖1 ，无限制0
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
	public Integer getCompleteFlag() {
		return completeFlag;
	}
	public void setCompleteFlag(Integer completeFlag) {
		this.completeFlag = completeFlag;
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
	public Double getFirstAmountHigh() {
		return firstAmountHigh;
	}
	public void setFirstAmountHigh(Double firstAmountHigh) {
		this.firstAmountHigh = firstAmountHigh;
	}
	public Double getAmountHigh() {
		return amountHigh;
	}
	public void setAmountHigh(Double amountHigh) {
		this.amountHigh = amountHigh;
	}
	public Integer getBuyType() {
		return buyType;
	}
	public void setBuyType(Integer buyType) {
		this.buyType = buyType;
	}
	public Integer getFirstNoLottery() {
		return firstNoLottery;
	}
	public void setFirstNoLottery(Integer firstNoLottery) {
		this.firstNoLottery = firstNoLottery;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
	
}
