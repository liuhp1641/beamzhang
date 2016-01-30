package com.cm.manage.controller.operate;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.manage.controller.base.BaseController;
import com.cm.manage.service.operate.IUserTaskService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.base.Json;
import com.cm.manage.vo.operate.UserTaskVO;
@Controller
@RequestMapping("/userTaskController")
public class UserTaskController extends BaseController {
	private static Logger logger = Logger.getLogger(UserTaskController.class);
	@Autowired
	private IUserTaskService userTaskService;

	/***
	 * @describe 用户任务日志列表页面
	 * @return
	 */
	@RequestMapping(params = "userTaskList")
	public String userTaskList(){
		return "/operate/userTaskList";
	}
	/***
	 * @describe 获取任务日志列表数据
	 * @param dataGrid
	 * @param taskVO
	 * @return
	 */
	@RequestMapping(params = "getUserTaskListData")
	@ResponseBody
	public EasyuiDataGridJson getUserTaskListData(EasyuiDataGrid dg,UserTaskVO userTaskVO){
		return userTaskService.queryTaskList(dg, userTaskVO);
	}
	@RequestMapping(params = "countByFunddingType")
	@ResponseBody
	public Json countByFunddingType(UserTaskVO userTaskVO){
		Json j = new Json();
		j.setSuccess(true);
		try{
			j.setObj(userTaskService.countByFunddingType(userTaskVO));
		}catch(Exception e){
			j.setSuccess(false);
			logger.error(e);
		}
		return j;
	}
}
