package com.cm.manage.util.order.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.cm.manage.model.order.ReturnNumber;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.lottery.bean.MatchItemInfo;
import com.cm.manage.util.lottery.bean.NumberInfo;

/**
 * UserBean: mcs
 * Date: 12-2-21
 * Time: 下午4:55
 */
public class SubNumberUtils {

    private static Map<String, String> numberChinaMap = new HashMap<String, String>();

    private static Map<String, String> spMap = new HashMap<String, String>();

    private static final Map<String, String> footChinaPlayMap = new HashMap<String, String>();

    static {

        numberChinaMap.put("201011", "主胜");
        numberChinaMap.put("201012", "主负");
        numberChinaMap.put("201021", "主胜");
        numberChinaMap.put("201022", "主负");
        numberChinaMap.put("2010301", "主1-5");
        numberChinaMap.put("2010302", "主6-10");
        numberChinaMap.put("2010303", "主11-15");
        numberChinaMap.put("2010304", "主16-20");
        numberChinaMap.put("2010305", "主21-25");
        numberChinaMap.put("2010306", "主26+");

        numberChinaMap.put("2010311", "客1-5");
        numberChinaMap.put("2010312", "客6-10");
        numberChinaMap.put("2010313", "客11-15");
        numberChinaMap.put("2010314", "客16-20");
        numberChinaMap.put("2010315", "客21-25");
        numberChinaMap.put("2010316", "客26+");
        numberChinaMap.put("201041", "大");
        numberChinaMap.put("201042", "小");


        spMap.put("201011", "winSp");
        spMap.put("201012", "negaSp");
        spMap.put("201021", "letWinSp");
        spMap.put("201022", "letNegaSp");
        spMap.put("2010301", "winNegaDiffM1T5Sp");
        spMap.put("2010302", "winNegaDiffM6T10Sp");
        spMap.put("2010303", "winNegaDiffM11T15Sp");
        spMap.put("2010304", "winNegaDiffM16T20Sp");
        spMap.put("2010305", "winNegaDiffM21T25Sp");
        spMap.put("2010306", "winNegaDiffM26Sp");
        spMap.put("2010311", "winNegaDiffG1T5Sp");
        spMap.put("2010312", "winNegaDiffG6T10Sp");
        spMap.put("2010313", "winNegaDiffG11T15Sp");
        spMap.put("2010314", "winNegaDiffG16T20Sp");
        spMap.put("2010315", "winNegaDiffG21T25Sp");
        spMap.put("2010316", "winNegaDiffG26Sp");
        spMap.put("201041", "bigSp");
        spMap.put("201042", "littleSp");


        numberChinaMap.put("200013", "胜");
        numberChinaMap.put("200011", "平");
        numberChinaMap.put("200010", "负");

        numberChinaMap.put("200020", "0");
        numberChinaMap.put("200021", "1");
        numberChinaMap.put("200022", "2");
        numberChinaMap.put("200023", "3");
        numberChinaMap.put("200024", "4");
        numberChinaMap.put("200025", "5");
        numberChinaMap.put("200026", "6");
        numberChinaMap.put("200027", "7+");

        numberChinaMap.put("2000333", "胜胜");
        numberChinaMap.put("2000331", "胜平");
        numberChinaMap.put("2000330", "胜负");
        numberChinaMap.put("2000313", "平胜");
        numberChinaMap.put("2000311", "平平");
        numberChinaMap.put("2000310", "平负");
        numberChinaMap.put("2000303", "负胜");
        numberChinaMap.put("2000301", "负平");
        numberChinaMap.put("2000300", "负负");

        numberChinaMap.put("2000410", "1:0");
        numberChinaMap.put("2000420", "2:0");
        numberChinaMap.put("2000430", "3:0");
        numberChinaMap.put("2000440", "4:0");
        numberChinaMap.put("2000450", "5:0");
        numberChinaMap.put("2000421", "2:1");
        numberChinaMap.put("2000431", "3:1");
        numberChinaMap.put("2000441", "4:1");
        numberChinaMap.put("2000451", "5:1");
        numberChinaMap.put("2000432", "3:2");
        numberChinaMap.put("2000442", "4:2");
        numberChinaMap.put("2000452", "5:2");
        numberChinaMap.put("2000490", "胜其他");
        numberChinaMap.put("2000400", "0:0");
        numberChinaMap.put("2000411", "1:1");
        numberChinaMap.put("2000422", "2:2");
        numberChinaMap.put("2000433", "3:3");
        numberChinaMap.put("2000499", "平其他");
        numberChinaMap.put("2000401", "0:1");
        numberChinaMap.put("2000402", "0:2");
        numberChinaMap.put("2000403", "0:3");
        numberChinaMap.put("2000404", "0:4");
        numberChinaMap.put("2000405", "0:5");
        numberChinaMap.put("2000412", "1:2");
        numberChinaMap.put("2000413", "1:3");
        numberChinaMap.put("2000414", "1:4");
        numberChinaMap.put("2000415", "1:5");
        numberChinaMap.put("2000423", "2:3");
        numberChinaMap.put("2000424", "2:4");
        numberChinaMap.put("2000425", "2:5");
        numberChinaMap.put("2000409", "负其他");

        numberChinaMap.put("200053", "胜");
        numberChinaMap.put("200051", "平");
        numberChinaMap.put("200050", "负");


        spMap.put("200013", "winSp");
        spMap.put("200011", "flatSp");
        spMap.put("200010", "negaSp");

        spMap.put("200020", "totalGoal0Sp");
        spMap.put("200021", "totalGoal1Sp");
        spMap.put("200022", "totalGoal2Sp");
        spMap.put("200023", "totalGoal3Sp");
        spMap.put("200024", "totalGoal4Sp");
        spMap.put("200025", "totalGoal5Sp");
        spMap.put("200026", "totalGoal6Sp");
        spMap.put("200027", "totalGoal7Sp");

        spMap.put("2000333", "halfCourtSSSp");
        spMap.put("2000331", "halfCourtSPSp");
        spMap.put("2000330", "halfCourtSFSp");
        spMap.put("2000313", "halfCourtPSSp");
        spMap.put("2000311", "halfCourtPPSp");
        spMap.put("2000310", "halfCourtPFSp");
        spMap.put("2000303", "halfCourtFSSp");
        spMap.put("2000301", "halfCourtFPSp");
        spMap.put("2000300", "halfCourtFFSp");

        spMap.put("2000410", "score10Sp");
        spMap.put("2000420", "score20Sp");
        spMap.put("2000430", "score30Sp");
        spMap.put("2000440", "score40Sp");
        spMap.put("2000450", "score50Sp");
        spMap.put("2000421", "score21Sp");
        spMap.put("2000431", "score31Sp");
        spMap.put("2000441", "score41Sp");
        spMap.put("2000451", "score51Sp");
        spMap.put("2000432", "score32Sp");
        spMap.put("2000442", "score42Sp");
        spMap.put("2000452", "score52Sp");
        spMap.put("2000490", "scoreSQTSp");
        spMap.put("2000400", "score00Sp");
        spMap.put("2000411", "score11Sp");
        spMap.put("2000422", "score22Sp");
        spMap.put("2000433", "score33Sp");
        spMap.put("2000499", "scorePQTSp");
        spMap.put("2000401", "score01Sp");
        spMap.put("2000402", "score02Sp");
        spMap.put("2000403", "score03Sp");
        spMap.put("2000404", "score04Sp");
        spMap.put("2000405", "score05Sp");
        spMap.put("2000412", "score12Sp");
        spMap.put("2000413", "score13Sp");
        spMap.put("2000414", "score14Sp");
        spMap.put("2000415", "score15Sp");
        spMap.put("2000423", "score23Sp");
        spMap.put("2000424", "score24Sp");
        spMap.put("2000425", "score25Sp");
        spMap.put("2000409", "scoreFQTSp");

        spMap.put("200053", "spfWinSp");
        spMap.put("200051", "spfFlatSp");
        spMap.put("200050", "spfNegaSp");


        numberChinaMap.put("400013", "胜");
        numberChinaMap.put("400011", "平");
        numberChinaMap.put("400010", "负");

        numberChinaMap.put("4000201", "上单");
        numberChinaMap.put("4000202", "上双");
        numberChinaMap.put("4000231", "下单");
        numberChinaMap.put("4000232", "下双");

        numberChinaMap.put("400030", "0");
        numberChinaMap.put("400031", "1");
        numberChinaMap.put("400032", "2");
        numberChinaMap.put("400033", "3");
        numberChinaMap.put("400034", "4");
        numberChinaMap.put("400035", "5");
        numberChinaMap.put("400036", "6");
        numberChinaMap.put("400037", "7+");

        numberChinaMap.put("4000433", "胜胜");
        numberChinaMap.put("4000431", "胜平");
        numberChinaMap.put("4000430", "胜负");
        numberChinaMap.put("4000413", "平胜");
        numberChinaMap.put("4000411", "平平");
        numberChinaMap.put("4000410", "平负");
        numberChinaMap.put("4000403", "负胜");
        numberChinaMap.put("4000401", "负平");
        numberChinaMap.put("4000400", "负负");

        numberChinaMap.put("4000510", "1:0");
        numberChinaMap.put("4000520", "2:0");
        numberChinaMap.put("4000530", "3:0");
        numberChinaMap.put("4000540", "4:0");
        numberChinaMap.put("4000521", "2:1");
        numberChinaMap.put("4000531", "3:1");
        numberChinaMap.put("4000541", "4:1");
        numberChinaMap.put("4000532", "3:2");
        numberChinaMap.put("4000542", "4:2");
        numberChinaMap.put("4000590", "胜其他");
        numberChinaMap.put("4000500", "0:0");
        numberChinaMap.put("4000511", "1:1");
        numberChinaMap.put("4000522", "2:2");
        numberChinaMap.put("4000533", "3:3");
        numberChinaMap.put("4000599", "平其他");
        numberChinaMap.put("4000501", "0:1");
        numberChinaMap.put("4000502", "0:2");
        numberChinaMap.put("4000503", "0:3");
        numberChinaMap.put("4000504", "0:4");
        numberChinaMap.put("4000512", "1:2");
        numberChinaMap.put("4000513", "1:3");
        numberChinaMap.put("4000514", "1:4");
        numberChinaMap.put("4000523", "2:3");
        numberChinaMap.put("4000524", "2:4");
        numberChinaMap.put("4000509", "负其他");


        spMap.put("400013", "winSp");
        spMap.put("400011", "flatSp");
        spMap.put("400010", "negaSp");

        spMap.put("4000201", "shangDanSp");
        spMap.put("4000202", "shangShuangSp");
        spMap.put("4000231", "xiaDanSp");
        spMap.put("4000232", "xiaShuangSp");

        spMap.put("400030", "totalGoal0Sp");
        spMap.put("400031", "totalGoal1Sp");
        spMap.put("400032", "totalGoal2Sp");
        spMap.put("400033", "totalGoal3Sp");
        spMap.put("400034", "totalGoal4Sp");
        spMap.put("400035", "totalGoal5Sp");
        spMap.put("400036", "totalGoal6Sp");
        spMap.put("400037", "totalGoal7Sp");

        spMap.put("4000433", "halfCourtSSSp");
        spMap.put("4000431", "halfCourtSPSp");
        spMap.put("4000430", "halfCourtSFSp");
        spMap.put("4000413", "halfCourtPSSp");
        spMap.put("4000411", "halfCourtPPSp");
        spMap.put("4000410", "halfCourtPFSp");
        spMap.put("4000403", "halfCourtFSSp");
        spMap.put("4000401", "halfCourtFPSp");
        spMap.put("4000400", "halfCourtFFSp");

        spMap.put("4000510", "score10Sp");
        spMap.put("4000520", "score20Sp");
        spMap.put("4000530", "score30Sp");
        spMap.put("4000540", "score40Sp");
        spMap.put("4000521", "score21Sp");
        spMap.put("4000531", "score31Sp");
        spMap.put("4000541", "score41Sp");
        spMap.put("4000532", "score32Sp");
        spMap.put("4000542", "score42Sp");
        spMap.put("4000590", "scoreSQTSp");
        spMap.put("4000500", "score00Sp");
        spMap.put("4000511", "score11Sp");
        spMap.put("4000522", "score22Sp");
        spMap.put("4000533", "score33Sp");
        spMap.put("4000599", "scorePQTSp");
        spMap.put("4000501", "score01Sp");
        spMap.put("4000502", "score02Sp");
        spMap.put("4000503", "score03Sp");
        spMap.put("4000504", "score04Sp");
        spMap.put("4000512", "score12Sp");
        spMap.put("4000513", "score13Sp");
        spMap.put("4000514", "score14Sp");
        spMap.put("4000523", "score23Sp");
        spMap.put("4000524", "score24Sp");
        spMap.put("4000509", "scoreFQTSp");


        footChinaPlayMap.put("RQSPF", "01");
        footChinaPlayMap.put("JQS", "02");
        footChinaPlayMap.put("BQC", "03");
        footChinaPlayMap.put("CBF", "04");
        footChinaPlayMap.put("SPF", "05");
        footChinaPlayMap.put("HH", "10");


        numberChinaMap.put("500013", "胜");
        numberChinaMap.put("500011", "平");
        numberChinaMap.put("500010", "负");

        numberChinaMap.put("500020", "0");
        numberChinaMap.put("500021", "1");
        numberChinaMap.put("500022", "2");
        numberChinaMap.put("500023", "3");
        numberChinaMap.put("500024", "4");
        numberChinaMap.put("500025", "5");
        numberChinaMap.put("500026", "6");
        numberChinaMap.put("500027", "7+");

        numberChinaMap.put("500333", "胜胜");
        numberChinaMap.put("5000331", "胜平");
        numberChinaMap.put("5000330", "胜负");
        numberChinaMap.put("5000313", "平胜");
        numberChinaMap.put("5000311", "平平");
        numberChinaMap.put("5000310", "平负");
        numberChinaMap.put("5000303", "负胜");
        numberChinaMap.put("5000301", "负平");
        numberChinaMap.put("5000300", "负负");

        numberChinaMap.put("5000410", "1:0");
        numberChinaMap.put("5000420", "2:0");
        numberChinaMap.put("5000430", "3:0");
        numberChinaMap.put("5000440", "4:0");
        numberChinaMap.put("5000450", "5:0");
        numberChinaMap.put("5000421", "2:1");
        numberChinaMap.put("5000431", "3:1");
        numberChinaMap.put("5000441", "4:1");
        numberChinaMap.put("5000451", "5:1");
        numberChinaMap.put("5000432", "3:2");
        numberChinaMap.put("5000442", "4:2");
        numberChinaMap.put("5000452", "5:2");
        numberChinaMap.put("5000490", "胜其他");
        numberChinaMap.put("5000400", "0:0");
        numberChinaMap.put("5000411", "1:1");
        numberChinaMap.put("5000422", "2:2");
        numberChinaMap.put("5000433", "3:3");
        numberChinaMap.put("5000499", "平其他");
        numberChinaMap.put("5000401", "0:1");
        numberChinaMap.put("5000402", "0:2");
        numberChinaMap.put("5000403", "0:3");
        numberChinaMap.put("5000404", "0:4");
        numberChinaMap.put("5000405", "0:5");
        numberChinaMap.put("5000412", "1:2");
        numberChinaMap.put("5000413", "1:3");
        numberChinaMap.put("5000414", "1:4");
        numberChinaMap.put("5000415", "1:5");
        numberChinaMap.put("5000423", "2:3");
        numberChinaMap.put("5000424", "2:4");
        numberChinaMap.put("5000425", "2:5");
        numberChinaMap.put("5000409", "负其他");

        numberChinaMap.put("500053", "胜");
        numberChinaMap.put("500051", "平");
        numberChinaMap.put("500050", "负");


        spMap.put("500013", "winSp");
        spMap.put("500011", "flatSp");
        spMap.put("500010", "negaSp");

        spMap.put("50020", "totalGoal0Sp");
        spMap.put("500021", "totalGoal1Sp");
        spMap.put("500022", "totalGoal2Sp");
        spMap.put("500023", "totalGoal3Sp");
        spMap.put("500024", "totalGoal4Sp");
        spMap.put("500025", "totalGoal5Sp");
        spMap.put("500026", "totalGoal6Sp");
        spMap.put("500027", "totalGoal7Sp");

        spMap.put("5000333", "halfCourtSSSp");
        spMap.put("5000331", "halfCourtSPSp");
        spMap.put("5000330", "halfCourtSFSp");
        spMap.put("5000313", "halfCourtPSSp");
        spMap.put("5000311", "halfCourtPPSp");
        spMap.put("5000310", "halfCourtPFSp");
        spMap.put("5000303", "halfCourtFSSp");
        spMap.put("5000301", "halfCourtFPSp");
        spMap.put("5000300", "halfCourtFFSp");

        spMap.put("5000410", "score10Sp");
        spMap.put("5000420", "score20Sp");
        spMap.put("5000430", "score30Sp");
        spMap.put("5000440", "score40Sp");
        spMap.put("5000450", "score50Sp");
        spMap.put("5000421", "score21Sp");
        spMap.put("5000431", "score31Sp");
        spMap.put("5000441", "score41Sp");
        spMap.put("5000451", "score51Sp");
        spMap.put("5000432", "score32Sp");
        spMap.put("5000442", "score42Sp");
        spMap.put("5000452", "score52Sp");
        spMap.put("5000490", "scoreSQTSp");
        spMap.put("5000400", "score00Sp");
        spMap.put("5000411", "score11Sp");
        spMap.put("5000422", "score22Sp");
        spMap.put("5000433", "score33Sp");
        spMap.put("5000499", "scorePQTSp");
        spMap.put("5000401", "score01Sp");
        spMap.put("5000402", "score02Sp");
        spMap.put("5000403", "score03Sp");
        spMap.put("500404", "score04Sp");
        spMap.put("5000405", "score05Sp");
        spMap.put("5000412", "score12Sp");
        spMap.put("5000413", "score13Sp");
        spMap.put("5000414", "score14Sp");
        spMap.put("5000415", "score15Sp");
        spMap.put("5000423", "score23Sp");
        spMap.put("5000424", "score24Sp");
        spMap.put("5000425", "score25Sp");
        spMap.put("5000409", "scoreFQTSp");

        spMap.put("500053", "spfWinSp");
        spMap.put("500051", "spfFlatSp");
        spMap.put("500050", "spfNegaSp");
    }

