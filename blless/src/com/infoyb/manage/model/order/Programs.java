package com.cm.manage.model.order;

import java.io.Serializable;
import java.util.Date;

public class Programs implements Serializable {
    private static final long serialVersionUID = 6783972831789411338L;
    private Long id;
    private String sid;//代理商号
    private String programsOrderId;// 方案编号
    private String autoOrderId;// 追号ID
    private String outOrderId;// 外部订单号
    private String orderId;//订单号
    private String userCode;//用户名
    private String presentedUserCode;//赠送人
    private String issue;//期次
    private String lotteryCode;//彩种编号
    private String playCode;//玩法编号
    private String pollCode;//选号方式
    private String title;//方案标题
    private Integer buyType;// 购买方式(1:代购、2:合买、4:追号)
    private String numberInfo;// 号码信息json//如果是竞彩的单式上传，存放选择的场次
    private String userInfo;//用户详细信息json
    private Integer orderStatus;// 订单状态
    private Integer sendStatus;// 发送状态(0, 等待发送，1，已发送)
    private Integer bonusStatus;// 中奖状态(0,等待开奖,1未中奖，2,中奖)
    private Integer privacy;//0公开 1不公开 2截止后公开 3跟单公开
    private Integer item;// 注数
    private Integer multiple;// 倍数

    private Double orderAmount;//方案金额
    private Double avgAmount;//每份金额
    private Integer totalWere;//总份数
    private Integer buyWere;// 认购份数
    private Double buyAmount;//认购金额
    private Integer lastWere;// 保底份数
    private Double lastAmount;//保底金额
    private Integer minWere;// 最底认购份数（默认1）

    private Integer commission;// 返佣比例
    private String description;// 方案宣言
    private Double fixBonusAmount;//
    private Double bonusAmount;// 方案中奖金额
    private Integer ticketCount;// 票总数
    private Integer bonusTicket;// 中奖票数
    private Integer successTicket;// 成功票数
    private Integer failureTicket;// 失败票数
    private Date createTime;// 创建时间
    private Date acceptTime;// 处理时间

    private Integer isTop;
    private String platform;//
    private String softVer;

    private Integer bonusToAccount;//是否派奖(0,1)
    private Integer bigBonus;//0小奖,1大奖

    private String backup1;
    private String backup2;
    private String backup3;

    private String gameStartId;//首关场次
    private Date gameStartTime;//首关开始时间
    private String gameEndId;//末关场次
    private Date gameEndTime;//末关结束时间
    private Date returnTime;
    private Date sendTime;
    private Date bonusTime;

    private Integer renGouCount;

    //优化方式 (0 非智能倍投 1 2 3 )
    private Integer optimizeType;
    //期望奖金
    private String expectBonus;
    //串关方式
    private String passModel;
    private Integer isHide;//是否隐藏
    private Integer isVerify;//是否审核

