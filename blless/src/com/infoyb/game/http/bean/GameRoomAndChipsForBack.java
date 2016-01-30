package com.cm.game.http.bean;

import java.io.Serializable;
import java.util.List;
/**
 * 
 *  Class Name: GameRoomAndChipsForBack.java
 *  Function: 21点游戏房间筹码设置组合实体类   
 *  
 *  @author zhangqiang  DateTime 2015-1-11 上午10:55:50    
 *  @version 1.0
 */
public class GameRoomAndChipsForBack implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 6032852158242971308L;
	//21点游戏房间详细信息
	private GameRoomForBack gameRoomForBack;
	//21点游戏筹码设置列表
	private List<GameChipsForBack> gameChipsForBackList;
	public GameRoomForBack getGameRoomForBack() {
		return gameRoomForBack;
	}
	public void setGameRoomForBack(GameRoomForBack gameRoomForBack) {
		this.gameRoomForBack = gameRoomForBack;
	}
	public List<GameChipsForBack> getGameChipsForBackList() {
		return gameChipsForBackList;
	}
	public void setGameChipsForBackList(List<GameChipsForBack> gameChipsForBackList) {
		this.gameChipsForBackList = gameChipsForBackList;
	}
}