    public static Map<String, Object> matchList(String number) {
        Map<String, Object> matchNumberMap = new HashMap<String, Object>();
        if (CommonUtil.isNotEmpty(number)) {
            try {
                String[] array1 = number.split("\\|");
                String matchs = array1[0];
                String type = "--";
                if (array1.length > 1) {
                    type = array1[1];
                }
                matchNumberMap.put("guoGuan", type);
                String[] matchArray = matchs.split(";");
                List<MatchItemInfo> matchItemInfoList = new ArrayList<MatchItemInfo>();
                if (matchArray.length > 0) {
                    for (String match : matchArray) {
                        if (match.split(":").length == 2) {
                            match += ":0";
                        }
                        MatchItemInfo matchItemInfo = MatchItemInfo.str2Object(match);
                        matchItemInfoList.add(matchItemInfo);
                    }
                }
                matchNumberMap.put("matchItemInfoList", matchItemInfoList);
                return matchNumberMap;
            } catch (Exception e) {
                return matchNumberMap;
            }
        }
        return matchNumberMap;
    }


    public static Map<String, Object> matchList(List<NumberInfo> numberInfoList) {
        Map<String, Object> matchNumberMap = new HashMap<String, Object>();
        try {
            List<MatchItemInfo> matchItemInfoList = new ArrayList<MatchItemInfo>();
            Map<String, MatchItemInfo> matchItemInfoMap = new HashMap<String, MatchItemInfo>();
            Set<String> guoguanSet = new HashSet<String>();
            for (NumberInfo numberInfo : numberInfoList) {
                String number = numberInfo.getNumber();
                if (CommonUtil.isNotEmpty(number)) {
                    String[] array1 = number.split("\\|");
                    String matchs = array1[0];
                    String type = "--";
                    if (array1.length > 1) {
                        type = array1[1];
                    }
                    guoguanSet.add(type);

                    String[] matchArray = matchs.split(";");

                    if (matchArray.length > 0) {
                        for (String match : matchArray) {
                            if (match.split(":").length == 2) {
                                match += ":0";
                            }
                            MatchItemInfo matchItemInfo = MatchItemInfo.str2Object(match);
                            if (!CommonUtil.isNotEmpty(matchItemInfo.getSubPlayCode())) {
                                matchItemInfo.setSubPlayCode(numberInfo.getPlayCode());
                            }
                            if (matchItemInfoMap.containsKey(matchItemInfo.getMatchId() + matchItemInfo.getSubPlayCode())) {
                                MatchItemInfo sub = matchItemInfoMap.get(matchItemInfo.getMatchId() + matchItemInfo.getSubPlayCode());
                                matchItemInfo.setValue(buildValue(matchItemInfo, sub));
                                matchItemInfoMap.put(matchItemInfo.getMatchId() + matchItemInfo.getSubPlayCode(), matchItemInfo);
                            } else {
                                matchItemInfoMap.put(matchItemInfo.getMatchId() + matchItemInfo.getSubPlayCode(), matchItemInfo);
                            }
                        }
                    }
                }
            }
            for (Map.Entry<String, MatchItemInfo> entry : matchItemInfoMap.entrySet()) {
                matchItemInfoList.add(entry.getValue());
            }

            StringBuilder sb = new StringBuilder();
            for (String guoguan : guoguanSet) {
                sb.append(",").append(guoguan);
            }
            matchNumberMap.put("guoGuan", sb.substring(1));
            matchNumberMap.put("matchItemInfoList", matchItemInfoList);
            return matchNumberMap;
        } catch (Exception e) {
            return matchNumberMap;
        }
    }

