package com.cm.manage.service.account.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.dao.account.IDrawDao;
import com.cm.manage.dao.account.IDrawTransferRecordDao;
import com.cm.manage.model.account.DrawTransferRecord;
import com.cm.manage.service.account.IDrawBatchTransferService;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.util.base.BusiException;
@Service("drawBatchTransferService")
public class DrawBatchTransferServiceImpl extends BaseServiceImpl implements IDrawBatchTransferService {

	@Autowired
	private IDrawTransferRecordDao drawTransferDao;
	@Autowired
	private IDrawDao drawDao;
	@Override
	public DrawTransferRecord findByBatchNo(String batchNo) throws BusiException {
		if(batchNo == null || "".equals(batchNo)){
			throw new BusiException("3021","批次号为空");
		}
		DrawTransferRecord d = drawTransferDao.findByBatchNo(batchNo);
		return d;
	}
	@Override
	public void updateTransInfo(DrawTransferRecord d,int status,String errorMsg){
		drawTransferDao.save(d);
		drawDao.updateByBatchNo(d.getBatchNo(), status,errorMsg);
	}
	@Override
	public List<DrawTransferRecord> getTransferByStatus(Integer status) {
		return drawTransferDao.getTransferByStatus(status);
	}

}
