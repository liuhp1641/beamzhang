package com.cm.manage.util.bonusClass;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.cm.manage.util.ConfigUtils;
import com.cm.manage.util.base.ResourceUtil;

/**
 * UserBean: lfx
 * Date: 12-3-8 下午4:31
 */
public class BonusClassUtils {

    public static Map<String, String> awardMapList = new HashMap<String, String>();

    static {
        try {
            SAXReader xmlReader = new SAXReader();
            Document document = xmlReader.read(ResourceUtil.getClassPath() + ConfigUtils.getValue("LOTTERY_BONUS_CLASS_LIST_PATH"));
            List<Element> list = document.selectNodes("/lotterys/lottery");
            for (Element lottery : list) {
                String lotteryCode = lottery.attributeValue("code");
                BonusClassManager bonusClassManager = new BonusClassManager();
                for (Element play : (List<Element>) lottery.selectNodes("award")) {
                    Iterator iterator = play.attributeIterator();
                    while (iterator.hasNext()) {
                        Attribute lv = (Attribute) iterator.next();
                        Level level = new Level();
                        level.setClasses(Integer.valueOf(lv.getName().substring(2)));
                        level.setAmount(lv.getValue());
                        bonusClassManager.addLevel(level);
                    }
                }
                for (Element play : (List<Element>) lottery.selectNodes("title")) {
                    Iterator iterator = play.attributeIterator();
                    while (iterator.hasNext()) {
                        Attribute lv = (Attribute) iterator.next();
                        Level level = bonusClassManager.getLevelOfNum(Integer.valueOf(lv.getName().substring(2)));
                        level.setTitle(lv.getValue());
                    }
                }
                awardMapList.put(lotteryCode, bonusClassManager.toString());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public static String getDefaultBonusClassByLotteryCode(String lotteryCode) {
        return awardMapList.get(lotteryCode);
    }

    public static BonusClassManager getDefaultBonusClassManagerByLotteryCode(String lotteryCode) {
        BonusClassManager bonusClassManager = new BonusClassManager(awardMapList.get(lotteryCode));
        return bonusClassManager;
    }

    public static String levelTitle(String lotteryCode, Integer index) {
        try {
            BonusClassManager bonusClassManager = getDefaultBonusClassManagerByLotteryCode(lotteryCode);
            Level level = bonusClassManager.getLevelOfNum(index);
            return level.getTitle();
        } catch (Exception e) {
            return "";
        }

    }

    public static void main(String[] args) {
        System.out.println();
    }
}