    private static String buildValue(MatchItemInfo matchItemInfo1, MatchItemInfo matchItemInfo2) {
        String[] value1Array = matchItemInfo1.getValue().split(",");
        String[] value2Array = matchItemInfo2.getValue().split(",");
        HashSet valueSet = new HashSet();
        for (String value : value1Array) {
            valueSet.add(value);
        }
        for (String value : value2Array) {
            valueSet.add(value);
        }
        Iterator it = valueSet.iterator();
        StringBuilder sb = new StringBuilder();
        while (it.hasNext()) {
            sb.append(",").append(it.next());
        }
        return sb.substring(1);
    }


    public static String getNumberChinaValue(String key) {
        if (numberChinaMap.containsKey(key)) {
            return numberChinaMap.get(key);
        }
        return "";
    }

    public static String getSpName(String key) {
        if (spMap.containsKey(key)) {
            return spMap.get(key);
        }
        return "";
    }

    /**
     * 投注号码格式化为Map
     * SPF|20120224301=3(2.85)/1(3.20),20120224302=3(3.55),20120224303=3(2.45)/0(3.85)|3*1
     *
     * @param returnNumberList
     * @return
     */
    public static Map<String, Object> formatBasketballReturnNumber(List<ReturnNumber> returnNumberList) {
        Map<String, Object> match = new HashMap<String, Object>();
        List<MatchItemInfo> matchItemInfoList = new ArrayList<MatchItemInfo>();
        for (ReturnNumber returnNumber : returnNumberList) {
            if (!CommonUtil.isNotEmpty(returnNumber) || !CommonUtil.isNotEmpty(returnNumber.getNumberInfo())) {
                continue;
            }
            for (String subNumberInfo : returnNumber.getNumberInfo().split(";")) {
                MatchItemInfo matchItemInfo = new MatchItemInfo();
                String[] str = subNumberInfo.split("\\|");
                String playTitle = str[1];
                String playCode = "";
                for (String matchStr : str[1].split(",")) {
                    String[] matchAtt = matchStr.split("=");
                    String matchId = matchAtt[0];//场次编号，如20120218(预设总分)
                    String matchNo = "";
                    String[] matchStrArr = matchId.split("\\(");
                    if (matchStrArr.length > 1) {
                        matchNo = matchStrArr[0];
                        //混合过关
                        if (matchNo.split(":").length > 1) {
                            playCode = matchNo.split(":")[1];
                            matchNo = matchNo.split(":")[0];
                        } else {
                            playCode = footChinaPlayMap.get(playTitle);
                        }
                        String preCaseStr = matchStrArr[1];
                        match.put(matchNo, preCaseStr.substring(0, preCaseStr.length() - 1));
                    } else {
                        matchNo = matchId;
                        playCode = footChinaPlayMap.get(playTitle);
                        //混合过关
                        if (matchNo.split(":").length > 1) {
                            playCode = matchNo.split(":")[1];
                            matchNo = matchNo.split(":")[0];
                        }
                    }

                    String[] numStrArr = matchAtt[1].split("/");
                    StringBuffer numberStr = new StringBuffer();
                    for (int i = 0; i < numStrArr.length; i++) {
                        match.put(matchNo + playCode + numStrArr[i].split("\\(")[0], numStrArr[i].substring(numStrArr[i].indexOf("(") + 1, numStrArr[i].length() - 1));
                        numberStr.append(",").append(numStrArr[i].split("\\(")[0]);
                    }

                    matchItemInfo.setMatchId(matchNo);
                    matchItemInfo.setDan(0);
                    matchItemInfo.setValue(numberStr.substring(1));

                    matchItemInfoList.add(matchItemInfo);
                }
            }
        }
        match.put("matchItemInfoList", matchItemInfoList);
        return match;
    }

