package com.cm.manage.vo.operate;

import java.io.Serializable;
/***
 * @describe 任务相关
 * @author sunjf
 *
 */
public class TaskVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String taskId;//任务的唯一标识
	private String name;//任务名称
	private String describe;//任务描述
	private String startTime;//任务开始时间
	private String endTime;//任务结束时间
	private Integer type;//任务类型，如0-条件型 1-关联
	private Integer status;//任务状态  0-待发布 1-已上线 2-已暂停 3-已结束
	private Integer taskOrder;//任务顺序
	private String dateLimitDesc;//任务期限描述
	private Integer displayCondition;//显示条件，0-可见可做，1-可见不可做，2-不可见不可做,,如果选择0可见可做，则该任务则没有任何前置条件,即has_prefix_task为0
	private Integer receiveTimes;//可领取次数
	private Integer isProgressDisplay;//是否显示进度条 0 -不显示 1-显示
	private Integer hasPrefixCondition;//是否有前置显示条件 0-没有 1-有
	private String rewardTarget;//奖励目标
	private Integer completeNum;//完成条件数量
	
	private Integer prefixType;//前置条件类型  0-消费 1-充值 2-中奖
	private Integer prefixTimes;//次数，若type为0，则times表示消费次数，其他类推
	private Double prefixFirstAmount;//首次金额
	private Double prefixAmount;//单次金额，若type为0，则amount表示首次消费金额，其他类推
	private Double prefixTotalAmount;//累计金额，若type为0，则total_amount表示累计消费金额，其他类推
	private String prefixTaskId;//前置任务ID
	private Integer prefixVipRank;//前置条件中的vIP
	private Integer prefixListType;//前置条件名单类型
	private Integer prefixCompleteFlag;//前置条件验证类型
	private String prefixSid;//渠道号
	private String prefixSidName;//渠道名称
	
	
	private Integer postType;//后置条件类型  0-消费 1-充值 2-中奖 3-注册
	private Integer postTimes;//次数，若type为0，则times表示消费次数，其他类推 ，对注册无效
	private Double postAmount;//单次金额，若type为0，则amount表示首次消费金额，其他类推，对注册无效
	private Double postFirstAmount;//首次金额
	private Double postTotalAmount;//累计金额，若type为0，则total_amount表示累计消费金额，其他类推，对注册无效
	private Integer postCompleteFlag;//条件满足参数 0 手机号、1身份证、2姓名、3vip综合的验证  ,对注册类型的有效
	private Integer postVipRank;//完成条件中vip设置
	private Double postAmountHigh;//单次金额上限
	private Double postFirstAmountHigh;//首次金额上限
	private Integer postBuyType;//购买方式
	private Integer postFirstNoLottery;//首次不中奖
	
	
	private Integer rewardType;//奖励类型 0--资金 1--头衔 2--其他待扩展
	private Integer fundingType;//资金类型 0--红包  1---积分 2--金币
	private String outUserCode;//奖励来源，对应账户的usercode
	private String accountName;//账户名称
	private Double rewardAmount;//奖励数量
	private String rewardDescribe;//奖励描述
	
	private String userInfoValue;
	private String prefixTaskName;
	private String userName;
	private Double rechargeAmount;//充值金余额
	private Double presentAmount;//红包余额
	private Double bonusAmount;//奖金余额
	private Double score;//积分
	private Double gold;//金币
	private Double balance;
	private Integer rewardVipRank;//奖励给用户的vip等级
	
	private String sid;
	private String balanceText;
	private String startFromDate;//用于任务搜索查询的起始结束控制时间
	private String startToDate;
	private String endFromDate;
	private String endToDate;
	private boolean flag;//是否模糊查询
	
	private String taskRewardDescribe;//奖励描述
	private String taskDetail;//任务详情
	private String prefixLotteryType;//前置彩种code
	private String postLotteryType;
	private String prefixLotteryTypeName;//前置彩种名称
	private String postLotteryTypeName;
	private Integer phoneNoMust;
	private Integer nameMust;
	private Integer cardNoMust;
	private Integer vipRankMust;
	private Integer vipRank;//vip等级
	private String rewardAmountText;
	
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
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
	public Integer getPrefixType() {
		return prefixType;
	}
	public void setPrefixType(Integer prefixType) {
		this.prefixType = prefixType;
	}
	public Integer getPrefixTimes() {
		return prefixTimes;
	}
	public void setPrefixTimes(Integer prefixTimes) {
		this.prefixTimes = prefixTimes;
	}
	public Double getPrefixAmount() {
		return prefixAmount;
	}
	public void setPrefixAmount(Double prefixAmount) {
		this.prefixAmount = prefixAmount;
	}
	public Double getPrefixTotalAmount() {
		return prefixTotalAmount;
	}
	public void setPrefixTotalAmount(Double prefixTotalAmount) {
		this.prefixTotalAmount = prefixTotalAmount;
	}
	
	public String getPrefixTaskId() {
		return prefixTaskId;
	}
	public void setPrefixTaskId(String prefixTaskId) {
		this.prefixTaskId = prefixTaskId;
	}
	public Integer getPostType() {
		return postType;
	}
	public void setPostType(Integer postType) {
		this.postType = postType;
	}
	public Integer getPostTimes() {
		return postTimes;
	}
	public void setPostTimes(Integer postTimes) {
		this.postTimes = postTimes;
	}
	public Double getPostAmount() {
		return postAmount;
	}
	public void setPostAmount(Double postAmount) {
		this.postAmount = postAmount;
	}
	public Double getPostTotalAmount() {
		return postTotalAmount;
	}
	public void setPostTotalAmount(Double postTotalAmount) {
		this.postTotalAmount = postTotalAmount;
	}
	public Integer getPostCompleteFlag() {
		return postCompleteFlag;
	}
	public void setPostCompleteFlag(Integer postCompleteFlag) {
		this.postCompleteFlag = postCompleteFlag;
	}
	public Integer getRewardType() {
		return rewardType;
	}
	public void setRewardType(Integer rewardType) {
		this.rewardType = rewardType;
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
	public Double getRewardAmount() {
		return rewardAmount;
	}
	public void setRewardAmount(Double rewardAmount) {
		this.rewardAmount = rewardAmount;
	}
	public String getRewardDescribe() {
		return rewardDescribe;
	}
	public void setRewardDescribe(String rewardDescribe) {
		this.rewardDescribe = rewardDescribe;
	}
	public Integer getVipRank() {
		return vipRank;
	}
	public void setVipRank(Integer vipRank) {
		this.vipRank = vipRank;
	}
	public Double getPrefixFirstAmount() {
		return prefixFirstAmount;
	}
	public void setPrefixFirstAmount(Double prefixFirstAmount) {
		this.prefixFirstAmount = prefixFirstAmount;
	}
	public Double getPostFirstAmount() {
		return postFirstAmount;
	}
	public void setPostFirstAmount(Double postFirstAmount) {
		this.postFirstAmount = postFirstAmount;
	}
	public String getUserInfoValue() {
		return userInfoValue;
	}
	public void setUserInfoValue(String userInfoValue) {
		this.userInfoValue = userInfoValue;
	}
	public String getPrefixTaskName() {
		return prefixTaskName;
	}
	public void setPrefixTaskName(String prefixTaskName) {
		this.prefixTaskName = prefixTaskName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public Double getBonusAmount() {
		return bonusAmount;
	}
	public void setBonusAmount(Double bonusAmount) {
		this.bonusAmount = bonusAmount;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Double getGold() {
		return gold;
	}
	public void setGold(Double gold) {
		this.gold = gold;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getBalanceText() {
		return balanceText;
	}
	public void setBalanceText(String balanceText) {
		this.balanceText = balanceText;
	}
	public String getStartFromDate() {
		return startFromDate;
	}
	public void setStartFromDate(String startFromDate) {
		this.startFromDate = startFromDate;
	}
	public String getStartToDate() {
		return startToDate;
	}
	public void setStartToDate(String startToDate) {
		this.startToDate = startToDate;
	}
	public String getEndFromDate() {
		return endFromDate;
	}
	public void setEndFromDate(String endFromDate) {
		this.endFromDate = endFromDate;
	}
	public String getEndToDate() {
		return endToDate;
	}
	public void setEndToDate(String endToDate) {
		this.endToDate = endToDate;
	}
	public boolean getFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	public String getTaskRewardDescribe() {
		return taskRewardDescribe;
	}
	public void setTaskRewardDescribe(String taskRewardDescribe) {
		this.taskRewardDescribe = taskRewardDescribe;
	}
	public String getTaskDetail() {
		return taskDetail;
	}
	public void setTaskDetail(String taskDetail) {
		this.taskDetail = taskDetail;
	}
	public String getPrefixLotteryType() {
		return prefixLotteryType;
	}
	public void setPrefixLotteryType(String prefixLotteryType) {
		this.prefixLotteryType = prefixLotteryType;
	}
	public String getPostLotteryType() {
		return postLotteryType;
	}
	public void setPostLotteryType(String postLotteryType) {
		this.postLotteryType = postLotteryType;
	}
	public String getPrefixLotteryTypeName() {
		return prefixLotteryTypeName;
	}
	public void setPrefixLotteryTypeName(String prefixLotteryTypeName) {
		this.prefixLotteryTypeName = prefixLotteryTypeName;
	}
	public String getPostLotteryTypeName() {
		return postLotteryTypeName;
	}
	public void setPostLotteryTypeName(String postLotteryTypeName) {
		this.postLotteryTypeName = postLotteryTypeName;
	}
	
	public Integer getRewardVipRank() {
		return rewardVipRank;
	}
	public void setRewardVipRank(Integer rewardVipRank) {
		this.rewardVipRank = rewardVipRank;
	}
	public Integer getPrefixVipRank() {
		return prefixVipRank;
	}
	public void setPrefixVipRank(Integer prefixVipRank) {
		this.prefixVipRank = prefixVipRank;
	}
	
	public Integer getPrefixListType() {
		return prefixListType;
	}
	public void setPrefixListType(Integer prefixListType) {
		this.prefixListType = prefixListType;
	}
	public Integer getPrefixCompleteFlag() {
		return prefixCompleteFlag;
	}
	public void setPrefixCompleteFlag(Integer prefixCompleteFlag) {
		this.prefixCompleteFlag = prefixCompleteFlag;
	}
	public Integer getPostVipRank() {
		return postVipRank;
	}
	public void setPostVipRank(Integer postVipRank) {
		this.postVipRank = postVipRank;
	}
	public String getRewardTarget() {
		return rewardTarget;
	}
	public void setRewardTarget(String rewardTarget) {
		this.rewardTarget = rewardTarget;
	}
	public Double getPostAmountHigh() {
		return postAmountHigh;
	}
	public void setPostAmountHigh(Double postAmountHigh) {
		this.postAmountHigh = postAmountHigh;
	}
	
	public Double getPostFirstAmountHigh() {
		return postFirstAmountHigh;
	}
	public void setPostFirstAmountHigh(Double postFirstAmountHigh) {
		this.postFirstAmountHigh = postFirstAmountHigh;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Integer getPhoneNoMust() {
		return phoneNoMust;
	}
	public void setPhoneNoMust(Integer phoneNoMust) {
		this.phoneNoMust = phoneNoMust;
	}
	public Integer getNameMust() {
		return nameMust;
	}
	public void setNameMust(Integer nameMust) {
		this.nameMust = nameMust;
	}
	public Integer getCardNoMust() {
		return cardNoMust;
	}
	public void setCardNoMust(Integer cardNoMust) {
		this.cardNoMust = cardNoMust;
	}
	public Integer getVipRankMust() {
		return vipRankMust;
	}
	public void setVipRankMust(Integer vipRankMust) {
		this.vipRankMust = vipRankMust;
	}
	public Integer getPostBuyType() {
		return postBuyType;
	}
	public void setPostBuyType(Integer postBuyType) {
		this.postBuyType = postBuyType;
	}
	public Integer getPostFirstNoLottery() {
		return postFirstNoLottery;
	}
	public void setPostFirstNoLottery(Integer postFirstNoLottery) {
		this.postFirstNoLottery = postFirstNoLottery;
	}
	public String getPrefixSid() {
		return prefixSid;
	}
	public void setPrefixSid(String prefixSid) {
		this.prefixSid = prefixSid;
	}
	public String getPrefixSidName() {
		return prefixSidName;
	}
	public void setPrefixSidName(String prefixSidName) {
		this.prefixSidName = prefixSidName;
	}
	public Integer getCompleteNum() {
		return completeNum;
	}
	public void setCompleteNum(Integer completeNum) {
		this.completeNum = completeNum;
	}
	public String getRewardAmountText() {
		return rewardAmountText;
	}
	public void setRewardAmountText(String rewardAmountText) {
		this.rewardAmountText = rewardAmountText;
	}
	
	
}
