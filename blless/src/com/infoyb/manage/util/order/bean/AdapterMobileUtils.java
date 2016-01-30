package com.cm.manage.util.order.bean;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.type.TypeReference;

import com.cm.manage.constant.IOrderConstants;
import com.cm.manage.model.order.Ticket;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.bonusClass.BonusClassUtils;
import com.cm.manage.util.json.JsonBinder;
import com.cm.manage.util.lottery.LotteryList;
import com.cm.manage.util.lottery.bean.LotteryBean;
import com.cm.manage.util.lottery.bean.NumberInfo;

public class AdapterMobileUtils {

    public static String spfScore(Integer mainTeamScore, Integer guestTeamScore, String letBall) {
        if (!CommonUtil.isNotEmpty(mainTeamScore) || !CommonUtil.isNotEmpty(guestTeamScore)) {
            return "";
        }
        if (!CommonUtil.isNotEmpty(letBall)) {
            letBall = "0";
        }
        //主让客
        if (letBall.startsWith("-")) {
            Double score = CommonUtil.formatDouble(letBall.replaceFirst("-", ""), 0.0);
            if (mainTeamScore.doubleValue() - guestTeamScore.doubleValue() > score.doubleValue()) {
                return "胜";
            } else if (mainTeamScore.doubleValue() - guestTeamScore.doubleValue() == score.doubleValue()) {
                return "平";
            } else if (mainTeamScore.doubleValue() - guestTeamScore < score.doubleValue()) {
                return "负";
            }
        } else if (letBall.startsWith("+")) { //客让主
            Double score = CommonUtil.formatDouble(letBall.replace("+", ""), 0.0);
            if (mainTeamScore.doubleValue() + score > guestTeamScore.doubleValue()) {
                return "胜";
            } else if (mainTeamScore.doubleValue() + score == guestTeamScore.doubleValue()) {
                return "平";
            } else if (mainTeamScore.doubleValue() + score < guestTeamScore.doubleValue()) {
                return "负";
            }
        } else {
            Double score = CommonUtil.formatDouble(letBall, 0.0);
            if (mainTeamScore.doubleValue() + score.doubleValue() > guestTeamScore.doubleValue()) {
                return "胜";
            } else if (mainTeamScore.doubleValue() + score.doubleValue() == guestTeamScore.doubleValue()) {
                return "平";
            } else if (mainTeamScore.doubleValue() + score.doubleValue() < guestTeamScore.doubleValue()) {
                return "负";
            }
        }
        return "";
    }

    public static String sfcScore(Integer mainTeamScore, Integer guestTeamScore) {
        if (!CommonUtil.isNotEmpty(mainTeamScore) || !CommonUtil.isNotEmpty(guestTeamScore) || 0 == mainTeamScore.intValue() || 0 == guestTeamScore.intValue()) {
            return "";
        }
        int score = 0;
        String result = "";
        if (mainTeamScore.intValue() > guestTeamScore.intValue()) {
            score = mainTeamScore.intValue() - guestTeamScore.intValue();
            result = "主";
        } else {
            score = guestTeamScore.intValue() - mainTeamScore.intValue();
            result = "客";
        }
        if (score >= 1 && score <= 5) {
            result += "1-5";
        } else if (score >= 6 && score <= 10) {
            result += "6-10";
        } else if (score >= 11 && score <= 15) {
            result += "11-15";
        } else if (score >= 16 && score <= 20) {
            result += "16-20";
        } else if (score >= 21 && score <= 25) {
            result += "21-25";
        } else if (score >= 26) {
            result += "26+";
        }
        return result;
    }

    public static String dxfScore(Integer mainTeamScore, Integer guestTeamScore, String preCast) {
        if (!CommonUtil.isNotEmpty(mainTeamScore) || !CommonUtil.isNotEmpty(guestTeamScore) || !CommonUtil.isNotEmpty(preCast)) {
            return "";
        }
        Double score = CommonUtil.formatDouble(preCast.replace("+", ""), 0.0);
        if (mainTeamScore.doubleValue() + guestTeamScore.doubleValue() > score.doubleValue()) {
            return "大";
        }
        if (mainTeamScore.doubleValue() + guestTeamScore.doubleValue() < score.doubleValue()) {
            return "小";
        }
        return "";
    }

