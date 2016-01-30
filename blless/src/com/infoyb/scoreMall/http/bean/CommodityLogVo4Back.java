package com.cm.scoreMall.http.bean;

import java.io.Serializable;
import java.util.List;

public class CommodityLogVo4Back implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4412247599610218835L;
	private String clCode; //log的Code
	private String commodityCode;//商品code
	private String userCode;//用户code
	private String createDate;//创建时间
	private double awardNum;//奖励数值

	private int awardProbability;//奖励赔率
	private int sealFlag;//是否开奖标示
	private String orderId;//订单code
	private String sealDate;//派奖日期
	
	private String commodityName; //商品名称
	private long payNum; //应付积分数
	private int awardType;//获奖类型
	private List<String> userCodeList;  //用户code列表
	
	private String createDateBegin;
	private String createDateEnd;
	private String sealDateBegin;
	private String sealDateEnd;
	
	private String sort;
	private String order;
	
	
	private double awardNumMin;//奖品金额最小值
	private double awardNumMax;//奖品金额最大值
	
	
	private String userName;//后台用的 用户名
	
	private int isVague;//是否模糊查询  0为否1为是
	
	
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
	public String getCreateDateBegin() {
		return createDateBegin;
	}
	public void setCreateDateBegin(String createDateBegin) {
		this.createDateBegin = createDateBegin;
	}
	public String getCreateDateEnd() {
		return createDateEnd;
	}
	public void setCreateDateEnd(String createDateEnd) {
		this.createDateEnd = createDateEnd;
	}
	public String getSealDateBegin() {
		return sealDateBegin;
	}
	public void setSealDateBegin(String sealDateBegin) {
		this.sealDateBegin = sealDateBegin;
	}
	public String getSealDateEnd() {
		return sealDateEnd;
	}
	public void setSealDateEnd(String sealDateEnd) {
		this.sealDateEnd = sealDateEnd;
	}
	public String getClCode() {
		return clCode;
	}
	public void setClCode(String clCode) {
		this.clCode = clCode;
	}
	public String getCommodityCode() {
		return commodityCode;
	}
	public void setCommodityCode(String commodityCode) {
		this.commodityCode = commodityCode;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
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
	public int getSealFlag() {
		return sealFlag;
	}
	public void setSealFlag(int sealFlag) {
		this.sealFlag = sealFlag;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public List<String> getUserCodeList() {
		return userCodeList;
	}
	public void setUserCodeList(List<String> userCodeList) {
		this.userCodeList = userCodeList;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public long getPayNum() {
		return payNum;
	}
	public void setPayNum(long payNum) {
		this.payNum = payNum;
	}
	public int getAwardType() {
		return awardType;
	}
	public void setAwardType(int awardType) {
		this.awardType = awardType;
	}
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
	
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getSealDate() {
		return sealDate;
	}
	public void setSealDate(String sealDate) {
		this.sealDate = sealDate;
	}
	@Override
	public String toString() {
		return "[clCode="+clCode+"   commodityCode="+commodityCode+"  userCode="+userCode+"  createDate="+createDate+"  awardNum="+awardNum+"  awardProbability="+awardProbability+"  sealFlag="+sealFlag+"  orderId="+orderId+"  sealDate="+sealDate+"  commodityName="+commodityName+"  payNum="+payNum+"  awardType="+awardType+"  userCodeList="+userCodeList+"  createDateBegin="+createDateBegin+"  createDateEnd="+createDateEnd+"  sealDateBegin="+sealDateBegin+"  sealDateEnd="+sealDateEnd+"  sort="+sort+"  order="+order+"]";
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getIsVague() {
		return isVague;
	}
	public void setIsVague(int isVague) {
		this.isVague = isVague;
	}
	
}
