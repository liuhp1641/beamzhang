package com.cm.manage.controller.operate;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.manage.controller.base.BaseController;
import com.cm.manage.service.operate.IUserActiveService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.base.Json;
import com.cm.manage.vo.operate.UserTaskExchangeVO;
@Controller
@RequestMapping("/userActiveController")
public class UserActiveController extends BaseController{
	private static Logger logger = Logger.getLogger(UserActiveController.class);
	@Autowired
	private IUserActiveService userActiveService;
	@RequestMapping(params = "toUserActiveList")
	public String toUserActiveList(){
		return "/operate/userActiveList";
	}
	@RequestMapping(params = "toUserActiveDetailList")
	public String toUserActiveDetailList(HttpServletRequest request,String userExchangeId){
		request.setAttribute("userExchangeId", userExchangeId);
		return "/operate/userActiveDetailList";
	}
	@RequestMapping(params = "userActiveList")
	@ResponseBody
	public EasyuiDataGridJson userActiveList(EasyuiDataGrid dg,UserTaskExchangeVO exchangeVO){
		return userActiveService.userActiveList(dg, exchangeVO);
		
	}
	@RequestMapping(params = "userActiveDetailList")
	@ResponseBody
	public EasyuiDataGridJson userActiveDetailList(EasyuiDataGrid dg,String userExchangeId){
		return userActiveService.userActiveDetailList(dg, userExchangeId);
	}
	/****
	 * @describe 统计活动返赠明细数据
	 * @param userExchangeId
	 * @return
	 */
	@RequestMapping(params = "countActiveDetail")
	@ResponseBody
	public Json countDetail(String userExchangeId){
		Json j = new Json();
		Map<String,String> resMap = userActiveService.count(userExchangeId);
		j.setObj(resMap);
		return j;
	}
	/****
	 * @describe 活动日志页面统计数据
	 * @param exchangeVO
	 * @return
	 */
	@RequestMapping(params = "countActive")
	@ResponseBody
	public Json count(UserTaskExchangeVO exchangeVO){
		Json j = new Json();
		Map<String,String> resMap = userActiveService.count(exchangeVO);
		j.setObj(resMap);
		return j;
	}
}