    public static String sxdsScore(Integer mainTeamScore, Integer guestTeamScore) {
        if (!CommonUtil.isNotEmpty(mainTeamScore) || !CommonUtil.isNotEmpty(guestTeamScore)) {
            return "";
        }
        int num = mainTeamScore.intValue() + guestTeamScore.intValue();
        StringBuffer sb = new StringBuffer();
        if (num >= 3) {
            sb.append("上");
        } else {
            sb.append("下");
        }
        if (num % 2 == 0) {
            sb.append("双");
        } else {
            sb.append("单");
        }
        return sb.toString();
    }


    public static String bfScore(Integer mainTeamScore, Integer guestTeamScore) {
        if (!CommonUtil.isNotEmpty(mainTeamScore) || !CommonUtil.isNotEmpty(guestTeamScore)) {
            return "";
        }
        String result = "";
        //胜
        if (mainTeamScore.intValue() > guestTeamScore.intValue()) {
            if (mainTeamScore.intValue() <= 5 && guestTeamScore.intValue() <= 2) {
                return mainTeamScore + ":" + guestTeamScore;
            } else {
                return "胜其他";
            }
        } else if (mainTeamScore.equals(guestTeamScore)) {
            if (mainTeamScore.intValue() <= 3 && guestTeamScore.intValue() <= 3) {
                return mainTeamScore + ":" + guestTeamScore;
            } else {
                return "平其他";
            }
        } else if (mainTeamScore.intValue() < guestTeamScore.intValue()) {
            if (mainTeamScore.intValue() <= 2 && guestTeamScore.intValue() <= 5) {
                return mainTeamScore + ":" + guestTeamScore;
            } else {
                return "负其他";
            }
        }
        return result;
    }

    public static String bfScoreForBeiDan(Integer mainTeamScore, Integer guestTeamScore) {
        if (!CommonUtil.isNotEmpty(mainTeamScore) || !CommonUtil.isNotEmpty(guestTeamScore)) {
            return "";
        }
        String result = "";
        //胜
        if (mainTeamScore.intValue() > guestTeamScore.intValue()) {
            if (mainTeamScore.intValue() <= 4 && guestTeamScore.intValue() <= 2) {
                return mainTeamScore + ":" + guestTeamScore;
            } else {
                return "胜其他";
            }
        } else if (mainTeamScore.equals(guestTeamScore)) {
            if (mainTeamScore.intValue() <= 3 && guestTeamScore.intValue() <= 3) {
                return mainTeamScore + ":" + guestTeamScore;
            } else {
                return "平其他";
            }
        } else if (mainTeamScore.intValue() < guestTeamScore.intValue()) {
            if (mainTeamScore.intValue() <= 2 && guestTeamScore.intValue() <= 4) {
                return mainTeamScore + ":" + guestTeamScore;
            } else {
                return "负其他";
            }
        }
        return result;
    }

    public static String zjqsScore(Integer mainTeamScore, Integer guestTeamScore) {
        if (!CommonUtil.isNotEmpty(mainTeamScore) || !CommonUtil.isNotEmpty(guestTeamScore)) {
            return "";
        }
        String result = "";
        if (mainTeamScore.intValue() + guestTeamScore.intValue() >= 7) {
            result = "7+";
        } else {
            result = (mainTeamScore + guestTeamScore) + "";
        }
        return result;
    }

    public static String percent(double num) {
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        return numberFormat.format(num);
    }

    public static String percentForInt(double num) {
        NumberFormat integerNumberFormat = NumberFormat.getIntegerInstance();
        integerNumberFormat.setRoundingMode(RoundingMode.FLOOR);
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        numberFormat.setMaximumFractionDigits(2);
        return numberFormat.format(CommonUtil.formatDouble(integerNumberFormat.format(num * 100), 0d) / 100);

    }


    public static String huoMaiPercent(Integer totalWere, Integer buyWere, Integer lastWere) {
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        StringBuffer temp = new StringBuffer(numberFormat.format(buyWere.doubleValue() / totalWere.doubleValue()));
        if (lastWere > 0) {
            temp.append("+");
            temp.append(numberFormat.format(lastWere.doubleValue() / totalWere.doubleValue()));
        }
        return temp.toString();
    }


    public static String huoMaiPercentForList(Integer totalWere, Integer buyWere, Integer lastWere) {
        NumberFormat integerNumberFormat = NumberFormat.getIntegerInstance();
        integerNumberFormat.setRoundingMode(RoundingMode.FLOOR);

        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        numberFormat.setMaximumIntegerDigits(3);
        numberFormat.setMaximumFractionDigits(2);

        double buyNum = buyWere.doubleValue() / totalWere.doubleValue();

        StringBuffer temp = new StringBuffer(numberFormat.format(CommonUtil.formatDouble(integerNumberFormat.format(buyNum * 100), 0d) / 100));
        if (lastWere > 0) {
            double lastNum = lastWere.doubleValue() / totalWere.doubleValue();
            temp.append("+");
            temp.append(numberFormat.format(CommonUtil.formatDouble(integerNumberFormat.format(lastNum * 100), 0d) / 100));
        }
        return temp.toString();
    }


