package com.cm.game.http.bean;

import java.io.Serializable;
/*
 * 
 *  Class Name: GameLogsStatistics.java
 *  Function: 游戏日志统计  
 *  
 *  @author zhangqiang  DateTime 2015-1-15 下午4:27:21    
 *  @version 1.0
 */
public class GameLogsStatisticsForBack implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -9080615458697191267L;
	//总计记录数
	private String totalNums;
	//累计下注积分
	private String betTotals;
	//累计赢金
	private String winGoalTotal;
	public String getTotalNums() {
		return totalNums;
	}
	public void setTotalNums(String totalNums) {
		this.totalNums = totalNums;
	}
	public String getBetTotals() {
		return betTotals;
	}
	public void setBetTotals(String betTotals) {
		this.betTotals = betTotals;
	}
	public String getWinGoalTotal() {
		return winGoalTotal;
	}
	public void setWinGoalTotal(String winGoalTotal) {
		this.winGoalTotal = winGoalTotal;
	}
}
