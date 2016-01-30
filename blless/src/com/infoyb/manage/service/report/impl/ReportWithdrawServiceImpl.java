package com.cm.manage.service.report.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.dao.report.IReportWithdrawDao;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.report.IReportWithdrawService;
import com.cm.manage.vo.report.ReportWithdrawVO;

@Service("reportWithdrawService")
public class ReportWithdrawServiceImpl extends BaseServiceImpl implements IReportWithdrawService {

	@Autowired
	private IReportWithdrawDao reportWithdrawDao;
	@Override
	public boolean saveReportWithdraw() {
		return reportWithdrawDao.saveReportWithdraw();
	}
	@Override
	public List<ReportWithdrawVO> getReportWithdraw(String year,String month) {
		return reportWithdrawDao.getReportWithdraw(year,month);
	}
	@Override
	public List<ReportWithdrawVO> reportWithdrawDetail(String year, String month) {
		return reportWithdrawDao.getReportWithdrawDetail(year,month);
	}
	@Override
	public Map<String, Object> prepareExcelData(String year, String month) {
		return reportWithdrawDao.prepareExcelData(year,month);
	}
	

}