    //0 待出票  1 已出票  2 投注失败   3 撤单  4 未中奖 5 中奖
    // 6 待满员 7 部分成功
    public static Integer orderStatus(Integer buyType, Integer orderStatus, Integer bonusStatus, Integer totalWere, Integer buyWere) {

        if (buyType.intValue() == IOrderConstants.BUY_TYPE_HUO_MAI) {

            if (orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_DEFAULT
                    || orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_WAIT) {
                if (totalWere.intValue() > buyWere.intValue()) {
                    return 6;
                } else {
                    return 0;
                }
            }

            if (orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_SUCCESS) {
                if (bonusStatus.intValue() == IOrderConstants.BONUS_STATUS_WAIT) {
                    if (totalWere.intValue() > buyWere.intValue()) {
                        return 6;
                    } else {
                        return 1;
                    }
                }
                if (bonusStatus.intValue() == IOrderConstants.BONUS_STATUS_YES) {
                    return 5;
                }
                if (bonusStatus.intValue() == IOrderConstants.BONUS_STATUS_NO) {
                    return 4;
                }
            }
            if (orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_FAILURE) {
                return 2;
            }
            if (orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_ADM_CANCEL
                    || orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_SYS_CANCEL
                    || orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_USER_CANCEL
                    || orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_TIME_OUT) {
                return 3;
            }
            if (orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_SOME_FAILURE) {
                return 7;
            }
            return 6;
        } else {
            if (orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_DEFAULT
                    || orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_WAIT) {
                return 0;
            }

            if (orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_SUCCESS) {
                if (bonusStatus.intValue() == IOrderConstants.BONUS_STATUS_WAIT) {
                    return 1;
                }
                if (bonusStatus.intValue() == IOrderConstants.BONUS_STATUS_YES) {
                    return 5;
                }
                if (bonusStatus.intValue() == IOrderConstants.BONUS_STATUS_NO) {
                    return 4;
                }
            }
            if (orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_FAILURE) {
                return 2;
            }
            if (orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_ADM_CANCEL
                    || orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_SYS_CANCEL
                    || orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_USER_CANCEL
                    || orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_TIME_OUT) {
                return 3;
            }
            if (orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_SOME_FAILURE) {
                return 7;
            }
            return 0;
        }
    }

    //0 待出票  1 已出票   2 投注失败   3 撤单  4 未中奖 5 中奖 6 待满员 7 部分成功
    public static String orderStatusDesc(Integer buyType, Integer orderStatus, Integer bonusStatus, Integer totalWere, Integer buyWere) {

        if (buyType.intValue() == IOrderConstants.BUY_TYPE_HUO_MAI) {
            if (orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_DEFAULT
                    || orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_WAIT) {
                if (totalWere.intValue() > buyWere.intValue()) {
                    return "待满员";
                } else {
                    return "待出票";
                }
            }
            if (orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_SUCCESS) {
                if (bonusStatus.intValue() == IOrderConstants.BONUS_STATUS_WAIT) {
                    if (totalWere.intValue() > buyWere) {
                        return "待满员";
                    } else {
                        return "已出票";
                    }
                }
                if (bonusStatus.intValue() == IOrderConstants.BONUS_STATUS_YES) {
                    return "中奖";
                }
                if (bonusStatus.intValue() == IOrderConstants.BONUS_STATUS_NO) {
                    return "未中奖";
                }
            }
            if (orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_FAILURE) {
                return "投注失败";
            }
            if (orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_ADM_CANCEL
                    || orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_SYS_CANCEL
                    || orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_USER_CANCEL
                    || orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_TIME_OUT) {
                return "撤单";
            }
            if (orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_SOME_FAILURE) {
                return "部分成功";
            }
            return "待满员";
        } else {

            if (orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_DEFAULT
                    || orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_WAIT) {
                return "待出票";
            }
            if (orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_SUCCESS) {
                if (bonusStatus.intValue() == IOrderConstants.BONUS_STATUS_WAIT) {
                    return "已出票";
                }
                if (bonusStatus.intValue() == IOrderConstants.BONUS_STATUS_YES) {
                    return "中奖";
                }
                if (bonusStatus.intValue() == IOrderConstants.BONUS_STATUS_NO) {
                    return "未中奖";
                }

            }
            if (orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_FAILURE) {
                return "投注失败";
            }
            if (orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_ADM_CANCEL
                    || orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_SYS_CANCEL
                    || orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_USER_CANCEL
                    || orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_TIME_OUT) {
                return "撤单";
            }
            if (orderStatus.intValue() == IOrderConstants.PROGRAMS_STATUS_SOME_FAILURE) {
                return "部分成功";
            }
            return "待出票";
        }
    }

