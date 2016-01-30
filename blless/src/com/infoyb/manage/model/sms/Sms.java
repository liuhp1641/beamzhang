package com.cm.manage.model.sms;

import java.io.Serializable;
import java.util.Date;

public class Sms implements Serializable {

    private static final long serialVersionUID = -4476189885247576197L;

    public final static String MODEL_FOR_VOICE_CODE = "voice";//语音
    public final static String MODEL_FOR_SMS_CODE = "sms";//短信

    public final static String OPERATOR_FOR_SYS = "系统";

    public final static String TYPE_FOR_REGISTER = "01";  //手机号注册
    public final static String TYPE_FOR_BIND_MOBILE = "02";//绑定手机
    public final static String TYPE_FOR_FORGET_PWD = "03";//找回密码

    private Long id;
    //接收人手机号码
    private String mobile;
    //类型
    private String model;

    //类型
    private String type;

    //短信内容
    private String body;
    //短信状态(0 未发送 1 发送成功 2 发送失败)
    private Integer status;
    //短信模板id
    private String templateId;
    //发送时间
    private Date sendTime;
    //创建时间
    private Date createTime;

    private String operator;
    private String memo;

    public Sms() {

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
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
}
