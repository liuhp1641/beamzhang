package com.cm.manage.service.member.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.dao.member.IMonitorDao;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.member.IMonitorService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.member.MonitorVO;
@Service("monitorService")
public class MonitorServiceImpl extends BaseServiceImpl implements
		IMonitorService {

	@Autowired
	private IMonitorDao monitorDao;
	@Override
	public EasyuiDataGridJson monitorList(EasyuiDataGrid dg, MonitorVO monitor) {
		
		return monitorDao.monitorList(dg, monitor);
	}
	
	/**
	 * ip监控列表
	 * @param dg
	 * @param monitor
	 * @return
	 */
	public EasyuiDataGridJson ipMonitorList(EasyuiDataGrid dg, MonitorVO monitor){
		return monitorDao.ipMonitorList(dg, monitor);
	}

	/**
	 * 身份监控
	 * @param dg
	 * @param monitor
	 * @return
	 */
	public EasyuiDataGridJson cardCodeMonitorList(EasyuiDataGrid dg, MonitorVO monitor){
		return monitorDao.cardCodeMonitorList(dg, monitor);
	}
}
