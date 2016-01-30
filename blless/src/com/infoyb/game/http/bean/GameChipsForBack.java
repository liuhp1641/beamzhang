package com.cm.game.http.bean;

import java.io.Serializable;
/*
 * 
 *  Class Name: GameChipsForBack.java
 *  Function: 房间筹码设置   
 *  
 *  @author zhangqiang  DateTime 2015-1-11 上午10:51:19    
 *  @version 1.0
 */
public class GameChipsForBack implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -3845100610280972904L;
	//房间编号
	private String  roomCode;
	//筹码额度
	private int chipsCredits;
	//筹码级别
	private int leve;
	public int getLeve() {
		return leve;
	}
	public void setLeve(int leve) {
		this.leve = leve;
	}
	public String getRoomCode() {
		return roomCode;
	}
	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}
	public int getChipsCredits() {
		return chipsCredits;
	}
	public void setChipsCredits(int chipsCredits) {
		this.chipsCredits = chipsCredits;
	}
	@Override
	public String toString() {
		return "roomCode="+roomCode+" chipsCredits="+chipsCredits;
	}
	
}
