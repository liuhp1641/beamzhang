package com.cm.manage.vo.helpCenter;

import java.util.List;

import com.cm.manage.model.helpCenter.Picture;

public class QuestionAor {
	private long id;
	
	private String userName;
	
	private String userCode;
	
	private long questionTypeId;
	
	private int weight;
	
	private String equipmentType;
	
	private String equipmentSystem;
	
	private String createDate;
	
	private String content;
	
	private List<Picture> pictureList;
	
	private String aorVoList;
	
	private int hiddenFlag;

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

	public long getQuestionTypeId() {
		return questionTypeId;
	}

	public void setQuestionTypeId(long questionTypeId) {
		this.questionTypeId = questionTypeId;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public String getEquipmentSystem() {
		return equipmentSystem;
	}

	public void setEquipmentSystem(String equipmentSystem) {
		this.equipmentSystem = equipmentSystem;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}



	public List<Picture> getPictureList() {
		return pictureList;
	}

	public void setPictureList(List<Picture> pictureList) {
		this.pictureList = pictureList;
	}

	public String getAorVoList() {
		return aorVoList;
	}

	public void setAorVoList(String aorVoList) {
		this.aorVoList = aorVoList;
	}

	public int getHiddenFlag() {
		return hiddenFlag;
	}

	public void setHiddenFlag(int hiddenFlag) {
		this.hiddenFlag = hiddenFlag;
	}
	
	
	
}
