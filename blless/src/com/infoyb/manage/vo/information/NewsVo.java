package com.cm.manage.vo.information;

import java.util.List;

public class NewsVo {
	private NewsDetail major;
	private List<NewsDetail> minorList;
	private int checkFlag;
	public NewsDetail getMajor() {
		return major;
	}
	public void setMajor(NewsDetail major) {
		this.major = major;
	}
	public List<NewsDetail> getMinorList() {
		return minorList;
	}
	public void setMinorList(List<NewsDetail> minorList) {
		this.minorList = minorList;
	}
	public int getCheckFlag() {
		return checkFlag;
	}
	public void setCheckFlag(int checkFlag) {
		this.checkFlag = checkFlag;
	}
	
	
}
