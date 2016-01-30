package com.cm.manage.service.report;

import java.util.List;

import com.cm.manage.model.report.BetReport;
import com.cm.manage.service.base.IBaseService;

public interface IBetReportService extends IBaseService {
	
	public void insertBetReport();
	
    public List<BetReport> betReportByMonth(Integer year,Integer month);
	
	public List<BetReport> betReportDetail(Integer year,Integer month);

}
