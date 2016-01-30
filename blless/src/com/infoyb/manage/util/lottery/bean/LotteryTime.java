package com.cm.manage.util.lottery.bean;


public class LotteryTime {
    public static final String ACTION_SALE = "sale";
    public static final String ACTION_SEND = "send";
    public static final String ACTION_ORDER_QUERY = "orderQuery";
    private String lotteryCode;
    private String action;
    private String week;
    private String startTime;
    private String endTime;
    private Integer addDate;

    public String getLotteryCode() {
        return lotteryCode;
    }

    public void setLotteryCode(String lotteryCode) {
        this.lotteryCode = lotteryCode;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getKey() {
        return getLotteryCode() + "_" + getAction();
    }

    public Integer getAddDate() {
        return addDate;
    }

    public void setAddDate(Integer addDate) {
        this.addDate = addDate;
    }
}
