package com.cm.manage.vo.system;

import java.io.Serializable;

public class Log implements Serializable{

	private static final long serialVersionUID = 7509407816961196141L;
	
	private String id;
	private String userId;//用户id
	private String content;//日志内容
	private String operation;//操作：删除、添加、修改等
	private String loginIP;//IP
	private String createTime;//时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getLoginIP() {
		return loginIP;
	}
	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