    /**
     * 投注号码格式化为Map
     * SPF|20120224301=3(2.85)/1(3.20),20120224302=3(3.55),20120224303=3(2.45)/0(3.85)|3*1
     *
     * @param returnNumberList
     * @return
     */
    public static Map<String, Object> formatFootballReturnNumber(List<ReturnNumber> returnNumberList) {
        Map<String, Object> match = new HashMap<String, Object>();
        List<MatchItemInfo> matchItemInfoList = new ArrayList<MatchItemInfo>();
        for (ReturnNumber returnNumber : returnNumberList) {
            if (!CommonUtil.isNotEmpty(returnNumber) || !CommonUtil.isNotEmpty(returnNumber.getNumberInfo())) {
                continue;
            }

            for (String subNumberInfo : returnNumber.getNumberInfo().split(";")) {
                MatchItemInfo matchItemInfo = new MatchItemInfo();
                String[] str = subNumberInfo.split("\\|");
                String playTitle = str[0];
                for (String matchStr : str[1].split(",")) {
                    String[] matchAtt = matchStr.split("=");
                    String matchId = matchAtt[0];//场次编号，如20120218001
                    String matchNo = "";
                    String playCode = "";
                    String[] matchStrArr = matchId.split("\\(");
                    if (matchStrArr.length > 1) {
                        matchNo = matchStrArr[0];
                        //混合过关
                        if (matchNo.split(":").length > 1) {
                            playCode = matchNo.split(":")[1];
                            matchNo = matchNo.split(":")[0];
                        } else {
                            playCode = footChinaPlayMap.get(playTitle);
                        }
                        String preCaseStr = matchStrArr[1];
                        match.put(matchNo, preCaseStr.substring(0, preCaseStr.length() - 1));
                    } else {
                        matchNo = matchId;
                        playCode = footChinaPlayMap.get(playTitle);
                        //混合过关
                        if (matchNo.split(":").length > 1) {
                            playCode = matchNo.split(":")[1];
                            matchNo = matchNo.split(":")[0];
                        }
                    }
                    String[] numStrArr = matchAtt[1].split("/");
                    StringBuffer numberStr = new StringBuffer();
                    for (int i = 0; i < numStrArr.length; i++) {
                        match.put(matchNo + playCode + numStrArr[i].split("\\(")[0].replace(":", "").replace("-", ""), numStrArr[i].substring(numStrArr[i].indexOf("(") + 1, numStrArr[i].length() - 1));
                        numberStr.append(",").append(numStrArr[i].split("\\(")[0].replace(":", "").replace("-", ""));
                    }

                    matchItemInfo.setMatchId(matchNo);
                    matchItemInfo.setDan(0);
                    matchItemInfo.setSubPlayCode(playCode);
                    matchItemInfo.setValue(numberStr.substring(1));

                    matchItemInfoList.add(matchItemInfo);
                }
            }
        }
        match.put("matchItemInfoList", matchItemInfoList);
        return match;
    }


