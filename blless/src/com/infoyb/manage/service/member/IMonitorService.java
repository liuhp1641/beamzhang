package com.cm.manage.service.member;

import com.cm.manage.service.base.IBaseService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.member.MonitorVO;

public interface IMonitorService extends IBaseService {
	/**
	 * 设备监控列表
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
