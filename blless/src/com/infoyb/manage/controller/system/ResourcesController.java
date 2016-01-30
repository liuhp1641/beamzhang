package com.cm.manage.controller.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.controller.base.BaseController;
import com.cm.manage.service.system.IResourcesService;
import com.cm.manage.vo.base.EasyuiTreeNode;
import com.cm.manage.vo.base.Json;
import com.cm.manage.vo.system.Resources;


/**
 * 资源控制
 * 
 * @author 
 * 
 */
@Controller
@RequestMapping("/resourcesController")
public class ResourcesController extends BaseController {

	private static final Logger logger = Logger.getLogger(ResourcesController.class);

	private IResourcesService resourcesService;

	public IResourcesService getResourcesService() {
		return resourcesService;
	}

	@Autowired
	public void setResourcesService(IResourcesService resourcesService) {
		this.resourcesService = resourcesService;
	}

	/**
	 * 跳转到资源管理页
	 * 
	 * @return
	 */
	@RequestMapping(params = "resources")
	public String resources() {
		return "/admin/resources";
	}

	/**
	 * 获取资源管理
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "tree")
	@ResponseBody
	public List<EasyuiTreeNode> tree(String id) {
		return resourcesService.tree(id);
	}

	/**
	 * 资源管理treegrid
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "treegrid")
	@ResponseBody
	public List<Resources> treegrid(String id) {
		return resourcesService.treegrid(id);
	}

	/**
	 * 添加资源
	 * 
	 * @param resources
	 * @return
	 */
	@RequestMapping(params = "add")
	@ResponseBody
	public Json add(HttpServletRequest request,Resources resources) {
		Json j = new Json();
		Resources r = resourcesService.add(resources);
		saveLog(request, "/resourcesController.do?add", CommonConstants.LOG_FOR_SECURITY, "添加资源："+resources.getText());
		j.setSuccess(true);
		j.setMsg("添加成功!");
		return j;
	}

	/**
	 * 删除资源
	 * 
	 * @param resources
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public Json del(HttpServletRequest request,Resources resources) {
		Json j = new Json();
		resourcesService.del(resources);
		saveLog(request, "/resourcesController.do?del", CommonConstants.LOG_FOR_SECURITY, "删除资源："+resources.getText());
		j.setSuccess(true);
		j.setMsg("删除成功!");
		return j;
	}

	/**
	 * 编辑资源
	 * 
	 * @param resources
	 * @return
	 */
	@RequestMapping(params = "edit")
	@ResponseBody
	public Json edit(HttpServletRequest request,Resources resources) {
		Json j = new Json();
		Resources r = resourcesService.edit(resources);
		saveLog(request, "/resourcesController.do?edit", CommonConstants.LOG_FOR_SECURITY, "编辑资源："+resources.getText());
		j.setSuccess(true);
		j.setMsg("编辑成功!");
		return j;
	}

}
