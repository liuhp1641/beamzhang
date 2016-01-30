package com.cm.manage.util.base;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * 项目参数工具类
 * 
 * @author 
 * 
 */
public class ResourceUtil {

	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("config");

	/**
	 * 获得sessionInfo名字
	 * 
	 * @return
	 */
	public static final String getSessionInfoName() {
		return bundle.getString("sessionInfoName");
	}
	
	 public static String getPropertyValue(String propertyName){
		String value =  bundle.getString(propertyName);
		if(value == null){
			return "";
		}
		return value;
	}

    public static String getClassPath() {
        URL url = ResourceUtil.class.getClassLoader().getResource("");
        if (null == url) {
            return "";
        }
        return url.getPath();
    }
}