    /**
     * 投注号码格式化为Map
     * SPF|20120224301=3(2.85)/1(3.20),20120224302=3(3.55),20120224303=3(2.45)/0(3.85)|3*1
     *
     * @param returnNumberList
     * @return
     */
    public static Map<String, Object> formatBeiDanReturnNumber(List<ReturnNumber> returnNumberList) {
        Map<String, Object> match = new HashMap<String, Object>();
        List<MatchItemInfo> matchItemInfoList = new ArrayList<MatchItemInfo>();
        for (ReturnNumber returnNumber : returnNumberList) {
            if (!CommonUtil.isNotEmpty(returnNumber) || !CommonUtil.isNotEmpty(returnNumber.getNumberInfo())) {
                continue;
            }
            for (String subNumberInfo : returnNumber.getNumberInfo().split(";")) {
                MatchItemInfo matchItemInfo = new MatchItemInfo();
                String[] str = subNumberInfo.split("\\|");
                for (String matchStr : str[1].split(",")) {
                    String[] matchAtt = matchStr.split("=");
                    String matchId = matchAtt[0];//场次编号，如20120218001
                    String matchNo = "";
                    String[] matchStrArr = matchId.split("\\(");
                    if (matchStrArr.length > 1) {
                        matchNo = matchStrArr[0];
                        String preCaseStr = matchStrArr[1];
                        match.put(matchNo, preCaseStr.substring(0, preCaseStr.length() - 1));
                    } else {
                        matchNo = matchId;
                    }
                    String[] numStrArr = matchAtt[1].split("/");
                    StringBuffer numberStr = new StringBuffer();
                    for (int i = 0; i < numStrArr.length; i++) {
                        match.put(matchNo + numStrArr[i].split("\\(")[0].replace(":", "").replace("-", ""), numStrArr[i].substring(numStrArr[i].indexOf("(") + 1, numStrArr[i].length() - 1));
                        numberStr.append(",").append(numStrArr[i].split("\\(")[0].replace(":", "").replace("-", ""));
                    }

                    matchItemInfo.setMatchId(matchNo);
                    matchItemInfo.setDan(0);
                    matchItemInfo.setValue(numberStr.substring(1));

                    matchItemInfoList.add(matchItemInfo);
                }
            }
        }
        match.put("matchItemInfoList", matchItemInfoList);
        return match;
    }

