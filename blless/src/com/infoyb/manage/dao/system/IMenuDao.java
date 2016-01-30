package com.cm.manage.dao.system;

import java.util.List;

import com.cm.manage.vo.base.EasyuiTreeNode;
import com.cm.manage.vo.system.Menu;
import com.cm.manage.vo.system.User;

public interface IMenuDao{

	/**
	 * 获得菜单树
	 * 
	 * @param id
	 * @return
	 */
	public List<EasyuiTreeNode> tree(String id);

	public List<Menu> treegrid(String id);

	public Menu add(Menu menu);

	public void del(Menu menu);

	public Menu edit(Menu menu);
	/**
	 * 用户可访问的模块
	 * @param loginUser
	 * @param id
	 * @return
	 */
	public List<Menu> usermenu(User loginUser);
	
	/**
	 * 获取用户可访问树
	 * @param loginUser
	 * @return
	 */
	public List<EasyuiTreeNode> usertree(User loginUser,String id);

}
