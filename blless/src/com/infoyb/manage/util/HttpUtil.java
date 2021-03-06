package com.cm.manage.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class HttpUtil{

	/**
	 * 获取httprequest对象
	 * @return
	 */
	public static HttpServletRequest getRequest(){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request;
	}
	
	public static Object getAttribute(String key) {
		Object obj = getRequest().getSession().getAttribute(key);
		return obj;
	}
	
	public static Map<String,String> getParams(HttpServletRequest request){
		//获取支付宝GET过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
	//		valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		return params;
	}
	public static void main(String[] args){
		Double d = 0.1;
		System.out.println(Double.valueOf(d));
		if(Double.valueOf(d) == 0.0){
			System.out.println("OK");
		}
	}
}
