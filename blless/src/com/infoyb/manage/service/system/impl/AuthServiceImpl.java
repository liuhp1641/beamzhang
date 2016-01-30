package com.cm.manage.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cm.manage.dao.system.IAuthDao;
import com.cm.manage.model.system.Syresources;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.system.IAuthService;

/**
 * 权限Service
 *
 * @author
 */
@Service("authService")
public class AuthServiceImpl extends BaseServiceImpl implements IAuthService {

    @Autowired
    private IAuthDao authDao;

    @Transactional(readOnly = true)
    public List<Syresources> offResourcesList() {
        return authDao.offResourcesList();
    }

    @Transactional(readOnly = true)
    public Syresources getSyresourcesByRequestPath(String requestPath) {
        return authDao.getSyresourcesByRequestPath(requestPath);
    }

    @Transactional(readOnly = true)
    public boolean checkAuth(String userId, String requestPath) {

        return authDao.checkAuth(userId, requestPath);
    }

}
