package com.cm.manage.util.lottery.bean;


public class LotteryBean {
    private String lotteryCode;
    private LotteryClass lotteryClass;
    private LotteryPlay lotteryPlay;
    private LotteryPoll lotteryPoll;

    public String getLotteryCode() {
        return lotteryCode;
    }

    public void setLotteryCode(String lotteryCode) {
        this.lotteryCode = lotteryCode;
    }

    public LotteryClass getLotteryClass() {
        return lotteryClass;
    }

    public void setLotteryClass(LotteryClass lotteryClass) {
        this.lotteryClass = lotteryClass;
    }

    public LotteryPlay getLotteryPlay() {
        return lotteryPlay;
    }

    public void setLotteryPlay(LotteryPlay lotteryPlay) {
        this.lotteryPlay = lotteryPlay;
    }

    public LotteryPoll getLotteryPoll() {
        return lotteryPoll;
    }

    public void setLotteryPoll(LotteryPoll lotteryPoll) {
        this.lotteryPoll = lotteryPoll;
    }
}
