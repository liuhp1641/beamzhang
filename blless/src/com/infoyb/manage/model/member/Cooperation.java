package com.cm.manage.model.member;

import java.io.Serializable;
import java.util.Date;

public class Cooperation implements Serializable {

    private static final long serialVersionUID = 7708962612733688322L;
    private Long id;
    private String sid;//渠道编号
    private String pid;//父渠道编号
    private Integer type;//渠道类型
    private String platform;//平台
    private String name;//名称
    private String coopPassword;//渠道查询登陆密码
    private String companyName;//公司名称
    private String tel;//公司电话
    private String fax;//公司传真
    private Integer status;//账户状态 0 冻结 1开通
    private String key;//加密KEy
    private String digestType; //md5,rsa
    private Date createTime;

    public Cooperation() {
    }

    public Cooperation(Long id, String sid, String pid, Integer type,String platform, String name, String coopPassword, String companyName, String tel, String fax, Integer status, String key, String digestType, Date createTime) {
        this.id = id;
        this.sid = sid;
        this.pid = pid;
        this.type = type;
        this.platform = platform;
        this.name = name;
        this.coopPassword = coopPassword;
        this.companyName = companyName;
        this.tel = tel;
        this.fax = fax;
        this.status = status;
        this.key = key;
        this.digestType = digestType;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoopPassword() {
        return coopPassword;
    }

    public void setCoopPassword(String cooperationPassword) {
        this.coopPassword = coopPassword;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDigestType() {
        return digestType;
    }

    public void setDigestType(String digestType) {
        this.digestType = digestType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
