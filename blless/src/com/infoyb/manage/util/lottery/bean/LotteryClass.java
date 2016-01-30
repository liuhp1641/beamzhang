package com.cm.manage.util.lottery.bean;


import java.util.List;

/**
 * 作者：邓玉明
 * 时间：11-3-13 下午4:27
 * QQ：757579248
 * email：cndym@163.com
 */

public class LotteryClass {
    private String code;
    private Integer subPrograms;
    private String name;
    private String dialect;
    private Integer current;
    private Integer modify;
    private Long early;
    private Long dashi;
    private Integer auto;
    private Long putOff;
    private List<LotteryPlay> lotteryPlayList;
    private Double handworkBonus;

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

    public List<LotteryPlay> getLotteryPlayList() {
        return lotteryPlayList;
    }

    public void setLotteryPlayList(List<LotteryPlay> lotteryPlayList) {
        this.lotteryPlayList = lotteryPlayList;
    }

    public LotteryPlay getLotteryPlay(String code) {
        for (LotteryPlay lotteryPlay : lotteryPlayList) {
            if (code.equals(lotteryPlay.getCode())) {
                return lotteryPlay;
            }
        }
        return null;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getModify() {
        return modify;
    }

    public void setModify(Integer modify) {
        this.modify = modify;
    }

    public Long getEarly() {
        return early;
    }

    public void setEarly(Long early) {
        this.early = early;
    }

    public Long getPutOff() {
        return putOff;
    }

    public void setPutOff(Long putOff) {
        this.putOff = putOff;
    }

    public Integer getAuto() {
        return auto;
    }

    public void setAuto(Integer auto) {
        this.auto = auto;
    }


    public Long getDashi() {
        return dashi;
    }

    public void setDashi(Long dashi) {
        this.dashi = dashi;
    }

    public Integer getSubPrograms() {
        return subPrograms;
    }

    public void setSubPrograms(Integer subPrograms) {
        this.subPrograms = subPrograms;
    }

    public Double getHandworkBonus() {
        return handworkBonus;
    }

    public void setHandworkBonus(Double handworkBonus) {
        this.handworkBonus = handworkBonus;
    }
}
