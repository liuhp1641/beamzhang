package com.cm.manage.model.quartz;

import java.io.Serializable;
import java.util.Date;


public class DistributionLock implements Serializable {

    private static final long serialVersionUID = 5009897502506644494L;

    private Long id;
    private String name;
    private String code;
    private String userName;
    private Date createTime;
    private Integer status;

    public DistributionLock(String name) {
        this.name = name;
    }

    public DistributionLock(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public DistributionLock() {
    }

    public DistributionLock(Long id, String name, String code, String userName, Date createTime, Integer status) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.userName = userName;
        this.createTime = createTime;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
