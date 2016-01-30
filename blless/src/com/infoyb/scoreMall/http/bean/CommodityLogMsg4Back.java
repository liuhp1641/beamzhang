package com.cm.scoreMall.http.bean;

import java.io.Serializable;
import java.util.List;

public class CommodityLogMsg4Back implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 3015663147625756099L;

	private List<CommodityLogVo4Back> commodityLogVo4BackList;//商品log列表
	private long itemTotal;//总条数
	private long allPayNum;//付款总和
	private double allAwardScoreNum;//获得积分总和
	private double allAwardRedBagNum;//获得红包总和
	public List<CommodityLogVo4Back> getCommodityLogVo4BackList() {
		return commodityLogVo4BackList;
	}
	public void setCommodityLogVo4BackList(
			List<CommodityLogVo4Back> commodityLogVo4BackList) {
		this.commodityLogVo4BackList = commodityLogVo4BackList;
	}
	public long getAllPayNum() {
		return allPayNum;
	}
	public void setAllPayNum(long allPayNum) {
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
	
	@Override
	public String toString() {
		return "commodityLogVo4BackList="+commodityLogVo4BackList+" allPayNum="+allPayNum+" allAwardScoreNum="+allAwardScoreNum+" allAwardRedBagNum="+allAwardRedBagNum;
	}
	public long getItemTotal() {
		return itemTotal;
	}
	public void setItemTotal(long itemTotal) {
		this.itemTotal = itemTotal;
	}
}
