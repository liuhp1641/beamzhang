package com.cm.game.http.bean;

import java.io.Serializable;
import java.util.List;
/**
 * 
 *  Class Name: GameRoomListForBack.java
 *  Function: 房间信息的查询后对象   
 *  
 *  @author zhangqiang  DateTime 2015-1-11 上午11:04:29    
 *  @version 1.0
 */
public class GameRoomListForBack implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -7327166435505694275L;
	//房间信息列表
	private List<GameRoomForBack> gameRoomForBackList;
	//当前页数
	private int pageId;
	//总页数
	private int pageTotal;
	//总条数
	private Long itemTotal;
	public List<GameRoomForBack> getGameRoomForBackList() {
		return gameRoomForBackList;
	}
	public void setGameRoomForBackList(List<GameRoomForBack> gameRoomForBackList) {
		this.gameRoomForBackList = gameRoomForBackList;
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
}
