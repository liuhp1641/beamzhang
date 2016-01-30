package com.cm.manage.dao.report;

import java.util.List;

import com.cm.manage.model.report.BetReport;

public interface IBetReportDao {
	
	public void insertBetReport();
	
	public List<BetReport> betReportByMonth(Integer year,Integer month);
	
	public List<BetReport> betReportDetail(Integer year,Integer month);

}
