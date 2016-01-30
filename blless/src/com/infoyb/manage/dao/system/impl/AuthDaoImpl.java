package com.cm.manage.dao.system.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cm.manage.dao.base.IBaseDao;
import com.cm.manage.dao.system.IAuthDao;
import com.cm.manage.model.system.Syresources;
import com.cm.manage.model.system.Syrole;
import com.cm.manage.model.system.SyroleSyresources;
import com.cm.manage.model.system.Syuser;
import com.cm.manage.model.system.SyuserSyrole;


@Repository("authDao")
public class AuthDaoImpl implements IAuthDao {

	@Autowired
	private IBaseDao<Syresources> resourcesDao;
	@Autowired
	private IBaseDao<Syuser> userDao;

	public List<Syresources> offResourcesList() {
		return resourcesDao.find("from Syresources t where t.onoff != '1'");
	}

	public Syresources getSyresourcesByRequestPath(String requestPath) {
		return resourcesDao.get("from Syresources t where t.src=?", requestPath);
	}

	public boolean checkAuth(String userId, String requestPath) {
		boolean b = false;
		Syuser syuser = userDao.get(Syuser.class, userId);
		Set<SyuserSyrole> syuserSyroles = syuser.getSyuserSyroles();// 用户和角色关系
		for (SyuserSyrole syuserSyrole : syuserSyroles) {
			Syrole syrole = syuserSyrole.getSyrole();
			Set<SyroleSyresources> syroleSyreources = syrole.getSyroleSyresourceses();// 角色和资源关系
			for (SyroleSyresources syroleSyreource : syroleSyreources) {
				Syresources syresources = syroleSyreource.getSyresources();
				if (syresources.getSrc() != null && requestPath.equals(syresources.getSrc())) {
					return true;
				}
			}
		}
		return b;
	}

}
