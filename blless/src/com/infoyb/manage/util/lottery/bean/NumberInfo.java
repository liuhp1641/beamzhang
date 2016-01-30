package com.cm.manage.util.lottery.bean;

import com.cm.manage.util.json.JsonBinder;


public class NumberInfo {
    private int sequence;
    private String playCode;
    private String pollCode;
    private String number;
    private int item;
    private int multiple;

    public NumberInfo() {
    }

    public static NumberInfo forInstance(String json) {
        return JsonBinder.buildNonDefaultBinder().fromJson(json, NumberInfo.class);
    }

    public static NumberInfo forInstance(String json, String playCode) {
        NumberInfo numberInfo = JsonBinder.buildNonDefaultBinder().fromJson(json, NumberInfo.class);
        numberInfo.setPlayCode(playCode);
        return numberInfo;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getPlayCode() {
        return playCode;
    }

    public void setPlayCode(String playCode) {
        this.playCode = playCode;
    }

    public String getPollCode() {
        return pollCode;
    }

    public void setPollCode(String pollCode) {
        this.pollCode = pollCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getMultiple() {
        return multiple;
    }

    public void setMultiple(int multiple) {
        this.multiple = multiple;
    }
}
