package com.cm.manage.vo.member;


public class MonitorVO implements java.io.Serializable{

	private static final long serialVersionUID = -9060324247621934659L;
	
	private String machId;//设备号
	
	private Integer regCount;//注册用户数
	
	private Integer loginCount;//登录用户数
	
	private boolean flag;
	
	private String realName;//姓名
	
	private String mobile;//手机号
	 
	 
	private Integer monitorType;//监控条件
	
	private String regstart;//注册开始时间
	    
    private String regend;//注册结束时间
	    
    private String loginstart;//登录开始时间
	    
    private String loginend;//登录结束时间
    
    private String cardCode; //身份证号码
    
    private String ip;

	public String getMachId() {
		return machId;
	}

	public void setMachId(String machId) {
		this.machId = machId;
	}

	public Integer getRegCount() {
		return regCount;
	}

	public void setRegCount(Integer regCount) {
		this.regCount = regCount;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getMonitorType() {
		return monitorType;
	}

	public void setMonitorType(Integer monitorType) {
		this.monitorType = monitorType;
	}

	public String getRegstart() {
		return regstart;
	}

	public void setRegstart(String regstart) {
		this.regstart = regstart;
	}

	public String getRegend() {
		return regend;
	}

	public void setRegend(String regend) {
		this.regend = regend;
	}

	public String getLoginstart() {
		return loginstart;
	}

	public void setLoginstart(String loginstart) {
		this.loginstart = loginstart;
	}

	public String getLoginend() {
		return loginend;
	}

	public void setLoginend(String loginend) {
		this.loginend = loginend;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
