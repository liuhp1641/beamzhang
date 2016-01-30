package com.cm.manage.vo.information;

import java.util.Date;

public class HtmlContentVo {
	private String createDateStart;
	private String createDateEnd;
	private long hid;
	private String htmlCode;
	private String htmlTitle;
	private int htmlState;
	private int businessType;
	private int isOutLink;
	private String htmlUrl;
	private String content;
	private String htmlCreateDate;
	private int checkFlag;
	
	private int isOpenApp;
	private int isAddButton;
	private String appCode;
	private String appPara;
	private String buttonText;
	private int isShare;
	
	
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
	public String getCreateDateStart() {
		return createDateStart;
	}
	public void setCreateDateStart(String createDateStart) {
		this.createDateStart = createDateStart;
	}
	public String getCreateDateEnd() {
		return createDateEnd;
	}
	public void setCreateDateEnd(String createDateEnd) {
		this.createDateEnd = createDateEnd;
	}
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

	public int getBusinessType() {
		return businessType;
	}
	public int getHtmlState() {
		return htmlState;
	}
	public void setHtmlState(int htmlState) {
		this.htmlState = htmlState;
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
	public String getHtmlCreateDate() {
		return htmlCreateDate;
	}
	public void setHtmlCreateDate(String htmlCreateDate) {
		this.htmlCreateDate = htmlCreateDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(int checkFlag) {
		this.checkFlag = checkFlag;
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
