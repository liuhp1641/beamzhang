package com.cm.manage.dao.member;

import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.member.Customer;

public interface IDeviceDao {
	/**
	 * 设备列表
	 * @param dg
	 * @param customer
	 * @return
	 */
	public EasyuiDataGridJson deviceList(EasyuiDataGrid dg, Customer customer,boolean flag);
}
