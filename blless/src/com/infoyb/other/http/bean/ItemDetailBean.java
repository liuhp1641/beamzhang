package com.cm.other.http.bean;

import java.io.Serializable;
import java.util.List;


public class ItemDetailBean implements Serializable {

    private static final long serialVersionUID = -8660460018856560374L;

    private Long id;

    private String columnCode;//栏目编码

    private String itemCode;//条目类型

    private String itemName;//条目名称

    private String itemDesc;//条目描述

    private String itemImg;//条目图片路径

    private String fgColor;//前景色

    private String bgColor;//背景色

    private Integer sort;//序号

    private Integer status;//状态

    private Integer hasTemplate;//是否有模板 0 否  1 是

    private Integer hasContent;//是否有内容 0 否 1 是

    //内容
    private List<ContentBean> contentList;

    public List<TemplateBean> templateList;
    
    public String templateString;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColumnCode() {
        return columnCode;
    }

    public void setColumnCode(String columnCode) {
        this.columnCode = columnCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getItemImg() {
        return itemImg;
    }

    public void setItemImg(String itemImg) {
        this.itemImg = itemImg;
    }

    public String getFgColor() {
        return fgColor;
    }

    public void setFgColor(String fgColor) {
        this.fgColor = fgColor;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getHasTemplate() {
        return hasTemplate;
    }

    public void setHasTemplate(Integer hasTemplate) {
        this.hasTemplate = hasTemplate;
    }

    public Integer getHasContent() {
        return hasContent;
    }

    public void setHasContent(Integer hasContent) {
        this.hasContent = hasContent;
    }

    public List<ContentBean> getContentList() {
        return contentList;
    }

    public void setContentList(List<ContentBean> contentList) {
        this.contentList = contentList;
    }

    public List<TemplateBean> getTemplateList() {
        return templateList;
    }

    public void setTemplateList(List<TemplateBean> templateList) {
        this.templateList = templateList;
    }

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public String getTemplateString() {
		return templateString;
	}

	public void setTemplateString(String templateString) {
		this.templateString = templateString;
	}
    
    
}
