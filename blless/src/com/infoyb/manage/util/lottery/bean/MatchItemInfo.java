package com.cm.manage.util.lottery.bean;


import java.io.Serializable;

import org.apache.commons.lang.ArrayUtils;

import com.cm.manage.constant.IOrderConstants;
import com.cm.manage.util.base.CommonUtil;


public class MatchItemInfo implements Serializable, Comparable<MatchItemInfo> {
    public static final int DAN = 1;
    private String matchId;
    private String value;
    private int dan;
    private int item;
    private String subPlayCode;


    public static MatchItemInfo str2Object(String number) {
        //场次1:选项1,选项2,选项3:设胆;场次2:选项1,选项2,选项3:设胆|过关方式（1*1,n*1,n*m)
        String[] arr = number.split(":");

        String playCode = null;
        if (IOrderConstants.JC_MIXPLAY_SUPPORT_PLAY_CODES.contains("[" + arr[1] + "]")) {
            if (arr.length == 4) {
                playCode = arr[1];
                arr = (String[]) ArrayUtils.remove(arr, 1);
            } else if (arr.length == 3) {
                playCode = arr[1];
                arr = (String[]) ArrayUtils.remove(arr, 1);
                arr = (String[]) ArrayUtils.add(arr, "0");
            }
        }

        if (arr.length != 3) {
            throw new IllegalArgumentException("号码(" + number + ")格式有误(2)");
        }
        MatchItemInfo matchItemInfo = new MatchItemInfo();
        matchItemInfo.setMatchId(arr[0]);
        matchItemInfo.setValue(arr[1]);
        matchItemInfo.setDan(CommonUtil.formatInt(arr[2], 0));
        matchItemInfo.setItem(arr[1].split(",").length);
        matchItemInfo.setSubPlayCode(playCode);
        return matchItemInfo;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getDan() {
        return dan;
    }

    public void setDan(int dan) {
        this.dan = dan;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return getMatchId() + ":" + getValue();
    }

    @Override
    public int compareTo(MatchItemInfo matchItemInfo) {
        if (CommonUtil.formatLong(matchId, 0) > CommonUtil.formatLong(matchItemInfo.getMatchId(), 0)) {
            return 1;
        } else if (CommonUtil.formatLong(matchId, 0) < CommonUtil.formatLong(matchItemInfo.getMatchId(), 0)) {
            return -1;
        } else {
            return 0;
        }
    }

    public String getSubPlayCode() {
        return subPlayCode;
    }

    public void setSubPlayCode(String subPlayCode) {
        this.subPlayCode = subPlayCode;
    }
}
