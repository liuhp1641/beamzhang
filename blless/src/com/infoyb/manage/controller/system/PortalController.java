package com.cm.manage.controller.system;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.manage.controller.base.BaseController;
import com.cm.manage.service.system.IPortalService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.base.Json;
import com.cm.manage.vo.system.Portal;

/**
 * portal控制器
 * 
 * @author 
 * 
 */
@Controller
@RequestMapping("/portalController")
public class PortalController extends BaseController {

	private static final Logger logger = Logger.getLogger(PortalController.class);

	private IPortalService protalService;

	public IPortalService getProtalService() {
		return protalService;
	}

	@Autowired
	public void setProtalService(IPortalService protalService) {
		this.protalService = protalService;
	}

	@RequestMapping(params = "datagrid")
	@ResponseBody
	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Portal portal) {
		return protalService.datagrid(dg, portal);
	}

	@RequestMapping(params = "show")
	@ResponseBody
	public EasyuiDataGridJson show(EasyuiDataGrid dg, Portal portal) {
		return datagrid(dg, portal);
	}

	@RequestMapping(params = "portal")
	public String show() {
		return "/admin/portal";
	}

	@RequestMapping(params = "del")
	@ResponseBody
	public Json del(String ids) {
		Json j = new Json();
		protalService.del(ids);
		j.setSuccess(true);
		return j;
	}

	@RequestMapping(params = "edit")
	@ResponseBody
	public Json edit(Portal portal) {
		Json j = new Json();
		protalService.edit(portal);
		j.setMsg("编辑成功！");
		j.setSuccess(true);
		return j;
	}

	@RequestMapping(params = "add")
	@ResponseBody
	public Json add(Portal portal) {
		Json j = new Json();
		protalService.add(portal);
		j.setSuccess(true);
		return j;
	}

}
