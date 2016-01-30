package com.cm.manage.model.helpCenter;

import java.util.Date;

import com.cm.manage.model.member.Member;

public class Question implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long  id;
	
	private String userName;
	
	private String userCode;
	
	private Date createDate;
	
	private String content;
	
	private int state;
	
	
	private String remark;
	
	private int sourceType;
	
	private String equipmentType;
	
	private String equipmentSystem;
	
	private int  answerNum;
	
	private long questionTypeId;
	
	private Date lastDate;
	
	private Date updateDate;
	
	private int weight;
	
	private int pageView;
	
	private int hiddenFlag;
	
	private int examineNum;
	
	private int unExamineNum;

	

	public int getExamineNum() {
		return examineNum;
	}

	public void setExamineNum(int examineNum) {
		this.examineNum = examineNum;
	}

	public int getUnExamineNum() {
		return unExamineNum;
	}

	public void setUnExamineNum(int unExamineNum) {
		this.unExamineNum = unExamineNum;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}


	public int getSourceType() {
		return sourceType;
	}

	public void setSourceType(int sourceType) {
		this.sourceType = sourceType;
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public int getAnswerNum() {
		return answerNum;
	}

	public void setAnswerNum(int answerNum) {
		this.answerNum = answerNum;
	}

	

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public long getQuestionTypeId() {
		return questionTypeId;
	}

	public void setQuestionTypeId(long questionTypeId) {
		this.questionTypeId = questionTypeId;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getPageView() {
		return pageView;
	}

	public void setPageView(int pageView) {
		this.pageView = pageView;
	}

	public int getHiddenFlag() {
		return hiddenFlag;
	}

	public void setHiddenFlag(int hiddenFlag) {
		this.hiddenFlag = hiddenFlag;
	}

	public String getEquipmentSystem() {
		return equipmentSystem;
	}

	public void setEquipmentSystem(String equipmentSystem) {
		this.equipmentSystem = equipmentSystem;
	}
	
	
	
	
}
