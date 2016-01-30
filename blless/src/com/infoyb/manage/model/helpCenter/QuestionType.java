package com.cm.manage.model.helpCenter;

public class QuestionType implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	
	private String qtName;
	
	private int serialNumber;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public String getQtName() {
		return qtName;
	}

	public void setQtName(String qtName) {
		this.qtName = qtName;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	
}
