package com.cm.manage.dao.system.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.system.IRoleDao;
import com.cm.manage.model.system.Symenu;
import com.cm.manage.model.system.Syresources;
import com.cm.manage.model.system.Syrole;
import com.cm.manage.model.system.SyroleSymenu;
import com.cm.manage.model.system.SyroleSyresources;
import com.cm.manage.model.system.SyuserSyrole;
import com.cm.manage.service.system.impl.RoleServiceImpl;
import com.cm.manage.util.base.CommonUtil;
import com.cm.manage.util.system.RoleComparator;
import com.cm.manage.vo.base.EasyuiTreeNode;
import com.cm.manage.vo.system.Role;


@Repository("roleDao")
public class RoleDaoImpl  implements IRoleDao {

	private static final Logger logger = Logger.getLogger(RoleServiceImpl.class);

	@Autowired
	private IBaseDao<Syrole> roleDao;
	@Autowired
	private IBaseDao<Symenu> menuDao;
	@Autowired
	private IBaseDao<SyroleSyresources> syroleSyresourcesDao;
	@Autowired
	private IBaseDao<SyroleSymenu> syroleSymenuDao;
	@Autowired
	private IBaseDao<SyuserSyrole> syuserSyroleDao;
	@Autowired
	private IBaseDao<Syresources> resourcesDao;

	public List<EasyuiTreeNode> tree(String id) {
		String hql = "from Syrole t where t.syrole is null order by t.seq";
		if (id != null && !id.trim().equals("")) {
			hql = "from Syrole t where t.syrole.id ='" + id + "' order by t.seq";
		}
		List<Syrole> syroleList = roleDao.find(hql);
		List<EasyuiTreeNode> tree = new ArrayList<EasyuiTreeNode>();
		for (Syrole syrole : syroleList) {
			tree.add(tree(syrole, false));
		}
		return tree;
	}

	private EasyuiTreeNode tree(Syrole syrole, boolean recursive) {
		EasyuiTreeNode node = new EasyuiTreeNode();
		node.setId(syrole.getId());
		node.setText(syrole.getText());
		Map<String, Object> attributes = new HashMap<String, Object>();
		node.setAttributes(attributes);
		if (syrole.getSyroles() != null && syrole.getSyroles().size() > 0) {
			node.setState("closed");
			if (recursive) {// 递归查询子节点
				List<Syrole> syroleList = new ArrayList<Syrole>(syrole.getSyroles());
				Collections.sort(syroleList, new RoleComparator());// 排序
				List<EasyuiTreeNode> children = new ArrayList<EasyuiTreeNode>();
				for (Syrole r : syroleList) {
					EasyuiTreeNode t = tree(r, true);
					children.add(t);
				}
				node.setChildren(children);
			}
		}
		return node;
	}

	public List<Role> treegrid(String id) {
		List<Role> treegrid = new ArrayList<Role>();
		String hql = "from Syrole t where t.syrole is null order by t.seq";
		if (id != null && !id.trim().equals("")) {
			hql = "from Syrole t where t.syrole.id = '" + id + "' order by t.seq";
		}
		List<Syrole> syroleList = roleDao.find(hql);
		for (Syrole syrole : syroleList) {
			Role r = new Role();
			BeanUtils.copyProperties(syrole, r);
			if (syrole.getSyrole() != null) {
				r.setParentId(syrole.getSyrole().getId());
				r.setParentText(syrole.getSyrole().getText());
			}
			if (syrole.getSyroles() != null && syrole.getSyroles().size() > 0) {
				r.setState("closed");
			}
			if (syrole.getSyroleSymenu() != null && syrole.getSyroleSymenu().size() > 0) {
				String menuTextList = "";
				String menuIdList = "";
				boolean b = false;
				Set<SyroleSymenu> syroleSymenuSet = syrole.getSyroleSymenu();
				for (SyroleSymenu syroleSymenu : syroleSymenuSet) {
					Symenu symenu = syroleSymenu.getSymenu();
					if (!b) {
						b = true;
					} else {
						menuTextList += ",";
						menuIdList += ",";
					}
					menuTextList += symenu.getText();
					menuIdList += symenu.getId();
				}
				r.setMenuId(menuIdList);
				r.setMenuText(menuTextList);
			}
			if (syrole.getSyroleSyresourceses() != null && syrole.getSyroleSyresourceses().size() > 0) {
				String resourcesTextList = "";
				String resourcesIdList = "";
				boolean b = false;
				Set<SyroleSyresources> syroleSyresourcesSet = syrole.getSyroleSyresourceses();
				for (SyroleSyresources syroleSyresources : syroleSyresourcesSet) {
					Syresources syresources = syroleSyresources.getSyresources();
					if (!b) {
						b = true;
					} else {
						resourcesTextList += ",";
						resourcesIdList += ",";
					}
					resourcesTextList += syresources.getText();
					resourcesIdList += syresources.getId();
				}
				r.setResourcesId(resourcesIdList);
				r.setResourcesText(resourcesTextList);
			}
			treegrid.add(r);
		}
		return treegrid;
	}

