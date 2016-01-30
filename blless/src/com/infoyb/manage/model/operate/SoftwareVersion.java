package com.cm.manage.model.operate;

public class SoftwareVersion implements java.io.Serializable{
	
	private static final long serialVersionUID = 1374100128914760744L;
	private long id;
	private String svCode;
	private String svType;
	private String svName;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSvCode() {
		return svCode;
	}
	public void setSvCode(String svCode) {
		this.svCode = svCode;
	}
	public String getSvType() {
		return svType;
	}
	public void setSvType(String svType) {
		this.svType = svType;
	}
	public String getSvName() {
		return svName;
	}
	public void setSvName(String svName) {
		this.svName = svName;
	}
	
	
	
}