    public static void main(String[] args) {

        //{"sysVer":"android 4.2.2","sid":"800001","platform":"04","softVer":"1.8.2","cmd":"3300","machId":"5cee612f-d752-3aae-9120-823e749eb59b","height":"1280","width":"720","md5":"b32e02029bb56e72162afe0e5c815e16","func":"sports","msg":"{\"buyNumberArray\":[{\"pollId\":\"00\",\"buyNumber\":\"20140111003:01:0,1;20140111003:05:0,1;20140111005:01:0;20140111005:05:0;20140111004:01:3,1;20140111004:05:1,3,0|3*1,2*1\",\"playId\":\"10\",\"index\":0,\"item\":78,\"amount\":156}],\"userCode\":\"12512264\",\"playId\":\"10\",\"lotteryId\":\"200\",\"issueArray\":[{\"issue\":\"20140111\",\"multiple\":1}],\"winStop\":0,\"floorsAmount\":0,\"buyAmount\":156,\"stopAmount\":0,\"totalAmount\":156,\"buyType\":1,\"privacy\":0,\"commision\":0}"}

        Map<String, Object> matchMap = matchList("20140111003:01:0,1;20140111003:05:0,1;20140111005:01:0;20140111005:05:0;20140111004:01:3,1;20140111004:05:1,3,0|3*1,2*1");

        List<MatchItemInfo> matchItemInfoList = (List<MatchItemInfo>) matchMap.get("matchItemInfoList");
        for (MatchItemInfo matchItemInfo : matchItemInfoList) {
            if (CommonUtil.isNotEmpty(matchItemInfo)) {
                String issue = matchItemInfo.getMatchId().substring(0, 8);
                String sn = matchItemInfo.getMatchId().substring(8);

                String number = matchItemInfo.getValue();
                String[] numberArray = number.split(",");
                for (String str : numberArray) {
                    System.out.println(issue + sn + " : " + str);
                }
            }
        }

    }
}
