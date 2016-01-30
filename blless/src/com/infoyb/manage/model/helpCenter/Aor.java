package com.cm.manage.model.helpCenter;

import java.util.Date;


public class Aor implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	
	private String userCode;
	
	private String sysUserId;
	
	private Date createDate;
	
	private String content;
	
	private long questionId;
	
	private long parentId;
	
	private int state;
	
	private String remark;
	
	private int hiddenFlag;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	


	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getHiddenFlag() {
		return hiddenFlag;
	}

	public void setHiddenFlag(int hiddenFlag) {
		this.hiddenFlag = hiddenFlag;
	}

	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
	}
	
	
	
}
