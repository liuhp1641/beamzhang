package com.cm.manage.dao.system.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.system.IUserDao;
import com.cm.manage.model.system.Syresources;
import com.cm.manage.model.system.Syrole;
import com.cm.manage.model.system.SyroleSyresources;
import com.cm.manage.model.system.Syuser;
import com.cm.manage.model.system.SyuserSyrole;
import com.cm.manage.util.base.Encrypt;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.system.User;


@Repository("userDao")
public class UserDaoImpl  implements IUserDao {

	@Autowired
	private IBaseDao<Syuser> userDao;
	@Autowired
	private IBaseDao<SyuserSyrole> syuserSyroleDao;
	@Autowired
	private IBaseDao<Syrole> roleDao;

	public User reg(User user) {
		user.setId(UUID.randomUUID().toString());
		user.setPassword(Encrypt.e(user.getPassword()));
		user.setCreatedatetime(new Date());
		user.setModifydatetime(new Date());
		Syuser u = new Syuser();
		BeanUtils.copyProperties(user, u);
		userDao.save(u);
		return user;
	}

	public User login(User user) {
		Syuser u = userDao.get("from Syuser u where u.name=? and u.password=?", user.getName(), Encrypt.e(user.getPassword()));
		if (u != null) {
			BeanUtils.copyProperties(u, user);
			return user;
		}
		return null;
	}

	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, User user) {
		EasyuiDataGridJson j = new EasyuiDataGridJson();
		String hql = " from Syuser t where 1=1 ";
		List<Object> values = new ArrayList<Object>();
		if (user != null) {// 添加查询条件
			if (user.getName() != null && !user.getName().trim().equals("")) {
				hql += " and t.name like '%%" + user.getName().trim() + "%%' ";
			}
			if (user.getCreatedatetimeStart() != null) {
				hql += " and t.createdatetime>=? ";
				values.add(user.getCreatedatetimeStart());
			}
			if (user.getCreatedatetimeEnd() != null) {
				hql += " and t.createdatetime<=? ";
				values.add(user.getCreatedatetimeEnd());
			}
			if (user.getModifydatetimeStart() != null) {
				hql += " and t.modifydatetime>=? ";
				values.add(user.getModifydatetimeStart());
			}
			if (user.getModifydatetimeEnd() != null) {
				hql += " and t.modifydatetime<=? ";
				values.add(user.getModifydatetimeEnd());
			}
		}
		String totalHql = " select count(*) " + hql;
		j.setTotal(userDao.count(totalHql, values));// 设置总记录数
		if (dg.getSort() != null) {// 设置排序
			hql += " order by " + dg.getSort() + " " + dg.getOrder();
		}
		List<Syuser> syusers = userDao.find(hql, dg.getPage(), dg.getRows(), values);// 查询分页

		List<User> users = new ArrayList<User>();
		if (syusers != null && syusers.size() > 0) {// 转换模型
			for (Syuser syuser : syusers) {
				User u = new User();
				BeanUtils.copyProperties(syuser, u);

				Set<SyuserSyrole> syuserSyroleSet = syuser.getSyuserSyroles();
				if (syuserSyroleSet != null && syuserSyroleSet.size() > 0) {
					boolean b = false;
					String roleId = "";
					String roleText = "";
					for (SyuserSyrole syuserSyrole : syuserSyroleSet) {
						if (!b) {
							b = true;
						} else {
							roleId += ",";
							roleText += ",";
						}
						roleId += syuserSyrole.getSyrole().getId();
						roleText += syuserSyrole.getSyrole().getText();
					}
					u.setRoleId(roleId);
					u.setRoleText(roleText);
				}

				users.add(u);
			}
		}
		j.setRows(users);// 设置返回的行
		return j;
	}

	public List<User> combobox(String q) {
		if (q == null) {
			q = "";
		}
		String hql = " from Syuser t where name like '%%" + q.trim() + "%%'";
		List<Syuser> l = userDao.find(hql, 1, 10);
		List<User> ul = new ArrayList<User>();
		if (l != null && l.size() > 0) {
			for (Syuser syuser : l) {
				User u = new User();
				BeanUtils.copyProperties(syuser, u);
				ul.add(u);
			}
		}
		return ul;
	}

	public User add(User user) {
		user.setId(UUID.randomUUID().toString());
		user.setPassword(Encrypt.e(user.getPassword()));
		if (user.getCreatedatetime() == null) {
			user.setCreatedatetime(new Date());
		}
		if (user.getModifydatetime() == null) {
			user.setModifydatetime(new Date());
		}
		Syuser syuser = new Syuser();
		BeanUtils.copyProperties(user, syuser);
		userDao.save(syuser);

		if (user.getRoleId() != null && !user.getRoleId().equals("")) {
			for (String id : user.getRoleId().split(",")) {
				SyuserSyrole syuserSyrole = new SyuserSyrole();
				syuserSyrole.setId(UUID.randomUUID().toString());
				syuserSyrole.setSyrole(roleDao.get(Syrole.class, id));
				syuserSyrole.setSyuser(syuser);
				syuserSyroleDao.save(syuserSyrole);
			}
		}

		return user;
	}

	public void editUsersRole(String userIds, String roleId) {
		for (String userId : userIds.split(",")) {
			Syuser syuser = userDao.get(Syuser.class, userId);
			if (syuser != null) {
				List<SyuserSyrole> syuserSyroleList = syuserSyroleDao.find("from SyuserSyrole t where t.syuser=?", syuser);
				if (syuserSyroleList != null && syuserSyroleList.size() > 0) {
					for (SyuserSyrole syuserSyrole : syuserSyroleList) {
						syuserSyroleDao.delete(syuserSyrole);
					}
				}
				if (roleId != null && !roleId.equals("")) {
					for (String id : roleId.split(",")) {
						Syrole syrole = roleDao.get(Syrole.class, id);
						if (syrole != null) {
							SyuserSyrole syuserSyrole = new SyuserSyrole();
							syuserSyrole.setId(UUID.randomUUID().toString());
							syuserSyrole.setSyrole(syrole);
							syuserSyrole.setSyuser(syuser);
							syuserSyroleDao.save(syuserSyrole);
						}
					}
				}

			}
		}
	}

	public User edit(User user) {
		Syuser syuser = userDao.get(Syuser.class, user.getId());
		if (syuser != null) {
			if (user.getPassword() != null && !user.getPassword().trim().equals("")) {
				syuser.setPassword(Encrypt.e(user.getPassword()));
			}
			if (user.getCreatedatetime() == null) {
				syuser.setCreatedatetime(new Date());
			}
			if (user.getModifydatetime() == null) {
				syuser.setModifydatetime(new Date());
			}
			BeanUtils.copyProperties(syuser, user);

			List<SyuserSyrole> syuserSyroleList = syuserSyroleDao.find("from SyuserSyrole t where t.syuser=?", syuser);
			if (syuserSyroleList != null && syuserSyroleList.size() > 0) {
				for (SyuserSyrole syuserSyrole : syuserSyroleList) {
					syuserSyroleDao.delete(syuserSyrole);
				}
			}
			if (user.getRoleId() != null && !user.getRoleId().equals("")) {
				for (String id : user.getRoleId().split(",")) {
					Syrole syrole = roleDao.get(Syrole.class, id);
					if (syrole != null) {
						SyuserSyrole syuserSyrole = new SyuserSyrole();
						syuserSyrole.setId(UUID.randomUUID().toString());
						syuserSyrole.setSyrole(syrole);
						syuserSyrole.setSyuser(syuser);
						syuserSyroleDao.save(syuserSyrole);
					}
				}
			}
		}
		return user;
	}

	public void del(String ids) {
		for (String id : ids.split(",")) {
			Syuser syuser = userDao.get(Syuser.class, id);
			if (syuser != null) {
				Set<SyuserSyrole> syuserSyroleSet = syuser.getSyuserSyroles();
				if (syuserSyroleSet != null && syuserSyroleSet.size() > 0) {
					List<SyuserSyrole> syuserSyroleList = syuserSyroleDao.find("from SyuserSyrole t where t.syuser=?", syuser);
					if (syuserSyroleList != null && syuserSyroleList.size() > 0) {
						for (SyuserSyrole syuserSyrole : syuserSyroleList) {
							syuserSyroleDao.delete(syuserSyrole);
						}
					}
				}
				userDao.delete(syuser);
			}
		}
	}

	public User getUserInfo(User user) {
		Syuser syuser = userDao.get(Syuser.class, user.getId());
		BeanUtils.copyProperties(syuser, user);
		String roleText = "";
		String resourcesText = "";
		Set<SyuserSyrole> syuserSyroleSet = syuser.getSyuserSyroles();
		if (syuserSyroleSet != null && syuserSyroleSet.size() > 0) {
			for (SyuserSyrole syuserSyrole : syuserSyroleSet) {
				if (!roleText.equals("")) {
					roleText += ",";
				}
				Syrole syrole = syuserSyrole.getSyrole();
				roleText += syrole.getText();

				Set<SyroleSyresources> syroleSyresourcesSet = syrole.getSyroleSyresourceses();
				if (syroleSyresourcesSet != null && syroleSyresourcesSet.size() > 0) {
					for (SyroleSyresources syroleSyresources : syroleSyresourcesSet) {
						if (!resourcesText.equals("")) {
							resourcesText += ",";
						}
						Syresources syresources = syroleSyresources.getSyresources();
						resourcesText += syresources.getText();
					}
				}
			}
		}
		user.setRoleText(roleText);
		user.setResourcesText(resourcesText);
		return user;
	}

	public User editUserInfo(User user) {
		if (user.getOldPassword() != null && !user.getOldPassword().trim().equals("") && user.getPassword() != null && !user.getPassword().trim().equals("")) {
			Syuser syuser = userDao.get("from Syuser t where t.id=? and t.password=?", user.getId(), Encrypt.e(user.getOldPassword()));
			if (syuser != null) {
				syuser.setPassword(Encrypt.e(user.getPassword()));
				return user;
			}
		}
		return null;
	}

	@Override
	public List<Syuser> getUserList() {
		return userDao.find("from Syuser");
	}


}
