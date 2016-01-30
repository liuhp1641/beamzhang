package com.cm.scoreMall.http.bean;

import java.io.Serializable;
import java.util.List;

public class CommodityMsg4Back implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6288956591223889422L;
	private List<CommodityVo4Back>  commodityVo4BacksList;//商品列表
	private int pageId;// 当前页数
	private int pageTotal;// 总页数
	private Long itemTotal;// 总条数
	public List<CommodityVo4Back> getCommodityVo4BacksList() {
		return commodityVo4BacksList;
	}
	public void setCommodityVo4BacksList(
			List<CommodityVo4Back> commodityVo4BacksList) {
		this.commodityVo4BacksList = commodityVo4BacksList;
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
	
	@Override
	public String toString() {
		return "commodityVo4BacksList="+commodityVo4BacksList+" itemTotal="+itemTotal;
	}
}
