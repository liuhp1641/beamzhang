package com.cm.manage.service.system;

import java.util.List;

import com.cm.manage.service.base.IBaseService;
import com.cm.manage.vo.base.EasyuiTreeNode;
import com.cm.manage.vo.system.Role;


/**
 * 角色Service
 * 
 * @author 
 * 
 */
public interface IRoleService extends IBaseService {

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
