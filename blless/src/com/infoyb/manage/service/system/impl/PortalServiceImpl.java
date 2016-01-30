package com.cm.manage.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cm.manage.dao.system.IPortalDao;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.system.IPortalService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.system.Portal;


/**
 * portal Service实现
 * 
 * @author 
 * 
 */
@Service("portalService")
public class PortalServiceImpl extends BaseServiceImpl implements IPortalService {

	@Autowired
	private IPortalDao portalDao;

	public EasyuiDataGridJson datagrid(EasyuiDataGrid dg, Portal portal) {
		
		return portalDao.datagrid(dg, portal);
	}

	public void del(String ids) {
		portalDao.del(ids);
	}

	public void edit(Portal portal) {
		portalDao.edit(portal);
	}

	public void add(Portal portal) {
		portalDao.add(portal);
	}

}
