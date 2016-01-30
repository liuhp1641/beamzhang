package com.cm.manage.vo.order;

import java.io.Serializable;

public class IssueForjczqVO implements Serializable {
    private static final long serialVersionUID = 1067014391997277511L;
    private Long id;
    private String lotteryCode;
    private String playCode;
    private String pollCode;
    private String issue;//当天时间
    private String sn;//当天场次序号
    private String week;//星期
    private String matchId;//场次号
    private String matchColor;//背景色
    private String endTime;//收单结束时间，开赛时间
    private String matchName;//联赛名
    private String mainTeam;//主队
    private String guestTeam;//客队
    private String mainTeamLogo;//主队logo
    private String guestTeamLogo;//客队logo
    private Integer endOperator;//是否结期处理;0:未处理，1:处理，2:取消
    private Integer bonusOperator;//是否返奖处理;0:未处理，1:处理 0 未返奖  1 已返奖

    private String createTime;//创建时间
    private String updateTime;//更新时间

    private Integer mainTeamHalfScore;//主队半场比分
    private Integer guestTeamHalfScore;//客队半场比分
    private Integer mainTeamScore;//主队比分
    private Integer guestTeamScore;//客队比分

    private String letBall;//让球/让分

    private Double winOrNegaSp;
    private Double negaSp;//让球胜平负-负
    private Double flatSp;//让球胜平负-平
    private Double winSp;//让球胜平负-胜

    //胜平负
    private Double spfWinOrNegaSp;
    private Double spfNegaSp;
    private Double spfFlatSp;
    private Double spfWinSp;

    private Double scoreSp;
    private Double scoreFQTSp;//比分-负其它
    private Double scorePQTSp;//比分-负其它
    private Double scoreSQTSp;//比分-负其它
    private Double score00Sp;//比分-0:0
    private Double score01Sp;//比分-0:1
    private Double score02Sp;
    private Double score03Sp;
    private Double score04Sp;
    private Double score05Sp;
    private Double score10Sp;
    private Double score11Sp;
    private Double score12Sp;
    private Double score13Sp;
    private Double score14Sp;
    private Double score15Sp;
    private Double score20Sp;
    private Double score21Sp;
    private Double score22Sp;
    private Double score23Sp;
    private Double score24Sp;
    private Double score25Sp;
    private Double score30Sp;
    private Double score31Sp;
    private Double score32Sp;
    private Double score33Sp;
    private Double score40Sp;
    private Double score41Sp;
    private Double score42Sp;
    private Double score50Sp;
    private Double score51Sp;
    private Double score52Sp;//比分-5:2

    private Double totalGoalSp;
    private Double totalGoal0Sp;//总进球-进0个
    private Double totalGoal1Sp;//总进球-进1个
    private Double totalGoal2Sp;
    private Double totalGoal3Sp;
    private Double totalGoal4Sp;
    private Double totalGoal5Sp;
    private Double totalGoal6Sp;
    private Double totalGoal7Sp;//总进球-进7个以上

    private Double halfCourtSp;
    private Double halfCourtFFSp;//半场胜平负-负负
    private Double halfCourtFPSp;
    private Double halfCourtFSSp;
    private Double halfCourtPFSp;
    private Double halfCourtPPSp;
    private Double halfCourtPSSp;
    private Double halfCourtSFSp;
    private Double halfCourtSPSp;
    private Double halfCourtSSSp;//半场胜平负-胜胜

    private Integer operatorsAward;//是否算奖（0等待算奖，1算奖中，2完成算奖）
    private Integer bonusToAccount;//是否派奖(0,1)


    private String endDanShiTime;
    private String endFuShiTime;//止售时间

    private Integer endStatus;

    private String backup1;//是否自动更新，"",0自动更新，1不自动更新对阵
    private String backup2;//是否隐藏对阵，"",0不隐藏，1隐藏对阵
    private String backup3;

    /**
     * 是否已录入赛果和开奖号码，后台管理录入或修改赛果信息后将置为1，赛果抓取程序将不考虑为1的场次（该字段只是赛果录入程序使用）
     * 0未录入赛果，1已录入赛果，默认为0
     */
    private Integer inputAwardStatus;
    private Integer isRecommend; //是否推荐
    private String label; //标签 "推荐"  "加奖"
    
    private boolean flag;
    
    private String startTime;

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

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getMatchColor() {
        return matchColor;
    }

    public void setMatchColor(String matchColor) {
        this.matchColor = matchColor;
    }

  
    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
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