	public Role add(Role role) {
		Syrole syrole = new Syrole();
		BeanUtils.copyProperties(role, syrole);
		if (role.getParentId() != null && !role.getParentId().toString().trim().equals("")) {
			syrole.setSyrole(roleDao.load(Syrole.class, role.getParentId()));
		}
		roleDao.saveOrUpdate(syrole);
		return role;
	}

	public void del(Role role) {
		Syrole syrole = roleDao.get(Syrole.class, role.getId());
		if (role != null) {
			recursiveDelete(syrole);
		}
	}

	private void recursiveDelete(Syrole syrole) {
		if (syrole.getSyroles() != null && syrole.getSyroles().size() > 0) {
			Set<Syrole> syroleSet = syrole.getSyroles();
			for (Syrole t : syroleSet) {
				recursiveDelete(t);
			}
		}
		   //删除该角色下的菜单节点信息 
		List<SyroleSymenu> syroleSymenusList = syroleSymenuDao.find("from SyroleSymenu t where t.syrole=?", syrole);
		if (syroleSymenusList != null && syroleSymenusList.size() > 0) {
			for (SyroleSymenu syroleSymenu : syroleSymenusList) {
				syroleSymenuDao.delete(syroleSymenu);
			}
		}
		List<SyroleSyresources> syroleSyresourcesList = syroleSyresourcesDao.find("from SyroleSyresources t where t.syrole=?", syrole);
		if (syroleSyresourcesList != null && syroleSyresourcesList.size() > 0) {
			for (SyroleSyresources syroleSyresources : syroleSyresourcesList) {
				syroleSyresourcesDao.delete(syroleSyresources);
			}
		}

		List<SyuserSyrole> syuserSyroleList = syuserSyroleDao.find("from SyuserSyrole t where t.syrole=?", syrole);
		if (syuserSyroleList != null && syuserSyroleList.size() > 0) {
			for (SyuserSyrole syuserSyrole : syuserSyroleList) {
				syuserSyroleDao.delete(syuserSyrole);
			}
		}

		roleDao.delete(syrole);
	}

