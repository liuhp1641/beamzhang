package com.cm.manage.service.base;

import com.cm.manage.model.system.ManagesLog;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;
import com.cm.manage.vo.system.ManagesLogVO;

public interface IManageLogService extends IBaseService {
	/**
	 * 操作日志
	 * @param log
	 */
	public void manageLogSave(ManagesLog log);
	
	/**
	 * 操作日志列表
	 * @param dg
	 * @param log
	 * @return
	 */
	public EasyuiDataGridJson manageLogList(EasyuiDataGrid dg, ManagesLogVO log);
}
