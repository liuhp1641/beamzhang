package com.cm.manage.dao.report;

import java.util.List;

import com.cm.manage.model.report.AccountReport;


public interface IAccountReportDao {
	
	public void insertAccountReport();
	
	public List<AccountReport> accountReportByMonth(Integer year,Integer month,Integer memberType);
}
