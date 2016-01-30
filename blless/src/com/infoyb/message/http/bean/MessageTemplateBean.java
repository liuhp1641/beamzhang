package com.cm.message.http.bean;

import java.io.Serializable;

/**
 * 消息模板
 */
public class MessageTemplateBean implements Serializable {

    private static final long serialVersionUID = -1515009570890042557L;
    private int id;
    private String messageType;
    private String title;
    private String content;// 更新描述信息

    public MessageTemplateBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


}
