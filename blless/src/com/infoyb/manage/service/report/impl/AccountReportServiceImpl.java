package com.cm.manage.service.report.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.manage.dao.report.IAccountReportDao;
import com.cm.manage.model.report.AccountReport;
import com.cm.manage.service.base.impl.BaseServiceImpl;
import com.cm.manage.service.report.IAccountReportService;
@Service("accountReportService")
public class AccountReportServiceImpl extends BaseServiceImpl implements
		IAccountReportService {
	@Autowired
	private IAccountReportDao accountReportDao;

	@Override
	public void insertAccountReport() {
		accountReportDao.insertAccountReport();
	}
	public List<AccountReport> accountReportByMonth(Integer year,Integer month,Integer memberType){
		return accountReportDao.accountReportByMonth(year, month, memberType);
	}

}
