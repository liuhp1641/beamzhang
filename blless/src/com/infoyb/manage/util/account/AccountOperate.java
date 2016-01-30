package com.cm.manage.util.account;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.cm.manage.util.ConfigUtils;
import com.cm.manage.util.base.ResourceUtil;
import com.cm.manage.vo.base.EasyuiTreeNode;

public class AccountOperate {
	private static Map<String,String> firstMap=new HashMap<String, String>();
	private static Map<String,String> secondMap=new HashMap<String, String>();
	private static Map<String,String> thirdMap=new HashMap<String, String>();
	private static Map<String,String> fouthMap=new HashMap<String, String>();
    private static Map<String, List<EasyuiTreeNode>> secondChildMap = new HashMap<String, List<EasyuiTreeNode>>();
    static {
        forInstance();
    }

    public static void forInstance() {
        try {
            SAXReader xmlReader = new SAXReader();
            Document document = xmlReader.read(ResourceUtil.getClassPath() + ConfigUtils.getValue("ACCOUNT_OPERATOR_PATH"));
            List list = document.selectNodes("/account/first");
            Iterator iterator = list.iterator();
            List<EasyuiTreeNode> firstNodeList=new ArrayList<EasyuiTreeNode>();
            while (iterator.hasNext()) {
                Element element = (Element) iterator.next();
                EasyuiTreeNode firstNode=new EasyuiTreeNode();
                String fistId=element.attributeValue("type");
                String firstName=element.attributeValue("name");
                firstNode.setId(fistId);
                firstNode.setText(firstName);
                firstMap.put(fistId, firstName);
                
                List<Element> secondList = element.elements("second");
                List<EasyuiTreeNode> secondNodeList=new ArrayList<EasyuiTreeNode>();
                for (Element second : secondList) {
            	    String secondId=second.attributeValue("type");
            	    String secondName=second.attributeValue("name");
            	    secondMap.put(fistId.concat(secondId), secondName);
            	    
            	    EasyuiTreeNode secondNode=new EasyuiTreeNode();
            	    secondNode.setId(fistId.concat(secondId));
            	    secondNode.setText(second.attributeValue("name"));
            	    secondNodeList.add(secondNode);
            	    
                    List<Element> thirdList = second.elements("third");
                    List<EasyuiTreeNode> thirdNodeList=new ArrayList<EasyuiTreeNode>();
                    for (Element third : thirdList) {
                    	String thirdId=third.attributeValue("type");
                    	String thirdName=third.attributeValue("name");
                    	thirdMap.put(fistId.concat(secondId).concat(thirdId), thirdName);
                    	
                    	EasyuiTreeNode thirdNode=new EasyuiTreeNode();
                    	thirdNode.setId(fistId.concat(secondId).concat(thirdId));
                    	thirdNode.setText(thirdName);
                    	
                    	List<Element> fouthList = third.elements("fouth");
                    	List<EasyuiTreeNode> fouthNodeList=new ArrayList<EasyuiTreeNode>();
                        for (Element fouth : fouthList) {
                        	String fouthId=fouth.attributeValue("type");
                        	String fouthName=fouth.attributeValue("nickName");
                        	fouthMap.put(fistId.concat(secondId).concat(thirdId).concat(fouthId), fouthName);
                        	
                        	EasyuiTreeNode fouthNode=new EasyuiTreeNode();
                        	fouthNode.setId(fistId.concat(secondId).concat(thirdId).concat(fouthId));
                        	fouthNode.setText(fouthName);
                        	fouthNodeList.add(fouthNode);
                        	
                        }
                        thirdNode.setChildren(fouthNodeList);
                        thirdNodeList.add(thirdNode);
                    }
                    secondChildMap.put(fistId.concat(secondId), thirdNodeList);
                }
                firstNode.setChildren(secondNodeList);
                firstNodeList.add(firstNode);
            }
          
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<EasyuiTreeNode> secondChildNode(String node) {
        if (secondChildMap.containsKey(node)) {
            return secondChildMap.get(node);
        }
        return null;
    }
    
    public static String getSecondName(String secondId){
    	return secondMap.get(secondId);
    }
    public static String getThirdName(String thirdId){
    	return thirdMap.get(thirdId);
    }
    public static String getFouthName(String fouthId){
    	return fouthMap.get(fouthId);
    }
    
    
    public static Map<String, String> getFirstMap() {
		return firstMap;
	}

	public static Map<String, String> getSecondMap() {
		return secondMap;
	}

	
	public static Map<String, String> getThirdMap() {
		return thirdMap;
	}

	public static Map<String, String> getFouthMap() {
		return fouthMap;
	}

	
	public static Map<String, List<EasyuiTreeNode>> getSecondChildMap() {
		return secondChildMap;
	}

	public static void main(String[] args) {
    	AccountOperate operate=new AccountOperate();
    	 List<EasyuiTreeNode> secondChilds= operate.secondChildNode("000");
		 JSONArray array = JSONArray.fromObject(secondChilds);
		 String pushJson=array.toString();
		 System.out.println(pushJson);
    	
	}

}
