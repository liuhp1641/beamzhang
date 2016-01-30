package com.cm.manage.service.report.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.dao.report.IReportRechargeDao;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.report.IReportRechargeService;
import com.cm.manage.vo.report.ReportRechargeVO;

@Service("reportRechargeService")
public class ReportRechargeServiceImpl extends BaseServiceImpl implements
IReportRechargeService {

	@Autowired
	private IReportRechargeDao reportRechargeDao;
	@Override
	public boolean saveReportRecharge() {
		return reportRechargeDao.saveReportRecharge();
	}
	@Override
	public List<ReportRechargeVO> getReportRecharge(String year,String month) {
		return reportRechargeDao.getReportRecharge(year,month);
	}
	@Override
	public List<ReportRechargeVO> reportRechargeDetail(String year, String month) {
		return reportRechargeDao.getReportRechargeDetail(year,month);
	}
	@Override
	public Map<String, Object> prepareExcelData(String year, String month) {
		return reportRechargeDao.prepareExcelData(year,month);
	}
	

}
