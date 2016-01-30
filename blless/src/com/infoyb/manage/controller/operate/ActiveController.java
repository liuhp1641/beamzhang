package com.cm.manage.controller.operate;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.controller.base.BaseController;
import com.cm.manage.model.operate.TaskExchange;
import com.cm.manage.model.operate.TaskExchangeDetail;
import com.cm.manage.service.operate.IActiveService;
import com.cm.manage.util.base.BusiException;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.base.Json;
import com.cm.manage.vo.operate.ExchangeVO;
/****
 * @describe 活动兑换
 * @author 金福
 *
 */
@Controller
@RequestMapping("/activeController")
public class ActiveController extends BaseController {
	@Autowired
	private IActiveService activeService;
	private static Logger logger = Logger.getLogger(ActiveController.class);
	@RequestMapping(params = "toactivelist")
	public String toActiveList(){
		return "/operate/activeList";
	}
	@RequestMapping(params = "toactiveitemlist")
	public String toActiveItemList(HttpServletRequest request,String taskExchangeId){
		request.setAttribute("taskExchangeId", taskExchangeId);
		return "/operate/activeItemList";
	}
	@RequestMapping(params = "toactiveitemlistdetail")
	public String toActiveItemListDetail(HttpServletRequest request,String taskExchangeId){
		request.setAttribute("taskExchangeId", taskExchangeId);
		return "/operate/activeItemListDetail";
	}
	@RequestMapping(params = "toaddactive")
	public String toAddActive(){
		return "/operate/addActive";
	}
	@RequestMapping(params = "toaddactiveitem")
	public String toAddActiveItem(HttpServletRequest request,String taskExchangeId){
		request.setAttribute("taskExchangeId", taskExchangeId);
		return "/operate/addActiveItem";
	}
	@RequestMapping(params = "toupdateactive")
	public String toUpdateActive(HttpServletRequest request,String taskExchangeId){
		TaskExchange taskExchange = activeService.findTaskExchangeById(taskExchangeId);
		if(taskExchange == null){
			request.setAttribute("exsitornot", "no");
		}
		request.setAttribute("taskExchange", taskExchange);
		return "/operate/updateActive";
	}
	@RequestMapping(params = "toupdateactiveitem")
	public String toUpdateActiveItem(HttpServletRequest request,String exchangeDetailId){
		TaskExchangeDetail detail = activeService.findTaskExchangeDetailById(exchangeDetailId);
		if(detail == null){
			request.setAttribute("exsitornot", "no");
		}
		request.setAttribute("exchangeDetail", detail);
		return "/operate/updateActiveItem";
	}
	@RequestMapping(params = "activelist")
	@ResponseBody
	public EasyuiDataGridJson activeList(EasyuiDataGrid dataGrid,ExchangeVO exchangeVO){
		return activeService.activeList(dataGrid, exchangeVO);
	}
	@RequestMapping(params = "activeitemlist")
	@ResponseBody
	public EasyuiDataGridJson activeItemList(EasyuiDataGrid dg,ExchangeVO exchangeVO){
		return activeService.activeItemList(dg,exchangeVO);
	}
	@RequestMapping(params = "activeDetail")
	public String getTaskExchangeDetail(HttpServletRequest request,String taskExchangeId){
		TaskExchange taskExchange = activeService.findTaskExchangeById(taskExchangeId);
		request.setAttribute("taskExchange", taskExchange);
		return "/operate/activeDetail";
		
	}
	/***
	 * @describe 添加活动
	 * @param request
	 * @param exchangeVO
	 * @return
	 */
	@RequestMapping(params = "addactive")
	@ResponseBody
	public Json addActive(HttpServletRequest request,ExchangeVO exchangeVO){
		Json j = new Json();
		j.setSuccess(true);
		try{
			activeService.saveTaskExchange(exchangeVO);
			String desc = "创建活动[" + exchangeVO.getExchangeName() + "]";
        	saveLog(request, "activeController.do?addactive", CommonConstants.LOG_FOR_ACTIVE, desc);
		}catch(Exception e){
			logger.error(e);
			j.setSuccess(false);
			j.setMsg("新建活动失败");
		}
		return j;
	}
	/***
	 * @describe 添加方案
	 * @param request
	 * @param exchangeVO
	 * @return
	 */
	@RequestMapping(params = "addactiveitem")
	@ResponseBody
	public Json addActiveItem(HttpServletRequest request,ExchangeVO exchangeVO){
		Json j = new Json();
		j.setSuccess(true);
		try{
			Map<String,String> logMap = activeService.saveTaskExchangeDetail(exchangeVO);
			String desc = "创建活动[" + logMap.get("activeName") + "]的方案[" + logMap.get("itemId") + "]";
        	saveLog(request, "activeController.do?addactiveitem", CommonConstants.LOG_FOR_ACTIVE, desc);
		}catch(Exception e){
			logger.error(e);
			j.setSuccess(false);
			j.setMsg("新建方案失败");
		}
		return j;
	}
	/***
	 * @describe 编辑活动
	 * @param request
	 * @param exchangeVO
	 * @return
	 */
	@RequestMapping(params = "editactive")
	@ResponseBody
	public Json EditActive(HttpServletRequest request,ExchangeVO exchangeVO){
		Json j = new Json();
		j.setSuccess(true);
		try{
			activeService.editTaskExchange(exchangeVO);
			String desc = "编辑活动[" + exchangeVO.getExchangeName() + "]";
        	saveLog(request, "activeController.do?editactive", CommonConstants.LOG_FOR_ACTIVE, desc);
		}catch(BusiException be){
			logger.error(be);
			j.setSuccess(false);
			j.setMsg(be.getMessage());
		}
		catch(Exception e){
			logger.error(e);
			j.setSuccess(false);
			j.setMsg("编辑活动失败");
		}
		return j;
	}
	/***
	 * @describe 编辑方案
	 * @param request
	 * @param exchangeVO
	 * @return
	 */
	@RequestMapping(params = "editactiveitem")
	@ResponseBody
	public Json EditActiveItem(HttpServletRequest request,ExchangeVO exchangeVO){
		Json j = new Json();
		j.setSuccess(true);
		try{
			Map<String,String> logMap = activeService.editTaskExchangeDetail(exchangeVO);
			String desc = "编辑活动[" + logMap.get("activeName") + "]的方案[" + logMap.get("itemId") + "]";
        	saveLog(request, "activeController.do?editactiveitem", CommonConstants.LOG_FOR_ACTIVE, desc);
		}catch(BusiException be){
			logger.error(be);
			j.setSuccess(false);
			j.setMsg(be.getMessage());
		}
		catch(Exception e){
			logger.error(e);
			j.setSuccess(false);
			j.setMsg("编辑方案失败");
		}
		return j;
	}
	
