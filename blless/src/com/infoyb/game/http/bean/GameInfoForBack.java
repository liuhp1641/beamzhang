package com.cm.game.http.bean;

import java.io.Serializable;
import java.util.List;
/*
 * 
 *  Class Name: GameInfoForBack.java
 *  Function: 游戏日志后台bean类  
 *  
 *  @author zhangqiang  DateTime 2015-1-16 上午9:32:13    
 *  @version 1.0
 */
public class GameInfoForBack implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -7353265851577167876L;
	//牌局编号（流水编号）
	private String gameCode;
	//房间名称
	private String roomName;
	//用户编号
	private String userCode;
	//用户code列表
	private List<String> userCodeList;
	//用户名称
	private String userName;
	//是否加倍 (0未加倍 1加倍)
	private String hasDouble;
	//是否分派 (0未分牌 1分牌)
	private String hasSeparated;
	//牌局胜负
	private String result;
	//开始时间
	private String beginTime;
	//开始时间的起始时间（用于查询）
	private String beginTimeBegin;
	//开始时间的截至时间（用于查询）
	private String beginTimeEnd;
	//结束时间
	private String endTime;
	//结束时间的起始时间（用于查询）
	private String endTimeBegin;
	//结束时间的截止时间（用于查询）
	private String endTimeEnd;
	//筹码类型（由房间关联）1-积分，2-金币
	private String accountType;
	//下注筹码
	private String betScore;
	//下注筹码汇总
	private String betScoreSum;
	//玩家点数
	private String bankerPokerPoint;
	//庄家点数
	private String playerPokerPoint;
	//赢金
	private String winScore;
	//筹码组合
	private String betScoreCombination;
	//是否模糊查询  0-模糊查询，1-精确查询
	private String isVague;
	private String sort;
	private String order;
	//账户名称
	private String accountName;
	//玩家手牌
	private String bankerPokerContent;
    // 庄家手牌
    private String playerPokerContent;
	public String getBankerPokerContent() {
		return bankerPokerContent;
	}
	public void setBankerPokerContent(String bankerPokerContent) {
		this.bankerPokerContent = bankerPokerContent;
	}
	public String getPlayerPokerContent() {
		return playerPokerContent;
	}
	public void setPlayerPokerContent(String playerPokerContent) {
		this.playerPokerContent = playerPokerContent;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getGameCode() {
		return gameCode;
	}
	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public List<String> getUserCodeList() {
		return userCodeList;
	}
	public void setUserCodeList(List<String> userCodeList) {
		this.userCodeList = userCodeList;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHasDouble() {
		return hasDouble;
	}
	public void setHasDouble(String hasDouble) {
		this.hasDouble = hasDouble;
	}
	public String getHasSeparated() {
		return hasSeparated;
	}
	public void setHasSeparated(String hasSeparated) {
		this.hasSeparated = hasSeparated;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getBeginTimeBegin() {
		return beginTimeBegin;
	}
	public void setBeginTimeBegin(String beginTimeBegin) {
		this.beginTimeBegin = beginTimeBegin;
	}
	public String getBeginTimeEnd() {
		return beginTimeEnd;
	}
	public void setBeginTimeEnd(String beginTimeEnd) {
		this.beginTimeEnd = beginTimeEnd;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getEndTimeBegin() {
		return endTimeBegin;
	}
	public void setEndTimeBegin(String endTimeBegin) {
		this.endTimeBegin = endTimeBegin;
	}
	public String getEndTimeEnd() {
		return endTimeEnd;
	}
	public void setEndTimeEnd(String endTimeEnd) {
		this.endTimeEnd = endTimeEnd;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getBetScore() {
		return betScore;
	}
	public void setBetScore(String betScore) {
		this.betScore = betScore;
	}
	public String getBetScoreSum() {
		return betScoreSum;
	}
	public void setBetScoreSum(String betScoreSum) {
		this.betScoreSum = betScoreSum;
	}
	public String getBankerPokerPoint() {
		return bankerPokerPoint;
	}
	public void setBankerPokerPoint(String bankerPokerPoint) {
		this.bankerPokerPoint = bankerPokerPoint;
	}
	public String getPlayerPokerPoint() {
		return playerPokerPoint;
	}
	public void setPlayerPokerPoint(String playerPokerPoint) {
		this.playerPokerPoint = playerPokerPoint;
	}
	public String getWinScore() {
		return winScore;
	}
	public void setWinScore(String winScore) {
		this.winScore = winScore;
	}
	public String getBetScoreCombination() {
		return betScoreCombination;
	}
	public void setBetScoreCombination(String betScoreCombination) {
		this.betScoreCombination = betScoreCombination;
	}
	public String getIsVague() {
		return isVague;
	}
	public void setIsVague(String isVague) {
		this.isVague = isVague;
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
}
