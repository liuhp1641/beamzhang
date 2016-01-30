package com.cm.manage.vo.helpCenter;

import java.io.Serializable;

public class QuestionSum implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long allNum;
	
	private long untreatedNum;
	
	private long treatedNum;

	public long getAllNum() {
		return allNum;
	}

	public void setAllNum(long allNum) {
		this.allNum = allNum;
	}

	public long getUntreatedNum() {
		return untreatedNum;
	}

	public void setUntreatedNum(long untreatedNum) {
		this.untreatedNum = untreatedNum;
	}

	public long getTreatedNum() {
		return treatedNum;
	}

	public void setTreatedNum(long treatedNum) {
		this.treatedNum = treatedNum;
	}

	
	
	
	
}
