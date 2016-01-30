package com.cm.scoreMall.http.bean;

import java.io.Serializable;
import java.util.Date;

public class CommodityVo4Back implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7462298485515337975L;
	private String commodityCode;//商品code
	private Date createDate;//创建日期
	private Date publishDate;//发布时间
	private Date beginDate;//开始时间
	private Date endDate;//结束时间
	private Date offLineDate;//下线时间
	
	private String createDateStr;//创建日期 字符串
	private String publishDateStr;//发布时间 字符串
	private String beginDateStr;//开始时间字符串
	private String endDateStr;//结束时间 字符串
	private String offLineDateStr;//下线时间 字符串
	
	private String commodityName;//商品名称
	private int payNum;//支付积分
	private double awardNumMin;//奖品金额最小值
	private double awardNumMax;//奖品金额最大值
	private int awardType;//0为积分，1为红包，2为金币
	private int state;//0为待上线，1为上线，2为下线 ,3为待开始
	private int partakeNum;//参与次数 0为无限制
	private int partakeDateType;//参与日期类型 0为不限制，1为天，2为星期，3为月
	private int weight;//权重
	private int termFlag;//1为短期，0为长期
	private String accountCode; //转账账户code
	private String accountName;//转账账户name
	private int style;
	
	private String createDateBegin;//创建日期 开始
	private String publishDateBegin;//发布时间 开始
	private String beginDateBegin;//开始时间 开始
	private String endDateBegin;//结束时间 开始
	
	private String createDateEnd;//创建日期 结束
	private String publishDateEnd;//发布时间 结束
	private String beginDateEnd;//开始时间 结束
	private String endDateEnd;//结束时间 结束
	
	private int isVague;//是否模糊查询  0为否1为是
	
	private int isOffLineFalg;//是否过滤下线 0为否  1为是
	
	private String sort;
	private String order;
	
	
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getCommodityCode() {
		return commodityCode;
	}
	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
	}

	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public int getPayNum() {
		return payNum;
	}
	public void setPayNum(int payNum) {
		this.payNum = payNum;
	}
	public double getAwardNumMin() {
		return awardNumMin;
	}
	public void setAwardNumMin(double awardNumMin) {
		this.awardNumMin = awardNumMin;
	}
	public double getAwardNumMax() {
		return awardNumMax;
	}
	public void setAwardNumMax(double awardNumMax) {
		this.awardNumMax = awardNumMax;
	}
	public int getAwardType() {
		return awardType;
	}
	public void setAwardType(int awardType) {
		this.awardType = awardType;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getPartakeNum() {
		return partakeNum;
	}
	public void setPartakeNum(int partakeNum) {
		this.partakeNum = partakeNum;
	}
	public int getPartakeDateType() {
		return partakeDateType;
	}
	public void setPartakeDateType(int partakeDateType) {
		this.partakeDateType = partakeDateType;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public Date getOffLineDate() {
		return offLineDate;
	}
	public void setOffLineDate(Date offLineDate) {
		this.offLineDate = offLineDate;
	}
	public String getOffLineDateStr() {
		return offLineDateStr;
	}
	public void setOffLineDateStr(String offLineDateStr) {
		this.offLineDateStr = offLineDateStr;
	}
	public int getTermFlag() {
		return termFlag;
	}
	public void setTermFlag(int termFlag) {
		this.termFlag = termFlag;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	
	
	public String getCreateDateStr() {
		return createDateStr;
	}
	public void setCreateDateStr(String createDateStr) {
		this.createDateStr = createDateStr;
	}
	public String getPublishDateStr() {
		return publishDateStr;
	}
	public void setPublishDateStr(String publishDateStr) {
		this.publishDateStr = publishDateStr;
	}
	public String getBeginDateStr() {
		return beginDateStr;
	}
	public void setBeginDateStr(String beginDateStr) {
		this.beginDateStr = beginDateStr;
	}
	public String getEndDateStr() {
		return endDateStr;
	}
	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}
	public String getCreateDateBegin() {
		return createDateBegin;
	}
	public void setCreateDateBegin(String createDateBegin) {
		this.createDateBegin = createDateBegin;
	}
	public String getPublishDateBegin() {
		return publishDateBegin;
	}
	public void setPublishDateBegin(String publishDateBegin) {
		this.publishDateBegin = publishDateBegin;
	}
	public String getBeginDateBegin() {
		return beginDateBegin;
	}
	public void setBeginDateBegin(String beginDateBegin) {
		this.beginDateBegin = beginDateBegin;
	}
	public String getEndDateBegin() {
		return endDateBegin;
	}
	public void setEndDateBegin(String endDateBegin) {
		this.endDateBegin = endDateBegin;
	}
	public String getCreateDateEnd() {
		return createDateEnd;
	}
	public void setCreateDateEnd(String createDateEnd) {
		this.createDateEnd = createDateEnd;
	}
	public String getPublishDateEnd() {
		return publishDateEnd;
	}
	public void setPublishDateEnd(String publishDateEnd) {
		this.publishDateEnd = publishDateEnd;
	}
	public String getBeginDateEnd() {
		return beginDateEnd;
	}
	public void setBeginDateEnd(String beginDateEnd) {
		this.beginDateEnd = beginDateEnd;
	}
	public String getEndDateEnd() {
		return endDateEnd;
	}
	public void setEndDateEnd(String endDateEnd) {
		this.endDateEnd = endDateEnd;
	}
	public int getIsVague() {
		return isVague;
	}
	public void setIsVague(int isVague) {
		this.isVague = isVague;
	}
	@Override
	public String toString() {
		return "commodityCode="+commodityCode+" createDate="+createDate+"  publishDate="+publishDate+" beginDate="+beginDate+" endDate="+endDate+" commodityName="+commodityName+" payNum="+payNum+" awardNumMin="+awardNumMin+" awardNumMax="+awardNumMax+" awardType="+awardType+" state="+state+"  partakeNum="+partakeNum+"  partakeDateType="+partakeDateType+"  weight="+weight+" termFlag="+termFlag+" accountCode"+accountCode;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public int getStyle() {
		return style;
	}
	public void setStyle(int style) {
		this.style = style;
	}
	public int getIsOffLineFalg() {
		return isOffLineFalg;
	}
	public void setIsOffLineFalg(int isOffLineFalg) {
		this.isOffLineFalg = isOffLineFalg;
	}
}
