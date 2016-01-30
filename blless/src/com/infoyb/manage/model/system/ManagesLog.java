package com.cm.manage.model.system;

import java.util.Date;

public class ManagesLog {

    private Long id;
    private String adminName;//操作管理员
    private String type;//操作类型
    private String description;//描述
    private Date createTime;//创建时间
    private String ip;//操作ip
    private Integer operatingType;//类型


    public Integer getOperatingType() {
        return operatingType;
    }

    public void setOperatingType(Integer operatingType) {
        this.operatingType = operatingType;
    }

    public ManagesLog() {
        super();
    }

    public ManagesLog(Long id, String adminName, String type,
                      String description, Date createTime) {
        super();
        this.id = id;
        this.adminName = adminName;
        this.type = type;
        this.description = description;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }


}
