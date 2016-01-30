package com.cm.manage.model.member;

import java.util.Date;

public class Member implements java.io.Serializable {

    private static final long serialVersionUID = -1498894662338670519L;

    private Long id;
    private String userCode;
    private String userName;
    private String password;

    private String mobile;//手机号码
    private String realName;//真实姓名
    private Integer cardType;//证件类型 0 身份证 1 军官证 2 护照
    private String cardCode; //身份证号码

    private String sid;//渠道号
    private String platform;//注册平台
    private String softVer;//软件版本
    private String sysVer;//操作系统版本
    private String ip;
    private String machId;//注册串号
    private String machName;
    private Date createTime;

    private Integer memberType; //用户类型：0 普通用户 1 系统用户
    private Integer status;
    private Integer registerFrom;//注册类型 1 用户名注册 2 手机号码注册 3 联合登录
    private Integer unionApp; //联合登录


    private String trackUserCode;//推荐人
    private Integer memberCount;//推荐总人数

    private String ico;//头像
    private Integer grade;//等级
    private Integer vip;//vip用户

    private Integer userNameModify; //是否能修改用户名 0 不能 1 能
    private Integer passwordModify;//密码是否修改 0 未修改 1已修改

    private String sex;
    private String lunarBirthday; //农历生日
    private String solarBirthday;//阳历生日
    private String constellation;//星座

    private String backup1;
    private String backup2;
    private String backup3;
    
    private String qq;//QQ账户
    private String weChat;//微信
    private String email;//邮箱
    private String sinaBlog;//新浪微博
    
    private String unUserID;//开放平台ID
    
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getMemberType() {
        return memberType;
    }

    public void setMemberType(Integer memberType) {
        this.memberType = memberType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRegisterFrom() {
        return registerFrom;
    }

    public void setRegisterFrom(Integer registerFrom) {
        this.registerFrom = registerFrom;
    }

    public Integer getUnionApp() {
        return unionApp;
    }

    public void setUnionApp(Integer unionApp) {
        this.unionApp = unionApp;
    }

    public String getTrackUserCode() {
        return trackUserCode;
    }

    public void setTrackUserCode(String trackUserCode) {
        this.trackUserCode = trackUserCode;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public Integer getVip() {
        return vip;
    }

    public void setVip(Integer vip) {
        this.vip = vip;
    }

    public Integer getUserNameModify() {
        return userNameModify;
    }

    public void setUserNameModify(Integer userNameModify) {
        this.userNameModify = userNameModify;
    }

    public Integer getPasswordModify() {
        return passwordModify;
    }

    public void setPasswordModify(Integer passwordModify) {
        this.passwordModify = passwordModify;
    }

    public String getBackup1() {
        return backup1;
    }

    public void setBackup1(String backup1) {
        this.backup1 = backup1;
    }

    public String getBackup2() {
        return backup2;
    }

    public void setBackup2(String backup2) {
        this.backup2 = backup2;
    }

    public String getBackup3() {
        return backup3;
    }

    public void setBackup3(String backup3) {
        this.backup3 = backup3;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLunarBirthday() {
        return lunarBirthday;
    }

    public void setLunarBirthday(String lunarBirthday) {
        this.lunarBirthday = lunarBirthday;
    }

    public String getSolarBirthday() {
        return solarBirthday;
    }

    public void setSolarBirthday(String solarBirthday) {
        this.solarBirthday = solarBirthday;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeChat() {
		return weChat;
	}

	public void setWeChat(String weChat) {
		this.weChat = weChat;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSinaBlog() {
		return sinaBlog;
	}

	public void setSinaBlog(String sinaBlog) {
		this.sinaBlog = sinaBlog;
	}

	public String getUnUserID() {
		return unUserID;
	}

	public void setUnUserID(String unUserID) {
		this.unUserID = unUserID;
	}
    
}