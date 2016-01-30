package com.cm.manage.vo.sms;

import java.io.Serializable;

public class MemberSms implements Serializable {

    private static final long serialVersionUID = -4476189885247576197L;

    private Long id;
    //接收人手机号码
    private String mobile;
    
    private String userCode;
    private String userName;
    
    //类型
    private String model;

    private String type;
    //短信内容
    private String body;
    //短信状态(0 未发送 1 发送成功 2 发送失败)
    private Integer status;
    //短信模板id
    private String templateId;
    //发送时间
    private String sendTime;
    //创建时间
    private String createTime;

    private String operator;
    private String memo;

    private String mobileStr;//批量手机号
    
    public MemberSms() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getMobileStr() {
		return mobileStr;
	}

	public void setMobileStr(String mobileStr) {
		this.mobileStr = mobileStr;
	}
	
}
