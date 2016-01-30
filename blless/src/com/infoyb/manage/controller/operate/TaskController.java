package com.cm.manage.controller.operate;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.controller.base.BaseController;
import com.cm.manage.model.operate.TaskInfo;
import com.cm.manage.service.operate.ITaskService;
import com.cm.manage.util.base.BusiException;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.base.Json;
import com.cm.manage.vo.operate.TaskVO;
@Controller
@RequestMapping("/taskController")
public class TaskController extends BaseController{
	private static Logger logger = Logger.getLogger(TaskController.class);
	 @Autowired
	 private ITaskService taskService;
	@RequestMapping(params = "tasklist")
	public String taskList(){
		return "/operate/tasklist";
	}
	@RequestMapping(params = "getlistdata")
	@ResponseBody
	public EasyuiDataGridJson showTaskList(EasyuiDataGrid dataGrid,TaskVO taskVO){
		return taskService.queryTaskList(dataGrid, taskVO);
	}
	@RequestMapping(params = "addtask")
	public String addList(){
		return "/operate/addtask";
	}
	@RequestMapping(params = "taskdetail")
	public String taskdetail(HttpServletRequest request,String taskId){
		Map<String,Object> resMap = taskService.getTaskDetail(taskId);
		request.setAttribute("taskMap", resMap);
		return "/operate/taskdetail";
	}
	/***
	 * @describe 查询总账户---任务奖励来源
	 * @return
	 */
	@RequestMapping(params = "queryaccount")
	@ResponseBody
	public Json queryInternalAccount(){
		Json j = new Json();
		j.setSuccess(true);
		try{
			List<Map> resList = taskService.queryInternalAcount();
			j.setObj(resList);
		}catch(Exception e){
			logger.error(e);
			j.setSuccess(false);
		}
		return j;
	}
	@RequestMapping(params = "confirmaddtask")
	@ResponseBody
	public Json confirmAddTask(HttpServletRequest request,TaskVO taskVO){
		Json j = new Json();
		j.setSuccess(true);
		try{
			taskService.saveTaskInfo(taskVO);
			String desc = "创建任务[" + taskVO.getName() + "]";
        	saveLog(request, "taskController.do?confirmaddtask", CommonConstants.LOG_FOR_TASK, desc);
		}catch(BusiException be){
			logger.error(be);
			j.setMsg(be.getMessage());
			j.setSuccess(false);
		}catch(Exception e){
			logger.error(e);
			j.setSuccess(false);
			j.setMsg("新建任务失败");
		}
		return j;
	}
	@RequestMapping(params = "stoptask")
	@ResponseBody
	public Json stopTask(HttpServletRequest request,String taskId,int status,String operate){
		Json j = new Json();
		j.setSuccess(true);
		try{
			Map<String,String> logDataMap = taskService.updateTaskStatus(taskId, status, operate);
			String desc = "任务[" + logDataMap.get("taskName") + "]的状态修改为暂停";
        	saveLog(request, "taskController.do?stoptask", CommonConstants.LOG_FOR_TASK, desc);
		}catch(BusiException be){
			logger.error(be);
			j.setMsg(be.getMessage());
			j.setSuccess(false);
		}catch(Exception e){
			logger.error(e);
			j.setSuccess(false);
			j.setMsg("暂停任务失败");
		}
		return j;
	}
	@RequestMapping(params = "recovertask")
	@ResponseBody
	public Json recoverTask(HttpServletRequest request,String taskId,int status,String operate){
		Json j = new Json();
		j.setSuccess(true);
		try{
			Map<String,String> logDataMap = taskService.updateTaskStatus(taskId, status, operate);
			String desc = "任务[" + logDataMap.get("taskName") + "]的状态恢复为已上线";
        	saveLog(request, "taskController.do?recovertask", CommonConstants.LOG_FOR_TASK, desc);
		}catch(BusiException be){
			logger.error(be);
			j.setMsg(be.getMessage());
			j.setSuccess(false);
		}catch(Exception e){
			logger.error(e);
			j.setSuccess(false);
			j.setMsg("恢复任务失败");
		}
		return j;
	}
	
	@RequestMapping(params = "endtask")
	@ResponseBody
	public Json endTask(HttpServletRequest request,String taskId,int status,String operate){
		Json j = new Json();
		j.setSuccess(true);
		try{
			Map<String,String> logDataMap = taskService.updateTaskStatus(taskId, status, operate);
			String desc = "任务[" + logDataMap.get("taskName") + "]的状态修改为已结束";
        	saveLog(request, "taskController.do?endtask", CommonConstants.LOG_FOR_TASK, desc);
		}catch(BusiException be){
			logger.error(be);
			j.setMsg(be.getMessage());
			j.setSuccess(false);
		}catch(Exception e){
			logger.error(e);
			j.setSuccess(false);
			j.setMsg("结束任务失败");
		}
		return j;
	}
	
	@RequestMapping(params = "deletetask")
	@ResponseBody
	public Json deleteTask(HttpServletRequest request,String taskIdStr){
		Json j = new Json();
		j.setSuccess(true);
		try{
			Map<String,String> logDataMap = taskService.deleteTask(taskIdStr);
			String desc = "删除任务[" + logDataMap.get("taskName") + "]";
        	saveLog(request, "taskController.do?deletetask", CommonConstants.LOG_FOR_TASK, desc);
		}catch(BusiException be){
			logger.error(be);
			j.setMsg(be.getMessage());
			j.setSuccess(false);
		}catch(Exception e){
			logger.error(e);
			j.setSuccess(false);
			j.setMsg("删除任务失败");
		}
		return j;
	}
	
	@RequestMapping(params = "starttask")
	@ResponseBody
	public Json startTask(HttpServletRequest request,String taskIdStr){
		Json j = new Json();
		j.setSuccess(true);
		try{
			Map<String,String> logDataMap = taskService.startTask(taskIdStr);
			String desc = "运行任务[" + logDataMap.get("taskName") + "]";
        	saveLog(request, "taskController.do?starttask", CommonConstants.LOG_FOR_TASK, desc);
		}catch(BusiException be){
			logger.error(be);
			j.setMsg(be.getMessage());
			j.setSuccess(false);
		}catch(Exception e){
			logger.error(e);
			j.setSuccess(false);
			j.setMsg("运行任务失败");
		}
		return j;
	}
	@RequestMapping(params = "lotteryList")
	@ResponseBody
	public Json getLotteryList(){
		Json j = new Json();
		j.setSuccess(true);
		try{
			j.setObj(taskService.getLotteryList());
		}catch(Exception e){
			logger.error(e);
			j.setSuccess(false);
			j.setMsg("获取彩种失败");
		}
		return j;
	}
	@RequestMapping(params = "getTask")
	@ResponseBody
	public Json getTask(String taskId){
		Json j = new Json();
		j.setSuccess(false);
		try{
			TaskInfo task = taskService.getTask(taskId);
			if(task != null){
				j.setSuccess(true);
				j.setObj(task);
			}else{
				j.setMsg("任务不存在");
			}
		}catch(Exception e){
			logger.error(e);
			j.setMsg("查询任务失败");
		}
		return j;
	}
	@RequestMapping(params = "cooperationList")
	@ResponseBody
	public Json getCooperationList(){
		Json j = new Json();
		j.setSuccess(true);
		try{
			j.setObj(taskService.getOperationList());
		}catch(Exception e){
			logger.error(e);
			j.setSuccess(false);
			j.setMsg("获取渠道信息失败");
		}
		return j;
	}	
}
