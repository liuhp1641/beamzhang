package com.cm.manage.util.bonusClass;

import java.util.HashMap;
import java.util.Map;

/**
 * UserBean: lfx
 * Date: 12-2-20 下午3:18
 */
public class Level {

    private Integer classes = 0;//n1奖级
    private String total = "0";//m1中奖总注数
    private String amount = "0";//c1等级奖金
    private String addAmount = "0";//加奖奖金
    private String title = "";//奖级标识

    public Level() {

    }

    public Level(String str) {
        Map<String, String> map = format(str);
        if (map.get("classes") != null)
            this.classes = Integer.valueOf(map.get("classes"));
        if (map.get("total") != null)
            this.total = map.get("total");
        if (map.get("amount") != null)
            this.amount = map.get("amount");
        if (map.get("addAmount") != null)
            this.addAmount = map.get("addAmount");
        if (map.get("title") != null){
            this.title = map.get("title");
        }
    }

    public Map<String,String> toMap(){
        Map<String,String> map = new HashMap<String, String>();
        map.put("classes",String.valueOf(classes));
        map.put("total",total);
        map.put("amount",amount);
        map.put("addAmount",String.valueOf(addAmount));
        map.put("title",title);
        return map;
    }

    private Map<String, String> format(String str) {
        Map<String, String> map = new HashMap<String, String>();
        String newStr = str.replace("'", "").replace("\"", "");
        for (String arr : newStr.split(",")) {
            String[] one = arr.split(":");
            map.put(one[0], one.length < 2 ? "" : one[1]);
        }
        return map;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{'classes':" + classes);
        stringBuffer.append(",'total':" + (total == "" ? "0" : total));
        stringBuffer.append(",'amount':" + (amount == "" ? "0" : amount));
        stringBuffer.append(",'addAmount':" + addAmount);
        stringBuffer.append(",'title':'" + title + "'}");
        return stringBuffer.toString();
    }

    public Integer getClasses() {
        return classes;
    }

    public void setClasses(Integer classes) {
        this.classes = classes;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total == null ? "" : total;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount == null ? "" : amount;
    }

    public String getAddAmount() {
        return addAmount;
    }

    public void setAddAmount(String addAmount) {
        this.addAmount = addAmount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? "" : title;
    }
}
