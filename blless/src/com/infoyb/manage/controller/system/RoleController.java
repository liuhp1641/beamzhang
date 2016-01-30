package com.cm.manage.controller.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.controller.base.BaseController;
import com.cm.manage.service.system.IRoleService;
import com.cm.manage.vo.base.EasyuiTreeNode;
import com.cm.manage.vo.base.Json;
import com.cm.manage.vo.system.Role;


/**
 * 角色控制
 * 
 * @author 
 * 
 */
@Controller
@RequestMapping("/roleController")
public class RoleController extends BaseController {

	private static final Logger logger = Logger.getLogger(RoleController.class);

	private IRoleService roleService;

	public IRoleService getRoleService() {
		return roleService;
	}

	@Autowired
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	/**
	 * 跳转到角色管理页
	 * 
	 * @return
	 */
	@RequestMapping(params = "role")
	public String role() {
		return "/admin/role";
	}

	/**
	 * 角色
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "tree")
	@ResponseBody
	public List<EasyuiTreeNode> tree(String id) {
		return roleService.tree(id);
	}

	/**
	 * 角色treegrid
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "treegrid")
	@ResponseBody
	public List<Role> treegrid(String id) {
		return roleService.treegrid(id);
	}

	/**
	 * 添加角色
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(params = "add")
	@ResponseBody
	public Json add(HttpServletRequest request,Role role) {
		Json j = new Json();
		Role r = roleService.add(role);
		saveLog(request, "/roleController.do?add", CommonConstants.LOG_FOR_SECURITY, "添加角色："+role.getText());
		j.setSuccess(true);
		j.setMsg("添加成功!");
		return j;
	}

	/**
	 * 删除角色
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public Json del(HttpServletRequest request,Role role) {
		Json j = new Json();
		roleService.del(role);
		saveLog(request, "/roleController.do?del", CommonConstants.LOG_FOR_SECURITY, "删除角色："+role.getText());
		j.setSuccess(true);
		j.setMsg("删除成功!");
		return j;
	}

	/**
	 * 编辑角色
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping(params = "edit")
	@ResponseBody
	public Json edit(HttpServletRequest request,Role role) {
		Json j = new Json();
		Role r = roleService.edit(role);
		saveLog(request, "/roleController.do?edit", CommonConstants.LOG_FOR_SECURITY, "编辑角色："+role.getText());
		j.setSuccess(true);
		j.setMsg("编辑成功!");
		return j;
	}
	/**
	 * 菜单授权
	 * @param roleId
	 * @param menuId
	 * @return
	 */
	@RequestMapping(params="menuGrant")
	@ResponseBody
	public Json menuGrant(HttpServletRequest request,String roleStr,String roleMenus){
		Json j = new Json();
		JSONObject object = JSONObject.fromObject(roleStr);
		object.remove("parentId");
		object.remove("parentText");
		Role role = (Role) JSONObject.toBean(object, Role.class);
		Role r = roleService.menuGrant(role, roleMenus);
		saveLog(request, "/roleController.do?menuGrant", CommonConstants.LOG_FOR_SECURITY, "角色("+role.getText()+")菜单授权");
		j.setSuccess(true);
		j.setMsg("授权成功!");
		return j;
	}
	
	/**
	 * 资源授权
	 * @param request
	 * @param roleStr
	 * @param roleResource
	 * @return
	 */
	@RequestMapping(params="resourceGrant")
	@ResponseBody
	public Json resourceGrant(HttpServletRequest request,String roleStr,String roleResource){
		Json j = new Json();
		JSONObject object = JSONObject.fromObject(roleStr);
		object.remove("parentId");
		object.remove("parentText");
		Role role = (Role) JSONObject.toBean(object, Role.class);
		Role r = roleService.resourceGrant(role, roleResource);
		saveLog(request, "/roleController.do?resourceGrant", CommonConstants.LOG_FOR_SECURITY, "角色("+role.getText()+")资源授权");
		j.setSuccess(true);
		j.setMsg("授权成功!");
		return j;
	}

}
