package com.cm.manage.util.order.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.cm.manage.util.base.CommonUtil;

/**
 * 方案详情 赛事
 */
public class ProgramsMatchBean implements Serializable, Comparable<ProgramsMatchBean> {

    private static final long serialVersionUID = 3886116595768509722L;

    private String machId;
    private String week;
    private String sn;
    private String matchNo;
    private Integer matchCancel;
    private Date openTime;
    private String mainTeam;
    private String guestTeam;
    private Integer status;
    private String statusDesc;

    private Integer mainTeamHalfScore;
    private Integer guestTeamHalfScore;
    private Integer mainTeamScore;
    private Integer guestTeamScore;

    private List<MatchPlayBean> playList;

    public ProgramsMatchBean() {
    }

    public String getMachId() {
        return machId;
    }

    public void setMachId(String machId) {
        this.machId = machId;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getMatchNo() {
        return matchNo;
    }

    public void setMatchNo(String matchNo) {
        this.matchNo = matchNo;
    }

    public Integer getMatchCancel() {
        return matchCancel;
    }

    public void setMatchCancel(Integer matchCancel) {
        this.matchCancel = matchCancel;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public String getMainTeam() {
        return mainTeam;
    }

    public void setMainTeam(String mainTeam) {
        this.mainTeam = mainTeam;
    }

    public String getGuestTeam() {
        return guestTeam;
    }

    public void setGuestTeam(String guestTeam) {
        this.guestTeam = guestTeam;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public Integer getMainTeamHalfScore() {
        return mainTeamHalfScore;
    }

    public void setMainTeamHalfScore(Integer mainTeamHalfScore) {
        this.mainTeamHalfScore = mainTeamHalfScore;
    }

    public Integer getGuestTeamHalfScore() {
        return guestTeamHalfScore;
    }

    public void setGuestTeamHalfScore(Integer guestTeamHalfScore) {
        this.guestTeamHalfScore = guestTeamHalfScore;
    }

    public Integer getMainTeamScore() {
        return mainTeamScore;
    }

    public void setMainTeamScore(Integer mainTeamScore) {
        this.mainTeamScore = mainTeamScore;
    }

    public Integer getGuestTeamScore() {
        return guestTeamScore;
    }

    public void setGuestTeamScore(Integer guestTeamScore) {
        this.guestTeamScore = guestTeamScore;
    }

    public List<MatchPlayBean> getPlayList() {
        return playList;
    }

    public void setPlayList(List<MatchPlayBean> playList) {
        this.playList = playList;
    }

    @Override
    public int compareTo(ProgramsMatchBean o) {
        String sn1 = this.getMachId();
        String sn2 = o.getMachId();
        if (CommonUtil.formatLong(sn1, 1) > CommonUtil.formatLong(sn2, 1)) {
            return 1;
        } else if (CommonUtil.formatLong(sn1, 1) < CommonUtil.formatLong(sn2, 1)) {
            return -1;
        } else {
            return 0;
        }
    }
}
