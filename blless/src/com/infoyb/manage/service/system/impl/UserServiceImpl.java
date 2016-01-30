package com.cm.manage.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cm.manage.dao.system.IUserDao;
import com.cm.manage.model.system.Syuser;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.system.IUserService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.system.User;


/**
 * 用户Service
 * 
 * @author 
 * 
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements IUserService {

	@Autowired
	private IUserDao userDao;


	public User reg(User user) {
		
		return userDao.reg(user);
	}

	@Transactional(readOnly = true)
	public User login(User user) {
		
		return userDao.login(user);
	}

	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, User user) {
	
		return userDao.datagrid(dg, user);
	}

	public List<User> combobox(String q) {
		
		return userDao.combobox(q);
	}

	public User add(User user) {
		return userDao.add(user);
	}

	public void editUsersRole(String userIds, String roleId) {
	
		userDao.editUsersRole(userIds, roleId);
	}

	public User edit(User user) {
		
		return userDao.edit(user);
	}

	public void del(String ids) {
		userDao.del(ids);
	}

	public User getUserInfo(User user) {
		
		return userDao.getUserInfo(user);
	}

	public User editUserInfo(User user) {
		
		return userDao.editUserInfo(user);
	}

	@Override
	public List<Syuser> getUserList() {
		return userDao.getUserList();
	}
}