	/***
	 * @describe 删除活动
	 * @param request
	 * @param exchangeVO
	 * @return
	 */
	@RequestMapping(params = "deleteactive")
	@ResponseBody
	public Json deleteActive(HttpServletRequest request,String taskExchangeIdStr){
		Json j = new Json();
		j.setSuccess(true);
		try{
			Map<String,String> logMap = activeService.deleteTaskExchange(taskExchangeIdStr);
			String desc = "删除活动[" + logMap.get("activeName") + "]";
        	saveLog(request, "activeController.do?deleteactive", CommonConstants.LOG_FOR_ACTIVE, desc);
		}catch(BusiException be){
			logger.error(be);
			j.setSuccess(false);
			j.setMsg(be.getMessage());
		}
		catch(Exception e){
			logger.error(e);
			j.setSuccess(false);
			j.setMsg("删除活动失败");
		}
		return j;
	}
	/***
	 * @describe 删除方案
	 * @param request
	 * @param exchangeVO
	 * @return
	 */
	@RequestMapping(params = "deleteactiveitem")
	@ResponseBody
	public Json deleteActiveItem(HttpServletRequest request,String taskExchangeDetailIdStr){
		Json j = new Json();
		j.setSuccess(true);
		try{
			Map<String,String> logMap = activeService.deleteTaskExchangeDetail(taskExchangeDetailIdStr);
			String desc = "删除方案[" + logMap.get("activeName") + "]";
        	saveLog(request, "activeController.do?deleteactiveitem", CommonConstants.LOG_FOR_ACTIVE, desc);
		}catch(BusiException be){
			logger.error(be);
			j.setSuccess(false);
			j.setMsg(be.getMessage());
		}
		catch(Exception e){
			logger.error(e);
			j.setSuccess(false);
			j.setMsg("删除方案失败");
		}
		return j;
	}
	/***
	 * @describe 启动方案
	 * @param request
	 * @param exchangeVO
	 * @return
	 */
	@RequestMapping(params = "startactiveitem")
	@ResponseBody
	public Json startActiveItem(HttpServletRequest request,String taskExchangeDetailIdStr){
		Json j = new Json();
		j.setSuccess(true);
		try{
			Map<String,String> logMap = activeService.startActiveItem(taskExchangeDetailIdStr);
			String desc = "启动方案[" + logMap.get("activeName") + "]";
        	saveLog(request, "activeController.do?startactiveitem", CommonConstants.LOG_FOR_ACTIVE, desc);
		}catch(BusiException be){
			logger.error(be);
			j.setSuccess(false);
			j.setMsg(be.getMessage());
		}
		catch(Exception e){
			logger.error(e);
			j.setSuccess(false);
			j.setMsg("启动方案失败");
		}
		return j;
	}
	
