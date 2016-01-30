package com.cm.manage.service.system;

import java.util.List;

import com.cm.manage.model.system.Syuser;
import com.cm.manage.service.base.IBaseService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.system.User;


/**
 * 用户Service
 * 
 * @author 
 * 
 */
public interface IUserService extends IBaseService {

	/**
	 * 用户注册
	 * 
	 * @param user
	 *            用户信息
	 * @return User
	 */
	public User reg(User user);

	/**
	 * 用户登录
	 * 
	 * @param user
	 *            用户信息
	 * @return User
	 */
	public User login(User user);

	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, User user);

	public List<User> combobox(String q);

	public User add(User user);

	public User edit(User user);

	public void del(String ids);

	public void editUsersRole(String userIds, String roleId);

	public User getUserInfo(User user);

	public User editUserInfo(User user);

	public List<Syuser> getUserList();

}
