package com.cm.manage.util.alipay;

import java.util.ArrayList;
import java.util.List;

public class AlipayBatchOrder {
	private String paymentDate;//付款日期
	private String processDate;//处理日期
	private int totalNumber;//总笔数
	private int successNumber;//处理成功笔数
	private int failNumber;//处理失败笔数
	private Double successAmount;//处理成功总金额
	private Double failAmount;//处理失败总金额
	private String payAccountNo;//支付宝账号
	private List<AlipayOrderItem> orderItem = new ArrayList<AlipayOrderItem>();
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getProcessDate() {
		return processDate;
	}
	public void setProcessDate(String processDate) {
		this.processDate = processDate;
	}
	public int getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}
	public int getSuccessNumber() {
		return successNumber;
	}
	public void setSuccessNumber(int successNumber) {
		this.successNumber = successNumber;
	}
	public int getFailNumber() {
		return failNumber;
	}
	public void setFailNumber(int failNumber) {
		this.failNumber = failNumber;
	}
	public Double getSuccessAmount() {
		return successAmount;
	}
	public void setSuccessAmount(Double successAmount) {
		this.successAmount = successAmount;
	}
	public Double getFailAmount() {
		return failAmount;
	}
	public void setFailAmount(Double failAmount) {
		this.failAmount = failAmount;
	}
	public String getPayAccountNo() {
		return payAccountNo;
	}
	public void setPayAccountNo(String payAccountNo) {
		this.payAccountNo = payAccountNo;
	}
	public List<AlipayOrderItem> getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(List<AlipayOrderItem> orderItem) {
		this.orderItem = orderItem;
	}
	
	

}