	/***
	 * @describe 启动活动
	 * @param request
	 * @param exchangeVO
	 * @return
	 */
	@RequestMapping(params = "startactive")
	@ResponseBody
	public Json startActive(HttpServletRequest request,String taskExchangeIdStr){
		Json j = new Json();
		j.setSuccess(true);
		try{
			Map<String,String> logMap = activeService.startActive(taskExchangeIdStr);
			String desc = "启动活动[" + logMap.get("activeName") + "]";
        	saveLog(request, "activeController.do?startactive", CommonConstants.LOG_FOR_ACTIVE, desc);
		}catch(BusiException be){
			logger.error(be);
			j.setSuccess(false);
			j.setMsg(be.getMessage());
		}
		catch(Exception e){
			logger.error(e);
			j.setSuccess(false);
			j.setMsg("启动活动失败");
		}
		return j;
	}
	
	@RequestMapping(params = "stopactive")
	@ResponseBody
	public Json stopActive(HttpServletRequest request,String taskExchangeId,int status,String operate){
		Json j = new Json();
		j.setSuccess(true);
		try{
			Map<String,String> logDataMap = activeService.updateTaskExchangeStatus(taskExchangeId, status, operate);
			String desc = "活动[" + logDataMap.get("activeName") + "]的状态修改为暂停";
        	saveLog(request, "activeController.do?stopactive", CommonConstants.LOG_FOR_ACTIVE, desc);
		}catch(BusiException be){
			logger.error(be);
			j.setMsg(be.getMessage());
			j.setSuccess(false);
		}catch(Exception e){
			logger.error(e);
			j.setSuccess(false);
			j.setMsg("暂停活动失败");
		}
		return j;
	}
	@RequestMapping(params = "recoveractive")
	@ResponseBody
	public Json recoverActive(HttpServletRequest request,String taskExchangeId,int status,String operate){
		Json j = new Json();
		j.setSuccess(true);
		try{
			Map<String,String> logDataMap = activeService.updateTaskExchangeStatus(taskExchangeId, status, operate);
			String desc = "活动[" + logDataMap.get("activeName") + "]的状态恢复为已上线";
        	saveLog(request, "activeController.do?recoveractive", CommonConstants.LOG_FOR_ACTIVE, desc);
		}catch(BusiException be){
			logger.error(be);
			j.setMsg(be.getMessage());
			j.setSuccess(false);
		}catch(Exception e){
			logger.error(e);
			j.setSuccess(false);
			j.setMsg("恢复活动失败");
		}
		return j;
	}
	
