package com.cm.manage.util.alipay;

import java.util.ArrayList;
import java.util.List;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.util.base.CommonUtil;
/***
 * @describe 将支付宝明细接口返回的xml字符串解析成AlipayOrder对象
 * @author sunjf
 *
 */
public class AlipayOrder {
	private XMLDocument XmlBody;
	private String is_success;
	private String sign_type;
	private String sign;
	private String error;
	
	private String fileName;
	private String pageSize;
	private String pageNum;
	private String totalItemCount;
	private String totalPageCount;
	private Integer totalNumber = 0;//总笔数
	private Integer successNumber = 0;//成功总笔数
	private Integer failNumber = 0;//失败总笔数
	private Double totalAmount = 0.0;//总金额
	private Double successAmount = 0.0;//成功总金额
	private Double failAmount = 0.0;//失败总金额
	
	private List<AlipayOrderItem> items = new ArrayList<AlipayOrderItem>();//一个批次对应的多个订单
	
	public void fromXml(String msg){
		this.XmlBody = new XMLDocument(msg);
		this.is_success = CommonUtil.trim(this.getTagNameValue("is_success"));
		if(CommonConstants.ALIPAY_RESULT_FAIL.equals(is_success)){
			this.error = this.getTagNameValue("error");
		}else if(CommonConstants.ALIPAY_RESULT_SUCCESS.equals(is_success)){
			this.totalItemCount = CommonUtil.trim(this.getTagNameValue("totalItemCount"));
			this.fileName = CommonUtil.trim((this.getTagNameValue("fileName")));
			this.pageSize = CommonUtil.trim(this.getTagNameValue("pageSize"));
			if(this.getXmlBody().getValue("detailList") != null){
				List xmlItems = this.getXmlBody().getDocuments("detailItem");
				if(xmlItems.size() > 0){
					this.totalNumber = xmlItems.size();
					AlipayOrderItem orderItem = null;
					for(int i=0;i<xmlItems.size();i++){
						XMLDocument one = (XMLDocument) xmlItems.get(i);
						orderItem = new AlipayOrderItem();
						this.setXmlBody(one);
						String status = CommonUtil.trim(this.getTagNameValue("status"));
						String amount = CommonUtil.trim(this.getTagNameValue("amount"));
						orderItem.setUserSerialNo(CommonUtil.trim(this.getTagNameValue("userSerialNo")));
						orderItem.setStatus(status);
						if(CommonConstants.ALIPAY_TRNS_SUCCESS.equals(status)){
							successNumber = successNumber + 1;
							successAmount = CommonUtil.add(successAmount, Double.valueOf(amount));
						}else if(CommonConstants.ALIPAY_TRNS_DISUSE.equals(status) || CommonConstants.ALIPAY_TRNS_FAIL.equals(status)){
							failNumber = failNumber + 1;
							failAmount = CommonUtil.add(failAmount, Double.valueOf(amount));
						}
						totalAmount = CommonUtil.add(totalAmount, Double.valueOf(amount));
						orderItem.setAmount(amount);
						orderItem.setInstructionId(CommonUtil.trim(this.getTagNameValue("instructionId")));
						orderItem.setRealName(CommonUtil.trim(this.getTagNameValue("bankAccountName")));
						orderItem.setBankName(CommonUtil.trim(this.getTagNameValue("bankName")));
						orderItem.setBankCardNo(CommonUtil.trim(this.getTagNameValue("bankAccountNo")));
						orderItem.setBankProvince(CommonUtil.trim(this.getTagNameValue("bankProvince")));
						orderItem.setBankCity(CommonUtil.trim(this.getTagNameValue("bankCity")));
						orderItem.setSubBankName(CommonUtil.trim(this.getTagNameValue("bankBranch")));
						orderItem.setBankFlag(CommonUtil.trim(this.getTagNameValue("refundMark")));
						orderItem.setCustomerType(CommonUtil.trim(this.getTagNameValue("bankAccountType")));
						orderItem.setUserMemo(CommonUtil.trim(this.getTagNameValue("recordMemo")));
						orderItem.setDealMemo(this.getTagNameValue("dealMemo"));
						items.add(orderItem);
					}
				}
				
			}
		}
		
	}
	
	
	
	public String getTagNameValue(String tagName){
		String val = "";
		val = this.XmlBody.getValueNoNull(tagName);
		return val;
	}
	
	

	public XMLDocument getXmlBody() {
		return XmlBody;
	}
	public void setXmlBody(XMLDocument xmlBody) {
		XmlBody = xmlBody;
	}
	public String getIs_success() {
		return is_success;
	}

	public void setIs_success(String isSuccess) {
		is_success = isSuccess;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String signType) {
		sign_type = signType;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getTotalItemCount() {
		return totalItemCount;
	}

	public void setTotalItemCount(String totalItemCount) {
		this.totalItemCount = totalItemCount;
	}

	public String getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(String totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public List<AlipayOrderItem> getItems() {
		return items;
	}

	public void setItems(List<AlipayOrderItem> items) {
		this.items = items;
	}
	
	
	
	public Integer getSuccessNumber() {
		return successNumber;
	}



	public void setSuccessNumber(Integer successNumber) {
		this.successNumber = successNumber;
	}



	public Integer getFailNumber() {
		return failNumber;
	}



	public void setFailNumber(Integer failNumber) {
		this.failNumber = failNumber;
	}



	public Double getTotalAmount() {
		return totalAmount;
	}



	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
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



	public Integer getTotalNumber() {
		return totalNumber;
	}



	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}



	/***
	 * @describe 将支付宝的交易状态转换成系统的提现交易状态
	 * @param alipayStatus
	 * @return
	 */
	public static int converStatus(String alipayStatus){
		if(CommonConstants.ALIPAY_TRNS_SUCCESS.equals(alipayStatus)){
			return CommonConstants.DRAW_SUCCESS;
		}else if(CommonConstants.ALIPAY_TRNS_DISUSE.equals(alipayStatus) || CommonConstants.ALIPAY_TRNS_FAIL.equals(alipayStatus)){
			return CommonConstants.DRAW_FAIL;//失败
		}else{
			return CommonConstants.DRAW_TRANSFERING;
		}
	}

}
