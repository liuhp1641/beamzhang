package com.cm.manage.vo.member;


public class MemberLoginVO implements java.io.Serializable{
	 private static final long serialVersionUID = -1498894662338670519L;
	 private Long id;
	 private String userCode;
	 private Integer status;
	 private String createTime;
	 
	 private String sid;//渠道号
	 private String softVer;//软件版本
	 private String sysVer;//操作系统版本
	 private String platform;//注册平台
	 private String ip;
	 private String machId;//登录串号
	 private String machName;
	 private Integer loginFrom;
	 private Integer loginCount;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSoftVer() {
		return softVer;
	}
	public void setSoftVer(String softVer) {
		this.softVer = softVer;
	}
	public String getSysVer() {
		return sysVer;
	}
	public void setSysVer(String sysVer) {
		this.sysVer = sysVer;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getMachId() {
		return machId;
	}
	public void setMachId(String machId) {
		this.machId = machId;
	}
	public String getMachName() {
		return machName;
	}
	public void setMachName(String machName) {
		this.machName = machName;
	}
	public Integer getLoginFrom() {
		return loginFrom;
	}
	public void setLoginFrom(Integer loginFrom) {
		this.loginFrom = loginFrom;
	}
	public Integer getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

}
