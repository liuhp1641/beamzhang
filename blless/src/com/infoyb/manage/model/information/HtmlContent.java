package com.cm.manage.model.information;

import java.util.Date;

public class HtmlContent  implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long hid;
	private String htmlCode;
	private String htmlTitle;
	private String content;
	private int htmlState;
	private int businessType;
	private int isOutLink;
	private String htmlUrl;
	private Date htmlCreateDate;
	private int isOpenApp;
	private int isAddButton;
	private String appCode;
	private String appPara;
	private String buttonText;
	private int isShare;
	public long getHid() {
		return hid;
	}
	public void setHid(long hid) {
		this.hid = hid;
	}
	public String getHtmlCode() {
		return htmlCode;
	}
	public void setHtmlCode(String htmlCode) {
		this.htmlCode = htmlCode;
	}
	public String getHtmlTitle() {
		return htmlTitle;
	}
	public void setHtmlTitle(String htmlTitle) {
		this.htmlTitle = htmlTitle;
	}

	public int getHtmlState() {
		return htmlState;
	}
	public void setHtmlState(int htmlState) {
		this.htmlState = htmlState;
	}
	public int getBusinessType() {
		return businessType;
	}
	public void setBusinessType(int businessType) {
		this.businessType = businessType;
	}
	public int getIsOutLink() {
		return isOutLink;
	}
	public void setIsOutLink(int isOutLink) {
		this.isOutLink = isOutLink;
	}
	public String getHtmlUrl() {
		return htmlUrl;
	}
	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}
	public Date getHtmlCreateDate() {
		return htmlCreateDate;
	}
	public void setHtmlCreateDate(Date htmlCreateDate) {
		this.htmlCreateDate = htmlCreateDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getIsOpenApp() {
		return isOpenApp;
	}
	public void setIsOpenApp(int isOpenApp) {
		this.isOpenApp = isOpenApp;
	}
	public int getIsAddButton() {
		return isAddButton;
	}
	public void setIsAddButton(int isAddButton) {
		this.isAddButton = isAddButton;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getAppPara() {
		return appPara;
	}
	public void setAppPara(String appPara) {
		this.appPara = appPara;
	}
	public String getButtonText() {
		return buttonText;
	}
	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}
	public int getIsShare() {
		return isShare;
	}
	public void setIsShare(int isShare) {
		this.isShare = isShare;
	}
	
	
}
