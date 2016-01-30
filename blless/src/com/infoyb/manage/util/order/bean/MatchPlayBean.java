package com.cm.manage.util.order.bean;

import java.io.Serializable;
import java.util.List;

import com.cm.manage.util.base.CommonUtil;


public class MatchPlayBean implements Serializable, Comparable<MatchPlayBean> {

    private static final long serialVersionUID = 1371086934286818144L;

    private String playCode;
    private String playName;
    private String letBall;
    private String preCast;
    private String result;
    private List<MatchPlayTermBean> termList;

    public MatchPlayBean() {

    }

    public String getPlayCode() {
        return playCode;
    }

    public void setPlayCode(String playCode) {
        this.playCode = playCode;
    }

    public String getPlayName() {
        return playName;
    }

    public void setPlayName(String playName) {
        this.playName = playName;
    }

    public String getLetBall() {
        return letBall;
    }

    public void setLetBall(String letBall) {
        this.letBall = letBall;
    }

    public String getPreCast() {
        return preCast;
    }

    public void setPreCast(String preCast) {
        this.preCast = preCast;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<MatchPlayTermBean> getTermList() {
        return termList;
    }

    public void setTermList(List<MatchPlayTermBean> termList) {
        this.termList = termList;
    }

    @Override
    public int compareTo(MatchPlayBean o) {
        String playCode1 = this.playCode;
        String playCode2 = o.getPlayCode();
        if (CommonUtil.formatInt(playCode1, 1) > CommonUtil.formatInt(playCode2, 1)) {
            return -1;
        } else if (CommonUtil.formatInt(playCode1, 1) < CommonUtil.formatInt(playCode2, 1)) {
            return 1;
        } else {
            return 0;
        }
    }
}
