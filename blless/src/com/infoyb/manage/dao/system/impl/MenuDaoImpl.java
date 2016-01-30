package com.cm.manage.dao.system.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.system.IMenuDao;
import com.cm.manage.model.system.Symenu;
import com.cm.manage.model.system.Syrole;
import com.cm.manage.model.system.SyroleSymenu;
import com.cm.manage.model.system.SyroleSyresources;
import com.cm.manage.model.system.Syuser;
import com.cm.manage.model.system.SyuserSyrole;
import com.cm.manage.util.system.MenuComparator;
import com.cm.manage.vo.base.EasyuiTreeNode;
import com.cm.manage.vo.system.Menu;
import com.cm.manage.vo.system.User;


@Repository("menuDao")
public class MenuDaoImpl  implements IMenuDao {

	@Autowired
	private IBaseDao<Symenu> menuDao;

	@Autowired
	private IBaseDao<Syuser> userDao;
	
	@Autowired
	private IBaseDao<SyroleSyresources> syrole;
	
	public List<EasyuiTreeNode> tree(String id) {
		String hql = "from Symenu t where t.symenu is null order by t.seq";
		if (id != null && !id.trim().equals("")) {
			hql = "from Symenu t where t.symenu.id ='" + id + "' order by t.seq";
		}
		List<Symenu> symenus = menuDao.find(hql);
		List<EasyuiTreeNode> tree = new ArrayList<EasyuiTreeNode>();
		for (Symenu symenu : symenus) {
			tree.add(tree(symenu, false));
		}
		return tree;
	}

	private EasyuiTreeNode tree(Symenu symenu, boolean recursive) {
		EasyuiTreeNode node = new EasyuiTreeNode();
		node.setId(symenu.getId());
		node.setText(symenu.getText());
		node.setIconCls(symenu.getIconcls());
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("src", symenu.getSrc());
		node.setAttributes(attributes);
		if (symenu.getSymenus() != null && symenu.getSymenus().size() > 0) {
			node.setState("closed");
			if (recursive) {// 递归查询子节点
				List<Symenu> symenuList = new ArrayList<Symenu>(symenu.getSymenus());
				Collections.sort(symenuList, new MenuComparator());// 排序
				List<EasyuiTreeNode> children = new ArrayList<EasyuiTreeNode>();
				for (Symenu m : symenuList) {
					EasyuiTreeNode t = tree(m, true);
					children.add(t);
				}
				node.setChildren(children);
			}
		}
		return node;
	}

	public List<Menu> treegrid(String id) {
		List<Menu> treegrid = new ArrayList<Menu>();
		String hql = "from Symenu t where t.symenu is null order by t.seq";
		if (id != null && !id.trim().equals("")) {
			hql = "from Symenu t where t.symenu.id = '" + id + "' order by t.seq";
		}
		List<Symenu> symenus = menuDao.find(hql);
		for (Symenu symenu : symenus) {
			Menu m = new Menu();
			BeanUtils.copyProperties(symenu, m);
			if (symenu.getSymenu() != null) {
				m.setParentId(symenu.getSymenu().getId());
				m.setParentText(symenu.getSymenu().getText());
			}
			m.setIconCls(symenu.getIconcls());
			if (symenu.getSymenus() != null && symenu.getSymenus().size() > 0) {
				m.setState("closed");
			}
			treegrid.add(m);
		}
		return treegrid;
	}

	public Menu add(Menu menu) {
		Symenu symenu = new Symenu();
		BeanUtils.copyProperties(menu, symenu);
		symenu.setIconcls(menu.getIconCls());
		if (menu.getParentId() != null && !menu.getParentId().toString().trim().equals("")) {
			symenu.setSymenu(menuDao.load(Symenu.class, menu.getParentId()));
		}
		menuDao.save(symenu);
		return menu;
	}

	public void del(Menu menu) {
		Symenu symenu = menuDao.get(Symenu.class, menu.getId());
		if (symenu != null) {
			recursiveDelete(symenu);
		}
	}

	private void recursiveDelete(Symenu symenu) {
		if (symenu.getSymenus() != null && symenu.getSymenus().size() > 0) {
			Set<Symenu> symenus = symenu.getSymenus();
			for (Symenu t : symenus) {
				recursiveDelete(t);
			}
		}
		menuDao.delete(symenu);
	}

	public Menu edit(Menu menu) {
		Symenu symenu = menuDao.get(Symenu.class, menu.getId());
		if (symenu != null) {
			BeanUtils.copyProperties(menu, symenu);
			symenu.setIconcls(menu.getIconCls());
			if (!symenu.getId().equals("0")) {// 根节点不可以修改上级节点
				symenu.setSymenu(menuDao.get(Symenu.class, menu.getParentId()));
			}
		}
		return menu;
	}
	
	
	@Override
	public List<Menu> usermenu(User loginUser) {
		Map<String,Object> map=new HashMap<String, Object>();
		String userId=loginUser.getId();
		Syuser syuser = userDao.get(Syuser.class, userId);
		Set<SyuserSyrole> syuserSyroles = syuser.getSyuserSyroles();// 用户和角色关系
		for (SyuserSyrole syuserSyrole : syuserSyroles) {
			Syrole syrole = syuserSyrole.getSyrole();
			Set<SyroleSymenu> syroleSymenus = syrole.getSyroleSymenu();// 角色和资源关系
			for (SyroleSymenu syroleSymenu : syroleSymenus) {
				Symenu symenu = syroleSymenu.getSymenu();
				map.put(symenu.getId(), symenu);
			}
		}
		List<Menu> treegrid = new ArrayList<Menu>();
		String hql = "from Symenu t where t.symenu is null order by t.seq";
		List<Symenu> symenus = menuDao.find(hql);
		for (Symenu symenu : symenus) {
			if(loginUser.getId().equals("0")){
				Menu m = new Menu();
				BeanUtils.copyProperties(symenu, m);
				m.setIconCls(symenu.getIconcls());
				treegrid.add(m);
			}else{
			if(map.get(symenu.getId())!=null){
			Menu m = new Menu();
			BeanUtils.copyProperties(symenu, m);
			m.setIconCls(symenu.getIconcls());
			treegrid.add(m);
			}
			}
		}
		return treegrid;
	}

	public List<EasyuiTreeNode> usertree(User loginUser,String id) {
		Map<String,Object> map=new HashMap<String, Object>();
		String userId=loginUser.getId();
		Syuser syuser = userDao.get(Syuser.class, userId);
		Set<SyuserSyrole> syuserSyroles = syuser.getSyuserSyroles();// 用户和角色关系
		for (SyuserSyrole syuserSyrole : syuserSyroles) {
			Syrole syrole = syuserSyrole.getSyrole();
			Set<SyroleSymenu> syroleSymenus = syrole.getSyroleSymenu();// 角色和资源关系
			for (SyroleSymenu syroleSymenu : syroleSymenus) {
				Symenu symenu = syroleSymenu.getSymenu();
				map.put(symenu.getId(), symenu);
			}
		}
		String hql = "from Symenu t where t.symenu is null order by t.seq";
		if (id != null && !id.trim().equals("")) {
			hql = "from Symenu t where t.symenu.id ='" + id + "' order by t.seq";
		}
		List<Symenu> symenus = menuDao.find(hql);
		
		List<EasyuiTreeNode> tree = new ArrayList<EasyuiTreeNode>();
	
		for (Symenu symenu : symenus) {
			if(loginUser.getId().equals("0")){
				tree.add(tree(symenu, false));
			}else{
			if(map.get(symenu.getId())!=null){
				tree.add(tree(symenu, false));
			}
			}
		}
		return tree;
	}

}
