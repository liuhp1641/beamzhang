package com.cm.manage.util.lottery.bean;

import java.util.List;

public class LotteryPlay {
    private String code;
    private String name;
    private int singlePassTicket;
    private int eachamount;

    private List<LotteryPoll> lotteryPollList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSinglePassTicket() {
        return singlePassTicket;
    }

    public void setSinglePassTicket(int singlePassTicket) {
        this.singlePassTicket = singlePassTicket;
    }

    public int getEachamount() {
        return eachamount;
    }

    public void setEachamount(int eachamount) {
        this.eachamount = eachamount;
    }

    public List<LotteryPoll> getLotteryPollList() {
        return lotteryPollList;
    }

    public void setLotteryPollList(List<LotteryPoll> lotteryPollList) {
        this.lotteryPollList = lotteryPollList;
    }

    public LotteryPoll getLotteryPoll(String code) {
        for (LotteryPoll lotteryPoll : lotteryPollList) {
            if (code.equals(lotteryPoll.getCode())) {
                return lotteryPoll;
            }
        }
        return null;
    }
}
