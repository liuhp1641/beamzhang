package com.cm.manage.service.report;

import java.util.List;

import com.cm.manage.model.report.AccountReport;
import com.cm.manage.service.base.IBaseService;

public interface IAccountReportService extends IBaseService {
	public void insertAccountReport();
	public List<AccountReport> accountReportByMonth(Integer year,Integer month,Integer memberType);
}
