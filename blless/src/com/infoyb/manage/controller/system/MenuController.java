package com.cm.manage.controller.system;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.manage.constant.CommonConstants;
import com.cm.manage.controller.base.BaseController;
import com.cm.manage.service.system.IMenuService;
import com.cm.manage.util.base.ResourceUtil;
import com.cm.manage.vo.base.EasyuiTreeNode;
import com.cm.manage.vo.base.Json;
import com.cm.manage.vo.system.Menu;
import com.cm.manage.vo.system.SessionInfo;
import com.cm.manage.vo.system.User;


/**
 * 菜单控制
 * 
 * @author 
 * 
 */
@Controller
@RequestMapping("/menuController")
public class MenuController extends BaseController {

	private static final Logger logger = Logger.getLogger(MenuController.class);

	private IMenuService menuService;

	public IMenuService getMenuService() {
		return menuService;
	}

	@Autowired
	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}

	/**
	 * 跳转到菜单管理页
	 * 
	 * @return
	 */
	@RequestMapping(params = "menu")
	public String menu() {
		return "/admin/menu";
	}

	/**
	 * 获取菜单
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "tree")
	@ResponseBody
	public List<EasyuiTreeNode> tree(String id) {
		return menuService.tree(id);
	}
	
	@RequestMapping(params="model")
	public String model(HttpSession session,HttpServletRequest request, HttpServletResponse response){
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		if (sessionInfo != null && sessionInfo.getUser() != null) {
			User user = sessionInfo.getUser();
			List<Menu> model=menuService.usermenu(user);
			request.setAttribute("model", model);
		} else {
			try {
			    request.setAttribute("msg", "您没有登录或登录超时，请重新登录！");
				request.getRequestDispatcher("jsp/error/authMsg.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "/layout/west";
	}
	@RequestMapping(params="usertree")
	@ResponseBody
	public List<EasyuiTreeNode> usertree(HttpSession session,String id){
		if(id.split(",").length>1){
			id=id.split(",")[1];
		}
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ResourceUtil.getSessionInfoName());
		if (sessionInfo != null && sessionInfo.getUser() != null) {
			User user = sessionInfo.getUser();
			return menuService.usertree(user,id);
		} 
		return null;
	}

	/**
	 * 获取菜单treegrid
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(params = "treegrid")
	@ResponseBody
	public List<Menu> treegrid(String id) {
		return menuService.treegrid(id);
	}

	/**
	 * 添加菜单
	 * 
	 * @param menu
	 * @return
	 */
	@RequestMapping(params = "add")
	@ResponseBody
	public Json add(HttpServletRequest request,Menu menu) {
		Json j = new Json();
		Menu m = menuService.add(menu);
		saveLog(request, "/menuController.do?add", CommonConstants.LOG_FOR_SECURITY, "创建菜单："+menu.getText());
		j.setSuccess(true);
		j.setMsg("添加成功!");
		return j;
	}

	/**
	 * 删除菜单
	 * 
	 * @param menu
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public Json del(HttpServletRequest request,Menu menu) {
		Json j = new Json();
		menuService.del(menu);
		saveLog(request, "/menuController.do?del", CommonConstants.LOG_FOR_SECURITY, "删除菜单："+menu.getText());
		j.setSuccess(true);
		j.setMsg("删除成功!");
		return j;
	}

	/**
	 * 编辑菜单
	 * 
	 * @param menu
	 * @return
	 */
	@RequestMapping(params = "edit")
	@ResponseBody
	public Json edit(HttpServletRequest request,Menu menu) {
		Json j = new Json();
		Menu m = menuService.edit(menu);
		saveLog(request, "/menuController.do?edit", CommonConstants.LOG_FOR_SECURITY, "编辑菜单："+menu.getText());
		j.setSuccess(true);
		j.setMsg("编辑成功!");
		return j;
	}

}
