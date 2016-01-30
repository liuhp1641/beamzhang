package com.cm.manage.service.account;

import java.util.List;

import com.cm.manage.model.account.DrawTransferRecord;
import com.cm.manage.service.base.IBaseService;
import com.cm.manage.util.base.BusiException;

public interface IDrawBatchTransferService extends IBaseService{

	public DrawTransferRecord findByBatchNo(String batchNo) throws BusiException;
	public void updateTransInfo(DrawTransferRecord d,int status,String errorMsg);
	public List<DrawTransferRecord> getTransferByStatus(Integer status);
}
