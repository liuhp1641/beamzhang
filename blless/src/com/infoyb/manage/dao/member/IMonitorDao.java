package com.cm.manage.dao.member;

import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.member.MonitorVO;

public interface IMonitorDao {
	/**
	 * 监控列表
	 * @param dg
	 * @param monitor
	 * @return
	 */
	public EasyuiDataGridJson monitorList(EasyuiDataGrid dg, MonitorVO monitor);
	
	/**
	 * ip监控列表
	 * @param dg
	 * @param monitor
	 * @return
	 */
	public EasyuiDataGridJson ipMonitorList(EasyuiDataGrid dg, MonitorVO monitor);
	
	/**
	 * 身份监控
	 * @param dg
	 * @param monitor
	 * @return
	 */
	public EasyuiDataGridJson cardCodeMonitorList(EasyuiDataGrid dg, MonitorVO monitor);
}
