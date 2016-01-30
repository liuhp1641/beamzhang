package com.cm.message.http.bean;

import java.io.Serializable;

/**
 * 消息类型
 */
public class MessageTypeBean implements Serializable {

    private static final long serialVersionUID = 8185594305131309481L;
    private int id;
    private String typeCode;
    private String typeName;
    private int priority;// 优先级

    public MessageTypeBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


}
