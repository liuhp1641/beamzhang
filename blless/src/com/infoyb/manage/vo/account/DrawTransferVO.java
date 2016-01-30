package com.cm.manage.vo.account;

import java.io.Serializable;
/***
 * @describe 用于封装送给支付宝的转账文件数据
 * @author sunjf
 *
 */
public class DrawTransferVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String batchNo;//转账批次号
	private String orderId;//提现申请流水号
	private String realName;//户名
	private String bankCardNo;//银行卡号
	private String bankName;//收款开户名称
	private String province;//收款行开户省份
	private String city;//收款行开户城市
	private String subBankName;//收款行具体的分支行名称
	private Double amount;//提现金额，该金额是扣除客户手续费后的金额
	private String customerType;//1对公 2对私
	private String notes;//备注
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getBankCardNo() {
		return bankCardNo;
	}
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSubBankName() {
		return subBankName;
	}
	public void setSubBankName(String subBankName) {
		this.subBankName = subBankName;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
}
