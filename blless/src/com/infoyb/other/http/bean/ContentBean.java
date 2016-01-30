package com.cm.other.http.bean;


import java.io.Serializable;
import java.util.Date;

/**
 * 条目内容
 */
public class ContentBean implements Serializable {

    private static final long serialVersionUID = 2833642515421796956L;
    private Long id;

    private String itemCode;

    private String primaryKey; //彩种 玩法 方案号 赛事编号 用户code(彩种_玩法)

    private String content;

    private String linkUrl;

    private Integer status;//状态

    private Date createTime;
    
    private String primaryKeyChinese; //彩种中文

    public ContentBean() {

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

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
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

	public String getPrimaryKeyChinese() {
		return primaryKeyChinese;
	}

	public void setPrimaryKeyChinese(String primaryKeyChinese) {
		this.primaryKeyChinese = primaryKeyChinese;
	}
    
}