    //0 待出票 1 追号中 2 未中奖 3 中奖 4 撤单
    public static Integer autoOrderStatus(Integer status, Integer bonusStatus) {
        if (bonusStatus.intValue() == IOrderConstants.BONUS_STATUS_YES) {
            return 3;
        }
        if (bonusStatus.intValue() == IOrderConstants.BONUS_STATUS_NO) {
            return 2;
        }
        if (status.intValue() == IOrderConstants.AUTO_ORDER_STATUS_DOING) {
            return 1;
        }
        if (status.intValue() == IOrderConstants.AUTO_ORDER_STATUS_ADM_CANCEL
                || status.intValue() == IOrderConstants.AUTO_ORDER_STATUS_SYS_CANCEL
                || status.intValue() == IOrderConstants.AUTO_ORDER_STATUS_USER_CANCEL) {
            return 4;
        }
        return 1;
    }

    //0 待出票 1 追号中 2 未中奖 3 中奖X元   4 撤单
    public static String autoOrderStatusDesc(Integer status, Integer bonusStatus) {
        if (bonusStatus.intValue() == IOrderConstants.BONUS_STATUS_YES) {
            return "中奖";
        }
        if (bonusStatus.intValue() == IOrderConstants.BONUS_STATUS_NO) {
            return "未中奖";
        }
        if (status.intValue() == IOrderConstants.AUTO_ORDER_STATUS_DOING) {
            return "追号中";
        }
        if (status.intValue() == IOrderConstants.AUTO_ORDER_STATUS_ADM_CANCEL
                || status.intValue() == IOrderConstants.AUTO_ORDER_STATUS_SYS_CANCEL
                || status.intValue() == IOrderConstants.AUTO_ORDER_STATUS_USER_CANCEL) {
            return "撤单";
        }
        return "追号中";
    }

    //0公开 1不公开 2截止后公开 3跟单公开
    public static String privacyDesc(Integer privacy) {
        if (privacy.intValue() == 0) {
            return "公开";
        } else if (privacy.intValue() == 1) {
            return "保密";
        } else if (privacy.intValue() == 2) {
            return "截止可见";
        } else if (privacy.intValue() == 3) {
            return "跟单可见";
        }
        return "公开";
    }

    public static String buyTypeDesc(Integer buyType) {
        if (buyType.intValue() == IOrderConstants.BUY_TYPE_DAI_GOU) {
            return "代购";
        } else if (buyType.intValue() == IOrderConstants.BUY_TYPE_ZHUI_HAO) {
            return "追号";
        } else if (buyType.intValue() == IOrderConstants.BUY_TYPE_HUO_MAI) {
            return "合买";
        }
        return "代购";
    }

    public static String orderTypeDesc(Integer orderType) {
        if (orderType.intValue() == IOrderConstants.ORDER_TYPE_PT) {
            return "代购";
        } else if (orderType.intValue() == IOrderConstants.ORDER_TYPE_ZH) {
            return "追号";
        } else if (orderType.intValue() == IOrderConstants.ORDER_TYPE_FQHM) {
            return "发起合买";
        } else if (orderType.intValue() == IOrderConstants.ORDER_TYPE_BD) {
            return "合买保底";
        } else if (orderType.intValue() == IOrderConstants.ORDER_TYPE_HMRG) {
            return "合买认购";
        } else if (orderType.intValue() == IOrderConstants.ORDER_TYPE_XTBD) {
            return "系统保底";
        } else if (orderType.intValue() == IOrderConstants.ORDER_TYPE_ZDGD) {
            return "自动跟单";
        }
        return "代购";
    }

