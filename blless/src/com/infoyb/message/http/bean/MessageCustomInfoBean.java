package com.cm.message.http.bean;


import java.io.Serializable;
import java.util.Date;

public class MessageCustomInfoBean implements Serializable {

    private static final long serialVersionUID = -8489079766874983146L;

    private Long id;
    private String messageCode;
    private String messageType; //消息类型
    private String title;  //消息标题
    private String content; //消息内容
    private String filePath;//发送人文件路径
    private String remoteFilePath;//远程文件路径
    private Long sendNum;//发送人数
    private Long readNum;//阅读人数
    private Integer status;
    private Date quartzTime;//定时时间
    private Date createTime;//创建时间
    private Date sendTime;//发送时间
    private Date readTime; //最后阅读时间
    private Integer isDevice; // 0 用户编码 1 机器码

    public MessageCustomInfoBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getRemoteFilePath() {
        return remoteFilePath;
    }

    public void setRemoteFilePath(String remoteFilePath) {
        this.remoteFilePath = remoteFilePath;
    }

    public Long getSendNum() {
        return sendNum;
    }

    public void setSendNum(Long sendNum) {
        this.sendNum = sendNum;
    }

    public Long getReadNum() {
        return readNum;
    }

    public void setReadNum(Long readNum) {
        this.readNum = readNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getQuartzTime() {
        return quartzTime;
    }

    public void setQuartzTime(Date quartzTime) {
        this.quartzTime = quartzTime;
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

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

	public Integer getIsDevice() {
		return isDevice;
	}

	public void setIsDevice(Integer isDevice) {
		this.isDevice = isDevice;
	}
    
}
