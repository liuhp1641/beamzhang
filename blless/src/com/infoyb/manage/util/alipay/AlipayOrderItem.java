package com.cm.manage.util.alipay;

/***
 * @describe 对应支付宝明细节点DetailItem中数据项
 * @author sunjf
 *
 */
public class AlipayOrderItem {
	private String userSerialNo;//商户流水号
	private String realName;//收款银行户名
	private String bankCardNo;//收款银行卡号
	private String bankName;//收款开户银行名称，如中国银行
	private String bankProvince;//开户行所在省份
	private String bankCity;//开户行所在城市
	private String subBankName;//收款支行名称
	private String amount;//收款金额
	private String customerType;//对公对私
	private String status;//状态
	private String instructionId;//支付宝内部提现流水号
	private String bankFlag;//是否退票 0 没有退票 1退票
	private String dealMemo;//明细描述信息
	private String userMemo;//用户原先提交的备注
	
	
	
	public String getUserSerialNo() {
		return userSerialNo;
	}
	public void setUserSerialNo(String userSerialNo) {
		this.userSerialNo = userSerialNo;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getInstructionId() {
		return instructionId;
	}
	public void setInstructionId(String instructionId) {
		this.instructionId = instructionId;
	}
	public String getDealMemo() {
		return dealMemo;
	}
	public void setDealMemo(String dealMemo) {
		this.dealMemo = dealMemo;
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
	public String getBankProvince() {
		return bankProvince;
	}
	public void setBankProvince(String bankProvince) {
		this.bankProvince = bankProvince;
	}
	public String getBankCity() {
		return bankCity;
	}
	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}
	public String getSubBankName() {
		return subBankName;
	}
	public void setSubBankName(String subBankName) {
		this.subBankName = subBankName;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getBankFlag() {
		return bankFlag;
	}
	public void setBankFlag(String bankFlag) {
		this.bankFlag = bankFlag;
	}
	public String getUserMemo() {
		return userMemo;
	}
	public void setUserMemo(String userMemo) {
		this.userMemo = userMemo;
	}
	
	
	

}
