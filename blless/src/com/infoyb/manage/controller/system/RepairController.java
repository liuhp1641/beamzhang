package com.cm.manage.controller.system;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.manage.controller.base.BaseController;
import com.cm.manage.service.system.IRepairService;
import com.cm.manage.vo.base.Json;

/**
 * 修复数据控制器
 * 
 * @author 
 * 
 */
@Controller
@RequestMapping("/repairController")
public class RepairController extends BaseController {

	private static final Logger logger = Logger.getLogger(RepairController.class);

	private IRepairService repairService;

	public IRepairService getRepairService() {
		return repairService;
	}

	@Autowired
	public void setRepairService(IRepairService repairService) {
		this.repairService = repairService;
	}

	/**
	 * 修复数据
	 * 
	 * @return json
	 */
	@RequestMapping(params = "repair")
	@ResponseBody
	synchronized public Json repair() {
		Json j = new Json();
		repairService.repair();
		j.setSuccess(true);
		return j;
	}

}
