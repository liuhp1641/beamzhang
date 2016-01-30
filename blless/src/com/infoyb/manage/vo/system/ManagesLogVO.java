package com.cm.manage.vo.system;


public class ManagesLogVO {

    private Long id;
    private String adminName;//操作管理员
    private String type;//操作类型
    private String description;//描述
    private String createTime;//创建时间
    private String ip;//操作ip
    private Integer operatingType;//类型
    
    private String operateStart;//操作开始时间
    
    private String operateEnd;//操作结束时间
    
    private boolean flag;

    public Integer getOperatingType() {
        return operatingType;
    }

    public void setOperatingType(Integer operatingType) {
        this.operatingType = operatingType;
    }

    public ManagesLogVO() {
        super();
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

	public String getOperateStart() {
		return operateStart;
	}

	public void setOperateStart(String operateStart) {
		this.operateStart = operateStart;
	}

	public String getOperateEnd() {
		return operateEnd;
	}

	public void setOperateEnd(String operateEnd) {
		this.operateEnd = operateEnd;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}
