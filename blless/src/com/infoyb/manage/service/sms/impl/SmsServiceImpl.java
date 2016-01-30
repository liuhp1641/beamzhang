package com.cm.manage.service.sms.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.dao.sms.ISmsDao;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.sms.ISmsService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.sms.MemberSms;
@Service("smsService")
public class SmsServiceImpl extends BaseServiceImpl implements ISmsService {

	@Autowired
	private ISmsDao smsDao;
	@Override
	public EasyuiDataGridJson smsDatagrid(EasyuiDataGrid dg, MemberSms sms) {
		return smsDao.smsDatagrid(dg,sms);
	}

	/**
	 * 短信发送
	 * @param sms
	 * @return
	 */
	public void sendSms(MemberSms sms){
		smsDao.sendSms(sms);
	}
}
