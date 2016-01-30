package com.cm.message.http.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 消息
 */
public class MessageInfoQueryBean implements Serializable {

    private static final long serialVersionUID = -8238474167752956350L;
    private String title;
    private Integer status;
    private Integer readNum;

    private Date startSendTime;
    private Date endSendTime;

    public MessageInfoQueryBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    public Date getStartSendTime() {
        return startSendTime;
    }

    public void setStartSendTime(Date startSendTime) {
        this.startSendTime = startSendTime;
    }

    public Date getEndSendTime() {
        return endSendTime;
    }

    public void setEndSendTime(Date endSendTime) {
        this.endSendTime = endSendTime;
    }
}
