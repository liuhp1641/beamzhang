package com.cm.manage.dao.sms;

import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.sms.MemberSms;

public interface ISmsDao {
	/**
	 * 短信查询
	 * @param dg
	 * @param userName
	 * @param sms
	 * @return
	 */
	public EasyuiDataGridJson smsDatagrid(EasyuiDataGrid dg, MemberSms sms);
	
	/**
	 * 短信发送
	 * @param sms
	 * @return
	 */
	public void sendSms(MemberSms sms);

}
