package com.cm.manage.dao.system;

import java.util.List;

import com.cm.manage.model.system.Syresources;
import com.cm.manage.vo.base.EasyuiTreeNode;
import com.cm.manage.vo.system.Role;

public interface IRoleDao  {

	public List<EasyuiTreeNode> tree(String id);

	public List<Role> treegrid(String id);

	public Role add(Role role);

	public void del(Role role);

	public Role edit(Role role);
	/**
	 * 角色菜单授权
	 * @param role
	 * @param menuId
	 * @return
	 */
	public Role menuGrant(Role role,String roleMenus);
	
	/**
	 * 角色资源授权
	 * @param role
	 * @param roleResource
	 * @return
	 */
	
	public Role resourceGrant(Role role,String roleResource);

}
