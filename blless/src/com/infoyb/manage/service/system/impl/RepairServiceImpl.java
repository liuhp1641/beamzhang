package com.cm.manage.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.cm.manage.dao.system.IRepairDao;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.system.IRepairService;

/**
 * 修复数据库Service
 * 
 * @author 
 * 
 */
@Service("repairService")
public class RepairServiceImpl extends BaseServiceImpl implements IRepairService {

	@Autowired
	private IRepairDao repairDao;

	public void repair() {
		repairDao.repair();
	}

}
