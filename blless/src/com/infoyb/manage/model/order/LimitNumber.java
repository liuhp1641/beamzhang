package com.cm.manage.model.order;

import java.io.Serializable;
import java.util.Date;

/**
 * User: 邓玉明
 * Date: 11-6-2 下午11:01
 */

/**
 * 限号表
 */
public class LimitNumber implements Serializable{

    private static final long serialVersionUID = 6107151176158724517L;
    private Long id;
    private String lotteryCode;//彩种编号
    private String issue;//期次
    private String playCode;//玩法
    private String number;//号码
    private Integer multiple;//倍数
    private Integer status; // 0 1
    private Date createTime;

    public LimitNumber() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLotteryCode() {
        return lotteryCode;
    }

    public void setLotteryCode(String lotteryCode) {
        this.lotteryCode = lotteryCode;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getPlayCode() {
        return playCode;
    }

    public void setPlayCode(String playCode) {
        this.playCode = playCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getMultiple() {
        return multiple;
    }

    public void setMultiple(Integer multiple) {
        this.multiple = multiple;
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
