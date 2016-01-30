package com.cm.manage.service.system.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cm.manage.dao.system.IRoleDao;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.system.IRoleService;
import com.cm.manage.vo.base.EasyuiTreeNode;
import com.cm.manage.vo.system.Role;

/**
 * 角色Service实现类
 * 
 * @author 
 * 
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl implements IRoleService {

	private static final Logger logger = Logger.getLogger(RoleServiceImpl.class);

	@Autowired
	private IRoleDao roleDao;
	

	
	@Transactional(readOnly = true)
	public List<EasyuiTreeNode> tree(String id) {
		
		return roleDao.tree(id);
	}

	@Transactional(readOnly = true)
	public List<Role> treegrid(String id) {
		
		return roleDao.treegrid(id);
	}

	public Role add(Role role) {
		return roleDao.add(role);
	}

	public void del(Role role) {
		roleDao.del(role);
	}

	public Role edit(Role role) {
		
		return roleDao.edit(role);
	}
	
	/**
	 * 角色菜单授权
	 * @param role
	 * @param menuId
	 * @return
	 */
	public Role menuGrant(Role role,String roleMenus){
		return roleDao.menuGrant(role, roleMenus);
	}
	
	/**
	 * 角色资源授权
	 * @param role
	 * @param roleResource
	 * @return
	 */
	
	public Role resourceGrant(Role role,String roleResource){
		return roleDao.resourceGrant(role, roleResource);
	}


}
