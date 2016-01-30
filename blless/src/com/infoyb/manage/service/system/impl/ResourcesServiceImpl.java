package com.cm.manage.service.system.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cm.manage.dao.system.IResourcesDao;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.system.IResourcesService;
import com.cm.manage.vo.base.EasyuiTreeNode;
import com.cm.manage.vo.system.Resources;

/**
 * 资源Service实现类
 *
 * @author
 */
@Service("resourcesService")
public class ResourcesServiceImpl extends BaseServiceImpl implements IResourcesService {

    private static final Logger logger = Logger.getLogger(ResourcesServiceImpl.class);

    @Autowired
    private IResourcesDao resourcesDao;


    public List<EasyuiTreeNode> tree(String id) {
        return resourcesDao.tree(id);
    }

    public List<Resources> treegrid(String id) {
        return resourcesDao.treegrid(id);
    }

    public Resources add(Resources resources) {
        return resourcesDao.add(resources);
    }

    public void del(Resources resources) {
        resourcesDao.del(resources);
    }

    public Resources edit(Resources resources) {
        return resourcesDao.edit(resources);
    }

}
