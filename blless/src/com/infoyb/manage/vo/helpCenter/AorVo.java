package com.cm.manage.vo.helpCenter;

public class AorVo {
	private long id;
	
	private String userName;
	
	private String userCode;
	
	private String content;
	
	private int hiddenFlag;
	
	private int state;
	
	private AorVo reAorVo;
	
	private String createDate;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getHiddenFlag() {
		return hiddenFlag;
	}

	public void setHiddenFlag(int hiddenFlag) {
		this.hiddenFlag = hiddenFlag;
	}

	public AorVo getReAorVo() {
		return reAorVo;
	}

	public void setReAorVo(AorVo reAorVo) {
		this.reAorVo = reAorVo;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	
}
