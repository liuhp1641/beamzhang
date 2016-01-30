package com.cm.manage.vo.quartz;

import com.cm.manage.model.quartz.DistributionLock;
import com.cm.manage.util.base.DateUtil;

import java.io.Serializable;
import java.util.Date;


public class DistributionLockVO implements Serializable {

    private static final long serialVersionUID = 5009897502506644494L;

    private String name;
    private String code;
    private String ip;
    private String createTime;
    private String leftTime;
    private Integer status;

    public DistributionLockVO(DistributionLock distributionLock) {
        this.name = distributionLock.getName();
        this.code = distributionLock.getCode();
        this.ip = distributionLock.getUserName();
        this.createTime = DateUtil.format(distributionLock.getCreateTime());
        this.leftTime =  DateUtil.diffNowTime(distributionLock.getCreateTime());
        this.status = distributionLock.getStatus();
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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(String leftTime) {
        this.leftTime = leftTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
