package com.cm.game.http.bean;

import java.io.Serializable;
/*
 * 
 *  Class Name: GameRoomForBack.java
 *  Function: 21点游戏房间  
 *  
 *  @author zhangqiang  DateTime 2015-1-11 上午10:43:47    
 *  @version 1.0
 */
public class GameRoomForBack implements Serializable {
	private static final long serialVersionUID = 5851336260728320687L;
	//房间编号
	private String roomCode;
	//房间名称
	private String roomName;
	//筹码上线
	private int chipsOnline;
	//筹码下线
	private int chipsOffline;
	//状态 1-待开始，2-隐藏，3-显示，4-进行中，5-已结束；
	private int state;
	//房间权重
	private int weight;
	//开始时间
	private String beginDate;
	//开始时间最新小值
	private String beginDateStart;
	//开始时间最大值
	private String beginDateEnd;
	//结束时间
	private String endDate;
	//结束时间最小值
	private String endDateStart;
	//结束时间最大值
	private String endDateEnd;
	//创建时间
	private String createDate;
	//创建时间最小值
	private String createDateStart;
	//创建时间最大值
	private String createDateEnd;
	//账户类型 1-积分，2-金币
	private int accountType;
	//账户余额范围最小值
	private int accountBanMin;
	//账户余额范围最大值
	private int accountBanMax;
	//庄家账户
	private String accountNo;
	//账户名称
	private String accountName;
	//注册来源
	private int signSource;
	//VIP级别最小值
	private int VIPLeveMin;
	//VIP级别最大值
	private int VIPLeveMax;
	//是否可参与
	private int isJoin;
	//筹码设置
	private String chipsStr;
	//筹码级别
	private String chipsLeveStr;
	//是否模糊查询  0-模糊查询，1-精确查询
	private int isVague;
	//是否过滤下线 1-过滤 2-不过滤
	private int isOffLineFlag;
	private String sort;
	private String order;
	public String getChipsLeveStr() {
		return chipsLeveStr;
	}
	public void setChipsLeveStr(String chipsLeveStr) {
		this.chipsLeveStr = chipsLeveStr;
	}
	public int getIsOffLineFlag() {
		return isOffLineFlag;
	}
	public void setIsOffLineFlag(int isOffLineFlag) {
		this.isOffLineFlag = isOffLineFlag;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getChipsStr() {
		return chipsStr;
	}
	public void setChipsStr(String chipsStr) {
		this.chipsStr = chipsStr;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public int getChipsOnline() {
		return chipsOnline;
	}
	public void setChipsOnline(int chipsOnline) {
		this.chipsOnline = chipsOnline;
	}
	public int getChipsOffline() {
		return chipsOffline;
	}
	public void setChipsOffline(int chipsOffline) {
		this.chipsOffline = chipsOffline;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getIsVague() {
		return isVague;
	}
	public void setIsVague(int isVague) {
		this.isVague = isVague;
	}
	public String getCreateDateStart() {
		return createDateStart;
	}
	public void setCreateDateStart(String createDateStart) {
		this.createDateStart = createDateStart;
	}
	public String getCreateDateEnd() {
		return createDateEnd;
	}
	public void setCreateDateEnd(String createDateEnd) {
		this.createDateEnd = createDateEnd;
	}
	public String getBeginDateStart() {
		return beginDateStart;
	}
	public void setBeginDateStart(String beginDateStart) {
		this.beginDateStart = beginDateStart;
	}
	public String getBeginDateEnd() {
		return beginDateEnd;
	}
	public void setBeginDateEnd(String beginDateEnd) {
		this.beginDateEnd = beginDateEnd;
	}
	public String getEndDateStart() {
		return endDateStart;
	}
	public void setEndDateStart(String endDateStart) {
		this.endDateStart = endDateStart;
	}
	public String getEndDateEnd() {
		return endDateEnd;
	}
	public void setEndDateEnd(String endDateEnd) {
		this.endDateEnd = endDateEnd;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getAccountType() {
		return accountType;
	}
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
	public int getAccountBanMin() {
		return accountBanMin;
	}
	public void setAccountBanMin(int accountBanMin) {
		this.accountBanMin = accountBanMin;
	}
	public int getAccountBanMax() {
		return accountBanMax;
	}
	public void setAccountBanMax(int accountBanMax) {
		this.accountBanMax = accountBanMax;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public int getSignSource() {
		return signSource;
	}
	public void setSignSource(int signSource) {
		this.signSource = signSource;
	}
	public int getVIPLeveMin() {
		return VIPLeveMin;
	}
	public void setVIPLeveMin(int vIPLeveMin) {
		VIPLeveMin = vIPLeveMin;
	}
	public int getVIPLeveMax() {
		return VIPLeveMax;
	}
	public void setVIPLeveMax(int vIPLeveMax) {
		VIPLeveMax = vIPLeveMax;
	}
	public int getIsJoin() {
		return isJoin;
	}
	public void setIsJoin(int isJoin) {
		this.isJoin = isJoin;
	}
	@Override
	public String toString() {
		return "roomCode="+roomCode+" room_name="+roomName+" chipsOffline="+chipsOffline+" chipsOnline="+chipsOnline+" state="+state+" weight="+weight+" beginDateStart="+beginDateStart+
				" beginDateEnd="+beginDateEnd+" endDateStart="+endDateStart+" endDateEnd="+endDateEnd+" createDateStart="+createDateStart+" createDateEnd="+createDateEnd;
	}
}
