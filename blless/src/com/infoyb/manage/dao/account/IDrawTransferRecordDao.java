package com.cm.manage.dao.account;

import java.util.List;

import com.cm.manage.model.account.DrawTransferRecord;
import com.cm.manage.vo.account.BatchTransferVO;
import com.cm.manage.vo.base.EasyuiDataGrid;
import com.cm.manage.vo.base.EasyuiDataGridJson;

public interface IDrawTransferRecordDao {
	public void save(DrawTransferRecord record);
	public void update(DrawTransferRecord record);
	public DrawTransferRecord findByBatchNo(String batchNo);
	public EasyuiDataGridJson transferList(EasyuiDataGrid dg,BatchTransferVO transferVO);
	public List<DrawTransferRecord> getTransferByStatus(Integer status);
}
