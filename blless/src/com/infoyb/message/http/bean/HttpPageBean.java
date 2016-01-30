package com.cm.message.http.bean;

import java.io.Serializable;
import java.util.List;

public class HttpPageBean<T> implements Serializable {

    private static final long serialVersionUID = 3655951787216104151L;

    private Integer page;
    private Integer pageSize;
    private Integer pageTotal;
    private Long itemTotal;
    private List<T> pageContent;

    public HttpPageBean() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Long getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(Long itemTotal) {
        this.itemTotal = itemTotal;
    }

    public List<T> getPageContent() {
        return pageContent;
    }

    public void setPageContent(List<T> pageContent) {
        this.pageContent = pageContent;
    }
}
