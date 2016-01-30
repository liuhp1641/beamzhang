package com.cm.scoreMall.http.bean;

import java.io.Serializable;

public class CCNVo4Back implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2979232977334884536L;
	private String commodityCode;//商品code
	private String commodityName;//商品名称
	public String getCommodityCode() {
		return commodityCode;
	}
	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	
	@Override
	public String toString() {
		return "[commodityCode="+commodityCode+" commodityName="+commodityName+"]";
	}
	
}
