package com.cm.manage.vo.scoremall;

import java.io.Serializable;

/*
 * 
 *=============================================
 *  @ClassName: ScoreMallLogSum.java
 *	@desc:  抽奖日志统计
 *  
 *  @author: zqiang  DateTime 2014-12-10 下午2:34:11    
 *  @version: 1.0
 *  @tags :
 *=============================================
 */
public class ScoreMallLogSum implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = -2403667604700040375L;
	private long itemTotal;
	private double allPayNum;
	private double allAwardScoreNum;
	private double allAwardRedBagNum;
	public long getItemTotal() {
		return itemTotal;
	}
	public void setItemTotal(long itemTotal) {
		this.itemTotal = itemTotal;
	}
	public double getAllPayNum() {
		return allPayNum;
	}
	public void setAllPayNum(double allPayNum) {
		this.allPayNum = allPayNum;
	}
	public double getAllAwardScoreNum() {
		return allAwardScoreNum;
	}
	public void setAllAwardScoreNum(double allAwardScoreNum) {
		this.allAwardScoreNum = allAwardScoreNum;
	}
	public double getAllAwardRedBagNum() {
		return allAwardRedBagNum;
	}
	public void setAllAwardRedBagNum(double allAwardRedBagNum) {
		this.allAwardRedBagNum = allAwardRedBagNum;
	}
}
