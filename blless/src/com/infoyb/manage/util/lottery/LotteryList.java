package com.cm.manage.util.lottery;

import com.cm.manage.util.ConfigUtils;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.base.ResourceUtil;
import com.cm.manage.util.lottery.bean.*;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LotteryList {
    private static Map<String, LotteryClass> map = new HashMap<String, LotteryClass>();
    private static Map<String, LotteryBean> lotteryBeanMap = new HashMap<String, LotteryBean>();
    private static List<LotteryClass> lotteryClassList = new ArrayList<LotteryClass>();
    private static Map<String, List<LotteryTime>> lotteryTimeMap = new HashMap<String, List<LotteryTime>>();

    static {
        forInstance();
    }

    public static void forInstance() {
        try {
            SAXReader xmlReader = new SAXReader();
            Document document = xmlReader.read(ResourceUtil.getClassPath() + ConfigUtils.getValue("LOTTERY_LIST_PATH"));
            List list = document.selectNodes("/lotterys/lottery");
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                Element element = (Element) iterator.next();
                LotteryClass lotteryClass = new LotteryClass();
                lotteryClass.setCode(element.attributeValue("code"));
                lotteryClass.setName(element.attributeValue("name"));
                lotteryClass.setDialect(element.attributeValue("dialect"));
                lotteryClass.setCurrent(CommonUtil.formatInt(element.attributeValue("current"), 1));
                lotteryClass.setSubPrograms(CommonUtil.formatInt(element.attributeValue("subPrograms"), 500));
                lotteryClass.setModify(CommonUtil.formatInt(element.attributeValue("modify"), 0));
                lotteryClass.setDashi(CommonUtil.formatLong(element.attributeValue("dashi"), 0));
                lotteryClass.setEarly(CommonUtil.formatLong(element.attributeValue("early"), 0));
                lotteryClass.setAuto(CommonUtil.formatInt(element.attributeValue("auto"), 0));
                lotteryClass.setPutOff(CommonUtil.formatLong(element.attributeValue("putOff"), 0));
                lotteryClass.setHandworkBonus(CommonUtil.formatDouble(element.attributeValue("handworkBonus"), 0d));
                lotteryClassList.add(lotteryClass);
                Element lotteryTimes = element.element("lotteryTimes");
                if (null != lotteryTimes) {
                    List<Element> lotteryTimeList = lotteryTimes.elements("lotteryTime");
                    if (null != lotteryTimeList) {
                        for (Element sub : lotteryTimeList) {
                            LotteryTime lotteryTime = new LotteryTime();
                            lotteryTime.setLotteryCode(lotteryClass.getCode());
                            lotteryTime.setAction(sub.attributeValue("action"));
                            lotteryTime.setStartTime(sub.attributeValue("startTime"));
                            lotteryTime.setEndTime(sub.attributeValue("endTime"));
                            lotteryTime.setWeek(sub.attributeValue("week"));
                            lotteryTime.setAddDate(CommonUtil.formatInt(sub.attributeValue("addDate"), 0));
                            if (lotteryTimeMap.containsKey(lotteryTime.getKey())) {
                                lotteryTimeMap.get(lotteryTime.getKey()).add(lotteryTime);
                            } else {
                                List<LotteryTime> temp = new ArrayList<LotteryTime>();
                                temp.add(lotteryTime);
                                lotteryTimeMap.put(lotteryTime.getKey(), temp);
                            }
                        }
                    }
                }

                List<Element> playList = element.elements("play");
                List<LotteryPlay> lotteryPlays = new ArrayList<LotteryPlay>();
                LotteryBean lotteryInfo1 = new LotteryBean();
                lotteryInfo1.setLotteryClass(lotteryClass);
                lotteryInfo1.setLotteryCode(new StringBuffer(lotteryClass.getCode()).toString());
                lotteryBeanMap.put(lotteryInfo1.getLotteryCode(), lotteryInfo1);
                for (Element play : playList) {
                    LotteryPlay lotteryPlay = new LotteryPlay();
                    lotteryPlay.setCode(play.attributeValue("code"));
                    lotteryPlay.setName(play.attributeValue("name"));
                    lotteryPlay.setSinglePassTicket(CommonUtil.formatInt(play.attributeValue("singlePassTicket"), 1));
                    lotteryPlay.setEachamount(CommonUtil.formatInt(play.attributeValue("eachamount"), 2));

                    List<Element> pollList = play.elements();
                    List<LotteryPoll> lotteryPolls = new ArrayList<LotteryPoll>();
                    LotteryBean lotteryInfo2 = new LotteryBean();
                    lotteryInfo2.setLotteryClass(lotteryClass);
                    lotteryInfo2.setLotteryPlay(lotteryPlay);
                    lotteryInfo2.setLotteryCode(new StringBuffer(lotteryClass.getCode()).append(lotteryPlay.getCode()).toString());
                    lotteryBeanMap.put(lotteryInfo2.getLotteryCode(), lotteryInfo2);

                    for (Element poll : pollList) {
                        LotteryPoll lotteryPoll = new LotteryPoll();
                        lotteryPoll.setCode(poll.attributeValue("code"));
                        lotteryPoll.setDb(Integer.parseInt(poll.attributeValue("db")));
                        lotteryPoll.setName(poll.attributeValue("name"));
                        lotteryPoll.setTicket(Integer.parseInt(poll.attributeValue("ticket")));
                        lotteryPoll.setExamina(poll.attributeValue("examina"));
                        lotteryPolls.add(lotteryPoll);

                        LotteryBean lotteryBean3 = new LotteryBean();
                        lotteryBean3.setLotteryClass(lotteryClass);
                        lotteryBean3.setLotteryPlay(lotteryPlay);
                        lotteryBean3.setLotteryPoll(lotteryPoll);
                        lotteryBean3.setLotteryCode(new StringBuffer(lotteryClass.getCode()).append(lotteryPlay.getCode()).append(lotteryPoll.getCode()).toString());
                        lotteryBeanMap.put(lotteryBean3.getLotteryCode(), lotteryBean3);

                    }
                    lotteryPlay.setLotteryPollList(lotteryPolls);
                    lotteryPlays.add(lotteryPlay);
                }
                lotteryClass.setLotteryPlayList(lotteryPlays);
                map.put(lotteryClass.getCode(), lotteryClass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static LotteryClass getLotteryClass(String code) {
        if (map.containsKey(code)) {
            return map.get(code);
        }
        return null;
    }

    public static LotteryPlay getLotteryPlay(String classCode, String playCode) {
        classCode = CommonUtil.formatStr(classCode, null);
        playCode = CommonUtil.formatStr(playCode, null);
        if (null != classCode && null != playCode) {
            LotteryClass lotteryClass = getLotteryClass(classCode);
            List<LotteryPlay> lotteryPlays = lotteryClass.getLotteryPlayList();
            for (LotteryPlay lotteryPlay : lotteryPlays) {
                if (playCode.equals(lotteryPlay.getCode())) {
                    return lotteryPlay;
                }
            }
        }
        return null;
    }

    public static LotteryPoll getLotteryPoll(String classCode, String playCode, String pollCode) {
        classCode = CommonUtil.formatStr(classCode, null);
        playCode = CommonUtil.formatStr(playCode, null);
        pollCode = CommonUtil.formatStr(pollCode, null);
        if (null != classCode && null != playCode && null != pollCode) {
            LotteryClass lotteryClass = getLotteryClass(classCode);
            List<LotteryPlay> lotteryPlays = lotteryClass.getLotteryPlayList();
            LotteryPlay lotteryPlay = null;
            for (LotteryPlay sub : lotteryPlays) {
                if (playCode.equals(sub.getCode())) {
                    lotteryPlay = sub;
                    break;
                }
            }
            if (null != lotteryPlay) {
                List<LotteryPoll> lotteryPolls = lotteryPlay.getLotteryPollList();
                for (LotteryPoll lotteryPoll : lotteryPolls) {
                    if (pollCode.equals(lotteryPoll.getCode())) {
                        return lotteryPoll;
                    }
                }
            }
        }
        return null;
    }

    public static LotteryBean getLotteryBean(String code) {
        if (lotteryBeanMap.containsKey(code)) {
            return lotteryBeanMap.get(code);
        }
        return null;
    }

    public static Map<String, LotteryClass> getLotteryCodeMap() {
        Map<String, LotteryClass> lotteryClassMap = new HashMap<String, LotteryClass>();
        lotteryClassMap.putAll(map);
        return lotteryClassMap;
    }

    public static List<LotteryClass> getLotteryClassList() {
        List<LotteryClass> lotteryClasses = new ArrayList<LotteryClass>();
        lotteryClasses.addAll(lotteryClassList);
        return lotteryClasses;
    }

    public static boolean timeFind(String lotteryCode, String action) {
        List<LotteryTime> lotteryTimes = lotteryTimeMap.get(lotteryCode + "_" + action);
        if (null == lotteryTimes) {
            return true;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        boolean temp = false;
        Calendar currDate = Calendar.getInstance();
        long currTime = currDate.getTime().getTime();
        int currWeek = currDate.get(Calendar.DAY_OF_WEEK) - 1;
        for (LotteryTime lotteryTime : lotteryTimes) {
            String tempWeek = lotteryTime.getWeek();
            if (tempWeek.contains(currWeek + "")) {
                try {
                    Date startTime = simpleDateFormat.parse(dateFormat.format(currDate.getTime()) + " " + lotteryTime.getStartTime());
                    long startTimeV = startTime.getTime();
                    Date endTime = simpleDateFormat.parse(dateFormat.format(currDate.getTime()) + " " + lotteryTime.getEndTime());
                    long endTimeV = endTime.getTime();
                    if (endTime.getTime() < startTime.getTime()) {
                        endTimeV += (1000 * 60 * 60 * 24);
                    }
                    if (currTime >= startTimeV && currTime <= endTimeV) {
                        temp = true;
                        break;
                    }
                } catch (ParseException e) {
                    continue;
                }
            }
        }
        return temp;
    }


    public static List<LotteryTime> getLotteryTime(String lotteryCode, String action) {
        String key = new StringBuffer(lotteryCode).append("_").append(action).toString();
        if (lotteryTimeMap.containsKey(key)) {
            return lotteryTimeMap.get(key);
        }
        return new ArrayList<LotteryTime>();
    }


    public static LotteryTime getLotteryTime(String lotteryCode, String action, int week) {
        LotteryTime re = null;
        List<LotteryTime> lotteryTimes = getLotteryTime(lotteryCode, action);
        for (LotteryTime lotteryTime : lotteryTimes) {
            if (lotteryTime.getWeek().contains(week + "")) {
                re = lotteryTime;
                break;
            }
        }
        return re;
    }

    public static void main(String[] args) {
        LotteryTime lotteryTime = getLotteryTime("201", "sale", 6);
        System.out.println(lotteryTime.getStartTime());
    }


}