    public Integer getEndOperator() {
        return endOperator;
    }

    public void setEndOperator(Integer endOperator) {
        this.endOperator = endOperator;
    }

    public Integer getBonusOperator() {
        return bonusOperator;
    }

    public void setBonusOperator(Integer bonusOperator) {
        this.bonusOperator = bonusOperator;
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

    public String getLetBall() {
        return letBall;
    }

    public void setLetBall(String letBall) {
        this.letBall = letBall;
    }

    public Double getNegaSp() {
        return negaSp;
    }

    public void setNegaSp(Double negaSp) {
        this.negaSp = negaSp;
    }

    public Double getFlatSp() {
        return flatSp;
    }

    public void setFlatSp(Double flatSp) {
        this.flatSp = flatSp;
    }

    public Double getWinSp() {
        return winSp;
    }

    public void setWinSp(Double winSp) {
        this.winSp = winSp;
    }

    public Double getSpfWinOrNegaSp() {
        return spfWinOrNegaSp;
    }

    public void setSpfWinOrNegaSp(Double spfWinOrNegaSp) {
        this.spfWinOrNegaSp = spfWinOrNegaSp;
    }

    public Double getSpfNegaSp() {
        return spfNegaSp;
    }

    public void setSpfNegaSp(Double spfNegaSp) {
        this.spfNegaSp = spfNegaSp;
    }

    public Double getSpfFlatSp() {
        return spfFlatSp;
    }

    public void setSpfFlatSp(Double spfFlatSp) {
        this.spfFlatSp = spfFlatSp;
    }

    public Double getSpfWinSp() {
        return spfWinSp;
    }

    public void setSpfWinSp(Double spfWinSp) {
        this.spfWinSp = spfWinSp;
    }

    public Double getScoreFQTSp() {
        return scoreFQTSp;
    }

    public void setScoreFQTSp(Double scoreFQTSp) {
        this.scoreFQTSp = scoreFQTSp;
    }

    public Double getScorePQTSp() {
        return scorePQTSp;
    }

    public void setScorePQTSp(Double scorePQTSp) {
        this.scorePQTSp = scorePQTSp;
    }

    public Double getScoreSQTSp() {
        return scoreSQTSp;
    }

    public void setScoreSQTSp(Double scoreSQTSp) {
        this.scoreSQTSp = scoreSQTSp;
    }

    public Double getScore00Sp() {
        return score00Sp;
    }

    public void setScore00Sp(Double score00Sp) {
        this.score00Sp = score00Sp;
    }

    public Double getScore01Sp() {
        return score01Sp;
    }

    public void setScore01Sp(Double score01Sp) {
        this.score01Sp = score01Sp;
    }

    public Double getScore02Sp() {
        return score02Sp;
    }

    public void setScore02Sp(Double score02Sp) {
        this.score02Sp = score02Sp;
    }

    public Double getScore03Sp() {
        return score03Sp;
    }

    public void setScore03Sp(Double score03Sp) {
        this.score03Sp = score03Sp;
    }

    public Double getScore04Sp() {
        return score04Sp;
    }

    public void setScore04Sp(Double score04Sp) {
        this.score04Sp = score04Sp;
    }

    public Double getScore05Sp() {
        return score05Sp;
    }

    public void setScore05Sp(Double score05Sp) {
        this.score05Sp = score05Sp;
    }

    public Double getScore10Sp() {
        return score10Sp;
    }

    public void setScore10Sp(Double score10Sp) {
        this.score10Sp = score10Sp;
    }

    public Double getScore11Sp() {
        return score11Sp;
    }

    public void setScore11Sp(Double score11Sp) {
        this.score11Sp = score11Sp;
    }

    public Double getScore12Sp() {
        return score12Sp;
    }

    public void setScore12Sp(Double score12Sp) {
        this.score12Sp = score12Sp;
    }

    public Double getScore13Sp() {
        return score13Sp;
    }

    public void setScore13Sp(Double score13Sp) {
        this.score13Sp = score13Sp;
    }

    public Double getScore14Sp() {
        return score14Sp;
    }

    public void setScore14Sp(Double score14Sp) {
        this.score14Sp = score14Sp;
    }

    public Double getScore15Sp() {
        return score15Sp;
    }

    public void setScore15Sp(Double score15Sp) {
        this.score15Sp = score15Sp;
    }

    public Double getScore20Sp() {
        return score20Sp;
    }

    public void setScore20Sp(Double score20Sp) {
        this.score20Sp = score20Sp;
    }

    public Double getScore21Sp() {
        return score21Sp;
    }

    public void setScore21Sp(Double score21Sp) {
        this.score21Sp = score21Sp;
    }

    public Double getScore22Sp() {
        return score22Sp;
    }

    public void setScore22Sp(Double score22Sp) {
        this.score22Sp = score22Sp;
    }

    public Double getScore23Sp() {
        return score23Sp;
    }

    public void setScore23Sp(Double score23Sp) {
        this.score23Sp = score23Sp;
    }

    public Double getScore24Sp() {
        return score24Sp;
    }

    public void setScore24Sp(Double score24Sp) {
        this.score24Sp = score24Sp;
    }

    public Double getScore25Sp() {
        return score25Sp;
    }

    public void setScore25Sp(Double score25Sp) {
        this.score25Sp = score25Sp;
    }

    public Double getScore30Sp() {
        return score30Sp;
    }

    public void setScore30Sp(Double score30Sp) {
        this.score30Sp = score30Sp;
    }

    public Double getScore31Sp() {
        return score31Sp;
    }

    public void setScore31Sp(Double score31Sp) {
        this.score31Sp = score31Sp;
    }

    public Double getScore32Sp() {
        return score32Sp;
    }

    public void setScore32Sp(Double score32Sp) {
        this.score32Sp = score32Sp;
    }

    public Double getScore33Sp() {
        return score33Sp;
    }

    public void setScore33Sp(Double score33Sp) {
        this.score33Sp = score33Sp;
    }

    public Double getScore40Sp() {
        return score40Sp;
    }

    public void setScore40Sp(Double score40Sp) {
        this.score40Sp = score40Sp;
    }

    public Double getScore41Sp() {
        return score41Sp;
    }

    public void setScore41Sp(Double score41Sp) {
        this.score41Sp = score41Sp;
    }

    public Double getScore42Sp() {
        return score42Sp;
    }

    public void setScore42Sp(Double score42Sp) {
        this.score42Sp = score42Sp;
    }

    public Double getScore50Sp() {
        return score50Sp;
    }

    public void setScore50Sp(Double score50Sp) {
        this.score50Sp = score50Sp;
    }

    public Double getScore51Sp() {
        return score51Sp;
    }

    public void setScore51Sp(Double score51Sp) {
        this.score51Sp = score51Sp;
    }

    public Double getScore52Sp() {
        return score52Sp;
    }

    public void setScore52Sp(Double score52Sp) {
        this.score52Sp = score52Sp;
    }

    public Double getTotalGoal0Sp() {
        return totalGoal0Sp;
    }

    public void setTotalGoal0Sp(Double totalGoal0Sp) {
        this.totalGoal0Sp = totalGoal0Sp;
    }

    public Double getTotalGoal1Sp() {
        return totalGoal1Sp;
    }

    public void setTotalGoal1Sp(Double totalGoal1Sp) {
        this.totalGoal1Sp = totalGoal1Sp;
    }

    public Double getTotalGoal2Sp() {
        return totalGoal2Sp;
    }

    public void setTotalGoal2Sp(Double totalGoal2Sp) {
        this.totalGoal2Sp = totalGoal2Sp;
    }

    public Double getTotalGoal3Sp() {
        return totalGoal3Sp;
    }

    public void setTotalGoal3Sp(Double totalGoal3Sp) {
        this.totalGoal3Sp = totalGoal3Sp;
    }

    public Double getTotalGoal4Sp() {
        return totalGoal4Sp;
    }

    public void setTotalGoal4Sp(Double totalGoal4Sp) {
        this.totalGoal4Sp = totalGoal4Sp;
    }

    public Double getTotalGoal5Sp() {
        return totalGoal5Sp;
    }

    public void setTotalGoal5Sp(Double totalGoal5Sp) {
        this.totalGoal5Sp = totalGoal5Sp;
    }

    public Double getTotalGoal6Sp() {
        return totalGoal6Sp;
    }

    public void setTotalGoal6Sp(Double totalGoal6Sp) {
        this.totalGoal6Sp = totalGoal6Sp;
    }

    public Double getTotalGoal7Sp() {
        return totalGoal7Sp;
    }

    public void setTotalGoal7Sp(Double totalGoal7Sp) {
        this.totalGoal7Sp = totalGoal7Sp;
    }

    public Double getHalfCourtFFSp() {
        return halfCourtFFSp;
    }

    public void setHalfCourtFFSp(Double halfCourtFFSp) {
        this.halfCourtFFSp = halfCourtFFSp;
    }

    public Double getHalfCourtFPSp() {
        return halfCourtFPSp;
    }

    public void setHalfCourtFPSp(Double halfCourtFPSp) {
        this.halfCourtFPSp = halfCourtFPSp;
    }

    public Double getHalfCourtFSSp() {
        return halfCourtFSSp;
    }

    public void setHalfCourtFSSp(Double halfCourtFSSp) {
        this.halfCourtFSSp = halfCourtFSSp;
    }

    public Double getHalfCourtPFSp() {
        return halfCourtPFSp;
    }

    public void setHalfCourtPFSp(Double halfCourtPFSp) {
        this.halfCourtPFSp = halfCourtPFSp;
    }

    public Double getHalfCourtPPSp() {
        return halfCourtPPSp;
    }

    public void setHalfCourtPPSp(Double halfCourtPPSp) {
        this.halfCourtPPSp = halfCourtPPSp;
    }

    public Double getHalfCourtPSSp() {
        return halfCourtPSSp;
    }

    public void setHalfCourtPSSp(Double halfCourtPSSp) {
        this.halfCourtPSSp = halfCourtPSSp;
    }

    public Double getHalfCourtSFSp() {
        return halfCourtSFSp;
    }

    public void setHalfCourtSFSp(Double halfCourtSFSp) {
        this.halfCourtSFSp = halfCourtSFSp;
    }

    public Double getHalfCourtSPSp() {
        return halfCourtSPSp;
    }

    public void setHalfCourtSPSp(Double halfCourtSPSp) {
        this.halfCourtSPSp = halfCourtSPSp;
    }

    public Double getHalfCourtSSSp() {
        return halfCourtSSSp;
    }

    public void setHalfCourtSSSp(Double halfCourtSSSp) {
        this.halfCourtSSSp = halfCourtSSSp;
    }


    public Integer getOperatorsAward() {
        return operatorsAward;
    }

    public void setOperatorsAward(Integer operatorsAward) {
        this.operatorsAward = operatorsAward;
    }

    public Double getWinOrNegaSp() {
        return winOrNegaSp;
    }

    public void setWinOrNegaSp(Double winOrNegaSp) {
        this.winOrNegaSp = winOrNegaSp;
    }

    public Double getScoreSp() {
        return scoreSp;
    }

    public void setScoreSp(Double scoreSp) {
        this.scoreSp = scoreSp;
    }

    public Double getTotalGoalSp() {
        return totalGoalSp;
    }

    public void setTotalGoalSp(Double totalGoalSp) {
        this.totalGoalSp = totalGoalSp;
    }

    public Double getHalfCourtSp() {
        return halfCourtSp;
    }

    public void setHalfCourtSp(Double halfCourtSp) {
        this.halfCourtSp = halfCourtSp;
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

    public Integer getEndStatus() {
        return endStatus;
    }

    public void setEndStatus(Integer endStatus) {
        this.endStatus = endStatus;
    }

    public Integer getInputAwardStatus() {
        return inputAwardStatus;
    }

    public void setInputAwardStatus(Integer inputAwardStatus) {
        this.inputAwardStatus = inputAwardStatus;
    }

    public String getBackup1() {
        return backup1;
    }

    public void setBackup1(String backup1) {
        this.backup1 = backup1;
    }

    public String getBackup2() {
        return backup2;
    }

    public void setBackup2(String backup2) {
        this.backup2 = backup2;
    }

    public String getBackup3() {
        return backup3;
    }

    public void setBackup3(String backup3) {
        this.backup3 = backup3;
    }

    public String getGuestTeamLogo() {
        return guestTeamLogo;
    }

    public void setGuestTeamLogo(String guestTeamLogo) {
        this.guestTeamLogo = guestTeamLogo;
    }

    public String getMainTeamLogo() {
        return mainTeamLogo;
    }

    public void setMainTeamLogo(String mainTeamLogo) {
        this.mainTeamLogo = mainTeamLogo;
    }

    public Integer getBonusToAccount() {
        return bonusToAccount;
    }

    public void setBonusToAccount(Integer bonusToAccount) {
        this.bonusToAccount = bonusToAccount;
    }

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getEndDanShiTime() {
		return endDanShiTime;
	}

	public void setEndDanShiTime(String endDanShiTime) {
		this.endDanShiTime = endDanShiTime;
	}

	public String getEndFuShiTime() {
		return endFuShiTime;
	}

	public void setEndFuShiTime(String endFuShiTime) {
		this.endFuShiTime = endFuShiTime;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
}
