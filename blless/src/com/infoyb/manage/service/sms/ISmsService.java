package com.cm.manage.service.sms;

import com.cm.manage.service.base.IBaseService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.sms.MemberSms;

public interface ISmsService extends IBaseService{
	/**
	 * 短信查询
	 * @param dg
	 * @param userName
	 * @param sms
	 * @return
	 */
	public EasyuiDataGridJson smsDatagrid(EasyuiDataGrid dg,MemberSms sms);

	/**
	 * 短信发送
	 * @param sms
	 * @return
	 */
	public void sendSms(MemberSms sms);
}
