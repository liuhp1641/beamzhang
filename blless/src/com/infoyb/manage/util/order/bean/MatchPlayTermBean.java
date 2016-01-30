package com.cm.manage.util.order.bean;

import java.io.Serializable;

import com.cm.manage.util.base.CommonUtil;


public class MatchPlayTermBean implements Serializable, Comparable<MatchPlayTermBean> {

    private static final long serialVersionUID = 7631522604316427573L;

    private String term;
    private String termName;
    private Double sp;
    private Integer checked;

    public MatchPlayTermBean() {
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public Double getSp() {
        return sp;
    }

    public void setSp(Double sp) {
        this.sp = sp;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    @Override
    public int compareTo(MatchPlayTermBean o) {
        String term1 = this.getTerm();
        String term2 = o.getTerm();
        if (CommonUtil.formatInt(term1, 1) > CommonUtil.formatInt(term2, 1)) {
            return -1;
        } else if (CommonUtil.formatInt(term1, 1) < CommonUtil.formatInt(term2, 1)) {
            return 1;
        } else {
            return 0;
        }
    }
}