	@RequestMapping(params = "endactive")
	@ResponseBody
	public Json endActive(HttpServletRequest request,String taskExchangeId,int status,String operate){
		Json j = new Json();
		j.setSuccess(true);
		try{
			Map<String,String> logDataMap = activeService.updateTaskExchangeStatus(taskExchangeId, status, operate);
			String desc = "活动[" + logDataMap.get("activeName") + "]的状态修改为已结束";
        	saveLog(request, "activeController.do?endactive", CommonConstants.LOG_FOR_ACTIVE, desc);
		}catch(BusiException be){
			logger.error(be);
			j.setMsg(be.getMessage());
			j.setSuccess(false);
		}catch(Exception e){
			logger.error(e);
			j.setSuccess(false);
			j.setMsg("结束活动失败");
		}
		return j;
	}
	@RequestMapping(params = "stopactiveitem")
	@ResponseBody
	public Json stopActiveItem(HttpServletRequest request,String taskExchangeDetailId,int status,String operate){
		Json j = new Json();
		j.setSuccess(true);
		try{
			Map<String,String> logDataMap = activeService.updateTaskExchangeDetailStatus(taskExchangeDetailId, status, operate);
			String desc = "方案[" + logDataMap.get("activeName") + "]的状态修改为暂停";
        	saveLog(request, "activeController.do?stopactiveitem", CommonConstants.LOG_FOR_ACTIVE, desc);
		}catch(BusiException be){
			logger.error(be);
			j.setMsg(be.getMessage());
			j.setSuccess(false);
		}catch(Exception e){
			logger.error(e);
			j.setSuccess(false);
			j.setMsg("暂停方案失败");
		}
		return j;
	}
	@RequestMapping(params = "recoveractiveitem")
	@ResponseBody
	public Json recoverActiveItem(HttpServletRequest request,String taskExchangeDetailId,int status,String operate){
		Json j = new Json();
		j.setSuccess(true);
		try{
			Map<String,String> logDataMap = activeService.updateTaskExchangeDetailStatus(taskExchangeDetailId, status, operate);
			String desc = "方案[" + logDataMap.get("activeName") + "]的状态恢复为已上线";
        	saveLog(request, "activeController.do?recoveractiveitem", CommonConstants.LOG_FOR_ACTIVE, desc);
		}catch(BusiException be){
			logger.error(be);
			j.setMsg(be.getMessage());
			j.setSuccess(false);
		}catch(Exception e){
			logger.error(e);
			j.setSuccess(false);
			j.setMsg("恢复方案失败");
		}
		return j;
	}
	
	@RequestMapping(params = "endactiveitem")
	@ResponseBody
	public Json endActiveItem(HttpServletRequest request,String taskExchangeDetailId,int status,String operate){
		Json j = new Json();
		j.setSuccess(true);
		try{
			Map<String,String> logDataMap = activeService.updateTaskExchangeDetailStatus(taskExchangeDetailId, status, operate);
			String desc = "方案[" + logDataMap.get("activeName") + "]的状态修改为已结束";
        	saveLog(request, "activeController.do?endactiveitem", CommonConstants.LOG_FOR_ACTIVE, desc);
		}catch(BusiException be){
			logger.error(be);
			j.setMsg(be.getMessage());
			j.setSuccess(false);
		}catch(Exception e){
			logger.error(e);
			j.setSuccess(false);
			j.setMsg("结束方案失败");
		}
		return j;
	}
}
