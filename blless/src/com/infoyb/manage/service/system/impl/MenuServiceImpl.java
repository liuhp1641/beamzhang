package com.cm.manage.service.system.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cm.manage.dao.system.IMenuDao;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.system.IMenuService;
import com.cm.manage.vo.base.EasyuiTreeNode;
import com.cm.manage.vo.system.Menu;
import com.cm.manage.vo.system.User;

/**
 * 菜单Service实现类
 * 
 * @author 
 * 
 */
@Service("menuService")
public class MenuServiceImpl extends BaseServiceImpl implements IMenuService {

	private static final Logger logger = Logger.getLogger(MenuServiceImpl.class);

	@Autowired
	private IMenuDao menuDao;
	
	@Transactional(readOnly = true)
	public List<EasyuiTreeNode> tree(String id) {
	
		return menuDao.tree(id);
	}
	@Transactional(readOnly = true)
	public List<Menu> treegrid(String id) {
	
		return menuDao.treegrid(id);
	}

	public Menu add(Menu menu) {
		
		return menuDao.add(menu);
	}

	public void del(Menu menu) {
		menuDao.del(menu);
	}

	public Menu edit(Menu menu) {
		return menuDao.edit(menu);
	}
	
	@Transactional(readOnly = true)
	public List<Menu> usermenu(User loginUser) {
		return menuDao.usermenu(loginUser);
	}

	@Transactional(readOnly = true)
	public List<EasyuiTreeNode> usertree(User loginUser,String id) {
	
		return menuDao.usertree(loginUser, id);
	}
	
	

}
