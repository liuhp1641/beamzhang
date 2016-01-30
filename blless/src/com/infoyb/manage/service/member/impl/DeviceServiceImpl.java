package com.cm.manage.service.member.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.dao.member.IDeviceDao;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.member.IDeviceService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.member.Customer;

@Service("deviceService")
public class DeviceServiceImpl extends BaseServiceImpl implements
		IDeviceService {

	@Autowired
	private IDeviceDao deviceDao;
	@Override
	public EasyuiDataGridJson deviceList(EasyuiDataGrid dg, Customer customer,
			boolean flag) {
		return deviceDao.deviceList(dg, customer, flag);
	}

}
