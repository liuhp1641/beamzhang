package com.cm.manage.model.information;

import java.util.Date;


public class Theme  implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long tid;
	private String theme_code;
	private String user_Id;
	private Date createDate;
	private Date publishDate;
	private Date onlineDate;
	private Date offLineDate;
	private String themeTitle;
	private String describe;
	private int state;
	private int style;
	private String pictureUrl;
	private int programType;
	private String lotteryCode;
	private int hidden_flag;
	private String htmlCode;
	private String parent_Code;
	private int isRecommend;
	private int isAllCooperation;
	private int isTop;
	private int weight;
	private int activityState;
	private int headStyle;
	
	private String platform;
	private int isAllSV;
	
	
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public int getIsAllSV() {
		return isAllSV;
	}
	public void setIsAllSV(int isAllSV) {
		this.isAllSV = isAllSV;
	}
	public int getIsTop() {
		return isTop;
	}
	public void setIsTop(int isTop) {
		this.isTop = isTop;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public long getTid() {
		return tid;
	}
	public void setTid(long tid) {
		this.tid = tid;
	}
	public String getTheme_code() {
		return theme_code;
	}
	public void setTheme_code(String theme_code) {
		this.theme_code = theme_code;
	}
	public String getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
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
	public Date getOnlineDate() {
		return onlineDate;
	}
	public void setOnlineDate(Date onlineDate) {
		this.onlineDate = onlineDate;
	}
	public Date getOffLineDate() {
		return offLineDate;
	}
	public void setOffLineDate(Date offLineDate) {
		this.offLineDate = offLineDate;
	}
	public String getThemeTitle() {
		return themeTitle;
	}
	public void setThemeTitle(String themeTitle) {
		this.themeTitle = themeTitle;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	public int getIsAllCooperation() {
		return isAllCooperation;
	}
	public void setIsAllCooperation(int isAllCooperation) {
		this.isAllCooperation = isAllCooperation;
	}
	public int getProgramType() {
		return programType;
	}
	public void setProgramType(int programType) {
		this.programType = programType;
	}
	public String getLotteryCode() {
		return lotteryCode;
	}
	public void setLotteryCode(String lotteryCode) {
		this.lotteryCode = lotteryCode;
	}
	
	public int getHidden_flag() {
		return hidden_flag;
	}
	public void setHidden_flag(int hidden_flag) {
		this.hidden_flag = hidden_flag;
	}
	public String getParent_Code() {
		return parent_Code;
	}
	public void setParent_Code(String parent_Code) {
		this.parent_Code = parent_Code;
	}
	public int getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(int isRecommend) {
		this.isRecommend = isRecommend;
	}
	public String getHtmlCode() {
		return htmlCode;
	}
	public void setHtmlCode(String htmlCode) {
		this.htmlCode = htmlCode;
	}
	public int getStyle() {
		return style;
	}
	public void setStyle(int style) {
		this.style = style;
	}
	public int getActivityState() {
		return activityState;
	}
	public void setActivityState(int activityState) {
		this.activityState = activityState;
	}
	public int getHeadStyle() {
		return headStyle;
	}
	public void setHeadStyle(int headStyle) {
		this.headStyle = headStyle;
	}
	
	
	
}
