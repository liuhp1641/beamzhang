package com.cm.game.http.bean;

import java.io.Serializable;
import java.util.List;
/**
 * 
 *  Class Name: GameInfoListForBack.java
 *  Function: 游戏日志集合（分页）  
 *  
 *  @author zhangqiang  DateTime 2015-1-16 下午1:32:48    
 *  @version 1.0
 */
public class GameInfoListForBack implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 4157088873319539826L;
	//当前页数
	private int pageId;
	//总页数
	private int pageTotal;
	//总条数
	private Long itemTotal;
	//总计记录数
	private String totalNums;
	//累计下注积分
	private String betTotals;
	//累计赢金
	private String winGoalTotal;
	//游戏日志集合
	private List<GameInfoForBack> gameInfoListForBack;
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
	public int getPageId() {
		return pageId;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	public int getPageTotal() {
		return pageTotal;
	}
	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}
	public Long getItemTotal() {
		return itemTotal;
	}
	public void setItemTotal(Long itemTotal) {
		this.itemTotal = itemTotal;
	}
	public List<GameInfoForBack> getGameInfoListForBack() {
		return gameInfoListForBack;
	}
	public void setGameInfoListForBack(List<GameInfoForBack> gameInfoListForBack) {
		this.gameInfoListForBack = gameInfoListForBack;
	}
}