	public Role edit(Role role) {
		Syrole syrole = roleDao.get(Syrole.class, role.getId());
		if (syrole != null) {
			BeanUtils.copyProperties(role, syrole);
			if (!syrole.getId().equals("0")) {// 跟节点不可以修改上级节点
				syrole.setSyrole(roleDao.get(Syrole.class, role.getParentId()));
			}
			   //删除该角色下的菜单节点信息 
			List<SyroleSymenu> syroleSymenusList = syroleSymenuDao.find("from SyroleSymenu t where t.syrole=?", syrole);
			if (syroleSymenusList != null && syroleSymenusList.size() > 0) {
				for (SyroleSymenu syroleSymenu : syroleSymenusList) {
					syroleSymenuDao.delete(syroleSymenu);
				}
			}
			List<SyroleSyresources> syroleSyresourcesList = syroleSyresourcesDao.find("from SyroleSyresources t where t.syrole=?", syrole);
			for (SyroleSyresources syroleSyresources : syroleSyresourcesList) {
				syroleSyresourcesDao.delete(syroleSyresources);
			}

			if (role.getMenuId() != null && !role.getMenuId().equals("")) {// 保存角色和资源的关系
				String[] menuIds = role.getMenuId().split(",");
				for (String menuId : menuIds) {
					SyroleSymenu syroleSymenus = new SyroleSymenu();// 关系
					Symenu symenu = menuDao.get(Symenu.class, menuId);
					syroleSymenus.setId(UUID.randomUUID().toString());
					syroleSymenus.setSyrole(syrole);
					syroleSymenus.setSymenu(symenu);
					syroleSymenuDao.save(syroleSymenus);
					Symenu parentMenu=getParentMenu(symenu);
					if(parentMenu!=null){
						syroleSymenus = new SyroleSymenu();// 关系
						syroleSymenus.setId(UUID.randomUUID().toString());
						syroleSymenus.setSyrole(syrole);
						syroleSymenus.setSymenu(parentMenu);
						syroleSymenuDao.save(syroleSymenus);
						
					}
				}
			}
			
			Map<String ,Object> resourcesMap=new HashMap<String, Object>();
			if (role.getResourcesId() != null && !role.getResourcesId().equals("")) {// 保存角色和资源的关系
				String[] resourceIds = role.getResourcesId().split(",");
				for (String resourceId : resourceIds) {
					SyroleSyresources syroleSyresources = new SyroleSyresources();// 关系
					Syresources syresources = resourcesDao.get(Syresources.class, resourceId);// 资源
					syroleSyresources.setId(UUID.randomUUID().toString());
					syroleSyresources.setSyrole(syrole);
					syroleSyresources.setSyresources(syresources);
					syroleSyresourcesDao.save(syroleSyresources);
					resourcesMap.put(resourceId, syresources);
				}
				Map<String ,Object> parentMap=new HashMap<String, Object>();
				for (String key : resourcesMap.keySet()) {
					Syresources syresources = (Syresources) resourcesMap.get(key);
					Syresources parentResource=getParentResource(syresources);
					if(parentResource!=null){
						String parentId=parentResource.getId();
						syresources = (Syresources) resourcesMap.get(parentId);
						if(CommonUtil.isNotEmpty(syresources)){
							continue ;
						}else{
							Syresources parent = resourcesDao.get(Syresources.class, parentId);// 资源
							String src=parent.getSrc();
							if(CommonUtil.isNotEmpty(src)){
								
								SyroleSyresources syroleSyresources = new SyroleSyresources();
								syroleSyresources.setId(UUID.randomUUID().toString());
								syroleSyresources.setSyrole(syrole);
								syroleSyresources.setSyresources(parent);
								syroleSyresourcesDao.save(syroleSyresources);
								parentMap.put(parentId, parent);
							}else{
								
								continue;
							}
							
						}
						
					}
				}
				if(parentMap!=null&&parentMap.size()>0){
					
					for (String key : parentMap.keySet()) {
						Syresources syresources = (Syresources) parentMap.get(key);
						Syresources parentResource=getParentResource(syresources);
						if(parentResource!=null){
							String parentId=parentResource.getId();
							syresources = (Syresources) resourcesMap.get(parentId);
							if(CommonUtil.isNotEmpty(syresources)){
								continue ;
							}else{
								Syresources parent = resourcesDao.get(Syresources.class, parentId);// 资源
								String src=parent.getSrc();
								if(CommonUtil.isNotEmpty(src)){
									SyroleSyresources syroleSyresources = new SyroleSyresources();
									syroleSyresources.setId(UUID.randomUUID().toString());
									syroleSyresources.setSyrole(syrole);
									syroleSyresources.setSyresources(parent);
									syroleSyresourcesDao.save(syroleSyresources);
								}else{
									
									continue;
								}
								
							}
							
						}
					}
				}
				
			}
		}
		return role;
	}
	
