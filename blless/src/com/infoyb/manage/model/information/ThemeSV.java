package com.cm.manage.model.information;

public class ThemeSV implements java.io.Serializable{
	private static final long serialVersionUID = 2407062735792731784L;
	
	private long id;
	private String themeCode;
	private String svCode;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getThemeCode() {
		return themeCode;
	}
	public void setThemeCode(String themeCode) {
		this.themeCode = themeCode;
	}
	public String getSvCode() {
		return svCode;
	}
	public void setSvCode(String svCode) {
		this.svCode = svCode;
	}
	
}
