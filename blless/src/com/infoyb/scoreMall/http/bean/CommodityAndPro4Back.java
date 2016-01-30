package com.cm.scoreMall.http.bean;

import java.io.Serializable;
import java.util.List;

public class CommodityAndPro4Back implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1561733005544105993L;
	private CommodityVo4Back commodityVo4Back; //商品详情
	private List<AwardProbability4Back> awardProbability4BackList; //商品对应的概率列表
	public CommodityVo4Back getCommodityVo4Back() {
		return commodityVo4Back;
	}
	public void setCommodityVo4Back(CommodityVo4Back commodityVo4Back) {
		this.commodityVo4Back = commodityVo4Back;
	}
	
	public List<AwardProbability4Back> getAwardProbability4BackList() {
		return awardProbability4BackList;
	}
	public void setAwardProbability4BackList(
			List<AwardProbability4Back> awardProbability4BackList) {
		this.awardProbability4BackList = awardProbability4BackList;
	}
	@Override
	public String toString() {
		return "commodityVo4Back="+commodityVo4Back+" awardProbability4BackList --"+awardProbability4BackList;
	}
	
}