    public Programs() {
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public Integer getIsHide() {
        return isHide;
    }

    public void setIsHide(Integer isHide) {
        this.isHide = isHide;
    }

    public Integer getIsVerify() {
        return isVerify;
    }

    public void setIsVerify(Integer isVerify) {
        this.isVerify = isVerify;
    }

    public Programs(String programsOrderId, String lotteryCode) {
        this.programsOrderId = programsOrderId;
        this.lotteryCode = lotteryCode;
    }

    public Programs(String lotteryCode, String issue, String programsOrderId) {
        this.lotteryCode = lotteryCode;
        this.issue = issue;
        this.programsOrderId = programsOrderId;
    }

    public Programs(String lotteryCode, String issue, String programsOrderId, String autoOrderId) {
        this.lotteryCode = lotteryCode;
        this.issue = issue;
        this.programsOrderId = programsOrderId;
        this.autoOrderId = autoOrderId;
    }

    public Programs(String lotteryCode, String issue, String programsOrderId, String autoOrderId,Integer buyType) {
        this.lotteryCode = lotteryCode;
        this.issue = issue;
        this.programsOrderId = programsOrderId;
        this.autoOrderId = autoOrderId;
        this.buyType = buyType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getProgramsOrderId() {
        return programsOrderId;
    }

    public void setProgramsOrderId(String programsOrderId) {
        this.programsOrderId = programsOrderId;
    }

    public String getAutoOrderId() {
        return autoOrderId;
    }

    public void setAutoOrderId(String autoOrderId) {
        this.autoOrderId = autoOrderId;
    }

    public String getOutOrderId() {
        return outOrderId;
    }

    public void setOutOrderId(String outOrderId) {
        this.outOrderId = outOrderId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPresentedUserCode() {
        return presentedUserCode;
    }

    public void setPresentedUserCode(String presentedUserCode) {
        this.presentedUserCode = presentedUserCode;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getLotteryCode() {
        return lotteryCode;
    }

    public void setLotteryCode(String lotteryCode) {
        this.lotteryCode = lotteryCode;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getBuyType() {
        return buyType;
    }

    public void setBuyType(Integer buyType) {
        this.buyType = buyType;
    }

    public String getNumberInfo() {
        return numberInfo;
    }

    public void setNumberInfo(String numberInfo) {
        this.numberInfo = numberInfo;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Integer getBonusStatus() {
        return bonusStatus;
    }

    public void setBonusStatus(Integer bonusStatus) {
        this.bonusStatus = bonusStatus;
    }

    public Integer getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Integer privacy) {
        this.privacy = privacy;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public Integer getMultiple() {
        return multiple;
    }

    public void setMultiple(Integer multiple) {
        this.multiple = multiple;
    }

    public Integer getTotalWere() {
        return totalWere;
    }

    public void setTotalWere(Integer totalWere) {
        this.totalWere = totalWere;
    }

    public Integer getBuyWere() {
        return buyWere;
    }

    public void setBuyWere(Integer buyWere) {
        this.buyWere = buyWere;
    }

    public Integer getMinWere() {
        return minWere;
    }

    public void setMinWere(Integer minWere) {
        this.minWere = minWere;
    }

    public Integer getLastWere() {
        return lastWere;
    }

    public void setLastWere(Integer lastWere) {
        this.lastWere = lastWere;
    }

    public Integer getCommission() {
        return commission;
    }

    public void setCommission(Integer commission) {
        this.commission = commission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Double getBonusAmount() {
        return bonusAmount;
    }

    public void setBonusAmount(Double bonusAmount) {
        this.bonusAmount = bonusAmount;
    }

    public Integer getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(Integer ticketCount) {
        this.ticketCount = ticketCount;
    }

    public Integer getBonusTicket() {
        return bonusTicket;
    }

    public void setBonusTicket(Integer bonusTicket) {
        this.bonusTicket = bonusTicket;
    }

    public Integer getSuccessTicket() {
        return successTicket;
    }

    public void setSuccessTicket(Integer successTicket) {
        this.successTicket = successTicket;
    }

    public Integer getFailureTicket() {
        return failureTicket;
    }

    public void setFailureTicket(Integer failureTicket) {
        this.failureTicket = failureTicket;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Integer getBonusToAccount() {
        return bonusToAccount;
    }

    public void setBonusToAccount(Integer bonusToAccount) {
        this.bonusToAccount = bonusToAccount;
    }

    public Double getFixBonusAmount() {
        return fixBonusAmount;
    }

    public void setFixBonusAmount(Double fixBonusAmount) {
        this.fixBonusAmount = fixBonusAmount;
    }

    public Integer getBigBonus() {
        return bigBonus;
    }

    public void setBigBonus(Integer bigBonus) {
        this.bigBonus = bigBonus;
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

    public Date getGameEndTime() {
        return gameEndTime;
    }

    public void setGameEndTime(Date gameEndTime) {
        this.gameEndTime = gameEndTime;
    }

    public String getGameEndId() {
        return gameEndId;
    }

    public void setGameEndId(String gameEndId) {
        this.gameEndId = gameEndId;
    }

    public Date getGameStartTime() {
        return gameStartTime;
    }

    public void setGameStartTime(Date gameStartTime) {
        this.gameStartTime = gameStartTime;
    }

    public String getGameStartId() {
        return gameStartId;
    }

    public void setGameStartId(String gameStartId) {
        this.gameStartId = gameStartId;
    }

    public Integer getTop() {
        return isTop;
    }

    public void setTop(Integer top) {
        isTop = top;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public Date getBonusTime() {
        return bonusTime;
    }

    public void setBonusTime(Date bonusTime) {
        this.bonusTime = bonusTime;
    }


    public Integer getRenGouCount() {
        return renGouCount;
    }

    public void setRenGouCount(Integer renGouCount) {
        this.renGouCount = renGouCount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSoftVer() {
        return softVer;
    }

    public void setSoftVer(String softVer) {
        this.softVer = softVer;
    }

    public Double getAvgAmount() {
        return avgAmount;
    }

    public void setAvgAmount(Double avgAmount) {
        this.avgAmount = avgAmount;
    }

    public Double getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(Double buyAmount) {
        this.buyAmount = buyAmount;
    }

    public Double getLastAmount() {
        return lastAmount;
    }

    public void setLastAmount(Double lastAmount) {
        this.lastAmount = lastAmount;
    }

    public Integer getOptimizeType() {
        return optimizeType;
    }

    public void setOptimizeType(Integer optimizeType) {
        this.optimizeType = optimizeType;
    }

    public String getExpectBonus() {
        return expectBonus;
    }

    public void setExpectBonus(String expectBonus) {
        this.expectBonus = expectBonus;
    }

    public String getPassModel() {
        return passModel;
    }

    public void setPassModel(String passModel) {
        this.passModel = passModel;
    }
}