	/**
	 * 角色菜单授权
	 * @param roleId
	 * @param menuId
	 * @return
	 */
	public Role menuGrant(Role role,String roleMenus){
		Syrole syrole = roleDao.get(Syrole.class, role.getId());
		if (syrole != null) {
			BeanUtils.copyProperties(role, syrole);
			if (!syrole.getId().equals("0")) {// 跟节点不可以修改上级节点
				if(role.getParentId()!=null){
					syrole.setSyrole(roleDao.get(Syrole.class, role.getParentId()));
				}
			}
			   //删除该角色下的菜单节点信息 
			List<SyroleSymenu> syroleSymenusList = syroleSymenuDao.find("from SyroleSymenu t where t.syrole=?", syrole);
			if (syroleSymenusList != null && syroleSymenusList.size() > 0) {
				for (SyroleSymenu syroleSymenu : syroleSymenusList) {
					syroleSymenuDao.delete(syroleSymenu);
				}
			}
			

			if (roleMenus != null && !roleMenus.equals("")) {// 保存角色和资源的关系
				String[] menuIds = roleMenus.split(",");
				for (String menuId : menuIds) {
					SyroleSymenu syroleSymenus = new SyroleSymenu();// 关系
					Symenu symenu = menuDao.get(Symenu.class, menuId);
					syroleSymenus.setId(UUID.randomUUID().toString());
					syroleSymenus.setSyrole(syrole);
					syroleSymenus.setSymenu(symenu);
					syroleSymenuDao.save(syroleSymenus);
					
				}
			}
		}
		return role;
	}
	
	/**
	 * 角色资源授权
	 * @param role
	 * @param roleResource
	 * @return
	 */
	
	public Role resourceGrant(Role role,String roleResource){

		Syrole syrole = roleDao.get(Syrole.class, role.getId());
		if (syrole != null) {
			BeanUtils.copyProperties(role, syrole);
			if (!syrole.getId().equals("0")) {// 跟节点不可以修改上级节点
				if(role.getParentId()!=null){
					syrole.setSyrole(roleDao.get(Syrole.class, role.getParentId()));
				}
			}
			   //删除该角色下的资源信息 
			List<SyroleSyresources> syroleSyresourcesList = syroleSyresourcesDao.find("from SyroleSyresources t where t.syrole=?", syrole);
			for (SyroleSyresources syroleSyresources : syroleSyresourcesList) {
				syroleSyresourcesDao.delete(syroleSyresources);
			}

			if (roleResource != null && !roleResource.equals("")) {// 保存角色和资源的关系
				String[] resourceIds = roleResource.split(",");
				for (String resourceId : resourceIds) {
					SyroleSyresources syroleSyresources = new SyroleSyresources();// 关系
					Syresources syresources = resourcesDao.get(Syresources.class, resourceId);// 资源
					if(syresources.getSrc()!=null&&!syresources.getSrc().equals("")){
						
						syroleSyresources.setId(UUID.randomUUID().toString());
						syroleSyresources.setSyrole(syrole);
						syroleSyresources.setSyresources(syresources);
						syroleSyresourcesDao.save(syroleSyresources);
					}else{
						continue;
					}
					
				}
			}
		}
		return role;
	
	}
	public Symenu getParentMenu(Symenu symenu){
		Symenu parentMenu=symenu.getSymenu();
		if(parentMenu!=null&&parentMenu.getSymenu()!=null){
			getParentMenu(parentMenu);
		}else{
			return parentMenu;
		}
		return null;
	}
	
	public Syresources getParentResource(Syresources syresources){

		Syresources parentResource=syresources.getSyresources();
		if(parentResource!=null&&parentResource.getSyresources()!=null){
			return parentResource;
		}
		return null;
	
	}

}
