package com.cm.manage.util.base;

import javax.servlet.http.HttpServletRequest;

/**
 * request工具
 * 
 * @author 
 * 
 */
public class RequestUtil {

	/**
	 * 获得请求路径
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestPath(HttpServletRequest request) {
		String requestPath = request.getRequestURI() + "?" + request.getQueryString();
		//处理支付宝同步通知的url，支付宝不允许带参数?的url
		if(requestPath.indexOf("handleAlipayController") > -1){
			requestPath = requestPath.substring(0, requestPath.lastIndexOf("?"));
		}
		if (requestPath.indexOf("&") > -1) {// 去掉其他参数
			requestPath = requestPath.substring(0, requestPath.indexOf("&"));
		}
		requestPath = requestPath.substring(request.getContextPath().length());// 去掉项目路径
		return requestPath;
	}

}
