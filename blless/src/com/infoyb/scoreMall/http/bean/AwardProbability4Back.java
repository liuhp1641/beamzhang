package com.cm.scoreMall.http.bean;

import java.io.Serializable;

public class AwardProbability4Back implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6974951795167019685L;
	private String apCode;//概率code
	private String commodityCode;//商品 code
	private double awardNum;//奖励数值
	private int awardProbability;//奖励概率
	public String getApCode() {
		return apCode;
	}
	public void setApCode(String apCode) {
		this.apCode = apCode;
	}
	public String getCommodityCode() {
		return commodityCode;
	}
	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
	}
	public double getAwardNum() {
		return awardNum;
	}
	public void setAwardNum(double awardNum) {
		this.awardNum = awardNum;
	}
	public int getAwardProbability() {
		return awardProbability;
	}
	public void setAwardProbability(int awardProbability) {
		this.awardProbability = awardProbability;
	}
	
	@Override
	public String toString() {
		return "apCode="+apCode+"   commodityCode="+commodityCode+"  awardNum="+awardNum+"  awardProbability="+awardProbability;
	}
}
