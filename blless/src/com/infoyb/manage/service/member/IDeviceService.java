package com.cm.manage.service.member;

import com.cm.manage.service.base.IBaseService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.member.Customer;

public interface IDeviceService extends IBaseService {
	/**
	 * 设备列表
	 * @param dg
	 * @param customer
	 * @return
	 */
	public EasyuiDataGridJson deviceList(EasyuiDataGrid dg, Customer customer,boolean flag);
}
