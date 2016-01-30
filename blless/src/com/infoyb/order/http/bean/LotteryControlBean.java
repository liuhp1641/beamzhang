package com.cm.order.http.bean;


import java.io.Serializable;
/**
 * 彩种控制
 */
public class LotteryControlBean implements Serializable {

    private static final long serialVersionUID = -659247764029399371L;
    private Long id;
    private String lotteryCode;
    private String lotteryName;
    private Integer sort;
    private Integer status; // 0 正常 1 暂停销售 2 隐藏  3 更新
    private Integer sendStatus; //出票状态 0 正常出票 1 暂停出票
    private String memo;//更新描述信息
    private String mark;//标记(加奖 返利 ...)
    private String tips;//营销语

    public LotteryControlBean(){

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
