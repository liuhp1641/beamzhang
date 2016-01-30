package com.cm.manage.model.order;

import java.io.Serializable;
import java.util.Date;

/**
 * 彩种控制
 */
public class LotteryControl implements Serializable {

    private Long id;
    private String lotteryCode;
    private String lotteryName;
    private Integer sort;
    private Integer status; // 0 正常 1 暂停销售 2 隐藏
    private Integer sendStatus; //出票状态 0 正常出票 1 暂停出票
    private String memo;//更新描述信息
    private String mark;//标记(加奖 返利 ...)
    private String tips;//营销语
    private Integer delayTime;//单位秒 送票未回执方案处理时间点(复式截止时间之后)
    private Date createTime;

    public LotteryControl() {
    }

    public LotteryControl(Long id, String lotteryCode, String lotteryName, Integer sort, Integer status, Integer sendStatus, String memo, String mark, String tips, Integer delayTime) {
        this.id = id;
        this.lotteryCode = lotteryCode;
        this.lotteryName = lotteryName;
        this.sort = sort;
        this.status = status;
        this.sendStatus = sendStatus;
        this.memo = memo;
        this.mark = mark;
        this.tips = tips;
        this.delayTime = delayTime;
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

    public String getLotteryName() {
        return lotteryName;
    }

    public void setLotteryName(String lotteryName) {
        this.lotteryName = lotteryName;
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

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(Integer delayTime) {
        this.delayTime = delayTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }
}
