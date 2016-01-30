package com.cm.other.http.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 条目模板
 */
public class TemplateBean implements Serializable {

    private static final long serialVersionUID = 8842687954337066060L;

    private Long id;

    private String itemCode;

    private String content;

    private Integer status;

    private Date createTime;

    public TemplateBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