    public static List<NumberBean> numberList(String lotteryCode, String numberInfoStr) {
        List<NumberBean> numberList = new ArrayList<NumberBean>();
        List<NumberInfo> numberInfoList = null;
        try {
            numberInfoList = JsonBinder.buildNonDefaultBinder().getMapper().readValue(numberInfoStr, new TypeReference<List<NumberInfo>>() {
            });
        } catch (Exception e) {
            return numberList;
        }
        for (NumberInfo numberInfo : numberInfoList) {
            LotteryBean lotteryBean = LotteryList.getLotteryBean(lotteryCode + numberInfo.getPlayCode() + numberInfo.getPollCode());
            NumberBean numberBean = new NumberBean();
            numberBean.setSequence(numberInfo.getSequence());
            numberBean.setPlayCode(numberInfo.getPlayCode());
            numberBean.setPlayName(lotteryBean.getLotteryPlay().getName());
            numberBean.setPollCode(lotteryBean.getLotteryPoll().getCode());
            numberBean.setPollName(lotteryBean.getLotteryPoll().getName());
            numberBean.setNumber(numberInfo.getNumber());
            numberBean.setItem(numberInfo.getItem());
            numberBean.setMultiple(numberInfo.getMultiple());
            numberList.add(numberBean);
        }
        return numberList;
    }

    //0 待出票 1  已出票 2 出票失败 3 未中奖 4 中奖 5 限号失败
    public static Integer getTicketStatus(Integer ticketStatus, Integer bonusStatus) {
        if (bonusStatus.intValue() == IOrderConstants.BONUS_STATUS_YES) {
            return 4;
        }
        if (bonusStatus.intValue() == IOrderConstants.BONUS_STATUS_NO) {
            return 3;
        }
        if (ticketStatus.intValue() == IOrderConstants.TICKET_STATUS_SUCCESS) {
            return 1;
        }
        if (ticketStatus.intValue() == IOrderConstants.TICKET_STATUS_LIMIT) {
            return 5;
        }
        if (ticketStatus.intValue() == IOrderConstants.TICKET_STATUS_FAILURE
                || ticketStatus.intValue() == IOrderConstants.TICKET_STATUS_CANCEL
                || ticketStatus.intValue() == IOrderConstants.TICKET_STATUS_TIME_OUT) {
            return 2;
        }
        return 0;
    }


    //0 待出票 1  已出票 2 出票失败 3 未中奖 4 中奖 5 限号失败
    public static String getTicketStatusDesc(Integer ticketStatus, Integer bonusStatus) {
        if (bonusStatus.intValue() == IOrderConstants.BONUS_STATUS_YES) {
            return "中奖";
        }
        if (bonusStatus.intValue() == IOrderConstants.BONUS_STATUS_NO) {
            return "未中奖";
        }
        if (ticketStatus.intValue() == IOrderConstants.TICKET_STATUS_SUCCESS) {
            return "已出票";
        }
        if (ticketStatus.intValue() == IOrderConstants.TICKET_STATUS_LIMIT) {
            return "限号";
        }
        if (ticketStatus.intValue() == IOrderConstants.TICKET_STATUS_FAILURE
                || ticketStatus.intValue() == IOrderConstants.TICKET_STATUS_CANCEL
                || ticketStatus.intValue() == IOrderConstants.TICKET_STATUS_TIME_OUT) {
            return "失败";
        }
        return "待出票";
    }

    public static String getWinStopDesc(Integer winStop) {
        if (winStop.intValue() == IOrderConstants.BONUS_STOP_NO) {
            return "中奖不停追";
        }
        if (winStop.intValue() == IOrderConstants.BONUS_STOP_YES) {
            return "中奖停追";
        }
        return "";
    }

    public static String getMatchStatusDesc(Integer status) {
        if (status.intValue() == IOrderConstants.SUB_ISSUE_STATUS_WAIT) {
            return "未开赛";
        }
        if (status.intValue() == IOrderConstants.SUB_ISSUE_STATUS_STARTING) {
            return "比赛中";
        }
        if (status.intValue() == IOrderConstants.SUB_ISSUE_STATUS_END) {
            return "已结束";
        }
        if (status.intValue() == IOrderConstants.SUB_ISSUE_STATUS_CANCEL) {
            return "已取消";
        }
        return "未开赛";
    }

    public static String formatGuoGuan(String guoGuan) {
        if (!CommonUtil.isNotEmpty(guoGuan)) {
            return "";
        }
        String[] guoGuanArray = guoGuan.split(",");
        StringBuffer buffer = new StringBuffer();
        for (String sub : guoGuanArray) {
            buffer.append(",");
            if (sub.equals("1*1")) {
                buffer.append("单关");
            } else {
                buffer.append(sub.replace("*", "串"));
            }
        }
        return buffer.substring(1);
    }
}