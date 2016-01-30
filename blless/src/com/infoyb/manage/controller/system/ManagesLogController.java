package com.cm.manage.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.manage.controller.base.BaseController;
import com.cm.manage.service.base.IManageLogService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.system.ManagesLogVO;
@Controller
@RequestMapping("/managesLogController")
public class ManagesLogController extends BaseController {

	@Autowired
	private IManageLogService  manageLogService;
	
	@RequestMapping(params="toManageslog")
	private String toManageslog(){
		return "/admin/manageLog";
	}
	/**
	 * 
	 * 操作日志列表
	 * @param dg
	 * @param log
	 * @return
	 */
	@RequestMapping(params="manageLogList")
	@ResponseBody
	public EasyuiDataGridJson manageLogList(EasyuiDataGrid dg, ManagesLogVO log){
		return manageLogService.manageLogList(dg, log);
	}
	
}
