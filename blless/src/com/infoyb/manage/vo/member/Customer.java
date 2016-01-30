package com.cm.manage.vo.member;


/***
 * 账户模板
 * @author chw
 *
 */
public class Customer implements java.io.Serializable{

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
    private String createTime;

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
    
    private String unUserID;//开放平台ID

    //以下字段为用户查询所用辅助字段
    private String loginTime;//登录时间
    
    private String regstart;//注册开始时间
    
    private String regend;//注册结束时间
    
    private String loginstart;//登录开始时间
    
    private String loginend;//登录结束时间
    
    private Double betTotal;//投注
    
    private Double balanceTotal;//余额
    
    private Double cashTotal;//现金
    
    private Double presentTotal;//红包
    
    private String consumeType;//消费类型 0：充值金 1：奖金 2：红包
    
    private String loginMachId;//登录串号
    
    private String loginMach;//登录设备名
    
    private Double balanceMin;//余额最低
    
    private Double balanceMax;//余额最高
    
    private Double leaveMin;//离开天数最低
    
    private Double leaveMax;//离开天数最高
    
    private Double bonusAmount;// 中奖金余额
    
    private Double rechargeAmount;// 可用的充值金额
    
    private Double presentAmount;// 可用的赠送(奖励)金额
    
    private Double score;// 可用的积分
    
    private Double commission;// 可用佣金
    
    private Double gold;// 可用金币

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

    public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
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

	public Double getBetTotal() {
		return betTotal;
	}

	public void setBetTotal(Double betTotal) {
		this.betTotal = betTotal;
	}


	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
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

	public String getConsumeType() {
		return consumeType;
	}

	public void setConsumeType(String consumeType) {
		this.consumeType = consumeType;
	}

	public String getLoginMachId() {
		return loginMachId;
	}

	public void setLoginMachId(String loginMachId) {
		this.loginMachId = loginMachId;
	}

	public String getUnUserID() {
		return unUserID;
	}

	public void setUnUserID(String unUserID) {
		this.unUserID = unUserID;
	}

	public Double getBalanceTotal() {
		return balanceTotal;
	}

	public void setBalanceTotal(Double balanceTotal) {
		this.balanceTotal = balanceTotal;
	}

	public Double getCashTotal() {
		return cashTotal;
	}

	public void setCashTotal(Double cashTotal) {
		this.cashTotal = cashTotal;
	}

	public Double getPresentTotal() {
		return presentTotal;
	}

	public void setPresentTotal(Double presentTotal) {
		this.presentTotal = presentTotal;
	}

	public Double getBalanceMin() {
		return balanceMin;
	}

	public void setBalanceMin(Double balanceMin) {
		this.balanceMin = balanceMin;
	}

	public Double getBalanceMax() {
		return balanceMax;
	}

	public void setBalanceMax(Double balanceMax) {
		this.balanceMax = balanceMax;
	}

	public Double getLeaveMin() {
		return leaveMin;
	}

	public void setLeaveMin(Double leaveMin) {
		this.leaveMin = leaveMin;
	}

	public Double getLeaveMax() {
		return leaveMax;
	}

	public void setLeaveMax(Double leaveMax) {
		this.leaveMax = leaveMax;
	}

	public Double getBonusAmount() {
		return bonusAmount;
	}

	public void setBonusAmount(Double bonusAmount) {
		this.bonusAmount = bonusAmount;
	}

	public Double getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(Double rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public Double getPresentAmount() {
		return presentAmount;
	}

	public void setPresentAmount(Double presentAmount) {
		this.presentAmount = presentAmount;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public Double getGold() {
		return gold;
	}

	public void setGold(Double gold) {
		this.gold = gold;
	}

	public String getLoginMach() {
		return loginMach;
	}

	public void setLoginMach(String loginMach) {
		this.loginMach = loginMach;
	}
}
