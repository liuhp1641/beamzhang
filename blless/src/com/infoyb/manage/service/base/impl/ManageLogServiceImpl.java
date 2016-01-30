package com.cm.manage.service.base.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.dao.base.IManageLogDao;
import com.cm.manage.model.system.ManagesLog;
import com.cm.manage.service.base.IManageLogService;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.system.ManagesLogVO;
@Service("manageLogService")
public class ManageLogServiceImpl extends BaseServiceImpl implements
		IManageLogService {

	@Autowired
	private IManageLogDao manageLogDao;
	@Override
	public void manageLogSave(ManagesLog log) {
		manageLogDao.manageLogSave(log);
	}
	/**
	 * 操作日志列表
	 * @param dg
	 * @param log
	 * @return
	 */
	public EasyuiDataGridJson manageLogList(EasyuiDataGrid dg, ManagesLogVO log){
		return manageLogDao.manageLogList(dg, log);
	}
}
