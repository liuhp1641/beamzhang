package com.cm.manage.util.bonusClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserBean: lfx
 * Date: 12-2-20 下午1:56
 */
public class BonusClassManager {

    private List<Level> levelList = new ArrayList<Level>();

    public BonusClassManager() {

    }

    public BonusClassManager(String bonusClassStr) {
        for (String strOne : bonusClassStr.replace("[{", "").replace("}]", "").split("\\},\\{")) {
            levelList.add(new Level(strOne));
        }
    }

    public List<Level> getLevelList() {
        return levelList;
    }

    public void setLevelList(List<Level> levelList) {
        this.levelList = levelList;
    }

    public void addLevel(Level level) {
        this.levelList.add(level);
    }

    /**
     * 获取等级为levelNum的信息，例：一等奖levleNum=1
     *
     * @param levelNum
     * @return
     */
    public Level getLevelOfNum(int levelNum) {
        for (Level level : levelList) {
            if (level.getClasses() == levelNum) {
                return level;
            }
        }
        //不存在levelNum等级的话新建一个
        Level levelNew = new Level();
        levelNew.setClasses(levelNum);
        this.levelList.add(levelNew);
        return levelNew;
    }

    /**
     * 还原成bonusClass串
     *
     * @return
     */
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        for (Level level : levelList) {
            stringBuffer.append(level.toString() + ",");
        }
        return stringBuffer.substring(0, stringBuffer.length() - 1) + "]";
    }

    public Map<String, Map<String, String>> toMap() {
        Map<String, Map<String, String>> map = new HashMap<String, Map<String, String>>();
        for (Level level : levelList) {
            map.put("lv" + (level.getClasses() < 10 ? "0" + level.getClasses() : level.getClasses()), level.toMap());
        }
        return map;
    }

    public static String filterLvMoneyToString(String bonusClass) {
        return filterLvMoneyToString(bonusClass, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8}, new String[]{"一等奖", "二等奖", "三等奖", "四等奖", "五等奖", "六等奖", "七等奖", "八等奖"});
    }

    public static String filterLvMoneyToString(String bonusClass, Integer[] levelArr, String[] levelTitleArr) {
        StringBuffer stringBuffer = new StringBuffer();
        BonusClassManager bcm = null;
        try {
            bcm = new BonusClassManager(bonusClass);
        } catch (Exception e) {
            return "";
        }
        for (int i = 0; i < levelArr.length; i++) {
            Level level = bcm.getLevelOfNum(levelArr[i]);
            try {
                if (Double.valueOf(level.getAmount()) > 0) {
                    stringBuffer.append(levelTitleArr[i] + ":" + level.getAmount() + "元,");
                }
            } catch (Exception e) {
            }
        }
        try {
            return stringBuffer.substring(0, stringBuffer.length() - 1);
        } catch (Exception e) {
            return "";
        }
    }

    public static void main(String[] args) {
        String test = "[{'c1':500000,'m1':20111027047,'a1':0,'n1':1,'t1':'一等奖'},{'c1':300000,'m1':0,'a1':0,'n1':2,'t1':'二等奖'},{'c1':3000,'m1':0,'a1':0,'n1':3,'t1':'三等奖'},{'c1':200,'m1':0,'a1':0,'n1':4,'t1':'四等奖'},{'c1':10,'m1':0,'a1':0,'n1':5,'t1':'五等奖'},{'c1':5,'m1':0,'a1':0,'n1':6,'t1':'六等奖'}]";
//        BonusClassManager bcm = new BonusClassManager(test);
//        Level level = bcm.getLevelOfNum(2);
//        level.setAmount("12345");
//        System.out.println(bcm.toString());

        System.out.println(BonusClassManager.filterLvMoneyToString(test));
    }
}
